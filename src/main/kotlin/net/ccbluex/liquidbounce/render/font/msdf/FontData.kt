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

import com.google.gson.annotations.SerializedName

data class FontData(
    val atlas: AtlasData,
    val metrics: MetricsData,
    val glyphs: List<GlyphData>,
    val kerning: List<KerningData> = emptyList()
) {
    data class AtlasData(
        @SerializedName(value = "distanceRange", alternate = ["range"])
        val distanceRange: Float,
        val width: Float,
        val height: Float
    ) {
        val range: Float
            get() = distanceRange
    }

    data class MetricsData(
        val lineHeight: Float,
        val ascender: Float,
        val descender: Float
    ) {
        val baselineHeight: Float
            get() = lineHeight + descender
    }

    data class BoundsData(
        val left: Float,
        val bottom: Float,
        val right: Float,
        val top: Float
    )

    data class GlyphData(
        val unicode: Int,
        val advance: Float,
        val planeBounds: BoundsData?,
        val atlasBounds: BoundsData?
    )

    data class KerningData(
        @SerializedName("unicode1")
        val leftChar: Int,
        @SerializedName("unicode2")
        val rightChar: Int,
        val advance: Float
    )
}
