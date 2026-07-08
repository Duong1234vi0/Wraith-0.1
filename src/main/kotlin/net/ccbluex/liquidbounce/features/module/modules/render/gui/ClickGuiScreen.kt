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

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.config.AutoConfig
import net.ccbluex.liquidbounce.config.ConfigSystem
import net.ccbluex.liquidbounce.config.types.BindValue
import net.ccbluex.liquidbounce.config.types.ChooseListValue
import net.ccbluex.liquidbounce.config.types.MultiChooseListValue
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.RangedValue
import net.ccbluex.liquidbounce.config.types.Value
import net.ccbluex.liquidbounce.config.types.ValueType
import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.gui.drawSkinFace
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.markAsError
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.ccbluex.liquidbounce.utils.client.regular
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.CharacterEvent
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.resources.DefaultPlayerSkin
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * #Edit By Codex 2026-06-22: Fresh native Style 4 ClickGUI replacement.
 * This intentionally does not restore the deleted dashboard implementation.
 */
@Suppress("TooManyFunctions", "LargeClass")
class ClickGuiScreen : Screen(Component.literal("ClickGUI")) {

    private var selectedPage = ClickGuiPage.COMBAT
    private var selectedModule: ClientModule? = null
    private var searchText = ""
    private var searchFocused = false
    private var moduleScroll = 0f
    private var configScroll = 0f
    private var themeScroll = 0f
    private var settingsScroll = 0f
    private var draggingSlider: SettingEntry? = null
    private var draggingRangeSide: RangeDragSide? = null
    private var bindListening: BindValue? = null
    private var settingsPanelAnimation: SettingsPanelAnimation? = null
    private var searchAnimationStart = 0f
    private var searchAnimationTarget = 0f
    private var searchAnimationStartedAt = 0L
    private var searchIconClickedAt = 0L
    private var openedAt = System.currentTimeMillis()
    private var closing = false
    private var closingStartedAt = 0L

    private val categoryRects = mutableListOf<Pair<ClickGuiPage, Rect>>()
    private val moduleRects = mutableListOf<Pair<ClientModule, Rect>>()
    private val configActionRects = mutableListOf<Pair<ConfigAction, Rect>>()
    private val themeRects = mutableListOf<Pair<Int, Rect>>()
    private val settingRects = mutableListOf<Pair<SettingEntry, Rect>>()
    private val toggleAnimations = mutableMapOf<Int, ToggleAnimation>()
    private var searchRect = Rect.ZERO
    private var searchIconRect = Rect.ZERO
    private var moduleArea = Rect.ZERO
    private var settingsArea = Rect.ZERO
    private var closeSettingsRect = Rect.ZERO

    override fun init() {
        super.init()
        closeSettingsPanel()
        openedAt = System.currentTimeMillis()
        searchAnimationStart = if (searchText.isBlank()) 0f else 1f
        searchAnimationTarget = searchAnimationStart
        searchAnimationStartedAt = openedAt
        searchIconClickedAt = 0L
        toggleAnimations.clear()
        closing = false
        closingStartedAt = 0L
    }

    override fun extractBackground(graphics: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        // #Edit By Codex 2026-06-26: Skip vanilla's opaque in-game screen shade.
        // The Style 4 surface draws its own lighter glass layer below.
    }

    override fun extractRenderState(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        validateSelectedModule()
        val layout = layout()
        val matrices = context.matrices
        if (closing && closeProgress() >= 1f) {
            super.onClose()
            return
        }

        context.fill(0, 0, width, height, SCREEN_DIM)
        applyMainBackgroundBlur(context)

        val animationScale = mainAnimationScale()
        val animated = pushMainAnimation(context, layout, animationScale)
        try {

            ExosGuiRenderer.roundedGradientRect(
                matrices,
                layout.x,
                layout.y,
                layout.w,
                layout.h,
                7f,
                SURFACE_0,
                SURFACE_1,
                SURFACE_0,
                SURFACE_1
            )
            ExosGuiRenderer.roundedOutline(matrices, layout.x, layout.y, layout.w, layout.h, 7f, BORDER_SOFT, 1f)
            context.nextStratum()

            renderSidebar(context, layout, mouseX, mouseY)
            renderModulePanel(context, layout, mouseX, mouseY)
            renderModuleSettingsPanel(context, layout, mouseX, mouseY)

            super.extractRenderState(context, mouseX, mouseY, delta)
        } finally {
            if (animated) {
                context.pose().popMatrix()
            }
        }
    }

    private fun renderSidebar(context: DrawContext, layout: Layout, mouseX: Int, mouseY: Int) {
        categoryRects.clear()
        val s = layout.scale

        ExosGuiRenderer.roundedGradientRectVerticalRadii(
            context.matrices,
            layout.x + 1f,
            layout.y + 1f,
            layout.sidebarW - 2f,
            layout.h - 2f,
            7f,
            7f,
            SIDEBAR_TOP,
            SIDEBAR_TOP,
            SIDEBAR_BOTTOM,
            SIDEBAR_BOTTOM
        )
        context.fill(
            floor(layout.x + layout.sidebarW).toInt(),
            floor(layout.y + 1f).toInt(),
            floor(layout.x + layout.sidebarW + 1f).toInt(),
            ceil(layout.y + layout.h - 1f).toInt(),
            DIVIDER
        )

        renderLogo(context, layout.x + 18f * s, layout.y + 20f * s, s)

        val rowH = 30f * s
        val rowGap = 8f * s
        var y = layout.y + 78f * s
        STANDARD_PAGES.forEach { page ->
            renderSidebarPage(context, page, Rect(layout.x + 12f * s, y, layout.sidebarW - 22f * s, rowH),
                mouseX, mouseY, s)
            y += rowH + rowGap
        }

        val dividerY = y + 2f * s
        context.fill(
            (layout.x + 22f * s).toInt(),
            dividerY.toInt(),
            (layout.x + layout.sidebarW - 22f * s).toInt(),
            (dividerY + 1f).toInt(),
            DIVIDER_STRONG
        )
        y += 14f * s

        SPECIAL_PAGES.forEach { page ->
            renderSidebarPage(context, page, Rect(layout.x + 12f * s, y, layout.sidebarW - 22f * s, rowH),
                mouseX, mouseY, s)
            y += rowH + rowGap
        }

        renderProfile(context, layout)
    }

    private fun renderSidebarPage(
        context: DrawContext,
        page: ClickGuiPage,
        row: Rect,
        mouseX: Int,
        mouseY: Int,
        scale: Float
    ) {
        categoryRects += page to row
        val hovered = row.contains(mouseX, mouseY)
        val selected = page == selectedPage

        if (selected || hovered) {
            val fill = if (selected) ACCENT_FILL else HOVER_FILL
            ExosGuiRenderer.roundedRect(context.matrices, row.x, row.y, row.w, row.h, 6f, fill)
            if (selected) {
                ExosGuiRenderer.roundedOutline(context.matrices, row.x, row.y, row.w, row.h, 6f, ACCENT_BORDER, 0.9f)
            }
        }

        renderPageIcon(
            context,
            page,
            row.x + 13f * scale,
            row.y + 6.3f * scale,
            17.2f * scale,
            if (selected) TEXT_PRIMARY else TEXT_SECONDARY
        )
        ExosGuiRenderer.title(
            context.matrices,
            page.title,
            row.x + CATEGORY_ICON_SLOT * scale,
            row.y + 8.2f * scale,
            if (selected) TEXT_PRIMARY else TEXT_SECONDARY,
            10.2f * scale
        )
    }

    private fun renderLogo(context: DrawContext, x: Float, y: Float, scale: Float) {
        val markX = x + 9f * scale
        val markY = y + 10f * scale
        val c = 0xAACFD5E3.toInt()
        context.fill((markX - 9f * scale).toInt(), markY.toInt(), (markX + 9f * scale).toInt(),
            (markY + 1f).toInt(), c)
        context.fill(markX.toInt(), (markY - 9f * scale).toInt(), (markX + 1f).toInt(),
            (markY + 9f * scale).toInt(), c)
        context.fill((markX - 3f * scale).toInt(), (markY - 3f * scale).toInt(),
            (markX + 4f * scale).toInt(), (markY + 4f * scale).toInt(), 0x33FFFFFF)
        ExosGuiRenderer.title(context.matrices, "ASTRA", x + 30f * scale, y + 1f * scale,
            TEXT_PRIMARY, 15.5f * scale)
        ExosGuiRenderer.body(context.matrices, "PREMIUM", x + 33f * scale, y + 19f * scale,
            ACCENT_TEXT, 7.1f * scale)
    }

    private fun renderProfile(context: DrawContext, layout: Layout) {
        val s = layout.scale
        val box = Rect(layout.x + 12f * s, layout.y + layout.h - 64f * s, layout.sidebarW - 22f * s, 45f * s)
        ExosGuiRenderer.roundedRect(context.matrices, box.x, box.y, box.w, box.h, 6f, PROFILE_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, box.x, box.y, box.w, box.h, 6f, BORDER_SOFT, 0.8f)

        val avatar = Rect(box.x + 8f * s, box.y + 9f * s, 27f * s, 27f * s)
        val drewFace = localSkinId()?.let { context.drawSkinFace(it, avatar.x, avatar.y, avatar.w) } == true
        if (!drewFace) {
            ExosGuiRenderer.roundedRect(context.matrices, avatar.x, avatar.y, avatar.w, avatar.h, 4f, ACCENT_FILL)
            ExosGuiRenderer.title(context.matrices, "A", avatar.x + 9f * s, avatar.y + 7f * s,
                TEXT_PRIMARY, 10f * s)
        }
        ExosGuiRenderer.roundedOutline(context.matrices, avatar.x, avatar.y, avatar.w, avatar.h, 4f, BORDER_SOFT, 0.8f)

        val user = mc.user.name.ifBlank { "Player" }
        ExosGuiRenderer.title(context.matrices, truncate(user, box.w - 51f * s, 9.8f * s,
            ExosGuiRenderer::titleWidth), box.x + 43f * s, box.y + 10f * s, TEXT_PRIMARY, 9.8f * s)
        ExosGuiRenderer.body(context.matrices, "Premium", box.x + 43f * s, box.y + 27f * s,
            ACCENT_TEXT, 7.2f * s)
    }

    private fun renderModulePanel(context: DrawContext, layout: Layout, mouseX: Int, mouseY: Int) {
        moduleRects.clear()
        configActionRects.clear()
        themeRects.clear()
        selectedPage.category ?: return when (selectedPage) {
            ClickGuiPage.CONFIG -> renderConfigPanel(context, layout, mouseX, mouseY)
            ClickGuiPage.THEME -> renderThemePanel(context, layout, mouseX, mouseY)
            ClickGuiPage.CLOUD -> renderCloudPanel(context, layout)
            else -> Unit
        }

        val s = layout.scale
        val pad = layout.pad
        val x = layout.x + layout.sidebarW
        val y = layout.y
        val w = layout.moduleW
        val h = layout.h

        val titleSize = 15.5f * s
        ExosGuiRenderer.title(context.matrices, selectedPage.title, x + pad, y + 22f * s,
            TEXT_PRIMARY, titleSize)
        renderSearchControl(context, x, y, w, pad, s, selectedPage.title, titleSize, mouseX, mouseY)
        context.fill((x + 18f * s).toInt(), (y + 55f * s).toInt(), (x + w - 18f * s).toInt(),
            (y + 56f * s).toInt(), DIVIDER)

        moduleArea = Rect(x + pad, y + 67f * s, w - pad * 2f, h - 96f * s)
        val modules = filteredModules()
        val columns = if (moduleArea.w >= 410f * s) 2 else 1
        val gap = 8f * s
        val cardH = layout.cardH
        val cardW = (moduleArea.w - gap * (columns - 1)) / columns
        val rows = ceil(modules.size / columns.toFloat()).toInt()
        val contentH = rows * cardH + max(0, rows - 1) * gap
        val maxScroll = max(0f, contentH - moduleArea.h)
        moduleScroll = moduleScroll.coerceIn(0f, maxScroll)

        ExosGuiRenderer.enableScissor(context.matrices, moduleArea.x, moduleArea.y, moduleArea.right, moduleArea.bottom)
        modules.forEachIndexed { index, module ->
            val col = index % columns
            val row = index / columns
            val card = Rect(
                moduleArea.x + col * (cardW + gap),
                moduleArea.y + row * (cardH + gap) - moduleScroll,
                cardW,
                cardH
            )
            if (card.bottom >= moduleArea.y && card.y <= moduleArea.bottom) {
                if (module != selectedModule) {
                    val animatedCard = animatedModuleCard(card, index)
                    renderModuleCard(context, module, animatedCard.rect, mouseX, mouseY, s, animatedCard.alpha)
                    moduleRects += module to animatedCard.rect
                }
            }
        }
        ExosGuiRenderer.disableScissor(context.matrices)

        val count = "${modules.size} modules"
        ExosGuiRenderer.body(
            context.matrices,
            count,
            x + (w - ExosGuiRenderer.bodyWidth(count, 8f)) * 0.5f,
            y + h - 24f * s,
            TEXT_SECONDARY,
            7.3f * s
        )
    }

