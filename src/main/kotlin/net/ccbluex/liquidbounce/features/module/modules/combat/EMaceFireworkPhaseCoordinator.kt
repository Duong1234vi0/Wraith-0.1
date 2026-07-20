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
@file:Suppress("MagicNumber")

package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketPostSendEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.movement.ModuleElytraMotion
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundSwingPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items

/**
 * Owns only the packet-phase adaptation for the two raw V-Core EMace Firework profiles.
 * The observer remains live while modules are disabled so a sent use can finish its safe restoration.
 */
object EMaceFireworkPhaseCoordinator : EventListener {

    private val state = EMaceFireworkCycleState()
    private var lastAcceptedRequestSerial = Long.MIN_VALUE
    private var diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
    private var diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
    private var emittingInitialHeldSlot: Int? = null

    override val running: Boolean
        get() = true

    val isPending: Boolean
        get() = state.pending != null

    val ownsSelectedSlotCycle: Boolean
        get() = state.pending?.plan?.requiresSelectedSlotOwnership == true

    fun requestPeakAssist(emaceOwned: Boolean): Boolean = request(
        profile = EMaceFireworkProfile.PEAK_ASSIST,
        emaceOwned = emaceOwned,
    )

    fun requestFireworkVector(): Boolean = request(
        profile = EMaceFireworkProfile.FIREWORK_VECTOR,
        emaceOwned = true,
    )

    fun onEMaceDisabled() {
        val pending = state.pending ?: return
        if (!pending.rawUseSent) {
            clear("EMace disabled before queued use", pending)
        }
    }

    private fun request(profile: EMaceFireworkProfile, emaceOwned: Boolean): Boolean {
        val player = mc.player ?: return false
        val level = mc.level ?: return false
        val connection = mc.connection ?: return false
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return false
        }
        if (state.pending != null || lastAcceptedRequestSerial == ActionTickBoundary.currentSerial()) {
            return false
        }

        val plan = resolvePlan(profile) ?: return false
        if (plan.requiresSelectedSlotOwnership && ModuleElytraMotion.hasPendingSelectedSlotFireworkCycle()) {
            return false
        }

        val attackConflict = ActionTickBoundary.hasObservedAttackInCurrentBoundary()
        val useConflict = ActionTickBoundary.hasObservedUseInCurrentBoundary()
        val queued = plan.requiresSelectedSlotOwnership &&
            ActionTickBoundary.hasHeldItemChangeConflictInCurrentBoundary()
        val diagnosticsEnabled = claimDiagnosticCycle()
        val cycle = EMaceFireworkCycle(
            plan = plan,
            playerIdentity = player,
            levelIdentity = level,
            connectionIdentity = connection,
            targetIdentity = if (emaceOwned) ModuleKillAura.targetTracker.target else null,
            emaceOwned = emaceOwned,
            stage = EMaceFireworkCycleStage.WAITING_TO_USE_AFTER_CONFLICT,
            requestBoundarySerial = ActionTickBoundary.currentSerial(),
            requestTick = player.tickCount,
            createdNanos = System.nanoTime(),
            conflictingAttackObserved = attackConflict,
            conflictingUseObserved = useConflict,
            queuedAfterConflict = queued,
            diagnosticsEnabled = diagnosticsEnabled,
        )
        if (!state.begin(cycle)) {
            return false
        }

        lastAcceptedRequestSerial = ActionTickBoundary.currentSerial()
        diagnostic(
            cycle,
            "request accepted profile=${plan.profile} source=${plan.source} requestTick=${cycle.requestTick} " +
                "requestSerial=${cycle.requestBoundarySerial} attackConflict=$attackConflict " +
                "useConflict=$useConflict queued=$queued sourceSlot=${plan.sourceSlot} " +
                "fireworkSlot=${plan.fireworkHotbarSlot ?: "none"} temporarySlot=${plan.temporaryHotbarSlot ?: "none"}",
        )

        if (queued) {
            return true
        }

