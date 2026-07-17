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

import net.ccbluex.liquidbounce.event.events.PlayerMovementTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.attackEntity
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.selectedSlot
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.phys.EntityHitResult

/** Strict Prestige AutoShieldBreaker port. */
object ModuleAutoShieldBreaker : ClientModule("AutoShieldBreaker", Category.COMBAT) {

    @Suppress("unused")
    private val movementHandler = handler<PlayerMovementTickEvent> {
        val target = (mc.crosshairTarget as? EntityHitResult)?.entity as? Player ?: return@handler
        if (!target.isUsingItem || target.useItem.item != Items.SHIELD) {
            return@handler
        }

        // The source assigns -1 on a miss; the requested compatibility correction leaves the slot untouched.
        val axeSlot = Slots.Hotbar.firstOrNull { it.itemStack.isAxe } ?: return@handler
        player.inventory.selectedSlot = axeSlot.hotbarSlot
        interaction.ensureHasSentCarriedItem()
        interaction.attackEntity(player, target)
        player.swingHand(InteractionHand.MAIN_HAND)
    }
}
