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

import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.*
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClickGuiScreen
import net.ccbluex.liquidbounce.utils.client.asText
import net.ccbluex.liquidbounce.utils.client.inGame
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.client.gui.screen.Screen
import net.minecraft.sound.SoundEvent
import org.lwjgl.glfw.GLFW
import net.minecraft.util.Identifier

/**
 * ClickGUI module
 *
 * Shows you an easy-to-use menu to toggle and configure modules.
 */

object ModuleClickGui :
    ClientModule("ClickGUI", Category.RENDER, bind = GLFW.GLFW_KEY_RIGHT_SHIFT, disableActivation = true) {

    override val running = true

    @Suppress("UnusedPrivateProperty")
    private val scale by float("Scale", 1f, 0.5f..2f).onChanged {
        EventManager.callEvent(ClickGuiScaleChangeEvent(it))
        EventManager.callEvent(ClickGuiValueChangeEvent(this))
    }

    @Suppress("UnusedPrivateProperty")
    private val cache by boolean("Cache", true).onChanged { cache ->
    }

    @Suppress("UnusedPrivateProperty")
    private val searchBarAutoFocus by boolean("SearchBarAutoFocus", true).onChanged {
        EventManager.callEvent(ClickGuiValueChangeEvent(this))
    }

    val isInSearchBar: Boolean
        get() = mc.currentScreen is ClickGuiScreen 
        // Note: Removed isTyping reference - native GUI handles this internally

    object Snapping : ToggleableConfigurable(this, "Snapping", true) {

        @Suppress("UnusedPrivateProperty")
        private val gridSize by int("GridSize", 10, 1..100, "px").onChanged {
            EventManager.callEvent(ClickGuiValueChangeEvent(ModuleClickGui))
        }

        init {
            inner.find { it.name == "Enabled" }?.onChanged {
                EventManager.callEvent(ClickGuiValueChangeEvent(ModuleClickGui))
            }
        }
    }

    init {
        tree(Snapping)
    }

    @Suppress("unused")
    private val moduleToggleSoundHandler = handler<ModuleToggleEvent> { event ->
        if (!inGame || event.moduleName == name) {
            return@handler
        }

        val sound = if (event.enabled) MODULE_ENABLE_SOUND else MODULE_DISABLE_SOUND
        val pitch = if (event.enabled) 1.06f else 0.94f
        val volume = if (event.enabled) 0.42f else 0.38f

        mc.soundManager.play(PositionedSoundInstance.master(SoundEvent.of(sound), pitch, volume))
    }

    override fun enable() {
        // Pretty sure we are not in a game, so we can't open the clickgui
        if (!inGame) {
            return
        }

        // Use native ClickGUI implementation instead of JCEF
        mc.setScreen(ClickGuiScreen())
        super.enable()
    }

    /**
     * An empty screen that acts as a hint when to draw the clickgui
     */
    class ClickScreen : Screen("ClickGUI".asText()) {

        override fun close() {
            mc.mouse.lockCursor()
            super.close()
        }

        override fun shouldPause(): Boolean {
            // preventing game pause
            return false
        }
    }

    private val MODULE_ENABLE_SOUND = Identifier.of("liquidbounce", "module_enable")
    private val MODULE_DISABLE_SOUND = Identifier.of("liquidbounce", "module_disable")

}
