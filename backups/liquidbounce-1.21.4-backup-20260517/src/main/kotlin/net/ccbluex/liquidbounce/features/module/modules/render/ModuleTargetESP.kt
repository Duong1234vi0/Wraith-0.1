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
import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.render.BufferBuilder
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import org.joml.Vector3f
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

/**
 * Target ESP with source-specific modes.
 *
 * - Dreamcore keeps the accepted three ghost/tail look.
 * - Rich modes are ported as independent choices, so their settings do not pollute Dreamcore.
 */
@Suppress("MagicNumber")
object ModuleTargetESP : ClientModule("TargetESP", Category.RENDER) {

    private val dreamcoreGlow = "dreamcore/glow.png".registerAsDynamicImageFromClientResources()
    private val richBloom = "rich/particles/bloom.png".registerAsDynamicImageFromClientResources()
    private val richCaptures = Array(5) { index ->
        "rich/targetesp/capture${index + 1}.png".registerAsDynamicImageFromClientResources()
    }

    private val modes = choices("Mode", 0) {
        arrayOf(
            DreamcoreMode(it),
            RichCircleMode(it),
            RichGhostsMode(it),
            RichCubeMode(it)
        )
    }

    override fun disable() {
        modes.choices.forEach(TargetEspMode::reset)
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val target = ModuleKillAura.targetTracker.target as? LivingEntity ?: run {
            modes.activeChoice.onNoTarget()
            return@handler
        }

        renderEnvironmentForWorld(event.matrixStack) {
            modes.activeChoice.render(this, event, target)
        }
    }

    private abstract class TargetEspMode(
        name: String,
        override val parent: ChoiceConfigurable<*>
    ) : Choice(name) {
        open fun reset() {}
        open fun onNoTarget() {}
        abstract fun render(env: WorldRenderEnvironment, event: WorldRenderEvent, target: LivingEntity)
    }

    private class DreamcoreMode(parent: ChoiceConfigurable<*>) : TargetEspMode("Dreamcore", parent) {

        private companion object {
            const val MAX_PARTICLES = 3
            const val BASE_SIZE = 0.5F
            const val BASE_MUL = 0.05F
            const val TAIL_LENGTH = 30.0F
            const val ANIMATION_SPEED = 300L
        }

        private val color by color("Color", Color4b.LIQUID_BOUNCE)
        private val size by float("Size", BASE_SIZE, 0.2F..1.0F)
        private val speed by float("Speed", 0.6F, 0.1F..2.0F)
        private val opacity by int("Opacity", 70, 10..100, "%")
        private val tailQuality by int("TailQuality", 55, 20..100, "%")

        private val particles = mutableListOf<GhostParticle>()
        private val glowBillboards = ArrayList<GlowBillboard>(128)

        private var movingAngle = 0.0F
        private var lastFrameTime = System.currentTimeMillis()
        private var hitForward = false
        private var hitAnimationStart = System.currentTimeMillis()
        private var hitAnimationStartValue = 0.0F
        private var hitAnimationValue = 0.0F

        override fun reset() {
            particles.clear()
            glowBillboards.clear()
            hitAnimationValue = 0.0F
            hitAnimationStartValue = 0.0F
            hitForward = false
        }

        override fun render(env: WorldRenderEnvironment, event: WorldRenderEvent, target: LivingEntity) {
            while (particles.size < MAX_PARTICLES) {
                particles.add(GhostParticle(target.interpolateCurrentPosition(event.partialTicks), Vec3d.ZERO))
            }

            val fps = max(mc.currentFps, 5)
            val fpsFactor = 500.0F / fps
            val now = System.currentTimeMillis()
            val frameScale = ((now - lastFrameTime).coerceAtMost(100) / 16.6667F).coerceAtLeast(0.1F)
            lastFrameTime = now

            movingAngle += 20.0F * speed * frameScale
            val hitAnimation = updateHitAnimation(target.hurtTime > 7)

            RenderSystem.depthMask(false)
            RenderSystem.disableCull()
            mc.gameRenderer.lightmapTextureManager.disable()
            RenderSystem.blendFuncSeparate(
                GlStateManager.SrcFactor.SRC_ALPHA,
                GlStateManager.DstFactor.ONE,
                GlStateManager.SrcFactor.ZERO,
                GlStateManager.DstFactor.ONE
            )
            RenderSystem.setShaderTexture(0, dreamcoreGlow)

            glowBillboards.clear()
            particles.forEachIndexed { index, particle ->
                updateParticlePosition(target, event.partialTicks, particle, index, hitAnimation, fpsFactor)
                particle.tick(fps, color, size, opacity, tailQuality, glowBillboards)
            }
            env.drawGlowBillboards(glowBillboards)

            RenderSystem.depthMask(true)
            RenderSystem.defaultBlendFunc()
            mc.gameRenderer.lightmapTextureManager.enable()
            RenderSystem.enableCull()
        }

