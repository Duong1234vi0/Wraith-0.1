/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketPostSendEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

/** Helps stabilize elytra targeting movement. */
object ModuleElytraMotion : ClientModule("ElytraMotion", Category.MOVEMENT) {

    private val attackDistance by float("AttackDistance", 3.0f, 0.1f..5.0f)
    private val autoFirework by boolean("AutoFirework", false)
    private val matrixBypass by boolean("MatrixBypass", false)

    var freeze = false
        private set

    private var fireworkTimerNanos = System.nanoTime()

    init {
        // The raw return belongs to the action cycle, even if the module is disabled before it completes.
        ElytraMotionFireworkCycleOwner
    }

    override fun disable() {
        freeze = false
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: run {
            freeze = false
            return@handler
        }

        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
            return@handler
        }

        if (!player.isFallFlying) {
            freeze = false
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && hasFireworkIntervalElapsed()) {
                useFirework()
                fireworkTimerNanos = System.nanoTime()
            }
        } else if (autoFirework && target != null && getBps(player) <= 30.0 &&
            hasFireworkIntervalElapsed()) {
            useFirework()
            fireworkTimerNanos = System.nanoTime()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
        } else if (freeze) {
            // The live mixin replaces LocalPlayer.move's movement argument with this value.
            event.movement = Vec3.ZERO
        }
    }

    fun check(target: LivingEntity?, elytraTarget: ModuleElytraTarget): Boolean {
        val player = mc.player ?: return false
        return target != null && player.isFallFlying && !elytraTarget.shouldTarget(target) &&
            target.distanceTo(player) < attackDistance
    }

    private fun useFirework() {
        val player = mc.player ?: return
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            useFireworkFromHand(InteractionHand.OFF_HAND)
            return
        }

        val firework = findFireworkInventoryFirst() ?: return
        val currentSlot = player.inventory.selectedSlot
        if (currentSlot !in 0..8) {
            return
        }

        if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
            useFireworkWithOffhandSwap(firework.serverSlot)
            return
        }

        if (ElytraMotionFireworkCycleOwner.isPending ||
            ModuleEMaceHelper.hasPendingSelectedSlotFireworkCycle()
        ) {
            return
        }

        when (firework) {
            is FireworkSlot.Hotbar -> useDirectHotbarFirework(firework.hotbarSlot, currentSlot)
            is FireworkSlot.Inventory -> {
                val temporarySlot = currentSlot % 8 + 1
                useTemporaryInventoryFirework(firework.serverSlot, temporarySlot, currentSlot)
            }
        }
    }

    private fun useDirectHotbarFirework(fireworkSlot: Int, visibleSlot: Int) {
        val player = mc.player ?: return
        val level = mc.level ?: return
        val connection = mc.connection ?: return
        if (fireworkSlot !in 0..8 || visibleSlot !in 0..8 ||
            ElytraMotionFireworkCycleOwner.isPending) {
            return
        }

        if (fireworkSlot == visibleSlot) {
            useFireworkFromHand(InteractionHand.MAIN_HAND)
            return
        }

        val diagnosticsEnabled = ElytraMotionFireworkCycleOwner.claimDiagnosticCycle(
            source = ElytraMotionFireworkSource.HOTBAR,
            originalInventorySlot = fireworkSlot,
            temporaryHotbarSlot = null,
            fireworkHotbarSlot = fireworkSlot,
            capturedVisibleSlot = visibleSlot,
        )
        val createdTick = player.tickCount
        val createdNanos = System.nanoTime()
        connection.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        ElytraMotionFireworkCycleOwner.diagnostic(
            diagnosticsEnabled,
            "initial raw HELD sent slot=$fireworkSlot",
        )
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        ElytraMotionFireworkCycleOwner.diagnostic(
            diagnosticsEnabled,
            "raw USE_ITEM send call completed hand=MAIN_HAND",
        )

        check(
            ElytraMotionFireworkCycleOwner.arm(
                ElytraMotionFireworkCycle(
                    source = ElytraMotionFireworkSource.HOTBAR,
                    playerIdentity = player,
                    levelIdentity = level,
                    connectionIdentity = connection,
                    originalInventorySlot = fireworkSlot,
                    fireworkHotbarSlot = fireworkSlot,
                    capturedVisibleSlot = visibleSlot,
                    temporaryHotbarSlot = null,
                    inventorySwapInCompleted = false,
                    rawUseSent = true,
                    inventorySwapBackPending = false,
                    diagnosticsEnabled = diagnosticsEnabled,
                    createdTick = createdTick,
                    createdNanos = createdNanos,
                )
            )
        ) { "ElytraMotion Firework cycle ownership changed during synchronous Hotbar use" }
    }

    private fun useTemporaryInventoryFirework(
        originalInventorySlot: Int,
        temporaryHotbarSlot: Int,
        visibleSlot: Int,
    ) {
        val player = mc.player ?: return
        val level = mc.level ?: return
        val connection = mc.connection ?: return
        if (originalInventorySlot !in 9..35 || temporaryHotbarSlot !in 0..8 || visibleSlot !in 0..8 ||
            ElytraMotionFireworkCycleOwner.isPending) {
            return
        }

        val diagnosticsEnabled = ElytraMotionFireworkCycleOwner.claimDiagnosticCycle(
            source = ElytraMotionFireworkSource.MAIN_INVENTORY,
            originalInventorySlot = originalInventorySlot,
            temporaryHotbarSlot = temporaryHotbarSlot,
            fireworkHotbarSlot = temporaryHotbarSlot,
            capturedVisibleSlot = visibleSlot,
        )
        val createdTick = player.tickCount
        val createdNanos = System.nanoTime()
        if (!clickElytraMotionSwap(originalInventorySlot, temporaryHotbarSlot)) {
            ElytraMotionFireworkCycleOwner.diagnostic(
                diagnosticsEnabled,
                "cycle clear reason=inventory SWAP-in rejected",
            )
            return
        }

        ElytraMotionFireworkCycleOwner.diagnostic(
            diagnosticsEnabled,
            "inventory SWAP-in completed originalInventorySlot=$originalInventorySlot " +
                "temporaryHotbarSlot=$temporaryHotbarSlot",
        )
        connection.sendPacket(ServerboundSetCarriedItemPacket(temporaryHotbarSlot))
        ElytraMotionFireworkCycleOwner.diagnostic(
            diagnosticsEnabled,
            "initial raw HELD sent slot=$temporaryHotbarSlot",
        )
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        ElytraMotionFireworkCycleOwner.diagnostic(
            diagnosticsEnabled,
            "raw USE_ITEM send call completed hand=MAIN_HAND",
        )

        check(
            ElytraMotionFireworkCycleOwner.arm(
                ElytraMotionFireworkCycle(
                    source = ElytraMotionFireworkSource.MAIN_INVENTORY,
                    playerIdentity = player,
                    levelIdentity = level,
                    connectionIdentity = connection,
                    originalInventorySlot = originalInventorySlot,
                    fireworkHotbarSlot = temporaryHotbarSlot,
                    capturedVisibleSlot = visibleSlot,
                    temporaryHotbarSlot = temporaryHotbarSlot,
                    inventorySwapInCompleted = true,
                    rawUseSent = true,
                    inventorySwapBackPending = true,
                    diagnosticsEnabled = diagnosticsEnabled,
                    createdTick = createdTick,
                    createdNanos = createdNanos,
                )
            )
        ) { "ElytraMotion Firework cycle ownership changed during synchronous inventory use" }
    }

    private fun useFireworkWithOffhandSwap(sourceSlot: Int) {
        if (ElytraMotionFireworkCycleOwner.isPending ||
            ModuleEMaceHelper.hasPendingSelectedSlotFireworkCycle()
        ) {
            return
        }
        if (!clickElytraMotionSwap(sourceSlot, OFFHAND_SWAP_BUTTON)) {
            return
        }
        useFireworkFromHand(InteractionHand.OFF_HAND)
        clickElytraMotionSwap(sourceSlot, OFFHAND_SWAP_BUTTON)
    }

    private fun useFireworkFromHand(hand: InteractionHand) {
        val level = mc.level ?: return
        val rotation = useRotation()
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
    }

    private fun useRotation(): Rotation {
        return ModuleKillAura.activeServerFacingRotationForCurrentTarget()
            ?: mc.player?.rotation
            ?: Rotation.ZERO
    }

    private fun findFireworkInventoryFirst(): FireworkSlot? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Inventory(slot)
            }
        }
        for (slot in 8 downTo 0) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Hotbar(slot, HOTBAR_CONTAINER_OFFSET + slot)
            }
        }
        return null
    }

    internal fun clickElytraMotionSwap(slot: Int, button: Int): Boolean {
        val player = mc.player ?: return false
        if (slot !in player.screenHandler.slots.indices ||
            button !in 0..8 && button != OFFHAND_SWAP_BUTTON) {
            return false
        }

        interaction.clickSlot(player.screenHandler.syncId, slot, button, ContainerInput.SWAP, player)
        return true
    }

    private fun getBps(entity: LivingEntity): Double {
        val dx = entity.x - entity.xo
        val dy = entity.y - entity.yo
        val dz = entity.z - entity.zo
        return sqrt(dx * dx + dy * dy + dz * dz) * 20.0
    }

    private sealed class FireworkSlot(open val serverSlot: Int) {
        data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : FireworkSlot(serverSlot)
        data class Inventory(override val serverSlot: Int) : FireworkSlot(serverSlot)
    }

    private const val FIREWORK_INTERVAL_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
    private const val HOTBAR_CONTAINER_OFFSET = 36
    private const val OFFHAND_SWAP_BUTTON = 40

    private fun hasFireworkIntervalElapsed() =
        System.nanoTime() - fireworkTimerNanos >= FIREWORK_INTERVAL_MS * NANOS_PER_MILLISECOND

    internal fun hasPendingSelectedSlotFireworkCycle(): Boolean = ElytraMotionFireworkCycleOwner.isPending
}

