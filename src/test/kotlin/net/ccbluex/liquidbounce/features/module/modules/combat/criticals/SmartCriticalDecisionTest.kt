package net.ccbluex.liquidbounce.features.module.modules.combat.criticals

import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.CriticalAttackScope
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraCriticalCycleCoordinator
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.planKillAuraCriticalSprintStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SmartCriticalDecisionTest {

    @Test
    fun physicalJumpCycleUsesInputTransitionAndServerProvenFallingBoundary() {
        val coordinator = scopedCoordinator()

        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = true,
            transformedJump = true,
            onGround = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, clickReady = true)

        assertTrue(coordinator.manualJumpCycleActive)
        assertTrue(coordinator.pendingReadyClick)
        assertTrue(coordinator.shouldDeferGameTick(CriticalsSelectionMode.SMART))

        coordinator.observePostMovement(serverOnGround = true, fallDistance = 0.0f)
        assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART), "ground")

        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.0f)
        assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART), "rising")

        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.0f)
        assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART), "apex")

        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.02f)
        assertTrue(coordinator.serverProvenFalling)
        assertTrue(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))
        assertTrue(coordinator.beginPipeline())
        assertFalse(coordinator.beginPipeline(), "one pipeline per ready click")
        coordinator.finishPipeline(clickConsumed = true)

        assertFalse(coordinator.pendingReadyClick)
        assertFalse(coordinator.pipelineInProgress)

        coordinator.observePostMovement(serverOnGround = true, fallDistance = 0.0f)
        assertFalse(coordinator.manualJumpCycleActive)

        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = true,
            transformedJump = true,
            onGround = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
        assertTrue(coordinator.manualJumpCycleActive, "held key begins the next landing-to-jump cycle")
        assertEquals(2L, coordinator.cycleSerial)
    }

    @Test
    fun tapReleaseRetainsTheRealJumpCycleUntilLanding() {
        val coordinator = scopedCoordinator()

        startManualCycle(coordinator)
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, clickReady = true)
        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.0f)

        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = false,
            transformedJump = false,
            onGround = false,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
        assertTrue(coordinator.manualJumpCycleActive, "key release must not redefine an in-flight cycle")

        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.08f)
        assertTrue(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))
        coordinator.observePostMovement(serverOnGround = true, fallDistance = 0.0f)
        assertFalse(coordinator.manualJumpCycleActive)
        assertFalse(coordinator.pendingReadyClick, "released cycle does not leak a pending click after landing")
    }

    @Test
    fun transformedJumpWithoutPhysicalIntentDoesNotCreateAStandaloneCriticalsDependency() {
        val coordinator = scopedCoordinator()
        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = false,
            transformedJump = true,
            onGround = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, clickReady = true)
        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.08f)

        assertFalse(coordinator.manualJumpCycleActive)
        assertFalse(coordinator.pendingReadyClick)
        assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))

        val ordinarySmart = decision(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpKeyPressed = false,
            transformedPlayerInputJump = true,
            manualJumpCycleActive = false,
            state = FallingCritical,
            smartHeuristicPermitsAttack = true,
        )
        assertFalse(ordinarySmart.manualJumpGateActive)
        assertTrue(ordinarySmart.permitsAttack)
    }

    @Test
    fun heldJumpAcrossManyCyclesConsumesExactlyOneReadyClickPerCycle() {
        val coordinator = scopedCoordinator()
        var attacks = 0

        repeat(20) { cycle ->
            startManualCycle(coordinator)

            if (cycle % 5 == 0) coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
            coordinator.observePostMovement(serverOnGround = true, fallDistance = 0.0f)
            assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))

            if (cycle % 5 == 1) coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
            coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.0f)
            assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))

            if (cycle % 5 == 2) coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
            coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.0f)
            assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))

            if (cycle % 5 == 3) coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
            coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.02f)
            if (coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART)) {
                assertTrue(coordinator.beginPipeline())
                attacks++
                coordinator.finishPipeline(clickConsumed = true)
            }

            if (cycle % 5 == 4) coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
            coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.24f)
            if (coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART)) {
                assertTrue(coordinator.beginPipeline())
                attacks++
                coordinator.finishPipeline(clickConsumed = true)
            }

            assertFalse(coordinator.pendingReadyClick)
            coordinator.observePostMovement(serverOnGround = true, fallDistance = 0.0f)
        }

        assertEquals(20, attacks)
        assertEquals(20L, coordinator.cycleSerial)
    }

    @Test
    fun pendingClickSurvivesGroundRiseAndApexInProductionCoordinator() {
        val coordinator = scopedCoordinator()
        startManualCycle(coordinator)
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, clickReady = true)

        listOf(
            true to 0.0f,
            false to 0.0f,
            false to 0.0f,
        ).forEach { (ground, fallDistance) ->
            coordinator.observePostMovement(ground, fallDistance)
            assertTrue(coordinator.pendingReadyClick)
            assertFalse(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))
        }

        coordinator.observePostMovement(serverOnGround = false, fallDistance = 0.01f)
        assertTrue(coordinator.pendingReadyClick)
        assertTrue(coordinator.shouldAttemptPostMovement(CriticalsSelectionMode.SMART))
        assertTrue(
            coordinator.hasAttackClick(schedulerReady = false, postMovementBoundary = true),
            "the production post-movement path must execute the retained scheduler click",
        )
        assertFalse(coordinator.hasAttackClick(schedulerReady = false, postMovementBoundary = false))
    }

    @Test
    fun sprintPreparationStillRequiresOnlyTheNecessaryStateChanges() {
        val fullySprinting = planKillAuraCriticalSprintStop(localSprinting = true, serverSprinting = true)
        assertTrue(fullySprinting.sendStopCommand)
        assertTrue(fullySprinting.clearLocalSprint)

        val serverAlreadyStopped = planKillAuraCriticalSprintStop(localSprinting = true, serverSprinting = false)
        assertFalse(serverAlreadyStopped.sendStopCommand)
        assertTrue(serverAlreadyStopped.clearLocalSprint)

        val localAlreadyStopped = planKillAuraCriticalSprintStop(localSprinting = false, serverSprinting = true)
        assertTrue(localAlreadyStopped.sendStopCommand)
        assertFalse(localAlreadyStopped.clearLocalSprint)

        val alreadyPrepared = planKillAuraCriticalSprintStop(localSprinting = false, serverSprinting = false)
        assertFalse(alreadyPrepared.sendStopCommand)
        assertFalse(alreadyPrepared.clearLocalSprint)
    }

    @Test
    fun smartDecisionUsesLatchedCycleInsteadOfCurrentKeyLevel() {
        val releasedDuringCycle = decision(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpKeyPressed = false,
            transformedPlayerInputJump = false,
            manualJumpCycleActive = true,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )
        assertTrue(releasedDuringCycle.manualJumpGateActive)
        assertFalse(releasedDuringCycle.permitsAttack)

        val heldWithoutProvenCycle = decision(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpKeyPressed = true,
            transformedPlayerInputJump = true,
            manualJumpCycleActive = false,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )
        assertFalse(heldWithoutProvenCycle.manualJumpGateActive)
        assertTrue(heldWithoutProvenCycle.permitsAttack)

        val coordinator = scopedCoordinator()
        assertTrue(
            coordinator.shouldDeferGameTick(
                CriticalsSelectionMode.SMART,
                physicalManualIntentAwaitingInput = true,
            ),
            "the pre-input game head must not leak a grounded hit before the cycle can latch",
        )
    }

    @Test
    fun smartAndAlwaysPrepareSprintOnlyForARealNaturalCriticalState() {
        for (mode in listOf(CriticalsSelectionMode.SMART, CriticalsSelectionMode.ALWAYS)) {
            val manualCycle = mode == CriticalsSelectionMode.SMART
            assertFalse(decision(mode, true, true, manualCycle, Grounded).permitsAttack)
            assertFalse(decision(mode, true, true, manualCycle, Rising).permitsAttack)
            assertFalse(decision(mode, true, true, manualCycle, Apex).permitsAttack)

            val sprintingFall = decision(
                mode = mode,
                physicalJumpKeyPressed = true,
                transformedPlayerInputJump = true,
                manualJumpCycleActive = manualCycle,
                state = FallingCritical.copy(sprinting = true),
                serverSprinting = true,
            )
            assertFalse(sprintingFall.permitsAttack)
            assertTrue(sprintingFall.requiresKillAuraSprintStop)

            val preparedFall = decision(
                mode = mode,
                physicalJumpKeyPressed = true,
                transformedPlayerInputJump = true,
                manualJumpCycleActive = manualCycle,
                state = FallingCritical,
                serverSprinting = false,
            )
            assertTrue(preparedFall.permitsAttack)
        }
    }

    @Test
    fun alwaysOwnsPostMovementFallbackAndIgnoreDoesNot() {
        val always = scopedCoordinator()
        always.noteClickReadiness(CriticalsSelectionMode.ALWAYS, clickReady = true)
        always.observePostMovement(serverOnGround = false, fallDistance = 0.1f)
        assertTrue(always.shouldAttemptPostMovement(CriticalsSelectionMode.ALWAYS))

        val ignore = scopedCoordinator()
        ignore.noteClickReadiness(CriticalsSelectionMode.IGNORE, clickReady = true)
        ignore.observePostMovement(serverOnGround = false, fallDistance = 0.1f)
        assertFalse(ignore.pendingReadyClick)
        assertFalse(ignore.shouldAttemptPostMovement(CriticalsSelectionMode.IGNORE))

        assertTrue(
            decision(
                mode = CriticalsSelectionMode.IGNORE,
                physicalJumpKeyPressed = true,
                transformedPlayerInputJump = true,
                manualJumpCycleActive = true,
                state = Grounded,
                smartHeuristicPermitsAttack = false,
            ).permitsAttack
        )
    }

    @Test
    fun impossibleEnvironmentAndLifecycleChangesResetProductionState() {
        val coordinator = scopedCoordinator()
        startManualCycle(coordinator)
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)

        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = true,
            transformedJump = false,
            onGround = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
        assertFalse(coordinator.manualJumpCycleActive, "cancelled transformed jump")
        assertFalse(coordinator.pendingReadyClick, "cancelled cycle click")

        startManualCycle(coordinator)
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)

        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = true,
            transformedJump = true,
            onGround = false,
            naturalCriticalEnvironmentPossible = false,
            gliding = false,
        )
        assertFalse(coordinator.manualJumpCycleActive)
        assertFalse(coordinator.pendingReadyClick)

        startManualCycle(coordinator)
        coordinator.noteClickReadiness(CriticalsSelectionMode.SMART, true)
        coordinator.synchronizeScope(true, PlayerIdentity, WorldIdentity, Any())
        assertReset(coordinator, "target change")

        startManualCycle(coordinator)
        coordinator.synchronizeScope(true, Any(), WorldIdentity, TargetIdentity)
        assertReset(coordinator, "player replacement")

        coordinator.synchronizeScope(true, PlayerIdentity, WorldIdentity, TargetIdentity)
        startManualCycle(coordinator)
        coordinator.synchronizeScope(true, PlayerIdentity, Any(), TargetIdentity)
        assertReset(coordinator, "world replacement")

        coordinator.synchronizeScope(true, PlayerIdentity, WorldIdentity, TargetIdentity)
        startManualCycle(coordinator)
        coordinator.synchronizeScope(false, PlayerIdentity, WorldIdentity, TargetIdentity)
        assertReset(coordinator, "module disable")
    }

    @Test
    fun criticalPreparationScopeRejectsTargetWorldPlayerAndLifecycleChanges() {
        val scope = CriticalAttackScope(PlayerIdentity, WorldIdentity, TargetIdentity)

        fun matches(
            currentPlayer: Any? = PlayerIdentity,
            currentWorld: Any? = WorldIdentity,
            currentTarget: Any? = TargetIdentity,
            targetWorld: Any? = WorldIdentity,
            running: Boolean = true,
            removed: Boolean = false,
        ) = scope.matches(currentPlayer, currentWorld, currentTarget, targetWorld, running, removed)

        assertTrue(matches())
        assertFalse(matches(currentTarget = null))
        assertFalse(matches(currentTarget = Any()))
        assertFalse(matches(currentPlayer = Any()))
        assertFalse(matches(currentWorld = Any()))
        assertFalse(matches(targetWorld = Any()))
        assertFalse(matches(running = false))
        assertFalse(matches(removed = true))
    }

    private fun scopedCoordinator() = KillAuraCriticalCycleCoordinator().also {
        it.synchronizeScope(true, PlayerIdentity, WorldIdentity, TargetIdentity)
    }

    private fun startManualCycle(coordinator: KillAuraCriticalCycleCoordinator) {
        coordinator.synchronizeScope(true, PlayerIdentity, WorldIdentity, TargetIdentity)
        coordinator.observeInput(
            mode = CriticalsSelectionMode.SMART,
            physicalJumpHeld = true,
            transformedJump = true,
            onGround = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = false,
        )
    }

    private fun assertReset(coordinator: KillAuraCriticalCycleCoordinator, message: String) {
        assertFalse(coordinator.manualJumpCycleActive, message)
        assertFalse(coordinator.pendingReadyClick, message)
        assertFalse(coordinator.pipelineInProgress, message)
    }

    private fun decision(
        mode: CriticalsSelectionMode = CriticalsSelectionMode.SMART,
        physicalJumpKeyPressed: Boolean,
        transformedPlayerInputJump: Boolean,
        manualJumpCycleActive: Boolean,
        state: CriticalState,
        naturalCriticalEnvironmentPossible: Boolean = true,
        gliding: Boolean = false,
        serverSprinting: Boolean = state.sprinting,
        smartHeuristicPermitsAttack: Boolean = true,
    ) = decideCriticalSelection(
        CriticalsSelectionContext(
            mode = mode,
            physicalJumpKeyPressed = physicalJumpKeyPressed,
            transformedPlayerInputJump = transformedPlayerInputJump,
            manualJumpCycleActive = manualJumpCycleActive,
            naturalCriticalEnvironmentPossible = naturalCriticalEnvironmentPossible,
            gliding = gliding,
            onGround = state.onGround,
            fallDistance = state.fallDistance,
            verticalVelocity = state.verticalVelocity,
            sprinting = state.sprinting,
            serverSprinting = serverSprinting,
            attackStrengthReady = state.attackStrengthReady,
            wouldDoCriticalHit = naturalCriticalEnvironmentPossible && !gliding && state.wouldDoCriticalHit,
            wouldDoCriticalHitIgnoringSprint = naturalCriticalEnvironmentPossible && !gliding &&
                state.wouldDoCriticalHitIgnoringSprint,
            smartHeuristicPermitsAttack = smartHeuristicPermitsAttack,
        )
    )

    private data class CriticalState(
        val onGround: Boolean,
        val fallDistance: Float,
        val verticalVelocity: Double,
        val sprinting: Boolean = false,
        val attackStrengthReady: Boolean = true,
    ) {
        val wouldDoCriticalHit: Boolean
            get() = attackStrengthReady && !sprinting && !onGround && fallDistance > 0.0f
        val wouldDoCriticalHitIgnoringSprint: Boolean
            get() = attackStrengthReady && !onGround && fallDistance > 0.0f
    }

    private companion object {
        val PlayerIdentity = Any()
        val WorldIdentity = Any()
        val TargetIdentity = Any()

        val Grounded = CriticalState(onGround = true, fallDistance = 0.0f, verticalVelocity = 0.0)
        val Rising = CriticalState(onGround = false, fallDistance = 0.0f, verticalVelocity = 0.24)
        val Apex = CriticalState(onGround = false, fallDistance = 0.0f, verticalVelocity = 0.0)
        val FallingCritical = CriticalState(onGround = false, fallDistance = 0.12f, verticalVelocity = -0.16)
    }
}
