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

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.Model
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.resources.Identifier

class SantaHatModel private constructor(root: ModelPart) : Model<Unit>(root, { texture ->
    RenderTypes.entityCutout(texture)
}) {

    override fun setupAnim(state: Unit) {
    }

    fun submit(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        state: AvatarRenderState,
        overlayCoords: Int
    ) {
        submitNodeCollector.submitModelPart(
            root(),
            poseStack,
            renderType(TEXTURE),
            state.lightCoords,
            overlayCoords,
            null as TextureAtlasSprite?,
            false,
            false,
            -1,
            null as ModelFeatureRenderer.CrumblingOverlay?,
            state.outlineColor
        )
    }

    companion object {
        val TEXTURE: Identifier = Identifier.fromNamespaceAndPath(
            "liquidbounce",
            "textures/entity/santa_hat.png"
        )

        fun create(): SantaHatModel = SantaHatModel(SantaHatModelLayer.createRoot())
    }
}
