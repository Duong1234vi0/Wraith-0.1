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

class CrabCustomModel private constructor(root: ModelPart) : CustomPlayerModel(root) {

    override fun copyFromPlayerModel(playerModel: PlayerModel) {
        applyCopiedAngles()
    }

    override fun applyCopiedAngles() {
    }

    companion object {
        fun create(): CrabCustomModel {
            return CrabCustomModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            val bone = root.addOrReplaceChild(
                "bone",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-8.0f, -12.0f, -6.0f, 16.0f, 7.0f, 12.0f)
                    .texOffs(44, 0)
                    .addBox(7.0f, -8.0f, -13.0f, 2.0f, 3.0f, 7.0f)
                    .texOffs(40, 40)
                    .addBox(-9.0f, -8.0f, -13.0f, 2.0f, 3.0f, 7.0f)
                    .texOffs(44, 27)
                    .addBox(-10.0f, -9.0f, -16.0f, 6.0f, 5.0f, 3.0f)
                    .texOffs(44, 19)
                    .addBox(4.0f, -9.0f, -16.0f, 6.0f, 5.0f, 3.0f)
                    .texOffs(32, 55)
                    .addBox(2.0f, -5.0f, -16.0f, 2.0f, 1.0f, 3.0f)
                    .texOffs(31, 19)
                    .addBox(-4.0f, -5.0f, -16.0f, 2.0f, 1.0f, 3.0f)
                    .texOffs(0, 41)
                    .addBox(-4.0f, -9.0f, -16.0f, 3.0f, 2.0f, 3.0f)
                    .texOffs(20, 39)
                    .addBox(1.0f, -9.0f, -16.0f, 3.0f, 2.0f, 3.0f)
                    .texOffs(0, 35)
                    .addBox(-6.0f, -8.0f, -9.0f, 3.0f, 3.0f, 3.0f)
                    .texOffs(22, 22)
                    .addBox(3.0f, -8.0f, -9.0f, 3.0f, 3.0f, 3.0f),
                PartPose.offset(0.0f, 24.0f, 0.0f)
            )
            bone.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create()
                    .texOffs(51, 39)
                    .addBox(-11.5f, -1.8f, 4.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(8, 51)
                    .addBox(-11.5f, -1.8f, 0.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(24, 55)
                    .addBox(-11.5f, -1.8f, -6.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(0, 35)
                    .addBox(-12.5f, -5.8f, -6.0f, 4.0f, 4.0f, 12.0f)
                    .texOffs(22, 23)
                    .addBox(3.3f, -13.6f, -6.0f, 5.0f, 4.0f, 12.0f),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.3491f)
            )
            bone.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create()
                    .texOffs(52, 52)
                    .addBox(9.5f, -1.8f, 4.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(16, 55)
                    .addBox(9.5f, -1.8f, 0.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(0, 51)
                    .addBox(9.5f, -1.8f, -6.0f, 2.0f, 6.0f, 2.0f)
                    .texOffs(20, 39)
                    .addBox(8.5f, -5.8f, -6.0f, 4.0f, 4.0f, 12.0f)
                    .texOffs(0, 19)
                    .addBox(-8.3f, -13.6f, -6.0f, 5.0f, 4.0f, 12.0f),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3491f)
            )
            bone.addOrReplaceChild(
                "cube_r3",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(1.3f, -18.0f, -6.7f, 3.0f, 6.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1309f)
            )
            bone.addOrReplaceChild(
                "cube_r4",
                CubeListBuilder.create()
                    .texOffs(0, 19)
                    .addBox(-4.3f, -18.0f, -6.7f, 3.0f, 6.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.1309f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
