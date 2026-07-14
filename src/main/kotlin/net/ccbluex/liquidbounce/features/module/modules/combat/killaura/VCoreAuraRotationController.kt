/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.module.modules.combat.killaura

import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.VCoreAuraRotationsConfigurable.AccelerateOnHit
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.VCoreAuraRotationsConfigurable.WallsBypass
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

object VCoreAuraRotationController {

    private var mode = Mode.NONE
    private var targetId = Int.MIN_VALUE
    private var rotationYaw = Float.NaN
    private var rotationPitch = Float.NaN
    private var pitchAcceleration = 1.0f
    private var rotationPoint = Vec3.ZERO
    private var rotationMotion = Vec3.ZERO

    var lookingAtHitbox = false
        private set

    var target: Entity? = null
        private set

    fun reset() {
        mode = Mode.NONE
        targetId = Int.MIN_VALUE
        target = null
        lookingAtHitbox = false
        rotationYaw = Float.NaN
        rotationPitch = Float.NaN
        pitchAcceleration = 1.0f
        rotationPoint = Vec3.ZERO
        rotationMotion = Vec3.ZERO
    }

    fun clearTarget() {
        if (target != null || targetId != Int.MIN_VALUE) {
            reset()
        }
    }

    fun rotate(target: LivingEntity, ready: Boolean, attackRange: Float): VCoreRotationResult? {
        val selectedMode = selectedMode()
        if (selectedMode == Mode.NONE || target.isRemoved) {
            reset()
            return null
        }

        synchronizeState(selectedMode, target)
        val result = when (selectedMode) {
            Mode.TRACK -> rotateTrack(target, ready, attackRange)
            Mode.GRIM -> rotateGrim(target, ready, attackRange)
            Mode.NONE -> null
        } ?: return null

        if (!result.rotation.yaw.isFinite() || !result.rotation.pitch.isFinite()) {
            reset()
            return null
        }

        rotationYaw = result.rotation.yaw
        rotationPitch = result.rotation.pitch
        lookingAtHitbox = result.lookingAtHitbox
        return result
    }

    fun isInRange(target: LivingEntity, attackRange: Float): Boolean {
        val rotateDistance = rotateDistance(attackRange) + glidingTargetRotateExtension()
        if (ModuleElytraTarget.shouldTarget(target)) {
            val predicted = ModuleElytraTarget.getPredictedVec(target, applyPrediction = true) ?: return false
            return player.eyePos.distanceToSqr(predicted) <= rotateDistance * rotateDistance
        }

        val eyePoint = target.position().add(0.0, target.getEyeHeight(target.pose).toDouble(), 0.0)
        val squaredRotateDistance = rotateDistance * rotateDistance
        if (player.eyePos.distanceToSqr(eyePoint) > squaredRotateDistance + 4.0f) {
            return false
        }

        val halfBox = target.boundingBox.xsize.toFloat() / 2.0f
        var x = -halfBox
        while (x <= halfBox) {
            var z = -halfBox
            while (z <= halfBox) {
                var y = 0.05f
                while (y <= target.boundingBox.ysize) {
                    val point = Vec3(target.x + x, target.y + y, target.z + z)
                    if (player.eyePos.distanceToSqr(point) <= squaredRotateDistance) {
                        val rotation = angleTo(point)
                        if (checkRtx(rotation, rotateDistance, wallRange(attackRange), target)) {
                            return true
                        }
                    }
                    y += 0.25f
                }
                z += 0.15f
            }
            x += 0.15f
        }

        return false
    }

    fun checkRtx(
        rotation: Rotation,
        distance: Float,
        wallDistance: Float,
        target: Entity
    ): Boolean {
        if (!VCoreAuraRotationsConfigurable.rayTrace) {
            return true
        }

        if (!rotation.yaw.isFinite() || !rotation.pitch.isFinite() || distance < 0.0f) {
            return false
        }

        val start = player.eyePos
        val direction = rotation.directionVector
        if (!direction.x.isFinite() || !direction.y.isFinite() || !direction.z.isFinite()) {
            return false
        }

        val end = start.add(direction.scale(distance.toDouble()))
        val blockHit = world.clip(
            ClipContext(start, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)
        ).takeIf { it.type == HitResult.Type.BLOCK }
        var blockDistanceSquared = distance.toDouble().pow(2.0)
        if (blockHit != null) {
            blockDistanceSquared = start.distanceToSqr(blockHit.location)
        }

        val searchBox = player.boundingBox.expandTowards(direction.scale(distance.toDouble())).inflate(1.0)
        val wallDistanceSquared = wallDistance.toDouble().pow(2.0)
        val entityHit = ProjectileUtil.getEntityHitResult(
            player,
            start,
            end,
            searchBox,
            { !it.isSpectator && it.canHit() && it === target },
            kotlin.math.max(blockDistanceSquared, wallDistanceSquared)
        ) ?: return false

        val hitDistanceSquared = start.distanceToSqr(entityHit.location)
        val allowWallHit = blockHit == null || hitDistanceSquared <= wallDistanceSquared ||
            hitDistanceSquared < blockDistanceSquared
        return allowWallHit && hitDistanceSquared <= distance.toDouble().pow(2.0) && entityHit.entity === target
    }

