package net.ccbluex.liquidbounce.utils.aiming

import java.nio.file.Files
import java.nio.file.Path
import net.ccbluex.liquidbounce.features.module.modules.combat.isPeakAssistAimApplied
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.LinearVCorePitchState
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.VCORE_ELYTRA_AIMED_PITCH_STEP
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.VCORE_ELYTRA_MAX_PITCH_STEP
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.VCORE_ELYTRA_PITCH_ACCELERATE
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.calculateLinearRotation
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.preserveLinearYawWithVCorePitch
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.resolveVCoreTrackPitchStep
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.shouldUseVCoreElytraPitch
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.updateVCoreTrackPitchAcceleration
import net.ccbluex.liquidbounce.utils.aiming.features.processors.anglesmooth.impl.usesVCoreFastElytraPitch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LinearElytraPitchTest {

    @Test
    fun ordinaryLinearMathRemainsExactForRepresentativeDifferences() {
        val cases = listOf(
            LinearCase(Rotation(0.0f, 0.0f), Rotation(90.0f, 0.0f), 35.0f, 20.0f),
            LinearCase(Rotation(10.0f, -20.0f), Rotation(10.0f, 65.0f), 40.0f, 15.0f),
            LinearCase(Rotation(-150.0f, 35.0f), Rotation(140.0f, -55.0f), 72.0f, 31.0f),
            LinearCase(Rotation(2.0f, 3.0f), Rotation(2.5f, 3.25f), 0.2f, 0.1f),
            LinearCase(Rotation(179.0f, 80.0f), Rotation(-179.0f, -80.0f), 180.0f, 180.0f),
        )

        for (case in cases) {
            assertEquals(
                case.current.towardsLinear(case.target, case.horizontal, case.vertical),
                calculateLinearRotation(case.current, case.target, case.horizontal, case.vertical),
            )
        }
    }

    @Test
    fun specializedPitchAlwaysPreservesTheAlreadyCalculatedLinearYaw() {
        val cases = listOf(
            LinearCase(Rotation(0.0f, 0.0f), Rotation(120.0f, 70.0f), 15.0f, 3.0f),
            LinearCase(Rotation(90.0f, -45.0f), Rotation(-120.0f, 80.0f), 70.0f, 25.0f),
            LinearCase(Rotation(-179.0f, 10.0f), Rotation(179.0f, -75.0f), 1.0f, 60.0f),
        )

        for (case in cases) {
            val oldLinear = calculateLinearRotation(case.current, case.target, case.horizontal, case.vertical)
            val enhanced = preserveLinearYawWithVCorePitch(oldLinear, case.current, case.target, pitchStep = 8.0f)

            assertEquals(oldLinear.yaw, enhanced.yaw, 0.0f)
            assertFalse(enhanced.isNormalized)
        }
    }

    @Test
    fun sourcePitchAccelerationOvershootsOnceAndRaytraceResetsIt() {
        var acceleration = VCORE_ELYTRA_AIMED_PITCH_STEP
        val sequence = mutableListOf(acceleration)
        repeat(6) {
            acceleration = updateVCoreTrackPitchAcceleration(
                acceleration,
                alreadyRaytracing = false,
                aimedPitchStep = VCORE_ELYTRA_AIMED_PITCH_STEP,
                maxPitchStep = VCORE_ELYTRA_MAX_PITCH_STEP,
                pitchAccelerate = VCORE_ELYTRA_PITCH_ACCELERATE,
            )
            sequence += acceleration
        }

        val expected = listOf(1.0f, 1.65f, 2.7225f, 4.492125f, 7.412006f, 12.22981f, 8.0f)
        expected.zip(sequence).forEach { (expectedValue, actualValue) ->
            assertEquals(expectedValue, actualValue, 0.00001f)
        }
        assertEquals(
            1.0f,
            updateVCoreTrackPitchAcceleration(12.22981f, true, 1.0f, 8.0f, 1.65f),
        )
    }

    @Test
    fun fastPitchUsesOnlyTheLocalPlayerFlightStateAndTicks() {
        assertFalse(usesVCoreFastElytraPitch(false, 100))
        for (ticks in 1..5) {
            assertFalse(usesVCoreFastElytraPitch(true, ticks))
        }
        assertTrue(usesVCoreFastElytraPitch(true, 6))
        assertTrue(usesVCoreFastElytraPitch(true, 60))

        assertEquals(180.0f, resolveVCoreTrackPitchStep(1.0f, -1.0f, true, 6))
        assertEquals(0.0f, resolveVCoreTrackPitchStep(1.0f, -1.0f, true, 5))
    }

    @Test
    fun specializedContextRequiresSelectedLinearKillAuraOwnershipAndExactValidTarget() {
        fun active(
            linear: Boolean = true,
            owner: Boolean = true,
            current: Boolean = true,
            valid: Boolean = true,
            elytra: Boolean = false,
            peak: Boolean = false,
        ) = shouldUseVCoreElytraPitch(linear, owner, current, valid, elytra, peak)

        assertTrue(active(elytra = true))
        assertTrue(active(peak = true))
        assertTrue(active(elytra = true, peak = true))
        assertFalse(active())
        assertFalse(active(linear = false, elytra = true))
        assertFalse(active(owner = false, elytra = true))
        assertFalse(active(current = false, elytra = true))
        assertFalse(active(valid = false, elytra = true))
        assertFalse(active(owner = false, peak = true))
    }

    @Test
    fun peakAssistActivationRequiresEveryExistingEligibilityCondition() {
        assertTrue(isPeakAssistAimApplied(true, true, true, true))
        assertFalse(isPeakAssistAimApplied(false, true, true, true))
        assertFalse(isPeakAssistAimApplied(true, false, true, true))
        assertFalse(isPeakAssistAimApplied(true, true, false, true))
        assertFalse(isPeakAssistAimApplied(true, true, true, false))
    }

    @Test
    fun targetPlayerWorldAndExplicitLifecycleTransitionsResetAcceleration() {
        val state = LinearVCorePitchState()
        val targetA = Any()
        val targetB = Any()
        val playerA = Any()
        val playerB = Any()
        val worldA = Any()
        val worldB = Any()

        state.enter(targetA, playerA, worldA)
        state.updatePitchAcceleration(12.22981f)
        state.enter(targetA, playerA, worldA)
        assertEquals(12.22981f, state.pitchAcceleration)

        state.enter(targetB, playerA, worldA)
        assertEquals(1.0f, state.pitchAcceleration)
        state.updatePitchAcceleration(7.412006f)
        state.enter(targetB, playerB, worldA)
        assertEquals(1.0f, state.pitchAcceleration)
        state.updatePitchAcceleration(4.492125f)
        state.enter(targetB, playerB, worldB)
        assertEquals(1.0f, state.pitchAcceleration)
        state.updatePitchAcceleration(2.7225f)
        state.reset()
        assertEquals(1.0f, state.pitchAcceleration)
    }

    @Test
    fun independentPitchClampStaysFiniteAndWithinMinecraftBounds() {
        val cases = listOf(
            PitchCase(89.0f, 90.0f, 12.22981f),
            PitchCase(-89.0f, -90.0f, 12.22981f),
            PitchCase(80.0f, -90.0f, 180.0f),
            PitchCase(-80.0f, 90.0f, 180.0f),
            PitchCase(0.0f, 90.0f, 0.0f),
            PitchCase(0.0f, -90.0f, Float.NaN),
            PitchCase(Float.NaN, 90.0f, 8.0f),
        )

        for (case in cases) {
            val result = preserveLinearYawWithVCorePitch(
                linearRotation = Rotation(37.0f, 12.0f),
                currentRotation = Rotation(0.0f, case.currentPitch),
                targetRotation = Rotation(0.0f, case.targetPitch),
                pitchStep = case.step,
            )
            assertTrue(result.pitch.isFinite())
            assertTrue(result.pitch in -90.0f..90.0f)
            assertEquals(37.0f, result.yaw)
        }

        assertEquals(0.0f, resolveVCoreTrackPitchStep(1.0f, -1.0f, false, 0))
        assertEquals(2.0f, resolveVCoreTrackPitchStep(1.0f, 1.0f, false, 0))
        assertEquals(0.0f, resolveVCoreTrackPitchStep(Float.NaN, 0.0f, false, 0))
    }

    @Test
    fun staticArchitectureKeepsLinearSelectedAndAvoidsForbiddenTrackDelegation() {
        val linear = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/LinearAngleSmooth.kt")
        val rotations = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraRotationsConfigurable.kt")

        assertFalse(linear.contains("TrackAngleSmooth("))
        assertFalse(linear.contains("TrackAngleSmooth.process"))
        assertFalse(linear.contains("ElytraTracking"))
        assertFalse(linear.contains("boolean(\""))
        assertFalse(linear.contains("enumChoice(\""))
        assertFalse(Regex("""KillAuraRotationsConfigurable\.activeAngleSmooth\s*=(?!=)""").containsMatchIn(linear))
        assertFalse(linear.contains("getElytraTargetVec"))
        assertFalse(linear.contains("applyPeakAssistAimOffset"))
        assertFalse(linear.contains("ModuleWallsBypass"))
        assertFalse(linear.contains("YawOffset"))
        assertFalse(linear.contains("PeekHigh"))
        assertFalse(linear.contains("MinYawStep"))
        assertFalse(linear.contains("MaxYawStep"))
        assertFalse(linear.contains("AccelerateOnHit"))
        assertFalse(linear.contains("isNormalized = true"))
        assertFalse(linear.contains("options.sensitivity"))
        assertFalse(linear.contains("override fun calculateTicks"))
        assertTrue(linear.contains("FactorAngleSmooth(\"Linear\", parent)"))
        assertTrue(linear.contains("activeAngleSmooth === this"))
        assertTrue(rotations.contains("get() = activeAngleSmooth is TrackAngleSmooth"))
    }

    private fun source(path: String) = Files.readString(Path.of(path))

    private data class LinearCase(
        val current: Rotation,
        val target: Rotation,
        val horizontal: Float,
        val vertical: Float,
    )

    private data class PitchCase(val currentPitch: Float, val targetPitch: Float, val step: Float)
}