        private fun updateParticlePosition(
            target: LivingEntity,
            tickDelta: Float,
            particle: GhostParticle,
            particleIndex: Int,
            hitAnimation: Float,
            fpsFactor: Float
        ) {
            val angleOffset = particleIndex * 360.0F / MAX_PARTICLES
            val currentAngle = movingAngle + angleOffset
            val radian = Math.toRadians(currentAngle.toDouble())
            val orbitRadius = 0.3F - hitAnimation * 0.3F

            val x = sin(radian) * orbitRadius
            val z = cos(radian) * orbitRadius
            val y = 0.2 + target.height / 2.0 * sin(Math.toRadians((movingAngle / (particleIndex + 1.0F)).toDouble()))
            val targetPos = target.interpolateCurrentPosition(tickDelta).add(x, y, z)

            val mul = BASE_MUL * fpsFactor * speed
            particle.motion = targetPos.subtract(particle.position).multiply(mul.toDouble())
        }

        private fun updateHitAnimation(forward: Boolean): Float {
            val now = System.currentTimeMillis()

            if (hitForward != forward) {
                hitForward = forward
                hitAnimationStartValue = hitAnimationValue
                hitAnimationStart = now
            }

            val progress = ((now - hitAnimationStart).toDouble() / ANIMATION_SPEED).coerceIn(0.0, 1.0)
            val eased = targetEspEaseOutBack(progress).toFloat()

            hitAnimationValue = if (hitForward) {
                hitAnimationStartValue + (1.0F - hitAnimationStartValue) * eased
            } else {
                hitAnimationStartValue * (1.0F - eased)
            }

            return hitAnimationValue
        }

        private fun targetEspEaseOutBack(x: Double): Double {
            return 1.0 + 3.70158 * (x - 1.0) * (x - 1.0) * (x - 1.0) +
                2.70158 * (x - 1.0) * (x - 1.0)
        }

        private class GhostParticle(
            var position: Vec3d,
            var motion: Vec3d,
            var alpha: Float = 1.0F
        ) {
            private val tail = mutableListOf<TailPoint>()

            fun tick(
                fps: Int,
                baseColor: Color4b,
                size: Float,
                opacity: Int,
                tailQuality: Int,
                out: MutableList<GlowBillboard>
            ) {
                val prevPosition = position
                position = position.add(motion)

                val distance = prevPosition.distanceTo(position)
                val sampleDistance = 0.09 - tailQuality * 0.0005
                val maxSamples = (2 + tailQuality / 20).coerceIn(2, 7)
                val maxTailPoints = (24 + tailQuality * 1.1F).toInt().coerceIn(36, 140)
                val samples = MathHelper.ceil(distance / sampleDistance).coerceIn(1, maxSamples)

                repeat(samples) { sample ->
                    val progress = (sample + 1.0) / samples
                    val x = prevPosition.x + (position.x - prevPosition.x) * progress
                    val y = prevPosition.y + (position.y - prevPosition.y) * progress
                    val z = prevPosition.z + (position.z - prevPosition.z) * progress

                    tail.add(TailPoint(x, y + 0.7, z, TAIL_LENGTH))
                }

                while (tail.size > maxTailPoints) {
                    tail.removeAt(0)
                }

                tail.removeIf { point ->
                    if (point.life <= 0.0F) {
                        return@removeIf true
                    }

                    val miniSize = size * point.life / TAIL_LENGTH
                    val alpha = ((point.life / TAIL_LENGTH * alpha) * baseColor.a * opacity / 100.0F).toInt()
                    val renderColor = baseColor.alpha(MathHelper.clamp(alpha, 0, baseColor.a))

                    out.add(GlowBillboard(point.x, point.y, point.z, miniSize, renderColor.toARGB()))

                    point.y += 0.004F / fps * 300.0F
                    point.life -= 0.3F / fps * 300.0F

                    return@removeIf point.life <= 0.0F
                }
            }
        }

