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
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.ScheduleInventoryActionEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.inventory.ClickInventoryAction
import net.ccbluex.liquidbounce.utils.inventory.InventoryActionChain
import net.ccbluex.liquidbounce.utils.inventory.InventoryActionMovementGate
import net.ccbluex.liquidbounce.utils.inventory.PlayerInventoryConstraints
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.inventoryClickSignature
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Item
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
        // A post-use server-slot restore must outlive the module toggle that initiated it.
        ElytraMotionFireworkCycle
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
        if (ElytraMotionFireworkCycle.isPending) {
            return
        }

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

        when (firework) {
            is FireworkSlot.Hotbar -> ElytraMotionFireworkCycle.startHotbar(
                firework.hotbarSlot,
                currentSlot,
            )
            is FireworkSlot.Inventory -> ElytraMotionFireworkCycle.startMainInventory(
                firework.serverSlot,
                currentSlot % 8 + 1,
                currentSlot,
            )
        }
    }

    private fun useFireworkWithOffhandSwap(sourceSlot: Int) {
        if (!clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)) {
            return
        }
        useFireworkFromHand(InteractionHand.OFF_HAND)
        clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)
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

    private fun clickSwap(slot: Int, button: Int): Boolean {
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

    /**
     * Owns the packet-only temporary Hotbar selection until Grim has observed a natural client tick boundary.
     * It is deliberately independent of [ModuleElytraMotion.running], so disabling the module cannot strand the
     * server on the Firework slot. Main-inventory clicks alone use the accepted inventory scheduler/movement gate.
     */
    private object ElytraMotionFireworkCycle : EventListener {

        private data class PendingInventoryAction(
            val request: InventoryActionMovementGate.Request,
            val action: ClickInventoryAction,
        )

        private val state = ElytraMotionFireworkCycleState()
        private val inventoryConstraints = PlayerInventoryConstraints(
            name = "ElytraMotionFirework",
            defaultStartDelay = 0..0,
            defaultClickDelay = 0..0,
            defaultCloseDelay = 0..0,
        )
        private val inventoryGate = InventoryActionMovementGate(
            ownerRunning = { state.pending != null },
            diagnosticPrefix = "[ElytraMotionFirework]",
        )

        private var pendingInventoryAction: PendingInventoryAction? = null
        private var expectedServerSlot = -1

        override val running: Boolean
            get() = true

        val isPending: Boolean
            get() = state.pending != null

        fun startHotbar(fireworkSlot: Int, returnSlot: Int): Boolean {
            val player = mc.player ?: return false
            val level = mc.level ?: return false
            if (fireworkSlot !in 0..8 || returnSlot !in 0..8 || isPending) {
                return false
            }

            val diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
            val startSteps = elytraMotionHotbarStartSteps(fireworkSlot, returnSlot)
            if (startSteps == listOf(ElytraMotionFireworkPacketStep.USE_ITEM)) {
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "ElytraMotion Firework already selected slot=$fireworkSlot; USE_ITEM only",
                )
                performUseWithoutRestore(diagnosticsEnabled, alreadySelected = true)
                return true
            }

            val cycle = ElytraMotionFireworkPendingCycle(
                playerIdentity = player,
                levelIdentity = level,
                fireworkSlot = fireworkSlot,
                capturedReturnSlot = returnSlot,
                inventorySourceSlot = null,
                originalTemporaryItem = null,
                diagnosticsEnabled = diagnosticsEnabled,
                createdTick = player.tickCount,
                createdNanos = System.nanoTime(),
            )
            if (!state.begin(cycle)) {
                return false
            }

            expectedServerSlot = returnSlot
            for (step in startSteps) {
                when (step) {
                    ElytraMotionFireworkPacketStep.HELD_FIREWORK ->
                        sendHeldItemChange(fireworkSlot, "select Firework slot", postActionSerial = null)
                    ElytraMotionFireworkPacketStep.USE_ITEM -> performPendingUse(cycle, alreadySelected = false)
                }
            }
            return true
        }

        fun startMainInventory(sourceSlot: Int, temporarySlot: Int, returnSlot: Int): Boolean {
            val player = mc.player ?: return false
            val level = mc.level ?: return false
            if (sourceSlot !in 9..35 || temporarySlot !in 0..8 || returnSlot !in 0..8 || isPending) {
                return false
            }

            val source = Slots.Inventory[sourceSlot - 9]
            if (source.itemStack.item != Items.FIREWORK_ROCKET) {
                return false
            }

            val cycle = ElytraMotionFireworkPendingCycle(
                playerIdentity = player,
                levelIdentity = level,
                fireworkSlot = temporarySlot,
                capturedReturnSlot = returnSlot,
                inventorySourceSlot = sourceSlot,
                originalTemporaryItem = Slots.Hotbar[temporarySlot].itemStack.item,
                diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle(),
                createdTick = player.tickCount,
                createdNanos = System.nanoTime(),
                stage = ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_IN,
            )
            if (!state.begin(cycle)) {
                return false
            }

            expectedServerSlot = returnSlot
            requestInventorySwap(cycle, restoring = false)
            return true
        }

        private fun performUseWithoutRestore(diagnosticsEnabled: Boolean, alreadySelected: Boolean) {
            val action = ActionTickBoundary.beginAction(
                owner = OWNER,
                kind = ActionTickBoundary.ActionKind.USE_ITEM,
                diagnosticsEnabled = diagnosticsEnabled,
            )
            useFireworkFromHand(InteractionHand.MAIN_HAND)
            ActionTickBoundary.actionCallCompleted(action)
            ActionTickBoundary.diagnostic(
                diagnosticsEnabled,
                "ElytraMotion Firework use completed alreadySelected=$alreadySelected " +
                    "observed=${ActionTickBoundary.wasActionObserved(action)}",
            )
        }

        private fun performPendingUse(cycle: ElytraMotionFireworkPendingCycle, alreadySelected: Boolean) {
            val action = ActionTickBoundary.beginAction(
                owner = OWNER,
                kind = ActionTickBoundary.ActionKind.USE_ITEM,
                diagnosticsEnabled = cycle.diagnosticsEnabled,
            )
            useFireworkFromHand(InteractionHand.MAIN_HAND)
            ActionTickBoundary.actionCallCompleted(action)
            state.completeUse(
                boundarySerial = ActionTickBoundary.currentSerial(),
                observed = ActionTickBoundary.wasActionObserved(action),
            )
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion Firework use slot=${cycle.fireworkSlot} return=${cycle.capturedReturnSlot} " +
                    "boundary=${cycle.useBoundarySerial} alreadySelected=$alreadySelected " +
                    "observed=${cycle.usePacketObserved}",
            )
        }

        @Suppress("unused")
        private val tickHandler = handler<GameTickEvent>(priority = (FIRST_PRIORITY + 2).toShort()) {
            val cycle = state.pending ?: return@handler
            val player = mc.player
            val level = mc.level

            if (player !== cycle.playerIdentity || level !== cycle.levelIdentity) {
                clear("player or level identity changed")
                return@handler
            }

            if (cycle.isTimedOut(player.tickCount, System.nanoTime())) {
                if (cycle.stage == ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY &&
                    ActionTickBoundary.hasPassed(cycle.useBoundarySerial)) {
                    restoreServerSlot(cycle, player)
                }
                if (state.pending != null) {
                    clear("bounded cycle timeout")
                }
                return@handler
            }

            when (cycle.stage) {
                ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_IN -> {
                    if (cycle.inventorySwapObserved && inventoryGate.state == InventoryActionMovementGate.State.IDLE) {
                        if (player.inventory.getStack(cycle.fireworkSlot).item != Items.FIREWORK_ROCKET) {
                            clear("Firework was not present after scheduled SWAP-in")
                            return@handler
                        }
                        sendHeldItemChange(cycle.fireworkSlot, "select temporary Firework slot", null)
                        performPendingUse(cycle, alreadySelected = cycle.fireworkSlot == cycle.capturedReturnSlot)
                    }
                }

                ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY -> {
                    if (ActionTickBoundary.hasPassed(cycle.useBoundarySerial)) {
                        restoreServerSlot(cycle, player)
                    } else if (cycle.lastWaitLoggedSerial != ActionTickBoundary.currentSerial()) {
                        cycle.lastWaitLoggedSerial = ActionTickBoundary.currentSerial()
                        ActionTickBoundary.diagnostic(
                            cycle.diagnosticsEnabled,
                            "ElytraMotion waiting for natural CLIENT_TICK_END useBoundary=${cycle.useBoundarySerial}",
                        )
                    }
                }

                ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK -> {
                    if (pendingInventoryAction == null && inventoryGate.state == InventoryActionMovementGate.State.IDLE) {
                        requestInventorySwap(cycle, restoring = true)
                    }
                    if (cycle.inventoryRestoreObserved && inventoryGate.state == InventoryActionMovementGate.State.IDLE) {
                        finish(cycle, "inventory Firework restored")
                    }
                }
            }
        }

        private fun restoreServerSlot(cycle: ElytraMotionFireworkPendingCycle, player: LocalPlayer) {
            val decision = state.resolveRestore(
                currentPlayerIdentity = player,
                currentLevelIdentity = mc.level,
                currentVisibleSlot = player.inventory.selectedSlot,
                currentSerial = ActionTickBoundary.currentSerial(),
                expectedServerSlot = expectedServerSlot,
            )
            when (decision) {
                ElytraMotionFireworkRestoreDecision.WAIT -> return
                ElytraMotionFireworkRestoreDecision.CLEAR -> completeHeldRestore(
                    cycle,
                    "server already synchronized",
                )
                is ElytraMotionFireworkRestoreDecision.SEND -> {
                    if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                            owner = OWNER,
                            actionBoundarySerial = cycle.useBoundarySerial,
                            diagnosticsEnabled = cycle.diagnosticsEnabled,
                        )) {
                        return
                    }
                    sendHeldItemChange(
                        decision.slot,
                        "restore visible slot after Firework use",
                        cycle.useBoundarySerial,
                    )
                    completeHeldRestore(cycle, "server slot restored")
                }
            }
        }

        private fun completeHeldRestore(cycle: ElytraMotionFireworkPendingCycle, reason: String) {
            if (cycle.inventorySourceSlot == null) {
                finish(cycle, reason)
            } else {
                cycle.stage = ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK
                pendingInventoryAction = null
                ActionTickBoundary.diagnostic(
                    cycle.diagnosticsEnabled,
                    "ElytraMotion held restore complete; waiting for scheduled inventory SWAP-back",
                )
            }
        }

        private fun sendHeldItemChange(slot: Int, purpose: String, postActionSerial: Long?) {
            val cycle = state.pending ?: return
            if (slot !in 0..8) {
                clear("invalid held slot $slot")
                return
            }

            val expectation = ActionTickBoundary.expectHeldItemChange(
                owner = OWNER,
                purpose = purpose,
                postActionBoundarySerial = postActionSerial,
                diagnosticsEnabled = cycle.diagnosticsEnabled,
            )
            if (!expectation.allowed) {
                return
            }
            network.sendPacket(ServerboundSetCarriedItemPacket(slot))
            ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        }

        private fun requestInventorySwap(cycle: ElytraMotionFireworkPendingCycle, restoring: Boolean) {
            val sourceSlot = cycle.inventorySourceSlot ?: return
            if (sourceSlot !in 9..35 || cycle.fireworkSlot !in 0..8) {
                clear("invalid inventory SWAP slots")
                return
            }

            val source = Slots.Inventory[sourceSlot - 9]
            val action = ClickInventoryAction.performSwap(
                from = source,
                to = Slots.Hotbar[cycle.fireworkSlot],
            ).withDetachedPlayerInventoryClickPermit()
            val expectedItem = if (restoring) cycle.originalTemporaryItem ?: Items.AIR else Items.FIREWORK_ROCKET
            val request = InventoryActionMovementGate.Request(
                mode = if (restoring) "ElytraMotionFireworkRestore" else "ElytraMotionFireworkBorrow",
                sourceSlot = source,
                expectedSourceItem = expectedItem,
                allowEmptySource = restoring && expectedItem == Items.AIR,
                switchMode = "SWAP",
                sendDirectly = false,
                clickSignatures = listOfNotNull(action.inventoryClickSignature()),
                expectsOffhandSwap = false,
                timeoutTicks = INVENTORY_GATE_TIMEOUT_TICKS,
                onFirstActionSent = {
                    if (restoring) cycle.inventoryRestoreObserved = true else cycle.inventorySwapObserved = true
                },
            )
            pendingInventoryAction = PendingInventoryAction(request, action)
            inventoryGate.request(request)
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion scheduled inventory ${if (restoring) "SWAP-back" else "SWAP-in"} " +
                    "source=$sourceSlot temporary=${cycle.fireworkSlot}",
            )
        }

        @Suppress("unused")
        private val scheduleHandler = handler<ScheduleInventoryActionEvent> { event ->
            val pending = pendingInventoryAction ?: return@handler
            if (!inventoryGate.isCurrent(pending.request) || !inventoryGate.isReady(pending.request) ||
                !pending.action.canPerformAction(inventoryConstraints) ||
                !inventoryGate.beginExecution(pending.request)) {
                return@handler
            }

            event.schedule.add(
                InventoryActionChain(
                    inventoryConstraints = inventoryConstraints,
                    actions = arrayOf(pending.action),
                    priority = Priority.IMPORTANT_FOR_USER_SAFETY,
                    precondition = { inventoryGate.isCurrent(pending.request) },
                )
            )
            pendingInventoryAction = null
        }

        @Suppress("unused")
        private val outgoingPacketHandler = handler<OutgoingPacketSentEvent> { event ->
            val cycle = state.pending ?: return@handler
            val packet = event.packet as? ServerboundSetCarriedItemPacket ?: return@handler
            if (packet.slot in 0..8) {
                expectedServerSlot = packet.slot
                ActionTickBoundary.diagnostic(
                    cycle.diagnosticsEnabled,
                    "ElytraMotion observed server held slot=${packet.slot}",
                )
            }
        }

        @Suppress("unused")
        private val worldChangeHandler = handler<WorldChangeEvent> {
            clear("world changed")
        }

        @Suppress("unused")
        private val disconnectHandler = handler<DisconnectEvent> {
            clear("disconnected")
        }

        private fun finish(cycle: ElytraMotionFireworkPendingCycle, reason: String) {
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion Firework cycle complete reason=$reason finalSlot=$expectedServerSlot " +
                    "useObserved=${cycle.usePacketObserved}",
            )
            state.clear()
            pendingInventoryAction = null
            expectedServerSlot = -1
        }

        private fun clear(reason: String) {
            state.pending?.let { cycle ->
                ActionTickBoundary.diagnostic(
                    cycle.diagnosticsEnabled,
                    "ElytraMotion Firework cycle cleared reason=$reason",
                )
            }
            inventoryGate.cancel(reason)
            state.clear()
            pendingInventoryAction = null
            expectedServerSlot = -1
        }

        private const val OWNER = "ElytraMotionFirework"
        private const val INVENTORY_GATE_TIMEOUT_TICKS = 40
    }
}

