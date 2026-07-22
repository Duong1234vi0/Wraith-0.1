/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.event.events.BlockSlipperinessMultiplierEvent
import net.ccbluex.liquidbounce.event.events.BlockVelocityMultiplierEvent
import net.ccbluex.liquidbounce.event.events.DeathEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketPostSendEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.PlayerMovementTickEvent
import net.ccbluex.liquidbounce.event.events.PlayerSneakMultiplier
import net.ccbluex.liquidbounce.event.events.PlayerUseMultiplier
import net.ccbluex.liquidbounce.event.events.SprintEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.protocol.game.*
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.UseEffects
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.Vec2

/**
 * V-Core 0.8 NoSlow port.
 */
object ModuleNoSlow : ClientModule("NoSlow", Category.MOVEMENT) {

    private enum class Mode(override val choiceName: String) : NamedChoice {
        Grim("Grim"),
        GrimTick("GrimTick"),
        Matrix("Matrix"),
        Normal("Normal")
    }

    private val mode by enumChoice("Mode", Mode.Normal).onChanged {
        resetGrimTickController()
    }
    private val mainHand by boolean("MainHand", true)

    private object Selection : Configurable("Selection") {
        val food by boolean("Food", true)
        val projectiles by boolean("Projectiles", false)
        val shield by boolean("Shield", true)
        val soulSand by boolean("SoulSand", false)
        val honey by boolean("Honey", false)
        val slime by boolean("Slime", false)
        val ice by boolean("Ice", false)
        val sweetBerryBush by boolean("SweetBerryBush", false)
        val sneak by boolean("Sneak", false)
        val crawl by boolean("Crawl", false)
    }

    init {
        tree(Selection)
    }

    private val grimTickController = GrimTickNoSlowController()
    private var naturalTickEndSerial = 0L
    private var diagnosticSession: DiagnosticSession? = null
    private var diagnosticTick = DiagnosticTick()
    private var diagnosticBoundariesRemaining = 0
    private var diagnosticMessagesRemaining = 0

    private var preserveNextUseItemPacketRotation = false

