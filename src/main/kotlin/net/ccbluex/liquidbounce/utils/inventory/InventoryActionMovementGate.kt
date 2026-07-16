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
package net.ccbluex.liquidbounce.utils.inventory

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.AttackInputEvent
import net.ccbluex.liquidbounce.event.events.BlockAttackEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.ItemUseEvent
import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.PlayerInteractBlockEvent
import net.ccbluex.liquidbounce.event.events.PlayerInteractItemEvent
import net.ccbluex.liquidbounce.event.events.SprintEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.utils.client.inGame
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.entity.isDead
import net.ccbluex.liquidbounce.utils.entity.playerInput
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.network.protocol.game.ServerboundContainerSlotStateChangedPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.network.protocol.game.ServerboundPickItemFromBlockPacket
import net.minecraft.network.protocol.game.ServerboundPickItemFromEntityPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.entity.player.Input
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Item

/**
 * Matches a concrete player-inventory click sent by [ClickInventoryAction].
 *
 * The movement gate only treats an expected packet as its own transaction
 * packet. This avoids completing an Offhand transaction because another
 * inventory module happened to click in the same client tick.
 */
internal data class InventoryClickSignature(
    val containerId: Int,
    val slot: Int,
    val button: Int,
    val action: ContainerInput,
) {
    fun matches(packet: ServerboundContainerClickPacket) =
        packet.containerId == containerId &&
            packet.slotNum.toInt() == slot &&
            packet.buttonNum.toInt() == button &&
            packet.containerInput == action
}

internal fun ClickInventoryAction.inventoryClickSignature(): InventoryClickSignature? {
    val slotId = slot.getIdForServer(screen) ?: return null
    return InventoryClickSignature(screen?.syncId ?: 0, slotId, button, actionType)
}

/**
 * Keeps an inventory transaction behind vanilla's input pipeline.
 *
 * Minecraft 26.1.2 sends [ServerboundPlayerInputPacket], then its sprint state,
 * and finally [ServerboundClientTickEndPacket] during a client tick. A pending
 * transaction suppresses the input through the normal input events, waits for
 * that stationary server-facing state to be observed, and only permits the
 * inventory packet during the following tick. It remains active through a
 * required container close.
 */
