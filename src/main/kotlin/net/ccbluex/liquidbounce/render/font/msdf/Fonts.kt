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

import net.ccbluex.liquidbounce.utils.client.mc
import java.util.concurrent.ConcurrentHashMap

object Fonts {
    private val cache = ConcurrentHashMap<FontKey, FontRenderer>()

    fun getSize(size: Int, type: FontType = FontType.DEFAULT): FontRenderer =
        cache.computeIfAbsent(FontKey(size, type.typeName, currentGuiScale())) {
            create(size.toFloat(), type.typeName)
        }

    fun create(size: Float, typeName: String = FontType.DEFAULT.typeName): FontRenderer =
        FontRenderer(typeName, size / 2.0f)

    fun createDisplay(size: Float, typeName: String = FontType.DEFAULT.typeName): FontRenderer =
        FontRenderer(typeName, size)

    fun clearCache() {
        cache.values.forEach(FontRenderer::clearResolvedFont)
        cache.clear()
    }

    private fun currentGuiScale(): Float =
        (maxOf(4, mc.window.guiScale.toInt()).toFloat() * 1.5f)

    private data class FontKey(val size: Int, val type: String, val guiScale: Float)

    enum class FontType(val typeName: String) {
        DEFAULT("sf_medium"),
        REGULAR("sf_regular"),
        SEMI("sf_semibold"),
        BOLD("sf_bold"),
        SUISSE_MEDIUM("suisseintl-medium"),
        SUISSE_REGULAR("suisseintl-regular"),
        SUISSE_SEMIBOLD("suisseintl-semibold")
    }
}
