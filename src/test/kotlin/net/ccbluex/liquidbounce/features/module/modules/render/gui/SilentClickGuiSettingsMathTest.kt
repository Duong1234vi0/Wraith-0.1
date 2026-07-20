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
import org.junit.jupiter.api.Test
import kotlin.math.abs

class SilentClickGuiSettingsMathTest {

    @Test
    fun `scroll clamps to visible settings content`() {
        assertEquals(0f, SilentClickGuiSettingsMath.clampScroll(-10f, 100f, 80f))
        assertEquals(20f, SilentClickGuiSettingsMath.clampScroll(40f, 100f, 80f))
        assertEquals(0f, SilentClickGuiSettingsMath.clampScroll(8f, 60f, 80f))
    }

    @Test
    fun `float sliders clamp and snap`() {
        assertClose(0.5f, SilentClickGuiSettingsMath.snapFloat(0.54f, 0f, 1f, 0.25f))
        assertClose(1f, SilentClickGuiSettingsMath.snapFloat(2f, 0f, 1f, 0.25f))
        assertClose(0f, SilentClickGuiSettingsMath.snapFloat(-1f, 0f, 1f, 0.25f))
    }

    @Test
    fun `int sliders clamp and snap`() {
        assertEquals(6, SilentClickGuiSettingsMath.snapInt(6.4f, 0, 10, 2))
        assertEquals(10, SilentClickGuiSettingsMath.snapInt(14f, 0, 10, 2))
        assertEquals(0, SilentClickGuiSettingsMath.snapInt(-3f, 0, 10, 2))
    }

    @Test
    fun `range handles keep ordered endpoints`() {
        assertEquals(3..5, SilentClickGuiSettingsMath.orderedIntRange(
            2..5,
            SilentClickGuiSettingsMath.RangeHandle.START,
            3
        ))
        assertEquals(2..4, SilentClickGuiSettingsMath.orderedIntRange(
            2..5,
            SilentClickGuiSettingsMath.RangeHandle.END,
            4
        ))
        assertEquals(5..5, SilentClickGuiSettingsMath.orderedIntRange(
            2..5,
            SilentClickGuiSettingsMath.RangeHandle.START,
            9
        ))
    }

    @Test
    fun `float ranges keep ordered endpoints`() {
        val start = SilentClickGuiSettingsMath.orderedFloatRange(
            0.2f..0.8f,
            SilentClickGuiSettingsMath.RangeHandle.START,
            0.9f
        )
        assertClose(0.8f, start.start)
        assertClose(0.8f, start.endInclusive)

        val end = SilentClickGuiSettingsMath.orderedFloatRange(
            0.2f..0.8f,
            SilentClickGuiSettingsMath.RangeHandle.END,
            0.1f
        )
        assertClose(0.2f, end.start)
        assertClose(0.2f, end.endInclusive)
    }

    @Test
    fun `nearest range handle follows closest endpoint`() {
        assertEquals(
            SilentClickGuiSettingsMath.RangeHandle.START,
            SilentClickGuiSettingsMath.nearestRangeHandle(0.2f, 0.8f, 0.3f)
        )
        assertEquals(
            SilentClickGuiSettingsMath.RangeHandle.END,
            SilentClickGuiSettingsMath.nearestRangeHandle(0.2f, 0.8f, 0.7f)
        )
    }

    private fun assertClose(expected: Float, actual: Float) {
        assert(abs(expected - actual) < 0.001f) {
            "expected <$expected> but was <$actual>"
        }
    }

}
