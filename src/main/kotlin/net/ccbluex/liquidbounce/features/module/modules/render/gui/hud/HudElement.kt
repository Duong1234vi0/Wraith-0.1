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

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud
import net.minecraft.client.gui.DrawContext

/**
 * Base class for HUD elements.
 *
 * Native persistence and editor integration remain Wraith-owned; concrete renderers map Silent's
 * visual shell onto this contract.
 */
abstract class HudElement(
    val name: String,
    x: Int,
    y: Int,
    var width: Int,
    var height: Int
) {
    var x: Int = x
        set(value) {
            field = value
            savePosition()
        }

    var y: Int = y
        set(value) {
            field = value
            savePosition()
        }

    var enabled: Boolean = true
        set(value) {
            field = value
            HudConfig.setElementEnabled(name, value)
        }

    private var suppressPositionSave = false

    init {
        loadFromConfig()
    }

    private fun loadFromConfig() {
        try {
            HudConfig.getElementPosition(name)?.let { saved ->
                fieldSetPosition(saved.first, saved.second)
            }
            enabled = HudConfig.getElementEnabled(name)
        } catch (e: Exception) {
            println("Error loading config for HUD element $name: ${e.message}")
        }
    }

    private fun fieldSetPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
    }

    private fun savePosition() {
        if (suppressPositionSave) {
            return
        }

        try {
            HudConfig.setElementPosition(name, x, y)
        } catch (e: Exception) {
            println("Error saving position for HUD element $name: ${e.message}")
        }
    }

    protected fun setRuntimePosition(newX: Int, newY: Int) {
        suppressPositionSave = true
        try {
            x = newX
            y = newY
        } finally {
            suppressPositionSave = false
        }
    }

    abstract fun render(context: DrawContext, isSelected: Boolean)

    open val draggable: Boolean
        get() = true

    open val allowDragX: Boolean
        get() = true

    open val allowDragY: Boolean
        get() = true

    open fun onChatClick(mouseX: Int, mouseY: Int, button: Int): Boolean = false

    open fun resetState() = Unit

    fun renderScaled(context: DrawContext, isSelected: Boolean) {
        val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
        if (scale == 1f) {
            render(context, isSelected)
            return
        }

        context.pose().pushMatrix()
        try {
            context.pose().translate(x.toFloat(), y.toFloat())
            context.pose().scale(scale, scale)
            context.pose().translate(-x.toFloat(), -y.toFloat())
            render(context, isSelected)
        } finally {
            context.pose().popMatrix()
        }
    }

    open fun isVisibleInHud(): Boolean = enabled

    open fun renderPreview(context: DrawContext) {
        if (isVisibleInHud()) {
            render(context, false)
        }
    }

    fun renderPreviewScaled(context: DrawContext) {
        if (isVisibleInHud()) {
            renderScaled(context, false)
        }
    }

    fun isMouseOver(mouseX: Int, mouseY: Int): Boolean {
        val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
        return mouseX >= x &&
            mouseX <= x + width * scale &&
            mouseY >= y &&
            mouseY <= y + height * scale
    }

    open fun openSettings() {
        enabled = !enabled
    }

    protected fun renderBackground(context: DrawContext, isSelected: Boolean) {
        if (isSelected) {
            context.fill(x, y, x + width, y + height, 0x80222222.toInt())
            context.fill(x, y, x + width, y + 1, 0xFFFFFFFF.toInt())
            context.fill(x, y + height - 1, x + width, y + height, 0xFFFFFFFF.toInt())
            context.fill(x, y, x + 1, y + height, 0xFFFFFFFF.toInt())
            context.fill(x + width - 1, y, x + width, y + height, 0xFFFFFFFF.toInt())
        }
    }
}
