package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GrimTickSprintContinuityTest {

    @Test
    fun productionNoLongerForcesSprintFalseDuringRecovery() {
        val noSlow = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt")
        val sprint = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleSprint.kt")

        assertFalse(noSlow.contains("grimTickSprintRecoveryHandler"))
        assertFalse(noSlow.contains("event.sprint = false"))
        assertFalse(noSlow.contains("setSprinting(false)"))
        assertFalse(noSlow.contains("network.sendPacket(ServerboundPlayerCommandPacket"))
        assertFalse(sprint.contains("isGrimTickRecoveryCycle"))
        assertFalse(sprint.contains("ModuleNoSlow"))
        assertTrue(noSlow.contains("handler<OutgoingPacketPostSendEvent>"))
        assertTrue(noSlow.contains("completeAtNaturalTickEnd(naturalTickEndSerial)"))
    }

    @Test
    fun uninterruptedSprintStaysTrueAcrossTheCompleteGrimTickSequence() {
        val model = SprintContinuityModel(initialSprint = true)
        val phases = listOf(
            Phase.WARMUP,
            Phase.WARMUP,
            Phase.FAST,
            Phase.RECOVERY,
            Phase.RECOVERY_CONFIRM,
            Phase.FAST,
            Phase.RECOVERY,
            Phase.RECOVERY_CONFIRM,
        )

        val results = phases.map(model::tick)

        assertEquals(List(phases.size) { true }, results.map { it.sprint })
        assertEquals(
            listOf(false, false, true, false, false, true, false, false),
            results.map { it.bypass },
        )
        assertEquals(
            listOf(true, true, false, true, true, false, true, true),
            results.map { it.itemSlowdownActive },
        )
        assertEquals(0, results.sumOf { it.stopCommands })
        assertEquals(0, results.drop(1).sumOf { it.startCommands })
    }

    @Test
    fun sprintAcquisitionProducesOneStartAndRecoveryProducesNoStop() {
        val model = SprintContinuityModel(initialSprint = false)
        val fast = model.tick(Phase.FAST, sprintRequested = true)
        val recovery = model.tick(Phase.RECOVERY, sprintRequested = true)
        val confirmation = model.tick(Phase.RECOVERY_CONFIRM, sprintRequested = true)

        assertTrue(fast.sprint)
        assertEquals(1, fast.startCommands)
        assertTrue(recovery.sprint)
        assertEquals(0, recovery.startCommands)
        assertEquals(0, recovery.stopCommands)
        assertTrue(confirmation.sprint)
        assertEquals(0, confirmation.startCommands)
        assertEquals(0, confirmation.stopCommands)
    }

    @Test
    fun genuineStopsRemainIndependentFromPhaseTransitions() {
        val model = SprintContinuityModel(initialSprint = true)

        val noMovement = model.tick(Phase.RECOVERY, sprintRequested = false, moving = false)
        val restart = model.tick(Phase.FAST, sprintRequested = true, moving = true)
        val backward = model.tick(Phase.RECOVERY, sprintRequested = false, moving = true, backwards = true)
        val collision = SprintContinuityModel(initialSprint = true).tick(
            Phase.RECOVERY,
            sprintRequested = true,
            moving = true,
            collision = true,
        )

        assertFalse(noMovement.sprint)
        assertEquals(1, noMovement.stopCommands)
        assertTrue(restart.sprint)
        assertEquals(1, restart.startCommands)
        assertFalse(backward.sprint)
        assertEquals(1, backward.stopCommands)
        assertFalse(collision.sprint)
        assertEquals(1, collision.stopCommands)
    }

    @Test
    fun diagnosticsAreSessionBoundedAndIncludeSprintContinuityFields() {
        val noSlow = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt")

        assertTrue(noSlow.contains("observeAiStepStart"))
        assertTrue(noSlow.contains("observeAiStepEnd"))
        assertTrue(noSlow.contains("physicalSprint"))
        assertTrue(noSlow.contains("inputSprintBefore"))
        assertTrue(noSlow.contains("inputSprintAfter"))
        assertTrue(noSlow.contains("startCommands"))
        assertTrue(noSlow.contains("stopCommands"))
        assertTrue(noSlow.contains("sprintStateTransitions"))
        assertTrue(noSlow.contains("session summary"))
        assertTrue(noSlow.contains("MAX_DIAGNOSTIC_BOUNDARIES = 180"))
    }

    @Test
    fun packetStateModelSendsOnlyRealSprintTransitions() {
        val state = PacketSprintStateModel()

        assertEquals("START", state.observe(localSprint = true))
        assertEquals(null, state.observe(localSprint = true))
        assertEquals(null, state.observe(localSprint = true))
        assertEquals("STOP", state.observe(localSprint = false))
        assertEquals(null, state.observe(localSprint = false))
    }

    private enum class Phase {
        WARMUP,
        FAST,
        RECOVERY,
        RECOVERY_CONFIRM,
    }

    private data class TickResult(
        val sprint: Boolean,
        val bypass: Boolean,
        val itemSlowdownActive: Boolean,
        val startCommands: Int,
        val stopCommands: Int,
    )

    private class SprintContinuityModel(initialSprint: Boolean) {
        private var sprint = initialSprint

        fun tick(
            phase: Phase,
            sprintRequested: Boolean = sprint,
            moving: Boolean = true,
            backwards: Boolean = false,
            collision: Boolean = false,
        ): TickResult {
            val previous = sprint
            sprint = sprintRequested && moving && !backwards && !collision
            return TickResult(
                sprint = sprint,
                bypass = phase == Phase.FAST,
                itemSlowdownActive = phase != Phase.FAST,
                startCommands = if (!previous && sprint) 1 else 0,
                stopCommands = if (previous && !sprint) 1 else 0,
            )
        }
    }

    private class PacketSprintStateModel {
        private var wasSprinting = false

        fun observe(localSprint: Boolean): String? {
            if (localSprint == wasSprinting) return null
            wasSprinting = localSprint
            return if (localSprint) "START" else "STOP"
        }
    }

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
}
