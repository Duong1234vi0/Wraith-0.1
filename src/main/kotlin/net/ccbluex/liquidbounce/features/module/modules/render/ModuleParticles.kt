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
import com.mojang.blaze3d.pipeline.DepthStencilState
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.CompareOp
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaDlcRenderPipelines
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.READ_FINAL_STATE
import net.ccbluex.liquidbounce.utils.movement.DirectionalInput
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4fc
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.EnumMap
import java.util.Random
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Xyeta/Wild Client particle system port.
 */
@Suppress("MagicNumber")
object ModuleParticles : ClientModule("Particles", category = Category.RENDER) {

    private const val TYPE_COUNT = 9
    private const val MAX_SKY_PARTICLES = 100
    private const val MAX_MOVE_PARTICLES = 64
    private const val MAX_HIT_PARTICLES = 96
    private const val MAX_TOTAL_PARTICLES = 260
    private const val VISIBILITY_RECHECK_TICKS = 4
    private const val MAX_RAYCAST_DIST_SQ = 16384.0
    private const val BILLBOARD_SCALE = 0.6f
    private const val SKY_SPEED = 1.5f
    private const val MOVE_LIFETIME_MS = 500L
    private const val HIT_LIFETIME_MS = 5000L
    private const val SKY_SPAWN_INTERVAL_MS = 5L
    private val LOG_035 = kotlin.math.ln(0.35)

    private val particleTypes by multiEnumChoice("Particles", ParticleType.entries)
    private val moveMode by enumChoice("MoveMode", MoveMode.DOWN)
    private val triggers by multiEnumChoice("Triggers", Trigger.entries)

    private val particles = ArrayList<XyetaParticle>(256)
    private val batchLists = Array(TYPE_COUNT) { ArrayList<XyetaParticle>(48) }
    private val random = Random()
    private val enabledTypeIndices = IntArray(TYPE_COUNT)
    private val cameraRotation = Quaternionf()
    private val rightAxis = Vector3f()
    private val upAxis = Vector3f()

    private var enabledTypeCount = 0
    private var frameTimeMs = 0L
    private var lastRenderTime = System.nanoTime()
    private var lastSkySpawnMs = 0L
    private var tickCounter = 0
    private var eyeX = 0.0
    private var eyeY = 0.0
    private var eyeZ = 0.0
    private var moveDown = true
    private var skyParticleCount = 0
    private var moveParticleCount = 0
    private var hitParticleCount = 0

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player
        if (player == null || mc.level == null) {
            clearState()
            return@handler
        }

        tickCounter++
        moveDown = moveMode == MoveMode.DOWN
        eyeX = player.x
        eyeY = player.eyeY
        eyeZ = player.z

        rebuildEnabledTypes()
        updateParticleVisibility()

        if (skyParticleCount < MAX_SKY_PARTICLES && Trigger.SKY in triggers) {
            spawnSkyParticle()
        }

        if (moveParticleCount < MAX_MOVE_PARTICLES && Trigger.MOVE in triggers && player.isMovingHorizontally()) {
            spawnMoveParticle(player)
        }
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent>(priority = READ_FINAL_STATE) { event ->
        if (event.isCancelled || Trigger.ATTACK !in triggers) {
            return@handler
        }

        repeat(3) {
            spawnHitParticle(event.entity)
        }
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (particles.isEmpty()) {
            lastRenderTime = System.nanoTime()
            return@handler
        }

        val nowNano = System.nanoTime()
        val deltaTime = min((nowNano - lastRenderTime).toFloat() * 1.0E-9f, 0.05f)
        lastRenderTime = nowNano
        frameTimeMs = System.currentTimeMillis()

        val camera = event.camera
        val cameraPos = camera.position()
        cameraRotation.set(camera.rotation())
        rightAxis.set(BILLBOARD_SCALE, 0.0f, 0.0f).rotate(cameraRotation)
        upAxis.set(0.0f, BILLBOARD_SCALE, 0.0f).rotate(cameraRotation)

        clearBatches()
        fillBatches(deltaTime, mc.options.cameraType.isFirstPerson)

        val themeRgb = ClientTheme.accentA and 0x00FFFFFF
        val modelViewMatrix = event.matrixStack.last().pose()

        for (textureIndex in 0 until TYPE_COUNT) {
            val batch = batchLists[textureIndex]
            if (batch.isEmpty()) {
                continue
            }

            drawBatch(textureIndex, batch, modelViewMatrix, cameraPos, themeRgb)
        }

        pruneDeadParticles()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearState()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearState()
    }

