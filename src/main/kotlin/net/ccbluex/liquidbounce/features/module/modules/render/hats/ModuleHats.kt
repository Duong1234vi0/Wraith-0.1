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

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.model.player.PlayerModel
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.AvatarRenderState
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * V-Core 0.8 cone hat port.
 */
@Suppress("MagicNumber")
object ModuleHats : ClientModule("Hat", Category.RENDER, aliases = arrayOf("Hats")) {

    private val autoOffset by boolean("AutoOffset", false)
    private val syncColor by boolean("SyncColor", true)
    private val color by color("Color", Color4b(10, 255, 135, 255)).visibleWhen { !syncColor }

    @JvmStatic
    fun submitHat(
        playerModel: PlayerModel,
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        state: AvatarRenderState
    ): Boolean {
        val entity = entityFromState(state) ?: return false
        if (!running || entity !== mc.player) {
            return false
        }

        // LivingEntityRenderer has already applied setupAnim(state) before feature layers are submitted.
        // Re-running it here can overwrite render-layer or mixin pose adjustments.
        poseStack.pushPose()
        try {
            playerModel.head.translateAndRotate(poseStack)
            poseStack.translate(0.0, yOffset(entity).toDouble(), 0.0)
            poseStack.mulPose(Axis.ZN.rotationDegrees(180.0f))
            poseStack.mulPose(Axis.YP.rotationDegrees(-180.0f))

            submitNodeCollector.submitCustomGeometry(poseStack, FILLED_RENDER_TYPE, ::renderFilledCone)
            submitNodeCollector.order(1).submitCustomGeometry(poseStack, RIM_RENDER_TYPE, ::renderRim)
        } finally {
            poseStack.popPose()
        }

        return true
    }

    private fun entityFromState(state: AvatarRenderState): LivingEntity? {
        return (state as? EntityRenderStateAddition)?.`liquid_bounce$getEntity`() as? LivingEntity
    }

    private fun yOffset(entity: LivingEntity): Float {
        var offset = -0.41f
        if (autoOffset && !entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty) {
            offset -= 0.071f
        }
        return offset
    }

    private fun renderFilledCone(pose: PoseStack.Pose, buffer: VertexConsumer) {
        for (i in 0 until STEPS) {
            val current = pointOnRim(i)
            val next = pointOnRim(i + 1)

            buffer.addVertex(pose, 0.0f, APEX_Y, 0.0f)
                .setColor(colorAt((i + 0.5) / STEPS, 255).toARGB())
            buffer.addVertex(pose, current.x, BASE_Y, current.z)
                .setColor(colorAt(i.toDouble() / STEPS, 120).toARGB())
            buffer.addVertex(pose, next.x, BASE_Y, next.z)
                .setColor(colorAt((i + 1.0) / STEPS, 120).toARGB())
        }
    }

    private fun renderRim(pose: PoseStack.Pose, buffer: VertexConsumer) {
        buffer.setLineWidth(2.0f)

        for (i in 0..STEPS) {
            val point = pointOnRim(i)
            buffer.addVertex(pose, point.x, BASE_Y, point.z)
                .setColor(colorAt(i.toDouble() / STEPS, 255).toARGB())
        }
    }

    private fun colorAt(fraction: Double, alpha: Int): Color4b {
        if (!syncColor) {
            return color.with(a = alpha)
        }

        return twoColorEffect(
            rgbIntToColor(ClientTheme.accentA),
            rgbIntToColor(ClientTheme.accentB),
            15.0,
            fraction * 360.0
        ).with(a = alpha)
    }

    private fun twoColorEffect(color1: Color4b, color2: Color4b, speed: Double, count: Double): Color4b {
        var angle = ((System.currentTimeMillis() / speed + count) % 360.0).toInt()
        angle = (if (angle >= 180) 360 - angle else angle) * 2
        return color1.interpolateTo(color2, angle / 360.0)
    }

    private fun rgbIntToColor(color: Int): Color4b {
        return Color4b(
            (color shr 16) and 0xFF,
            (color shr 8) and 0xFF,
            color and 0xFF,
            255
        )
    }

    private fun pointOnRim(index: Int): HatPoint {
        val angle = index * ANGLE_STEP
        return HatPoint(
            (cos(angle) * RADIUS).toFloat(),
            (sin(angle) * RADIUS).toFloat()
        )
    }

    private data class HatPoint(val x: Float, val z: Float)

    private const val RADIUS = 0.6
    private const val STEPS = 72
    private const val APEX_Y = 0.3f
    private const val BASE_Y = 0.0f
    private const val BUFFER_SIZE = 4096
    private val ANGLE_STEP = PI * 2.0 / STEPS

    private val FILLED_RENDER_TYPE: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        RenderType.create(
            "liquidbounce_vcore_hat_filled",
            RenderSetup.builder(hatPipeline("hat_filled", VertexFormat.Mode.TRIANGLES))
                .bufferSize(BUFFER_SIZE)
                .createRenderSetup()
        )
    }

    private val RIM_RENDER_TYPE: RenderType by lazy(LazyThreadSafetyMode.NONE) {
        RenderType.create(
            "liquidbounce_vcore_hat_rim",
            RenderSetup.builder(hatPipeline("hat_rim", VertexFormat.Mode.DEBUG_LINE_STRIP))
                .bufferSize(BUFFER_SIZE)
                .createRenderSetup()
        )
    }

    private fun hatPipeline(name: String, mode: VertexFormat.Mode): RenderPipeline {
        return RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/$name"))
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .withCull(false)
            .withVertexFormat(DefaultVertexFormat.POSITION_COLOR, mode)
            .build()
    }
}
