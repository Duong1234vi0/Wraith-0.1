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
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.config.types.ChooseListValue
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.canSee
import net.ccbluex.liquidbounce.utils.entity.currentFps
import net.ccbluex.liquidbounce.utils.entity.multiply
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.pop
import net.ccbluex.liquidbounce.utils.entity.push
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.minecraft.client.Camera
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.decoration.ArmorStand
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f
import org.joml.Vector4f
import java.util.Random
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Suppress("MagicNumber", "TooManyFunctions", "LargeClass")
object ModuleTargetESP : ClientModule("TargetEsp", Category.RENDER) {

    private val modeValue = ChooseListValue(
        name = "Mode",
        defaultValue = Mode.CRYSTALS,
        choices = Mode.entries.toTypedArray(),
        legacyChoiceMapper = ::migrateRemovedMode
    ).also { value ->
        inner.add(value)
        tagBy(value)
    }
    private val mode by modeValue

    private val circleSpeed by float("CircleSpeed", 0.05F, 0.01F..0.30F)
        .visibleWhen { mode == Mode.CIRCLE }
    private val circleRainbow by boolean("CircleRainbow", true)
        .visibleWhen { mode == Mode.CIRCLE }
    private val circleDamageRed by boolean("CircleDamageRed", true)
        .visibleWhen { mode == Mode.CIRCLE }
    private val circleThroughWalls by boolean("CircleThroughWalls", false)
        .visibleWhen { mode == Mode.CIRCLE }
    private val soulsSpeed by float("SoulsSpeed", 5.0F, 1.0F..20.0F)
        .visibleWhen { mode == Mode.SOULS }

    @Suppress("unused")
    private val ignoredCrystalRotationSpeed by float("CrystalRotationSpeed", 0.5F, 0.1F..2.0F)
        .visibleWhen { false }

    private const val FULL_BRIGHT = 0xF000F0
    private const val TWO_PI = (Math.PI * 2.0)
    private const val CUBE_SPAWN_INTERVAL = 0.017F
    private const val CUBE_LIFETIME_MS = 1000L
    private const val CUBE_HALF_EXTENT = 0.06F
    private const val GHOST_COUNT = 5
    private const val GHOST_TRAIL_LIMIT = 35
    private const val GHOST_V2_PARTICLE_COUNT = 3
    private const val GHOST_V2_PARTICLE_SIZE = 0.28F
    private const val GHOST_V2_MOVING_STEP = 20.0F
    private const val GHOST_V2_MOVING_DURATION_MS = 55L
    private const val GHOST_V2_HIT_DURATION_MS = 300L
    private const val GHOST_V2_TRAIL_LIFETIME = 20.5F
    private const val GHOST_V2_TRAIL_POINT_SPACING = 0.045
    private const val GHOST_V2_MAX_POINTS_PER_UPDATE = 10
    private const val GHOST_V2_TRAIL_VERTICAL_OFFSET = 0.7F

    private val bloomTexture = id("textures/targetesp/babka/bloom.png")

    private val generalAnimation = BabkaAnimation(500L, BabkaEasing.EXPO_OUT)
    private val crystalsAnimation = BabkaAnimation(350L, BabkaEasing.EXPO_OUT)
    private val soulAnimation = BabkaAnimation(250L, BabkaEasing.CUBIC_OUT)
    private val celestialAnimation = BabkaAnimation(400L, BabkaEasing.EXPO_OUT)
    private val soulsAnimation = BabkaAnimation(400L, BabkaEasing.EXPO_OUT)
    private val souls2Animation = BabkaAnimation(400L, BabkaEasing.EXPO_OUT)

    private val random = Random()
    private val cubeParticles = ArrayList<CubeParticle>(64)
    private val ghostTrails = Array(GHOST_COUNT) { ArrayList<RelativePoint>(GHOST_TRAIL_LIMIT) }
    private val ghostV2Renderer = VCoreGhostV2Renderer()
    private val soulTest3Offsets = Array(15) { index ->
        val seeded = Random(index.toLong() * 12345L)
        RelativePoint(
            seeded.nextDouble() - 0.5,
            seeded.nextDouble() - 0.5,
            seeded.nextDouble() - 0.5
        )
    }

    private var lastTarget: LivingEntity? = null
    private var lastMode = Mode.CRYSTALS
    private var lastCircleUpdateMs = 0L
    private var lastCubeUpdateMs = 0L
    private var cubeSpawnAccumulator = 0.0F
    private var circleStep = 0.0F
    private var circleProgress = 0.0F
    private var circleLastHurtTime = 0
    private var circleDamageFlashStart = 0L
    private var circleDamageFlashIntensity = 0.0F
    private var modeTimestampMs = System.currentTimeMillis()
    private var soulTest3PreviousNano = System.nanoTime()
    private var soulTest3Value = 0.0F
    private var crystalMoving = 0.0F
    private var shaderWorldPassRendered = false
    private var shaderWorldPassDisabled = false
    private var shaderFailureLogged = false
    private var shaderMappingFallbackLogged = false
    private var normalFailureLogged = false
    private var fallbackFrame: PreparedFrame? = null

    override fun disable() {
        clearAllState()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearAllState()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearAllState()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (shaderWorldPassRendered) {
            shaderWorldPassRendered = false
            return@handler
        }

        runCatching {
            val frame = fallbackFrame ?: prepareFrame(
                partialTicks = event.partialTicks,
                cameraPos = event.camera.position(),
                nowMs = System.currentTimeMillis(),
                nowNano = System.nanoTime()
            ) ?: return@handler
            fallbackFrame = null

            renderPreparedFrame(
                frame = frame,
                stack = event.matrixStack,
                provider = mc.renderBuffers().bufferSource(),
                camera = event.camera,
                pass = RenderPass.NORMAL
            )
        }.onFailure { throwable ->
            if (!normalFailureLogged) {
                normalFailureLogged = true
                logger.error("TargetESP world render failed; skipping this frame.", throwable)
            }
        }
    }

    @JvmStatic
    fun renderShaderWorldPass(cameraPos: Vec3) {
        shaderWorldPassRendered = false

        if (shaderWorldPassDisabled || !running || !IrisShaderPack.inUse()) {
            return
        }

        if (!BabkaTargetEspPipelines.irisMappingsAvailable()) {
            if (!shaderMappingFallbackLogged) {
                shaderMappingFallbackLogged = true
                logger.warn("TargetESP Iris pipeline mapping unavailable; using complete late-render fallback.")
            }
            return
        }

        val partialTicks = mc.deltaTracker.getGameTimeDeltaPartialTick(false)
        val nowMs = System.currentTimeMillis()
        val nowNano = System.nanoTime()
        val frame = prepareFrame(partialTicks, cameraPos, nowMs, nowNano) ?: return
        val stack = PoseStack()
        val provider = mc.renderBuffers().bufferSource()
        val camera = mc.gameRenderer.camera

        runCatching {
            renderPreparedFrame(frame, stack, provider, camera, RenderPass.SHADER_WORLD)
            shaderWorldPassRendered = true
        }.onFailure { throwable ->
            shaderWorldPassDisabled = true
            fallbackFrame = frame
            if (!shaderFailureLogged) {
                shaderFailureLogged = true
                logger.error("TargetESP shader-world pass failed; falling back to WorldRenderEvent.", throwable)
            }
        }
    }

