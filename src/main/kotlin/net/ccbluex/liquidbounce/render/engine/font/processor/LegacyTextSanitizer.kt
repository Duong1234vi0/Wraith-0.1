package net.ccbluex.liquidbounce.render.engine.font.processor
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.network.chat.*
import net.minecraft.network.chat.FormattedText.StyledContentConsumer
import net.minecraft.ChatFormatting
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.FormattedCharSink
import java.util.*

/**
 * This is a utility class which degenerates legacy formatting which is contained in new minecraft formatting
 * (i.e. `{text: "§a§lYeet"}`) into pure new formatting (i.e. `{text: "Yeet", bold: true, color: "green"}`).
 *
 * @param innerVisitor the receiver of the degenerated text formatting.
 */
class LegacyTextSanitizer(
    private val innerVisitor: StyledContentConsumer<Unit>
): StyledContentConsumer<Unit> {

    override fun accept(style: Style, text: String): Optional<Unit> {
        var currentStyle = style

        var currentIndex = 0

        while (currentIndex < text.length) {
            val nextCommand = text.indexOf('§', currentIndex)

            // If there is no more paragraph or if the paragraph is the last in the text, stop the processing.
            if (nextCommand == -1 || nextCommand + 1 >= text.length) {
                break
            }


            // If there is text before the paragraph, accept it first
            if (currentIndex != nextCommand) {
                this.innerVisitor.accept(currentStyle, text.substring(currentIndex, nextCommand))
            }

            val nextCode = text.codePointAt(nextCommand + 1)

            currentStyle = applyCodeForStyle(nextCode, currentStyle)
            // skip the §X characters
            currentIndex = nextCommand + 2
        }

        if (currentIndex != text.length) {
            this.innerVisitor.accept(currentStyle, text.substring(currentIndex))
        }

        return Optional.empty()
    }

    private fun applyCodeForStyle(codePoint: Int, currentStyle: Style): Style {
        return ChatFormatting.getByCode(codePoint.toChar())?.applyFormatting(currentStyle) ?: currentStyle
    }

    private fun ChatFormatting.applyFormatting(style: Style): Style {
        return when {
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
    }

    class SanitizedLegacyText(private val text: Component): FormattedCharSequence {
        override fun accept(visitor: FormattedCharSink): Boolean {
            var idx = 0

            val degenerator = LegacyTextSanitizer { style, text ->
                for (codePoint in text.chars()) {
                    visitor.accept(idx, style, codePoint)

                    idx++
                }

                Optional.empty()
            }

            text.visit<Unit>(degenerator, Style.EMPTY)

            return true
        }
    }
}
