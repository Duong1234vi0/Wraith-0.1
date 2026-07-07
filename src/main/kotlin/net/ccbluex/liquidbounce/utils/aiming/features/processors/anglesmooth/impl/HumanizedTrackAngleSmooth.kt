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
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.AngleSmooth
import net.ccbluex.liquidbounce.utils.aiming.utils.facingEnemy
import net.ccbluex.liquidbounce.utils.kotlin.random
import java.util.Random
import kotlin.math.abs
import kotlin.math.max

/**
 * #Edit By Claude: Humanized variant of [TrackAngleSmooth].
 *
 * Track (VCore port) at high settings reads as a bot in recorded data: per-tick yaw acceleration
 * std ~4-5x a human, direction-change rate ~2x, almost no pauses/micro-moves, and aim that sits
 * too perfectly on target. This mode keeps Track's stepped-tracker feel but pulls those metrics
 * back toward a hand-recorded human baseline (bucket 1.2-3 blocks, where our dataset is densest):
 *
 *  1. Lower + smoothed yaw step  -> cuts accel_std and direction-change.
 *  2. Yaw momentum (EMA)         -> consecutive ticks correlate, fewer random sign flips.
 *  3. Pause / micro-step window  -> only when already near the target, raising zeroDelta/microMove
 *                                   the way a human hesitates; never pauses while far off-aim.
 *  4. Aim imperfection           -> small jitter added to the target so we don't bind perfectly,
 *                                   producing a residual aim error closer to human.
 *  5. Distance-aware hook        -> tuned for 1.2-3 now; other buckets reuse the same factors until
 *                                   we have enough data to split them.
 *
 * Does NOT touch [TrackAngleSmooth]; this is a separate AngleSmooth choice. GCD / mouse-sensitivity
 * is still applied afterward by the engine via [Rotation.normalize], so it is NOT reimplemented here.
 */
class HumanizedTrackAngleSmooth(parent: ChoiceConfigurable<*>) : AngleSmooth("HumanizedTrack", parent) {

    // Conservative defaults: well below Track's 65..75 yaw / 8.0 pitch "max" feel.
    private val minYawStep by int("MinYawStep", 18, 1..180)
    private val maxYawStep by int("MaxYawStep", 35, 1..180)
    private val yawSmoothing by float("YawSmoothing", 0.5f, 0.0f..0.95f)

    private val aimedPitchStep by float("AimedPitchStep", 1.0f, 0.0f..90f)
    // Iter3: cap raised 5.0 -> 6.5. V2 pitch on jump-crit under-reacted (|step| 0.94 vs human 1.37);
    // the ramp was capping out too low while pitch was unaligned during vertical movement.
    private val maxPitchStep by float("MaxPitchStep", 6.5f, 1.0f..90f)
    private val pitchAccelerate by float("PitchAccelerate", 1.3f, 1.0f..10f)

    // Pause / micro behaviour. Only allowed once aim is within [aimWindow] of the target.
    private val pauseChance by int("PauseChance", 18, 0..100)
    private val aimWindow by float("AimWindow", 6.0f, 0.5f..45f)
    private val maxPauseTicks by int("MaxPauseTicks", 2, 1..6)

    // Residual aim error: small jitter so we don't sit perfectly on target.
    private val aimImperfection by float("AimImperfection", 2.5f, 0.0f..10f)

    // Distance-aware close-range control. V1 flailed at <1.2 blocks (mean |yaw step| 13.5 deg, 47%
    // of ticks >15 deg, residual yaw 52 deg) because the yaw factor was distance-blind. V2 scaled the
    // close-range yaw cap by a FLAT 0.35 and over-corrected: yaw std dropped to human level but the
    // aim could no longer keep up with a near (fast-moving) target -> residual yaw blew up to 97 deg
    // and the pause window never fired. Iter3 makes the scale DYNAMIC: only squeeze yaw once we are
    // already near the target on yaw; while still far off, aim at near-full speed so we converge.
    private val closeRange by float("CloseRange", 1.4f, 0.5f..3f)
    // Scale applied when we are ALREADY aligned on yaw (close + small residual) -> keeps it smooth.
    private val closeRangeYawScale by float("CloseRangeYawScale", 0.65f, 0.05f..1f)
    // Yaw residual (deg) under which the close-range squeeze ramps in. Above this we use ~full speed.
    private val closeRangeYawConverge by float("CloseRangeConverge", 22f, 1f..90f)
    private val closeRangeAimWindow by float("CloseRangeAimWindow", 14f, 1f..45f)

    // --- cross-tick state (live path only) -------------------------------------------------------
    private var pitchAcceleration = 1.0f
    private var prevYawStep = 0f
    private var pauseTicks = 0
    private var lastTargetId = -1
    private val random = Random()

    override fun enable() {
        resetState()
        super.enable()
    }

    override fun disable() {
        resetState()
        super.disable()
    }

    private fun resetState() {
        pitchAcceleration = 1.0f
        prevYawStep = 0f
        pauseTicks = 0
        lastTargetId = -1
    }

