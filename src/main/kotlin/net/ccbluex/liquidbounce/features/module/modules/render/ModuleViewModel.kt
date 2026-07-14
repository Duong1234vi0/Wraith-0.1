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
package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.vertex.PoseStack
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.world.entity.HumanoidArm

/**
 * Pouch Client 1.16.5 ViewModel port.
 */
object ModuleViewModel : ClientModule("ViewModel", Category.RENDER) {

    private val rightXValue = float("RightX", 0.0F, -2.0F..2.0F, 0.1F)
    private val rightYValue = float("RightY", 0.0F, -2.0F..2.0F, 0.1F)
    private val rightZValue = float("RightZ", 0.0F, -2.0F..2.0F, 0.1F)

    private val leftXValue = float("LeftX", 0.0F, -2.0F..2.0F, 0.1F)
    private val leftYValue = float("LeftY", 0.0F, -2.0F..2.0F, 0.1F)
    private val leftZValue = float("LeftZ", 0.0F, -2.0F..2.0F, 0.1F)

    @Suppress("unused")
    private val reset = action("Reset") {
        rightXValue.set(0.0F)
        rightYValue.set(0.0F)
        rightZValue.set(0.0F)
        leftXValue.set(0.0F)
        leftYValue.set(0.0F)
        leftZValue.set(0.0F)
    }

    @JvmStatic
    val rightX: Float
        get() = rightXValue.get()

    @JvmStatic
    val rightY: Float
        get() = rightYValue.get()

    @JvmStatic
    val rightZ: Float
        get() = rightZValue.get()

    @JvmStatic
    val leftX: Float
        get() = leftXValue.get()

    @JvmStatic
    val leftY: Float
        get() = leftYValue.get()

    @JvmStatic
    val leftZ: Float
        get() = leftZValue.get()

    @JvmStatic
    fun applyTransform(matrices: PoseStack, arm: HumanoidArm) {
        if (!running) {
            return
        }

        if (arm == HumanoidArm.RIGHT) {
            matrices.translate(rightX, rightY, rightZ)
        } else {
            matrices.translate(leftX, leftY, leftZ)
        }
    }
}
