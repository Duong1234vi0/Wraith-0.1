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
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldEntityRemoveEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.gui.drawRoundedTexture
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.utils.client.Timer
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.entity.age
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.eyePos
import net.ccbluex.liquidbounce.utils.entity.pos
import net.ccbluex.liquidbounce.utils.entity.standingEyeHeight
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvent
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec3
import java.lang.reflect.Method
import java.util.UUID
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * VegaLine FragEffects port.
 *
 * Original trigger model:
 *  - remember the currently attacked/aimed living entity for 1000ms;
 *  - when that same entity reaches zero health, play the strike animation, sound,
 *    timer slowdowns, camera flick and particle bursts.
 */
@Suppress("MagicNumber")
object ModuleFragEffects : ClientModule("FragEffects", Category.RENDER) {

    private val noMobsDetect by boolean("NoMobsDetect", true)

    private val strikeAnimation = VegaAnimation(0.0f, 0.0f, 0.03f)
    private val stateAnimation = VegaAnimation(0.0f, 0.0f, 0.03f)
    private val deathMemories = ArrayList<EntityDeathMemory>()
    private val fragParticles = ArrayList<FragParticle>()

    private var timerForce2 = false
    private var lastDeceasedBase: LivingEntity? = null
    private var lastDeceasedParticlePos: Vec3? = null
    private var hasLoadedDesaturate = false
    private var texturePrimed = false

    private val vignetteTexture = "vegaline/modules/frageffects/vignette.png"
        .registerAsDynamicImageFromClientResources()

    private val strikeSound = SoundEvent.createVariableRangeEvent(
        Identifier.fromNamespaceAndPath("liquidbounce", "frageffects_strike")
    )

    private val desaturateEffect = Identifier.fromNamespaceAndPath("liquidbounce", "frageffects_desaturate")

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        if (!event.isCancelled) {
            controllingAddingMemoryToEntity(event.entity as? LivingEntity)
        }
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (timerForce2) {
            Timer.requestTimerSpeed(0.1f, Priority.IMPORTANT_FOR_USAGE_2, this, resetAfterTicks = 1)
            timerForce2 = false
        }

        val targetEntities = ArrayList<LivingEntity>(3)

        (ModuleKillAura.targetTracker.target as? LivingEntity)?.let(targetEntities::add)
        ((mc.crosshairTarget as? EntityHitResult)?.entity as? LivingEntity)?.let(targetEntities::add)

        targetEntities
            .filterNot(::isDeathLikeState)
            .forEach(::controllingAddingMemoryToEntity)

