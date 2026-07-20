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

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlin.math.roundToInt

object SilentClickGuiSettingsMath {

    enum class RangeHandle {
        START,
        END
    }

    fun clampScroll(scroll: Float, contentHeight: Float, viewportHeight: Float): Float {
        val maxScroll = max(0f, contentHeight - viewportHeight)
        return scroll.coerceIn(0f, maxScroll)
    }

    fun sliderProgress(mouseX: Float, trackX: Float, trackWidth: Float): Float {
        if (trackWidth <= 0f) {
            return 0f
        }

        return ((mouseX - trackX) / trackWidth).coerceIn(0f, 1f)
    }

    fun valueFromProgress(progress: Float, minValue: Float, maxValue: Float): Float {
        return minValue + (maxValue - minValue) * progress.coerceIn(0f, 1f)
    }

    fun snapFloat(value: Float, minValue: Float, maxValue: Float, step: Float?): Float {
        val clamped = value.coerceIn(minValue, maxValue)
        val effectiveStep = step?.takeIf { it > 0f } ?: return clamped

        val snapped = minValue + round((clamped - minValue) / effectiveStep) * effectiveStep
        return snapped.coerceIn(minValue, maxValue)
    }

    fun snapInt(value: Float, minValue: Int, maxValue: Int, step: Int?): Int {
        val clamped = value.coerceIn(minValue.toFloat(), maxValue.toFloat())
        val effectiveStep = step?.takeIf { it > 0 } ?: return clamped.roundToInt().coerceIn(minValue, maxValue)

        val snapped = minValue + round((clamped - minValue) / effectiveStep).toInt() * effectiveStep
        return snapped.coerceIn(minValue, maxValue)
    }

    fun orderedIntRange(current: IntRange, handle: RangeHandle, nextValue: Int): IntRange {
        return when (handle) {
            RangeHandle.START -> min(nextValue, current.last)..current.last
            RangeHandle.END -> current.first..max(nextValue, current.first)
        }
    }

    fun orderedFloatRange(
        current: ClosedFloatingPointRange<Float>,
        handle: RangeHandle,
        nextValue: Float
    ): ClosedFloatingPointRange<Float> {
        return when (handle) {
            RangeHandle.START -> min(nextValue, current.endInclusive)..current.endInclusive
            RangeHandle.END -> current.start..max(nextValue, current.start)
        }
    }

    fun nearestRangeHandle(startProgress: Float, endProgress: Float, mouseProgress: Float): RangeHandle {
        return if (abs(mouseProgress - startProgress) <= abs(mouseProgress - endProgress)) {
            RangeHandle.START
        } else {
            RangeHandle.END
        }
    }

}
