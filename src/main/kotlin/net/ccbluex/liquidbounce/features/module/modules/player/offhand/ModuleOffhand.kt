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
package net.ccbluex.liquidbounce.features.module.modules.player.offhand

import com.google.common.base.Predicate
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.KeyEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.RefreshArrayListEvent
import net.ccbluex.liquidbounce.event.events.ScheduleInventoryActionEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.inventory.HotbarItemSlot
import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.ccbluex.liquidbounce.utils.inventory.OffHandSlot
import net.ccbluex.liquidbounce.utils.item.isSword
import net.ccbluex.liquidbounce.utils.client.Chronometer
import net.ccbluex.liquidbounce.utils.client.isNewerThanOrEquals1_16
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.usesViaFabricPlus
import net.ccbluex.liquidbounce.utils.inventory.*
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.client.multiplayer.ClientLevel
import org.lwjgl.glfw.GLFW
import net.minecraft.client.player.LocalPlayer
import net.minecraft.world.inventory.ContainerInput
import java.util.ArrayDeque

internal fun isModuleRunningByExactName(name: String): Boolean {
    return ModuleManager.firstOrNull { module ->
        module.name.equals(name, ignoreCase = true) ||
            module.aliases.any { alias -> alias.equals(name, ignoreCase = true) }
    }?.running == true
}

/**
 * Offhand module
 *
 * Manages your offhand.
 */
object ModuleOffhand : ClientModule("Offhand", Category.PLAYER, aliases = arrayOf("AutoTotem")) {

    private val inventoryConstraints = tree(PlayerInventoryConstraints())
    private val switchMode = enumChoice(
        "SwitchMode",
        if (usesViaFabricPlus) SwitchMode.AUTOMATIC else SwitchMode.SWITCH
    )
    private val switchDelay by int("SwitchDelay", 0, 0..500, "ms")
    private val cycleSlots by key("Cycle", GLFW.GLFW_KEY_H)

    private object Gapple : ToggleableConfigurable(this, "Gapple", true) {
        object WhileHoldingSword : ToggleableConfigurable(this, "WhileHoldingSword", true) {
            val onlyWhileKa by boolean("OnlyWhileKillAura", true)
        }

        val gappleBind by key("GappleBind")

        init {
            tree(WhileHoldingSword)
        }
    }

    private object Crystal : ToggleableConfigurable(this, "Crystal", true) {
        val onlyWhileCa by boolean("OnlyWhileCrystalAura", false)
        val whenNoTotems by boolean("WhenNoTotems", true)
        val crystalBind by key("CrystalBind")
    }

    private object Strength : ToggleableConfigurable(this, "StrengthPotion", false) {
        val onlyWhileHoldingSword by boolean("OnlyWhileHoldingSword", true)
        val onlyWhileKa by boolean("OnlyWhileKillAura", true)
        val strengthBind by key("StrengthBind")
    }

    init {
        treeAll(
            Totem,
            Crystal,
            Gapple,
            Strength
        )
    }

    private val INVENTORY_MAIN_PRIORITY = Slots.Inventory + Slots.Hotbar
    private val INVENTORY_HOTBAR_PRIORITY = Slots.Hotbar + Slots.Inventory
    private val chronometer = Chronometer()
    private var activeMode: Mode = Mode.NONE
    private var lastMode: Mode? = null
    private var lastTagMode: Mode = Mode.NONE
    private var staticMode = Mode.NONE
    private var last: Pair<Item, ItemSlot>? = null
    private val transactionGate = InventoryActionMovementGate(
        ownerRunning = { running },
        diagnosticPrefix = "[OffhandTxnDiag]"
    )

    private enum class HotbarRefillState {
        WAITING_FOR_POST_POP_SETTLE,
        WAITING_FOR_CLICK,
        WAITING_FOR_POST_CLICK_BOUNDARY,
    }

