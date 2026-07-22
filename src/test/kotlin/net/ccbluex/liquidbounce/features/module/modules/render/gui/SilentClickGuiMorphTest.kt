package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
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