    @Suppress("unused")
    private val tickUpdater = tickHandler {
        val localPlayer = mc.player ?: return@tickHandler

        if (!isUsingSlowItem()) {
            return@tickHandler
        }

        if (mode == Mode.Grim) {
            if (localPlayer.usedItemHand == InteractionHand.OFF_HAND) {
                val selectedSlot = localPlayer.inventory.selectedSlot
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot % 8 + 1))
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot % 7 + 2))
                network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot))
            } else if (mainHand && (localPlayer.ticksUsingItem <= 3 || localPlayer.tickCount % 2 == 0)) {
                interaction.sendSequencedPacket(world) { sequence ->
                    createGrimUseItemPacket(sequence)
                }
            }
        }
    }

    @Suppress("unused")
    private val grimTickMovementBeginHandler = handler<PlayerMovementTickEvent> {
        val localPlayer = mc.player

        if (mode != Mode.GrimTick || localPlayer == null) {
            resetGrimTickController("mode or player unavailable")
            return@handler
        }

        val usingSlowItem = isUsingSlowItem() && !localPlayer.isDead
        val eligible = usingSlowItem && allowsActiveItem() && allowsActiveHand()
        if (eligible) {
            beginDiagnosticSessionIfNeeded(localPlayer)
        }

        val bypass = grimTickController.beginCycle(
            tickEndSerial = naturalTickEndSerial + 1,
            active = usingSlowItem,
            eligible = eligible,
            playerIdentity = localPlayer,
            handIdentity = localPlayer.usedItemHand.takeIf { usingSlowItem },
            itemIdentity = localPlayer.useItem.takeIf { usingSlowItem },
            useSessionId = localPlayer.tickCount - localPlayer.ticksUsingItem,
            selectedSlot = localPlayer.inventory.selectedSlot,
            settingsFingerprint = settingsFingerprint(),
        )

        if (!eligible) {
            endDiagnosticSession("item use ended or became ineligible")
            return@handler
        }

        diagnosticTick = DiagnosticTick(
            tickSerial = naturalTickEndSerial + 1,
            playerTick = localPlayer.tickCount,
            phase = grimTickController.phase,
            bypass = bypass,
        )
        if (grimTickController.boundaryIssue == GrimTickNoSlowController.BoundaryIssue.MISSING_TICK_END) {
            diagnostic("tick-end missing before new movement begin; failed closed to recovery")
        }
        if (bypass) {
            diagnostic("fast cycle starts serial=${naturalTickEndSerial + 1}")
        }
    }

    @Suppress("unused")
    private val grimTickOutgoingPacketHandler = handler<OutgoingPacketSentEvent> { event ->
        mc.player?.takeIf {
            mode == Mode.GrimTick && isUsingSlowItem() && allowsActiveItem() && allowsActiveHand()
        }?.let(::beginDiagnosticSessionIfNeeded)

        if (!isDiagnosticActive()) {
            return@handler
        }

        when (val packet = event.packet) {
            is ServerboundMovePlayerPacket -> {
                diagnosticTick.movementPacket = packet.javaClass.simpleName
                diagnosticTick.movementHasPosition = packet.hasPosition()
                diagnosticTick.movementHasRotation = packet.hasRotation()
            }

            is ServerboundPlayerInputPacket -> {
                val input = packet.input
                diagnosticTick.playerInput = "f=${input.forward()} b=${input.backward()} l=${input.left()} " +
                    "r=${input.right()} jump=${input.jump()} shift=${input.shift()} sprint=${input.sprint()}"
                diagnosticTick.playerInputSprint = input.sprint()
            }

            is ServerboundPlayerCommandPacket -> when (packet.action) {
                ServerboundPlayerCommandPacket.Action.START_SPRINTING -> diagnosticTick.sprintCommand = "START"
                ServerboundPlayerCommandPacket.Action.STOP_SPRINTING -> diagnosticTick.sprintCommand = "STOP"
                else -> Unit
            }

            is ServerboundUseItemPacket -> diagnosticTick.actions += "use:${packet.hand}"
            is ServerboundPlayerActionPacket -> diagnosticTick.actions += "action:${packet.action}"
            is ServerboundSetCarriedItemPacket -> diagnosticTick.actions += "slot:${packet.slot}"
            is ServerboundClientTickEndPacket -> diagnosticTick.tickEndObserved = true
        }
    }

    @Suppress("unused")
    private val grimTickNaturalTickEndHandler = handler<OutgoingPacketPostSendEvent> { event ->
        if (event.packet !is ServerboundClientTickEndPacket) {
            return@handler
        }

        naturalTickEndSerial++
        val completedPhase = grimTickController.phase
        val completed = grimTickController.completeAtNaturalTickEnd(naturalTickEndSerial)

        if (isDiagnosticActive()) {
            if (!completed) {
                diagnostic("tick-end ignored issue=${grimTickController.boundaryIssue} serial=$naturalTickEndSerial")
            } else {
                when (completedPhase) {
                    GrimTickNoSlowController.Phase.RECOVERY -> diagnostic("recovery cycle completes serial=$naturalTickEndSerial")
                    GrimTickNoSlowController.Phase.RECOVERY_CONFIRM -> diagnostic(
                        "recovery-confirm cycle completes serial=$naturalTickEndSerial"
                    )
                    else -> Unit
                }
            }
            logDiagnosticTick()
        }
    }

    @Suppress("unused")
    private val grimTickSprintRecoveryHandler = handler<SprintEvent>(priority = Short.MIN_VALUE) { event ->
        if (mode != Mode.GrimTick || !grimTickController.isRecoveryCycle()) {
            return@handler
        }
        val before = event.sprint
        if (event.source == SprintEvent.Source.INPUT ||
            event.source == SprintEvent.Source.MOVEMENT_TICK ||
            event.source == SprintEvent.Source.NETWORK
        ) {
            event.sprint = false
            diagnosticTick.sprintBefore = diagnosticTick.sprintBefore ?: before
            diagnosticTick.sprintAfter = false
        }
    }

    @Suppress("unused")
    private val grimTickWorldChangeHandler = handler<WorldChangeEvent> {
        resetGrimTickController("world changed", resetNaturalSerial = true)
    }

    @Suppress("unused")
    private val grimTickDisconnectHandler = handler<DisconnectEvent> {
        resetGrimTickController("disconnected", resetNaturalSerial = true)
    }

    @Suppress("unused")
    private val grimTickDeathHandler = handler<DeathEvent> {
        resetGrimTickController("player died")
    }

    @Suppress("unused")
    private val useMultiplierHandler = handler<PlayerUseMultiplier> { event ->
        if (!shouldApplyMatrixInput()) {
            return@handler
        }

        val localPlayer = mc.player ?: return@handler
        val multiplier = matrixInputMultiplier(localPlayer.input.movementForward, localPlayer.input.movementSideways)

        event.forward = multiplier
        event.sideways = multiplier
    }

    @Suppress("unused")
    private val sneakMultiplierHandler = handler<PlayerSneakMultiplier> { event ->
        val localPlayer = mc.player ?: return@handler
        if (localPlayer.isVisuallyCrawling) {
            if (Selection.crawl) {
                event.multiplier = 1.0
            }
        } else if (Selection.sneak) {
            event.multiplier = 1.0
        }
    }

    @Suppress("unused")
    private val blockVelocityHandler = handler<BlockVelocityMultiplierEvent> { event ->
        if (Selection.soulSand && event.block === Blocks.SOUL_SAND) {
            event.multiplier = Blocks.DIRT.speedFactor
        }

        if (Selection.honey && event.block === Blocks.HONEY_BLOCK) {
            event.multiplier = Blocks.DIRT.speedFactor
        }
    }

    @Suppress("unused")
    private val blockSlipperinessHandler = handler<BlockSlipperinessMultiplierEvent> { event ->
        if (Selection.slime && event.block === Blocks.SLIME_BLOCK) {
            event.slipperiness = Blocks.DIRT.friction
        }

        if (Selection.ice && isIceBlock(event.block) && !mc.options.jumpKey.isPressed) {
            event.slipperiness = Blocks.DIRT.friction
        }
    }

    override fun enable() = resetGrimTickController()

    override fun disable() = resetGrimTickController()

    fun canNoSlow(): Boolean {
        val localPlayer = mc.player ?: return true

        return when (mode) {
            Mode.Matrix -> false
            Mode.GrimTick -> grimTickController.decision()
            Mode.Grim, Mode.Normal -> allowsActiveItem() && allowsActiveHand() && !isGrimNewBlocked(localPlayer)
        }
    }

    fun shouldBypassItemSlowdownForCurrentTick(): Boolean {
        val localPlayer = mc.player
        if (!running || localPlayer == null) {
            resetGrimTickController("module stopped or player unavailable")
            return false
        }

        return canNoSlow().also { bypass ->
            if (mode == Mode.GrimTick && bypass) {
                val input = localPlayer.input.moveVector
                diagnosticTick.inputBefore = diagnosticTick.inputBefore ?: input.describe()
                diagnosticTick.inputAfter = input.describe()
            }
        }
    }

    fun isGrimTickRecoveryCycle(): Boolean =
        running && mode == Mode.GrimTick && grimTickController.isRecoveryCycle()

    fun observeVanillaItemSlowdown(before: Vec2, after: Vec2) {
        if (!isDiagnosticActive() || mode != Mode.GrimTick) {
            return
        }
        diagnosticTick.inputBefore = before.describe()
        diagnosticTick.inputAfter = after.describe()
    }

    fun shouldBypassSneakSprintCheck(): Boolean {
        val localPlayer = mc.player ?: return false
        return running && Selection.sneak && localPlayer.isCrouching && !localPlayer.isVisuallyCrawling
    }

    fun shouldCancelSlimeStep(): Boolean = running && Selection.slime

    fun shouldCancelSweetBerryBushCollision(): Boolean = running && Selection.sweetBerryBush

    fun shouldPreserveUseItemPacketRotation(): Boolean = preserveNextUseItemPacketRotation

    fun shouldApplyMatrixKeyboardInput(): Boolean {
        val localPlayer = mc.player ?: return false
        return shouldApplyMatrixInput() && localPlayer.isPassenger
    }

    fun matrixInputMultiplier(movementForward: Float, movementSideways: Float): Float {
        val localPlayer = mc.player ?: return 1.0f
        val movingDiagonally = movementForward != 0.0f && movementSideways != 0.0f

        return if (localPlayer.onGround()) {
            if (movingDiagonally) 0.35f else 0.5f
        } else {
            if (movingDiagonally) 0.47f else 0.67f
        }
    }

    private fun isUsingSlowItem(): Boolean {
        val localPlayer = mc.player ?: return false
        return localPlayer.isUsingItem && !localPlayer.isPassenger && !localPlayer.isFallFlying
    }

    private fun allowsActiveItem(): Boolean {
        val localPlayer = mc.player ?: return false
        val active = localPlayer.useItem

        if (!Selection.food && active.components.has(DataComponents.FOOD)) {
            return false
        }

        if (!Selection.shield && active.item === Items.SHIELD) {
            return false
        }

        return Selection.projectiles ||
            active.item !== Items.CROSSBOW &&
            active.item !== Items.BOW &&
            active.item !== Items.TRIDENT
    }

    private fun allowsActiveHand(): Boolean {
        val localPlayer = mc.player ?: return false
        return mainHand || localPlayer.usedItemHand != InteractionHand.MAIN_HAND
    }

    private fun isGrimNewBlocked(localPlayer: net.minecraft.client.player.LocalPlayer): Boolean {
        val offhand = localPlayer.offhandItem
        return mode == Mode.Grim &&
            localPlayer.usedItemHand == InteractionHand.MAIN_HAND &&
            (offhand.components.has(DataComponents.FOOD) || offhand.item === Items.SHIELD)
    }

    private fun shouldApplyMatrixInput(): Boolean {
        val localPlayer = mc.player ?: return false
        return mode == Mode.Matrix && localPlayer.isUsingItem && !localPlayer.isFallFlying
    }

    private fun createGrimUseItemPacket(sequence: Int): ServerboundUseItemPacket {
        val localPlayer = mc.player
        preserveNextUseItemPacketRotation = true
        try {
            return ServerboundUseItemPacket(
                InteractionHand.OFF_HAND,
                sequence,
                localPlayer?.yaw ?: 0.0f,
                localPlayer?.pitch ?: 0.0f
            )
        } finally {
            preserveNextUseItemPacketRotation = false
        }
    }

    private fun settingsFingerprint(): Int {
        var result = mode.ordinal
        result = 31 * result + mainHand.hashCode()
        result = 31 * result + Selection.food.hashCode()
        result = 31 * result + Selection.projectiles.hashCode()
        result = 31 * result + Selection.shield.hashCode()
        return result
    }

    private fun resetGrimTickController(reason: String = "module or settings reset", resetNaturalSerial: Boolean = false) {
        endDiagnosticSession(reason)
        grimTickController.reset()
        if (resetNaturalSerial) {
            naturalTickEndSerial = 0L
        }
        preserveNextUseItemPacketRotation = false
    }

    private fun beginDiagnosticSessionIfNeeded(localPlayer: net.minecraft.client.player.LocalPlayer) {
        val next = DiagnosticSession(
            player = localPlayer,
            hand = localPlayer.usedItemHand,
            stack = localPlayer.useItem,
            useSessionId = localPlayer.tickCount - localPlayer.ticksUsingItem,
            selectedSlot = localPlayer.inventory.selectedSlot,
            settingsFingerprint = settingsFingerprint(),
        )
        val current = diagnosticSession
        if (current != null && current.sameAs(next)) {
            return
        }

        endDiagnosticSession("new use session")
        diagnosticSession = next
        diagnosticBoundariesRemaining = MAX_DIAGNOSTIC_BOUNDARIES
        diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
        diagnostic("use session starts hand=${next.hand} item=${itemId(next.stack)} slot=${next.selectedSlot}")
        diagnostic("phase resets to warm-up recovery")
    }

    private fun endDiagnosticSession(reason: String) {
        if (diagnosticSession != null) {
            diagnostic("phase resets reason=$reason")
        }
        diagnosticSession = null
        diagnosticTick = DiagnosticTick()
        diagnosticBoundariesRemaining = 0
        diagnosticMessagesRemaining = 0
    }

    private fun isDiagnosticActive() = diagnosticSession != null &&
        diagnosticBoundariesRemaining > 0 && diagnosticMessagesRemaining > 0 &&
        running && mode == Mode.GrimTick

    private fun logDiagnosticTick() {
        if (!isDiagnosticActive()) {
            return
        }
        val localPlayer = mc.player ?: return
        val session = diagnosticSession ?: return
        val stack = session.stack
        val multiplier = stack.getOrDefault(DataComponents.USE_EFFECTS, UseEffects.DEFAULT).speedMultiplier()
        diagnostic(
            "tick serial=${diagnosticTick.tickSerial} playerTick=${diagnosticTick.playerTick} " +
                "phase=${diagnosticTick.phase} bypass=${diagnosticTick.bypass} hand=${session.hand} " +
                "item=${itemId(stack)} ticksUsing=${localPlayer.ticksUsingItem} useMultiplier=$multiplier " +
                "input=${diagnosticTick.inputBefore ?: "unknown"}->${diagnosticTick.inputAfter ?: "unknown"} " +
                "sprint=${diagnosticTick.sprintBefore ?: localPlayer.isSprinting}->" +
                "${diagnosticTick.sprintAfter ?: localPlayer.isSprinting} move=${diagnosticTick.movementPacket} " +
                "pos=${diagnosticTick.movementHasPosition} rot=${diagnosticTick.movementHasRotation} " +
                "playerInput=${diagnosticTick.playerInput} inputSprint=${diagnosticTick.playerInputSprint} " +
                "sprintCmd=${diagnosticTick.sprintCommand} actions=${diagnosticTick.actions.joinToString(",")} " +
                "tickEnd=${diagnosticTick.tickEndObserved}"
        )
        diagnosticBoundariesRemaining--
        diagnosticTick = DiagnosticTick()
        if (diagnosticBoundariesRemaining == 0) {
            diagnostic("diagnostic boundary budget exhausted limit=$MAX_DIAGNOSTIC_BOUNDARIES")
        }
    }

    private fun diagnostic(message: String) {
        if (diagnosticSession == null || diagnosticMessagesRemaining <= 0) {
            return
        }
        diagnosticMessagesRemaining--
        logger.info("$DIAGNOSTIC_PREFIX $message")
    }

    private fun itemId(stack: net.minecraft.world.item.ItemStack) = BuiltInRegistries.ITEM.getKey(stack.item)

    private fun Vec2.describe() = "${x},${y}"

    private data class DiagnosticSession(
        val player: Any,
        val hand: InteractionHand,
        val stack: net.minecraft.world.item.ItemStack,
        val useSessionId: Int,
        val selectedSlot: Int,
        val settingsFingerprint: Int,
    ) {
        fun sameAs(other: DiagnosticSession) = player === other.player && hand == other.hand && stack === other.stack &&
            useSessionId == other.useSessionId && selectedSlot == other.selectedSlot &&
            settingsFingerprint == other.settingsFingerprint
    }

    private data class DiagnosticTick(
        val tickSerial: Long = Long.MIN_VALUE,
        val playerTick: Int = -1,
        val phase: GrimTickNoSlowController.Phase = GrimTickNoSlowController.Phase.INACTIVE,
        val bypass: Boolean = false,
        var inputBefore: String? = null,
        var inputAfter: String? = null,
        var sprintBefore: Boolean? = null,
        var sprintAfter: Boolean? = null,
        var movementPacket: String = "none",
        var movementHasPosition: Boolean? = null,
        var movementHasRotation: Boolean? = null,
        var playerInput: String = "unchanged",
        var playerInputSprint: Boolean? = null,
        var sprintCommand: String = "none",
        val actions: MutableList<String> = mutableListOf(),
        var tickEndObserved: Boolean = false,
    )

    private const val DIAGNOSTIC_PREFIX = "[GrimTickDiag]"
    private const val MAX_DIAGNOSTIC_BOUNDARIES = 180
    private const val MAX_DIAGNOSTIC_MESSAGES = MAX_DIAGNOSTIC_BOUNDARIES * 4

    private fun isIceBlock(block: net.minecraft.world.level.block.Block): Boolean {
        return block === Blocks.ICE ||
            block === Blocks.PACKED_ICE ||
            block === Blocks.BLUE_ICE ||
            block === Blocks.FROSTED_ICE
    }
}
