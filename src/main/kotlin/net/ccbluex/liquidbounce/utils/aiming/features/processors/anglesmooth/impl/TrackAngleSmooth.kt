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
package net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.FactorAngleSmooth
import net.ccbluex.liquidbounce.utils.aiming.utils.facingEnemy
import net.ccbluex.liquidbounce.utils.kotlin.random
import kotlin.math.max

/**
 * #Edit By Claude: Port of VCore's "Track" rotation
 * (vcore/features/modules/combat/aura/rotation/Track.java).
 *
 * Track is a stepped tracker: yaw turns by a random degrees-per-tick step within
 * [[minYawStep], [maxYawStep]], while pitch *accelerates* — small step once it is already looking at the
 * target, otherwise growing by [pitchAccelerate] each tick up to [maxPitchStep]. This maps directly onto
 * the fork's [FactorAngleSmooth] (max degrees/tick per axis, clamped by [Rotation.towardsLinear]); GCD /
 * mouse-sensitivity fixing is applied afterward by the engine via [Rotation.normalize], so it is NOT
 * reimplemented here.
 */
class TrackAngleSmooth(parent: ChoiceConfigurable<*>) : FactorAngleSmooth("Track", parent) {

    private val minYawStep by int("MinYawStep", 65, 1..180)
    private val maxYawStep by int("MaxYawStep", 75, 1..180)
    private val aimedPitchStep by float("AimedPitchStep", 1.0f, 0.0f..90f)
    private val maxPitchStep by float("MaxPitchStep", 8.0f, 1.0f..90f)
    private val pitchAccelerate by float("PitchAccelerate", 1.65f, 1.0f..10f)

    /**
     * Accumulated pitch acceleration carried across ticks (VCore's `aura.pitchAcceleration`). Only mutated
     * on the live rotation path (`rotationTarget != null`); the [calculateTicks] simulation path uses a
     * local accumulator so it cannot corrupt this state or stall the simulation loop.
     */
    private var pitchAcceleration = 1.0f

    override fun calculateFactors(
        rotationTarget: RotationTarget?,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Pair<Float, Float> {
        // VCore: yaw step is a fresh random pick within the configured range each tick.
        val yawStep = (minYawStep.toFloat()..maxYawStep.toFloat()).random()

        // VCore Track.java:46-55 — when we're already looking at the target use the small aimed step,
        // otherwise accelerate the pitch step geometrically, capped at maxPitchStep.
        val entity = rotationTarget?.entity
        val lookingAtTarget = entity != null &&
            facingEnemy(entity, max(3.0, player.boxedDistanceTo(entity)), currentRotation)

        val pitchStep: Float
        if (rotationTarget != null) {
            pitchAcceleration = if (lookingAtTarget) {
                aimedPitchStep
            } else {
                (pitchAcceleration * pitchAccelerate).coerceAtMost(maxPitchStep)
            }

            // #Edit By Codex 2026-07-01: VCore Track.java snaps pitch after sustained elytra flight
            // (`ticksElytraFlying > 5 ? 180F : pitchAcceleration + random`), which keeps predicted
            // ElytraTarget points from lagging below/above fast targets.
            pitchStep = if (player.isGliding && player.fallFlyingTicks > 5) {
                180f
            } else {
                pitchAcceleration
            }
        } else {
            // Simulation path (calculateTicks): keep accel local so we never touch the live field.
            pitchStep = maxPitchStep
        }

        return yawStep to pitchStep
    }

}
