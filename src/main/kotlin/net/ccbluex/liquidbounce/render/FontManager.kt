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
package net.ccbluex.liquidbounce.render

import net.ccbluex.liquidbounce.render.font.msdf.FontRenderer
import net.ccbluex.liquidbounce.render.font.msdf.Fonts
import net.ccbluex.liquidbounce.render.font.msdf.MsdfFontLibrary
import net.ccbluex.liquidbounce.render.font.msdf.MsdfTextRenderer
import java.util.concurrent.ConcurrentHashMap

object FontManager {
    const val TITLE_FONT_NAME: String = "suisseintlsemibold"
    const val BODY_FONT_NAME: String = "suisseintlmedium"
    const val DEFAULT_FONT_NAME: String = "medium"
    const val CLICK_GUI_BODY_FONT_NAME: String = "silent_sf_pro_regular"
    const val CLICK_GUI_ICON_FONT_NAME: String = "silentcoreicons"

    private val requiredFonts = listOf(
        DEFAULT_FONT_NAME,
        "regular",
        "bold",
        "regular_semibold",
        BODY_FONT_NAME,
        "suisseintlregular",
        TITLE_FONT_NAME,
        CLICK_GUI_BODY_FONT_NAME,
        CLICK_GUI_ICON_FONT_NAME
    )

    private val displayRendererCache = ConcurrentHashMap<RendererKey, FontRenderer>()

    fun initialize() {
        reload()
    }

    fun reload() {
        clearCache()
        MsdfFontLibrary.preloadRequiredFonts(requiredFonts)
    }

    fun clearCache() {
        displayRendererCache.values.forEach(FontRenderer::clearResolvedFont)
        displayRendererCache.clear()
        Fonts.clearCache()
        MsdfFontLibrary.clearCache()
    }

    fun title(size: Float): FontRenderer = displayRenderer(TITLE_FONT_NAME, size)

    fun body(size: Float): FontRenderer = displayRenderer(BODY_FONT_NAME, size)

    fun default(size: Float): FontRenderer = displayRenderer(DEFAULT_FONT_NAME, size)

    fun clickGuiBody(size: Float): FontRenderer = displayRenderer(CLICK_GUI_BODY_FONT_NAME, size)

    fun clickGuiIcons(size: Float): FontRenderer = displayRenderer(CLICK_GUI_ICON_FONT_NAME, size)

    fun textHeight(size: Float): Float = MsdfTextRenderer.visualHeight(size)

    fun lineHeight(size: Float, fontName: String = DEFAULT_FONT_NAME): Float =
        MsdfTextRenderer.lineHeight(displayRenderer(fontName, size).resolvedFont(), size)

    fun requiredFontNames(): List<String> = requiredFonts

    private fun displayRenderer(fontName: String, size: Float): FontRenderer {
        val normalizedSize = if (size.isFinite()) size.coerceAtLeast(0.1f) else 0.1f
        return displayRendererCache.computeIfAbsent(RendererKey(fontName, normalizedSize)) {
            Fonts.createDisplay(normalizedSize, fontName)
        }
    }

    private data class RendererKey(val fontName: String, val size: Float)
}
