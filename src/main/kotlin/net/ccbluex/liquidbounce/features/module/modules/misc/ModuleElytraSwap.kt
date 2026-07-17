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
@file:Suppress("MagicNumber")

package net.ccbluex.liquidbounce.features.module.modules.misc

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.MovementInputEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketPostSendEvent
import net.ccbluex.liquidbounce.event.events.SprintEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.inventory.OffHandSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FINAL_DECISION
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import org.lwjgl.glfw.GLFW

/** V-Core ElytraSwap, kept under Wraith's existing public module identity. */
object ModuleElytraSwap : ClientModule(
    "ElytraSwap",
    Category.PLAYER,
    aliases = arrayOf("ChestSwap"),
) {

    private val switchButton by key("SwitchButton", InputConstants.UNKNOWN)
    private val fireWorkButton by key("FireWorkButton", InputConstants.UNKNOWN)
    private val delay by boolean("Delay", false)
    private val grimStop by boolean("GrimStop", false)
    private val startFireWork by boolean("StartFireWork", false)

    private var switchTimerNanos = System.nanoTime()
    private var fireworkTimerNanos = System.nanoTime()

    var swapping = false
        private set

    private var swapDelayTicks = 0
    private var pendingSwapAt = -1L
    private var pendingSwapSlot = -1
    private var pendingStartFirework = false
    private var scheduledSwap = false
    private var scheduledSwapSlot = -1
    private var scheduledStartFirework = false
    private var scheduledActionAt = -1L
    private var inputBlockedUntil = -1L
    private var trackedPlayer: Any? = null

    override fun enable() {
        resetRuntimeState()
        trackedPlayer = mc.player
    }

    override fun disable() {
        resetRuntimeState()
        trackedPlayer = null
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val currentPlayer = mc.player
        if (trackedPlayer !== currentPlayer) {
            resetRuntimeState()
            trackedPlayer = currentPlayer
        }

        handleScheduledSwap()
        if (swapDelayTicks > 0) {
            swapDelayTicks--
        }

        if (mc.screen != null || currentPlayer == null) {
            return@handler
        }

        if (shouldPollElytraSwapKeyOnTick(switchButton) && switchButton.isPressed &&
            timerEvery(SWITCH_KEY_INTERVAL_MS, switchTimerNanos) { switchTimerNanos = it }) {
            swapChest()
        }

        if (shouldPollElytraSwapKeyOnTick(fireWorkButton) && fireWorkButton.isPressed &&
            timerEvery(FIREWORK_KEY_INTERVAL_MS, fireworkTimerNanos) { fireworkTimerNanos = it } &&
            currentPlayer.isFallFlying) {
            useFireworkForPeakAssist()
        }
    }

    @Suppress("unused")
    private val outgoingPacketHandler = handler<OutgoingPacketPostSendEvent> { event ->
        val command = event.packet as? ServerboundPlayerCommandPacket ?: return@handler
        handleElytraSwapStartFireworkPostSend(
            command.action == ServerboundPlayerCommandPacket.Action.START_FALL_FLYING,
            startFireWork,
        ) {
            useFireworkForPeakAssist()
        }
    }

    @Suppress("unused")
    private val mouseButtonHandler = handler<MouseButtonEvent> { event ->
        val screenOpen = event.screen != null || mc.screen != null
        val currentPlayer = mc.player ?: return@handler

        when {
            matchesElytraSwapMousePress(switchButton, event.button, event.action, screenOpen) -> {
                if (timerEvery(SWITCH_KEY_INTERVAL_MS, switchTimerNanos) { switchTimerNanos = it }) {
                    swapChest()
                }
            }
            matchesElytraSwapMousePress(fireWorkButton, event.button, event.action, screenOpen) -> {
                if (timerEvery(FIREWORK_KEY_INTERVAL_MS, fireworkTimerNanos) { fireworkTimerNanos = it } &&
                    currentPlayer.isFallFlying) {
                    useFireworkForPeakAssist()
                }
            }
        }
    }

    @Suppress("unused")
    private val movementInputHandler = handler<MovementInputEvent>(priority = FINAL_DECISION) { event ->
        if (!isInputSuppressed()) {
            return@handler
        }

        event.directionalInput = DirectionalInput.NONE
        event.jump = false
        event.sneak = false
    }

    @Suppress("unused")
    private val sprintInputHandler = handler<SprintEvent>(priority = FINAL_DECISION) { event ->
        if (event.source == SprintEvent.Source.INPUT && isInputSuppressed()) {
            event.sprint = false
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetRuntimeState()
        trackedPlayer = mc.player
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        resetRuntimeState()
        trackedPlayer = null
    }

    /**
     * Authoritative V-Core ElytraSwap Firework profile. PeakAssist may call this while the module
     * itself is disabled because V-Core uses ElytraSwap as a shared dependency.
     */
    fun useFireworkForPeakAssist(): Boolean {
        val player = mc.player ?: return false
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return false
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            return useFireworkFromHand(InteractionHand.OFF_HAND)
        }

        val firework = findInventoryFirst(Items.FIREWORK_ROCKET)
        if (firework == null) {
            chat("You've got no fireworks!", this)
            return false
        }

        val currentSlot = player.inventory.selectedSlot
        if (currentSlot !in 0..8) {
            return false
        }

        if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
            return useFireworkWithOffhandSwap(firework.serverSlot)
        }

        return when (firework) {
            is FireworkLocation.Hotbar -> useFireworkFromHotbar(firework.hotbarSlot, currentSlot)
            is FireworkLocation.Inventory -> useFireworkFromMainInventory(
                firework.serverSlot,
                currentSlot % 8 + 1,
                currentSlot,
            )
        }
    }

    /** Source chestplate priority, shared with EMace Armor Swap. */
    fun getChestPlateSlot(): Int? {
        for (item in CHESTPLATE_PRIORITY) {
            findInventoryFirst(item)?.let { return it.serverSlot }
        }
        return null
    }

    private fun useFireworkFromHotbar(fireworkSlot: Int, returnSlot: Int): Boolean {
        if (fireworkSlot !in 0..8 || returnSlot !in 0..8) {
            return false
        }

        network.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        val used = useFireworkFromHand(InteractionHand.MAIN_HAND)
        network.sendPacket(ServerboundSetCarriedItemPacket(returnSlot))
        return used
    }

    private fun useFireworkWithOffhandSwap(sourceSlot: Int): Boolean {
        var used = false
        for (action in elytraSwapOffhandFireworkActions()) {
            when (action) {
                ElytraSwapFireworkAction.SWAP -> if (!clickSwap(sourceSlot, OffHandSlot.hotbarSlotForServer)) {
                    return false
                }
                ElytraSwapFireworkAction.CLOSE -> sendCloseContainer()
                ElytraSwapFireworkAction.USE -> used = useFireworkFromHand(InteractionHand.OFF_HAND)
                ElytraSwapFireworkAction.HELD_TEMPORARY,
                ElytraSwapFireworkAction.HELD_ORIGINAL -> error("Invalid offhand Firework action")
            }
        }
        return used
    }

    private fun useFireworkFromMainInventory(sourceSlot: Int, temporarySlot: Int, originalSlot: Int): Boolean {
        var used = false
        for (action in elytraSwapMainInventoryFireworkActions()) {
            when (action) {
                ElytraSwapFireworkAction.SWAP -> if (!clickSwap(sourceSlot, temporarySlot)) return false
                ElytraSwapFireworkAction.CLOSE -> sendCloseContainer()
                ElytraSwapFireworkAction.HELD_TEMPORARY ->
                    network.sendPacket(ServerboundSetCarriedItemPacket(temporarySlot))
                ElytraSwapFireworkAction.USE -> used = useFireworkFromHand(InteractionHand.MAIN_HAND)
                ElytraSwapFireworkAction.HELD_ORIGINAL ->
                    network.sendPacket(ServerboundSetCarriedItemPacket(originalSlot))
            }
        }
        return used
    }

    private fun useFireworkFromHand(hand: InteractionHand): Boolean {
        val level = mc.level ?: return false
        val rotation = ModuleKillAura.activeServerFacingRotationForCurrentTarget() ?: mc.player?.rotation ?: return false
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
        return true
    }

    private fun swapChest() {
        if (swapDelayTicks > 0 || mc.player == null) {
            return
        }

        val request = getSwapRequest() ?: return
        if (delay) {
            if (pendingSwapSlot != request.slot || pendingStartFirework != request.startFirework) {
                pendingSwapSlot = request.slot
                pendingStartFirework = request.startFirework
                pendingSwapAt = System.currentTimeMillis() + SWAP_DELAY_MS
                return
            }

            if (System.currentTimeMillis() < pendingSwapAt) {
                return
            }
        }

        if (grimStop) {
            scheduledSwap = true
            scheduledSwapSlot = request.slot
            scheduledStartFirework = request.startFirework
            scheduledActionAt = System.currentTimeMillis() + GRIM_STOP_DELAY_MS
        } else {
            executeSwap(request)
        }
    }

    private fun handleScheduledSwap() {
        if (!scheduledSwap || System.currentTimeMillis() < scheduledActionAt) {
            return
        }

        if (mc.player == null) {
            resetRuntimeState()
            return
        }

        executeSwap(SwapRequest(scheduledSwapSlot, scheduledStartFirework))
        inputBlockedUntil = System.currentTimeMillis() + POST_GRIM_INPUT_BLOCK_MS
        scheduledSwap = false
        scheduledSwapSlot = -1
        scheduledStartFirework = false
        scheduledActionAt = -1L
    }

    private fun executeSwap(request: SwapRequest) {
        val player = mc.player ?: return
        val chestSlot = Slots.Armor[2].getIdForServer(null) ?: return
        if (request.slot !in player.screenHandler.slots.indices ||
            chestSlot !in player.screenHandler.slots.indices) {
            return
        }

        swapping = true
        try {
            clickPickup(request.slot)
            clickPickup(chestSlot)
            clickPickup(request.slot)
            if (request.startFirework) {
                network.sendPacket(
                    ServerboundPlayerCommandPacket(
                        player,
                        ServerboundPlayerCommandPacket.Action.START_FALL_FLYING,
                    )
                )
            }
        } finally {
            swapping = false
        }

        resetPendingDelay()
        swapDelayTicks = 2 + player.ping / 25
    }

    private fun getSwapRequest(): SwapRequest? {
        val player = mc.player ?: return null
        return if (player.getItemBySlot(EquipmentSlot.CHEST).item == Items.ELYTRA) {
            val slot = getChestPlateSlot()
            if (slot == null) {
                chat("You don't have a chestplate!", this)
                null
            } else {
                SwapRequest(slot, false)
            }
        } else {
            val elytra = findInventoryFirst(Items.ELYTRA)
            if (elytra == null) {
                chat("You don't have an elytra!", this)
                null
            } else {
                SwapRequest(elytra.serverSlot, startFireWork && player.fallDistance > 0.0f)
            }
        }
    }

    private fun findInventoryFirst(item: Item): FireworkLocation? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == item) {
                return FireworkLocation.Inventory(slot)
            }
        }
        for (slot in 8 downTo 0) {
            if (player.inventory.getStack(slot).item == item) {
                return FireworkLocation.Hotbar(slot, 36 + slot)
            }
        }
        return null
    }

    private fun clickPickup(slot: Int) {
        val player = mc.player ?: return
        interaction.clickSlot(player.screenHandler.syncId, slot, 0, ContainerInput.PICKUP, player)
    }

    private fun clickSwap(slot: Int, button: Int): Boolean {
        val player = mc.player ?: return false
        if (slot !in player.screenHandler.slots.indices ||
            button !in 0..8 && button != OffHandSlot.hotbarSlotForServer) {
            return false
        }

        interaction.clickSlot(player.screenHandler.syncId, slot, button, ContainerInput.SWAP, player)
        return true
    }

    private fun sendCloseContainer() {
        val player = mc.player ?: return
        network.sendPacket(ServerboundContainerClosePacket(player.screenHandler.syncId))
    }

    private fun isInputSuppressed() = scheduledSwap || System.currentTimeMillis() < inputBlockedUntil

    private fun resetPendingDelay() {
        pendingSwapAt = -1L
        pendingSwapSlot = -1
        pendingStartFirework = false
    }

    private fun resetRuntimeState() {
        swapping = false
        swapDelayTicks = 0
        resetPendingDelay()
        scheduledSwap = false
        scheduledSwapSlot = -1
        scheduledStartFirework = false
        scheduledActionAt = -1L
        inputBlockedUntil = -1L
    }

    private fun timerEvery(intervalMs: Long, startedAt: Long, update: (Long) -> Unit): Boolean {
        val now = System.nanoTime()
        if (now - startedAt < intervalMs * NANOS_PER_MILLISECOND) {
            return false
        }
        update(now)
        return true
    }

    private data class SwapRequest(val slot: Int, val startFirework: Boolean)

    private sealed class FireworkLocation(open val serverSlot: Int) {
        data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : FireworkLocation(serverSlot)
        data class Inventory(override val serverSlot: Int) : FireworkLocation(serverSlot)
    }

    private const val SWITCH_KEY_INTERVAL_MS = 500L
    private const val FIREWORK_KEY_INTERVAL_MS = 200L
    private const val SWAP_DELAY_MS = 200L
    private const val GRIM_STOP_DELAY_MS = 60L
    private const val POST_GRIM_INPUT_BLOCK_MS = 10L
    private const val NANOS_PER_MILLISECOND = 1_000_000L

    private val CHESTPLATE_PRIORITY = arrayOf(
        Items.NETHERITE_CHESTPLATE,
        Items.DIAMOND_CHESTPLATE,
        Items.CHAINMAIL_CHESTPLATE,
        Items.IRON_CHESTPLATE,
        Items.GOLDEN_CHESTPLATE,
        Items.LEATHER_CHESTPLATE,
    )
}

