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
package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.vertex.PoseStack
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomModelRegistry
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomModelSpec
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomModelTextureLoader
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomModelType
import net.ccbluex.liquidbounce.features.module.modules.render.custommodels.CustomPlayerModel
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.entity.player.Player

/**
 * AlphaDLC CustomModel port.
 *
 * The module owns selection and cache lifetime only. Geometry, textures, transforms, and per-model item offsets
 * live in the custom-model registry/model package.
 */
object ModuleCustomModels : ClientModule("CustomModels", Category.RENDER) {

    private val mode by enumChoice("Mode", CustomModelType.WHITE_DEMON).apply { tagBy(this) }
    private val friends by boolean("Friends", false)
    private val hideArmor by boolean("HideArmor", true)
    private val hideCape by boolean("HideCape", false)

    private val modelCache = hashMapOf<ModelCacheKey, CustomPlayerModel>()
    private val failedModels = linkedSetOf<CustomModelType>()

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearModelCache()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        pruneModelCache()
    }

    override fun disable() {
        clearModelCache()
        super.disable()
    }

    @JvmStatic
    fun selectedModelFor(state: LivingEntityRenderState?): CustomModelType? {
        return selectedSpecFor(state)?.type
    }

    @JvmStatic
    fun shouldUseCustomModel(state: LivingEntityRenderState?): Boolean {
        val avatarState = state as? AvatarRenderState ?: return false
        val spec = selectedSpecFor(avatarState) ?: return false

        return textureFor(spec) != null && modelFor(avatarState, spec) != null
    }

    @JvmStatic
    fun shouldHideArmor(state: LivingEntityRenderState?): Boolean {
        return hideArmor && shouldUseCustomModel(state)
    }

    @JvmStatic
    fun shouldHideCape(state: LivingEntityRenderState?): Boolean {
        return hideCape && shouldUseCustomModel(state)
    }

    @JvmStatic
    fun customHeldItemTransform(state: ArmedEntityRenderState?, arm: HumanoidArm): FloatArray? {
        val spec = selectedSpecFor(state as? LivingEntityRenderState) ?: return null
        return spec.heldItemTransform?.invoke(arm)
    }

    @JvmStatic
    fun renderCustomModel(
        playerModel: PlayerModel,
        matrices: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        state: AvatarRenderState
    ) {
        val spec = selectedSpecFor(state) ?: return
        val texture = textureFor(spec) ?: return
        val model = modelFor(state, spec) ?: return

        runCatching {
            model.copyFromPlayerModel(playerModel)

            matrices.pushPose()
            try {
                matrices.scale(spec.transform.scaleX, spec.transform.scaleY, spec.transform.scaleZ)
                matrices.translate(
                    spec.transform.translateX,
                    spec.transform.translateY,
                    spec.transform.translateZ
                )
                submitNodeCollector.submitModel(
                    model,
                    state,
                    matrices,
                    model.renderType(texture),
                    state.lightCoords,
                    LivingEntityRenderer.getOverlayCoords(state, 0.0f),
                    -1,
                    null,
                    state.outlineColor,
                    null
                )
            } finally {
                matrices.popPose()
            }
        }.onFailure { throwable ->
            failedModels += spec.type
            logger.error("Failed to render CustomModels ${spec.displayName}; vanilla player rendering will be used.", throwable)
        }
    }

    private fun selectedSpecFor(state: LivingEntityRenderState?): CustomModelSpec? {
        if (!running || state !is AvatarRenderState) {
            return null
        }

        val player = playerFor(state) ?: return null
        if (!shouldApplyTo(player)) {
            return null
        }

        val type = mode
        if (type in failedModels) {
            return null
        }

        return CustomModelRegistry[type]
    }

    private fun shouldApplyTo(player: Player): Boolean {
        val localPlayer = mc.player ?: return false
        if (player.id == localPlayer.id || player.uuid == localPlayer.uuid || player === localPlayer) {
            return true
        }

        return friends && FriendManager.isFriend(player)
    }

    private fun playerFor(state: AvatarRenderState): Player? {
        val entity = (state as EntityRenderStateAddition).`liquid_bounce$getEntity`()
        return entity as? Player
    }

    private fun textureFor(spec: CustomModelSpec): Identifier? {
        return CustomModelTextureLoader.texture(spec)
    }

    private fun modelFor(state: AvatarRenderState, spec: CustomModelSpec): CustomPlayerModel? {
        val key = ModelCacheKey(state.id, spec.type)
        return modelCache[key] ?: runCatching {
            spec.modelFactory()
        }.onFailure { throwable ->
            failedModels += spec.type
            logger.error("Failed to create CustomModels ${spec.displayName}; vanilla player rendering will be used.", throwable)
        }.getOrNull()?.also { model ->
            modelCache[key] = model
        }
    }

    private fun clearModelCache() {
        modelCache.clear()
    }

    private fun pruneModelCache() {
        val liveIds = mc.level?.players()?.mapTo(hashSetOf()) { it.id } ?: emptySet()
        modelCache.keys.removeIf { key -> key.entityId !in liveIds }
    }

    private data class ModelCacheKey(val entityId: Int, val type: CustomModelType)
}
