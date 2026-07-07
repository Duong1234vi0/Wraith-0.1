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

import net.ccbluex.liquidbounce.features.module.modules.render.WyvernHudMode
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

        // #Edit By Codex 2026-06-27: TargetHUD no longer rewrites x/y every render,
        // so compute its old centered defaults once and then let chat dragging persist.
        val targetHud1X = ((mc.window.guiScaledWidth - 100) * 0.5f).toInt().coerceAtLeast(0)
        val targetHud1Y = ((mc.window.guiScaledHeight - 38) * 0.5f + 31.75f).toInt().coerceAtLeast(0)
        val targetHud2X = ((mc.window.guiScaledWidth - 100) * 0.5f).toInt().coerceAtLeast(0)
        val targetHud2Y = ((mc.window.guiScaledHeight - 36) * 0.5f).toInt().coerceAtLeast(0)

        elements += WyvernWatermarkElement("Watermark", 10, 10, WyvernHudMode.HUD1)
        elements += WyvernPotionsElement("Potions", 119, 73, WyvernHudMode.HUD1)
        elements += WyvernKeybindsElement("Keybinds", 260, 73, WyvernHudMode.HUD1)
        elements += WyvernTargetHudElement("TargetHUD", targetHud1X, targetHud1Y, WyvernHudMode.HUD1, centerYOffset = 31.75f)
        elements += WyvernNotifyElement(12, 132)
        elements += WyvernWatermarkElement("WatermarkV2", 5, 5, WyvernHudMode.HUD2)
        elements += WyvernKeybindsElement("KeybindsV2", 5, 30, WyvernHudMode.HUD2)
        elements += WyvernPotionsElement("PotionsV2", 5, 100, WyvernHudMode.HUD2)
        elements += WyvernTargetHudElement("TargetHUDV2", targetHud2X, targetHud2Y, WyvernHudMode.HUD2, centerYOffset = 0f)
        elements += WyvernInventoryElement(10, 165)
    }

    fun pushModuleNotification(moduleName: String, enabled: Boolean) {
        initialize()
        elements.filterIsInstance<HudNotificationSink>().forEach { it.addModuleNotification(moduleName, enabled) }
    }

    fun pushTextNotification(
        title: String,
        message: String,
        severity: net.ccbluex.liquidbounce.event.events.NotificationEvent.Severity
    ) {
        initialize()
        elements.filterIsInstance<HudNotificationSink>().forEach { it.addTextNotification(title, message, severity) }
    }
}
