/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
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
package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.render.FontManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

class SilentClickGuiStaticTest {

    @Test
    fun `Silent MSDF atlases are preloaded by Wraith font infrastructure`() {
        assertTrue(FontManager.requiredFontNames().contains("silent_sf_pro_regular"))
        assertTrue(FontManager.requiredFontNames().contains("silentcoreicons"))
        assertTrue(Files.exists(Path.of("src/main/resources/assets/liquidbounce/fonts/msdf/silent_sf_pro_regular.json")))
        assertTrue(Files.exists(Path.of("src/main/resources/assets/liquidbounce/fonts/msdf/silent_sf_pro_regular.png")))
        assertTrue(Files.exists(Path.of("src/main/resources/assets/liquidbounce/fonts/msdf/silentcoreicons.json")))
        assertTrue(Files.exists(Path.of("src/main/resources/assets/liquidbounce/fonts/msdf/silentcoreicons.png")))
    }

    @Test
    fun `old category PNG resources are absent`() {
        listOf("combat", "movement", "player", "render", "misc", "config", "cloud").forEach { id ->
            assertFalse(Files.exists(Path.of("src/main/resources/resources/liquidbounce/clickgui/icons/$id.png")))
        }
    }

    @Test
    fun `production ClickGUI no longer registers dynamic category PNG images`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt")
        )
        assertFalse(source.contains("registerDynamicImageFromResources"))
        assertFalse(source.contains("clickgui/icons/"))
        assertTrue(source.contains("clickGuiIcon"))
        assertTrue(source.contains("Future logo slot: intentionally blank."))
    }

    @Test
    fun `ClickGUI shell transform uses real GUI pose stack`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt")
        )

        assertTrue(source.contains("val pose = context.pose()"))
        assertTrue(source.contains("pose.pushMatrix()"))
        assertTrue(source.contains("pose.translate(transform.centerX, transform.centerY)"))
        assertTrue(source.contains("pose.scale(transform.totalScale, transform.totalScale)"))
        assertTrue(source.contains("pose.popMatrix()"))
        assertFalse(source.contains("matrices.pushPose()"))
        assertFalse(source.contains("matrices.translate(transform.centerX"))
        assertFalse(source.contains("matrices.scale(transform.totalScale"))
    }

    @Test
    fun `ClickGUI local scissor relies on GuiGraphicsExtractor pose transform`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt")
        )

        assertTrue(source.contains("private inline fun withLocalScissor"))
        assertTrue(source.contains("context.enableScissor("))
        assertTrue(source.contains("floor(rect.x).toInt()"))
        assertTrue(source.contains("ceil(rect.right).toInt()"))
        assertFalse(source.contains("transform.localToScreen(rect)"))
        assertFalse(source.contains("context.enableScissor(screen.x0"))
    }

    @Test
    fun `ClickGUI source does not render old or copied branding strings`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt")
        )
        val renderedBranding = Regex(
            "clickGuiBody\\([^\\n]+\"(ASTRA|PREMIUM|Wraith|Silent|Logo|Coming Soon)\""
        )
        assertFalse(source.contains("ASTRA"))
        assertFalse(source.contains("PREMIUM"))
        assertFalse(renderedBranding.containsMatchIn(source))
    }

    @Test
    fun `Wraith ThemeSystem remains the only ClickGUI theme source`() {
        val guiSources = Files.walk(Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui"))
            .filter { Files.isRegularFile(it) && it.toString().endsWith(".kt") }
            .use { paths -> paths.toList().joinToString("\n") { Files.readString(it) } }

        assertTrue(guiSources.contains("ClickGuiTheme"))
        assertTrue(guiSources.contains("ClientTheme"))
        assertFalse(guiSources.contains("ThemeManager"))
        assertFalse(guiSources.contains("AutoBuy"))
    }

    @Test
    fun `production ClickGUI uses compact side settings architecture`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt")
        )
        assertTrue(source.contains("selectedModule"))
        assertTrue(source.contains("sideSettingsScroll"))
        assertTrue(source.contains("SilentClickGuiLayout.settingsViewport"))
        assertFalse(source.contains("expandedModules"))
        assertFalse(source.contains("moduleExpansionAnimations"))
        assertFalse(source.contains("moduleSettingsScroll"))
        assertFalse(source.contains("waterfall"))
        assertFalse(source.contains("MAX_SETTINGS_HEIGHT"))
        assertFalse(source.contains("MarqueeState"))
    }

    @Test
    fun `compact animation replaces dramatic opening scale`() {
        assertEquals(0.94f, SilentClickGuiAnimation.MIN_SCALE)
        assertEquals(220L, SilentClickGuiAnimation.OPEN_SCALE_MS)
        assertEquals(180L, SilentClickGuiAnimation.OPEN_ALPHA_MS)
        assertTrue(SilentClickGuiAnimation.openScale(0) >= 0.94f)
        assertTrue(SilentClickGuiAnimation.openScale(220) <= 1f)
    }

    @Test
    fun `Silent mode selector does not draw a selected rounded background`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/SilentClickGuiSettings.kt")
        )

        assertTrue(source.contains("SilentClickGuiSettingsMath.modeOptionGeometry"))
        assertFalse(source.contains("fade(0x26FFFFFF"))
    }

    @Test
    fun `Silent multi checkbox active indicator is not a body-font glyph`() {
        val source = Files.readString(
            Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/SilentClickGuiSettings.kt")
        )

        assertTrue(source.contains("SilentClickGuiSettingsMath.checkboxGeometry"))
        assertFalse(source.contains("\"v\""))
        assertFalse(source.contains("\"✓\""))
    }

}
