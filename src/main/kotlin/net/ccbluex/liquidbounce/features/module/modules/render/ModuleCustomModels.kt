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
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.blaze3d.vertex.PoseStack
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.Player

/**
 * Dreamcore Custom Models port.
 *
 * Currently ports the Rabbit model faithfully with the original Dreamcore texture.
 */
object ModuleCustomModels : ClientModule("CustomModels", Category.RENDER) {

    private val mode by enumChoice("Mode", ModelMode.RABBIT).apply { tagBy(this) }
    private val friends by boolean("Friends", false)
    private val hideArmor by boolean("HideArmor", true)
    private val hideCape by boolean("HideCape", false)

    private val rabbitModels = hashMapOf<Int, RabbitPlayerModel>()
    // #Edit By Codex 2026-06-13: Dreamcore assets were removed during TargetESP cleanup;
    // load the rabbit texture lazily so a missing asset does not crash client startup.
    private var rabbitTexture: Identifier? = null
    private var rabbitTextureFailed = false

    private fun rabbitTexture(): Identifier? {
        if (rabbitTextureFailed) {
            return null
        }

        rabbitTexture?.let { return it }

        val texture = Identifier.fromNamespaceAndPath("liquidbounce", "custom-models-rabbit")
        return runCatching {
            texture.apply { registerDynamicImageFromResources("dreamcore/models/rabbit.png") }
        }.onFailure { throwable ->
            rabbitTextureFailed = true
            logger.error("Failed to load CustomModels rabbit texture; disabling Rabbit model rendering.", throwable)
        }.getOrNull()?.also {
            rabbitTexture = it
        }
    }

    @JvmStatic
    fun shouldUseRabbit(state: LivingEntityRenderState?): Boolean {
        if (!running || mode != ModelMode.RABBIT || state !is AvatarRenderState) {
            return false
        }

        if (rabbitTexture() == null) {
            return false
        }

        val localPlayer = mc.player ?: return false
        val renderPlayer = (state as EntityRenderStateAddition).`liquid_bounce$getEntity`() as? Player ?: return false
        val name = renderPlayer.gameProfile.name
        if (state.id == localPlayer.id || name == localPlayer.gameProfile.name) {
            return true
        }

        return friends && FriendManager.isFriend(name)
    }

    @JvmStatic
    fun shouldHidePlayerFeature(state: LivingEntityRenderState?): Boolean {
        return hideArmor && shouldUseRabbit(state)
    }

    @JvmStatic
    fun shouldHideCape(state: LivingEntityRenderState?): Boolean {
        return hideCape && shouldUseRabbit(state)
    }

    override fun disable() {
        rabbitModels.clear()
    }

    @JvmStatic
    fun renderRabbit(
        playerModel: PlayerModel,
        matrices: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        state: AvatarRenderState
    ) {
        if (!shouldUseRabbit(state)) {
            return
        }

        val texture = rabbitTexture() ?: return
        val rabbitModel = rabbitModels.getOrPut(state.id) { RabbitPlayerModel.create() }
        rabbitModel.copyPlayerAngles(playerModel)

        matrices.pushPose()
        matrices.scale(1.25f, 1.25f, 1.25f)
        matrices.translate(0.0f, -0.3f, 0.0f)
        submitNodeCollector.submitModel(
            rabbitModel,
            state,
            matrices,
            rabbitModel.renderType(texture),
            state.lightCoords,
            LivingEntityRenderer.getOverlayCoords(state, 0.0f),
            -1,
            null,
            state.outlineColor,
            null
        )
        matrices.popPose()
    }

    private enum class ModelMode(override val choiceName: String) : NamedChoice {
        RABBIT("Rabbit")
    }
}

