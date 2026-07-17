package net.ccbluex.liquidbounce.features.module.modules.combat.killaura

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class VCoreAuraFidelityTest {

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
}
