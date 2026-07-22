package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class SilentClickGuiMorphTest {
    private val source = SilentClickGuiLayout.moduleCardRect(0)
    private val target = SilentClickGuiLayout.sidePanel

    @Test
    fun `opening and closing geometry are exact floating point endpoints`() {
        assertRect(source, SilentClickGuiMorph.morphRect(source, target, 0f, 300L))
        assertRect(target, SilentClickGuiMorph.morphRect(source, target, 1f, 300L))
        assertRect(target, SilentClickGuiMorph.morphRect(target, source, 0f, 260L))
        assertRect(source, SilentClickGuiMorph.morphRect(target, source, 1f, 260L))
        listOf(0f, .01f, .25f, .5f, .75f, .99f, 1f).forEach { progress ->
            assertFiniteRect(SilentClickGuiMorph.morphRect(source, target, progress, 300L))
            assertFiniteRect(SilentClickGuiMorph.morphRect(target, source, progress, 260L))
            assertTrue(SilentClickGuiMorph.radius(6f, 8f, progress) in 6f..8f)
        }
    }

    @Test
    fun `flight arc scales with distance and stays restrained`() {
        val nearby = SilentClickGuiLayout.moduleCardRect(1)
        val far = SilentClickGuiLayout.moduleCardRect(0)
        assertTrue(SilentClickGuiMorph.arcHeight(nearby, target) in 5f..11f)
        assertTrue(SilentClickGuiMorph.arcHeight(far, target) in 5f..11f)
        assertTrue(SilentClickGuiMorph.arcHeight(far, target) > SilentClickGuiMorph.arcHeight(nearby, target))
        assertTrue(SilentClickGuiMorph.arcHeight(far, target) < 11f)
    }

    @Test
    fun `easing functions are exact bounded finite and monotonic`() {
        val easings = listOf<(Float) -> Float>(
            SilentClickGuiMorph::positionEase,
            SilentClickGuiMorph::sizeEase,
            SilentClickGuiMorph::alphaEase,
            SilentClickGuiMorph::smooth,
        )
        easings.forEach { easing ->
            assertEquals(0f, easing(0f))
            assertEquals(1f, easing(1f))
            var previous = 0f
            repeat(1_001) { index ->
                val value = easing(index / 1_000f)
                assertTrue(value.isFinite())
                assertTrue(value in 0f..1f)
                assertTrue(value + 0.000001f >= previous)
                previous = value
            }
        }
        assertNotEquals(SilentClickGuiMorph.positionEase(.5f), SilentClickGuiMorph.sizeEase(.5f))
        assertTrue(SilentClickGuiMorph.heightEase(.2f, 300L) < SilentClickGuiMorph.sizeEase(.2f))
        assertEquals(0f, SilentClickGuiMorph.panelSettingsAlpha(.18f))
        assertTrue(SilentClickGuiMorph.panelHeaderAlpha(.3f) > SilentClickGuiMorph.panelSettingsAlpha(.3f))
    }

    @Test
    fun `explicit stages own their complete configured durations`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val first = module("First")
        val second = module("Second")
        controller.open(first, Category.COMBAT, 0, source)
        val openingTravel = controller.activeTravelDurationMs
        assertEquals(ModuleSettingsMorphPhase.OPEN_PREP, controller.phase)
        assertEquals(ModuleSettingsMorphController.OPEN_PREP_MS, controller.stageDurationMs())
        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN_TRAVEL, controller.phase)
        assertEquals(openingTravel, controller.stageDurationMs())
        now += ms(openingTravel)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN_SETTLE, controller.phase)
        assertEquals(ModuleSettingsMorphController.OPEN_SETTLE_MS, controller.stageDurationMs())
        now += ms(ModuleSettingsMorphController.OPEN_SETTLE_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)

        val secondCard = SilentClickGuiLayout.moduleCardRect(1)
        controller.switch(second, Category.COMBAT, 1, secondCard)
        assertEquals(ModuleSettingsMorphController.SWITCH_FADE_MS, controller.stageDurationMs())
        now += ms(ModuleSettingsMorphController.SWITCH_FADE_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.SWITCH_PREP, controller.phase)
        assertEquals(ModuleSettingsMorphController.SWITCH_PREP_MS, controller.stageDurationMs())
        now += ms(ModuleSettingsMorphController.SWITCH_PREP_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.SWITCH_TRAVEL, controller.phase)
        val switchTravel = controller.activeTravelDurationMs
        now += ms(switchTravel)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.SWITCH_SETTLE, controller.phase)
        now += ms(ModuleSettingsMorphController.SWITCH_SETTLE_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)

        controller.requestClose()
        controller.setCloseTarget(secondCard, needsReveal = false)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_FADE_OUT, controller.phase)
        assertEquals(ModuleSettingsMorphController.CLOSE_FADE_MS, controller.stageDurationMs())
        now += ms(ModuleSettingsMorphController.CLOSE_FADE_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_TRAVEL, controller.phase)
        val closeTravel = controller.activeTravelDurationMs
        now += ms(closeTravel)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_SETTLE, controller.phase)
        assertEquals(ModuleSettingsMorphController.CLOSE_SETTLE_MS, controller.stageDurationMs())
        now += ms(ModuleSettingsMorphController.CLOSE_SETTLE_MS)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.CLOSED, controller.phase)

        assertEquals(
            ModuleSettingsMorphController.OPEN_PREP_MS + openingTravel + ModuleSettingsMorphController.OPEN_SETTLE_MS,
            ModuleSettingsMorphController.openingTotalMs(source),
        )
        assertEquals(
            ModuleSettingsMorphController.SWITCH_FADE_MS + ModuleSettingsMorphController.SWITCH_PREP_MS +
                switchTravel + ModuleSettingsMorphController.SWITCH_SETTLE_MS,
            ModuleSettingsMorphController.switchingTotalMs(secondCard),
        )
        assertEquals(
            ModuleSettingsMorphController.CLOSE_FADE_MS + closeTravel + ModuleSettingsMorphController.CLOSE_SETTLE_MS,
            ModuleSettingsMorphController.closingTotalMs(target, secondCard),
        )
    }

    @Test
    fun `representative cards receive bounded distance aware travel durations`() {
        val cards = listOf(
            SilentClickGuiLayout.moduleCardRect(0),
            SilentClickGuiLayout.moduleCardRect(1),
            SilentClickGuiLayout.moduleCardRect(4),
            SilentClickGuiLayout.moduleCardRect(9),
        )
        val opening = cards.map {
            ModuleSettingsMorphController.travelDurationMs(
                it, target, ModuleSettingsMorphController.OPEN_MIN_TRAVEL_MS,
                ModuleSettingsMorphController.OPEN_MAX_TRAVEL_MS
            )
        }
        val closing = cards.map {
            ModuleSettingsMorphController.travelDurationMs(
                target, it, ModuleSettingsMorphController.CLOSE_MIN_TRAVEL_MS,
                ModuleSettingsMorphController.CLOSE_MAX_TRAVEL_MS
            )
        }
        val switching = cards.map {
            ModuleSettingsMorphController.travelDurationMs(
                it, target, ModuleSettingsMorphController.SWITCH_MIN_TRAVEL_MS,
                ModuleSettingsMorphController.SWITCH_MAX_TRAVEL_MS
            )
        }
        assertEquals(listOf(339L, 261L, 334L, 263L), opening)
        assertEquals(listOf(299L, 221L, 294L, 223L), closing)
        assertEquals(listOf(319L, 241L, 314L, 243L), switching)
        opening.forEach { assertTrue(it in 260L..340L) }
        closing.forEach { assertTrue(it in 220L..300L) }
        switching.forEach { assertTrue(it in 240L..320L) }
        assertTrue(opening[0] > opening[1])
        assertTrue(opening[2] > opening[3])
        assertEquals(
            ModuleSettingsMorphController.OPEN_MIN_TRAVEL_MS,
            ModuleSettingsMorphController.travelDurationMs(
                target, target, ModuleSettingsMorphController.OPEN_MIN_TRAVEL_MS,
                ModuleSettingsMorphController.OPEN_MAX_TRAVEL_MS
            )
        )
    }

    @Test
    fun `late frames preserve overshoot across one or multiple stages`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        controller.open(module("First"), Category.COMBAT, 0, source)
        val travel = controller.activeTravelDurationMs

        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS + 13L)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN_TRAVEL, controller.phase)
        assertClose(13f / travel, controller.progress(now))

        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS + travel + 50L)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN_SETTLE, controller.phase)
        assertClose(.5f, controller.progress(now))

        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS + travel +
            ModuleSettingsMorphController.OPEN_SETTLE_MS + 150L)
        val entered = controller.tick(now)
        assertTrue(ModuleSettingsMorphPhase.OPEN in entered)
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
    }

    @Test
    fun `geometry is frame rate independent through regular irregular and stalled frames`() {
        val sampleMs = ModuleSettingsMorphController.OPEN_PREP_MS + 170L
        val baseline = simulateAt(sampleMs, listOf(1_000.0 / 144.0))
        listOf(30.0, 60.0, 90.0, 120.0, 144.0).forEach { fps ->
            assertRect(baseline, simulateAt(sampleMs, listOf(1_000.0 / fps)), .002f)
        }
        assertRect(baseline, simulateAt(sampleMs, listOf(7.0, 19.0, 11.0, 33.0, 5.0, 27.0)), .002f)
        assertRect(baseline, simulateAt(sampleMs, listOf(80.0, 150.0)), .002f)
    }

    @Test
    fun `opening reversal keeps rectangle and velocity continuous`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val first = module("First")
        controller.open(first, Category.COMBAT, 0, source)

        now = ms(30L)
        val prepRect = controller.frame(now).rect
        controller.requestClose()
        assertRect(prepRect, controller.frame(now).rect)

        controller.clear()
        now = 0L
        controller.open(first, Category.COMBAT, 0, source)
        val travel = controller.activeTravelDurationMs
        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS + travel / 2L)
        controller.tick(now)
        val before = controller.frame(now).rect
        val velocityBefore = controller.velocity(now)
        controller.requestClose()
        controller.setCloseTarget(source, needsReveal = false)
        controller.tick(now)
        val after = controller.frame(now).rect
        val velocityAfter = controller.velocity(now)
        assertRect(before, after)
        assertVelocityClose(velocityBefore, velocityAfter, .02f)
        assertFiniteVelocity(velocityAfter)
    }

    @Test
    fun `settle close reopen and switch close remain finite and deterministic`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val first = module("First")
        val second = module("Second")
        controller.open(first, Category.COMBAT, 0, source)
        val openTravel = controller.activeTravelDurationMs
        now = ms(ModuleSettingsMorphController.OPEN_PREP_MS + openTravel + 30L)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN_SETTLE, controller.phase)
        val settleRect = controller.frame(now).rect
        controller.requestClose()
        assertRect(settleRect, controller.frame(now).rect)

        now += ms(ModuleSettingsMorphController.CLOSE_FADE_MS + controller.activeTravelDurationMs / 2L)
        controller.tick(now)
        val closingRect = controller.frame(now).rect
        val closingVelocity = controller.velocity(now)
        controller.open(first, Category.COMBAT, 0, source)
        assertRect(closingRect, controller.frame(now).rect)
        assertVelocityClose(closingVelocity, controller.velocity(now), .02f)

        val reopenRemaining = controller.activeTravelDurationMs + ModuleSettingsMorphController.OPEN_SETTLE_MS
        now += ms(reopenRemaining)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        controller.switch(second, Category.COMBAT, 1, SilentClickGuiLayout.moduleCardRect(1))
        now += ms(ModuleSettingsMorphController.SWITCH_FADE_MS + ModuleSettingsMorphController.SWITCH_PREP_MS + 80L)
        controller.tick(now)
        assertEquals(ModuleSettingsMorphPhase.SWITCH_TRAVEL, controller.phase)
        val switchRect = controller.frame(now).rect
        controller.requestClose()
        controller.setCloseTarget(SilentClickGuiLayout.moduleCardRect(1), needsReveal = false)
        controller.tick(now)
        assertRect(switchRect, controller.frame(now).rect)
        assertFiniteRect(controller.frame(now).rect)
        assertFiniteVelocity(controller.velocity(now))
    }

    @Test
    fun `production uses one monotonic frame timestamp and retains one surface ownership`() {
        val controllerSource = Files.readString(Path.of(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ModuleSettingsMorphController.kt"
        ))
        val screenSource = Files.readString(Path.of(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt"
        ))
        assertFalse(controllerSource.contains("System.currentTimeMillis"))
        assertTrue(controllerSource.contains("System::nanoTime"))
        assertFalse(controllerSource.contains("OPEN_PREP_MS.toFloat() /"))
        assertFalse(controllerSource.contains("40f /"))
        assertFalse(controllerSource.contains("transitionStartedAtNanos = now" + System.lineSeparator() +
            "                phase"))
        assertTrue(screenSource.contains("val frameTimeNanos = morph.nowNanos()"))
        assertTrue(screenSource.contains("morph.tick(frameTimeNanos)"))
        assertTrue(screenSource.contains("morph.frame(frameTimeNanos)"))
        assertTrue(screenSource.contains("private fun drawMorphSurface"))
        assertFalse(screenSource.contains("morphRect(start, morph.latestSourceRect, 1f - progress)"))
        assertFalse(screenSource.contains("motionTrail"))
        assertFalse(screenSource.contains("DynamicTexture("))
        assertFalse(screenSource.contains("Framebuffer"))
        assertTrue(screenSource.contains("val pose = context.pose()"))
        assertTrue(screenSource.contains("withLocalScissor(context, viewport)"))
    }

    @Test
    fun `full close lifecycle terminates and emits one completion`() {
        val harness = Harness()
        val module = module("First")
        harness.openFully(module)
        harness.controller.requestClose()
        harness.controller.setCloseTarget(source, needsReveal = false)

        harness.advance(ModuleSettingsMorphController.CLOSE_FADE_MS)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_TRAVEL, harness.controller.phase)
        harness.advance(harness.controller.activeTravelDurationMs)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_SETTLE, harness.controller.phase)
        assertTrue(ModuleSettingsMorphInputPolicy.isBusy(harness.controller.phase))
        assertTrue(ModuleSettingsMorphInputPolicy.ownsSurface(harness.controller.phase))
        assertTrue(ModuleSettingsMorphInputPolicy.shouldHideModuleCard(harness.controller.phase, module, module))

        harness.advance(ModuleSettingsMorphController.CLOSE_SETTLE_MS)
        assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
        assertFalse(ModuleSettingsMorphInputPolicy.isBusy(harness.controller.phase))
        assertFalse(ModuleSettingsMorphInputPolicy.ownsSurface(harness.controller.phase))
        assertFalse(ModuleSettingsMorphInputPolicy.shouldHideModuleCard(
            harness.controller.phase, harness.controller.selectedModule, module
        ))
        assertEquals(ModuleSettingsCloseCompletion(null, null), harness.controller.consumeCloseCompletion())
        assertNull(harness.controller.consumeCloseCompletion())
    }

    @Test
    fun `close settle terminal boundary is exact without progress reset`() {
        val harness = Harness()
        harness.openFully(module("Boundary"))
        harness.enterCloseSettle(source)
        val settleStarted = harness.now
        val durationNanos = ms(ModuleSettingsMorphController.CLOSE_SETTLE_MS)

        harness.now = settleStarted + durationNanos - 1L
        harness.controller.tick(harness.now)
        assertEquals(ModuleSettingsMorphPhase.CLOSE_SETTLE, harness.controller.phase)
        assertNull(harness.controller.consumeCloseCompletion())

        harness.now = settleStarted + durationNanos
        val entered = harness.controller.tick(harness.now)
        assertTrue(ModuleSettingsMorphPhase.CLOSED in entered)
        assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
        assertEquals(1f, harness.controller.progress(harness.now))

        harness.now++
        harness.controller.tick(harness.now)
        assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
        assertEquals(1f, harness.controller.progress(harness.now))
    }

    @Test
    fun `closed ticks are idempotent and completion cannot repeat`() {
        val harness = Harness()
        harness.openFully(module("Idempotent"))
        harness.enterCloseSettle(source)
        harness.advance(ModuleSettingsMorphController.CLOSE_SETTLE_MS)
        val closedTimestamp = harness.controller.transitionStartedAtNanos
        assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
        assertEquals(ModuleSettingsCloseCompletion(null, null), harness.controller.consumeCloseCompletion())

        listOf(harness.now, harness.now + 1L, harness.now + ms(10_000L)).forEach { timestamp ->
            assertTrue(harness.controller.tick(timestamp).isEmpty())
            assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
            assertEquals(closedTimestamp, harness.controller.transitionStartedAtNanos)
            assertNull(harness.controller.consumeCloseCompletion())
        }
    }

    @Test
    fun `late frame crosses close travel and settle exactly once`() {
        val harness = Harness()
        harness.openFully(module("Late"))
        harness.controller.requestClose()
        harness.controller.setCloseTarget(source, needsReveal = false)
        harness.advance(ModuleSettingsMorphController.CLOSE_FADE_MS)
        val remaining = harness.controller.activeTravelDurationMs +
            ModuleSettingsMorphController.CLOSE_SETTLE_MS + 500L

        val entered = harness.advance(remaining)
        assertTrue(ModuleSettingsMorphPhase.CLOSE_SETTLE in entered)
        assertTrue(ModuleSettingsMorphPhase.CLOSED in entered)
        assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
        assertEquals(ModuleSettingsCloseCompletion(null, null), harness.controller.consumeCloseCompletion())
        assertNull(harness.controller.consumeCloseCompletion())
    }

    @Test
    fun `close settle cannot restart the old sixty five millisecond flash loop`() {
        val harness = Harness()
        harness.openFully(module("Flash"))
        harness.enterCloseSettle(source)

        repeat(8) { cycle ->
            harness.advance(ModuleSettingsMorphController.CLOSE_SETTLE_MS)
            assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase, "cycle $cycle")
            assertFalse(ModuleSettingsMorphInputPolicy.ownsSurface(harness.controller.phase))
            assertEquals(1f, harness.controller.progress(harness.now))
        }
        assertEquals(ModuleSettingsCloseCompletion(null, null), harness.controller.consumeCloseCompletion())
        assertNull(harness.controller.consumeCloseCompletion())
    }

    @Test
    fun `every timed phase has a different successor or terminal transition`() {
        val timed = ModuleSettingsMorphPhase.entries.filter {
            ModuleSettingsMorphController.stageDurationMsFor(it) != null
        }
        assertEquals(
            setOf(
                ModuleSettingsMorphPhase.OPEN_PREP,
                ModuleSettingsMorphPhase.OPEN_TRAVEL,
                ModuleSettingsMorphPhase.OPEN_SETTLE,
                ModuleSettingsMorphPhase.SWITCH_FADE_OUT,
                ModuleSettingsMorphPhase.SWITCH_PREP,
                ModuleSettingsMorphPhase.SWITCH_TRAVEL,
                ModuleSettingsMorphPhase.SWITCH_SETTLE,
                ModuleSettingsMorphPhase.CLOSE_FADE_OUT,
                ModuleSettingsMorphPhase.CLOSE_TRAVEL,
                ModuleSettingsMorphPhase.CLOSE_SETTLE,
            ),
            timed.toSet(),
        )
        timed.forEach { phase ->
            val successor = ModuleSettingsMorphController.successorOnExpiration(phase)
            assertTrue(successor != null && successor != phase, "$phase must expire into a different phase")
        }
        assertEquals(
            ModuleSettingsMorphPhase.CLOSED,
            ModuleSettingsMorphController.successorOnExpiration(ModuleSettingsMorphPhase.CLOSE_SETTLE),
        )
    }

    @Test
    fun `deferred page and search survive until one shot terminal completion`() {
        val harness = Harness()
        harness.openFully(module("Deferred"))
        harness.controller.requestClose(page = SilentClickGuiPage.THEME, search = "sprint")
        harness.controller.setCloseTarget(source, needsReveal = false)

        harness.advance(ModuleSettingsMorphController.CLOSE_FADE_MS)
        harness.advance(harness.controller.activeTravelDurationMs)
        assertNull(harness.controller.consumeCloseCompletion())
        harness.advance(ModuleSettingsMorphController.CLOSE_SETTLE_MS)

        assertEquals(
            ModuleSettingsCloseCompletion(SilentClickGuiPage.THEME, "sprint"),
            harness.controller.consumeCloseCompletion(),
        )
        assertNull(harness.controller.consumeCloseCompletion())
        harness.controller.tick(harness.now + ms(500L))
        assertNull(harness.controller.consumeCloseCompletion())
    }

    @Test
    fun `input unlocks and Escape cannot be trapped in terminal or busy phases`() {
        val module = module("Input")
        ModuleSettingsMorphController.CLOSE_PHASES.forEach { phase ->
            assertTrue(ModuleSettingsMorphInputPolicy.isBusy(phase))
            assertTrue(ModuleSettingsMorphInputPolicy.shouldHideModuleCard(phase, module, module))
            assertEquals(ModuleSettingsMorphEscapeAction.CLOSE_ROOT,
                ModuleSettingsMorphInputPolicy.escapeAction(phase))
        }
        assertEquals(ModuleSettingsMorphEscapeAction.CLOSE_SETTINGS,
            ModuleSettingsMorphInputPolicy.escapeAction(ModuleSettingsMorphPhase.OPEN))
        assertEquals(ModuleSettingsMorphEscapeAction.CLOSE_ROOT,
            ModuleSettingsMorphInputPolicy.escapeAction(ModuleSettingsMorphPhase.CLOSED))
        assertFalse(ModuleSettingsMorphInputPolicy.isBusy(ModuleSettingsMorphPhase.CLOSED))
        assertFalse(ModuleSettingsMorphInputPolicy.shouldHideModuleCard(ModuleSettingsMorphPhase.CLOSED, null, module))
    }

    @Test
    fun `root dismissal can clear every active morph phase and reopen closed`() {
        ModuleSettingsMorphPhase.entries.forEach { targetPhase ->
            val harness = harnessAt(targetPhase)
            assertEquals(targetPhase, harness.controller.phase)
            harness.controller.clear()
            assertEquals(ModuleSettingsMorphPhase.CLOSED, harness.controller.phase)
            assertNull(harness.controller.selectedModule)
            assertFalse(ModuleSettingsMorphInputPolicy.isBusy(harness.controller.phase))
            assertFalse(ModuleSettingsMorphInputPolicy.ownsSurface(harness.controller.phase))
            assertNull(harness.controller.consumeCloseCompletion())
        }
    }

    @Test
    fun `full close terminates across frame rates irregular frames and stalls`() {
        val frameSets = mutableListOf<List<Double>>()
        listOf(30.0, 60.0, 90.0, 120.0, 144.0).forEach { fps ->
            frameSets += listOf(1_000.0 / fps)
        }
        frameSets += listOf(7.0, 19.0, 11.0, 33.0, 5.0, 27.0)
        frameSets += listOf(16.0, 150.0, 16.0)
        frameSets += listOf(16.0, 500.0, 16.0)

        frameSets.forEach { intervals ->
            val result = simulateFullClose(intervals)
            assertEquals(ModuleSettingsMorphPhase.CLOSED, result.first, "intervals=$intervals")
            assertEquals(1, result.second, "intervals=$intervals")
        }
    }

    private fun simulateAt(sampleMs: Long, frameDurationsMs: List<Double>): SilentRect {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        controller.open(module("Sim"), Category.COMBAT, 0, source)
        var elapsed = 0.0
        var index = 0
        while (elapsed < sampleMs) {
            elapsed = minOf(sampleMs.toDouble(), elapsed + frameDurationsMs[index % frameDurationsMs.size])
            now = (elapsed * 1_000_000.0).toLong()
            controller.tick(now)
            index++
        }
        return controller.frame(ms(sampleMs)).rect
    }

    private fun simulateFullClose(frameDurationsMs: List<Double>): Pair<ModuleSettingsMorphPhase, Int> {
        val harness = Harness()
        harness.controller.open(module("FrameRate"), Category.COMBAT, 0, source)
        var index = 0
        var completions = 0
        var guard = 0
        while (harness.controller.phase != ModuleSettingsMorphPhase.OPEN && guard++ < 1_000) {
            harness.advanceNanos((frameDurationsMs[index++ % frameDurationsMs.size] * 1_000_000.0).toLong())
        }
        assertEquals(ModuleSettingsMorphPhase.OPEN, harness.controller.phase)
        harness.controller.requestClose()
        harness.controller.setCloseTarget(source, needsReveal = false)
        guard = 0
        while (harness.controller.phase != ModuleSettingsMorphPhase.CLOSED && guard++ < 1_000) {
            harness.advanceNanos((frameDurationsMs[index++ % frameDurationsMs.size] * 1_000_000.0).toLong())
            if (harness.controller.consumeCloseCompletion() != null) completions++
        }
        if (harness.controller.consumeCloseCompletion() != null) completions++
        repeat(5) {
            harness.advanceNanos((frameDurationsMs[index++ % frameDurationsMs.size] * 1_000_000.0).toLong())
            if (harness.controller.consumeCloseCompletion() != null) completions++
        }
        return harness.controller.phase to completions
    }

    private fun harnessAt(target: ModuleSettingsMorphPhase): Harness {
        val harness = Harness()
        val first = module("Phase")
        val second = module("Switch")
        when (target) {
            ModuleSettingsMorphPhase.CLOSED -> Unit
            ModuleSettingsMorphPhase.OPEN_PREP -> harness.controller.open(first, Category.COMBAT, 0, source)
            ModuleSettingsMorphPhase.OPEN_TRAVEL -> {
                harness.controller.open(first, Category.COMBAT, 0, source)
                harness.advance(ModuleSettingsMorphController.OPEN_PREP_MS)
            }
            ModuleSettingsMorphPhase.OPEN_SETTLE -> {
                harness.controller.open(first, Category.COMBAT, 0, source)
                harness.advance(ModuleSettingsMorphController.OPEN_PREP_MS)
                harness.advance(harness.controller.activeTravelDurationMs)
            }
            ModuleSettingsMorphPhase.OPEN -> harness.openFully(first)
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> {
                harness.openFully(first)
                harness.controller.switch(second, Category.COMBAT, 1, SilentClickGuiLayout.moduleCardRect(1))
            }
            ModuleSettingsMorphPhase.SWITCH_PREP -> {
                harness.openFully(first)
                harness.controller.switch(second, Category.COMBAT, 1, SilentClickGuiLayout.moduleCardRect(1))
                harness.advance(ModuleSettingsMorphController.SWITCH_FADE_MS)
            }
            ModuleSettingsMorphPhase.SWITCH_TRAVEL -> {
                val prep = harnessAt(ModuleSettingsMorphPhase.SWITCH_PREP)
                prep.advance(ModuleSettingsMorphController.SWITCH_PREP_MS)
                return prep
            }
            ModuleSettingsMorphPhase.SWITCH_SETTLE -> {
                val travel = harnessAt(ModuleSettingsMorphPhase.SWITCH_TRAVEL)
                travel.advance(travel.controller.activeTravelDurationMs)
                return travel
            }
            ModuleSettingsMorphPhase.CLOSE_FADE_OUT -> {
                harness.openFully(first)
                harness.controller.requestClose()
                harness.controller.setCloseTarget(source, needsReveal = false)
            }
            ModuleSettingsMorphPhase.CLOSE_TRAVEL -> {
                val fade = harnessAt(ModuleSettingsMorphPhase.CLOSE_FADE_OUT)
                fade.advance(ModuleSettingsMorphController.CLOSE_FADE_MS)
                return fade
            }
            ModuleSettingsMorphPhase.CLOSE_SETTLE -> {
                val travel = harnessAt(ModuleSettingsMorphPhase.CLOSE_TRAVEL)
                travel.advance(travel.controller.activeTravelDurationMs)
                return travel
            }
        }
        return harness
    }

    private inner class Harness {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }

        fun advance(milliseconds: Long): List<ModuleSettingsMorphPhase> =
            advanceNanos(ms(milliseconds))

        fun advanceNanos(nanoseconds: Long): List<ModuleSettingsMorphPhase> {
            now += nanoseconds
            return controller.tick(now)
        }

        fun openFully(module: ClientModule) {
            controller.open(module, Category.COMBAT, 0, source)
            advance(ModuleSettingsMorphController.OPEN_PREP_MS)
            advance(controller.activeTravelDurationMs)
            advance(ModuleSettingsMorphController.OPEN_SETTLE_MS)
            assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        }

        fun enterCloseSettle(targetRect: SilentRect) {
            controller.requestClose()
            controller.setCloseTarget(targetRect, needsReveal = false)
            advance(ModuleSettingsMorphController.CLOSE_FADE_MS)
            advance(controller.activeTravelDurationMs)
            assertEquals(ModuleSettingsMorphPhase.CLOSE_SETTLE, controller.phase)
        }
    }

    private fun module(name: String) = ClientModule(name, Category.COMBAT)

    private fun ms(value: Long): Long = value * 1_000_000L

    private fun assertFiniteRect(rect: SilentRect) {
        assertTrue(rect.x.isFinite() && rect.y.isFinite() && rect.w.isFinite() && rect.h.isFinite())
        assertTrue(rect.w >= 0f && rect.h >= 0f)
    }

    private fun assertFiniteVelocity(velocity: SilentRectVelocity) {
        assertTrue(velocity.x.isFinite() && velocity.y.isFinite() && velocity.w.isFinite() && velocity.h.isFinite())
    }

    private fun assertVelocityClose(expected: SilentRectVelocity, actual: SilentRectVelocity, tolerance: Float) {
        assertClose(expected.x, actual.x, tolerance)
        assertClose(expected.y, actual.y, tolerance)
        assertClose(expected.w, actual.w, tolerance)
        assertClose(expected.h, actual.h, tolerance)
    }

    private fun assertRect(expected: SilentRect, actual: SilentRect, tolerance: Float = .001f) {
        assertClose(expected.x, actual.x, tolerance)
        assertClose(expected.y, actual.y, tolerance)
        assertClose(expected.w, actual.w, tolerance)
        assertClose(expected.h, actual.h, tolerance)
    }

    private fun assertClose(expected: Float, actual: Float, tolerance: Float = .001f) {
        assertTrue(abs(expected - actual) <= tolerance, "expected <$expected> but was <$actual>")
    }
}
