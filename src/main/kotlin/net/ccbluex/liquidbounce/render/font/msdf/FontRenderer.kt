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

import net.ccbluex.liquidbounce.features.module.modules.misc.nameprotect.sanitizeForeignInput
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import java.util.Optional

class FontRenderer(
    val fontTypeName: String,
    val displaySize: Float
) {
    private var msdfFont: MsdfFont? = null

    val height: Float
        get() = getStringHeight("A")

    val lineHeight: Float
        get() = MsdfTextRenderer.lineHeight(resolvedFont(), displaySize)

    fun resolvedFont(): MsdfFont = msdfFont ?: MsdfFontLibrary.resolveType(fontTypeName).also { msdfFont = it }

    fun clearResolvedFont() {
        msdfFont = null
    }

    fun drawText(context: GuiGraphicsExtractor, text: Component, x: Float, y: Float, color: Int = -1): Boolean =
        drawString(context, componentToFormattedString(text), x, y, color)

    fun drawString(context: GuiGraphicsExtractor, text: String, x: Float, y: Float, color: Int): Boolean =
        MsdfTextRenderer.drawString(context, resolvedFont(), displaySize, text, x, y, color)

    fun drawFlatString(context: GuiGraphicsExtractor, text: String, x: Float, y: Float, color: Int): Boolean =
        MsdfTextRenderer.drawFlatString(context, resolvedFont(), displaySize, text, x, y, color)

    fun drawStringWithSpacing(
        context: GuiGraphicsExtractor,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        spacing: Float
    ): Boolean = MsdfTextRenderer.drawStringWithSpacing(context, resolvedFont(), displaySize, text, x, y, color, spacing)

    fun drawGradientString(
        context: GuiGraphicsExtractor,
        text: String,
        x: Float,
        y: Float,
        colorStart: Int,
        colorEnd: Int
    ): Boolean = MsdfTextRenderer.drawGradientString(context, resolvedFont(), displaySize, text, x, y, colorStart, colorEnd)

    fun drawRainbowString(
        context: GuiGraphicsExtractor,
        text: String,
        x: Float,
        y: Float,
        color1: Int,
        color2: Int
    ): Boolean = MsdfTextRenderer.drawRainbowString(context, resolvedFont(), displaySize, text, x, y, color1, color2)

    fun drawHalfSplitString(
        context: GuiGraphicsExtractor,
        text: String,
        x: Float,
        y: Float,
        color1: Int,
        color2: Int
    ): Boolean = MsdfTextRenderer.drawHalfSplitString(context, resolvedFont(), displaySize, text, x, y, color1, color2)

    fun drawCenteredString(context: GuiGraphicsExtractor, text: String, x: Float, y: Float, color: Int): Boolean =
        drawString(context, text, x - getStringWidth(text) / 2.0f, y, color)

    fun drawVerticallyCentered(
        context: GuiGraphicsExtractor,
        text: String,
        x: Float,
        y: Float,
        height: Float,
        color: Int
    ): Boolean = drawString(context, text, x, getCenteredY(y, height, text), color)

    fun drawCenteredBoth(
        context: GuiGraphicsExtractor,
        text: String,
        centerX: Float,
        y: Float,
        height: Float,
        color: Int
    ): Boolean = drawCenteredString(context, text, centerX, getCenteredY(y, height, text), color)

    fun getCenteredY(boxY: Float, boxHeight: Float, sample: String = "A"): Float {
        val text = sample.ifEmpty { "A" }
        return boxY + (boxHeight - getStringHeight(text)) / 2.0f
    }

    fun getStringWidth(text: Component): Float = getStringWidth(text.string)

    fun getStringWidth(text: String?): Float =
        MsdfTextRenderer.getStringWidth(resolvedFont(), displaySize, text)

    fun getFlatStringWidth(text: String?): Float =
        MsdfTextRenderer.getFlatStringWidth(resolvedFont(), displaySize, text)

    fun getStringHeight(text: Component): Float = getStringHeight(text.string)

    fun getStringHeight(text: String?): Float =
        MsdfTextRenderer.getStringHeight(resolvedFont(), displaySize, text)

    fun trimToWidth(text: String, maxWidth: Int, backwards: Boolean = false): String =
        if (backwards) trimToWidthBackwards(text, maxWidth) else trimToWidth(text, maxWidth)

    fun trimToWidth(text: String, maxWidth: Int): String =
        text.substring(0, getTrimmedLength(text, maxWidth))

    fun trimToWidthBackwards(text: String, maxWidth: Int): String {
        for (index in text.length downTo 0) {
            val suffix = text.substring(index)
            if (getStringWidth(suffix) <= maxWidth.toFloat()) {
                return suffix
            }
        }
        return ""
    }

    private fun getTrimmedLength(text: String, maxWidth: Int): Int {
        var length = 0
        var index = 1

        while (index <= text.length) {
            if (getStringWidth(text.substring(0, index)) > maxWidth.toFloat()) {
                return maxOf(0, index - 1)
            }
            length = index
            index++
        }

        return length
    }

    private fun componentToFormattedString(component: Component): String {
        val sanitized = component.sanitizeForeignInput()
        val builder = StringBuilder()

        sanitized.visit<Unit>({ style, text ->
            appendStyle(builder, style)
            builder.append(text).append('§').append('r')
            Optional.empty()
        }, Style.EMPTY)

        return builder.toString()
    }

    private fun appendStyle(builder: StringBuilder, style: Style) {
        val color = style.color?.value ?: return
        builder.append(MsdfTextRenderer.formatColor(0xFF000000.toInt() or (color and 0x00FFFFFF)))
    }
}
