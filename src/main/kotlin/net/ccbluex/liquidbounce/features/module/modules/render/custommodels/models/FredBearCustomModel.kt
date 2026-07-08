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

class FredBearCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    private val fredBody = root.getChild(FRED_BODY)
    private val fredHead = fredBody.getChild(FRED_HEAD)
    private val armRight = fredBody.getChild(ARM_RIGHT)
    private val armLeft = fredBody.getChild(ARM_LEFT)
    private val legRight = fredBody.getChild(LEG_RIGHT)
    private val legLeft = fredBody.getChild(LEG_LEFT)

    private var headRotation = CopiedRotation()
    private var armRightRotation = CopiedRotation()
    private var armLeftRotation = CopiedRotation()
    private var legRightRotation = CopiedRotation()
    private var legLeftRotation = CopiedRotation()

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        headRotation = captureRotation(playerModel.head)
        armLeftRotation = captureRotation(playerModel.leftArm, -0.2618f)
        armRightRotation = captureRotation(playerModel.rightArm, 0.2618f)
        legRightRotation = captureRotation(playerModel.rightLeg)
        legLeftRotation = captureRotation(playerModel.leftLeg)
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
        applyRotation(fredHead, headRotation)
        applyRotation(armLeft, armLeftRotation)
        applyRotation(armRight, armRightRotation)
        applyRotation(legRight, legRightRotation)
        applyRotation(legLeft, legLeftRotation)
    }

    companion object {
        private const val FRED_BODY = "fredBody"
        private const val FRED_HEAD = "fredHead"
        private const val ARM_RIGHT = "armRight"
        private const val ARM_LEFT = "armLeft"
        private const val LEG_RIGHT = "legRight"
        private const val LEG_LEFT = "legLeft"

        fun create(): FredBearCustomModel {
            return FredBearCustomModel(createLayerDefinition().bakeRoot())
        }

        @Suppress("LongMethod")
        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            val fredBody = root.addOrReplaceChild(
                FRED_BODY,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-1.0f, -14.0f, -1.0f, 2.0f, 24.0f, 2.0f),
                PartPose.offset(0.0f, -9.0f, 0.0f)
            )
            fredBody.addOrReplaceChild(
                "torso",
                CubeListBuilder.create()
                    .texOffs(8, 0)
                    .addBox(-6.0f, -9.0f, -4.0f, 12.0f, 18.0f, 8.0f),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0175f, 0.0f, 0.0f)
            )

            val armRight = fredBody.addOrReplaceChild(
                ARM_RIGHT,
                CubeListBuilder.create()
                    .texOffs(48, 0)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f),
                PartPose.offsetAndRotation(-6.5f, -8.0f, 0.0f, 0.0f, 0.0f, 0.2618f)
            )
            armRight.addOrReplaceChild(
                "armRightpad",
                CubeListBuilder.create()
                    .texOffs(70, 10)
                    .addBox(-2.5f, 0.0f, -2.5f, 5.0f, 9.0f, 5.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            val armRight2 = armRight.addOrReplaceChild(
                "armRight2",
                CubeListBuilder.create()
                    .texOffs(90, 20)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 9.6f, 0.0f, -0.1745f, 0.0f, 0.0f)
            )
            armRight2.addOrReplaceChild(
                "armRightpad2",
                CubeListBuilder.create()
                    .texOffs(0, 26)
                    .addBox(-2.5f, 0.0f, -2.5f, 5.0f, 7.0f, 5.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            armRight2.addOrReplaceChild(
                "handRight",
                CubeListBuilder.create()
                    .texOffs(20, 26)
                    .addBox(-2.0f, 0.0f, -2.5f, 4.0f, 4.0f, 5.0f),
                PartPose.offsetAndRotation(0.0f, 8.0f, 0.0f, 0.0f, 0.0f, -0.0524f)
            )

            val armLeft = fredBody.addOrReplaceChild(
                ARM_LEFT,
                CubeListBuilder.create()
                    .texOffs(62, 10)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f),
                PartPose.offsetAndRotation(6.5f, -8.0f, 0.0f, 0.0f, 0.0f, -0.2618f)
            )
            armLeft.addOrReplaceChild(
                "armLeftpad",
                CubeListBuilder.create()
                    .texOffs(38, 54)
                    .addBox(-2.5f, 0.0f, -2.5f, 5.0f, 9.0f, 5.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            val armLeft2 = armLeft.addOrReplaceChild(
                "armLeft2",
                CubeListBuilder.create()
                    .texOffs(90, 48)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 9.6f, 0.0f, -0.1745f, 0.0f, 0.0f)
            )
            armLeft2.addOrReplaceChild(
                "armLeftpad2",
                CubeListBuilder.create()
                    .texOffs(0, 58)
                    .addBox(-2.5f, 0.0f, -2.5f, 5.0f, 7.0f, 5.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            armLeft2.addOrReplaceChild(
                "handLeft",
                CubeListBuilder.create()
                    .texOffs(58, 56)
                    .addBox(-1.0f, 0.0f, -2.5f, 4.0f, 4.0f, 5.0f),
                PartPose.offsetAndRotation(0.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0524f)
            )

            fredBody.addOrReplaceChild(
                "crotch",
                CubeListBuilder.create()
                    .texOffs(56, 0)
                    .addBox(-5.5f, 0.0f, -3.5f, 11.0f, 3.0f, 7.0f),
                PartPose.offset(0.0f, 9.5f, 0.0f)
            )

            val legRight = fredBody.addOrReplaceChild(
                LEG_RIGHT,
                CubeListBuilder.create()
                    .texOffs(90, 8)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f),
                PartPose.offset(-3.3f, 12.5f, 0.0f)
            )
            legRight.addOrReplaceChild(
                "legRightpad",
                CubeListBuilder.create()
                    .texOffs(73, 33)
                    .addBox(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            val legRight2 = legRight.addOrReplaceChild(
                "legRight2",
                CubeListBuilder.create()
                    .texOffs(20, 35)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 9.6f, 0.0f, 0.0349f, 0.0f, 0.0f)
            )
            legRight2.addOrReplaceChild(
                "legRightpad2",
                CubeListBuilder.create()
                    .texOffs(0, 39)
                    .addBox(-2.5f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            legRight2.addOrReplaceChild(
                "footRight",
                CubeListBuilder.create()
                    .texOffs(22, 39)
                    .addBox(-2.5f, 0.0f, -6.0f, 5.0f, 3.0f, 8.0f),
                PartPose.offsetAndRotation(0.0f, 8.0f, 0.0f, -0.0349f, 0.0f, 0.0f)
            )

            val legLeft = fredBody.addOrReplaceChild(
                LEG_LEFT,
                CubeListBuilder.create()
                    .texOffs(54, 10)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 10.0f, 2.0f),
                PartPose.offset(3.3f, 12.5f, 0.0f)
            )
            legLeft.addOrReplaceChild(
                "legLeftpad",
                CubeListBuilder.create()
                    .texOffs(48, 39)
                    .addBox(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            val legLeft2 = legLeft.addOrReplaceChild(
                "legLeft2",
                CubeListBuilder.create()
                    .texOffs(72, 48)
                    .addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 9.6f, 0.0f, 0.0349f, 0.0f, 0.0f)
            )
            legLeft2.addOrReplaceChild(
                "legLeftpad2",
                CubeListBuilder.create()
                    .texOffs(16, 50)
                    .addBox(-2.5f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f),
                PartPose.offset(0.0f, 0.5f, 0.0f)
            )
            legLeft2.addOrReplaceChild(
                "footLeft",
                CubeListBuilder.create()
                    .texOffs(72, 50)
                    .addBox(-2.5f, 0.0f, -6.0f, 5.0f, 3.0f, 8.0f),
                PartPose.offsetAndRotation(0.0f, 8.0f, 0.0f, -0.0349f, 0.0f, 0.0f)
            )

            val fredHead = fredBody.addOrReplaceChild(
                FRED_HEAD,
                CubeListBuilder.create()
                    .texOffs(39, 22)
                    .addBox(-5.5f, -8.0f, -4.5f, 11.0f, 8.0f, 9.0f),
                PartPose.offset(0.0f, -13.0f, -0.5f)
            )
            fredHead.addOrReplaceChild(
                "jaw",
                CubeListBuilder.create()
                    .texOffs(49, 65)
                    .addBox(-5.0f, 0.0f, -4.5f, 10.0f, 3.0f, 9.0f),
                PartPose.offsetAndRotation(0.0f, 0.5f, 0.0f, 0.0873f, 0.0f, 0.0f)
            )
            fredHead.addOrReplaceChild(
                "frednose",
                CubeListBuilder.create()
                    .texOffs(17, 67)
                    .addBox(-4.0f, -2.0f, -3.0f, 8.0f, 4.0f, 3.0f),
                PartPose.offset(0.0f, -2.0f, -4.5f)
            )
            val earRight = fredHead.addOrReplaceChild(
                "earRight",
                CubeListBuilder.create()
                    .texOffs(8, 0)
                    .addBox(-1.0f, -3.0f, -0.5f, 2.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(-4.5f, -5.5f, 0.0f, 0.0524f, 0.0f, -1.0472f)
            )
            earRight.addOrReplaceChild(
                "earRightpad",
                CubeListBuilder.create()
                    .texOffs(85, 0)
                    .addBox(-2.0f, -5.0f, -1.0f, 4.0f, 4.0f, 2.0f),
                PartPose.offset(0.0f, -1.0f, 0.0f)
            )
            val earLeft = fredHead.addOrReplaceChild(
                "earLeft",
                CubeListBuilder.create()
                    .texOffs(40, 0)
                    .addBox(-1.0f, -3.0f, -0.5f, 2.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(4.5f, -5.5f, 0.0f, 0.0524f, 0.0f, 1.0472f)
            )
            earLeft.addOrReplaceChild(
                "earRightpad_1",
                CubeListBuilder.create()
                    .texOffs(40, 39)
                    .addBox(-2.0f, -5.0f, -1.0f, 4.0f, 4.0f, 2.0f),
                PartPose.offset(0.0f, -1.0f, 0.0f)
            )
            val hat = fredHead.addOrReplaceChild(
                "hat",
                CubeListBuilder.create()
                    .texOffs(70, 24)
                    .addBox(-3.0f, -0.5f, -3.0f, 6.0f, 1.0f, 6.0f),
                PartPose.offsetAndRotation(0.0f, -8.4f, 0.0f, -0.0175f, 0.0f, 0.0f)
            )
            hat.addOrReplaceChild(
                "hat2",
                CubeListBuilder.create()
                    .texOffs(78, 61)
                    .addBox(-2.0f, -4.0f, -2.0f, 4.0f, 4.0f, 4.0f),
                PartPose.offsetAndRotation(0.0f, 0.1f, 0.0f, -0.0175f, 0.0f, 0.0f)
            )

            return LayerDefinition.create(mesh, 100, 80)
        }
    }
}