internal class InventoryActionMovementGate(
    private val ownerRunning: () -> Boolean,
    private val diagnosticPrefix: String,
) : EventListener {

    enum class State {
        IDLE,
        WAITING_FOR_STATIONARY_INPUT,
        READY_TO_EXECUTE,
        EXECUTING,
        WAITING_FOR_CLOSE,
        RELEASE_ON_NEXT_TICK,
    }

    data class Request(
        val mode: String,
        val sourceSlot: ItemSlot,
        val expectedSourceItem: Item,
        val switchMode: String,
        val sendDirectly: Boolean,
        val clickSignatures: List<InventoryClickSignature>,
        val expectsOffhandSwap: Boolean,
        val timeoutTicks: Int,
        val onFirstActionSent: () -> Unit,
    )

    /**
     * This listener deliberately remains active while its owner is disabled so
     * disconnect/world-change cleanup cannot leave a previously suppressed
     * input state behind.
     */
    override val running: Boolean
        get() = true

    var state: State = State.IDLE
        private set

    private var currentRequest: Request? = null
    private var requestPlayer: LocalPlayer? = null
    private var clientTick = 0L
    private var requestTick = 0L
    private var deadlineTick = 0L
    private var stationaryInputObservedTick: Long? = null
    private var stationaryStateConfirmedTick: Long? = null
    private var requiresSprintStop = false
    private var sprintStopObserved = false
    private var latestServerInput: Input? = null
    private var serverSprinting = false
    private var expectedClicks = mutableListOf<InventoryClickSignature>()
    private var expectsOffhandSwap = false
    private var transactionExecuted = false
    private var closePacketObserved = false
    private var releaseOnTick = 0L
    private var lastConflictingActionTick = Long.MIN_VALUE
    private var lastConflictingPacket = ""
    private var lastConflictLogTick = Long.MIN_VALUE
    private var diagnosticsRemaining = MAX_DIAGNOSTIC_TRANSACTIONS
    private var diagnosticsEnabledForRequest = false

    private val suppressesInput
        get() = state != State.IDLE

    fun request(request: Request) {
        val current = currentRequest
        if (current != null && !current.matches(request)) {
            // A click chain is atomic once execution has begun. Let its own
            // precondition cancel safely before the first click; never replace
            // a chain after it has sent a pickup that may be holding the cursor.
            if (state == State.EXECUTING || state == State.WAITING_FOR_CLOSE || state == State.RELEASE_ON_NEXT_TICK) {
                return
            }
            cancel("superseded by a new Offhand request")
        }

        if (currentRequest != null || !inGame || !ownerRunning()) {
            return
        }

        currentRequest = request
        requestPlayer = mc.player
        requestTick = clientTick
        deadlineTick = clientTick + request.timeoutTicks.coerceAtLeast(MINIMUM_TIMEOUT_TICKS)
        stationaryInputObservedTick = null
        stationaryStateConfirmedTick = null
        expectedClicks.clear()
        expectsOffhandSwap = false
        transactionExecuted = false
        closePacketObserved = false
        state = State.WAITING_FOR_STATIONARY_INPUT

        val localInput = player.input.playerInput
        // Before the first input packet of a freshly joined world, Minecraft's
        // server-side input state is its empty default. Treat that unknown
        // baseline as stationary only when the current local input is also
        // stationary; a moving/sprinting request still requires fresh packet
        // evidence below.
        val serverInputIsStationary = latestServerInput?.isStationary() != false
        requiresSprintStop = player.isSprinting || serverSprinting
        sprintStopObserved = !requiresSprintStop
        diagnosticsEnabledForRequest = diagnosticsRemaining > 0
        if (diagnosticsEnabledForRequest) {
            diagnosticsRemaining--
        }

        diagnostic(
            "transaction requested mode=${request.mode} source=${request.sourceDescription()} " +
                "switch=${request.switchMode} sendDirectly=${request.sendDirectly} " +
                "localInput=${localInput.describe()} localSprint=${player.isSprinting} " +
                "serverInput=${latestServerInput.describe()} serverSprint=$serverSprinting " +
                "inventoryOpen=${InventoryManager.isInventoryOpenServerSide}"
        )

        // When neither the local nor the last observed server-facing state is
        // moving, there is no release packet to wait for. Still reserve this
        // complete client tick: an Offhand action must never be emitted in the
        // same tick phase in which it was requested.
        if (localInput.isStationary() && !player.isSprinting && !player.isUsingItem &&
            serverInputIsStationary && !serverSprinting) {
            state = State.READY_TO_EXECUTE
            stationaryStateConfirmedTick = clientTick
            diagnostic("transaction already has stationary server-facing state; waiting one full tick")
        }
    }

    fun isCurrent(request: Request) = currentRequest?.matches(request) == true

    /**
     * Once the first expected packet has actually left the client, a pickup
     * chain is allowed to change its source slot as part of its own atomic
     * inventory movement. Before that point, the source must still validate.
     */
    fun hasStarted(request: Request) = isCurrent(request) && transactionExecuted

    fun isSourceValid(request: Request): Boolean {
        if (!isCurrent(request) || !inGame || mc.player !== requestPlayer) {
            return false
        }

        val stack = request.sourceSlot.itemStack
        return !stack.isEmpty && stack.item === request.expectedSourceItem
    }

    fun isReady(request: Request): Boolean {
        if (!isCurrent(request) || state != State.READY_TO_EXECUTE) {
            return false
        }

        val stationaryTick = stationaryStateConfirmedTick ?: return false
        if (clientTick <= stationaryTick || clientTick <= requestTick || player.isUsingItem) {
            return false
        }

        if (lastConflictingActionTick == clientTick) {
            if (lastConflictLogTick != clientTick) {
                lastConflictLogTick = clientTick
                diagnostic("transaction deferred for conflicting action=$lastConflictingPacket")
            }
            return false
        }

        return true
    }

    fun beginExecution(request: Request): Boolean {
        if (!isReady(request)) {
            return false
        }

        if (!isSourceValid(request)) {
            cancel("source item moved before transaction execution")
            return false
        }

        if (request.clickSignatures.isEmpty() && !request.expectsOffhandSwap) {
            logger.error("$diagnosticPrefix transaction has no tracked inventory action; refusing execution")
            cancel("no tracked inventory action")
            return false
        }

        state = State.EXECUTING
        expectedClicks = request.clickSignatures.toMutableList()
        expectsOffhandSwap = request.expectsOffhandSwap
        diagnostic(
            "transaction ready and executing expectedClicks=${expectedClicks.size} " +
                "expectsOffhandSwap=$expectsOffhandSwap"
        )
        return true
    }

    fun cancel(reason: String) {
        if (state == State.IDLE) {
            return
        }

        diagnostic("transaction cancelled/invalidated reason=$reason")
        InventoryManager.clearDetachedPlayerInventoryClickPermit()
        clearState()
    }

    private val movementInputHandler = handler<MovementInputEvent>(priority = FINAL_DECISION) { event ->
        if (!suppressesInput) {
            return@handler
        }

        event.directionalInput = DirectionalInput.NONE
        event.jump = false
        event.sneak = false
    }

    private val sprintInputHandler = handler<SprintEvent>(priority = FINAL_DECISION) { event ->
        if (suppressesInput && event.source == SprintEvent.Source.INPUT) {
            event.sprint = false
        }
    }

    // These are observational only: user actions still proceed. When they
    // happen before the Offhand action is sent, the ready state defers the
    // transaction to the next clean stationary tick.
    private val attackInputHandler = handler<AttackInputEvent> {
        recordConflictingAction("AttackInputEvent")
    }

    private val itemUseHandler = handler<ItemUseEvent> {
        recordConflictingAction("ItemUseEvent")
    }

    private val blockAttackHandler = handler<BlockAttackEvent> {
        recordConflictingAction("BlockAttackEvent")
    }

    private val attackEntityHandler = handler<AttackEntityEvent> {
        recordConflictingAction("AttackEntityEvent")
    }

    private val interactItemHandler = handler<PlayerInteractItemEvent> {
        recordConflictingAction("PlayerInteractItemEvent")
    }

    private val interactBlockHandler = handler<PlayerInteractBlockEvent> {
        recordConflictingAction("PlayerInteractBlockEvent")
    }

    // This runs immediately before SequenceManager's FIRST_PRIORITY scheduler.
    // That lets the scheduler execute exactly on Tick N+1 after a Tick N client
    // tick-end confirmed the stationary server-facing state.
    private val gameTickHandler = handler<GameTickEvent>(priority = (FIRST_PRIORITY + 1).toShort()) {
        clientTick++

        if (state == State.IDLE) {
            return@handler
        }

        if (!ownerRunning()) {
            cancel("Offhand module stopped")
            return@handler
        }

        if (!inGame || mc.player !== requestPlayer || player.isDead) {
            cancel("world, player, or death state changed")
            resetServerFacingState()
            return@handler
        }

        if (state == State.RELEASE_ON_NEXT_TICK && clientTick >= releaseOnTick) {
            diagnostic("movement suppression released")
            clearState()
            return@handler
        }

        if (clientTick > deadlineTick) {
            logger.error("$diagnosticPrefix transaction safety timeout; releasing input without sending an unsafe action")
            cancel("safety timeout")
        }
    }

    private val worldChangeHandler = handler<WorldChangeEvent> {
        cancel("world changed")
        resetServerFacingState()
        resetDiagnosticBudget()
    }

    private val disconnectHandler = handler<DisconnectEvent> {
        cancel("disconnected")
        resetServerFacingState()
        resetDiagnosticBudget()
    }

    private val outgoingPacketHandler = handler<OutgoingPacketSentEvent> { event ->
        val packet = event.packet

        when (packet) {
            is ServerboundPlayerInputPacket -> observePlayerInput(packet)
            is ServerboundPlayerCommandPacket -> observePlayerCommand(packet)
            is ServerboundClientTickEndPacket -> observeClientTickEnd()
            is ServerboundContainerClickPacket -> observeContainerClick(packet)
            is ServerboundContainerClosePacket -> observeContainerClose(packet)
            is ServerboundContainerButtonClickPacket,
            is ServerboundContainerSlotStateChangedPacket,
            is ServerboundSetCreativeModeSlotPacket -> recordConflictingAction(packet)
            is ServerboundSetCarriedItemPacket -> observeHeldItemChange(packet)
            is ServerboundPlayerActionPacket -> observePlayerAction(packet)
            is ServerboundInteractPacket,
            is ServerboundUseItemPacket,
            is ServerboundUseItemOnPacket,
            is ServerboundPickItemFromBlockPacket,
            is ServerboundPickItemFromEntityPacket -> recordConflictingAction(packet)
        }
    }

    private fun observePlayerInput(packet: ServerboundPlayerInputPacket) {
        latestServerInput = packet.input

        if (state == State.IDLE) {
            return
        }

        if (packet.input.isStationary()) {
            stationaryInputObservedTick = clientTick
            diagnostic(
                "stationary input packet observed packet=${packet.javaClass.simpleName} " +
                    "input=${packet.input.describe()}"
            )
        } else {
            stationaryInputObservedTick = null
            stationaryStateConfirmedTick = null
            if (state == State.READY_TO_EXECUTE) {
                state = State.WAITING_FOR_STATIONARY_INPUT
            }
            diagnostic("moving input packet observed while transaction is pending input=${packet.input.describe()}")
        }
    }

    private fun observePlayerCommand(packet: ServerboundPlayerCommandPacket) {
        if (!inGame || packet.id != player.id) {
            return
        }

        when (packet.action) {
            ServerboundPlayerCommandPacket.Action.START_SPRINTING -> {
                serverSprinting = true
                sprintStopObserved = false
                stationaryStateConfirmedTick = null
                if (state == State.READY_TO_EXECUTE) {
                    state = State.WAITING_FOR_STATIONARY_INPUT
                }
            }

            ServerboundPlayerCommandPacket.Action.STOP_SPRINTING -> {
                serverSprinting = false
                if (state != State.IDLE) {
                    sprintStopObserved = true
                    diagnostic("sprint-stop state observed packet=${packet.javaClass.simpleName}")
                }
            }

            else -> return
        }
    }

    private fun observeClientTickEnd() {
        if (state == State.EXECUTING && expectedClicks.isEmpty() && !expectsOffhandSwap) {
            finishTransaction("offhand swap and carried-slot chain sent")
            return
        }

        if (state != State.WAITING_FOR_STATIONARY_INPUT || player.isUsingItem) {
            return
        }

        if (stationaryInputObservedTick != null && !serverSprinting &&
            (!requiresSprintStop || sprintStopObserved)) {
            stationaryStateConfirmedTick = clientTick
            state = State.READY_TO_EXECUTE
            diagnostic("stationary input/sprint state confirmed at client tick end; transaction waits until next tick")
        }
    }

    private fun observeContainerClick(packet: ServerboundContainerClickPacket) {
        val expectedIndex = expectedClicks.indexOfFirst { signature -> signature.matches(packet) }
        if (expectedIndex == -1) {
            if (state == State.WAITING_FOR_STATIONARY_INPUT || state == State.READY_TO_EXECUTE) {
                recordConflictingAction(packet)
            }
            return
        }

        expectedClicks.removeAt(expectedIndex)
        markFirstActionSent()
        diagnostic(
            "container click sent packet=${packet.javaClass.simpleName} container=${packet.containerId} " +
                "slot=${packet.slotNum} action=${packet.containerInput} " +
                "inventoryOpen=${InventoryManager.isInventoryOpenServerSide}"
        )

        if (expectedClicks.isEmpty()) {
            if (InventoryManager.isInventoryOpenServerSide) {
                state = State.WAITING_FOR_CLOSE
            } else {
                finishTransaction("all detached container clicks sent")
            }
        }
    }

    private fun observeContainerClose(packet: ServerboundContainerClosePacket) {
        if (state != State.WAITING_FOR_CLOSE || packet.containerId != 0) {
            if (state == State.WAITING_FOR_STATIONARY_INPUT || state == State.READY_TO_EXECUTE) {
                recordConflictingAction(packet)
            }
            return
        }

        diagnostic(
            "container close sent packet=${packet.javaClass.simpleName} container=${packet.containerId} " +
                "inventoryOpen=${InventoryManager.isInventoryOpenServerSide}"
        )
        closePacketObserved = true
        finishTransaction("container close sent")
    }

    private fun observeHeldItemChange(packet: ServerboundSetCarriedItemPacket) {
        if (state == State.EXECUTING && currentRequest?.expectsOffhandSwap == true) {
            diagnostic("held-item slot change sent packet=${packet.javaClass.simpleName} slot=${packet.slot}")
        } else if (state == State.WAITING_FOR_STATIONARY_INPUT || state == State.READY_TO_EXECUTE) {
            recordConflictingAction(packet)
        }
    }

    private fun observePlayerAction(packet: ServerboundPlayerActionPacket) {
        if (packet.action == ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND &&
            state == State.EXECUTING && expectsOffhandSwap) {
            expectsOffhandSwap = false
            markFirstActionSent()
            diagnostic("offhand swap action sent packet=${packet.javaClass.simpleName}")
            // The Smart path may still restore the selected hotbar slot after
            // this swap. Hold suppression through CLIENT_TICK_END so that final
            // carried-slot packet is part of the stationary transaction too.
            return
        }

        recordConflictingAction(packet)
    }

    private fun recordConflictingAction(packet: Packet<*>) {
        recordConflictingAction(packet.javaClass.simpleName)
    }

    private fun recordConflictingAction(description: String) {
        lastConflictingActionTick = clientTick
        lastConflictingPacket = description
        if (state == State.WAITING_FOR_STATIONARY_INPUT || state == State.READY_TO_EXECUTE) {
            diagnostic("conflicting action observed source=$lastConflictingPacket")
        }
    }

    private fun markFirstActionSent() {
        if (transactionExecuted) {
            return
        }

        transactionExecuted = true
        currentRequest?.let { request ->
            runCatching(request.onFirstActionSent).onFailure {
                logger.error("$diagnosticPrefix failed to record the started transaction", it)
            }
        }
    }

    private fun finishTransaction(reason: String) {
        state = State.RELEASE_ON_NEXT_TICK
        releaseOnTick = clientTick + 1
        diagnostic("transaction complete reason=$reason closeObserved=$closePacketObserved releaseTick=$releaseOnTick")
    }

    private fun clearState() {
        state = State.IDLE
        currentRequest = null
        requestPlayer = null
        requestTick = 0
        deadlineTick = 0
        stationaryInputObservedTick = null
        stationaryStateConfirmedTick = null
        requiresSprintStop = false
        sprintStopObserved = false
        expectedClicks.clear()
        expectsOffhandSwap = false
        transactionExecuted = false
        closePacketObserved = false
        releaseOnTick = 0
        diagnosticsEnabledForRequest = false
    }

    private fun resetServerFacingState() {
        latestServerInput = null
        serverSprinting = false
        lastConflictingActionTick = Long.MIN_VALUE
        lastConflictingPacket = ""
        lastConflictLogTick = Long.MIN_VALUE
    }

    private fun resetDiagnosticBudget() {
        diagnosticsRemaining = MAX_DIAGNOSTIC_TRANSACTIONS
        diagnosticsEnabledForRequest = false
    }

    private fun Request.matches(other: Request) =
        mode == other.mode &&
            sourceSlot == other.sourceSlot &&
            expectedSourceItem === other.expectedSourceItem &&
            switchMode == other.switchMode &&
            sendDirectly == other.sendDirectly &&
            clickSignatures == other.clickSignatures &&
            expectsOffhandSwap == other.expectsOffhandSwap

    private fun Request.sourceDescription(): String {
        val slotId = sourceSlot.getIdForServer(null)
        return "slot=$slotId item=${expectedSourceItem.descriptionId}"
    }

    private fun Input?.describe() = this?.let {
        "f=${it.forward()} b=${it.backward()} l=${it.left()} r=${it.right()} " +
            "jump=${it.jump()} sneak=${it.shift()} sprint=${it.sprint()}"
    } ?: "unknown"

    private fun Input.isStationary() =
        !forward() && !backward() && !left() && !right() && !jump() && !shift() && !sprint()

    private fun diagnostic(message: String) {
        if (diagnosticsEnabledForRequest) {
            val context = if (inGame) {
                " input=${player.input.playerInput.describe()} playerSprint=${player.isSprinting} " +
                    "inventoryOpen=${InventoryManager.isInventoryOpenServerSide}"
            } else {
                ""
            }
            logger.info("$diagnosticPrefix tick=$clientTick requestTick=$requestTick state=$state $message$context")
        }
    }

    private companion object {
        const val MINIMUM_TIMEOUT_TICKS = 20
        const val MAX_DIAGNOSTIC_TRANSACTIONS = 24
    }
}
