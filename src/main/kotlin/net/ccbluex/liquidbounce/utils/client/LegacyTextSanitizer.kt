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
package net.ccbluex.liquidbounce.utils.client

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FormattedText.StyledContentConsumer
import net.minecraft.network.chat.Style
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.FormattedCharSink
import java.util.Optional

/**
 * Degenerates legacy formatting embedded in component string content
 * (for example, `{text: "§a§lName"}`) into normal Minecraft component styles.
 */
class LegacyTextSanitizer(
    private val innerVisitor: StyledContentConsumer<Unit>
) : StyledContentConsumer<Unit> {

    override fun accept(style: Style, text: String): Optional<Unit> {
        var currentStyle = style
        var currentIndex = 0

        while (currentIndex < text.length) {
            val nextCommand = text.indexOf('§', currentIndex)

            if (nextCommand == -1 || nextCommand + 1 >= text.length) {
                break
            }

            if (currentIndex != nextCommand) {
                innerVisitor.accept(currentStyle, text.substring(currentIndex, nextCommand))
            }

            currentStyle = applyCodeForStyle(text.codePointAt(nextCommand + 1), currentStyle)
            currentIndex = nextCommand + 2
        }

        if (currentIndex != text.length) {
            innerVisitor.accept(currentStyle, text.substring(currentIndex))
        }

        return Optional.empty()
    }

    private fun applyCodeForStyle(codePoint: Int, currentStyle: Style): Style =
        ChatFormatting.getByCode(codePoint.toChar())?.applyFormatting(currentStyle) ?: currentStyle

    private fun ChatFormatting.applyFormatting(style: Style): Style = when {
        isColor -> style.withColor(this)
        else -> when (this) {
            ChatFormatting.RESET -> Style.EMPTY
            ChatFormatting.BOLD -> style.withBold(true)
            ChatFormatting.OBFUSCATED -> style.withObfuscated(true)
            ChatFormatting.STRIKETHROUGH -> style.withStrikethrough(true)
            ChatFormatting.UNDERLINE -> style.withUnderlined(true)
            ChatFormatting.ITALIC -> style.withItalic(true)
            else -> style
        }
    }

    class SanitizedLegacyText(private val text: Component) : FormattedCharSequence {
        override fun accept(visitor: FormattedCharSink): Boolean {
            var index = 0

            val degenerator = LegacyTextSanitizer { style, string ->
                for (codePoint in string.chars()) {
                    visitor.accept(index, style, codePoint)
                    index++
                }

                Optional.empty()
            }

            text.visit<Unit>(degenerator, Style.EMPTY)

            return true
        }
    }
}
