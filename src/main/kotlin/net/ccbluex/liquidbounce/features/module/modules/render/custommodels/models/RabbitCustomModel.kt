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

class RabbitCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val bone = root.getChild(BONE)
    private val rabbitHead = bone.getChild(RABBIT_HEAD)
    private val rabbitRightArm = bone.getChild(RABBIT_RIGHT_ARM)
    private val rabbitLeftArm = bone.getChild(RABBIT_LEFT_ARM)
    private val rabbitRightLeg = bone.getChild(RABBIT_RIGHT_LEG)
    private val rabbitLeftLeg = bone.getChild(RABBIT_LEFT_LEG)

    private var headRotation = CopiedRotation()
    private var rightArmRotation = CopiedRotation()
    private var leftArmRotation = CopiedRotation()
    private var rightLegRotation = CopiedRotation()
    private var leftLegRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headRotation = captureRotation(playerModel.head)
        rightArmRotation = captureRotation(playerModel.rightArm)
        leftArmRotation = captureRotation(playerModel.leftArm)
        rightLegRotation = captureRotation(playerModel.rightLeg)
        leftLegRotation = captureRotation(playerModel.leftLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(rabbitHead, headRotation)
        applyRotation(rabbitRightArm, rightArmRotation)
        applyRotation(rabbitLeftArm, leftArmRotation)
        applyRotation(rabbitRightLeg, rightLegRotation)
        applyRotation(rabbitLeftLeg, leftLegRotation)
    }

    companion object {
        private const val BONE = "bone"
        private const val RABBIT_HEAD = "rabbitHead"
        private const val RABBIT_RIGHT_ARM = "rabbitRarm"
        private const val RABBIT_LEFT_ARM = "rabbitLarm"
        private const val RABBIT_RIGHT_LEG = "rabbitRleg"
        private const val RABBIT_LEFT_LEG = "rabbitLleg"

        fun create(): RabbitCustomModel {
            return RabbitCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            val bone = root.addOrReplaceChild(
                BONE,
                CubeListBuilder.create()
                    .texOffs(28, 45)
                    .addBox(-5.0f, -13.0f, -5.0f, 10.0f, 11.0f, 8.0f),
                PartPose.offset(0.0f, 24.0f, 0.0f)
            )

            bone.addOrReplaceChild(
                RABBIT_RIGHT_LEG,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f),
                PartPose.offset(-3.0f, -2.0f, -1.0f)
            )
            bone.addOrReplaceChild(
                RABBIT_LEFT_LEG,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f),
                PartPose.offset(3.0f, -2.0f, -1.0f)
            )
            bone.addOrReplaceChild(
                RABBIT_RIGHT_ARM,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                PartPose.offsetAndRotation(-5.0f, -13.0f, -1.0f, 0.0f, 0.0f, 0.0873f)
            )
            bone.addOrReplaceChild(
                RABBIT_LEFT_ARM,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(0.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                PartPose.offsetAndRotation(5.0f, -13.0f, -1.0f, 0.0f, 0.0f, -0.0873f)
            )
            bone.addOrReplaceChild(
                RABBIT_HEAD,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.0f, 0.0f, -4.0f, 6.0f, 1.0f, 6.0f)
                    .texOffs(56, 0)
                    .addBox(-5.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                    .texOffs(56, 0)
                    .addBox(3.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                    .texOffs(0, 45)
                    .addBox(-4.0f, -11.0f, -4.0f, 8.0f, 11.0f, 8.0f)
                    .texOffs(46, 0)
                    .addBox(1.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f)
                    .texOffs(46, 0)
                    .addBox(-4.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f),
                PartPose.offset(0.0f, -14.0f, -1.0f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
