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
@file:JvmName("CustomFontGui")

package net.ccbluex.liquidbounce.render.gui

import net.ccbluex.liquidbounce.render.FontManager
import net.ccbluex.liquidbounce.render.engine.font.FontRenderer
import net.ccbluex.liquidbounce.render.engine.font.GlyphPage
import net.ccbluex.liquidbounce.render.engine.font.RenderedGlyph
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.navigation.ScreenRectangle
import net.minecraft.client.gui.render.TextureSetup
import org.joml.Matrix3x2f
import org.joml.Vector3f
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

/**
 * Submits a string drawn with a [FontRenderer] (custom .ttf glyph atlas)
 * through vanilla 1.26.2's batched GUI render path so it composites
 * correctly with everything else painted via
 * [GuiGraphicsExtractor.guiRenderState]. Each glyph quad is emitted as
 * its own [GlyphGuiElementRenderState] grouped by glyph page (texture),
 * which keeps vanilla's depth/scissor/sort behaviour intact.
 *
 * Returns the X position immediately after the rendered text (in pixel
 * space, before scaling). Useful for chaining draws.
 */
fun GuiGraphicsExtractor.drawCustomFontText(
    renderer: FontRenderer,
    text: String,
    x: Float,
    y: Float,
    color: Int,
    size: Float,
    shadow: Boolean,
): Float {
    if (text.isEmpty()) {
        return x
    }
    val processed = renderer.process(text, Color4b(color, hasAlpha = true))
    val scale = size / FontManager.DEFAULT_FONT_SIZE
    val pose = Matrix3x2f(this.pose())
    val scissor = this.scissorStack.peek()

    val advance: Float
    if (shadow) {
        renderer.captureGlyphs(processed, x + 2f * scale, y + 2f * scale, scale, Color4b(0, 0, 0, 150))
        flushGlyphs(renderer, pose, scissor)
        advance = renderer.captureGlyphs(processed, x, y, scale, null)
    } else {
        advance = renderer.captureGlyphs(processed, x, y, scale, null)
    }
    flushGlyphs(renderer, pose, scissor)
    return advance
}

/**
 * Walks the cached [RenderedGlyph]s from a [FontRenderer], groups them
 * by glyph page (= GPU texture) and submits one batched GUI element per
 * page. The previous path created one [GlyphGuiElementRenderState] for
 * every glyph, which made dense GUI text allocate/sort hundreds of GUI
 * elements per frame once small labels started using custom fonts.
 */
private fun GuiGraphicsExtractor.flushGlyphs(
    renderer: FontRenderer,
    pose: Matrix3x2f,
    scissor: ScreenRectangle?,
) {
    val glyphs = renderer.drainGlyphs()
    if (glyphs.isEmpty()) {
        return
    }
    val pipeline = GlyphGuiElementRenderState.defaultPipeline()
    val glyphsByPage = LinkedHashMap<GlyphPage, MutableList<RenderedGlyph>>()
    for (glyph in glyphs) {
        glyphsByPage.getOrPut(glyph.glyph.page) { ArrayList() } += glyph
    }

    for ((page, pageGlyphs) in glyphsByPage) {
        val texture = page.texture
        val view = texture.textureView ?: continue
        val sampler = texture.sampler ?: continue
        val quads = FloatArray(pageGlyphs.size * 8)
        val colors = IntArray(pageGlyphs.size)
        var count = 0
        var bounds: ScreenRectangle? = null

        for (glyph in pageGlyphs) {
            val atlas = glyph.glyph.renderInfo.atlasLocation ?: continue
            val uvMin = atlas.uvCoordinatesOnTexture.min
            val uvMax = atlas.uvCoordinatesOnTexture.max
            val q = count * 8
            quads[q] = glyph.x1
            quads[q + 1] = glyph.y1
            quads[q + 2] = glyph.x2
            quads[q + 3] = glyph.y2
            quads[q + 4] = uvMin.u
            quads[q + 5] = uvMin.v
            quads[q + 6] = uvMax.u
            quads[q + 7] = uvMax.v
            colors[count] = glyph.color.toARGB()
            bounds = unionBounds(bounds, glyphBounds(glyph, pose, scissor))
            count++
        }

        if (count == 0) {
            continue
        }

        // #Edit By Codex 2026-06-19: batch all glyph quads that share a glyph page into one GUI
        // render state. This keeps the Celestial fonts but removes per-character GUI state churn.
        this.guiRenderState.addGuiElement(
            GlyphBatchGuiElementRenderState(
                quads,
                colors,
                count,
                pipeline,
                TextureSetup.singleTexture(view, sampler),
                pose,
                scissor,
                bounds,
            )
        )
    }
}

