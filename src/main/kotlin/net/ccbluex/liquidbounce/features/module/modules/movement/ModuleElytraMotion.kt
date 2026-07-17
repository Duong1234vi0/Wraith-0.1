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
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

/** Helps stabilize elytra targeting movement. */
object ModuleElytraMotion : ClientModule("ElytraMotion", Category.MOVEMENT) {

    private val attackDistance by float("AttackDistance", 3.0f, 0.1f..5.0f)
    private val autoFirework by boolean("AutoFirework", false)
    private val matrixBypass by boolean("MatrixBypass", false)

    var freeze = false
        private set

    private var fireworkTimerNanos = System.nanoTime()

    override fun disable() {
        freeze = false
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: run {
            freeze = false
            return@handler
        }

        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
            return@handler
        }

        if (!player.isFallFlying) {
            freeze = false
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && hasFireworkIntervalElapsed()) {
                useFirework()
                fireworkTimerNanos = System.nanoTime()
            }
        } else if (autoFirework && target != null && getBps(player) <= 30.0 &&
            hasFireworkIntervalElapsed()) {
            useFirework()
            fireworkTimerNanos = System.nanoTime()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
        } else if (freeze) {
            // The live mixin replaces LocalPlayer.move's movement argument with this value.
            event.movement = Vec3.ZERO
        }
    }

    fun check(target: LivingEntity?, elytraTarget: ModuleElytraTarget): Boolean {
        val player = mc.player ?: return false
        return target != null && player.isFallFlying && !elytraTarget.shouldTarget(target) &&
            target.distanceTo(player) < attackDistance
    }

    private fun useFirework() {
        val player = mc.player ?: return
        if (mc.gameMode == null || player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return
        }

        if (player.offhandItem.item == Items.FIREWORK_ROCKET) {
            useFireworkFromHand(InteractionHand.OFF_HAND)
            return
        }

        val firework = findFireworkInventoryFirst() ?: return
        val currentSlot = player.inventory.selectedSlot
        if (currentSlot !in 0..8) {
            return
        }

        if (player.isUsingItem && player.usedItemHand == InteractionHand.MAIN_HAND) {
            useFireworkWithOffhandSwap(firework.serverSlot)
            return
        }

        when (firework) {
            is FireworkSlot.Hotbar -> useFireworkFromHotbar(firework.hotbarSlot, currentSlot)
            is FireworkSlot.Inventory -> {
                val temporarySlot = currentSlot % 8 + 1
                if (!clickSwap(firework.serverSlot, temporarySlot)) {
                    return
                }
                useFireworkFromHotbar(temporarySlot, currentSlot)
                clickSwap(firework.serverSlot, temporarySlot)
            }
        }
    }

    private fun useFireworkFromHotbar(fireworkSlot: Int, returnSlot: Int) {
        if (fireworkSlot !in 0..8 || returnSlot !in 0..8) {
            return
        }

        network.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        network.sendPacket(ServerboundSetCarriedItemPacket(returnSlot))
    }

    private fun useFireworkWithOffhandSwap(sourceSlot: Int) {
        if (!clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)) {
            return
        }
        useFireworkFromHand(InteractionHand.OFF_HAND)
        clickSwap(sourceSlot, OFFHAND_SWAP_BUTTON)
    }

    private fun useFireworkFromHand(hand: InteractionHand) {
        val level = mc.level ?: return
        val rotation = useRotation()
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
    }

    private fun useRotation(): Rotation {
        return ModuleKillAura.activeServerFacingRotationForCurrentTarget()
            ?: mc.player?.rotation
            ?: Rotation.ZERO
    }

    private fun findFireworkInventoryFirst(): FireworkSlot? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Inventory(slot)
            }
        }
        for (slot in 8 downTo 0) {
            if (player.inventory.getStack(slot).item == Items.FIREWORK_ROCKET) {
                return FireworkSlot.Hotbar(slot, HOTBAR_CONTAINER_OFFSET + slot)
            }
        }
        return null
    }

    private fun clickSwap(slot: Int, button: Int): Boolean {
        val player = mc.player ?: return false
        if (slot !in player.screenHandler.slots.indices ||
            button !in 0..8 && button != OFFHAND_SWAP_BUTTON) {
            return false
        }

        interaction.clickSlot(player.screenHandler.syncId, slot, button, ContainerInput.SWAP, player)
        return true
    }

    private fun getBps(entity: LivingEntity): Double {
        val dx = entity.x - entity.xo
        val dy = entity.y - entity.yo
        val dz = entity.z - entity.zo
        return sqrt(dx * dx + dy * dy + dz * dz) * 20.0
    }

    private sealed class FireworkSlot(open val serverSlot: Int) {
        data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : FireworkSlot(serverSlot)
        data class Inventory(override val serverSlot: Int) : FireworkSlot(serverSlot)
    }

    private const val FIREWORK_INTERVAL_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
    private const val HOTBAR_CONTAINER_OFFSET = 36
    private const val OFFHAND_SWAP_BUTTON = 40

    private fun hasFireworkIntervalElapsed() =
        System.nanoTime() - fireworkTimerNanos >= FIREWORK_INTERVAL_MS * NANOS_PER_MILLISECOND
}
