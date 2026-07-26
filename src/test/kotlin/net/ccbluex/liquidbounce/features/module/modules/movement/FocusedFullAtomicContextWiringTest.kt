package net.ccbluex.liquidbounce.features.module.modules.movement

import java.nio.file.Files
import java.nio.file.Path
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMoveFix
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FocusedFullAtomicContextWiringTest {

    @Test
    fun everyPhysicalDirectionIsConsistentWithoutSprint() {
        for (input in FocusedFullMoveFix.candidateInputs()) {
            val selected = FocusedFullMoveFix.selectInput(137f, -41f, input[0], input[1], sprinting = false)
            assertTrue(selected[0] != 0f || selected[1] != 0f)
        }
    }

    @Test
    fun everyPhysicalDirectionGetsForwardBearingCandidateWithSprint() {
        for (input in FocusedFullMoveFix.candidateInputs()) {
            val selected = FocusedFullMoveFix.selectInput(137f, -41f, input[0], input[1], sprinting = true)
            assertTrue(FocusedFullMoveFix.isSprintLegal(selected[0], selected[1]))
        }
    }

    @Test
    fun standingStillNeverInventsMovement() {
        assertArrayEquals(floatArrayOf(0f, 0f), FocusedFullMoveFix.selectInput(180f, 0f, 0f, 0f, sprinting = true))
    }

    @Test
    fun targetMutationSourcesAreRecordedWithoutRelatchingInput() {
        val aura = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(aura.contains("setTrackedTarget(crosshairTarget, \"crosshair-substitution\")"))
        assertTrue(aura.contains("setTrackedTarget(target, \"update-target\")"))
        assertTrue(aura.contains("resetTrackedTarget(\"no-valid-target\")"))
        assertTrue(aura.contains("resetTrackedTarget(\"module-disable\")"))
    }

    @Test
    fun contextHardInvalidatorsArePresent() {
        val context = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/FocusedFullMovementContext.kt")
        listOf(
            "killaura-disabled",
            "provider-not-killaura",
            "invalid-rotation",
            "missing-rotation-target",
            "dead-rotation-target",
            "passenger",
            "dead",
            "autocart-owns-movement",
            "combat-paused",
            "rotation-paused",
            "world-change",
            "disconnect",
        ).forEach { assertTrue(context.contains(it), "missing invalidator $it") }
    }

    @Test
    fun inputIsLatchedBeforeSprintAndReleasedAfterMovementPacket() {
        val keyboard = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinKeyboardInput.java")
        val playerMixin = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinClientPlayerEntity.java")
        assertTrue(keyboard.indexOf("FocusedFullMovementContextManager.latch(") < keyboard.indexOf("new SprintEvent("))
        assertTrue(playerMixin.contains("hookMovementPost"))
        assertTrue(playerMixin.contains("FocusedFullMovementContextManager.completeCycle();"))
    }

    @Test
    fun vanillaSprintMayStopBeforeABackwardCandidateIsPredicted() {
        val context = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/FocusedFullMovementContext.kt")
        assertTrue(context.contains("vanillaSprintWillRemainOrStart"))
        assertTrue(context.contains("ModuleSprint.willRequestSprintFor(unrestrictedInput)"))
        assertTrue(context.contains("!ModuleKillAura.shouldBlockSprinting"))
    }

    @Test
    fun networkSprintDecisionIsObservedAtTheActualPacketBoundary() {
        val playerMixin = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinClientPlayerEntity.java")
        assertTrue(playerMixin.contains("observeNetworkSprintDecision(event.getSprint())"))
    }

    @Test
    fun focusedFullUsesTheSameYawForGroundVelocityJumpAndPacket() {
        val rotation = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationManager.kt")
        val living = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinLivingEntity.java")
        assertTrue(rotation.contains("FocusedFullMovementContextManager.movementYaw()"))
        assertTrue(rotation.contains("FocusedFullMovementContextManager.packetRotation()"))
        assertTrue(living.contains("FocusedFullMovementContextManager.jumpYaw()"))
    }

    @Test
    fun sprintModesAndPhysicalSprintAreAuditableAtTheInputBoundary() {
        val sprint = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleSprint.kt")
        val keyboard = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinKeyboardInput.java")
        assertTrue(sprint.contains("LEGIT"))
        assertTrue(sprint.contains("OMNIDIRECTIONAL"))
        assertTrue(sprint.contains("OMNIROTATIONAL"))
        assertTrue(keyboard.contains("original.sprint()"))
    }

    @Test
    fun hardInvalidatorsAndTargetSwitchSourcesAreBounded() {
        val context = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/FocusedFullMovementContext.kt")
        val aura = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        assertTrue(context.contains("player.isPassenger"))
        assertTrue(context.contains("player.isDeadOrDying"))
        assertTrue(context.contains("ModuleAutoCart.isAutoCartMoveFixActive()"))
        assertTrue(aura.contains("update-target"))
        assertTrue(aura.contains("crosshair-substitution"))
        assertTrue(aura.contains("no-valid-target"))
    }

    @Test
    fun focusFallbackCannotSendUnlatchedSilentYaw() {
        val rotation = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationManager.kt")
        val packetMethod = rotation.substringAfter("fun movementPacketRotation()")
            .substringBefore("@Suppress(\"LongParameterList\")")
        assertTrue(packetMethod.contains("FocusedFullMovementContextManager.packetRotation()"))
        assertTrue(packetMethod.contains("MovementCorrection.FOCUSED_FULL"))
        assertTrue(packetMethod.contains("return null"))
    }

    @Test
    fun ordinaryMovementCorrectionPathsRemainSeparate() {
        val keyboard = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinKeyboardInput.java")
        val full = keyboard.substringAfter("if (movementCorrection == MovementCorrection.FULL)")
            .substringBefore("if (movementCorrection != MovementCorrection.SILENT)")
        assertTrue(full.contains("getCorrectedInputSmooth("))
        assertFalse(full.contains("FocusedFullMovementContextManager"))
        assertTrue(keyboard.contains("if (movementCorrection != MovementCorrection.SILENT)"))
    }

    @Test
    fun diagnosticsAreBoundedAndDoNotReadChat() {
        val context = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/FocusedFullMovementContext.kt")
        assertTrue(context.contains("MAX_CYCLES = 200"))
        assertTrue(context.contains("MAX_DURATION_MS = 30_000L"))
        assertTrue(context.contains("[FocusedFullDiag]"))
        assertFalse(context.contains("chat"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))
}
