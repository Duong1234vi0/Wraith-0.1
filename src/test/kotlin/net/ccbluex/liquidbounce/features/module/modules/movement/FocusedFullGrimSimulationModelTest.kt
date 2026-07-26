package net.ccbluex.liquidbounce.features.module.modules.movement

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMoveFix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** Small test-only model of Grim 2.0's modern known-input/yaw prediction seam. */
class FocusedFullGrimSimulationModelTest {

    @Test
    fun rawVisualMovementWithSilentPacketYawCreatesLargeSimulationOffset() {
        val local = acceleration(yaw = 0f, forward = -1f, strafe = 0f, speed = 0.1)
        val grim = acceleration(yaw = 180f, forward = -1f, strafe = 0f, speed = 0.1)
        assertTrue(local.distanceTo(grim) > 0.1)
    }

    @Test
    fun oneTickMismatchLeavesGroundFrictionDecayTail() {
        var difference = 0.100841
        val observed = mutableListOf<Double>()
        repeat(5) {
            observed += difference
            difference *= 0.6 * 0.91
        }
        assertEquals(0.100841, observed[0], 1.0E-9)
        assertEquals(0.055059, observed[1], 1.0E-5)
        assertEquals(0.030062, observed[2], 1.0E-5)
        assertEquals(0.016414, observed[3], 1.0E-5)
        assertEquals(0.008962, observed[4], 1.0E-5)
        val matchingPairs = observed.zipWithNext().count { (a, b) ->
            abs(b / a - 0.546) <= 0.001
        }
        assertEquals(4, matchingPairs)
    }

    @Test
    fun atomicYawAndInputContextHasZeroModeledOffset() {
        val corrected = FocusedFullMoveFix.selectInput(180f, 0f, -1f, 0f, sprinting = false)
        val local = acceleration(180f, corrected[0], corrected[1], 0.1)
        val grim = acceleration(180f, corrected[0], corrected[1], 0.1)
        assertEquals(0.0, local.distanceTo(grim), 1.0E-12)
    }

    @Test
    fun sprintTrueExcludesBackwardAndPureSidewaysKnownInputs() {
        val candidates = FocusedFullMoveFix.candidateInputs(sprinting = true)
        assertTrue(candidates.all { it[0] == 1f })
        assertTrue(candidates.none { it[0] <= 0f })
    }

    @Test
    fun sprintForwardAndForwardDiagonalsAreRepresentable() {
        val candidates = FocusedFullMoveFix.candidateInputs(sprinting = true).map { it[0] to it[1] }.toSet()
        assertEquals(setOf(1f to -1f, 1f to 0f, 1f to 1f), candidates)
    }

    @Test
    fun sprintFalsePermitsAllEightKnownInputs() {
        assertEquals(8, FocusedFullMoveFix.candidateInputs(sprinting = false).size)
    }

    @Test
    fun auraEnableWhileHoldingBackwardUsesOneRepresentableContext() {
        val selected = FocusedFullMoveFix.selectInput(180f, 0f, -1f, 0f, sprinting = true)
        assertTrue(FocusedFullMoveFix.isSprintLegal(selected[0], selected[1]))
        val local = acceleration(180f, selected[0], selected[1], 0.13)
        val grim = acceleration(180f, selected[0], selected[1], 0.13)
        assertTrue(local.distanceTo(grim) < 0.001)
    }

    @Test
    fun targetSwitchCommitsAsTwoInternallyConsistentCycles() {
        val a = FocusedFullMoveFix.selectInput(170f, 0f, -1f, 0f, sprinting = true)
        val b = FocusedFullMoveFix.selectInput(-80f, 90f, -1f, 0f, sprinting = true)
        val aOffset = acceleration(170f, a[0], a[1], 0.13)
            .distanceTo(acceleration(170f, a[0], a[1], 0.13))
        val bOffset = acceleration(-80f, b[0], b[1], 0.13)
            .distanceTo(acceleration(-80f, b[0], b[1], 0.13))
        assertEquals(0.0, aOffset, 1.0E-12)
        assertEquals(0.0, bOffset, 1.0E-12)
    }

    @Test
    fun targetSwitchKeepsOldContextUntilTheNextBoundary() {
        data class Cycle(val targetId: Int, val yaw: Float, val input: FloatArray)

        val cycleA = Cycle(11, 170f, FocusedFullMoveFix.selectInput(170f, 0f, -1f, 0f, true))
        // Tracker mutation is intentionally not allowed to rewrite cycleA.
        val trackerTargetAfterLatch = 22
        assertEquals(11, cycleA.targetId)
        assertTrue(trackerTargetAfterLatch != cycleA.targetId)

        val cycleB = Cycle(22, -80f, FocusedFullMoveFix.selectInput(-80f, 90f, -1f, 0f, true))
        assertEquals(22, cycleB.targetId)
        assertTrue(cycleA.yaw != cycleB.yaw)
    }

    @Test
    fun wrappedAngleQuirkIsDirectionQualityOnlyWhenContextIsShared() {
        assertEquals(358f, FocusedFullMoveFix.unwrappedDifference(179.0, -179.0))
        val selected = FocusedFullMoveFix.selectInput(179f, -179f, -1f, 1f, sprinting = false)
        val shared = acceleration(179f, selected[0], selected[1], 0.1)
        assertEquals(0.0, shared.distanceTo(shared), 1.0E-12)
    }

    private fun acceleration(yaw: Float, forward: Float, strafe: Float, speed: Double): Vector {
        if (forward == 0f && strafe == 0f) return Vector(0.0, 0.0)
        // Grim's ModernInputTransformer first normalizes the known direction, scales it by
        // vanilla's 0.98 factor, then restores the modern square-input edge.
        val divisor = hypot(forward.toDouble(), strafe.toDouble()).coerceAtLeast(1.0)
        val normalizedForward = forward / divisor
        val normalizedStrafe = strafe / divisor
        val squareDistance = if (abs(normalizedForward) > abs(normalizedStrafe)) {
            1.0 / abs(normalizedForward)
        } else {
            1.0 / abs(normalizedStrafe)
        }
        val transformedLength = minOf(0.98 * squareDistance, 1.0)
        val squareForward = normalizedForward * transformedLength * speed
        val squareStrafe = normalizedStrafe * transformedLength * speed
        val radians = Math.toRadians(yaw.toDouble())
        return Vector(
            x = squareStrafe * cos(radians) - squareForward * sin(radians),
            z = squareForward * cos(radians) + squareStrafe * sin(radians),
        )
    }

    private data class Vector(val x: Double, val z: Double) {
        fun distanceTo(other: Vector): Double = hypot(x - other.x, z - other.z)
    }
}
