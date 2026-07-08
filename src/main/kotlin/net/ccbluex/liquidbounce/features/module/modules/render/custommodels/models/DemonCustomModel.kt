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
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.player.PlayerModel

class DemonCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val head = root.getChild(HEAD)
    private val leftArm = root.getChild(LEFT_ARM)
    private val rightArm = root.getChild(RIGHT_ARM)
    private val leftLeg = root.getChild(LEFT_LEG)
    private val rightLeg = root.getChild(RIGHT_LEG)

    private var headRotation = CopiedRotation()
    private var leftArmRotation = CopiedRotation()
    private var rightArmRotation = CopiedRotation()
    private var leftLegRotation = CopiedRotation()
    private var rightLegRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headRotation = captureRotation(playerModel.head)
        leftArmRotation = captureRotation(playerModel.leftArm, -0.2182f)
        rightArmRotation = captureRotation(playerModel.rightArm, 0.2182f)
        leftLegRotation = captureRotation(playerModel.leftLeg)
        rightLegRotation = captureRotation(playerModel.rightLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(head, headRotation)
        applyRotation(leftArm, leftArmRotation)
        applyRotation(rightArm, rightArmRotation)
        applyRotation(leftLeg, leftLegRotation)
        applyRotation(rightLeg, rightLegRotation)
    }

    companion object {
        private const val HEAD = "head"
        private const val LEFT_ARM = "leftArm"
        private const val RIGHT_ARM = "rightArm"
        private const val LEFT_LEG = "leftLeg"
        private const val RIGHT_LEG = "rightLeg"

        fun create(): DemonCustomModel {
            return DemonCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root

            val head = root.addOrReplaceChild(
                HEAD,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-4.0f, -4.0f, -3.0f, 8.0f, 8.0f, 8.0f, CubeDeformation(0.3f)),
                PartPose.offset(0.0f, -6.0f, -1.0f)
            )
            head.addOrReplaceChild(
                "leftHorn",
                CubeListBuilder.create()
                    .texOffs(32, 8)
                    .addBox(13.4346f, -5.2071f, 2.7071f, 6.0f, 2.0f, 2.0f, CubeDeformation(0.1f))
                    .texOffs(0, 0)
                    .addBox(17.4346f, -10.4071f, 2.7071f, 2.0f, 5.0f, 2.0f, CubeDeformation(0.1f)),
                PartPose.offsetAndRotation(-8.0f, 8.0f, 0.0f, -0.3927f, 0.3927f, -0.5236f)
            )
            head.addOrReplaceChild(
                "rightHorn",
                CubeListBuilder.create()
                    .texOffs(32, 8)
                    .addBox(-19.4346f, -5.2071f, 2.7071f, 6.0f, 2.0f, 2.0f, CubeDeformation(0.1f))
                    .texOffs(0, 0)
                    .addBox(-19.4346f, -10.4071f, 2.7071f, 2.0f, 5.0f, 2.0f, CubeDeformation(0.1f)),
                PartPose.offsetAndRotation(8.0f, 8.0f, 0.0f, -0.3927f, -0.3927f, 0.5236f)
            )

            val body = root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(0, 16)
                    .addBox(-4.5f, -1.7028f, 1.4696f, 8.0f, 12.0f, 4.0f),
                PartPose.offsetAndRotation(0.5f, -0.1f, -3.5f, 0.1745f, 0.0f, 0.0f)
            )
            body.addOrReplaceChild(
                "leftWing",
                CubeListBuilder.create()
                    .texOffs(40, 12)
                    .addBox(-7.0072f, -0.5972f, 0.7515f, 12.0f, 13.0f, 0.0f),
                PartPose.offsetAndRotation(8.25f, -2.0f, 10.0f, 0.0873f, -0.829f, 0.1745f)
            )
            body.addOrReplaceChild(
                "rightWing",
                CubeListBuilder.create()
                    .texOffs(40, 12)
                    .addBox(-4.9928f, -0.5972f, 0.7515f, 12.0f, 13.0f, 0.0f),
                PartPose.offsetAndRotation(-9.25f, -2.0f, 10.0f, 0.0873f, 0.829f, -0.1745f)
            )

            root.addOrReplaceChild(
                LEFT_ARM,
                CubeListBuilder.create()
                    .texOffs(24, 16)
                    .addBox(-1.1f, -1.05f, 0.0f, 4.0f, 14.0f, 4.0f),
                PartPose.offsetAndRotation(5.4f, -1.25f, -2.0f, 0.0f, 0.0f, -0.2182f)
            )
            root.addOrReplaceChild(
                RIGHT_ARM,
                CubeListBuilder.create()
                    .texOffs(24, 16)
                    .addBox(-2.9f, -1.05f, 0.0f, 4.0f, 14.0f, 4.0f),
                PartPose.offsetAndRotation(-5.4f, -1.25f, -2.0f, 0.0f, 0.0f, 0.2182f)
            )

            val leftLeg = root.addOrReplaceChild(
                LEFT_LEG,
                CubeListBuilder.create()
                    .texOffs(48, 22)
                    .addBox(-3.25f, -2.25f, -1.0f, 4.0f, 9.0f, 4.0f),
                PartPose.offset(3.0f, 10.0f, 0.0f)
            )
            val leftLeg1 = leftLeg.addOrReplaceChild(
                "leftLeg1",
                CubeListBuilder.create()
                    .texOffs(34, 34)
                    .addBox(0.95f, 4.6f, 8.0511f, 3.0f, 5.0f, 3.0f),
                PartPose.offsetAndRotation(-1.7f, -0.1f, -3.55f, -0.5236f, 0.0f, 0.0f)
            )
            leftLeg1.addOrReplaceChild(
                "bone2",
                CubeListBuilder.create()
                    .texOffs(26, 0)
                    .addBox(-0.7f, -1.15f, 9.3f, 4.0f, 2.0f, 4.0f)
                    .texOffs(40, 0)
                    .addBox(-0.7f, -1.15f, 7.3f, 4.0f, 2.0f, 2.0f),
                PartPose.offsetAndRotation(1.4f, 15.0f, 0.25f, 0.5236f, 0.0f, 0.0f)
            )
            val bone3 = leftLeg1.addOrReplaceChild(
                "bone3",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-1.0f, 0.0f, -2.0f, 0.0f, -0.0873f, -0.2618f)
            )
            bone3.addOrReplaceChild(
                "bone7",
                CubeListBuilder.create()
                    .texOffs(16, 34)
                    .addBox(-0.7911f, -10.1159f, 8.0029f, 4.0f, 4.0f, 5.0f)
                    .texOffs(0, 32)
                    .addBox(-0.7911f, -15.1159f, 4.0029f, 4.0f, 9.0f, 4.0f),
                PartPose.offset(1.9f, 12.0f, 0.25f)
            )

            val rightLeg = root.addOrReplaceChild(
                RIGHT_LEG,
                CubeListBuilder.create()
                    .texOffs(48, 22)
                    .addBox(-0.75f, -2.25f, -1.0f, 4.0f, 9.0f, 4.0f),
                PartPose.offset(-3.0f, 10.0f, 0.0f)
            )
            val rightLeg3 = rightLeg.addOrReplaceChild(
                "rightLeg3",
                CubeListBuilder.create()
                    .texOffs(34, 34)
                    .addBox(-3.95f, 4.6f, 8.0511f, 3.0f, 5.0f, 3.0f),
                PartPose.offsetAndRotation(1.7f, -0.1f, -3.55f, -0.5236f, 0.0f, 0.0f)
            )
            rightLeg3.addOrReplaceChild(
                "bone4",
                CubeListBuilder.create()
                    .texOffs(26, 0)
                    .addBox(-3.3f, -1.15f, 9.3f, 4.0f, 2.0f, 4.0f)
                    .texOffs(40, 0)
                    .addBox(-3.3f, -1.15f, 7.3f, 4.0f, 2.0f, 2.0f),
                PartPose.offsetAndRotation(-1.4f, 15.0f, 0.25f, 0.5236f, 0.0f, 0.0f)
            )
            val bone5 = rightLeg3.addOrReplaceChild(
                "bone5",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(1.0f, 0.0f, -2.0f, 0.0f, 0.0873f, 0.2618f)
            )
            bone5.addOrReplaceChild(
                "bone6",
                CubeListBuilder.create()
                    .texOffs(16, 34)
                    .addBox(-3.2089f, -10.1159f, 8.0029f, 4.0f, 4.0f, 5.0f)
                    .texOffs(0, 32)
                    .addBox(-3.2089f, -15.1159f, 4.0029f, 4.0f, 9.0f, 4.0f),
                PartPose.offset(-1.9f, 12.0f, 0.25f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
