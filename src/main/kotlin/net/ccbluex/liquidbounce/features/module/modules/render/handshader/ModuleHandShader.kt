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
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.submersionType
import net.minecraft.client.CameraType
import net.minecraft.world.level.material.FogType

object ModuleHandShader : ClientModule("HandShader", Category.RENDER) {

    enum class Mode(override val choiceName: String) : net.ccbluex.liquidbounce.config.types.NamedChoice {
        Full("Full"),
        Partial("Partial"),
        WebShader("WebShader"),
        Plasma("Plasma")
    }

    private val mode by enumChoice("Mode", Mode.Full).apply(::tagBy)
    private val color by color("Color", Color4b(150, 130, 255))
    private val blurRadius by float("BlurRadius", 18.0f, 1.0f..50.0f)
    private val opacity by float("Opacity", 0.6f, 0.0f..1.0f)
    private val threshold by float("Threshold", 0.03f, 0.005f..0.15f)
    private val shaderSpeed by float("ShaderSpeed", 1.0f, 0.1f..3.0f)
    private val shaderScale by float("ShaderScale", 1.0f, 0.25f..4.0f)

    private var outputTarget: TextureTarget? = null

    override fun disable() {
        invalidateCapture()
        HandPostProcessCoordinator.invalidateSharedResourcesIfUnused(this)
    }

    @JvmStatic
    fun shouldRender(): Boolean {
        val localPlayer = mc.player ?: return false

        return running &&
            HandShaderPipeline.isAvailable &&
            mc.options.cameraType == CameraType.FIRST_PERSON &&
            mc.gameRenderer.camera.submersionType == FogType.NONE &&
            !localPlayer.isUnderWater &&
            !localPlayer.isInLava &&
            (!localPlayer.isSwimming || !localPlayer.isInWater)
    }

    @JvmStatic
    fun beginHandCapture(mainTarget: RenderTarget) {
        HandPostProcessCoordinator.beginHandCapture(mainTarget)
    }

    @JvmStatic
    fun finishHandCapture(mainTarget: RenderTarget) {
        HandPostProcessCoordinator.finishHandCapture(mainTarget)
    }

    fun renderFromRawCapture(snapshot: HandPostProcessCoordinator.Snapshot, mainTarget: RenderTarget) {
        RenderSystem.assertOnRenderThread()

        if (!shouldRender()) {
            return
        }

        runCatching {
            val output = ensureOutputTarget(mainTarget) ?: return

            HandShaderPipeline.render(
                sourceColor = snapshot.afterColor,
                handDepth = snapshot.handDepth,
                handMask = snapshot.exactHandMask ?: snapshot.handDepth,
                outputColor = requireNotNull(output.colorTextureView) {
                    "HandShader output target has no color texture view."
                },
                maskMode = snapshot.maskMode,
                mode = mode,
                tint = color,
                blurRadius = blurRadius,
                threshold = threshold,
                opacity = opacity,
                time = ((System.currentTimeMillis() % 100000L) / 1000.0f) * shaderSpeed,
                shaderScale = shaderScale
            ).takeIf { it } ?: return

            HandPostProcessCoordinator.copyColor(output, mainTarget)
        }.onFailure { throwable ->
            logger.error("HandShader post-process failed.", throwable)
            invalidateCapture()
        }
    }

    @JvmStatic
    fun invalidateCapture() {
        RenderSystem.assertOnRenderThread()
        outputTarget = outputTarget.destroyAndNull()
    }

    private fun ensureOutputTarget(mainTarget: RenderTarget): TextureTarget? {
        outputTarget = ensureTarget(outputTarget, "LiquidBounce HandShader output", mainTarget, useDepth = false)
        return outputTarget
    }

    private fun ensureTarget(
        current: TextureTarget?,
        label: String,
        mainTarget: RenderTarget,
        useDepth: Boolean
    ): TextureTarget? {
        val width = mainTarget.width
        val height = mainTarget.height
        if (width <= 0 || height <= 0) {
            return current.destroyAndNull()
        }

        if (current != null && !current.isInvalid(width, height, useDepth)) {
            return current
        }

        current.destroyAndNull()
        return TextureTarget(label, width, height, useDepth)
    }

    private fun TextureTarget.isInvalid(width: Int, height: Int, useDepth: Boolean): Boolean {
        return this.width != width ||
            this.height != height ||
            this.useDepth != useDepth ||
            colorTexture?.isClosed != false ||
            colorTextureView?.isClosed != false ||
            (useDepth && (depthTexture?.isClosed != false || depthTextureView?.isClosed != false))
    }

    private fun TextureTarget?.destroyAndNull(): TextureTarget? {
        this?.destroyBuffers()
        return null
    }
}
