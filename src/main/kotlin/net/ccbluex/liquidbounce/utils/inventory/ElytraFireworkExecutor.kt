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
package net.ccbluex.liquidbounce.utils.inventory

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.ScheduleInventoryActionEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.player.offhand.ModuleOffhand
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.interactItem
import net.ccbluex.liquidbounce.utils.entity.firstPersonRenderer
import net.ccbluex.liquidbounce.utils.entity.isAccepted
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.resetEquipProgress
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items

/**
 * Shared non-configurable V-Core Firework executor used by ElytraMotion and EMaceHelper.
 *
 * The source performs the entire swap/use/restore sequence in one packet window. Current 26.1.2
 * retains the same item selection and final inventory result, but splits it across real natural
 * client tick boundaries and the existing movement-safe inventory scheduler.
 */
internal object ElytraFireworkExecutor : EventListener {

    enum class Owner {
        ELYTRA_MOTION,
        PEAK_ASSIST,
        FIREWORK_VECTOR,
    }

    enum class SelectionOrder {
        /** V-Core ElytraMotion / ElytraSwap `findItemInInventory` order. */
        INVENTORY_FIRST,

        /** V-Core EMaceHelper's `findItemInHotBar`, then inventory order. */
        HOTBAR_FIRST,
    }

    private enum class Route {
        OFFHAND,
        HOTBAR,
        SWAP_TO_HOTBAR,
        SWAP_TO_OFFHAND,
    }

    private enum class State {
        WAITING_FOR_START_BOUNDARY,
        WAITING_FOR_SWAP_IN,
        WAITING_FOR_SWAP_IN_BOUNDARY,
        WAITING_FOR_USE_BOUNDARY,
        WAITING_FOR_SWAP_BACK,
        WAITING_FOR_SWAP_BACK_BOUNDARY,
    }

    private sealed interface FireworkLocation {
        data class Hotbar(val slot: HotbarItemSlot) : FireworkLocation
        data class Inventory(val slot: InventoryItemSlot) : FireworkLocation
    }

    private data class PendingUse(
        val owner: Owner,
        val player: LocalPlayer,
        val level: ClientLevel,
        val route: Route,
        val sourceSlot: ItemSlot?,
        val temporaryHotbarSlot: HotbarItemSlot?,
        val originalSelectedSlot: Int,
        val requestBoundarySerial: Long,
        val createdTick: Int,
        val onUseAccepted: (() -> Unit)?,
        var state: State = State.WAITING_FOR_START_BOUNDARY,
        var actionBoundarySerial: Long = Long.MIN_VALUE,
        var cancelled: Boolean = false,
        var swapRequest: InventoryActionMovementGate.Request? = null,
    )

    private var pending: PendingUse? = null
    private val constraints = PlayerInventoryConstraints("ElytraFireworkSafeTransaction")
    private val transactionGate = InventoryActionMovementGate(
        ownerRunning = { pending != null },
        diagnosticPrefix = "[ElytraFireworkTxnDiag]"
    )

    /** True while a shared Firework action owns a packet or inventory boundary. */
    fun isBusy() = pending != null || transactionGate.state != InventoryActionMovementGate.State.IDLE

    fun hasFirework(order: SelectionOrder): Boolean {
        val player = mc.player ?: return false
        return player.offhandItem.item == Items.FIREWORK_ROCKET || findFirework(order) != null
    }

    /**
     * Queues one source-equivalent use. A false return means no Firework transaction was started;
     * callers that mirror V-Core timers still own their source timer reset decision.
     */
    fun request(
        owner: Owner,
        order: SelectionOrder,
        onUseAccepted: (() -> Unit)? = null,
    ): Boolean {
        if (pending != null || transactionGate.state != InventoryActionMovementGate.State.IDLE ||
            ModuleEMaceHelper.isArmorSwapTransactionActive()) {
            return false
        }

        val player = mc.player ?: return false
        val level = mc.level ?: return false
        if (mc.screen != null || InventoryManager.isInventoryOpen || ModuleOffhand.hasActiveInventoryTransaction() ||
            player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return false
        }

        val originalSlot = player.inventory.selectedSlot
        val location = if (player.offhandItem.item == Items.FIREWORK_ROCKET) null else findFirework(order)
            ?: return false

        val route: Route
        val sourceSlot: ItemSlot?
        val temporaryHotbarSlot: HotbarItemSlot?

        if (location == null) {
            route = Route.OFFHAND
            sourceSlot = null
            temporaryHotbarSlot = null
        } else if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
            route = Route.SWAP_TO_OFFHAND
            sourceSlot = location.toItemSlot()
            temporaryHotbarSlot = null
        } else when (location) {
            is FireworkLocation.Hotbar -> {
                route = Route.HOTBAR
                sourceSlot = location.slot
                temporaryHotbarSlot = location.slot
            }

            is FireworkLocation.Inventory -> {
                route = Route.SWAP_TO_HOTBAR
                sourceSlot = location.slot
                // This exact source formula deliberately never chooses the current selected slot.
                temporaryHotbarSlot = HotbarItemSlot(originalSlot % 8 + 1)
            }
        }

