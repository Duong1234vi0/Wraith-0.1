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

/**
 * Compatibility wrapper for current HUD/render code.
 *
 * Do not add a second palette or selected index here; [ClickGuiTheme] is the shared source of truth.
 */
object ClientTheme {
    val COLORS: Array<Pair<Int, Int>>
        get() = ClickGuiTheme.COLORS

    val NAMES: Array<String>
        get() = ClickGuiTheme.NAMES

    val DESCRIPTIONS: Array<String>
        get() = ClickGuiTheme.DESCRIPTIONS

    var selected: Int
        get() = ClickGuiTheme.selected
        set(value) {
            ClickGuiTheme.selected = value
        }

    val accentA: Int
        get() = ClickGuiTheme.accentA

    val accentB: Int
        get() = ClickGuiTheme.accentB
}
