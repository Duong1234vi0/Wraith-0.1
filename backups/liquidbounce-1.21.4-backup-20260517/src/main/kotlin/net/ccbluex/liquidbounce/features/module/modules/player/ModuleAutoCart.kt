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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.KeyEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.Entity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.entity.vehicle.TntMinecartEntity
import net.minecraft.item.Items
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.FluidTags
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext
import org.lwjgl.glfw.GLFW

/**
 * Places a rail and TNT minecart where the player's arrow is predicted to land.
 */
object ModuleAutoCart : ClientModule("AutoCart", Category.PLAYER) {

    private const val MAX_PREDICTION_TICKS = 80
    private const val BOW_CHARGE_TICKS = 10
    private const val CROSSBOW_CHARGE_TICKS = 25
    private const val ARROW_GRAVITY = 0.05000000074505806

    private val weaponMode by enumChoice("Mode", WeaponMode.BOW)
    private val range by float("Range", 5.0f, 1.0f..8.0f)
    private val cartLeadTicks by int("CartLeadTicks", 2, 0..8, "ticks")
    private val shootKey by key("ShootKey", GLFW.GLFW_KEY_UNKNOWN)

    private val knownArrows = hashSetOf<Int>()
    private val processedArrows = hashSetOf<Int>()

    private var shotQueued = false
    private var weaponCharging = false
    private var weaponChargeTime = 0
    private var weaponPrevSlot = -1
    private var pendingWeaponRestoreSlot = -1
    private var pendingCrossbowFireSlot = -1
    private var pendingCrossbowRestoreSlot = -1

    private var pendingCartPlacement: PendingCartPlacement? = null

    override fun enable() {
        knownArrows.clear()
        processedArrows.clear()
        pendingCartPlacement = null
        pendingWeaponRestoreSlot = -1
        pendingCrossbowFireSlot = -1
        pendingCrossbowRestoreSlot = -1
        resetWeaponState()
        cacheExistingArrows()
    }

    override fun disable() {
        knownArrows.clear()
        processedArrows.clear()
        pendingCartPlacement = null
        pendingWeaponRestoreSlot = -1
        pendingCrossbowFireSlot = -1
        pendingCrossbowRestoreSlot = -1
        mc.options.useKey.setPressed(false)
        resetWeaponState()
    }

    @Suppress("unused")
    private val keyHandler = handler<KeyEvent> { event ->
        if (event.action != GLFW.GLFW_PRESS || shootKey.code == GLFW.GLFW_KEY_UNKNOWN) {
            return@handler
        }

        if (event.key.code == shootKey.code) {
            shotQueued = true
        }
    }

    @Suppress("unused")
    private val tickHandler = tickHandler {
        if (handlePendingCrossbowFire()) {
            retainAliveArrows()
            return@tickHandler
        }

        handlePendingWeaponRestore()

        handleWeaponShoot()

        if (handlePendingCartPlacement()) {
            retainAliveArrows()
            return@tickHandler
        }

        val aliveArrows = hashSetOf<Int>()

        for (entity in world.entities) {
            val projectile = entity as? PersistentProjectileEntity ?: continue

            if (projectile is TridentEntity || projectile.owner !== player) {
                continue
            }

            val id = projectile.id
            aliveArrows += id
            knownArrows += id

            if (id in processedArrows || !hasRequiredItemsInHotbar() || !shouldProcessArrow(projectile)) {
                continue
            }

            val prediction = predictLanding(projectile) ?: continue
            val rangeSq = range * range

            if (player.squaredDistanceTo(Vec3d.ofCenter(prediction.placePos)) > rangeSq) {
                processedArrows += id
                continue
            }

            if (scheduleCartAt(prediction, id) || projectile.isOnGround) {
                processedArrows += id

                if (handlePendingCartPlacement()) {
                    retainAliveArrows()
                    return@tickHandler
                }
            }
        }

        knownArrows.retainAll(aliveArrows)
        processedArrows.retainAll(aliveArrows)
    }