        return executeUse(cycle)
    }

    @Suppress("unused")
    private val preAuraHandler = handler<GameTickEvent>(priority = EMACE_FIREWORK_PRE_AURA_PRIORITY) {
        val cycle = state.pending ?: return@handler
        val decision = state.preparePreAura(
            currentPlayerIdentity = mc.player,
            currentLevelIdentity = mc.level,
            currentConnectionIdentity = mc.connection,
            currentTargetIdentity = ModuleKillAura.targetTracker.target,
            emaceRunning = ModuleEMaceHelper.running,
            currentVisibleSlot = mc.player?.inventory?.selectedSlot ?: -1,
            currentBoundarySerial = ActionTickBoundary.currentSerial(),
            currentTick = mc.player?.tickCount ?: Int.MAX_VALUE,
            nowNanos = System.nanoTime(),
        )

        when (decision) {
            EMaceFireworkCycleDecision.WAIT -> Unit
            EMaceFireworkCycleDecision.EXECUTE_USE -> {
                if (cycle.plan.requiresSelectedSlotOwnership &&
                    ModuleElytraMotion.hasPendingSelectedSlotFireworkCycle()
                ) {
                    diagnostic(cycle, "queued use still waiting for ElytraMotion selected-slot ownership")
                    return@handler
                }
                executeUse(cycle)
            }

            is EMaceFireworkCycleDecision.RESTORE -> restoreAndClear(cycle, decision)
            is EMaceFireworkCycleDecision.DROP -> diagnostic(cycle, "cycle clear reason=${decision.reason}")
        }
    }

    @Suppress("unused")
    private val postSendHandler = handler<OutgoingPacketPostSendEvent> { event ->
        when (val packet = event.packet) {
            is ServerboundClientTickEndPacket -> {
                val cycle = state.pending ?: return@handler
                if (state.observeNaturalBoundary(
                        serial = ActionTickBoundary.currentSerial(),
                        tick = mc.player?.tickCount ?: Int.MAX_VALUE,
                    )
                ) {
                    diagnostic(
                        cycle,
                        "natural boundary observed serial=${ActionTickBoundary.currentSerial()}; " +
                            "post-send emitted no action or held packet",
                    )
                }
            }

            is ServerboundSetCarriedItemPacket -> {
                if (emittingInitialHeldSlot == packet.slot) {
                    state.pending?.initialHeldSent = true
                    return@handler
                }

                val cycle = state.pending ?: return@handler
                if (cycle.rawUseSent && !cycle.postUseBoundaryObserved) {
                    warning(cycle, "unexpected HELD_ITEM_CHANGE between use and boundary slot=${packet.slot}")
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

    private fun executeUse(cycle: EMaceFireworkCycle): Boolean {
        if (state.pending !== cycle || !isPlanStillValid(cycle.plan)) {
            clear("Firework source changed before use", cycle)
            return false
        }

        val plan = cycle.plan
        if (plan.inventorySwapInRequired) {
            val button = if (plan.source == EMaceFireworkSource.OFFHAND_SWAP) {
                OFFHAND_SWAP_BUTTON
            } else {
                checkNotNull(plan.temporaryHotbarSlot)
            }
            if (!clickSwap(plan.sourceSlot, button)) {
                clear("inventory SWAP-in rejected", cycle)
                return false
            }
            cycle.inventorySwapInCompleted = true
            diagnostic(cycle, "SWAP-in completed sourceSlot=${plan.sourceSlot} button=$button")
            if (plan.closeAfterSwap) {
                sendCloseContainer()
                diagnostic(cycle, "CLOSE sent after SWAP-in")
            }
        }

        if (plan.initialHeldRequired) {
            val fireworkSlot = checkNotNull(plan.fireworkHotbarSlot)
            emittingInitialHeldSlot = fireworkSlot
            try {
                network.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
            } finally {
                emittingInitialHeldSlot = null
            }
            if (!cycle.initialHeldSent) {
                rollbackBeforeUse(cycle, "initial HELD packet cancelled")
                return false
            }
            diagnostic(cycle, "initial HELD sent slot=$fireworkSlot")
        }

        val action = ActionTickBoundary.beginAction(
            owner = "EMaceFirework/${plan.profile}",
            kind = ActionTickBoundary.ActionKind.USE_ITEM,
            diagnosticsEnabled = cycle.diagnosticsEnabled,
        )
        useRawFirework(plan.useHand)
        ActionTickBoundary.actionCallCompleted(action)
        if (!ActionTickBoundary.wasActionObserved(action)) {
            ActionTickBoundary.cancelAction(action)
            rollbackBeforeUse(cycle, "raw USE_ITEM packet cancelled")
            return false
        }

        cycle.rawUseSent = true
        cycle.useBoundarySerial = ActionTickBoundary.currentSerial()
        state.markUseSent(cycle)
        diagnostic(cycle, "raw USE_ITEM sent hand=${plan.useHand} useSerial=${cycle.useBoundarySerial}")

        if (plan.swingRequired) {
            network.sendPacket(ServerboundSwingPacket(plan.useHand))
            cycle.swingSent = true
            diagnostic(cycle, "swing sent hand=${plan.useHand}")
        }

        if (!plan.requiresPostUseRestoration) {
            clear("raw hand use completed without held or inventory restoration", cycle)
        }
        return true
    }

    private fun restoreAndClear(
        cycle: EMaceFireworkCycle,
        decision: EMaceFireworkCycleDecision.RESTORE,
    ) {
        var reason = "post-use restoration completed"
        try {
            decision.heldSlot?.let { slot ->
                network.sendPacket(ServerboundSetCarriedItemPacket(slot))
                cycle.heldRestorationCompleted = true
                diagnostic(cycle, "next-GameTick HEAD raw HELD restore slot=$slot")
            }

            if (decision.swapBack) {
                val button = when (cycle.plan.source) {
                    EMaceFireworkSource.MAIN_INVENTORY -> checkNotNull(cycle.plan.temporaryHotbarSlot)
                    EMaceFireworkSource.OFFHAND_SWAP -> OFFHAND_SWAP_BUTTON
                    else -> error("Unexpected SWAP-back source ${cycle.plan.source}")
                }
                if (clickSwap(cycle.plan.sourceSlot, button)) {
                    cycle.inventorySwapBackCompleted = true
                    diagnostic(cycle, "SWAP-back completed sourceSlot=${cycle.plan.sourceSlot} button=$button")
                    if (cycle.plan.closeAfterSwap) {
                        sendCloseContainer()
                        diagnostic(cycle, "CLOSE sent after SWAP-back")
                    }
                } else {
                    reason = "inventory SWAP-back rejected after boundary"
                }
            }
        } catch (throwable: Throwable) {
            reason = "restoration failed: ${throwable.javaClass.simpleName}"
            logger.error("$DIAGNOSTIC_PREFIX $reason", throwable)
        } finally {
            clear(reason, cycle)
        }
    }

    private fun rollbackBeforeUse(cycle: EMaceFireworkCycle, reason: String) {
        try {
            if (cycle.initialHeldSent) {
                network.sendPacket(ServerboundSetCarriedItemPacket(cycle.plan.capturedVisibleSlot))
            }
            if (cycle.inventorySwapInCompleted && cycle.plan.inventorySwapBackRequired) {
                val button = if (cycle.plan.source == EMaceFireworkSource.OFFHAND_SWAP) {
                    OFFHAND_SWAP_BUTTON
                } else {
                    checkNotNull(cycle.plan.temporaryHotbarSlot)
                }
                clickSwap(cycle.plan.sourceSlot, button)
                if (cycle.plan.closeAfterSwap) {
                    sendCloseContainer()
                }
            }
        } finally {
            clear(reason, cycle)
        }
    }

    private fun resolvePlan(profile: EMaceFireworkProfile): EMaceFireworkPlan? {
        val player = mc.player ?: return null
        val visibleSlot = player.inventory.selectedSlot.takeIf { it in 0..8 } ?: return null
        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            return EMaceFireworkPlan.directHand(profile, InteractionHand.OFF_HAND, visibleSlot)
        }

        return when (profile) {
            EMaceFireworkProfile.PEAK_ASSIST -> {
                val found = findPeakAssistFirework() ?: return null
                if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
                    EMaceFireworkPlan.offhandSwap(profile, found.serverSlot, visibleSlot)
                } else when (found) {
                    is EMaceLocatedFirework.Hotbar -> if (found.hotbarSlot == visibleSlot) {
                        EMaceFireworkPlan.directHand(profile, InteractionHand.MAIN_HAND, visibleSlot)
                    } else {
                        EMaceFireworkPlan.hotbar(profile, found.hotbarSlot, visibleSlot)
                    }
                    is EMaceLocatedFirework.Inventory -> EMaceFireworkPlan.mainInventory(
                        profile,
                        found.serverSlot,
                        visibleSlot % 8 + 1,
                        visibleSlot,
                    )
                }
            }

            EMaceFireworkProfile.FIREWORK_VECTOR -> {
                findVectorHotbar()?.let { found ->
                    return if (found.hotbarSlot == visibleSlot) {
                        EMaceFireworkPlan.directHand(profile, InteractionHand.MAIN_HAND, visibleSlot)
                    } else {
                        EMaceFireworkPlan.hotbar(profile, found.hotbarSlot, visibleSlot)
                    }
                }
                val found = findVectorInventory() ?: return null
                if (player.screenHandler !== player.inventoryMenu) {
                    return null
                }
                EMaceFireworkPlan.mainInventory(profile, found.serverSlot, visibleSlot, visibleSlot)
            }
        }
    }

    private fun findPeakAssistFirework(): EMaceLocatedFirework? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return EMaceLocatedFirework.Inventory(slot)
            }
        }
        for (slot in 8 downTo 0) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return EMaceLocatedFirework.Hotbar(slot, HOTBAR_CONTAINER_OFFSET + slot)
            }
        }
        return null
    }

    private fun findVectorHotbar(): EMaceLocatedFirework.Hotbar? {
        val player = mc.player ?: return null
        for (slot in 0..8) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return EMaceLocatedFirework.Hotbar(slot, HOTBAR_CONTAINER_OFFSET + slot)
            }
        }
        return null
    }

    private fun findVectorInventory(): EMaceLocatedFirework.Inventory? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return EMaceLocatedFirework.Inventory(slot)
            }
        }
        return null
    }

    private fun isPlanStillValid(plan: EMaceFireworkPlan): Boolean {
        val player = mc.player ?: return false
        if (plan.profile == EMaceFireworkProfile.FIREWORK_VECTOR &&
            plan.source == EMaceFireworkSource.MAIN_INVENTORY && player.screenHandler !== player.inventoryMenu
        ) {
            return false
        }
        return when (plan.source) {
            EMaceFireworkSource.OFFHAND -> player.offhandItem.item == Items.FIREWORK_ROCKET
            EMaceFireworkSource.HOTBAR -> player.inventory.getStack(plan.sourceSlot).item == Items.FIREWORK_ROCKET
            EMaceFireworkSource.MAIN_INVENTORY ->
                player.inventory.getStack(plan.sourceSlot).item == Items.FIREWORK_ROCKET
            EMaceFireworkSource.OFFHAND_SWAP -> {
                val inventorySlot = if (plan.sourceSlot >= HOTBAR_CONTAINER_OFFSET) {
                    plan.sourceSlot - HOTBAR_CONTAINER_OFFSET
                } else {
                    plan.sourceSlot
                }
                inventorySlot in 0..35 && player.inventory.getStack(inventorySlot).item == Items.FIREWORK_ROCKET
            }
        }
    }

    private fun useRawFirework(hand: InteractionHand) {
        val level = mc.level ?: return
        val rotation = ModuleKillAura.activeServerFacingRotationForCurrentTarget() ?: mc.player?.rotation ?: return
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
    }

    private fun clickSwap(slot: Int, button: Int): Boolean {
        val player = mc.player ?: return false
        if (slot !in player.screenHandler.slots.indices ||
            button !in 0..8 && button != OFFHAND_SWAP_BUTTON
        ) {
            return false
        }
        interaction.clickSlot(player.screenHandler.syncId, slot, button, ContainerInput.SWAP, player)
        return true
    }

    private fun sendCloseContainer() {
        val player = mc.player ?: return
        network.sendPacket(ServerboundContainerClosePacket(player.screenHandler.syncId))
    }

    private fun clear(reason: String, expected: EMaceFireworkCycle? = null) {
        val pending = state.clear() ?: return
        check(expected == null || pending === expected) { "EMace Firework cycle ownership changed during cleanup" }
        diagnostic(pending, "cycle clear reason=$reason")
    }

    private fun claimDiagnosticCycle(): Boolean {
        if (diagnosticCyclesRemaining <= 0 || diagnosticMessagesRemaining <= 0) {
            return false
        }
        diagnosticCyclesRemaining--
        return true
    }

    private fun diagnostic(cycle: EMaceFireworkCycle, message: String) {
        if (!cycle.diagnosticsEnabled || diagnosticMessagesRemaining <= 0) {
            return
        }
        diagnosticMessagesRemaining--
        logger.info("$DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} " +
            "serial=${ActionTickBoundary.currentSerial()} $message")
    }

    private fun warning(cycle: EMaceFireworkCycle, message: String) {
        if (!cycle.diagnosticsEnabled || diagnosticMessagesRemaining <= 0) {
            return
        }
        diagnosticMessagesRemaining--
        logger.warn("$DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} " +
            "serial=${ActionTickBoundary.currentSerial()} $message")
    }

    private fun resetDiagnosticBudget() {
        diagnosticCyclesRemaining = MAX_DIAGNOSTIC_CYCLES
        diagnosticMessagesRemaining = MAX_DIAGNOSTIC_MESSAGES
    }

    private const val DIAGNOSTIC_PREFIX = "[EMaceFireworkOrder]"
    private const val MAX_DIAGNOSTIC_CYCLES = 4
    private const val MAX_DIAGNOSTIC_MESSAGES = MAX_DIAGNOSTIC_CYCLES * 24
    private const val HOTBAR_CONTAINER_OFFSET = 36
    private const val OFFHAND_SWAP_BUTTON = 40
}

