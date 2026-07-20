/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.gui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.abs

class SilentClickGuiLayoutTest {

    @Test
    fun `reference geometry matches Silent panel`() {
        assertEquals(398f, SilentClickGuiLayout.REFERENCE_WIDTH)
        assertEquals(254f, SilentClickGuiLayout.REFERENCE_HEIGHT)

        assertRect(SilentRect(5f, 5f, 78f, 244f), SilentClickGuiLayout.sidebar)
        assertRect(SilentRect(87f, 5f, 306.5f, 24f), SilentClickGuiLayout.header)
        assertEquals(33f, SilentClickGuiLayout.content.y)
    }

    @Test
    fun `module waterfall uses three Silent columns`() {
        assertEquals(3, SilentClickGuiLayout.MODULE_COLUMNS)
        assertEquals(101f, SilentClickGuiLayout.MODULE_COLUMN_WIDTH)
        assertEquals(103f, SilentClickGuiLayout.MODULE_HORIZONTAL_STEP)

        repeat(12) { index ->
            assertEquals(index % 3, SilentClickGuiLayout.columnFor(index))
        }
    }

    @Test
    fun `expanded module only moves same waterfall column`() {
        val waterfall = SilentClickGuiLayout.waterfall(listOf(50f, 22f, 22f, 22f, 22f, 22f))

        assertEquals(SilentClickGuiLayout.content.y, waterfall.rects[1].y)
        assertEquals(SilentClickGuiLayout.content.y, waterfall.rects[2].y)
        assertEquals(SilentClickGuiLayout.content.y + 52f, waterfall.rects[3].y)
        assertEquals(SilentClickGuiLayout.content.y + 24f, waterfall.rects[4].y)
        assertEquals(SilentClickGuiLayout.content.y + 24f, waterfall.rects[5].y)
    }

    @Test
    fun `all categories fit above footer and keep logo slot blank`() {
        val categoryRects = SilentClickGuiLayout.allCategoryRects()
        assertEquals(8, categoryRects.size)
        assertTrue(categoryRects.last().bottom < SilentClickGuiLayout.footer.y)
        assertEquals(5f, SilentClickGuiLayout.logoSlot.y)
        assertEquals(29f, SilentClickGuiLayout.logoSlot.h)
    }

    @Test
    fun `screen to local transform remains aligned through scales`() {
        val transform = SilentClickGuiLayout.transform(800, 600, 1f)
        val center = transform.screenToLocal(400.0, 300.0)
        assertClose(199f, center.x)
        assertClose(127f, center.y)

        val exact = SilentTransform(398f, 254f, 1f, 1f).screenToLocal(199.0, 127.0)
        assertClose(199f, exact.x)
        assertClose(127f, exact.y)

        val small = SilentTransform(800f, 600f, 0.5f, 1f).screenToLocal(400.0, 300.0)
        assertClose(199f, small.x)
        assertClose(127f, small.y)

        val animated = SilentClickGuiLayout.transform(800, 600, 0.43f)
        val category = SilentClickGuiLayout.categoryRect(2)
        val screen = animated.localToScreen(SilentRect(category.x + 10f, category.y + 8f, 1f, 1f))
        val back = animated.screenToLocal(screen.x0.toDouble(), screen.y0.toDouble())
        assertTrue(abs(back.x - (category.x + 10f)) < 1.5f)
        assertTrue(abs(back.y - (category.y + 8f)) < 1.5f)
    }

    @Test
    fun `small screens still produce a safe positive fit scale`() {
        assertTrue(SilentClickGuiLayout.fitScale(1, 1) > 0f)
    }

    @Test
    fun `closing state rejects ordinary clicks`() {
        assertTrue(SilentClickGuiLayout.acceptsOrdinaryClicks(false))
        assertFalse(SilentClickGuiLayout.acceptsOrdinaryClicks(true))
    }

    @Test
    fun `Silent category icon glyph mapping is exact`() {
        assertEquals("h", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.COMBAT))
        assertEquals("g", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.MOVEMENT))
        assertEquals("f", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.PLAYER))
        assertEquals("e", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.RENDER))
        assertEquals("P", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.MISC))
        assertEquals("X", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.CONFIG))
        assertEquals("T", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.THEME))
        assertEquals("K", SilentClickGuiIcons.glyphFor(SilentClickGuiPage.CLOUD))
    }

    private fun assertRect(expected: SilentRect, actual: SilentRect) {
        assertClose(expected.x, actual.x)
        assertClose(expected.y, actual.y)
        assertClose(expected.w, actual.w)
        assertClose(expected.h, actual.h)
    }

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.001f, "expected <$expected> but was <$actual>")
    }

}