    private fun handleWeaponShoot() {
        if (weaponCharging) {
            if (!player.mainHandStack.isOf(weaponMode.item)) {
                finishWeaponShot(false)
                return
            }

            weaponChargeTime++
            if (weaponChargeTime >= weaponMode.chargeTicks) {
                finishWeaponShot(true)
            }
            return
        }

        if (!shotQueued) {
            return
        }

        shotQueued = false

        if (weaponMode == WeaponMode.CROSSBOW) {
            val chargedCrossbowSlot = Slots.Hotbar.findClosestSlot { stack ->
                stack.isOf(Items.CROSSBOW) && stack.get(DataComponentTypes.CHARGED_PROJECTILES)?.isEmpty == false
            }?.hotbarSlot

            if (chargedCrossbowSlot != null) {
                weaponPrevSlot = player.inventory.selectedSlot
                selectSlot(chargedCrossbowSlot)
                fireCrossbow()
                return
            }
        }

        val weaponSlot = Slots.Hotbar.findClosestSlot(weaponMode.item)?.hotbarSlot ?: return

        weaponPrevSlot = player.inventory.selectedSlot
        selectSlot(weaponSlot)
        mc.options.useKey.setPressed(true)
        weaponCharging = true
        weaponChargeTime = 0
    }

    private fun finishWeaponShot(releaseShot: Boolean) {
        if (releaseShot) {
            mc.options.useKey.setPressed(false)
            if (player.isUsingItem) {
                interaction.stopUsingItem(player)
            }
        }

        if (weaponMode == WeaponMode.CROSSBOW && releaseShot) {
            pendingCrossbowFireSlot = player.inventory.selectedSlot
            pendingCrossbowRestoreSlot = weaponPrevSlot
        } else if (weaponPrevSlot in 0..8 && player.inventory.selectedSlot != weaponPrevSlot) {
            pendingWeaponRestoreSlot = weaponPrevSlot
        }

        resetWeaponState()
    }

    private fun resetWeaponState() {
        shotQueued = false
        weaponCharging = false
        weaponChargeTime = 0
        weaponPrevSlot = -1
    }

    private fun cacheExistingArrows() {
        for (entity in world.entities) {
            val projectile = entity as? PersistentProjectileEntity ?: continue

            if (projectile !is TridentEntity && projectile.owner === player) {
                knownArrows += projectile.id
            }
        }
    }

    private fun hasRequiredItemsInHotbar(): Boolean =
        Slots.Hotbar.findClosestSlot(Items.RAIL) != null &&
            Slots.Hotbar.findClosestSlot(Items.TNT_MINECART) != null

    private fun shouldProcessArrow(projectile: PersistentProjectileEntity): Boolean =
        !projectile.isOnGround && projectile.velocity.lengthSquared() > 1.0E-4

    private fun retainAliveArrows() {
        val aliveArrows = world.entities.asSequence()
            .filterIsInstance<PersistentProjectileEntity>()
            .filter { it !is TridentEntity && it.owner === player }
            .mapTo(hashSetOf()) { it.id }

        knownArrows.retainAll(aliveArrows)
        processedArrows.retainAll(aliveArrows)
    }

    private fun predictLanding(arrow: PersistentProjectileEntity): LandingPrediction? {
        var pos = arrow.pos
        var motion = arrow.velocity

        for (tick in 1..MAX_PREDICTION_TICKS) {
            val previousPos = pos
            pos = pos.add(motion)
            motion = updateMotion(previousPos, motion)

            val hit = world.raycast(
                RaycastContext(
                    previousPos,
                    pos,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    arrow
                )
            )

            if (hit.type != HitResult.Type.MISS && hit is BlockHitResult) {
                val placePos = resolvePlacePos(hit) ?: return null
                return LandingPrediction(placePos, tick)
            }

            if (pos.y < world.bottomY - 2.0) {
                break
            }
        }

        return null
    }

    private fun updateMotion(pos: Vec3d, motion: Vec3d): Vec3d {
        val inWater = world.getBlockState(BlockPos.ofFloored(pos)).fluidState.isIn(FluidTags.WATER)
        val drag = if (inWater) 0.6 else 0.99

        return motion.multiply(drag).add(0.0, -ARROW_GRAVITY, 0.0)
    }