    private data class PendingHotbarRefill(
        val player: LocalPlayer,
        val level: ClientLevel,
        val delayMs: Int,
        val targetHotbarIndex: Int,
        val targetSlot: HotbarItemSlot,
        val popBoundarySerial: Long,
        val delayTimer: Chronometer = Chronometer().also { it.reset() },
        var sourceSlot: InventoryItemSlot? = null,
        var state: HotbarRefillState = HotbarRefillState.WAITING_FOR_POST_POP_SETTLE,
        var clickDeadlineTick: Int = Int.MIN_VALUE,
        var clickBoundarySerial: Long = Long.MIN_VALUE,
        var validationTicksRemaining: Int = REFILL_TARGET_VALIDATION_TICKS,
    )

    private val pendingHotbarRefills = ArrayDeque<PendingHotbarRefill>()

    override val tag: String
        get() = activeMode.modeName

    override fun enable() {
        staticMode = when {
            Crystal.enabled && Mode.CRYSTAL.canCycleTo() -> Mode.CRYSTAL
            Gapple.enabled -> Mode.GAPPLE
            Totem.enabled && !Totem.Health.enabled -> Mode.TOTEM
            else -> Mode.NONE
        }
    }

    override fun disable() {
        clearPendingHotbarRefills()
        transactionGate.cancel("Offhand disabled")
    }

    @Suppress("unused")
    private val refillHotbarPopHandler = handler<TotemPopEvent> { event ->
        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (event.entity !== localPlayer || !Totem.enabled || !Totem.RefillHotbar.enabled) {
            return@handler
        }

        val targetHotbarIndex = Totem.RefillHotbar.totemSlot - 1
        if (targetHotbarIndex !in 0..8) {
            return@handler
        }

        pendingHotbarRefills.addLast(
            PendingHotbarRefill(
                player = localPlayer,
                level = level,
                delayMs = Totem.RefillHotbar.delay.random(),
                targetHotbarIndex = targetHotbarIndex,
                targetSlot = HotbarItemSlot(targetHotbarIndex),
                popBoundarySerial = ActionTickBoundary.currentSerial()
            )
        )
    }

    /**
     * Runs after the primary Offhand handler. The gate's IDLE state is the fully released state,
     * so this one detached click cannot overlap an accepted offhand transaction.
     */
    @Suppress("unused")
    private val refillHotbarScheduleHandler = handler<ScheduleInventoryActionEvent>(priority = 99) { event ->
        val pending = currentHotbarRefill() ?: return@handler
        val localPlayer = mc.player ?: run {
            clearPendingHotbarRefills()
            return@handler
        }
        val level = mc.level ?: run {
            clearPendingHotbarRefills()
            return@handler
        }
        if (!isRefillLifecycleValid(pending, localPlayer, level)) {
            clearPendingHotbarRefills()
            return@handler
        }

        if (pending.state != HotbarRefillState.WAITING_FOR_POST_POP_SETTLE) {
            return@handler
        }

        // #Edit By Codex 2026-07-17: status-35 arrives before vanilla and Offhand settle inventory,
        // so never decide from the stale pre-pop hotbar snapshot.
        if (!pending.delayTimer.hasElapsed(pending.delayMs.toLong()) ||
            !ActionTickBoundary.hasPassed(pending.popBoundarySerial) ||
            transactionGate.state != InventoryActionMovementGate.State.IDLE) {
            return@handler
        }

        val targetSlot = pending.targetSlot
        if (pending.targetHotbarIndex !in 0..8) {
            completeHotbarRefill(pending)
            return@handler
        }
        if (targetSlot.itemStack.item == Items.TOTEM_OF_UNDYING) {
            completeHotbarRefill(pending)
            return@handler
        }

        val sourceSlot = pending.sourceSlot ?: Slots.Inventory
            .filter { it.itemStack.item == Items.TOTEM_OF_UNDYING }
            .randomOrNull()
            ?.also { pending.sourceSlot = it }
            ?: run {
                completeHotbarRefill(pending)
                return@handler
            }

        if (sourceSlot.itemStack.item != Items.TOTEM_OF_UNDYING) {
            completeHotbarRefill(pending)
            return@handler
        }

        val action = ClickInventoryAction.performSwap(from = sourceSlot, to = targetSlot)
            .withDetachedPlayerInventoryClickPermit()
        if (!action.canPerformAction(inventoryConstraints)) {
            return@handler
        }

        pending.state = HotbarRefillState.WAITING_FOR_CLICK
        pending.clickDeadlineTick = localPlayer.tickCount + refillClickTimeoutTicks()
        event.schedule.add(
            InventoryActionChain(
                inventoryConstraints = inventoryConstraints,
                actions = arrayOf(action),
                priority = Priority.NORMAL,
                precondition = {
                    when {
                        currentHotbarRefill() !== pending -> false
                        !isRefillLifecycleValid(pending, mc.player, mc.level) -> {
                            clearPendingHotbarRefills()
                            false
                        }
                        pending.state != HotbarRefillState.WAITING_FOR_CLICK -> false
                        transactionGate.state != InventoryActionMovementGate.State.IDLE -> false
                        targetSlot.itemStack.item == Items.TOTEM_OF_UNDYING -> {
                            completeHotbarRefill(pending)
                            false
                        }
                        sourceSlot.itemStack.item != Items.TOTEM_OF_UNDYING -> {
                            completeHotbarRefill(pending)
                            false
                        }
                        else -> action.canPerformAction(inventoryConstraints)
                    }
                }
            )
        )
    }

