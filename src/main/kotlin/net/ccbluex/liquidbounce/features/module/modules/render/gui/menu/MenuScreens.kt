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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.menu
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.components.Button
import net.minecraft.network.chat.Component

/**
 * Alt Manager screen for managing alternative accounts
 */
class AltManagerScreen(private val parent: Screen) : Screen(Component.literal("Alt Manager")) {
    
    override fun init() {
        super.init()
        
        // Back button
        addDrawableChild(
            ButtonWidget.builder(Component.literal("Back")) { 
                mc.setScreen(parent)
            }.dimensions(10, height - 30, 60, 20).build()
        )
        
        // Add Account button
        addDrawableChild(
            ButtonWidget.builder(Component.literal("Add Account")) { 
                // TODO: Open add account dialog
            }.dimensions(width / 2 - 100, height / 2, 200, 20).build()
        )
    }
    
    override fun extractRenderState(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        context.fillGradient(0, 0, width, height, 0x80000000.toInt(), 0x80444444.toInt())
        
        // Title
        val title = "Alt Manager"
        val titleSize = 15f
        val titleWidth = ExosGuiRenderer.titleWidth(title, titleSize)
        ExosGuiRenderer.title(context.matrices, title, (width - titleWidth) * 0.5f, 20f, 0xFFFFFFFF.toInt(), titleSize)
        
        // Placeholder text
        val placeholder = "Alt Manager functionality will be implemented here"
        val placeholderSize = 8f
        val placeholderWidth = ExosGuiRenderer.bodyWidth(placeholder, placeholderSize)
        ExosGuiRenderer.body(
            context.matrices,
            placeholder,
            (width - placeholderWidth) * 0.5f,
            height / 2f - 40f,
            0xFFAAAAAA.toInt(),
            placeholderSize
        )
        
        super.extractRenderState(context, mouseX, mouseY, delta)
    }
}

/**
 * LiquidBounce settings screen
 */
class LiquidBounceSettingsScreen(private val parent: Screen) : Screen(Component.literal("LiquidBounce Settings")) {
    
    override fun init() {
        super.init()
        
        // Back button
        addDrawableChild(
            ButtonWidget.builder(Component.literal("Back")) { 
                mc.setScreen(parent)
            }.dimensions(10, height - 30, 60, 20).build()
        )
    }
    
    override fun extractRenderState(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        context.fillGradient(0, 0, width, height, 0x80000000.toInt(), 0x80444444.toInt())
        
        // Title
        val title = "LiquidBounce Settings"
        val titleSize = 15f
        val titleWidth = ExosGuiRenderer.titleWidth(title, titleSize)
        ExosGuiRenderer.title(context.matrices, title, (width - titleWidth) * 0.5f, 20f, 0xFFFFFFFF.toInt(), titleSize)
        
        // Settings categories
        val categories = listOf(
            "General Settings",
            "GUI Settings", 
            "Performance Settings",
            "Theme Settings"
        )
        
        var yOffset = 60
        for (category in categories) {
            ExosGuiRenderer.body(context.matrices, "• $category", 50f, yOffset.toFloat(), 0xFFCCCCCC.toInt(), 8f)
            yOffset += 25
        }
        
        // Placeholder text
        val placeholder = "Settings implementation coming soon"
        val placeholderSize = 8f
        val placeholderWidth = ExosGuiRenderer.bodyWidth(placeholder, placeholderSize)
        ExosGuiRenderer.body(
            context.matrices,
            placeholder,
            (width - placeholderWidth) * 0.5f,
            height - 100f,
            0xFFAAAAAA.toInt(),
            placeholderSize
        )
        
        super.extractRenderState(context, mouseX, mouseY, delta)
    }
}