    private fun resolvePlacePos(hit: BlockHitResult): BlockPos? {
        val basePos = hit.blockPos

        if (isRail(basePos)) {
            return basePos
        }

        val preferred = if (hit.side == Direction.UP) basePos.up() else basePos

        return when {
            canPlaceRailAt(preferred) -> preferred
            canPlaceRailAt(basePos.up()) -> basePos.up()
            canPlaceRailAt(basePos.down()) -> basePos.down()
            else -> null
        }
    }

    private fun scheduleCartAt(prediction: LandingPrediction, arrowId: Int): Boolean {
        if (pendingCartPlacement != null) {
            return false
        }

        val railSlot = Slots.Hotbar.findClosestSlot(Items.RAIL)?.hotbarSlot ?: return false
        val cartSlot = Slots.Hotbar.findClosestSlot(Items.TNT_MINECART)?.hotbarSlot ?: return false

        pendingCartPlacement = PendingCartPlacement(
            arrowId = arrowId,
            placePos = prediction.placePos,
            ticksToImpact = prediction.ticksToImpact,
            railSlot = railSlot,
            cartSlot = cartSlot,
            restoreSlot = player.inventory.selectedSlot
        )

        return true
    }

    private fun handlePendingCrossbowFire(): Boolean {
        val slot = pendingCrossbowFireSlot
        if (slot !in 0..8) {
            return false
        }

        pendingCrossbowFireSlot = -1
        selectSlot(slot)

        val didFire = player.mainHandStack.isOf(Items.CROSSBOW) &&
            player.mainHandStack.get(DataComponentTypes.CHARGED_PROJECTILES)?.isEmpty == false

        if (didFire) {
            interaction.interactItem(player, Hand.MAIN_HAND)
            player.swingHand(Hand.MAIN_HAND)
        }

        if (pendingCrossbowRestoreSlot in 0..8 && player.inventory.selectedSlot != pendingCrossbowRestoreSlot) {
            pendingWeaponRestoreSlot = pendingCrossbowRestoreSlot
        }

        pendingCrossbowRestoreSlot = -1
        return true
    }

    private fun fireCrossbow() {
        interaction.interactItem(player, Hand.MAIN_HAND)
        player.swingHand(Hand.MAIN_HAND)

        if (weaponPrevSlot in 0..8 && player.inventory.selectedSlot != weaponPrevSlot) {
            pendingWeaponRestoreSlot = weaponPrevSlot
        }

        weaponPrevSlot = -1
    }

    private fun handlePendingWeaponRestore(): Boolean {
        val slot = pendingWeaponRestoreSlot
        if (slot !in 0..8) {
            return false
        }

        pendingWeaponRestoreSlot = -1
        selectSlot(slot)
        return true
    }

    private fun handlePendingCartPlacement(): Boolean {
        val pending = pendingCartPlacement ?: return false

        while (true) {
            when (pending.stage) {
                CartStage.PLACE_RAIL -> {
                    val alreadyRail = isRail(pending.placePos)

                    if (!alreadyRail && !placeRail(pending.placePos, pending.railSlot)) {
                        pending.stage = CartStage.RESTORE_SLOT
                        return true
                    }

                    pending.stage = CartStage.WAIT_CART
                    processedArrows += pending.arrowId

                    if (!alreadyRail) {
                        return true
                    }
                }

                CartStage.WAIT_CART -> {
                    val arrow = findArrowById(pending.arrowId)

                    if (arrow == null || arrow.isOnGround) {
                        pending.stage = CartStage.RESTORE_SLOT
                        return false
                    }

                    predictLanding(arrow)?.let { prediction ->
                        pending.ticksToImpact = prediction.ticksToImpact
                        if (!isRail(pending.placePos)) {
                            pending.placePos = prediction.placePos
                        }
                    }

                    if (pending.ticksToImpact > cartLeadTicks) {
                        return false
                    }

                    if (!hasTntMinecart(pending.placePos)) {
                        placeTntMinecart(pending.placePos, pending.cartSlot)
                    }

                    pending.stage = CartStage.RESTORE_SLOT
                    return true
                }

                CartStage.RESTORE_SLOT -> {
                    selectSlot(pending.restoreSlot)
                    pending.stage = CartStage.RESTOCK_CART
                    return true
                }

                CartStage.RESTOCK_CART -> {
                    restockMinecartSlot(pending.cartSlot)
                    pendingCartPlacement = null
                    return true
                }
            }
        }
    }

