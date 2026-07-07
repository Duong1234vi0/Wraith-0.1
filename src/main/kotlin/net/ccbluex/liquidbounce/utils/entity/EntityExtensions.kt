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

@file:Suppress("TooManyFunctions")

package net.ccbluex.liquidbounce.utils.entity
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.common.ShapeFlag
import net.ccbluex.liquidbounce.interfaces.ClientPlayerEntityAddition
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.block.DIRECTIONS_EXCLUDING_UP
import net.ccbluex.liquidbounce.utils.block.isBlastResistant
import net.ccbluex.liquidbounce.utils.block.raycast
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.toRadians
import net.ccbluex.liquidbounce.utils.math.minus
import net.ccbluex.liquidbounce.utils.math.plus
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.ccbluex.liquidbounce.utils.movement.findEdgeCollision
import net.minecraft.world.phys.shapes.EntityCollisionContext
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.client.player.ClientInput
import net.minecraft.client.player.LocalPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.monster.Creeper
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.minecart.MinecartTNT
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Input
import net.minecraft.world.phys.HitResult
import net.minecraft.core.*
import net.minecraft.world.phys.*
import net.minecraft.util.Mth
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.Difficulty
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerExplosion
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt

val Entity.netherPosition: Vec3
    get() = if (world.dimension() == Level.NETHER) {
        Vec3(x, y, z)
    } else {
        Vec3(x / 8.0, y, z / 8.0)
    }

val LocalPlayer.moving
    get() = input.getMoveVector().lengthSquared() != 0.0f

val Input.untransformed: Input
    get() = this

val Input.initial: Input
    get() = this

val Entity.exactPosition
    get() = Vec3(x, y, z)

val Entity.blockVecPosition
    get() = Vec3i(blockX, blockY, blockZ)

val Player.ping: Int
    get() = mc.connection?.getPlayerInfo(uuid)?.latency ?: 0

val LocalPlayer.airTicks: Int
    get() = (this as ClientPlayerEntityAddition).`liquid_bounce$getAirTicks`()

val LocalPlayer.onGroundTicks: Int
    get() = (this as ClientPlayerEntityAddition).`liquid_bounce$getOnGroundTicks`()

val LocalPlayer.direction: Float
    get() = getMovementDirectionOfInput(DirectionalInput(input))

fun LocalPlayer.getMovementDirectionOfInput(input: DirectionalInput): Float {
    return getMovementDirectionOfInput(this.yaw, input)
}

val LocalPlayer.isBlockAction: Boolean
    get() = isUsingItem && useItem.useAnimation == ItemUseAnimation.BLOCK

fun Entity.lastRenderPos() = Vec3(this.lastRenderX, this.lastRenderY, this.lastRenderZ)

val InteractionHand.equipmentSlot: EquipmentSlot
    get() = when (this) {
        InteractionHand.MAIN_HAND -> EquipmentSlot.MAINHAND
        InteractionHand.OFF_HAND -> EquipmentSlot.OFFHAND
    }

fun LocalPlayer.wouldBeCloseToFallOff(position: Vec3): Boolean {
    val hitbox =
        this.dimensions
            .makeBoundingBox(position)
            .expand(-0.05, 0.0, -0.05)
            .offset(0.0, this.fallDistance - this.maxUpStep().toDouble(), 0.0)

    return world.noCollision(this, hitbox)
}

fun LocalPlayer.isCloseToEdge(
    directionalInput: DirectionalInput,
    distance: Double = 0.1,
    pos: Vec3 = this.pos,
): Boolean {
    val alpha = (getMovementDirectionOfInput(this.yaw, directionalInput) + 90.0F).toRadians()
    val simulatedInput = SimulatedPlayer.SimulatedPlayerInput.fromClientPlayer(directionalInput)
    simulatedInput.playerInput = simulatedInput.playerInput.copy(
        jump = false,
        sneak = false,
    )
    simulatedInput.update()

    val simulatedPlayer = SimulatedPlayer.fromClientPlayer(
        simulatedInput
    )

    simulatedPlayer.pos = pos
    simulatedPlayer.tick()

    val nextVelocity = simulatedPlayer.deltaMovement
    val direction = if (nextVelocity.horizontalDistanceSqr() > 0.003 * 0.003) {
        nextVelocity.multiply(1.0, 0.0, 1.0).normalize()
    } else {
        Vec3(cos(alpha).toDouble(), 0.0, sin(alpha).toDouble())
    }

    val from = pos.add(0.0, -0.1, 0.0)
    val to = from + direction.multiply(distance)

    if (findEdgeCollision(from, to) != null) {
        return true
    }

    val playerPosInTwoTicks = simulatedPlayer.pos.add(nextVelocity.multiply(1.0, 0.0, 1.0))

    return wouldBeCloseToFallOff(pos) || wouldBeCloseToFallOff(playerPosInTwoTicks)
}

