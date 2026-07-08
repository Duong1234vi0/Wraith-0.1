package net.ccbluex.liquidbounce.utils.client

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FormattedText.StyledContentConsumer
import net.minecraft.network.chat.Style
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Optional

class LegacyTextSanitizerTest {

    @Test
    fun test() {
        assertEquals(listOf("This is a Test!" to Style.EMPTY), getResults(Component.literal("This is a Test!")))
    }

    private fun getResults(text: Component): ArrayList<Pair<String, Style>> {
        val visitor = TestVisitor()

        text.visit<Unit>(LegacyTextSanitizer(visitor), Style.EMPTY)

        return visitor.contents
    }

    private class TestVisitor : StyledContentConsumer<Unit> {
        val contents = ArrayList<Pair<String, Style>>()

        override fun accept(style: Style, asString: String): Optional<Unit> {
            contents.add(asString to style)

            return Optional.empty()
        }
    }
}
