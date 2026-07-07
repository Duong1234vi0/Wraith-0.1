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
package net.ccbluex.liquidbounce.features.module.modules.render.gui
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.client.mc
import java.io.File
import java.util.Properties

/**
 * Configuration system for lightweight GUI editor behavior.
 */
object GuiConfig {
    private val configFile = File(mc.gameDirectory, "config/liquidbounce_gui.properties")
    private val properties = Properties()
    
    // Default values
    private const val DEFAULT_GRID_SIZE = 20
    
    init {
        loadConfig()
    }
    
    private fun loadConfig() {
        try {
            if (configFile.exists()) {
                configFile.inputStream().use { properties.load(it) }
            } else {
                // Set defaults if config doesn't exist
                setDefaults()
                saveConfig()
            }
        } catch (e: Exception) {
            println("Error loading GUI config: ${e.message}")
            setDefaults()
        }
    }
    
    private fun setDefaults() {
        properties.setProperty("grid.size", DEFAULT_GRID_SIZE.toString())
    }
    
    fun saveConfig() {
        try {
            configFile.parentFile?.mkdirs()
            configFile.outputStream().use { 
                properties.store(it, "LiquidBounce GUI Configuration") 
            }
        } catch (e: Exception) {
            println("Error saving GUI config: ${e.message}")
        }
    }
    
    val gridSize: Int
        get() = properties.getProperty("grid.size", DEFAULT_GRID_SIZE.toString()).toIntOrNull() ?: DEFAULT_GRID_SIZE

    fun getGridSnappedPosition(x: Int, y: Int): Pair<Int, Int> {
        val snappedX = (x / gridSize) * gridSize
        val snappedY = (y / gridSize) * gridSize
        return Pair(snappedX, snappedY)
    }
    
    fun resetToDefaults() {
        setDefaults()
        saveConfig()
    }
}