    private fun findArrowById(id: Int): PersistentProjectileEntity? =
        world.entities.asSequence()
            .filterIsInstance<PersistentProjectileEntity>()
            .firstOrNull { it.id == id && it !is TridentEntity && it.owner === player }

    private fun isRail(pos: BlockPos): Boolean =
        world.getBlockState(pos).isIn(BlockTags.RAILS)

    private fun canPlaceRailAt(pos: BlockPos): Boolean {
        if (isRail(pos)) {
            return true
        }

        if (!world.getBlockState(pos).isReplaceable) {
            return false
        }

        val support = pos.down()
        val supportState = world.getBlockState(support)

        return supportState.isSideSolidFullSquare(world, support, Direction.UP)
    }

    private fun placeRail(placePos: BlockPos, railSlot: Int): Boolean {
        selectSlot(railSlot)

        val support = placePos.down()
        val hitResult = BlockHitResult(
            Vec3d(placePos.x + 0.5, placePos.y.toDouble(), placePos.z + 0.5),
            Direction.UP,
            support,
            false
        )

        val result = interaction.interactBlock(player, Hand.MAIN_HAND, hitResult)
        if (result.isAccepted) {
            player.swingHand(Hand.MAIN_HAND)
            return true
        }

        return isRail(placePos)
    }

    private fun placeTntMinecart(railPos: BlockPos, cartSlot: Int) {
        selectSlot(cartSlot)

        val hitResult = BlockHitResult(
            Vec3d(railPos.x + 0.5, railPos.y + 0.15, railPos.z + 0.5),
            Direction.UP,
            railPos,
            false
        )

        val result: ActionResult = interaction.interactBlock(player, Hand.MAIN_HAND, hitResult)
        if (result.isAccepted) {
            player.swingHand(Hand.MAIN_HAND)
        }
    }

    private fun restockMinecartSlot(hotbarSlot: Int) {
        if (hotbarSlot !in 0..8 || player.inventory.getStack(hotbarSlot).isOf(Items.TNT_MINECART)) {
            return
        }

        val inventorySlot = Slots.Inventory.findSlot(Items.TNT_MINECART) ?: return
        val slotId = inventorySlot.getIdForServer(null)

        interaction.clickSlot(0, slotId, hotbarSlot, SlotActionType.SWAP, player)
    }

    private fun hasTntMinecart(railPos: BlockPos): Boolean {
        val checkBox = Box(railPos).expand(0.2, 1.0, 0.2)

        return world.getOtherEntities(null, checkBox).any { entity: Entity ->
            entity is TntMinecartEntity
        }
    }

    private fun selectSlot(slot: Int) {
        if (slot !in 0..8 || player.inventory.selectedSlot == slot) {
            return
        }

        player.inventory.selectedSlot = slot
        interaction.syncSelectedSlot()
    }

    private data class LandingPrediction(
        val placePos: BlockPos,
        val ticksToImpact: Int
    )

    private data class PendingCartPlacement(
        val arrowId: Int,
        var placePos: BlockPos,
        var ticksToImpact: Int,
        val railSlot: Int,
        val cartSlot: Int,
        val restoreSlot: Int,
        var stage: CartStage = CartStage.PLACE_RAIL
    )

    private enum class CartStage {
        PLACE_RAIL,
        WAIT_CART,
        RESTORE_SLOT,
        RESTOCK_CART
    }

    private enum class WeaponMode(
        override val choiceName: String,
        val item: net.minecraft.item.Item,
        val chargeTicks: Int
    ) : NamedChoice {
        BOW("Bow", Items.BOW, BOW_CHARGE_TICKS),
        CROSSBOW("Crossbow", Items.CROSSBOW, CROSSBOW_CHARGE_TICKS)
    }

}