    private fun prepareFrame(partialTicks: Float, cameraPos: Vec3, nowMs: Long, nowNano: Long): PreparedFrame? {
        val previousMode = lastMode
        if (mode != lastMode) {
            if (previousMode == Mode.GHOST_V2 || mode == Mode.GHOST_V2) {
                ghostV2Renderer.reset()
            }
            modeTimestampMs = nowMs
            lastMode = mode
        }

        val rawTarget = currentTarget()
        if (mode == Mode.GHOST_V2) {
            if (rawTarget == null) {
                ghostV2Renderer.reset()
                lastTarget = null
                return null
            }
            lastTarget = rawTarget
            updateAnimations(hasTarget = true, nowMs)
            return PreparedFrame(
                target = rawTarget,
                liveTarget = rawTarget,
                partialTicks = partialTicks,
                cameraPos = cameraPos,
                nowMs = nowMs,
                nowNano = nowNano,
                accentColor = clientColor(),
                circleProgress = circleProgress,
                generalAnimation = generalAnimation.value.toFloat(),
                crystalsAnimation = crystalsAnimation.value.toFloat(),
                soulAnimation = soulAnimation.value.toFloat(),
                celestialAnimation = celestialAnimation.value.toFloat(),
                soulsAnimation = soulsAnimation.value.toFloat(),
                souls2Animation = souls2Animation.value.toFloat()
            )
        }

        val targetSwitched = rawTarget != null && lastTarget != null && rawTarget !== lastTarget
        if (targetSwitched) {
            clearTargetRelativeState()
            modeTimestampMs = nowMs
        }
        if (rawTarget != null) {
            lastTarget = rawTarget
        } else if (lastTarget?.let { !isEntityStillInLevel(it) } == true) {
            lastTarget = null
        }

        val renderTarget = lastTarget
        val hasLiveTarget = rawTarget != null
        updateAnimations(hasLiveTarget, nowMs)
        updateCircleState(rawTarget, hasLiveTarget, nowMs)
        updateCubes(rawTarget, nowMs)
        updateSoulTest3State(renderTarget, partialTicks, nowNano)

        if (mode == Mode.CRYSTAL_3DD && soulAnimation.value > 0.001) {
            crystalMoving += 1.0F
        }

        if (!hasLiveTarget && renderTarget != null &&
            (mode == Mode.CUBES || selectedAnimationValue() <= 0.001F && circleProgress <= 0.001F)
        ) {
            lastTarget = null
        }

        val targetForMode = lastTarget
        if (targetForMode == null && cubeParticles.isEmpty()) {
            return null
        }

        return PreparedFrame(
            target = targetForMode,
            liveTarget = rawTarget,
            partialTicks = partialTicks,
            cameraPos = cameraPos,
            nowMs = nowMs,
            nowNano = nowNano,
            accentColor = clientColor(),
            circleProgress = circleProgress,
            generalAnimation = generalAnimation.value.toFloat(),
            crystalsAnimation = crystalsAnimation.value.toFloat(),
            soulAnimation = soulAnimation.value.toFloat(),
            celestialAnimation = celestialAnimation.value.toFloat(),
            soulsAnimation = soulsAnimation.value.toFloat(),
            souls2Animation = souls2Animation.value.toFloat()
        )
    }

    private fun updateAnimations(hasTarget: Boolean, nowMs: Long) {
        val currentMode = mode
        val targetValue = if (hasTarget) 1.0 else 0.0

        generalAnimation.run(if (currentMode == Mode.GHOSTS2 && hasTarget) 1.0 else 0.0, nowMs)
        crystalsAnimation.run(
            if ((currentMode == Mode.CRYSTALS || currentMode == Mode.CRYSTALS_1) && hasTarget) 1.0 else 0.0,
            nowMs
        )
        soulAnimation.run(
            if (
                (currentMode == Mode.SOUL_2 || currentMode == Mode.SOUL_TEST_3 ||
                    currentMode == Mode.CRYSTAL_3DD) && hasTarget
            ) targetValue else 0.0,
            nowMs
        )
        celestialAnimation.run(if (currentMode == Mode.CELESTIAL && hasTarget) 1.0 else 0.0, nowMs)
        soulsAnimation.run(if (currentMode == Mode.SOULS && hasTarget) 1.0 else 0.0, nowMs)
        souls2Animation.run(if (currentMode == Mode.SOULS_2 && hasTarget) 1.0 else 0.0, nowMs)
    }

    private fun updateCircleState(target: LivingEntity?, hasTarget: Boolean, nowMs: Long) {
        if (lastCircleUpdateMs == 0L) {
            lastCircleUpdateMs = nowMs
        }
        val deltaSeconds = min((nowMs - lastCircleUpdateMs) / 1000.0F, 0.1F)
        lastCircleUpdateMs = nowMs

        if (mode == Mode.CIRCLE) {
            circleStep += circleSpeed * deltaSeconds * 60.0F
            circleProgress = if (hasTarget) {
                min(1.0F, circleProgress + 0.1F)
            } else {
                max(0.0F, circleProgress - 0.05F)
            }
        } else {
            circleProgress = max(0.0F, circleProgress - 0.05F)
        }

        if (target != null && circleDamageRed) {
            if (target.hurtTime > 0 && circleLastHurtTime == 0) {
                circleDamageFlashStart = nowMs
            }
            circleLastHurtTime = target.hurtTime
            val elapsed = (nowMs - circleDamageFlashStart).coerceAtLeast(0L)
            val flashTarget = if (elapsed < 500L) {
                1.0F - easeOutCubic((elapsed / 500.0F).coerceIn(0.0F, 1.0F))
            } else {
                0.0F
            }
            circleDamageFlashIntensity = lerp(0.1F, circleDamageFlashIntensity, flashTarget)
        } else {
            circleLastHurtTime = 0
            circleDamageFlashIntensity = lerp(0.1F, circleDamageFlashIntensity, 0.0F)
        }
    }

