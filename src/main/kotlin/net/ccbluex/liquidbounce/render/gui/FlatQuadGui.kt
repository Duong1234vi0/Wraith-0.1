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
@file:JvmName("FlatQuadGui")

package net.ccbluex.liquidbounce.render.gui

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

fun GuiGraphicsExtractor.drawFlatQuad(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    color: Int
) {
    if (width <= 0f || height <= 0f) {
        return
    }

    val x0 = min(x, x + width)
    val y0 = min(y, y + height)
    val x1 = max(x, x + width)
    val y1 = max(y, y + height)

    this.guiRenderState.addGuiElement(
        FlatQuadGuiElementRenderState(
            x0,
            y0,
            x1,
            y1,
            color,
            Matrix3x2f(this.pose()),
            this.scissorStack.peek(),
            flatQuadBounds(this, x0, y0, x1, y1)
        )
    )
}

private fun flatQuadBounds(
    extractor: GuiGraphicsExtractor,
    x0: Float,
    y0: Float,
    x1: Float,
    y1: Float
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
