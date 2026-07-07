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
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.client.Chronometer
import net.ccbluex.liquidbounce.utils.client.SilentHotbar
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.inventory.OffHandSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
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

    private val fireworkTimer = Chronometer()

    override fun disable() {
        freeze = false
        fireworkTimer.reset(0L)
    }

    @Suppress("unused")
    private val tickHandler = tickHandler {
        val localPlayer = mc.player ?: run {
            freeze = false
            return@tickHandler
        }

        if (shouldPauseForPeakAssist()) {
            freeze = false
            return@tickHandler
        }

        if (!localPlayer.isGliding) {
            freeze = false
            return@tickHandler
        }

        val target = ModuleKillAura.targetTracker.target
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && fireworkTimer.hasElapsed(FIREWORK_DELAY_MS)) {
                useFirework()
                fireworkTimer.reset()
            }
        } else if (autoFirework && target != null && getBps(localPlayer) <= LOW_SPEED_BPS &&
            fireworkTimer.hasElapsed(FIREWORK_DELAY_MS)) {
            useFirework()
            fireworkTimer.reset()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        val localPlayer = mc.player ?: run {
            freeze = false
            return@handler
        }

        if (shouldPauseForPeakAssist()) {
            freeze = false
        } else if (freeze) {
            event.movement = Vec3.ZERO
            localPlayer.deltaMovement = Vec3.ZERO
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

    private fun shouldPauseForPeakAssist(): Boolean {
        // #Edit By Codex 2026-07-01: VCore's PeakAssist helper is not present in this branch.
        // Keep the pause seam safe without adding a fake half-ported module.
        return false
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
        if (localPlayer.isUsingItem && localPlayer.usedItemHand == InteractionHand.MAIN_HAND) {
            useFireworkWithOffhandSwap(fireworkSlot.serverSlot)
            return
        }

        when (fireworkSlot) {
            is FireworkSlot.Hotbar -> useFireworkFromHotbar(fireworkSlot.hotbarSlot, currentServerSlot())
            is FireworkSlot.Inventory -> {
                val swapHotbarSlot = pickSwapHotbarSlot()
                interaction.clickSlot(
                    localPlayer.screenHandler.syncId,
                    fireworkSlot.serverSlot,
                    swapHotbarSlot,
                    ContainerInput.SWAP,
                    localPlayer
                )
                useFireworkFromHotbar(swapHotbarSlot, currentServerSlot())
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
        Slots.Hotbar.findClosestSlot(Items.FIREWORK_ROCKET)?.let { slot ->
            return FireworkSlot.Hotbar(slot.hotbarSlot, slot.getIdForServer(null) ?: return null)
        }

        Slots.Inventory.findSlot(Items.FIREWORK_ROCKET)?.let { slot ->
            return FireworkSlot.Inventory(slot.getIdForServer(null))
        }

        return null
    }

    private fun useFireworkFromHotbar(fireworkSlot: Int, returnSlot: Int) {
        switchToSilent(fireworkSlot)
        useFireworkFromHand(InteractionHand.MAIN_HAND)
        switchToSilent(returnSlot)
    }

    private fun useFireworkWithOffhandSwap(slotId: Int) {
        val localPlayer = mc.player ?: return
        interaction.clickSlot(
            localPlayer.screenHandler.syncId,
            slotId,
            OffHandSlot.hotbarSlotForServer,
            ContainerInput.SWAP,
            localPlayer
        )
        useFireworkFromHand(InteractionHand.OFF_HAND)
        interaction.clickSlot(
            localPlayer.screenHandler.syncId,
            slotId,
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
        RotationManager.currentRotation ?: RotationManager.serverRotation
    } else {
        mc.player?.rotation ?: RotationManager.serverRotation
    }

    private fun switchToSilent(slot: Int) {
        val localPlayer = mc.player ?: return
        if (slot in 0..8 && localPlayer.inventory.selectedSlot != slot && SilentHotbar.serversideSlot != slot) {
            network.sendPacket(ServerboundSetCarriedItemPacket(slot))
        } else if (slot in 0..8 && SilentHotbar.serversideSlot != slot) {
            network.sendPacket(ServerboundSetCarriedItemPacket(slot))
        }
    }

    private fun currentServerSlot(): Int = SilentHotbar.serversideSlot.coerceIn(0, 8)

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
}
