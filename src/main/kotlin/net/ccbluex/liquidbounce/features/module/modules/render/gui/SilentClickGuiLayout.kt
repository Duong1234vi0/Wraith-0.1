/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
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

import kotlin.math.max
import kotlin.math.min
import kotlin.math.ceil
import kotlin.math.floor

object SilentClickGuiLayout {
    const val REFERENCE_WIDTH = 398f
    const val REFERENCE_HEIGHT = 254f
    const val SAFE_MIN_SCALE = 0.20f
    const val DEFAULT_MARGIN = 8f

    val mainPanel = SilentRect(0f, 0f, REFERENCE_WIDTH, REFERENCE_HEIGHT)
    val sidebar = SilentRect(5f, 5f, 78f, 244f)
    val logoSlot = SilentRect(5f, 5f, 78f, 29f)
    val header = SilentRect(87f, 5f, 306.5f, 24f)
    val content = SilentRect(87f, 33f, 306.5f, 216f)
    val footer = SilentRect(9.5f, 224.5f, 69f, 21f)

    const val CATEGORY_ROW_X = 14f
    const val CATEGORY_ROW_Y = 39f
    const val CATEGORY_ROW_WIDTH = 64f
    const val CATEGORY_ROW_HEIGHT = 21f
    const val CATEGORY_ROW_STEP = 22f

    const val MODULE_COLUMNS = 3
    const val MODULE_COLUMN_WIDTH = 101f
    const val MODULE_HORIZONTAL_STEP = 103f
    const val MODULE_HEADER_HEIGHT = 22f
    const val MODULE_VERTICAL_GAP = 2f
    const val MAX_SETTINGS_HEIGHT = 150f

    const val THEME_BOX_SIZE = 60f
    const val THEME_BOX_SPACING = 15f

    fun fitScale(screenWidth: Int, screenHeight: Int, margin: Float = DEFAULT_MARGIN): Float {
        val safeWidth = screenWidth.toFloat().coerceAtLeast(1f)
        val safeHeight = screenHeight.toFloat().coerceAtLeast(1f)
        val safeMargin = margin.coerceAtLeast(0f)
        val widthScale = ((safeWidth - safeMargin * 2f) / REFERENCE_WIDTH).takeIf { it.isFinite() } ?: SAFE_MIN_SCALE
        val heightScale = ((safeHeight - safeMargin * 2f) / REFERENCE_HEIGHT).takeIf { it.isFinite() } ?: SAFE_MIN_SCALE
        return max(SAFE_MIN_SCALE, min(widthScale, heightScale))
    }

    fun transform(screenWidth: Int, screenHeight: Int, animationScale: Float, margin: Float = DEFAULT_MARGIN) =
        SilentTransform(
            screenWidth = screenWidth.toFloat(),
            screenHeight = screenHeight.toFloat(),
            fitScale = fitScale(screenWidth, screenHeight, margin),
            animationScale = animationScale.coerceAtLeast(0.0001f)
        )

    fun categoryRect(index: Int): SilentRect =
        SilentRect(
            CATEGORY_ROW_X,
            CATEGORY_ROW_Y + CATEGORY_ROW_STEP * index,
            CATEGORY_ROW_WIDTH,
            CATEGORY_ROW_HEIGHT
        )

    fun allCategoryRects(): List<SilentRect> =
        SilentClickGuiPage.ORDER.indices.map(::categoryRect)

    fun columnFor(moduleIndex: Int): Int =
        moduleIndex.floorMod(MODULE_COLUMNS)

    fun moduleX(column: Int): Float =
        content.x + MODULE_HORIZONTAL_STEP * column.coerceIn(0, MODULE_COLUMNS - 1)

    fun waterfall(moduleHeights: List<Float>, scroll: Float = 0f): Waterfall {
        val cursors = FloatArray(MODULE_COLUMNS) { content.y }
        val rects = ArrayList<SilentRect>(moduleHeights.size)

        moduleHeights.forEachIndexed { index, rawHeight ->
            val column = columnFor(index)
            val height = rawHeight.coerceAtLeast(MODULE_HEADER_HEIGHT)
            val rect = SilentRect(moduleX(column), cursors[column] - scroll, MODULE_COLUMN_WIDTH, height)
            rects += rect
            cursors[column] += height + MODULE_VERTICAL_GAP
        }

        val contentHeight = cursors.maxOrNull()?.minus(content.y + MODULE_VERTICAL_GAP) ?: 0f
        return Waterfall(rects, contentHeight.coerceAtLeast(0f))
    }

