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

class SonicCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val sonicHead = root.getChild(SONIC_HEAD)
    private val rightLeg = root.getChild(RIGHT_LEG)
    private val leftLeg = root.getChild(LEFT_LEG)
    private val leftArm = root.getChild(LEFT_ARM)
    private val rightArm = root.getChild(RIGHT_ARM)

    private var headRotation = CopiedRotation()
    private var rightLegRotation = CopiedRotation()
    private var leftLegRotation = CopiedRotation()
    private var leftArmRotation = CopiedRotation()
    private var rightArmRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headRotation = captureRotation(playerModel.head)
        leftArmRotation = captureRotation(playerModel.leftArm, -0.0873f)
        rightArmRotation = captureRotation(playerModel.rightArm, 0.0873f)
        rightLegRotation = captureRotation(playerModel.rightLeg)
        leftLegRotation = captureRotation(playerModel.leftLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(sonicHead, headRotation)
        applyRotation(leftArm, leftArmRotation)
        applyRotation(rightArm, rightArmRotation)
        applyRotation(rightLeg, rightLegRotation)
        applyRotation(leftLeg, leftLegRotation)
    }

    companion object {
        private const val SONIC_HEAD = "sonicHead"
        private const val RIGHT_LEG = "rleg"
        private const val LEFT_LEG = "lleg"
        private const val LEFT_ARM = "larm"
        private const val RIGHT_ARM = "rarm"

        fun create(): SonicCustomModel {
            return SonicCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            root.addOrReplaceChild(
                "bbMain",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.0f, -17.0f, -2.0f, 5.0f, 7.0f, 5.0f)
                    .texOffs(0, 22)
                    .addBox(-3.0f, -16.0f, -3.0f, 5.0f, 5.0f, 1.0f)
                    .texOffs(0, 0)
                    .addBox(-3.0f, -16.0f, 3.0f, 5.0f, 5.0f, 1.0f)
                    .texOffs(0, 0)
                    .addBox(0.0f, -15.0f, 3.0f, 1.0f, 1.0f, 3.0f)
                    .texOffs(0, 0)
                    .addBox(-3.0f, -14.0f, 4.0f, 1.0f, 1.0f, 2.0f)
                    .texOffs(0, 0)
                    .addBox(-1.0f, -11.0f, 3.0f, 1.0f, 1.0f, 2.0f)
                    .texOffs(0, 0)
                    .addBox(0.0f, -14.0f, 6.0f, 1.0f, 1.0f, 1.0f)
                    .texOffs(0, 0)
                    .addBox(-3.0f, -13.0f, 6.0f, 1.0f, 1.0f, 1.0f)
                    .texOffs(0, 0)
                    .addBox(-1.0f, -12.0f, 5.0f, 1.0f, 1.0f, 1.0f)
                    .texOffs(0, 0)
                    .addBox(-4.0f, -16.0f, -2.0f, 1.0f, 5.0f, 5.0f)
                    .texOffs(0, 0)
                    .addBox(2.0f, -16.0f, -2.0f, 1.0f, 5.0f, 5.0f),
                PartPose.offset(0.0f, 24.0f, 0.0f)
            )
            val sonicHead = root.addOrReplaceChild(
                SONIC_HEAD,
                CubeListBuilder.create()
                    .texOffs(36, 3)
                    .addBox(-3.5f, -7.0f, -3.0f, 7.0f, 7.0f, 7.0f)
                    .texOffs(60, 0)
                    .addBox(-0.5f, -3.0f, -4.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(-0.5f, 7.0f, 0.0f)
            )
            sonicHead.addOrReplaceChild(
                "spike1",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.5f, -4.0f, 6.0f, 3.0f, 3.0f, 3.0f)
                    .texOffs(0, 0)
                    .addBox(-3.5f, -5.0f, 4.0f, 3.0f, 3.0f, 2.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            sonicHead.addOrReplaceChild(
                "spike2",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(0.5f, -6.0f, 6.0f, 3.0f, 3.0f, 3.0f)
                    .texOffs(0, 0)
                    .addBox(0.5f, -7.0f, 4.0f, 3.0f, 3.0f, 2.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            sonicHead.addOrReplaceChild(
                "spike3",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.5f, -9.0f, 3.0f, 3.0f, 3.0f, 2.0f)
                    .texOffs(0, 0)
                    .addBox(-2.5f, -10.0f, 5.0f, 3.0f, 3.0f, 3.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_LEG,
                CubeListBuilder.create()
                    .texOffs(53, 54)
                    .addBox(-1.5f, 7.0f, -1.0f, 2.0f, 1.0f, 2.0f)
                    .texOffs(0, 0)
                    .addBox(-1.0f, -1.0f, -0.5f, 1.0f, 8.0f, 1.0f)
                    .texOffs(52, 58)
                    .addBox(-1.5f, 8.0f, -3.0f, 2.0f, 2.0f, 4.0f)
                    .texOffs(52, 58)
                    .addBox(-2.0f, 9.0f, -2.0f, 0.0f, 1.0f, 1.0f),
                PartPose.offset(-3.0f, 14.0f, 0.5f)
            )
            root.addOrReplaceChild(
                LEFT_LEG,
                CubeListBuilder.create()
                    .texOffs(53, 54)
                    .addBox(-0.5f, 7.0f, -1.0f, 2.0f, 1.0f, 2.0f)
                    .texOffs(0, 0)
                    .addBox(0.0f, -1.0f, -0.5f, 1.0f, 8.0f, 1.0f)
                    .texOffs(52, 58)
                    .addBox(-0.5f, 8.0f, -3.0f, 2.0f, 2.0f, 4.0f)
                    .texOffs(52, 58)
                    .addBox(1.5f, 9.0f, -2.0f, 0.0f, 1.0f, 1.0f),
                PartPose.offset(2.0f, 14.0f, 0.5f)
            )
            root.addOrReplaceChild(
                LEFT_ARM,
                CubeListBuilder.create()
                    .texOffs(0, 60)
                    .addBox(-0.5f, 8.0f, -1.0f, 2.0f, 2.0f, 2.0f)
                    .texOffs(60, 17)
                    .addBox(0.0f, 0.0f, -0.5f, 1.0f, 8.0f, 1.0f),
                PartPose.offsetAndRotation(3.0f, 9.0f, 0.5f, 0.0f, 0.0f, -0.0873f)
            )
            root.addOrReplaceChild(
                RIGHT_ARM,
                CubeListBuilder.create()
                    .texOffs(60, 17)
                    .addBox(-1.0f, 0.0f, -0.5f, 1.0f, 8.0f, 1.0f)
                    .texOffs(0, 60)
                    .addBox(-1.5f, 8.0f, -1.0f, 2.0f, 2.0f, 2.0f),
                PartPose.offsetAndRotation(-4.0f, 9.0f, 0.5f, 0.0f, 0.0f, 0.0873f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
