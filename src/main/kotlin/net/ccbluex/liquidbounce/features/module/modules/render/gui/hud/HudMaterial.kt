/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import net.ccbluex.liquidbounce.config.types.NamedChoice

enum class HudMaterialPreset(override val choiceName: String) : NamedChoice {
    MODERN("Modern"),
    FLAT("Flat"),
    MINIMAL("Minimal"),
    GLASS("Glass"),
}

enum class HudDensity(override val choiceName: String) : NamedChoice {
    COMPACT("Compact"),
    NORMAL("Normal")
}

data class HudMaterial(
    val radius: Float,
    val backgroundTop: Int,
    val backgroundBottom: Int,
    val shadowColor: Int,
    val shadowOffsetY: Float,
    val shadowSpread: Float,
    val borderColor: Int,
    val borderWidth: Float,
    val highlightColor: Int,
)

object HudMaterials {
    fun preset(type: HudMaterialPreset): HudMaterial = when (type) {
        HudMaterialPreset.MODERN -> HudMaterial(
            radius = 5f,
            backgroundTop = 0xEA343434.toInt(),
            backgroundBottom = 0xEA161616.toInt(),
            shadowColor = 0x24000000,
            shadowOffsetY = 1.25f,
            shadowSpread = 2.25f,
            borderColor = 0x12787878,
            borderWidth = 0.32f,
            highlightColor = 0x12FFFFFF,
        )

        HudMaterialPreset.FLAT -> HudMaterial(
            radius = 4f,
            backgroundTop = 0xD91F1F1F.toInt(),
            backgroundBottom = 0xD9161616.toInt(),
            shadowColor = 0x14000000,
            shadowOffsetY = 0f,
            shadowSpread = 1.2f,
            borderColor = 0x0E707070,
            borderWidth = 0.25f,
            highlightColor = 0x0CFFFFFF,
        )

        HudMaterialPreset.MINIMAL -> HudMaterial(
            radius = 4f,
            backgroundTop = 0xA81C1C1C.toInt(),
            backgroundBottom = 0xA8121212.toInt(),
            shadowColor = 0x00000000,
            shadowOffsetY = 0f,
            shadowSpread = 0f,
            borderColor = 0x0AFFFFFF,
            borderWidth = 0.22f,
            highlightColor = 0x06FFFFFF,
        )

        HudMaterialPreset.GLASS -> HudMaterial(
            radius = 5f,
            backgroundTop = 0xB8343434.toInt(),
            backgroundBottom = 0xB8202020.toInt(),
            shadowColor = 0x1A000000,
            shadowOffsetY = 1f,
            shadowSpread = 1.8f,
            borderColor = 0x12787878,
            borderWidth = 0.32f,
            highlightColor = 0x14FFFFFF,
        )
    }
}
