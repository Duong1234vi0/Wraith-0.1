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
@file:JvmName("RoundedRectGui")

package net.ccbluex.liquidbounce.render.gui

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

/**
 * High-level Kotlin API that submits SDF rounded-rect GUI elements
 * through vanilla 1.26.2's batched GUI render path. Used by
 * [net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer]
 * to replace the broken `DrawContext.fillGradient(...)` early-return that
 * silently dropped corner radius and 4-corner gradients.
 */

// #Edit By Claude 2026-06-11: per-corner rounding masks. Bit per corner so a
// caller can round only some corners. Matches the unpack in rounded_rect.vsh
// (bit0 TL, bit1 TR, bit2 BR, bit3 BL).
const val CORNER_TOP_LEFT = 0x1
const val CORNER_TOP_RIGHT = 0x2
const val CORNER_BOTTOM_RIGHT = 0x4
const val CORNER_BOTTOM_LEFT = 0x8
const val CORNERS_ALL = 0xF
const val CORNERS_TOP = CORNER_TOP_LEFT or CORNER_TOP_RIGHT
const val CORNERS_BOTTOM = CORNER_BOTTOM_LEFT or CORNER_BOTTOM_RIGHT
const val CORNERS_LEFT = CORNER_TOP_LEFT or CORNER_BOTTOM_LEFT
const val CORNERS_RIGHT = CORNER_TOP_RIGHT or CORNER_BOTTOM_RIGHT

/**
 * Submit a rounded rectangle with per-corner colors and an optional
 * stroke. `strokePx <= 0` paints a filled rectangle; a positive value
 * paints only an inner band of that width along the rounded edge.
 *
 * Coordinates are in the same screen-space pixels vanilla uses for
 * `DrawContext.fill` etc.; the current pose and scissor stack are
 * captured automatically.
 *
 * #Edit By Claude 2026-06-11: [cornerMask] selects WHICH corners are
 * rounded (bit0 TL, bit1 TR, bit2 BR, bit3 BL). The default [CORNERS_ALL]
 * rounds all four (old behaviour). e.g. [CORNERS_TOP] rounds only the top
 * two corners for a title band with a flat bottom edge.
 */
fun GuiGraphicsExtractor.drawRoundedRect(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radiusPx: Float,
    colorTopLeft: Int,
    colorTopRight: Int,
    colorBottomRight: Int,
    colorBottomLeft: Int,
    strokePx: Float = 0f,
    cornerMask: Int = CORNERS_ALL,
) {
    if (width <= 0f || height <= 0f) {
        return
    }

    val x0 = min(x, x + width)
    val y0 = min(y, y + height)
    val x1 = max(x, x + width)
    val y1 = max(y, y + height)
    val maxRadius = min(x1 - x0, y1 - y0) * 0.5f
    val clampedRadius = radiusPx.coerceIn(0f, maxRadius)
    val clampedStroke = strokePx.coerceAtLeast(0f)

    this.guiRenderState.addGuiElement(
        RoundedRectGuiElementRenderState(
            x0, y0, x1, y1,
            colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft,
            clampedRadius, clampedRadius, clampedStroke, cornerMask,
            Matrix3x2f(this.pose()),
            this.scissorStack.peek(),
            roundedRectBounds(this, x0, y0, x1, y1),
        )
    )
}

/**
 * #Edit By Codex 2026-06-19: rounded rectangle with separate top and bottom
 * radii. The shader keeps the old one-radius path for existing callers, while
 * this path lets settings headers use strong top corners and softer lower
 * corners without a stitched overlay.
 */
@Suppress("LongParameterList")
fun GuiGraphicsExtractor.drawRoundedRectVerticalRadii(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    topRadiusPx: Float,
    bottomRadiusPx: Float,
    colorTopLeft: Int,
    colorTopRight: Int,
    colorBottomRight: Int,
    colorBottomLeft: Int,
    strokePx: Float = 0f,
    cornerMask: Int = CORNERS_ALL,
) {
    if (width <= 0f || height <= 0f) {
        return
    }

    val x0 = min(x, x + width)
    val y0 = min(y, y + height)
    val x1 = max(x, x + width)
    val y1 = max(y, y + height)
    val maxRadius = min(x1 - x0, y1 - y0) * 0.5f
    val clampedTopRadius = topRadiusPx.coerceIn(0f, maxRadius)
    val clampedBottomRadius = bottomRadiusPx.coerceIn(0f, maxRadius)
    val clampedStroke = strokePx.coerceAtLeast(0f)

    this.guiRenderState.addGuiElement(
        RoundedRectGuiElementRenderState(
            x0, y0, x1, y1,
            colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft,
            clampedTopRadius, clampedBottomRadius, clampedStroke, cornerMask,
            Matrix3x2f(this.pose()),
            this.scissorStack.peek(),
            roundedRectBounds(this, x0, y0, x1, y1),
        )
    )
}