private object ElytraMotionFireworkCycleOwner : EventListener {

    private val state = ElytraMotionFireworkCycleState()
    private var naturalBoundarySerial = 0L
    private var diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
    private var diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES

    override val running: Boolean
        get() = true

    val isPending: Boolean
        get() = state.pending != null

    fun claimDiagnosticCycle(
        source: ElytraMotionFireworkSource,
        originalInventorySlot: Int,
        temporaryHotbarSlot: Int?,
        fireworkHotbarSlot: Int,
        capturedVisibleSlot: Int,
    ): Boolean {
        if (diagnosticCyclesRemaining <= 0 || diagnosticMessagesRemaining <= 0) {
            return false
        }

        diagnosticCyclesRemaining--
        diagnostic(
            true,
            "cycle created source=$source originalInventorySlot=$originalInventorySlot " +
                "temporaryHotbarSlot=${temporaryHotbarSlot ?: "none"} " +
                "fireworkHotbarSlot=$fireworkHotbarSlot capturedVisibleSlot=$capturedVisibleSlot",
        )
        return true
    }

    fun arm(cycle: ElytraMotionFireworkCycle): Boolean = state.begin(cycle)

    fun diagnostic(enabled: Boolean, message: String) {
        if (!enabled || diagnosticMessagesRemaining <= 0) {
            return
        }

        diagnosticMessagesRemaining--
        logger.info("$DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} $message")
    }