private class RabbitPlayerModel private constructor(root: ModelPart) :
    EntityModel<AvatarRenderState>(root, RenderTypes::entityTranslucent) {

    private val rabbitBone = root.getChild(RABBIT_BONE)
    private val rabbitRightLeg = rabbitBone.getChild(RABBIT_RIGHT_LEG)
    private val rabbitLeftLeg = rabbitBone.getChild(RABBIT_LEFT_LEG)
    private val rabbitRightArm = rabbitBone.getChild(RABBIT_RIGHT_ARM)
    private val rabbitLeftArm = rabbitBone.getChild(RABBIT_LEFT_ARM)
    private val rabbitHead = rabbitBone.getChild(RABBIT_HEAD)

    private var headXRot = 0.0f
    private var headYRot = 0.0f
    private var headZRot = 0.0f
    private var leftArmXRot = 0.0f
    private var leftArmYRot = 0.0f
    private var leftArmZRot = 0.0f
    private var rightArmXRot = 0.0f
    private var rightArmYRot = 0.0f
    private var rightArmZRot = 0.0f
    private var rightLegXRot = 0.0f
    private var rightLegYRot = 0.0f
    private var rightLegZRot = 0.0f
    private var leftLegXRot = 0.0f
    private var leftLegYRot = 0.0f
    private var leftLegZRot = 0.0f

    override fun setupAnim(state: AvatarRenderState) {
        super.setupAnim(state)
        applyCopiedAngles()
    }

    fun copyPlayerAngles(playerModel: PlayerModel) {
        headXRot = playerModel.head.xRot
        headYRot = playerModel.head.yRot
        headZRot = playerModel.head.zRot

        leftArmXRot = playerModel.leftArm.xRot
        leftArmYRot = playerModel.leftArm.yRot
        leftArmZRot = playerModel.leftArm.zRot - ARM_REST_ANGLE

        rightArmXRot = playerModel.rightArm.xRot
        rightArmYRot = playerModel.rightArm.yRot
        rightArmZRot = playerModel.rightArm.zRot + ARM_REST_ANGLE

        rightLegXRot = playerModel.rightLeg.xRot
        rightLegYRot = playerModel.rightLeg.yRot
        rightLegZRot = playerModel.rightLeg.zRot

        leftLegXRot = playerModel.leftLeg.xRot
        leftLegYRot = playerModel.leftLeg.yRot
        leftLegZRot = playerModel.leftLeg.zRot
        applyCopiedAngles()
    }

    private fun applyCopiedAngles() {
        rabbitHead.xRot = headXRot
        rabbitHead.yRot = headYRot
        rabbitHead.zRot = headZRot

        rabbitLeftArm.xRot = leftArmXRot
        rabbitLeftArm.yRot = leftArmYRot
        rabbitLeftArm.zRot = leftArmZRot

        rabbitRightArm.xRot = rightArmXRot
        rabbitRightArm.yRot = rightArmYRot
        rabbitRightArm.zRot = rightArmZRot

        rabbitRightLeg.xRot = rightLegXRot
        rabbitRightLeg.yRot = rightLegYRot
        rabbitRightLeg.zRot = rightLegZRot

        rabbitLeftLeg.xRot = leftLegXRot
        rabbitLeftLeg.yRot = leftLegYRot
        rabbitLeftLeg.zRot = leftLegZRot
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
            return RabbitPlayerModel(createLayerDefinition().bakeRoot())
        }

        private fun createLayerDefinition(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            val bone = root.addOrReplaceChild(
                RABBIT_BONE,
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
                RABBIT_LEFT_ARM,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(0.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                PartPose.offsetAndRotation(5.0f, -13.0f, -1.0f, 0.0f, 0.0f, -ARM_REST_ANGLE)
            )
            bone.addOrReplaceChild(
                RABBIT_RIGHT_ARM,
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f),
                PartPose.offsetAndRotation(-5.0f, -13.0f, -1.0f, 0.0f, 0.0f, ARM_REST_ANGLE)
            )

            val headBuilder = CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-3.0f, 0.0f, -4.0f, 6.0f, 1.0f, 6.0f)
                .texOffs(56, 0)
                .addBox(-5.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                .mirror(true)
                .texOffs(56, 0)
                .addBox(3.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f)
                .mirror(false)
                .texOffs(0, 45)
                .addBox(-4.0f, -11.0f, -4.0f, 8.0f, 11.0f, 8.0f)
                .texOffs(46, 0)
                .addBox(1.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f)
                .texOffs(46, 0)
                .addBox(-4.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f)

            bone.addOrReplaceChild(RABBIT_HEAD, headBuilder, PartPose.offset(0.0f, -14.0f, -1.0f))

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}