    private fun updateCubes(target: LivingEntity?, nowMs: Long) {
        if (lastCubeUpdateMs == 0L) {
            lastCubeUpdateMs = nowMs
        }
        val deltaSeconds = min((nowMs - lastCubeUpdateMs) / 1000.0F, 0.1F)
        lastCubeUpdateMs = nowMs

        if (mode == Mode.CUBES && target != null) {
            cubeSpawnAccumulator += deltaSeconds
            while (cubeSpawnAccumulator >= CUBE_SPAWN_INTERVAL) {
                cubeSpawnAccumulator -= CUBE_SPAWN_INTERVAL
                val angle = random.nextDouble() * 360.0
                val radians = Math.toRadians(angle)
                cubeParticles += CubeParticle(
                    target = target,
                    offsetX = cos(radians) * 0.7,
                    offsetY = randomRange(0.04, 0.20),
                    offsetZ = sin(radians) * 0.7,
                    createdMs = nowMs
                )
            }
        }

        val iterator = cubeParticles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            val alive = nowMs - particle.createdMs
            if (alive >= CUBE_LIFETIME_MS || !isEntityStillInLevel(particle.target)) {
                iterator.remove()
                continue
            }
            particle.offsetY += randomRange(0.01, 0.04) * deltaSeconds * 60.0
        }
    }

    private fun updateSoulTest3State(
        target: LivingEntity?,
        partialTicks: Float,
        nowNano: Long
    ) {
        if (mode != Mode.SOUL_TEST_3) {
            soulTest3PreviousNano = nowNano
            return
        }

        val hurt = target?.hurtTime ?: 0
        val hurtTime = (hurt - if (hurt != 0) partialTicks else 0.0F) / 10.0F
        val deltaTime = (nowNano - soulTest3PreviousNano).toFloat() / 2_000_000.0F
        soulTest3PreviousNano = nowNano
        soulTest3Value += hurtTime * deltaTime
    }

    private fun renderPreparedFrame(
        frame: PreparedFrame,
        stack: PoseStack,
        provider: MultiBufferSource.BufferSource,
        camera: Camera,
        pass: RenderPass
    ) {
        val rotation = Quaternionf(camera.rotation)
        val right = Vector3f(1.0F, 0.0F, 0.0F).rotate(rotation)
        val up = Vector3f(0.0F, 1.0F, 0.0F).rotate(rotation)
        val context = RenderContext(frame, stack, provider, camera, pass, right, up)

        runCatching {
            when (mode) {
                Mode.CRYSTALS -> frame.target?.let { renderCrystals(context, it, sharp = false) }
                Mode.CRYSTALS_1 -> frame.target?.let { renderCrystals(context, it, sharp = true) }
                Mode.CUBES -> renderCubes(context)
                Mode.CIRCLE -> frame.target?.let { renderCircle(context, it) }
                Mode.GHOSTS2 -> frame.target?.let { renderGhosts2(context, it) }
                Mode.GHOST_V2 -> frame.liveTarget?.let { renderGhostV2(context, it) } ?: ghostV2Renderer.reset()
                Mode.CELESTIAL -> frame.target?.let { renderCelestial(context, it) }
                Mode.SOUL_2 -> frame.target?.let { renderSoul2(context, it) }
                Mode.SOUL_TEST_3 -> frame.target?.let { renderSoulTest3(context, it) }
                Mode.CRYSTAL_3DD -> frame.target?.let { renderCrystal3DD(context, it) }
                Mode.SOULS -> frame.target?.let { renderSouls(context, it) }
                Mode.SOULS_2 -> frame.target?.let { renderSouls2(context, it) }
            }
            provider.endBatch()
        }.onFailure { throwable ->
            provider.endBatch()
            if (pass == RenderPass.SHADER_WORLD) {
                throw throwable
            }
            logger.error("TargetESP mode ${mode.choiceName} failed; skipping this frame.", throwable)
        }
    }

    private fun renderCrystals(ctx: RenderContext, target: LivingEntity, sharp: Boolean) {
        val anim = ctx.frame.crystalsAnimation
        if (anim <= 0.001F) {
            return
        }

        val eased = easeOutCubic(anim)
        val time = ((mc.player?.tickCount ?: 0).toFloat() + ctx.frame.partialTicks) * 6.0F
        val targetPos = target.getPosition(ctx.frame.partialTicks)
        val baseX = targetPos.x - ctx.frame.cameraPos.x
        val baseY = targetPos.y - ctx.frame.cameraPos.y
        val baseZ = targetPos.z - ctx.frame.cameraPos.z
        val entityHeight = target.bbHeight
        val halfWidth = target.bbWidth * 0.5F
        val pelvisY = entityHeight * 0.35F
        val torsoY = entityHeight * 0.55F
        val neckY = entityHeight * 0.74F
        val color = brighten(ctx.frame.accentColor, 1.3F, (200.0F * anim).roundToInt())
        val crystalConsumer = ctx.provider.getBuffer(ctx.crystalBodyLayer())

        ctx.stack.push()
        try {
            ctx.stack.translate(baseX, baseY, baseZ)
            repeat(18) { i ->
                val s1 = sin(i * 1.7F + 0.3F) * 0.5F + 0.5F
                val s2 = cos(i * 2.3F + 0.7F) * 0.5F + 0.5F
                val s3 = sin(i * 3.1F + 1.1F) * 0.5F + 0.5F
                val angle = time + i * (360.0F / 18.0F) + s1 * 12.0F
                val radians = Math.toRadians(angle.toDouble())
                val radius = halfWidth + 0.25F + s3 * 0.15F
                val cx = radius * cos(radians).toFloat()
                val cz = radius * sin(radians).toFloat()
                val cy = s2 * entityHeight
                val scale = 0.18F * eased
                if (scale > 0.001F) {
                    val lookY = when {
                        cy / entityHeight < 0.33F -> pelvisY
                        cy / entityHeight < 0.6F -> torsoY
                        else -> neckY
                    }
                    val dx = -cx
                    val dy = lookY - cy
                    val dz = -cz
                    val yaw = Math.toDegrees(atan2(dz.toDouble(), dx.toDouble())).toFloat()
                    val pitch = Math.toDegrees(atan2(dy.toDouble(), sqrt(dx * dx + dz * dz).toDouble())).toFloat()
                    appendCrystalShape(ctx.stack, crystalConsumer, cx, cy, cz, scale, yaw, pitch, sharp, color)
                }
            }
        } finally {
            ctx.stack.pop()
        }

        repeat(18) { i ->
            val s1 = sin(i * 1.7F + 0.3F) * 0.5F + 0.5F
            val s2 = cos(i * 2.3F + 0.7F) * 0.5F + 0.5F
            val s3 = sin(i * 3.1F + 1.1F) * 0.5F + 0.5F
            val angle = time + i * (360.0F / 18.0F) + s1 * 12.0F
            val radians = Math.toRadians(angle.toDouble())
            val radius = halfWidth + 0.25F + s3 * 0.15F
            val cx = baseX + radius * cos(radians)
            val cz = baseZ + radius * sin(radians)
            val cy = baseY + s2 * entityHeight
            val scale = 0.18F * eased
            if (anim * 0.15F > 0.001F && scale > 0.0001F) {
                drawBillboard(ctx, bloomTexture, cx, cy, cz, scale * 5.5F, alphaColor(color, anim * 0.15F))
                drawBillboard(ctx, bloomTexture, cx, cy, cz, scale * 3.5F, alphaColor(color, anim * 0.25F))
            }
        }
    }

    private fun appendCrystalShape(
        stack: PoseStack,
        consumer: VertexConsumer,
        x: Float,
        y: Float,
        z: Float,
        scale: Float,
        yaw: Float,
        pitch: Float,
        sharp: Boolean,
        color: Int
    ) {
        stack.push()
        try {
            stack.translate(x.toDouble(), y.toDouble(), z.toDouble())
            stack.multiply(Axis.YP.rotationDegrees(-yaw))
            stack.multiply(Axis.ZP.rotationDegrees(pitch))
            stack.scale(scale, scale, scale)
            val matrix = stack.last().pose()
            val width = if (sharp) 0.35F else 0.7F
            val halfHeight = if (sharp) 1.2F else 1.0F
            tri(consumer, matrix, halfHeight, 0.0F, 0.0F, 0.0F, width, 0.0F, 0.0F, 0.0F, width, color)
            tri(consumer, matrix, halfHeight, 0.0F, 0.0F, 0.0F, 0.0F, width, 0.0F, -width, 0.0F, color)
            tri(consumer, matrix, halfHeight, 0.0F, 0.0F, 0.0F, -width, 0.0F, 0.0F, 0.0F, -width, color)
            tri(consumer, matrix, halfHeight, 0.0F, 0.0F, 0.0F, 0.0F, -width, 0.0F, width, 0.0F, color)
            tri(consumer, matrix, -halfHeight, 0.0F, 0.0F, 0.0F, width, 0.0F, 0.0F, 0.0F, width, color)
            tri(consumer, matrix, -halfHeight, 0.0F, 0.0F, 0.0F, 0.0F, width, 0.0F, -width, 0.0F, color)
            tri(consumer, matrix, -halfHeight, 0.0F, 0.0F, 0.0F, -width, 0.0F, 0.0F, 0.0F, -width, color)
            tri(consumer, matrix, -halfHeight, 0.0F, 0.0F, 0.0F, 0.0F, -width, 0.0F, width, 0.0F, color)
        } finally {
            stack.pop()
        }
    }

    private fun renderCubes(ctx: RenderContext) {
        if (cubeParticles.isEmpty()) {
            return
        }

        val faceConsumer = ctx.provider.getBuffer(ctx.cubeFaceLayer())
        val lineConsumer = ctx.provider.getBuffer(ctx.cubeLineLayer())
        val accent = ctx.frame.accentColor

        for (particle in cubeParticles) {
            val alive = ctx.frame.nowMs - particle.createdMs
            val alpha = cubeAlpha(alive)
            if (alpha <= 0.001F) {
                continue
            }
            val targetPos = particle.target.getPosition(ctx.frame.partialTicks)
            val x = targetPos.x + particle.offsetX - ctx.frame.cameraPos.x
            val y = targetPos.y + particle.offsetY - ctx.frame.cameraPos.y
            val z = targetPos.z + particle.offsetZ - ctx.frame.cameraPos.z
            val rotation = alive.toFloat() / 10.0F
            ctx.stack.push()
            try {
                ctx.stack.translate(x, y, z)
                ctx.stack.multiply(Axis.XP.rotationDegrees(rotation))
                ctx.stack.multiply(Axis.YP.rotationDegrees(rotation))
                ctx.stack.multiply(Axis.ZP.rotationDegrees(rotation))
                val matrix = ctx.stack.last().pose()
                appendCubeFaces(faceConsumer, matrix, CUBE_HALF_EXTENT, alphaColor(accent, alpha / 12.0F))
                appendCubeEdges(lineConsumer, matrix, CUBE_HALF_EXTENT, alphaColor(accent, alpha / 4.0F))
            } finally {
                ctx.stack.pop()
            }
            drawBillboard(ctx, bloomTexture, x, y, z, 0.9F, alphaColor(accent, alpha * 0.12F), depth = true)
        }
    }

    private fun renderCircle(ctx: RenderContext, target: LivingEntity) {
        val player = mc.player ?: return
        if (!circleThroughWalls && !player.canSee(target)) {
            return
        }
        val progress = easeOutCubic(ctx.frame.circleProgress)
        if (progress <= 0.001F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val radius = target.bbWidth * 0.9F
        val spriteSize = 0.12F
        val absSin = abs(1.0F + sin(circleStep)) / 2.0F
        val y = pos.y + absSin * target.bbHeight - ctx.frame.cameraPos.y
        val cx = pos.x - ctx.frame.cameraPos.x
        val cz = pos.z - ctx.frame.cameraPos.z

        for (point in 0 until 72) {
            val pointAngle = point * (360.0F / 72.0F)
            val radians = Math.toRadians(pointAngle.toDouble())
            val x = cx + cos(radians) * radius
            val z = cz + sin(radians) * radius
            var color = if (circleRainbow) {
                val timeFactor = (ctx.frame.nowMs % 3000L).toFloat() / 3000.0F
                hsbRgb((timeFactor * 360.0F + pointAngle) / 360.0F, 0.7F, 1.0F)
            } else {
                ctx.frame.accentColor
            }
            if (circleDamageRed) {
                color = mixColor(color, rgba(255, 50, 50, 255), circleDamageFlashIntensity)
            }
            drawBillboard(ctx, bloomTexture, x, y, z, spriteSize * progress * 4.0F, alphaColor(color, progress * 0.15F))
            drawBillboard(ctx, bloomTexture, x, y, z, spriteSize * progress * 2.0F, alphaColor(color, progress * 0.35F))
            drawBillboard(ctx, bloomTexture, x, y, z, spriteSize * progress * 0.8F, alphaColor(brighten(color, 1.5F, 255), progress))
        }
    }

    private fun renderGhosts2(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.generalAnimation
        if (anim <= 0.001F) {
            ghostTrails.forEach { it.clear() }
            return
        }

        val targetPos = target.getPosition(ctx.frame.partialTicks)
        val baseX = targetPos.x - ctx.frame.cameraPos.x
        val baseY = targetPos.y - ctx.frame.cameraPos.y
        val baseZ = targetPos.z - ctx.frame.cameraPos.z
        val speed = ctx.frame.nowMs * 0.005
        val radius = target.bbWidth + 0.15

        for (ghost in 0 until GHOST_COUNT) {
            val phase = ghost * (TWO_PI / GHOST_COUNT)
            val x = sin(speed + phase) * radius
            val z = cos(speed + phase) * radius
            val verticalDirection = if (ghost % 2 == 0) 1.0 else -1.0
            val yOscillation = sin((speed + phase) * 0.8) * verticalDirection
            val y = yOscillation * target.bbHeight * 0.55 + target.bbHeight * 0.5
            val trail = ghostTrails[ghost]
            trail.add(0, RelativePoint(x, y, z))
            while (trail.size > GHOST_TRAIL_LIMIT) {
                trail.removeAt(trail.lastIndex)
            }

            for (segmentIndex in trail.indices) {
                val segment = trail[segmentIndex]
                val fade = 1.0F - segmentIndex.toFloat() / trail.size.toFloat()
                val baseScale = if (segmentIndex == 0) 0.34F else 0.21F
                val scale = baseScale * fade * anim
                val alpha = (fade.toDouble().pow(1.8).toFloat() * anim).coerceIn(0.0F, 1.0F)
                drawBillboard(
                    ctx,
                    bloomTexture,
                    baseX + segment.x,
                    baseY + segment.y,
                    baseZ + segment.z,
                    scale,
                    alphaColor(ctx.frame.accentColor, alpha)
                )
            }

            drawBillboard(
                ctx,
                bloomTexture,
                baseX + x,
                baseY + y,
                baseZ + z,
                0.2F * anim,
                alphaColor(rgba(255, 255, 255, 255), anim)
            )
        }
    }

    private fun renderCelestial(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.celestialAnimation
        if (anim <= 0.001F) {
            return
        }

        val targetPos = target.getPosition(ctx.frame.partialTicks)
        val baseX = targetPos.x - ctx.frame.cameraPos.x
        val baseY = targetPos.y - ctx.frame.cameraPos.y
        val baseZ = targetPos.z - ctx.frame.cameraPos.z
        val horizontalTime = ctx.frame.nowMs / 250.0
        val verticalTime = ctx.frame.nowMs / 400.0
        var radius = 0.65

        for (ghost in 0 until 4) {
            for (point in 0 until 20) {
                val progress = point / 20.0F
                val ease = 1.0F - progress * 0.6F
                val delayedHorizontal = horizontalTime - point * 0.06
                val delayedVertical = verticalTime - point * 0.06
                val cycle = (sin(delayedVertical) + 1.0) * 0.5
                val angle = Math.toRadians(ghost * 90.0 + (delayedHorizontal * 55.0) % 360.0)
                val x = cos(angle) * radius
                val z = sin(angle) * radius
                val y = if (ghost % 2 == 0) 0.15 + 1.65 * cycle else 1.75 - 1.65 * cycle
                val scale = anim * (0.9F * ease) * 0.8F
                drawBillboard(ctx, bloomTexture, baseX + x, baseY + y, baseZ + z, scale, alphaColor(ctx.frame.accentColor, anim))
            }
            radius *= -1.0
        }
    }

    private fun renderSoul2(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.soulAnimation
        if (anim <= 0.001F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val centerX = pos.x - ctx.frame.cameraPos.x
        val centerY = pos.y + target.bbHeight / 2.0F - ctx.frame.cameraPos.y
        val centerZ = pos.z - ctx.frame.cameraPos.z
        val elapsed = (ctx.frame.nowMs - modeTimestampMs) / 1100.0
        val rotation = elapsed * 360.0
        val radius = 0.5F

        for (layer in 0 until 4) {
            val layerOffset = (layer - 1) * 0.4F
            var previousSize = 0.0F
            for (point in 0 until 130) {
                val angle = rotation + point + layerOffset * 360.0F
                val radians = Math.toRadians(-angle)
                val yOffset = sin(radians + 2.0) * layerOffset
                var size = radius * (point / 140.0F)
                size = (size + previousSize) / 2.0F
                previousSize = size
                val finalSize = size * anim
                val alphaFactor = finalSize.coerceIn(0.0F, 1.0F)
                val alpha = min(255.0F, 600.0F * anim * alphaFactor) / 255.0F
                appendSoul2Quad(
                    ctx,
                    centerX,
                    centerY + yOffset,
                    centerZ,
                    cos(radians).toFloat() * radius,
                    sin(radians).toFloat() * radius,
                    finalSize,
                    alphaColor(ctx.frame.accentColor, alpha)
                )
            }
        }
    }

    private fun renderSoulTest3(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.soulAnimation
        if (anim <= 0.001F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val period = 1500L
        val cycleTime = ctx.frame.nowMs.floorMod(period)
        val ascending = cycleTime > period / 2L
        var progress = cycleTime.toFloat() / (period / 2.0F)
        progress = if (ascending) {
            progress - 1.0F
        } else {
            1.0F - progress
        }
        progress = if (progress < 0.5F) {
            2.0F * progress * progress
        } else {
            1.0F - (-2.0F * progress + 2.0F).pow(2.0F) / 2.0F
        }
        val yOffset = target.bbHeight / 2.0F *
            (if (progress > 0.5F) 1.0F - progress else progress) *
            if (ascending) -1.0F else 1.0F

        val baseX = pos.x - ctx.frame.cameraPos.x
        val baseY = pos.y + target.bbHeight * progress + yOffset - ctx.frame.cameraPos.y
        val baseZ = pos.z - ctx.frame.cameraPos.z
        val timeMs = (ctx.frame.nowMs - modeTimestampMs) / 2.5

        for (layer in 0 until 4) {
            for (point in 0 until 15) {
                val particleProgress = point / 14.0F
                val size = (0.5F * (1.0F - particleProgress) + 0.5F * particleProgress) * anim
                val angle = 0.2 * (timeMs + soulTest3Value - point * 3.5) / 15.0
                val wavePoint = if (particleProgress < 0.5F) particleProgress * 2.0F else (1.0F - particleProgress) * 2.0F
                val amplitude = sin(wavePoint * PI).toFloat() * 2.0F
                val offset = soulTest3Offsets[point]
                val ox = offset.x * amplitude * anim - offset.x * amplitude
                val oy = offset.y * amplitude * anim - offset.y * amplitude
                val oz = offset.z * amplitude * anim - offset.z * amplitude
                val radius = 0.7
                val (x, y, z) = when (layer) {
                    0 -> Triple(cos(angle) * radius + ox, oy, sin(angle) * radius + oz)
                    1 -> Triple(-sin(angle) * radius + ox, oy, cos(angle) * radius + oz)
                    2 -> Triple(-cos(angle) * radius + ox, oy, -sin(angle) * radius + oz)
                    else -> Triple(sin(angle) * radius + ox, oy, -cos(angle) * radius + oz)
                }
                val alpha = min(255.0F, 400.0F * anim) / 255.0F
                drawBillboard(
                    ctx,
                    bloomTexture,
                    baseX + x,
                    baseY + y,
                    baseZ + z,
                    size * 0.5F,
                    alphaColor(ctx.frame.accentColor, alpha),
                    flipU = true
                )
            }
        }
    }

    private fun renderCrystal3DD(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.soulAnimation
        if (anim <= 0.01F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val baseX = pos.x - ctx.frame.cameraPos.x
        val baseY = pos.y - ctx.frame.cameraPos.y
        val baseZ = pos.z - ctx.frame.cameraPos.z
        val width = target.bbWidth * 1.5F
        val bodyConsumer = ctx.provider.getBuffer(ctx.crystalBodyLayer())
        val baseColor = ctx.frame.accentColor
        val top = shadeColor(baseColor, 60, (anim * 255.0F).roundToInt())
        val side1 = shadeColor(baseColor, 30, (anim * 255.0F).roundToInt())
        val side2 = shadeColor(baseColor, 0, (anim * 255.0F).roundToInt())
        val bottom = shadeColor(baseColor, -30, (anim * 255.0F).roundToInt())
        val cw = 0.075F
        val ch = 0.2F

        for (index in 0 until 360 step 19) {
            val value = 1.2F - 0.5F * anim
            val angle = index + crystalMoving * 0.3F
            val radians = Math.toRadians(angle.toDouble())
            val x = sin(radians).toFloat() * width * value
            val z = cos(radians).toFloat() * width * value
            val heightPercent = ((index / 20.0F) * 0.6180339F) % 1.0F
            val y = target.bbHeight * heightPercent
            ctx.stack.push()
            try {
                ctx.stack.translate((baseX + x).toDouble(), (baseY + y).toDouble(), (baseZ + z).toDouble())
                val dir = Vector3f(-x, 0.0F, -z)
                if (dir.lengthSquared() > 0.0001F) {
                    dir.normalize()
                    ctx.stack.multiply(Quaternionf().rotationTo(Vector3f(0.0F, 1.0F, 0.0F), dir))
                }
                appendJavelinCrystal(bodyConsumer, ctx.stack.last().pose(), cw, ch, top, side1, side2, bottom)
            } finally {
                ctx.stack.pop()
            }
            drawBillboard(
                ctx,
                bloomTexture,
                baseX + x,
                baseY + y,
                baseZ + z,
                0.15F * anim,
                alphaColor(baseColor, 100.0F / 255.0F * anim)
            )
        }
    }

    private fun renderSouls(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.soulsAnimation
        if (anim <= 0.001F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val baseX = pos.x - ctx.frame.cameraPos.x
        val baseY = pos.y - ctx.frame.cameraPos.y
        val baseZ = pos.z - ctx.frame.cameraPos.z
        val phase = (ctx.frame.nowMs % 100000L).toFloat() / soulsSpeed
        val radius = target.bbWidth * 1.5F
        val multiplier = max(0.5F, 1.2F - 0.5F * anim)
        var wormTick = 0
        var cooldown = 0

        var index = 0
        while (index < 360) {
            if (cooldown > 0) {
                cooldown -= 2
                index += 2
                continue
            }

            wormTick += 2
            if (wormTick > 50) {
                wormTick = 0
                cooldown = 100
                index += 2
                continue
            }

            val angle = Math.toRadians((index + phase).toDouble())
            val x = sin(angle).toFloat() * radius * multiplier
            val z = cos(angle).toFloat() * radius * multiplier
            val waveY = sin(Math.toRadians(index / 2.0 + phase / 5.0))
            val y = target.bbHeight / 1.5 + target.bbHeight / 3.0 * waveY
            val coreSize = 0.13F + 0.005F * wormTick
            val largeGlowSize = 0.7F + 0.005F * wormTick
            drawBillboard(ctx, bloomTexture, baseX + x, baseY + y, baseZ + z, largeGlowSize, alphaColor(ctx.frame.accentColor, anim * 0.05F))
            drawBillboard(ctx, bloomTexture, baseX + x, baseY + y, baseZ + z, coreSize, alphaColor(ctx.frame.accentColor, anim))
            index += 2
        }
    }

    private fun renderSouls2(ctx: RenderContext, target: LivingEntity) {
        val anim = ctx.frame.souls2Animation
        if (anim <= 0.001F) {
            return
        }

        val pos = target.getPosition(ctx.frame.partialTicks)
        val centerX = pos.x - ctx.frame.cameraPos.x
        val centerY = pos.y + target.bbHeight / 2.0F - ctx.frame.cameraPos.y
        val centerZ = pos.z - ctx.frame.cameraPos.z

        for (point in 0 until 20) {
            val trailFactor = 1.0F - point / 20.0F * 0.7F
            val angle = 0.15 * (ctx.frame.nowMs - point * 10L) / 25.0
            val size = 0.285F * trailFactor
            val paths = arrayOf(
                RelativePoint(sin(angle) * 0.7, cos(angle) * 0.7, -cos(angle) * 0.7),
                RelativePoint(-sin(angle) * 0.7, sin(angle) * 0.7, -cos(angle) * 0.7),
                RelativePoint(-sin(angle) * 0.7, -sin(angle) * 0.7, cos(angle) * 0.7)
            )
            for (path in paths) {
                drawBillboard(ctx, bloomTexture, centerX + path.x, centerY + path.y, centerZ + path.z, size * 2.5F, alphaColor(ctx.frame.accentColor, anim * 0.3F))
                drawBillboard(ctx, bloomTexture, centerX + path.x, centerY + path.y, centerZ + path.z, size, alphaColor(ctx.frame.accentColor, anim))
            }
        }
    }

    private fun renderGhostV2(ctx: RenderContext, target: LivingEntity) {
        if (!isValidTarget(target)) {
            ghostV2Renderer.reset()
            return
        }

        ghostV2Renderer.render(ctx, target)
    }

    private fun drawBillboard(
        ctx: RenderContext,
        texture: Identifier,
        x: Double,
        y: Double,
        z: Double,
        size: Float,
        color: Int,
        depth: Boolean = false,
        flipU: Boolean = false
    ) {
        if (size <= 0.0001F || alpha(color) <= 0) {
            return
        }

        val layer = ctx.spriteLayer(texture, depth)
        val consumer = ctx.provider.getBuffer(layer)
        val matrix = ctx.stack.last().pose()
        val half = size * 0.5F
        val rx = ctx.right.x * half
        val ry = ctx.right.y * half
        val rz = ctx.right.z * half
        val ux = ctx.up.x * half
        val uy = ctx.up.y * half
        val uz = ctx.up.z * half
        val leftU = if (flipU) 1.0F else 0.0F
        val rightU = if (flipU) 0.0F else 1.0F

        textureVertex(consumer, matrix, x - rx + ux, y - ry + uy, z - rz + uz, leftU, 1.0F, color, ctx.entityTextureFormat)
        textureVertex(consumer, matrix, x + rx + ux, y + ry + uy, z + rz + uz, rightU, 1.0F, color, ctx.entityTextureFormat)
        textureVertex(consumer, matrix, x + rx - ux, y + ry - uy, z + rz - uz, rightU, 0.0F, color, ctx.entityTextureFormat)
        textureVertex(consumer, matrix, x - rx - ux, y - ry - uy, z - rz - uz, leftU, 0.0F, color, ctx.entityTextureFormat)
    }

    private fun appendSoul2Quad(
        ctx: RenderContext,
        x: Double,
        y: Double,
        z: Double,
        localX: Float,
        localZ: Float,
        size: Float,
        color: Int
    ) {
        if (size <= 0.0001F || alpha(color) <= 0) {
            return
        }

        ctx.stack.push()
        try {
            ctx.stack.translate(x, y, z)
            ctx.stack.multiply(ctx.camera.rotation)
            val consumer = ctx.provider.getBuffer(ctx.spriteLayer(bloomTexture, depth = false))
            val matrix = ctx.stack.last().pose()
            val half = size * 0.5F
            val left = localX - half
            val front = localZ - half
            textureVertex(consumer, matrix, left.toDouble(), -half.toDouble(), front.toDouble(), 0.0F, 1.0F, color, ctx.entityTextureFormat)
            textureVertex(consumer, matrix, (left + size).toDouble(), -half.toDouble(), front.toDouble(), 1.0F, 1.0F, color, ctx.entityTextureFormat)
            textureVertex(consumer, matrix, (left + size).toDouble(), half.toDouble(), front.toDouble(), 1.0F, 0.0F, color, ctx.entityTextureFormat)
            textureVertex(consumer, matrix, left.toDouble(), half.toDouble(), front.toDouble(), 0.0F, 0.0F, color, ctx.entityTextureFormat)
        } finally {
            ctx.stack.pop()
        }
    }

    private fun currentTarget(): LivingEntity? {
        if (!ModuleKillAura.running) {
            return null
        }
        val target = ModuleKillAura.targetTracker.target as? LivingEntity ?: return null
        if (!isValidTarget(target)) {
            return null
        }
        return target
    }

    private fun isValidTarget(target: LivingEntity): Boolean {
        val player = mc.player ?: return false
        if (target === player || target is ArmorStand || target.isRemoved || !target.isAlive) {
            return false
        }
        return isEntityStillInLevel(target)
    }

    private fun isEntityStillInLevel(entity: LivingEntity): Boolean {
        val level = mc.level ?: return false
        return entity.level() === level && !entity.isRemoved
    }

    private fun clearTargetRelativeState() {
        ghostTrails.forEach { it.clear() }
        ghostV2Renderer.reset()
        circleProgress = 0.0F
        circleDamageFlashIntensity = 0.0F
        circleLastHurtTime = 0
        soulTest3Value = 0.0F
        soulTest3PreviousNano = System.nanoTime()
    }

    private fun clearAllState() {
        lastTarget = null
        generalAnimation.reset()
        crystalsAnimation.reset()
        soulAnimation.reset()
        celestialAnimation.reset()
        soulsAnimation.reset()
        souls2Animation.reset()
        circleStep = 0.0F
        circleProgress = 0.0F
        circleLastHurtTime = 0
        circleDamageFlashStart = 0L
        circleDamageFlashIntensity = 0.0F
        lastCircleUpdateMs = 0L
        lastCubeUpdateMs = 0L
        cubeSpawnAccumulator = 0.0F
        cubeParticles.clear()
        ghostTrails.forEach { it.clear() }
        ghostV2Renderer.reset()
        modeTimestampMs = System.currentTimeMillis()
        soulTest3PreviousNano = System.nanoTime()
        soulTest3Value = 0.0F
        crystalMoving = 0.0F
        shaderWorldPassRendered = false
        shaderWorldPassDisabled = false
        shaderFailureLogged = false
        shaderMappingFallbackLogged = false
        normalFailureLogged = false
        fallbackFrame = null
        BabkaTargetEspPipelines.clearTextureCache()
    }

    private fun selectedAnimationValue(): Float = when (mode) {
        Mode.CRYSTALS, Mode.CRYSTALS_1 -> crystalsAnimation.value.toFloat()
        Mode.CIRCLE -> circleProgress
        Mode.GHOSTS2 -> generalAnimation.value.toFloat()
        Mode.GHOST_V2 -> 0.0F
        Mode.CELESTIAL -> celestialAnimation.value.toFloat()
        Mode.SOUL_2, Mode.SOUL_TEST_3, Mode.CRYSTAL_3DD -> soulAnimation.value.toFloat()
        Mode.SOULS -> soulsAnimation.value.toFloat()
        Mode.SOULS_2 -> souls2Animation.value.toFloat()
        Mode.CUBES -> 0.0F
    }

    private fun tri(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        x1: Float,
        y1: Float,
        z1: Float,
        x2: Float,
        y2: Float,
        z2: Float,
        x3: Float,
        y3: Float,
        z3: Float,
        color: Int
    ) {
        colorVertex(consumer, matrix, x1, y1, z1, color)
        colorVertex(consumer, matrix, x2, y2, z2, color)
        colorVertex(consumer, matrix, x3, y3, z3, color)
    }

    private fun appendCubeFaces(consumer: VertexConsumer, matrix: Matrix4f, size: Float, color: Int) {
        val vertices = floatArrayOf(
            -size, -size, size, size, -size, size, size, size, size, -size, size, size,
            size, -size, -size, -size, -size, -size, -size, size, -size, size, size, -size,
            -size, size, size, size, size, size, size, size, -size, -size, size, -size,
            -size, -size, -size, size, -size, -size, size, -size, size, -size, -size, size,
            size, -size, size, size, -size, -size, size, size, -size, size, size, size,
            -size, -size, -size, -size, -size, size, -size, size, size, -size, size, -size
        )
        var i = 0
        while (i < vertices.size) {
            colorVertex(consumer, matrix, vertices[i], vertices[i + 1], vertices[i + 2], color)
            i += 3
        }
    }

    private fun appendCubeEdges(consumer: VertexConsumer, matrix: Matrix4f, size: Float, color: Int) {
        val corners = arrayOf(
            floatArrayOf(-size, -size, -size), floatArrayOf(size, -size, -size),
            floatArrayOf(size, size, -size), floatArrayOf(-size, size, -size),
            floatArrayOf(-size, -size, size), floatArrayOf(size, -size, size),
            floatArrayOf(size, size, size), floatArrayOf(-size, size, size)
        )
        val edges = intArrayOf(0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7, 7, 4, 0, 4, 1, 5, 2, 6, 3, 7)
        var i = 0
        while (i < edges.size) {
            val a = corners[edges[i]]
            val b = corners[edges[i + 1]]
            val normal = Vector3f(b[0] - a[0], b[1] - a[1], b[2] - a[2])
            if (normal.lengthSquared() > 0.0F) {
                normal.normalize()
                matrix.transformDirection(normal)
                if (normal.lengthSquared() > 0.0F) {
                    normal.normalize()
                }
            } else {
                normal.set(0.0F, 1.0F, 0.0F)
            }
            lineVertex(consumer, matrix, a[0], a[1], a[2], color, normal)
            lineVertex(consumer, matrix, b[0], b[1], b[2], color, normal)
            i += 2
        }
    }

    private fun appendJavelinCrystal(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        cw: Float,
        ch: Float,
        top: Int,
        side1: Int,
        side2: Int,
        bottom: Int
    ) {
        val ex = floatArrayOf(cw, 0.0F, -cw, 0.0F)
        val ez = floatArrayOf(0.0F, cw, 0.0F, -cw)
        for (i in 0 until 4) {
            val next = (i + 1) % 4
            val color = if (i % 2 == 0) top else side1
            tri(consumer, matrix, 0.0F, ch, 0.0F, ex[i], 0.0F, ez[i], ex[next], 0.0F, ez[next], color)
        }
        for (i in 0 until 4) {
            val next = (i + 1) % 4
            val color = if (i % 2 == 0) bottom else side2
            tri(consumer, matrix, 0.0F, -ch, 0.0F, ex[next], 0.0F, ez[next], ex[i], 0.0F, ez[i], color)
        }
    }

    private fun colorVertex(consumer: VertexConsumer, matrix: Matrix4f, x: Float, y: Float, z: Float, color: Int) {
        consumer.addVertex(matrix, x, y, z).setColor(color)
    }

    private fun lineVertex(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        x: Float,
        y: Float,
        z: Float,
        color: Int,
        normal: Vector3f
    ) {
        consumer.addVertex(matrix, x, y, z)
            .setColor(color)
            .setNormal(normal.x, normal.y, normal.z)
            .setLineWidth(1.0F)
    }

    private fun textureVertex(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        x: Double,
        y: Double,
        z: Double,
        u: Float,
        v: Float,
        color: Int,
        entityFormat: Boolean
    ) {
        val vertex = consumer.addVertex(matrix, x.toFloat(), y.toFloat(), z.toFloat())
            .setUv(u, v)
            .setColor(color)
        if (entityFormat) {
            vertex.setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(FULL_BRIGHT)
                .setNormal(0.0F, 1.0F, 0.0F)
        }
    }

    private fun clientColor(): Int = (ClientTheme.accentA and 0x00FFFFFF) or (255 shl 24)

    private fun vCoreGhostV2Color(nowMs: Long): Int {
        var angle = ((nowMs / 10.0 + 0.0) % 360.0).toInt()
        angle = if (angle >= 180) {
            360 - angle
        } else {
            angle
        }
        angle *= 2
        val factor = (angle / 360.0F).coerceIn(0.0F, 1.0F)

        fun channel(from: Int, to: Int): Int =
            (from + (to - from) * factor).toInt().coerceIn(0, 255)

        val first = ClientTheme.accentA
        val second = ClientTheme.accentB
        return rgba(
            channel(red(first), red(second)),
            channel(green(first), green(second)),
            channel(blue(first), blue(second)),
            channel(alpha(first), alpha(second))
        )
    }

    private fun id(path: String) = Identifier.fromNamespaceAndPath("liquidbounce", path)

    private fun cubeAlpha(alive: Long): Float = when {
        alive < 200L -> alive / 200.0F
        alive > 800L -> (CUBE_LIFETIME_MS - alive) / 200.0F
        else -> 1.0F
    }.coerceIn(0.0F, 1.0F)

    private fun easeOutCubic(value: Float): Float {
        val t = value - 1.0F
        return t * t * t + 1.0F
    }

    private fun lerp(delta: Float, start: Float, end: Float): Float = start + delta * (end - start)

    private fun randomRange(min: Double, max: Double): Double = min + random.nextDouble() * (max - min)

    private fun rgba(r: Int, g: Int, b: Int, a: Int): Int =
        (a.coerceIn(0, 255) shl 24) or
            (r.coerceIn(0, 255) shl 16) or
            (g.coerceIn(0, 255) shl 8) or
            b.coerceIn(0, 255)

    private fun red(color: Int) = color shr 16 and 255

    private fun green(color: Int) = color shr 8 and 255

    private fun blue(color: Int) = color and 255

    private fun alpha(color: Int) = color ushr 24 and 255

    private fun alphaColor(color: Int, alpha: Float): Int =
        rgba(red(color), green(color), blue(color), (alpha.coerceIn(0.0F, 1.0F) * 255.0F).roundToInt())

    private fun brighten(color: Int, multiplier: Float, alpha: Int): Int =
        rgba(
            (red(color) * multiplier).roundToInt().coerceAtMost(255),
            (green(color) * multiplier).roundToInt().coerceAtMost(255),
            (blue(color) * multiplier).roundToInt().coerceAtMost(255),
            alpha
        )

    private fun shadeColor(color: Int, offset: Int, alpha: Int): Int =
        rgba(red(color) + offset, green(color) + offset, blue(color) + offset, alpha)

    private fun mixColor(from: Int, to: Int, factor: Float): Int {
        val f = factor.coerceIn(0.0F, 1.0F)
        return rgba(
            lerp(f, red(from).toFloat(), red(to).toFloat()).roundToInt(),
            lerp(f, green(from).toFloat(), green(to).toFloat()).roundToInt(),
            lerp(f, blue(from).toFloat(), blue(to).toFloat()).roundToInt(),
            alpha(from)
        )
    }

    private fun hsbRgb(hue: Float, saturation: Float, brightness: Float): Int {
        val h = (hue - floor(hue)) * 6.0F
        val f = h - floor(h)
        val p = brightness * (1.0F - saturation)
        val q = brightness * (1.0F - saturation * f)
        val t = brightness * (1.0F - saturation * (1.0F - f))
        val (r, g, b) = when (h.toInt()) {
            0 -> Triple(brightness, t, p)
            1 -> Triple(q, brightness, p)
            2 -> Triple(p, brightness, t)
            3 -> Triple(p, q, brightness)
            4 -> Triple(t, p, brightness)
            else -> Triple(brightness, p, q)
        }
        return rgba((r * 255.0F).roundToInt(), (g * 255.0F).roundToInt(), (b * 255.0F).roundToInt(), 255)
    }

    private fun Long.floorMod(modulus: Long): Long = ((this % modulus) + modulus) % modulus

    private fun migrateRemovedMode(name: String): Mode? =
        if (name == "Custom" || name.startsWith("Custom:") || name == "Skull" || name == "Soul Test") {
            Mode.CRYSTALS
        } else {
            null
        }

    enum class Mode(
        override val choiceName: String,
        override val choiceAliases: Array<String> = emptyArray()
    ) : NamedChoice {
        CRYSTALS("Crystals", arrayOf("Rhomb", "ThunderHackV2", "Chain")),
        CRYSTALS_1("Crystals 1"),
        CUBES("Cubes"),
        CIRCLE("Circle"),
        GHOSTS2("Ghosts2"),
        GHOST_V2("Ghost V2"),
        CELESTIAL("Celestial"),
        SOUL_2("Soul 2"),
        SOUL_TEST_3("Soul Test 3"),
        CRYSTAL_3DD("Crystal 3DD"),
        SOULS("Souls", arrayOf("Души")),
        SOULS_2("Souls 2", arrayOf("Души 2"))
    }

    private enum class RenderPass {
        NORMAL,
        SHADER_WORLD
    }

    private enum class BabkaEasing {
        EXPO_OUT,
        CUBIC_OUT
    }

    private class BabkaAnimation(
        private val durationMs: Long,
        private val easing: BabkaEasing
    ) {
        var value = 0.0
            private set
        private var targetValue = 0.0
        private var startValue = 0.0
        private var startTime = System.currentTimeMillis()

        fun run(newTarget: Double, now: Long = System.currentTimeMillis()): Double {
            update(now)
            if (newTarget != targetValue) {
                startValue = value
                targetValue = newTarget
                startTime = now
            }
            return update(now)
        }

        fun reset() {
            value = 0.0
            targetValue = 0.0
            startValue = 0.0
            startTime = System.currentTimeMillis()
        }

        private fun update(now: Long): Double {
            val elapsed = (now - startTime).coerceAtLeast(0L)
            if (elapsed >= durationMs) {
                value = targetValue
                return value
            }
            val progress = elapsed.toDouble() / durationMs.toDouble()
            val eased = when (easing) {
                BabkaEasing.EXPO_OUT -> if (progress >= 1.0) 1.0 else -(2.0.pow(-10.0 * progress)) + 1.0
                BabkaEasing.CUBIC_OUT -> {
                    val t = progress - 1.0
                    t * t * t + 1.0
                }
            }
            value = startValue + (targetValue - startValue) * eased
            return value
        }
    }

    private class VCoreGhostV2Renderer {
        private val particles = ArrayList<VCoreGhostV2Particle>(GHOST_V2_PARTICLE_COUNT)
        private val moving = InfinityAnimation()
        private val targetEspAnim = Animation()
            .setEasing(Easing.TARGETESP_EASE_OUT_BACK)
            .setSpeed(GHOST_V2_HIT_DURATION_MS.toInt())
            .setSize(1.0F)
            .setForward(false)

        fun render(ctx: RenderContext, target: LivingEntity) {
            if (!target.isAlive || target.isRemoved) {
                reset()
                return
            }

            spawnParticleIfNeeded(target)

            val fps = max(mc.currentFps, 5)
            val fpsFactor = 500.0F / fps.toFloat()
            moving.animate(moving.get() + GHOST_V2_MOVING_STEP, GHOST_V2_MOVING_DURATION_MS.toInt())
            targetEspAnim.setForward(target.hurtTime > 7)

            val movingValue = moving.get()
            val hitAnimationFactor = targetEspAnim.get()
            val color = vCoreGhostV2Color(ctx.frame.nowMs)

            particles.forEachIndexed { index, particle ->
                updateParticle(index, particle, target, movingValue, hitAnimationFactor, fpsFactor)
                particle.tick(fps)
                particle.render(ctx, color)
            }
        }

        fun reset() {
            particles.clear()
            moving.getAnimation().reset()
            targetEspAnim.reset()
        }

        private fun spawnParticleIfNeeded(target: LivingEntity) {
            val desiredCount = max(1, GHOST_V2_PARTICLE_COUNT)
            if (particles.size < desiredCount) {
                particles += VCoreGhostV2Particle(target.position(), GHOST_V2_PARTICLE_SIZE)
            }
            while (particles.size > desiredCount) {
                particles.removeAt(particles.lastIndex)
            }
        }

        private fun updateParticle(
            index: Int,
            particle: VCoreGhostV2Particle,
            target: LivingEntity,
            movingValue: Float,
            hitAnimationFactor: Float,
            fpsFactor: Float
        ) {
            val segments = max(1, GHOST_V2_PARTICLE_COUNT)
            val angleOffset = index * 360.0F / segments.toFloat()
            val currentAngle = movingValue + angleOffset
            val rad = Math.toRadians(currentAngle.toDouble())
            val box = target.boundingBox
            val hitboxWidth = max(box.xsize, box.zsize).toFloat()
            val baseRadius = hitboxWidth * 0.5F + GHOST_V2_PARTICLE_SIZE * 0.25F
            val dynamicRadius = baseRadius - baseRadius * hitAnimationFactor
            val offsetX = sin(rad).toFloat() * dynamicRadius
            val offsetZ = cos(rad).toFloat() * dynamicRadius
            val verticalAmplitude = (box.ysize * 0.5).toFloat()
            val verticalCenter = verticalAmplitude - 0.7F
            val verticalSwing = sin(Math.toRadians((movingValue / (index + 1.0F)).toDouble())).toFloat() *
                verticalAmplitude
            val targetPos = target.position()
            val desiredPosition = targetPos.add(
                offsetX.toDouble(),
                (verticalCenter + verticalSwing).toDouble(),
                offsetZ.toDouble()
            )
            val mul = 0.05F * fpsFactor
            particle.motion = desiredPosition.subtract(particle.position).scale(mul.toDouble())
        }
    }

    private class VCoreGhostV2Particle(
        position: Vec3,
        private val size: Float
    ) {
        var previousPosition: Vec3 = position
            private set
        var position: Vec3 = position
            private set
        var motion: Vec3 = Vec3.ZERO
        private val tail = ArrayList<Vector4f>(64)

        fun tick(fps: Int) {
            previousPosition = position
            val currentPosition = position.add(motion)
            position = currentPosition
            addTrailPoints(previousPosition, currentPosition)

            val safeFps = max(fps, 5)
            val deltaY = 0.004F / safeFps.toFloat() * 300.0F
            val deltaLife = 0.3F / safeFps.toFloat() * 300.0F
            val iterator = tail.iterator()
            while (iterator.hasNext()) {
                val point = iterator.next()
                point.y += deltaY
                point.w -= deltaLife
                if (point.w <= 0.0F) {
                    iterator.remove()
                }
            }
            motion = motion.scale((0.95F / safeFps.toFloat() * 300.0F).toDouble())
        }

        fun render(ctx: RenderContext, color: Int) {
            for (point in tail) {
                val progress = (point.w / GHOST_V2_TRAIL_LIFETIME).coerceIn(0.0F, 1.0F)
                if (progress <= 0.0F) {
                    continue
                }

                val miniSize = size * progress
                val baseAlpha = (progress * 255.0F).toInt().coerceIn(0, 255)
                val x = point.x.toDouble() - ctx.frame.cameraPos.x
                val y = point.y.toDouble() - ctx.frame.cameraPos.y
                val z = point.z.toDouble() - ctx.frame.cameraPos.z

                drawGhostLayer(ctx, x, y, z, miniSize * 2.6F, color, (baseAlpha * 0.11000001F).toInt())
                drawGhostLayer(ctx, x, y, z, miniSize * 1.8F, color, (baseAlpha * 0.23099999F).toInt())
                drawGhostLayer(ctx, x, y, z, miniSize, color, baseAlpha)
            }
        }

        private fun addTrailPoints(from: Vec3, to: Vec3) {
            val distance = from.distanceTo(to)
            if (distance <= 1.0E-4) {
                appendTrailPoint(to.x, to.y, to.z)
                return
            }

            val steps = (Math.ceil(distance / GHOST_V2_TRAIL_POINT_SPACING).toInt() + 1)
                .coerceIn(2, GHOST_V2_MAX_POINTS_PER_UPDATE)
            for (step in 1..steps) {
                val factor = step.toDouble() / steps.toDouble()
                appendTrailPoint(
                    from.x + (to.x - from.x) * factor,
                    from.y + (to.y - from.y) * factor,
                    from.z + (to.z - from.z) * factor
                )
            }
        }

        private fun appendTrailPoint(x: Double, y: Double, z: Double) {
            tail += Vector4f(
                x.toFloat(),
                (y + GHOST_V2_TRAIL_VERTICAL_OFFSET).toFloat(),
                z.toFloat(),
                GHOST_V2_TRAIL_LIFETIME
            )
        }

        private fun drawGhostLayer(
            ctx: RenderContext,
            x: Double,
            y: Double,
            z: Double,
            size: Float,
            color: Int,
            alpha: Int
        ) {
            drawBillboard(
                ctx,
                bloomTexture,
                x,
                y,
                z,
                size,
                rgba(red(color), green(color), blue(color), alpha.coerceIn(0, 255))
            )
        }
    }

    private class AnimationTimer {
        private var baseTime = System.currentTimeMillis()

        fun finished(duration: Long): Boolean = getElapsedTime() >= duration

        fun reset() {
            baseTime = System.currentTimeMillis()
        }

        fun setMillis(millis: Long) {
            baseTime = millis
        }

        fun getElapsedTime(): Long = System.currentTimeMillis() - baseTime
    }

    private enum class Easing {
        LINEAR,
        TARGETESP_EASE_OUT_BACK;

        fun apply(arg: Double): Double = when (this) {
            LINEAR -> arg
            TARGETESP_EASE_OUT_BACK ->
                1.0 + 3.70158 * (arg - 1.0).pow(3.0) + 2.70158 * (arg - 1.0).pow(2.0)
        }
    }

    private class Animation {
        private val timer = AnimationTimer()
        private var speed = 0
        private var size = 1.0
        private var forward = false
        private var easing = Easing.LINEAR

        fun finished(expectedForward: Boolean): Boolean =
            if (timer.finished(speed.toLong())) {
                if (expectedForward) forward else !forward
            } else {
                false
            }

        fun finished(): Boolean = timer.finished(speed.toLong()) && forward

        fun setForward(forward: Boolean): Animation {
            if (this.forward != forward) {
                this.forward = forward
                val remaining = size - min(size, timer.getElapsedTime().toDouble())
                timer.setMillis((System.currentTimeMillis() - remaining).toLong())
            }

            return this
        }

        fun finish(): Animation {
            timer.setMillis(System.currentTimeMillis() - speed)
            return this
        }

        fun setEasing(easing: Easing): Animation {
            this.easing = easing
            return this
        }

        fun setSpeed(speed: Int): Animation {
            this.speed = speed
            return this
        }

        fun setSize(size: Float): Animation {
            this.size = size.toDouble()
            return this
        }

        fun getLinear(): Float =
            if (forward) {
                if (timer.finished(speed.toLong())) {
                    size.toFloat()
                } else {
                    (timer.getElapsedTime().toDouble() / speed * size).toFloat()
                }
            } else {
                if (timer.finished(speed.toLong())) {
                    0.0F
                } else {
                    ((1.0 - timer.getElapsedTime().toDouble() / speed) * size).toFloat()
                }
            }

        fun get(): Float =
            if (forward) {
                if (timer.finished(speed.toLong())) {
                    size.toFloat()
                } else {
                    (easing.apply(timer.getElapsedTime().toDouble() / speed) * size).toFloat()
                }
            } else {
                if (timer.finished(speed.toLong())) {
                    0.0F
                } else {
                    ((1.0 - easing.apply(timer.getElapsedTime().toDouble() / speed)) * size).toFloat()
                }
            }

        fun reversed(): Float = 1.0F - get()

        fun reset() {
            timer.reset()
        }

        fun getTimer(): AnimationTimer = timer

        fun getSpeed(): Int = speed

        fun getSize(): Double = size

        fun getEasing(): Easing = easing

        fun isForward(): Boolean = forward
    }

    private class InfinityAnimation {
        private var output = 0.0F
        private var endpoint = 0.0F
        private var easing = Easing.LINEAR
        private var animation = Animation()
            .setSize(0.0F)
            .setSpeed(0)
            .setForward(false)
            .setEasing(easing)

        fun animate(destination: Float, ms: Int): Float {
            val duration = max(1, ms)
            output = endpoint - animation.get()
            endpoint = destination
            if (output != endpoint - destination) {
                animation = Animation()
                    .setSize(endpoint - output)
                    .setSpeed(duration)
                    .setForward(false)
                    .setEasing(easing)
            }

            return output
        }

        fun finished(): Boolean = output == endpoint || animation.finished() || animation.finished(false)

        fun get(): Float {
            output = endpoint - animation.get()
            return output
        }

        fun easing(easing: Easing): InfinityAnimation {
            this.easing = easing
            return this
        }

        fun getAnimation(): Animation = animation
    }

    private data class CubeParticle(
        val target: LivingEntity,
        var offsetX: Double,
        var offsetY: Double,
        var offsetZ: Double,
        val createdMs: Long
    )

    private data class RelativePoint(var x: Double, var y: Double, var z: Double) {
        fun set(nx: Double, ny: Double, nz: Double) {
            x = nx
            y = ny
            z = nz
        }

        fun distanceTo(other: RelativePoint): Double {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return sqrt(dx * dx + dy * dy + dz * dz)
        }
    }

    private data class PreparedFrame(
        val target: LivingEntity?,
        val liveTarget: LivingEntity?,
        val partialTicks: Float,
        val cameraPos: Vec3,
        val nowMs: Long,
        val nowNano: Long,
        val accentColor: Int,
        val circleProgress: Float,
        val generalAnimation: Float,
        val crystalsAnimation: Float,
        val soulAnimation: Float,
        val celestialAnimation: Float,
        val soulsAnimation: Float,
        val souls2Animation: Float
    )

    private class RenderContext(
        val frame: PreparedFrame,
        val stack: PoseStack,
        val provider: MultiBufferSource.BufferSource,
        val camera: Camera,
        val pass: RenderPass,
        val right: Vector3f,
        val up: Vector3f
    ) {
        val entityTextureFormat: Boolean
            get() = pass == RenderPass.SHADER_WORLD

        fun crystalBodyLayer(): RenderType =
            BabkaTargetEspPipelines.trianglesTranslucentNoDepth

        fun cubeFaceLayer(): RenderType =
            BabkaTargetEspPipelines.cubeFacesDepth

        fun cubeLineLayer(): RenderType =
            BabkaTargetEspPipelines.cubeLinesDepth

        fun spriteLayer(texture: Identifier, depth: Boolean): RenderType =
            if (pass == RenderPass.SHADER_WORLD) {
                RenderTypes.entityTranslucentEmissive(texture, false)
            } else if (depth) {
                BabkaTargetEspPipelines.texturedAdditiveDepth(texture)
            } else {
                BabkaTargetEspPipelines.texturedAdditiveNoDepth(texture)
            }
    }

    private object IrisShaderPack {
        private var loaded = false
        private var apiInstance: Any? = null
        private var inUseMethod: java.lang.reflect.Method? = null

        fun inUse(): Boolean {
            if (!loaded) {
                loaded = true
                runCatching {
                    val apiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi")
                    apiInstance = apiClass.getMethod("getInstance").invoke(null)
                    inUseMethod = apiClass.getMethod("isShaderPackInUse")
                }.onFailure {
                    apiInstance = null
                    inUseMethod = null
                }
            }

            val api = apiInstance ?: return false
            val method = inUseMethod ?: return false
            return runCatching { method.invoke(api) as? Boolean ?: false }.getOrDefault(false)
        }
    }
}
