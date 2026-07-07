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
@file:JvmName("GlowRectGui")

package net.ccbluex.liquidbounce.render.gui

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

/**
 * Submits a soft outer-glow halo around the rounded rectangle
 * `(x, y, width, height)` with corner [radiusPx], fading outward over
 * [spreadPx] pixels. Routed through vanilla 1.26.2's batched GUI render path
 * via [GlowRectGuiElementRenderState] / [GlowRectShader] (ADDITIVE blend).
 *
 * The quad is expanded by [spreadPx] on every side so the fragment shader can
 * fade the SDF distance from the true panel edge outward. [color] is ARGB; its
 * alpha scales the overall glow intensity.
 */
fun GuiGraphicsExtractor.drawGlowRect(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radiusPx: Float,
    color: Int,
    spreadPx: Float,
) = drawGradientGlowRect(
    x,
    y,
    width,
    height,
    radiusPx,
    color,
    color,
    color,
    color,
    spreadPx,
)

@Suppress("LongParameterList")
fun GuiGraphicsExtractor.drawGradientGlowRect(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radiusPx: Float,
    colorTopLeft: Int,
    colorTopRight: Int,
    colorBottomRight: Int,
    colorBottomLeft: Int,
    spreadPx: Float,
) {
    if (width <= 0f || height <= 0f || spreadPx <= 0f) {
        return
    }

    val spread = spreadPx.coerceAtMost(2048f)
    val x0 = min(x, x + width) - spread
    val y0 = min(y, y + height) - spread
    val x1 = max(x, x + width) + spread
    val y1 = max(y, y + height) + spread

    val maxRadius = min((x1 - x0), (y1 - y0)) * 0.5f
    val clampedRadius = radiusPx.coerceIn(0f, maxRadius)

    this.guiRenderState.addGuiElement(
        GlowRectGuiElementRenderState(
            x0, y0, x1, y1,
            colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft,
            clampedRadius, spread,
            Matrix3x2f(this.pose()),
            this.scissorStack.peek(),
            glowBounds(this, x0, y0, x1, y1),
        )
    )
}

private fun glowBounds(
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
    val rect = ScreenRectangle(minX, minY, max(1, maxX - minX), max(1, maxY - minY))
    return runCatching { extractor.scissorStack.peek()?.intersection(rect) ?: rect }
        .getOrDefault(rect)
}
