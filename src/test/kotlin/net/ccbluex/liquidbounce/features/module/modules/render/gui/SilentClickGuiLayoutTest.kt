package net.ccbluex.liquidbounce.features.module.modules.render.gui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.abs

class SilentClickGuiLayoutTest {
    @Test
    fun `compact reference geometry matches requested composition`() {
        assertEquals(468f, SilentClickGuiLayout.REFERENCE_WIDTH)
        assertEquals(226f, SilentClickGuiLayout.REFERENCE_HEIGHT)
        assertRect(SilentRect(0f, 0f, 334f, 226f), SilentClickGuiLayout.mainPanel)
        assertRect(SilentRect(342f, 0f, 126f, 226f), SilentClickGuiLayout.sidePanel)
        assertRect(SilentRect(5f, 5f, 68f, 216f), SilentClickGuiLayout.sidebar)
        assertRect(SilentRect(78f, 5f, 251f, 24f), SilentClickGuiLayout.header)
        assertRect(SilentRect(78f, 34f, 251f, 187f), SilentClickGuiLayout.content)
        assertRect(SilentRect(348f, 6f, 114f, 29f), SilentClickGuiLayout.sideHeader)
        assertRect(SilentRect(349f, 40f, 112f, 179f), SilentClickGuiLayout.settingsViewport)
        assertEquals(8f, SilentClickGuiLayout.sidePanel.x - SilentClickGuiLayout.mainPanel.right)
    }

    @Test
    fun `FCL canvas is centered and capped at 0 point 86`() {
        val transform = SilentClickGuiLayout.transform(535, 243, 1f)
        assertClose(0.86f, transform.fitScale)
        assertClose(66.26f, transform.localToScreen(SilentPoint(0f, 0f)).x)
        assertClose(24.32f, transform.localToScreen(SilentPoint(0f, 0f)).y)
        assertClose(468.74f, transform.localToScreen(SilentPoint(468f, 226f)).x)
        assertClose(218.68f, transform.localToScreen(SilentPoint(468f, 226f)).y)
    }

    @Test
    fun `module grid is fixed two columns and row major`() {
        assertEquals(2, SilentClickGuiLayout.MODULE_COLUMNS)
        assertEquals(123f, SilentClickGuiLayout.MODULE_COLUMN_WIDTH)
        assertEquals(30f, SilentClickGuiLayout.MODULE_CARD_HEIGHT)
        assertEquals(5f, SilentClickGuiLayout.MODULE_HORIZONTAL_GAP)
        assertEquals(4f, SilentClickGuiLayout.MODULE_VERTICAL_GAP)
        repeat(10) { index ->
            val card = SilentClickGuiLayout.moduleCardRect(index)
            assertEquals(if (index % 2 == 0) 78f else 206f, card.x)
            assertEquals(34f + (index / 2) * 34f, card.y)
            assertEquals(30f, card.h)
        }
        assertEquals(166f, SilentClickGuiLayout.moduleGridContentHeight(10))
        assertEquals(132f, SilentClickGuiLayout.moduleGridContentHeight(7))
        assertFalse(SilentClickGuiLayout.moduleCardRect(0).h != SilentClickGuiLayout.moduleCardRect(1).h)
    }

    @Test
    fun `settings zone is a real touch target inside the card`() {
        val zone = SilentClickGuiLayout.moduleSettingsZone(SilentClickGuiLayout.moduleCardRect(0))
        assertTrue(zone.w >= 14f)
        assertTrue(zone.h >= 16f)
        assertTrue(SilentClickGuiLayout.moduleCardRect(0).contains(SilentPoint(zone.x + 1f, zone.y + 1f)))
    }

    @Test
    fun `three column waterfall and inline expansion helpers no longer exist`() {
        val source = java.nio.file.Files.readString(java.nio.file.Path.of(
            "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/SilentClickGuiLayout.kt"))
        assertFalse(source.contains("waterfall"))
        assertFalse(source.contains("MAX_SETTINGS_HEIGHT"))
        assertFalse(source.contains("MODULE_HEADER_HEIGHT"))
    }

    @Test
    fun `inverse transform remains valid during restrained animation`() {
        val transform = SilentClickGuiLayout.transform(535, 243, 0.97f)
        val local = SilentPoint(191.5f, 82.25f)
        val screen = transform.localToScreen(local)
        val back = transform.screenToLocal(screen.x.toDouble(), screen.y.toDouble())
        assertClose(local.x, back.x)
        assertClose(local.y, back.y)
    }

    @Test
    fun `safe minimum scale and closing click gate remain defensive`() {
        assertTrue(SilentClickGuiLayout.fitScale(1, 1) > 0f)
        assertTrue(SilentClickGuiLayout.acceptsOrdinaryClicks(false))
        assertFalse(SilentClickGuiLayout.acceptsOrdinaryClicks(true))
    }

    private fun assertRect(expected: SilentRect, actual: SilentRect) {
        assertClose(expected.x, actual.x); assertClose(expected.y, actual.y)
        assertClose(expected.w, actual.w); assertClose(expected.h, actual.h)
    }

    private fun assertClose(expected: Float, actual: Float, tolerance: Float = 0.01f) {
        assertTrue(abs(expected - actual) < tolerance, "expected <$expected> but was <$actual>")
    }
}
