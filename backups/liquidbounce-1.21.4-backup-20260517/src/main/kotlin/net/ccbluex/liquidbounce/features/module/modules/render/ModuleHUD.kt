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
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.MouseCursorEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.ScreenRenderEvent
import net.ccbluex.liquidbounce.event.events.SpaceSeperatedNamesChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.config.types.Value
import net.ccbluex.liquidbounce.features.misc.HideAppearance.isDestructed
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudConfig
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudElement
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudManager
import net.ccbluex.liquidbounce.utils.client.inGame
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.screen.ChatScreen
import org.lwjgl.glfw.GLFW
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Module HUD
 *
 * The client in-game dashboard.
 */

object ModuleHud : ClientModule("HUD", Category.RENDER, state = true, hide = true) {

    override val running
        get() = this.enabled && !isDestructed && inGame

    @Suppress("unused")
    private val spaceSeperatedNames by boolean("SpaceSeperatedNames", true).onChange { state ->
        EventManager.callEvent(SpaceSeperatedNamesChangeEvent(state))
        state
    }

    val centeredCrosshair by boolean("CenteredCrosshair", false)

    private val elementToggles = LinkedHashMap<String, Value<Boolean>>()

    private val watermark = elementToggle("WaterMark", "Watermark")
    private val moduleList = elementToggle("ArrayList", "ModuleList")
    private val coords = elementToggle("Coords")
    private val fps = elementToggle("Fps")
    private val ping = elementToggle("Ping")
    private val memory = elementToggle("Memory")
    private val speedometer = elementToggle("Speedometer")
    private val tps = elementToggle("TPS")
    private val totems = elementToggle("Totems")
    private val gapples = elementToggle("Gapples")
    private val pvpResources = elementToggle("PVPResources", "PVPResources")
    private val armorHud = elementToggle("ArmorHud", "ArmorHud")
    private val inventoryHud = elementToggle("InventoryHud", "InventoryHud")
    private val potionHud = elementToggle("PotionHud", "PotionHud")
    private val keyBinds = elementToggle("KeyBinds", "KeyBinds")
    private val targetHud = elementToggle("TargetHud", "TargetHud")
    private val radar = elementToggle("RadarRewrite", "Radar")
    private val staffBoard = elementToggle("StaffBoard", "StaffBoard")
    private val killStats = elementToggle("KillStats", "KillStats")
    private val chestCounter = elementToggle("ChestCounter", "ChestCounter")
    private val crosshair = elementToggle("Crosshair")
    private val crosshairArrows = elementToggle("CrosshairArrows", "CrosshairArrows")
    private val hotbar = elementToggle("Hotbar")
    private val legacyHud = elementToggle("LegacyHud", "LegacyHud")
    private val candleHud = elementToggle("CandleHud", "CandleHud")
    private val companion = elementToggle("Companion")
    private val killFeed = elementToggle("KillFeed", "KillFeed")
    private val particles = elementToggle("Particles")

    private var chatSelectedElement: HudElement? = null
    private var chatDraggingElement: HudElement? = null
    private var chatDragOffsetX = 0
    private var chatDragOffsetY = 0
    private var chatMouseX = 0
    private var chatMouseY = 0
    
    /**
     * Blur effect is completely disabled
     */
    const val isBlurEffectActive = false

    private fun elementToggle(elementName: String, settingName: String = elementName, default: Boolean = true): Value<Boolean> {
        return boolean(settingName, default).onChanged { enabled ->
            HudManager.findElement(elementName)?.setEnabledFromModule(enabled)
        }.also {
            elementToggles[elementName] = it
        }
    }

    fun setElementEnabled(elementName: String, enabled: Boolean) {
        elementToggles[elementName]?.set(enabled)
        HudManager.findElement(elementName)?.setEnabledFromModule(enabled)
    }

    fun syncElementSettings() {
        HudManager.elements.forEach { element ->
            val enabled = elementToggles[element.name]?.get() ?: element.enabled
            element.setEnabledFromModule(enabled)
        }
    }

    private fun HudElement.setEnabledFromModule(enabled: Boolean) {
        if (this.enabled != enabled) {
            this.enabled = enabled
        }
    }

    val renderHandler = handler<OverlayRenderEvent> { event ->
        // Don't render if the module is disabled or the debug screen is open
        if (!enabled || mc.currentScreen is ChatScreen || mc.inGameHud.debugHud.shouldShowDebugHud()) {
            return@handler
        }

        // Ensure elements are initialized
        HudManager.initialize()
        syncElementSettings()

        for (element in HudManager.elements) {
            element.renderPreview(event.context)
        }
    }

    @Suppress("unused")
    private val chatScreenRenderHandler = handler<ScreenRenderEvent> { event ->
        if (!running || mc.currentScreen !is ChatScreen || mc.inGameHud.debugHud.shouldShowDebugHud()) {
            return@handler
        }

        HudManager.initialize()
        syncElementSettings()
        if (chatSelectedElement?.enabled != true) {
            chatSelectedElement = null
        }

        for (element in HudManager.elements) {
            if (element.enabled) {
                element.render(event.context, element == chatSelectedElement)
            }
        }
    }

    @Suppress("unused")
    private val chatMouseCursorHandler = handler<MouseCursorEvent> { event ->
        updateChatMouse(event.x, event.y)

        val element = chatDraggingElement ?: return@handler
        if (!running || mc.currentScreen !is ChatScreen) {
            chatDraggingElement = null
            return@handler
        }

        element.x = (chatMouseX - chatDragOffsetX).coerceIn(0, max(0, mc.window.scaledWidth - element.width))
        element.y = (chatMouseY - chatDragOffsetY).coerceIn(0, max(0, mc.window.scaledHeight - element.height))
    }

    @Suppress("unused")
    private val chatMouseButtonHandler = handler<MouseButtonEvent> { event ->
        if (!running || (event.screen ?: mc.currentScreen) !is ChatScreen || event.button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return@handler
        }

        refreshChatMouse()

        when (event.action) {
            GLFW.GLFW_PRESS -> {
                val element = HudManager.elements
                    .asReversed()
                    .firstOrNull { it.enabled && it.isMouseOver(chatMouseX, chatMouseY) }

                chatSelectedElement = element
                chatDraggingElement = element
                if (element != null) {
                    chatDragOffsetX = chatMouseX - element.x
                    chatDragOffsetY = chatMouseY - element.y
                }
            }

            GLFW.GLFW_RELEASE -> {
                if (chatDraggingElement != null) {
                    HudConfig.saveConfig()
                }
                chatDraggingElement = null
            }
        }
    }

    private fun refreshChatMouse() {
        val x = DoubleArray(1)
        val y = DoubleArray(1)
        GLFW.glfwGetCursorPos(mc.window.handle, x, y)
        updateChatMouse(x[0], y[0])
    }

    private fun updateChatMouse(windowX: Double, windowY: Double) {
        val window = mc.window
        chatMouseX = (windowX * window.scaledWidth / window.width.toDouble()).roundToInt()
        chatMouseY = (windowY * window.scaledHeight / window.height.toDouble()).roundToInt()
    }
}
