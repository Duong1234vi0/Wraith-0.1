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
package net.ccbluex.liquidbounce.features.module.modules.render.handshader

import com.mojang.blaze3d.pipeline.TextureTarget
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.GpuTexture
import com.mojang.blaze3d.textures.GpuTextureView
import com.mojang.blaze3d.textures.TextureFormat
import net.ccbluex.liquidbounce.utils.client.logger
import java.lang.ref.WeakReference
import java.lang.reflect.Method

/**
 * Optional Iris hand integration.
 *
 * Iris owns RenderTargets and depth textures. LiquidBounce stores only a weak
 * current-frame RenderTargets reference, LiquidBounce-owned depth/mask targets,
 * and LiquidBounce-created texture views for Iris textures.
 */
object IrisHandCompat {

    data class ExactMaskSnapshot(
        val exactHandMask: GpuTextureView,
        val frameSerial: Long
    )

    private var apiLoaded = false
    private var apiInstance: Any? = null
    private var shaderPackInUseMethod: Method? = null
    private var apiFailureLogged = false

    private var renderTargetsClass: Class<*>? = null
    private var getDepthTextureNoHandMethod: Method? = null
    private var getDepthTextureMethod: Method? = null
    private var renderTargetsFailureLogged = false

    private var renderTargetsRef: WeakReference<Any>? = null
    private var renderTargetsFrameSerial = -1L

    private var noHandDepthTexture: GpuTexture? = null
    private var noHandDepthView: GpuTextureView? = null
    private var currentDepthTexture: GpuTexture? = null
    private var currentDepthView: GpuTextureView? = null

    private var translucentPreDepthTexture: GpuTexture? = null
    private var translucentPreDepthView: GpuTextureView? = null
    private var translucentPreDepthFormat: TextureFormat? = null
    private var translucentPreDepthFrameSerial = -1L

    private var irisMaskA: TextureTarget? = null
    private var irisMaskB: TextureTarget? = null
    private var solidMaskFrameSerial = -1L
    private var finalMaskFrameSerial = -1L
    private var finalMaskTarget: TextureTarget? = null

    @JvmStatic
    fun isShaderPackInUse(): Boolean {
        if (!apiLoaded) {
            apiLoaded = true
            runCatching {
                val apiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi")
                apiInstance = apiClass.getMethod("getInstance").invoke(null)
                shaderPackInUseMethod = apiClass.getMethod("isShaderPackInUse")
            }.onFailure { throwable ->
                apiInstance = null
                shaderPackInUseMethod = null
                logApiFailureOnce("Iris API is unavailable for hand shader-pack detection.", throwable)
            }
        }

        val api = apiInstance ?: return false
        val method = shaderPackInUseMethod ?: return false
        return runCatching { method.invoke(api) as? Boolean ?: false }
            .onFailure { throwable ->
                logApiFailureOnce("Iris shader-pack detection failed.", throwable)
            }
            .getOrDefault(false)
    }

    @JvmStatic
    fun onPreHandDepthCopied(renderTargets: Any) {
        RenderSystem.assertOnRenderThread()

        val previous = renderTargetsRef?.get()
        if (previous !== renderTargets) {
            clearRenderTargetDependentResources()
            renderTargetsRef = WeakReference(renderTargets)
        }

        renderTargetsFrameSerial = HandPostProcessCoordinator.currentFrameSerial()
    }

    @JvmStatic
    fun finishSolidHandPhase() {
        RenderSystem.assertOnRenderThread()

        if (!canBuildExactMask()) {
            return
        }

        runCatching {
            val renderTargets = currentRenderTargetsForFrame() ?: return
            val beforeDepth = textureViewForNoHandDepth(depthTextureNoHand(renderTargets) ?: return) ?: return
            val afterDepth = textureViewForCurrentDepth(depthTexture(renderTargets) ?: return) ?: return
            val maskTargets = ensureMaskTargets(afterDepth) ?: return

            val rendered = IrisExactHandMaskPipeline.render(
                beforeDepth = beforeDepth,
                afterDepth = afterDepth,
                previousMask = requireNotNull(maskTargets.second.colorTextureView),
                outputMask = requireNotNull(maskTargets.first.colorTextureView),
                mergePrevious = false
            )

            if (rendered) {
                solidMaskFrameSerial = HandPostProcessCoordinator.currentFrameSerial()
                finalMaskFrameSerial = solidMaskFrameSerial
                finalMaskTarget = maskTargets.first
            } else {
                clearMaskSerials()
            }
        }.onFailure { throwable ->
            clearMaskSerials()
            logRenderTargetsFailureOnce("Iris solid hand mask generation failed.", throwable)
        }
    }

