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

import net.ccbluex.liquidbounce.features.module.modules.misc.nameprotect.ModuleNameProtect
import net.ccbluex.liquidbounce.render.gui.drawMsdfGlyphBatch
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.GuiGraphicsExtractor

object MsdfTextRenderer {
    const val THICKNESS: Float = 0.05f
    const val SMOOTHNESS: Float = 0.5f
    const val FLAT_THICKNESS: Float = 0.03f
    const val FLAT_SMOOTHNESS: Float = 0.2f
    const val BASELINE_FACTOR: Float = 0.7f
    const val X_NUDGE: Float = -0.75f

    private const val TEXT_COLOR = -1
    private const val CUSTOM_COLOR_DELIMITER = '\u23cf'

    fun visualHeight(size: Float): Float = size * BASELINE_FACTOR

    fun lineHeight(font: MsdfFont, size: Float): Float = font.metrics.lineHeight * size

    fun getStringWidth(font: MsdfFont, size: Float, text: String?): Float {
        val resolved = resolveText(text)
        val effectiveThickness = 0.025f * size
        var width = 0.0f

        for (segment in parseSegments(resolved, -1)) {
            width += font.getLayoutWidth(segment.text, size, effectiveThickness, 0.0f)
        }

        return width
    }

    fun getFlatStringWidth(font: MsdfFont, size: Float, text: String?): Float {
        val resolved = resolveText(text)
        val effectiveThickness = 0.015f * size
        var width = 0.0f

        for (segment in parseSegments(resolved, -1)) {
            width += font.getLayoutWidth(segment.text, size, effectiveThickness, 0.0f)
        }

        return width
    }

    fun getStringHeight(font: MsdfFont, size: Float, text: String?): Float {
        val resolved = resolveText(text)
        if (resolved.isNullOrEmpty()) {
            return visualHeight(size)
        }

        val lines = resolved.split('\n')
        if (lines.size <= 1) {
            return visualHeight(size)
        }

        return lineHeight(font, size) * (lines.size - 1) + visualHeight(size)
    }

    fun drawString(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        color: Int
    ): Boolean = drawFormatted(context, font, size, text, x, y, normalizeArgb(color), THICKNESS, SMOOTHNESS, 0.0f)

    fun drawFlatString(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        color: Int
    ): Boolean = drawFormatted(context, font, size, text, x, y, normalizeArgb(color), FLAT_THICKNESS, FLAT_SMOOTHNESS, 0.0f)

    fun drawStringWithSpacing(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        color: Int,
        spacing: Float
    ): Boolean = drawFormatted(context, font, size, text, x, y, normalizeArgb(color), THICKNESS, SMOOTHNESS, spacing)

    fun drawGradientString(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        colorStart: Int,
        colorEnd: Int
    ): Boolean {
        val resolved = resolveText(text)
        if (resolved.isNullOrEmpty()) {
            return true
        }

        val quads = ArrayList<MsdfTextQuad>()
        val effectiveThickness = 0.025f * size
        var drawY = y

        for (line in resolved.split('\n')) {
            drawGradientLine(
                font,
                size,
                line,
                x,
                drawY,
                normalizeArgb(colorStart),
                normalizeArgb(colorEnd),
                effectiveThickness,
                quads
            )
            drawY += lineHeight(font, size)
        }

        return context.drawMsdfGlyphBatch(font.textureId, font.atlas.range, THICKNESS, SMOOTHNESS, quads)
    }

    fun drawRainbowString(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        color1: Int,
        color2: Int
    ): Boolean {
        val resolved = resolveText(text)
        if (resolved.isNullOrEmpty()) {
            return true
        }

        val quads = ArrayList<MsdfTextQuad>()
        val effectiveThickness = 0.025f * size
        var drawY = y

        for (line in resolved.split('\n')) {
            font.applyRainbowGlyphs(
                line,
                size,
                effectiveThickness,
                0.0f,
                x + X_NUDGE,
                drawY + size * BASELINE_FACTOR,
                normalizeArgb(color1),
                normalizeArgb(color2),
                quads
            )
            drawY += lineHeight(font, size)
        }

        return context.drawMsdfGlyphBatch(font.textureId, font.atlas.range, THICKNESS, SMOOTHNESS, quads)
    }

    fun drawHalfSplitString(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        color1: Int,
        color2: Int
    ): Boolean {
        val flat = resolveText(text)?.replace("\n", "") ?: return true
        if (flat.isEmpty()) {
            return true
        }

        val totalWidth = getStringWidth(font, size, flat)
        val midX = x + totalWidth / 2.0f
        val effectiveThickness = 0.025f * size
        val quads = ArrayList<MsdfTextQuad>()
        var cursorX = x
        val drawY = y + size * BASELINE_FACTOR

        for (char in flat) {
            val glyph = char.toString()
            val glyphWidth = font.getLayoutWidth(glyph, size, effectiveThickness, 0.0f)
            if (char != ' ') {
                val color = if (cursorX + glyphWidth / 2.0f < midX) color1 else color2
                font.applyGlyphs(
                    glyph,
                    size,
                    effectiveThickness,
                    0.0f,
                    cursorX + X_NUDGE,
                    drawY,
                    normalizeArgb(color),
                    quads
                )
            }
            cursorX += glyphWidth
        }

        return context.drawMsdfGlyphBatch(font.textureId, font.atlas.range, THICKNESS, SMOOTHNESS, quads)
    }