internal val EMACE_FIREWORK_PRE_AURA_PRIORITY: Short = (FIRST_PRIORITY + 1).toShort()

internal enum class EMaceFireworkProfile {
    PEAK_ASSIST,
    FIREWORK_VECTOR,
}

internal enum class EMaceFireworkSource {
    OFFHAND,
    HOTBAR,
    MAIN_INVENTORY,
    OFFHAND_SWAP,
}

internal enum class EMaceFireworkCycleStage {
    WAITING_TO_USE_AFTER_CONFLICT,
    WAITING_FOR_USE_BOUNDARY,
    READY_TO_RESTORE,
}

internal data class EMaceFireworkPlan(
    val profile: EMaceFireworkProfile,
    val source: EMaceFireworkSource,
    val sourceSlot: Int,
    val fireworkHotbarSlot: Int?,
    val temporaryHotbarSlot: Int?,
    val capturedVisibleSlot: Int,
    val useHand: InteractionHand,
    val initialHeldRequired: Boolean,
    val inventorySwapInRequired: Boolean,
    val inventorySwapBackRequired: Boolean,
    val closeAfterSwap: Boolean,
    val swingRequired: Boolean,
) {
    val heldRestorationRequired: Boolean
        get() = initialHeldRequired

    val requiresPostUseRestoration: Boolean
        get() = heldRestorationRequired || inventorySwapBackRequired

    val requiresSelectedSlotOwnership: Boolean
        get() = initialHeldRequired || inventorySwapInRequired

    companion object {
        fun directHand(profile: EMaceFireworkProfile, hand: InteractionHand, visibleSlot: Int) =
            EMaceFireworkPlan(
                profile, EMaceFireworkSource.OFFHAND.takeIf { hand == InteractionHand.OFF_HAND }
                    ?: EMaceFireworkSource.HOTBAR,
                sourceSlot = visibleSlot,
                fireworkHotbarSlot = null,
                temporaryHotbarSlot = null,
                capturedVisibleSlot = visibleSlot,
                useHand = hand,
                initialHeldRequired = false,
                inventorySwapInRequired = false,
                inventorySwapBackRequired = false,
                closeAfterSwap = false,
                swingRequired = profile == EMaceFireworkProfile.FIREWORK_VECTOR,
            )

        fun hotbar(profile: EMaceFireworkProfile, fireworkSlot: Int, visibleSlot: Int) = EMaceFireworkPlan(
            profile, EMaceFireworkSource.HOTBAR, fireworkSlot, fireworkSlot, null, visibleSlot,
            InteractionHand.MAIN_HAND, true, false, false, false,
            profile == EMaceFireworkProfile.FIREWORK_VECTOR,
        )

        fun mainInventory(
            profile: EMaceFireworkProfile,
            sourceSlot: Int,
            temporarySlot: Int,
            visibleSlot: Int,
        ) = EMaceFireworkPlan(
            profile, EMaceFireworkSource.MAIN_INVENTORY, sourceSlot, temporarySlot, temporarySlot, visibleSlot,
            InteractionHand.MAIN_HAND, true, true, true, true,
            profile == EMaceFireworkProfile.FIREWORK_VECTOR,
        )

        fun offhandSwap(profile: EMaceFireworkProfile, sourceSlot: Int, visibleSlot: Int) = EMaceFireworkPlan(
            profile, EMaceFireworkSource.OFFHAND_SWAP, sourceSlot, null, null, visibleSlot,
            InteractionHand.OFF_HAND, false, true, true, true, false,
        )
    }
}

