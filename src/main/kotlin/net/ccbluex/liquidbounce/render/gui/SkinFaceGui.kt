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
@file:JvmName("SkinFaceGui")

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
 * Draws a player skin FACE (head front + hat overlay) into the GUI through
 * vanilla 1.26.2's batched render path ([GuiGraphicsExtractor.guiRenderState]),
 * the only custom-texture path that composites correctly on this fork. Mirrors
 * the [GuiGraphicsExtractor.drawCustomFontText] idiom in CustomFontGui.kt.
 *
 * The skin [Identifier] is resolved to its GPU [textureView]/[sampler] exactly
 * like `RenderCompat.bindTextureIfNeeded`. Standard 64x64 skin layout is
 * assumed: face = (8,8)..(16,16), hat overlay = (40,8)..(48,16).
 *
 * Returns false (drawing nothing) if the texture has no GPU view yet, so the
 * caller can fall back to a placeholder.
 */
fun GuiGraphicsExtractor.drawSkinFace(
    skinId: Identifier,
    x: Float,
    y: Float,
    size: Float,
    argb: Int = 0xFFFFFFFF.toInt(),
): Boolean {
    val texture = runCatching { mc.textureManager.getTexture(skinId) }.getOrNull() ?: return false
    val view = texture.textureView ?: return false
    val sampler = texture.sampler ?: return false

    val pose = Matrix3x2f(this.pose())
    val scissor = this.scissorStack.peek()
    val setup = TextureSetup.singleTexture(view, sampler)
    val pipeline = GlyphGuiElementRenderState.defaultPipeline()
    val bounds = quadBounds(x, y, x + size, y + size, pose, scissor)

    // Base face: u/v 8..16 of 64 -> 0.125..0.25
    this.guiRenderState.addGuiElement(
        GlyphGuiElementRenderState(
            x, y, x + size, y + size,
            FACE_U0, FACE_V0, FACE_U1, FACE_V1,
            argb, pipeline, setup, pose, scissor, bounds,
        )
    )
    // Hat overlay on top: u 40..48 / v 8..16 -> u 0.625..0.75, v 0.125..0.25
    this.guiRenderState.addGuiElement(
        GlyphGuiElementRenderState(
            x, y, x + size, y + size,
            HAT_U0, HAT_V0, HAT_U1, HAT_V1,
            argb, pipeline, setup, pose, scissor, bounds,
        )
    )
    return true
}

/**
 * #Edit By Codex 2026-06-27: rounded variant for Wyvern TargetHud avatars.
 * Both the base face and the hat overlay go through the same rounded texture
 * mask, so square skin corners cannot bleed through a circular/rounded frame.
 */
fun GuiGraphicsExtractor.drawRoundedSkinFace(
    skinId: Identifier,
    x: Float,
    y: Float,
    size: Float,
    radius: Float,
    argb: Int = 0xFFFFFFFF.toInt(),
): Boolean {
    val drewFace = drawRoundedTexture(
        skinId,
        x,
        y,
        size,
        size,
        radius,
        argb,
        FACE_U0,
        FACE_V0,
        FACE_U1,
        FACE_V1,
    )
    if (!drewFace) {
        return false
    }

    drawRoundedTexture(
        skinId,
        x,
        y,
        size,
        size,
        radius,
        argb,
        HAT_U0,
        HAT_V0,
        HAT_U1,
        HAT_V1,
    )
    return true
}

/**
 * Transforms the quad corners by [pose] and intersects the axis-aligned
 * bounding box with [scissor]. Copied from CustomFontGui.kt's glyphBounds so
 * vanilla's scissor/sort behaviour stays intact.
 */
private fun quadBounds(
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

private const val FACE_U0 = 8f / 64f
private const val FACE_V0 = 8f / 64f
private const val FACE_U1 = 16f / 64f
private const val FACE_V1 = 16f / 64f
private const val HAT_U0 = 40f / 64f
private const val HAT_V0 = 8f / 64f
private const val HAT_U1 = 48f / 64f
private const val HAT_V1 = 16f / 64f
