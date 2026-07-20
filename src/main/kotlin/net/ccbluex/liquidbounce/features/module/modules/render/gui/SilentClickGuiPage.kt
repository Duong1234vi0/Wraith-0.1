/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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

import net.ccbluex.liquidbounce.features.module.Category

enum class SilentClickGuiPage(
    val title: String,
    val id: String,
    val category: Category?
) {
    COMBAT("Combat", "combat", Category.COMBAT),
    MOVEMENT("Movement", "movement", Category.MOVEMENT),
    PLAYER("Player", "player", Category.PLAYER),
    RENDER("Render", "render", Category.RENDER),
    MISC("Misc", "misc", Category.MISC),
    CONFIG("Config", "config", null),
    THEME("Theme", "theme", null),
    CLOUD("Cloud", "cloud", null);

    companion object {
        val ORDER: List<SilentClickGuiPage> = listOf(
            COMBAT,
            MOVEMENT,
            PLAYER,
            RENDER,
            MISC,
            CONFIG,
            THEME,
            CLOUD
        )
    }
}
