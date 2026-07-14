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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.render.compat.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.interfaces.EntityRenderStateAddition
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.utils.shiftHue
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import com.mojang.blaze3d.vertex.BufferBuilder
import net.ccbluex.liquidbounce.render.compat.BufferRenderer
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.util.Mth
import com.mojang.math.Axis
import org.joml.Matrix4f
import org.lwjgl.opengl.GL11
import kotlin.math.max
import kotlin.math.sin

/**
 * EvaWare Wings port.
 */
@Suppress("MagicNumber")
object ModuleWings : ClientModule("Wings", Category.RENDER) {

    private val modeValue = enumChoice("Mode", WingMode.EVAWARE).onChanged {
        if (it == WingMode.XYETA) {
            resetXyetaAnimation()
        }
    }
    private val mode by modeValue

    private val self by boolean("Self", true).visibleWhen { isEvaWareMode }
    private val players by boolean("Players", false).visibleWhen { isEvaWareMode }
    private val size by float("Size", 1.0f, 0.75f..1.35f).visibleWhen { isEvaWareMode }
    private val alpha by int("Alpha", 220, 60..255).visibleWhen { isEvaWareMode }
    private val offsetX by float("OffsetX", 0.0f, -1.5f..1.5f).visibleWhen { isEvaWareMode }
    private val offsetY by float("OffsetY", 0.0f, -1.5f..1.5f).visibleWhen { isEvaWareMode }
    private val offsetZ by float("OffsetZ", 0.0f, -1.5f..1.5f).visibleWhen { isEvaWareMode }
    private val customColor by boolean("CustomColor", false).visibleWhen { isEvaWareMode }
    private val color by color("Color", Color4b(255, 100, 255)).visibleWhen { isEvaWareMode }
    private val xyetaTexture by enumChoice("Texture", XyetaTexture.TYPE1).visibleWhen { isXyetaMode }

    private var selfBodyYaw = 0.0f
    private var selfBodyYawInitialized = false
    private val xyetaAnimation = XyetaWingAnimation()

    private val isEvaWareMode: Boolean
        get() = mode == WingMode.EVAWARE

    private val isXyetaMode: Boolean
        get() = mode == WingMode.XYETA

    override fun enable() {
        selfBodyYawInitialized = false
        resetXyetaAnimation()
    }

