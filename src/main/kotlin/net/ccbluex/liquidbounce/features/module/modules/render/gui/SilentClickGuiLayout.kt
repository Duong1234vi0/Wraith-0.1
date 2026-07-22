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

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

object SilentClickGuiLayout {
    const val REFERENCE_WIDTH = 468f
    const val REFERENCE_HEIGHT = 226f
    const val SAFE_MIN_SCALE = 0.05f
    const val DEFAULT_MARGIN = 12f
    const val MAX_FIT_SCALE = 0.86f

    val mainPanel = SilentRect(0f, 0f, 334f, 226f)
    val sidePanel = SilentRect(342f, 0f, 126f, 226f)
    val sidebar = SilentRect(5f, 5f, 68f, 216f)
    val logoSlot = SilentRect(5f, 5f, 68f, 24f)
    val header = SilentRect(78f, 5f, 251f, 24f)
    val content = SilentRect(78f, 34f, 251f, 187f)
    val footer = SilentRect(9f, 199f, 60f, 22f)
    val sideHeader = SilentRect(348f, 6f, 114f, 29f)
    val settingsViewport = SilentRect(349f, 40f, 112f, 179f)

    const val CATEGORY_ROW_X = 11f
    const val CATEGORY_ROW_Y = 34f
    const val CATEGORY_ROW_WIDTH = 56f
    const val CATEGORY_ROW_HEIGHT = 18f
    const val CATEGORY_ROW_STEP = 20f

    const val MODULE_COLUMNS = 2
    const val MODULE_COLUMN_WIDTH = 123f
    const val MODULE_CARD_HEIGHT = 30f
    const val MODULE_HORIZONTAL_GAP = 5f
    const val MODULE_HORIZONTAL_STEP = 128f
    const val MODULE_VERTICAL_GAP = 4f
    const val MODULE_VERTICAL_STEP = 34f
    const val SETTINGS_ZONE_WIDTH = 16f
    const val SETTINGS_ZONE_HEIGHT = 18f

    const val THEME_BOX_WIDTH = 77f
    const val THEME_BOX_HEIGHT = 30f
    const val THEME_BOX_GAP = 5f
    const val THEME_ROW_STEP = 44f

    fun fitScale(screenWidth: Int, screenHeight: Int, margin: Float = DEFAULT_MARGIN): Float {
        val safeWidth = screenWidth.toFloat().coerceAtLeast(1f)
        val safeHeight = screenHeight.toFloat().coerceAtLeast(1f)
        val safeMargin = margin.coerceAtLeast(0f)
        val widthScale = ((safeWidth - safeMargin * 2f) / REFERENCE_WIDTH).takeIf { it.isFinite() } ?: SAFE_MIN_SCALE
        val heightScale = ((safeHeight - safeMargin * 2f) / REFERENCE_HEIGHT).takeIf { it.isFinite() } ?: SAFE_MIN_SCALE
        return max(SAFE_MIN_SCALE, min(MAX_FIT_SCALE, min(widthScale, heightScale)))
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

    fun columnFor(moduleIndex: Int): Int = moduleIndex.floorMod(MODULE_COLUMNS)

    fun rowFor(moduleIndex: Int): Int = moduleIndex / MODULE_COLUMNS

    fun moduleX(column: Int): Float = content.x + MODULE_HORIZONTAL_STEP * column.coerceIn(0, MODULE_COLUMNS - 1)

    fun moduleCardRect(moduleIndex: Int, scroll: Float = 0f): SilentRect =
        SilentRect(moduleX(columnFor(moduleIndex)), content.y + rowFor(moduleIndex) * MODULE_VERTICAL_STEP - scroll,
            MODULE_COLUMN_WIDTH, MODULE_CARD_HEIGHT)

    fun moduleSettingsZone(card: SilentRect): SilentRect =
        SilentRect(card.right - SETTINGS_ZONE_WIDTH - 4f, card.y + 7f, SETTINGS_ZONE_WIDTH, SETTINGS_ZONE_HEIGHT)

    fun moduleGridContentHeight(moduleCount: Int): Float {
        if (moduleCount <= 0) return 0f
        val rows = ceil(moduleCount / MODULE_COLUMNS.toFloat()).toInt()
        return rows * MODULE_CARD_HEIGHT + max(0, rows - 1) * MODULE_VERTICAL_GAP
    }

    fun themeBoxRect(index: Int, scroll: Float = 0f): SilentRect {
        val column = index.floorMod(3)
        val row = index / 3
        return SilentRect(
            content.x + 2f + column * (THEME_BOX_WIDTH + THEME_BOX_GAP),
            content.y + 7f + row * THEME_ROW_STEP - scroll,
            THEME_BOX_WIDTH,
            THEME_BOX_HEIGHT
        )
    }

    fun themeContentHeight(count: Int): Float {
        if (count <= 0) {
            return 0f
        }

        val rows = ((count - 1) / 3) + 1
        return 7f + rows * THEME_BOX_HEIGHT + max(0, rows - 1) * (THEME_ROW_STEP - THEME_BOX_HEIGHT) + 16f
    }

    fun acceptsOrdinaryClicks(closing: Boolean): Boolean = !closing

    private fun Int.floorMod(mod: Int): Int = ((this % mod) + mod) % mod

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