    @Suppress("unused")
    private val refillHotbarSentHandler = handler<OutgoingPacketSentEvent> { event ->
        val pending = currentHotbarRefill() ?: return@handler
        if (pending.state != HotbarRefillState.WAITING_FOR_CLICK) {
            return@handler
        }
        val sourceSlot = pending.sourceSlot ?: return@handler
        val targetSlot = pending.targetSlot
        val packet = event.packet as? ServerboundContainerClickPacket ?: return@handler

        if (packet.containerId == 0 && packet.slotNum.toInt() == sourceSlot.getIdForServer(null) &&
            packet.buttonNum.toInt() == targetSlot.hotbarSlotForServer && packet.containerInput == ContainerInput.SWAP) {
            // Connection.send HEAD observes this before the outer click returns. Keep the cycle alive
            // until a later natural boundary proves that a post-click held-slot packet is safe.
            pending.state = HotbarRefillState.WAITING_FOR_POST_CLICK_BOUNDARY
            pending.clickBoundarySerial = ActionTickBoundary.currentSerial()
            pending.validationTicksRemaining = REFILL_TARGET_VALIDATION_TICKS
        }
    }

    @Suppress("unused")
    private val refillHotbarLifecycleHandler = handler<GameTickEvent> {
        val pending = currentHotbarRefill() ?: return@handler
        val localPlayer = mc.player
        val level = mc.level
        if (!isRefillLifecycleValid(pending, localPlayer, level)) {
            clearPendingHotbarRefills()
            return@handler
        }

        when (pending.state) {
            HotbarRefillState.WAITING_FOR_POST_POP_SETTLE -> Unit

            HotbarRefillState.WAITING_FOR_CLICK -> {
                if (localPlayer!!.tickCount >= pending.clickDeadlineTick) {
                    completeHotbarRefill(pending)
                }
            }

            HotbarRefillState.WAITING_FOR_POST_CLICK_BOUNDARY -> {
                completeRefillAfterClickBoundary(pending, localPlayer!!)
            }
        }
    }

    @Suppress("unused")
    private val refillHotbarWorldChangeHandler = handler<WorldChangeEvent> {
        clearPendingHotbarRefills()
    }

    @Suppress("unused")
    private val refillHotbarDisconnectHandler = handler<DisconnectEvent> {
        clearPendingHotbarRefills()
    }

