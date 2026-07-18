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
import net.ccbluex.liquidbounce.utils.client.SilentHotbar
import net.ccbluex.liquidbounce.utils.client.interactItem
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
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
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

    private val fireworkTimer = ElytraMotionFireworkTimer(System.nanoTime())
    private val fireworkAttemptGuard = ElytraMotionFireworkAttemptGuard()

    /** True only while a Hotbar/Main-inventory Firework owns the MainHand action window. */
    val isMainHandFireworkActionActive: Boolean
        get() = ElytraMotionFireworkCycle.isMainHandActionActive

    /** Includes the short direct Offhand USE_ITEM window used by KillAura's pre-action gate. */
    internal val isFireworkActionWindowActive: Boolean
        get() = ElytraMotionFireworkCycle.isAnyActionActive

    init {
        // A post-use server-slot restore must outlive the module toggle that initiated it.
        ElytraMotionFireworkCycle
    }

    override fun enable() {
        ElytraMotionFireworkCycle.resume()
    }

    override fun disable() {
        freeze = false
        ElytraMotionFireworkCycle.requestStop()
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
            if (matrixBypass && fireworkTimer.hasElapsed(System.nanoTime())) {
                attemptFirework(player)
            }
        } else if (autoFirework && target != null && getBps(player) <= 30.0 &&
            fireworkTimer.hasElapsed(System.nanoTime())) {
            attemptFirework(player)
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

    private fun attemptFirework(player: LocalPlayer): FireworkUseResult {
        if (!fireworkAttemptGuard.tryAcquire(player, player.tickCount)) {
            return FireworkUseResult.BLOCKED
        }

        val result = useFirework()
        fireworkTimer.record(result, System.nanoTime())
        return result
    }

    private fun useFirework(): FireworkUseResult {
        if (ElytraMotionFireworkCycle.isPending || ElytraMotionFireworkCycle.isAnyActionActive) {
            return FireworkUseResult.BLOCKED
        }

        val player = mc.player ?: return FireworkUseResult.FAILED
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return FireworkUseResult.BLOCKED
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            return ElytraMotionFireworkCycle.useDirect(
                source = FireworkSource.OFFHAND,
                hand = InteractionHand.OFF_HAND,
            )
        }

        val firework = findFireworkInventoryFirst() ?: return FireworkUseResult.NO_ITEM
        val currentSlot = player.inventory.selectedSlot
        if (currentSlot !in 0..8) {
            return FireworkUseResult.FAILED
        }

        return when (firework) {
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

    private fun interactFirework(hand: InteractionHand): InteractionResult? {
        val player = mc.player ?: return null
        val gameMode = mc.gameMode ?: return null
        val rotation = useRotation()
        return gameMode.interactItem(player, hand, rotation.yaw, rotation.pitch)
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

    private const val HOTBAR_CONTAINER_OFFSET = 36

    /** Owns the SilentHotbar lease until Grim has observed the natural client tick boundary. */
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
        private var directActionWindow: ElytraMotionFireworkActionWindow? = null
        private var stopRequested = false

        override val running: Boolean
            get() = true

        val isPending: Boolean
            get() = state.pending != null

        val isMainHandActionActive: Boolean
            get() = state.isMainHandActionActive(ActionTickBoundary.currentSerial()) ||
                directActionWindow?.isActive(ActionTickBoundary.currentSerial(), mainHandOnly = true) == true

        val isAnyActionActive: Boolean
            get() = isMainHandActionActive ||
                directActionWindow?.isActive(ActionTickBoundary.currentSerial(), mainHandOnly = false) == true

        fun requestStop() {
            stopRequested = true
            val cycle = state.pending ?: return
            if (!cycle.leaseOwned && cycle.stage == ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_IN &&
                !cycle.inventorySwapObserved) {
                clear("module disabled before inventory SWAP-in")
            }
        }

        fun resume() {
            stopRequested = false
        }

        fun useDirect(source: FireworkSource, hand: InteractionHand): FireworkUseResult {
            val player = mc.player ?: return FireworkUseResult.FAILED
            val level = mc.level ?: return FireworkUseResult.FAILED
            val diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
            ActionTickBoundary.diagnostic(diagnosticsEnabled, "ElytraMotion selected Firework source=${source.name}")
            val outcome = performInteraction(diagnosticsEnabled, hand, leaseExpected = false)
            if (outcome.observed) {
                directActionWindow = ElytraMotionFireworkActionWindow(
                    playerIdentity = player,
                    levelIdentity = level,
                    hand = hand,
                    boundarySerial = outcome.boundarySerial,
                    createdTick = player.tickCount,
                    createdNanos = System.nanoTime(),
                )
            }
            ActionTickBoundary.diagnostic(
                diagnosticsEnabled,
                "ElytraMotion direct Firework source=${source.name} hand=$hand " +
                    "result=${outcome.result} observed=${outcome.observed}",
            )
            return if (outcome.acceptedAndObserved) FireworkUseResult.USED else FireworkUseResult.FAILED
        }

        fun startHotbar(fireworkSlot: Int, returnSlot: Int): FireworkUseResult {
            val player = mc.player ?: return FireworkUseResult.FAILED
            val level = mc.level ?: return FireworkUseResult.FAILED
            if (fireworkSlot !in 0..8 || returnSlot !in 0..8 || isPending) {
                return FireworkUseResult.BLOCKED
            }

            val diagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
            ActionTickBoundary.diagnostic(
                diagnosticsEnabled,
                "ElytraMotion selected Firework source=${FireworkSource.HOTBAR.name} slot=$fireworkSlot",
            )
            val startSteps = elytraMotionHotbarStartSteps(fireworkSlot, returnSlot)
            if (startSteps == listOf(ElytraMotionFireworkPacketStep.USE_ITEM)) {
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "ElytraMotion Firework already selected slot=$fireworkSlot; USE_ITEM only",
                )
                return useDirect(FireworkSource.HOTBAR, InteractionHand.MAIN_HAND)
            }

            if (!claimFireworkLease(fireworkSlot, diagnosticsEnabled)) {
                return FireworkUseResult.BLOCKED
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
                source = FireworkSource.HOTBAR,
                leaseOwned = true,
            )
            if (!state.begin(cycle)) {
                SilentHotbar.resetSlot(OWNER)
                return FireworkUseResult.BLOCKED
            }

            expectedServerSlot = returnSlot
            performPendingUse(cycle, alreadySelected = false)
            return FireworkUseResult.STARTED_PENDING
        }

        fun startMainInventory(sourceSlot: Int, temporarySlot: Int, returnSlot: Int): FireworkUseResult {
            val player = mc.player ?: return FireworkUseResult.FAILED
            val level = mc.level ?: return FireworkUseResult.FAILED
            if (sourceSlot !in 9..35 || temporarySlot !in 0..8 || returnSlot !in 0..8 || isPending) {
                return FireworkUseResult.BLOCKED
            }
            if (SilentHotbar.hasIncompatibleSelection(OWNER)) {
                return FireworkUseResult.BLOCKED
            }

            val source = Slots.Inventory[sourceSlot - 9]
            if (source.itemStack.item != Items.FIREWORK_ROCKET) {
                return FireworkUseResult.NO_ITEM
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
                source = FireworkSource.MAIN_INVENTORY,
            )
            if (!state.begin(cycle)) {
                return FireworkUseResult.BLOCKED
            }

            expectedServerSlot = returnSlot
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion selected Firework source=${FireworkSource.MAIN_INVENTORY.name} " +
                    "sourceSlot=$sourceSlot temporary=$temporarySlot",
            )
            requestInventorySwap(cycle, restoring = false)
            return FireworkUseResult.STARTED_PENDING
        }

        private fun claimFireworkLease(slot: Int, diagnosticsEnabled: Boolean): Boolean {
            if (SilentHotbar.hasIncompatibleSelection(OWNER)) {
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "ElytraMotion Firework lease blocked by another SilentHotbar requester",
                )
                return false
            }

            val claimed = SilentHotbar.selectSlotSilentlyWithLease(OWNER, slot, SILENT_HOTBAR_LEASE_TICKS)
            if (!claimed || !SilentHotbar.isLeaseOwnedBy(OWNER) || SilentHotbar.serversideSlot != slot) {
                SilentHotbar.resetSlot(OWNER)
                ActionTickBoundary.diagnostic(
                    diagnosticsEnabled,
                    "ElytraMotion Firework lease selection failed slot=$slot",
                )
                return false
            }

            return true
        }

        private fun performInteraction(
            diagnosticsEnabled: Boolean,
            hand: InteractionHand,
            leaseExpected: Boolean,
        ): ElytraMotionFireworkInteractionOutcome {
            val action = ActionTickBoundary.beginAction(
                owner = OWNER,
                kind = ActionTickBoundary.ActionKind.USE_ITEM,
                diagnosticsEnabled = diagnosticsEnabled,
            )
            val heldExpectation = if (leaseExpected) {
                ActionTickBoundary.expectHeldItemChange(
                    owner = OWNER,
                    purpose = "synchronize leased Firework slot",
                    action = action,
                    diagnosticsEnabled = diagnosticsEnabled,
                )
            } else {
                null
            }
            val result = runCatching { interactFirework(hand) }.getOrNull()
            ActionTickBoundary.actionCallCompleted(action)
            heldExpectation?.let(ActionTickBoundary::completeHeldItemChangeAttempt)
            return ElytraMotionFireworkInteractionOutcome(
                result = result,
                observed = ActionTickBoundary.wasActionObserved(action),
                boundarySerial = ActionTickBoundary.currentSerial(),
            )
        }

        private fun performPendingUse(cycle: ElytraMotionFireworkPendingCycle, alreadySelected: Boolean) {
            val outcome = performInteraction(
                diagnosticsEnabled = cycle.diagnosticsEnabled,
                hand = InteractionHand.MAIN_HAND,
                leaseExpected = cycle.leaseOwned,
            )
            state.completeUse(
                boundarySerial = outcome.boundarySerial,
                observed = outcome.observed,
                accepted = outcome.result?.isAccepted == true,
            )
            if (outcome.acceptedAndObserved) {
                fireworkTimer.record(FireworkUseResult.USED, System.nanoTime())
            }
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion Firework use source=${cycle.source.name} slot=${cycle.fireworkSlot} " +
                    "return=${cycle.capturedReturnSlot} result=${outcome.result} " +
                    "boundary=${cycle.useBoundarySerial} alreadySelected=$alreadySelected " +
                    "observed=${cycle.usePacketObserved} accepted=${cycle.interactionAccepted}",
            )
        }

        @Suppress("unused")
        private val tickHandler = handler<GameTickEvent>(priority = (FIRST_PRIORITY + 2).toShort()) {
            val player = mc.player
            val level = mc.level
            val direct = directActionWindow
            if (direct != null && (player !== direct.playerIdentity || level !== direct.levelIdentity ||
                    direct.isExpired(player?.tickCount ?: Int.MAX_VALUE, System.nanoTime()) ||
                    !direct.isActive(ActionTickBoundary.currentSerial(), mainHandOnly = false))) {
                directActionWindow = null
            }

            val cycle = state.pending ?: return@handler

            if (player !== cycle.playerIdentity || level !== cycle.levelIdentity) {
                clear("player or level identity changed")
                return@handler
            }

            if (cycle.stage != ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK &&
                cycle.isTimedOut(player.tickCount, System.nanoTime())) {
                if (cycle.stage == ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY) {
                    if (!ActionTickBoundary.hasPassed(cycle.useBoundarySerial)) {
                        // A timeout cannot justify an unsafe held-slot or inventory packet. Keep the
                        // lease until the real boundary; disconnect/world replacement clears it safely.
                        ActionTickBoundary.diagnostic(
                            cycle.diagnosticsEnabled,
                            "ElytraMotion timeout reached before natural boundary; retaining Firework lease",
                        )
                        return@handler
                    }
                    restoreServerSlot(cycle, player)
                }
                if (state.pending != null) {
                    if (cycle.leaseOwned) {
                        SilentHotbar.resetSlot(OWNER)
                        cycle.leaseOwned = false
                    }
                    if (cycle.inventorySourceSlot != null && cycle.inventorySwapObserved) {
                        beginInventoryRestore(cycle, "bounded cycle timeout")
                    } else {
                        clear("bounded cycle timeout")
                    }
                }
                return@handler
            }

            when (cycle.stage) {
                ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_IN -> {
                    if (cycle.inventorySwapObserved && inventoryGate.state == InventoryActionMovementGate.State.IDLE) {
                        if (player.inventory.getStack(cycle.fireworkSlot).item != Items.FIREWORK_ROCKET) {
                            beginInventoryRestore(cycle, "Firework was not present after scheduled SWAP-in")
                            return@handler
                        }
                        if (stopRequested) {
                            beginInventoryRestore(cycle, "module disabled after inventory SWAP-in")
                            return@handler
                        }
                        if (!claimFireworkLease(cycle.fireworkSlot, cycle.diagnosticsEnabled)) {
                            beginInventoryRestore(cycle, "SilentHotbar lease unavailable after inventory SWAP-in")
                            return@handler
                        }
                        cycle.leaseOwned = true
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
                ElytraMotionFireworkRestoreDecision.DROP -> {
                    clear("restore context became invalid")
                    return
                }
                is ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC -> {
                    if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                            owner = OWNER,
                            actionBoundarySerial = cycle.useBoundarySerial,
                            diagnosticsEnabled = cycle.diagnosticsEnabled,
                        )) {
                        return
                    }
                    SilentHotbar.resetSlot(OWNER)
                    cycle.leaseOwned = false
                    val expectation = ActionTickBoundary.expectHeldItemChange(
                        owner = OWNER,
                        purpose = "restore visible slot after Firework use",
                        postActionBoundarySerial = cycle.useBoundarySerial,
                        diagnosticsEnabled = cycle.diagnosticsEnabled,
                    )
                    if (!expectation.allowed) {
                        return
                    }
                    interaction.ensureHasSentCarriedItem()
                    ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
                    completeHeldRestore(
                        cycle,
                        if (decision.emitsHeldPacket) "server slot restored" else "visible slot already Firework",
                    )
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

        private fun beginInventoryRestore(cycle: ElytraMotionFireworkPendingCycle, reason: String) {
            if (cycle.leaseOwned) {
                SilentHotbar.resetSlot(OWNER)
                cycle.leaseOwned = false
            }
            cycle.stage = ElytraMotionFireworkCycleStage.WAITING_FOR_SWAP_BACK
            pendingInventoryAction = null
            ActionTickBoundary.diagnostic(
                cycle.diagnosticsEnabled,
                "ElytraMotion beginning inventory restore reason=$reason",
            )
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
            SilentHotbar.resetSlot(OWNER)
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
            SilentHotbar.resetSlot(OWNER)
            state.clear()
            pendingInventoryAction = null
            expectedServerSlot = -1
            directActionWindow = null
        }

        private const val OWNER = "ElytraMotionFirework"
        private const val INVENTORY_GATE_TIMEOUT_TICKS = 40
        private const val SILENT_HOTBAR_LEASE_TICKS = 120
    }
}

internal enum class ElytraMotionFireworkPacketStep {
    HELD_FIREWORK,
    USE_ITEM,
}

internal enum class FireworkSource {
    OFFHAND,
    HOTBAR,
    MAIN_INVENTORY,
}

internal enum class FireworkUseResult(val resetsTimer: Boolean) {
    USED(true),
    STARTED_PENDING(false),
    BLOCKED(false),
    NO_ITEM(false),
    FAILED(false),
}

internal data class ElytraMotionFireworkInteractionOutcome(
    val result: InteractionResult?,
    val observed: Boolean,
    val boundarySerial: Long,
) {
    val acceptedAndObserved: Boolean
        get() = observed && result?.isAccepted == true
}

internal class ElytraMotionFireworkTimer(initialSuccessfulUseNanos: Long) {
    private var lastSuccessfulUseNanos = initialSuccessfulUseNanos

    fun hasElapsed(nowNanos: Long): Boolean =
        nowNanos - lastSuccessfulUseNanos >= FIREWORK_INTERVAL_NANOS

    fun record(result: FireworkUseResult, nowNanos: Long): Boolean {
        if (!result.resetsTimer) {
            return false
        }
        lastSuccessfulUseNanos = nowNanos
        return true
    }

    internal fun lastSuccessfulUseNanos() = lastSuccessfulUseNanos

    private companion object {
        const val FIREWORK_INTERVAL_NANOS = 500L * 1_000_000L
    }
}

internal class ElytraMotionFireworkAttemptGuard {
    private var playerIdentity: Any? = null
    private var attemptedTick = Int.MIN_VALUE

    fun tryAcquire(currentPlayerIdentity: Any, currentTick: Int): Boolean {
        if (playerIdentity === currentPlayerIdentity && attemptedTick == currentTick) {
            return false
        }
        playerIdentity = currentPlayerIdentity
        attemptedTick = currentTick
        return true
    }
}

internal data class ElytraMotionFireworkActionWindow(
    val playerIdentity: Any,
    val levelIdentity: Any,
    val hand: InteractionHand,
    val boundarySerial: Long,
    val createdTick: Int,
    val createdNanos: Long,
) {
    fun isActive(currentSerial: Long, mainHandOnly: Boolean): Boolean =
        boundarySerial != Long.MIN_VALUE && currentSerial <= boundarySerial &&
            (!mainHandOnly || hand == InteractionHand.MAIN_HAND)

    fun isExpired(currentTick: Int, nowNanos: Long): Boolean =
        currentTick - createdTick > 100 || nowNanos - createdNanos > 10_000_000_000L
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
    val source: FireworkSource = FireworkSource.HOTBAR,
    var useBoundarySerial: Long = Long.MIN_VALUE,
    var usePacketObserved: Boolean = false,
    var interactionAccepted: Boolean = false,
    var leaseOwned: Boolean = false,
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
    data object DROP : ElytraMotionFireworkRestoreDecision
    data class RELEASE_AND_SYNC(
        val slot: Int,
        val emitsHeldPacket: Boolean,
    ) : ElytraMotionFireworkRestoreDecision
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

    fun completeUse(boundarySerial: Long, observed: Boolean, accepted: Boolean = false) {
        pending?.apply {
            useBoundarySerial = boundarySerial
            usePacketObserved = observed
            interactionAccepted = accepted
            stage = ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY
        }
    }

    fun isMainHandActionActive(currentSerial: Long): Boolean {
        val cycle = pending ?: return false
        return cycle.leaseOwned && cycle.stage == ElytraMotionFireworkCycleStage.WAITING_FOR_BOUNDARY &&
            cycle.useBoundarySerial != Long.MIN_VALUE && currentSerial <= cycle.useBoundarySerial
    }

    fun resolveRestore(
        currentPlayerIdentity: Any?,
        currentLevelIdentity: Any?,
        currentVisibleSlot: Int,
        currentSerial: Long,
        expectedServerSlot: Int,
    ): ElytraMotionFireworkRestoreDecision {
        val cycle = pending ?: return ElytraMotionFireworkRestoreDecision.DROP
        if (currentPlayerIdentity !== cycle.playerIdentity || currentLevelIdentity !== cycle.levelIdentity) {
            clear()
            return ElytraMotionFireworkRestoreDecision.DROP
        }
        if (cycle.useBoundarySerial == Long.MIN_VALUE || currentSerial <= cycle.useBoundarySerial) {
            return ElytraMotionFireworkRestoreDecision.WAIT
        }

        val restoreSlot = currentVisibleSlot.takeIf { it in 0..8 } ?: cycle.capturedReturnSlot
        if (restoreSlot !in 0..8) {
            return ElytraMotionFireworkRestoreDecision.DROP
        }
        return ElytraMotionFireworkRestoreDecision.RELEASE_AND_SYNC(
            slot = restoreSlot,
            emitsHeldPacket = expectedServerSlot != restoreSlot,
        )
    }

    fun clear() {
        pending = null
    }
}