    override fun disable() {
        selfBodyYawInitialized = false
        resetXyetaAnimation()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (isXyetaMode) {
            xyetaAnimation.updatePingPong()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        selfBodyYawInitialized = false
        resetXyetaAnimation()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (!isEvaWareMode) {
            return@handler
        }

        val localPlayer = mc.player ?: return@handler
        val world = mc.level ?: return@handler

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
                    world.players().forEach { player ->
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

    private fun Player.canRenderWings(): Boolean {
        return isAlive && !isRemoved && !getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA)
    }

    private fun WorldRenderEnvironment.renderWings(player: Player, tickDelta: Float) {
        val pose = player.resolvePose(tickDelta) ?: return
        val bodyYaw = player.resolveBodyYaw(tickDelta)
        val movement = Mth.clamp(player.walkAnimation.speed(tickDelta), 0.0f, 1.0f)
        val flap = sin((player.age + tickDelta) * pose.flapSpeed) * pose.flapAmplitude
        val open = (DEFAULT_SPREAD + flap + movement * pose.motionSpreadBoost) * pose.openMultiplier
        val wingScale = size * pose.scaleMultiplier
        val baseColor = player.resolveBaseColor()
        val glowColor = baseColor.interpolateTo(Color4b.WHITE, 0.28)
        val coreColor = baseColor.interpolateTo(Color4b.WHITE, 0.55)

        withPositionRelativeToCamera(player.interpolateCurrentPosition(tickDelta)) {
            matrixStack.pushPose()
            matrixStack.multiply(Axis.YP.rotationDegrees(180.0f - bodyYaw))

            if (pose.preTranslateY != 0.0f || pose.preTranslateZ != 0.0f) {
                matrixStack.translate(0.0, pose.preTranslateY.toDouble(), pose.preTranslateZ.toDouble())
            }

            if (pose.pitchRotation != 0.0f) {
                matrixStack.multiply(Axis.XP.rotationDegrees(pose.pitchRotation))
            }

            if (pose.rollRotation != 0.0f) {
                matrixStack.multiply(Axis.ZP.rotationDegrees(pose.rollRotation))
            }

            if (offsetX != 0.0f || offsetY != 0.0f || offsetZ != 0.0f) {
                matrixStack.translate(offsetX.toDouble(), offsetY.toDouble(), offsetZ.toDouble())
            }

            matrixStack.translate(0.0, pose.anchorY.toDouble(), pose.anchorZ.toDouble())
            matrixStack.scale(wingScale, wingScale, wingScale)

            renderWingSide(matrixStack, -1.0f, open, baseColor, glowColor, coreColor, pose)
            renderWingSide(matrixStack, 1.0f, open, baseColor, glowColor, coreColor, pose)
            matrixStack.popPose()
        }
    }

    private fun renderWingSide(
        matrixStack: PoseStack,
        side: Float,
        open: Float,
        baseColor: Color4b,
        glowColor: Color4b,
        coreColor: Color4b,
        pose: WingPose
    ) {
        matrixStack.pushPose()
        matrixStack.translate(
            (side * pose.sideOffset).toDouble(),
            pose.sideYOffset.toDouble(),
            pose.sideZOffset.toDouble()
        )
        matrixStack.multiply(Axis.YP.rotationDegrees(side * open))
        matrixStack.multiply(Axis.ZP.rotationDegrees(side * pose.sideRoll))
        matrixStack.multiply(Axis.XP.rotationDegrees(pose.sidePitch))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        drawWingLayer(matrixStack, side, 1.22f, glowColor.alpha((alpha * 0.22f).toInt()), glowColor.alpha(0))
        drawWingLayer(matrixStack, side, 0.84f, coreColor.alpha((alpha * 0.26f).toInt()), coreColor.alpha(0))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA)
        drawWingLayer(matrixStack, side, 1.0f, baseColor.alpha(alpha), baseColor.alpha(10))

        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
        drawWingOutline(matrixStack, side, 1.0f, baseColor.alpha((alpha * 0.62f).toInt()))
        drawWingRibs(matrixStack, side, 0.96f, glowColor.alpha((alpha * 0.20f).toInt()))
        matrixStack.popPose()
    }

    private fun drawWingLayer(
        matrixStack: PoseStack,
        side: Float,
        scale: Float,
        rootColor: Color4b,
        edgeColor: Color4b
    ) {
        val matrix = matrixStack.last().pose()
        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR)

        SHAPE.forEachIndexed { index, current ->
            val next = SHAPE[(index + 1) % SHAPE.size]
            buffer.addVertex(matrix, 0.0f, 0.0f, 0.0f).setColor(rootColor.toARGB())
            buffer.addVertex(matrix, side * current.x * scale, current.y * scale, 0.0f)
                .setColor(edgeColor.applyPointAlpha(current.alphaMultiplier).toARGB())
            buffer.addVertex(matrix, side * next.x * scale, next.y * scale, 0.0f)
                .setColor(edgeColor.applyPointAlpha(next.alphaMultiplier).toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.build() ?: return)
    }

    private fun drawWingOutline(matrixStack: PoseStack, side: Float, scale: Float, color: Color4b) {
        val matrix = matrixStack.last().pose()
        RenderSystem.lineWidth(1.35f)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)

        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR)
        SHAPE.forEach { point ->
            buffer.addVertex(matrix, side * point.x * scale, point.y * scale, 0.0f).setColor(color.toARGB())
        }
        SHAPE.first().also { first ->
            buffer.addVertex(matrix, side * first.x * scale, first.y * scale, 0.0f).setColor(color.toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.build() ?: return)
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
    }

    private fun drawWingRibs(matrixStack: PoseStack, side: Float, scale: Float, color: Color4b) {
        val matrix = matrixStack.last().pose()
        RenderSystem.lineWidth(0.9f)

        val buffer = RenderSystem.renderThreadTesselator()
            .begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR)
        RIB_INDICES.forEach { index ->
            val point = SHAPE[index]
            buffer.addVertex(matrix, 0.0f, 0.0f, 0.0f)
                .setColor(color.alpha(max(8, (color.a * 0.75f).toInt())).toARGB())
            buffer.addVertex(matrix, side * point.x * scale, point.y * scale, 0.0f)
                .setColor(color.applyPointAlpha(point.alphaMultiplier).toARGB())
        }

