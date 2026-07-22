package net.ccbluex.liquidbounce.features.module.modules.movement.noslow

import java.nio.file.Files
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GrimTickNoSlowControllerTest {

    @Test
    fun noItemUseIsInactiveAndVanillaSafe() {
        val controller = GrimTickNoSlowController()

        assertFalse(begin(controller, cycle = 1, active = false))
        assertFalse(controller.decision())
        assertEquals(GrimTickNoSlowController.Phase.INACTIVE, controller.phase)
        assertFalse(controller.completeCycle(1))
    }

    @Test
    fun warmupThenFullSpeedAndRecoveryAlternate() {
        val controller = GrimTickNoSlowController()

        assertEquals(
            listOf(false, false, true, false, true, false, true),
            (1L..7L).map { cycle ->
                begin(controller, cycle).also { assertTrue(controller.completeCycle(cycle)) }
            },
        )
    }

    @Test
    fun everyCallerReadsOneImmutableCycleDecision() {
        val controller = warmedController()
        assertTrue(begin(controller, 3))

        val input = controller.decision()
        val pose = controller.decision()
        val sprintStart = controller.decision()
        val sprintStop = controller.decision()

        assertEquals(listOf(true, true, true, true), listOf(input, pose, sprintStart, sprintStop))
        assertEquals(GrimTickNoSlowController.Phase.FAST, controller.phase)
    }

    @Test
    fun mutableUseTimeInsideCycleCannotChangeTheLatch() {
        val legacy = LegacyConsumableCache(ready = true)
        assertTrue(legacy.decision(CacheKey(tick = 20, ticksUsingItem = 2)))
        assertFalse(legacy.decision(CacheKey(tick = 20, ticksUsingItem = 3)))

        val controller = warmedController()
        assertTrue(begin(controller, 3))
        repeat(8) {
            // The controller deliberately has no mutable use-time cache key.
            assertTrue(controller.decision())
        }
    }

    @Test
    fun phaseAdvancesOnceAndDuplicatePostIsIgnored() {
        val controller = GrimTickNoSlowController()

        assertFalse(begin(controller, 1))
        assertTrue(controller.completeCycle(1))
        assertFalse(controller.completeCycle(1))
        assertFalse(controller.completeCycle(2))

        assertFalse(begin(controller, 2))
        assertTrue(controller.completeCycle(2))
        assertFalse(controller.completeCycle(2))

        assertTrue(begin(controller, 3))
    }

    @Test
    fun missingPostFailsClosedAndCannotCreateFastFast() {
        val controller = warmedController()

        assertTrue(begin(controller, 3))
        // Cycle 3 never reaches POST. Cycle 4 must be a real recovery.
        assertFalse(begin(controller, 4))
        assertEquals(GrimTickNoSlowController.Phase.RECOVERY, controller.phase)
        assertTrue(controller.completeCycle(4))
        assertTrue(begin(controller, 5))
    }

    @Test
    fun pausedCancelledAndNoWorldPathsDoNotAdvance() {
        val controller = warmedController()
        assertTrue(begin(controller, 3))

        // No POST represents an interrupted/cancelled cycle; a later begin fails closed.
        assertFalse(begin(controller, 4))
        controller.reset()
        assertFalse(begin(controller, 5, active = false))
        assertFalse(begin(controller, 6, active = false, playerOverride = null))
        assertFalse(controller.decision())
    }

    @Test
    fun useIdentityAndLifecycleChangesRestartWarmup() {
        val contexts = listOf(
            Context(player = OTHER_PLAYER),
            Context(hand = OFF_HAND),
            Context(item = OTHER_ITEM),
            Context(useSessionId = 99),
            Context(settingsFingerprint = 99),
        )

        contexts.forEachIndexed { index, changed ->
            val controller = warmedController()
            assertTrue(begin(controller, 3))
            assertFalse(begin(controller, 4, context = changed), "identity case $index")
            assertEquals(GrimTickNoSlowController.Phase.WARMUP, controller.phase)
        }

        val useEndRestart = warmedController()
        assertTrue(begin(useEndRestart, 3))
        assertFalse(begin(useEndRestart, 4, active = false))
        assertFalse(begin(useEndRestart, 5, context = Context(useSessionId = 101)))

        val lifecycle = warmedController()
        lifecycle.reset() // disable, mode change, world, disconnect, death, or player replacement
        assertFalse(begin(lifecycle, 10))
        assertEquals(GrimTickNoSlowController.Phase.WARMUP, lifecycle.phase)
    }

    @Test
    fun ridingFallFlyingAndUnselectedItemsAreVanillaSafe() {
        val controller = warmedController()

        assertFalse(begin(controller, 3, active = false)) // riding/fall flying/use end
        assertFalse(begin(controller, 4, eligible = false)) // hand/item selection
        assertFalse(controller.decision())
    }

    @Test
    fun handItemMovementAndSprintVariantsDoNotWeakenFastPhase() {
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
            // Direction, diagonal/straight, ground/air, and sprint are intentionally
            // absent from the controller: eligible fast cycles stay fully unslowed.
            assertTrue(begin(controller, 3, context = context))
        }
    }

    @Test
    fun grimConsecutivePredictionModelRequiresRealRecovery() {
        val model = GrimNoSlowModel(offsetToFlag = 0.001)

        assertFalse(model.checkedPrediction(offset = 0.1))
        assertTrue(model.flaggedLastTick)
        assertFalse(model.checkedPrediction(offset = 0.0))
        assertFalse(model.flaggedLastTick)
        assertFalse(model.checkedPrediction(offset = 0.1))
        assertTrue(model.checkedPrediction(offset = 0.1))
    }

    @Test
    fun controllerSequenceNeverFeedsTwoFastCheckedPredictions() {
        val controller = GrimTickNoSlowController()
        val model = GrimNoSlowModel(offsetToFlag = 0.001)
        val phases = mutableListOf<Boolean>()

        for (cycle in 1L..20L) {
            val fast = begin(controller, cycle)
            phases += fast
            assertFalse(model.checkedPrediction(if (fast) 0.1 else 0.0))
            assertTrue(controller.completeCycle(cycle))
        }

        assertFalse(phases.zipWithNext().any { (left, right) -> left && right })
        assertEquals(0, model.setbacks)
    }

    @Test
    fun productionWiringUsesMovementBeginAndNetworkPostWithoutTraceSpam() {
        val module = Files.readString(sourcePath("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/noslow/ModuleNoSlow.kt"))
        val mixin = Files.readString(sourcePath("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinClientPlayerEntity.java"))

        assertTrue(module.contains("handler<PlayerMovementTickEvent>"))
        assertTrue(module.contains("event.state == EventState.POST"))
        assertTrue(module.contains("grimTickController.completeCycle(movementCycleSequence)"))
        assertFalse(module.contains("updateGrimTickState"))
        assertFalse(module.contains("consumeGrimTickWindow"))
        assertFalse(module.contains("GRIMTICK_TRACE"))
        assertEquals(4, Regex("shouldBypassItemSlowdownForCurrentTick\\(\\)").findAll(mixin).count())
    }

    private fun warmedController(context: Context = Context()) = GrimTickNoSlowController().also { controller ->
        assertFalse(begin(controller, 1, context = context))
        assertTrue(controller.completeCycle(1))
        assertFalse(begin(controller, 2, context = context))
        assertTrue(controller.completeCycle(2))
    }

    private fun begin(
        controller: GrimTickNoSlowController,
        cycle: Long,
        active: Boolean = true,
        eligible: Boolean = true,
        context: Context = Context(),
        playerOverride: Any? = context.player,
    ) = controller.beginCycle(
        cycleId = cycle,
        active = active,
        eligible = eligible,
        playerIdentity = playerOverride,
        handIdentity = context.hand,
        itemIdentity = context.item,
        useSessionId = context.useSessionId,
        settingsFingerprint = context.settingsFingerprint,
    )

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
        val settingsFingerprint: Int = 1,
    )

    private data class CacheKey(val tick: Int, val ticksUsingItem: Int)

    private class LegacyConsumableCache(private var ready: Boolean) {
        private var key: CacheKey? = null
        private var cached = false

        fun decision(newKey: CacheKey): Boolean {
            if (key == newKey) return cached
            key = newKey
            cached = ready
            ready = false
            return cached
        }
    }

    private class GrimNoSlowModel(private val offsetToFlag: Double) {
        var flaggedLastTick = false
            private set
        var setbacks = 0
            private set

        fun checkedPrediction(offset: Double): Boolean {
            if (offset > offsetToFlag) {
                if (flaggedLastTick) setbacks++
                val setback = flaggedLastTick
                flaggedLastTick = true
                return setback
            }

            flaggedLastTick = false
            return false
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
