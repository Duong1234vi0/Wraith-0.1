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

import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.*
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.arrow.Arrow
import net.minecraft.world.phys.AABB

/**
 * ItemESP module
 *
 * Allows you to see dropped items through walls.
 */

object ModuleItemESP : ClientModule("ItemESP", Category.RENDER) {

    override val baseKey: String
        get() = "liquidbounce.module.itemEsp"

    private val modes = choices("Mode", OutlineMode, arrayOf(GlowMode, OutlineMode, BoxMode))
    private val colorMode = choices("ColorMode", 0) {
        arrayOf(
            GenericStaticColorMode(it, Color4b(255, 179, 72, 255)),
            GenericRainbowColorMode(it)
        )
    }

    private object BoxMode : Choice("AABB") {

        override val parent: ChoiceConfigurable<Choice>
            get() = modes

        private val box = AABB(-0.125, 0.125, -0.125, 0.125, 0.375, 0.125)

        @Suppress("unused")
        val renderHandler = handler<WorldRenderEvent> { event ->
            val matrixStack = event.matrixStack

            val base = getColor()
            val baseColor = base.with(a = 50)
            val outlineColor = base.with(a = 100)

            val filtered = world.entitiesForRendering().filter(::shouldRender)

            renderEnvironmentForWorld(matrixStack) {
                BoxRenderer.drawWith(this) {
                    for (entity in filtered) {
                        val pos = entity.interpolateCurrentPosition(event.partialTicks)

                        withPositionRelativeToCamera(pos) {
                            drawBox(box, baseColor, outlineColor)
                        }
                    }
                }
            }
        }
    }

    object GlowMode : Choice("Glow") {
        override val parent: ChoiceConfigurable<Choice>
            get() = modes
    }

    object OutlineMode : Choice("Outline") {
        override val parent: ChoiceConfigurable<Choice>
            get() = modes
    }

    fun shouldRender(it: Entity?) = it is ItemEntity || it is Arrow

    fun getColor() = this.colorMode.activeChoice.getColor(null)
}
