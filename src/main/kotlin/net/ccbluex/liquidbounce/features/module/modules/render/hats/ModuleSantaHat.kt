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
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.world.entity.player.Player

object ModuleSantaHat : ClientModule(
    "SantaHat",
    Category.RENDER,
    aliases = arrayOf("ChristmasHat", "PouchSantaHat")
) {

    private var model: SantaHatModel? = null
    private var modelFailed = false
    private var textureFailureLogged = false
    private var submitFailureLogged = false

    @JvmStatic
    fun submitHat(
        playerModel: PlayerModel,
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        state: AvatarRenderState
    ): Boolean {
        val player = playerFromState(state) ?: return false
        if (!running || player !== mc.player || !textureAvailable()) {
            return false
        }

        val santaHatModel = modelOrNull() ?: return false
        poseStack.pushPose()
        return try {
            playerModel.head.translateAndRotate(poseStack)
            santaHatModel.submit(
                poseStack,
                submitNodeCollector,
                state,
                LivingEntityRenderer.getOverlayCoords(state, 0.0f)
            )
            true
        } catch (throwable: Throwable) {
            logSubmitFailure(throwable)
            false
        } finally {
            poseStack.popPose()
        }
    }

    private fun playerFromState(state: AvatarRenderState): Player? {
        return (state as? EntityRenderStateAddition)?.`liquid_bounce$getEntity`() as? Player
    }

    private fun textureAvailable(): Boolean {
        val available = runCatching {
            mc.resourceManager.getResource(SantaHatModel.TEXTURE).isPresent
        }.getOrElse { throwable ->
            if (!textureFailureLogged) {
                textureFailureLogged = true
                logger.error("Failed to check SantaHat texture ${SantaHatModel.TEXTURE}; SantaHat will be skipped.", throwable)
            }
            return false
        }

        if (!available && !textureFailureLogged) {
            textureFailureLogged = true
            logger.error("Missing SantaHat texture ${SantaHatModel.TEXTURE}; SantaHat will be skipped.")
        }

        return available
    }

    private fun modelOrNull(): SantaHatModel? {
        if (modelFailed) {
            return null
        }

        model?.let { return it }

        return runCatching {
            SantaHatModel.create()
        }.onFailure { throwable ->
            modelFailed = true
            logger.error("Failed to create SantaHat model; SantaHat will be skipped.", throwable)
        }.getOrNull()?.also { created ->
            model = created
        }
    }

    private fun logSubmitFailure(throwable: Throwable) {
        if (!submitFailureLogged) {
            submitFailureLogged = true
            logger.error("Failed to submit SantaHat geometry; SantaHat will be skipped.", throwable)
        }
    }
}