    @JvmStatic
    fun beginTranslucentHandPhase() {
        RenderSystem.assertOnRenderThread()

        if (!canBuildExactMask()) {
            return
        }

        runCatching {
            val renderTargets = currentRenderTargetsForFrame() ?: return
            val currentDepth = depthTexture(renderTargets) ?: return
            if (!isUsableTexture(currentDepth)) {
                clearTranslucentPreDepth()
                return
            }

            val preDepthTexture = ensureTranslucentPreDepthTexture(currentDepth) ?: return
            RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(
                currentDepth,
                preDepthTexture,
                0,
                0,
                0,
                0,
                0,
                preDepthTexture.getWidth(0),
                preDepthTexture.getHeight(0)
            )
            translucentPreDepthFrameSerial = HandPostProcessCoordinator.currentFrameSerial()
        }.onFailure { throwable ->
            clearTranslucentPreDepth()
            logRenderTargetsFailureOnce("Iris translucent pre-hand depth copy failed.", throwable)
        }
    }

    @JvmStatic
    fun finishTranslucentHandPhase() {
        RenderSystem.assertOnRenderThread()

        if (!canBuildExactMask()) {
            return
        }

        runCatching {
            val frame = HandPostProcessCoordinator.currentFrameSerial()
            val renderTargets = currentRenderTargetsForFrame() ?: return
            val preDepth = translucentPreDepthView
                ?.takeIf { !it.isClosed && it.texture().isClosed == false }

            if (translucentPreDepthFrameSerial != frame || preDepth == null) {
                preserveSolidMaskAsFinal(frame)
                return
            }

            val afterDepth = textureViewForCurrentDepth(depthTexture(renderTargets) ?: return) ?: return
            val maskTargets = ensureMaskTargets(afterDepth) ?: return
            val hasSolidMask = solidMaskFrameSerial == frame

            val output = if (hasSolidMask) maskTargets.second else maskTargets.first
            val previous = if (hasSolidMask) maskTargets.first else maskTargets.second
            val rendered = IrisExactHandMaskPipeline.render(
                beforeDepth = preDepth,
                afterDepth = afterDepth,
                previousMask = requireNotNull(previous.colorTextureView),
                outputMask = requireNotNull(output.colorTextureView),
                mergePrevious = hasSolidMask
            )

            if (rendered) {
                finalMaskFrameSerial = frame
                finalMaskTarget = output
            } else {
                preserveSolidMaskAsFinal(frame)
            }
        }.onFailure { throwable ->
            preserveSolidMaskAsFinal(HandPostProcessCoordinator.currentFrameSerial())
            logRenderTargetsFailureOnce("Iris translucent hand mask generation failed.", throwable)
        }
    }

    fun exactMaskForCurrentFrame(): ExactMaskSnapshot? {
        val frame = HandPostProcessCoordinator.currentFrameSerial()
        if (finalMaskFrameSerial != frame) {
            return null
        }

        val maskView = finalMaskTarget?.colorTextureView
            ?.takeIf { !it.isClosed && it.texture().isClosed == false } ?: return null

        return ExactMaskSnapshot(maskView, frame)
    }

    fun invalidate() {
        RenderSystem.assertOnRenderThread()
        clearRenderTargetDependentResources()
        renderTargetsRef = null
        renderTargetsFrameSerial = -1L
    }

    private fun canBuildExactMask(): Boolean {
        return isShaderPackInUse() &&
            HandPostProcessCoordinator.wantsIrisExactMask() &&
            IrisExactHandMaskPipeline.isAvailable
    }

    private fun currentRenderTargetsForFrame(): Any? {
        val frame = HandPostProcessCoordinator.currentFrameSerial()
        if (renderTargetsFrameSerial != frame) {
            return null
        }
        return renderTargetsRef?.get()
    }

    private fun preserveSolidMaskAsFinal(frame: Long) {
        if (solidMaskFrameSerial == frame) {
            finalMaskFrameSerial = frame
            finalMaskTarget = irisMaskA
        }
    }

    private fun clearRenderTargetDependentResources() {
        clearIrisTextureViews()
        clearTranslucentPreDepth()
        irisMaskA = irisMaskA.destroyAndNull()
        irisMaskB = irisMaskB.destroyAndNull()
        clearMaskSerials()
    }

    private fun clearIrisTextureViews() {
        noHandDepthView?.close()
        noHandDepthView = null
        noHandDepthTexture = null
        currentDepthView?.close()
        currentDepthView = null
        currentDepthTexture = null
    }

    private fun clearTranslucentPreDepth() {
        translucentPreDepthView?.close()
        translucentPreDepthView = null
        translucentPreDepthTexture?.close()
        translucentPreDepthTexture = null
        translucentPreDepthFormat = null
        translucentPreDepthFrameSerial = -1L
    }

    private fun clearMaskSerials() {
        solidMaskFrameSerial = -1L
        finalMaskFrameSerial = -1L
        finalMaskTarget = null
    }

    private fun textureViewForNoHandDepth(texture: GpuTexture): GpuTextureView? {
        if (!isUsableTexture(texture)) {
            clearIrisTextureViews()
            return null
        }

        if (texture !== noHandDepthTexture || noHandDepthView?.isClosed != false) {
            noHandDepthView?.close()
            noHandDepthTexture = texture
            noHandDepthView = RenderSystem.getDevice().createTextureView(texture)
        }

        return noHandDepthView?.takeIf { !it.isClosed && it.texture().isClosed == false }
    }

