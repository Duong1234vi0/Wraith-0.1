/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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

import com.mojang.blaze3d.systems.RenderSystem
import net.ccbluex.liquidbounce.render.FontManager
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForGUI
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.util.math.MatrixStack
import org.joml.Matrix4f
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * Lightweight 2D renderer inspired by Exos' RenderUtil path.
 *
 * This avoids DrawContext.fill/drawBorder for the ClickGUI surface and builds the
 * geometry directly through Minecraft's render thread tessellator.
 */
object ExosGuiRenderer {

    private const val CORNER_SEGMENTS = 8

    fun roundedRect(
        matrices: MatrixStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int
    ) = roundedGradientRect(matrices, x, y, width, height, radius, color, color, color, color)

    fun roundedGradientRect(
        matrices: MatrixStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }

        val points = roundedPoints(x, y, width, height, radius)
        val centerX = x + width * 0.5f
        val centerY = y + height * 0.5f
        val matrix = matrices.peek().positionMatrix
        val tessellator = RenderSystem.renderThreadTesselator()
        val buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR)

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.disableCull()
        RenderSystem.disableDepthTest()
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR)

        val centerColor = colorAt(centerX, centerY, x, y, width, height, topLeft, topRight, bottomRight, bottomLeft)

        for (i in points.indices) {
            val p1 = points[i]
            val p2 = points[(i + 1) % points.size]

            vertex(buffer, matrix, centerX, centerY, centerColor)
            vertex(buffer, matrix, p1.first, p1.second, colorAt(p1.first, p1.second, x, y, width, height, topLeft, topRight, bottomRight, bottomLeft))
            vertex(buffer, matrix, p2.first, p2.second, colorAt(p2.first, p2.second, x, y, width, height, topLeft, topRight, bottomRight, bottomLeft))
        }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
        RenderSystem.enableDepthTest()
        RenderSystem.enableCull()
        RenderSystem.disableBlend()
    }

    fun roundedOutline(
        matrices: MatrixStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }

        val points = roundedPoints(x, y, width, height, radius)
        val matrix = matrices.peek().positionMatrix
        val tessellator = RenderSystem.renderThreadTesselator()
        val buffer = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR)

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.disableDepthTest()
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR)

        for (point in points) {
            vertex(buffer, matrix, point.first, point.second, color)
        }
        points.firstOrNull()?.let { vertex(buffer, matrix, it.first, it.second, color) }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
        RenderSystem.enableDepthTest()
        RenderSystem.disableBlend()
    }

    fun ringSegment(
        matrices: MatrixStack,
        centerX: Float,
        centerY: Float,
        radius: Float,
        thickness: Float,
        startDegrees: Float,
        sweepDegrees: Float,
        color: Int,
        segments: Int = 56
    ) {
        if (radius <= 0f || thickness <= 0f || sweepDegrees <= 0f) {
            return
        }

        val sweep = sweepDegrees.coerceIn(0f, 360f)
        val outerRadius = radius
        val innerRadius = (radius - thickness).coerceAtLeast(0f)
        val steps = max(3, (segments * (sweep / 360f)).toInt())
        val matrix = matrices.peek().positionMatrix
        val tessellator = RenderSystem.renderThreadTesselator()
        val buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR)

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.disableCull()
        RenderSystem.disableDepthTest()
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR)

        for (i in 0 until steps) {
            val a1 = (startDegrees + sweep * i / steps) * PI.toFloat() / 180f
            val a2 = (startDegrees + sweep * (i + 1) / steps) * PI.toFloat() / 180f

            val outerX1 = centerX + cos(a1.toDouble()).toFloat() * outerRadius
            val outerY1 = centerY + sin(a1.toDouble()).toFloat() * outerRadius
            val innerX1 = centerX + cos(a1.toDouble()).toFloat() * innerRadius
            val innerY1 = centerY + sin(a1.toDouble()).toFloat() * innerRadius
            val outerX2 = centerX + cos(a2.toDouble()).toFloat() * outerRadius
            val outerY2 = centerY + sin(a2.toDouble()).toFloat() * outerRadius
            val innerX2 = centerX + cos(a2.toDouble()).toFloat() * innerRadius
            val innerY2 = centerY + sin(a2.toDouble()).toFloat() * innerRadius

            vertex(buffer, matrix, outerX1, outerY1, color)
            vertex(buffer, matrix, innerX1, innerY1, color)
            vertex(buffer, matrix, innerX2, innerY2, color)

            vertex(buffer, matrix, outerX1, outerY1, color)
            vertex(buffer, matrix, innerX2, innerY2, color)
            vertex(buffer, matrix, outerX2, outerY2, color)
        }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
        RenderSystem.enableDepthTest()
        RenderSystem.enableCull()
        RenderSystem.disableBlend()
    }

    fun text(
        matrices: MatrixStack,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        size: Float = 15f,
        shadow: Boolean = false
    ) {
        if (text.isEmpty()) {
            return
        }

        renderEnvironmentForGUI(matrices) {
            FontManager.FONT_RENDERER.withBuffers { buffers ->
                draw(
                    process(text, Color4b(color, hasAlpha = true)),
                    x,
                    y,
                    shadow = shadow,
                    scale = size / FontManager.DEFAULT_FONT_SIZE
                )
                commit(buffers)
            }
        }
    }

    fun textWidth(text: String, size: Float = 15f): Float {
        if (text.isEmpty()) {
            return 0f
        }

        val font = FontManager.FONT_RENDERER
        return font.getStringWidth(font.process(text), false) * (size / FontManager.DEFAULT_FONT_SIZE)
    }

    fun withAlpha(color: Int, alpha: Int): Int = (alpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF)

    fun mix(a: Int, b: Int, progress: Float): Int {
        val t = progress.coerceIn(0f, 1f)
        val ar = (a shr 16) and 0xFF
        val ag = (a shr 8) and 0xFF
        val ab = a and 0xFF
        val aa = (a ushr 24) and 0xFF
        val br = (b shr 16) and 0xFF
        val bg = (b shr 8) and 0xFF
        val bb = b and 0xFF
        val ba = (b ushr 24) and 0xFF

        return (((aa + (ba - aa) * t).toInt().coerceIn(0, 255)) shl 24) or
            (((ar + (br - ar) * t).toInt().coerceIn(0, 255)) shl 16) or
            (((ag + (bg - ag) * t).toInt().coerceIn(0, 255)) shl 8) or
            ((ab + (bb - ab) * t).toInt().coerceIn(0, 255))
    }

    private fun vertex(
        buffer: net.minecraft.client.render.BufferBuilder,
        matrix: Matrix4f,
        x: Float,
        y: Float,
        color: Int
    ) {
        buffer.vertex(matrix, x, y, 0f).color(color)
    }

    private fun roundedPoints(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float
    ): List<Pair<Float, Float>> {
        val r = radius.coerceIn(0f, min(width, height) * 0.5f)
        if (r <= 0f) {
            return listOf(
                x to y,
                x + width to y,
                x + width to y + height,
                x to y + height
            )
        }

        val points = ArrayList<Pair<Float, Float>>(CORNER_SEGMENTS * 4 + 4)
        corner(points, x + width - r, y + r, -90.0, 0.0, r)
        corner(points, x + width - r, y + height - r, 0.0, 90.0, r)
        corner(points, x + r, y + height - r, 90.0, 180.0, r)
        corner(points, x + r, y + r, 180.0, 270.0, r)
        return points
    }

    private fun corner(
        out: MutableList<Pair<Float, Float>>,
        cx: Float,
        cy: Float,
        startDeg: Double,
        endDeg: Double,
        radius: Float
    ) {
        for (i in 0..CORNER_SEGMENTS) {
            val progress = i.toDouble() / CORNER_SEGMENTS.toDouble()
            val angle = (startDeg + (endDeg - startDeg) * progress) * PI / 180.0
            out += (cx + cos(angle).toFloat() * radius) to (cy + sin(angle).toFloat() * radius)
        }
    }

    private fun colorAt(
        px: Float,
        py: Float,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int
    ): Int {
        val tx = ((px - x) / max(width, 1f)).coerceIn(0f, 1f)
        val ty = ((py - y) / max(height, 1f)).coerceIn(0f, 1f)
        val top = mix(topLeft, topRight, tx)
        val bottom = mix(bottomLeft, bottomRight, tx)
        return mix(top, bottom, ty)
    }
}
