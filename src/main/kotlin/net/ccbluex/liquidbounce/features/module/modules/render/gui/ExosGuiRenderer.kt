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
package net.ccbluex.liquidbounce.features.module.modules.render.gui

import com.mojang.blaze3d.vertex.PoseStack
import net.ccbluex.liquidbounce.render.FontManager
import net.ccbluex.liquidbounce.render.font.msdf.FontRenderer
import net.ccbluex.liquidbounce.render.gui.drawGradientGlowRect
import net.ccbluex.liquidbounce.render.gui.drawGlowRect
import net.ccbluex.liquidbounce.render.gui.drawRoundedGradientOutline
import net.ccbluex.liquidbounce.render.gui.drawRoundedOutline
import net.ccbluex.liquidbounce.render.gui.drawRoundedRect
import net.ccbluex.liquidbounce.render.gui.drawRoundedRectVerticalRadii
import net.ccbluex.liquidbounce.utils.entity.drawContextForMatrices
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * Lightweight 2D renderer for HUD and small module UI surfaces.
 *
 * Originally this lived on `DrawContext.fillGradient` (square corners,
 * 2-color gradient) which silently dropped the `radius` arg. This
 * version routes every primitive through the SDF rounded-rect pipeline
 * registered by [net.ccbluex.liquidbounce.render.gui.RoundedRectShader],
 * giving anti-aliased corners, true 4-corner gradients, and a real
 * stroke mode — submitted via vanilla 1.26.2's batched GUI render path
 * so scissor / pose / z-order all behave like other elements.
 *
 * Public function signatures are kept stable for existing HUD/render call-sites.
 */
object ExosGuiRenderer {

    fun roundedRect(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int,
    ) = roundedGradientRect(matrices, x, y, width, height, radius, color, color, color, color)

