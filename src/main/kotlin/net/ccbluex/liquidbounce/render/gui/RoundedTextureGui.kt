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
@file:JvmName("RoundedTextureGui")

package net.ccbluex.liquidbounce.render.gui

import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import net.minecraft.client.gui.render.TextureSetup
import net.minecraft.resources.Identifier
import org.joml.Matrix3x2f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max

/**
 * Draws a texture region clipped by the rounded texture shader.
 *
 * Returns false if the texture is not backed by a GPU view yet, allowing HUD
 * callers to fall back without crashing during startup/resource reload.
 */
@Suppress("LongParameterList")
fun GuiGraphicsExtractor.drawRoundedTexture(
    textureId: Identifier,
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: Float,
    argb: Int,
    u0: Float = 0f,
    v0: Float = 0f,
    u1: Float = 1f,
    v1: Float = 1f,
): Boolean {
    if (width <= 0f || height <= 0f) {
        return false
    }

    val texture = runCatching { mc.textureManager.getTexture(textureId) }.getOrNull() ?: return false
    val view = texture.textureView ?: return false
    val sampler = texture.sampler ?: return false
    val pose = Matrix3x2f(this.pose())
    val scissor = this.scissorStack.peek()
    val x1 = x + width
    val y1 = y + height

    this.guiRenderState.addGuiElement(
        RoundedTextureGuiElementRenderState(
            x, y, x1, y1,
            u0, v0, u1, v1,
            argb,
            radius.coerceIn(0f, minOf(width, height) * 0.5f),
            TextureSetup.singleTexture(view, sampler),
            pose,
            scissor,
            roundedTextureBounds(x, y, x1, y1, pose, scissor),
        )
    )
    return true
}

private fun roundedTextureBounds(
    x0: Float,
    y0: Float,
    x1: Float,
    y1: Float,
    pose: Matrix3x2f,
    scissor: ScreenRectangle?,
): ScreenRectangle? {
    val v1x = pose.m00() * x0 + pose.m10() * y0 + pose.m20()
    val v1y = pose.m01() * x0 + pose.m11() * y0 + pose.m21()
    val v2x = pose.m00() * x1 + pose.m10() * y0 + pose.m20()
    val v2y = pose.m01() * x1 + pose.m11() * y0 + pose.m21()
    val v3x = pose.m00() * x0 + pose.m10() * y1 + pose.m20()
    val v3y = pose.m01() * x0 + pose.m11() * y1 + pose.m21()
    val v4x = pose.m00() * x1 + pose.m10() * y1 + pose.m20()
    val v4y = pose.m01() * x1 + pose.m11() * y1 + pose.m21()
    val minX = floor(minOf(v1x, v2x, v3x, v4x)).toInt()
    val minY = floor(minOf(v1y, v2y, v3y, v4y)).toInt()
    val maxX = ceil(maxOf(v1x, v2x, v3x, v4x)).toInt()
    val maxY = ceil(maxOf(v1y, v2y, v3y, v4y)).toInt()
    val rect = ScreenRectangle(minX, minY, max(1, maxX - minX), max(1, maxY - minY))
    return runCatching { scissor?.intersection(rect) ?: rect }.getOrDefault(rect)
}