    fun themeBoxRect(index: Int, scroll: Float = 0f): SilentRect {
        val column = index.floorMod(4)
        val row = index / 4
        return SilentRect(
            content.x + 6f + column * (THEME_BOX_SIZE + THEME_BOX_SPACING),
            content.y + 10f + row * (THEME_BOX_SIZE + THEME_BOX_SPACING + 13f) - scroll,
            THEME_BOX_SIZE,
            THEME_BOX_SIZE
        )
    }

    fun themeContentHeight(count: Int): Float {
        if (count <= 0) {
            return 0f
        }

        val rows = ((count - 1) / 4) + 1
        return 10f + rows * THEME_BOX_SIZE + max(0, rows - 1) * (THEME_BOX_SPACING + 13f) + 18f
    }

    fun acceptsOrdinaryClicks(closing: Boolean): Boolean = !closing

    private fun Int.floorMod(mod: Int): Int = ((this % mod) + mod) % mod

    data class Waterfall(val rects: List<SilentRect>, val contentHeight: Float)
}

data class SilentPoint(val x: Float, val y: Float)

data class SilentRect(val x: Float, val y: Float, val w: Float, val h: Float) {
    val right: Float get() = x + w
    val bottom: Float get() = y + h

    fun contains(point: SilentPoint): Boolean =
        w > 0f && h > 0f && point.x >= x && point.x <= right && point.y >= y && point.y <= bottom

    fun intersects(other: SilentRect): Boolean =
        right >= other.x && x <= other.right && bottom >= other.y && y <= other.bottom

    companion object {
        val ZERO = SilentRect(0f, 0f, 0f, 0f)
    }
}

data class ScreenRect(val x0: Int, val y0: Int, val x1: Int, val y1: Int)

data class SilentTransform(
    val screenWidth: Float,
    val screenHeight: Float,
    val fitScale: Float,
    val animationScale: Float
) {
    val totalScale: Float
        get() = (fitScale * animationScale).coerceAtLeast(0.0001f)

    val centerX: Float
        get() = screenWidth * 0.5f

    val centerY: Float
        get() = screenHeight * 0.5f

    fun screenToLocal(mouseX: Double, mouseY: Double): SilentPoint =
        SilentPoint(
            ((mouseX.toFloat() - centerX) / totalScale) + SilentClickGuiLayout.REFERENCE_WIDTH * 0.5f,
            ((mouseY.toFloat() - centerY) / totalScale) + SilentClickGuiLayout.REFERENCE_HEIGHT * 0.5f
        )

    fun localToScreen(point: SilentPoint): SilentPoint =
        SilentPoint(
            centerX + (point.x - SilentClickGuiLayout.REFERENCE_WIDTH * 0.5f) * totalScale,
            centerY + (point.y - SilentClickGuiLayout.REFERENCE_HEIGHT * 0.5f) * totalScale
        )

    fun localToScreen(rect: SilentRect): ScreenRect {
        val x0 = centerX + (rect.x - SilentClickGuiLayout.REFERENCE_WIDTH * 0.5f) * totalScale
        val y0 = centerY + (rect.y - SilentClickGuiLayout.REFERENCE_HEIGHT * 0.5f) * totalScale
        val x1 = centerX + (rect.right - SilentClickGuiLayout.REFERENCE_WIDTH * 0.5f) * totalScale
        val y1 = centerY + (rect.bottom - SilentClickGuiLayout.REFERENCE_HEIGHT * 0.5f) * totalScale
        val minX = min(x0, x1)
        val minY = min(y0, y1)
        val maxX = max(x0, x1)
        val maxY = max(y0, y1)
        return ScreenRect(
            floor(minX).toInt(),
            floor(minY).toInt(),
            ceil(maxX).toInt(),
            ceil(maxY).toInt()
        )
    }
}