    private fun drawFormatted(
        context: GuiGraphicsExtractor,
        font: MsdfFont,
        size: Float,
        text: String?,
        x: Float,
        y: Float,
        defaultColor: Int,
        thickness: Float,
        smoothness: Float,
        spacing: Float
    ): Boolean {
        val resolved = resolveText(text)
        if (resolved.isNullOrEmpty()) {
            return true
        }

        val quads = ArrayList<MsdfTextQuad>()
        val effectiveThickness = (thickness * 0.5f) * size
        var drawY = y

        for (line in resolved.split('\n')) {
            var lineX = x
            for (segment in parseSegments(line, defaultColor)) {
                if (segment.text.isEmpty()) {
                    continue
                }
                font.applyGlyphs(
                    segment.text,
                    size,
                    effectiveThickness,
                    spacing,
                    lineX + X_NUDGE,
                    drawY + size * BASELINE_FACTOR,
                    segment.color,
                    quads
                )
                lineX += font.getLayoutWidth(segment.text, size, effectiveThickness, spacing)
            }
            drawY += lineHeight(font, size)
        }

        return context.drawMsdfGlyphBatch(font.textureId, font.atlas.range, thickness, smoothness, quads)
    }

    private fun drawGradientLine(
        font: MsdfFont,
        size: Float,
        line: String,
        x: Float,
        y: Float,
        colorStart: Int,
        colorEnd: Int,
        effectiveThickness: Float,
        quads: MutableList<MsdfTextQuad>
    ) {
        if (line.isEmpty()) {
            return
        }

        var cursorX = x
        val length = line.length

        line.forEachIndexed { index, char ->
            val glyph = char.toString()
            if (char != ' ') {
                val t = if (length <= 1) 0.0f else index.toFloat() / (length - 1).toFloat()
                font.applyGlyphs(
                    glyph,
                    size,
                    effectiveThickness,
                    0.0f,
                    cursorX + X_NUDGE,
                    y + size * BASELINE_FACTOR,
                    interpolateColor(colorStart, colorEnd, t),
                    quads
                )
            }
            cursorX += font.getLayoutWidth(glyph, size, effectiveThickness, 0.0f)
        }
    }

    private fun resolveText(text: String?): String? =
        if (text != null && ModuleNameProtect.running) ModuleNameProtect.replace(text) else text

    private fun parseSegments(text: String?, defaultColor: Int): List<TextSegment> {
        if (text.isNullOrEmpty()) {
            return emptyList()
        }

        val segments = ArrayList<TextSegment>()
        val current = StringBuilder()
        var color = if (defaultColor == -1) TEXT_COLOR else defaultColor
        var colorFormat = false
        var customColor = false
        val customColorBuffer = StringBuilder()

        for (char in text) {
            if (char == '§') {
                flushSegment(segments, current, color)
                colorFormat = true
                continue
            }

            if (colorFormat) {
                colorFormat = false
                val code = char.uppercaseChar()
                val nextColor = minecraftColor(code)
                if (nextColor != null) {
                    color = nextColor
                } else if (code == 'R' && defaultColor != -1) {
                    color = defaultColor
                }
                continue
            }

            if (char == CUSTOM_COLOR_DELIMITER) {
                if (customColor) {
                    color = runCatching { opaqueRgb(customColorBuffer.toString().toInt()) }
                        .getOrDefault(color)
                    customColorBuffer.setLength(0)
                }
                customColor = !customColor
                continue
            }

            if (customColor) {
                customColorBuffer.append(char)
                continue
            }

            current.append(char)
        }

        flushSegment(segments, current, color)
        return segments
    }

    private fun flushSegment(segments: MutableList<TextSegment>, current: StringBuilder, color: Int) {
        if (current.isNotEmpty()) {
            segments += TextSegment(current.toString(), normalizeArgb(color))
            current.setLength(0)
        }
    }

    fun formatColor(color: Int): String = "$CUSTOM_COLOR_DELIMITER${normalizeArgb(color)}$CUSTOM_COLOR_DELIMITER"

    fun normalizeArgb(color: Int): Int =
        if (color != 0 && (color ushr 24) == 0) color or 0xFF000000.toInt() else color

    private fun opaqueRgb(color: Int): Int = 0xFF000000.toInt() or (color and 0x00FFFFFF)

    fun interpolateColor(colorStart: Int, colorEnd: Int, t: Float): Int {
        val clamped = t.coerceIn(0.0f, 1.0f)
        val a1 = (colorStart ushr 24) and 0xFF
        val r1 = (colorStart ushr 16) and 0xFF
        val g1 = (colorStart ushr 8) and 0xFF
        val b1 = colorStart and 0xFF
        val a2 = (colorEnd ushr 24) and 0xFF
        val r2 = (colorEnd ushr 16) and 0xFF
        val g2 = (colorEnd ushr 8) and 0xFF
        val b2 = colorEnd and 0xFF
        val a = (a1 + (a2 - a1) * clamped).toInt()
        val r = (r1 + (r2 - r1) * clamped).toInt()
        val g = (g1 + (g2 - g1) * clamped).toInt()
        val b = (b1 + (b2 - b1) * clamped).toInt()

        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    private fun minecraftColor(code: Char): Int? {
        val color = ChatFormatting.getByCode(code.lowercaseChar())?.takeIf { it.isColor }?.color ?: return null
        return 0xFF000000.toInt() or (color and 0x00FFFFFF)
    }

    private data class TextSegment(val text: String, val color: Int)
}