    private fun completeRefillAfterClickBoundary(pending: PendingHotbarRefill, localPlayer: LocalPlayer) {
        if (!ActionTickBoundary.hasPassed(pending.clickBoundarySerial)) {
            return
        }

        if (pending.targetHotbarIndex !in 0..8 ||
            pending.targetSlot.itemStack.item != Items.TOTEM_OF_UNDYING) {
            if (pending.validationTicksRemaining-- <= 0) {
                completeHotbarRefill(pending)
            }
            return
        }

        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = "Offhand RefillHotbar",
                actionBoundarySerial = pending.clickBoundarySerial,
                diagnosticsEnabled = false
            )
        ) {
            return
        }

        if (localPlayer.inventory.selectedSlot == pending.targetHotbarIndex) {
            completeHotbarRefill(pending)
            return
        }

        val gameMode = mc.gameMode ?: run {
            completeHotbarRefill(pending)
            return
        }
        val expectation = ActionTickBoundary.expectHeldItemChange(
            owner = "Offhand RefillHotbar",
            purpose = "select refilled TotemSlot",
            postActionBoundarySerial = pending.clickBoundarySerial,
            diagnosticsEnabled = false
        )
        if (!expectation.allowed) {
            return
        }

        // #Edit By Codex 2026-07-17: synchronizing selection in Connection.send HEAD could overtake
        // the refill click. Only select TotemSlot after the observed click's CLIENT_TICK_END boundary.
        localPlayer.inventory.selectedSlot = pending.targetHotbarIndex
        gameMode.ensureHasSentCarriedItem()
        ActionTickBoundary.completeHeldItemChangeAttempt(expectation)
        completeHotbarRefill(pending)
    }

    private fun currentHotbarRefill(): PendingHotbarRefill? = pendingHotbarRefills.peekFirst()

    private fun isRefillLifecycleValid(
        pending: PendingHotbarRefill,
        localPlayer: LocalPlayer?,
        level: ClientLevel?,
    ) = running && Totem.enabled && Totem.RefillHotbar.enabled &&
        localPlayer === pending.player && level === pending.level

    private fun completeHotbarRefill(pending: PendingHotbarRefill) {
        if (pendingHotbarRefills.peekFirst() === pending) {
            pendingHotbarRefills.removeFirst()
        } else {
            pendingHotbarRefills.remove(pending)
        }
    }

    private fun clearPendingHotbarRefills() {
        pendingHotbarRefills.clear()
    }

    private fun refillClickTimeoutTicks() = (
        inventoryConstraints.startDelay.last + inventoryConstraints.clickDelay.last +
            inventoryConstraints.closeDelay.last + 4
        ).coerceAtLeast(20)

    private const val REFILL_TARGET_VALIDATION_TICKS = 5

    @Suppress("unused")
    val keyHandler = handler<KeyEvent> {
        if (it.action != GLFW.GLFW_PRESS) {
            return@handler
        }

        when (it.key.value) {
            Gapple.gappleBind.value -> Mode.GAPPLE.onBindPress()
            Crystal.crystalBind.value -> Mode.CRYSTAL.onBindPress()
            Strength.strengthBind.value -> {
                // since we can't cycle to strength, its status has to be checked here
                if (Strength.enabled) {
                    Mode.STRENGTH.onBindPress()
                }
            }

            cycleSlots.value -> {
                val entries = Mode.entries
                val startIndex = staticMode.ordinal
                var index = (startIndex + 1) % entries.size

                while (index != startIndex) {
                    val mode = entries[index]
                    if (mode.canCycleTo()) {
                        staticMode = mode
                        return@handler
                    }

                    index = (index + 1) % entries.size
                }
            }
        }
    }

    @Suppress("unused")
    private val autoTotemHandler = handler<ScheduleInventoryActionEvent>(priority = 100) {
        activeMode = Mode.entries.firstOrNull(Mode::shouldEquip) ?: staticMode
        if (activeMode == Mode.NONE && Totem.Health.switchBack && lastMode == Mode.TOTEM) {
            activeMode = Mode.BACK
        }

        if (activeMode != lastTagMode) {
            EventManager.callEvent(RefreshArrayListEvent)
            lastTagMode = activeMode
        }

        if (activeMode != lastMode && lastMode == Mode.TOTEM) {
            if (!Totem.switchBackStarted) {
                Totem.switchBack.reset()
            }

            Totem.switchBackStarted = true
            if (!Totem.switchBack.hasElapsed(Totem.switchBackDelay.toLong())) {
                return@handler
            }
        }

        Totem.switchBackStarted = false

        if (!chronometer.hasElapsed(activeMode.getDelay().toLong())) {
            return@handler
        }

        val slot = activeMode.getSlot() ?: return@handler
        lastMode = activeMode

        // the item is already located in Off-hand slot
        if (slot == OffHandSlot) {
            return@handler
        }

        val requestedMode = activeMode
        val expectedSourceItem = slot.itemStack.item
        val plan = switchMode.get().performSwitch(slot)
        val actions = plan.clickActions.map(ClickInventoryAction::withDetachedPlayerInventoryClickPermit)
        val directSend = requestedMode == Mode.TOTEM && Totem.sendsDirectly
        if (Totem.Health.switchBack) {
            last = expectedSourceItem to slot
        }
        val request = InventoryActionMovementGate.Request(
            mode = requestedMode.modeName,
            sourceSlot = slot,
            expectedSourceItem = expectedSourceItem,
            switchMode = switchMode.get().choiceName,
            sendDirectly = directSend,
            clickSignatures = actions.mapNotNull(ClickInventoryAction::inventoryClickSignature),
            expectsOffhandSwap = plan.usesHotbarOffhandSwap,
            timeoutTicks = transactionTimeout(plan, directSend),
            onFirstActionSent = {},
        )

        transactionGate.request(request)
        if (!transactionGate.isCurrent(request)) {
            return@handler
        }

        // The first pickup legitimately changes the source slot. Only reject
        // a moved source before this transaction has actually emitted its
        // first expected packet; afterwards the gate owns the atomic chain.
        if (!transactionGate.hasStarted(request) && !transactionGate.isSourceValid(request)) {
            transactionGate.cancel("source item moved before transaction execution")
            return@handler
        }

        if (!transactionGate.isReady(request)) {
            return@handler
        }

        // A scheduled transaction has to be immediately viable. Once the
        // InventoryManager starts it, its TickSequence owns configured
        // Start/Click/Close delays; if it cannot start, leave the gate ready
        // instead of losing the request behind an empty schedule.
        if (!directSend && !plan.usesHotbarOffhandSwap &&
            !actions.all { action -> action.canPerformAction(inventoryConstraints) }) {
            return@handler
        }

        if (!transactionGate.beginExecution(request)) {
            return@handler
        }

        // Keep the restored module's delay bookkeeping at the point where its
        // action chain is accepted; the gate only changes packet ordering.
        chronometer.reset()

        when {
            plan.usesHotbarOffhandSwap -> performHotbarOffhandSwap(slot)
            directSend -> actions.forEach { action -> action.performAction() }
            else -> it.schedule.add(
                InventoryActionChain(
                    inventoryConstraints = inventoryConstraints,
                    actions = actions.toTypedArray(),
                    priority = Priority.NORMAL,
                    precondition = {
                        // A pickup transaction intentionally moves the source
                        // after its first click. Revalidate while waiting, then
                        // let the already-started click chain finish normally.
                        // A reset/cancellation still stops every remaining click.
                        when {
                            !transactionGate.isCurrent(request) -> false
                            !actions.all { action -> action.canPerformAction(inventoryConstraints) } -> {
                                transactionGate.cancel("scheduled action became unavailable")
                                false
                            }
                            transactionGate.hasStarted(request) -> true
                            else -> transactionGate.isSourceValid(request).also { valid ->
                                if (!valid) transactionGate.cancel("source item moved before scheduled click")
                            }
                        }
                    }
                )
            )
        }
    }

    private data class SwitchPlan(
        val clickActions: List<ClickInventoryAction>,
        val usesHotbarOffhandSwap: Boolean = false,
    )

    private fun performSwitch(from: ItemSlot, smart: Boolean): SwitchPlan {
        if (smart && from is HotbarItemSlot) {
            return SwitchPlan(emptyList(), usesHotbarOffhandSwap = true)
        }

        val actions = ArrayList<ClickInventoryAction>(3)
        actions += ClickInventoryAction.performPickup(slot = from)
        actions += ClickInventoryAction.performPickup(slot = OffHandSlot)
        if (!OffHandSlot.itemStack.isEmpty) {
            actions += ClickInventoryAction.performPickup(slot = from)
        }

        return SwitchPlan(actions)
    }

    private fun performHotbarOffhandSwap(from: ItemSlot) {
        val hotbarSlot = from as? HotbarItemSlot ?: return
        val selectedSlot = player.inventory.selectedSlot
        val targetSlot = hotbarSlot.hotbarSlot

        if (selectedSlot != targetSlot) {
            network.sendPacket(ServerboundSetCarriedItemPacket(targetSlot))
        }
        network.sendPacket(
            ServerboundPlayerActionPacket(
                ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND,
                BlockPos.ZERO,
                Direction.DOWN
            )
        )
        if (selectedSlot != targetSlot) {
            network.sendPacket(ServerboundSetCarriedItemPacket(selectedSlot))
        }
    }

    private fun transactionTimeout(plan: SwitchPlan, directSend: Boolean): Int {
        if (directSend || plan.usesHotbarOffhandSwap) {
            return 20
        }

        // On current protocols a detached player-inventory action remains
        // detached, so InventoryManager may apply StartDelay before each click
        // while it confirms the player inventory is available. Budget every
        // action rather than timing out a valid configured chain early.
        return inventoryConstraints.startDelay.last * (plan.clickActions.size + 1) +
            inventoryConstraints.clickDelay.last * plan.clickActions.size +
            inventoryConstraints.closeDelay.last + 4
    }

    fun isOperating() = running && activeMode != Mode.NONE

    private enum class Mode(val modeName: String, private val item: Item, private val fallBackItem: Item? = null) {
        TOTEM("Totem", Items.TOTEM_OF_UNDYING) {
            override fun shouldEquip() = Totem.shouldEquip()

            override fun getDelay() = Totem.switchDelay

            override fun getPrioritizedInventoryPart() = 1

            override fun getSlot(): ItemSlot? {
                val slot = super.getSlot()
                if (slot == null && Crystal.enabled && Crystal.whenNoTotems) {
                    return CRYSTAL.getSlot()
                }

                return slot
            }

            override fun canCycleTo() = Totem.enabled
        },
        STRENGTH("Strength", Items.POTION) {
            val isStrengthPotion = Predicate<ItemStack> { stack ->
                if (stack.item != Items.POTION) {
                    return@Predicate false
                }

                val content = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                return@Predicate content.effects.any { effect -> effect.effectType == MobEffects.STRENGTH }
            }

            override fun shouldEquip(): Boolean {
                val killAura = Strength.onlyWhileKa && !ModuleKillAura.running
                if (!Strength.enabled || killAura || player.hasStatusEffect(MobEffects.STRENGTH)) {
                    return false
                }

                return player.mainHandItem.isSword || !Strength.onlyWhileHoldingSword
            }

            override fun getSlot(): ItemSlot? {
                if (isStrengthPotion.test(player.offhandItem)) {
                    return OffHandSlot
                }

                return INVENTORY_MAIN_PRIORITY.findSlot { isStrengthPotion.test(it) }
            }
        },
        GAPPLE("Gapple", Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_APPLE) {
            override fun shouldEquip(): Boolean {
                if (!Gapple.enabled) {
                    return false
                }

                if (player.mainHandItem.isSword && Gapple.WhileHoldingSword.enabled) {
                    return if (Gapple.WhileHoldingSword.onlyWhileKa) {
                        ModuleKillAura.running
                    } else {
                        true
                    }
                }

                return false
            }

            override fun canCycleTo() = Gapple.enabled
        },
        CRYSTAL("Crystal", Items.END_CRYSTAL) {
            override fun canCycleTo() = Crystal.enabled &&
                (!Crystal.onlyWhileCa || isModuleRunningByExactName("CrystalAura"))
        },
        BACK("Back", Items.AIR) {
            override fun getSlot(): ItemSlot? {
                return last?.let {
                    if (it.first == it.second.itemStack.item) it.second else null
                }
            }
        },
        NONE("None", Items.AIR);

        private var modeBeforeDirectSwitch: Mode? = null

        open fun shouldEquip() = false

        open fun getDelay() = switchDelay

        open fun canCycleTo() = false

        /**
         * 0 = Main inventory
         * 1 = Hotbar
         */
        open fun getPrioritizedInventoryPart() = 0

        fun onBindPress() {
            if (activeMode == this && modeBeforeDirectSwitch != null && modeBeforeDirectSwitch!!.canCycleTo()) {
                staticMode = modeBeforeDirectSwitch!!
                modeBeforeDirectSwitch = null
            } else if (canCycleTo()) {
                modeBeforeDirectSwitch = staticMode
                staticMode = this
            } else {
                modeBeforeDirectSwitch = null
            }
        }

        open fun getSlot(): ItemSlot? {
            if (item == Items.AIR) {
                return null
            }

            if (player.offhandItem.item == item) {
                return OffHandSlot
            }

            val slots = if (getPrioritizedInventoryPart() == 0) {
                INVENTORY_MAIN_PRIORITY
            } else {
                INVENTORY_HOTBAR_PRIORITY
            }

            var itemSlot = slots.findSlot(item)
            if (itemSlot == null && fallBackItem != null) {
                if (player.offhandItem.item == fallBackItem) {
                    return OffHandSlot
                }

                itemSlot = slots.findSlot(fallBackItem)
            }

            return itemSlot
        }
    }

    @Suppress("unused")
    private enum class SwitchMode(override val choiceName: String) : NamedChoice {
        /**
         * Pickup, but it performs a SWAP_ITEM_WITH_OFFHAND action whenever possible to possible send fewer packets.
         * Works on all versions.
         *
         * It's not the default because some servers kick you when you perform a SWAP_ITEM_WITH_OFFHAND action
         * often and quickly.
         */
        SMART("Smart") {
            override fun performSwitch(from: ItemSlot) = ModuleOffhand.performSwitch(from, true)
        },

        /**
         * Performs a switch action, works on 1.16.
         * The best method on newer servers.
         */
        SWITCH("Switch") {
            override fun performSwitch(from: ItemSlot) = listOf(
                ClickInventoryAction.performSwap(
                    from = from,
                    to = OffHandSlot
                )
            ).let(::SwitchPlan)
        },

        /**
         * Performs 2-3 a pickup actions.
         * Works on all versions.
         */
        PICKUP("PickUp") {
            override fun performSwitch(from: ItemSlot) = ModuleOffhand.performSwitch(from, false)
        },

        /**
         * Chooses the switch action based on the version. Only works if vfp is installed.
         */
        AUTOMATIC("Automatic") {
            override fun performSwitch(from: ItemSlot): SwitchPlan {
                return if (isNewerThanOrEquals1_16) {
                    SWITCH.performSwitch(from)
                } else {
                    PICKUP.performSwitch(from)
                }
            }
        };

        abstract fun performSwitch(from: ItemSlot): SwitchPlan
    }

}
