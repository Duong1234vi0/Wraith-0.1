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
package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleWallsBypass
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraRotationsConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.minecraft.util.Mth
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max

/** V-Core Aura Track rotation mapped onto Wraith's angle-smooth processor stage. */
class TrackAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("Track", parent) {

    private val minYawStep by int("MinYawStep", 65, 1..180)
    private val maxYawStep by int("MaxYawStep", 75, 1..180)
    private val aimedPitchStep by float("AimedPitchStep", 1.0f, 0.0f..90.0f)
    private val maxPitchStep by float("MaxPitchStep", 8.0f, 1.0f..90.0f)
    private val pitchAccelerate by float("PitchAccelerate", 1.65f, 1.0f..10.0f)
    private val accelerateOnHit by enumChoice("AccelerateOnHit", AccelerateOnHit.OFF)

    private var pitchAcceleration = 1.0f

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation {
        val entity = rotationTarget.entity
        val ready = entity != null && ModuleKillAura.isTrackAttackReady(entity)
        val alreadyRaytracing = entity != null &&
            ModuleKillAura.isVCorePitchRaytracingTarget(entity, currentRotation)

        pitchAcceleration = updateVCoreTrackPitchAcceleration(
            pitchAcceleration = pitchAcceleration,
            alreadyRaytracing = alreadyRaytracing,
            aimedPitchStep = aimedPitchStep,
            maxPitchStep = maxPitchStep,
            pitchAccelerate = pitchAccelerate,
        )

        var yawStep = (minYawStep.toFloat()..maxYawStep.toFloat()).random()
        var pitchStep = if (player.isFallFlying && player.fallFlyingTicks > 5) {
            180.0f
        } else {
            pitchAcceleration + (-1.0f..1.0f).random()
        }

        if (ready) {
            when (accelerateOnHit) {
                AccelerateOnHit.OFF -> Unit
                AccelerateOnHit.YAW -> yawStep = 180.0f
                AccelerateOnHit.PITCH -> pitchStep = 90.0f
                AccelerateOnHit.BOTH -> {
                    yawStep = 180.0f
                    pitchStep = 90.0f
                }
            }
        }

        var deltaYawRaw = Mth.wrapDegrees(targetRotation.yaw - currentRotation.yaw)
        if (entity != null && !ready && KillAuraRotationsConfigurable.aimThroughWalls &&
            ModuleWallsBypass.running && ModuleWallsBypass.isYawOffset() && !player.canSee(entity)) {
            deltaYawRaw += 20.0f
        }
        if (deltaYawRaw > 180.0f) {
            deltaYawRaw -= 180.0f
        }

        val deltaYaw = Mth.clamp(abs(deltaYawRaw), -yawStep, yawStep)
        val deltaPitch = targetRotation.pitch - currentRotation.pitch
        val clampedPitch = Mth.clamp(deltaPitch, -pitchStep, pitchStep)
        val newYaw = currentRotation.yaw + if (deltaYawRaw > 0.0f) deltaYaw else -deltaYaw
        val newPitch = (currentRotation.pitch + clampedPitch).coerceIn(-90.0f, 90.0f)
        val sensitivity = mc.options.sensitivity().get().toDouble()
        val gcd = Math.pow(sensitivity * 0.6 + 0.2, 3.0) * 1.2

        return Rotation(
            yaw = (newYaw - (newYaw - currentRotation.yaw) % gcd).toFloat(),
            pitch = (newPitch - (newPitch - currentRotation.pitch) % gcd).toFloat(),
            isNormalized = true,
        )
    }

    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int {
        if (currentRotation.approximatelyEquals(targetRotation)) {
            return 0
        }

        val delta = currentRotation.rotationDeltaTo(targetRotation)
        val yawTicks = ceil(abs(delta.deltaYaw) / minYawStep.coerceAtLeast(1)).toInt()
        val simulatedPitchStep = if (player.isFallFlying && player.fallFlyingTicks > 5) 180.0f else maxPitchStep
        val pitchTicks = ceil(abs(delta.deltaPitch) / simulatedPitchStep.coerceAtLeast(1.0f)).toInt()
        return max(yawTicks, pitchTicks)
    }

    private enum class AccelerateOnHit(override val choiceName: String) : NamedChoice {
        OFF("Off"),
        YAW("Yaw"),
        PITCH("Pitch"),
        BOTH("Both"),
    }
}
