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

class ChinchillaCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val nose = root.getChild(NOSE)
    private val rightEar = root.getChild(RIGHT_EAR)
    private val rightWhiskers = root.getChild(RIGHT_WHISKERS)
    private val leftEar = root.getChild(LEFT_EAR)
    private val leftRearFoot = root.getChild(LEFT_REAR_FOOT)
    private val rightRearFoot = root.getChild(RIGHT_REAR_FOOT)
    private val rightFrontLeg = root.getChild(RIGHT_FRONT_LEG)
    private val rightFrontFoot = root.getChild(RIGHT_FRONT_FOOT)
    private val leftFrontLeg = root.getChild(LEFT_FRONT_LEG)
    private val leftFrontFoot = root.getChild(LEFT_FRONT_FOOT)
    private val chin = root.getChild(CHIN)
    private val leftWhiskers = root.getChild(LEFT_WHISKERS)
    private val chinHead = root.getChild(CHIN_HEAD)

    private var headXRot = 0.0f
    private var headYRot = 0.0f
    private var leftLegXRot = 0.0f
    private var rightLegXRot = 0.0f

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headXRot = playerModel.head.xRot
        headYRot = playerModel.head.yRot
        leftLegXRot = playerModel.leftLeg.xRot
        rightLegXRot = playerModel.rightLeg.xRot
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        chinHead.xRot = headXRot
        chinHead.yRot = headYRot
        chin.xRot = chinHead.xRot
        chin.yRot = chinHead.yRot
        nose.xRot = chinHead.xRot
        nose.yRot = chinHead.yRot
        rightEar.yRot = chinHead.yRot + 2.094395f
        leftEar.yRot = chinHead.yRot + 1.047198f
        leftFrontLeg.xRot = leftLegXRot
        rightFrontLeg.xRot = rightLegXRot
        leftFrontFoot.xRot = leftLegXRot
        rightFrontFoot.xRot = rightLegXRot
        leftRearFoot.xRot = -leftLegXRot
        rightRearFoot.xRot = -rightLegXRot
        leftWhiskers.yRot = chinHead.yRot - 0.1745329f
        leftWhiskers.xRot = chinHead.xRot - 0.1745329f
        rightWhiskers.yRot = chinHead.yRot + 0.1745329f
        rightWhiskers.xRot = chinHead.xRot + 0.1745329f
    }

    companion object {
        private const val NOSE = "nose"
        private const val RIGHT_EAR = "rightEar"
        private const val RIGHT_WHISKERS = "rightWhiskers"
        private const val LEFT_EAR = "leftEar"
        private const val LEFT_REAR_FOOT = "leftRearFoot"
        private const val RIGHT_REAR_FOOT = "rightRearFoot"
        private const val RIGHT_FRONT_LEG = "rightFrontLeg"
        private const val RIGHT_FRONT_FOOT = "rightFrontFoot"
        private const val LEFT_FRONT_LEG = "leftFrontLeg"
        private const val LEFT_FRONT_FOOT = "leftFrontFoot"
        private const val CHIN = "chin"
        private const val LEFT_WHISKERS = "leftWhiskers"
        private const val CHIN_HEAD = "chinHead"

        fun create(): ChinchillaCustomModel {
            return ChinchillaCustomModel(createLayerDefinition().bakeRoot())
        }

        @Suppress("LongMethod")
        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            root.addOrReplaceChild(
                "back",
                CubeListBuilder.create()
                    .texOffs(0, 14)
                    .addBox(-3.0f, 0.3f, 3.3f, 6.0f, 6.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -2.0f, 0.2443461f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                NOSE,
                CubeListBuilder.create()
                    .texOffs(46, 24)
                    .addBox(-2.0f, -3.0f, -3.0f, 4.0f, 4.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, 0.3687912f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_EAR,
                CubeListBuilder.create()
                    .texOffs(48, 8)
                    .addBox(0.5f, -6.0f, -0.6f, 1.0f, 4.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, 0.8726646f, 2.094395f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_WHISKERS,
                CubeListBuilder.create()
                    .texOffs(18, 19)
                    .addBox(-5.0f, -1.4f, -4.04f, 4.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, -0.0872665f, 0.1745329f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_EAR,
                CubeListBuilder.create()
                    .texOffs(48, 0)
                    .addBox(0.5f, -6.0f, -2.4f, 1.0f, 4.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, -0.8726646f, 1.047198f, 0.0f)
            )
            root.addOrReplaceChild(
                "upperTail",
                CubeListBuilder.create()
                    .texOffs(14, 23)
                    .addBox(-1.5f, 1.5f, 2.6f, 3.0f, 3.0f, 4.0f),
                PartPose.offsetAndRotation(0.0f, 22.2f, 4.066667f, 0.7330383f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                "chinBody",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.5f, -2.0f, -1.0f, 7.0f, 7.0f, 7.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -2.0f, -0.0872665f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                "lowerTail",
                CubeListBuilder.create()
                    .texOffs(0, 23)
                    .addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 5.0f),
                PartPose.offsetAndRotation(0.0f, 22.2f, 4.066667f, -0.1047198f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_REAR_FOOT,
                CubeListBuilder.create()
                    .texOffs(28, 9)
                    .addBox(-1.5f, 0.0f, -3.0f, 2.0f, 1.0f, 4.0f),
                PartPose.offsetAndRotation(-3.0f, 23.0f, 3.0f, 0.0f, 0.0698132f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_REAR_FOOT,
                CubeListBuilder.create()
                    .texOffs(28, 0)
                    .addBox(-0.5f, 0.0f, -3.0f, 2.0f, 1.0f, 4.0f),
                PartPose.offsetAndRotation(3.0f, 23.0f, 3.0f, 0.0f, -0.0698132f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_FRONT_LEG,
                CubeListBuilder.create()
                    .texOffs(40, 9)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 4.0f, 2.0f),
                PartPose.offsetAndRotation(-3.0f, 20.0f, -3.0f, -0.1858931f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                RIGHT_FRONT_FOOT,
                CubeListBuilder.create()
                    .texOffs(28, 14)
                    .addBox(-1.0f, 3.0f, -2.8f, 2.0f, 1.0f, 3.0f),
                PartPose.offsetAndRotation(-3.0f, 20.0f, -3.0f, 0.0f, -0.0698132f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_FRONT_LEG,
                CubeListBuilder.create()
                    .texOffs(40, 0)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 4.0f, 2.0f),
                PartPose.offsetAndRotation(3.0f, 20.0f, -3.0f, -0.1858931f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_FRONT_FOOT,
                CubeListBuilder.create()
                    .texOffs(28, 5)
                    .addBox(-1.0f, 3.0f, -2.8f, 2.0f, 1.0f, 3.0f),
                PartPose.offsetAndRotation(3.0f, 20.0f, -3.0f, 0.0f, 0.0698132f, 0.0f)
            )
            root.addOrReplaceChild(
                CHIN,
                CubeListBuilder.create()
                    .texOffs(46, 18)
                    .addBox(-2.0f, -1.3f, -4.04f, 4.0f, 3.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, -0.1115358f, 0.0f, 0.0f)
            )
            root.addOrReplaceChild(
                LEFT_WHISKERS,
                CubeListBuilder.create()
                    .texOffs(18, 14)
                    .addBox(1.0f, -1.4f, -4.04f, 4.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 18.0f, -3.0f, -0.0872665f, -0.1745329f, 0.0f)
            )
            root.addOrReplaceChild(
                CHIN_HEAD,
                CubeListBuilder.create()
                    .texOffs(28, 21)
                    .addBox(-2.5f, -3.0f, -2.4f, 5.0f, 5.0f, 4.0f),
                PartPose.offset(0.0f, 18.0f, -3.0f)
            )

            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}