    private fun rotateTrack(target: LivingEntity, ready: Boolean, attackRange: Float): VCoreRotationResult? {
        val aimPoint = when {
            !player.isGliding -> getDynamicLookPoint(target, attackRange)
            ModuleElytraTarget.shouldTarget(target) -> ModuleElytraTarget.getPredictedVec(target, applyPrediction = true)
            else -> target.eyePosition
        } ?: return null

        val aimDistance = rotateDistance(attackRange)
        pitchAcceleration = if (checkRtx(currentRotation(), aimDistance, aimDistance, target)) {
            VCoreAuraRotationsConfigurable.aimedPitchStep
        } else if (pitchAcceleration < VCoreAuraRotationsConfigurable.maxPitchStep) {
            pitchAcceleration * VCoreAuraRotationsConfigurable.pitchAccelerate
        } else {
            VCoreAuraRotationsConfigurable.maxPitchStep
        }

        var deltaYawRaw = Mth.wrapDegrees(
            Mth.wrapDegrees(Math.toDegrees(atan2(aimPoint.z - player.z, aimPoint.x - player.x)).toFloat() - 90.0f) -
                rotationYaw
        )
        if (VCoreAuraRotationsConfigurable.ignoreWalls && !ready && !player.hasLineOfSight(target) &&
            VCoreAuraRotationsConfigurable.wallsBypass == WallsBypass.YAW_OFFSET) {
            deltaYawRaw += 20.0f
        }

        val deltaPitch = (-Math.toDegrees(
            atan2(
                aimPoint.y - player.eyeY,
                sqrt((aimPoint.x - player.x).pow(2.0) + (aimPoint.z - player.z).pow(2.0))
            )
        ) - rotationPitch).toFloat()

        var yawStep = random(
            VCoreAuraRotationsConfigurable.minYawStep.toFloat(),
            VCoreAuraRotationsConfigurable.maxYawStep.toFloat()
        )
        var pitchStep = if (player.fallFlyingTicks > 5) 180.0f else pitchAcceleration + random(-1.0f, 1.0f)
        if (ready) {
            when (VCoreAuraRotationsConfigurable.accelerateOnHit) {
                AccelerateOnHit.OFF -> Unit
                AccelerateOnHit.YAW -> yawStep = 180.0f
                AccelerateOnHit.PITCH -> pitchStep = 90.0f
                AccelerateOnHit.BOTH -> {
                    yawStep = 180.0f
                    pitchStep = 90.0f
                }
            }
        }

        if (deltaYawRaw > 180.0f) {
            deltaYawRaw -= 180.0f
        }
        val deltaYaw = Mth.clamp(abs(deltaYawRaw), -yawStep, yawStep)
        val clampedPitch = Mth.clamp(deltaPitch, -pitchStep, pitchStep)
        val newYaw = rotationYaw + if (deltaYawRaw > 0.0f) deltaYaw else -deltaYaw
        val newPitch = Mth.clamp(rotationPitch + clampedPitch, -90.0f, 90.0f)
        val sensitivity = mc.options.sensitivity().get()
        val gcd = (sensitivity * 0.6 + 0.2).pow(3.0) * 1.2
        val finalRotation = Rotation(
            (newYaw - (newYaw - rotationYaw) % gcd).toFloat(),
            (newPitch - (newPitch - rotationPitch) % gcd).toFloat(),
            isNormalized = true
        )
        return resultFor(target, finalRotation, attackRange)
    }

