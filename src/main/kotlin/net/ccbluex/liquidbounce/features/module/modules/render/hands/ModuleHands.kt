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
package net.ccbluex.liquidbounce.features.module.modules.render.hands

import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.pipeline.TextureTarget
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.GpuTextureView
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.handshader.HandPostProcessCoordinator
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.submersionType
import net.minecraft.client.CameraType
import net.minecraft.world.level.material.FogType
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlin.math.sin

@Suppress("MagicNumber", "TooManyFunctions")
object ModuleHands : ClientModule(
    "Hands",
    Category.RENDER,
    aliases = arrayOf("PouchHands", "HandsEffect")
) {

    enum class Mode(override val choiceName: String) : NamedChoice {
        Mirror("Mirror"),
        Fill("Fill")
    }

    enum class ColorMode(override val choiceName: String) : NamedChoice {
        Theme("Theme"),
        Custom("Custom")
    }

    enum class SwingTrailBehavior(override val choiceName: String) : NamedChoice {
        Continuous("Continuous"),
        PouchBlow("PouchBlow")
    }

    private val mode by enumChoice("Mode", Mode.Fill).apply(::tagBy)

    private val mirrorColorMode by enumChoice("MirrorColorMode", ColorMode.Custom)
        .visibleWhen { isMirrorMode }
    private val mirrorColor by color("MirrorColor", Color4b.fromHex("#8A98FFFF"))
        .visibleWhen { isMirrorMode && mirrorColorMode == ColorMode.Custom }
    private val mirrorMix by steppedFloat("MirrorMix", 0.0f, 0.0f..0.5f, 0.01f)
        .visibleWhen { isMirrorMode }

    private val rainbow by boolean("Rainbow", false)
        .visibleWhen { isFillMode }
    private val rainbowSpeed by steppedFloat("RainbowSpeed", 0.4f, 0.0f..2.0f, 0.05f)
        .visibleWhen { isFillMode && rainbow }
    private val rainbowScale by steppedFloat("RainbowScale", 1.0f, 0.2f..3.0f, 0.1f)
        .visibleWhen { isFillMode && rainbow }
    private val fillColorMode by enumChoice("FillColorMode", ColorMode.Custom)
        .visibleWhen { isFillMode && !rainbow }
    private val fillColor by color("FillColor", Color4b.fromHex("#FF4444FF"))
        .visibleWhen { isFillMode && !rainbow && fillColorMode == ColorMode.Custom }
    private val fillOpacity by steppedFloat("FillOpacity", 0.8f, 0.0f..1.0f, 0.05f)
        .visibleWhen { isFillMode }
    private val keepShading by boolean("KeepShading", true)
        .visibleWhen { isFillMode }
    private val shadingStrength by steppedFloat("ShadingStrength", 0.3f, 0.0f..1.0f, 0.05f)
        .visibleWhen { isFillMode && keepShading }

    private val outline by boolean("Outline", false)
    private val outlineWidth by steppedFloat("OutlineWidth", 1.0f, 0.5f..3.0f, 0.5f)
        .visibleWhen { outline }
    private val outlineColor by color("OutlineColor", Color4b.fromHex("#8A98FFFF"))
        .visibleWhen { outline && isCustomEffectColor }

    private val glow by boolean("Glow", true)
    private val blurIterations by int("BlurIterations", 4, 1..6)
        .visibleWhen { glow }
    private val outerGlow by boolean("OuterGlow", true)
        .visibleWhen { glow }
    private val brightness by steppedFloat("Brightness", 2.0f, 0.5f..5.0f, 0.1f)
        .visibleWhen { glow && outerGlow }

    private val autoColor by boolean("AutoColor", false)
        .visibleWhen { glow || outline }
    private val saturation by steppedFloat("Saturation", 1.4f, 0.5f..3.0f, 0.1f)
        .visibleWhen { autoColor }
    private val effectColorMode by enumChoice("EffectColorMode", ColorMode.Custom)
        .visibleWhen { (glow || outline) && !autoColor }
    private val glowColor1 by color("GlowColor1", Color4b.fromHex("#8A98FFFF"))
        .visibleWhen { glow && !autoColor && effectColorMode == ColorMode.Custom }
    private val glowColor2 by color("GlowColor2", Color4b.fromHex("#FF6BACFF"))
        .visibleWhen { glow && !autoColor && effectColorMode == ColorMode.Custom }

    private val trail by boolean("Trail", true)
        .visibleWhen { glow && outerGlow }
    private val fadeSpeed by steppedFloat("FadeSpeed", 0.012f, 0.002f..0.2f, 0.002f)
        .visibleWhen { isTrailVisible }
    private val lift by steppedFloat("Lift", 0.2f, 0.0f..1.5f, 0.05f)
        .visibleWhen { isTrailVisible }
    private val sway by steppedFloat("Sway", 0.05f, 0.0f..0.2f, 0.005f)
        .visibleWhen { isTrailVisible }
    private val turbulence by steppedFloat("Turbulence", 0.0f, 0.0f..0.6f, 0.01f)
        .visibleWhen { isTrailVisible }
    private val flicker by steppedFloat("Flicker", 0.0f, 0.0f..0.2f, 0.01f)
        .visibleWhen { isTrailVisible }
    private val legacyBlowOnSwing by boolean("BlowOnSwing", false)
        .visibleWhen { false }
        .onChanged(::migrateLegacyBlowOnSwing)
    private val swingTrailBehaviorValue = enumChoice("SwingTrailBehavior", SwingTrailBehavior.Continuous)
        .visibleWhen { isTrailVisible }
    private val blowStrength by steppedFloat("BlowStrength", 4.0f, 1.0f..10.0f, 0.5f)
        .visibleWhen { isTrailVisible && swingTrailBehavior == SwingTrailBehavior.PouchBlow }
    private val modelTrail by boolean("ModelTrail", true)
        .visibleWhen { isTrailVisible }
    private val modelOpacity by steppedFloat("ModelOpacity", 0.6f, 0.1f..1.0f, 0.05f)
        .visibleWhen { isTrailVisible && modelTrail }

    private var baseTarget: TextureTarget? = null
    private var edgeMaskTarget: TextureTarget? = null
    private var glowTarget: TextureTarget? = null
    private var outlineTarget: TextureTarget? = null
    private var compositeTarget: TextureTarget? = null
    private val sceneBlurTargets = arrayOfNulls<TextureTarget>(MAX_BLUR_ITERATIONS)
    private val handBlurTargets = arrayOfNulls<TextureTarget>(MAX_BLUR_ITERATIONS)
    private var historyA: TextureTarget? = null
    private var historyB: TextureTarget? = null
    private var historyFlip = false
    private var historyInitialized = false
    private var lastTrailTime = 0L
    private var smoothedDelta = 1.0f / 60.0f
    private var lastSwinging = false
    private var swingBurstStarted = -10_000L

    private val isMirrorMode: Boolean
        get() = mode == Mode.Mirror

    private val isFillMode: Boolean
        get() = mode == Mode.Fill

    private val isCustomEffectColor: Boolean
        get() = !autoColor && effectColorMode == ColorMode.Custom

    private val isTrailVisible: Boolean
        get() = glow && outerGlow && trail

    private val swingTrailBehavior: SwingTrailBehavior
        get() = swingTrailBehaviorValue.get()

    private fun migrateLegacyBlowOnSwing(enabled: Boolean) {
        if (enabled && swingTrailBehaviorValue.get() == SwingTrailBehavior.Continuous) {
            swingTrailBehaviorValue.set(SwingTrailBehavior.PouchBlow)
        }
    }

    override fun disable() {
        invalidateResources()
        HandPostProcessCoordinator.invalidateSharedResourcesIfUnused(this)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        invalidateResources()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        invalidateResources()
    }

    @JvmStatic
    fun shouldRender(): Boolean {
        val localPlayer = mc.player ?: return false

        return running &&
            HandsPipeline.isCoreAvailable &&
            mc.options.cameraType == CameraType.FIRST_PERSON &&
            mc.gameRenderer.camera.submersionType == FogType.NONE &&
            !localPlayer.isUnderWater &&
            !localPlayer.isInLava &&
            (!localPlayer.isSwimming || !localPlayer.isInWater)
    }

    fun renderFromRawCapture(snapshot: HandPostProcessCoordinator.Snapshot, mainTarget: RenderTarget) {
        if (!shouldRender()) {
            return
        }

        runCatching {
            renderUnchecked(snapshot, mainTarget)
        }.onFailure { throwable ->
            logger.error("Hands post-process failed.", throwable)
            invalidateResources()
        }
    }

    @JvmStatic
    fun invalidateResources() {
        RenderSystem.assertOnRenderThread()
        baseTarget = baseTarget.destroyAndNull()
        edgeMaskTarget = edgeMaskTarget.destroyAndNull()
        glowTarget = glowTarget.destroyAndNull()
        outlineTarget = outlineTarget.destroyAndNull()
        compositeTarget = compositeTarget.destroyAndNull()
        for (i in sceneBlurTargets.indices) {
            sceneBlurTargets[i] = sceneBlurTargets[i].destroyAndNull()
            handBlurTargets[i] = handBlurTargets[i].destroyAndNull()
        }
        historyA = historyA.destroyAndNull()
        historyB = historyB.destroyAndNull()
        historyFlip = false
        historyInitialized = false
        lastTrailTime = 0L
        smoothedDelta = 1.0f / 60.0f
        lastSwinging = false
        swingBurstStarted = -10_000L
    }

    @JvmStatic
    fun invalidateTrailHistory() {
        RenderSystem.assertOnRenderThread()
        historyA = historyA.destroyAndNull()
        historyB = historyB.destroyAndNull()
        historyFlip = false
        historyInitialized = false
        lastTrailTime = 0L
        smoothedDelta = 1.0f / 60.0f
        lastSwinging = false
        swingBurstStarted = -10_000L
    }

    private fun renderUnchecked(snapshot: HandPostProcessCoordinator.Snapshot, mainTarget: RenderTarget) {
        val width = snapshot.width
        val height = snapshot.height
        if (width <= 0 || height <= 0) {
            return
        }

        val time = ((System.currentTimeMillis() % 100000L) / 1000.0f)
        val base = ensureFullTarget(baseTarget, "LiquidBounce Hands base", width, height).also {
            baseTarget = it
        } ?: return
        val composite = ensureFullTarget(compositeTarget, "LiquidBounce Hands composite", width, height).also {
            compositeTarget = it
        } ?: return

        val sceneBlur = if (isMirrorMode) {
            renderKawase(
                // In Iris mode this is the stable final scene, so the blur can include a little hand self-bleed.
                source = snapshot.beforeColor,
                sourceWidth = width,
                sourceHeight = height,
                targets = sceneBlurTargets,
                iterations = blurIterations.coerceIn(1, MAX_BLUR_ITERATIONS),
                label = "scene"
            )?.colorTextureView ?: snapshot.beforeColor
        } else {
            snapshot.beforeColor
        }

        if (!renderBase(snapshot, base, sceneBlur, width, height, time)) {
            return
        }

        val needsBlur = glow && outerGlow || outline && autoColor || isTrailVisible
        val effectMask = if (snapshot.maskMode == HandPostProcessCoordinator.HandMaskMode.IRIS_EXACT_MASK &&
            (needsBlur || outline)) {
            renderEdgeMask(base, width, height) ?: base
        } else {
            base
        }

        val blurredHand = if (needsBlur) {
            renderKawase(
                source = requireNotNull(effectMask.colorTextureView),
                sourceWidth = width,
                sourceHeight = height,
                targets = handBlurTargets,
                iterations = if (glow) blurIterations.coerceIn(1, MAX_BLUR_ITERATIONS) else AUTO_COLOR_BLUR_ITERATIONS,
                label = "hand"
            )
        } else {
            null
        }
        val blurredHandView = blurredHand?.colorTextureView ?: requireNotNull(base.colorTextureView)

        val glowView = if (glow && outerGlow) {
            renderGlow(effectMask, blurredHandView, width, height)?.colorTextureView
        } else {
            null
        }

        val outlineView = if (outline) {
            renderOutline(effectMask, blurredHandView, width, height, time)?.colorTextureView
        } else {
            null
        }

        val trailView = if (isTrailVisible && HandsPipeline.isTrailAvailable && glowView != null) {
            renderTrail(base, glowView, width, height, time)?.colorTextureView
        } else {
            null
        }

        val mainView = requireNotNull(mainTarget.colorTextureView) {
            "Hands main target has no color texture view."
        }
        val baseView = requireNotNull(base.colorTextureView)
        val dummyView = baseView

        val rendered = HandsPipeline.renderPass(
            pipeline = HandsPipeline.compositePipeline,
            label = "LiquidBounce Hands composite",
            output = requireNotNull(composite.colorTextureView),
            config = HandsPipeline.Config(
                flags0 = HandsPipeline.IVec4(
                    x = if (glowView != null) 1 else 0,
                    y = if (outlineView != null) 1 else 0,
                    z = if (trailView != null) 1 else 0
                )
            ),
            textures = arrayOf(
                "CurrentMain" to mainView,
                "BaseHand" to baseView,
                "GlowTexture" to (glowView ?: dummyView),
                "OutlineTexture" to (outlineView ?: dummyView),
                "TrailTexture" to (trailView ?: dummyView)
            )
        )

        if (rendered) {
            HandPostProcessCoordinator.copyColor(composite, mainTarget)
        }
    }

    private fun renderEdgeMask(mask: TextureTarget, width: Int, height: Int): TextureTarget? {
        val output = ensureFullTarget(edgeMaskTarget, "LiquidBounce Hands edge mask", width, height).also {
            edgeMaskTarget = it
        } ?: return null

        val rendered = HandsPipeline.renderPass(
            pipeline = HandsPipeline.edgeMaskPipeline,
            label = "LiquidBounce Hands edge mask",
            output = requireNotNull(output.colorTextureView),
            config = HandsPipeline.Config(),
            textures = arrayOf(
                "MaskTexture" to requireNotNull(mask.colorTextureView)
            )
        )

        return output.takeIf { rendered }
    }

    private fun renderBase(
        snapshot: HandPostProcessCoordinator.Snapshot,
        output: TextureTarget,
        blurredScene: GpuTextureView,
        width: Int,
        height: Int,
        time: Float
    ): Boolean {
        val modeId = if (isMirrorMode) MODE_MIRROR else MODE_FILL
        val baseColor = if (isMirrorMode) {
            mirrorResolvedColor()
        } else {
            fillResolvedColor()
        }

        return HandsPipeline.renderPass(
            pipeline = HandsPipeline.basePipeline,
            label = "LiquidBounce Hands base",
            output = requireNotNull(output.colorTextureView),
            config = HandsPipeline.Config(
                color0 = baseColor,
                params0 = HandsPipeline.Vec4(time, width.toFloat(), height.toFloat(), 0.0f),
                params1 = HandsPipeline.Vec4(fillOpacity, shadingStrength, mirrorMix, 0.0f),
                params2 = HandsPipeline.Vec4(rainbowSpeed, rainbowScale, saturation, 0.0f),
                flags0 = HandsPipeline.IVec4(
                    x = modeId,
                    y = if (isFillMode && rainbow) 1 else 0,
                    z = if (keepShading) 1 else 0,
                    w = snapshot.maskMode.uniformValue
                )
            ),
            textures = arrayOf(
                "SceneBefore" to snapshot.beforeColor,
                "RawAfter" to snapshot.afterColor,
                "DepthSampler" to snapshot.handDepth,
                "HandMaskSampler" to (snapshot.exactHandMask ?: snapshot.handDepth),
                "BlurredScene" to blurredScene
            )
        )
    }

    private fun renderGlow(mask: TextureTarget, blurredHand: GpuTextureView, width: Int, height: Int): TextureTarget? {
        val output = ensureFullTarget(glowTarget, "LiquidBounce Hands glow", width, height).also {
            glowTarget = it
        } ?: return null
        val colors = effectGradientColors()

        val rendered = HandsPipeline.renderPass(
            pipeline = HandsPipeline.glowPipeline,
            label = "LiquidBounce Hands outer glow",
            output = requireNotNull(output.colorTextureView),
            config = HandsPipeline.Config(
                color0 = colors.first,
                color1 = colors.second,
                params1 = HandsPipeline.Vec4(brightness, 0.0f, 0.0f, 0.0f),
                params2 = HandsPipeline.Vec4(0.0f, 0.0f, saturation, 0.0f),
                flags0 = HandsPipeline.IVec4(x = if (autoColor) 1 else 0)
            ),
            textures = arrayOf(
                "BloomTexture" to blurredHand,
                "MaskTexture" to requireNotNull(mask.colorTextureView)
            )
        )

        return output.takeIf { rendered }
    }

    private fun renderOutline(
        mask: TextureTarget,
        blurredHand: GpuTextureView,
        width: Int,
        height: Int,
        time: Float
    ): TextureTarget? {
        val output = ensureFullTarget(outlineTarget, "LiquidBounce Hands outline", width, height).also {
            outlineTarget = it
        } ?: return null

        val colorMode = when {
            isFillMode && rainbow -> COLOR_MODE_RAINBOW
            autoColor -> COLOR_MODE_AUTO
            else -> COLOR_MODE_SOLID
        }

        val rendered = HandsPipeline.renderPass(
            pipeline = HandsPipeline.outlinePipeline,
            label = "LiquidBounce Hands outline",
            output = requireNotNull(output.colorTextureView),
            config = HandsPipeline.Config(
                color0 = outlineResolvedColor(),
                params0 = HandsPipeline.Vec4(time, width.toFloat(), height.toFloat(), 0.0f),
                params1 = HandsPipeline.Vec4(outlineWidth, 0.0f, 0.0f, 0.0f),
                params2 = HandsPipeline.Vec4(rainbowSpeed, rainbowScale, saturation, 0.0f),
                flags0 = HandsPipeline.IVec4(x = colorMode)
            ),
            textures = arrayOf(
                "MaskTexture" to requireNotNull(mask.colorTextureView),
                "BloomTexture" to blurredHand
            )
        )

        return output.takeIf { rendered }
    }

    private fun renderTrail(
        model: TextureTarget,
        currentGlow: GpuTextureView,
        width: Int,
        height: Int,
        time: Float
    ): TextureTarget? {
        val history = ensureHistoryTargets(width, height) ?: return null
        if (!historyInitialized) {
            if (!clearHistory(history.first, model) || !clearHistory(history.second, model)) {
                return null
            }
            historyInitialized = true
        }

        val now = System.currentTimeMillis()
        val rawDelta = if (lastTrailTime > 0L) {
            min(0.05f, (now - lastTrailTime).toFloat() / 1000.0f)
        } else {
            1.0f / 60.0f
        }
        lastTrailTime = now
        smoothedDelta = smoothedDelta * 0.7f + rawDelta * 0.3f

        val localPlayer = mc.player
        val swinging = localPlayer?.swinging == true
        val trailBehavior = swingTrailBehavior
        if (trailBehavior == SwingTrailBehavior.PouchBlow && swinging && !lastSwinging) {
            swingBurstStarted = now
        }
        lastSwinging = swinging

        val fadeAdd = if (trailBehavior == SwingTrailBehavior.PouchBlow) {
            val burstAge = (now - swingBurstStarted).toFloat() / 1000.0f
            val burst = max(0.0f, 1.0f - burstAge / 0.35f)
            burst * blowStrength * 0.025f
        } else {
            0.0f
        }
        val swayFrequency = 5.0265484f
        val upDelta = lift * smoothedDelta
        val swayDelta = (sin(time * swayFrequency) - sin((time - smoothedDelta) * swayFrequency)) * sway

        val read = if (historyFlip) history.second else history.first
        val write = if (historyFlip) history.first else history.second

        val rendered = HandsPipeline.renderPass(
            pipeline = HandsPipeline.trailUpdatePipeline,
            label = "LiquidBounce Hands trail update",
            output = requireNotNull(write.colorTextureView),
            config = HandsPipeline.Config(
                params0 = HandsPipeline.Vec4(swayDelta, upDelta, 0.0f, 0.0f),
                params1 = HandsPipeline.Vec4(fadeSpeed + fadeAdd, modelOpacity, 0.0f, 0.0f),
                params3 = HandsPipeline.Vec4(time, smoothedDelta, turbulence, flicker),
                flags0 = HandsPipeline.IVec4(x = if (modelTrail) 1 else 0)
            ),
            textures = arrayOf(
                "HistoryTexture" to requireNotNull(read.colorTextureView),
                "CurrentGlowTexture" to currentGlow,
                "ModelTexture" to requireNotNull(model.colorTextureView)
            ),
            trailOnly = true
        )

        if (!rendered) {
            return null
        }

        historyFlip = !historyFlip
        return write
    }

    private fun clearHistory(target: TextureTarget, dummy: TextureTarget): Boolean {
        val dummyView = requireNotNull(dummy.colorTextureView)
        return HandsPipeline.renderPass(
            pipeline = HandsPipeline.trailUpdatePipeline,
            label = "LiquidBounce Hands trail clear",
            output = requireNotNull(target.colorTextureView),
            config = HandsPipeline.Config(flags0 = HandsPipeline.IVec4(y = 1)),
            textures = arrayOf(
                "HistoryTexture" to dummyView,
                "CurrentGlowTexture" to dummyView,
                "ModelTexture" to dummyView
            ),
            trailOnly = true
        )
    }

    private fun renderKawase(
        source: GpuTextureView,
        sourceWidth: Int,
        sourceHeight: Int,
        targets: Array<TextureTarget?>,
        iterations: Int,
        label: String
    ): TextureTarget? {
        val prepared = ensureBlurTargets(targets, sourceWidth, sourceHeight, iterations, label) ?: return null
        var currentView = source

        for (i in 0 until iterations) {
            val output = prepared[i]
            val rendered = HandsPipeline.renderPass(
                pipeline = HandsPipeline.kawaseDownPipeline,
                label = "LiquidBounce Hands $label Kawase down",
                output = requireNotNull(output.colorTextureView),
                config = kawaseConfig(output, 1.0f + i.toFloat()),
                textures = arrayOf("Sampler0" to currentView)
            )
            if (!rendered) {
                return null
            }
            currentView = requireNotNull(output.colorTextureView)
        }

        for (i in iterations - 1 downTo 1) {
            val output = prepared[i - 1]
            val rendered = HandsPipeline.renderPass(
                pipeline = HandsPipeline.kawaseUpPipeline,
                label = "LiquidBounce Hands $label Kawase up",
                output = requireNotNull(output.colorTextureView),
                config = kawaseConfig(output, 1.0f + i.toFloat()),
                textures = arrayOf("Sampler0" to currentView)
            )
            if (!rendered) {
                return null
            }
            currentView = requireNotNull(output.colorTextureView)
        }

        return prepared[0]
    }

    private fun kawaseConfig(output: TextureTarget, offset: Float): HandsPipeline.Config {
        return HandsPipeline.Config(
            params0 = HandsPipeline.Vec4(
                0.5f / output.width.toFloat(),
                0.5f / output.height.toFloat(),
                offset,
                0.0f
            )
        )
    }

    private fun ensureBlurTargets(
        targets: Array<TextureTarget?>,
        width: Int,
        height: Int,
        iterations: Int,
        label: String
    ): Array<TextureTarget>? {
        if (width <= 0 || height <= 0 || iterations !in 1..targets.size) {
            return null
        }

        for (i in 0 until iterations) {
            val targetWidth = max(2, width shr (i + 1))
            val targetHeight = max(2, height shr (i + 1))
            val current = targets[i]
            if (current == null || current.isInvalid(targetWidth, targetHeight)) {
                current.destroyAndNull()
                targets[i] = TextureTarget("LiquidBounce Hands $label blur $i", targetWidth, targetHeight, false)
            }
        }

        @Suppress("UNCHECKED_CAST")
        return targets as Array<TextureTarget>
    }

    private fun ensureHistoryTargets(width: Int, height: Int): Pair<TextureTarget, TextureTarget>? {
        val historyWidth = max(2, width / 2)
        val historyHeight = max(2, height / 2)

        val oldA = historyA
        if (oldA == null || oldA.isInvalid(historyWidth, historyHeight)) {
            oldA.destroyAndNull()
            historyA = TextureTarget("LiquidBounce Hands trail history A", historyWidth, historyHeight, false)
            historyInitialized = false
        }

        val oldB = historyB
        if (oldB == null || oldB.isInvalid(historyWidth, historyHeight)) {
            oldB.destroyAndNull()
            historyB = TextureTarget("LiquidBounce Hands trail history B", historyWidth, historyHeight, false)
            historyInitialized = false
        }

        return (historyA ?: return null) to (historyB ?: return null)
    }

    private fun ensureFullTarget(
        current: TextureTarget?,
        label: String,
        width: Int,
        height: Int
    ): TextureTarget? {
        if (width <= 0 || height <= 0) {
            return current.destroyAndNull()
        }

        if (current != null && !current.isInvalid(width, height)) {
            return current
        }

        current.destroyAndNull()
        return TextureTarget(label, width, height, false)
    }

    private fun TextureTarget.isInvalid(width: Int, height: Int): Boolean {
        return this.width != width ||
            this.height != height ||
            this.useDepth ||
            colorTexture?.isClosed != false ||
            colorTextureView?.isClosed != false
    }

    private fun TextureTarget?.destroyAndNull(): TextureTarget? {
        this?.destroyBuffers()
        return null
    }

    private fun steppedFloat(
        name: String,
        default: Float,
        range: ClosedFloatingPointRange<Float>,
        step: Float
    ) = float(name, default, range).onChange { value ->
        snap(value, range.start, range.endInclusive, step)
    }

    private fun snap(value: Float, min: Float, max: Float, step: Float): Float {
        val snapped = min + round((value - min) / step) * step
        return snapped.coerceIn(min, max)
    }

    private fun mirrorResolvedColor(): Color4b {
        return when (mirrorColorMode) {
            ColorMode.Theme -> themeA()
            ColorMode.Custom -> mirrorColor
        }
    }

    private fun fillResolvedColor(): Color4b {
        return when (fillColorMode) {
            ColorMode.Theme -> themeA()
            ColorMode.Custom -> fillColor
        }
    }

    private fun outlineResolvedColor(): Color4b {
        return if (autoColor) {
            outlineColor
        } else {
            when (effectColorMode) {
                ColorMode.Theme -> themeA()
                ColorMode.Custom -> outlineColor
            }
        }
    }

    private fun effectGradientColors(): Pair<Color4b, Color4b> {
        return if (autoColor) {
            glowColor1 to glowColor2
        } else {
            when (effectColorMode) {
                ColorMode.Theme -> themeA() to themeB()
                ColorMode.Custom -> glowColor1 to glowColor2
            }
        }
    }

    private fun themeA() = Color4b(ClientTheme.accentA, hasAlpha = true)

    private fun themeB() = Color4b(ClientTheme.accentB, hasAlpha = true)

    private const val MODE_MIRROR = 0
    private const val MODE_FILL = 1
    private const val COLOR_MODE_SOLID = 0
    private const val COLOR_MODE_RAINBOW = 1
    private const val COLOR_MODE_AUTO = 2
    private const val MAX_BLUR_ITERATIONS = 6
    private const val AUTO_COLOR_BLUR_ITERATIONS = 3
}