    fun roundedGradientRect(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }

        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedRect(
            x, y, width, height, radius,
            topLeft, topRight, bottomRight, bottomLeft,
            strokePx = 0f,
        )
    }

    fun roundedOutline(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int,
        strokeWidth: Float = 1f,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }

        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedOutline(x, y, width, height, radius, color, strokeWidth)
    }

    /**
     * #Edit By Claude 2026-06-11: rounded fill where only SOME corners are
     * rounded (the rest stay square). [cornerMask] uses the CORNER_* / CORNERS_*
     * constants from RoundedRectGui. Lets a title band round just its top edge
     * in ONE draw instead of the old "rounded rect + flat overlay" hack (which
     * left a visible seam on dark gradients). Single solid colour.
     */
    fun roundedRectCorners(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int,
        cornerMask: Int,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedRect(x, y, width, height, radius, color, cornerMask)
    }

    /**
     * #Edit By Claude 2026-06-11: 4-corner gradient fill restricted to a chosen
     * corner mask (top-rounded title bands, etc.).
     */
    @Suppress("LongParameterList")
    fun roundedGradientRectCorners(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int,
        cornerMask: Int,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedRect(
            x, y, width, height, radius,
            topLeft, topRight, bottomRight, bottomLeft,
            strokePx = 0f,
            cornerMask = cornerMask,
        )
    }

    /**
     * #Edit By Codex 2026-06-19: 4-corner gradient fill with separate top and
     * bottom radii. Used by the detached module-settings frame so its top edge
     * can be more rounded than the lower edge without drawing a second overlay.
     */
    @Suppress("LongParameterList")
    fun roundedGradientRectVerticalRadii(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        topRadius: Float,
        bottomRadius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int,
        cornerMask: Int = 0xF,
        strokeWidth: Float = 0f,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedRectVerticalRadii(
            x, y, width, height, topRadius, bottomRadius,
            topLeft, topRight, bottomRight, bottomLeft,
            strokePx = strokeWidth,
            cornerMask = cornerMask,
        )
    }

    /**
     * #Edit By Claude 2026-06-11: gradient stroke outline (four corner colours).
     * Mirrors celestial's interpolated outline_rect.frag edge colour.
     */
    @Suppress("LongParameterList")
    fun roundedGradientOutline(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int,
        strokeWidth: Float = 1f,
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        context.drawRoundedGradientOutline(
            x, y, width, height, radius,
            topLeft, topRight, bottomRight, bottomLeft,
            strokeWidth,
        )
    }

    /**
     * Draws a circular ring sector. Approximated as triangle fan because
     * the SDF rounded-rect pipeline is rectangle-only; this path is rare
     * (HUD watermark / target progress) and short-lived enough that the
     * one-off geometry is fine.
     */
    fun ringSegment(
        matrices: PoseStack,
        centerX: Float,
        centerY: Float,
        radius: Float,
        thickness: Float,
        startDegrees: Float,
        sweepDegrees: Float,
        color: Int,
        segments: Int = 56,
    ) {
        if (radius <= 0f || thickness <= 0f || sweepDegrees <= 0f) {
            return
        }

        val context = drawContextForMatrices(matrices) ?: return
        val sweep = sweepDegrees.coerceIn(0f, 360f)
        val outerRadius = radius
        val innerRadius = (radius - thickness).coerceAtLeast(0f)
        val steps = max(3, (segments * (sweep / 360f)).toInt())

        // Approximate the arc by marching along its centerline and
        // painting tiny rounded squares whose radius matches the band
        // half-thickness, so the outer edge ends up anti-aliased.
        val bandHalf = (outerRadius - innerRadius) * 0.5f
        val bandRadius = (outerRadius + innerRadius) * 0.5f
        val tile = max(1f, bandHalf * 0.9f)
        for (i in 0..steps) {
            val a = (startDegrees + sweep * i / steps) * PI.toFloat() / 180f
            val cx = centerX + cos(a.toDouble()).toFloat() * bandRadius
            val cy = centerY + sin(a.toDouble()).toFloat() * bandRadius
            context.drawRoundedRect(
                x = cx - tile,
                y = cy - tile,
                width = tile * 2f,
                height = tile * 2f,
                radiusPx = tile,
                color = color,
            )
        }
    }

    fun text(
        matrices: PoseStack,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        size: Float = 15f,
        shadow: Boolean = false,
    ) {
        if (text.isEmpty()) {
            return
        }

        drawWithRenderer(matrices, FontManager.default(size), text, x, y, color, shadow)
    }

    fun textInter(
        matrices: PoseStack,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        size: Float,
        shadow: Boolean = false,
    ) {
        if (text.isEmpty()) {
            return
        }

        drawWithRenderer(matrices, FontManager.default(size), text, x, y, color, shadow)
    }

    private fun drawWithRenderer(
        matrices: PoseStack,
        renderer: FontRenderer,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        shadow: Boolean,
    ) {
        if (text.isEmpty()) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        if (shadow) {
            // AlphaDLC's MSDF font renderer does not add a shader-side shadow.
        }
        renderer.drawString(context, text, x, y, color)
    }

    fun title(
        matrices: PoseStack,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        size: Float = 15f,
        shadow: Boolean = false,
    ) = drawWithRenderer(matrices, FontManager.title(size), text, x, y, color, shadow)

    fun body(
        matrices: PoseStack,
        text: String,
        x: Float,
        y: Float,
        color: Int,
        size: Float = 15f,
        shadow: Boolean = false,
    ) = drawWithRenderer(matrices, FontManager.body(size), text, x, y, color, shadow)

    fun titleWidth(text: String, size: Float = 15f): Float {
        if (text.isEmpty()) {
            return 0f
        }

        return FontManager.title(size).getStringWidth(text)
    }

    fun bodyWidth(text: String, size: Float = 15f): Float {
        if (text.isEmpty()) {
            return 0f
        }

        return FontManager.body(size).getStringWidth(text)
    }

    fun titleHeight(text: String = "A", size: Float = 15f): Float =
        FontManager.title(size).getStringHeight(text)

    fun titleHeight(size: Float): Float = titleHeight("A", size)

    fun bodyHeight(text: String = "A", size: Float = 15f): Float =
        FontManager.body(size).getStringHeight(text)

    fun bodyHeight(size: Float): Float = bodyHeight("A", size)

    /** #Edit By Claude: scissor helpers so callers can clip effects (e.g. the
     * TargetHud sheen) to a region. Coordinates are screen-space pixels. */
    fun enableScissor(matrices: PoseStack, x: Float, y: Float, x2: Float, y2: Float) {
        val context = drawContextForMatrices(matrices) ?: return
        context.enableScissor(x.toInt(), y.toInt(), x2.toInt(), y2.toInt())
    }

    fun disableScissor(matrices: PoseStack) {
        val context = drawContextForMatrices(matrices) ?: return
        context.disableScissor()
    }

    fun textWidth(text: String, size: Float = 15f): Float {
        if (text.isEmpty()) {
            return 0f
        }

        return FontManager.default(size).getStringWidth(text)
    }

    fun textHeight(text: String = "A", size: Float = 15f): Float =
        FontManager.default(size).getStringHeight(text)

    fun textHeight(size: Float): Float = textHeight("A", size)

    fun withAlpha(color: Int, alpha: Int): Int =
        (alpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF)

    /**
     * #Edit By Claude: returns a darker shade of [color] in the SAME hue,
     * scaling each RGB channel by [factor] (0 = black, 1 = unchanged).
     * Alpha is preserved. Used for the monochrome dark->bright gradients on
     * the Potions title separator and the TargetHud health bar so a flat
     * white selection becomes dim-white -> bright-white rather than one
     * solid tone.
     */
    fun shade(color: Int, factor: Float): Int {
        val f = factor.coerceIn(0f, 1f)
        val a = (color ushr 24) and 0xFF
        val r = (((color shr 16) and 0xFF) * f).toInt().coerceIn(0, 255)
        val g = (((color shr 8) and 0xFF) * f).toInt().coerceIn(0, 255)
        val b = ((color and 0xFF) * f).toInt().coerceIn(0, 255)
        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }

    fun mix(a: Int, b: Int, progress: Float): Int {
        val t = progress.coerceIn(0f, 1f)
        val ar = (a shr 16) and 0xFF
        val ag = (a shr 8) and 0xFF
        val ab = a and 0xFF
        val aa = (a ushr 24) and 0xFF
        val br = (b shr 16) and 0xFF
        val bg = (b shr 8) and 0xFF
        val bb = b and 0xFF
        val ba = (b ushr 24) and 0xFF

        return (((aa + (ba - aa) * t).toInt().coerceIn(0, 255)) shl 24) or
            (((ar + (br - ar) * t).toInt().coerceIn(0, 255)) shl 16) or
            (((ag + (bg - ag) * t).toInt().coerceIn(0, 255)) shl 8) or
            ((ab + (bb - ab) * t).toInt().coerceIn(0, 255))
    }

    /**
     * #Edit By Claude: real shader-based outer glow halo around a rounded
     * rect, via [GlowRectShader] (SDF + ADDITIVE bloom, Complementary-ish
     * brightness curve). Falls back to the cheap layered-outline [glow] if the
     * shader path throws (e.g. pipeline not yet compiled). [color] is ARGB; its
     * alpha controls intensity. [spread] is the halo reach in pixels.
     */
    fun shaderGlow(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int,
        spread: Float = 6f,
    ) {
        if (width <= 0f || height <= 0f || spread <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        runCatching {
            context.drawGlowRect(x, y, width, height, radius, color, spread)
        }.onFailure {
            glow(matrices, x, y, width, height, radius, color, spread)
        }
    }

    @Suppress("LongParameterList")
    fun shaderGradientGlow(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        topLeft: Int,
        topRight: Int,
        bottomRight: Int,
        bottomLeft: Int,
        spread: Float = 1f,
    ) {
        if (width <= 0f || height <= 0f || spread <= 0f) {
            return
        }
        val context = drawContextForMatrices(matrices) ?: return
        runCatching {
            context.drawGradientGlowRect(x, y, width, height, radius, topLeft, topRight, bottomRight, bottomLeft, spread)
        }.onFailure {
            val fallback = mix(mix(topLeft, topRight, 0.5f), mix(bottomLeft, bottomRight, 0.5f), 0.5f)
            glow(matrices, x, y, width, height, radius, fallback, spread)
        }
    }

    /**
     * Cheap, framebuffer-free glow halo for active GUI rows. Drawn as
     * a single oversized rounded-rect with a soft alpha falloff —
     * cheaper than gaussian blur and visually identical at small sizes.
     */
    fun glow(
        matrices: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Float,
        color: Int,
        spread: Float = 4f,
    ) {
        if (width <= 0f || height <= 0f || spread <= 0f) {
            return
        }

        val context = drawContextForMatrices(matrices) ?: return
        val baseAlpha = (color ushr 24) and 0xFF
        if (baseAlpha == 0) {
            return
        }
        val rgb = color and 0x00FFFFFF
        val layers = 3
        for (i in 1..layers) {
            val t = i.toFloat() / layers
            val pad = spread * t
            val a = (baseAlpha * (1f - t) * 0.55f).toInt().coerceIn(0, 255)
            context.drawRoundedRect(
                x = x - pad,
                y = y - pad,
                width = width + pad * 2f,
                height = height + pad * 2f,
                radiusPx = radius + pad,
                color = (a shl 24) or rgb,
            )
        }
    }

    @Suppress("UnusedPrivateMember")
    private fun clamp(value: Float, min: Float, max: Float): Float = min(max(value, min), max)

    // #Edit By Codex 2026-06-22: removed animated theme/chroma fill and border helpers.
}