    private fun textureViewForCurrentDepth(texture: GpuTexture): GpuTextureView? {
        if (!isUsableTexture(texture)) {
            clearIrisTextureViews()
            return null
        }

        if (texture !== currentDepthTexture || currentDepthView?.isClosed != false) {
            currentDepthView?.close()
            currentDepthTexture = texture
            currentDepthView = RenderSystem.getDevice().createTextureView(texture)
        }

        return currentDepthView?.takeIf { !it.isClosed && it.texture().isClosed == false }
    }

    private fun ensureMaskTargets(depthView: GpuTextureView): Pair<TextureTarget, TextureTarget>? {
        val width = depthView.getWidth(0)
        val height = depthView.getHeight(0)
        if (width <= 0 || height <= 0) {
            irisMaskA = irisMaskA.destroyAndNull()
            irisMaskB = irisMaskB.destroyAndNull()
            clearMaskSerials()
            return null
        }

        val first = ensureMaskTarget(irisMaskA, "LiquidBounce Iris exact hand mask A", width, height).also {
            irisMaskA = it
        } ?: return null
        val second = ensureMaskTarget(irisMaskB, "LiquidBounce Iris exact hand mask B", width, height).also {
            irisMaskB = it
        } ?: return null

        return first to second
    }

    private fun ensureMaskTarget(current: TextureTarget?, label: String, width: Int, height: Int): TextureTarget? {
        if (current != null && !current.isMaskInvalid(width, height)) {
            return current
        }

        current.destroyAndNull()
        return TextureTarget(label, width, height, false)
    }

    private fun ensureTranslucentPreDepthTexture(depth: GpuTexture): GpuTexture? {
        val width = depth.getWidth(0)
        val height = depth.getHeight(0)
        val format = depth.getFormat()
        if (width <= 0 || height <= 0) {
            clearTranslucentPreDepth()
            return null
        }

        val current = translucentPreDepthTexture
        if (current != null &&
            !current.isClosed &&
            translucentPreDepthView?.isClosed == false &&
            translucentPreDepthFormat == format &&
            current.getWidth(0) == width &&
            current.getHeight(0) == height) {
            return current
        }

        clearTranslucentPreDepth()
        return runCatching {
            RenderSystem.getDevice().createTexture(
                "LiquidBounce Iris translucent pre-hand depth",
                GpuTexture.USAGE_COPY_DST or GpuTexture.USAGE_TEXTURE_BINDING,
                format,
                width,
                height,
                1,
                1
            ).also { texture ->
                translucentPreDepthTexture = texture
                translucentPreDepthView = RenderSystem.getDevice().createTextureView(texture)
                translucentPreDepthFormat = format
            }
        }.onFailure { throwable ->
            clearTranslucentPreDepth()
            logRenderTargetsFailureOnce("Iris exact-format translucent pre-depth allocation failed.", throwable)
        }.getOrNull()
    }

    private fun TextureTarget.isMaskInvalid(width: Int, height: Int): Boolean {
        return this.width != width ||
            this.height != height ||
            this.useDepth ||
            colorTexture?.isClosed != false ||
            colorTextureView?.isClosed != false
    }

    private fun depthTextureNoHand(renderTargets: Any): GpuTexture? {
        val methods = renderTargetMethods(renderTargets) ?: return null
        return methods.noHand.invoke(renderTargets) as? GpuTexture
    }

    private fun depthTexture(renderTargets: Any): GpuTexture? {
        val methods = renderTargetMethods(renderTargets) ?: return null
        return methods.current.invoke(renderTargets) as? GpuTexture
    }

    private fun renderTargetMethods(renderTargets: Any): RenderTargetMethods? {
        val clazz = renderTargets.javaClass
        if (renderTargetsClass != clazz) {
            renderTargetsClass = clazz
            getDepthTextureNoHandMethod = clazz.getMethod("getDepthTextureNoHand")
            getDepthTextureMethod = clazz.getMethod("getDepthTexture")
        }

        val noHand = getDepthTextureNoHandMethod ?: return null
        val current = getDepthTextureMethod ?: return null
        return RenderTargetMethods(noHand, current)
    }

    private fun isUsableTexture(texture: GpuTexture): Boolean {
        return !texture.isClosed && texture.getWidth(0) > 0 && texture.getHeight(0) > 0
    }

    private fun TextureTarget?.destroyAndNull(): TextureTarget? {
        this?.destroyBuffers()
        return null
    }

    private fun logApiFailureOnce(message: String, throwable: Throwable) {
        if (!apiFailureLogged) {
            apiFailureLogged = true
            logger.warn(message, throwable)
        }
    }

    private fun logRenderTargetsFailureOnce(message: String, throwable: Throwable) {
        if (!renderTargetsFailureLogged) {
            renderTargetsFailureLogged = true
            logger.warn(message, throwable)
        }
    }

    private data class RenderTargetMethods(
        val noHand: Method,
        val current: Method
    )
}
