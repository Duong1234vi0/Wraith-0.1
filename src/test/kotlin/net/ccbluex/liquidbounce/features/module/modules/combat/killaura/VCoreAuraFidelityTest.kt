package net.ccbluex.liquidbounce.features.module.modules.combat.killaura

import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.minecraft.world.phys.Vec3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class VCoreAuraFidelityTest {

    private fun boundaryMotion(point: Vec3) = updateVCoreTrackBoundaryMotion(
        rotationPoint = point,
        rotationMotion = Vec3(0.005, 0.006, 0.007),
        lengthX = 0.6,
        lengthY = 1.8,
        lengthZ = 0.6,
        randomMotionXZ = { 0.02 },
        randomMotionY = { 0.01 },
    )

    @Test
    fun upperTrackXBoundaryAlwaysReversesNegative() {
        assertEquals(-0.02, boundaryMotion(Vec3(0.275, 0.5, 0.0)).x)
    }

    @Test
    fun upperTrackYBoundaryAlwaysReversesNegative() {
        assertEquals(-0.01, boundaryMotion(Vec3(0.0, 1.8, 0.0)).y)
    }

    @Test
    fun upperTrackZBoundaryAlwaysReversesNegative() {
        assertEquals(-0.02, boundaryMotion(Vec3(0.0, 0.5, 0.275)).z)
    }

    @Test
    fun lowerTrackXBoundaryAlwaysReversesPositive() {
        assertEquals(0.02, boundaryMotion(Vec3(-0.275, 0.5, 0.0)).x)
    }

    @Test
    fun lowerTrackYBoundaryAlwaysReversesPositive() {
        assertEquals(0.01, boundaryMotion(Vec3(0.0, 0.05, 0.0)).y)
    }

    @Test
    fun lowerTrackZBoundaryAlwaysReversesPositive() {
        assertEquals(0.02, boundaryMotion(Vec3(0.0, 0.5, -0.275)).z)
    }

    @Test
    fun initialTrackRaytraceUsesAttackAndAuthoritativeWallRanges() {
        val result = evaluateVCoreTrackInitialRaytrace(true, 4.2, 3.0) { range, wallsRange ->
            assertEquals(4.2, range)
            assertEquals(3.0, wallsRange)
            true
        }

        assertTrue(result)
    }

    @Test
    fun initialTrackRotationFallsBackToPlayerWithoutProvenAuraOwnership() {
        val auraRotation = Rotation(10.0f, 20.0f)
        val playerRotation = Rotation(30.0f, 40.0f)

        assertEquals(auraRotation, selectVCoreTrackInitialRotation(auraRotation, playerRotation))
        assertEquals(playerRotation, selectVCoreTrackInitialRotation(null, playerRotation))
    }

    @Test
    fun visibleTrackTargetUsesEqualAttackAndWallTraceRanges() {
        val result = evaluateTrackPitchRaytrace(true, true, 32.5) { range, wallsRange ->
            assertEquals(32.5, range)
            assertEquals(range, wallsRange)
            true
        }

        assertTrue(result)
    }

    @Test
    fun behindWallTargetWithinTraceRangeIsAccepted() {
        val targetDistance = 4.0
        assertTrue(evaluateTrackPitchRaytrace(true, true, 5.0) { _, wallsRange ->
            targetDistance <= wallsRange
        })
    }

    @Test
    fun behindWallTargetOutsideTraceRangeIsRejected() {
        val targetDistance = 6.0
        assertFalse(evaluateTrackPitchRaytrace(true, true, 5.0) { _, wallsRange ->
            targetDistance <= wallsRange
        })
    }

    @Test
    fun disabledRaycastMatchesVCoreAndReturnsTrueWithoutTracing() {
        assertTrue(evaluateTrackPitchRaytrace(true, false, 5.0) { _, _ ->
            fail("Raytrace must not run when Aura Raycast is None")
        })
    }

    @Test
    fun trackPitchRejectsAnEntityThatIsNotTheCurrentAuraTarget() {
        assertFalse(evaluateTrackPitchRaytrace(false, true, 5.0) { _, _ ->
            fail("Raytrace must not run for a non-current target")
        })
    }

    @Test
    fun dedicatedShieldBreakDoesNotRunNormalPostActionMarkers() {
        var emaceMarked = false
        var maceMarked = false

        runVCoreAuraPostAction(
            dedicatedShieldBreak = true,
            attacked = true,
            markEMace = { emaceMarked = true },
            markMaceSwap = { maceMarked = true },
        )

        assertFalse(emaceMarked)
        assertFalse(maceMarked)
    }

    @Test
    fun normalAuraCycleRetainsBothPostActionMarkers() {
        var emaceMarks = 0
        var maceArgument: Boolean? = null

        runVCoreAuraPostAction(
            dedicatedShieldBreak = false,
            attacked = false,
            markEMace = { emaceMarks++ },
            markMaceSwap = { maceArgument = it },
        )

        assertEquals(1, emaceMarks)
        assertEquals(false, maceArgument)
    }

    @Test
    fun shieldBreakerWithoutMaceStunReturnsTheCurrentOriginalSlot() {
        assertEquals(2, vCoreShieldBreakerReturnSlot(currentSelectedSlot = 2))
    }

    @Test
    fun shieldBreakerMaceStunWithoutMatchingMaceStillReturnsTheOriginalSlot() {
        assertEquals(2, vCoreShieldBreakerReturnSlot(currentSelectedSlot = 2))
    }

    @Test
    fun shieldBreakerMaceStunWithMatchingMaceReturnsTheSelectedMaceSlot() {
        assertEquals(6, vCoreShieldBreakerReturnSlot(currentSelectedSlot = 6))
    }
}
