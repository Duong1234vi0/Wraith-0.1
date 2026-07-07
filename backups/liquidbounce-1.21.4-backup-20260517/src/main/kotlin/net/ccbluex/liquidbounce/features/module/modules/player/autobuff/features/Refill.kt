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
 *
 *
 */

package net.ccbluex.liquidbounce.features.module.modules.player.autobuff.features

import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.ScheduleInventoryActionEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.player.autobuff.ModuleAutoBuff
import net.ccbluex.liquidbounce.features.module.modules.player.autobuff.ModuleAutoBuff.features
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.sendPacketSilently
import net.ccbluex.liquidbounce.utils.inventory.*
import net.ccbluex.liquidbounce.utils.item.isNothing
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket

object Refill : ToggleableConfigurable(ModuleAutoBuff, "Refill", true) {

    private val inventoryConstraints = tree(PlayerInventoryConstraints())
    private val delayedContainerPackets = mutableListOf<Packet<*>>()
    private var capturingRefillClick = false

    fun execute(event: ScheduleInventoryActionEvent) {
        val emptyHotbarSlots = Slots.Hotbar.count { it.itemStack.isNothing() }
        if (emptyHotbarSlots == 0) {
            return
        }

        val validFeatures = features.filter { it.enabled }

        // Refill only as many items as the hotbar can actually accept. Scheduling every valid potion
        // makes the inventory manager spam redundant quick-move attempts when click delays are zero.
        val validItems = validFeatures.asSequence()
            .flatMap { feature ->
                Slots.Inventory.asSequence().filter { slot -> feature.isValidItem(slot.itemStack, false) }
            }
            .distinct()
            .take(emptyHotbarSlots)
            .toList()

        // Check if we have any valid items
        if (validItems.isEmpty()) {
            return
        }

        // Sort the items by the order of the features
        for (slot in validItems) {
            val quickMove = ClickInventoryAction.performQuickMove(slot = slot)
            event.schedule(
                inventoryConstraints, BufferedRefillClickAction(quickMove),
                Priority.IMPORTANT_FOR_USAGE_1
            )
        }
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent>(priority = (FIRST_PRIORITY - 1).toShort()) { event ->
        if (!running || event.origin != TransferOrigin.OUTGOING || event.isCancelled) {
            return@handler
        }

        val packet = event.packet
        val shouldBuffer = capturingRefillClick && packet is ClickSlotC2SPacket ||
            delayedContainerPackets.isNotEmpty() && packet is CloseHandledScreenC2SPacket

        if (!shouldBuffer) {
            return@handler
        }

        event.cancelEvent()
        delayedContainerPackets += packet
    }

    @Suppress("unused")
    private val movementInputHandler = handler<MovementInputEvent>(priority = FINAL_DECISION) { event ->
        if (!running || delayedContainerPackets.isEmpty()) {
            return@handler
        }

        val packets = delayedContainerPackets.toTypedArray()
        delayedContainerPackets.clear()

        event.sneak = false
        event.jump = false
        event.directionalInput = DirectionalInput.NONE

        mc.send {
            packets.forEach(::sendPacketSilently)
        }
    }

    private class BufferedRefillClickAction(
        private val clickAction: ClickInventoryAction
    ) : InventoryAction {

        override fun canPerformAction(inventoryConstraints: InventoryConstraints) =
            clickAction.canPerformAction(inventoryConstraints)

        override fun performAction(): Boolean {
            Refill.capturingRefillClick = true
            try {
                return clickAction.performAction()
            } finally {
                Refill.capturingRefillClick = false
            }
        }

        override fun requiresPlayerInventoryOpen() = clickAction.requiresPlayerInventoryOpen()

    }

}
