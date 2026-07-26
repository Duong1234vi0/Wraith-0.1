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

internal const val VCORE_ELYTRA_AIMED_PITCH_STEP = 1.0f
internal const val VCORE_ELYTRA_MAX_PITCH_STEP = 8.0f
internal const val VCORE_ELYTRA_PITCH_ACCELERATE = 1.65f
internal const val VCORE_ELYTRA_FAST_PITCH_STEP = 180.0f

internal fun updateVCoreTrackPitchAcceleration(
    pitchAcceleration: Float,
    alreadyRaytracing: Boolean,
    aimedPitchStep: Float,
    maxPitchStep: Float,
    pitchAccelerate: Float,
): Float = if (alreadyRaytracing) {
    aimedPitchStep
} else if (pitchAcceleration < maxPitchStep) {
    // V-Core branches on the old value and deliberately allows this multiplication to overshoot once.
    pitchAcceleration * pitchAccelerate
} else {
    maxPitchStep
}

internal fun usesVCoreFastElytraPitch(localPlayerFallFlying: Boolean, localFallFlyingTicks: Int): Boolean =
    localPlayerFallFlying && localFallFlyingTicks > 5

internal fun resolveVCoreTrackPitchStep(
    pitchAcceleration: Float,
    pitchJitter: Float,
    localPlayerFallFlying: Boolean,
    localFallFlyingTicks: Int,
): Float {
    val step = if (usesVCoreFastElytraPitch(localPlayerFallFlying, localFallFlyingTicks)) {
        VCORE_ELYTRA_FAST_PITCH_STEP
    } else {
        pitchAcceleration + pitchJitter
    }

    return step.takeIf(Float::isFinite)?.coerceAtLeast(0.0f) ?: 0.0f
}