/**
 * Check if the player can step up by [height] blocks.
 *
 * TODO: Use Minecraft Step logic instead of this basic collision check.
 */
fun LocalPlayer.canStep(height: Double = 1.0): Boolean {
    if (!horizontalCollision || isDescending || !onGround()) {
        // If we are not colliding with anything, we are not meant to step
        return false
    }

    val box = this.boundingBox
    val direction = getMovementDirectionOfInput(DirectionalInput(input))

    val angle = Math.toRadians(direction.toDouble())
    val xOffset = -sin(angle) * 0.1
    val zOffset = cos(angle) * 0.1

    val offsetBox = box.offset(xOffset, 0.0, zOffset)
    val stepBox = offsetBox.offset(0.0, height, 0.0)

    return world.getBlockCollisions(this, stepBox).all { shape ->
        shape == Shapes.empty()
    } && world.getBlockCollisions(this, offsetBox).all { shape ->
        shape != Shapes.empty()
    }
}


fun getMovementDirectionOfInput(facingYaw: Float, input: DirectionalInput): Float {
    val forwards = input.forwards && !input.backwards
    val backwards = input.backwards && !input.forwards
    val left = input.left && !input.right
    val right = input.right && !input.left

    var actualYaw = facingYaw
    var forward = 1f

    if (backwards) {
        actualYaw += 180f
        forward = -0.5f
    } else if (forwards) {
        forward = 0.5f
    }

    if (left) {
        actualYaw -= 90f * forward
    }
    if (right) {
        actualYaw += 90f * forward
    }

    return actualYaw
}

val Player.sqrtSpeed: Double
    get() = velocity.sqrtSpeed

val Vec3.sqrtSpeed: Double
    get() = sqrt(x * x + z * z)

fun Vec3.withStrafe(
    speed: Double = sqrtSpeed,
    strength: Double = 1.0,
    input: DirectionalInput? = DirectionalInput(player.input),
    yaw: Float = player.getMovementDirectionOfInput(input ?: DirectionalInput(player.input)),
): Vec3 {
    if (input?.isMoving == false) {
        return Vec3(0.0, y, 0.0)
    }

    val prevX = x * (1.0 - strength)
    val prevZ = z * (1.0 - strength)
    val useSpeed = speed * strength

    val angle = Math.toRadians(yaw.toDouble())
    val x = (-sin(angle) * useSpeed) + prevX
    val z = (cos(angle) * useSpeed) + prevZ
    return Vec3(x, y, z)
}

val Entity.prevPos: Vec3
    get() = Vec3(this.prevX, this.prevY, this.prevZ)

val Entity.rotation: Rotation
    get() = Rotation(this.yaw, this.pitch, true)

val LocalPlayer.lastRotation: Rotation
    get() = Rotation(this.lastYaw, this.lastPitch, true)

val Entity.box: AABB
    get() = boundingBox.expand(targetingMargin.toDouble())

/**
 * Allows to calculate the distance between the current entity and [entity] from the nearest corner of the bounding box
 */
fun Entity.boxedDistanceTo(entity: Entity): Double {
    return sqrt(squaredBoxedDistanceTo(entity))
}

fun Entity.squaredBoxedDistanceTo(entity: Entity): Double {
    return this.squaredBoxedDistanceTo(entity.eyePos)
}

fun Entity.squaredBoxedDistanceTo(otherPos: Vec3): Double {
    return this.box.squaredBoxedDistanceTo(otherPos)
}

fun Entity.squareBoxedDistanceTo(entity: Entity, offsetPos: Vec3): Double {
    return this.box.offset(offsetPos - this.pos).squaredBoxedDistanceTo(entity.eyePos)
}

fun AABB.squaredBoxedDistanceTo(otherPos: Vec3): Double {
    val pos = getNearestPoint(otherPos, this)

    return pos.squaredDistanceTo(otherPos)
}

fun Entity.interpolateCurrentPosition(tickDelta: Float): Vec3 {
    if (this.age == 0) {
        return this.pos
    }

    return Vec3(
        this.lastRenderX + (this.x - this.lastRenderX) * tickDelta,
        this.lastRenderY + (this.y - this.lastRenderY) * tickDelta,
        this.lastRenderZ + (this.z - this.lastRenderZ) * tickDelta
    )
}