internal data class EMaceFireworkCycle(
    val plan: EMaceFireworkPlan,
    val playerIdentity: Any,
    val levelIdentity: Any,
    val connectionIdentity: Any,
    val targetIdentity: Entity?,
    val emaceOwned: Boolean,
    var stage: EMaceFireworkCycleStage,
    val requestBoundarySerial: Long,
    val requestTick: Int,
    val createdNanos: Long,
    val conflictingAttackObserved: Boolean,
    val conflictingUseObserved: Boolean,
    val queuedAfterConflict: Boolean,
    val diagnosticsEnabled: Boolean,
    var preUseBoundaryObserved: Boolean = false,
    var inventorySwapInCompleted: Boolean = false,
    var initialHeldSent: Boolean = false,
    var rawUseSent: Boolean = false,
    var swingSent: Boolean = false,
    var useBoundarySerial: Long = Long.MIN_VALUE,
    var postUseBoundaryObserved: Boolean = false,
    var postUseBoundarySerial: Long = Long.MIN_VALUE,
    var postUseBoundaryTick: Int = Int.MIN_VALUE,
    var heldRestorationClaimed: Boolean = false,
    var heldRestorationCompleted: Boolean = false,
    var inventorySwapBackClaimed: Boolean = false,
    var inventorySwapBackCompleted: Boolean = false,
) {
    fun timedOut(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - requestTick > MAX_PENDING_TICKS || nowNanos - createdNanos > MAX_PENDING_NANOS

    private companion object {
        const val MAX_PENDING_TICKS = 20
        const val MAX_PENDING_NANOS = 1_000_000_000L
    }
}

internal sealed interface EMaceFireworkCycleDecision {
    data object WAIT : EMaceFireworkCycleDecision
    data object EXECUTE_USE : EMaceFireworkCycleDecision
    data class RESTORE(val heldSlot: Int?, val swapBack: Boolean) : EMaceFireworkCycleDecision
    data class DROP(val reason: String) : EMaceFireworkCycleDecision
}

internal class EMaceFireworkCycleState {
    var pending: EMaceFireworkCycle? = null
        private set

    fun begin(cycle: EMaceFireworkCycle): Boolean {
        if (pending != null || cycle.plan.capturedVisibleSlot !in 0..8) {
            return false
        }
        pending = cycle
        return true
    }

    fun markUseSent(expected: EMaceFireworkCycle): Boolean {
        val cycle = pending ?: return false
        if (cycle !== expected || !cycle.rawUseSent) {
            return false
        }
        cycle.stage = EMaceFireworkCycleStage.WAITING_FOR_USE_BOUNDARY
        return true
    }

    fun observeNaturalBoundary(serial: Long, tick: Int): Boolean {
        val cycle = pending ?: return false
        if (!cycle.rawUseSent || cycle.postUseBoundaryObserved || serial <= cycle.useBoundarySerial) {
            return false
        }
        cycle.postUseBoundaryObserved = true
        cycle.postUseBoundarySerial = serial
        cycle.postUseBoundaryTick = tick
        cycle.stage = EMaceFireworkCycleStage.READY_TO_RESTORE
        return true
    }

    @Suppress("LongParameterList")
    fun preparePreAura(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentConnectionIdentity: Any?,
        currentTargetIdentity: Entity?,
        emaceRunning: Boolean,
        currentVisibleSlot: Int,
        currentBoundarySerial: Long,
        currentTick: Int,
        nowNanos: Long,
    ): EMaceFireworkCycleDecision {
        val cycle = pending ?: return EMaceFireworkCycleDecision.DROP("no pending cycle")
        val invalidReason = when {
            cycle.playerIdentity !== currentPlayerIdentity -> "local player replaced"
            cycle.levelIdentity !== currentLevelIdentity -> "level replaced"
            cycle.connectionIdentity !== currentConnectionIdentity -> "connection replaced"
            cycle.timedOut(currentTick, nowNanos) -> "cycle timeout"
            !cycle.rawUseSent && cycle.emaceOwned && !emaceRunning -> "EMace disabled before use"
            !cycle.rawUseSent && cycle.targetIdentity != null &&
                (cycle.targetIdentity !== currentTargetIdentity || cycle.targetIdentity.isRemoved) -> "target lost before use"
            else -> null
        }
        if (invalidReason != null) {
            pending = null
            return EMaceFireworkCycleDecision.DROP(invalidReason)
        }

        return when (cycle.stage) {
            EMaceFireworkCycleStage.WAITING_TO_USE_AFTER_CONFLICT -> {
                if (!cycle.queuedAfterConflict || currentBoundarySerial > cycle.requestBoundarySerial) {
                    cycle.preUseBoundaryObserved = cycle.queuedAfterConflict
                    EMaceFireworkCycleDecision.EXECUTE_USE
                } else {
                    EMaceFireworkCycleDecision.WAIT
                }
            }

            EMaceFireworkCycleStage.WAITING_FOR_USE_BOUNDARY -> EMaceFireworkCycleDecision.WAIT
            EMaceFireworkCycleStage.READY_TO_RESTORE -> {
                if (cycle.heldRestorationClaimed || cycle.inventorySwapBackClaimed) {
                    EMaceFireworkCycleDecision.WAIT
                } else {
                    val heldSlot = if (cycle.plan.heldRestorationRequired) {
                        currentVisibleSlot.takeIf { it in 0..8 } ?: cycle.plan.capturedVisibleSlot
                    } else {
                        null
                    }
                    if (cycle.plan.heldRestorationRequired && heldSlot !in 0..8) {
                        pending = null
                        EMaceFireworkCycleDecision.DROP("no valid visible return slot")
                    } else {
                        cycle.heldRestorationClaimed = cycle.plan.heldRestorationRequired
                        cycle.inventorySwapBackClaimed = cycle.plan.inventorySwapBackRequired
                        EMaceFireworkCycleDecision.RESTORE(heldSlot, cycle.plan.inventorySwapBackRequired)
                    }
                }
            }
        }
    }

    fun clear(): EMaceFireworkCycle? = pending.also { pending = null }
}

private sealed class EMaceLocatedFirework(open val serverSlot: Int) {
    data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : EMaceLocatedFirework(serverSlot)
    data class Inventory(override val serverSlot: Int) : EMaceLocatedFirework(serverSlot)
}
