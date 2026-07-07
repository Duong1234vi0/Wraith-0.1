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

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.utils.shiftHue
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.BufferBuilder
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis
import org.joml.Matrix4f
import org.lwjgl.opengl.GL11
import kotlin.math.max
import kotlin.math.sin

/**
 * EvaWare Wings port.
 */
@Suppress("MagicNumber")
object ModuleWings : ClientModule("Wings", Category.RENDER) {

    private val self by boolean("Self", true)
    private val players by boolean("Players", false)
    private val size by float("Size", 1.0f, 0.75f..1.35f)
    private val alpha by int("Alpha", 220, 60..255)
    private val offsetX by float("OffsetX", 0.0f, -1.5f..1.5f)
    private val offsetY by float("OffsetY", 0.0f, -1.5f..1.5f)
    private val offsetZ by float("OffsetZ", 0.0f, -1.5f..1.5f)
    private val customColor by boolean("CustomColor", false)
    private val color by color("Color", Color4b(255, 100, 255))

    private var selfBodyYaw = 0.0f
    private var selfBodyYawInitialized = false

    override fun disable() {
        selfBodyYawInitialized = false
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val localPlayer = mc.player ?: return@handler
        val world = mc.world ?: return@handler

        RenderSystem.enableBlend()
        RenderSystem.disableCull()
        RenderSystem.enableDepthTest()
        RenderSystem.depthMask(false)
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR)

