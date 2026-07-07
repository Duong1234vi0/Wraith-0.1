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
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud
import net.ccbluex.liquidbounce.features.module.modules.render.gui.GuiConfig
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component
import java.awt.Color

/**
 * Native HUD configuration screen that replaces the Svelte-based implementation
 */
@Suppress("TooManyFunctions")
class HudScreen : Screen(Component.literal("HUD Editor")) {
    
    private val hudElements: List<HudElement> get() = HudManager.elements
    private var selectedElement: HudElement? = null
    private var isDragging = false
    private var dragOffsetX = 0
    private var dragOffsetY = 0
    
    override fun init() {
        super.init()
        
        HudManager.initialize()
        ModuleHud.syncElementSettings()
    }
    
    override fun extractRenderState(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        // Semi-transparent background
        context.fill(0, 0, width, height, 0x80000000.toInt())
        
        // Render grid
        renderGrid(context)
        
        // Render all HUD elements
        for (element in hudElements) {
            element.renderScaled(context, element == selectedElement)
        }
        
        // Render instructions
        renderInstructions(context)
        
        super.extractRenderState(context, mouseX, mouseY, delta)
    }
    
    private fun renderGrid(context: DrawContext) {
        val gridSize = GuiConfig.gridSize
        val gridColor = 0x33FFFFFF
        
        // Vertical lines
        var x = 0
        while (x < width) {
            context.fill(x, 0, x + 1, height, gridColor)
            x += gridSize
        }
        
        // Horizontal lines
        var y = 0
        while (y < height) {
            context.fill(0, y, width, y + 1, gridColor)
            y += gridSize
        }
    }
    
    private fun renderInstructions(context: DrawContext) {
        val instructions = listOf(
            "Left-click and drag to move HUD elements",
            "Right-click elements to toggle enabled/disabled", 
            "Press ESC to save and exit HUD editor",
            "Elements snap to ${GuiConfig.gridSize}px grid for alignment"
        )
        
        // Background for instructions
        context.fill(5, 5, 300, 5 + instructions.size * 12 + 10, 0x80000000.toInt())
        context.drawBorder(5, 5, 295, instructions.size * 12 + 10, 0xFF444444.toInt())
        
        var yOffset = 10
        for (instruction in instructions) {
            context.drawText(textRenderer, instruction, 10, yOffset, 0xFFFFFF, false)
            yOffset += 12
        }
    }
    
    override fun mouseClicked(event: MouseButtonEvent, doubleClick: Boolean): Boolean =
        mouseClicked(event.x, event.y, event.button())

    fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        val intMouseX = mouseX.toInt()
        val intMouseY = mouseY.toInt()
        
        // Find clicked element
        selectedElement = null
        for (element in hudElements.reversed()) { // Check from top to bottom
            if (element.isMouseOver(intMouseX, intMouseY)) {
                selectedElement = element
                
                if (button == 0) { // Left click - start dragging
                    isDragging = true
                    val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
                    dragOffsetX = ((intMouseX - element.x) / scale).toInt()
                    dragOffsetY = ((intMouseY - element.y) / scale).toInt()
                } else if (button == 1) { // Right click - open settings
                    element.openSettings()
                    ModuleHud.setElementEnabled(element.name, element.enabled)
                }
                
                return true
            }
        }
        
        return false
    }
    
    override fun mouseDragged(event: MouseButtonEvent, dx: Double, dy: Double): Boolean =
        mouseDragged(event.x, event.y, event.button(), dx, dy)

    fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        if (isDragging && selectedElement != null) {
            val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
            val newX = (mouseX - dragOffsetX * scale).toInt()
            val newY = (mouseY - dragOffsetY * scale).toInt()
            
            // Snap to grid using config
            val snapped = GuiConfig.getGridSnappedPosition(newX, newY)
            selectedElement!!.x = snapped.first
            selectedElement!!.y = snapped.second
            
            // Keep within bounds
            selectedElement!!.x = selectedElement!!.x.coerceIn(
                0,
                (width - selectedElement!!.width * scale).toInt().coerceAtLeast(0)
            )
            selectedElement!!.y = selectedElement!!.y.coerceIn(
                0,
                (height - selectedElement!!.height * scale).toInt().coerceAtLeast(0)
            )
            
            return true
        }
        
        return false
    }
    
    override fun mouseReleased(event: MouseButtonEvent): Boolean =
        mouseReleased(event.x, event.y, event.button())

    fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        isDragging = false
        
        // Save positions (in real implementation would persist to config)
        saveElementPositions()
        
        return false
    }
    
    private fun saveElementPositions() {
        HudScreenHelper.saveElementPositions()
    }
    
    override fun isPauseScreen(): Boolean {
        return false
    }
    
    override fun onClose() {
        // Apply changes and return to game
        super.onClose()
    }
}

/**
 * Helper object for HUD screen operations
 */
object HudScreenHelper {
    fun saveElementPositions() {
        try {
            // Save all element positions to configuration
            HudConfig.saveConfig()
        } catch (e: Exception) {
            println("Error saving HUD element positions: ${e.message}")
        }
    }
}
