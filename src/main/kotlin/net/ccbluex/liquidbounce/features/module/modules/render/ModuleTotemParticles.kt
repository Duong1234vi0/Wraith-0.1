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

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.systems.RenderSystem
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer.FakePlayer
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.Camera
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SingleQuadParticle
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.state.level.QuadParticleRenderState
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.model.sprite.SpriteId
import net.minecraft.resources.Identifier
import net.minecraft.util.ARGB
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import org.joml.Quaternionf
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * StichDLC's TotemParticles module is just the bundled Mas Effects revive-particle system.
 *
 * Real status-35 totem pops are handled from the vanilla totem emitter call in
 * [net.ccbluex.liquidbounce.injection.mixins.minecraft.network.MixinClientPlayNetworkHandler].
 * FakePlayer has no network status packet, so it still enters through [TotemPopEvent].
 */
@Suppress("MagicNumber")
object ModuleTotemParticles : ClientModule("TotemParticles", Category.RENDER) {

    @Suppress("unused")
    private val fakePlayerTotemPopHandler = handler<TotemPopEvent> { event ->
        if (event.entity is FakePlayer) {
            spawnMasEffect(event.entity)
        }
    }

    /**
     * Called from the vanilla status-35 particle emitter interception.
     *
     * @return true when the source Mas Effects particles were spawned and the vanilla emitter should
     * be suppressed; false lets vanilla behavior continue unchanged.
     */
    @JvmStatic
    fun replaceVanillaTotemEmitter(entity: Entity): Boolean {
        if (!running) {
            return false
        }

        val livingEntity = entity as? LivingEntity ?: return false
        return spawnMasEffect(livingEntity)
    }

    /**
     * Used by FakePlayer before it creates the synthetic vanilla emitter.
     */
    @JvmStatic
    fun shouldSuppressVanillaTotemEmitter(entity: Entity): Boolean {
        return running && entity is LivingEntity && MasEffectSprites.available()
    }

    @JvmStatic
    fun precompile() {
        runCatching {
            RenderSystem.getDevice().precompilePipeline(REVIVE_NO_CULL_PIPELINE)
        }.onFailure { throwable ->
            LiquidBounce.logger.error("Failed to precompile Mas Effects revive particle pipeline", throwable)
        }
    }

    private fun spawnMasEffect(entity: LivingEntity): Boolean {
        if (!running) {
            return false
        }

        val level = mc.level ?: return false
        if (entity.level() !== level || entity.isRemoved) {
            return false
        }

        val sprites = MasEffectSprites.resolve() ?: return false
        val x = entity.x
        val y = entity.y + entity.bbHeight.toDouble() * 0.5
        val z = entity.z

        repeat(REVIVE_COUNT) {
            mc.particleEngine.add(ReviveParticle(level, x, y, z, REVIVE_SCALE, entity, sprites.revive))
        }

        repeat(REVIVE_SPARK_COUNT) {
            mc.particleEngine.add(ReviveSparkParticle(level, x, y, z, entity, sprites.spark))
        }

        return true
    }