    private fun getDynamicLookPoint(target: LivingEntity, attackRange: Float): Vec3 {
        val box = target.boundingBox
        val minMotionXZ = 0.003f
        val maxMotionXZ = 0.03f
        val minMotionY = 0.001f
        val maxMotionY = 0.03f
        if (rotationMotion == Vec3.ZERO) {
            rotationMotion = Vec3(random(-0.05, 0.05), random(-0.05, 0.05), random(-0.05, 0.05))
        }

        rotationPoint = rotationPoint.add(rotationMotion)
        if (rotationPoint.x >= (box.xsize - 0.05) / 2.0) {
            rotationMotion = Vec3(-random(minMotionXZ, maxMotionXZ).toDouble(), rotationMotion.y, rotationMotion.z)
        }
        if (rotationPoint.y >= box.ysize) {
            rotationMotion = Vec3(rotationMotion.x, -random(minMotionY, maxMotionY).toDouble(), rotationMotion.z)
        }
        if (rotationPoint.z >= (box.zsize - 0.05) / 2.0) {
            rotationMotion = Vec3(rotationMotion.x, rotationMotion.y, -random(minMotionXZ, maxMotionXZ).toDouble())
        }
        if (rotationPoint.x <= -(box.xsize - 0.05) / 2.0) {
            rotationMotion = Vec3(random(minMotionXZ, 0.03f).toDouble(), rotationMotion.y, rotationMotion.z)
        }
        if (rotationPoint.y <= 0.05) {
            rotationMotion = Vec3(rotationMotion.x, random(minMotionY, maxMotionY).toDouble(), rotationMotion.z)
        }
        if (rotationPoint.z <= -(box.zsize - 0.05) / 2.0) {
            rotationMotion = Vec3(rotationMotion.x, rotationMotion.y, random(minMotionXZ, maxMotionXZ).toDouble())
        }

        // V-Core calls immutable Vec3d.add without assigning the result here; preserve that no-op.
        rotationPoint.add(random(-0.03, 0.03), 0.0, random(-0.03, 0.03))
        if (VCoreAuraRotationsConfigurable.ignoreWalls && !player.hasLineOfSight(target) &&
            VCoreAuraRotationsConfigurable.wallsBypass == WallsBypass.PEEK_HIGH) {
            return target.position().add(random(-0.15, 0.15), box.ysize, random(-0.15, 0.15))
        }

        if (!checkRtx(currentRotation(), attackRange, wallRange(attackRange), target)) {
            val middle = target.position().add(0.0, target.getEyeHeight(target.pose) / 2.0, 0.0)
            val middleRotation = angleTo(middle)
            if (player.eyePos.distanceToSqr(middle) <= attackRange * attackRange &&
                checkRtx(middleRotation, attackRange, 0.0f, target)) {
                rotationPoint = Vec3(
                    random(-0.1, 0.1),
                    target.getEyeHeight(target.pose) / random(1.8, 2.5),
                    random(-0.1, 0.1)
                )
            } else {
                val halfBox = box.xsize.toFloat() / 2.0f
                var x = -halfBox
                while (x <= halfBox) {
                    var z = -halfBox
                    while (z <= halfBox) {
                        var y = 0.05f
                        while (y <= box.ysize) {
                            val point = Vec3(target.x + x, target.y + y, target.z + z)
                            if (player.eyePos.distanceToSqr(point) <= attackRange * attackRange &&
                                checkRtx(angleTo(point), attackRange, 0.0f, target)) {
                                rotationPoint = Vec3(x.toDouble(), y.toDouble(), z.toDouble())
                                break
                            }
                            y += 0.15f
                        }
                        z += 0.05f
                    }
                    x += 0.05f
                }
            }
        }

        return target.position().add(rotationPoint)
    }