    override fun disable() {
        clearState()
    }

    private fun rebuildEnabledTypes() {
        var count = 0
        for (i in 0 until TYPE_COUNT) {
            if (ParticleType.entries[i] in particleTypes) {
                enabledTypeIndices[count++] = i
            }
        }

        enabledTypeCount = count
    }

    private fun nextTextureIndex(): Int {
        if (enabledTypeCount == 0) {
            return 0
        }

        return enabledTypeIndices[random.nextInt(enabledTypeCount)]
    }

    private fun fillBatches(deltaTime: Float, firstPerson: Boolean) {
        for (particle in particles) {
            particle.update(deltaTime, moveDown, frameTimeMs)
            if (particle.isMove && firstPerson) {
                continue
            }

            val alpha = particle.animation.output().toFloat()
            particle.renderAlpha = alpha
            if (alpha > 0.005f) {
                batchLists[particle.textureIndex].add(particle)
            }
        }
    }

    private fun drawBatch(
        textureIndex: Int,
        batch: List<XyetaParticle>,
        modelViewMatrix: Matrix4fc,
        cameraPos: Vec3,
        themeRgb: Int
    ) {
        val texture = ParticleType.entries[textureIndex].texture
        val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)

        for (particle in batch) {
            appendParticleQuad(buffer, particle, cameraPos, themeRgb)
        }