    private class ReviveParticle(
        level: ClientLevel,
        x: Double,
        y: Double,
        z: Double,
        private val scaler: Float,
        private var target: LivingEntity?,
        private val sprites: SpriteSet
    ) : SingleQuadParticle(level, x, y, z, sprites.first()) {

        private val rotX = random.nextInt(360).toFloat()
        private var rotY = random.nextInt(360).toFloat()
        private val rotZ = random.nextInt(360).toFloat()
        private val sourceRotation = Quaternionf(0.0f, -0.7f, 0.7f, 0.0f)

        init {
            lifetime = 20
            alpha = 0.0f
            quadSize = 0.2f
            gravity = 0.0f
            if (random.nextBoolean()) {
                setColor(1.0f, 1.0f, 0.0f)
            } else {
                setColor(0.0f, 1.0f, 0.0f)
            }
            setSpriteFromAge(sprites)
        }

        override fun tick() {
            xo = x
            yo = y
            zo = z

            if (!running || mc.level !== level) {
                target = null
                remove()
                return
            }

            if (age++ >= lifetime) {
                remove()
                return
            }

            setSpriteFromAge(sprites)

            val currentTarget = validTargetOrNull()
            if (currentTarget != null) {
                setPos(currentTarget.x, currentTarget.y + currentTarget.bbHeight.toDouble() * 0.5, currentTarget.z)
            }

            rotY += 20.0f
            val dynamicRotation = euler(0.0f, 0.0f, rotY)
            val offset = euler(rotZ, rotX, -rotZ)
            offset.mul(dynamicRotation)
            sourceRotation.set(offset)

            quadSize = alpha * scaler
            val progress = age.toFloat() / lifetime.toFloat()
            alpha = Mth.clamp(
                (sqrt(sin(progress * PI)).toFloat() / 1.2f),
                0.0f,
                1.0f
            )
        }

        override fun extract(
            particleTypeRenderState: QuadParticleRenderState,
            camera: Camera,
            partialTickTime: Float
        ) {
            val cameraPos = camera.position()
            val px = (Mth.lerp(partialTickTime.toDouble(), xo, x) - cameraPos.x).toFloat()
            val py = (Mth.lerp(partialTickTime.toDouble(), yo, y) - cameraPos.y).toFloat()
            val pz = (Mth.lerp(partialTickTime.toDouble(), zo, z) - cameraPos.z).toFloat()

            particleTypeRenderState.add(
                REVIVE_NO_CULL_LAYER,
                px,
                py,
                pz,
                sourceRotation.x,
                sourceRotation.y,
                sourceRotation.z,
                sourceRotation.w,
                getQuadSize(partialTickTime),
                getU1(),
                getU0(),
                getV0(),
                getV1(),
                ARGB.colorFromFloat(alpha, rCol, gCol, bCol),
                getLightCoords(partialTickTime)
            )
        }

        override fun getLayer(): SingleQuadParticle.Layer {
            return REVIVE_NO_CULL_LAYER
        }

        private fun validTargetOrNull(): LivingEntity? {
            val current = target
            if (current == null || current.level() !== level || current.isRemoved) {
                target = null
                return null
            }
            return current
        }
    }

    private class ReviveSparkParticle(
        level: ClientLevel,
        x: Double,
        y: Double,
        z: Double,
        private var target: LivingEntity?,
        sprites: SpriteSet
    ) : SingleQuadParticle(level, x, y, z, sprites.first()) {

        init {
            lifetime = random.nextInt(19) + 30
            alpha = 1.0f
            quadSize = 0.1f
            gravity = 0.0f
            hasPhysics = true
            xd = randomSparkVelocity()
            yd = randomSparkVelocity()
            zd = randomSparkVelocity()
            setSprite(sprites.get(random))
            if (random.nextBoolean()) {
                setColor(0.0f, 1.0f, 0.0f)
            } else {
                setColor(1.0f, 1.0f, 0.0f)
            }
        }

        override fun tick() {
            xo = x
            yo = y
            zo = z

            if (!running || mc.level !== level) {
                target = null
                remove()
                return
            }

            if (age++ >= lifetime) {
                remove()
                return
            }

            val currentTarget = validTargetOrNull()
            val targetDir = if (currentTarget != null) {
                val targetY = currentTarget.y + currentTarget.bbHeight.toDouble() * 0.5
                val direction = Vec3(currentTarget.x - x, targetY - y, currentTarget.z - z)
                if (direction.lengthSqr() > 1.0E-6) direction.normalize() else Vec3.ZERO
            } else {
                Vec3.ZERO
            }

            if (age < lifetime / 5) {
                move(xd, yd, zd)
                xd *= 0.98
                yd *= 0.98
                zd *= 0.98
            } else if (age >= lifetime / 4 && currentTarget != null) {
                val pos = Vec3(x, y, z)
                val targetBase = Vec3(currentTarget.x, currentTarget.y, currentTarget.z)

                if (targetBase.distanceTo(pos) < SPARK_PULL_RANGE) {
                    alpha = Mth.clamp(
                        1.0f - ((age.toFloat() - (lifetime.toFloat() / 1.5f)) / 20.0f),
                        0.0f,
                        1.0f
                    )

                    val pull = (age - 15).toFloat() * 0.05f
                    xd = targetDir.x * pull
                    yd = targetDir.y * pull
                    zd = targetDir.z * pull
                    move(xd, yd, zd)

                    if (targetBase.distanceTo(pos) < SPARK_REMOVE_DISTANCE) {
                        alpha = 0.0f
                        remove()
                    }
                }
            }
        }

        override fun getLayer(): SingleQuadParticle.Layer {
            return SingleQuadParticle.Layer.TRANSLUCENT
        }

        private fun validTargetOrNull(): LivingEntity? {
            val current = target
            if (current == null || current.level() !== level || current.isRemoved) {
                target = null
                return null
            }
            return current
        }

        private fun randomSparkVelocity(): Double {
            return (random.nextInt(21) - 10).toDouble() / 25.0
        }
    }

