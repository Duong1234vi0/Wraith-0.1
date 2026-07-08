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

import net.minecraft.resources.Identifier
import kotlin.math.sin

class MsdfFont(
    val name: String,
    val textureId: Identifier,
    val atlas: FontData.AtlasData,
    val metrics: FontData.MetricsData,
    private val glyphs: Map<Int, MsdfGlyph>,
    private val kernings: Map<Int, Map<Int, Float>>
) {
    fun applyGlyphs(
        text: String,
        size: Float,
        thickness: Float,
        spacing: Float,
        startX: Float,
        baselineY: Float,
        color: Int,
        output: MutableList<MsdfTextQuad>
    ) {
        var x = startX
        var previousChar = -1

        for (char in text) {
            val code = char.code
            val glyph = glyphs[code]
            if (glyph == null) {
                if (char != ' ') {
                    continue
                }

                x += getSpaceAdvance(size) + thickness + spacing
                previousChar = code
                continue
            }

            kernings[previousChar]?.let { kerning ->
                x += (kerning[code] ?: 0.0f) * size
            }

            glyph.appendQuad(size, x, baselineY, color, output)
            x += glyph.getWidth(size) + thickness + spacing
            previousChar = code
        }
    }

    fun applyRainbowGlyphs(
        text: String,
        size: Float,
        thickness: Float,
        spacing: Float,
        startX: Float,
        baselineY: Float,
        color1: Int,
        color2: Int,
        output: MutableList<MsdfTextQuad>
    ) {
        var x = startX
        var previousChar = -1
        val time = System.currentTimeMillis()

        text.forEachIndexed { index, char ->
            val code = char.code
            val glyph = glyphs[code]
            if (glyph == null) {
                if (char != ' ') {
                    return@forEachIndexed
                }

                x += getSpaceAdvance(size) + thickness + spacing
                previousChar = code
                return@forEachIndexed
            }

            kernings[previousChar]?.let { kerning ->
                x += (kerning[code] ?: 0.0f) * size
            }

            val t = (((sin((time + index * 100L).toDouble() / 500.0) + 1.0) / 2.0).toFloat())
                .coerceIn(0.0f, 1.0f)
            glyph.appendQuad(size, x, baselineY, interpolateColor(color1, color2, t), output)
            x += glyph.getWidth(size) + thickness + spacing
            previousChar = code
        }
    }

    fun getWidth(text: String, size: Float): Float = getLayoutWidth(text, size, 0.0f, 0.0f)

    fun getLayoutWidth(text: String?, size: Float, thickness: Float, spacing: Float): Float {
        if (text.isNullOrEmpty()) {
            return 0.0f
        }

        var previousChar = -1
        var width = 0.0f

        for (char in text) {
            val code = char.code
            val glyph = glyphs[code]
            if (glyph == null) {
                if (char != ' ') {
                    continue
                }

                width += getSpaceAdvance(size) + thickness + spacing
                previousChar = code
                continue
            }

            kernings[previousChar]?.let { kerning ->
                width += (kerning[code] ?: 0.0f) * size
            }

            width += glyph.getWidth(size) + thickness + spacing
            previousChar = code
        }

        return width
    }

    fun getVisualRightEdge(text: String?, size: Float, effectiveThickness: Float, spacing: Float): Float {
        if (text.isNullOrEmpty()) {
            return 0.0f
        }

        var previousChar = -1
        var x = 0.0f

        text.forEachIndexed { index, char ->
            val code = char.code
            val glyph = glyphs[code] ?: return@forEachIndexed

            kernings[previousChar]?.let { kerning ->
                x += (kerning[code] ?: 0.0f) * size
            }

            x += if (index < text.length - 1) {
                glyph.getWidth(size) + effectiveThickness + spacing
            } else {
                glyph.getVisualWidth(size)
            }
            previousChar = code
        }

        return x
    }

    private fun getSpaceAdvance(size: Float): Float =
        glyphs[' '.code]?.getWidth(size) ?: 0.25f * size

    private fun interpolateColor(colorStart: Int, colorEnd: Int, t: Float): Int {
        val clamped = t.coerceIn(0.0f, 1.0f)
        val startAlpha = ((colorStart ushr 24) and 0xFF) / 255.0f
        val startRed = ((colorStart ushr 16) and 0xFF) / 255.0f
        val startGreen = ((colorStart ushr 8) and 0xFF) / 255.0f
        val startBlue = (colorStart and 0xFF) / 255.0f
        val endAlpha = ((colorEnd ushr 24) and 0xFF) / 255.0f
        val endRed = ((colorEnd ushr 16) and 0xFF) / 255.0f
        val endGreen = ((colorEnd ushr 8) and 0xFF) / 255.0f
        val endBlue = (colorEnd and 0xFF) / 255.0f

        val alpha = startAlpha + clamped * (endAlpha - startAlpha)
        val red = startRed + clamped * (endRed - startRed)
        val green = startGreen + clamped * (endGreen - startGreen)
        val blue = startBlue + clamped * (endBlue - startBlue)

        return (alpha * 255.0f).toInt() shl 24 or
            ((red * 255.0f).toInt() shl 16) or
            ((green * 255.0f).toInt() shl 8) or
            (blue * 255.0f).toInt()
    }

    companion object {
        fun resolveMsdfResourceName(name: String): String = when (name) {
            "suisseintl-semibold", "suisseintlsemibold" -> "suisseintlsemibold"
            else -> name
        }

        fun fromData(name: String, textureId: Identifier, data: FontData): MsdfFont {
            val atlas = data.atlas
            val glyphs = data.glyphs.associate { glyph ->
                glyph.unicode to MsdfGlyph(glyph, atlas.width, atlas.height)
            }
            val kernings = data.kerning
                .groupBy { it.leftChar }
                .mapValues { (_, values) -> values.associate { it.rightChar to it.advance } }

            return MsdfFont(name, textureId, atlas, data.metrics, glyphs, kernings)
        }
    }
}