        deathMemories.forEach(EntityDeathMemory::updateMemoryTrigger)
        deathMemories.removeIf(EntityDeathMemory::isNotValidMemory)
    }

    @Suppress("unused")
    private val entityRemoveHandler = handler<WorldEntityRemoveEvent> { event ->
        val removed = event.entity
        if (removed == mc.player) {
            return@handler
        }

        deathMemories
            .firstOrNull { it.isValidMemory() && it.matches(removed) }
            ?.triggerFromRemoval(removed)
    }

    @Suppress("unused")
    private val overlayHandler = handler<OverlayRenderEvent> { event ->
        if (!texturePrimed) {
            drawLightVignette(event, 1.0f, loadMode = true)
            texturePrimed = true
        }

        val alphaPC = getGlobalEffectAnimation(setup = true)
        if (alphaPC != 0.0f) {
            updateDesaturate(alphaPC >= 0.4f)
            drawLightVignette(event, alphaPC, loadMode = false)
        } else {
            updateDesaturate(false)
        }
    }

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val alphaPC = stateAnimation.updateAnim()
        if (alphaPC < 0.003f || fragParticles.isEmpty()) {
            return@handler
        }

        fragParticles.removeIf(FragParticle::toRemove)
        drawAllParticles(event, alphaPC)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetRuntime(clearParticles = true)
    }

    override fun enable() {
        timerForce2 = false
        texturePrimed = false
    }

    override fun disable() {
        resetRuntime(clearParticles = true)
    }

    @JvmStatic
    fun getStrikeEffectFovModifyPC(): Float = getGlobalEffectAnimation(setup = false)

    private fun resetRuntime(clearParticles: Boolean) {
        deathMemories.clear()
        timerForce2 = false
        lastDeceasedBase = null
        lastDeceasedParticlePos = null
        updateDesaturate(false)
        strikeAnimation.forceAnim(0.0f)
        strikeAnimation.to = 0.0f
        stateAnimation.forceAnim(0.0f)
        stateAnimation.to = 0.0f
        if (clearParticles) {
            fragParticles.clear()
        }
    }

    private fun updateStrikeAnimation(): Float {
        var value = 0.0f
        when (strikeAnimation.to) {
            1.0f -> {
                strikeAnimation.speed = 0.07f
                value = strikeAnimation.updateAnim()

                if (value > 0.998f) {
                    strikeAnimation.forceAnim(1.0f)
                    strikeAnimation.to = 0.0f
                    val base = lastDeceasedBase
                    if (base != null && !base.isRemoved) {
                        spawnAllParticles(base, 360, 2400.0f)
                        spawnAllParticles(base, 1200, 300.0f)
                    } else {
                        lastDeceasedParticlePos?.let { spawnPos ->
                            spawnAllParticlesAt(spawnPos, 360, 2400.0f)
                            spawnAllParticlesAt(spawnPos, 1200, 300.0f)
                        }
                    }
                    value = 1.0f
                }
            }

            0.0f -> {
                strikeAnimation.speed = 0.07f
                value = strikeAnimation.updateAnim()

                if (value < 0.01f && value != 0.0f) {
                    strikeAnimation.forceAnim(0.0f)
                }
            }
        }

        return value
    }

    private fun triggerStrikeAnimation() {
        strikeAnimation.to = 1.0f
        strikeAnimation.forceAnim(0.5f)
    }

    private fun playStrikeSound() {
        runCatching {
            mc.soundManager.play(SimpleSoundInstance.forUI(strikeSound, 1.0f, 0.45f))
        }
    }

    private fun doFlickToDeceased(deceased: Entity) {
        doFlickToPosition(bodyPos(deceased))
    }

    private fun doFlickToPosition(targetPos: Vec3) {
        val localPlayer = mc.player ?: return
        val rotations = rotationsTo(targetPos, localPlayer.eyePos)

        if (angleDifference(localPlayer.yRot, rotations.first) > 10.0f ||
            angleDifference(localPlayer.xRot, rotations.second) > 5.0f
        ) {
            localPlayer.yRot = rotations.first
            localPlayer.xRot = rotations.second
        }
    }

    private fun triggerMemory(memory: EntityDeathMemory, deceased: LivingEntity?) {
        triggerStrikeAnimation()
        playStrikeSound()
        doFlickToPosition(memory.lastKnownBodyPos)

        Timer.requestTimerSpeed(0.35f, Priority.IMPORTANT_FOR_USAGE_2, this, resetAfterTicks = 1)
        timerForce2 = true

        if (deceased != null && !deceased.isRemoved) {
            spawnAllParticles(deceased, 2400, 150.0f)
            spawnAllParticles(deceased, 3600, 450.0f)
            lastDeceasedBase = deceased
            lastDeceasedParticlePos = particleSpawnPos(deceased)
        } else {
            spawnAllParticlesAt(memory.lastKnownParticlePos, 2400, 150.0f)
            spawnAllParticlesAt(memory.lastKnownParticlePos, 3600, 450.0f)
            lastDeceasedBase = null
            lastDeceasedParticlePos = memory.lastKnownParticlePos
        }
    }

    private fun controllingAddingMemoryToEntity(baseTo: LivingEntity?) {
        val localPlayer = mc.player ?: return
        if (baseTo == null ||
            baseTo == localPlayer ||
            baseTo.age < 2 ||
            isDeathLikeState(baseTo) ||
            !shouldRememberEntity(baseTo)
        ) {
            return
        }

        var searchedMemory: EntityDeathMemory? = null
        for (memory in deathMemories) {
            if (memory.isValidMemory() && memory.matches(baseTo)) {
                searchedMemory = memory
            }
        }

        if (searchedMemory != null) {
            searchedMemory.resetMemory(baseTo, MAX_TIME_MEMORY_MS)
        } else if (deathMemories.isEmpty()) {
            deathMemories.add(
                EntityDeathMemory(baseTo, MAX_TIME_MEMORY_MS) { memory, deceased ->
                    triggerMemory(memory, deceased)
                }
            )
        }
    }

    private fun shouldRememberEntity(entity: LivingEntity): Boolean = entity is Player || !noMobsDetect

    private fun isDeathLikeState(entity: LivingEntity): Boolean {
        return entity.health <= 0.0f || entity.isDeadOrDying || !entity.isAlive || entity.isRemoved
    }

    private fun getGlobalEffectAnimation(setup: Boolean): Float {
        if (setup) {
            stateAnimation.to = if (running) 1.0f else 0.0f
        }

        var value = if (setup) {
            stateAnimation.updateAnim() * updateStrikeAnimation()
        } else {
            stateAnimation.anim * strikeAnimation.anim
        }

        if (value < 0.005f) {
            value = 0.0f
        }
        if (value > 0.995f) {
            value = 1.0f
        }

        return value
    }

    private fun updateDesaturate(setTrue: Boolean) {
        val gameRenderer = mc.gameRenderer

        if (setTrue) {
            if (!hasLoadedDesaturate) {
                hasLoadedDesaturate = invokeSetPostEffect(gameRenderer, desaturateEffect)
            }
            return
        }

        if (hasLoadedDesaturate) {
            if (gameRenderer.currentPostEffect() == desaturateEffect) {
                gameRenderer.clearPostEffect()
            }
            hasLoadedDesaturate = false
        }
    }

    private fun drawLightVignette(event: OverlayRenderEvent, alphaPC: Float, loadMode: Boolean) {
        val width = event.context.guiWidth().toFloat()
        val height = event.context.guiHeight().toFloat()
        val sideSetX = width * 0.5f * (1.0f - alphaPC) * (1.0f - alphaPC)
        val sideSetY = height * 0.5f * (1.0f - alphaPC) * (1.0f - alphaPC)
        val greenBlue = (255.0f * (1.0f - alphaPC / 1.25f)).roundToInt().coerceIn(0, 255)
        val alpha = (190.0f * alphaPC).roundToInt().coerceIn(0, 255)
        val color = if (loadMode) {
            0
        } else {
            argb(alpha, 255, greenBlue, greenBlue)
        }

        event.context.drawRoundedTexture(
            vignetteTexture,
            -sideSetX,
            -sideSetY,
            width + sideSetX * 2.0f,
            height + sideSetY * 2.0f,
            0.0f,
            color
        )
    }

    private fun spawnAllParticles(base: LivingEntity, count: Int, maxTime: Float) {
        spawnAllParticlesAt(particleSpawnPos(base), count, maxTime)
    }

    private fun spawnAllParticlesAt(spawnPos: Vec3, count: Int, maxTime: Float) {
        val startDst = 0.5f
        val yExt = startDst / 5.0f
        val yMaxRand = yExt / 2.0f

        repeat(count) { index ->
            val pc01 = index.toFloat() / count.toFloat()
            val yRandom = -yMaxRand / 2.0f + Math.random().toFloat() * yMaxRand
            val dstRandom = startDst / 2.0f + (-startDst / 40.0f + Math.random().toFloat() * startDst / 20.0f)
            val yExtRandom = yExt * Math.random().toFloat()

            fragParticles.add(
                FragParticle(
                    spawnPos.add(0.0, yRandom.toDouble(), 0.0),
                    pc01 * 360.0f,
                    startDst,
                    startDst + dstRandom,
                    36.0f * Math.random().toFloat(),
                    yExtRandom,
                    maxTime
                )
            )
        }
    }

    private fun bodyPos(entity: Entity): Vec3 =
        entity.pos.add(0.0, entity.bbHeight.toDouble() * 0.5, 0.0)

    private fun particleSpawnPos(base: LivingEntity): Vec3 =
        base.pos.add(0.0, base.standingEyeHeight.toDouble() / 2.0, 0.0)

    private fun drawAllParticles(event: WorldRenderEvent, alphaPC: Float) {
        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.enableDepthTest()
            RenderSystem.depthMask(false)
            RenderSystem.disableCull()

            val drawMode = if (strikeAnimation.to == 0.0f) {
                VertexFormat.Mode.TRIANGLES
            } else {
                VertexFormat.Mode.POINTS
            }

            val cameraPos = event.camera.position()
            matrixStack.pushPose()
            matrixStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z)
            try {
                drawCustomMesh(
                    drawMode,
                    DefaultVertexFormat.POSITION_COLOR,
                    ShaderProgramKeys.POSITION_COLOR
                ) { matrix ->
                    for (fragParticle in fragParticles) {
                        val particleAlpha = (min(fragParticle.get010PC() * 3.0f, 1.0f) *
                            fragParticle.getAlphaPC()).coerceIn(0.0f, 1.0f) * alphaPC
                        if (particleAlpha == 0.0f) {
                            continue
                        }

                        val color = particleColor(particleAlpha, fragParticle.randomFloat)
                        val pos = fragParticle.getPos()
                        vertex(matrix, pos.x.toFloat(), pos.y.toFloat(), pos.z.toFloat()).setColor(color)
                    }
                }
            } finally {
                matrixStack.popPose()
                RenderSystem.defaultBlendFunc()
                RenderSystem.depthMask(true)
                RenderSystem.enableCull()
            }
        }
    }

    private fun particleColor(alphaPC: Float, randomFloat: Float): Int {
        val alpha = (255.0f * alphaPC).roundToInt().coerceIn(0, 255)
        val red = (255.0f * randomFloat).roundToInt().coerceIn(0, 255)
        val greenBlue = (255.0f * alphaPC * randomFloat).roundToInt().coerceIn(0, 255)
        return argb(alpha, red, greenBlue, greenBlue)
    }

    private class EntityDeathMemory(
        base: LivingEntity,
        var maxTimeMemory: Long,
        private val onTrigger: (EntityDeathMemory, LivingEntity?) -> Unit
    ) {
        var base: LivingEntity? = base
            private set
        private var entityId: Int = base.id
        private var uuid: UUID? = base.uuid
        private var isPlayer: Boolean = base is Player
        var lastKnownBodyPos: Vec3 = bodyPos(base)
            private set
        var lastKnownParticlePos: Vec3 = particleSpawnPos(base)
            private set
        private var startTime = System.currentTimeMillis()
        private var hasTriggered = false

        fun resetMemory(base: LivingEntity, maxTimeMemory: Long) {
            if (!hasTriggered) {
                this.base = base
                this.maxTimeMemory = maxTimeMemory
                this.startTime = System.currentTimeMillis()
                updateSnapshot(base)
            }
        }

        fun matches(entity: Entity): Boolean =
            entity.id == entityId || uuid != null && uuid == entity.uuid

        fun isValidMemory(): Boolean {
            return base != null && maxTimeMemory != 0L && System.currentTimeMillis() - startTime < maxTimeMemory
        }

        fun isNotValidMemory(): Boolean = !isValidMemory()

        fun updateMemoryTrigger() {
            val trackedBase = base
            if (isValidMemory() && trackedBase != null && !hasTriggered) {
                updateSnapshot(trackedBase)

                if (isDeathLikeState(trackedBase)) {
                    trigger(trackedBase)
                }
            }
        }

        fun triggerFromRemoval(removed: Entity) {
            if (!isValidMemory() || hasTriggered || !matches(removed)) {
                return
            }

            (removed as? LivingEntity)?.let(::updateSnapshot)
            trigger(removed as? LivingEntity)
        }

        private fun trigger(deceased: LivingEntity?) {
            hasTriggered = true
            maxTimeMemory = 300L
            onTrigger(this, deceased)
        }

        private fun updateSnapshot(base: LivingEntity) {
            entityId = base.id
            uuid = base.uuid
            isPlayer = base is Player
            lastKnownBodyPos = bodyPos(base)
            lastKnownParticlePos = particleSpawnPos(base)
            this.base = base
        }
    }

    private class FragParticle(
        private val spawnPos: Vec3,
        private val startYaw: Float,
        private val startDst: Float,
        private val toDst: Float,
        additionYaw: Float,
        private val toY: Float,
        private val maxTime: Float
    ) {
        private val startTime = System.currentTimeMillis()
        private val toYaw = startYaw + additionYaw
        val randomFloat: Float = Math.random().toFloat()

        fun getTimePC(): Float {
            return ((System.currentTimeMillis() - startTime).toFloat() / maxTime).coerceIn(0.0f, 1.0f)
        }

        fun get010PC(): Float {
            val timePC = getTimePC()
            return (if (timePC > 0.5f) 1.0f - timePC else timePC) * 2.0f
        }

        fun getAlphaPC(): Float = 1.0f - getTimePC()

        fun toRemove(): Boolean = getTimePC() == 1.0f

        fun getPos(): Vec3 {
            val timePC = getTimePC()
            val yaw = lerp(startYaw, toYaw, timePC)
            val dst = lerp(startDst, toDst, timePC)
            val yawRadian = Math.toRadians(yaw.toDouble())
            return spawnPos.add(sin(yawRadian) * dst, (toY * timePC).toDouble(), cos(yawRadian) * dst)
        }
    }

    private class VegaAnimation(
        var anim: Float,
        var to: Float,
        var speed: Float
    ) {
        private var lastMs = System.currentTimeMillis()

        fun updateAnim(): Float {
            if (abs(anim - to) < 1.0E-4f) {
                anim = to
            }

            val count = (min((System.currentTimeMillis() - lastMs).toFloat(), 400.0f) / 5.0f).toInt()
            if (count > 0) {
                lastMs = System.currentTimeMillis()
            }

            repeat(count) {
                anim = lerp(anim, to, speed)
            }

            return anim
        }

        fun forceAnim(anim: Float) {
            this.anim = anim
            lastMs = System.currentTimeMillis()
        }
    }

    private fun rotationsTo(point: Vec3, from: Vec3): Pair<Float, Float> {
        val diff = point.subtract(from)
        val diffX = diff.x
        val diffY = diff.y
        val diffZ = diff.z
        val yaw = Mth.wrapDegrees(Math.toDegrees(atan2(diffZ, diffX)).toFloat() - 90.0f)
        val pitch = Mth.wrapDegrees(-Math.toDegrees(atan2(diffY, sqrt(diffX * diffX + diffZ * diffZ))).toFloat())
        return yaw to pitch
    }

    private fun angleDifference(current: Float, target: Float): Float = abs(Mth.wrapDegrees(current - target))

    private fun lerp(from: Float, to: Float, delta: Float): Float = from + (to - from) * delta

    private fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or
            (red.coerceIn(0, 255) shl 16) or
            (green.coerceIn(0, 255) shl 8) or
            blue.coerceIn(0, 255)
    }

    private fun invokeSetPostEffect(gameRenderer: GameRenderer, effect: Identifier): Boolean {
        return runCatching {
            setPostEffectMethod.invoke(gameRenderer, effect)
        }.isSuccess
    }

    private val setPostEffectMethod: Method by lazy {
        GameRenderer::class.java.getDeclaredMethod("setPostEffect", Identifier::class.java).apply {
            isAccessible = true
        }
    }

    private const val MAX_TIME_MEMORY_MS = 1000L
}