    @Suppress("unused")
    private val postSendHandler = handler<OutgoingPacketPostSendEvent> { event ->
        when (val packet = event.packet) {
            is ServerboundClientTickEndPacket -> {
                naturalBoundarySerial++
                val pending = state.pending ?: return@handler
                if (state.observeNaturalBoundary(naturalBoundarySerial, mc.player?.tickCount ?: Int.MAX_VALUE)) {
                    diagnostic(
                        pending.diagnosticsEnabled,
                        "natural CLIENT_TICK_END post-send observed boundarySerial=$naturalBoundarySerial; " +
                            "restoration deferred to next GameTick HEAD",
                    )
                }
            }

            is ServerboundSetCarriedItemPacket -> {
                val pending = state.pending ?: return@handler
                if (pending.rawUseSent && !pending.boundaryObserved && !pending.heldRestorationCompleted) {
                    warning(
                        pending.diagnosticsEnabled,
                        "unexpected HELD_ITEM_CHANGE between USE_ITEM and natural boundary slot=${packet.slot}",
                    )
                }
            }
        }
    }

    @Suppress("unused")
    private val restoreHandler = handler<GameTickEvent>(priority = (FIRST_PRIORITY + 3).toShort()) {
        val pending = state.pending ?: return@handler
        val currentPlayer = mc.player
        val currentConnection = mc.connection
        val decision = state.prepareNextTickRestore(
            currentPlayerIdentity = currentPlayer,
            currentLevelIdentity = mc.level,
            currentConnectionIdentity = currentConnection,
            currentVisibleSlot = currentPlayer?.inventory?.selectedSlot ?: -1,
            currentTick = currentPlayer?.tickCount ?: Int.MAX_VALUE,
            nowNanos = System.nanoTime(),
        )

        when (decision) {
            ElytraMotionFireworkCycleDecision.WAIT -> Unit

            is ElytraMotionFireworkCycleDecision.DROP -> diagnostic(
                pending.diagnosticsEnabled,
                "cycle clear reason=${decision.reason}",
            )

            is ElytraMotionFireworkCycleDecision.RESTORE -> {
                // prepareNextTickRestore transitions held ownership before this nested send can re-enter observers.
                diagnostic(
                    pending.diagnosticsEnabled,
                    "next-GameTick HEAD raw HELD restore slot=${decision.slot} " +
                        "boundarySerial=${pending.boundarySerial}",
                )
                var clearReason = "held slot restored at next GameTick HEAD"
                try {
                    checkNotNull(currentConnection).sendPacket(ServerboundSetCarriedItemPacket(decision.slot))
                    check(state.markHeldRestorationCompleted(pending)) {
                        "ElytraMotion held restoration ownership was already completed"
                    }

                    if (pending.source == ElytraMotionFireworkSource.MAIN_INVENTORY) {
                        val temporarySlot = checkNotNull(pending.temporaryHotbarSlot)
                        check(state.claimInventorySwapBack(pending)) {
                            "ElytraMotion inventory SWAP-back ownership was already consumed"
                        }
                        val swappedBack = ModuleElytraMotion.clickElytraMotionSwap(
                            pending.originalInventorySlot,
                            temporarySlot,
                        )
                        clearReason = if (swappedBack) {
                            diagnostic(
                                pending.diagnosticsEnabled,
                                "inventory SWAP-back completed originalInventorySlot=${pending.originalInventorySlot} " +
                                    "temporaryHotbarSlot=$temporarySlot",
                            )
                            "held slot restored and inventory SWAP-back completed at next GameTick HEAD"
                        } else {
                            "inventory SWAP-back rejected after held restoration"
                        }
                    }
                } catch (throwable: Throwable) {
                    clearReason = "next-GameTick restoration failed: ${throwable.javaClass.simpleName}"
                    logger.error("$DIAGNOSTIC_PREFIX $clearReason", throwable)
                } finally {
                    clear(clearReason, pending)
                }
            }
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clear("world changed")
        resetDiagnosticBudget()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clear("disconnected")
        resetDiagnosticBudget()
    }

    private fun clear(reason: String, expected: ElytraMotionFireworkCycle? = null) {
        val pending = state.clear() ?: return
        check(expected == null || pending === expected) {
            "ElytraMotion Firework cycle ownership changed during cleanup"
        }
        diagnostic(pending.diagnosticsEnabled, "cycle clear reason=$reason")
    }

    private fun warning(enabled: Boolean, message: String) {
        if (!enabled || diagnosticMessagesRemaining <= 0) {
            return
        }

        diagnosticMessagesRemaining--
        logger.warn("$DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} $message")
    }

    private fun resetDiagnosticBudget() {
        diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
        diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
    }

    private const val DIAGNOSTIC_PREFIX = "[ElytraMotionFireworkOrder]"
    private const val MAX_DIAGNOSTIC_CYCLES = 4
    private const val MAX_DIAGNOSTIC_MESSAGES = MAX_DIAGNOSTIC_CYCLES * 12
}

internal enum class ElytraMotionFireworkSource {
    HOTBAR,
    MAIN_INVENTORY,
}

internal data class ElytraMotionFireworkCycle(
    val source: ElytraMotionFireworkSource,
    val playerIdentity: Any,
    val levelIdentity: Any,
    val connectionIdentity: Any,
    val originalInventorySlot: Int,
    val fireworkHotbarSlot: Int,
    val capturedVisibleSlot: Int,
    val temporaryHotbarSlot: Int?,
    var inventorySwapInCompleted: Boolean,
    var rawUseSent: Boolean,
    var boundaryObserved: Boolean = false,
    var boundarySerial: Long = Long.MIN_VALUE,
    var boundaryObservedTick: Int = Int.MIN_VALUE,
    var heldRestoreClaimed: Boolean = false,
    var heldRestorationCompleted: Boolean = false,
    var inventorySwapBackPending: Boolean,
    var inventorySwapBackCompleted: Boolean = false,
    val diagnosticsEnabled: Boolean,
    val createdTick: Int,
    val createdNanos: Long,
) {
    fun isTimedOut(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - createdTick > MAX_PENDING_TICKS || nowNanos - createdNanos > MAX_PENDING_NANOS

    private companion object {
        const val MAX_PENDING_TICKS = 20
        const val MAX_PENDING_NANOS = 1_000_000_000L
    }
}

internal sealed interface ElytraMotionFireworkCycleDecision {
    data object WAIT : ElytraMotionFireworkCycleDecision
    data class RESTORE(val slot: Int) : ElytraMotionFireworkCycleDecision
    data class DROP(val reason: String) : ElytraMotionFireworkCycleDecision
}

internal class ElytraMotionFireworkCycleState {
    var pending: ElytraMotionFireworkCycle? = null
        private set

    fun begin(cycle: ElytraMotionFireworkCycle): Boolean {
        if (pending != null || !cycle.isValid()) {
            return false
        }

        pending = cycle
        return true
    }

    private fun ElytraMotionFireworkCycle.isValid(): Boolean {
        if (fireworkHotbarSlot !in 0..8 || capturedVisibleSlot !in 0..8 || !rawUseSent ||
            boundaryObserved || heldRestoreClaimed || heldRestorationCompleted || inventorySwapBackCompleted) {
            return false
        }

        return when (source) {
            ElytraMotionFireworkSource.HOTBAR -> originalInventorySlot in 0..8 &&
                temporaryHotbarSlot == null && !inventorySwapInCompleted && !inventorySwapBackPending

            ElytraMotionFireworkSource.MAIN_INVENTORY -> originalInventorySlot in 9..35 &&
                temporaryHotbarSlot == fireworkHotbarSlot && inventorySwapInCompleted && inventorySwapBackPending
        }
    }

    fun observeNaturalBoundary(serial: Long, tick: Int): Boolean {
        val cycle = pending ?: return false
        if (!cycle.rawUseSent || cycle.boundaryObserved) {
            return false
        }

        cycle.boundaryObserved = true
        cycle.boundarySerial = serial
        cycle.boundaryObservedTick = tick
        return true
    }

    fun invalidReason(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentConnectionIdentity: Any?,
        currentTick: Int,
        nowNanos: Long,
    ): String? {
        val cycle = pending ?: return null
        return when {
            cycle.playerIdentity !== currentPlayerIdentity -> "local player replaced"
            cycle.levelIdentity !== currentLevelIdentity -> "level replaced"
            cycle.connectionIdentity !== currentConnectionIdentity -> "connection replaced"
            !cycle.isValidStoredState() -> "invalid stored cycle state"
            cycle.isTimedOut(currentTick, nowNanos) -> "cycle timeout"
            else -> null
        }
    }

    private fun ElytraMotionFireworkCycle.isValidStoredState(): Boolean =
        fireworkHotbarSlot in 0..8 && capturedVisibleSlot in 0..8 && rawUseSent &&
            when (source) {
                ElytraMotionFireworkSource.HOTBAR -> originalInventorySlot in 0..8 &&
                    temporaryHotbarSlot == null && !inventorySwapInCompleted && !inventorySwapBackPending

                ElytraMotionFireworkSource.MAIN_INVENTORY -> originalInventorySlot in 9..35 &&
                    temporaryHotbarSlot == fireworkHotbarSlot && inventorySwapInCompleted &&
                    (inventorySwapBackPending || inventorySwapBackCompleted)
            }

    fun prepareNextTickRestore(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentConnectionIdentity: Any?,
        currentVisibleSlot: Int,
        currentTick: Int,
        nowNanos: Long,
    ): ElytraMotionFireworkCycleDecision {
        val cycle = pending ?: return ElytraMotionFireworkCycleDecision.DROP("no pending cycle")
        val invalidReason = invalidReason(
            currentPlayerIdentity,
            currentLevelIdentity,
            currentConnectionIdentity,
            currentTick,
            nowNanos,
        )
        if (invalidReason != null) {
            pending = null
            return ElytraMotionFireworkCycleDecision.DROP(invalidReason)
        }
        if (!cycle.boundaryObserved || cycle.heldRestorationCompleted) {
            return ElytraMotionFireworkCycleDecision.WAIT
        }
        if (cycle.heldRestoreClaimed) {
            return ElytraMotionFireworkCycleDecision.WAIT
        }

        val restoreSlot = currentVisibleSlot.takeIf { it in 0..8 } ?: cycle.capturedVisibleSlot
        if (restoreSlot !in 0..8) {
            pending = null
            return ElytraMotionFireworkCycleDecision.DROP("no valid visible return slot")
        }

        cycle.heldRestoreClaimed = true
        return ElytraMotionFireworkCycleDecision.RESTORE(restoreSlot)
    }

    fun markHeldRestorationCompleted(expected: ElytraMotionFireworkCycle): Boolean {
        val cycle = pending ?: return false
        if (cycle !== expected || !cycle.heldRestoreClaimed || cycle.heldRestorationCompleted) {
            return false
        }

        cycle.heldRestorationCompleted = true
        return true
    }

    fun claimInventorySwapBack(expected: ElytraMotionFireworkCycle): Boolean {
        val cycle = pending ?: return false
        if (cycle !== expected || cycle.source != ElytraMotionFireworkSource.MAIN_INVENTORY ||
            !cycle.heldRestorationCompleted || !cycle.inventorySwapBackPending ||
            cycle.inventorySwapBackCompleted) {
            return false
        }

        cycle.inventorySwapBackPending = false
        cycle.inventorySwapBackCompleted = true
        return true
    }

    fun clear(): ElytraMotionFireworkCycle? = pending.also { pending = null }
}