    private fun rotateGrim(target: LivingEntity, ready: Boolean, attackRange: Float): VCoreRotationResult? {
        val canSeeTarget = player.hasLineOfSight(target)
        var aimPoint = if (ModuleElytraTarget.shouldTarget(target)) {
            ModuleElytraTarget.getPredictedVec(target, applyPrediction = true) ?: target.boundingBox.center
        } else {
            target.boundingBox.center
        }
        if (VCoreAuraRotationsConfigurable.ignoreWalls && !canSeeTarget &&
            VCoreAuraRotationsConfigurable.wallsBypass == WallsBypass.PEEK_HIGH) {
            aimPoint = target.position().add(random(-0.15, 0.15), target.boundingBox.ysize, random(-0.15, 0.15))
        }
        val delta = aimPoint.subtract(player.eyePos)
        if (delta.lengthSqr() == 0.0 || !delta.lengthSqr().isFinite()) {
            return fallbackResult(target, attackRange)
        }
        val direction = delta.normalize()
        var shortestYawPath = (((Math.toDegrees(atan2(direction.z, direction.x)) - 90.0 - rotationYaw) % 360.0 +
            540.0) % 360.0 - 180.0).toFloat()
        if (VCoreAuraRotationsConfigurable.ignoreWalls && !ready && !canSeeTarget &&
            VCoreAuraRotationsConfigurable.wallsBypass == WallsBypass.YAW_OFFSET) {
            shortestYawPath += 20.0f
        }
        val findPitch = min(90.0, -Math.toDegrees(atan2(direction.y, hypot(direction.x, direction.z)))).toFloat()
        var targetYaw = rotationYaw + shortestYawPath + random(-1.0f, 1.0f)
        var targetPitch = Mth.clamp(findPitch, -90.0f, 90.0f) + random(-1.0f, 1.0f)
        if ((targetYaw == -90.0f && targetPitch == 90.0f) || targetYaw == -180.0f) {
            targetYaw = player.yRot
            targetPitch = player.xRot
        } else {
            val sensitivity = mc.options.sensitivity().get()
            val value = sensitivity * 0.6 + 0.2
            val gcd = value.pow(1.5) * 0.8 * 0.15
            targetYaw = (targetYaw - targetYaw % gcd).toFloat()
            targetPitch = (targetPitch - targetPitch % gcd).toFloat()
        }

        val rotation = Rotation(targetYaw, targetPitch, isNormalized = true)
        if (!targetYaw.isFinite() || !targetPitch.isFinite()) {
            return fallbackResult(target, attackRange)
        }
        return resultFor(target, rotation, attackRange)
    }

    private fun fallbackResult(target: LivingEntity, attackRange: Float): VCoreRotationResult {
        val rotation = Rotation(player.yRot, player.xRot, isNormalized = true)
        return resultFor(target, rotation, attackRange)
    }

    private fun resultFor(target: LivingEntity, rotation: Rotation, attackRange: Float): VCoreRotationResult {
        val looking = if (ModuleElytraTarget.shouldTarget(target)) {
            ModuleElytraTarget.canAttack(target)
        } else {
            checkRtx(rotation, attackRange, wallRange(attackRange), target)
        }
        return VCoreRotationResult(rotation, looking)
    }

    private fun synchronizeState(selectedMode: Mode, target: LivingEntity) {
        val stateInvalid = !rotationYaw.isFinite() || !rotationPitch.isFinite()
        if (mode != selectedMode || targetId != target.id || this.target !== target || stateInvalid) {
            mode = selectedMode
            targetId = target.id
            this.target = target
            val initial = RotationManager.currentRotation ?: player.rotation
            rotationYaw = initial.yaw.takeIf(Float::isFinite) ?: player.yRot
            rotationPitch = initial.pitch.takeIf(Float::isFinite) ?: player.xRot
            pitchAcceleration = 1.0f
            rotationPoint = Vec3.ZERO
            rotationMotion = Vec3.ZERO
            lookingAtHitbox = false
        }
    }

    private fun currentRotation() = Rotation(rotationYaw, rotationPitch, isNormalized = true)

    private fun selectedMode() = when {
        KillAuraRotationsConfigurable.isVCoreTrack -> Mode.TRACK
        KillAuraRotationsConfigurable.isVCoreGrim -> Mode.GRIM
        else -> Mode.NONE
    }

    private fun rotateDistance(attackRange: Float) = attackRange + ModuleKillAura.activeVCoreAimRange

    private fun wallRange(attackRange: Float) = if (VCoreAuraRotationsConfigurable.ignoreWalls) {
        rotateDistance(attackRange)
    } else {
        0.0f
    }

    private fun glidingTargetRotateExtension(): Float {
        // V-Core adds four rotate-distance blocks only while gliding and Aura already has a target.
        // This fork maps that state to KillAura's current target; no ElytraPlus branch is invented.
        return if (player.isGliding && ModuleKillAura.targetTracker.target != null) {
            4.0f
        } else {
            0.0f
        }
    }

    private fun angleTo(point: Vec3): Rotation = Rotation.lookingAt(point, player.eyePos)

    private fun random(min: Float, max: Float): Float = min + Math.random().toFloat() * (max - min)

    private fun random(min: Double, max: Double): Double = ThreadLocalRandom.current().nextDouble() * (max - min) + min

    private enum class Mode {
        NONE,
        TRACK,
        GRIM
    }
}

data class VCoreRotationResult(
    val rotation: Rotation,
    val lookingAtHitbox: Boolean
)