    private class FixedSpriteSet(private val sprites: List<TextureAtlasSprite>) : SpriteSet {
        override fun get(index: Int, max: Int): TextureAtlasSprite {
            val frame = (index * (sprites.size - 1) / max).coerceIn(0, sprites.lastIndex)
            return sprites[frame]
        }

        override fun get(random: RandomSource): TextureAtlasSprite {
            return sprites[random.nextInt(sprites.size)]
        }

        override fun first(): TextureAtlasSprite {
            return sprites.first()
        }
    }

    private data class MasEffectSpriteSets(
        val revive: SpriteSet,
        val spark: SpriteSet
    )

    private object MasEffectSprites {
        private var missingLogged = false

        fun available(): Boolean {
            return resolve(logMissing = false) != null
        }

        fun resolve(logMissing: Boolean = true): MasEffectSpriteSets? {
            val reviveSprites = REVIVE_TEXTURES.map(::lookupParticleSprite)
            val sparkSprites = REVIVE_SPARK_TEXTURES.map(::lookupParticleSprite)
            val missing = (REVIVE_TEXTURES + REVIVE_SPARK_TEXTURES).filterIndexed { index, _ ->
                val sprite = if (index < REVIVE_TEXTURES.size) {
                    reviveSprites[index]
                } else {
                    sparkSprites[index - REVIVE_TEXTURES.size]
                }
                sprite.contents().name() == MissingTextureAtlasSprite.getLocation()
            }

            if (missing.isNotEmpty()) {
                if (logMissing && !missingLogged) {
                    missingLogged = true
                    LiquidBounce.logger.error(
                        "Mas Effects totem particles are unavailable; missing particle atlas sprites: {}",
                        missing.joinToString()
                    )
                }
                return null
            }

            missingLogged = false
            return MasEffectSpriteSets(FixedSpriteSet(reviveSprites), FixedSpriteSet(sparkSprites))
        }

        private fun lookupParticleSprite(id: Identifier): TextureAtlasSprite {
            return mc.atlasManager.get(SpriteId(TextureAtlas.LOCATION_PARTICLES, id))
        }
    }

    private fun euler(x: Float, y: Float, z: Float): Quaternionf {
        return Quaternionf()
            .rotationAxis(Math.toRadians(x.toDouble()).toFloat(), 1.0f, 0.0f, 0.0f)
            .mul(Quaternionf().rotationAxis(Math.toRadians(y.toDouble()).toFloat(), 0.0f, 1.0f, 0.0f))
            .mul(Quaternionf().rotationAxis(Math.toRadians(z.toDouble()).toFloat(), 0.0f, 0.0f, 1.0f))
    }

    private val REVIVE_NO_CULL_PIPELINE: RenderPipeline = RenderPipeline.builder(RenderPipelines.PARTICLE_SNIPPET)
        .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/maseffects_revive_particle"))
        .withColorTargetState(ColorTargetState(BlendFunction.TRANSLUCENT))
        .withCull(false)
        .build()

    private val REVIVE_NO_CULL_LAYER = SingleQuadParticle.Layer(
        true,
        TextureAtlas.LOCATION_PARTICLES,
        REVIVE_NO_CULL_PIPELINE
    )

    private val REVIVE_TEXTURES = listOf(
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_0"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_1"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_2")
    )

    private val REVIVE_SPARK_TEXTURES = listOf(
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_spark_0"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_spark_1"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_spark_2"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_spark_3"),
        Identifier.fromNamespaceAndPath("liquidbounce", "maseffects/particles/revive_spark_4")
    )

    private const val REVIVE_COUNT = 17
    private const val REVIVE_SPARK_COUNT = 99
    private const val REVIVE_SCALE = 2.5f
    private const val SPARK_PULL_RANGE = 20.0
    private const val SPARK_REMOVE_DISTANCE = 2.0
}
