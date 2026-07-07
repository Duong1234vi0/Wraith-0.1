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

import net.ccbluex.liquidbounce.utils.client.mc

/**
 * Singleton to manage HUD elements, ensuring consistency between the editor and in-game display.
 */
object HudManager {
    val elements = mutableListOf<HudElement>()

    fun findElement(name: String): HudElement? {
        initialize()
        return elements.find { it.name == name }
    }

    fun initialize() {
        if (elements.isNotEmpty()) {
            return
        }

        val screenWidth = mc.window.scaledWidth
        val screenHeight = mc.window.scaledHeight

        elements += WatermarkElement(6, 6)
        elements += ModuleListElement(screenWidth - 150, 6)
        elements += CoordsElement(6, screenHeight - 54)
        elements += FpsCounterElement(6, 30)
        elements += PingHudElement(6, 50)
        elements += MemoryHudElement(6, 70)
        elements += SpeedometerElement(6, 90)
        elements += TPSCounterElement(6, 110)
        elements += TotemCounterElement(6, 130)
        elements += GapplesHudElement(6, 150)
        elements += PVPResourcesElement(6, 172)
        elements += ArmorHudElement(screenWidth / 2 - 44, screenHeight - 82)
        elements += InventoryHudElement(screenWidth - 176, screenHeight - 100)
        elements += PotionHudElement(screenWidth - 140, 96)
        elements += KeyBindsElement(6, 205)
        elements += TargetHudElement(screenWidth / 2 - 77, screenHeight - 142)
        elements += RadarRewriteElement(screenWidth - 92, 10)
        elements += StaffBoardElement(screenWidth - 128, 185)
        elements += KillStatsElement(6, screenHeight - 76)
        elements += ChestCounterElement(6, screenHeight - 96)
        elements += CrosshairElement(screenWidth / 2 - 9, screenHeight / 2 - 9)
        elements += CrosshairArrowsElement(screenWidth / 2 - 20, screenHeight / 2 - 20)
        elements += HotbarElement(screenWidth / 2 - 92, screenHeight - 25)
        elements += LegacyHudElement(6, 250)
        elements += CandleHudElement(screenWidth - 106, 270)
        elements += CompanionElement(screenWidth - 120, 292)
        elements += KillFeedElement(screenWidth - 110, 314)
        elements += ParticlesElement(screenWidth / 2 + 8, screenHeight / 2 + 26)
    }
}