    private fun renderConfigPanel(context: DrawContext, layout: Layout, mouseX: Int, mouseY: Int) {
        searchIconRect = Rect.ZERO
        val s = layout.scale
        val pad = layout.pad
        val x = layout.x + layout.sidebarW
        val y = layout.y
        val w = layout.moduleW
        val h = layout.h

        ExosGuiRenderer.title(context.matrices, ClickGuiPage.CONFIG.title, x + pad, y + 22f * s,
            TEXT_PRIMARY, 15.5f * s)
        context.fill((x + 18f * s).toInt(), (y + 55f * s).toInt(), (x + w - 18f * s).toInt(),
            (y + 56f * s).toInt(), DIVIDER)

        val saveButton = Rect(x + w - pad - 82f * s, y + 67f * s, 82f * s, 27f * s)
        searchRect = Rect(x + pad, y + 67f * s, (saveButton.x - x - pad - 9f * s).coerceAtLeast(150f * s), 27f * s)
        ExosGuiRenderer.roundedRect(context.matrices, searchRect.x, searchRect.y, searchRect.w, searchRect.h, 6f, INPUT_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, searchRect.x, searchRect.y, searchRect.w, searchRect.h, 6f,
            if (searchFocused) ACCENT_BORDER else BORDER_SOFT, 0.8f)
        val searchDisplay = searchText.ifBlank { "Search configs..." }
        ExosGuiRenderer.body(context.matrices, searchDisplay, searchRect.x + 12f * s, searchRect.y + 7f * s,
            if (searchText.isBlank()) TEXT_MUTED else TEXT_PRIMARY, 7.7f * s)

        renderConfigButton(context, saveButton, "Save", ConfigAction(ConfigActionType.SAVE, null), mouseX, mouseY)

        moduleArea = Rect(x + pad, y + 108f * s, w - pad * 2f, h - 137f * s)
        val configs = filteredConfigs()
        val columns = if (moduleArea.w >= 410f * s) 2 else 1
        val gap = 8f * s
        val cardH = 58f * s
        val cardW = (moduleArea.w - gap * (columns - 1)) / columns
        val rows = ceil(configs.size / columns.toFloat()).toInt()
        val contentH = rows * cardH + max(0, rows - 1) * gap
        val maxScroll = max(0f, contentH - moduleArea.h)
        configScroll = configScroll.coerceIn(0f, maxScroll)

        ExosGuiRenderer.enableScissor(context.matrices, moduleArea.x, moduleArea.y, moduleArea.right, moduleArea.bottom)
        if (configs.isEmpty()) {
            val text = "No Local Configs"
            ExosGuiRenderer.body(context.matrices, text,
                moduleArea.x + (moduleArea.w - ExosGuiRenderer.bodyWidth(text, 8.5f)) * 0.5f,
                moduleArea.y + 34f * s, TEXT_MUTED, 8.5f)
        } else {
            configs.forEachIndexed { index, file ->
                val col = index % columns
                val row = index / columns
                val card = Rect(
                    moduleArea.x + col * (cardW + gap),
                    moduleArea.y + row * (cardH + gap) - configScroll,
                    cardW,
                    cardH
                )
                if (card.bottom >= moduleArea.y && card.y <= moduleArea.bottom) {
                    renderConfigCard(context, file, card, mouseX, mouseY, s)
                }
            }
        }
        ExosGuiRenderer.disableScissor(context.matrices)
    }

    private fun renderThemePanel(context: DrawContext, layout: Layout, mouseX: Int, mouseY: Int) {
        searchRect = Rect.ZERO
        searchIconRect = Rect.ZERO
        val s = layout.scale
        val pad = layout.pad
        val x = layout.x + layout.sidebarW
        val y = layout.y
        val w = layout.moduleW
        val h = layout.h

        ExosGuiRenderer.title(context.matrices, ClickGuiPage.THEME.title, x + pad, y + 22f * s,
            TEXT_PRIMARY, 15.5f * s)
        val selectedName = ClickGuiTheme.NAMES.getOrElse(ClickGuiTheme.selected) { "Theme ${ClickGuiTheme.selected + 1}" }
        ExosGuiRenderer.body(
            context.matrices,
            selectedName,
            x + w - pad - ExosGuiRenderer.bodyWidth(selectedName, 7.5f * s),
            y + 27f * s,
            ACCENT_TEXT,
            7.5f * s
        )
        context.fill((x + 18f * s).toInt(), (y + 55f * s).toInt(), (x + w - 18f * s).toInt(),
            (y + 56f * s).toInt(), DIVIDER)

        moduleArea = Rect(x + pad, y + 67f * s, w - pad * 2f, h - 96f * s)
        val themes = filteredThemes()
        val columns = if (moduleArea.w >= 410f * s) 2 else 1
        val gap = 8f * s
        val cardH = 52f * s
        val cardW = (moduleArea.w - gap * (columns - 1)) / columns
        val rows = ceil(themes.size / columns.toFloat()).toInt()
        val contentH = rows * cardH + max(0, rows - 1) * gap
        val maxScroll = max(0f, contentH - moduleArea.h)
        themeScroll = themeScroll.coerceIn(0f, maxScroll)

        ExosGuiRenderer.enableScissor(context.matrices, moduleArea.x, moduleArea.y, moduleArea.right, moduleArea.bottom)
        themes.forEachIndexed { index, themeIndex ->
            val col = index % columns
            val row = index / columns
            val card = Rect(
                moduleArea.x + col * (cardW + gap),
                moduleArea.y + row * (cardH + gap) - themeScroll,
                cardW,
                cardH
            )
            if (card.bottom >= moduleArea.y && card.y <= moduleArea.bottom) {
                renderThemeCard(context, themeIndex, card, mouseX, mouseY, s)
                themeRects += themeIndex to card
            }
        }
        ExosGuiRenderer.disableScissor(context.matrices)
    }

    private fun renderCloudPanel(context: DrawContext, layout: Layout) {
        searchRect = Rect.ZERO
        searchIconRect = Rect.ZERO
        moduleArea = Rect.ZERO
        val s = layout.scale
        val pad = layout.pad
        val x = layout.x + layout.sidebarW
        val y = layout.y
        val w = layout.moduleW
        val h = layout.h

        ExosGuiRenderer.title(context.matrices, ClickGuiPage.CLOUD.title, x + pad, y + 22f * s,
            TEXT_PRIMARY, 15.5f * s)
        context.fill((x + 18f * s).toInt(), (y + 55f * s).toInt(), (x + w - 18f * s).toInt(),
            (y + 56f * s).toInt(), DIVIDER)

        val text = "Nothing Found"
        ExosGuiRenderer.title(context.matrices, text,
            x + (w - ExosGuiRenderer.titleWidth(text, 14f * s)) * 0.5f,
            y + h * 0.48f, TEXT_SECONDARY, 14f * s)
    }

    private fun renderSearchControl(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        pad: Float,
        scale: Float,
        title: String,
        titleSize: Float,
        mouseX: Int,
        mouseY: Int
    ) {
        if (searchFocused || searchText.isNotBlank()) {
            setSearchExpanded(true)
        }

        val iconBox = Rect(x + width - pad - 27f * scale, y + 18f * scale, 27f * scale, 27f * scale)
        val titleRight = x + pad + ExosGuiRenderer.titleWidth(title, titleSize) + 16f * scale
        val maxSearchW = (iconBox.right - titleRight).coerceAtLeast(iconBox.w)
        val expandedW = min(222f * scale, maxSearchW)
        val progress = searchProgress()
        val searchW = lerp(iconBox.w, expandedW, progress)
        val search = Rect(iconBox.right - searchW, iconBox.y, searchW, iconBox.h)
        searchRect = if (progress > 0.04f) search else Rect.ZERO
        searchIconRect = Rect(search.right - iconBox.w, iconBox.y, iconBox.w, iconBox.h)

        if (progress > 0.02f) {
            ExosGuiRenderer.roundedRect(context.matrices, search.x, search.y, search.w, search.h, 6f,
                fadeColor(INPUT_FILL, 0.35f + 0.65f * progress))
            ExosGuiRenderer.roundedOutline(context.matrices, search.x, search.y, search.w, search.h, 6f,
                if (searchFocused) ACCENT_BORDER else BORDER_SOFT, 0.8f)

            if (searchText.isNotBlank()) {
                val textMaxW = (search.w - iconBox.w - 14f * scale).coerceAtLeast(0f)
                ExosGuiRenderer.body(
                    context.matrices,
                    truncate(searchText, textMaxW, 7.7f * scale, ExosGuiRenderer::bodyWidth),
                    search.x + 10f * scale,
                    search.y + 7f * scale,
                    TEXT_PRIMARY,
                    7.7f * scale
                )
            }
        } else {
            val hovered = iconBox.contains(mouseX, mouseY)
            ExosGuiRenderer.roundedRect(context.matrices, iconBox.x, iconBox.y, iconBox.w, iconBox.h, 6f,
                if (hovered) HOVER_FILL else INPUT_FILL)
            ExosGuiRenderer.roundedOutline(context.matrices, iconBox.x, iconBox.y, iconBox.w, iconBox.h, 6f,
                if (hovered) ACCENT_BORDER else BORDER_SOFT, 0.8f)
        }

        renderSearchIcon(context, searchIconRect, if (searchFocused) TEXT_PRIMARY else TEXT_SECONDARY)
    }

    private fun renderConfigCard(
        context: DrawContext,
        file: File,
        rect: Rect,
        mouseX: Int,
        mouseY: Int,
        scale: Float
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
            if (hovered) CARD_HOVER else CARD_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
            if (hovered) ACCENT_BORDER else BORDER_SOFT, if (hovered) 0.95f else 0.75f)

        val name = truncate(file.nameWithoutExtension, rect.w - 28f * scale, 10.2f * scale,
            ExosGuiRenderer::titleWidth)
        ExosGuiRenderer.title(context.matrices, name, rect.x + 15f * scale, rect.y + 9f * scale,
            TEXT_PRIMARY, 10.2f * scale)
        val info = "${max(1L, file.length() / 1024L)} KB"
        ExosGuiRenderer.body(context.matrices, info, rect.x + 15f * scale, rect.y + 28f * scale,
            TEXT_SECONDARY, 7.1f * scale)