    override fun process(
        rotationTarget: RotationTarget,
        currentRotation: Rotation,
        targetRotation: Rotation
    ): Rotation {
        val entity = rotationTarget.entity

        // Reset accumulated state whenever the target changes, so stale momentum/pause from the
        // previous target can't yank the rotation on the first tick of a new one.
        val targetId = entity?.id ?: -1
        if (targetId != lastTargetId) {
            pitchAcceleration = 1.0f
            prevYawStep = 0f
            pauseTicks = 0
            lastTargetId = targetId
        }

        val totalDelta = currentRotation.rotationDeltaTo(targetRotation)
        val remaining = totalDelta.length()
        val yawResidual = abs(totalDelta.deltaYaw)

        // Distance-aware scaling. Closer than [closeRange] we still want a smoother yaw, BUT the V2
        // flat 0.35 squeeze stopped us converging (residual yaw 97 deg). So the squeeze is DYNAMIC:
        // - yaw still far off (residual >= closeRangeYawConverge) -> ~full speed, so we catch up.
        // - yaw nearly aligned (residual -> 0)                    -> closeRangeYawScale, so it's calm.
        // Linear ramp between the two. Far range (>= closeRange) is unaffected (scale 1).
        val distance = if (entity != null) player.boxedDistanceTo(entity).toFloat() else Float.MAX_VALUE
        val isClose = distance < closeRange
        val yawScale = if (isClose) {
            val t = (yawResidual / closeRangeYawConverge).coerceIn(0f, 1f)
            closeRangeYawScale + (1f - closeRangeYawScale) * t
        } else {
            1f
        }
        val effAimWindow = if (isClose) closeRangeAimWindow else aimWindow

        // --- pause window: only hesitate when we are GENUINELY near the target on BOTH the overall
        // delta AND yaw specifically. Gating on yawResidual too prevents pausing while yaw is still
        // far off (the V2 bug where pause/micro never produced human-like frames because we paused
        // by overall-delta while yaw was nowhere near aligned). -------------------------------------
        val nearTarget = remaining <= effAimWindow && yawResidual <= effAimWindow
        if (pauseTicks > 0) {
            pauseTicks--
            return currentRotation
        }
        if (nearTarget && pauseChance > 0 && random.nextInt(100) < pauseChance) {
            // Hold for 1..maxPauseTicks ticks -> produces human-like zeroDelta / micro-move frames.
            pauseTicks = 1 + random.nextInt(max(1, maxPauseTicks))
            return currentRotation
        }

        // --- yaw: random pick within a narrow range, smoothed against the previous tick ----------
        val rawYawStep = (minYawStep.toFloat()..maxYawStep.toFloat()).random() * yawScale
        val yawStep = if (prevYawStep <= 0f) {
            rawYawStep
        } else {
            // EMA: high smoothing -> consecutive steps correlate -> lower accel_std & dir-change.
            prevYawStep * yawSmoothing + rawYawStep * (1f - yawSmoothing)
        }
        prevYawStep = yawStep

        // --- pitch: accelerate like Track but with a lower cap -----------------------------------
        // Only collapse to the small aimedPitchStep when we are BOTH looking at the target AND already
        // close on pitch. Recorded data showed pitch froze at aimedPitchStep close-range (facingEnemy
        // almost always true), so it under-reacted to jump-crit vertical movement (human pitch|step|
        // 1.37 while jumping vs aura 0.83). Keeping the ramp while pitch delta is large lets it track.
        val pitchAligned = abs(totalDelta.deltaPitch) <= aimWindow
        val lookingAtTarget = entity != null &&
            facingEnemy(entity, max(3.0, player.boxedDistanceTo(entity)), currentRotation)
        pitchAcceleration = if (lookingAtTarget && pitchAligned) {
            aimedPitchStep
        } else {
            (pitchAcceleration * pitchAccelerate).coerceAtMost(maxPitchStep)
        }
        val pitchStep = pitchAcceleration

        // --- aim imperfection: jitter the target so we don't bind perfectly ----------------------
        // Gaussian noise, but CLAMPED to +/- aimImperfection and never larger than the remaining
        // delta on that axis -> we can't be pushed further from the target than we already are, so
        // the aim point stays inside the target/hitbox window. Scaled down very close to avoid
        // oscillating on top of the target.
        val aimTarget = if (aimImperfection > 0f && remaining > aimWindow * 0.5f) {
            val yawNoise = clampNoise(random.nextGaussian().toFloat() * aimImperfection,
                aimImperfection, abs(totalDelta.deltaYaw))
            val pitchNoise = clampNoise(random.nextGaussian().toFloat() * aimImperfection * 0.5f,
                aimImperfection * 0.5f, abs(totalDelta.deltaPitch))
            Rotation(
                targetRotation.yaw + yawNoise,
                (targetRotation.pitch + pitchNoise).coerceIn(-90f, 90f)
            )
        } else {
            targetRotation
        }

        return currentRotation.towardsLinear(aimTarget, yawStep, pitchStep)
    }

    /**
     * Clamp gaussian noise to +/- [limit] and never beyond [remaining] (the current per-axis delta
     * to the target). This keeps the jittered aim point from ever pointing further from the target
     * than we already are, so it stays within the target/hitbox window.
     */
    private fun clampNoise(noise: Float, limit: Float, remaining: Float): Float {
        val bound = minOf(abs(limit), abs(remaining))
        return noise.coerceIn(-bound, bound)
    }

    override fun calculateTicks(currentRotation: Rotation, targetRotation: Rotation): Int {
        // Simulation path: no state mutation, no pause, no imperfection. Mirrors FactorAngleSmooth.
        var rotation = currentRotation
        var ticks = -1

        do {
            val yawStep = (minYawStep.toFloat()..maxYawStep.toFloat()).random()
            rotation = rotation.towardsLinear(targetRotation, yawStep, maxPitchStep)
            ticks++
        } while (!rotation.approximatelyEquals(targetRotation) && ticks < 80)

        return ticks
    }

}
