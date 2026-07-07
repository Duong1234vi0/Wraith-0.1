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
import net.ccbluex.liquidbounce.render.gui.CORNERS_TOP
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.minecraft.client.gui.DrawContext
import kotlin.math.max
import kotlin.math.min

object HudSurfaceRenderer {
    private const val EDGE_STROKE_SCREEN_PX = 0.32f
    private const val EDGE_GLOW_SCREEN_SPREAD_PX = 1.0f
    private const val EDGE_BASE_ALPHA = 116f
    private const val EDGE_GLOW_BASE_ALPHA = 16f

    private val pendingThemeEdges = ArrayList<ThemeEdgeCommand>(16)
    private val recycledThemeEdges = ArrayList<ThemeEdgeCommand>(16)

    private class ThemeEdgeCommand {
        var x = 0f
        var y = 0f
        var width = 0f
        var height = 0f
        var radius = 0f
        var alpha = 0f
        var accentStrength = 0f

        fun set(
            x: Float,
            y: Float,
            width: Float,
            height: Float,
            radius: Float,
            alpha: Float,
            accentStrength: Float,
        ): ThemeEdgeCommand {
            this.x = x
            this.y = y
            this.width = width
            this.height = height
            this.radius = radius
            this.alpha = alpha
            this.accentStrength = accentStrength
            return this
        }
    }

    fun drawPanel(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        material: HudMaterial = ModuleHud.currentMaterial,
        alpha: Float = 1f,
        accentStrength: Float = 1f,
    ) {
        if (width <= 0f || height <= 0f || alpha <= 0.01f) {
            return
        }

        val radius = material.radius.coerceIn(2f, min(width, height) * 0.5f)
        val baseAlpha = (alpha * ModuleHud.backgroundAlpha).coerceIn(0f, 1f)
        val shadowEnabled = ModuleHud.shadowEnabled && material.shadowSpread > 0f

        drawThemeEdgeHalo(context, x, y, width, height, radius, alpha, accentStrength)

        if (shadowEnabled) {
            val shadowAlpha = (((material.shadowColor ushr 24) and 0xFF) *
                alpha * ModuleHud.shadowStrength).toInt().coerceIn(0, 32)
            if (shadowAlpha > 0) {
                ExosGuiRenderer.roundedRect(
                    context.matrices,
                    x + 0.5f,
                    y + material.shadowOffsetY,
                    (width - 1f).coerceAtLeast(0f),
                    height,
                    radius,
                    ExosGuiRenderer.withAlpha(material.shadowColor, shadowAlpha)
                )
                ExosGuiRenderer.roundedRect(
                    context.matrices,
                    x,
                    y + material.shadowOffsetY + material.shadowSpread * 0.35f,
                    width,
                    height,
                    radius,
                    ExosGuiRenderer.withAlpha(material.shadowColor, (shadowAlpha * 0.55f).toInt())
                )
            }
        }

        val top = withSurfaceAlpha(material.backgroundTop, baseAlpha)
        val bottom = withSurfaceAlpha(material.backgroundBottom, baseAlpha)
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x,
            y,
            width,
            height,
            radius,
            top,
            withSurfaceAlpha(ExosGuiRenderer.shade(material.backgroundTop, 0.92f), baseAlpha),
            bottom,
            withSurfaceAlpha(ExosGuiRenderer.shade(material.backgroundBottom, 1.04f), baseAlpha)
        )

        val highlightAlpha = (((material.highlightColor ushr 24) and 0xFF) * alpha).toInt().coerceIn(0, 255)
        if (highlightAlpha > 0) {
            ExosGuiRenderer.roundedRectCorners(
                context.matrices,
                x + 1f,
                y + 1f,
                (width - 2f).coerceAtLeast(0f),
                1.15f,
                max(0f, radius - 1f),
                ExosGuiRenderer.withAlpha(material.highlightColor, highlightAlpha),
                CORNERS_TOP
            )
        }