internal enum class ElytraMotionFireworkPacketStep {
    HELD_FIREWORK,
    USE_ITEM,
}

internal fun elytraMotionHotbarStartSteps(fireworkSlot: Int, visibleSlot: Int): List<ElytraMotionFireworkPacketStep> =
    if (fireworkSlot == visibleSlot) {
        listOf(ElytraMotionFireworkPacketStep.USE_ITEM)
    } else {
        listOf(ElytraMotionFireworkPacketStep.HELD_FIREWORK, ElytraMotionFireworkPacketStep.USE_ITEM)
    }

internal enum class ElytraMotionFireworkCycleStage {
    WAITING_FOR_SWAP_IN,
    WAITING_FOR_BOUNDARY,
    WAITING_FOR_SWAP_BACK,
}

internal data class ElytraMotionFireworkPendingCycle(
    val playerIdentity: Any,
    val levelIdentity: Any,
    val fireworkSlot: Int,
    val capturedReturnSlot: Int,
    val inventorySourceSlot: Int?,
    val originalTemporaryItem: Item?,
    val diagnosticsEnabled: Boolean,
    val createdTick: Int,
    val createdNanos: Long,
    var useBoundarySerial: Long = Long.MIN_VALUE,
    var usePacketObserved: Boolean = false,
    var stage: ElytraMotionFireworkCycleStage = ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY,
    var inventorySwapObserved: Boolean = false,
    var inventoryRestoreObserved: Boolean = false,
    var lastWaitLoggedSerial: Long = Long.MIN_VALUE,
) {
    fun isTimedOut(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - createdTick > MAX_PENDING_TICKS || nowNanos - createdNanos > MAX_PENDING_NANOS

    private companion object {
        const val MAX_PENDING_TICKS = 100
        const val MAX_PENDING_NANOS = 10_000_000_000L
    }
}

internal sealed interface ElytraMotionFireworkRestoreDecision {
    data object WAIT : ElytraMotionFireworkRestoreDecision
    data object CLEAR : ElytraMotionFireworkRestoreDecision
    data class SEND(val slot: Int) : ElytraMotionFireworkRestoreDecision
}

internal class ElytraMotionFireworkCycleState {
    var pending: ElytraMotionFireworkPendingCycle? = null
        private set

    fun begin(cycle: ElytraMotionFireworkPendingCycle): Boolean {
        if (pending != null || cycle.fireworkSlot !in 0..8 || cycle.capturedReturnSlot !in 0..8) {
            return false
        }
        pending = cycle
        return true
    }

    fun completeUse(boundarySerial: Long, observed: Boolean) {
        pending?.apply {
            useBoundarySerial = boundarySerial
            usePacketObserved = observed
            stage = ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY
        }
    }

    fun resolveRestore(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentVisibleSlot: Int,
        currentSerial: Long,
        expectedServerSlot: Int,
    ): ElytraMotionFireworkRestoreDecision {
        val cycle = pending ?: return ElytraMotionFireworkRestoreDecision.CLEAR
        if (currentPlayerIdentity !== cycle.playerIdentity || currentLevelIdentity !== cycle.levelIdentity) {
            clear()
            return ElytraMotionFireworkRestoreDecision.CLEAR
        }
        if (cycle.useBoundarySerial == Long.MIN_VALUE || currentSerial <= cycle.useBoundarySerial) {
            return ElytraMotionFireworkRestoreDecision.WAIT
        }

        val restoreSlot = currentVisibleSlot.takeIf { it in 0..8 } ?: cycle.capturedReturnSlot
        if (restoreSlot !in 0..8 || expectedServerSlot == restoreSlot) {
            return ElytraMotionFireworkRestoreDecision.CLEAR
        }
        return ElytraMotionFireworkRestoreDecision.SEND(restoreSlot)
    }

    fun clear() {
        pending = null
    }
}
