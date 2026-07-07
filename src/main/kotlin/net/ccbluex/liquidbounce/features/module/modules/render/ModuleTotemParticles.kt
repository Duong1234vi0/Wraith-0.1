/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.multiply
import net.ccbluex.liquidbounce.utils.math.interpolate
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import org.joml.Quaternionf
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * MasEffects-style custom totem pop.
 *
 * The referenced mod catches entity status 35, hides the vanilla totem particle,
 * then spawns rotating `revive` quads plus sparks that first expand and then pull
 * back into the popped entity.
 */
@Suppress("MagicNumber")
object ModuleTotemParticles : ClientModule("TotemParticles", Category.RENDER) {

    private val opacity by float("Opacity", 1.0f, 0.0f..1.0f)
    private val hideVanilla by boolean("HideVanilla", true)

    private val particles = CopyOnWriteArrayList<MasTotemParticle>()

    @JvmStatic
    fun shouldHideVanillaParticles(): Boolean = running && hideVanilla

    @Suppress("unused")
    private val totemPopHandler = handler<TotemPopEvent> { event ->
        repeat(REVIVE_COUNT) {
            particles.add(ReviveParticle(event.entity))
        }

        val center = centerOf(event.entity)
        repeat(REVIVE_SPARK_COUNT) {
            particles.add(ReviveSparkParticle(event.entity, center))
        }
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        particles.removeIf(MasTotemParticle::tick)
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (particles.isEmpty()) {
            return@handler
        }

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableBlend()
            RenderSystem.enableDepthTest()
            RenderSystem.depthMask(false)
            RenderSystem.disableCull()
            // #Edit By Codex 2026-06-30: MasEffects renders these as normal translucent particles,
            // not SRC_ALPHA/ONE glow. Keeping the default blend avoids the over-bright clumped look.
            RenderSystem.defaultBlendFunc()

            try {
                particles.forEach { particle ->
                    particle.render(this, event.partialTicks)
                }
            } finally {
                RenderSystem.defaultBlendFunc()
                RenderSystem.depthMask(true)
                RenderSystem.enableCull()
            }
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        particles.clear()
    }

    override fun disable() {
        particles.clear()
    }

    private sealed class MasTotemParticle(
        pos: Vec3
    ) {
        var pos: Vec3 = pos
            protected set
        protected var prevPos: Vec3 = pos
        protected var age = 0
        protected abstract val lifetime: Int
        protected abstract val texture: Identifier
        protected abstract val alpha: Float
        protected abstract val color: Int

        abstract fun tick(): Boolean

        fun render(env: WorldRenderEnvironment, partialTicks: Float) {
            if (alpha <= 0.001f) {
                return
            }

            env.renderParticle(
                pos = pos.interpolate(prevPos, partialTicks.toDouble()),
                texture = texture,
                color = setAlpha(color, (alpha * 255.0f).toInt()),
                size = size(partialTicks),
                rotation = rotation(),
                billboard = billboard()
            )
        }

        protected abstract fun size(partialTicks: Float): Float
        protected open fun rotation(): Quaternionf = Quaternionf()
        protected open fun billboard(): Boolean = true
    }

    private class ReviveParticle(
        private val target: LivingEntity
    ) : MasTotemParticle(centerOf(target)) {
        private val rotX = randomAngle()
        private var rotY = randomAngle()
        private val rotZ = randomAngle()
        private var quadSize = 0.2f
        private var quadRotation = Quaternionf()

        override val lifetime = 20
        override val texture: Identifier
            get() = REVIVE_TEXTURES[((age * REVIVE_TEXTURES.size) / lifetime).coerceIn(0, REVIVE_TEXTURES.lastIndex)]
        override var alpha = 0.0f
            private set
        override val color = randomTotemColor()

        override fun tick(): Boolean {
            prevPos = pos
            pos = centerOf(target)

            if (age++ >= lifetime) {
                return true
            }

            if (opacity <= 0.0f) {
                alpha = 0.0f
                return false
            }

            rotY += 20.0
            quadRotation = euler(rotZ.toFloat(), rotX.toFloat(), -rotZ.toFloat())
                .mul(euler(0.0f, rotY.toFloat(), 0.0f))

            // #Edit By Codex 2026-06-30: Source updates scale from the previous alpha, then computes
            // the new alpha for this tick. This keeps the revive quads expanding like MasEffects.
            quadSize = alpha / opacity * REVIVE_SCALE
            alpha = Mth.clamp(
                sqrt(sin(age.toDouble() / lifetime.toDouble() * PI)).toFloat() / 1.2f,
                0.0f,
                1.0f
            ) * opacity

            return false
        }

        override fun size(partialTicks: Float): Float = quadSize

        override fun rotation(): Quaternionf = quadRotation

        override fun billboard(): Boolean = false
    }