/** Convenience overload for solid rounded fill. */
fun GuiGraphicsExtractor.drawRoundedRect(
    x: Float, y: Float, width: Float, height: Float,
    radiusPx: Float, color: Int,
) = drawRoundedRect(x, y, width, height, radiusPx, color, color, color, color, 0f)

/** #Edit By Claude 2026-06-11: solid fill with a chosen corner mask. */
fun GuiGraphicsExtractor.drawRoundedRect(
    x: Float, y: Float, width: Float, height: Float,
    radiusPx: Float, color: Int, cornerMask: Int,
) = drawRoundedRect(x, y, width, height, radiusPx, color, color, color, color, 0f, cornerMask)

/** Convenience overload for rounded outline (stroke-only). */
fun GuiGraphicsExtractor.drawRoundedOutline(
    x: Float, y: Float, width: Float, height: Float,
    radiusPx: Float, color: Int, strokePx: Float = 1f,
) = drawRoundedRect(
    x,
    y,
    width,
    height,
    radiusPx,
    color,
    color,
    color,
    color,
    strokePx.coerceAtLeast(0.05f)
)

/**
 * #Edit By Claude 2026-06-11: gradient outline (stroke-only) with four
 * corner colours — celestial's outline shaders interpolate the edge colour,
 * and the SDF pipeline already lerps [vColor] per vertex, so this is just a
 * stroke draw fed four colours instead of one.
 */
@Suppress("LongParameterList")
fun GuiGraphicsExtractor.drawRoundedGradientOutline(
    x: Float, y: Float, width: Float, height: Float,
    radiusPx: Float,
    colorTopLeft: Int, colorTopRight: Int, colorBottomRight: Int, colorBottomLeft: Int,
    strokePx: Float = 1f,
) = drawRoundedRect(
    x, y, width, height, radiusPx,
    colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft,
    strokePx.coerceAtLeast(0.05f),
)

/**
 * Vanilla uses a fast scissor-clipped axis-aligned bound for
 * sort/cull. We approximate `ColoredRectangleRenderState.getBounds` by
 * transforming the four corners with the current 2D pose and
 * intersecting with the active scissor — close enough for batched
 * culling and identical to the path other GUI elements use.
 */
private fun roundedRectBounds(
    extractor: GuiGraphicsExtractor,
    x0: Float, y0: Float, x1: Float, y1: Float,
): ScreenRectangle? {
    val pose = extractor.pose()
    val v1x = pose.m00() * x0 + pose.m10() * y0 + pose.m20()
    val v1y = pose.m01() * x0 + pose.m11() * y0 + pose.m21()
    val v2x = pose.m00() * x1 + pose.m10() * y0 + pose.m20()
    val v2y = pose.m01() * x1 + pose.m11() * y0 + pose.m21()
    val v3x = pose.m00() * x0 + pose.m10() * y1 + pose.m20()
    val v3y = pose.m01() * x0 + pose.m11() * y1 + pose.m21()
    val v4x = pose.m00() * x1 + pose.m10() * y1 + pose.m20()
    val v4y = pose.m01() * x1 + pose.m11() * y1 + pose.m21()
    val minX = floor(minOf(v1x, v2x, v3x, v4x)).toInt()
    val minY = floor(minOf(v1y, v2y, v3y, v4y)).toInt()
    val maxX = ceil(maxOf(v1x, v2x, v3x, v4x)).toInt()
    val maxY = ceil(maxOf(v1y, v2y, v3y, v4y)).toInt()
    val rect = ScreenRectangle(minX, minY, maxX - minX, maxY - minY)
    return runCatching { extractor.scissorStack.peek()?.intersection(rect) ?: rect }
        .getOrDefault(rect)
}
