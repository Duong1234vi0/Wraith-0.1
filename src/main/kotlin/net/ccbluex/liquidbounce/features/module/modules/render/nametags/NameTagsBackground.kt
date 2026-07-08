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

import com.mojang.blaze3d.pipeline.RenderPipeline
import net.ccbluex.liquidbounce.render.gui.NameTagsSdfGuiElementRenderState
import net.ccbluex.liquidbounce.render.gui.NameTagsTextureGuiElementRenderState
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import net.minecraft.client.gui.render.TextureSetup
import net.minecraft.resources.Identifier
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sqrt

object NameTagsBackground {

    fun render(context: DrawContext, x: Float, y: Float, width: Float, height: Float, radius: Float, argb: Int) {
        val geometry = NameTagsGuiGeometry.from(context, x, y, width, height, radius) ?: return
        context.guiRenderState.addGuiElement(
            NameTagsSdfGuiElementRenderState(
                NameTagsBackgroundShader.PIPELINE,
                TextureSetup.noTexture(),
                geometry.x0,
                geometry.y0,
                geometry.x1,
                geometry.y1,
                argb,
                geometry.locationX,
                geometry.locationY,
                geometry.sizeXFixed,
                geometry.sizeYFixed,
                geometry.radiusFixed,
                geometry.pose,
                geometry.scissor,
                geometry.bounds
            )
        )
    }
}

object NameTagsSkin {

    fun render(context: DrawContext, skin: Identifier, x: Float, y: Float, size: Float) {
        NameTagsBackground.render(context, x, y, size, size, size / 2.0f, -1)

        val texture = runCatching { mc.textureManager.getTexture(skin) }.getOrNull() ?: return
        val view = texture.textureView ?: return
        val sampler = texture.sampler ?: return
        val geometry = NameTagsGuiGeometry.from(context, x, y, size, size, 0.0f) ?: return

        context.guiRenderState.addGuiElement(
            NameTagsTextureGuiElementRenderState(
                geometry.x0,
                geometry.y0,
                geometry.x1,
                geometry.y1,
                8.0f / 64.0f,
                8.0f / 64.0f,
                16.0f / 64.0f,
                16.0f / 64.0f,
                -1,
                TextureSetup.singleTexture(view, sampler),
                geometry.pose,
                geometry.scissor,
                geometry.bounds
            )
        )
    }
}

internal object NameTagsGuiGeometry {
    private const val SIZE_FIXED = 16.0f
    private const val RADIUS_FIXED = 256.0f

    fun from(
        context: GuiGraphicsExtractor,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float
    ): Geometry? {
        if (width <= 0.0f || height <= 0.0f) {
            return null
        }

        val x0 = minOf(x, x + width)
        val y0 = minOf(y, y + height)
        val x1 = maxOf(x, x + width)
        val y1 = maxOf(y, y + height)
        val pose = Matrix3x2f(context.pose())
        val scissor = context.scissorStack.peek()
        val bounds = bounds(x0, y0, x1, y1, pose, scissor)

        val guiScale = mc.window.guiScale.toFloat()
        val transformedX = pose.m00() * x0 + pose.m10() * y0 + pose.m20()
        val transformedY = pose.m01() * x0 + pose.m11() * y0 + pose.m21()
        val scaleX = sqrt(pose.m00() * pose.m00() + pose.m01() * pose.m01()).coerceAtLeast(0.0001f)
        val scaleY = sqrt(pose.m10() * pose.m10() + pose.m11() * pose.m11()).coerceAtLeast(0.0001f)
        val scaledWidth = width * scaleX * guiScale
        val scaledHeight = height * scaleY * guiScale
        val locationX = transformedX * guiScale
        val locationY = mc.window.height.toFloat() - scaledHeight - transformedY * guiScale

        return Geometry(
            x0 = x0,
            y0 = y0,
            x1 = x1,
            y1 = y1,
            locationX = locationX,
            locationY = locationY,
            sizeXFixed = fixed(scaledWidth, SIZE_FIXED),
            sizeYFixed = fixed(scaledHeight, SIZE_FIXED),
            radiusFixed = fixed(radius * scaleY * guiScale, RADIUS_FIXED),
            pose = pose,
            scissor = scissor,
            bounds = bounds
        )
    }

    fun addSdfElement(
        context: GuiGraphicsExtractor,
        pipeline: RenderPipeline,
        textureSetup: TextureSetup,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        argb: Int
    ) {
        val geometry = from(context, x, y, width, height, radius) ?: return
        context.guiRenderState.addGuiElement(
            NameTagsSdfGuiElementRenderState(
                pipeline,
                textureSetup,
                geometry.x0,
                geometry.y0,
                geometry.x1,
                geometry.y1,
                argb,
                geometry.locationX,
                geometry.locationY,
                geometry.sizeXFixed,
                geometry.sizeYFixed,
                geometry.radiusFixed,
                geometry.pose,
                geometry.scissor,
                geometry.bounds
            )
        )
    }

    private fun fixed(value: Float, scale: Float): Int =
        (value * scale).roundToInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt())

    private fun bounds(
        x0: Float,
        y0: Float,
        x1: Float,
        y1: Float,
        pose: Matrix3x2f,
        scissor: ScreenRectangle?
    ): ScreenRectangle? {
        fun tx(x: Float, y: Float) = pose.m00() * x + pose.m10() * y + pose.m20()
        fun ty(x: Float, y: Float) = pose.m01() * x + pose.m11() * y + pose.m21()

        val minX = floor(minOf(tx(x0, y0), tx(x1, y0), tx(x0, y1), tx(x1, y1))).toInt()
        val minY = floor(minOf(ty(x0, y0), ty(x1, y0), ty(x0, y1), ty(x1, y1))).toInt()
        val maxX = ceil(maxOf(tx(x0, y0), tx(x1, y0), tx(x0, y1), tx(x1, y1))).toInt()
        val maxY = ceil(maxOf(ty(x0, y0), ty(x1, y0), ty(x0, y1), ty(x1, y1))).toInt()
        val rect = ScreenRectangle(minX, minY, max(1, maxX - minX), max(1, maxY - minY))
        return runCatching { scissor?.intersection(rect) ?: rect }.getOrDefault(rect)
    }

    data class Geometry(
        val x0: Float,
        val y0: Float,
        val x1: Float,
        val y1: Float,
        val locationX: Float,
        val locationY: Float,
        val sizeXFixed: Int,
        val sizeYFixed: Int,
        val radiusFixed: Int,
        val pose: Matrix3x2f,
        val scissor: ScreenRectangle?,
        val bounds: ScreenRectangle?
    )
}