        queueThemeEdge(x, y, width, height, radius, alpha, accentStrength)
    }

    fun drawHeaderPanel(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        headerHeight: Float,
        alpha: Float,
        material: HudMaterial = ModuleHud.currentMaterial,
    ) {
        drawPanel(context, x, y, width, height, material, alpha)
        if (headerHeight <= 0f || height <= headerHeight) {
            return
        }

        val radius = material.radius.coerceIn(2f, min(width, height) * 0.5f)
        val headerTop = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.shade(material.backgroundTop, 1.10f),
            (105f * alpha * ModuleHud.backgroundAlpha).toInt().coerceIn(0, 255)
        )
        val headerBottom = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.shade(material.backgroundBottom, 1.02f),
            (72f * alpha * ModuleHud.backgroundAlpha).toInt().coerceIn(0, 255)
        )
        ExosGuiRenderer.roundedGradientRectCorners(
            context.matrices,
            x,
            y,
            width,
            headerHeight,
            radius,
            headerTop,
            ExosGuiRenderer.mix(headerTop, headerBottom, 0.25f),
            headerBottom,
            headerBottom,
            CORNERS_TOP
        )
        ExosGuiRenderer.roundedRect(
            context.matrices,
            x + 4f,
            y + headerHeight - 0.75f,
            (width - 8f).coerceAtLeast(0f),
            0.55f,
            0.25f,
            argb((32f * alpha).toInt(), 255, 255, 255)
        )
    }

    fun drawAccentStrip(context: DrawContext, x: Float, y: Float, width: Float, alpha: Float) {
        if (width <= 0f || alpha <= 0.01f) {
            return
        }
        val left = ExosGuiRenderer.withAlpha(ClientTheme.accentA, (75f * alpha).toInt())
        val right = ExosGuiRenderer.withAlpha(ClientTheme.accentB, (75f * alpha).toInt())
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x,
            y,
            width,
            1.1f,
            0.55f,
            left,
            right,
            right,
            left
        )
    }

    fun drawChip(context: DrawContext, x: Float, y: Float, width: Float, height: Float, alpha: Float) {
        if (width <= 0f || height <= 0f || alpha <= 0.01f) {
            return
        }
        val top = argb((56f * alpha).toInt(), 52, 52, 52)
        val bottom = argb((42f * alpha).toInt(), 22, 22, 22)
        ExosGuiRenderer.roundedGradientRect(context.matrices, x, y, width, height, 2f, top, top, bottom, bottom)
    }

    fun clearPendingEdges() {
        recyclePendingEdges()
    }

    fun flushThemeEdges(context: DrawContext) {
        if (pendingThemeEdges.isEmpty()) {
            return
        }

        var index = 0
        while (index < pendingThemeEdges.size) {
            val edge = pendingThemeEdges[index]
            drawThemeEdge(
                context,
                edge.x,
                edge.y,
                edge.width,
                edge.height,
                edge.radius,
                edge.alpha,
                edge.accentStrength
            )
            index++
        }
        recyclePendingEdges()
    }

    fun drawThemeEdge(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        alpha: Float,
        accentStrength: Float,
    ) {
        if (!ModuleHud.themeEdgeEnabled || width <= 0f || height <= 0f) {
            return
        }

        val edgeAlpha = edgeAlpha(alpha, accentStrength, EDGE_BASE_ALPHA).coerceAtMost(125)
        if (edgeAlpha <= 1) {
            return
        }

        val stroke = EDGE_STROKE_SCREEN_PX / ModuleHud.hudScale.coerceAtLeast(0.01f)
        val topLeft = ExosGuiRenderer.withAlpha(ClientTheme.accentA, edgeAlpha)
        val topRight = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.mix(ClientTheme.accentA, ClientTheme.accentB, 0.52f),
            (edgeAlpha * 0.9f).toInt()
        )
        val bottomRight = ExosGuiRenderer.withAlpha(ClientTheme.accentB, edgeAlpha)
        val bottomLeft = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.shade(ClientTheme.accentA, 0.62f),
            (edgeAlpha * 0.58f).toInt()
        )

        ExosGuiRenderer.roundedGradientOutline(
            context.matrices,
            x,
            y,
            width,
            height,
            radius,
            topLeft,
            topRight,
            bottomRight,
            bottomLeft,
            stroke
        )
    }

    private fun withSurfaceAlpha(color: Int, alphaFactor: Float): Int {
        val alpha = (((color ushr 24) and 0xFF) * alphaFactor).toInt().coerceIn(0, 255)
        return ExosGuiRenderer.withAlpha(color, alpha)
    }

    private fun queueThemeEdge(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        alpha: Float,
        accentStrength: Float,
    ) {
        if (!ModuleHud.themeEdgeEnabled || edgeAlpha(alpha, accentStrength, EDGE_BASE_ALPHA) <= 1) {
            return
        }
        val edge = if (recycledThemeEdges.isNotEmpty()) {
            recycledThemeEdges.removeAt(recycledThemeEdges.lastIndex)
        } else {
            ThemeEdgeCommand()
        }
        pendingThemeEdges += edge.set(x, y, width, height, radius, alpha, accentStrength)
    }

    private fun drawThemeEdgeHalo(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        alpha: Float,
        accentStrength: Float,
    ) {
        if (!ModuleHud.edgeGlowEnabled || width <= 0f || height <= 0f) {
            return
        }

        val glowAlpha = edgeAlpha(alpha, accentStrength, EDGE_GLOW_BASE_ALPHA).coerceAtMost(20)
        if (glowAlpha <= 1) {
            return
        }

        val spread = EDGE_GLOW_SCREEN_SPREAD_PX / ModuleHud.hudScale.coerceAtLeast(0.01f)
        val topLeft = ExosGuiRenderer.withAlpha(ClientTheme.accentA, glowAlpha)
        val topRight = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.mix(ClientTheme.accentA, ClientTheme.accentB, 0.52f),
            (glowAlpha * 0.88f).toInt()
        )
        val bottomRight = ExosGuiRenderer.withAlpha(ClientTheme.accentB, glowAlpha)
        val bottomLeft = ExosGuiRenderer.withAlpha(
            ExosGuiRenderer.shade(ClientTheme.accentA, 0.62f),
            (glowAlpha * 0.52f).toInt()
        )

        ExosGuiRenderer.shaderGradientGlow(
            context.matrices,
            x,
            y,
            width,
            height,
            radius,
            topLeft,
            topRight,
            bottomRight,
            bottomLeft,
            spread
        )
    }

    private fun edgeAlpha(alpha: Float, accentStrength: Float, baseAlpha: Float): Int {
        val factor = alpha.coerceIn(0f, 1f) *
            ModuleHud.backgroundAlpha.coerceIn(0f, 1f) *
            accentStrength.coerceIn(0f, 1f) *
            ModuleHud.themeEdgeStrength
        return (baseAlpha * factor).toInt().coerceIn(0, 255)
    }

    private fun recyclePendingEdges() {
        if (pendingThemeEdges.isEmpty()) {
            return
        }
        recycledThemeEdges += pendingThemeEdges
        pendingThemeEdges.clear()
    }

    private fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or
            (red.coerceIn(0, 255) shl 16) or
            (green.coerceIn(0, 255) shl 8) or
            blue.coerceIn(0, 255)
    }
}
