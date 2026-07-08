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
package net.ccbluex.liquidbounce.render.font.msdf

class MsdfGlyph(
    data: FontData.GlyphData,
    atlasWidth: Float,
    atlasHeight: Float
) {
    val code: Int = data.unicode
    private val minU: Float
    private val maxU: Float
    private val minV: Float
    private val maxV: Float
    private val advance: Float = data.advance
    private val topPosition: Float
    private val width: Float
    private val height: Float

    init {
        val atlasBounds = data.atlasBounds
        if (atlasBounds != null) {
            minU = atlasBounds.left / atlasWidth
            maxU = atlasBounds.right / atlasWidth
            minV = 1.0f - atlasBounds.top / atlasHeight
            maxV = 1.0f - atlasBounds.bottom / atlasHeight
        } else {
            minU = 0.0f
            maxU = 0.0f
            minV = 0.0f
            maxV = 0.0f
        }

        val planeBounds = data.planeBounds
        if (planeBounds != null) {
            width = planeBounds.right - planeBounds.left
            height = planeBounds.top - planeBounds.bottom
            topPosition = planeBounds.top
        } else {
            width = 0.0f
            height = 0.0f
            topPosition = 0.0f
        }
    }

    fun appendQuad(size: Float, x: Float, y: Float, color: Int, output: MutableList<MsdfTextQuad>) {
        val scaledWidth = width * size
        val scaledHeight = height * size
        val topY = y - topPosition * size

        output += MsdfTextQuad(
            x0 = x,
            y0 = topY,
            x1 = x + scaledWidth,
            y1 = topY + scaledHeight,
            u0 = minU,
            v0 = minV,
            u1 = maxU,
            v1 = maxV,
            color = color
        )
    }

    fun getWidth(size: Float): Float = advance * size

    fun getVisualWidth(size: Float): Float = width * size
}

data class MsdfTextQuad(
    val x0: Float,
    val y0: Float,
    val x1: Float,
    val y1: Float,
    val u0: Float,
    val v0: Float,
    val u1: Float,
    val v1: Float,
    val color: Int
)