        val mesh = buffer.build() ?: return
        AlphaDlcRenderPipelines.draw(
            mesh,
            XyetaParticlePipeline.layer(texture).pipeline(),
            modelViewMatrix,
            depthAttachment = true,
            texture = texture
        )
    }

    private fun appendParticleQuad(
        buffer: BufferBuilder,
        particle: XyetaParticle,
        cameraPos: Vec3,
        themeRgb: Int
    ) {
        val colorArgb = ((particle.renderAlpha * 255.0f).toInt().coerceIn(0, 255) shl 24) or themeRgb
        val quadMax = particle.quadSize - 0.75f
        val baseX = (particle.x - cameraPos.x).toFloat()
        val baseY = (particle.y - cameraPos.y).toFloat()
        val baseZ = (particle.z - cameraPos.z).toFloat()

        val x1 = baseX + rightAxis.x * -0.75f + upAxis.x * quadMax
        val y1 = baseY + rightAxis.y * -0.75f + upAxis.y * quadMax
        val z1 = baseZ + rightAxis.z * -0.75f + upAxis.z * quadMax
        val x2 = baseX + rightAxis.x * quadMax + upAxis.x * quadMax
        val y2 = baseY + rightAxis.y * quadMax + upAxis.y * quadMax
        val z2 = baseZ + rightAxis.z * quadMax + upAxis.z * quadMax
        val x3 = baseX + rightAxis.x * quadMax + upAxis.x * -0.75f
        val y3 = baseY + rightAxis.y * quadMax + upAxis.y * -0.75f
        val z3 = baseZ + rightAxis.z * quadMax + upAxis.z * -0.75f
        val x4 = baseX + rightAxis.x * -0.75f + upAxis.x * -0.75f
        val y4 = baseY + rightAxis.y * -0.75f + upAxis.y * -0.75f
        val z4 = baseZ + rightAxis.z * -0.75f + upAxis.z * -0.75f

        buffer.addVertex(x1, y1, z1).setUv(0.0f, 0.0f).setColor(colorArgb)
        buffer.addVertex(x2, y2, z2).setUv(1.0f, 0.0f).setColor(colorArgb)
        buffer.addVertex(x3, y3, z3).setUv(1.0f, 1.0f).setColor(colorArgb)
        buffer.addVertex(x4, y4, z4).setUv(0.0f, 1.0f).setColor(colorArgb)
    }

    private fun updateParticleVisibility() {
        for (i in particles.lastIndex downTo 0) {
            val particle = particles[i]
            if (!moveDown && particle.startY + 15.0 < particle.y) {
                particle.animation.direction = AnimationDirection.BACKWARDS
            } else if (particle.nextVisibilityTick <= tickCounter) {
                particle.nextVisibilityTick = tickCounter + VISIBILITY_RECHECK_TICKS
                if (!canParticleBeSeen(particle.x, particle.y, particle.z)) {
                    particle.animation.direction = AnimationDirection.BACKWARDS
                }
            }
        }
    }

    private fun spawnSkyParticle() {
        val nowMs = System.currentTimeMillis()
        if (nowMs - lastSkySpawnMs < SKY_SPAWN_INTERVAL_MS || particles.size >= MAX_TOTAL_PARTICLES) {
            return
        }

        val player = mc.player ?: return
        val spawnX = player.x + 30.0 - random.nextInt(60)
        val spawnY = if (moveDown) {
            player.y + 10.0 + random.nextInt(25)
        } else {
            player.y + 1.0 + random.nextInt(10)
        }
        val spawnZ = player.z + 30.0 - random.nextInt(60)

        if (canParticleBeSeen(spawnX, spawnY, spawnZ)) {
            addParticle(SkyParticle(spawnX, spawnY, spawnZ, nextTextureIndex(), tickCounter + random.nextInt(4)))
            lastSkySpawnMs = nowMs
        }
    }

    private fun spawnMoveParticle(entity: Entity) {
        if (particles.size >= MAX_TOTAL_PARTICLES) {
            return
        }

        val x = entity.x + 0.25 - random.nextDouble() * 0.5
        val y = entity.y + 0.75 + random.nextDouble() * 0.75
        val z = entity.z + 0.25 - random.nextDouble() * 0.5
        val bornAtMs = if (frameTimeMs != 0L) frameTimeMs else System.currentTimeMillis()
        addParticle(MoveParticle(x, y, z, nextTextureIndex(), bornAtMs, tickCounter + 1))
    }

    private fun spawnHitParticle(entity: Entity) {
        if (particles.size >= MAX_TOTAL_PARTICLES || hitParticleCount >= MAX_HIT_PARTICLES) {
            return
        }

        val entityX = entity.x
        val entityY = entity.y
        val entityZ = entity.z
        val startX = entityX + (random.nextDouble() - 0.5) * 0.75
        val startY = entityY + random.nextDouble()
        val startZ = entityZ + (random.nextDouble() - 0.5) * 0.75
        val endX = entityX - 8.0 + random.nextInt(16)
        val endY = entityY + random.nextInt(10)
        val endZ = entityZ - 8.0 + random.nextInt(16)
        val bornAtMs = if (frameTimeMs != 0L) frameTimeMs else System.currentTimeMillis()

        addParticle(
            HitParticle(startX, startY, startZ, nextTextureIndex(), endX, endY, endZ, bornAtMs, tickCounter + 1)
        )
    }

    private fun addParticle(particle: XyetaParticle) {
        particles.add(particle)
        when (particle.kind) {
            ParticleKind.SKY -> skyParticleCount++
            ParticleKind.MOVE -> moveParticleCount++
            ParticleKind.HIT -> hitParticleCount++
        }
    }

    private fun pruneDeadParticles() {
        for (i in particles.lastIndex downTo 0) {
            if (particles[i].animation.finished(AnimationDirection.BACKWARDS)) {
                removeParticle(i)
            }
        }
    }

    private fun removeParticle(index: Int) {
        val lastIndex = particles.lastIndex
        val removed = particles[index]
        if (index != lastIndex) {
            particles[index] = particles[lastIndex]
        }

        particles.removeAt(lastIndex)
        when (removed.kind) {
            ParticleKind.SKY -> skyParticleCount--
            ParticleKind.MOVE -> moveParticleCount--
            ParticleKind.HIT -> hitParticleCount--
        }
    }

    private fun canParticleBeSeen(x: Double, y: Double, z: Double): Boolean {
        val level = mc.level ?: return false
        val player = mc.player ?: return false
        val adjustedY = y - if (moveDown) 0.5 else 0.0
        val deltaX = x - eyeX
        val deltaY = adjustedY - eyeY
        val deltaZ = z - eyeZ

        if (deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ > MAX_RAYCAST_DIST_SQ) {
            return false
        }

        val eye = Vec3(eyeX, eyeY, eyeZ)
        val target = Vec3(x, adjustedY, z)
        return level.clip(ClipContext(eye, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).type ==
            HitResult.Type.MISS
    }

    private fun clearBatches() {
        for (batch in batchLists) {
            batch.clear()
        }
    }

    private fun clearState() {
        particles.clear()
        clearBatches()
        enabledTypeCount = 0
        frameTimeMs = 0L
        tickCounter = 0
        lastRenderTime = System.nanoTime()
        lastSkySpawnMs = 0L
        eyeX = 0.0
        eyeY = 0.0
        eyeZ = 0.0
        moveDown = true
        skyParticleCount = 0
        moveParticleCount = 0
        hitParticleCount = 0
    }

    // Xyeta's decompiled MovementUtil required non-zero X, Y, and Z motion.
    // Ground movement has no vertical motion, so this uses the intended horizontal input/velocity trigger.
    private fun Entity.isMovingHorizontally(): Boolean {
        if (this === mc.player && DirectionalInput(mc.player!!.input).isMoving) {
            return true
        }

        return deltaMovement.horizontalDistanceSqr() > 1.0E-6
    }

    private enum class ParticleType(
        override val choiceName: String,
        val texture: Identifier
    ) : NamedChoice {
        TYPE1("Type1", "particles/xyeta/type1.png".registerAsDynamicImageFromClientResources()),
        TYPE2("Type2", "particles/xyeta/type2.png".registerAsDynamicImageFromClientResources()),
        TYPE3("Type3", "particles/xyeta/type3.png".registerAsDynamicImageFromClientResources()),
        TYPE4("Type4", "particles/xyeta/type4.png".registerAsDynamicImageFromClientResources()),
        TYPE5("Type5", "particles/xyeta/type5.png".registerAsDynamicImageFromClientResources()),
        TYPE6("Type6", "particles/xyeta/type6.png".registerAsDynamicImageFromClientResources()),
        TYPE7("Type7", "particles/xyeta/type7.png".registerAsDynamicImageFromClientResources()),
        TYPE8("Type8", "particles/xyeta/type8.png".registerAsDynamicImageFromClientResources()),
        TYPE9("Type9", "particles/xyeta/type9.png".registerAsDynamicImageFromClientResources())
    }

    private enum class MoveMode(override val choiceName: String) : NamedChoice {
        UP("Up"),
        DOWN("Down")
    }

    private enum class Trigger(override val choiceName: String) : NamedChoice {
        SKY("Sky"),
        MOVE("Move"),
        ATTACK("Attack")
    }

    private enum class ParticleKind {
        SKY,
        MOVE,
        HIT
    }

    private sealed class XyetaParticle(
        var x: Double,
        var y: Double,
        var z: Double,
        val textureIndex: Int,
        val kind: ParticleKind,
        val quadSize: Float,
        var nextVisibilityTick: Int
    ) {
        val startY = y
        val animation = XyetaParticleAnimation().apply {
            direction = AnimationDirection.FORWARDS
        }
        var renderAlpha = 0.0f

        val isMove: Boolean
            get() = kind == ParticleKind.MOVE

        open fun update(deltaTime: Float, moveDown: Boolean, nowMs: Long) {
            y += (if (moveDown) -SKY_SPEED else SKY_SPEED) * deltaTime
        }
    }

    private class SkyParticle(
        x: Double,
        y: Double,
        z: Double,
        textureIndex: Int,
        nextVisibilityTick: Int
    ) : XyetaParticle(x, y, z, textureIndex, ParticleKind.SKY, 1.5f, nextVisibilityTick)

    private class MoveParticle(
        x: Double,
        y: Double,
        z: Double,
        textureIndex: Int,
        private val bornAtMs: Long,
        nextVisibilityTick: Int
    ) : XyetaParticle(x, y, z, textureIndex, ParticleKind.MOVE, 0.75f, nextVisibilityTick) {
        override fun update(deltaTime: Float, moveDown: Boolean, nowMs: Long) {
            if (nowMs - bornAtMs >= MOVE_LIFETIME_MS) {
                animation.direction = AnimationDirection.BACKWARDS
            }
        }
    }

    private class HitParticle(
        x: Double,
        y: Double,
        z: Double,
        textureIndex: Int,
        private val endX: Double,
        private val endY: Double,
        private val endZ: Double,
        private val bornAtMs: Long,
        nextVisibilityTick: Int
    ) : XyetaParticle(x, y, z, textureIndex, ParticleKind.HIT, 1.5f, nextVisibilityTick) {
        override fun update(deltaTime: Float, moveDown: Boolean, nowMs: Long) {
            val lerpSpeed = 1.0f - exp(LOG_035 * deltaTime).toFloat()
            x += (endX - x) * lerpSpeed
            y += (endY - y) * lerpSpeed
            z += (endZ - z) * lerpSpeed

            if (nowMs - bornAtMs >= HIT_LIFETIME_MS) {
                animation.direction = AnimationDirection.BACKWARDS
            }
        }
    }

    private enum class AnimationDirection {
        FORWARDS,
        BACKWARDS
    }

    private class XyetaParticleAnimation {
        private val duration = 325L
        private val endPoint = 1.0
        private val easeAmount = 0.1f
        private var startMs = System.currentTimeMillis()

        var direction = AnimationDirection.BACKWARDS
            set(value) {
                if (field == value) {
                    return
                }

                val now = System.currentTimeMillis()
                val elapsed = (now - startMs).coerceIn(0L, duration)
                startMs = now - (duration - elapsed)
                field = value
            }

        fun output(): Double {
            val elapsed = (System.currentTimeMillis() - startMs).coerceAtLeast(0L)
            return when (direction) {
                AnimationDirection.FORWARDS -> {
                    if (elapsed >= duration) {
                        endPoint
                    } else {
                        equation(elapsed.toDouble()) * endPoint
                    }
                }
                AnimationDirection.BACKWARDS -> {
                    if (elapsed >= duration) {
                        0.0
                    } else {
                        equation(min(duration, max(0L, duration - elapsed)).toDouble()) * endPoint
                    }
                }
            }
        }

        fun finished(direction: AnimationDirection): Boolean =
            this.direction == direction && System.currentTimeMillis() - startMs >= duration

        private fun equation(x: Double): Double {
            val normalized = x / duration.toDouble()
            val shrink = easeAmount + 1.0f
            return max(
                0.0,
                1.0 + shrink * (normalized - 1.0).pow(3.0) + easeAmount * (normalized - 1.0).pow(2.0)
            )
        }
    }

    private object XyetaParticlePipeline {
        private val pipeline: RenderPipeline by lazy(LazyThreadSafetyMode.NONE) {
            RenderPipeline.builder(RenderPipelines.MATRICES_PROJECTION_SNIPPET)
                .withLocation(Identifier.fromNamespaceAndPath("liquidbounce", "pipeline/xyeta_particles"))
                .withVertexShader("core/position_tex_color")
                .withFragmentShader("core/position_tex_color")
                .withSampler("Sampler0")
                .withColorTargetState(ColorTargetState(BlendFunction.LIGHTNING))
                .withDepthStencilState(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, true))
                .withCull(false)
                .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
                .build()
        }

        private val layers = EnumMap<ParticleType, RenderType>(ParticleType::class.java)

        fun layer(texture: Identifier): RenderType {
            val type = ParticleType.entries.first { it.texture == texture }
            return layers.getOrPut(type) {
                RenderType.create(
                    "xyeta_particles_${type.choiceName.lowercase()}",
                    RenderSetup.builder(pipeline)
                        .withTexture("Sampler0", texture)
                        .bufferSize(8192)
                        .createRenderSetup()
                )
            }
        }
    }
}
