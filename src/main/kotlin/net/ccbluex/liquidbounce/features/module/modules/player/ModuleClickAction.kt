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

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.EventState
import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.events.PlayerNetworkMovementTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.interactItem
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.armor
import net.ccbluex.liquidbounce.utils.entity.clickSlot
import net.ccbluex.liquidbounce.utils.entity.code
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.damage
import net.ccbluex.liquidbounce.utils.entity.interactItem
import net.ccbluex.liquidbounce.utils.entity.isDamageable
import net.ccbluex.liquidbounce.utils.entity.maxDamage
import net.ccbluex.liquidbounce.utils.entity.moving
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.screenHandler
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.syncId
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.inventory.InventoryItemSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.world.InteractionHand
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.phys.EntityHitResult
import org.lwjgl.glfw.GLFW

/**
 * VCore 0.8 ClickAction port.
 *
 * #Edit By Codex: preserves VCore's one-shot Pearl/Friend keys and hold-to-XP behavior while using
 * the fork's slot-click, interaction, friend, and movement-input APIs.
 */
@Suppress("MagicNumber")
object ModuleClickAction : ClientModule("ClickAction", Category.PLAYER) {

    private val pearl by boolean("Pearl", false)
    private val xp by boolean("XP", false)
    private val friend by boolean("Friend", false)

    private val mode by enumChoice("Mode", Mode.LEGIT)

    private val pearlKey by key("PearlKey", InputConstants.UNKNOWN)
    private val xpKey by key("XPKey", InputConstants.UNKNOWN)
    private val friendKey by key("FriendKey", InputConstants.UNKNOWN)

    private object XpAntiWaste : ToggleableConfigurable(this, "AntiWaste", false) {
        val stopOn by int("StopOn", 95, 0..100, "%")
    }

    private object XpRotation : ToggleableConfigurable(this, "Rotation", false) {
        val pitch by float("Pitch", 70.0f, 45.0f..90.0f)
    }

    private var pearlPressed = false
    private var friendPressed = false
    private var xpPressed = false
    private var pearlRequested = false
    private var friendRequested = false

    private var scheduledPearlSwap = false
    private var scheduledInventorySlot = -1
    private var scheduledHotbarSlot = -1
    private var scheduledTicks = 0

    private var restoreSlot = -1
    private var restoreHotbar = -1
    private var restorePending = false
    private var lastWarn = 0L

    private var throwingXp = false
    private var xpLastSlot = -1
    private var lastXpUseTime = 0L

    init {
        tree(XpAntiWaste)
        tree(XpRotation)
    }

    @Suppress("unused")
    private val movementInputHandler = handler<MovementInputEvent>(priority = FINAL_DECISION) { event ->
        if (!scheduledPearlSwap) {
            return@handler
        }

        event.sneak = false
        event.jump = false
        event.directionalInput = DirectionalInput.NONE
    }

    @Suppress("unused")
    private val movementSyncHandler = handler<PlayerNetworkMovementTickEvent> { event ->
        if (event.state == EventState.PRE) {
            val localPlayer = mc.player ?: return@handler
            if (shouldSpoofFeetPitch(localPlayer)) {
                localPlayer.pitch = XpRotation.pitch
            }
        }
    }

    @Suppress("unused")
    private val tickHandler = tickHandler {
        if (mc.player == null || mc.gameMode == null) {
            resetState()
            return@tickHandler
        }

        pollKeys()

        if (friendRequested) {
            handleFriendAction()
            friendRequested = false
        }

        if (pearlRequested) {
            handlePearlAction()
            pearlRequested = false
        }

        handleScheduledPearlSwap()
        handleLegitRestore()
        handleXpAutomation()
    }

    override fun enable() {
        resetState()
    }

    override fun disable() {
        resetState()
        stopXpUse()
    }

    private fun pollKeys() {
        val guiOpen = mc.screen != null

        if (pearl) {
            val pressed = isKeyPressed(pearlKey)
            if (!guiOpen && pressed && !pearlPressed) {
                pearlRequested = true
            }
            pearlPressed = pressed
        } else {
            pearlPressed = false
        }

        if (friend) {
            val pressed = isKeyPressed(friendKey)
            if (!guiOpen && pressed && !friendPressed) {
                friendRequested = true
            }
            friendPressed = pressed
        } else {
            friendPressed = false
        }

        xpPressed = xp && isKeyPressed(xpKey)
    }

    private fun handleFriendAction() {
        val hitResult = mc.crosshairTarget as? EntityHitResult
        val target = hitResult?.entity as? net.minecraft.world.entity.player.Player

        if (target == null || target === mc.player) {
            warn(if (hitResult == null) "Aim at a player first." else "That isn't a player.")
            return
        }

        val name = target.displayName?.string ?: target.scoreboardName
        val friendEntry = FriendManager.Friend(name, null)

        if (FriendManager.isFriend(name)) {
            FriendManager.friends.remove(friendEntry)
            chat("Removed friend: $name")
        } else {
            FriendManager.friends.add(friendEntry)
            chat("Added friend: $name")
        }
    }

