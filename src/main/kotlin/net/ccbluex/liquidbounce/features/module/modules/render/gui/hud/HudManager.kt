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

        elements += SilentNotificationElement()
        elements += SilentWatermarkElement()
        elements += SilentHotKeysElement()
        elements += SilentInformationElement()
        elements += CelestialTargetHudElement()
        elements += SilentStaffListElement()
    }

    fun resetTransient() {
        elements.forEach(HudElement::resetState)
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
