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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.InventoryScreen

/**
 * ThunderHack InventoryAnimation port.
 */
object ModuleInventoryAnimation : ClientModule("InventoryAnimation", Category.RENDER) {

    private val type by enumChoice("Type", AnimationType.SCALE)
    private val speed by float("Speed", 10.0F, 1.0F..30.0F)
    private val inventoryOnly by boolean("InventoryOnly", false)
    private val dimBackground by boolean("DimBackground", false)

    fun shouldAnimate(screen: Screen): Boolean {
        return running && (!inventoryOnly || screen is InventoryScreen)
    }

    fun getAnimationType(): AnimationType {
        return type
    }

    fun getAnimationSpeed(): Float {
        return speed
    }

    fun shouldDimBackground(): Boolean {
        return dimBackground
    }

    enum class AnimationType(override val choiceName: String) : NamedChoice {
        SCALE("Scale"),
        BOUNCE("Bounce"),
        SLIDE_UP("SlideUp"),
        SLIDE_DOWN("SlideDown"),
        SLIDE_LEFT("SlideLeft"),
        SLIDE_RIGHT("SlideRight"),
        FLIP("Flip"),
        WARP("Warp"),
        GLITCH("Glitch")
    }
}
