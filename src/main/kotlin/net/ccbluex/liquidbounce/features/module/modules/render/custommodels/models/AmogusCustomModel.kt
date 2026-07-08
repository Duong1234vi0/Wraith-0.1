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
package net.ccbluex.liquidbounce.features.module.modules.render.custommodels.models

import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomPlayerModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.player.PlayerModel

class AmogusCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val leftLeg = root.getChild(LEFT_LEG)
    private val rightLeg = root.getChild(RIGHT_LEG)

    private var leftLegRotation = CopiedRotation()
    private var rightLegRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        leftLegRotation = captureRotation(playerModel.leftLeg)
        rightLegRotation = captureRotation(playerModel.rightLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(leftLeg, leftLegRotation)
        applyRotation(rightLeg, rightLegRotation)
    }

    companion object {
        private const val LEFT_LEG = "leftLeg"
        private const val RIGHT_LEG = "rightLeg"

        fun create(): AmogusCustomModel {
            return AmogusCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(34, 8)
                    .addBox(-4.0f, 6.0f, -3.0f, 8.0f, 12.0f, 6.0f)
                    .texOffs(15, 10)
                    .addBox(-3.0f, 9.0f, 3.0f, 6.0f, 8.0f, 3.0f)
                    .texOffs(26, 0)
                    .addBox(-3.0f, 5.0f, -3.0f, 6.0f, 1.0f, 6.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                "eye",
                CubeListBuilder.create()
                    .texOffs(0, 10)
                    .addBox(-3.0f, 7.0f, -4.0f, 6.0f, 4.0f, 1.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_LEG,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(2.9f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f),
                PartPose.offset(-2.0f, 18.0f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_LEG,
                CubeListBuilder.create()
                    .texOffs(13, 0)
                    .addBox(-5.9f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f),
                PartPose.offset(2.0f, 18.0f, 0.0f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