        private data class TailPoint(
            val x: Double,
            var y: Double,
            val z: Double,
            var life: Float
        )
    }

    private class RichCircleMode(parent: ChoiceConfigurable<*>) : TargetEspMode("RichCircle", parent) {
        private val color by color("Color", Color4b(255, 101, 57, 255))
        private val speed by float("Speed", 1.0F, 0.3F..2.0F)
        private val opacity by int("Opacity", 80, 10..100, "%")
        private val segments by int("Segments", 56, 32..96)

        override fun render(env: WorldRenderEnvironment, event: WorldRenderEvent, target: LivingEntity) {
            val pos = target.interpolateCurrentPosition(event.partialTicks)
            val red = target.hurtRed(event.partialTicks)
            val animStep = (mc.player?.age ?: 0) + event.partialTicks
            val step = animStep * 0.15F * speed
            val hitEffect = (red * 2.0F).coerceAtMost(1.0F)
            val distanceMultiplier = 1.0F + sin(hitEffect * PI).toFloat() * 0.18F
            val width = target.width * distanceMultiplier
            val color = color.mixRed(red).alpha(color.a * opacity / 100)

            RenderSystem.disableCull()
            RenderSystem.depthMask(false)
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)

            env.drawCustomMesh(
                VertexFormat.DrawMode.TRIANGLE_STRIP,
                VertexFormats.POSITION_COLOR,
                ShaderProgramKeys.POSITION_COLOR
            ) { matrix ->
                for (i in 0..segments) {
                    val angle = i.toDouble() / segments * PI * 2.0
                    val x = cos(angle) * width
                    val z = sin(angle) * width
                    val y1 = 0.15 + absSin(step) * target.height
                    val y2 = 0.15 + absSin(step - 0.45F) * target.height

                    val start = pos.add(x, y1, z)
                    val end = pos.add(x, y2, z)
                    val startColor = color.alpha((color.a * 0.72F).toInt())
                    val endColor = color.alpha(0)

                    vertexWorld(env, matrix, start).color(startColor.toARGB())
                    vertexWorld(env, matrix, end).color(endColor.toARGB())
                }
            }

            env.drawCustomMesh(
                VertexFormat.DrawMode.DEBUG_LINES,
                VertexFormats.POSITION_COLOR,
                ShaderProgramKeys.POSITION_COLOR
            ) { matrix ->
                for (i in 0 until segments) {
                    val angle = i.toDouble() / segments * PI * 2.0
                    val nextAngle = (i + 1).toDouble() / segments * PI * 2.0
                    val y = 0.15 + absSin(step) * target.height
                    val start = pos.add(cos(angle) * width, y, sin(angle) * width)
                    val end = pos.add(cos(nextAngle) * width, y, sin(nextAngle) * width)

                    vertexWorld(env, matrix, start).color(color.toARGB())
                    vertexWorld(env, matrix, end).color(color.toARGB())
                }
            }