    private class ReviveSparkParticle(
        private val target: LivingEntity,
        pos: Vec3
    ) : MasTotemParticle(pos) {
        private var velocity = Vec3(
            randomSparkVelocity(),
            randomSparkVelocity(),
            randomSparkVelocity()
        )
        private var roll = 1.0f

        override val lifetime = Random.nextInt(30, 49)
        override val texture = REVIVE_SPARK_TEXTURES.random()
        override var alpha = opacity
            private set
        override val color = randomTotemColor()

        override fun tick(): Boolean {
            prevPos = pos

            if (age++ >= lifetime) {
                return true
            }

            pos = pos.add(velocity)
            velocity = velocity.scale(0.98)

            val targetCenter = centerOf(target)
            val distanceToTarget = targetCenter.distanceTo(pos)
            val targetDir = if (distanceToTarget > 1.0E-4) {
                targetCenter.subtract(pos).normalize()
            } else {
                Vec3.ZERO
            }

            if (age.toFloat() >= lifetime / 5.0f && age.toFloat() < lifetime / 4.0f) {
                roll = 0.0f
            }

            if (age.toFloat() >= lifetime / 4.0f && distanceToTarget < SPARK_PULL_RANGE) {
                alpha = Mth.clamp(
                    1.0f - ((age.toFloat() - lifetime.toFloat() / 1.5f) / 20.0f),
                    0.0f,
                    1.0f
                ) * opacity
                roll = 1.0f
                velocity = targetDir.scale((age - 15).toDouble() * 0.05)

                if (distanceToTarget < SPARK_REMOVE_DISTANCE) {
                    alpha = 0.0f
                    return true
                }
            }

            return false
        }

        override fun size(partialTicks: Float): Float = REVIVE_SPARK_SIZE

        override fun rotation(): Quaternionf = Quaternionf().fromAxisAngleRad(0.0f, 0.0f, 1.0f, roll)
    }

    private fun WorldRenderEnvironment.renderParticle(
        pos: Vec3,
        texture: Identifier,
        color: Int,
        size: Float,
        rotation: Quaternionf,
        billboard: Boolean
    ) {
        matrixStack.pushPose()
        with(mc.gameRenderer.camera.position()) {
            matrixStack.translate(-x, -y, -z)
        }
        matrixStack.translate(pos.x, pos.y, pos.z)

        RenderSystem.setShaderTexture(0, texture)

        if (billboard) {
            matrixStack.multiply(mc.gameRenderer.camera.rotation())
            matrixStack.scale(-1.0f, 1.0f, -1.0f)
            matrixStack.multiply(rotation)
            drawQuad(size, color)
        } else {
            matrixStack.pushPose()
            matrixStack.multiply(rotation)
            drawQuad(size, color)
            matrixStack.popPose()

            matrixStack.pushPose()
            matrixStack.multiply(Quaternionf(rotation).invert())
            drawQuad(size, color)
            matrixStack.popPose()
        }

        matrixStack.popPose()
    }

    private fun WorldRenderEnvironment.drawQuad(size: Float, color: Int) {
        // #Edit By Codex 2026-06-30: Minecraft's SingleQuadParticle treats quadSize as the
        // half-extents. Dividing it here made the MasEffects revive ring about 50% too small.
        val half = size

        drawCustomMesh(
            VertexFormat.Mode.QUADS,
            DefaultVertexFormat.POSITION_TEX_COLOR,
            ShaderProgramKeys.POSITION_TEX_COLOR
        ) { matrix ->
            vertex(matrix, -half, -half, 0.0f)
                .setColor(color)
                .setUv(0.0f, 1.0f)

            vertex(matrix, half, -half, 0.0f)
                .setColor(color)
                .setUv(1.0f, 1.0f)

            vertex(matrix, half, half, 0.0f)
                .setColor(color)
                .setUv(1.0f, 0.0f)

            vertex(matrix, -half, half, 0.0f)
                .setColor(color)
                .setUv(0.0f, 0.0f)
        }
    }

    private fun centerOf(entity: LivingEntity): Vec3 {
        return Vec3(entity.x, entity.y + entity.getDimensions(entity.pose).height.toDouble() / 2.0, entity.z)
    }

    private fun euler(x: Float, y: Float, z: Float): Quaternionf {
        return Quaternionf()
            .fromAxisAngleDeg(1.0f, 0.0f, 0.0f, x)
            .mul(Quaternionf().fromAxisAngleDeg(0.0f, 1.0f, 0.0f, y))
            .mul(Quaternionf().fromAxisAngleDeg(0.0f, 0.0f, 1.0f, z))
    }

    private fun randomTotemColor(): Int {
        return if (Random.nextBoolean()) GREEN else YELLOW
    }

    private fun randomAngle(): Double = Random.nextInt(-180, 181).toDouble()

    private fun randomSparkVelocity(): Double = Random.nextInt(-10, 11).toDouble() / 25.0

    private fun setAlpha(color: Int, alpha: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or (color and 0xFFFFFF)
    }

    private val REVIVE_TEXTURES = arrayOf(
        "maseffects/particles/revive_0.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_1.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_2.png".registerAsDynamicImageFromClientResources()
    )
    private val REVIVE_SPARK_TEXTURES = arrayOf(
        "maseffects/particles/revive_spark_0.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_spark_1.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_spark_2.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_spark_3.png".registerAsDynamicImageFromClientResources(),
        "maseffects/particles/revive_spark_4.png".registerAsDynamicImageFromClientResources()
    )

    private const val REVIVE_COUNT = 17
    private const val REVIVE_SPARK_COUNT = 99
    private const val REVIVE_SCALE = 2.5f
    private const val REVIVE_SPARK_SIZE = 0.1f
    private const val SPARK_PULL_RANGE = 20.0
    private const val SPARK_REMOVE_DISTANCE = 2.0
    private const val GREEN = 0x00FF00
    private const val YELLOW = 0xFFFF00
}
