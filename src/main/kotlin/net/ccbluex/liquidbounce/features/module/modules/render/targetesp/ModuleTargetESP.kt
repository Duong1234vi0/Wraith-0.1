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
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.multiply
import net.ccbluex.liquidbounce.utils.entity.pop
import net.ccbluex.liquidbounce.utils.entity.push
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Rich-Modern TargetESP — ported 1:1 from
 * `rich.modules.impl.render.TargetESP` (MC 1.21.11 / Yarn) onto the fork's
 * 1.26.2 / Mojang mappings.
 *
 * #Edit By Claude 2026-06-12.
 *
 * DESIGN: this deliberately keeps Rich's OWN render structure rather than the
 * fork's `drawCustomMesh`/`RenderCompat` path. Each mode draws through the
 * vanilla entity buffer source ([net.minecraft.client.renderer.MultiBufferSource.BufferSource])
 * using [RichEspPipelines], which bakes the additive blend
 * ([com.mojang.blaze3d.pipeline.BlendFunction.LIGHTNING]) into the pipeline.
 * The fork's `RenderCompat.blendFunc` is a no-op, which is exactly why the
 * earlier compat-path TargetESP attempts looked dull/black (see Agent1.md).
 *
 * 1.21.11 → 1.26.2 mapping notes:
 *  - `MatrixStack` → [PoseStack] (`push`/`pop`/`multiply` are fork extensions,
 *    `translate`/`scale` are native, `last().pose()` gives the [Matrix4f]).
 *  - `consumer.vertex(m,x,y,z).texture(u,v).color(argb)` →
 *    `consumer.addVertex(m,x,y,z).setUv(u,v).setColor(argb)`.
 *  - `RotationAxis.POSITIVE_Z.rotationDegrees` → `Axis.ZP.rotationDegrees`.
 *  - `target.getLerpedPos(pt)` → `target.getPosition(pt)`.
 *  - `target.getWidth()/getHeight()` → `bbWidth/bbHeight`.
 *  - target comes from `ModuleKillAura.targetTracker.target` (gated on the
 *    aura running), matching Rich's `Aura.target` gate.
 *
 * DEVIATIONS (noted): Circle mode draws its ribbon + accent rings as
 * camera-relative POSITION_COLOR quads through [RichEspPipelines.circleQuads]
 * instead of Rich's deferred `Render3D` line/quad batch (the fork has no
 * equivalent line-width buffer). The look — a glowing twin-band ring that
 * bobs vertically — is preserved; the thin GL line accents become thin quad
 * rings. All settings are always visible (Rich hid CrystalSpeed/Color3
 * per-mode; cosmetic only).
 */
@Suppress("MagicNumber", "TooManyFunctions", "LargeClass")
object ModuleTargetESP : ClientModule("TargetEsp", Category.RENDER) {

    private val mode by enumChoice("Mode", Mode.RHOMB)
    private val crystalRotationSpeed by float("CrystalRotationSpeed", 0.5F, 0.1F..2.0F)

    // #Edit By Codex 2026-06-13: lazy-load Rich textures so FCL resource reload cannot crash
    // during ModuleManager registration; a broken texture disables that effect instead.
    private val cubeTexture = RichTexture("rich-targetesp-cube", "rich/world/cube.png")
    private val chainTexture = RichTexture("rich-targetesp-chain", "rich/world/chain.png")
    private val glowTexture = RichTexture("rich-targetesp-glow", "rich/particle/glow.png")
    // #Edit By Codex 2026-06-13: ThunderHackV2 uses ThunderHack's firefly particle texture.
    private val fireflyTexture = RichTexture("thunderhack-targetesp-firefly", "thunderhack/particles/firefly.png")

    // Appear animation (Rich: OutBack, 300 ms).
    private var appear = 0F
    private var hurtProgress = 0F
    private var movingValue = 0F
    private var circleStep = 0F

    private var smoothedPos: Vec3? = null
    private var lastTarget: LivingEntity? = null
    private var rotationAngle = 0F
    private var smoothY = 0.0
    private var smoothY2 = 0.0

    private var lastFrameTime = System.currentTimeMillis()
    private var shaderWorldPassRendered = false
    private var shaderWorldPassDisabled = false

    private const val FULL_BRIGHT = 0xF000F0

    private class RichTexture(private val idPath: String, private val resource: String) {
        private var identifier: Identifier? = null
        private var failed = false