        pending = PendingUse(
            owner = owner,
            player = player,
            level = level,
            route = route,
            sourceSlot = sourceSlot,
            temporaryHotbarSlot = temporaryHotbarSlot,
            originalSelectedSlot = originalSlot,
            requestBoundarySerial = ActionTickBoundary.currentSerial(),
            createdTick = player.tickCount,
            onUseAccepted = onUseAccepted,
        )
        return true
    }

    /**
     * A disabled owner may not start a fresh use. Once a swap or selected slot has happened, the
     * executor keeps only the minimum cleanup path needed to restore the user's item state.
     */
    fun cancelOwner(owner: Owner) {
        val current = pending ?: return
        if (current.owner != owner) {
            return
        }

        current.cancelled = true
        if (current.state == State.WAITING_FOR_START_BOUNDARY ||
            current.state == State.WAITING_FOR_SWAP_IN &&
            (current.swapRequest == null || !transactionGate.hasStarted(current.swapRequest!!))
        ) {
            transactionGate.cancel("Firework owner stopped before inventory action")
            pending = null
        }
    }

    override val running: Boolean
        get() = true

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent>(priority = -1000) {
        val current = pending ?: return@handler
        val player = mc.player
        val level = mc.level
        if (player !== current.player || level !== current.level) {
            transactionGate.cancel("Firework player or world changed")
            pending = null
            return@handler
        }

        if (player.tickCount - current.createdTick > TRANSACTION_TIMEOUT_TICKS) {
            current.cancelled = true
        }

        when (current.state) {
            State.WAITING_FOR_START_BOUNDARY -> {
                if (!ActionTickBoundary.hasPassed(current.requestBoundarySerial)) {
                    return@handler
                }

                if (current.cancelled) {
                    pending = null
                    return@handler
                }

                when (current.route) {
                    Route.OFFHAND,
                    Route.HOTBAR -> useFirework(current)

                    Route.SWAP_TO_HOTBAR,
                    Route.SWAP_TO_OFFHAND -> current.state = State.WAITING_FOR_SWAP_IN
                }
            }

            State.WAITING_FOR_SWAP_IN_BOUNDARY -> {
                if (!ActionTickBoundary.hasPassed(current.actionBoundarySerial) ||
                    transactionGate.state != InventoryActionMovementGate.State.IDLE) {
                    return@handler
                }

                if (current.cancelled || !isPreparedFireworkStillPresent(current)) {
                    current.state = State.WAITING_FOR_SWAP_BACK
                } else {
                    useFirework(current)
                }
            }

            State.WAITING_FOR_USE_BOUNDARY -> handleUseBoundary(current)

            State.WAITING_FOR_SWAP_BACK_BOUNDARY -> {
                if (ActionTickBoundary.hasPassed(current.actionBoundarySerial) &&
                    transactionGate.state == InventoryActionMovementGate.State.IDLE) {
                    pending = null
                }
            }

            State.WAITING_FOR_SWAP_IN,
            State.WAITING_FOR_SWAP_BACK -> Unit
        }
    }

    @Suppress("unused")
    private val scheduleHandler = handler<ScheduleInventoryActionEvent> { event ->
        val current = pending ?: return@handler
        val state = current.state
        if (state != State.WAITING_FOR_SWAP_IN && state != State.WAITING_FOR_SWAP_BACK) {
            return@handler
        }

        if (ModuleOffhand.hasActiveInventoryTransaction()) {
            return@handler
        }

        if (current.cancelled && state == State.WAITING_FOR_SWAP_IN &&
            (current.swapRequest == null || !transactionGate.hasStarted(current.swapRequest!!))) {
            transactionGate.cancel("Firework request cancelled before swap in")
            pending = null
            return@handler
        }

        val source = current.sourceSlot ?: run {
            pending = null
            return@handler
        }
        val destination: HotbarItemSlot = when (current.route) {
            Route.SWAP_TO_HOTBAR -> current.temporaryHotbarSlot ?: return@handler
            Route.SWAP_TO_OFFHAND -> OffHandSlot
            else -> return@handler
        }

        val action = ClickInventoryAction.performSwap(from = source, to = destination)
            .withDetachedPlayerInventoryClickPermit()
        val isRestore = state == State.WAITING_FOR_SWAP_BACK
        val request = InventoryActionMovementGate.Request(
            mode = if (isRestore) "ElytraFireworkRestore" else "ElytraFireworkPrepare",
            sourceSlot = source,
            expectedSourceItem = if (isRestore) source.itemStack.item else Items.FIREWORK_ROCKET,
            allowEmptySource = isRestore,
            switchMode = "SWAP",
            sendDirectly = false,
            clickSignatures = listOfNotNull(action.inventoryClickSignature()),
            expectsOffhandSwap = false,
            timeoutTicks = TRANSACTION_TIMEOUT_TICKS,
            onFirstActionSent = {},
        )

        transactionGate.request(request)
        if (!transactionGate.isCurrent(request) || !transactionGate.isReady(request) ||
            !action.canPerformAction(constraints) || !transactionGate.beginExecution(request)) {
            return@handler
        }

        current.swapRequest = request
        event.schedule.add(
            InventoryActionChain(
                inventoryConstraints = constraints,
                actions = arrayOf(action),
                priority = net.ccbluex.liquidbounce.utils.kotlin.Priority.NORMAL,
                precondition = {
                    pending === current && transactionGate.isCurrent(request) && action.canPerformAction(constraints)
                }
            )
        )
    }

    @Suppress("unused")
    private val packetHandler = handler<OutgoingPacketSentEvent> { event ->
        val current = pending ?: return@handler
        val packet = event.packet as? ServerboundContainerClickPacket ?: return@handler
        val source = current.sourceSlot ?: return@handler
        val destination = when (current.route) {
            Route.SWAP_TO_HOTBAR -> current.temporaryHotbarSlot
            Route.SWAP_TO_OFFHAND -> OffHandSlot
            else -> null
        } ?: return@handler
        val sourceId = source.getIdForServer(null) ?: return@handler

        if (packet.containerId != 0 || packet.slotNum.toInt() != sourceId ||
            packet.buttonNum.toInt() != destination.hotbarSlotForServer ||
            packet.containerInput != ContainerInput.SWAP) {
            return@handler
        }

        when (current.state) {
            State.WAITING_FOR_SWAP_IN -> {
                current.actionBoundarySerial = ActionTickBoundary.currentSerial()
                current.state = State.WAITING_FOR_SWAP_IN_BOUNDARY
            }

            State.WAITING_FOR_SWAP_BACK -> {
                current.actionBoundarySerial = ActionTickBoundary.currentSerial()
                current.state = State.WAITING_FOR_SWAP_BACK_BOUNDARY
            }

            else -> Unit
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        transactionGate.cancel("Firework world changed")
        pending = null
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        transactionGate.cancel("Firework disconnected")
        pending = null
    }

    private fun useFirework(current: PendingUse) {
        val player = current.player
        val hand = when (current.route) {
            Route.OFFHAND,
            Route.SWAP_TO_OFFHAND -> InteractionHand.OFF_HAND

            Route.HOTBAR,
            Route.SWAP_TO_HOTBAR -> InteractionHand.MAIN_HAND
        }

        val selectedFireworkSlot = current.temporaryHotbarSlot
        val action = ActionTickBoundary.beginAction(
            owner = "ElytraFirework",
            kind = ActionTickBoundary.ActionKind.USE_ITEM,
            diagnosticsEnabled = false,
        )

        if (selectedFireworkSlot != null && player.inventory.selectedSlot != selectedFireworkSlot.hotbarSlot) {
            val expectation = ActionTickBoundary.expectHeldItemChange(
                owner = "ElytraFirework",
                purpose = "select Firework slot",
                action = action,
                diagnosticsEnabled = false,
            )
            if (!expectation.allowed) {
                ActionTickBoundary.cancelAction(action)
                current.actionBoundarySerial = ActionTickBoundary.currentSerial()
                current.state = State.WAITING_FOR_USE_BOUNDARY
                return
            }

            player.inventory.selectedSlot = selectedFireworkSlot.hotbarSlot
            interaction.ensureHasSentCarriedItem()
        }

        val stack = player.getItemInHand(hand)
        if (stack.item != Items.FIREWORK_ROCKET) {
            ActionTickBoundary.cancelAction(action)
            current.actionBoundarySerial = ActionTickBoundary.currentSerial()
            current.state = State.WAITING_FOR_USE_BOUNDARY
            return
        }

        val (yaw, pitch) = useRotation(player)
        val result = interaction.interactItem(player, hand, yaw, pitch)
        ActionTickBoundary.actionCallCompleted(action)
        current.actionBoundarySerial = if (ActionTickBoundary.wasActionObserved(action)) {
            action.observedSerial
        } else {
            ActionTickBoundary.currentSerial()
        }

        if (result.isAccepted) {
            // The custom-rotation interaction helper only performs the use. V-Core explicitly swings.
            player.swingHand(hand)
            mc.firstPersonRenderer.resetEquipProgress(hand)
            current.onUseAccepted?.invoke()
        }

        current.state = State.WAITING_FOR_USE_BOUNDARY
    }

    private fun handleUseBoundary(current: PendingUse) {
        if (!ActionTickBoundary.hasPassed(current.actionBoundarySerial)) {
            return
        }

        if (current.temporaryHotbarSlot != null && !restoreSelectedSlot(current)) {
            return
        }

        when (current.route) {
            Route.SWAP_TO_HOTBAR,
            Route.SWAP_TO_OFFHAND -> current.state = State.WAITING_FOR_SWAP_BACK

            Route.OFFHAND,
            Route.HOTBAR -> pending = null
        }
    }

    private fun restoreSelectedSlot(current: PendingUse): Boolean {
        val player = current.player
        val temporarySlot = current.temporaryHotbarSlot ?: return true
        if (current.originalSelectedSlot !in 0..8 || player.inventory.selectedSlot != temporarySlot.hotbarSlot) {
            return true
        }

        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = "ElytraFirework",
                actionBoundarySerial = current.actionBoundarySerial,
                diagnosticsEnabled = false,
            )
        ) {
            return false
        }

        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = "ElytraFirework",
            purpose = "restore selected slot",
            postActionBoundarySerial = current.actionBoundarySerial,
            diagnosticsEnabled = false,
        )
        if (!expectation.allowed) {
            return false
        }

        // #Edit By Codex 2026-07-17: a V-Core same-window Firework restore maps to the
        // first real post-USE_ITEM boundary so PacketOrderE cannot observe USE -> held slot.
        player.inventory.selectedSlot = current.originalSelectedSlot
        interaction.ensureHasSentCarriedItem()
        ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        return true
    }

    private fun isPreparedFireworkStillPresent(current: PendingUse): Boolean = when (current.route) {
        Route.SWAP_TO_HOTBAR -> current.temporaryHotbarSlot?.itemStack?.item == Items.FIREWORK_ROCKET
        Route.SWAP_TO_OFFHAND -> current.player.offhandItem.item == Items.FIREWORK_ROCKET
        else -> false
    }

    private fun findFirework(order: SelectionOrder): FireworkLocation? {
        fun findInInventoryDescending(): FireworkLocation? {
            for (slot in Slots.Inventory.asReversed()) {
                if (slot.itemStack.item == Items.FIREWORK_ROCKET) {
                    return FireworkLocation.Inventory(slot)
                }
            }

            return null
        }

        fun findInHotbarDescending(): FireworkLocation? {
            for (slot in Slots.Hotbar.asReversed()) {
                if (slot.itemStack.item == Items.FIREWORK_ROCKET) {
                    return FireworkLocation.Hotbar(slot)
                }
            }

            return null
        }

        fun findInHotbarAscending(): FireworkLocation? {
            for (slot in Slots.Hotbar) {
                if (slot.itemStack.item == Items.FIREWORK_ROCKET) {
                    return FireworkLocation.Hotbar(slot)
                }
            }

            return null
        }

        return when (order) {
            SelectionOrder.INVENTORY_FIRST -> findInInventoryDescending() ?: findInHotbarDescending()
            SelectionOrder.HOTBAR_FIRST -> findInHotbarAscending() ?: findInInventoryDescending()
        }
    }

    private fun FireworkLocation.toItemSlot(): ItemSlot = when (this) {
        is FireworkLocation.Hotbar -> slot
        is FireworkLocation.Inventory -> slot
    }

    private fun useRotation(player: LocalPlayer): Pair<Float, Float> {
        val rotation = if (ModuleKillAura.running && ModuleKillAura.targetTracker.target != null) {
            RotationManager.currentRotation ?: RotationManager.actualServerRotation
        } else {
            null
        }

        return (rotation?.yaw ?: player.yaw) to (rotation?.pitch ?: player.pitch)
    }

    private const val TRANSACTION_TIMEOUT_TICKS = 120
}
