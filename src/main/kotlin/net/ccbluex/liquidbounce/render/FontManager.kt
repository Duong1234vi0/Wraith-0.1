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
package net.ccbluex.liquidbounce.render
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.engine.font.FontGlyphPageManager
import net.ccbluex.liquidbounce.render.engine.font.FontRenderer
import net.ccbluex.liquidbounce.utils.client.logger
import net.minecraft.util.Util
import net.minecraft.util.Util.OS.*
import java.awt.Font
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import java.io.File

object FontManager {

    private val FONT_RENDER_CONTEXT = FontRenderContext(AffineTransform(), true, true)

    /**
     * As fallback, we can use a common font that is available on all systems.
     */
    private val COMMON_FONT = runCatching {
        when (Util.getPlatform()) {
            WINDOWS -> systemFont("Segoe UI")
            OSX -> systemFont("Helvetica")
            LINUX -> systemFont("DejaVu Sans")
            else -> systemFont("Arial")
        }
    }.onFailure { throwable ->
        logger.error("Failed to load common font.", throwable)
    }.getOrNull() ?: systemFont("Arial")

    /**
     * Default font for displaying CJK (Chinese, Japanese, Korean) characters.
     */
    private val CJK_FONT = runCatching {
        when (Util.getPlatform()) {
            WINDOWS -> systemFont("Microsoft YaHei")
            OSX -> systemFont("PingFang SC")
            LINUX -> systemFont("Noto Sans CJK")
            else -> null // No default CJK font available
        }
    }.onFailure { throwable ->
        logger.error("Failed to load CJK font.", throwable)
    }.getOrNull()

    /**
     * All font faces that are known to the font manager.
     */
    internal val fontFaces = mutableSetOf(
        COMMON_FONT
    )

    /**
     * #Edit By Claude 2026-06-17: Celestial fonts bundled under
     * `resources/liquidbounce/font/`. Montserrat is used for TITLES (module
     * names, panel headers, the SYLENT logo); Pixel is used for BODY text
     * (module descriptions / notes). They are loaded into [fontFaces] here,
     * i.e. BEFORE [createGlyphManager] runs (LiquidBounce.prepareGuiStage),
     * so each font gets its own glyph page + [FontFace.renderer].
     *
     * NOTE: upstream never wired `queueFile`/`queueFolder` to any caller (see
     * the PR #3884 TODO on [FONT_RENDERER]), so older UI text fell back to
     * [COMMON_FONT] (a system font). These two faces are the first real
     * bundled fonts on the fork.
     */
    const val TITLE_FONT_NAME = "Montserrat"
    const val BODY_FONT_NAME = "Pixel"

    init {
        registerResourceFont(TITLE_FONT_NAME, "font/montserrat.ttf")
        registerResourceFont(BODY_FONT_NAME, "font/pixel.ttf")
    }

    /** Montserrat renderer (titles). Falls back to Inter, then the common font. */
    val TITLE_FONT_RENDERER
        get() = (fontFace(TITLE_FONT_NAME) ?: fontFace("Inter Regular") ?: COMMON_FONT).renderer

    /** Pixel renderer (body / descriptions). Falls back to Inter, then the common font. */
    val BODY_FONT_RENDERER
        get() = (fontFace(BODY_FONT_NAME) ?: fontFace("Inter Regular") ?: COMMON_FONT).renderer

    /**
     * Loads a bundled `.ttf` from `/resources/liquidbounce/<resourcePath>` and
     * registers it as a [FontFace] under [name]. No-op if a face with that
     * name is already present; logs and skips on any failure (so a missing /
     * corrupt font can never crash client startup).
     */
    private fun registerResourceFont(name: String, resourcePath: String) {
        if (fontFaces.any { face -> face.name == name }) {
            return
        }
        runCatching {
            val stream = FontManager::class.java.getResourceAsStream("/resources/liquidbounce/$resourcePath")
                ?: error("Font resource /resources/liquidbounce/$resourcePath not found")
            val font = stream.use { Font.createFont(Font.TRUETYPE_FONT, it) }
                .deriveFont(DEFAULT_FONT_SIZE)
            val fontFace = FontFace(name, DEFAULT_FONT_SIZE)
            fontFace.fillStyle(font, 0)
            fontFaces += fontFace
        }.onFailure { throwable ->
            logger.error("Failed to load bundled font $resourcePath", throwable)
        }
    }

