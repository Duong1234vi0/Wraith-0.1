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
package net.ccbluex.liquidbounce.utils.aiming.utils
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.math.sq
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.ClipContext
import kotlin.math.max

fun rayTraceCollidingBlocks(start: Vec3, end: Vec3): BlockHitResult? {
    val result = mc.level!!.raycast(
        ClipContext(
            start,
            end,
            ClipContext.Block.COLLIDER,
            ClipContext.Fluid.ANY,
            mc.player!!
        )
    )

    if (result == null || result.type != HitResult.Type.BLOCK) {
        return null
    }

    return result
}

fun raytraceEntity(
    range: Double,
    rotation: Rotation,
    filter: (Entity) -> Boolean,
): EntityHitResult? {
    val entity = mc.cameraEntity ?: return null

    val cameraVec = entity.eyePos
    val rotationVec = rotation.directionVector

    val vec3d3 = cameraVec.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range)
    val box = entity.boundingBox.stretch(rotationVec.multiply(range)).expand(1.0, 1.0, 1.0)

    val hitResult =
        ProjectileUtil.getEntityHitResult(
            entity,
            cameraVec,
            vec3d3,
            box,
            { !it.isSpectator && it.canHit() && filter(it) },
            range * range,
        )

    return hitResult
}

fun raytraceBlock(
    range: Double,
    rotation: Rotation = RotationManager.currentRotation ?: player.rotation,
    pos: BlockPos,
    state: BlockState,
): BlockHitResult? {
    val entity: Entity = mc.cameraEntity ?: return null

    val start = entity.eyePos
    val rotationVec = rotation.directionVector

    val end = start.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range)

    val player = mc.player ?: return null
    val level = mc.level ?: return null
    return state.getShape(level, pos, CollisionContext.of(player)).clip(start, end, pos)
}

fun raycast(
    rotation: Rotation = RotationManager.currentRotation ?: player.rotation,
    range: Double = max(player.blockInteractionRange(), player.entityInteractionRange()),
    includeFluids: Boolean = false,
    tickDelta: Float = 1f,
): BlockHitResult {
    return raycast(
        range = range,
        includeFluids = includeFluids,
        start = player.getCameraPosVec(tickDelta),
        direction = rotation.directionVector
    )
}

fun raycast(
    range: Double = max(player.blockInteractionRange(), player.entityInteractionRange()),
    includeFluids: Boolean = false,
    start: Vec3,
    direction: Vec3,
    entity: Entity = mc.cameraEntity!!,
): BlockHitResult {
    val end = start.add(direction.x * range, direction.y * range, direction.z * range)

    return world.raycast(
        ClipContext(
            start,
            end,
            ClipContext.Block.OUTLINE,
            if (includeFluids) ClipContext.Fluid.ANY else ClipContext.Fluid.NONE,
            entity,
        ),
    )
}

/**
 * Allows you to check if a point is behind a wall
 */
fun canSeePointFrom(
    eyes: Vec3,
    vec3: Vec3,
) = mc.world?.raycast(
    ClipContext(
        eyes, vec3, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, mc.player!!,
    ),
)?.type == HitResult.Type.MISS

/**
 * Allows you to check if your enemy is behind a wall
 */
fun facingEnemy(
    toEntity: Entity,
    range: Double,
    rotation: Rotation,
): Boolean {
    return raytraceEntity(range, rotation) { it == toEntity } != null
}

fun facingEnemy(
    fromEntity: Entity = mc.cameraEntity!!,
    toEntity: Entity,
    rotation: Rotation,
    range: Double,
    wallsRange: Double,
): Boolean {
    val cameraVec = fromEntity.eyePos
    val rotationVec = rotation.directionVector

    val rangeSquared = range.sq()
    val wallsRangeSquared = wallsRange.sq()

    val vec3d3 = cameraVec.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range)
    val box = fromEntity.boundingBox.stretch(rotationVec.multiply(range)).expand(1.0, 1.0, 1.0)

    val entityHitResult =
        ProjectileUtil.getEntityHitResult(
            fromEntity, cameraVec, vec3d3, box, { !it.isSpectator && it.canHit() && it == toEntity }, rangeSquared,
        ) ?: return false

    val distance = cameraVec.squaredDistanceTo(entityHitResult.pos)

    return distance <= rangeSquared && canSeePointFrom(cameraVec, entityHitResult.pos) || distance <= wallsRangeSquared
}

/**
 * Allows you to check if a point is behind a wall
 */
fun facingBlock(
    eyes: Vec3,
    vec3: Vec3,
    blockPos: BlockPos,
    expectedSide: Direction? = null,
    expectedMaxRange: Double? = null,
): Boolean {
    val searchedPos =
        mc.world?.raycast(
            ClipContext(
                eyes, vec3, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, mc.player!!,
            ),
        ) ?: return false

    if (searchedPos.type != HitResult.Type.BLOCK || (expectedSide != null && searchedPos.side != expectedSide)) {
        return false
    }
    if (expectedMaxRange != null && searchedPos.pos.squaredDistanceTo(eyes) > expectedMaxRange * expectedMaxRange) {
        return false
    }

    return searchedPos.blockPosition() == blockPos
}