        BufferRenderer.drawWithGlobalProgram(buffer.build() ?: return)
    }

    private fun Player.resolveBaseColor(): Color4b {
        if (customColor) {
            return color
        }

        return shiftHue(Color4b.LIQUID_BOUNCE, age * 6)
    }

    @JvmStatic
    fun shouldRenderXyeta(state: HumanoidRenderState?): Boolean {
        if (!running || mode != WingMode.XYETA || state !is EntityRenderStateAddition) {
            return false
        }

        return state.`liquid_bounce$getEntity`() === mc.player
    }

    @JvmStatic
    fun selectedXyetaTexture(): Identifier = xyetaTexture.texture

    @JvmStatic
    fun applyXyetaAngles(state: HumanoidRenderState, leftWing: ModelPart, rightWing: ModelPart) {
        val animationOutput = xyetaAnimation.output().toFloat()

        leftWing.y = if (state.isCrouching) 3.0f else 0.0f
        leftWing.xRot = state.elytraRotX - (0.2f + 0.6f * animationOutput)
        leftWing.zRot = state.elytraRotZ - 1.4f
        leftWing.yRot = state.elytraRotY - 0.1f

        rightWing.yRot = -leftWing.yRot
        rightWing.y = leftWing.y
        rightWing.xRot = leftWing.xRot
        rightWing.zRot = -leftWing.zRot
    }

    private fun resetXyetaAnimation() {
        xyetaAnimation.reset()
    }

    private fun Player.resolveBodyYaw(tickDelta: Float): Float {
        val target = Mth.rotLerp(tickDelta, yBodyRotO, yBodyRot)
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
        val delta = Mth.wrapDegrees(target - current).coerceIn(-maxDelta, maxDelta)
        return current + delta
    }

    private fun Player.resolvePose(tickDelta: Float): WingPose? {
        val pitch = Mth.lerp(tickDelta, prevPitch, pitch)

        if (isGliding) {
            val flightTicks = fallFlyingTicks + tickDelta
            val flightProgress = Mth.clamp(flightTicks * flightTicks / 100.0f, 0.0f, 1.0f)
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

    private enum class WingMode(override val choiceName: String) : NamedChoice {
        EVAWARE("EvaWare"),
        XYETA("Xyeta")
    }

    private enum class XyetaTexture(override val choiceName: String, val texture: Identifier) : NamedChoice {
        TYPE1("Type1", "wings/xyeta/type1.png".registerAsDynamicImageFromClientResources()),
        TYPE2("Type2", "wings/xyeta/type2.png".registerAsDynamicImageFromClientResources()),
        TYPE3("Type3", "wings/xyeta/type3.png".registerAsDynamicImageFromClientResources())
    }

    private enum class XyetaAnimationDirection {
        FORWARDS,
        BACKWARDS
    }

    private class XyetaWingAnimation {
        private var direction = XyetaAnimationDirection.FORWARDS
        private var startMs = System.currentTimeMillis()

        fun reset() {
            direction = XyetaAnimationDirection.FORWARDS
            startMs = System.currentTimeMillis()
        }

        fun output(): Double {
            val elapsed = elapsedMs()
            return when (direction) {
                XyetaAnimationDirection.FORWARDS -> {
                    if (elapsed >= DURATION_MS) ENDPOINT else equation(elapsed.toDouble()) * ENDPOINT
                }
                XyetaAnimationDirection.BACKWARDS -> {
                    if (elapsed >= DURATION_MS) {
                        0.0
                    } else {
                        equation((DURATION_MS - elapsed).toDouble()) * ENDPOINT
                    }
                }
            }
        }

        fun updatePingPong() {
            if (finished(XyetaAnimationDirection.FORWARDS)) {
                setDirection(XyetaAnimationDirection.BACKWARDS)
            } else if (finished(XyetaAnimationDirection.BACKWARDS)) {
                setDirection(XyetaAnimationDirection.FORWARDS)
            }
        }

        private fun finished(expected: XyetaAnimationDirection): Boolean {
            return direction == expected && elapsedMs() >= DURATION_MS
        }

        private fun setDirection(next: XyetaAnimationDirection) {
            if (direction == next) {
                return
            }

            val elapsed = elapsedMs().coerceAtMost(DURATION_MS)
            direction = next
            startMs = System.currentTimeMillis() - (DURATION_MS - elapsed)
        }

        private fun elapsedMs(): Long {
            return System.currentTimeMillis() - startMs
        }

        private fun equation(x: Double): Double {
            val x1 = x / DURATION_MS.toDouble()
            val shrink = EASE_AMOUNT + 1.0
            val offset = x1 - 1.0
            return max(0.0, 1.0 + shrink * offset * offset * offset + EASE_AMOUNT * offset * offset)
        }

        private companion object {
            const val DURATION_MS = 550L
            const val ENDPOINT = 0.5
            const val EASE_AMOUNT = 0.001
        }
    }
}
