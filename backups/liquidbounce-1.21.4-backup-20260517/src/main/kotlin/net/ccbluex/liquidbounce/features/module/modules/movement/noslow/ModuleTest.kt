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
package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.client.player
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.util.Hand

/**
 * Dreamcore NoSlow port for testing without replacing the current NoSlow module.
 */
object ModuleTest : ClientModule("Test", Category.MOVEMENT) {

    private val mode by enumChoice("Mode", Mode.GRIM_AUTO).apply { tagBy(this) }
    private val sprintWhileUsing by boolean("SprintWhileUsing", false)

    private var ticks = 0
    private var cachedTick = -1
    private var cachedNoSlow = false

    @Suppress("unused")
    private val tickHandler = tickHandler {
        if (player.isGliding) {
            resetState()
            return@tickHandler
        }

        val usableItem = findUsableItem()
        val canUseUsable = isUsableState(usableItem)

        when (mode) {
            Mode.GRIM_AUTO -> {
                if (canUseUsable) {
                    grimUsableTick(usableItem!!)
                } else {
                    grimTicksTick()
                }
            }

            Mode.GRIM_USABLE -> {
                if (canUseUsable) {
                    grimUsableTick(usableItem!!)
                }
            }

            Mode.GRIM_TICKS -> grimTicksTick()
        }
    }

    override fun disable() {
        resetState()
    }

    fun canNoSlow(): Boolean {
        val localPlayer = mc.player ?: return false
        if (!running || mc.world == null || localPlayer.isGliding) {
            resetState()
            return false
        }

        val tick = localPlayer.age
        if (tick == cachedTick) {
            return cachedNoSlow
        }

        val usableItem = findUsableItem()
        val canUseUsable = isUsableState(usableItem)
        val result = when (mode) {
            Mode.GRIM_AUTO -> if (canUseUsable) canGrimUsable() else canGrimTicks()
            Mode.GRIM_USABLE -> canUseUsable && canGrimUsable()
            Mode.GRIM_TICKS -> canGrimTicks()
        }

        cachedTick = tick
        cachedNoSlow = result
        return result
    }

    fun shouldAllowSprintWhileUsing(): Boolean {
        return sprintWhileUsing && canNoSlow()
    }

    private fun grimTicksTick() {
        if (player.isUsingItem) {
            ticks++
        } else {
            ticks = 0
        }
    }

    private fun canGrimTicks(): Boolean {
        if (ticks > 1) {
            ticks = 0
            return true
        }

        return false
    }

    private fun grimUsableTick(item: Item) {
        if (player.age % 2 == 0) {
            useItem(item)
        }
    }

    private fun canGrimUsable(): Boolean {
        return player.itemUseTime >= 2
    }

    private fun isUsableState(item: Item?): Boolean {
        return player.isUsingItem && player.activeHand == Hand.OFF_HAND && item != null
    }

    private fun findUsableItem(): Item? {
        return when {
            findInventoryIndex(Items.CROSSBOW) != -1 -> Items.CROSSBOW
            findInventoryIndex(Items.BOW) != -1 -> Items.BOW
            else -> null
        }
    }

    private fun useItem(item: Item) {
        val itemSlot = findInventoryIndex(item)
        if (itemSlot == -1) {
            return
        }

        val selectedSlot = player.inventory.selectedSlot
        val hotbarSlot = if (itemSlot in 0..8) itemSlot else (selectedSlot % 8) + 1

        if (itemSlot > 8) {
            interaction.clickSlot(
                player.currentScreenHandler.syncId,
                itemSlot,
                hotbarSlot,
                SlotActionType.SWAP,
                player
            )
        }

        useHotbarSlot(hotbarSlot)
    }

    private fun useHotbarSlot(slot: Int) {
        val selectedSlot = player.inventory.selectedSlot
        if (slot != selectedSlot) {
            player.inventory.selectedSlot = slot
            network.sendPacket(UpdateSelectedSlotC2SPacket(slot))
        }

        interaction.syncSelectedSlot()
        interaction.interactItem(player, Hand.MAIN_HAND)

        if (slot != selectedSlot) {
            player.inventory.selectedSlot = selectedSlot
            network.sendPacket(UpdateSelectedSlotC2SPacket(selectedSlot))
        }
    }

    private fun findInventoryIndex(item: Item): Int {
        return player.inventory.main.indexOfFirst { stack ->
            stack.item === item
        }
    }

    private fun resetState() {
        ticks = 0
        cachedTick = -1
        cachedNoSlow = false
    }

    private enum class Mode(override val choiceName: String) : NamedChoice {
        GRIM_AUTO("Grim Auto"),
        GRIM_USABLE("Grim Usable"),
        GRIM_TICKS("Grim Ticks")
    }
}
