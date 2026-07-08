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

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.resources.Identifier

/**
 * Draws a player skin FACE (head front + hat overlay) into the GUI through
 * vanilla 1.26.2's batched render path ([GuiGraphicsExtractor.guiRenderState]),
 * the only custom-texture path that composites correctly on this fork.
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
    val drewFace = drawRoundedTexture(
        skinId,
        x,
        y,
        size,
        size,
        0f,
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
        0f,
        argb,
        HAT_U0,
        HAT_V0,
        HAT_U1,
        HAT_V1,
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

private const val FACE_U0 = 8f / 64f
private const val FACE_V0 = 8f / 64f
private const val FACE_U1 = 16f / 64f
private const val FACE_V1 = 16f / 64f
private const val HAT_U0 = 40f / 64f
private const val HAT_V0 = 8f / 64f
private const val HAT_U1 = 48f / 64f
private const val HAT_V1 = 16f / 64f
