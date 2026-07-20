package net.ccbluex.liquidbounce.features.module.modules.combat.criticals

import java.nio.file.Files
import java.nio.file.Path
import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode
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
        assertTrue(
            decision(
                mode = CriticalsSelectionMode.IGNORE,
                physicalJumpKeyPressed = true,
                state = Grounded,
                smartHeuristicPermitsAttack = false,
            ).permitsAttack
        )

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
        val cooldown = source("src/main/kotlin/net/ccbluex/liquidbounce/utils/clicking/ItemCooldown.kt")

        assertTrue(criticals.contains("fun isCriticalHit(target: Entity): Boolean = evaluateCriticalHit(target).permitsAttack"))
        assertTrue(criticals.contains("!shouldWaitForCrit(target, ignoreState = true)"))
        assertTrue(criticals.contains("physicalJumpKeyPressed = mc.options.jumpKey.isPressed"))
        assertTrue(criticals.contains("wouldDoCriticalHit = wouldDoNaturalCritical"))
        assertTrue(killAura.contains("MAX_SMART_CRITICAL_DIAGNOSTICS"))
        assertTrue(killAura.contains("\"physicalJumpKeyPressed\""))
        assertTrue(killAura.contains("\"clickConsumed\""))
        assertTrue(killAura.contains("\"attackEmitted\""))
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
            val phase = JumpCycle[(tick + phaseOffset) % JumpCycle.size]
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
            val decision = decision(
                physicalJumpKeyPressed = true,
                state = phase,
                smartHeuristicPermitsAttack = true,
            )

            if (decision.permitsAttack) {
                attacks += readyWindow
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
            attackStrengthReady = state.attackStrengthReady,
            wouldDoCriticalHit = naturalCriticalEnvironmentPossible && !gliding && state.wouldDoCriticalHit,
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
