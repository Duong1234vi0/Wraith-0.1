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
package net.ccbluex.liquidbounce.features.module.modules.player

import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.SilentHotbar
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.inventory.InventoryItemSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.client.util.InputUtil
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import org.lwjgl.glfw.GLFW
import java.util.Random

/**
 * ClickAction ported from Unique 1.7.
 *
 * Pearl throws an ender pearl on key press, XP toggles repeated experience bottle use,
 * and Friend toggles the player currently under the crosshair.
 */
@Suppress("MagicNumber")
object ModuleClickAction : ClientModule("ClickAction", Category.PLAYER) {

    private val pearl by boolean("Pearl", true)
    private val xp by boolean("XP", true)
    private val friend by boolean("Friend", false)
    private val pearlVisualSpoof by boolean("PearlVisualSpoof", true)

    private val pearlKey by key("PearlKey", GLFW.GLFW_KEY_INSERT)
    private val xpKey by key("XPKey", GLFW.GLFW_KEY_KP_0)
    private val friendKey by key("FriendKey", GLFW.GLFW_KEY_0)

    private val feetXP by boolean("FeetXP", true)
    private val feetPitch by float("FeetXPPitch", 70.0f, 45.0f..90.0f)
    private val antiWaste by boolean("AntiWaste", true)
    private val stopOn by int("StopOn", 95, 0..100, "%")

    private var pearlPressed = false
    private var friendPressed = false
    private var xpEnabled = false
    private var xpKeyPressed = false
    private var lastXpTime = 0L
    private var silentRestoreTicks = -1
    private var pendingInventoryPearlAction: PendingInventoryPearlAction? = null
    private var visualSpoofHotbarSlot = -1
    private var visualSpoofStack = ItemStack.EMPTY
    private var visualSpoofTicks = -1

    private val random = Random()

    @Suppress("unused")
    private val movementInputHandler = handler<MovementInputEvent>(priority = FINAL_DECISION) { event ->
        val action = pendingInventoryPearlAction ?: return@handler
        if (action.stage != InventoryPearlStage.SWAP && action.stage != InventoryPearlStage.RESTORE) {
            return@handler
        }

        if (action.ticks > 0) {
            return@handler
        }

        val localPlayer = mc.player ?: return@handler

        event.sneak = false
        event.jump = false
        event.directionalInput = DirectionalInput.NONE

        val stage = action.stage
        val syncId = action.syncId
        val pearlSlotId = action.pearlSlotId
        val hotbarSlot = action.hotbarSlot

        mc.send {
            interaction.clickSlot(syncId, pearlSlotId, hotbarSlot, SlotActionType.SWAP, localPlayer)
        }

        when (stage) {
            InventoryPearlStage.SWAP -> {
                action.stage = InventoryPearlStage.USE
                action.ticks = 0
            }

            InventoryPearlStage.RESTORE -> {
                silentRestoreTicks = 1
                pendingInventoryPearlAction = null
            }

            InventoryPearlStage.USE -> { }
        }
    }

    @Suppress("unused")
    private val tickHandler = tickHandler {
        if (mc.player == null || mc.interactionManager == null) {
            return@tickHandler
        }

        handlePendingRestores()

        if (pearl) {
            val pressed = isKeyPressed(pearlKey)

            if (pressed && !pearlPressed) {
                throwPearl()
                pearlPressed = true
            } else if (!pressed) {
                pearlPressed = false
            }
        }

        if (xp) {
            handleXpToggle()

            if (xpEnabled) {
                handleXP()
            }
        } else {
            xpEnabled = false
        }

        if (friend) {
            val pressed = isKeyPressed(friendKey)

            if (pressed && !friendPressed) {
                handleFriend()
                friendPressed = true
            } else if (!pressed) {
                friendPressed = false
            }
        }
    }

    override fun disable() {
        xpEnabled = false
        xpKeyPressed = false
        pearlPressed = false
        friendPressed = false
        silentRestoreTicks = -1
        pendingInventoryPearlAction = null
        resetPearlVisualSpoof()
        SilentHotbar.resetSlot(this)
    }

    fun getRenderedHotbarStack(stack: ItemStack): ItemStack {
        val localPlayer = mc.player ?: return stack
        if (!pearlVisualSpoof || visualSpoofHotbarSlot !in 0..8 || visualSpoofStack.isEmpty && stack.isEmpty) {
            return stack
        }

        return if (localPlayer.inventory.getStack(visualSpoofHotbarSlot) === stack) {
            visualSpoofStack
        } else {
            stack
        }
    }

    private fun handleXpToggle() {
        val pressed = isKeyPressed(xpKey)

        if (pressed && !xpKeyPressed) {
            xpEnabled = !xpEnabled
        }

        xpKeyPressed = pressed
    }

    private fun handleXP() {
        if (antiWaste && !needXp()) {
            xpEnabled = false
            return
        }

        val xpSlot = Slots.Hotbar.findClosestSlot(Items.EXPERIENCE_BOTTLE)?.hotbarSlot
        if (xpSlot == null) {
            xpEnabled = false
            return
        }

        val localPlayer = mc.player ?: return

        if (feetXP) {
            localPlayer.pitch = feetPitch
        }

        val now = System.currentTimeMillis()
        val delay = 23 + random.nextInt(15)
        if (now - lastXpTime < delay) {
            return
        }

        useHotbarSlot(xpSlot)
        lastXpTime = now
    }

    private fun needXp(): Boolean {
        val localPlayer = mc.player ?: return false
        val limit = stopOn.toFloat()

        return localPlayer.inventory.armor.any { it.needsRepairBelow(limit) } ||
            localPlayer.inventory.main.any { it.needsRepairBelow(limit) }
    }