        try {
            renderEnvironmentForWorld(event.matrixStack) {
                if (self && !mc.options.perspective.isFirstPerson && localPlayer.canRenderWings()) {
                    renderWings(localPlayer, event.partialTicks)
                }

                if (players) {
                    world.players.forEach { player ->
                        if (player != localPlayer && player.canRenderWings()) {
                            renderWings(player, event.partialTicks)
                        }
                    }
                }
            }
        } finally {
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
            RenderSystem.disableBlend()
            RenderSystem.blendFuncSeparate(
                GlStateManager.SrcFactor.SRC_ALPHA,
                GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SrcFactor.ONE,
                GlStateManager.DstFactor.ZERO
            )
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        }
    }

    private fun PlayerEntity.canRenderWings(): Boolean {
        return isAlive && !isRemoved && !getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA)
    }

    private fun WorldRenderEnvironment.renderWings(player: PlayerEntity, tickDelta: Float) {
        val pose = player.resolvePose(tickDelta) ?: return
        val bodyYaw = player.resolveBodyYaw(tickDelta)
        val movement = MathHelper.clamp(player.limbAnimator.getSpeed(tickDelta), 0.0f, 1.0f)
        val flap = sin((player.age + tickDelta) * pose.flapSpeed) * pose.flapAmplitude
        val open = (DEFAULT_SPREAD + flap + movement * pose.motionSpreadBoost) * pose.openMultiplier
        val wingScale = size * pose.scaleMultiplier
        val baseColor = player.resolveBaseColor()
        val glowColor = baseColor.interpolateTo(Color4b.WHITE, 0.28)
        val coreColor = baseColor.interpolateTo(Color4b.WHITE, 0.55)

        withPositionRelativeToCamera(player.interpolateCurrentPosition(tickDelta)) {
            matrixStack.push()
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - bodyYaw))

            if (pose.preTranslateY != 0.0f || pose.preTranslateZ != 0.0f) {
                matrixStack.translate(0.0, pose.preTranslateY.toDouble(), pose.preTranslateZ.toDouble())
            }

            if (pose.pitchRotation != 0.0f) {
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pose.pitchRotation))
            }

            if (pose.rollRotation != 0.0f) {
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(pose.rollRotation))
            }

            if (offsetX != 0.0f || offsetY != 0.0f || offsetZ != 0.0f) {
                matrixStack.translate(offsetX.toDouble(), offsetY.toDouble(), offsetZ.toDouble())
            }

            matrixStack.translate(0.0, pose.anchorY.toDouble(), pose.anchorZ.toDouble())
            matrixStack.scale(wingScale, wingScale, wingScale)

            renderWingSide(matrixStack, -1.0f, open, baseColor, glowColor, coreColor, pose)
            renderWingSide(matrixStack, 1.0f, open, baseColor, glowColor, coreColor, pose)
            matrixStack.pop()
        }
    }

    private fun renderWingSide(
        matrixStack: MatrixStack,
        side: Float,
        open: Float,
        baseColor: Color4b,
        glowColor: Color4b,
        coreColor: Color4b,
        pose: WingPose
    ) {
        matrixStack.push()
        matrixStack.translate(
            (side * pose.sideOffset).toDouble(),
            pose.sideYOffset.toDouble(),
            pose.sideZOffset.toDouble()
        )
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(side * open))
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(side * pose.sideRoll))
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pose.sidePitch))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        drawWingLayer(matrixStack, side, 1.22f, glowColor.alpha((alpha * 0.22f).toInt()), glowColor.alpha(0))
        drawWingLayer(matrixStack, side, 0.84f, coreColor.alpha((alpha * 0.26f).toInt()), coreColor.alpha(0))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA)
        drawWingLayer(matrixStack, side, 1.0f, baseColor.alpha(alpha), baseColor.alpha(10))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        drawWingOutline(matrixStack, side, 1.0f, baseColor.alpha((alpha * 0.62f).toInt()))
        drawWingRibs(matrixStack, side, 0.96f, glowColor.alpha((alpha * 0.20f).toInt()))
        matrixStack.pop()
    }

    private fun drawWingLayer(
        matrixStack: MatrixStack,
        side: Float,
        scale: Float,
        rootColor: Color4b,
        edgeColor: Color4b
    ) {
        val matrix = matrixStack.peek().positionMatrix
        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR)

        SHAPE.forEachIndexed { index, current ->
            val next = SHAPE[(index + 1) % SHAPE.size]
            buffer.vertex(matrix, 0.0f, 0.0f, 0.0f).color(rootColor.toARGB())
            buffer.vertex(matrix, side * current.x * scale, current.y * scale, 0.0f)
                .color(edgeColor.applyPointAlpha(current.alphaMultiplier).toARGB())
            buffer.vertex(matrix, side * next.x * scale, next.y * scale, 0.0f)
                .color(edgeColor.applyPointAlpha(next.alphaMultiplier).toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
    }

    private fun drawWingOutline(matrixStack: MatrixStack, side: Float, scale: Float, color: Color4b) {
        val matrix = matrixStack.peek().positionMatrix
        RenderSystem.lineWidth(1.35f)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)

        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR)
        SHAPE.forEach { point ->
            buffer.vertex(matrix, side * point.x * scale, point.y * scale, 0.0f).color(color.toARGB())
        }
        SHAPE.first().also { first ->
            buffer.vertex(matrix, side * first.x * scale, first.y * scale, 0.0f).color(color.toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
    }

    private fun drawWingRibs(matrixStack: MatrixStack, side: Float, scale: Float, color: Color4b) {
        val matrix = matrixStack.peek().positionMatrix
        RenderSystem.lineWidth(0.9f)

        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR)
        RIB_INDICES.forEach { index ->
            val point = SHAPE[index]
            buffer.vertex(matrix, 0.0f, 0.0f, 0.0f)
                .color(color.alpha(max(8, (color.a * 0.75f).toInt())).toARGB())
            buffer.vertex(matrix, side * point.x * scale, point.y * scale, 0.0f)
                .color(color.applyPointAlpha(point.alphaMultiplier).toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.endNullable() ?: return)
    }

    private fun PlayerEntity.resolveBaseColor(): Color4b {
        if (customColor) {
            return color
        }

        return shiftHue(Color4b.LIQUID_BOUNCE, age * 6)
    }

    private fun PlayerEntity.resolveBodyYaw(tickDelta: Float): Float {
        val target = MathHelper.lerpAngleDegrees(tickDelta, prevBodyYaw, bodyYaw)
        if (this != mc.player) {
            return target
        }

        if (!selfBodyYawInitialized || age < 2) {
            selfBodyYaw = target
            selfBodyYawInitialized = true
            return selfBodyYaw
        }

        selfBodyYaw = approachDegrees(selfBodyYaw, target, 14.0f)
        return selfBodyYaw
    }

    private fun approachDegrees(current: Float, target: Float, maxDelta: Float): Float {
        val delta = MathHelper.wrapDegrees(target - current).coerceIn(-maxDelta, maxDelta)
        return current + delta
    }

    private fun PlayerEntity.resolvePose(tickDelta: Float): WingPose? {
        val pitch = MathHelper.lerp(tickDelta, prevPitch, pitch)

        if (isGliding) {
            val flightTicks = getGlidingTicks() + tickDelta
            val flightProgress = MathHelper.clamp(flightTicks * flightTicks / 100.0f, 0.0f, 1.0f)
            val pitchRotation = flightProgress * (-90.0f - pitch)
            return WingPose(
                preTranslateY = 0.34f,
                preTranslateZ = 0.46f,
                anchorY = 0.46f,
                anchorZ = 0.0f,
                pitchRotation = pitchRotation,
                rollRotation = 0.0f,
                openMultiplier = 0.76f,
                scaleMultiplier = 0.92f,
                motionSpreadBoost = 0.10f,
                flapAmplitude = 0.58f,
                sideOffset = 0.05f,
                sideYOffset = 0.0f,
                sideZOffset = 0.06f,
                sideRoll = -5.0f,
                sidePitch = -2.0f,
                flapSpeed = 0.13f
            )
        }

        if (isTouchingWater) {
            return null
        }

        if (isSneaking) {
            return WingPose(
                preTranslateY = 0.0f,
                preTranslateZ = 0.0f,
                anchorY = 0.96f,
                anchorZ = 0.10f,
                pitchRotation = 18.0f,
                rollRotation = 0.0f,
                openMultiplier = 1.0f,
                scaleMultiplier = 1.0f,
                motionSpreadBoost = 0.18f,
                flapAmplitude = 4.5f,
                sideOffset = 0.06f,
                sideYOffset = 0.0f,
                sideZOffset = 0.02f,
                sideRoll = -11.0f,
                sidePitch = -4.0f,
                flapSpeed = 0.12f
            )
        }

        return WingPose(
            preTranslateY = 0.0f,
            preTranslateZ = 0.0f,
            anchorY = 1.38f,
            anchorZ = 0.10f,
            pitchRotation = 0.0f,
            rollRotation = 0.0f,
            openMultiplier = 1.0f,
            scaleMultiplier = 1.0f,
            motionSpreadBoost = 0.18f,
            flapAmplitude = 4.5f,
            sideOffset = 0.06f,
            sideYOffset = 0.0f,
            sideZOffset = 0.02f,
            sideRoll = -11.0f,
            sidePitch = -4.0f,
            flapSpeed = 0.12f
        )
    }

    private fun Color4b.applyPointAlpha(multiplier: Float): Color4b {
        return alpha((a * multiplier).toInt().coerceIn(0, 255))
    }

    private data class WingPoint(val x: Float, val y: Float, val alphaMultiplier: Float)

    private data class WingPose(
        val preTranslateY: Float,
        val preTranslateZ: Float,
        val anchorY: Float,
        val anchorZ: Float,
        val pitchRotation: Float,
        val rollRotation: Float,
        val openMultiplier: Float,
        val scaleMultiplier: Float,
        val motionSpreadBoost: Float,
        val flapAmplitude: Float,
        val sideOffset: Float,
        val sideYOffset: Float,
        val sideZOffset: Float,
        val sideRoll: Float,
        val sidePitch: Float,
        val flapSpeed: Float
    )

    private val SHAPE = arrayOf(
        WingPoint(0.08f, 0.10f, 0.88f),
        WingPoint(0.28f, 0.34f, 0.78f),
        WingPoint(0.56f, 0.82f, 0.62f),
        WingPoint(0.86f, 0.30f, 0.52f),
        WingPoint(1.14f, 0.46f, 0.40f),
        WingPoint(1.24f, 0.04f, 0.30f),
        WingPoint(1.02f, -0.18f, 0.28f),
        WingPoint(1.18f, -0.64f, 0.22f),
        WingPoint(0.86f, -0.46f, 0.20f),
        WingPoint(0.80f, -0.98f, 0.14f),
        WingPoint(0.54f, -0.74f, 0.16f),
        WingPoint(0.30f, -1.16f, 0.12f),
        WingPoint(0.10f, -0.54f, 0.18f)
    )

    private val RIB_INDICES = intArrayOf(2, 4, 7, 9, 11)

    private const val DEFAULT_SPREAD = 8.0f
}
