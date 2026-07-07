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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.render.entity.state.PlayerEntityRenderState
import net.minecraft.client.util.math.MatrixStack

/**
 * Dreamcore Custom Models port.
 *
 * Currently ports the Rabbit model faithfully with the original Dreamcore texture.
 */
object ModuleCustomModels : ClientModule("CustomModels", Category.RENDER) {

    private val mode by enumChoice("Mode", ModelMode.RABBIT).apply { tagBy(this) }
    private val friends by boolean("Friends", false)
    private val hideArmor by boolean("HideArmor", true)

    private val rabbitModel = RabbitPlayerModel.create()
    private val rabbitTexture = "dreamcore/models/rabbit.png".registerAsDynamicImageFromClientResources()

    @JvmStatic
    fun shouldUseRabbit(state: LivingEntityRenderState?): Boolean {
        if (!running || mode != ModelMode.RABBIT || state !is PlayerEntityRenderState) {
            return false
        }

        val player = mc.player ?: return false
        val name = state.name ?: return false
        if (state.id == player.id || name == player.gameProfile.name) {
            return true
        }

        return friends && FriendManager.isFriend(name)
    }

    @JvmStatic
    fun shouldHidePlayerFeature(state: LivingEntityRenderState?): Boolean {
        return hideArmor && shouldUseRabbit(state)
    }

    @JvmStatic
    fun renderRabbit(
        playerModel: PlayerEntityModel,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int,
        color: Int
    ) {
        val vertexConsumer = vertexConsumers.getBuffer(rabbitModel.getLayer(rabbitTexture))
        rabbitModel.renderFromPlayerModel(playerModel, matrices, vertexConsumer, light, overlay, color)
    }

    private enum class ModelMode(override val choiceName: String) : NamedChoice {
        RABBIT("Rabbit")
    }
}

private class RabbitPlayerModel private constructor(root: ModelPart) :
    EntityModel<PlayerEntityRenderState>(root, RenderLayer::getEntityTranslucent) {

    private val rabbitBone = root.getChild(RABBIT_BONE)
    private val rabbitRightLeg = rabbitBone.getChild(RABBIT_RIGHT_LEG)
    private val rabbitLeftLeg = rabbitBone.getChild(RABBIT_LEFT_LEG)
    private val rabbitRightArm = rabbitBone.getChild(RABBIT_RIGHT_ARM)
    private val rabbitLeftArm = rabbitBone.getChild(RABBIT_LEFT_ARM)
    private val rabbitHead = rabbitBone.getChild(RABBIT_HEAD)

    override fun setAngles(state: PlayerEntityRenderState) {
        // The Rabbit animation copies the already solved vanilla player model angles.
    }

    fun renderFromPlayerModel(
        playerModel: PlayerEntityModel,
        matrices: MatrixStack,
        vertexConsumer: VertexConsumer,
        light: Int,
        overlay: Int,
        color: Int
    ) {
        copyPlayerAngles(playerModel)

        matrices.push()
        matrices.scale(1.25f, 1.25f, 1.25f)
        matrices.translate(0.0f, -0.3f, 0.0f)
        render(matrices, vertexConsumer, light, overlay, color)
        matrices.pop()
    }

    private fun copyPlayerAngles(playerModel: PlayerEntityModel) {
        rabbitHead.pitch = playerModel.head.pitch
        rabbitHead.yaw = playerModel.head.yaw
        rabbitHead.roll = playerModel.head.roll

        rabbitLeftArm.pitch = playerModel.leftArm.pitch
        rabbitLeftArm.yaw = playerModel.leftArm.yaw
        rabbitLeftArm.roll = playerModel.leftArm.roll - ARM_REST_ANGLE

        rabbitRightArm.pitch = playerModel.rightArm.pitch
        rabbitRightArm.yaw = playerModel.rightArm.yaw
        rabbitRightArm.roll = playerModel.rightArm.roll + ARM_REST_ANGLE

        rabbitRightLeg.pitch = playerModel.rightLeg.pitch
        rabbitRightLeg.yaw = playerModel.rightLeg.yaw
        rabbitRightLeg.roll = playerModel.rightLeg.roll

        rabbitLeftLeg.pitch = playerModel.leftLeg.pitch
        rabbitLeftLeg.yaw = playerModel.leftLeg.yaw
        rabbitLeftLeg.roll = playerModel.leftLeg.roll
    }

    companion object {
        private const val RABBIT_BONE = "rabbitBone"
        private const val RABBIT_RIGHT_LEG = "rabbitRightLeg"
        private const val RABBIT_LEFT_LEG = "rabbitLeftLeg"
        private const val RABBIT_RIGHT_ARM = "rabbitRightArm"
        private const val RABBIT_LEFT_ARM = "rabbitLeftArm"
        private const val RABBIT_HEAD = "rabbitHead"
        private const val ARM_REST_ANGLE = 0.0873f

        fun create(): RabbitPlayerModel {
            return RabbitPlayerModel(createTexturedModelData().createModel())
        }

        private fun createTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val root = modelData.root
            val bone = root.addChild(
                RABBIT_BONE,
                ModelPartBuilder.create()
                    .uv(28, 45)
                    .cuboid(-5.0f, -13.0f, -5.0f, 10.0f, 11.0f, 8.0f),
                ModelTransform.pivot(0.0f, 24.0f, 0.0f)
            )

            bone.addChild(
                RABBIT_RIGHT_LEG,
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f),
                ModelTransform.pivot(-3.0f, -2.0f, -1.0f)
            )
            bone.addChild(
                RABBIT_LEFT_LEG,
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f),
                ModelTransform.pivot(3.0f, -2.0f, -1.0f)
            )
            bone.addChild(
                RABBIT_LEFT_ARM,
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(0.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                ModelTransform.of(5.0f, -13.0f, -1.0f, 0.0f, 0.0f, -ARM_REST_ANGLE)
            )
            bone.addChild(
                RABBIT_RIGHT_ARM,
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-2.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                ModelTransform.of(-5.0f, -13.0f, -1.0f, 0.0f, 0.0f, ARM_REST_ANGLE)
            )

            val headBuilder = ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-3.0f, 0.0f, -4.0f, 6.0f, 1.0f, 6.0f)
                .uv(56, 0)
                .cuboid(-5.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                .mirrored(true)
                .uv(56, 0)
                .cuboid(3.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                .mirrored(false)
                .uv(0, 45)
                .cuboid(-4.0f, -11.0f, -4.0f, 8.0f, 11.0f, 8.0f)
                .uv(46, 0)
                .cuboid(1.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f)
                .uv(46, 0)
                .cuboid(-4.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f)

            bone.addChild(RABBIT_HEAD, headBuilder, ModelTransform.pivot(0.0f, -14.0f, -1.0f))

            return TexturedModelData.of(modelData, 64, 64)
        }
    }
}
