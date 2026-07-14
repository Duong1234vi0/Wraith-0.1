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

import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.pipeline.TextureTarget
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.GpuTextureView
import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.render.hands.ModuleHands
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.entity.camera
import net.minecraft.client.CameraType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.GameType

/**
 * Shared first-person hand capture path for all hand post-processing modules.
 *
 * The vanilla hand/item is rendered exactly once. Effects receive the same raw
 * before-hand and after-hand color textures plus the main depth texture.
 */
object HandPostProcessCoordinator : EventListener {

    enum class HandMaskMode(val uniformValue: Int) {
        VANILLA_CAPTURE(0),
        IRIS_EXACT_MASK(1)
    }

    data class Snapshot(
        val beforeColor: GpuTextureView,
        val afterColor: GpuTextureView,
        val handDepth: GpuTextureView,
        val exactHandMask: GpuTextureView?,
        val maskMode: HandMaskMode,
        val width: Int,
        val height: Int
    )

    private var beforeTarget: TextureTarget? = null
    private var afterTarget: TextureTarget? = null
    private var captureStarted = false
    private var captureMode: HandMaskMode? = null
    private var lastCaptureMode: HandMaskMode? = null
    private var frameSerial = 0L
    private var exactIrisMaskUnavailableLogged = false

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        invalidate()
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        invalidate()
    }

    @JvmStatic
    fun beginFrame() {
        RenderSystem.assertOnRenderThread()
        frameSerial++
    }

    @JvmStatic
    fun currentFrameSerial(): Long = frameSerial

    @JvmStatic
    fun beginHandCapture(mainTarget: RenderTarget) {
        RenderSystem.assertOnRenderThread()
        captureStarted = false
        captureMode = null

        if (!shouldCapture()) {
            return
        }

        if (IrisHandCompat.isShaderPackInUse()) {
            beginIrisHandCapture(mainTarget)
            return
        }

        updateCaptureMode(HandMaskMode.VANILLA_CAPTURE)

        runCatching {
            val before = ensureTarget(beforeTarget, "LiquidBounce hand raw before", mainTarget).also {
                beforeTarget = it
            } ?: return

            copyColor(mainTarget, before)
            captureStarted = true
            captureMode = HandMaskMode.VANILLA_CAPTURE
        }.onFailure { throwable ->
            logger.error("Hand post-process before-hand capture failed.", throwable)
            invalidate()
        }
    }

    @JvmStatic
    fun finishHandCapture(mainTarget: RenderTarget) {
        RenderSystem.assertOnRenderThread()

        if (!captureStarted) {
            return
        }

        when (captureMode) {
            HandMaskMode.IRIS_EXACT_MASK -> finishIrisHandCapture(mainTarget)
            HandMaskMode.VANILLA_CAPTURE -> finishVanillaHandCapture(mainTarget)
            null -> captureStarted = false
        }
    }

    @JvmStatic
    fun wantsCapture(): Boolean {
        return shouldCapture()
    }

    @JvmStatic
    fun wantsIrisExactMask(): Boolean {
        return shouldCapture() && canRenderFirstPersonHand()
    }

    @JvmStatic
    fun invalidate() {
        RenderSystem.assertOnRenderThread()
        captureStarted = false
        captureMode = null
        beforeTarget = beforeTarget.destroyAndNull()
        afterTarget = afterTarget.destroyAndNull()
        IrisHandCompat.invalidate()
    }

    @JvmStatic
    fun invalidateSharedResourcesIfUnused(disablingModule: Any? = null) {
        RenderSystem.assertOnRenderThread()

        val handsActive = ModuleHands.running && disablingModule !== ModuleHands
        val handShaderActive = ModuleHandShader.running && disablingModule !== ModuleHandShader
        if (!handsActive && !handShaderActive) {
            invalidate()
        }
    }

    private fun beginIrisHandCapture(mainTarget: RenderTarget) {
        if (!canRenderFirstPersonHand()) {
            return
        }

        if (IrisHandCompat.exactMaskForCurrentFrame() == null) {
            logExactIrisMaskUnavailableOnce()
            return
        }

        if (mainTarget.width <= 0 ||
            mainTarget.height <= 0 ||
            mainTarget.colorTexture?.isClosed != false ||
            mainTarget.colorTextureView?.isClosed != false) {
            logExactIrisMaskUnavailableOnce()
            return
        }

        updateCaptureMode(HandMaskMode.IRIS_EXACT_MASK)
        captureStarted = true
        captureMode = HandMaskMode.IRIS_EXACT_MASK
    }

    private fun finishVanillaHandCapture(mainTarget: RenderTarget) {
        runCatching {
            val after = ensureTarget(afterTarget, "LiquidBounce hand raw after", mainTarget).also {
                afterTarget = it
            } ?: return

            copyColor(mainTarget, after)

            val snapshot = Snapshot(
                beforeColor = requireNotNull(beforeTarget?.colorTextureView) {
                    "Hand before target has no color texture view."
                },
                afterColor = requireNotNull(after.colorTextureView) {
                    "Hand after target has no color texture view."
                },
                handDepth = requireNotNull(mainTarget.depthTextureView) {
                    "Main target has no hand depth texture view."
                },
                exactHandMask = null,
                maskMode = HandMaskMode.VANILLA_CAPTURE,
                width = mainTarget.width,
                height = mainTarget.height
            )

            dispatch(snapshot, mainTarget)
        }.onFailure { throwable ->
            logger.error("Hand post-process dispatch failed.", throwable)
        }.also {
            captureStarted = false
            captureMode = null
        }
    }

    private fun finishIrisHandCapture(mainTarget: RenderTarget) {
        runCatching {
            val irisMask = IrisHandCompat.exactMaskForCurrentFrame() ?: run {
                logExactIrisMaskUnavailableOnce()
                return
            }
            val after = ensureTarget(afterTarget, "LiquidBounce hand raw Iris final", mainTarget).also {
                afterTarget = it
            } ?: run {
                logExactIrisMaskUnavailableOnce()
                return
            }

            copyColor(mainTarget, after)
            val finalColor = requireNotNull(after.colorTextureView) {
                "Hand Iris raw target has no color texture view."
            }

            val snapshot = Snapshot(
                beforeColor = finalColor,
                afterColor = finalColor,
                handDepth = irisMask.exactHandMask,
                exactHandMask = irisMask.exactHandMask,
                maskMode = HandMaskMode.IRIS_EXACT_MASK,
                width = mainTarget.width,
                height = mainTarget.height
            )

            dispatch(snapshot, mainTarget)
        }.onFailure { throwable ->
            logger.error("Iris hand post-process dispatch failed.", throwable)
        }.also {
            captureStarted = false
            captureMode = null
        }
    }

    private fun shouldCapture(): Boolean {
        return ModuleHandShader.shouldRender() || ModuleHands.shouldRender()
    }

    private fun dispatch(snapshot: Snapshot, mainTarget: RenderTarget) {
        if (ModuleHandShader.shouldRender()) {
            ModuleHandShader.renderFromRawCapture(snapshot, mainTarget)
        }

        if (ModuleHands.shouldRender()) {
            ModuleHands.renderFromRawCapture(snapshot, mainTarget)
        }
    }

    private fun updateCaptureMode(mode: HandMaskMode) {
        if (lastCaptureMode != null && lastCaptureMode != mode) {
            ModuleHands.invalidateTrailHistory()
        }
        lastCaptureMode = mode
    }

    private fun canRenderFirstPersonHand(): Boolean {
        mc.player ?: return false
        val camera = mc.gameRenderer.camera
        val cameraEntity = camera.entity()

        return mc.options.cameraType == CameraType.FIRST_PERSON &&
            !camera.isDetached &&
            !camera.isPanoramicMode &&
            cameraEntity is Player &&
            !cameraEntity.isSleeping &&
            !mc.options.hideGui &&
            mc.gameMode?.playerMode != GameType.SPECTATOR
    }

    private fun logExactIrisMaskUnavailableOnce() {
        if (!exactIrisMaskUnavailableLogged) {
            exactIrisMaskUnavailableLogged = true
            logger.warn(
                "Exact Iris hand mask unavailable; skipping hand post-processing for this frame."
            )
        }
    }

    private fun ensureTarget(current: TextureTarget?, label: String, mainTarget: RenderTarget): TextureTarget? {
        val width = mainTarget.width
        val height = mainTarget.height
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

    internal fun copyColor(source: RenderTarget, target: RenderTarget) {
        RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(
            requireNotNull(source.colorTexture) { "Hand source target has no color texture." },
            requireNotNull(target.colorTexture) { "Hand destination target has no color texture." },
            0,
            0,
            0,
            0,
            0,
            target.width,
            target.height
        )
    }
}