fun Entity.interpolateCurrentRotation(tickDelta: Float): Rotation {
    if (this.age == 0) {
        return this.rotation
    }

    return Rotation(
        this.prevYaw + (this.yaw - this.prevYaw) * tickDelta,
        this.prevPitch + (this.pitch - this.prevPitch) * tickDelta,
    )
}

/**
 * Get the nearest point of a box. Very useful to calculate the distance of an enemy.
 */
fun getNearestPoint(eyes: Vec3, box: AABB): Vec3 {
    val origin = doubleArrayOf(eyes.x, eyes.y, eyes.z)
    val destMins = doubleArrayOf(box.minX, box.minY, box.minZ)
    val destMaxs = doubleArrayOf(box.maxX, box.maxY, box.maxZ)

    // It loops through every coordinate of the double arrays and picks the nearest point
    for (i in 0..2) {
        origin[i] = origin[i].coerceIn(destMins[i], destMaxs[i])
    }

    return Vec3(origin[0], origin[1], origin[2])
}

fun getNearestPointOnSide(eyes: Vec3, box: AABB, side: Direction): Vec3 {
    val nearestPointInBlock = getNearestPoint(eyes, box)

    val x = nearestPointInBlock.x
    val y = nearestPointInBlock.y
    val z = nearestPointInBlock.z

    val nearestPointOnSide =
        when (side) {
            Direction.DOWN -> Vec3(x, box.minY, z)
            Direction.UP -> Vec3(x, box.maxY, z)
            Direction.NORTH -> Vec3(x, y, box.minZ)
            Direction.SOUTH -> Vec3(x, y, box.maxZ)
            Direction.WEST -> Vec3(box.maxX, y, z)
            Direction.EAST -> Vec3(box.minX, y, z)
        }

    return nearestPointOnSide

}

fun LivingEntity.wouldBlockHit(source: Player): Boolean {
    if (!this.isBlocking) {
        return false
    }

    val facingVec = getViewVector(1.0f)
    val deltaPos = (pos - source.pos).multiply(1.0, 0.0, 1.0)

    return deltaPos.dotProduct(facingVec) < 0.0
}

/**
 * Applies armor, enchantments, effects, etc. to the damage and returns the damage
 * that is actually applied. This function is so damn ugly that I turned off code smell analysis for it.
 */
@Suppress("detekt:all")
fun LivingEntity.getEffectiveDamage(source: DamageSource, damage: Float, ignoreShield: Boolean = false): Float {
    if (isDead || world.difficulty == Difficulty.PEACEFUL) {
        return 0.0f
    }
    if (!ignoreShield && isBlocking) {
        return 0.0f
    }
    if (hasEffect(MobEffects.FIRE_RESISTANCE) && source.msgId == "onFire") {
        return 0.0f
    }
    return damage.coerceAtLeast(0.0f)
}

fun LivingEntity.getExplosionDamageFromEntity(entity: Entity): Float {
    return when (entity) {
        is EndCrystal -> getDamageFromExplosion(entity.pos, 6f, 12f, 144f)
        is PrimedTnt -> getDamageFromExplosion(entity.pos.add(0.0, 0.0625, 0.0), 4f, 8f, 64f)
        is MinecartTNT -> {
            val d = 5f
            getDamageFromExplosion(entity.pos, 4f + d * 1.5f)
        }

        is Creeper -> {
            val f = if (entity.isPowered) 2f else 1f
            getDamageFromExplosion(entity.pos, entity.explosionRadius * f)
        }

        else -> 0f
    }
}

/**
 * See [ExplosionDamageCalculator.calculateDamage].
 */
@Suppress("LongParameterList")
fun LivingEntity.getDamageFromExplosion(
    pos: Vec3,
    power: Float = 6f,
    explosionRange: Float = power * 2f, // allows setting precomputed values
    damageDistance: Float = explosionRange * explosionRange,
    exclude: Array<BlockPos>? = null,
    include: BlockPos? = null,
    maxBlastResistance: Float? = null,
    entityBoundingBox: AABB? = null
): Float {
    // no damage will be dealt if the entity is outside the explosion range or when the difficulty is peaceful
    if (this.position().distanceToSqr(pos) > damageDistance || world.difficulty == Difficulty.PEACEFUL) {
        return 0f
    }

    try {
        ShapeFlag.noShapeChange = true

        val useTweakedMethod = exclude != null ||
            maxBlastResistance != null ||
            include != null ||
            entityBoundingBox != null

        val exposure = getExposureToExplosion(pos, exclude, include, maxBlastResistance, entityBoundingBox)

        val distanceDecay = 1.0 - (sqrt(this.position().distanceToSqr(pos)) / explosionRange.toDouble())
        val pre1 = exposure.toDouble() * distanceDecay

        val preprocessedDamage = (pre1 * pre1 + pre1) / 2.0 * 7.0 * explosionRange.toDouble() + 1.0
        if (preprocessedDamage == 0.0) {
            return 0f
        }

        return preprocessedDamage.toFloat()
    } finally {
        ShapeFlag.noShapeChange = false
    }
}

