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

import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.ModuleToggleEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.MouseCursorEvent
import net.ccbluex.liquidbounce.event.events.NotificationEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.SpaceSeperatedNamesChangeEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.HideAppearance.isDestructed
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudConfig
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudElement
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HudManager
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HotbarStatusHud
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.HotbarStatusHudPulseState
import net.ccbluex.liquidbounce.features.module.modules.render.gui.hud.SilentHudElementKind
import net.ccbluex.liquidbounce.utils.client.inGame
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.screens.ChatScreen
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

    val hudScale by float("HUDScale", 1f, 0.7f..1.2f)

    private val watermark by boolean("Watermark", true)
    private val notifications by boolean("Notifications", true)
    private val hotkeys by boolean("HotKeys", true)
    private val information by boolean("Information", true)
    private val targetHud by boolean("TargetHUD", true)
    private val staffList by boolean("StaffList", true)
    private val totemCounter by boolean("TotemCounter", true)
    private val armorDurability by boolean("ArmorDurability", true)

    private val hotbarStatusPulse = HotbarStatusHudPulseState()

    private var chatSelectedElement: HudElement? = null
    private var chatDraggingElement: HudElement? = null
    private var chatDragOffsetX = 0
    private var chatDragOffsetY = 0
    private var chatMouseX = 0
    private var chatMouseY = 0

    val isBlurEffectActive: Boolean
        get() = false

    fun setElementEnabled(elementName: String, enabled: Boolean) {
        HudManager.findElement(elementName)?.enabled = enabled
    }

    fun syncElementSettings() {
        // Element visibility is read directly from the HUD module values.
    }

    fun isSilentElementVisible(element: SilentHudElementKind): Boolean {
        return when (element) {
            SilentHudElementKind.WATERMARK -> watermark
            SilentHudElementKind.HOTKEYS -> hotkeys
            SilentHudElementKind.INFORMATION -> information
            SilentHudElementKind.NOTIFICATIONS -> notifications
            SilentHudElementKind.TARGET_HUD -> targetHud
            SilentHudElementKind.STAFF_LIST -> staffList
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
        HotbarStatusHud.render(
            event.context,
            mc.player,
            totemCounter,
            armorDurability,
            hotbarStatusPulse.ticks,
        )
    }

    @Suppress("unused")
    private val hotbarStatusTickHandler = handler<GameTickEvent> {
        hotbarStatusPulse.tick(mc.player)
    }

    @Suppress("unused")
    private val hotbarStatusTotemPopHandler = handler<TotemPopEvent> { event ->
        hotbarStatusPulse.onTotemPop(event.entity, mc.player)
    }

    @Suppress("unused")
    private val hotbarStatusWorldChangeHandler = handler<WorldChangeEvent> {
        hotbarStatusPulse.reset()
        HudManager.resetTransient()
    }

    @Suppress("unused")
    private val hotbarStatusDisconnectHandler = handler<DisconnectEvent> {
        hotbarStatusPulse.reset()
        HudManager.resetTransient()
    }

    override fun disable() {
        hotbarStatusPulse.reset()
        HudManager.resetTransient()
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
        val nextX = (chatMouseX - chatDragOffsetX * scale)
            .roundToInt()
            .coerceIn(0, max(0, (mc.window.scaledWidth - element.width * scale).roundToInt()))
        val nextY = (chatMouseY - chatDragOffsetY * scale)
            .roundToInt()
            .coerceIn(0, max(0, (mc.window.scaledHeight - element.height * scale).roundToInt()))
        if (element.allowDragX) {
            element.x = nextX
        }
        if (element.allowDragY) {
            element.y = nextY
        }
    }

    @Suppress("unused")
    private val chatMouseButtonHandler = handler<MouseButtonEvent> { event ->
        if (!running || (event.screen ?: mc.screen) !is ChatScreen || event.button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return@handler
        }

        refreshChatMouse()

        when (event.action) {
            GLFW.GLFW_PRESS -> {
                if (HudManager.elements.asReversed().any {
                        it.isVisibleInHud() && it.onChatClick(chatMouseX, chatMouseY, event.button)
                    }
                ) {
                    return@handler
                }

                val selected = HudManager.elements
                    .asReversed()
                    .firstOrNull { it.isVisibleInHud() && it.isMouseOver(chatMouseX, chatMouseY) }
                val element = selected?.takeIf { it.draggable }

                chatSelectedElement = selected
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
