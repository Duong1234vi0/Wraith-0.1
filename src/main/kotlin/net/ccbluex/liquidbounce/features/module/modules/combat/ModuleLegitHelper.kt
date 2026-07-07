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
package net.ccbluex.liquidbounce.features.module.modules.combat
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection
import net.ccbluex.liquidbounce.utils.client.interactItem
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.minecraft.world.level.block.Blocks
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.arrow.Arrow
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.Items
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.network.protocol.Packet
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.InteractionHand
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.AABB
import net.minecraft.core.Direction
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.ClipContext
import org.lwjgl.glfw.GLFW
import kotlin.math.sqrt

/**
 * ThunderHack LegitHelper port.
 *
 * Keeps the original helper bundle in one combat module: anchors, crystals,
 * bow minecarts, shield breaker, wind-charge jump and charged crossbow use.
 */
object ModuleLegitHelper : ClientModule("LegitHelper", Category.COMBAT) {

    private val minecarts by boolean("Minecarts", true)
    private val maxDistance by float("MaxDistance", 4.0f, 2.0f..6.0f)
    private val refill by boolean("Refill", true)
    private val refillSlot by int("RefillSlot", 9, 1..9)

    private val anchors by boolean("Anchors", true)
    private val anchorDelay by int("AnchorDelay", 50, 5..250, "ms")
    private val anchorBind by key("AnchorBind", GLFW.GLFW_KEY_Y)

    private val crystals by boolean("Crystals", true)
    private val crystalBind by key("CrystalBind", GLFW.GLFW_KEY_U)
    private val placeDelay by int("PlaceDelay", 2, 0..20, "ticks")
    private val breakDelay by int("BreakDelay", 2, 0..20, "ticks")
    private val crystalOptimizer by boolean("CrystalOptimizer", false)
    private val obSwitch by boolean("ObSwitch", true)
    private val crySwitch by boolean("CrySwitch", true)
    private val crystalSwapBack by boolean("CrystalSwapBack", true)

    private val shieldBreaker by boolean("ShieldBreaker", false)
    private val breakerDelay by int("BreakerDelay", 50, 5..250, "ms")
    private val shieldSwapBack by boolean("ShieldSwapBack", true)

    private val windBoostJump by boolean("WindBoostJump", true)
    private val windBoostBind by key("WindBoostBind", GLFW.GLFW_KEY_I)

    private val crossBow by boolean("CrossBow", true)
    private val crossBowBind by key("CrossBowBind", GLFW.GLFW_KEY_O)
    private val cbSwapBack by boolean("CBSwapBack", true)

    private val scheduledActions = mutableListOf<ScheduledAction>()

    private var lastObSwitchPlace = 0L
    private var lastAnchorSequence = 0L
    private var lastShieldBreaker = 0L
    private var lastCrossBow = 0L
    private var pendingWindBoost = false
    private var wasWindBoostPressed = false
    private var rotationVec: Vec3? = null
    private var prevCrystalSlot = -1
    private var crystalPlaceDelayCounter = 0
    private var crystalBreakDelayCounter = 0

    override fun disable() {
        scheduledActions.clear()
        rotationVec = null
        pendingWindBoost = false
        wasWindBoostPressed = false
        prevCrystalSlot = -1
        crystalPlaceDelayCounter = 0
        crystalBreakDelayCounter = 0
    }

    @Suppress("unused")
    private val rotationUpdateHandler = handler<RotationUpdateEvent> {
        updateWindBoostRotationTarget()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val now = System.currentTimeMillis()

        runScheduledActions(now)
        applyQueuedRotation()
        handleWindBoost()

        if (handleAnchors(now)) {
            return@handler
        }

        handleCrystals()
        handleShieldBreaker(now)
        handleMinecartRefill()
        handleCrossBow(now)
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.OUTGOING || event.isCancelled) {
            return@handler
        }

