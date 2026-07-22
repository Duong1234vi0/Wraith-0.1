package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.abs

class SilentClickGuiMorphTest {
    private val source = SilentRect(78f, 34f, 123f, 30f)
    private val target = SilentClickGuiLayout.sidePanel

    @Test
    fun `opening and closing geometry are exact and finite`() {
        assertRect(source, SilentClickGuiMorph.morphRect(source, target, 0f))
        assertRect(target, SilentClickGuiMorph.morphRect(source, target, 1f))
        assertRect(target, SilentClickGuiMorph.morphRect(target, source, 0f))
        assertRect(source, SilentClickGuiMorph.morphRect(target, source, 1f))
        listOf(0f, .01f, .25f, .5f, .75f, .99f, 1f).forEach { p ->
            val rect = SilentClickGuiMorph.morphRect(source, target, p)
            assertTrue(rect.x.isFinite() && rect.y.isFinite() && rect.w >= 0f && rect.h >= 0f)
            val closing = SilentClickGuiMorph.morphRect(target, source, p)
            assertTrue(closing.x.isFinite() && closing.y.isFinite() && closing.w >= 0f && closing.h >= 0f)
            assertTrue(SilentClickGuiMorph.radius(p) in 6f..8f)
        }
    }

    @Test
    fun `quadratic flight has exact endpoints and restrained arc`() {
        val a = SilentPoint(0f, 20f)
        val b = SilentPoint(100f, 20f)
        val c = SilentPoint(50f, 6f)
        assertEquals(a, SilentClickGuiMorph.quadraticPoint(a, c, b, 0f))
        assertEquals(b, SilentClickGuiMorph.quadraticPoint(a, c, b, 1f))
        assertTrue(SilentClickGuiMorph.quadraticPoint(a, c, b, .5f).y < 20f)
    }

    @Test
    fun `content phases are monotonic`() {
        val points = listOf(0f, .2f, .5f, .75f, 1f)
        assertTrue(points.zipWithNext().all { it.first.let(SilentClickGuiMorph::cardContentAlpha) >= it.second.let(SilentClickGuiMorph::cardContentAlpha) })
        assertTrue(points.zipWithNext().all { it.first.let(SilentClickGuiMorph::panelContentAlpha) <= it.second.let(SilentClickGuiMorph::panelContentAlpha) })
    }

    @Test
    fun `controller covers open switch and reverse close`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val first = ClientModule("First", Category.COMBAT)
        val second = ClientModule("Second", Category.COMBAT)
        controller.open(first, Category.COMBAT, 0, source)
        assertEquals(ModuleSettingsMorphPhase.OPENING, controller.phase)
        now = ModuleSettingsMorphController.OPEN_TRAVEL_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.OPENING_SETTLE, controller.phase)
        now += ModuleSettingsMorphController.OPEN_SETTLE_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        controller.switch(second, Category.COMBAT, 1, SilentRect(206f, 34f, 123f, 30f))
        assertEquals(ModuleSettingsMorphPhase.SWITCH_FADE_OUT, controller.phase)
        now += ModuleSettingsMorphController.SWITCH_FADE_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.SWITCH_OPENING, controller.phase)
        now += ModuleSettingsMorphController.SWITCH_TRAVEL_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.SWITCH_SETTLE, controller.phase)
        now += ModuleSettingsMorphController.SWITCH_SETTLE_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        controller.requestClose()
        controller.setCloseTarget(source, needsReveal = false)
        assertEquals(ModuleSettingsMorphPhase.PREPARING_CLOSE, controller.phase)
        now += ModuleSettingsMorphController.CLOSE_PREP_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.CLOSING, controller.phase)
        now += ModuleSettingsMorphController.CLOSE_TRAVEL_MS
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.CLOSING_SETTLE, controller.phase)
        now += ModuleSettingsMorphController.CLOSE_SETTLE_MS
        assertTrue(controller.progress() >= 1f)
    }

    @Test
    fun `rapid switching keeps one deterministic destination`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val first = ClientModule("First", Category.COMBAT)
        val second = ClientModule("Second", Category.COMBAT)
        val third = ClientModule("Third", Category.COMBAT)
        controller.open(first, Category.COMBAT, 0, source)
        now = ModuleSettingsMorphController.OPEN_TRAVEL_MS
        controller.tick()
        now += ModuleSettingsMorphController.OPEN_SETTLE_MS
        controller.tick()
        controller.switch(second, Category.COMBAT, 1, SilentRect(206f, 34f, 123f, 30f))
        controller.switch(third, Category.COMBAT, 2, SilentRect(78f, 68f, 123f, 30f))
        now += ModuleSettingsMorphController.SWITCH_FADE_MS
        controller.tick()
        assertEquals(second, controller.selectedModule)
        assertEquals(ModuleSettingsMorphPhase.SWITCH_OPENING, controller.phase)
    }

    @Test
    fun `closing moves directly from panel to current card`() {
        assertRect(target, SilentClickGuiMorph.morphRect(target, source, 0f))
        assertRect(source, SilentClickGuiMorph.morphRect(target, source, 1f))
        val middle = SilentClickGuiMorph.morphRect(target, source, 0.5f)
        assertTrue(middle.x > source.x && middle.x < target.x)
    }

    @Test
    fun `interrupted opening captures its current rectangle for close`() {
        var now = 0L
        val controller = ModuleSettingsMorphController { now }
        val module = ClientModule("First", Category.COMBAT)
        controller.open(module, Category.COMBAT, 0, source)
        now = ModuleSettingsMorphController.OPEN_TRAVEL_MS / 2
        val expected = SilentClickGuiMorph.openingRect(source, controller.progress())
        controller.requestClose()
        assertRect(expected, controller.closingStartRect)
        controller.setCloseTarget(source, needsReveal = false)
        now += ModuleSettingsMorphController.CLOSE_PREP_MS
        controller.tick()
        assertRect(expected, SilentClickGuiMorph.morphRect(controller.closingStartRect, source, 0f))
    }

    @Test
    fun `travel uses one staged surface and no old inverse interpolation`() {
        val sourceText = java.nio.file.Files.readString(java.nio.file.Path.of(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt"))
        assertTrue(sourceText.contains("drawMorphSurface"))
        assertTrue(sourceText.contains("SWITCH_OPENING"))
        assertTrue(sourceText.contains("CLOSING_SETTLE"))
        assertTrue(!sourceText.contains("morphRect(start, morph.latestSourceRect, 1f - progress)"))
    }

    private fun assertRect(expected: SilentRect, actual: SilentRect) {
        assertTrue(abs(expected.x - actual.x) < .001f)
        assertTrue(abs(expected.y - actual.y) < .001f)
        assertTrue(abs(expected.w - actual.w) < .001f)
        assertTrue(abs(expected.h - actual.h) < .001f)
    }
}
