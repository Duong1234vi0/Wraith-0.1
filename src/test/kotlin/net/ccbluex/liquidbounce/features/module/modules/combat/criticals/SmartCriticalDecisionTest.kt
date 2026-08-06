package net.ccbluex.liquidbounce.features.module.modules.combat.criticals

import java.nio.file.Files
import java.nio.file.Path
import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.CriticalAttackScope
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.planKillAuraCriticalSprintStop
import net.ccbluex.liquidbounce.utils.clicking.calculateItemCooldownProgress
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SmartCriticalDecisionTest {

    @Test
    fun criticalsModuleDisabledDoesNotDisableKillAuraSmartManualJumpGate() {
        val decision = decision(
            physicalJumpKeyPressed = true,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )

        assertTrue(decision.manualJumpGateActive)
        assertFalse(decision.permitsAttack)
    }

    @Test
    fun smartWithoutJumpHeldKeepsExistingPermissiveAndWaitingHeuristic() {
        assertTrue(
            decision(
                physicalJumpKeyPressed = false,
                state = Grounded,
                smartHeuristicPermitsAttack = true,
            ).permitsAttack
        )

        assertFalse(
            decision(
                physicalJumpKeyPressed = false,
                state = Apex,
                smartHeuristicPermitsAttack = false,
            ).permitsAttack
        )
    }

    @Test
    fun smartWithJumpHeldRequiresTheRealNaturalCriticalPredicate() {
        assertFalse(decision(physicalJumpKeyPressed = true, state = Grounded).permitsAttack)
        assertFalse(decision(physicalJumpKeyPressed = true, state = Rising).permitsAttack)
        assertFalse(decision(physicalJumpKeyPressed = true, state = Apex).permitsAttack)
        assertTrue(decision(physicalJumpKeyPressed = true, state = FallingCritical).permitsAttack)

        assertFalse(decision(physicalJumpKeyPressed = true, state = FallingCritical.copy(sprinting = true)).permitsAttack)
        assertTrue(decision(physicalJumpKeyPressed = true, state = FallingCritical.copy(sprinting = false)).permitsAttack)
    }

    @Test
    fun smartHeldJumpPreparesSprintOnlyInTheDescendingCriticalWindow() {
        val simulation = PreparedAttackSimulation(CriticalsSelectionMode.SMART, physicalJumpKeyPressed = true)

        simulation.tick(Grounded.copy(sprinting = true), clickReady = true)
        simulation.tick(Rising.copy(sprinting = true), clickReady = false)

        assertEquals(0, simulation.attacks)
        assertEquals(0, simulation.stopSprintCommands)
        assertEquals(0, simulation.clickConsumptions)
        assertEquals(1, simulation.pendingClickId)

        simulation.tick(FallingCritical.copy(sprinting = true), clickReady = false)

        assertEquals(1, simulation.attacks)
        assertEquals(1, simulation.stopSprintCommands)
        assertEquals(1, simulation.clickConsumptions)
        assertEquals(0, simulation.physicalAttackKeyPresses)
        assertEquals(null, simulation.pendingClickId)
    }

    @Test
    fun alwaysRemainsCriticalOnlyAndAutomaticallyPreparesAReadyFallingAttack() {
        val simulation = PreparedAttackSimulation(CriticalsSelectionMode.ALWAYS, physicalJumpKeyPressed = false)

        simulation.tick(Grounded.copy(sprinting = true), clickReady = true)
        simulation.tick(Rising.copy(sprinting = true), clickReady = false)
        assertEquals(0, simulation.attacks)
        assertEquals(0, simulation.clickConsumptions)

        val fallingWhileSprinting = decision(
            mode = CriticalsSelectionMode.ALWAYS,
            physicalJumpKeyPressed = false,
            state = FallingCritical.copy(sprinting = true),
        )
        assertFalse(fallingWhileSprinting.permitsAttack)
        assertTrue(fallingWhileSprinting.requiresKillAuraSprintStop)
        assertEquals(0, simulation.attacks)

        val serverStillSprinting = decision(
            mode = CriticalsSelectionMode.ALWAYS,
            physicalJumpKeyPressed = false,
            state = FallingCritical.copy(sprinting = false),
            serverSprinting = true,
        )
        assertFalse(serverStillSprinting.permitsAttack)
        assertTrue(serverStillSprinting.requiresKillAuraSprintStop)

        simulation.tick(FallingCritical.copy(sprinting = true), clickReady = false)

        assertEquals(1, simulation.attacks)
        assertEquals(1, simulation.stopSprintCommands)
        assertEquals(1, simulation.clickConsumptions)
        assertEquals(0, simulation.physicalAttackKeyPresses)
    }

    @Test
    fun repeatedBunnyHopCyclesCanEachProduceExactlyOnePreparedCritical() {
        val simulation = PreparedAttackSimulation(CriticalsSelectionMode.SMART, physicalJumpKeyPressed = true)

        repeat(6) {
            simulation.tick(Grounded.copy(sprinting = true), clickReady = true)
            simulation.tick(Rising.copy(sprinting = true), clickReady = false)
            simulation.tick(FallingCritical.copy(sprinting = true), clickReady = false)
        }

        assertEquals(6, simulation.attacks)
        assertEquals(6, simulation.stopSprintCommands)
        assertEquals(6, simulation.clickConsumptions)
        assertEquals(6, simulation.attackedClickIds.distinct().size)
        assertEquals(0, simulation.physicalAttackKeyPresses)
    }

    @Test
    fun sprintStopPlanSynchronizesOnlyWhatIsStillSprinting() {
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
    fun secondValidationCannotDuplicateOrCancelThePreparedAttack() {
        val simulation = PreparedAttackSimulation(CriticalsSelectionMode.ALWAYS, physicalJumpKeyPressed = false)

        simulation.tick(FallingCritical.copy(sprinting = true), clickReady = true)
        simulation.tick(FallingCritical.copy(sprinting = false), clickReady = false)

        assertEquals(1, simulation.attacks)
        assertEquals(1, simulation.clickConsumptions)
        assertEquals(1, simulation.stopSprintCommands)
        assertEquals(listOf(1), simulation.attackedClickIds)
    }

    @Test
    fun criticalPreparationScopeRejectsTargetWorldPlayerAndLifecycleChanges() {
        val player = Any()
        val world = Any()
        val target = Any()
        val scope = CriticalAttackScope(player, world, target)

        fun matches(
            currentPlayer: Any? = player,
            currentWorld: Any? = world,
            currentTarget: Any? = target,
            targetWorld: Any? = world,
            running: Boolean = true,
            removed: Boolean = false,
        ) = scope.matches(currentPlayer, currentWorld, currentTarget, targetWorld, running, removed)

        assertTrue(matches())
        assertFalse(matches(currentTarget = null), "target loss")
        assertFalse(matches(currentTarget = Any()), "target change")
        assertFalse(matches(currentPlayer = Any()), "player replacement")
        assertFalse(matches(currentWorld = Any()), "world replacement")
        assertFalse(matches(targetWorld = Any()), "target from another world")
        assertFalse(matches(running = false), "module disable")
        assertFalse(matches(removed = true), "removed target")
    }

    @Test
    fun schedulerRetentionDoesNotConsumeRejectedManualJumpSmartDecisions() {
        val scheduler = RetainedClickSimulation()

        scheduler.tick(Grounded)
        scheduler.tick(Rising)

        assertEquals(0, scheduler.newCooldownCalls)
        assertEquals(0, scheduler.lastClickTimeUpdates)
        assertEquals(0, scheduler.attackTickerResets)
        assertEquals(0, scheduler.attacks)
        assertEquals(1, scheduler.pendingClickId)

        scheduler.tick(FallingCritical)

        assertEquals(1, scheduler.newCooldownCalls)
        assertEquals(1, scheduler.lastClickTimeUpdates)
        assertEquals(1, scheduler.attackTickerResets)
        assertEquals(1, scheduler.attacks)
        assertEquals(null, scheduler.pendingClickId)
    }

    @Test
    fun modesKeepTheirOriginalMeaning() {
        val ignore = decision(
            mode = CriticalsSelectionMode.IGNORE,
            physicalJumpKeyPressed = true,
            state = FallingCritical.copy(sprinting = true),
            smartHeuristicPermitsAttack = false,
        )
        assertTrue(ignore.permitsAttack)
        assertFalse(ignore.requiresKillAuraSprintStop)

        assertFalse(
            decision(
                mode = CriticalsSelectionMode.ALWAYS,
                physicalJumpKeyPressed = false,
                state = Grounded,
                smartHeuristicPermitsAttack = true,
            ).permitsAttack
        )
        assertTrue(
            decision(
                mode = CriticalsSelectionMode.ALWAYS,
                physicalJumpKeyPressed = false,
                state = FallingCritical,
                smartHeuristicPermitsAttack = false,
            ).permitsAttack
        )

        assertTrue(
            decision(
                mode = CriticalsSelectionMode.SMART,
                physicalJumpKeyPressed = false,
                state = Grounded,
                smartHeuristicPermitsAttack = true,
            ).permitsAttack
        )
    }

    @Test
    fun criticalImpossibleStatesKeepSmartPermissiveInsteadOfWaitingForever() {
        val criticalImpossibleStates = listOf(
            "water",
            "lava",
            "climbing",
            "vehicle",
            "blindness",
            "slow falling",
            "levitation",
            "web",
            "no gravity",
            "flying",
        )

        for (name in criticalImpossibleStates) {
            val decision = decision(
                physicalJumpKeyPressed = true,
                naturalCriticalEnvironmentPossible = false,
                state = Grounded,
                smartHeuristicPermitsAttack = true,
            )

            assertFalse(decision.manualJumpGateActive, name)
            assertTrue(decision.permitsAttack, name)
        }

        val gliding = decision(
            physicalJumpKeyPressed = true,
            naturalCriticalEnvironmentPossible = true,
            gliding = true,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )

        assertFalse(gliding.manualJumpGateActive)
        assertTrue(gliding.permitsAttack)
    }

    @Test
    fun physicalJumpKeyControlsManualIntentAndTransformedInputDoesNot() {
        assertTrue(
            decision(
                physicalJumpKeyPressed = true,
                transformedPlayerInputJump = false,
                state = Grounded,
                smartHeuristicPermitsAttack = true,
            ).manualJumpGateActive
        )

        val injectedJumpOnly = decision(
            physicalJumpKeyPressed = false,
            transformedPlayerInputJump = true,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )
        assertFalse(injectedJumpOnly.manualJumpGateActive)
        assertTrue(injectedJumpOnly.permitsAttack)

        val releasedJump = decision(
            physicalJumpKeyPressed = false,
            transformedPlayerInputJump = false,
            state = Grounded,
            smartHeuristicPermitsAttack = true,
        )
        assertFalse(releasedJump.manualJumpGateActive)
        assertTrue(releasedJump.permitsAttack)
    }

    @Test
    fun continuousJumpCadenceNeverReleasesGroundedOrRisingReadyWindows() {
        val thresholds = listOf(0.85f, 1.0f, 1.05f)
        val weaponPeriods = listOf(
            "sword-like" to 12.5f,
            "slower axe-like" to 20.0f,
        )

        for ((weaponName, period) in weaponPeriods) {
            for (threshold in thresholds) {
                for (phaseOffset in JumpCycle.indices) {
                    val result = simulateContinuousJump(
                        period = period,
                        threshold = threshold,
                        phaseOffset = phaseOffset,
                    )

                    assertTrue(result.cooldownReadyWindows >= REQUIRED_READY_WINDOWS, result.label(weaponName, threshold))
                    assertTrue(result.attacks.isNotEmpty(), result.label(weaponName, threshold))
                    assertTrue(result.rejectedReadyWindows.isNotEmpty(), result.label(weaponName, threshold))

                    for (attack in result.attacks) {
                        assertFalse(attack.phase.onGround, attack.describe(result.label(weaponName, threshold)))
                        assertTrue(attack.phase.fallDistance > 0.0f, attack.describe(result.label(weaponName, threshold)))
                        assertEquals(1, result.attacks.count { it.pendingClickId == attack.pendingClickId })
                    }
                }
            }
        }
    }

    @Test
    fun productionStillUsesTheSmartHelperAndDoesNotChangeCooldownOrPacketCriticalPaths() {
        val criticals = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/criticals/ModuleCriticals.kt")
        val killAura = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt")
        val killAuraClicker = source(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraClicker.kt"
        )
        val cooldown = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/clicking/ItemCooldown.kt")

        assertTrue(criticals.contains("fun isCriticalHit(target: Entity): Boolean = evaluateCriticalHit(target).permitsAttack"))
        assertTrue(criticals.contains("!shouldWaitForCrit(target, ignoreState = true)"))
        assertTrue(criticals.contains("physicalJumpKeyPressed = mc.options.jumpKey.isPressed"))
        assertTrue(criticals.contains("wouldDoCriticalHit = wouldDoNaturalCritical"))
        assertTrue(criticals.contains("wouldDoCriticalHitIgnoringSprint = wouldDoNaturalCriticalIgnoringSprint"))
        assertTrue(criticals.contains("clicker.isClickTick || clicker.willClickAt(1)"))
        assertTrue(killAura.contains("prepareCriticalAttackIfNeeded(target, criticalAttackScope)"))
        assertTrue(killAura.contains("ServerboundPlayerCommandPacket.Action.STOP_SPRINTING"))
        assertTrue(killAura.contains("player.setSprinting(false)"))
        assertTrue(killAura.contains("player.wasSprinting = false"))
        assertTrue(killAura.contains("target.attack(true, keepSprint && !shouldBlockSprinting)"))
        assertFalse(killAura.contains("MAX_SMART_CRITICAL_DIAGNOSTICS"))
        assertFalse(killAura.contains("Action.START_SPRINTING"))
        assertTrue(killAuraClicker.contains("simulateKeybindPress = false"))
        assertFalse(killAura.contains("options.attackKey.isPressed"))
        assertTrue(cooldown.contains("ITEM_COOLDOWN_SAMPLE_TICK_DELTA"))
        assertFalse(killAura.contains("ServerboundMovePlayerPacket"))
    }

    private data class CriticalState(
        val name: String,
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

    private data class ReadyWindow(
        val tick: Int,
        val phase: CriticalState,
        val pendingClickId: Int,
    ) {
        fun describe(prefix: String) =
            "$prefix tick=$tick pendingClickId=$pendingClickId phase=${phase.name} fallDistance=${phase.fallDistance}"
    }

    private data class CadenceResult(
        val phaseOffset: Int,
        val cooldownReadyWindows: Int,
        val attacks: List<ReadyWindow>,
        val rejectedReadyWindows: List<ReadyWindow>,
    ) {
        fun label(weaponName: String, threshold: Float) =
            "weapon=$weaponName threshold=$threshold phaseOffset=$phaseOffset"
    }

    private inner class RetainedClickSimulation {
        var pendingClickId: Int? = null
            private set
        private var nextPendingClickId = 0
        var newCooldownCalls = 0
            private set
        var lastClickTimeUpdates = 0
            private set
        var attackTickerResets = 0
            private set
        var attacks = 0
            private set

        fun tick(state: CriticalState) {
            if (pendingClickId == null) {
                pendingClickId = ++nextPendingClickId
            }

            val decision = decision(
                physicalJumpKeyPressed = true,
                state = state,
                smartHeuristicPermitsAttack = true,
            )
            if (!decision.permitsAttack) {
                return
            }

            attacks++
            newCooldownCalls++
            lastClickTimeUpdates++
            attackTickerResets++
            pendingClickId = null
        }
    }

    private inner class PreparedAttackSimulation(
        private val mode: CriticalsSelectionMode,
        private val physicalJumpKeyPressed: Boolean,
    ) {
        var pendingClickId: Int? = null
            private set
        private var nextPendingClickId = 0
        var attacks = 0
            private set
        var clickConsumptions = 0
            private set
        var stopSprintCommands = 0
            private set
        val physicalAttackKeyPresses = 0
        val attackedClickIds = mutableListOf<Int>()

        fun tick(state: CriticalState, clickReady: Boolean) {
            if (clickReady && pendingClickId == null) {
                pendingClickId = ++nextPendingClickId
            }
            val activeClick = pendingClickId ?: return

            var currentState = state
            var serverSprinting = state.sprinting
            var currentDecision = decision(
                mode = mode,
                physicalJumpKeyPressed = physicalJumpKeyPressed,
                state = currentState,
                serverSprinting = serverSprinting,
            )
            if (!currentDecision.permitsAttack && currentDecision.requiresKillAuraSprintStop) {
                val stopPlan = planKillAuraCriticalSprintStop(
                    localSprinting = currentState.sprinting,
                    serverSprinting = serverSprinting,
                )
                if (stopPlan.sendStopCommand) {
                    stopSprintCommands++
                }
                if (stopPlan.clearLocalSprint) {
                    currentState = currentState.copy(sprinting = false)
                }
                if (stopPlan.sendStopCommand) {
                    serverSprinting = false
                }
                currentDecision = decision(
                    mode = mode,
                    physicalJumpKeyPressed = physicalJumpKeyPressed,
                    state = currentState,
                    serverSprinting = serverSprinting,
                )
            }
            if (!currentDecision.permitsAttack) {
                return
            }

            attacks++
            clickConsumptions++
            attackedClickIds += activeClick
            pendingClickId = null
        }
    }

    private fun simulateContinuousJump(
        period: Float,
        threshold: Float,
        phaseOffset: Int,
    ): CadenceResult {
        var lastAttackedTicks = 0
        var pendingClickId: Int? = null
        var nextPendingClickId = 0
        var cooldownReadyWindows = 0
        var tick = 0
        val attacks = mutableListOf<ReadyWindow>()
        val rejected = mutableListOf<ReadyWindow>()

        while (cooldownReadyWindows < REQUIRED_READY_WINDOWS) {
            tick++
            lastAttackedTicks++
            val phase = JumpCycle[(tick + phaseOffset) % JumpCycle.size].copy(sprinting = true)
            val cooldownReady = calculateItemCooldownProgress(lastAttackedTicks, 0, period) >= threshold
            if (!cooldownReady) {
                continue
            }

            cooldownReadyWindows++
            if (pendingClickId == null) {
                pendingClickId = ++nextPendingClickId
            }
            val activePendingClickId = pendingClickId ?: error("pending click was not initialized")

            val readyWindow = ReadyWindow(tick, phase, activePendingClickId)
            var finalPhase = phase
            var serverSprinting = true
            var decision = decision(
                physicalJumpKeyPressed = true,
                state = finalPhase,
                serverSprinting = serverSprinting,
                smartHeuristicPermitsAttack = true,
            )
            if (decision.requiresKillAuraSprintStop) {
                val stopPlan = planKillAuraCriticalSprintStop(
                    localSprinting = finalPhase.sprinting,
                    serverSprinting = true,
                )
                assertTrue(stopPlan.sendStopCommand)
                finalPhase = finalPhase.copy(sprinting = false)
                serverSprinting = false
                decision = decision(
                    physicalJumpKeyPressed = true,
                    state = finalPhase,
                    serverSprinting = serverSprinting,
                    smartHeuristicPermitsAttack = true,
                )
            }

            if (decision.permitsAttack) {
                attacks += readyWindow.copy(phase = finalPhase)
                pendingClickId = null
                lastAttackedTicks = 0
            } else {
                rejected += readyWindow
            }

            assertTrue(tick < 2_000, "simulation did not converge")
        }

        return CadenceResult(
            phaseOffset = phaseOffset,
            cooldownReadyWindows = cooldownReadyWindows,
            attacks = attacks,
            rejectedReadyWindows = rejected,
        )
    }

    private fun decision(
        mode: CriticalsSelectionMode = CriticalsSelectionMode.SMART,
        physicalJumpKeyPressed: Boolean,
        transformedPlayerInputJump: Boolean = physicalJumpKeyPressed,
        naturalCriticalEnvironmentPossible: Boolean = true,
        gliding: Boolean = false,
        state: CriticalState,
        serverSprinting: Boolean = state.sprinting,
        smartHeuristicPermitsAttack: Boolean = true,
    ) = decideCriticalSelection(
        CriticalsSelectionContext(
            mode = mode,
            physicalJumpKeyPressed = physicalJumpKeyPressed,
            transformedPlayerInputJump = transformedPlayerInputJump,
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

    private fun source(path: String) = Files.readString(Path.of(path))

    private companion object {
        const val REQUIRED_READY_WINDOWS = 20

        val Grounded = CriticalState(
            name = "grounded",
            onGround = true,
            fallDistance = 0.0f,
            verticalVelocity = 0.0,
        )
        val Rising = CriticalState(
            name = "rising",
            onGround = false,
            fallDistance = 0.0f,
            verticalVelocity = 0.24,
        )
        val Apex = CriticalState(
            name = "apex",
            onGround = false,
            fallDistance = 0.0f,
            verticalVelocity = 0.0,
        )
        val FallingCritical = CriticalState(
            name = "falling",
            onGround = false,
            fallDistance = 0.12f,
            verticalVelocity = -0.16,
        )
        val Landing = CriticalState(
            name = "landing",
            onGround = true,
            fallDistance = 0.0f,
            verticalVelocity = 0.0,
        )

        val JumpCycle = listOf(
            Grounded,
            Rising,
            CriticalState("late-rising", onGround = false, fallDistance = 0.0f, verticalVelocity = 0.10),
            Apex,
            CriticalState("early-falling", onGround = false, fallDistance = 0.02f, verticalVelocity = -0.05),
            FallingCritical,
            CriticalState("late-falling", onGround = false, fallDistance = 0.28f, verticalVelocity = -0.22),
            Landing,
        )
    }
}
