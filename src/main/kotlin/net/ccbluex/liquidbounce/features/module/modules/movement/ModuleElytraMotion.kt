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
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.PlayerTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.Chronometer
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.inventory.OffHandSlot
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

/**
 * VCore 0.8 ElytraMotion port.
 */
object ModuleElytraMotion : ClientModule("ElytraMotion", Category.MOVEMENT) {

    val attackDistance by float("AttackDistance", 3.0f, 0.1f..5.0f)
    private val autoFirework by boolean("AutoFirework", false)
    private val matrixBypass by boolean("MatrixBypass", false)

    var freeze = false
        private set

    private val fireworkTimer = Chronometer(System.currentTimeMillis())

    override fun disable() {
        freeze = false
    }

    @Suppress("unused")
    private val tickHandler = handler<PlayerTickEvent> {
        val localPlayer = mc.player ?: run {
            freeze = false
            return@handler
        }

        if (!localPlayer.isGliding) {
            freeze = false
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && fireworkTimer.hasAtLeastElapsed(FIREWORK_DELAY_MS)) {
                useFirework()
                fireworkTimer.reset()
            }
        } else if (autoFirework && target != null && getBps(localPlayer) <= LOW_SPEED_BPS &&
            fireworkTimer.hasAtLeastElapsed(FIREWORK_DELAY_MS)) {
            useFirework()
            fireworkTimer.reset()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        if (mc.player == null) {
            freeze = false
            return@handler
        }

        if (freeze) {
            event.movement = Vec3.ZERO
        }
    }

    fun check(target: LivingEntity?, elytraTarget: ModuleElytraTarget? = ModuleElytraTarget): Boolean {
        val localPlayer = mc.player ?: return false

        if (target == null || !localPlayer.isGliding) {
            return false
        }

        val canTarget = elytraTarget != null && elytraTarget.shouldTarget(target)
        return !canTarget && target.distanceTo(localPlayer) < attackDistance
    }

    private fun useFirework() {
        val localPlayer = mc.player ?: return
        if (mc.gameMode == null || localPlayer.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return
        }

        if (OffHandSlot.itemStack.item == Items.FIREWORK_ROCKET) {
            useFireworkFromHand(InteractionHand.OFF_HAND)
            return
        }

        val fireworkSlot = findFireworkSlot() ?: return
        val currentSlot = localPlayer.inventory.selectedSlot
        if (localPlayer.isUsingItem && localPlayer.usedItemHand == InteractionHand.MAIN_HAND) {
            useFireworkWithOffhandSwap(fireworkSlot.serverSlot)
            return
        }

        when (fireworkSlot) {
            is FireworkSlot.Hotbar -> useFireworkFromHotbar(fireworkSlot.hotbarSlot, currentSlot)
            is FireworkSlot.Inventory -> {
                val swapHotbarSlot = pickSwapHotbarSlot()
                interaction.clickSlot(
                    localPlayer.screenHandler.syncId,
                    fireworkSlot.serverSlot,
                    swapHotbarSlot,
                    ContainerInput.SWAP,
                    localPlayer
                )
                useFireworkFromHotbar(swapHotbarSlot, currentSlot)
                interaction.clickSlot(
                    localPlayer.screenHandler.syncId,
                    fireworkSlot.serverSlot,
                    swapHotbarSlot,
                    ContainerInput.SWAP,
                    localPlayer
                )
            }
        }
    }

    private fun findFireworkSlot(): FireworkSlot? {
        val localPlayer = mc.player ?: return null

        for (rawSlot in 36 downTo 0) {
            val stack = localPlayer.inventory.getStack(rawSlot)
            if (stack.item != Items.FIREWORK_ROCKET) {
                continue
            }

            val itemSlot = if (rawSlot < 9) rawSlot + HOTBAR_CONTAINER_OFFSET else rawSlot
            return if (itemSlot >= HOTBAR_CONTAINER_OFFSET) {
                FireworkSlot.Hotbar(itemSlot - HOTBAR_CONTAINER_OFFSET, itemSlot)
            } else {
                FireworkSlot.Inventory(itemSlot)
            }
        }

        return null
    }

    private fun useFireworkFromHotbar(fireworkSlot: Int, returnSlot: Int) {
        network.sendPacket(ServerboundSetCarriedItemPacket(fireworkSlot))
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        network.sendPacket(ServerboundSetCarriedItemPacket(returnSlot))
    }

    private fun useFireworkWithOffhandSwap(itemSlot: Int) {
        val localPlayer = mc.player ?: return
        val slotIndex = if (itemSlot <= 8) itemSlot + HOTBAR_CONTAINER_OFFSET else itemSlot
        interaction.clickSlot(
            localPlayer.screenHandler.syncId,
            slotIndex,
            OffHandSlot.hotbarSlotForServer,
            ContainerInput.SWAP,
            localPlayer
        )
        useFireworkFromHand(InteractionHand.OFF_HAND)
        interaction.clickSlot(
            localPlayer.screenHandler.syncId,
            slotIndex,
            OffHandSlot.hotbarSlotForServer,
            ContainerInput.SWAP,
            localPlayer
        )
    }

    private fun useFireworkFromHand(hand: InteractionHand) {
        val level = mc.level ?: return
        val rotation = getUseRotation()
        interaction.sendSequencedPacket(level) { sequence ->
            ServerboundUseItemPacket(hand, sequence, rotation.yaw, rotation.pitch)
        }
    }

    private fun getUseRotation() = if (ModuleKillAura.running && ModuleKillAura.targetTracker.target != null) {
        ModuleKillAura.rotationForPacketUse() ?: mc.player?.rotation ?: Rotation.ZERO
    } else {
        mc.player?.rotation ?: Rotation.ZERO
    }

    private fun pickSwapHotbarSlot(): Int {
        val selected = mc.player?.inventory?.selectedSlot ?: 0
        return selected % 8 + 1
    }

    private fun getBps(entity: LivingEntity): Double {
        val diff = entity.pos.subtract(entity.prevPos)
        return sqrt(diff.x * diff.x + diff.y * diff.y + diff.z * diff.z) * TICKS_PER_SECOND
    }

    private sealed class FireworkSlot(open val serverSlot: Int) {
        data class Hotbar(val hotbarSlot: Int, override val serverSlot: Int) : FireworkSlot(serverSlot)
        data class Inventory(override val serverSlot: Int) : FireworkSlot(serverSlot)
    }

    private const val TICKS_PER_SECOND = 20.0
    private const val LOW_SPEED_BPS = 30.0
    private const val FIREWORK_DELAY_MS = 500L
    private const val HOTBAR_CONTAINER_OFFSET = 36
}
