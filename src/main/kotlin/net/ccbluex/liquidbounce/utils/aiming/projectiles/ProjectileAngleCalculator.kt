package net.ccbluex.liquidbounce.utils.aiming.projectiles
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.entity.ConstantPositionExtrapolation
import net.ccbluex.liquidbounce.utils.entity.PositionExtrapolation
import net.ccbluex.liquidbounce.utils.render.trajectory.TrajectoryInfo
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3

/**
 * Calculates the shooting angle which hits the supplied target
 */
abstract class ProjectileAngleCalculator {
    abstract fun calculateAngleFor(
        projectileInfo: TrajectoryInfo,
        sourcePos: Vec3,
        targetPosFunction: PositionExtrapolation,
        targetShape: EntityDimensions,
    ): Rotation?

    fun calculateAngleForStaticTarget(
        projectileInfo: TrajectoryInfo,
        target: Vec3,
        shape: EntityDimensions
    ): Rotation? {
        return this.calculateAngleFor(
            projectileInfo,
            sourcePos = player.eyePos,
            targetPosFunction = ConstantPositionExtrapolation(target),
            targetShape = shape
        )
    }

    fun calculateAngleForEntity(projectileInfo: TrajectoryInfo, entity: LivingEntity): Rotation? {
        return this.calculateAngleFor(
            projectileInfo,
            sourcePos = player.eyePos,
            targetPosFunction = PositionExtrapolation.getBestForEntity(entity),
            targetShape = entity.dimensions
        )
    }
}