internal enum class ElytraSwapFireworkAction {
    SWAP,
    CLOSE,
    HELD_TEMPORARY,
    USE,
    HELD_ORIGINAL,
}

internal fun elytraSwapMainInventoryFireworkActions() = listOf(
    ElytraSwapFireworkAction.SWAP,
    ElytraSwapFireworkAction.CLOSE,
    ElytraSwapFireworkAction.HELD_TEMPORARY,
    ElytraSwapFireworkAction.USE,
    ElytraSwapFireworkAction.HELD_ORIGINAL,
    ElytraSwapFireworkAction.SWAP,
    ElytraSwapFireworkAction.CLOSE,
)

internal fun elytraSwapOffhandFireworkActions() = listOf(
    ElytraSwapFireworkAction.SWAP,
    ElytraSwapFireworkAction.CLOSE,
    ElytraSwapFireworkAction.USE,
    ElytraSwapFireworkAction.SWAP,
    ElytraSwapFireworkAction.CLOSE,
)

internal fun shouldPollElytraSwapKeyOnTick(key: InputConstants.Key): Boolean =
    key != InputConstants.UNKNOWN && key.type != InputConstants.Type.MOUSE

internal fun matchesElytraSwapMousePress(
    configuredKey: InputConstants.Key,
    button: Int,
    action: Int,
    screenOpen: Boolean,
): Boolean = !screenOpen && action == GLFW.GLFW_PRESS &&
    configuredKey.type == InputConstants.Type.MOUSE && configuredKey.value == button

internal inline fun handleElytraSwapStartFireworkPostSend(
    isStartFallFlying: Boolean,
    startFireworkEnabled: Boolean,
    useFirework: () -> Unit,
) {
    if (isStartFallFlying && startFireworkEnabled) {
        useFirework()
    }
}
