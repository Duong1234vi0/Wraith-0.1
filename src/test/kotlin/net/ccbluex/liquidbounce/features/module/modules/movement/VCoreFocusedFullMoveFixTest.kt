package net.ccbluex.liquidbounce.features.module.modules.movement

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMoveFix
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class VCoreFocusedFullMoveFixTest {

    @Test
    fun quantizedTargetYawMatchesSourceOracle() {
        val actual = FocusedFullMoveFix.quantizedTargetYaw(
            playerX = 2.0,
            playerEyeY = 2.62,
            playerZ = -3.0,
            targetX = -4.0,
            targetY = 5.0,
            targetZ = 7.0,
            mouseSensitivity = 0.37,
        )

        val dx = -4.0 - 2.0
        val dy = 5.0 - 2.62
        val dz = 7.0 - -3.0
        val horizontal = sqrt(dx * dx + dz * dz)
        var yaw = Math.toDegrees(atan2(dz, dx)).toFloat() - 90.0f
        val pitch = -Math.toDegrees(atan2(dy, horizontal)).toFloat()
        val sensitivity = 0.37.pow(1.5).toFloat() * 0.05f + 0.1f
        val gcd = sensitivity * sensitivity * sensitivity * 1.2f
        yaw -= yaw % gcd
        val quantizedPitch = pitch - pitch % (gcd * sensitivity)

        assertEquals(yaw, actual, 1.0E-5f)
        assertTrue(quantizedPitch.isFinite())
    }

    @Test
    fun allEightNonZeroCandidateInputsAreConsidered() {
        val candidates = FocusedFullMoveFix.candidateInputs()
        assertEquals(8, candidates.size)
        assertTrue(candidates.all { it[0] in -1.0f..1.0f && it[1] in -1.0f..1.0f })
        assertTrue(candidates.none { it[0] == 0.0f && it[1] == 0.0f })
        assertEquals(8, candidates.map { it[0] to it[1] }.toSet().size)
    }

    @Test
    fun zeroInputRemainsZero() {
        assertArrayEquals(
            floatArrayOf(0.0f, 0.0f),
            FocusedFullMoveFix.selectInput(73.0f, -121.0f, 0.0f, 0.0f)
        )
    }

    @Test
    fun cardinalInputMatchesVCoreSearch() {
        assertArrayEquals(
            sourceOracle(90.0f, 0.0f, 1.0f, 0.0f),
            FocusedFullMoveFix.selectInput(90.0f, 0.0f, 1.0f, 0.0f)
        )
    }

    @Test
    fun diagonalInputMatchesVCoreSearch() {
        assertArrayEquals(
            sourceOracle(-37.0f, 112.0f, -1.0f, 1.0f),
            FocusedFullMoveFix.selectInput(-37.0f, 112.0f, -1.0f, 1.0f)
        )
    }

    @Test
    fun finalDifferenceKeepsVCoreUnwrappedQuirk() {
        assertEquals(358.0f, FocusedFullMoveFix.unwrappedDifference(179.0, -179.0))
        assertTrue(abs(179.0 - -179.0) > 180.0)
    }

    @Test
    fun positiveStrafeMeansLeftInBothInputRepresentations() {
        assertTrue(DirectionalInput(0.0f, 1.0f).left)
        assertEquals(-90.0, FocusedFullMoveFix.directionDegrees(0.0f, 0.0f, 1.0f), 1.0E-6)
    }

    @Test
    fun focusedFullDoesNotUseOrdinaryFullSmootherState() {
        val source = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinKeyboardInput.java")
        val focused = source.substringAfter("if (movementCorrection == MovementCorrection.FOCUSED_FULL)")
            .substringBefore("if (movementCorrection == MovementCorrection.FULL)")
        assertTrue(focused.contains("resetFullMoveFixState();"))
        assertTrue(focused.contains("getVCoreFocusedFullInput") || focused.contains("FocusedFullMoveFix.selectInput"))
        assertFalse(focused.contains("getCorrectedInputSmooth"))
        assertFalse(focused.contains("previousFullForward"))
    }

    @Test
    fun autoCartOwnershipDisablesFocusedFull() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(source.contains("!ModuleAutoCart.isAutoCartMoveFixActive()"))
    }

    @Test
    fun passengerDisablesFocusedFull() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(source.contains("!player.isPassenger"))
    }

    @Test
    fun nonKillAuraProviderDoesNotActivateScopedPort() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(source.contains("rotationTarget.provider === this"))
        assertTrue(source.contains("rotationTarget.entity === target"))
    }

    @Test
    fun killAuraTargetAndRotationOwnershipEnablesScopedPort() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(source.contains("val target = targetTracker.target ?: return false"))
        assertTrue(source.contains("val rotation = RotationManager.currentRotation ?: return false"))
        assertTrue(source.contains("MovementCorrection.FOCUSED_FULL"))
    }

    @Test
    fun groundVelocityUsesActiveRotationYaw() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationManager.kt")
        assertTrue(source.contains("movementInputToVelocity(") && source.contains("rotation.yaw"))
    }

    @Test
    fun jumpVectorUsesActiveRotationYaw() {
        val source = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinLivingEntity.java")
        assertTrue(source.contains("rotation.getYaw() * 0.017453292F"))
        assertTrue(source.contains("new Vec3(-Mth.sin(yaw) * 0.2F"))
    }

    @Test
    fun elytraCorrectionUsesActiveRotationOnlyUnderOwnershipGate() {
        val source = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinLivingEntity.java")
        assertTrue(source.contains("return rotation.getPitch();"))
        assertTrue(source.contains("return rotation.getDirectionVector();"))
        assertTrue(source.contains("!ModuleKillAura.isFocusedFullMoveFixActive()"))
    }

    private fun sourceOracle(rotationYaw: Float, targetYaw: Float, forward: Float, strafe: Float): FloatArray {
        if (forward == 0.0f && strafe == 0.0f) return floatArrayOf(0.0f, 0.0f)

        val targetAngle = wrapDegrees(FocusedFullMoveFix.directionDegrees(targetYaw, forward, strafe))
        var best = floatArrayOf(0.0f, 0.0f)
        var bestDifference = Float.MAX_VALUE
        for (candidateForward in -1..1) {
            for (candidateStrafe in -1..1) {
                if (candidateForward == 0 && candidateStrafe == 0) continue
                val predicted = wrapDegrees(
                    FocusedFullMoveFix.directionDegrees(
                        rotationYaw,
                        candidateForward.toFloat(),
                        candidateStrafe.toFloat(),
                    )
                )
                val difference = abs((targetAngle - predicted).toFloat())
                if (difference < bestDifference) {
                    bestDifference = difference
                    best = floatArrayOf(candidateForward.toFloat(), candidateStrafe.toFloat())
                }
            }
        }
        return best
    }

    private fun wrapDegrees(value: Double): Double {
        var wrapped = value % 360.0
        if (wrapped >= 180.0) wrapped -= 360.0
        if (wrapped < -180.0) wrapped += 360.0
        return wrapped
    }

    private fun source(path: String): String = Files.readString(Path.of(path))
}