        handleCrystalOptimizer(event.packet)
        handleMinecartBowRelease(event.packet)
    }

    private fun handleAnchors(now: Long): Boolean {
        if (!anchors || !anchorBind.isPressed || now - lastAnchorSequence < anchorDelay * 5L + 100L) {
            return false
        }

        lastAnchorSequence = now

        val glowSlot = Slots.Hotbar.findClosestSlot(Items.GLOWSTONE)?.hotbarSlot ?: return true
        val anchorSlot = Slots.Hotbar.findClosestSlot(Items.RESPAWN_ANCHOR)?.hotbarSlot ?: return true
        val prevSlot = player.inventory.selectedSlot

        selectSlot(anchorSlot)
        schedule(anchorDelay.toLong()) { useCurrentCrosshair() }
        schedule(anchorDelay * 2L) { selectSlot(glowSlot) }
        schedule(anchorDelay * 3L) { useCurrentCrosshair() }
        schedule(anchorDelay * 4L) { selectSlot(prevSlot) }
        schedule(anchorDelay * 5L) { useCurrentCrosshair() }

        return true
    }

    private fun handleCrystals() {
        if (!crystals || !crystalBind.isPressed) {
            resetCrystals()
            return
        }

        if (prevCrystalSlot == -1 && crystalSwapBack) {
            prevCrystalSlot = player.inventory.selectedSlot
        }

        if (crystalPlaceDelayCounter > 0) {
            crystalPlaceDelayCounter--
        }

        if (crystalBreakDelayCounter > 0) {
            crystalBreakDelayCounter--
        }

        val crosshairTarget = mc.crosshairTarget ?: return

        handleObsidianSwitch(crosshairTarget)
        handleCrystalSwitch(crosshairTarget)
        handleCrystalAction(crosshairTarget)
    }

    private fun handleObsidianSwitch(crosshairTarget: HitResult) {
        if (!obSwitch || crosshairTarget !is BlockHitResult || crosshairTarget.type != HitResult.Type.BLOCK) {
            return
        }

        val blockPos = crosshairTarget.blockPosition()
        val block = world.getBlockState(blockPos).block

        if (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) {
            return
        }

        val obSlot = Slots.Hotbar.findClosestSlot(Items.OBSIDIAN)?.hotbarSlot ?: return

        if (player.inventory.selectedSlot != obSlot) {
            selectSlot(obSlot)
        }

        val now = System.currentTimeMillis()

        if (player.mainHandItem.item == Items.OBSIDIAN && crystalPlaceDelayCounter <= 0 &&
            now - lastObSwitchPlace >= 500L) {
            interaction.interactBlock(player, InteractionHand.MAIN_HAND, crosshairTarget)
            crystalPlaceDelayCounter = placeDelay
            lastObSwitchPlace = now
        }
    }

    private fun handleCrystalSwitch(crosshairTarget: HitResult) {
        if (!crySwitch || player.mainHandItem.item == Items.END_CRYSTAL) {
            return
        }

        val shouldHoldCrystal = when (crosshairTarget) {
            is BlockHitResult -> {
                val block = world.getBlockState(crosshairTarget.blockPosition()).block
                crosshairTarget.type == HitResult.Type.BLOCK && (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK)
            }

            is EntityHitResult -> crosshairTarget.entity is EndCrystal
            else -> false
        }

        if (!shouldHoldCrystal) {
            return
        }

        val crystalSlot = Slots.Hotbar.findClosestSlot(Items.END_CRYSTAL)?.hotbarSlot ?: return

        if (player.inventory.selectedSlot != crystalSlot) {
            selectSlot(crystalSlot)
        }
    }

    private fun handleCrystalAction(crosshairTarget: HitResult) {
        if (player.mainHandItem.item != Items.END_CRYSTAL) {
            return
        }

        when (crosshairTarget) {
            is BlockHitResult -> {
                if (crosshairTarget.type != HitResult.Type.BLOCK || crystalPlaceDelayCounter > 0) {
                    return
                }

                val blockPos = crosshairTarget.blockPosition()
                val block = world.getBlockState(blockPos).block

                if ((block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) && isValidCrystalPlacement(blockPos)) {
                    interaction.interactBlock(player, InteractionHand.MAIN_HAND, crosshairTarget)
                    crystalPlaceDelayCounter = placeDelay
                }
            }

            is EntityHitResult -> {
                val entity = crosshairTarget.entity

                if (entity is EndCrystal && crystalBreakDelayCounter <= 0) {
                    interaction.attackEntity(player, entity)
                    player.swingHand(InteractionHand.MAIN_HAND)
                    crystalBreakDelayCounter = breakDelay
                }
            }
        }
    }

    private fun resetCrystals() {
        crystalPlaceDelayCounter = 0
        crystalBreakDelayCounter = 0

        if (prevCrystalSlot != -1 && crystalSwapBack) {
            selectSlot(prevCrystalSlot)
        }

        prevCrystalSlot = -1
    }

    private fun handleShieldBreaker(now: Long) {
        if (!shieldBreaker || now - lastShieldBreaker < 500L) {
            return
        }

        val target = (mc.crosshairTarget as? EntityHitResult)?.entity as? Player ?: return

        if (FriendManager.isFriend(target) ||
            target.activeItem.item != Items.SHIELD ||
            (target.offhandItem.item != Items.SHIELD && target.mainHandItem.item != Items.SHIELD)) {
            return
        }

        val axeSlot = findAxeInHotbar()

        if (axeSlot == -1) {
            return
        }

        lastShieldBreaker = now

        val prevSlot = player.inventory.selectedSlot

        schedule(breakerDelay.toLong()) { selectSlot(axeSlot) }
        schedule(breakerDelay * 2L) {
            val currentTarget = (mc.crosshairTarget as? EntityHitResult)?.entity ?: return@schedule
            interaction.attackEntity(player, currentTarget)
            player.swingHand(InteractionHand.MAIN_HAND)
        }

        if (shieldSwapBack) {
            schedule(breakerDelay * 3L) { selectSlot(prevSlot) }
        }
    }

    private fun handleMinecartRefill() {
        if (!minecarts || !refill || player.inventory.getStack(refillSlot - 1).item == Items.TNT_MINECART) {
            return
        }

        val inventorySlot = Slots.Inventory.findSlot(Items.TNT_MINECART) ?: return
        val slotId = inventorySlot.getIdForServer(null)

        interaction.clickSlot(0, slotId, refillSlot - 1, ContainerInput.SWAP, player)
    }

    private fun handleCrossBow(now: Long) {
        if (!crossBow || !crossBowBind.isPressed || now - lastCrossBow < 300L) {
            return
        }

        val crossBowSlot = Slots.Hotbar.findClosestSlot { stack ->
            stack.item == Items.CROSSBOW && stack.get(DataComponents.CHARGED_PROJECTILES)?.isEmpty == false
        }?.hotbarSlot ?: return

        lastCrossBow = now

        val prevSlot = player.inventory.selectedSlot

        selectSlot(crossBowSlot)
        interaction.interactItem(player, InteractionHand.MAIN_HAND, player.yaw, player.pitch)

        if (cbSwapBack) {
            selectSlot(prevSlot)
        }
    }

    private fun handleWindBoost() {
        if (!pendingWindBoost || !player.onGround()) {
            if (!player.onGround()) {
                pendingWindBoost = false
            }
            return
        }

        val windSlot = Slots.Hotbar.findClosestSlot(Items.WIND_CHARGE)?.hotbarSlot ?: return
        val prevSlot = player.inventory.selectedSlot

        val rotation = RotationManager.currentRotation ?: RotationManager.serverRotation

        if (rotation.pitch < 85.0f) {
            return
        }

        pendingWindBoost = false

        selectSlot(windSlot)
        interaction.interactItem(
            player,
            InteractionHand.MAIN_HAND,
            rotation.yaw,
            rotation.pitch
        )
        schedule(0L) { selectSlot(prevSlot) }
    }

    private fun updateWindBoostRotationTarget() {
        if (!windBoostJump) {
            pendingWindBoost = false
            wasWindBoostPressed = false
            return
        }

        val pressed = windBoostBind.isPressed
        val canStart = pressed && !wasWindBoostPressed && player.onGround() &&
            Slots.Hotbar.findClosestSlot(Items.WIND_CHARGE) != null

        wasWindBoostPressed = pressed

        if (canStart) {
            pendingWindBoost = true
        }

        if (!pendingWindBoost) {
            return
        }

        RotationManager.setRotationTarget(
            RotationTarget(
                rotation = Rotation(player.yaw, 90.0f),
                ticksUntilReset = 2,
                resetThreshold = 2.0f,
                considerInventory = false,
                movementCorrection = MovementCorrection.SILENT
            ),
            Priority.IMPORTANT_FOR_USAGE_3,
            this
        )
    }

    private fun handleCrystalOptimizer(packet: Packet<*>) {
        if (!crystalOptimizer || packet !is ServerboundInteractPacket) {
            return
        }

        val entity = world.getEntityById(packet.entityId)

        if (entity is EndCrystal) {
            world.removeEntity(entity.id, Entity.RemovalReason.KILLED)
        }
    }

    private fun handleMinecartBowRelease(packet: Packet<*>) {
        if (packet !is ServerboundPlayerActionPacket || packet.action != ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM ||
            !minecarts || player.mainHandItem.item != Items.BOW) {
            return
        }

        val blockPos = calcTrajectory(player.yaw) ?: return
        val distanceSq = player.eyePos.squaredDistanceTo(blockPos.toCenterPos())

        if (distanceSq > maxDistance * maxDistance || distanceSq <= 3.0) {
            return
        }

        val railSlot = Slots.Hotbar.findClosestSlot(
            Items.RAIL,
            Items.ACTIVATOR_RAIL,
            Items.DETECTOR_RAIL,
            Items.POWERED_RAIL
        )?.hotbarSlot ?: return

        val cartSlot = Slots.Hotbar.findClosestSlot(Items.TNT_MINECART)?.hotbarSlot ?: return
        val prevSlot = player.inventory.selectedSlot

        selectSlot(railSlot)
        interactBlockPacket(
            BlockHitResult(
                Vec3(blockPos.x + 0.5, blockPos.up().y.toDouble(), blockPos.z.toDouble()),
                Direction.UP,
                blockPos,
                false
            )
        )

        rotationVec = blockPos.up().toCenterPos()
        selectSlot(cartSlot)
        interactBlockPacket(
            BlockHitResult(
                Vec3(blockPos.x + 0.5, blockPos.up().y + 0.125, blockPos.z + 0.5),
                Direction.UP,
                blockPos.up(),
                false
            )
        )
        selectSlot(prevSlot)
    }

    private fun isValidCrystalPlacement(blockPos: BlockPos): Boolean {
        val up = blockPos.up()

        if (!world.isAir(up)) {
            return false
        }

        val box = AABB(
            up.x.toDouble(),
            up.y.toDouble(),
            up.z.toDouble(),
            up.x + 1.0,
            up.y + 2.0,
            up.z + 1.0
        )

        return world.getOtherEntities(null, box).isEmpty()
    }

    private fun calcTrajectory(yaw: Float): BlockPos? {
        var x = player.x
        var y = player.y + player.getEyeHeight(player.pose) - 0.1000000014901161
        var z = player.z

        val yawRad = Math.toRadians(yaw.toDouble())
        val pitchRad = Math.toRadians(player.pitch.toDouble())
        var motionX = -Math.sin(yawRad) * Math.cos(pitchRad)
        var motionY = -Math.sin(pitchRad)
        var motionZ = Math.cos(yawRad) * Math.cos(pitchRad)

        var power = player.ticksUsingItem / 20.0f
        power = (power * power + power * 2.0f) / 3.0f

        if (power > 1.0f) {
            power = 1.0f
        }

        val distance = sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ)

        if (distance <= 0.0) {
            return null
        }

        motionX /= distance
        motionY /= distance
        motionZ /= distance

        val bowPower = power * 3.0f
        motionX *= bowPower
        motionY *= bowPower
        motionZ *= bowPower

        if (!player.onGround()) {
            motionY += player.deltaMovement.y
        }

        repeat(300) {
            val lastPos = Vec3(x, y, z)

            x += motionX
            y += motionY
            z += motionZ

            motionX *= 0.99
            motionY *= 0.99
            motionZ *= 0.99
            motionY -= 0.05

            val projectileBox = AABB(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.3)

            if (world.entitiesForRendering().any { entity ->
                    entity !is Arrow && entity != player && entity.boundingBox.intersects(projectileBox)
                }) {
                return null
            }

            val pos = Vec3(x, y, z)
            val blockHit = world.raycast(
                ClipContext(lastPos, pos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)
            )

            if (blockHit.type == HitResult.Type.BLOCK) {
                return blockHit.blockPosition()
            }

            if (y <= -65.0) {
                return null
            }
        }

        return null
    }

    private fun applyQueuedRotation() {
        val vec = rotationVec ?: return
        val rotation = Rotation.lookingAt(vec, player.eyePos)

        player.yRot = rotation.yaw
        player.xRot = rotation.pitch
        rotationVec = null
    }

    private fun useCurrentCrosshair() {
        when (val target = mc.crosshairTarget) {
            is BlockHitResult -> interaction.interactBlock(player, InteractionHand.MAIN_HAND, target)
            else -> interaction.interactItem(player, InteractionHand.MAIN_HAND)
        }

        player.swingHand(InteractionHand.MAIN_HAND)
    }

    private fun interactBlockPacket(blockHitResult: BlockHitResult) {
        interaction.sendSequencedPacket(world) { sequence ->
            ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, blockHitResult, sequence)
        }
    }

    private fun selectSlot(slot: Int) {
        if (slot !in 0..8 || player.inventory.selectedSlot == slot) {
            return
        }

        player.inventory.selectedSlot = slot
        interaction.ensureHasSentCarriedItem()
    }

    private fun findAxeInHotbar(): Int {
        for (slot in 0..8) {
            if (player.inventory.getStack(slot).item is AxeItem) {
                return slot
            }
        }

        return -1
    }

    private fun schedule(delayMs: Long, action: () -> Unit) {
        scheduledActions += ScheduledAction(System.currentTimeMillis() + delayMs, action)
    }

    private fun runScheduledActions(now: Long) {
        val iterator = scheduledActions.iterator()

        while (iterator.hasNext()) {
            val scheduledAction = iterator.next()

            if (now < scheduledAction.dueTime) {
                continue
            }

            iterator.remove()
            scheduledAction.action()
        }
    }

    private data class ScheduledAction(
        val dueTime: Long,
        val action: () -> Unit
    )
}