    private fun handlePearlAction() {
        val localPlayer = mc.player ?: return
        if (localPlayer.cooldowns.isOnCooldown(Items.ENDER_PEARL.defaultInstance)) {
            return
        }

        val hotbarPearl = Slots.Hotbar.findClosestSlot(Items.ENDER_PEARL)
        val inventoryPearl = if (hotbarPearl == null) findPearlInInventory() else null

        if (hotbarPearl == null && inventoryPearl == null) {
            warn("No pearls found.")
            return
        }

        if (mode == Mode.RAGE) {
            if (hotbarPearl != null) {
                throwPearlFromHotbar(hotbarPearl.hotbarSlot, rageMode = true)
            } else if (inventoryPearl != null) {
                throwPearlWithSwap(inventoryPearl, localPlayer.inventory.selectedSlot, scheduleRestore = false)
            }
            return
        }

        if (hotbarPearl != null) {
            throwPearlFromHotbar(hotbarPearl.hotbarSlot, rageMode = false)
            return
        }

        if (restorePending) {
            warn("Waiting for previous swap to finish.")
            return
        }

        val targetHotbar = pickSwapHotbarSlot()
        if (localPlayer.moving) {
            schedulePearlSwap(inventoryPearl, targetHotbar)
        } else {
            throwPearlWithSwap(inventoryPearl, targetHotbar, scheduleRestore = true)
        }
    }

    private fun schedulePearlSwap(inventorySlot: InventoryItemSlot?, hotbarSlot: Int) {
        val slotId = inventorySlot?.getIdForServerWithCurrentScreen() ?: return
        scheduledPearlSwap = true
        scheduledInventorySlot = slotId
        scheduledHotbarSlot = hotbarSlot
        scheduledTicks = 1
    }

    private fun handleScheduledPearlSwap() {
        if (!scheduledPearlSwap) {
            return
        }

        if (scheduledTicks > 0) {
            scheduledTicks--
            return
        }

        val localPlayer = mc.player ?: return
        clickSwap(scheduledInventorySlot, scheduledHotbarSlot, localPlayer)
        throwPearlFromHotbar(scheduledHotbarSlot, rageMode = false)
        clickSwap(scheduledInventorySlot, scheduledHotbarSlot, localPlayer)

        scheduledPearlSwap = false
        scheduledInventorySlot = -1
        scheduledHotbarSlot = -1
        scheduledTicks = 0
    }

    private fun throwPearlWithSwap(inventorySlot: InventoryItemSlot?, hotbarSlot: Int, scheduleRestore: Boolean) {
        val localPlayer = mc.player ?: return
        val slotId = inventorySlot?.getIdForServerWithCurrentScreen() ?: return

        clickSwap(slotId, hotbarSlot, localPlayer)
        throwPearlFromHotbar(hotbarSlot, rageMode = mode == Mode.RAGE)

        if (mode == Mode.RAGE && !scheduleRestore) {
            clickSwap(slotId, hotbarSlot, localPlayer)
        } else {
            restoreSlot = slotId
            restoreHotbar = hotbarSlot
            restorePending = true
        }
    }

    private fun throwPearlFromHotbar(hotbarSlot: Int, rageMode: Boolean) {
        val localPlayer = mc.player ?: return

        if (rageMode) {
            val previousSlot = localPlayer.inventory.selectedSlot
            localPlayer.inventory.selectedSlot = hotbarSlot
            interaction.ensureHasSentCarriedItem()
            interaction.interactItem(localPlayer, InteractionHand.MAIN_HAND)
            localPlayer.swingHand(InteractionHand.MAIN_HAND)
            localPlayer.inventory.selectedSlot = previousSlot
            interaction.ensureHasSentCarriedItem()
            return
        }

        val previousSlot = localPlayer.inventory.selectedSlot
        localPlayer.inventory.selectedSlot = hotbarSlot
        interaction.ensureHasSentCarriedItem()
        interaction.interactItem(localPlayer, InteractionHand.MAIN_HAND)
        localPlayer.swingHand(InteractionHand.MAIN_HAND)
        localPlayer.inventory.selectedSlot = previousSlot
        interaction.ensureHasSentCarriedItem()
    }

    private fun handleLegitRestore() {
        if (mode != Mode.LEGIT || !restorePending) {
            return
        }

        val localPlayer = mc.player ?: return
        if (localPlayer.moving) {
            return
        }

        clickSwap(restoreSlot, restoreHotbar, localPlayer)
        restoreSlot = -1
        restoreHotbar = -1
        restorePending = false
    }

