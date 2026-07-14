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
package net.ccbluex.liquidbounce.features.module.modules.render.hats

import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.geom.builders.PartDefinition
import kotlin.math.PI

/**
 * Pouch/OptiFine parsed SantaHat model.
 *
 * The JSON values are not copied raw. OptiFine's `invertAxis = "xy"` parser first negates X/Y translations,
 * negates X/Y rotations after degree-to-radian conversion, and rewrites box origins with
 * `origin = -origin - size` for each inverted axis. ModelPart keeps the same pixel units and divides by 16
 * during rendering, so these converted values can be used directly in PartPose/CubeListBuilder.
 */
@Suppress("MagicNumber")
object SantaHatModelLayer {

    fun createRoot(): ModelPart {
        val mesh = MeshDefinition()
        val root = mesh.root

        val santaHat = root.addOrReplaceChild(
            ROOT,
            CubeListBuilder.create(),
            PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, degrees(90.0f), 0.0f)
        )

        santaHat.addSubModel(
            name = "santa_hat_top2",
            translateX = -0.53024f,
            translateY = -10.61642f,
            translateZ = 0.0f,
            rotateZ = -60.0f,
            boxX = -2.5f,
            boxY = -5.0f,
            boxZ = -1.5f,
            width = 3.0f,
            height = 3.0f,
            depth = 3.0f,
            textureU = 0,
            textureV = 0
        )
        santaHat.addSubModel(
            name = "santa_hat_top1",
            translateX = -1.03024f,
            translateY = -9.75039f,
            translateZ = 0.0f,
            rotateZ = -50.0f,
            boxX = -3.0f,
            boxY = -3.0f,
            boxZ = -3.0f,
            width = 6.0f,
            height = 4.0f,
            depth = 6.0f,
            textureU = 0,
            textureV = 0
        )
        santaHat.addSubModel(
            name = "santa_hat_top0",
            translateX = -0.2892f,
            translateY = -8.72718f,
            translateZ = 0.0f,
            rotateZ = -20.0f,
            boxX = -5.0f,
            boxY = -2.0f,
            boxZ = -4.0f,
            width = 9.0f,
            height = 3.0f,
            depth = 8.0f,
            textureU = 0,
            textureV = 0
        )
        santaHat.addSubModel(
            name = "santa_hat_top3",
            translateX = -5.78024f,
            translateY = -12.11642f,
            translateZ = 0.0f,
            rotateZ = 15.0f,
            boxX = -3.09808f,
            boxY = -2.16987f,
            boxZ = -2.0f,
            width = 4.0f,
            height = 4.0f,
            depth = 4.0f,
            textureU = 0,
            textureV = 16
        )
        santaHat.addSubModel(
            name = "santa_hat_base",
            translateX = -0.2892f,
            translateY = -8.72718f,
            translateZ = 0.0f,
            rotateZ = -15.0f,
            boxX = -5.5f,
            boxY = -1.0f,
            boxZ = -4.5f,
            width = 10.0f,
            height = 4.0f,
            depth = 9.0f,
            textureU = 0,
            textureV = 16,
            inflate = 0.1f
        )

        return LayerDefinition.create(mesh, TEXTURE_WIDTH, TEXTURE_HEIGHT).bakeRoot().getChild(ROOT)
    }

    private fun PartDefinition.addSubModel(
        name: String,
        translateX: Float,
        translateY: Float,
        translateZ: Float,
        rotateZ: Float,
        boxX: Float,
        boxY: Float,
        boxZ: Float,
        width: Float,
        height: Float,
        depth: Float,
        textureU: Int,
        textureV: Int,
        inflate: Float = 0.0f
    ) {
        addOrReplaceChild(
            name,
            CubeListBuilder.create()
                .texOffs(textureU, textureV)
                .addBox(boxX, boxY, boxZ, width, height, depth, CubeDeformation(inflate)),
            PartPose.offsetAndRotation(
                translateX,
                translateY,
                translateZ,
                0.0f,
                0.0f,
                degrees(rotateZ)
            )
        )
    }

    private fun degrees(value: Float): Float = (value * PI.toFloat()) / 180.0f

    private const val ROOT = "santa_hat"
    private const val TEXTURE_WIDTH = 64
    private const val TEXTURE_HEIGHT = 32
}
