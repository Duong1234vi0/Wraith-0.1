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
@file:JvmName("MsdfFontGui")

package net.ccbluex.liquidbounce.render.gui

import net.ccbluex.liquidbounce.render.font.msdf.MsdfTextQuad
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import net.minecraft.client.gui.render.TextureSetup
import net.minecraft.resources.Identifier
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max

fun GuiGraphicsExtractor.drawMsdfGlyphBatch(
    textureId: Identifier,
    range: Float,
    thickness: Float,
    smoothness: Float,
    glyphs: List<MsdfTextQuad>
): Boolean {
    if (glyphs.isEmpty()) {
        return true
    }

    val texture = runCatching { mc.textureManager.getTexture(textureId) }.getOrNull() ?: return false
    val view = texture.textureView ?: return false
    val pose = Matrix3x2f(this.pose())
    val scissor = this.scissorStack.peek()
    val quads = FloatArray(glyphs.size * 8)
    val colors = IntArray(glyphs.size)

    glyphs.forEachIndexed { index, glyph ->
        val offset = index * 8
        quads[offset] = glyph.x0
        quads[offset + 1] = glyph.y0
        quads[offset + 2] = glyph.x1
        quads[offset + 3] = glyph.y1
        quads[offset + 4] = glyph.u0
        quads[offset + 5] = glyph.v0
        quads[offset + 6] = glyph.u1
        quads[offset + 7] = glyph.v1
        colors[index] = glyph.color
    }

    this.guiRenderState.addGuiElement(
        MsdfTextBatchGuiElementRenderState(
            quads,
            colors,
            range,
            thickness,
            smoothness,
            TextureSetup.singleTexture(view, MsdfFontShader.linearSampler),
            pose,
            scissor,
            msdfBatchBounds(glyphs, pose, scissor)
        )
    )

    return true
}

private fun msdfBatchBounds(
    glyphs: List<MsdfTextQuad>,
    pose: Matrix3x2f,
    scissor: ScreenRectangle?
): ScreenRectangle? {
    var minX = Float.POSITIVE_INFINITY
    var minY = Float.POSITIVE_INFINITY
    var maxX = Float.NEGATIVE_INFINITY
    var maxY = Float.NEGATIVE_INFINITY

    fun include(x: Float, y: Float) {
        val tx = pose.m00() * x + pose.m10() * y + pose.m20()
        val ty = pose.m01() * x + pose.m11() * y + pose.m21()
        minX = minOf(minX, tx)
        minY = minOf(minY, ty)
        maxX = maxOf(maxX, tx)
        maxY = maxOf(maxY, ty)
    }

    for (glyph in glyphs) {
        include(glyph.x0, glyph.y0)
        include(glyph.x1, glyph.y0)
        include(glyph.x0, glyph.y1)
        include(glyph.x1, glyph.y1)
    }

    if (!minX.isFinite() || !minY.isFinite() || !maxX.isFinite() || !maxY.isFinite()) {
        return null
    }

    val rect = ScreenRectangle(
        floor(minX).toInt(),
        floor(minY).toInt(),
        max(1, ceil(maxX).toInt() - floor(minX).toInt()),
        max(1, ceil(maxY).toInt() - floor(minY).toInt())
    )
    return runCatching { scissor?.intersection(rect) ?: rect }.getOrDefault(rect)
}
