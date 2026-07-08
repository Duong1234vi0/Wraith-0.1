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
package net.ccbluex.liquidbounce.features.module.modules.render.nametags

import com.mojang.blaze3d.pipeline.RenderTarget
import com.mojang.blaze3d.pipeline.TextureTarget
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.textures.FilterMode
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.render.TextureSetup

object NameTagsBlur {
    private const val STEPS = 3
    private const val OFFSET = 1.0f

    private val targets = arrayOfNulls<TextureTarget>(STEPS + 1)
    private var requestedThisFrame = false
    private var capturedThisFrame = false

    @JvmStatic
    fun beginFrame() {
        requestedThisFrame = false
        capturedThisFrame = false
    }

    @JvmStatic
    fun renderMaskedBackground(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        backgroundColor: Int
    ) {
        requestedThisFrame = true
        val setup = textureSetupForMask() ?: return
        NameTagsGuiGeometry.addSdfElement(
            context,
            NameTagsBlurPipelines.MASK_PIPELINE,
            setup,
            x,
            y,
            width,
            height,
            radius,
            0
        )
        NameTagsBackground.render(context, x, y, width, height, radius, backgroundColor)
    }

    @JvmStatic
    fun captureIfNeeded(mainTarget: RenderTarget) {
        if (!requestedThisFrame || capturedThisFrame) {
            return
        }
        capturedThisFrame = true
        requestedThisFrame = false

        RenderSystem.assertOnRenderThread()
        runCatching {
            renderKawase(mainTarget)
        }.onFailure { throwable ->
            logger.error("NameTags Kawase blur capture failed.", throwable)
            invalidate()
        }
    }

    @JvmStatic
    fun invalidate() {
        RenderSystem.assertOnRenderThread()
        requestedThisFrame = false
        capturedThisFrame = false
        for (i in targets.indices) {
            targets[i] = targets[i].destroyAndNull()
        }
    }

    private fun textureSetupForMask(): TextureSetup? {
        val mainTarget = mc.mainRenderTarget ?: return null
        ensureTargets(mainTarget) ?: return null
        val target = targets[0] ?: return null
        val view = target.colorTextureView ?: return null
        return TextureSetup.singleTexture(view, RenderSystem.getSamplerCache().getClampToEdge(FilterMode.LINEAR))
    }

    private fun renderKawase(mainTarget: RenderTarget) {
        val preparedTargets = ensureTargets(mainTarget) ?: return
        val sourceView = requireNotNull(mainTarget.colorTextureView) {
            "NameTags blur source target has no color texture view."
        }

        NameTagsBlurPipelines.renderPass(
            pipeline = NameTagsBlurPipelines.DOWNSCALE_PIPELINE,
            source = sourceView,
            output = requireNotNull(preparedTargets[0].colorTextureView),
            sourceWidth = mainTarget.width,
            sourceHeight = mainTarget.height,
            passIndex = 0,
            steps = STEPS,
            offset = OFFSET
        )

        for (i in 0 until STEPS) {
            val source = preparedTargets[i]
            val output = preparedTargets[i + 1]
            NameTagsBlurPipelines.renderPass(
                pipeline = NameTagsBlurPipelines.DOWNSCALE_PIPELINE,
                source = requireNotNull(source.colorTextureView),
                output = requireNotNull(output.colorTextureView),
                sourceWidth = source.width,
                sourceHeight = source.height,
                passIndex = i + 1,
                steps = STEPS,
                offset = OFFSET
            )
        }

        for (i in STEPS downTo 1) {
            val source = preparedTargets[i]
            val output = preparedTargets[i - 1]
            NameTagsBlurPipelines.renderPass(
                pipeline = NameTagsBlurPipelines.UPSCALE_PIPELINE,
                source = requireNotNull(source.colorTextureView),
                output = requireNotNull(output.colorTextureView),
                sourceWidth = source.width,
                sourceHeight = source.height,
                passIndex = i,
                steps = STEPS,
                offset = OFFSET
            )
        }
    }

    private fun ensureTargets(mainTarget: RenderTarget): Array<TextureTarget>? {
        val width = mainTarget.width
        val height = mainTarget.height
        if (width <= 0 || height <= 0) {
            invalidate()
            return null
        }

        for (i in targets.indices) {
            val current = targets[i]
            if (current == null || current.isInvalid(width, height)) {
                current.destroyAndNull()
                targets[i] = TextureTarget("LiquidBounce NameTags Kawase fbo$i", width, height, false)
            }
        }

        @Suppress("UNCHECKED_CAST")
        return targets as Array<TextureTarget>
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
}
