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
package net.ccbluex.liquidbounce.features.module.modules.render.custommodels

import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.rendertype.RenderTypes

abstract class CustomPlayerModel(root: ModelPart) : EntityModel<AvatarRenderState>(root, RenderTypes::entityTranslucent) {

    override fun setupAnim(state: AvatarRenderState) {
        super.setupAnim(state)
        applyCopiedAngles()
    }

    abstract fun copyFromPlayerModel(playerModel: PlayerModel)

    protected abstract fun applyCopiedAngles()

    protected fun captureRotation(source: ModelPart, rollOffset: Float = 0.0f): CopiedRotation {
        return CopiedRotation(source.xRot, source.yRot, source.zRot + rollOffset)
    }

    protected fun applyRotation(target: ModelPart, rotation: CopiedRotation) {
        target.xRot = rotation.xRot
        target.yRot = rotation.yRot
        target.zRot = rotation.zRot
    }

    protected data class CopiedRotation(
        val xRot: Float = 0.0f,
        val yRot: Float = 0.0f,
        val zRot: Float = 0.0f
    )
}
