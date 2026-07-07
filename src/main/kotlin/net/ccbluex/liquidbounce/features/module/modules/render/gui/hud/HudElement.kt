/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.gui.drawSkinFace
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.resources.DefaultPlayerSkin
import net.minecraft.resources.Identifier
import kotlin.math.ceil

/**
 * Base class for HUD elements.
 *
 * #Edit By Codex 2026-06-22: all concrete HUD element implementations were removed.
 * The HUD module/editor can still handle an empty element list without rendering anything.
 */
abstract class HudElement(
    val name: String,
    x: Int,
    y: Int,
    var width: Int,
    var height: Int
) {
    var x: Int = x
        set(value) {
            field = value
            savePosition()
        }

    var y: Int = y
        set(value) {
            field = value
            savePosition()
        }

    var enabled: Boolean = true
        set(value) {
            field = value
            HudConfig.setElementEnabled(name, value)
        }

    init {
        loadFromConfig()
    }

    private fun loadFromConfig() {
        try {
            HudConfig.getElementPosition(name)?.let { saved ->
                fieldSetPosition(saved.first, saved.second)
            }
            enabled = HudConfig.getElementEnabled(name)
        } catch (e: Exception) {
            println("Error loading config for HUD element $name: ${e.message}")
        }
    }

    private fun fieldSetPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
    }

    private fun savePosition() {
        try {
            HudConfig.setElementPosition(name, x, y)
        } catch (e: Exception) {
            println("Error saving position for HUD element $name: ${e.message}")
        }
    }

    abstract fun render(context: DrawContext, isSelected: Boolean)

    fun renderScaled(context: DrawContext, isSelected: Boolean) {
        val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
        if (scale == 1f) {
            render(context, isSelected)
            return
        }

        context.pose().pushMatrix()
        try {
            context.pose().translate(x.toFloat(), y.toFloat())
            context.pose().scale(scale, scale)
            context.pose().translate(-x.toFloat(), -y.toFloat())
            render(context, isSelected)
        } finally {
            context.pose().popMatrix()
        }
    }

    open fun isVisibleInHud(): Boolean = enabled

    open fun renderPreview(context: DrawContext) {
        if (isVisibleInHud()) {
            render(context, false)
        }
    }

    fun renderPreviewScaled(context: DrawContext) {
        if (isVisibleInHud()) {
            renderScaled(context, false)
        }
    }

    fun isMouseOver(mouseX: Int, mouseY: Int): Boolean {
        val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
        return mouseX >= x &&
            mouseX <= x + width * scale &&
            mouseY >= y &&
            mouseY <= y + height * scale
    }

    open fun openSettings() {
        enabled = !enabled
    }

    protected fun renderBackground(context: DrawContext, isSelected: Boolean) {
        HudSurfaceRenderer.flushThemeEdges(context)
        if (isSelected) {
            context.fill(x, y, x + width, y + height, 0x80222222.toInt())
            context.fill(x, y, x + width, y + 1, 0xFFFFFFFF.toInt())
            context.fill(x, y + height - 1, x + width, y + height, 0xFFFFFFFF.toInt())
            context.fill(x, y, x + 1, y + height, 0xFFFFFFFF.toInt())
            context.fill(x + width - 1, y, x + width, y + height, 0xFFFFFFFF.toInt())
        }
    }
}

/**
 * Small top-left watermark: player head + version label.
 */
class SimpleWatermarkElement(x: Int, y: Int) : HudElement("Watermark", x, y, 96, 22) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val text = "SYLENT 0.1"
        val textSize = 10.5f
        val headSize = 24f
        val frameH = 20f
        val gap = 5f
        val padX = 7f
        val textW = ExosGuiRenderer.titleWidth(text, textSize)
        val textFrameW = textW + padX * 2f
        val totalW = headSize + gap + textFrameW
        val totalH = 24f
        val avatarTop = y.toFloat()
        val textTop = y.toFloat() + (totalH - frameH) * 0.5f
        val textX = x.toFloat() + headSize + gap

        width = ceil(totalW).toInt()
        height = ceil(totalH).toInt()

        val bgA = themedSurface(ClientTheme.accentA, 0.24f, 224)
        val bgB = themedSurface(ClientTheme.accentB, 0.20f, 218)
        val outline = ExosGuiRenderer.withAlpha(0xFF707070.toInt(), 18)

        // #Edit By Codex 2026-06-22: avatar and text use separate compact frames.
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x.toFloat(),
            avatarTop,
            headSize,
            headSize,
            5f,
            bgA,
            bgB,
            bgB,
            bgA
        )
        ExosGuiRenderer.roundedOutline(context.matrices, x.toFloat(), avatarTop, headSize, headSize, 5f, outline, 0.32f)

        val faceInset = 2f
        val drewFace = localSkinId()?.let { skin ->
            context.drawSkinFace(skin, x + faceInset, avatarTop + faceInset, headSize - faceInset * 2f)
        } == true

        if (!drewFace) {
            ExosGuiRenderer.roundedRect(
                context.matrices,
                x + faceInset,
                avatarTop + faceInset,
                headSize - faceInset * 2f,
                headSize - faceInset * 2f,
                3f,
                ExosGuiRenderer.withAlpha(ClientTheme.accentA, 190)
            )
            ExosGuiRenderer.title(context.matrices, "S", x + 9.2f, avatarTop + 6.6f, 0xFFFFFFFF.toInt(), 10f)
        }

        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            textX,
            textTop,
            textFrameW,
            frameH,
            5f,
            bgA,
            bgB,
            bgB,
            bgA
        )
        ExosGuiRenderer.roundedOutline(context.matrices, textX, textTop, textFrameW, frameH, 5f, outline, 0.32f)
        ExosGuiRenderer.title(
            context.matrices,
            text,
            textX + padX,
            textTop + (frameH - textSize) * 0.5f - 0.4f,
            0xFFFFFFFF.toInt(),
            textSize,
            shadow = true
        )

        renderBackground(context, isSelected)
    }

    private fun localSkinId(): Identifier? {
        val player = mc.player as? AbstractClientPlayer ?: return null
        // #Edit By Codex 2026-06-22: avoid rendering the purple missing-texture tile for insecure/default skins.
        val skin = runCatching { player.skin }.getOrNull()
        val skinId = skin?.takeIf { it.secure() }?.body()?.id()
        return skinId
            ?.takeUnless { it == MissingTextureAtlasSprite.getLocation() }
            ?: DefaultPlayerSkin.get(player.uuid).body().id()
    }

    private fun themedSurface(accent: Int, tint: Float, alpha: Int): Int {
        val base = 0xFF080B12.toInt()
        return ExosGuiRenderer.withAlpha(ExosGuiRenderer.mix(base, accent, tint), alpha)
    }
}
