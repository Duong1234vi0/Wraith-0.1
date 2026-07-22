package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GrimTickNoSlowControllerTest {

    @Test
    fun exactWarmupFastRecoveryConfirmationSequence() {
        val controller = GrimTickNoSlowController()

        assertEquals(
            listOf(false, false, true, false, false, true, false, false),
            (1L..8L).map { serial ->
                begin(controller, serial).also { assertTrue(controller.completeAtNaturalTickEnd(serial)) }
            },
        )
        assertEquals(
            listOf(
                GrimTickNoSlowController.Phase.WARMUP,
                GrimTickNoSlowController.Phase.WARMUP,
                GrimTickNoSlowController.Phase.FAST,
                GrimTickNoSlowController.Phase.RECOVERY,
                GrimTickNoSlowController.Phase.RECOVERY_CONFIRM,
                GrimTickNoSlowController.Phase.FAST,
                GrimTickNoSlowController.Phase.RECOVERY,
                GrimTickNoSlowController.Phase.RECOVERY_CONFIRM,
            ),
            phases(8),
        )
    }

    @Test
    fun fastDecisionIsImmutableAndCompletelyUnslowed() {
        val controller = warmedController()
        assertTrue(begin(controller, 3))

        assertEquals(listOf(true, true, true, true), List(4) { controller.decision() })
        assertEquals(GrimTickNoSlowController.Phase.FAST, controller.phase)
        assertFalse(controller.isRecoveryCycle())
    }

    @Test
    fun onlyMatchingNaturalTickEndCompletesAndDuplicateIsIgnored() {
        val controller = GrimTickNoSlowController()
        assertFalse(begin(controller, 1))

        assertFalse(controller.completeAtNaturalTickEnd(2))
        assertEquals(
            GrimTickNoSlowController.BoundaryIssue.UNEXPECTED_TICK_END,
            controller.boundaryIssue,
        )

        assertFalse(begin(controller, 3))
        assertTrue(controller.completeAtNaturalTickEnd(3))
        assertFalse(controller.completeAtNaturalTickEnd(3))
        assertEquals(
            GrimTickNoSlowController.BoundaryIssue.DUPLICATE_TICK_END,
            controller.boundaryIssue,
        )
    }

    @Test
    fun missingTickEndFailsClosedAndRequiresRecoveryConfirmation() {
        val controller = warmedController()
        assertTrue(begin(controller, 3))

        assertFalse(begin(controller, 3))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY, controller.phase)
        assertEquals(
            GrimTickNoSlowController.BoundaryIssue.MISSING_TICK_END,
            controller.boundaryIssue,
        )
        assertTrue(controller.completeAtNaturalTickEnd(3))

        assertFalse(begin(controller, 4))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY_CONFIRM, controller.phase)
        assertTrue(controller.completeAtNaturalTickEnd(4))
        assertTrue(begin(controller, 5))
    }

    @Test
    fun interruptedRecoveryOrConfirmationCannotRearmFast() {
        val recoveryInterrupted = controllerAfterFast()
        assertFalse(begin(recoveryInterrupted, 4))
        assertFalse(begin(recoveryInterrupted, 4))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY, recoveryInterrupted.phase)

        val confirmationInterrupted = controllerAfterFast()
        assertFalse(begin(confirmationInterrupted, 4))
        assertTrue(confirmationInterrupted.completeAtNaturalTickEnd(4))
        assertFalse(begin(confirmationInterrupted, 5))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY_CONFIRM, confirmationInterrupted.phase)
        assertFalse(begin(confirmationInterrupted, 5))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY, confirmationInterrupted.phase)
    }

    @Test
    fun lifecycleAndEveryUseIdentityChangeRestartTwoWarmups() {
        val changedContexts = listOf(
            Context(player = OTHER_PLAYER),
            Context(hand = OFF_HAND),
            Context(item = OTHER_ITEM),
            Context(useSessionId = 99),
            Context(selectedSlot = 6),
            Context(settingsFingerprint = 99),
        )

        changedContexts.forEachIndexed { index, changed ->
            val controller = warmedController()
            assertTrue(begin(controller, 3))
            assertFalse(begin(controller, 4, context = changed), "identity case $index")
            assertEquals(GrimTickNoSlowController.Phase.WARMUP, controller.phase)
            assertTrue(controller.completeAtNaturalTickEnd(4))
            assertFalse(begin(controller, 5, context = changed))
        }

        val reset = warmedController()
        reset.reset()
        assertFalse(begin(reset, 10))
        assertEquals(GrimTickNoSlowController.Phase.WARMUP, reset.phase)
    }

    @Test
    fun inactiveIneligibleRidingAndFallFlyingStayVanilla() {
        val controller = warmedController()
        assertFalse(begin(controller, 3, active = false))
        assertFalse(begin(controller, 4, eligible = false))
        assertFalse(controller.decision())
        assertFalse(controller.isRecoveryCycle())
    }

    @Test
    fun handItemAndMovementVariantsKeepEveryFastPhaseFullSpeed() {
        val variants = listOf(
            Context(hand = MAIN_HAND, item = FOOD),
            Context(hand = OFF_HAND, item = FOOD),
            Context(hand = MAIN_HAND, item = SHIELD),
            Context(hand = OFF_HAND, item = SHIELD),
            Context(hand = MAIN_HAND, item = BOW),
            Context(hand = MAIN_HAND, item = TRIDENT),
            Context(hand = OFF_HAND, item = CROSSBOW),
        )
        variants.forEach { context ->
            val controller = warmedController(context)
            assertTrue(begin(controller, 3, context = context))
        }
    }

    @Test
    fun detailedGrimModelMatchesCheckedUncheckedAndItemStateSemantics() {
        val model = GrimNoSlowModel()
        assertFalse(model.observe(Prediction.INVALID_CHECKED))
        assertTrue(model.flaggedLastTick)
        assertFalse(model.observe(Prediction.UNCHECKED))
        assertTrue(model.flaggedLastTick)
        assertFalse(model.observe(Prediction.ITEM_STATE_FALSE))
        assertTrue(model.flaggedLastTick)
        assertTrue(model.observe(Prediction.INVALID_CHECKED))

        assertFalse(model.observe(Prediction.VALID_CHECKED))
        assertFalse(model.flaggedLastTick)
        assertEquals(0.95, model.violations, 0.00001)
    }

    @Test
    fun oldSingleRecoveryCanFlagWhenRecoveryDoesNotClearLatch() {
        val model = GrimNoSlowModel()
        assertFalse(model.observe(Prediction.INVALID_CHECKED))
        assertFalse(model.observe(Prediction.UNCHECKED))
        assertTrue(model.observe(Prediction.INVALID_CHECKED))
    }

    @Test
    fun validRecoveryClearsLatchAndConfirmationPreventsImmediateFastAfterOneFailure() {
        val model = GrimNoSlowModel()
        assertFalse(model.observe(Prediction.INVALID_CHECKED))
        assertFalse(model.observe(Prediction.VALID_CHECKED))
        assertFalse(model.observe(Prediction.INVALID_CHECKED))

        val controller = controllerAfterFast()
        assertFalse(begin(controller, 4))
        assertTrue(controller.completeAtNaturalTickEnd(4))
        assertFalse(begin(controller, 5))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY_CONFIRM, controller.phase)
    }

    @Test
    fun productionWiringUsesPostSentNaturalTickEndAndNoPacketSpoof() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt")
        val controller = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/GrimTickNoSlowController.kt")

        assertTrue(module.contains("handler<OutgoingPacketPostSendEvent>"))
        assertTrue(module.contains("event.packet !is ServerboundClientTickEndPacket"))
        assertTrue(module.contains("completeAtNaturalTickEnd(naturalTickEndSerial)"))
        assertFalse(module.contains("handler<PlayerNetworkMovementTickEvent>"))
        assertFalse(module.contains("EventState.POST"))
        assertFalse(module.contains("ServerboundClientTickEndPacket("))
        assertFalse(module.contains("network.sendPacket(ServerboundClientTickEndPacket"))
        assertFalse(controller.contains("PlayerNetworkMovementTickEvent"))
    }

    @Test
    fun recoveryDoesNotOverrideSprintAndModuleSprintRunsNormally() {
        val noSlow = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt")
        val sprint = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleSprint.kt")

        assertFalse(sprint.contains("ModuleNoSlow.isGrimTickRecoveryCycle()"))
        assertFalse(noSlow.contains("grimTickSprintRecoveryHandler"))
        assertFalse(noSlow.contains("event.sprint = false"))
        assertFalse(noSlow.contains("setSprinting(false)"))
        assertFalse(noSlow.contains("network.sendPacket(ServerboundPlayerCommandPacket"))
    }

    @Test
    fun diagnosticsAreBoundedAndPacketObservationOnly() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt")

        assertTrue(module.contains("[GrimTickDiag]"))
        assertTrue(module.contains("MAX_DIAGNOSTIC_BOUNDARIES = 180"))
        assertTrue(module.contains("ServerboundMovePlayerPacket"))
        assertTrue(module.contains("ServerboundPlayerInputPacket"))
        assertTrue(module.contains("ServerboundPlayerCommandPacket"))
        assertTrue(module.contains("ServerboundUseItemPacket"))
        assertTrue(module.contains("ServerboundPlayerActionPacket"))
        assertTrue(module.contains("ServerboundSetCarriedItemPacket"))
        assertFalse(module.contains("release use spam", ignoreCase = true))
    }

    private fun phases(count: Int): List<GrimTickNoSlowController.Phase> {
        val controller = GrimTickNoSlowController()
        return (1L..count.toLong()).map { serial ->
            begin(controller, serial)
            controller.phase.also { assertTrue(controller.completeAtNaturalTickEnd(serial)) }
        }
    }

    private fun warmedController(context: Context = Context()) = GrimTickNoSlowController().also { controller ->
        assertFalse(begin(controller, 1, context = context))
        assertTrue(controller.completeAtNaturalTickEnd(1))
        assertFalse(begin(controller, 2, context = context))
        assertTrue(controller.completeAtNaturalTickEnd(2))
    }

    private fun controllerAfterFast() = warmedController().also { controller ->
        assertTrue(begin(controller, 3))
        assertTrue(controller.completeAtNaturalTickEnd(3))
    }

    private fun begin(
        controller: GrimTickNoSlowController,
        serial: Long,
        active: Boolean = true,
        eligible: Boolean = true,
        context: Context = Context(),
    ) = controller.beginCycle(
        tickEndSerial = serial,
        active = active,
        eligible = eligible,
        playerIdentity = context.player,
        handIdentity = context.hand,
        itemIdentity = context.item,
        useSessionId = context.useSessionId,
        selectedSlot = context.selectedSlot,
        settingsFingerprint = context.settingsFingerprint,
    )

    private fun source(relative: String) = Files.readString(sourcePath(relative))

    private fun sourcePath(relative: String): Path {
        var cursor = Path.of("").toAbsolutePath()
        repeat(8) {
            val candidate = cursor.resolve(relative)
            if (Files.exists(candidate)) return candidate
            cursor = cursor.parent ?: return@repeat
        }
        error("Unable to locate $relative")
    }

    private data class Context(
        val player: Any = PLAYER,
        val hand: Any = MAIN_HAND,
        val item: Any = FOOD,
        val useSessionId: Int = 7,
        val selectedSlot: Int = 2,
        val settingsFingerprint: Int = 1,
    )

    private enum class Prediction {
        VALID_CHECKED,
        INVALID_CHECKED,
        UNCHECKED,
        ITEM_STATE_FALSE,
    }

    private class GrimNoSlowModel {
        var flaggedLastTick = false
            private set
        var violations = 0.0
            private set

        fun observe(prediction: Prediction): Boolean = when (prediction) {
            Prediction.UNCHECKED, Prediction.ITEM_STATE_FALSE -> false
            Prediction.VALID_CHECKED -> {
                violations = (violations - 0.05).coerceAtLeast(0.0)
                flaggedLastTick = false
                false
            }
            Prediction.INVALID_CHECKED -> {
                val flags = flaggedLastTick
                if (flags) violations++
                flaggedLastTick = true
                flags
            }
        }
    }

    companion object {
        private val PLAYER = Any()
        private val OTHER_PLAYER = Any()
        private val MAIN_HAND = Any()
        private val OFF_HAND = Any()
        private val FOOD = Any()
        private val SHIELD = Any()
        private val BOW = Any()
        private val TRIDENT = Any()
        private val CROSSBOW = Any()
        private val OTHER_ITEM = Any()
    }
}