        val buttonY = rect.y + rect.h - 22f * scale
        val delete = Rect(rect.right - 57f * scale, buttonY, 42f * scale, 16f * scale)
        val load = Rect(delete.x - 47f * scale, buttonY, 42f * scale, 16f * scale)
        renderConfigButton(context, load, "Load", ConfigAction(ConfigActionType.LOAD, file), mouseX, mouseY)
        renderConfigButton(context, delete, "Delete", ConfigAction(ConfigActionType.DELETE, file), mouseX, mouseY)
    }

    private fun renderThemeCard(
        context: DrawContext,
        themeIndex: Int,
        rect: Rect,
        mouseX: Int,
        mouseY: Int,
        scale: Float
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        val selected = ClickGuiTheme.selected == themeIndex
        val colors = ClickGuiTheme.COLORS[themeIndex]
        val name = ClickGuiTheme.NAMES.getOrElse(themeIndex) { "Theme ${themeIndex + 1}" }
        val description = ClickGuiTheme.DESCRIPTIONS.getOrElse(themeIndex) { "Accent color preset" }
        val fill = when {
            selected -> CARD_SELECTED
            hovered -> CARD_HOVER
            else -> CARD_FILL
        }
        val border = when {
            selected -> ExosGuiRenderer.withAlpha(colors.second, 220)
            hovered -> ExosGuiRenderer.withAlpha(colors.first, 170)
            else -> BORDER_SOFT
        }

        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f, fill)
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f, border,
            if (selected) 1.05f else 0.75f)

        val preview = Rect(rect.right - 66f * scale, rect.y + 12f * scale, 50f * scale, 9f * scale)
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            preview.x,
            preview.y,
            preview.w,
            preview.h,
            4.5f * scale,
            colors.first,
            colors.second,
            colors.second,
            colors.first
        )
        ExosGuiRenderer.roundedOutline(context.matrices, preview.x, preview.y, preview.w, preview.h,
            4.5f * scale, 0x55FFFFFF, 0.65f)

        val textMax = (preview.x - rect.x - 28f * scale).coerceAtLeast(72f * scale)
        ExosGuiRenderer.title(
            context.matrices,
            truncate(name, textMax, 9.6f * scale, ExosGuiRenderer::titleWidth),
            rect.x + 14f * scale,
            rect.y + 9f * scale,
            if (selected) TEXT_ENABLED else TEXT_PRIMARY,
            9.6f * scale
        )
        ExosGuiRenderer.body(
            context.matrices,
            truncate(description, rect.w - 30f * scale, 7f * scale, ExosGuiRenderer::bodyWidth),
            rect.x + 14f * scale,
            rect.y + 28f * scale,
            TEXT_SECONDARY,
            7f * scale
        )

        if (selected) {
            renderTickIcon(
                context,
                rect.right - 24f * scale,
                rect.y + rect.h - 20f * scale,
                (9f * scale).roundToInt().coerceAtLeast(7),
                TEXT_PRIMARY
            )
        }
    }

    private fun renderConfigButton(
        context: DrawContext,
        rect: Rect,
        label: String,
        action: ConfigAction,
        mouseX: Int,
        mouseY: Int
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        configActionRects += action to rect
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f,
            if (hovered) ACCENT_FILL else INPUT_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f,
            if (hovered) ACCENT_BORDER else BORDER_SOFT, 0.75f)
        ExosGuiRenderer.body(context.matrices, label,
            rect.x + (rect.w - ExosGuiRenderer.bodyWidth(label, 7.2f)) * 0.5f,
            rect.y + 4.1f, if (hovered) TEXT_PRIMARY else TEXT_SECONDARY, 7.2f)
    }

    private fun renderModuleCard(
        context: DrawContext,
        module: ClientModule,
        rect: Rect,
        mouseX: Int,
        mouseY: Int,
        scale: Float,
        alpha: Float
    ) {
        val selected = selectedModule == module
        val hovered = rect.contains(mouseX, mouseY)
        val visualRect = rect
        val a = alpha.coerceIn(0f, 1f)
        val bg = when {
            module.enabled -> CARD_ENABLED
            selected -> CARD_SELECTED
            hovered -> CARD_HOVER
            else -> CARD_FILL
        }
        val border = when {
            selected || module.enabled -> ACCENT_BORDER
            else -> BORDER_SOFT
        }
        ExosGuiRenderer.roundedRect(context.matrices, visualRect.x, visualRect.y, visualRect.w, visualRect.h, 6f,
            fadeColor(bg, a))
        ExosGuiRenderer.roundedOutline(
            context.matrices,
            visualRect.x,
            visualRect.y,
            visualRect.w,
            visualRect.h,
            6f,
            fadeColor(border, a),
            if (selected || module.enabled) 1f else 0.75f
        )

        val nameWidth = visualRect.w - 30f * scale
        ExosGuiRenderer.title(context.matrices, truncate(module.name, nameWidth, 10.2f * scale,
            ExosGuiRenderer::titleWidth),
            visualRect.x + 15f * scale, visualRect.y + 10f * scale,
            fadeColor(if (module.enabled) TEXT_ENABLED else TEXT_PRIMARY, a), 10.2f * scale)

        val desc = moduleDescription(module)
        ExosGuiRenderer.body(context.matrices, truncate(desc, nameWidth, 7.1f * scale,
            ExosGuiRenderer::bodyWidth), visualRect.x + 15f * scale, visualRect.y + 30f * scale,
            fadeColor(TEXT_SECONDARY, a), 7.1f * scale)
    }

    private fun renderModuleSettingsPanel(context: DrawContext, layout: Layout, mouseX: Int, mouseY: Int) {
        settingRects.clear()
        settingsArea = Rect.ZERO
        closeSettingsRect = Rect.ZERO

        val module = selectedModule ?: return
        val panel = moduleSettingsPanelRect(layout, module)
        settingsArea = panel

        val animation = settingsPanelAnimation?.takeIf { it.module == module }
        if (animation != null) {
            val progress = animationProgress(animation)
            if (progress < 1f) {
                renderSettingsPanelAnimation(context, animation, panel, progress)
                return
            }
            settingsPanelAnimation = null
        }

        renderSettingsPanelBody(context, module, panel, mouseX, mouseY)
    }

    private fun renderSettingsPanelAnimation(
        context: DrawContext,
        animation: SettingsPanelAnimation,
        target: Rect,
        progress: Float
    ) {
        val rect = animationRect(animation, target, progress)
        val fade = (0.36f + 0.64f * cubicOut(progress)).coerceIn(0f, 1f)
        val borderFade = (0.18f + 0.42f * cubicOut(progress)).coerceIn(0f, 0.6f)
        val radius = lerp(6f, 8f, cubicOut(progress))

        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            rect.x,
            rect.y,
            rect.w,
            rect.h,
            radius,
            fadeColor(SETTINGS_SURFACE_0, fade),
            fadeColor(SETTINGS_SURFACE_1, fade),
            fadeColor(SETTINGS_SURFACE_0, fade),
            fadeColor(SETTINGS_SURFACE_1, fade)
        )
        ExosGuiRenderer.roundedOutline(
            context.matrices,
            rect.x,
            rect.y,
            rect.w,
            rect.h,
            radius,
            fadeColor(BORDER_SOFT, borderFade),
            0.75f
        )

        val titleAlpha = (96 + 159 * cubicOut(progress)).toInt()
        ExosGuiRenderer.title(context.matrices, truncate(animation.module.name, rect.w - 28f, 10f,
            ExosGuiRenderer::titleWidth), rect.x + 14f, rect.y + 9f,
            ExosGuiRenderer.withAlpha(TEXT_PRIMARY, titleAlpha), 10f)

        if (progress > 0.62f && rect.h > SETTINGS_HEADER_H) {
            val hintAlpha = (72 * quintOut((progress - 0.62f) / 0.38f)).toInt()
            ExosGuiRenderer.body(
                context.matrices,
                truncate(moduleDescription(animation.module), rect.w - 28f, 7.2f, ExosGuiRenderer::bodyWidth),
                rect.x + 14f,
                rect.y + 31f,
                ExosGuiRenderer.withAlpha(TEXT_SECONDARY, hintAlpha),
                7.2f
            )
        }
    }

    private fun renderSettingsPanelBody(
        context: DrawContext,
        module: ClientModule,
        panel: Rect,
        mouseX: Int,
        mouseY: Int
    ) {
        val pad = SETTINGS_PAD
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            panel.x,
            panel.y,
            panel.w,
            panel.h,
            8f,
            SETTINGS_SURFACE_0,
            SETTINGS_SURFACE_1,
            SETTINGS_SURFACE_0,
            SETTINGS_SURFACE_1
        )

        val headerY = panel.y + 19f
        ExosGuiRenderer.title(context.matrices, truncate(module.name, panel.w - pad * 2f - 28f, 12.5f,
            ExosGuiRenderer::titleWidth), panel.x + pad, headerY, TEXT_PRIMARY, 12.5f)
        closeSettingsRect = Rect(panel.right - pad - 18f, panel.y + 15f, 18f, 18f)
        val closeHover = closeSettingsRect.contains(mouseX, mouseY)
        ExosGuiRenderer.roundedRect(
            context.matrices,
            closeSettingsRect.x,
            closeSettingsRect.y,
            closeSettingsRect.w,
            closeSettingsRect.h,
            5f,
            if (closeHover) HOVER_FILL else INPUT_FILL
        )
        renderCloseIcon(context, closeSettingsRect, if (closeHover) TEXT_PRIMARY else TEXT_SECONDARY)
        ExosGuiRenderer.body(context.matrices, truncate(moduleDescription(module), panel.w - pad * 2f, 7.4f,
            ExosGuiRenderer::bodyWidth), panel.x + pad, headerY + 25f, TEXT_SECONDARY, 7.4f)
        context.fill((panel.x + pad).toInt(), (headerY + 49f).toInt(),
            (panel.right - pad).toInt(), (headerY + 50f).toInt(), DIVIDER)

        val entries = visibleSettings(module)
        if (entries.isEmpty()) {
            val text = "No settings"
            ExosGuiRenderer.body(context.matrices, text,
                panel.x + (panel.w - ExosGuiRenderer.bodyWidth(text, 8f)) * 0.5f,
                panel.y + 82f, TEXT_MUTED, 8f)
            return
        }

        val list = Rect(panel.x + pad, headerY + 67f, panel.w - pad * 2f, panel.h - 89f)
        val contentH = entries.sumOf { settingHeight(it).toDouble() }.toFloat()
        settingsScroll = settingsScroll.coerceIn(0f, max(0f, contentH - list.h))

        ExosGuiRenderer.enableScissor(context.matrices, list.x - 1f, list.y, list.right + 1f, list.bottom)
        var y = list.y - settingsScroll
        entries.forEach { entry ->
            val h = settingHeight(entry)
            val row = Rect(list.x + entry.depth * 10f, y, list.w - entry.depth * 10f, h - 6f)
            if (row.bottom >= list.y && row.y <= list.bottom) {
                renderSetting(context, entry, row, mouseX, mouseY)
                settingRects += entry to row
            }
            y += h
        }
        ExosGuiRenderer.disableScissor(context.matrices)
    }

    private fun renderSetting(context: DrawContext, entry: SettingEntry, rect: Rect, mouseX: Int, mouseY: Int) {
        if (entry.value is ToggleableConfigurable) {
            renderToggleableGroup(context, rect, entry.value, mouseX, mouseY)
            return
        }
        if (entry.value is ChoiceConfigurable<*>) {
            renderChoiceConfigurable(context, rect, entry.value, mouseX, mouseY)
            return
        }
        if (entry.value is ChooseListValue<*>) {
            renderChooseListValue(context, rect, entry.value, mouseX, mouseY)
            return
        }
        if (entry.value is MultiChooseListValue<*>) {
            renderMultiChooseListValue(context, rect, entry.value, mouseX, mouseY)
            return
        }

        val hovered = rect.contains(mouseX, mouseY)
        if (hovered) {
            ExosGuiRenderer.roundedRect(context.matrices, rect.x - 6f, rect.y - 3f, rect.w + 12f, rect.h + 6f,
                5f, HOVER_FILL)
        }

        val name = displayName(entry.value.name)
        ExosGuiRenderer.body(context.matrices, truncate(name, rect.w * 0.45f, 8.3f, ExosGuiRenderer::bodyWidth),
            rect.x, rect.y + 7f, TEXT_PRIMARY, 8.3f)

        when (val value = entry.value) {
            is BindValue -> drawBind(context, rect, value)
            is RangedValue<*> -> drawRanged(context, rect, value)
            else -> when (value.type()) {
                ValueType.BOOLEAN -> drawToggle(context, Rect(rect.right - 34f, rect.y + 7f, 34f, 17f),
                    value.get() == true, value)
                ValueType.COLOR -> drawColor(context, rect, value)
                else -> drawPlainValue(context, rect, value)
            }
        }
    }

    private fun renderToggleableGroup(
        context: DrawContext,
        rect: Rect,
        value: ToggleableConfigurable,
        mouseX: Int,
        mouseY: Int
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        val fill = when {
            value.enabled -> GROUP_ENABLED_FILL
            hovered -> HOVER_FILL
            else -> GROUP_FILL
        }
        ExosGuiRenderer.roundedRect(context.matrices, rect.x - 4f, rect.y - 1f, rect.w + 8f, rect.h - 2f,
            6f, fill)
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x - 4f, rect.y - 1f, rect.w + 8f, rect.h - 2f,
            6f, if (value.enabled) ACCENT_BORDER else BORDER_SOFT, if (value.enabled) 0.9f else 0.65f)

        val marker = if (value.enabled) "v" else ">"
        ExosGuiRenderer.body(context.matrices, marker, rect.x + 7f, rect.y + 9f,
            if (value.enabled) ACCENT_TEXT else TEXT_MUTED, 8.2f)
        ExosGuiRenderer.title(
            context.matrices,
            truncate(displayName(value.name), rect.w - 78f, 9.4f, ExosGuiRenderer::titleWidth),
            rect.x + 22f,
            rect.y + 8f,
            if (value.enabled) TEXT_ENABLED else TEXT_PRIMARY,
            9.4f
        )
        ExosGuiRenderer.body(context.matrices, "Enable", rect.right - 82f, rect.y + 9.5f,
            TEXT_SECONDARY, 7.1f)
        drawToggle(context, Rect(rect.right - 34f, rect.y + 7f, 34f, 17f), value.enabled, value)
    }

    private fun renderChoiceConfigurable(
        context: DrawContext,
        rect: Rect,
        value: ChoiceConfigurable<*>,
        mouseX: Int,
        mouseY: Int
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        if (hovered) {
            ExosGuiRenderer.roundedRect(context.matrices, rect.x - 6f, rect.y - 3f, rect.w + 12f, rect.h + 6f,
                5f, HOVER_FILL)
        }

        ExosGuiRenderer.body(context.matrices, displayName(value.name), rect.x, rect.y + 5f,
            TEXT_PRIMARY, 8.3f)

        val frame = choiceFrameRect(rect, value)
        ExosGuiRenderer.roundedRect(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f, CHOICE_FRAME_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f, BORDER_SOFT, 0.75f)

        choiceOptionRects(frame, value).forEach { option ->
            val selected = option.choice === value.activeChoice
            val optionHovered = option.rect.contains(mouseX, mouseY)
            val fill = when {
                selected -> CHOICE_SELECTED_FILL
                optionHovered -> HOVER_FILL
                else -> INPUT_FILL
            }
            ExosGuiRenderer.roundedRect(context.matrices, option.rect.x, option.rect.y, option.rect.w, option.rect.h,
                4f, fill)
            ExosGuiRenderer.roundedOutline(context.matrices, option.rect.x, option.rect.y, option.rect.w,
                option.rect.h, 4f, if (selected) ACCENT_BORDER else BORDER_SOFT, if (selected) 0.9f else 0.65f)

            val checkSize = 8
            val textX = option.rect.x + 8f + if (selected) checkSize + 5f else 0f
            if (selected) {
                renderTickIcon(context, option.rect.x + 7f, option.rect.y + (option.rect.h - checkSize) * 0.5f,
                    checkSize, ACCENT_TEXT)
            }
            ExosGuiRenderer.body(context.matrices, truncate(option.choice.choiceName,
                option.rect.right - textX - 7f, 7.1f, ExosGuiRenderer::bodyWidth), textX,
                option.rect.y + 5.2f, if (selected) TEXT_PRIMARY else TEXT_SECONDARY, 7.1f)
        }
    }

    private fun renderChooseListValue(
        context: DrawContext,
        rect: Rect,
        value: ChooseListValue<*>,
        mouseX: Int,
        mouseY: Int
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        if (hovered) {
            ExosGuiRenderer.roundedRect(context.matrices, rect.x - 6f, rect.y - 3f, rect.w + 12f, rect.h + 6f,
                5f, HOVER_FILL)
        }

        ExosGuiRenderer.body(context.matrices, displayName(value.name), rect.x, rect.y + 5f,
            TEXT_PRIMARY, 8.3f)

        val frame = chooseFrameRect(rect, value)
        ExosGuiRenderer.roundedRect(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f, CHOICE_FRAME_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f, BORDER_SOFT, 0.75f)

        chooseOptionRects(frame, value).forEach { option ->
            val selected = option.choice === value.get()
            val optionHovered = option.rect.contains(mouseX, mouseY)
            val fill = when {
                selected -> CHOICE_SELECTED_FILL
                optionHovered -> HOVER_FILL
                else -> INPUT_FILL
            }
            ExosGuiRenderer.roundedRect(context.matrices, option.rect.x, option.rect.y, option.rect.w, option.rect.h,
                4f, fill)
            ExosGuiRenderer.roundedOutline(context.matrices, option.rect.x, option.rect.y, option.rect.w,
                option.rect.h, 4f, if (selected) ACCENT_BORDER else BORDER_SOFT, if (selected) 0.9f else 0.65f)

            val checkSize = 8
            val textX = option.rect.x + 8f + if (selected) checkSize + 5f else 0f
            if (selected) {
                renderTickIcon(context, option.rect.x + 7f, option.rect.y + (option.rect.h - checkSize) * 0.5f,
                    checkSize, ACCENT_TEXT)
            }
            ExosGuiRenderer.body(context.matrices, truncate(option.choice.choiceName,
                option.rect.right - textX - 7f, 7.1f, ExosGuiRenderer::bodyWidth), textX,
                option.rect.y + 5.2f, if (selected) TEXT_PRIMARY else TEXT_SECONDARY, 7.1f)
        }
    }

    private fun renderMultiChooseListValue(
        context: DrawContext,
        rect: Rect,
        value: MultiChooseListValue<*>,
        mouseX: Int,
        mouseY: Int
    ) {
        val hovered = rect.contains(mouseX, mouseY)
        if (hovered) {
            ExosGuiRenderer.roundedRect(context.matrices, rect.x - 6f, rect.y - 3f, rect.w + 12f, rect.h + 6f,
                5f, HOVER_FILL)
        }

        ExosGuiRenderer.body(context.matrices, displayName(value.name), rect.x, rect.y + 5f,
            TEXT_PRIMARY, 8.3f)

        val frame = multiChooseFrameRect(rect, value)
        ExosGuiRenderer.roundedRect(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f, CHOICE_FRAME_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, frame.x, frame.y, frame.w, frame.h, 5f,
            BORDER_SOFT, 0.75f)

        multiChooseOptionRects(frame, value).forEach { option ->
            val selected = option.choice in value.get()
            val optionHovered = option.rowRect.contains(mouseX, mouseY)
            val rowFill = when {
                selected -> ExosGuiRenderer.withAlpha(ClientTheme.accentA, 28)
                optionHovered -> HOVER_FILL
                else -> INPUT_FILL
            }
            ExosGuiRenderer.roundedRect(context.matrices, option.rowRect.x, option.rowRect.y,
                option.rowRect.w, option.rowRect.h, 4f, rowFill)
            ExosGuiRenderer.roundedOutline(context.matrices, option.rowRect.x, option.rowRect.y,
                option.rowRect.w, option.rowRect.h, 4f,
                if (selected || optionHovered) BORDER_SOFT_STRONG else BORDER_SOFT,
                if (optionHovered) 0.78f else 0.58f)

            val checkboxFill = when {
                selected -> CHOICE_SELECTED_FILL
                optionHovered -> HOVER_FILL
                else -> INPUT_FILL
            }
            ExosGuiRenderer.roundedRect(context.matrices, option.checkboxRect.x, option.checkboxRect.y,
                option.checkboxRect.w, option.checkboxRect.h, 3f, checkboxFill)
            ExosGuiRenderer.roundedOutline(context.matrices, option.checkboxRect.x, option.checkboxRect.y,
                option.checkboxRect.w, option.checkboxRect.h, 3f,
                if (selected) ACCENT_BORDER else if (optionHovered) BORDER_SOFT_STRONG else BORDER_SOFT,
                if (selected) 0.9f else 0.68f)

            if (selected) {
                val tickSize = MULTI_CHOOSE_TICK_SIZE
                renderTickIcon(
                    context,
                    option.checkboxRect.x + (option.checkboxRect.w - tickSize) * 0.5f,
                    option.checkboxRect.y + (option.checkboxRect.h - tickSize) * 0.5f,
                    tickSize,
                    ACCENT_TEXT
                )
            }

            val textX = option.checkboxRect.right + MULTI_CHOOSE_TEXT_GAP
            val text = truncate(multiChoiceDisplayName(option.choice),
                option.rowRect.right - textX - MULTI_CHOOSE_OPTION_PAD, 7.1f, ExosGuiRenderer::bodyWidth)
            ExosGuiRenderer.body(context.matrices, text, textX, option.rowRect.y + 5.2f,
                if (selected) TEXT_ENABLED else TEXT_SECONDARY, 7.1f)
        }
    }

    private fun drawToggle(context: DrawContext, rect: Rect, enabled: Boolean, key: Any) {
        val progress = toggleProgress(key, enabled)
        val knobSize = rect.h - 4f
        val knobStart = rect.x + 2f
        val knobEnd = rect.right - knobSize - 2f
        val knobX = lerp(knobStart, knobEnd, progress)
        val mixedTrack = ExosGuiRenderer.mix(0xFF3A4149.toInt(), ExosGuiRenderer.withAlpha(ACCENT, 230), progress)
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, rect.h * 0.5f, mixedTrack)
        ExosGuiRenderer.roundedRect(context.matrices, knobX, rect.y + 2f, knobSize, knobSize,
            knobSize * 0.5f, 0xFFF8F9FB.toInt())
    }

    private fun drawChoice(context: DrawContext, rect: Rect, value: String) {
        val text = truncate(value, rect.w * 0.5f, 8f, ExosGuiRenderer::bodyWidth)
        ExosGuiRenderer.body(context.matrices, text, rect.right - ExosGuiRenderer.bodyWidth(text, 8f) - 12f,
            rect.y + 7f, TEXT_SECONDARY, 8f)
    }

    private fun drawBind(context: DrawContext, rect: Rect, value: BindValue) {
        val listening = bindListening == value
        val box = Rect(rect.right - 76f, rect.y + 1f, 76f, 25f)
        ExosGuiRenderer.roundedRect(context.matrices, box.x, box.y, box.w, box.h, 5f, INPUT_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, box.x, box.y, box.w, box.h, 5f,
            if (listening) ACCENT_BORDER else BORDER_SOFT, 0.8f)
        val key = if (listening) "Press key" else value.get().keyName
        ExosGuiRenderer.body(context.matrices, truncate(key, box.w - 14f, 7.8f, ExosGuiRenderer::bodyWidth),
            box.x + 8f, box.y + 7f, if (listening) ACCENT_TEXT else TEXT_SECONDARY, 7.8f)
    }

    private fun drawRanged(context: DrawContext, rect: Rect, value: RangedValue<*>) {
        val current = value.get()
        val formatted = formatRanged(value)
        ExosGuiRenderer.body(context.matrices, formatted,
            rect.right - ExosGuiRenderer.bodyWidth(formatted, 8f), rect.y + 3f, TEXT_SECONDARY, 8f)

        val slider = sliderTrack(rect)
        ExosGuiRenderer.roundedRect(context.matrices, slider.x, slider.y, slider.w, slider.h, slider.h * 0.5f,
            SLIDER_TRACK)

        val progress = rangedProgresses(value, current)
        val startX = slider.x + slider.w * progress.first
        val endX = slider.x + slider.w * progress.second
        val drawsRange = current is IntRange || current is ClosedFloatingPointRange<*>
        val activeX = if (drawsRange) min(startX, endX) else slider.x
        val activeW = if (drawsRange) max(1f, abs(endX - startX)) else max(1f, endX - slider.x)
        ExosGuiRenderer.roundedRect(context.matrices, activeX, slider.y, activeW, slider.h, slider.h * 0.5f,
            ACCENT)

        drawSliderKnob(context, startX, slider.y + slider.h * 0.5f)
        if (drawsRange && progress.second != progress.first) {
            drawSliderKnob(context, endX, slider.y + slider.h * 0.5f)
        }
    }

    private fun drawSliderKnob(context: DrawContext, centerX: Float, centerY: Float) {
        val size = SLIDER_KNOB
        ExosGuiRenderer.roundedRect(context.matrices, centerX - size * 0.5f, centerY - size * 0.5f,
            size, size, size * 0.5f, SLIDER_KNOB_FILL)
        ExosGuiRenderer.roundedOutline(context.matrices, centerX - size * 0.5f, centerY - size * 0.5f,
            size, size, size * 0.5f, 0xAAFFFFFF.toInt(), 0.65f)
    }

    private fun drawColor(context: DrawContext, rect: Rect, value: Value<*>) {
        val color = (value.get() as? Color4b)?.toARGB() ?: ACCENT
        val box = Rect(rect.right - 28f, rect.y + 1f, 25f, 25f)
        ExosGuiRenderer.roundedRect(context.matrices, box.x, box.y, box.w, box.h, 5f, color)
        ExosGuiRenderer.roundedOutline(context.matrices, box.x, box.y, box.w, box.h, 5f, BORDER_SOFT, 0.8f)
    }

    private fun drawPlainValue(context: DrawContext, rect: Rect, value: Value<*>) {
        val text = truncate(value.getTagValue().toString(), rect.w * 0.5f, 8f, ExosGuiRenderer::bodyWidth)
        ExosGuiRenderer.body(context.matrices, text, rect.right - ExosGuiRenderer.bodyWidth(text, 8f),
            rect.y + 7f, TEXT_SECONDARY, 8f)
    }

    override fun mouseClicked(event: MouseButtonEvent, doubleClick: Boolean): Boolean =
        mouseClicked(event.x(), event.y(), event.button())

    private fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (closing) {
            return true
        }

        if (bindListening != null) {
            bindListening?.get()?.bind(InputConstants.Type.MOUSE.getOrCreate(button))
            bindListening = null
            return true
        }

        val mx = mouseX.toInt()
        val my = mouseY.toInt()
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && searchIconRect.contains(mx, my)) {
            setSearchExpanded(true)
            searchIconClickedAt = System.currentTimeMillis()
            searchFocused = true
            return true
        }

        searchFocused = searchRect.contains(mx, my)
        if (!searchFocused && selectedPage.category != null && searchText.isBlank()) {
            setSearchExpanded(false)
        }

        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && closeSettingsRect.contains(mx, my)) {
            closeSettingsPanel()
            return true
        }

        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            themeRects.firstOrNull { it.second.contains(mx, my) }?.let { (themeIndex, _) ->
                ClickGuiTheme.selected = themeIndex
                return true
            }

            configActionRects.firstOrNull { it.second.contains(mx, my) }?.let { (action, _) ->
                handleConfigAction(action)
                return true
            }
        }

        settingRects.firstOrNull { it.second.contains(mx, my) }?.let { (entry, rect) ->
            handleSettingClick(entry, rect, mouseX.toFloat(), mouseY.toFloat(), button)
            return true
        }

        if (settingsArea.contains(mx, my)) {
            return true
        }

        categoryRects.firstOrNull { it.second.contains(mx, my) }?.let { (category, _) ->
            selectedPage = category
            closeSettingsPanel()
            moduleScroll = 0f
            configScroll = 0f
            themeScroll = 0f
            searchFocused = false
            if (searchText.isBlank()) {
                setSearchExpanded(false)
            }
            return true
        }

        moduleRects.firstOrNull { it.second.contains(mx, my) }?.let { (module, rect) ->
            when (button) {
                GLFW.GLFW_MOUSE_BUTTON_LEFT -> module.enabled = !module.enabled
                GLFW.GLFW_MOUSE_BUTTON_RIGHT -> {
                    selectedModule = module
                    settingsScroll = 0f
                    settingsPanelAnimation = SettingsPanelAnimation(module, rect, System.currentTimeMillis())
                }
            }
            return true
        }

        return true
    }

    private fun closeSettingsPanel() {
        selectedModule = null
        settingsPanelAnimation = null
        settingsScroll = 0f
        draggingSlider = null
        draggingRangeSide = null
    }

    private fun validateSelectedModule() {
        val module = selectedModule ?: return
        if (module.category != selectedPage.category || module.hidden || module !in filteredModules()) {
            closeSettingsPanel()
        }
    }

    override fun mouseReleased(event: MouseButtonEvent): Boolean {
        draggingSlider = null
        draggingRangeSide = null
        return true
    }

    override fun mouseDragged(event: MouseButtonEvent, dx: Double, dy: Double): Boolean {
        val entry = draggingSlider ?: return false
        val rect = settingRects.firstOrNull { it.first == entry }?.second ?: return false
        updateSlider(entry.value as RangedValue<*>, rect, event.x().toFloat())
        return true
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, horizontal: Double, vertical: Double): Boolean {
        if (closing) {
            return true
        }

        val amount = (-vertical * 28.0).toFloat()
        when {
            settingsArea.contains(mouseX.toInt(), mouseY.toInt()) -> settingsScroll = (settingsScroll + amount).coerceAtLeast(0f)
            selectedPage == ClickGuiPage.CONFIG && moduleArea.contains(mouseX.toInt(), mouseY.toInt()) ->
                configScroll = (configScroll + amount).coerceAtLeast(0f)
            selectedPage == ClickGuiPage.THEME && moduleArea.contains(mouseX.toInt(), mouseY.toInt()) ->
                themeScroll = (themeScroll + amount).coerceAtLeast(0f)
            moduleArea.contains(mouseX.toInt(), mouseY.toInt()) -> moduleScroll = (moduleScroll + amount).coerceAtLeast(0f)
            else -> return false
        }
        return true
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (closing) {
            return true
        }

        bindListening?.let {
            if (event.isEscape()) {
                it.get().unbind()
            } else {
                it.get().bind(InputConstants.Type.KEYSYM.getOrCreate(event.key()))
            }
            bindListening = null
            return true
        }

        if (event.isEscape()) {
            closeSettingsPanel()
            searchFocused = false
            if (searchText.isBlank()) {
                setSearchExpanded(false)
            }
            return super.keyPressed(event)
        }

        if (searchFocused) {
            when (event.key()) {
                GLFW.GLFW_KEY_ESCAPE -> {
                    searchFocused = false
                    if (searchText.isBlank()) {
                        setSearchExpanded(false)
                    }
                    return true
                }
                GLFW.GLFW_KEY_BACKSPACE -> {
                    searchText = searchText.dropLast(1)
                    moduleScroll = 0f
                    configScroll = 0f
                    return true
                }
                GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER -> {
                    searchFocused = false
                    return true
                }
            }
        }

        return super.keyPressed(event)
    }

    override fun charTyped(event: CharacterEvent): Boolean {
        if (!searchFocused || !event.isAllowedChatCharacter()) {
            return false
        }
        setSearchExpanded(true)
        searchText += event.codepointAsString()
        moduleScroll = 0f
        configScroll = 0f
        return true
    }

    override fun isPauseScreen(): Boolean = false

    override fun onClose() {
        if (!closing) {
            closing = true
            closingStartedAt = System.currentTimeMillis()
            searchFocused = false
            bindListening = null
            draggingSlider = null
            draggingRangeSide = null
            return
        }

        super.onClose()
    }

    private fun handleSettingClick(entry: SettingEntry, rect: Rect, mouseX: Float, mouseY: Float, button: Int) {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return
        }

        when (val value = entry.value) {
            is ToggleableConfigurable -> value.enabled = !value.enabled
            is ChoiceConfigurable<*> -> handleChoiceClick(value, rect, mouseX, mouseY)
            is ChooseListValue<*> -> handleChooseClick(value, rect, mouseX, mouseY)
            is MultiChooseListValue<*> -> handleMultiChooseClick(value, rect, mouseX, mouseY)
            is BindValue -> bindListening = value
            is RangedValue<*> -> {
                draggingSlider = entry
                draggingRangeSide = nearestRangeSide(value, rect, mouseX)
                updateSlider(value, rect, mouseX)
            }
            else -> when (value.type()) {
                ValueType.BOOLEAN -> setBoolean(value, value.get() != true)
                ValueType.COLOR -> cycleColor(value)
                else -> Unit
            }
        }
    }

    private fun handleChoiceClick(value: ChoiceConfigurable<*>, rect: Rect, mouseX: Float, mouseY: Float) {
        val frame = choiceFrameRect(rect, value)
        choiceOptionRects(frame, value).firstOrNull { it.rect.contains(mouseX.toInt(), mouseY.toInt()) }
            ?.choice
            ?.choiceName
            ?.let(value::setByString)
    }

    private fun handleChooseClick(value: ChooseListValue<*>, rect: Rect, mouseX: Float, mouseY: Float) {
        val frame = chooseFrameRect(rect, value)
        chooseOptionRects(frame, value).firstOrNull { it.rect.contains(mouseX.toInt(), mouseY.toInt()) }
            ?.choice
            ?.choiceName
            ?.let(value::setByString)
    }

    private fun handleMultiChooseClick(value: MultiChooseListValue<*>, rect: Rect, mouseX: Float, mouseY: Float) {
        val frame = multiChooseFrameRect(rect, value)
        multiChooseOptionRects(frame, value).firstOrNull {
            it.rowRect.contains(mouseX.toInt(), mouseY.toInt())
        }?.choice?.let { toggleMultiChoice(value, it) }
    }

    private fun updateSlider(value: RangedValue<*>, rect: Rect, mouseX: Float) {
        val slider = sliderTrack(rect)
        val progress = ((mouseX - slider.x) / slider.w).coerceIn(0f, 1f)
        when (val current = value.get()) {
            is Float -> {
                val range = value.range as ClosedFloatingPointRange<*>
                val start = (range.start as Number).toFloat()
                val end = (range.endInclusive as Number).toFloat()
                setAny(value, start + (end - start) * progress)
            }
            is Int -> {
                val range = value.range as IntRange
                setAny(value, (range.first + (range.last - range.first) * progress).toInt())
            }
            is IntRange -> {
                val range = value.range as IntRange
                val next = (range.first + (range.last - range.first) * progress).roundToInt()
                    .coerceIn(range.first, range.last)
                when (draggingRangeSide ?: nearestRangeSide(value, rect, mouseX)) {
                    RangeDragSide.START -> setAny(value, next.coerceAtMost(current.last)..current.last)
                    RangeDragSide.END -> setAny(value, current.first..next.coerceAtLeast(current.first))
                }
            }
            is ClosedFloatingPointRange<*> -> {
                val range = value.range as ClosedFloatingPointRange<*>
                val start = (range.start as Number).toFloat()
                val end = (range.endInclusive as Number).toFloat()
                val next = (start + (end - start) * progress).coerceIn(start, end)
                val currentStart = (current.start as Number).toFloat()
                val currentEnd = (current.endInclusive as Number).toFloat()
                when (draggingRangeSide ?: nearestRangeSide(value, rect, mouseX)) {
                    RangeDragSide.START -> setAny(value, next.coerceAtMost(currentEnd)..currentEnd)
                    RangeDragSide.END -> setAny(value, currentStart..next.coerceAtLeast(currentStart))
                }
            }
            else -> current
        }
    }

    private fun rangedProgresses(value: RangedValue<*>, current: Any): Pair<Float, Float> = when (current) {
        is Float -> {
            val range = value.range as ClosedFloatingPointRange<*>
            val start = (range.start as Number).toFloat()
            val end = (range.endInclusive as Number).toFloat()
            val progress = progressInRange(current, start, end)
            progress to progress
        }
        is Int -> {
            val range = value.range as IntRange
            val progress = progressInRange(current.toFloat(), range.first.toFloat(), range.last.toFloat())
            progress to progress
        }
        is IntRange -> {
            val range = value.range as IntRange
            progressInRange(current.first.toFloat(), range.first.toFloat(), range.last.toFloat()) to
                progressInRange(current.last.toFloat(), range.first.toFloat(), range.last.toFloat())
        }
        is ClosedFloatingPointRange<*> -> {
            val range = value.range as ClosedFloatingPointRange<*>
            val start = (range.start as Number).toFloat()
            val end = (range.endInclusive as Number).toFloat()
            progressInRange((current.start as Number).toFloat(), start, end) to
                progressInRange((current.endInclusive as Number).toFloat(), start, end)
        }
        else -> 0f to 0f
    }

    private fun nearestRangeSide(value: RangedValue<*>, rect: Rect, mouseX: Float): RangeDragSide {
        val slider = sliderTrack(rect)
        val progress = ((mouseX - slider.x) / slider.w).coerceIn(0f, 1f)
        val current = value.get()
        val ranges = rangedProgresses(value, current)
        return if (abs(progress - ranges.first) <= abs(progress - ranges.second)) {
            RangeDragSide.START
        } else {
            RangeDragSide.END
        }
    }

    private fun progressInRange(value: Float, start: Float, end: Float): Float {
        if (abs(end - start) <= 1.0E-4f) {
            return 0f
        }

        return ((value - start) / (end - start)).coerceIn(0f, 1f)
    }

    private fun sliderTrack(rect: Rect): Rect {
        val inset = SLIDER_KNOB * 0.5f + 1f
        return Rect(rect.x + inset, rect.y + 25f, (rect.w - inset * 2f).coerceAtLeast(1f), 4f)
    }

    private fun choiceFrameRect(rect: Rect, value: ChoiceConfigurable<*>): Rect {
        val rows = choiceRows(value.choices.size)
        val height = CHOICE_FRAME_PAD * 2f + rows * CHOICE_BOX_H + max(0, rows - 1) * CHOICE_BOX_GAP
        return Rect(rect.x, rect.y + 25f, rect.w, height)
    }

    private fun choiceOptionRects(frame: Rect, value: ChoiceConfigurable<*>): List<ChoiceOptionRect> {
        val result = ArrayList<ChoiceOptionRect>(value.choices.size)
        val columns = choiceColumns(value.choices.size)
        val cellW = (frame.w - CHOICE_FRAME_PAD * 2f - CHOICE_BOX_GAP * (columns - 1)) / columns
        value.choices.forEachIndexed { index, choice ->
            val column = index % columns
            val row = index / columns
            result += ChoiceOptionRect(
                choice,
                Rect(
                    frame.x + CHOICE_FRAME_PAD + column * (cellW + CHOICE_BOX_GAP),
                    frame.y + CHOICE_FRAME_PAD + row * (CHOICE_BOX_H + CHOICE_BOX_GAP),
                    cellW,
                    CHOICE_BOX_H
                )
            )
        }
        return result
    }

    private fun chooseFrameRect(rect: Rect, value: ChooseListValue<*>): Rect {
        val rows = choiceRows(value.choices.size)
        val height = CHOICE_FRAME_PAD * 2f + rows * CHOICE_BOX_H + max(0, rows - 1) * CHOICE_BOX_GAP
        return Rect(rect.x, rect.y + 25f, rect.w, height)
    }

    private fun chooseOptionRects(frame: Rect, value: ChooseListValue<*>): List<ChooseOptionRect> {
        val result = ArrayList<ChooseOptionRect>(value.choices.size)
        val columns = choiceColumns(value.choices.size)
        val cellW = (frame.w - CHOICE_FRAME_PAD * 2f - CHOICE_BOX_GAP * (columns - 1)) / columns
        value.choices.forEachIndexed { index, choice ->
            val column = index % columns
            val row = index / columns
            result += ChooseOptionRect(
                choice,
                Rect(
                    frame.x + CHOICE_FRAME_PAD + column * (cellW + CHOICE_BOX_GAP),
                    frame.y + CHOICE_FRAME_PAD + row * (CHOICE_BOX_H + CHOICE_BOX_GAP),
                    cellW,
                    CHOICE_BOX_H
                )
            )
        }
        return result
    }

    private fun choiceRows(choiceCount: Int): Int {
        val columns = choiceColumns(choiceCount)
        return ceil(choiceCount / columns.toFloat()).toInt().coerceAtLeast(1)
    }

    private fun choiceColumns(choiceCount: Int): Int =
        if (choiceCount <= 1) 1 else 2

    private fun multiChooseFrameRect(rect: Rect, value: MultiChooseListValue<*>): Rect {
        val rows = multiChooseRows(rect.w, value)
        val height = CHOICE_FRAME_PAD * 2f + rows * MULTI_CHOOSE_OPTION_H +
            max(0, rows - 1) * MULTI_CHOOSE_OPTION_GAP
        return Rect(rect.x, rect.y + 25f, rect.w, height)
    }

    private fun multiChooseOptionRects(
        frame: Rect,
        value: MultiChooseListValue<*>
    ): List<MultiChooseOptionRect> {
        val choices = value.choices.toList()
        val result = ArrayList<MultiChooseOptionRect>(choices.size)
        val columns = multiChooseColumnCount(frame.w, choices)
        val cellW = (frame.w - CHOICE_FRAME_PAD * 2f - MULTI_CHOOSE_COLUMN_GAP * (columns - 1)) / columns

        choices.forEachIndexed { index, choice ->
            val column = index % columns
            val row = index / columns
            val rowRect = Rect(
                frame.x + CHOICE_FRAME_PAD + column * (cellW + MULTI_CHOOSE_COLUMN_GAP),
                frame.y + CHOICE_FRAME_PAD + row * (MULTI_CHOOSE_OPTION_H + MULTI_CHOOSE_OPTION_GAP),
                cellW,
                MULTI_CHOOSE_OPTION_H
            )
            val checkboxRect = Rect(
                rowRect.x + MULTI_CHOOSE_OPTION_PAD,
                rowRect.y + (rowRect.h - MULTI_CHOOSE_CHECKBOX_SIZE) * 0.5f,
                MULTI_CHOOSE_CHECKBOX_SIZE,
                MULTI_CHOOSE_CHECKBOX_SIZE
            )
            result += MultiChooseOptionRect(choice, rowRect, checkboxRect)
        }

        return result
    }

    private fun multiChooseRows(width: Float, value: MultiChooseListValue<*>): Int {
        val choices = value.choices.toList()
        val columns = multiChooseColumnCount(width, choices)
        return ceil(choices.size / columns.toFloat()).toInt().coerceAtLeast(1)
    }

    private fun multiChooseColumnCount(width: Float, choices: List<*>): Int {
        if (choices.size <= 1) {
            return 1
        }

        val twoColumnW = (width - CHOICE_FRAME_PAD * 2f - MULTI_CHOOSE_COLUMN_GAP) * 0.5f
        val labelW = twoColumnW - MULTI_CHOOSE_OPTION_PAD * 2f - MULTI_CHOOSE_CHECKBOX_SIZE -
            MULTI_CHOOSE_TEXT_GAP
        if (twoColumnW < MULTI_CHOOSE_MIN_COLUMN_W || labelW < MULTI_CHOOSE_MIN_LABEL_W) {
            return 1
        }

        val longest = choices.maxOfOrNull {
            ExosGuiRenderer.bodyWidth(multiChoiceDisplayName(it), 7.1f)
        } ?: 0f

        return if (longest <= labelW * MULTI_CHOOSE_TWO_COLUMN_READABLE_FACTOR) 2 else 1
    }

    private fun multiChoiceDisplayName(choice: Any?): String = when (choice) {
        is NamedChoice -> choice.choiceName
        is String -> choice
        null -> ""
        else -> choice.toString()
    }

    @Suppress("UNCHECKED_CAST")
    private fun toggleMultiChoice(value: MultiChooseListValue<*>, choice: Any?) {
        (value as MultiChooseListValue<Any?>).toggle(choice)
    }

    private fun formatRanged(value: RangedValue<*>): String = when (val current = value.get()) {
        is Float -> String.format(Locale.US, "%.2f%s", current, value.suffix)
        is Int -> "${current}${value.suffix}"
        is IntRange -> "${current.first}-${current.last}${value.suffix}"
        is ClosedFloatingPointRange<*> -> {
            val start = (current.start as Number).toFloat()
            val end = (current.endInclusive as Number).toFloat()
            String.format(Locale.US, "%.2f-%.2f%s", start, end, value.suffix)
        }
        else -> current.toString()
    }

    private fun cycleChoice(value: ChoiceConfigurable<*>) {
        val choices = value.choices
        if (choices.isEmpty()) {
            return
        }
        val index = choices.indexOf(value.activeChoice)
        value.setByString(choices[(index + 1).floorMod(choices.size)].choiceName)
    }

    private fun cycleChoose(value: ChooseListValue<*>) {
        val choices = value.choices
        if (choices.isEmpty()) {
            return
        }
        val index = choices.indexOf(value.get())
        value.setByString(choices[(index + 1).floorMod(choices.size)].choiceName)
    }

    private fun cycleColor(value: Value<*>) {
        val current = value.get() as? Color4b ?: return
        val presets = ClientTheme.COLORS.flatMap { listOf(it.first, it.second) }
        val next = presets.firstOrNull { it and 0x00FFFFFF != current.toARGB() and 0x00FFFFFF } ?: ACCENT
        setAny(value, Color4b(next, hasAlpha = true).with(a = current.a))
    }

    private fun setBoolean(value: Value<*>, state: Boolean) {
        setAny(value, state)
    }

    @Suppress("UNCHECKED_CAST")
    private fun setAny(value: Value<*>, newValue: Any) {
        (value as Value<Any>).set(newValue)
    }

    private fun visibleSettings(module: ClientModule): List<SettingEntry> {
        val values = mutableListOf<SettingEntry>()
        collectValues(module, values, 0)
        return values
    }

    private fun collectValues(configurable: Configurable, out: MutableList<SettingEntry>, depth: Int) {
        configurable.inner.forEach { value ->
            if (value.notAnOption || value.name.equals("Enabled", true) || value.name.equals("Hidden", true)) {
                return@forEach
            }

            when (value) {
                is ChoiceConfigurable<*> -> {
                    out += SettingEntry(value, depth)
                    collectValues(value.activeChoice, out, depth + 1)
                }
                is ToggleableConfigurable -> {
                    out += SettingEntry(value, depth)
                    if (value.enabled) {
                        collectValues(value, out, depth + 1)
                    }
                }
                is Configurable -> collectValues(value, out, depth)
                else -> out += SettingEntry(value, depth)
            }
        }
    }

    private fun settingHeight(entry: SettingEntry): Float = when (entry.value) {
        is ToggleableConfigurable -> 40f
        is ChoiceConfigurable<*> -> {
            val rows = choiceRows(entry.value.choices.size)
            val frame = rows * CHOICE_BOX_H + max(0, rows - 1) * CHOICE_BOX_GAP +
                CHOICE_FRAME_PAD * 2f
            31f + frame + 8f
        }
        is ChooseListValue<*> -> {
            val rows = choiceRows(entry.value.choices.size)
            val frame = rows * CHOICE_BOX_H + max(0, rows - 1) * CHOICE_BOX_GAP +
                CHOICE_FRAME_PAD * 2f
            31f + frame + 8f
        }
        is MultiChooseListValue<*> -> {
            val rows = multiChooseRows(detachedSettingsWidth() - SETTINGS_PAD * 2f - entry.depth * 10f, entry.value)
            val frame = rows * MULTI_CHOOSE_OPTION_H + max(0, rows - 1) * MULTI_CHOOSE_OPTION_GAP +
                CHOICE_FRAME_PAD * 2f
            31f + frame + 8f
        }
        is RangedValue<*> -> {
            val current = entry.value.get()
            if (current is Float || current is Int || current is IntRange || current is ClosedFloatingPointRange<*>) {
                50f
            } else {
                ROW_H
            }
        }
        else -> ROW_H
    }

    private fun filteredModules(): List<ClientModule> {
        val category = selectedPage.category ?: return emptyList()
        val query = searchText.trim()
        return modulesFor(category).filter { module ->
            query.isEmpty() || module.name.contains(query, ignoreCase = true) ||
                moduleDescription(module).contains(query, ignoreCase = true)
        }.sortedBy { it.name.lowercase(Locale.ROOT) }
    }

    private fun modulesFor(category: Category): List<ClientModule> =
        ModuleManager.filter { it.category == category && !it.hidden }

    private fun filteredConfigs(): List<File> {
        val query = searchText.trim()
        return ConfigSystem.userConfigsFolder
            .listFiles { file -> file.isFile && file.extension.equals("json", ignoreCase = true) }
            ?.filter { query.isEmpty() || it.nameWithoutExtension.contains(query, ignoreCase = true) }
            ?.sortedBy { it.nameWithoutExtension.lowercase(Locale.ROOT) }
            ?: emptyList()
    }

    private fun filteredThemes(): List<Int> = ClickGuiTheme.COLORS.indices.toList()

    private fun handleConfigAction(action: ConfigAction) {
        when (action.type) {
            ConfigActionType.SAVE -> saveCurrentConfig()
            ConfigActionType.LOAD -> action.file?.let(::loadConfig)
            ConfigActionType.DELETE -> action.file?.let(::deleteConfig)
        }
    }

    private fun saveCurrentConfig() {
        ConfigSystem.userConfigsFolder.mkdirs()
        val stamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.ROOT).format(Date())
        val file = ConfigSystem.userConfigsFolder.resolve("config-$stamp.json")
        runCatching {
            file.createNewFile()
            file.bufferedWriter().use { AutoConfig.serializeAutoConfig(it) }
        }.onSuccess {
            chat(regular("Saved config ${file.nameWithoutExtension}"))
        }.onFailure {
            chat(markAsError("Failed to save config ${file.nameWithoutExtension}"))
        }
    }

    private fun loadConfig(file: File) {
        runCatching {
            file.bufferedReader().use { reader ->
                AutoConfig.withLoading {
                    AutoConfig.loadAutoConfig(reader)
                }
            }
        }.onSuccess {
            chat(regular("Loaded config ${file.nameWithoutExtension}"))
        }.onFailure {
            chat(markAsError("Failed to load config ${file.nameWithoutExtension}"))
        }
    }

    private fun deleteConfig(file: File) {
        runCatching {
            check(file.exists() && file.delete()) { "delete failed" }
        }.onSuccess {
            configScroll = 0f
            chat(regular("Deleted config ${file.nameWithoutExtension}"))
        }.onFailure {
            chat(markAsError("Failed to delete config ${file.nameWithoutExtension}"))
        }
    }

    private fun moduleDescription(module: ClientModule): String =
        runCatching { module.description.get() ?: "" }.getOrDefault("")

    private fun renderPageIcon(context: DrawContext, page: ClickGuiPage, x: Float, y: Float, size: Float, color: Int) {
        if (page == ClickGuiPage.THEME) {
            val dot = (size * 0.38f).coerceAtLeast(5f)
            ExosGuiRenderer.roundedRect(context.matrices, x, y, size, size, 4f, 0x22000000)
            ExosGuiRenderer.roundedRect(context.matrices, x + size * 0.18f, y + size * 0.18f,
                dot, dot, dot * 0.5f, ClientTheme.accentA)
            ExosGuiRenderer.roundedRect(context.matrices, x + size * 0.46f, y + size * 0.18f,
                dot, dot, dot * 0.5f, ClientTheme.accentB)
            ExosGuiRenderer.roundedRect(context.matrices, x + size * 0.31f, y + size * 0.48f,
                dot, dot, dot * 0.5f, color)
            return
        }

        val icon = pageIcon(page)
        if (icon != null) {
            val ix = x.toInt()
            val iy = y.toInt()
            val isize = size.toInt().coerceAtLeast(1)
            context.blit(RenderPipelines.GUI_TEXTURED, icon, ix, iy, 0f, 0f, isize, isize, isize, isize, color)
            return
        }

        val cx = x + size * 0.5f
        val cy = y + size * 0.5f
        context.fill((cx - size * 0.35f).toInt(), cy.toInt(), (cx + size * 0.35f).toInt(), (cy + 1f).toInt(), color)
        context.fill(cx.toInt(), (cy - size * 0.35f).toInt(), (cx + 1f).toInt(), (cy + size * 0.35f).toInt(), color)
    }

    private fun renderCloseIcon(context: DrawContext, rect: Rect, color: Int) {
        val icon = closeIcon()
        val iconSize = 9
        val iconX = (rect.x + (rect.w - iconSize) * 0.5f).toInt()
        val iconY = (rect.y + (rect.h - iconSize) * 0.5f).toInt()

        if (icon != null) {
            context.blit(RenderPipelines.GUI_TEXTURED, icon, iconX, iconY, 0f, 0f, iconSize, iconSize,
                iconSize, iconSize, color)
            return
        }

        ExosGuiRenderer.body(context.matrices, "X", rect.x + 6.4f, rect.y + 4.2f, color, 7.6f)
    }

    private fun renderTickIcon(context: DrawContext, x: Float, y: Float, size: Int, color: Int) {
        val icon = tickIcon()
        if (icon != null) {
            context.blit(RenderPipelines.GUI_TEXTURED, icon, x.toInt(), y.toInt(), 0f, 0f, size, size,
                size, size, color)
            return
        }

        val stroke = (size / 4f).coerceAtLeast(1f)
        context.fill(
            (x + size * 0.18f).toInt(),
            (y + size * 0.53f).toInt(),
            (x + size * 0.18f + stroke).toInt(),
            (y + size * 0.84f).toInt(),
            color
        )
        context.fill(
            (x + size * 0.18f).toInt(),
            (y + size * 0.72f).toInt(),
            (x + size * 0.78f).toInt(),
            (y + size * 0.72f + stroke).toInt(),
            color
        )
    }

    private fun renderSearchIcon(context: DrawContext, rect: Rect, color: Int) {
        val icon = searchIcon()
        val shake = searchIconShake()
        val iconSize = (rect.h * 0.52f).toInt().coerceAtLeast(12)
        val iconX = (rect.x + (rect.w - iconSize) * 0.5f + shake).toInt()
        val iconY = (rect.y + (rect.h - iconSize) * 0.5f).toInt()

        if (icon != null) {
            context.blit(RenderPipelines.GUI_TEXTURED, icon, iconX, iconY, 0f, 0f, iconSize, iconSize,
                iconSize, iconSize, color)
            return
        }

        ExosGuiRenderer.body(context.matrices, "?", iconX.toFloat(), iconY.toFloat() - 1f, color,
            iconSize.toFloat())
    }

    private fun pageIcon(page: ClickGuiPage): Identifier? =
        pageIconCache.getOrPut(page) {
            runCatching {
                Identifier.fromNamespaceAndPath("liquidbounce", "clickgui-icon-${page.id}").apply {
                    registerDynamicImageFromResources("clickgui/icons/${page.id}.png")
                }
            }.getOrNull()
        }

    private fun closeIcon(): Identifier? =
        closeIconCache ?: runCatching {
            Identifier.fromNamespaceAndPath("liquidbounce", "clickgui-icon-close").apply {
                registerDynamicImageFromResources("clickgui/icons/x.png")
            }
        }.getOrNull().also { closeIconCache = it }

    private fun tickIcon(): Identifier? =
        tickIconCache ?: runCatching {
            Identifier.fromNamespaceAndPath("liquidbounce", "clickgui-icon-tick").apply {
                registerDynamicImageFromResources("clickgui/icons/tick.png")
            }
        }.getOrNull().also { tickIconCache = it }

    private fun searchIcon(): Identifier? =
        searchIconCache ?: runCatching {
            Identifier.fromNamespaceAndPath("liquidbounce", "clickgui-icon-search").apply {
                registerDynamicImageFromResources("clickgui/icons/search.png")
            }
        }.getOrNull().also { searchIconCache = it }

    private fun localSkinId(): Identifier? {
        val player = mc.player as? AbstractClientPlayer ?: return null
        val skin = runCatching { player.skin }.getOrNull()
        val skinId = skin?.takeIf { it.secure() }?.body()?.id()
        return skinId
            ?.takeUnless { it == MissingTextureAtlasSprite.getLocation() }
            ?: DefaultPlayerSkin.get(player.uuid).body().id()
    }

    private fun layout(): Layout {
        val margin = 12f
        val gap = SETTINGS_PANEL_GAP
        val settingsW = if (selectedModule != null) detachedSettingsWidth() else 0f
        val availableW = (width - margin * 2f).coerceAtLeast(320f)
        val availableH = (height - margin * 2f).coerceAtLeast(260f)
        val targetW = width * MAIN_WIDTH_RATIO
        val targetH = height * MAIN_HEIGHT_RATIO
        val minW = min(availableW, MAIN_MIN_W)
        val maxW = min(availableW, MAIN_MAX_W)
        val maxMainWithSettings = if (settingsW > 0f) availableW - settingsW - gap else maxW
        val panelW = targetW.coerceIn(minW, min(maxW, maxMainWithSettings.coerceAtLeast(minW)))
        val minH = min(availableH, MAIN_MIN_H)
        val maxH = min(availableH, MAIN_MAX_H)
        val panelH = targetH.coerceIn(minH, maxH)
        val centeredX = (width - panelW) * 0.5f
        val maxXWithRightSettings = if (settingsW > 0f) {
            width - margin - settingsW - gap - panelW
        } else {
            centeredX
        }
        val panelX = min(centeredX, maxXWithRightSettings).coerceAtLeast(margin)
        val scale = (panelH / MAIN_REFERENCE_H).coerceIn(0.82f, 1f)
        val sidebarW = (panelW * 0.25f).coerceIn(126f * scale, 158f * scale)
        return Layout(
            x = panelX,
            y = (height - panelH) * 0.5f,
            w = panelW,
            h = panelH,
            sidebarW = sidebarW,
            moduleW = panelW - sidebarW,
            scale = scale,
            pad = 24f * scale,
            cardH = 55f * scale
        )
    }

    private fun detachedSettingsWidth(): Float {
        val responsive = width * SETTINGS_PANEL_WIDTH_RATIO
        val minWidth = min(width - 24f, SETTINGS_PANEL_MIN_W)
        val maxWidth = min(width - 24f, SETTINGS_PANEL_W)
        return responsive.coerceIn(minWidth, maxWidth)
    }

    private fun moduleSettingsPanelRect(layout: Layout, module: ClientModule): Rect {
        val panelW = detachedSettingsWidth()
        val entries = visibleSettings(module)
        val contentH = entries.sumOf { settingHeight(it).toDouble() }.toFloat()
        val desiredH = (SETTINGS_HEADER_H + contentH + SETTINGS_BOTTOM_PAD).coerceAtLeast(SETTINGS_MIN_H)
        val maxH = (height - layout.y - 10f).coerceAtLeast(SETTINGS_MIN_H)
        val panelH = min(desiredH, maxH)
        val rightX = layout.x + layout.w + SETTINGS_PANEL_GAP
        val leftX = layout.x - SETTINGS_PANEL_GAP - panelW
        val x = when {
            rightX + panelW <= width - 8f -> rightX
            leftX >= 8f -> leftX
            else -> width - 8f - panelW
        }
        return Rect(x, layout.y, panelW, panelH)
    }

    private fun displayName(name: String): String =
        name.replace(Regex("([a-z])([A-Z])"), "$1 $2")

    private fun truncate(text: String, maxWidth: Float, size: Float, measure: (String, Float) -> Float): String {
        if (measure(text, size) <= maxWidth) {
            return text
        }
        var out = text
        while (out.length > 1 && measure("$out...", size) > maxWidth) {
            out = out.dropLast(1)
        }
        return "$out..."
    }

    private fun Int.floorMod(mod: Int): Int = ((this % mod) + mod) % mod

    private fun pushMainAnimation(context: DrawContext, layout: Layout, scale: Float): Boolean {
        if (scale >= 0.999f) {
            return false
        }

        // #Edit By Codex 2026-06-26: use the real 1.26 GUI pose matrix; context.matrices is a compat shim.
        val centerX = layout.x + layout.w * 0.5f
        val centerY = layout.y + layout.h * 0.5f
        context.pose().pushMatrix()
        context.pose().translate(centerX, centerY)
        context.pose().scale(scale, scale)
        context.pose().translate(-centerX, -centerY)
        return true
    }

    private fun mainAnimationScale(): Float {
        val progress = if (closing) {
            1f - closeProgress()
        } else {
            ((System.currentTimeMillis() - openedAt).toFloat() / OPEN_ANIMATION_MS).coerceIn(0f, 1f)
        }
        return lerp(OPEN_MIN_SCALE, 1f, bounceScaleOut(progress))
    }

    private fun closeProgress(): Float =
        ((System.currentTimeMillis() - closingStartedAt).toFloat() / CLOSE_ANIMATION_MS).coerceIn(0f, 1f)

    private fun animatedModuleCard(rect: Rect, index: Int): AnimatedRect {
        val delay = min(index, 12) * MODULE_CARD_DELAY_MS
        val raw = ((System.currentTimeMillis() - openedAt).toFloat() - delay) / MODULE_CARD_ANIMATION_MS
        val progress = cubicOut(raw.coerceIn(0f, 1f))
        val offsetY = MODULE_CARD_OFFSET_Y * (1f - progress)
        val scale = lerp(MODULE_CARD_MIN_SCALE, 1f, progress)
        val shifted = Rect(rect.x, rect.y + offsetY, rect.w, rect.h)
        return AnimatedRect(scaleRectFromCenter(shifted, scale), progress)
    }

    private fun scaleRectFromCenter(rect: Rect, scale: Float): Rect {
        val nextW = rect.w * scale
        val nextH = rect.h * scale
        return Rect(
            rect.x + (rect.w - nextW) * 0.5f,
            rect.y + (rect.h - nextH) * 0.5f,
            nextW,
            nextH
        )
    }

    private fun toggleProgress(key: Any, enabled: Boolean): Float {
        val id = System.identityHashCode(key)
        val target = if (enabled) 1f else 0f
        val now = System.currentTimeMillis()
        val animation = toggleAnimations[id]
        if (animation == null) {
            toggleAnimations[id] = ToggleAnimation(target, target, now)
            return target
        }

        val current = toggleAnimationValue(animation, now)
        if (abs(animation.target - target) > 0.001f) {
            animation.start = current
            animation.target = target
            animation.startedAt = now
            return current
        }

        return current
    }

    private fun toggleAnimationValue(animation: ToggleAnimation, now: Long = System.currentTimeMillis()): Float {
        val progress = ((now - animation.startedAt).toFloat() / TOGGLE_ANIMATION_MS).coerceIn(0f, 1f)
        return lerp(animation.start, animation.target, cubicOut(progress))
    }

    private fun setSearchExpanded(expanded: Boolean) {
        val target = if (expanded) 1f else 0f
        if (abs(searchAnimationTarget - target) <= 0.001f) {
            return
        }

        searchAnimationStart = searchProgress()
        searchAnimationTarget = target
        searchAnimationStartedAt = System.currentTimeMillis()
    }

    private fun searchProgress(): Float {
        val progress = ((System.currentTimeMillis() - searchAnimationStartedAt).toFloat() /
            SEARCH_ANIMATION_MS).coerceIn(0f, 1f)
        return lerp(searchAnimationStart, searchAnimationTarget, cubicOut(progress))
    }

    private fun searchIconShake(): Float {
        if (searchIconClickedAt == 0L) {
            return 0f
        }

        val progress = ((System.currentTimeMillis() - searchIconClickedAt).toFloat() / SEARCH_SHAKE_MS)
            .coerceIn(0f, 1f)
        if (progress >= 1f) {
            return 0f
        }

        return sin(progress * Math.PI.toFloat() * 7f) * (1f - progress) * 2.4f
    }

    private fun bounceScaleOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        val inv = 1f - t
        return 1f - inv * inv * inv
    }

    private fun animationProgress(animation: SettingsPanelAnimation): Float =
        ((System.currentTimeMillis() - animation.startedAt).toFloat() / SETTINGS_ANIMATION_MS).coerceIn(0f, 1f)

    private fun applyMainBackgroundBlur(context: DrawContext) {
        runCatching {
            context.blurBeforeThisStratum()
            context.nextStratum()
        }
    }

    private fun animationRect(animation: SettingsPanelAnimation, target: Rect, progress: Float): Rect {
        val p = progress.coerceIn(0f, 1f)

        val move = expoOut(p)
        val collapse = cubicOut((p / 0.22f).coerceIn(0f, 1f))
        val expand = quintOut(((p - 0.14f) / 0.86f).coerceIn(0f, 1f))
        val compactH = min(animation.from.h, SETTINGS_COMPACT_H)
        val stripH = lerp(animation.from.h, compactH, collapse)

        return Rect(
            lerp(animation.from.x, target.x, move),
            lerp(animation.from.y, target.y, move),
            lerp(animation.from.w, target.w, expand),
            lerp(stripH, target.h, expand)
        )
    }

    private fun cubicOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        val inv = 1f - t
        return 1f - inv * inv * inv
    }

    private fun quintOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        val inv = 1f - t
        return 1f - inv * inv * inv * inv * inv
    }

    private fun expoOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        return if (t >= 1f) 1f else 1f - Math.pow(2.0, (-10f * t).toDouble()).toFloat()
    }

    private fun fadeColor(color: Int, factor: Float): Int {
        val alpha = ((color ushr 24) * factor.coerceIn(0f, 1f)).toInt().coerceIn(0, 255)
        return ExosGuiRenderer.withAlpha(color, alpha)
    }

    private fun lerp(from: Float, to: Float, progress: Float): Float =
        from + (to - from) * progress.coerceIn(0f, 1f)

    private data class Layout(
        val x: Float,
        val y: Float,
        val w: Float,
        val h: Float,
        val sidebarW: Float,
        val moduleW: Float,
        val scale: Float,
        val pad: Float,
        val cardH: Float
    )

    private data class SettingEntry(val value: Value<*>, val depth: Int)

    private data class SettingsPanelAnimation(
        val module: ClientModule,
        val from: Rect,
        val startedAt: Long
    )

    private data class AnimatedRect(val rect: Rect, val alpha: Float)

    private data class ToggleAnimation(
        var start: Float,
        var target: Float,
        var startedAt: Long
    )

    private data class ConfigAction(val type: ConfigActionType, val file: File?)

    private enum class ConfigActionType {
        SAVE,
        LOAD,
        DELETE
    }

    private enum class RangeDragSide {
        START,
        END
    }

    private enum class ClickGuiPage(val title: String, val id: String, val category: Category?) {
        COMBAT("Combat", "combat", Category.COMBAT),
        PLAYER("Player", "player", Category.PLAYER),
        MOVEMENT("Movement", "movement", Category.MOVEMENT),
        RENDER("Render", "render", Category.RENDER),
        MISC("Misc", "misc", Category.MISC),
        CONFIG("Config", "config", null),
        THEME("Theme", "theme", null),
        CLOUD("Cloud", "cloud", null)
    }

    private data class ChoiceOptionRect(val choice: Choice, val rect: Rect)
    private data class ChooseOptionRect(val choice: NamedChoice, val rect: Rect)
    private data class MultiChooseOptionRect(val choice: Any?, val rowRect: Rect, val checkboxRect: Rect)

    private data class Rect(val x: Float, val y: Float, val w: Float, val h: Float) {
        val right: Float get() = x + w
        val bottom: Float get() = y + h

        fun contains(mouseX: Int, mouseY: Int): Boolean =
            w > 0f && h > 0f && mouseX >= x && mouseX <= right && mouseY >= y && mouseY <= bottom

        companion object {
            val ZERO = Rect(0f, 0f, 0f, 0f)
        }
    }

    private companion object {
        private val STANDARD_PAGES = arrayOf(
            ClickGuiPage.COMBAT,
            ClickGuiPage.PLAYER,
            ClickGuiPage.MOVEMENT,
            ClickGuiPage.RENDER,
            ClickGuiPage.MISC
        )
        private val SPECIAL_PAGES = arrayOf(ClickGuiPage.CONFIG, ClickGuiPage.THEME, ClickGuiPage.CLOUD)
        private val pageIconCache = mutableMapOf<ClickGuiPage, Identifier?>()
        private var closeIconCache: Identifier? = null
        private var tickIconCache: Identifier? = null
        private var searchIconCache: Identifier? = null

        private const val MAIN_WIDTH_RATIO = 0.56f
        private const val MAIN_HEIGHT_RATIO = 0.74f
        private const val MAIN_MIN_W = 430f
        private const val MAIN_MAX_W = 680f
        private const val MAIN_MIN_H = 320f
        private const val MAIN_MAX_H = 540f
        private const val MAIN_REFERENCE_H = 420f
        private const val CATEGORY_ICON_SLOT = 38f
        private const val ROW_H = 32f
        private const val SETTINGS_PANEL_W = 280f
        private const val SETTINGS_PANEL_MIN_W = 214f
        private const val SETTINGS_PANEL_WIDTH_RATIO = 0.25f
        private const val SETTINGS_PANEL_GAP = 10f
        private const val SETTINGS_ANIMATION_MS = 360f
        private const val OPEN_ANIMATION_MS = 230f
        private const val CLOSE_ANIMATION_MS = 180f
        private const val OPEN_MIN_SCALE = 0.78f
        private const val SETTINGS_PAD = 18f
        private const val SETTINGS_HEADER_H = 89f
        private const val SETTINGS_BOTTOM_PAD = 22f
        private const val SETTINGS_MIN_H = 142f
        private const val SETTINGS_COMPACT_H = 31f
        private const val SEARCH_ANIMATION_MS = 180f
        private const val SEARCH_SHAKE_MS = 260f
        private const val MODULE_CARD_ANIMATION_MS = 220f
        private const val MODULE_CARD_DELAY_MS = 30f
        private const val MODULE_CARD_OFFSET_Y = 26f
        private const val MODULE_CARD_MIN_SCALE = 0.97f
        private const val TOGGLE_ANIMATION_MS = 160f
        private const val CHOICE_FRAME_PAD = 6f
        private const val CHOICE_BOX_H = 20f
        private const val CHOICE_BOX_GAP = 5f
        private const val MULTI_CHOOSE_OPTION_H = 20f
        private const val MULTI_CHOOSE_OPTION_GAP = 5f
        private const val MULTI_CHOOSE_COLUMN_GAP = 7f
        private const val MULTI_CHOOSE_OPTION_PAD = 7f
        private const val MULTI_CHOOSE_CHECKBOX_SIZE = 12f
        private const val MULTI_CHOOSE_TICK_SIZE = 8
        private const val MULTI_CHOOSE_TEXT_GAP = 5f
        private const val MULTI_CHOOSE_MIN_COLUMN_W = 92f
        private const val MULTI_CHOOSE_MIN_LABEL_W = 44f
        private const val MULTI_CHOOSE_TWO_COLUMN_READABLE_FACTOR = 1.2f
        private const val SLIDER_KNOB = 9f

        private val ACCENT: Int
            get() = ClientTheme.accentA
        private val ACCENT_TEXT: Int
            get() = ExosGuiRenderer.mix(ClientTheme.accentA, TEXT_PRIMARY, 0.45f)
        private val ACCENT_FILL: Int
            get() = ExosGuiRenderer.withAlpha(ClientTheme.accentA, 68)
        private val ACCENT_BORDER: Int
            get() = ExosGuiRenderer.withAlpha(ClientTheme.accentB, 204)
        private val TEXT_PRIMARY = 0xFFE8EAED.toInt()
        private val TEXT_ENABLED = 0xFFF7F3FF.toInt()
        private val TEXT_SECONDARY = 0xFF9AA3AF.toInt()
        private val TEXT_MUTED = 0xFF747D8A.toInt()
        private val SCREEN_DIM = 0x24000000
        private val SURFACE_0 = 0xB810141A.toInt()
        private val SURFACE_1 = 0xAE151A20.toInt()
        private val SIDEBAR_TOP = 0x3A141922
        private val SIDEBAR_BOTTOM = 0x340D1117
        private val CARD_FILL = 0xC9171C22.toInt()
        private val CARD_HOVER = 0xDD1B222B.toInt()
        private val CARD_SELECTED: Int
            get() = ExosGuiRenderer.mix(0xE5212230.toInt(), ExosGuiRenderer.withAlpha(ClientTheme.accentA, 229), 0.16f)
        private val CARD_ENABLED: Int
            get() = ExosGuiRenderer.mix(0xE01F2430.toInt(), ExosGuiRenderer.withAlpha(ClientTheme.accentA, 224), 0.13f)
        private val SETTINGS_SURFACE_0 = 0xF011151B.toInt()
        private val SETTINGS_SURFACE_1 = 0xF0161B23.toInt()
        private val INPUT_FILL = 0xAA10151C.toInt()
        private val GROUP_FILL = 0xB20E141B.toInt()
        private val GROUP_ENABLED_FILL: Int
            get() = ExosGuiRenderer.mix(0xB91B1726.toInt(), ExosGuiRenderer.withAlpha(ClientTheme.accentA, 185), 0.14f)
        private val CHOICE_FRAME_FILL = 0xCC090D13.toInt()
        private val CHOICE_SELECTED_FILL: Int
            get() = ExosGuiRenderer.withAlpha(ClientTheme.accentA, 102)
        private val SLIDER_TRACK = 0xFF303640.toInt()
        private val SLIDER_KNOB_FILL: Int
            get() = ExosGuiRenderer.mix(ClientTheme.accentB, TEXT_PRIMARY, 0.7f)
        private val PROFILE_FILL = 0x66191F27
        private val HOVER_FILL = 0x222A3340
        private val BORDER_SOFT = 0x553E4652
        private val BORDER_SOFT_STRONG = 0x774A5564
        private val DIVIDER = 0x223E4652
        private val DIVIDER_STRONG = 0x443E4652
    }
}