    private fun ItemStack.needsRepairBelow(limit: Float): Boolean {
        if (isEmpty || !isDamageable || maxDamage <= 0) {
            return false
        }

        val remaining = (maxDamage - damage).toFloat() / maxDamage.toFloat() * 100.0f
        return remaining < limit
    }

    private fun throwPearl() {
        val localPlayer = mc.player ?: return
        if (mc.interactionManager == null) {
            return
        }

        val hotbarPearl = Slots.Hotbar.findClosestSlot(Items.ENDER_PEARL)

        if (hotbarPearl != null) {
            useHotbarSlot(hotbarPearl.hotbarSlot)
            return
        }

        val inventoryPearl = findPearlInInventory() ?: return
        val targetHotbarSlot = findInventoryPearlTargetSlot(localPlayer.inventory.selectedSlot)
        val pearlInventoryIndex = inventoryPearl.inventoryIndex
        if (pearlInventoryIndex !in 9..35) {
            return
        }

        if (pendingInventoryPearlAction != null) {
            return
        }

        val syncId = localPlayer.currentScreenHandler.syncId
        val pearlSlotId = inventoryPearl.getIdForServerWithCurrentScreen() ?: return
        beginPearlVisualSpoof(targetHotbarSlot)
        pendingInventoryPearlAction = PendingInventoryPearlAction(
            pearlInventoryIndex,
            targetHotbarSlot,
            syncId,
            pearlSlotId,
            ticks = 0,
            stage = InventoryPearlStage.SWAP
        )
    }

    private fun useHotbarSlot(slot: Int) {
        val localPlayer = mc.player ?: return

        SilentHotbar.selectSlotSilently(this, slot, 1)
        interaction.syncSelectedSlot()
        interaction.interactItem(localPlayer, Hand.MAIN_HAND)
        localPlayer.swingHand(Hand.MAIN_HAND)
        silentRestoreTicks = 1
    }

    private fun handlePendingRestores() {
        val localPlayer = mc.player ?: return

        pendingInventoryPearlAction?.let { action ->
            if (action.ticks > 0) {
                action.ticks--
            } else {
                when (action.stage) {
                    InventoryPearlStage.SWAP -> { }

                    InventoryPearlStage.USE -> {
                        SilentHotbar.selectSlotSilently(this, action.hotbarSlot, 2)
                        interaction.syncSelectedSlot()
                        interaction.interactItem(localPlayer, Hand.MAIN_HAND)
                        localPlayer.swingHand(Hand.MAIN_HAND)
                        action.stage = InventoryPearlStage.RESTORE
                        action.ticks = 0
                    }

                    InventoryPearlStage.RESTORE -> {
                        action.ticks = 0
                    }
                }
            }
        }

        if (silentRestoreTicks >= 0) {
            if (silentRestoreTicks > 0) {
                silentRestoreTicks--
            } else {
                SilentHotbar.resetSlot(this)
                interaction.syncSelectedSlot()
                silentRestoreTicks = -1
            }
        }

        if (visualSpoofTicks >= 0) {
            if (visualSpoofTicks > 0) {
                visualSpoofTicks--
            } else {
                resetPearlVisualSpoof()
            }
        }
    }

    private fun beginPearlVisualSpoof(hotbarSlot: Int) {
        if (!pearlVisualSpoof) {
            return
        }

        val localPlayer = mc.player ?: return
        visualSpoofHotbarSlot = hotbarSlot
        visualSpoofStack = localPlayer.inventory.getStack(hotbarSlot).copy()
        visualSpoofTicks = 6
    }

    private fun resetPearlVisualSpoof() {
        visualSpoofHotbarSlot = -1
        visualSpoofStack = ItemStack.EMPTY
        visualSpoofTicks = -1
    }

    private fun findPearlInInventory(): InventoryItemSlot? =
        Slots.Inventory.findSlot(Items.ENDER_PEARL)

    private fun findInventoryPearlTargetSlot(selectedSlot: Int): Int {
        val inventory = mc.player?.inventory ?: return selectedSlot
        return (0..8).firstOrNull { it != selectedSlot && inventory.getStack(it).isEmpty }
            ?: (0..8).firstOrNull { it != selectedSlot }
            ?: selectedSlot
    }

    private fun handleFriend() {
        val hitResult = mc.crosshairTarget as? EntityHitResult ?: return
        val target = hitResult.entity as? PlayerEntity ?: return
        val name = target.displayName?.string ?: target.nameForScoreboard
        val friendEntry = FriendManager.Friend(name, null)

        if (FriendManager.isFriend(name)) {
            FriendManager.friends.remove(friendEntry)
            chat("Removed $name from friends.")
        } else {
            FriendManager.friends.add(friendEntry)
            chat("Added $name as friend.")
        }
    }

    private fun isKeyPressed(key: InputUtil.Key): Boolean =
        key.code != GLFW.GLFW_KEY_UNKNOWN && key.isPressed

    private data class PendingInventoryPearlAction(
        val inventoryIndex: Int,
        val hotbarSlot: Int,
        val syncId: Int,
        val pearlSlotId: Int,
        var ticks: Int = 1,
        var stage: InventoryPearlStage = InventoryPearlStage.USE
    )

    private enum class InventoryPearlStage {
        SWAP,
        USE,
        RESTORE
    }

    private val InventoryItemSlot.inventoryIndex: Int
        get() = getIdForServerWithCurrentScreen()?.takeIf { it in 9..35 } ?: -1
}
