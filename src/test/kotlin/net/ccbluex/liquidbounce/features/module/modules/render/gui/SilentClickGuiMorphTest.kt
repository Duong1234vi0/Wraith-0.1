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
        now = 320L
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        controller.switch(second, Category.COMBAT, 1, SilentRect(206f, 34f, 123f, 30f))
        assertEquals(ModuleSettingsMorphPhase.SWITCHING, controller.phase)
        now += 160L
        controller.tick()
        assertEquals(ModuleSettingsMorphPhase.OPEN, controller.phase)
        controller.requestClose()
        controller.setCloseTarget(source, needsReveal = false)
        assertEquals(ModuleSettingsMorphPhase.CLOSING, controller.phase)
        now += 280L
        assertTrue(controller.progress() >= 1f)
    }

    private fun assertRect(expected: SilentRect, actual: SilentRect) {
        assertTrue(abs(expected.x - actual.x) < .001f)
        assertTrue(abs(expected.y - actual.y) < .001f)
        assertTrue(abs(expected.w - actual.w) < .001f)
        assertTrue(abs(expected.h - actual.h) < .001f)
    }
}