            RenderSystem.defaultBlendFunc()
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
        }
    }

    private class RichGhostsMode(parent: ChoiceConfigurable<*>) : TargetEspMode("RichGhosts", parent) {
        private val color by color("Color", Color4b(255, 101, 57, 255))
        private val speed by float("Speed", 0.62F, 0.2F..1.5F)
        private val size by float("Size", 0.6F, 0.25F..1.0F)
        private val opacity by int("Opacity", 75, 10..100, "%")

        private val billboards = ArrayList<GlowBillboard>(48)

        override fun render(env: WorldRenderEnvironment, event: WorldRenderEvent, target: LivingEntity) {
            val pos = target.interpolateCurrentPosition(event.partialTicks)
            val red = target.hurtRed(event.partialTicks)
            val color = color.mixRed(red)
            val age = (mc.player?.age ?: 0) + event.partialTicks
            val halfHeight = target.height / 2.0F + 0.2F
            val baseWidth = target.width + 0.2F
            val minY = 0.2F
            val maxY = target.height - 0.2F
            val hitEffect = (red * 2.0F).coerceAtMost(2.0F)
            val acceleration = sin(hitEffect * PI).toFloat() * 0.18F
            val bounce = sin(hitEffect * PI).toFloat() * -0.04F

            billboards.clear()
            repeat(4) { layer ->
                val length = 10
                for (i in 0..length) {
                    val baseAngle = ((i / 2.0F + age * speed * 2.0F) * length + layer * 90.0F) %
                        (length * 180.0F)
                    val radians = Math.toRadians(baseAngle.toDouble())
                    val sinQuad = sin(Math.toRadians((age * 0.7F + i * (layer + halfHeight)).toDouble()) * 1.1) / 2.0
                    val adjustedSin = if (layer % 2 == 0) sinQuad else -sinQuad
                    val yOffset = minY + (adjustedSin + 0.5) * (maxY - minY)
                    val offset = (i + length).toFloat() / (length + length)
                    val finalWidth = baseWidth * (1.0F + acceleration) * 1.04F
                    val scale = size * offset * (0.6F + speed * 0.1F) + bounce
                    val alpha = (color.a * offset * opacity / 100.0F).toInt()

                    billboards.add(
                        GlowBillboard(
                            pos.x + cos(radians) * finalWidth,
                            pos.y + yOffset,
                            pos.z + sin(radians) * finalWidth,
                            scale.coerceAtLeast(0.04F),
                            color.alpha(MathHelper.clamp(alpha, 0, color.a)).toARGB()
                        )
                    )
                }
            }

            RenderSystem.depthMask(false)
            RenderSystem.disableCull()
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
            RenderSystem.setShaderTexture(0, richBloom)
            env.drawGlowBillboards(billboards)
            RenderSystem.defaultBlendFunc()
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
        }
    }

    private class RichCubeMode(parent: ChoiceConfigurable<*>) : TargetEspMode("RichCube", parent) {
        private val color by color("Color", Color4b(255, 101, 57, 255))
        private val capture by int("Capture", 1, 1..5)
        private val size by float("Size", 0.82F, 0.35F..1.35F)
        private val speed by float("SpinSpeed", 1.0F, 0.2F..2.0F)
        private val opacity by int("Opacity", 85, 10..100, "%")

        private var spin = 0.0F
        private var lastFrameTime = System.currentTimeMillis()

        override fun reset() {
            spin = 0.0F
            lastFrameTime = System.currentTimeMillis()
        }

        override fun render(env: WorldRenderEnvironment, event: WorldRenderEvent, target: LivingEntity) {
            val now = System.currentTimeMillis()
            val frameScale = ((now - lastFrameTime).coerceAtMost(100) / 16.6667F).coerceAtLeast(0.1F)
            lastFrameTime = now
            spin = (spin + 2.8F * speed * frameScale) % 360.0F

            val red = target.hurtRed(event.partialTicks)
            val color = color.mixRed(red).alpha(color.a * opacity / 100)
            val pos = target.interpolateCurrentPosition(event.partialTicks)
                .add(0.0, target.boundingBox.lengthY / 2.0, 0.0)

            RenderSystem.depthMask(false)
            RenderSystem.disableCull()
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
            RenderSystem.setShaderTexture(0, richCaptures[(capture - 1).coerceIn(0, richCaptures.lastIndex)])
            env.drawTexturedBillboard(pos, size * (1.0F + red * 0.12F), color.toARGB(), spin)
            RenderSystem.defaultBlendFunc()
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
        }
    }

    private data class GlowBillboard(
        val x: Double,
        val y: Double,
        val z: Double,
        val size: Float,
        val argb: Int
    )

    private fun LivingEntity.hurtRed(tickDelta: Float): Float {
        return MathHelper.clamp((hurtTime - tickDelta) / 20.0F, 0.0F, 1.0F)
    }

    private fun Color4b.mixRed(amount: Float): Color4b {
        val factor = amount.coerceIn(0.0F, 1.0F)
        return Color4b(
            MathHelper.lerp(factor, r.toFloat(), 255.0F).toInt().coerceIn(0, 255),
            MathHelper.lerp(factor, g.toFloat(), 0.0F).toInt().coerceIn(0, 255),
            MathHelper.lerp(factor, b.toFloat(), 0.0F).toInt().coerceIn(0, 255),
            a
        )
    }

    private fun absSin(value: Float): Double {
        return kotlin.math.abs(sin(value.toDouble()))
    }

    private fun WorldRenderEnvironment.drawGlowBillboards(points: List<GlowBillboard>) {
        if (points.isEmpty()) {
            return
        }

        val axes = billboardAxes()

        drawCustomMesh(
            VertexFormat.DrawMode.QUADS,
            VertexFormats.POSITION_TEXTURE_COLOR,
            ShaderProgramKeys.POSITION_TEX_COLOR
        ) { matrix ->
            points.forEach { point ->
                drawBillboardQuad(
                    env = this@drawGlowBillboards,
                    matrix = matrix,
                    center = Vec3d(point.x, point.y, point.z),
                    size = point.size,
                    argb = point.argb,
                    axes = axes
                )
            }
        }
    }

    private fun WorldRenderEnvironment.drawTexturedBillboard(pos: Vec3d, size: Float, argb: Int, rollDegrees: Float) {
        val axes = billboardAxes(rollDegrees)

        drawCustomMesh(
            VertexFormat.DrawMode.QUADS,
            VertexFormats.POSITION_TEXTURE_COLOR,
            ShaderProgramKeys.POSITION_TEX_COLOR
        ) { matrix ->
            drawBillboardQuad(this@drawTexturedBillboard, matrix, pos, size, argb, axes)
        }
    }

    private fun WorldRenderEnvironment.billboardAxes(rollDegrees: Float = 0.0F): Pair<Vector3f, Vector3f> {
        val rotation = mc.gameRenderer.camera.rotation
        val cameraRight = Vector3f(1.0F, 0.0F, 0.0F).rotate(rotation)
        val cameraUp = Vector3f(0.0F, 1.0F, 0.0F).rotate(rotation)

        if (rollDegrees == 0.0F) {
            return cameraRight to cameraUp
        }

        val roll = Math.toRadians(rollDegrees.toDouble())
        val cos = cos(roll).toFloat()
        val sin = sin(roll).toFloat()
        val right = Vector3f(
            cameraRight.x * cos + cameraUp.x * sin,
            cameraRight.y * cos + cameraUp.y * sin,
            cameraRight.z * cos + cameraUp.z * sin
        )
        val up = Vector3f(
            cameraUp.x * cos - cameraRight.x * sin,
            cameraUp.y * cos - cameraRight.y * sin,
            cameraUp.z * cos - cameraRight.z * sin
        )

        return right to up
    }

    private fun BufferBuilder.drawBillboardQuad(
        env: WorldRenderEnvironment,
        matrix: org.joml.Matrix4f,
        center: Vec3d,
        size: Float,
        argb: Int,
        axes: Pair<Vector3f, Vector3f>
    ) {
        val cameraPos = env.camera.pos
        val centerX = (center.x - cameraPos.x).toFloat()
        val centerY = (center.y - cameraPos.y).toFloat()
        val centerZ = (center.z - cameraPos.z).toFloat()
        val halfSize = size / 2.0F
        val right = axes.first
        val up = axes.second

        val rightX = right.x * halfSize
        val rightY = right.y * halfSize
        val rightZ = right.z * halfSize
        val upX = up.x * halfSize
        val upY = up.y * halfSize
        val upZ = up.z * halfSize

        vertex(matrix, centerX + rightX + upX, centerY + rightY + upY, centerZ + rightZ + upZ)
            .texture(0.0F, 1.0F)
            .color(argb)

        vertex(matrix, centerX - rightX + upX, centerY - rightY + upY, centerZ - rightZ + upZ)
            .texture(1.0F, 1.0F)
            .color(argb)

        vertex(matrix, centerX - rightX - upX, centerY - rightY - upY, centerZ - rightZ - upZ)
            .texture(1.0F, 0.0F)
            .color(argb)

        vertex(matrix, centerX + rightX - upX, centerY + rightY - upY, centerZ + rightZ - upZ)
            .texture(0.0F, 0.0F)
            .color(argb)
    }

    private fun BufferBuilder.vertexWorld(
        env: WorldRenderEnvironment,
        matrix: org.joml.Matrix4f,
        pos: Vec3d
    ) = vertex(
        matrix,
        (pos.x - env.camera.pos.x).toFloat(),
        (pos.y - env.camera.pos.y).toFloat(),
        (pos.z - env.camera.pos.z).toFloat()
    )

}