    /**
     * The active font renderer that all text rendering will be based on.
     *
     * TODO: Because PR #3884 is not merged yet, we have to define the active font renderer manually.
     *    This will be removed once the PR is merged, because after the PR is merged, the font renderer
     *    can be selected through the module settings instead.
     */
    val FONT_RENDERER
        get() = (fontFace("Inter Regular") ?: COMMON_FONT).renderer

    /**
     * Since our font renderer does not support dynamic font size changes,
     * we will use 43 as the default font size.
     */
    const val DEFAULT_FONT_SIZE: Float = 43f

    /**
     * The glyph manager that is responsible for managing the glyph pages.
     */
    var glyphManager: FontGlyphPageManager? = null
        private set
        get() = field ?: error("Glyph manager was not initialized yet!")

    /**
     * Non-throwing variant of [glyphManager]. Used by call sites that
     * want to fall back gracefully when glyph atlas isn't ready yet
     * (e.g. very early frames before [createGlyphManager] has run).
     */
    fun glyphManagerOrNull(): FontGlyphPageManager? = runCatching { glyphManager }.getOrNull()

    /**
     * Returns the font by the given name.
     */
    internal fun fontFace(name: String) = fontFaces.associateBy { fontFace -> fontFace.name }[name]

    internal fun createGlyphManager() {
        glyphManager = FontGlyphPageManager(
            baseFonts = fontFaces,
            additionalFonts = setOf(CJK_FONT).filterNotNull().toSet()
        )
    }

    internal fun queueFolder(path: File) {
        try {
            path.listFiles { file -> file.extension == "ttf" }
                ?.forEach(::queueFile)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to load font from folder $path", e)
        }
    }

    internal fun queueFile(file: File) {
        try {
            if (!file.exists()) {
                logger.warn("Font file ${file.absolutePath} does not exist.")
                return
            }

            if (file.extension != "ttf") {
                logger.warn("Font file ${file.absolutePath} is not a TrueType font.")
                return
            }

            if (fontFaces.any { it.file == file }) {
                logger.warn("Font file ${file.absolutePath} is already loaded.")
                return
            }

            val font = Font
                .createFont(Font.TRUETYPE_FONT, file)
                .deriveFont(DEFAULT_FONT_SIZE)

            // Name will consist of the font name and family. This makes it possible
            // to select the different styles of the font.
            val fontFace = FontFace(font.name, DEFAULT_FONT_SIZE, file)
            // In this case, we have only one style available, which is the plain style.
            fontFace.fillStyle(font, 0)
            fontFaces += fontFace
        } catch (e: Exception) {
            logger.warn("Failed to load font from file ${file.absolutePath}", e)
        }
    }

    private fun systemFont(name: String): FontFace {
        val fontFace = FontFace(name, DEFAULT_FONT_SIZE)

        arrayOf(
            Font.PLAIN,
            Font.BOLD,
            Font.ITALIC,
            Font.BOLD or Font.ITALIC
        ).map { style ->
            Font(name, style, DEFAULT_FONT_SIZE.toInt())
                .deriveFont(DEFAULT_FONT_SIZE)
        }.forEachIndexed { index, font ->
            fontFace.fillStyle(font, index)
        }

        return fontFace
    }

    data class FontFace(
        val name: String,
        val size: Float,
        /**
         * The file of the font. If the font is a system font, this will be null.
         */
        val file: File? = null,
        @Suppress("ArrayInDataClass")
        /**
         * Style of the font. If an element is null, fall back to `[0]`
         *
         * [Font.PLAIN] -> 0 (Must not be null)
         *
         * [Font.BOLD] -> 1 (Can be null)
         *
         * [Font.ITALIC] -> 2 (Can be null)
         *
         * [Font.BOLD] | [Font.ITALIC] -> 3 (Can be null)
         */
        val styles: Array<FontId?> = arrayOfNulls(4)
    ) {

        val renderer: FontRenderer by lazy {
            FontRenderer(this, glyphManager!!)
        }

        /**
         * Fills the font style at the given index.
         */
        fun fillStyle(font: Font, index: Int) {
            val metrics = font.getLineMetrics("Hg", FONT_RENDER_CONTEXT)

            styles[index] = FontId(index, font, metrics.height, metrics.ascent)
        }

    }

    class FontId(
        val style: Int,
        val awtFont: Font,
        val height: Float,
        val ascent: Float
    )

}
