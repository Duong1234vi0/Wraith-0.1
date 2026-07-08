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

class JeffKillerCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val head = root.getChild(HEAD)
    private val leftArm = root.getChild(LEFT_ARM)
    private val rightLeg = root.getChild(RIGHT_LEG)
    private val leftLeg = root.getChild(LEFT_LEG)

    private var headRotation = CopiedRotation()
    private var leftArmRotation = CopiedRotation()
    private var rightLegRotation = CopiedRotation()
    private var leftLegRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headRotation = captureRotation(playerModel.head)
        leftArmRotation = captureRotation(playerModel.leftArm)
        rightLegRotation = captureRotation(playerModel.rightLeg)
        leftLegRotation = captureRotation(playerModel.leftLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(head, headRotation)
        applyRotation(leftArm, leftArmRotation)
        applyRotation(rightLeg, rightLegRotation)
        applyRotation(leftLeg, leftLegRotation)
    }

    companion object {
        private const val HEAD = "head"
        private const val LEFT_ARM = "leftArm"
        private const val RIGHT_LEG = "rightLeg"
        private const val LEFT_LEG = "leftLeg"

        fun create(): JeffKillerCustomModel {
            return JeffKillerCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            root.addOrReplaceChild(
                HEAD,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-5.0f, -9.75f, -5.0f, 10.0f, 10.0f, 8.0f),
                PartPose.offset(0.0f, 1.0f, -3.0f)
            )
            root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(0, 18)
                    .addBox(-4.0f, -23.1486f, 0.5266f, 8.0f, 14.0f, 4.0f),
                PartPose.offsetAndRotation(0.0f, 24.0f, 0.0f, 0.2618f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                    .texOffs(36, 0)
                    .addBox(-7.0f, -4.5f, -23.25f, 3.0f, 12.0f, 3.0f)
                    .texOffs(16, 36)
                    .addBox(-6.0f, 5.75f, -25.25f, 1.0f, 2.0f, 5.0f)
                    .texOffs(31, 15)
                    .addBox(-6.0f, 5.75f, -30.25f, 1.0f, 2.0f, 5.0f)
                    .texOffs(0, 0)
                    .addBox(-6.0f, 8.75f, -28.25f, 1.0f, 1.0f, 3.0f)
                    .texOffs(24, 18)
                    .addBox(-6.0f, 7.75f, -29.25f, 1.0f, 1.0f, 5.0f),
                PartPose.offsetAndRotation(0.0f, 24.0f, 0.0f, -1.309f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_ARM,
                CubeListBuilder.create()
                    .texOffs(37, 37)
                    .addBox(0.0f, -1.75f, -1.5f, 3.0f, 12.0f, 3.0f),
                PartPose.offset(4.0f, 3.0f, -3.0f)
            )
            root.addOrReplaceChild(
                RIGHT_LEG,
                CubeListBuilder.create()
                    .texOffs(0, 36)
                    .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f),
                PartPose.offset(-2.0f, 14.0f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_LEG,
                CubeListBuilder.create()
                    .texOffs(24, 24)
                    .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 4.0f),
                PartPose.offset(2.0f, 14.0f, 0.0f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