/**
 * Basically [ServerExplosion.calculateReceivedDamage] but this method allows us to exclude blocks using [exclude].
 */
@Suppress("NestedBlockDepth")
fun LivingEntity.getExposureToExplosion(
    source: Vec3,
    exclude: Array<BlockPos>?,
    include: BlockPos?,
    maxBlastResistance: Float?,
    entityBoundingBox: AABB?
): Float = 1.0f

/**
 * Sometimes the server does not publish the actual entity health with its metadata.
 * This function incorporates other sources to get the actual value.
 *
 * Currently, uses the following sources:
 * 1. Scoreboard
 */
fun LivingEntity.getActualHealth(fromScoreboard: Boolean = true): Float {
    if (fromScoreboard) {
        val health = getHealthFromScoreboard()

        if (health != null) {
            return health
        }
    }


    return health
}

private fun LivingEntity.getHealthFromScoreboard(): Float? {
    return null
}

fun Entity.getBoundingBoxAt(pos: Vec3): AABB {
    return boundingBox.offset(pos - this.pos)
}

/**
 * Check if the entity collides with anything below his bounding box.
 */
fun Entity.doesNotCollideBelow(until: Double = -64.0): Boolean {
    if (this.y < until || boundingBox.minY < until) {
        return true
    }

    val offsetBb = boundingBox.withMinY(until)
    return world.getBlockCollisions(this, offsetBb)
        .all(Shapes.empty()::equals)
}

/**
 * Check if the entity box collides with any block in the world at the given [pos].
 */
fun Entity.doesCollideAt(pos: Vec3 = player.pos): Boolean {
    return !world.getBlockCollisions(this, getBoundingBoxAt(pos)).all(Shapes.empty()::equals)
}

/**
 * Check if the entity is likely falling to the void based on the given position and bounding box.
 */
fun Entity.wouldFallIntoVoid(pos: Vec3, voidLevel: Double = -64.0, safetyExpand: Double = 0.0): Boolean {
    val offsetBb = boundingBox.offset(pos - this.pos)

    if (pos.y < voidLevel || offsetBb.minY < voidLevel) {
        return true
    }

    // If there is no collision to void threshold, we do not want to teleport down.
    val boundingBox = offsetBb
        // Set the minimum Y to the void threshold to check for collisions below the player
        .withMinY(voidLevel)
        // Expand the bounding box to check if there might blocks to safely land on
        .expand(safetyExpand, 0.0, safetyExpand)
    return world.getBlockCollisions(this, boundingBox)
        .all(Shapes.empty()::equals)
}


fun LocalPlayer.warp(pos: Vec3? = null, onGround: Boolean = false) {
    val vehicle = this.vehicle

    if (vehicle != null) {
        pos?.let { vehicle.setPos(it.x, it.y, it.z) }
        network.send(ServerboundMoveVehiclePacket.fromEntity(vehicle))
        return
    }

    if (pos != null) {
        network.send(ServerboundMovePlayerPacket.Pos(pos.x, pos.y, pos.z, onGround, horizontalCollision))
    } else {
        network.send(ServerboundMovePlayerPacket.StatusOnly(onGround, horizontalCollision))
    }
}

fun LocalPlayer.isInHole(feetBlockPos: BlockPos = getFeetBlockPos()): Boolean {
    return DIRECTIONS_EXCLUDING_UP.all {
        feetBlockPos.relative(it).isBlastResistant()
    }
}

fun LocalPlayer.isBurrowed(): Boolean {
    return getFeetBlockPos().isBlastResistant()
}

fun LocalPlayer.getFeetBlockPos(): BlockPos {
    val bb = boundingBox
    return BlockPos(
        Mth.floor(Mth.lerp(0.5, bb.minX, bb.maxX)),
        Mth.ceil(bb.minY),
        Mth.floor(Mth.lerp(0.5, bb.minZ, bb.maxZ))
    )
}
