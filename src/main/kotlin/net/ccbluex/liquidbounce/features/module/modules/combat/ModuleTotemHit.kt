/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.getEntityById
import net.ccbluex.liquidbounce.utils.entity.mainHandItem
import net.ccbluex.liquidbounce.utils.entity.sendPacket
import net.ccbluex.liquidbounce.utils.entity.selectedSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.network.protocol.game.ServerboundAttackPacket
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items

/** Strict Prestige TotemHit port. */
object ModuleTotemHit : ClientModule("TotemHit", Category.COMBAT) {

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.OUTGOING) {
            return@handler
        }

        val attackPacket = event.packet as? ServerboundAttackPacket ?: return@handler
        val level = mc.level ?: return@handler
        if (level.getEntityById(attackPacket.entityId()) !is Player || player.mainHandItem.item != Items.TOTEM_OF_UNDYING) {
            return@handler
        }

        val swordSlot = Slots.Hotbar.firstOrNull { it.itemStack.isSword } ?: return@handler
        val previousSlot = player.inventory.selectedSlot

        try {
            player.inventory.selectedSlot = swordSlot.hotbarSlot
            interaction.ensureHasSentCarriedItem()

            // #Edit By Codex 2026-07-17: normal nested send re-enters PacketEvent while the visible
            // main hand is the Sword, so Prestige's Totem condition naturally prevents recursion.
            network.sendPacket(attackPacket)
        } finally {
            player.inventory.selectedSlot = previousSlot
            // Restore before the outer attack leaves so its server-side carried slot is the original one.
            interaction.ensureHasSentCarriedItem()
        }
    }
}
