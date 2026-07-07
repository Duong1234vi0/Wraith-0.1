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

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import net.ccbluex.liquidbounce.utils.client.mc
import java.io.File

/**
 * Shared ClickGUI/HUD theme palette and selected preset.
 *
 * Current HUD and render modules keep using [ClientTheme], which delegates here. This object is the
 * single source of truth so selecting a theme in the Style 4 ClickGUI immediately recolors HUD
 * elements, notifications, and render effects that read the shared accents.
 */
object ClickGuiTheme {

    val COLORS = arrayOf(
        0xFF35DBFF.toInt() to 0xFFB84DFF.toInt(), // Neon Cyan
        0xFF4FD6A0.toInt() to 0xFF3AA0E0.toInt(), // Mint Flux
        0xFFFFB266.toInt() to 0xFFFF6FA5.toInt(), // Sunset Pulse
        0xFFE07A7A.toInt() to 0xFFE0A36B.toInt(), // Coral Heat
        0xFFA78BFA.toInt() to 0xFF5EEAD4.toInt(), // Aurora
        0xFF7AA8E0.toInt() to 0xFFB47AD9.toInt(), // Twilight
        0xFF6FB6A8.toInt() to 0xFF4F7FB0.toInt(), // Ocean
        0xFFD98FB0.toInt() to 0xFF8F9FD9.toInt(), // Rose Quartz
        0xFFC2A36B.toInt() to 0xFF8FA86B.toInt(), // Sandstone
        0xFFFF2D95.toInt() to 0xFF00E5FF.toInt(), // Cyberpunk
        0xFFFF1E1E.toInt() to 0xFFFFC400.toInt(), // Inferno
        0xFF7CFF00.toInt() to 0xFF00FFB2.toInt(), // Toxic
        0xFF6A00FF.toInt() to 0xFFFF00C8.toInt(), // Galaxy
        0xFFFF4500.toInt() to 0xFFFFD000.toInt(), // Lava
        0xFF00FFA3.toInt() to 0xFF00B3FF.toInt(), // Emerald
        0xFFFF6AD5.toInt() to 0xFF26D9FF.toInt(), // Vaporwave
        0xFF8B0000.toInt() to 0xFFFF3030.toInt(), // Crimson
        0xFF2E5BFF.toInt() to 0xFFFF4FCB.toInt(), // Nebula
        0xFFF2F2F2.toInt() to 0xFFBFBFBF.toInt(), // White
        0xFF4A4A4A.toInt() to 0xFF1A1A1A.toInt()  // Black
    )

    val NAMES = arrayOf(
        "Neon Cyan",
        "Mint Flux",
        "Sunset Pulse",
        "Coral Heat",
        "Aurora",
        "Twilight",
        "Ocean",
        "Rose Quartz",
        "Sandstone",
        "Cyberpunk",
        "Inferno",
        "Toxic",
        "Galaxy",
        "Lava",
        "Emerald",
        "Vaporwave",
        "Crimson",
        "Nebula",
        "White",
        "Black"
    )

    val DESCRIPTIONS = arrayOf(
        "Cyan to violet dashboard accent",
        "Soft green to blue accent",
        "Warm gold to pink accent",
        "Muted red to orange accent",
        "Purple to teal soft accent",
        "Blue to purple dusk accent",
        "Teal to deep blue accent",
        "Pink to periwinkle accent",
        "Gold to olive earth accent",
        "Hot magenta to electric cyan",
        "Blazing red to molten amber",
        "Toxic lime to aqua glow",
        "Deep violet to magenta",
        "Molten orange to gold",
        "Emerald to bright sky",
        "Retro pink to cyan",
        "Dark to vivid crimson",
        "Cosmic blue to pink",
        "Clean light monochrome",
        "Sleek dark monochrome"
    )

    var selected: Int = ClickGuiThemeConfigManager.loadSelectedTheme().coerceIn(COLORS.indices)
        set(value) {
            val clamped = value.coerceIn(COLORS.indices)
            field = clamped
            ClickGuiThemeConfigManager.saveSelectedTheme(clamped)
        }

    val accentA: Int
        get() = COLORS[selected.coerceIn(COLORS.indices)].first

    val accentB: Int
        get() = COLORS[selected.coerceIn(COLORS.indices)].second
}

private data class ClickGuiThemeState(val selectedTheme: Int = 0)

private object ClickGuiThemeConfigManager {
    private val gson = Gson()
    private val configFile: File
        get() = File(mc.gameDirectory, "config/liquidbounce_theme.json")

    fun loadSelectedTheme(): Int {
        return try {
            if (!configFile.exists()) {
                0
            } else {
                gson.fromJson(configFile.readText(), ClickGuiThemeState::class.java)?.selectedTheme ?: 0
            }
        } catch (e: JsonSyntaxException) {
            println("Error parsing ClickGUI theme config: ${e.message}")
            0
        } catch (e: Exception) {
            println("Error loading ClickGUI theme config: ${e.message}")
            0
        }
    }

    fun saveSelectedTheme(selectedTheme: Int) {
        try {
            configFile.parentFile?.mkdirs()
            configFile.writeText(gson.toJson(ClickGuiThemeState(selectedTheme)))
        } catch (e: Exception) {
            println("Error saving ClickGUI theme config: ${e.message}")
        }
    }
}
