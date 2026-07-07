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
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.ModuleToggleEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.MouseCursorEvent
import net.ccbluex.liquidbounce.event.events.NotificationEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.SpaceSeperatedNamesChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.HideAppearance.isDestructed
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudConfig
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudDensity
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudElement
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudManager
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudMaterial
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudMaterialPreset
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudMaterials
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudSurfaceRenderer
import net.ccbluex.liquidbounce.utils.client.inGame
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.screens.ChatScreen
import org.lwjgl.glfw.GLFW
import kotlin.math.max
import kotlin.math.roundToInt

enum class WyvernHudMode(override val choiceName: String) : NamedChoice {
    HUD1("Hud1"),
    HUD2("Hud2")
}

enum class WyvernHudElement {
    WATERMARK,
    ARRAY_LIST,
    POTIONS,
    STAFF,
    NOTIFICATIONS,
    INFORMATION,
    KEYBINDS,
    TARGET_HUD,
    INVENTORY,
    HOTBAR
}

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

    val wyvernHudMode by enumChoice("HudMode", WyvernHudMode.HUD1)
    val hudMaterial by enumChoice("Material", HudMaterialPreset.MODERN)
    val hudDensity by enumChoice("Density", HudDensity.NORMAL)
    val backgroundAlpha by float("BackgroundAlpha", 1f, 0.55f..1f)
    val panelRadius by float("PanelRadius", 5f, 3f..9f)
    val shadow by boolean("Shadow", true)
    val shadowStrength by float("ShadowStrength", 0.16f, 0f..1f)
    private val themeEdge by boolean("Outline", true)
    private val edgeGlow by boolean("EdgeGlow", true)
    private val edgeStrength by float("OutlineAlpha", 0.45f, 0f..1f)
    val hudScale by float("HUDScale", 1f, 0.7f..1.2f)

    private val watermark by boolean("Watermark", true)
    private val potions by boolean("Potions", true)
    private val notifications by boolean("Notifications", true)
    private val keybinds by boolean("Keybinds", true)
    private val targetHud by boolean("TargetHUD", true)
    private val inventory by boolean("Inventory", false)

    private var chatSelectedElement: HudElement? = null
    private var chatDraggingElement: HudElement? = null
    private var chatDragOffsetX = 0
    private var chatDragOffsetY = 0
    private var chatMouseX = 0
    private var chatMouseY = 0
    
    val currentMaterial: HudMaterial
        get() = HudMaterials.preset(hudMaterial).copy(
            radius = panelRadius,
        )

    val densityFactor: Float
        get() = when (hudDensity) {
            HudDensity.COMPACT -> 0.88f
            HudDensity.NORMAL -> 1f
        }

    val shadowEnabled: Boolean
        get() = shadow

    val themeEdgeEnabled: Boolean
        get() = themeEdge

    val edgeGlowEnabled: Boolean
        get() = edgeGlow

    val themeEdgeStrength: Float
        get() = edgeStrength.coerceIn(0f, 1f)

    val isBlurEffectActive: Boolean
        get() = false

    fun setElementEnabled(elementName: String, enabled: Boolean) {
        HudManager.findElement(elementName)?.enabled = enabled
    }

    fun syncElementSettings() {
        // Element visibility is read directly from the HUD module values.
    }

    fun isWyvernElementVisible(element: WyvernHudElement): Boolean {
        return when (element) {
            WyvernHudElement.WATERMARK -> watermark
            WyvernHudElement.ARRAY_LIST -> false
            WyvernHudElement.POTIONS -> potions
            WyvernHudElement.STAFF -> false
            WyvernHudElement.NOTIFICATIONS -> notifications
            WyvernHudElement.INFORMATION -> false
            WyvernHudElement.KEYBINDS -> keybinds
            WyvernHudElement.TARGET_HUD -> targetHud
            WyvernHudElement.INVENTORY -> inventory
            WyvernHudElement.HOTBAR -> false
        }
    }

    val renderHandler = handler<OverlayRenderEvent> { event ->
        // Don't render if the module is disabled or the debug screen is open.
        if (!enabled || mc.debugOverlay.showDebugScreen()) {
            return@handler
        }

        // Ensure elements are initialized
        HudManager.initialize()
        syncElementSettings()
        HudSurfaceRenderer.clearPendingEdges()

        // #Edit By Claude: the editor path used to live on ScreenRenderEvent, which
        // is never fired by any mixin, so the HUD vanished while chat was open. The
        // OverlayRenderEvent (Gui.extractRenderState) DOES fire during chat, so we
        // render here in both cases and only add the selection highlight while the
        // chat editor is open.
        val inChatEditor = running && mc.screen is ChatScreen
        if (inChatEditor) {
            if (chatSelectedElement?.isVisibleInHud() != true) {
                chatSelectedElement = null
            }
            for (element in HudManager.elements) {
                if (element.isVisibleInHud()) {
                    element.renderScaled(event.context, element == chatSelectedElement)
                }
            }
        } else {
            for (element in HudManager.elements) {
                element.renderPreviewScaled(event.context)
            }
        }
        HudSurfaceRenderer.flushThemeEdges(event.context)
    }

    @Suppress("unused")
    private val moduleToggleHandler = handler<ModuleToggleEvent> { event ->
        if (!event.hidden) {
            HudManager.pushModuleNotification(event.moduleName, event.enabled)
        }
    }

    @Suppress("unused")
    private val notificationHandler = handler<NotificationEvent> { event ->
        if (event.severity != NotificationEvent.Severity.ENABLED &&
            event.severity != NotificationEvent.Severity.DISABLED
        ) {
            HudManager.pushTextNotification(event.title, event.message, event.severity)
        }
    }

    @Suppress("unused")
    private val chatMouseCursorHandler = handler<MouseCursorEvent> { event ->
        updateChatMouse(event.x, event.y)

        val element = chatDraggingElement ?: return@handler
        if (!running || mc.screen !is ChatScreen) {
            chatDraggingElement = null
            return@handler
        }

        val scale = hudScale.coerceIn(0.7f, 1.2f)
        element.x = (chatMouseX - chatDragOffsetX * scale)
            .roundToInt()
            .coerceIn(0, max(0, (mc.window.scaledWidth - element.width * scale).roundToInt()))
        element.y = (chatMouseY - chatDragOffsetY * scale)
            .roundToInt()
            .coerceIn(0, max(0, (mc.window.scaledHeight - element.height * scale).roundToInt()))
    }

    @Suppress("unused")
    private val chatMouseButtonHandler = handler<MouseButtonEvent> { event ->
        if (!running || (event.screen ?: mc.screen) !is ChatScreen || event.button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return@handler
        }

        refreshChatMouse()

        when (event.action) {
            GLFW.GLFW_PRESS -> {
                val element = HudManager.elements
                    .asReversed()
                    .firstOrNull { it.isVisibleInHud() && it.isMouseOver(chatMouseX, chatMouseY) }

                chatSelectedElement = element
                chatDraggingElement = element
                if (element != null) {
                    val scale = hudScale.coerceIn(0.7f, 1.2f)
                    chatDragOffsetX = ((chatMouseX - element.x) / scale).roundToInt()
                    chatDragOffsetY = ((chatMouseY - element.y) / scale).roundToInt()
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
        GLFW.glfwGetCursorPos(mc.window.handle(), x, y)
        updateChatMouse(x[0], y[0])
    }

    private fun updateChatMouse(windowX: Double, windowY: Double) {
        val window = mc.window
        chatMouseX = (windowX * window.scaledWidth / window.width.toDouble()).roundToInt()
        chatMouseY = (windowY * window.scaledHeight / window.height.toDouble()).roundToInt()
    }
}
