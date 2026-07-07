package net.ccbluex.liquidbounce.utils.render.trajectory
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.toRadians
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import kotlin.math.cos
import kotlin.math.sin

class TrajectoryInfoRenderer(
    private val owner: Entity,
    private var velocity: Vec3,
    private var pos: Vec3,
    private val trajectoryInfo: TrajectoryInfo,
    /**
     * The visualization should be what-you-see-is-what-you-get, so we use the actual current position of the player
     * for simulation. Since the trajectory line should follow the player smoothly, we offset it by some amount.
     */
    private val renderOffset: Vec3
) {
    companion object {
        fun getHypotheticalTrajectory(
            entity: Entity,
            trajectoryInfo: TrajectoryInfo,
            rotation: Rotation,
            partialTicks: Float = mc.deltaTracker.getGameTimeDeltaPartialTick(true)
        ): TrajectoryInfoRenderer {
            val yawRadians = rotation.yaw / 180f * Math.PI.toFloat()
            val pitchRadians = rotation.pitch / 180f * Math.PI.toFloat()

            val interpolatedOffset = entity.interpolateCurrentPosition(partialTicks).subtract(entity.position())

            val pos = Vec3(
                entity.x,
                entity.eyeY - 0.10000000149011612,
                entity.z
            )

            var velocity = Vec3(
                -sin(yawRadians) * cos(pitchRadians).toDouble(),
                -sin((rotation.pitch + trajectoryInfo.roll).toRadians()).toDouble(),
                cos(yawRadians) * cos(pitchRadians).toDouble()
            ).normalize().scale(trajectoryInfo.initialVelocity)

            if (trajectoryInfo.copiesPlayerVelocity) {
                velocity = velocity.add(Vec3(
                    entity.deltaMovement.x,
                    if (entity.onGround()) 0.0 else entity.deltaMovement.y,
                    entity.deltaMovement.z
                ))
            }

            return TrajectoryInfoRenderer(
                owner = entity,
                velocity = velocity,
                pos = pos,
                trajectoryInfo = trajectoryInfo,
                renderOffset = interpolatedOffset.add(Vec3(-cos(yawRadians) * 0.16, 0.0, -sin(yawRadians) * 0.16))
            )
        }
    }

    fun drawTrajectoryForProjectile(
        maxTicks: Int,
        color: Color4b,
        matrixStack: PoseStack
    ): HitResult? {
        return runSimulation(maxTicks)
    }

    fun runSimulation(
        maxTicks: Int,
        outPositions: MutableList<Vec3> = mutableListOf(),
    ): HitResult? {
        for (ignored in 0 until maxTicks) {
            pos = pos.add(velocity)
            velocity = velocity.scale(trajectoryInfo.drag).subtract(0.0, trajectoryInfo.gravity, 0.0)
            outPositions += pos
        }

        return null
    }
}