        fun get(): Identifier? {
            if (failed) {
                return null
            }

            identifier?.let { return it }

            val texture = Identifier.fromNamespaceAndPath("liquidbounce", idPath)
            return runCatching {
                texture.apply { registerDynamicImageFromResources(resource) }
            }.onFailure { throwable ->
                failed = true
                logger.error("Failed to load TargetESP texture $resource", throwable)
            }.getOrNull()?.also {
                identifier = it
            }
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

    private class PreparedRender(val target: LivingEntity, val alpha: Float, val smoothed: Vec3)

    override fun disable() {
        smoothedPos = null
        lastTarget = null
        appear = 0F
        smoothY = 0.0
        smoothY2 = 0.0
    }

    private fun deltaTime(): Float {
        val now = System.currentTimeMillis()
        var deltaMs = (now - lastFrameTime).toFloat()
        lastFrameTime = now
        deltaMs = deltaMs.coerceIn(1F, 100F)
        return deltaMs / (1000F / 60F)
    }

    private fun easeOutBack(t: Float): Float {
        val c1 = 1.70158F
        val c3 = c1 + 1F
        val p = t - 1F
        return 1F + c3 * p * p * p + c1 * p * p
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        if (shaderWorldPassRendered) {
            shaderWorldPassRendered = false
            return@handler
        }

        runCatching {
            renderTargetEsp(event)
        }.onFailure { throwable ->
            logger.error("TargetESP render failed; disabling module to avoid a client crash.", throwable)
            enabled = false
        }
    }

    private fun target(): LivingEntity? = if (ModuleKillAura.running) {
        ModuleKillAura.targetTracker.target
    } else {
        null
    }

    private fun resetTargetState() {
        smoothedPos = null
        lastTarget = null
        appear = 0F
        smoothY = 0.0
        smoothY2 = 0.0
    }

    private fun prepareRender(partialTicks: Float): PreparedRender? {
        val target = target()
        if (target == null) {
            resetTargetState()
            return null
        }

        val dt = deltaTime()

        appear = (appear + dt / 18F).coerceIn(0F, 1F)
        val alpha = easeOutBack(appear).coerceIn(0F, 1F)
        if (alpha <= 0.01F) {
            return null
        }

        movingValue += 2F * dt
        if (movingValue > 360000F) {
            movingValue = 0F
        }

        val hurtDecay = 0.1F * dt
        hurtProgress = if (target.hurtTime > 0) {
            target.hurtTime / 10F
        } else {
            (hurtProgress - hurtDecay).coerceAtLeast(0F)
        }
        circleStep += 0.06F * dt

        val targetPos = target.getPosition(partialTicks)
        val smooth = smoothedPos
        if (lastTarget !== target || smooth == null) {
            smoothedPos = targetPos
            lastTarget = target
        } else {
            val factor = min(1.0, partialTicks * 1.5).toFloat()
            smoothedPos = Vec3(
                smooth.x + (targetPos.x - smooth.x) * factor,
                smooth.y + (targetPos.y - smooth.y) * factor,
                smooth.z + (targetPos.z - smooth.z) * factor
            )
        }

        return PreparedRender(target, alpha, smoothedPos!!)
    }

    @JvmStatic
    fun renderShaderWorldPass(cameraPos: Vec3) {
        shaderWorldPassRendered = false

        if (shaderWorldPassDisabled || !running || !IrisShaderPack.inUse()) {
            return
        }

        val prepared = prepareRender(mc.deltaTracker.getGameTimeDeltaPartialTick(false)) ?: return
        val provider = mc.renderBuffers().bufferSource()
        val stack = PoseStack()

        runCatching {
            var rendered = false
            try {
                // #Edit By Codex 2026-06-14: apply the shader-world pass to every TargetESP mode.
                // Textured modes use Iris' ENTITY_TRANSLUCENT_EMISSIVE mapping; color-only geometry uses LIGHTNING.
                if (mode == Mode.CIRCLE) {
                    renderCircle(stack, provider, prepared.target, cameraPos, prepared.alpha, RenderTypes.lightning())
                    rendered = true
                } else {
                    stack.translate(
                        prepared.smoothed.x - cameraPos.x,
                        prepared.smoothed.y - cameraPos.y,
                        prepared.smoothed.z - cameraPos.z
                    )
                    when (mode) {
                        Mode.RHOMB -> cubeTexture.get()?.let {
                            renderRhomb(
                                stack,
                                provider,
                                prepared.target,
                                prepared.alpha,
                                RenderTypes.entityTranslucentEmissive(it, false)
                            )
                            rendered = true
                        }
                        Mode.THUNDER_HACK_V2 -> fireflyTexture.get()?.let {
                            renderThunderHackV2(
                                stack,
                                provider,
                                prepared.target,
                                prepared.alpha,
                                RenderTypes.entityTranslucentEmissive(it, false)
                            )
                            rendered = true
                        }
                        Mode.CHAIN -> chainTexture.get()?.let {
                            renderChain(
                                stack,
                                provider,
                                prepared.target,
                                prepared.alpha,
                                RenderTypes.entityTranslucentEmissive(it, false)
                            )
                            rendered = true
                        }
                        Mode.CRYSTALS -> {
                            val bloomRenderType = glowTexture.get()?.let {
                                RenderTypes.entityTranslucentEmissive(it, false)
                            }
                            renderCrystals(
                                stack, provider, prepared.target, prepared.alpha,
                                cameraPos, prepared.smoothed, shaderPass = true, bloomRenderType
                            )
                            rendered = true
                        }
                        Mode.CIRCLE -> {}
                    }
                }
            } finally {
                provider.endBatch()
            }
            shaderWorldPassRendered = rendered
        }.onFailure { throwable ->
            shaderWorldPassDisabled = true
            logger.error(
                "TargetESP shader world pass failed; falling back to the normal post-world renderer.",
                throwable
            )
        }
    }

    private fun renderTargetEsp(event: WorldRenderEvent) {
        val prepared = prepareRender(event.partialTicks) ?: return
        val target = prepared.target
        val alpha = prepared.alpha

        val stack = event.matrixStack
        val provider = mc.renderBuffers().bufferSource()
        val camPos = mc.gameRenderer.camera.position()

        if (mode == Mode.CIRCLE) {
            try {
                renderCircle(stack, provider, target, camPos, alpha)
            } finally {
                provider.endBatch()
            }
            return
        }

        val sp = prepared.smoothed

        stack.push()
        try {
            stack.translate(sp.x - camPos.x, sp.y - camPos.y, sp.z - camPos.z)

            when (mode) {
                Mode.RHOMB -> renderRhomb(stack, provider, target, alpha)
                Mode.THUNDER_HACK_V2 -> renderThunderHackV2(stack, provider, target, alpha)
                Mode.CHAIN -> renderChain(stack, provider, target, alpha)
                Mode.CRYSTALS -> renderCrystals(stack, provider, target, alpha, camPos, sp)
                Mode.CIRCLE -> {}
            }
        } finally {
            provider.endBatch()
            stack.pop()
        }
    }

    // ----- Rhomb -----

    private fun renderRhomb(stack: PoseStack, provider: net.minecraft.client.renderer.MultiBufferSource,
                            target: LivingEntity, alpha: Float, renderType: RenderType? = null) {
        val texture = cubeTexture.get() ?: return
        val entityReady = renderType != null
        val consumer = provider.getBuffer(renderType ?: RichEspPipelines.romb(texture))
        val camRot = mc.gameRenderer.camera.rotation

        stack.translate(0.0, target.bbHeight / 2.0, 0.0)
        if (entityReady) {
            // #Edit By Codex 2026-06-14: vanilla shader-mapped entity layers depth-test, unlike the
            // old no-depth Rich Rhomb pipeline. Pull the billboard slightly toward the camera so it
            // remains in front of the target body instead of being clipped inside it.
            translateTowardCamera(stack, target, target.bbWidth.toDouble() * 0.65 + 0.05)
        }
        stack.multiply(camRot)

        val timeRotation = (System.currentTimeMillis() % 6283) / 1000F
        stack.multiply(Axis.ZP.rotationDegrees(sin(timeRotation) * 360F))

        stack.scale(0.5F, 0.5F, 1F)

        val c1 = withAlpha(themeColor(0F), (255 * alpha).toInt())
        val c2 = withAlpha(themeColor(1F), (255 * alpha).toInt())

        val m = stack.last().pose()
        addTexturedVertex(consumer, m, -1F, -1F, 0F, 0F, 0F, c2, entityReady)
        addTexturedVertex(consumer, m, -1F, 1F, 0F, 0F, 1F, c1, entityReady)
        addTexturedVertex(consumer, m, 1F, 1F, 0F, 1F, 1F, c2, entityReady)
        addTexturedVertex(consumer, m, 1F, -1F, 0F, 1F, 0F, c1, entityReady)
    }

    private fun translateTowardCamera(stack: PoseStack, target: LivingEntity, amount: Double) {
        val cameraPos = mc.gameRenderer.camera.position()
        val partialTicks = mc.deltaTracker.getGameTimeDeltaPartialTick(false)
        val targetPos = target.getPosition(partialTicks)
        val centerX = targetPos.x
        val centerY = targetPos.y + target.bbHeight * 0.5
        val centerZ = targetPos.z
        val dx = cameraPos.x - centerX
        val dy = cameraPos.y - centerY
        val dz = cameraPos.z - centerZ
        val length = sqrt(dx * dx + dy * dy + dz * dz)

        if (length > 1.0E-4) {
            stack.translate(dx / length * amount, dy / length * amount, dz / length * amount)
        }
    }

    // ----- ThunderHack V2 / NextGen -----

    // #Edit By Codex 2026-06-13: replaces the removed Rich Ghost mode with ThunderHack's
    // firefly rendering, using shared client theme colors instead of ThunderHack HudEditor colors.
    // #Edit By Codex 2026-06-13: orbit direction/center now follows Rich-Modern Ghost so it stays
    // visually synced with the target while preserving the ThunderHackV2 firefly render style.
    private fun renderThunderHackV2(stack: PoseStack, provider: net.minecraft.client.renderer.MultiBufferSource,
                                    target: LivingEntity, alpha: Float, renderType: RenderType? = null) {
        val texture = fireflyTexture.get() ?: return
        val shaderPass = renderType != null
        val consumer = provider.getBuffer(renderType ?: RichEspPipelines.romb(texture))
        val camRot = mc.gameRenderer.camera.rotation
        val elapsed = System.currentTimeMillis().toDouble()
        val ghostCount = 4
        val espLength = if (shaderPass) 24 else 23
        val baseScale = if (shaderPass) 0.31F else 0.29F
        val radius = 0.72
        val verticalRadius = (target.bbHeight * 0.36).coerceIn(0.46, 0.78)
        val distance = if (shaderPass) 9.5 else 10.0
        val alphaStep = if (shaderPass) 11 else 12
        val alphaBoost = if (shaderPass) 1.42F else 1.10F
        val centerY = target.bbHeight * 0.5

        for (j in 0 until ghostCount) {
            // #Edit By Codex 2026-06-14: four quarter-phased diagonal orbit planes. This adds
            // the fourth ghost without duplicating one of the old three paths or stretching the sprite.
            val phase = j * PI * 0.5
            val planeYaw = j * PI * 0.25
            val yawSin = sin(planeYaw)
            val yawCos = cos(planeYaw)

            for (i in 0..espLength) {
                val progress = i.toFloat() / espLength
                val headWeight = (1F - progress).coerceIn(0F, 1F)
                val angle = 0.15 * ((elapsed * 0.58) - i * distance) / 30.0 + phase
                val radiusPulse = 0.96 + 0.06 * sin(angle * 2.0 + j)
                val horizontalRadius = radius * radiusPulse
                val localX = sin(angle) * horizontalRadius
                val localZ = -cos(angle) * horizontalRadius
                val localY = cos(angle) * verticalRadius
                val offset = Vec3(
                    localX * yawCos - localZ * yawSin,
                    localY,
                    localX * yawSin + localZ * yawCos
                )

                val alphaValue = (
                    (255 - i * alphaStep) * alpha * alphaBoost * (0.82F + headWeight * 0.18F)
                    ).toInt().coerceIn(0, 255)
                if (alphaValue <= 0) {
                    continue
                }

                val colorMix = if (shaderPass) {
                    0.18F + headWeight * 0.24F
                } else {
                    headWeight * 0.10F
                }
                val baseColor = lerpColor(themeColor(progress), 0xFFFFFFFF.toInt(), colorMix)
                val color = withAlpha(baseColor, alphaValue)
                val ghostScale = baseScale *
                    Math.pow(headWeight.toDouble(), 1.28).toFloat() *
                    (0.92F + headWeight * 0.16F)
                if (ghostScale <= 0.01F) {
                    continue
                }

                stack.push()
                stack.translate(offset.x, centerY + offset.y, offset.z)
                stack.multiply(camRot)
                stack.multiply(Axis.ZP.rotationDegrees((elapsed * 0.1 - i * 10.0).toFloat()))

                val m = stack.last().pose()
                drawEntityReadyQuad(consumer, m, ghostScale, color)
                if (i <= 1) {
                    val coreAlpha = (alphaValue * if (shaderPass) 0.76F else 0.34F).toInt().coerceIn(0, 255)
                    val coreColor = withAlpha(
                        lerpColor(baseColor, 0xFFFFFFFF.toInt(), if (shaderPass) 0.70F else 0.30F),
                        coreAlpha
                    )
                    drawEntityReadyQuad(consumer, m, ghostScale * 0.92F, coreColor)
                }

                stack.pop()
            }
        }
    }

    private fun drawEntityReadyQuad(consumer: VertexConsumer, matrix: Matrix4f, scale: Float, color: Int) {
        addEntityReadyVertex(consumer, matrix, -scale, scale, 0F, 0F, 1F, color)
        addEntityReadyVertex(consumer, matrix, scale, scale, 0F, 1F, 1F, color)
        addEntityReadyVertex(consumer, matrix, scale, -scale, 0F, 1F, 0F, color)
        addEntityReadyVertex(consumer, matrix, -scale, -scale, 0F, 0F, 0F, color)
    }

    private fun addTexturedVertex(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        x: Float,
        y: Float,
        z: Float,
        u: Float,
        v: Float,
        color: Int,
        entityReady: Boolean
    ) {
        if (entityReady) {
            addEntityReadyVertex(consumer, matrix, x, y, z, u, v, color)
        } else {
            consumer.addVertex(matrix, x, y, z).setUv(u, v).setColor(color)
        }
    }

    private fun addEntityReadyVertex(
        consumer: VertexConsumer,
        matrix: Matrix4f,
        x: Float,
        y: Float,
        z: Float,
        u: Float,
        v: Float,
        color: Int
    ) {
        // #Edit By Codex 2026-06-14: the Iris shader path uses vanilla ENTITY format, so ThunderHackV2
        // vertices must provide overlay, light, and normal data. The old POSITION_TEX_COLOR path ignores them.
        consumer.addVertex(matrix, x, y, z)
            .setUv(u, v)
            .setColor(color)
            .setOverlay(OverlayTexture.NO_OVERLAY)
            .setLight(FULL_BRIGHT)
            .setNormal(0F, 0F, 1F)
    }

    // ----- Chain -----

    private fun renderChain(stack: PoseStack, provider: net.minecraft.client.renderer.MultiBufferSource,
                            target: LivingEntity, alpha: Float, renderType: RenderType? = null) {
        val texture = chainTexture.get() ?: return
        val entityReady = renderType != null
        val consumer = provider.getBuffer(renderType ?: RichEspPipelines.chain(texture))

        val animValue = (System.currentTimeMillis() % 360000) / 1000F * 60F
        val gradusX = (20 * min(1 + sin(Math.toRadians(animValue.toDouble())), 1.0)).toFloat()
        val gradusZ = (20 * (min(1 + sin(Math.toRadians(animValue.toDouble())), 2.0) - 1)).toFloat()
        val width = target.bbWidth * 3

        val linksStep = 18
        val totalAngle = 360 * 2
        val chainSizeVal = 8F
        val down = 1.5F
        val chainScale = 0.5F

        val alphaVal = (alpha * 128).toInt().coerceIn(0, 128)

        var baseColor1 = themeColor(0F)
        var baseColor2 = themeColor(1F)
        if (hurtProgress > 0) {
            baseColor1 = lerpColor(baseColor1, 0xFFFF0000.toInt(), hurtProgress)
            baseColor2 = lerpColor(baseColor2, 0xFFFF0000.toInt(), hurtProgress)
        }
        val c1 = withAlpha(baseColor1, alphaVal)
        val c2 = withAlpha(baseColor2, alphaVal)

        val rotationValue = (System.currentTimeMillis() % 720000) / 1000F * 30F

        for (chain in 0 until 2) {
            val valF = 1.2F - 0.5F * (if (chain == 0) 1.0F else 0.9F)

            stack.push()
            stack.translate(0.0, target.bbHeight / 2.0, 0.0)
            stack.scale(chainScale, chainScale, chainScale)
            stack.multiply(Axis.ZP.rotationDegrees(if (chain == 0) gradusX else -gradusX))
            stack.multiply(Axis.XP.rotationDegrees(if (chain == 0) gradusZ else -gradusZ))

            val y = -0.5F
            val m = stack.last().pose()

            val modif = linksStep / 2
            var i = 0
            while (i < totalAngle) {
                val offsetX = (if (chain == 0) gradusX else -gradusX) / 100F
                val offsetZ = (if (chain == 0) -gradusZ else gradusZ) / 100F

                val prevSin = (offsetX + sin(Math.toRadians((i - modif + rotationValue).toDouble())) * width * valF).toFloat()
                val prevCos = (offsetZ + cos(Math.toRadians((i - modif + rotationValue).toDouble())) * width * valF).toFloat()
                val sinV = (offsetX + sin(Math.toRadians((i + rotationValue).toDouble())) * width * valF).toFloat()
                val cosV = (offsetZ + cos(Math.toRadians((i + rotationValue).toDouble())) * width * valF).toFloat()

                val u0 = 1F / 360F * (i - modif) * chainSizeVal
                val u1 = 1F / 360F * i * chainSizeVal

                addTexturedVertex(consumer, m, prevSin, y, prevCos, u0, 0F, c1, entityReady)
                addTexturedVertex(consumer, m, sinV, y, cosV, u1, 0F, c1, entityReady)
                addTexturedVertex(consumer, m, sinV, y + down, cosV, u1, 0.99F, c2, entityReady)
                addTexturedVertex(consumer, m, prevSin, y + down, prevCos, u0, 0.99F, c2, entityReady)

                i += modif
            }
            stack.pop()
        }
    }

    // ----- Crystals (Wonderful "Кристаллы" port) -----

    // #Edit By Claude 2026-06-16: replaced the Rich-Modern static-crystal mode with Wonderful's
    // orbiting-crystal mode (`fun.wonderful.client.modules.impl.render.TargetESP.renderCrystals3D`,
    // the last/"Кристаллы" TargetESP mode in wonderful-1.0.0.jar). 18 small diamond bipyramids
    // (w=0.06, h=0.2) orbit the target at 20° steps, weave up/down, each oriented toward the target
    // centre, with two additive bloom billboards behind each. The orbit eases in from 1.2x -> 0.7x
    // target width as `appear` ramps.
    // FAITHFUL GOTCHA (Agent1.md 2026-06-12): Wonderful's drawBillboard screen-scales the glow by
    // max(0.1, dist*0.007). Skipping that scale is what painted a giant blob in the old port, so it
    // is preserved here in `crystalBillboard`.
    private fun renderCrystals(
        stack: PoseStack,
        provider: net.minecraft.client.renderer.MultiBufferSource,
        target: LivingEntity,
        alpha: Float,
        camPos: Vec3,
        smoothed: Vec3,
        shaderPass: Boolean = false,
        bloomRenderType: RenderType? = null
    ) {
        rotationAngle = (rotationAngle + crystalRotationSpeed) % 360F

        val entityWidth = target.bbWidth
        val entityHeight = target.bbHeight
        val width = entityWidth * 1.5F
        val orbitScale = 1.2F - 0.5F * appear

        val baseColor = themeColor(0.35F)
        var color = multAlpha(baseColor, alpha)
        var glowColor = multAlpha(baseColor, alpha * 0.28F)
        if (hurtProgress > 0F) {
            val hurtColor = multAlpha(0xFFFF0303.toInt(), alpha)
            color = lerpColor(color, hurtColor, hurtProgress)
            glowColor = lerpColor(glowColor, multAlpha(hurtColor, 0.65F), hurtProgress)
        }

        // Pass 1 — diamond crystal shapes (POSITION_COLOR, translucent, no depth).
        val shapeConsumer = provider.getBuffer(
            if (shaderPass) RenderTypes.lightning() else RichEspPipelines.crystalFilled
        )
        val targetCenterY = entityHeight / 2.0F
        var i = 0
        while (i < 360) {
            val angleRad = Math.toRadians((i + rotationAngle).toDouble())
            val sinV = (sin(angleRad) * width * orbitScale).toFloat()
            val cosV = (cos(angleRad) * width * orbitScale).toFloat()
            // Wonderful: yOffset = 0.1 + height * |sin(i)| with the raw degree index used as radians.
            val yOffset = 0.1F + entityHeight * abs(sin(i.toDouble()).toFloat())

            stack.push()
            stack.translate(sinV.toDouble(), yOffset.toDouble(), cosV.toDouble())
            // Orient the crystal's +Y axis toward the target centre (axis-angle from up -> dir).
            val dir = Vector3f(-sinV, targetCenterY - yOffset, -cosV)
            val len = dir.length()
            if (len >= 0.001F) {
                dir.div(len)
                val up = Vector3f(0F, 1F, 0F)
                val axis = Vector3f()
                up.cross(dir, axis)
                val axisLen = axis.length()
                if (axisLen >= 0.001F) {
                    axis.div(axisLen)
                    val dot = up.dot(dir).coerceIn(-1F, 1F)
                    stack.multiply(Quaternionf().setAngleAxis(acos(dot), axis.x, axis.y, axis.z))
                }
                drawCrystalShape(shapeConsumer, stack.last().pose(), color)
            }
            stack.pop()
            i += 20
        }

        // Pass 2 — two additive bloom billboards behind each crystal (screen-scaled, no depth).
        val bloomConsumer: VertexConsumer? = if (shaderPass) {
            bloomRenderType?.let { provider.getBuffer(it) }
        } else {
            glowTexture.get()?.let { provider.getBuffer(RichEspPipelines.bloom(it)) }
        }
        if (bloomConsumer != null) {
            val entityReady = shaderPass
            val glowBaseSize = 4.5F + entityWidth * 3.0F
            val outerGlowSize = glowBaseSize * 1.28F
            val camRot = mc.gameRenderer.camera.rotation
            var ix = 0
            while (ix < 360) {
                val angleRad = Math.toRadians((ix + rotationAngle).toDouble())
                val sinV = (sin(angleRad) * width * orbitScale).toFloat()
                val cosV = (cos(angleRad) * width * orbitScale).toFloat()
                val yOffset = 0.1F + entityHeight * abs(sin(ix.toDouble()).toFloat())

                val dx = smoothed.x + sinV - camPos.x
                val dy = smoothed.y + yOffset - camPos.y
                val dz = smoothed.z + cosV - camPos.z
                val distScale = (sqrt(dx * dx + dy * dy + dz * dz) * 0.007).coerceAtLeast(0.1).toFloat()

                crystalBillboard(
                    stack, bloomConsumer, camRot, sinV, yOffset, cosV,
                    outerGlowSize * distScale * 0.5F, multAlpha(glowColor, 0.24F),
                    rotationAngle + ix, entityReady
                )
                crystalBillboard(
                    stack, bloomConsumer, camRot, sinV, yOffset, cosV,
                    glowBaseSize * distScale * 0.5F, glowColor,
                    -(rotationAngle + ix * 0.5F), entityReady
                )
                ix += 20
            }
        }
    }

    // Diamond bipyramid (8 triangles): 4 faces from the top apex, 4 from the bottom apex.
    private fun drawCrystalShape(consumer: VertexConsumer, m: Matrix4f, color: Int) {
        val w = 0.06F
        val h = 0.2F
        crystalTri(consumer, m, 0F, h, 0F, w, 0F, 0F, 0F, 0F, w, color)
        crystalTri(consumer, m, 0F, h, 0F, 0F, 0F, w, -w, 0F, 0F, color)
        crystalTri(consumer, m, 0F, h, 0F, -w, 0F, 0F, 0F, 0F, -w, color)
        crystalTri(consumer, m, 0F, h, 0F, 0F, 0F, -w, w, 0F, 0F, color)
        crystalTri(consumer, m, 0F, -h, 0F, w, 0F, 0F, 0F, 0F, w, color)
        crystalTri(consumer, m, 0F, -h, 0F, 0F, 0F, w, -w, 0F, 0F, color)
        crystalTri(consumer, m, 0F, -h, 0F, -w, 0F, 0F, 0F, 0F, -w, color)
        crystalTri(consumer, m, 0F, -h, 0F, 0F, 0F, -w, w, 0F, 0F, color)
    }

    @Suppress("LongParameterList")
    private fun crystalTri(
        consumer: VertexConsumer, m: Matrix4f,
        x1: Float, y1: Float, z1: Float,
        x2: Float, y2: Float, z2: Float,
        x3: Float, y3: Float, z3: Float,
        color: Int
    ) {
        // QUADS pipeline: emit the 3rd vertex twice to form a degenerate quad from a triangle.
        consumer.addVertex(m, x1, y1, z1).setColor(color)
        consumer.addVertex(m, x2, y2, z2).setColor(color)
        consumer.addVertex(m, x3, y3, z3).setColor(color)
        consumer.addVertex(m, x3, y3, z3).setColor(color)
    }

    @Suppress("LongParameterList")
    private fun crystalBillboard(
        stack: PoseStack,
        consumer: VertexConsumer,
        camRot: Quaternionf,
        offsetX: Float,
        offsetY: Float,
        offsetZ: Float,
        half: Float,
        color: Int,
        rotation: Float,
        entityReady: Boolean
    ) {
        if ((color ushr 24) == 0 || half <= 0F) {
            return
        }
        stack.push()
        stack.translate(offsetX.toDouble(), offsetY.toDouble(), offsetZ.toDouble())
        stack.multiply(camRot)
        if (rotation != 0F) {
            stack.multiply(Axis.ZP.rotationDegrees(rotation))
        }
        val m = stack.last().pose()
        addTexturedVertex(consumer, m, -half, -half, 0F, 0F, 1F, color, entityReady)
        addTexturedVertex(consumer, m, -half, half, 0F, 0F, 0F, color, entityReady)
        addTexturedVertex(consumer, m, half, half, 0F, 1F, 0F, color, entityReady)
        addTexturedVertex(consumer, m, half, -half, 0F, 1F, 1F, color, entityReady)
        stack.pop()
    }

    // ----- Circle -----

    private fun renderCircle(
        stack: PoseStack,
        provider: net.minecraft.client.renderer.MultiBufferSource,
        target: LivingEntity,
        camPos: Vec3,
        alpha: Float,
        renderType: RenderType? = null
    ) {
        var baseColor1 = themeColor(0F)
        var baseColor2 = themeColor(1F)
        if (hurtProgress > 0) {
            baseColor1 = lerpColor(baseColor1, 0xFFFF0000.toInt(), hurtProgress)
            baseColor2 = lerpColor(baseColor2, 0xFFFF0000.toInt(), hurtProgress)
        }

        val center = target.getPosition(1.0F)
        val hitEffect = min(hurtProgress * 2F, 1F)
        val distanceMultiplier = 1.0F + sin(hitEffect * PI).toFloat() * 0.18F
        val entityWidth = target.bbWidth * distanceMultiplier
        val entityHeight = target.bbHeight

        val size = 64
        val cs = circleStep - 0.17F
        val targetY = smoothSin(cs.toDouble()) * entityHeight
        val targetY2 = smoothSin((cs - 0.35F).toDouble()) * entityHeight
        smoothY = lerp(smoothY, targetY, 0.12)
        smoothY2 = lerp(smoothY2, targetY2, 0.10)

        val consumer = provider.getBuffer(renderType ?: RichEspPipelines.circleQuads)
        val m = stack.last().pose()

        for (i in 0 until size) {
            val t = i.toFloat() / size
            val tNext = ((i + 1) % size).toFloat() / size
            val gT = (0.5 - 0.5 * cos(t * Math.PI * 2)).toFloat()
            val gTNext = (0.5 - 0.5 * cos(tNext * Math.PI * 2)).toFloat()

            val curColor = lerpColor(baseColor1, baseColor2, gT)
            val nextColor = lerpColor(baseColor1, baseColor2, gTNext)

            val brightColor = multAlpha(curColor, 0.8F * alpha)
            val brightColorNext = multAlpha(nextColor, 0.8F * alpha)
            val fadeColor = multAlpha(curColor, 0F)
            val fadeColorNext = multAlpha(nextColor, 0F)

            val cosSin = circlePoint(i, size, entityWidth.toDouble())
            val nextCosSin = circlePoint((i + 1) % size, size, entityWidth.toDouble())

            val cTop = camRel(center.x + cosSin.x, center.y + smoothY, center.z + cosSin.z, camPos)
            val cBottom = camRel(center.x + cosSin.x, center.y + smoothY2, center.z + cosSin.z, camPos)
            val nTop = camRel(center.x + nextCosSin.x, center.y + smoothY, center.z + nextCosSin.z, camPos)
            val nBottom = camRel(center.x + nextCosSin.x, center.y + smoothY2, center.z + nextCosSin.z, camPos)

            // Front ribbon (bright top -> transparent bottom) and back (winding flipped).
            quad(consumer, m, cTop, nTop, nBottom, cBottom,
                brightColor, brightColorNext, fadeColorNext, fadeColor)
            quad(consumer, m, cBottom, nBottom, nTop, cTop,
                fadeColor, fadeColorNext, brightColorNext, brightColor)
        }
    }

    private fun camRel(x: Double, y: Double, z: Double, camPos: Vec3): Vector3f =
        Vector3f((x - camPos.x).toFloat(), (y - camPos.y).toFloat(), (z - camPos.z).toFloat())

    @Suppress("LongParameterList")
    private fun quad(consumer: VertexConsumer, m: Matrix4f, p1: Vector3f, p2: Vector3f, p3: Vector3f, p4: Vector3f,
                     c1: Int, c2: Int, c3: Int, c4: Int) {
        consumer.addVertex(m, p1.x, p1.y, p1.z).setColor(c1)
        consumer.addVertex(m, p2.x, p2.y, p2.z).setColor(c2)
        consumer.addVertex(m, p3.x, p3.y, p3.z).setColor(c3)
        consumer.addVertex(m, p4.x, p4.y, p4.z).setColor(c4)
    }

    private fun circlePoint(i: Int, size: Int, width: Double): Vec3 {
        val index = min(i, size)
        val cosV = cos(index * (Math.PI * 2) / size) * width
        val sinV = -sin(index * (Math.PI * 2) / size) * width
        return Vec3(cosV, 0.0, sinV)
    }

    private fun smoothSin(input: Double): Double {
        val sinV = (sin(input) + 1) / 2
        return -(cos(Math.PI * sinV) - 1) / 2
    }

    private fun lerp(start: Double, end: Double, delta: Double): Double = start + (end - start) * delta

    // ----- color helpers (Rich, ARGB ints) -----

    // #Edit By Codex 2026-06-13: TargetESP colors now sync with the shared client theme instead
    // of exposing confusing Color1/Color2/Color3 settings that some modes did not honor.
    private fun themeColor(progress: Float): Int =
        lerpColor(ClientTheme.accentA, ClientTheme.accentB, progress.coerceIn(0F, 1F))

    private fun lerpColor(c1: Int, c2: Int, t: Float): Int {
        val a1 = (c1 shr 24) and 0xFF
        val r1 = (c1 shr 16) and 0xFF
        val g1 = (c1 shr 8) and 0xFF
        val b1 = c1 and 0xFF
        val a2 = (c2 shr 24) and 0xFF
        val r2 = (c2 shr 16) and 0xFF
        val g2 = (c2 shr 8) and 0xFF
        val b2 = c2 and 0xFF
        val a = (a1 + (a2 - a1) * t).toInt()
        val r = (r1 + (r2 - r1) * t).toInt()
        val g = (g1 + (g2 - g1) * t).toInt()
        val b = (b1 + (b2 - b1) * t).toInt()
        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    private fun withAlpha(color: Int, alpha: Int): Int {
        val a = alpha.coerceIn(0, 255)
        return (color and 0x00FFFFFF) or (a shl 24)
    }

    private fun multAlpha(color: Int, percent: Float): Int {
        val a = (((color shr 24) and 0xFF) * percent).toInt().coerceIn(0, 255)
        return (color and 0x00FFFFFF) or (a shl 24)
    }

    private fun darkenColor(color: Int, factor: Float): Int {
        val a = (color shr 24) and 0xFF
        val r = (((color shr 16) and 0xFF) * factor).toInt()
        val g = (((color shr 8) and 0xFF) * factor).toInt()
        val b = ((color and 0xFF) * factor).toInt()
        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    private fun lightenColor(color: Int, factor: Float): Int {
        val a = (color shr 24) and 0xFF
        val r = min(255, (((color shr 16) and 0xFF) * factor).toInt())
        val g = min(255, (((color shr 8) and 0xFF) * factor).toInt())
        val b = min(255, ((color and 0xFF) * factor).toInt())
        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    @Suppress("unused")
    private enum class Mode(override val choiceName: String) : NamedChoice {
        RHOMB("Rhomb"),
        THUNDER_HACK_V2("ThunderHackV2"),
        CHAIN("Chain"),
        CRYSTALS("Crystals"),
        CIRCLE("Circle")
    }
}