    private fun handleXpAutomation() {
        val localPlayer = mc.player
        if (!xp || !xpPressed || mc.screen != null || localPlayer == null) {
            stopXpUse()
            return
        }

        if (XpAntiWaste.running && !needXpRepair(localPlayer)) {
            stopXpUse()
            return
        }

        if (!hasAvailableXpBottle(localPlayer)) {
            stopXpUse()
            return
        }

        if (!throwingXp) {
            xpLastSlot = localPlayer.inventory.selectedSlot
            throwingXp = true
            lastXpUseTime = 0L
        }

        val now = System.currentTimeMillis()
        if (now - lastXpUseTime < XP_DELAY_MS) {
            return
        }

        val throwYaw = localPlayer.yaw
        val throwPitch = getXpThrowPitch(localPlayer)

        if (localPlayer.offhandItem.item == Items.EXPERIENCE_BOTTLE) {
            interaction.interactItem(localPlayer, InteractionHand.OFF_HAND, throwYaw, throwPitch)
            localPlayer.swingHand(InteractionHand.OFF_HAND)
        } else {
            val xpSlot = Slots.Hotbar.findClosestSlot(Items.EXPERIENCE_BOTTLE)?.hotbarSlot
            if (xpSlot == null) {
                stopXpUse()
                return
            }

            if (localPlayer.inventory.selectedSlot != xpSlot) {
                localPlayer.inventory.selectedSlot = xpSlot
                interaction.ensureHasSentCarriedItem()
            }

            interaction.interactItem(localPlayer, InteractionHand.MAIN_HAND, throwYaw, throwPitch)
            localPlayer.swingHand(InteractionHand.MAIN_HAND)
        }

        lastXpUseTime = now
    }

    private fun needXpRepair(localPlayer: net.minecraft.world.entity.player.Player): Boolean {
        return localPlayer.inventory.armor.any { it.needsRepairBelow(XpAntiWaste.stopOn) }
    }

    private fun stopXpUse() {
        val localPlayer = mc.player
        if (localPlayer != null && throwingXp && xpLastSlot in 0..8 && xpLastSlot != localPlayer.inventory.selectedSlot) {
            localPlayer.inventory.selectedSlot = xpLastSlot
            interaction.ensureHasSentCarriedItem()
        }

        throwingXp = false
        xpLastSlot = -1
        lastXpUseTime = 0L
    }

    private fun shouldSpoofFeetPitch(localPlayer: net.minecraft.world.entity.player.Player): Boolean {
        return xp &&
            XpRotation.running &&
            xpPressed &&
            mc.screen == null &&
            (!XpAntiWaste.running || needXpRepair(localPlayer)) &&
            hasAvailableXpBottle(localPlayer)
    }

    private fun getXpThrowPitch(localPlayer: net.minecraft.world.entity.player.Player): Float =
        if (shouldSpoofFeetPitch(localPlayer)) XpRotation.pitch else localPlayer.pitch

    private fun hasAvailableXpBottle(localPlayer: net.minecraft.world.entity.player.Player): Boolean {
        return localPlayer.offhandItem.item == Items.EXPERIENCE_BOTTLE ||
            Slots.Hotbar.findClosestSlot(Items.EXPERIENCE_BOTTLE) != null
    }

    private fun ItemStack.needsRepairBelow(limit: Int): Boolean {
        if (isEmpty || !isDamageable || maxDamage <= 0) {
            return false
        }

        val remaining = (maxDamage - damage).toFloat() / maxDamage.toFloat() * 100.0f
        return remaining < limit
    }

    private fun pickSwapHotbarSlot(): Int {
        val selected = mc.player?.inventory?.selectedSlot ?: 0
        return (selected + 1) % 9
    }

    private fun clickSwap(slotId: Int, hotbarSlot: Int, localPlayer: net.minecraft.world.entity.player.Player) {
        interaction.clickSlot(localPlayer.screenHandler.syncId, slotId, hotbarSlot, ContainerInput.SWAP, localPlayer)
    }

    private fun findPearlInInventory(): InventoryItemSlot? =
        Slots.Inventory.findSlot(Items.ENDER_PEARL)

    private fun warn(message: String) {
        val now = System.currentTimeMillis()
        if (now - lastWarn > WARN_DELAY_MS) {
            chat(message)
            lastWarn = now
        }
    }

    private fun resetState() {
        pearlPressed = pearl && isKeyPressed(pearlKey)
        friendPressed = friend && isKeyPressed(friendKey)
        xpPressed = xp && isKeyPressed(xpKey)
        pearlRequested = false
        friendRequested = false
        scheduledPearlSwap = false
        scheduledInventorySlot = -1
        scheduledHotbarSlot = -1
        scheduledTicks = 0
        restoreSlot = -1
        restoreHotbar = -1
        restorePending = false
    }

    private fun isKeyPressed(key: InputConstants.Key): Boolean =
        key.code != GLFW.GLFW_KEY_UNKNOWN && key.isPressed

    private enum class Mode(override val choiceName: String) : NamedChoice {
        LEGIT("Legit"),
        RAGE("Rage")
    }

    private const val XP_DELAY_MS = 20L
    private const val WARN_DELAY_MS = 750L
}