private fun glyphBounds(
    glyph: RenderedGlyph,
    pose: Matrix3x2f,
    scissor: ScreenRectangle?,
): ScreenRectangle? {
    val v1x = pose.m00() * glyph.x1 + pose.m10() * glyph.y1 + pose.m20()
    val v1y = pose.m01() * glyph.x1 + pose.m11() * glyph.y1 + pose.m21()
    val v2x = pose.m00() * glyph.x2 + pose.m10() * glyph.y1 + pose.m20()
    val v2y = pose.m01() * glyph.x2 + pose.m11() * glyph.y1 + pose.m21()
    val v3x = pose.m00() * glyph.x1 + pose.m10() * glyph.y2 + pose.m20()
    val v3y = pose.m01() * glyph.x1 + pose.m11() * glyph.y2 + pose.m21()
    val v4x = pose.m00() * glyph.x2 + pose.m10() * glyph.y2 + pose.m20()
    val v4y = pose.m01() * glyph.x2 + pose.m11() * glyph.y2 + pose.m21()
    val minX = floor(minOf(v1x, v2x, v3x, v4x)).toInt()
    val minY = floor(minOf(v1y, v2y, v3y, v4y)).toInt()
    val maxX = ceil(maxOf(v1x, v2x, v3x, v4x)).toInt()
    val maxY = ceil(maxOf(v1y, v2y, v3y, v4y)).toInt()
    val rect = ScreenRectangle(minX, minY, max(1, maxX - minX), max(1, maxY - minY))
    return runCatching { scissor?.intersection(rect) ?: rect }.getOrDefault(rect)
}

private fun unionBounds(current: ScreenRectangle?, next: ScreenRectangle?): ScreenRectangle? {
    if (current == null) {
        return next
    }
    if (next == null) {
        return current
    }

    val left = min(current.left(), next.left())
    val top = min(current.top(), next.top())
    val right = max(current.right(), next.right())
    val bottom = max(current.bottom(), next.bottom())
    return ScreenRectangle(left, top, max(1, right - left), max(1, bottom - top))
}

/**
 * Test-friendly wrapper around [FontRenderer.drawInternal] (which is
 * private). Forwards to the public [FontRenderer.draw] entry which
 * appends [RenderedGlyph]s to the renderer's internal cache without
 * actually flushing them.
 */
private fun FontRenderer.captureGlyphs(
    processed: net.ccbluex.liquidbounce.render.engine.font.processor.TextProcessor.ProcessedText,
    x: Float,
    y: Float,
    scale: Float,
    overrideColor: Color4b?,
): Float {
    return if (overrideColor != null) {
        // FontRenderer.drawInternal is private; the only public entry is
        // draw(...) which always doubles when shadow=true. We just call
        // draw(shadow=false) and rely on the caller to handle shadow
        // separately. Override colors are achieved by calling once per
        // pass with a pre-coloured ProcessedText already produced by
        // process(text, defaultColor=overrideColor).
        val recoloured = recolour(processed, overrideColor)
        draw(recoloured, x, y, shadow = false, scale = scale)
    } else {
        draw(processed, x, y, shadow = false, scale = scale)
    }
}

private fun FontRenderer.drainGlyphs(): List<RenderedGlyph> = drainCachedGlyphs()

// #Edit By Claude 2026-06-18: PERF — these reflection Fields used to be resolved (getDeclaredField +
// isAccessible + class scan) on EVERY text draw. Once the new Montserrat/Pixel fonts made small UI labels
// route through this path (the old `text()` gated <22px to the cheap vanilla bitmap), that
// per-glyph-flush reflection ran hundreds of times per frame and tanked FPS (~500 → ~15-20). Resolve
// the Fields ONCE here, lazily, and reuse them.
private val CACHE_FIELD: java.lang.reflect.Field by lazy {
    FontRenderer::class.java.getDeclaredField("cache").apply { isAccessible = true }
}
private val RENDERED_GLYPHS_FIELD: java.lang.reflect.Field by lazy {
    val cacheClass = CACHE_FIELD.type
    cacheClass.getDeclaredField("renderedGlyphs").apply { isAccessible = true }
}
private val LINES_FIELD: java.lang.reflect.Field by lazy {
    val cacheClass = CACHE_FIELD.type
    cacheClass.getDeclaredField("lines").apply { isAccessible = true }
}

/**
 * Returns the list of glyphs currently in the renderer's cache and
 * clears it. The renderer's [FontRenderer.commit] is the canonical way
 * to drain the cache, but it requires a [net.ccbluex.liquidbounce.render.RenderEnvironment]
 * which isn't appropriate for the GUI flush path. Calling this instead
 * lets us route the same cached glyphs through vanilla's GUI batcher.
 */
private fun FontRenderer.drainCachedGlyphs(): List<RenderedGlyph> {
    val cache = CACHE_FIELD.get(this)
    @Suppress("UNCHECKED_CAST")
    val list = RENDERED_GLYPHS_FIELD.get(cache) as ArrayList<RenderedGlyph>
    val copy = ArrayList(list)
    list.clear()
    // Drop any accumulated underline/strikethrough lines as well so they
    // do not leak into a later batch via FontRenderer.commit.
    (LINES_FIELD.get(cache) as MutableList<*>).clear()
    return copy
}

private fun recolour(
    processed: net.ccbluex.liquidbounce.render.engine.font.processor.TextProcessor.ProcessedText,
    color: Color4b,
): net.ccbluex.liquidbounce.render.engine.font.processor.TextProcessor.ProcessedText {
    // ProcessedText is a data class; rebuild with each char re-coloured.
    val newChars = processed.chars.map { ch ->
        ch.copy(color = color)
    }
    return processed.copy(chars = newChars)
}
