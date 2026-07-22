/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
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
package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.config.AutoConfig
import net.ccbluex.liquidbounce.config.ConfigSystem
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.render.gui.drawSkinFace
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.client.markAsError
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.regular
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.CharacterEvent
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.resources.DefaultPlayerSkin
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.EnumMap
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Suppress("TooManyFunctions", "LargeClass")
class ClickGuiScreen : Screen(Component.literal("ClickGUI")) {

    private enum class ConfigActionType {
        SAVE,
        LOAD,
        DELETE
    }

    private data class ConfigAction(
        val rect: SilentRect,
        val type: ConfigActionType,
        val file: File? = null,
    )

    private data class ModuleHit(
        val module: ClientModule,
        val card: SilentRect,
        val settingsZone: SilentRect,
    )

    private var selectedPage = rememberedPage
    private var searchText = ""
    private var searchFocused = false
    private var moduleScroll = 0f
    private var themeScroll = 0f
    private var configScroll = 0f
    private var openedAt = 0L
    private var closing = false
    private var closingStartedAt = 0L
    private var closeScaleStart = 1f
    private var closeAlphaStart = 1f
    private var pendingDelete: File? = null
    private var searchRect = SilentRect.ZERO
    private var selectedModule: ClientModule? = null
    private var sideSettingsScroll = 0f
    private var sideTransitionStartedAt = 0L
    private val rememberedModules = EnumMap<Category, ClientModule>(Category::class.java)

    private val settings = SilentClickGuiSettings()
    private val moduleHits = mutableListOf<ModuleHit>()
    private val themeHits = mutableMapOf<Int, SilentRect>()
    private val configActions = mutableListOf<ConfigAction>()

    override fun init() {
        super.init()
        openedAt = System.currentTimeMillis()
        closing = false
        closingStartedAt = 0L
        closeScaleStart = 1f
        closeAlphaStart = 1f
        settings.clearTransient()
        searchFocused = false
        moduleScroll = moduleScroll.coerceAtLeast(0f)
        themeScroll = themeScroll.coerceAtLeast(0f)
        configScroll = configScroll.coerceAtLeast(0f)
        if (selectedPage.category != null) {
            repairSelectedModule(filteredModules())
        }
    }

    override fun extractBackground(graphics: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        // Keep vanilla's opaque screen background out; the ClickGUI draws its own translucent shell.
    }

    override fun extractRenderState(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        if (closing && closeFinished()) {
            super.onClose()
            return
        }

        runCatching {
            context.blurBeforeThisStratum()
            context.nextStratum()
        }

        val scale = visualScale()
        val alpha = visualAlpha()
        val transform = SilentClickGuiLayout.transform(width, height, scale)
        val localMouse = transform.screenToLocal(mouseX.toDouble(), mouseY.toDouble())

        settings.beginFrame()
        // Ensure ExosGuiRenderer can resolve this DrawContext from the compatibility PoseStack key.
        context.matrices
        val pose = context.pose()
        pose.pushMatrix()
        pose.translate(transform.centerX, transform.centerY)
        pose.scale(transform.totalScale, transform.totalScale)
        pose.translate(
            -SilentClickGuiLayout.REFERENCE_WIDTH * 0.5f,
            -SilentClickGuiLayout.REFERENCE_HEIGHT * 0.5f
        )

        try {
            renderShell(context, transform, localMouse, alpha)
        } finally {
            pose.popMatrix()
            settings.finishFrame()
        }
    }

    override fun mouseClicked(event: MouseButtonEvent, doubleClick: Boolean): Boolean {
        if (closing) {
            return true
        }

        if (settings.handleMouseBind(event)) {
            return true
        }

        val local = inputTransform().screenToLocal(event.x(), event.y())
        if (SilentClickGuiLayout.sidePanel.contains(local)) {
            val module = selectedModule
            if (module != null && SilentClickGuiLayout.sideHeader.contains(local)) {
                val header = SilentClickGuiLayout.sideHeader
                val toggle = SilentRect(header.right - 30f, header.y + 5f, 21f, 16f)
                val close = SilentRect(header.right - 10f, header.y + 3f, 10f, 20f)
                if (close.contains(local)) {
                    selectedModule = null
                    settings.clearTransient()
                    sideSettingsScroll = 0f
                } else if (toggle.contains(local) && !module.notActivatable && !module.disableActivation) {
                    module.enabled = !module.enabled
                }
                return true
            }
            if (SilentClickGuiLayout.settingsViewport.contains(local) && module != null) {
                return settings.handleClick(local.x, local.y, event.button())
            }
            return true
        }
        if (!SilentClickGuiLayout.mainPanel.contains(local)) {
            searchFocused = false
            return false
        }

        if (searchRect.contains(local) && searchSupported()) {
            searchFocused = true
            return true
        }

        searchFocused = false
        val button = event.button()
        SilentClickGuiPage.ORDER.forEachIndexed { index, page ->
            if (SilentClickGuiLayout.categoryRect(index).contains(local)) {
                selectPage(page)
                return true
            }
        }

        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT && button != GLFW.GLFW_MOUSE_BUTTON_RIGHT &&
            button != GLFW.GLFW_MOUSE_BUTTON_MIDDLE) {
            return false
        }

        return when {
            selectedPage.category != null -> handleModuleClick(local, button)
            selectedPage == SilentClickGuiPage.THEME -> handleThemeClick(local, button)
            selectedPage == SilentClickGuiPage.CONFIG -> handleConfigClick(local, button)
            selectedPage == SilentClickGuiPage.CLOUD -> SilentClickGuiLayout.content.contains(local)
            else -> false
        }
    }

    override fun mouseReleased(event: MouseButtonEvent): Boolean {
        settings.clearDrag()
        return super.mouseReleased(event)
    }

    override fun mouseDragged(event: MouseButtonEvent, dx: Double, dy: Double): Boolean {
        if (settings.updateDragging(inputTransform().screenToLocal(event.x(), event.y()).x)) {
            return true
        }

        return super.mouseDragged(event, dx, dy)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, horizontal: Double, vertical: Double): Boolean {
        if (closing) {
            return true
        }

        val local = inputTransform().screenToLocal(mouseX, mouseY)
        if (SilentClickGuiLayout.settingsViewport.contains(local) && selectedModule != null && selectedPage.category != null) {
            val entries = settings.visibleSettings(selectedModule!!)
            val maxScroll = SilentClickGuiSettingsMath.maxScroll(settings.contentHeight(entries), SilentClickGuiLayout.settingsViewport.h)
            sideSettingsScroll = (sideSettingsScroll + (-vertical * 18.0).toFloat()).coerceIn(0f, maxScroll)
            return true
        }
        if (!SilentClickGuiLayout.content.contains(local)) {
            return false
        }

        val amount = (-vertical * 18.0).toFloat()
        return when {
            selectedPage.category != null -> scrollModules(local, amount)
            selectedPage == SilentClickGuiPage.THEME -> {
                val maxScroll = max(0f, SilentClickGuiLayout.themeContentHeight(ClickGuiTheme.COLORS.size) -
                    SilentClickGuiLayout.content.h)
                themeScroll = (themeScroll + amount).coerceIn(0f, maxScroll)
                true
            }
            selectedPage == SilentClickGuiPage.CONFIG -> {
                val maxScroll = max(0f, configListHeight(filteredConfigs().size) - configListViewport().h)
                configScroll = (configScroll + amount).coerceIn(0f, maxScroll)
                true
            }
            else -> true
        }
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (settings.handleKey(event)) {
            return true
        }

        if (event.isEscape()) {
            startClosing()
            return true
        }

        if (searchFocused && searchSupported()) {
            when (event.key()) {
                GLFW.GLFW_KEY_BACKSPACE -> {
                    if (searchText.isNotEmpty()) {
                        searchText = searchText.dropLast(1)
                        resetSearchScroll()
                    }
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
        if (!searchFocused || !searchSupported() || !event.isAllowedChatCharacter()) {
            return false
        }

        searchText += event.codepointAsString()
        resetSearchScroll()
        return true
    }

    override fun isPauseScreen(): Boolean = false

    override fun onClose() {
        if (!closing) {
            startClosing()
            return
        }

        if (closeFinished()) {
            super.onClose()
        }
    }

    private fun renderShell(context: DrawContext, transform: SilentTransform, mouse: SilentPoint, alpha: Float) {
        renderRootSurface(context, alpha)
        renderSidebar(context, mouse, alpha)
        renderHeader(context, alpha)

        when (selectedPage) {
            SilentClickGuiPage.CONFIG -> renderConfigPage(context, transform, mouse, alpha)
            SilentClickGuiPage.THEME -> renderThemePage(context, transform, mouse, alpha)
            SilentClickGuiPage.CLOUD -> renderCloudPage(context, transform, alpha)
            else -> renderModulePage(context, transform, mouse, alpha)
        }
        renderSideSettings(context, mouse, alpha)
    }

    private fun renderRootSurface(context: DrawContext, alpha: Float) {
        val matrices = context.matrices
        ExosGuiRenderer.roundedGradientRect(
            matrices,
            0f,
            0f,
            SilentClickGuiLayout.mainPanel.w,
            SilentClickGuiLayout.mainPanel.h,
            8f,
            fade(0xDC090B10.toInt(), alpha),
            fade(0xD611141B.toInt(), alpha),
            fade(0xDA080A0F.toInt(), alpha),
            fade(0xD70D0F15.toInt(), alpha)
        )
        ExosGuiRenderer.roundedOutline(matrices, 0f, 0f, SilentClickGuiLayout.mainPanel.w,
            SilentClickGuiLayout.mainPanel.h, 8f, fade(0x24FFFFFF, alpha), 0.75f)
        ExosGuiRenderer.roundedRect(matrices, SilentClickGuiLayout.sidebar.x, SilentClickGuiLayout.sidebar.y,
            SilentClickGuiLayout.sidebar.w, SilentClickGuiLayout.sidebar.h, 6f, fade(0x23000000, alpha))
        ExosGuiRenderer.roundedOutline(matrices, SilentClickGuiLayout.sidebar.x, SilentClickGuiLayout.sidebar.y,
            SilentClickGuiLayout.sidebar.w, SilentClickGuiLayout.sidebar.h, 6f, fade(0x16FFFFFF, alpha), 0.65f)
        ExosGuiRenderer.roundedRect(matrices, SilentClickGuiLayout.header.x, SilentClickGuiLayout.header.y,
            SilentClickGuiLayout.header.w, SilentClickGuiLayout.header.h, 6f, fade(0x18FFFFFF, alpha))
        ExosGuiRenderer.roundedOutline(matrices, SilentClickGuiLayout.header.x, SilentClickGuiLayout.header.y,
            SilentClickGuiLayout.header.w, SilentClickGuiLayout.header.h, 6f, fade(0x13FFFFFF, alpha), 0.55f)
        val side = SilentClickGuiLayout.sidePanel
        ExosGuiRenderer.roundedGradientRect(matrices, side.x, side.y, side.w, side.h, 8f,
            fade(0xE20B0F17.toInt(), alpha), fade(0xE4141922.toInt(), alpha),
            fade(0xE00A0E16.toInt(), alpha), fade(0xE10D121B.toInt(), alpha))
        ExosGuiRenderer.roundedOutline(matrices, side.x, side.y, side.w, side.h, 8f, fade(0x28FFFFFF, alpha), 0.75f)
        val viewport = SilentClickGuiLayout.settingsViewport
        ExosGuiRenderer.roundedRect(matrices, viewport.x, viewport.y, viewport.w, viewport.h, 5f, fade(0x18000000, alpha))
    }

    private fun renderSidebar(context: DrawContext, mouse: SilentPoint, alpha: Float) {
        // Future logo slot: intentionally blank.
        SilentClickGuiPage.ORDER.forEachIndexed { index, page ->
            val rect = SilentClickGuiLayout.categoryRect(index)
            val active = selectedPage == page
            val hovered = rect.contains(mouse)
            val fill = when {
                active -> colorWithAlpha(ClientTheme.accentA, 38, alpha)
                hovered -> fade(0x12FFFFFF, alpha)
                else -> 0
            }
            if (fill != 0) {
                ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f, fill)
            }

            val textColor = fade(if (active) ClientTheme.accentA else 0x9FFFFFFF.toInt(), alpha)
            ExosGuiRenderer.clickGuiIcon(context.matrices, SilentClickGuiIcons.glyphFor(page), rect.x + 4f, rect.y + 4.2f,
                textColor, 8f)
            ExosGuiRenderer.clickGuiBody(context.matrices, page.title, rect.x + 18f, rect.y + 6.2f,
                textColor, 6.3f)
        }

        renderFooter(context, alpha)
    }

    private fun renderFooter(context: DrawContext, alpha: Float) {
        val footer = SilentClickGuiLayout.footer
        ExosGuiRenderer.roundedRect(context.matrices, footer.x, footer.y, footer.w, footer.h, 6f,
            fade(0x16000000, alpha))
        ExosGuiRenderer.roundedOutline(context.matrices, footer.x, footer.y, footer.w, footer.h, 6f,
            fade(0x12FFFFFF, alpha), 0.55f)

        val face = SilentRect(footer.x + 4f, footer.y + 4f, 13f, 13f)
        val drewFace = localSkinId()
            ?.let { context.drawSkinFace(it, face.x, face.y, face.w, fade(0xFFFFFFFF.toInt(), alpha)) } == true
        if (!drewFace) {
            ExosGuiRenderer.roundedGradientRect(context.matrices, face.x, face.y, face.w, face.h, 3f,
                colorWithAlpha(ClientTheme.accentA, 170, alpha),
                colorWithAlpha(ClientTheme.accentB, 170, alpha),
                colorWithAlpha(ClientTheme.accentB, 170, alpha),
                colorWithAlpha(ClientTheme.accentA, 170, alpha))
        }
        ExosGuiRenderer.roundedOutline(context.matrices, face.x, face.y, face.w, face.h, 3f,
            fade(0x20FFFFFF, alpha), 0.5f)

        val name = mc.user.name.ifBlank { "Player" }
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(name, footer.w - 23f, 5.8f),
            footer.x + 21f, footer.y + 4.5f, fade(0xE8FFFFFF.toInt(), alpha), 5.8f)
        ExosGuiRenderer.clickGuiBody(context.matrices, "Local", footer.x + 21f, footer.y + 12.3f,
            fade(0x78FFFFFF, alpha), 4.9f)
    }

    private fun renderHeader(context: DrawContext, alpha: Float) {
        val page = selectedPage
        val icon = SilentClickGuiIcons.glyphFor(page)
        val pill = SilentRect(84f, 10f, min(76f, 25f + ExosGuiRenderer.clickGuiBodyWidth(page.title, 6.1f)), 14f)
        drawPill(context, pill, alpha)
        ExosGuiRenderer.clickGuiIcon(context.matrices, icon, pill.x + 5f, pill.y + 3.1f,
            fade(ClientTheme.accentA, alpha), 7.2f)
        ExosGuiRenderer.clickGuiBody(context.matrices, page.title, pill.x + 18f, pill.y + 4f,
            fade(0xEFFFFFFF.toInt(), alpha), 6.4f)

        val status = statusText()
        val statusWidth = min(48f, max(28f, ExosGuiRenderer.clickGuiBodyWidth(status, 5.6f) + 10f))
        val statusRect = SilentRect(pill.right + 5f, 10f, statusWidth, 14f)
        drawPill(context, statusRect, alpha)
        ExosGuiRenderer.clickGuiBody(context.matrices, status,
            statusRect.x + (statusRect.w - ExosGuiRenderer.clickGuiBodyWidth(status, 6f)) * 0.5f,
            statusRect.y + 4.2f, fade(0xBFFFFFFF.toInt(), alpha), 6f)

        if (searchSupported()) {
            searchRect = SilentRect(244f, 10f, 79f, 14f)
            val focusedFill = if (searchFocused) colorWithAlpha(ClientTheme.accentA, 34, alpha) else fade(0x16000000, alpha)
            ExosGuiRenderer.roundedRect(context.matrices, searchRect.x, searchRect.y, searchRect.w, searchRect.h, 5f,
                focusedFill)
            ExosGuiRenderer.roundedOutline(context.matrices, searchRect.x, searchRect.y, searchRect.w, searchRect.h, 5f,
                fade(if (searchFocused) ClientTheme.accentA else 0x18FFFFFF, alpha), 0.55f)
            ExosGuiRenderer.clickGuiBody(context.matrices, "Search", searchRect.x + 7f, searchRect.y + 4.2f,
                fade(0x82FFFFFF.toInt(), alpha), 5.8f)
            if (searchText.isNotEmpty()) {
                val query = truncate(searchText, 39f, 5.6f)
                ExosGuiRenderer.clickGuiBody(context.matrices, query, searchRect.right -
                    ExosGuiRenderer.clickGuiBodyWidth(query, 5.8f) - 7f, searchRect.y + 4.2f,
                    fade(0xDFFFFFFF.toInt(), alpha), 5.8f)
            }
        } else {
            searchRect = SilentRect.ZERO
        }
    }

    private fun renderModulePage(
        context: DrawContext,
        transform: SilentTransform,
        mouse: SilentPoint,
        alpha: Float,
    ) {
        val modules = filteredModules()
        repairSelectedModule(modules)
        val contentHeight = SilentClickGuiLayout.moduleGridContentHeight(modules.size)
        val maxScroll = max(0f, contentHeight - SilentClickGuiLayout.content.h)
        moduleScroll = moduleScroll.coerceIn(0f, maxScroll)

        moduleHits.clear()
        withLocalScissor(context, SilentClickGuiLayout.content) {
            modules.forEachIndexed { index, module ->
                val card = SilentClickGuiLayout.moduleCardRect(index, moduleScroll)
                if (!card.intersects(SilentClickGuiLayout.content)) {
                    return@forEachIndexed
                }

                renderModuleCard(context, module, card, mouse, alpha)
            }

            if (maxScroll > 0.5f) {
                val content = SilentClickGuiLayout.content
                val thumbHeight = (content.h * content.h / contentHeight).coerceIn(18f, content.h)
                val thumbY = content.y + (content.h - thumbHeight) * (moduleScroll / maxScroll)
                ExosGuiRenderer.roundedRect(context.matrices, content.right - 2.2f, thumbY, 1.6f, thumbHeight,
                    0.8f, fade(0x70FFFFFF, alpha))
            }
        }
    }

    private fun renderModuleCard(
        context: DrawContext,
        module: ClientModule,
        card: SilentRect,
        mouse: SilentPoint,
        alpha: Float,
    ) {
        val enabled = module.enabled
        val selected = selectedModule === module
        val hovered = card.contains(mouse)
        val fill = when {
            enabled -> colorWithAlpha(ClientTheme.accentA, 30, alpha)
            hovered -> fade(0x17FFFFFF, alpha)
            else -> fade(0x0FFFFFFF, alpha)
        }

        ExosGuiRenderer.roundedRect(context.matrices, card.x, card.y, card.w, card.h, 6f, fill)
        ExosGuiRenderer.roundedOutline(context.matrices, card.x, card.y, card.w, card.h, 5f,
            fade(if (selected) ClientTheme.accentA else if (enabled) ClientTheme.accentA else 0x14FFFFFF, alpha),
            if (selected) 0.9f else 0.55f)
        if (enabled) {
            ExosGuiRenderer.roundedRect(context.matrices, card.x, card.y, 2f, card.h, 1f,
                colorWithAlpha(ClientTheme.accentA, 170, alpha))
        }

        val nameColor = fade(if (enabled) 0xFFFFFFFF.toInt() else 0xC8FFFFFF.toInt(), alpha)
        val zone = SilentClickGuiLayout.moduleSettingsZone(card)
        val nameMax = zone.x - card.x - 9f
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(module.name, nameMax, 6f),
            card.x + 6f, card.y + 4f, nameColor, 6f)
        val bind = module.bind.keyName
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(bind, 22f, 4.4f),
            card.right - ExosGuiRenderer.clickGuiBodyWidth(truncate(bind, 22f, 4.4f), 4.4f) - zone.w - 8f,
            card.y + 5f, fade(0x75FFFFFF, alpha), 4.4f)
        val descRect = SilentRect(card.x + 6f, card.y + 15f, card.w - 12f, 9f)
        withLocalScissor(context, descRect) {
            ExosGuiRenderer.clickGuiBody(context.matrices, truncate(moduleDescription(module), descRect.w, 4.4f),
                descRect.x, descRect.y, fade(0x72FFFFFF, alpha), 4.4f)
        }
        ExosGuiRenderer.clickGuiIcon(context.matrices, if (selected) "<" else ">", zone.x + 4f, zone.y + 4.6f,
            fade(if (selected) ClientTheme.accentA else 0x88FFFFFF.toInt(), alpha), 5.5f)
        moduleHits += ModuleHit(module, card, zone)
    }

    private fun renderSideSettings(context: DrawContext, mouse: SilentPoint, alpha: Float) {
        val side = SilentClickGuiLayout.sidePanel
        val header = SilentClickGuiLayout.sideHeader
        val viewport = SilentClickGuiLayout.settingsViewport
        val module = selectedModule.takeIf { selectedPage.category != null && it != null && it.category == selectedPage.category }
        val title = module?.name ?: "Module Settings"
        val subtitle = module?.let { selectedPage.title } ?: "Select a module"
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(title, 70f, 6.2f), side.x + 8f, header.y + 6f,
            fade(0xE8FFFFFF.toInt(), alpha), 6.2f)
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(subtitle, 64f, 4.7f), side.x + 8f, header.y + 17f,
            fade(0x78FFFFFF, alpha), 4.7f)
        if (module != null) {
            val toggle = SilentRect(header.right - 28f, header.y + 8f, 18f, 9f)
            drawSwitch(context, toggle.x, toggle.y, module.enabled, alpha)
            val close = SilentRect(header.right - 8f, header.y + 7f, 8f, 12f)
            ExosGuiRenderer.clickGuiBody(context.matrices, "×", close.x, close.y, fade(0xA8FFFFFF.toInt(), alpha), 6f)
        } else {
            ExosGuiRenderer.clickGuiIcon(context.matrices, "X", side.x + 53f, side.y + 78f,
                fade(0x50FFFFFF, alpha), 12f)
            ExosGuiRenderer.clickGuiBody(context.matrices, "Select a module",
                side.x + 8f, side.y + 108f, fade(0x88FFFFFF.toInt(), alpha), 5.6f)
        }

        if (module == null) return
        val entries = settings.visibleSettings(module)
        val contentHeight = settings.contentHeight(entries)
        sideSettingsScroll = SilentClickGuiSettingsMath.clampScroll(sideSettingsScroll, contentHeight, viewport.h)
        val transition = SilentClickGuiAnimation.expoOut(
            (System.currentTimeMillis() - sideTransitionStartedAt).toFloat() / SilentClickGuiAnimation.SIDE_CONTENT_MS
        )
        withLocalScissor(context, viewport) {
            val pose = context.pose()
            pose.pushMatrix()
            pose.translate((1f - transition) * -7f, 0f)
            try {
                settings.render(context, viewport, entries, sideSettingsScroll, mouse.x, mouse.y, alpha * transition)
            } finally {
                pose.popMatrix()
            }
        }
    }

    private fun drawSwitch(context: DrawContext, x: Float, y: Float, enabled: Boolean, alpha: Float) {
        ExosGuiRenderer.roundedRect(context.matrices, x, y, 18f, 9f, 4f,
            fade(if (enabled) ClientTheme.accentA else 0x35000000, alpha))
        ExosGuiRenderer.roundedRect(context.matrices, x + if (enabled) 10f else 1f, y + 1.5f, 6f, 6f, 3f,
            fade(0xFFFFFFFF.toInt(), alpha))
    }

    private fun renderThemePage(context: DrawContext, transform: SilentTransform, mouse: SilentPoint, alpha: Float) {
        val contentHeight = SilentClickGuiLayout.themeContentHeight(ClickGuiTheme.COLORS.size)
        val maxScroll = max(0f, contentHeight - SilentClickGuiLayout.content.h)
        themeScroll = themeScroll.coerceIn(0f, maxScroll)
        themeHits.clear()

        withLocalScissor(context, SilentClickGuiLayout.content) {
            ClickGuiTheme.COLORS.indices.forEach { index ->
                val rect = SilentClickGuiLayout.themeBoxRect(index, themeScroll)
                if (!rect.intersects(SilentClickGuiLayout.content)) {
                    return@forEach
                }
                themeHits[index] = rect
                val (accentA, accentB) = ClickGuiTheme.COLORS[index]
                val hovered = rect.contains(mouse)
                ExosGuiRenderer.roundedGradientRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
                    fade(accentA, alpha), fade(accentB, alpha), fade(accentB, alpha), fade(accentA, alpha))
                ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
                    fade(if (index == ClickGuiTheme.selected) 0xFFFFFFFF.toInt() else 0x24FFFFFF, alpha),
                    if (index == ClickGuiTheme.selected) 1.25f else 0.65f)
                if (hovered) {
                    ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
                        fade(0x18FFFFFF, alpha))
                }

                val name = ClickGuiTheme.NAMES.getOrElse(index) { "Theme ${index + 1}" }
                val desc = ClickGuiTheme.DESCRIPTIONS.getOrElse(index) { "" }
                ExosGuiRenderer.clickGuiBody(context.matrices, truncate(name, rect.w, 5.8f), rect.x,
                    rect.bottom + 3f, fade(0xE6FFFFFF.toInt(), alpha), 5.8f)
                if (desc.isNotEmpty()) {
                    ExosGuiRenderer.clickGuiBody(context.matrices, truncate(desc, rect.w, 4.5f), rect.x,
                        rect.bottom + 10.2f, fade(0x78FFFFFF, alpha), 4.5f)
                }
            }
        }
    }

    private fun renderConfigPage(context: DrawContext, transform: SilentTransform, mouse: SilentPoint, alpha: Float) {
        configActions.clear()
        val content = SilentClickGuiLayout.content
        val saveRect = SilentRect(content.x + 6f, content.y + 6f, 66f, 15f)
        configActions += ConfigAction(saveRect, ConfigActionType.SAVE)
        drawButton(context, saveRect, "Save", saveRect.contains(mouse), alpha)

        val configs = filteredConfigs()
        val viewport = configListViewport()
        val maxScroll = max(0f, configListHeight(configs.size) - viewport.h)
        configScroll = configScroll.coerceIn(0f, maxScroll)

        withLocalScissor(context, viewport) {
            if (configs.isEmpty()) {
                ExosGuiRenderer.clickGuiBody(context.matrices, "No local configs", viewport.x + 6f, viewport.y + 8f,
                    fade(0x88FFFFFF.toInt(), alpha), 6.2f)
            }

            configs.forEachIndexed { index, file ->
                val rect = configCardRect(index, configScroll)
                if (!rect.intersects(viewport)) {
                    return@forEachIndexed
                }
                renderConfigCard(context, file, rect, mouse, alpha)
            }
        }
    }

    private fun renderConfigCard(context: DrawContext, file: File, rect: SilentRect, mouse: SilentPoint, alpha: Float) {
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f, fade(0x12FFFFFF, alpha))
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 6f,
            fade(0x15FFFFFF, alpha), 0.55f)
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(file.nameWithoutExtension, rect.w - 10f, 6f),
            rect.x + 6f, rect.y + 5f, fade(0xE6FFFFFF.toInt(), alpha), 6f)
        val detail = "${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ROOT).format(Date(file.lastModified()))}  " +
            "${max(1L, file.length() / 1024L)} KB"
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(detail, rect.w - 10f, 4.8f),
            rect.x + 6f, rect.y + 14f, fade(0x74FFFFFF, alpha), 4.8f)

        val deletePending = pendingDelete?.absolutePath == file.absolutePath
        val delete = SilentRect(rect.right - 37f, rect.bottom - 14f, 31f, 10f)
        val load = SilentRect(delete.x - 35f, delete.y, 30f, 10f)
        configActions += ConfigAction(load, ConfigActionType.LOAD, file)
        configActions += ConfigAction(delete, ConfigActionType.DELETE, file)
        drawMiniButton(context, load, "Load", load.contains(mouse), alpha)
        drawMiniButton(context, delete, if (deletePending) "Confirm" else "Delete", delete.contains(mouse), alpha)
    }

    private fun renderCloudPage(context: DrawContext, transform: SilentTransform, alpha: Float) {
        withLocalScissor(context, SilentClickGuiLayout.content) {
            val centerX = SilentClickGuiLayout.content.x + SilentClickGuiLayout.content.w * 0.5f
            val centerY = SilentClickGuiLayout.content.y + SilentClickGuiLayout.content.h * 0.43f
            ExosGuiRenderer.clickGuiIcon(context.matrices, SilentClickGuiIcons.glyphFor(SilentClickGuiPage.CLOUD),
                centerX - 5f, centerY - 18f, fade(ClientTheme.accentA, alpha), 12f)
            ExosGuiRenderer.clickGuiBody(context.matrices, "Nothing Found",
                centerX - ExosGuiRenderer.clickGuiBodyWidth("Nothing Found", 7f) * 0.5f,
                centerY, fade(0xDFFFFFFF.toInt(), alpha), 7f)
            val detail = "Cloud configs are not connected in this build"
            ExosGuiRenderer.clickGuiBody(context.matrices, detail,
                centerX - ExosGuiRenderer.clickGuiBodyWidth(detail, 5.2f) * 0.5f,
                centerY + 10f, fade(0x7AFFFFFF, alpha), 5.2f)
        }
    }

    private fun handleModuleClick(local: SilentPoint, button: Int): Boolean {
        if (SilentClickGuiLayout.settingsViewport.contains(local) && settings.handleClick(local.x, local.y, button)) {
            return true
        }

        moduleHits.asReversed().forEach { hit ->
            if (hit.card.contains(local)) {
                when (button) {
                    GLFW.GLFW_MOUSE_BUTTON_LEFT -> {
                        if (hit.settingsZone.contains(local)) {
                            selectModule(hit.module)
                        } else if (!hit.module.notActivatable && !hit.module.disableActivation) {
                            hit.module.enabled = !hit.module.enabled
                        }
                    }
                    GLFW.GLFW_MOUSE_BUTTON_RIGHT -> selectModule(hit.module)
                    GLFW.GLFW_MOUSE_BUTTON_MIDDLE -> settings.startModuleBind(hit.module)
                }
                return true
            }
        }

        return SilentClickGuiLayout.content.contains(local)
    }

    private fun handleThemeClick(local: SilentPoint, button: Int): Boolean {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return SilentClickGuiLayout.content.contains(local)
        }

        val entry = themeHits.entries.firstOrNull { (_, rect) -> rect.contains(local) } ?: return false
        ClickGuiTheme.selected = entry.key
        return true
    }

    private fun handleConfigClick(local: SilentPoint, button: Int): Boolean {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return SilentClickGuiLayout.content.contains(local)
        }

        val action = configActions.asReversed().firstOrNull { it.rect.contains(local) } ?: return false
        when (action.type) {
            ConfigActionType.SAVE -> saveCurrentConfig()
            ConfigActionType.LOAD -> action.file?.let(::loadConfig)
            ConfigActionType.DELETE -> action.file?.let(::requestDeleteConfig)
        }
        return true
    }

    private fun scrollModules(local: SilentPoint, amount: Float): Boolean {
        val modules = filteredModules()
        val maxScroll = max(0f, SilentClickGuiLayout.moduleGridContentHeight(modules.size) - SilentClickGuiLayout.content.h)
        moduleScroll = (moduleScroll + amount).coerceIn(0f, maxScroll)
        return true
    }

    private fun selectPage(page: SilentClickGuiPage) {
        if (selectedPage == page) {
            return
        }

        selectedPage = page
        rememberedPage = page
        moduleScroll = 0f
        themeScroll = 0f
        configScroll = 0f
        pendingDelete = null
        searchFocused = false
        settings.clearTransient()
        selectedModule = null
        sideSettingsScroll = 0f
        if (page.category != null) {
            repairSelectedModule(modulesFor(page.category))
        }
    }

    private fun selectModule(module: ClientModule?) {
        if (module == null || selectedModule === module) return
        selectedModule = module
        module.category?.let { rememberedModules[it] = module }
        sideSettingsScroll = 0f
        sideTransitionStartedAt = System.currentTimeMillis()
        settings.clearTransient()
    }

    private fun repairSelectedModule(modules: List<ClientModule>) {
        val category = selectedPage.category ?: run {
            selectedModule = null
            return
        }
        val remembered = rememberedModules[category]
        val next = when {
            remembered != null && remembered in modules -> remembered
            selectedModule != null && selectedModule in modules -> selectedModule
            else -> modules.firstOrNull()
        }
        if (next !== selectedModule) selectModule(next)
    }

    private fun filteredModules(): List<ClientModule> {
        val category = selectedPage.category ?: return emptyList()
        val query = searchText.trim()
        return modulesFor(category).filter { module ->
            query.isEmpty() || module.name.contains(query, ignoreCase = true) ||
                moduleDescription(module).contains(query, ignoreCase = true)
        }
    }

    private fun modulesFor(category: Category): List<ClientModule> =
        ModuleManager.filter { it.category == category && !it.hidden }

    private fun filteredConfigs(): List<File> {
        val query = searchText.trim()
        return ConfigSystem.userConfigsFolder
            .listFiles { file -> file.isFile && file.extension.equals("json", ignoreCase = true) }
            ?.filter { query.isEmpty() || fileMatchesSearch(it, query) }
            ?.sortedBy { it.nameWithoutExtension.lowercase(Locale.ROOT) }
            ?: emptyList()
    }

    private fun fileMatchesSearch(file: File, query: String): Boolean =
        file.nameWithoutExtension.contains(query, ignoreCase = true)

    private fun saveCurrentConfig() {
        ConfigSystem.userConfigsFolder.mkdirs()
        val stamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.ROOT).format(Date())
        val file = ConfigSystem.userConfigsFolder.resolve("config-$stamp.json")
        runCatching {
            file.createNewFile()
            file.bufferedWriter().use { AutoConfig.serializeAutoConfig(it) }
        }.onSuccess {
            configScroll = 0f
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

    private fun requestDeleteConfig(file: File) {
        if (pendingDelete?.absolutePath != file.absolutePath) {
            pendingDelete = file
            chat(regular("Click delete again to confirm ${file.nameWithoutExtension}"))
            return
        }

        deleteConfig(file)
    }

    private fun deleteConfig(file: File) {
        runCatching {
            check(file.exists() && file.delete()) { "delete failed" }
        }.onSuccess {
            pendingDelete = null
            configScroll = 0f
            chat(regular("Deleted config ${file.nameWithoutExtension}"))
        }.onFailure {
            chat(markAsError("Failed to delete config ${file.nameWithoutExtension}"))
        }
    }

    private fun resetSearchScroll() {
        moduleScroll = 0f
        configScroll = 0f
        if (selectedPage.category != null) repairSelectedModule(filteredModules())
    }

    private fun searchSupported(): Boolean =
        selectedPage.category != null || selectedPage == SilentClickGuiPage.CONFIG

    private fun statusText(): String = when (selectedPage) {
        SilentClickGuiPage.THEME -> "${ClickGuiTheme.selected + 1}/${ClickGuiTheme.COLORS.size}"
        SilentClickGuiPage.CONFIG -> filteredConfigs().size.toString()
        SilentClickGuiPage.CLOUD -> "Nothing Found"
        else -> {
            val modules = filteredModules()
            "${modules.count { it.enabled }}/${modules.size}"
        }
    }

    private fun moduleDescription(module: ClientModule): String =
        runCatching { module.description.get() ?: "" }.getOrDefault("")

    private fun drawPill(context: DrawContext, rect: SilentRect, alpha: Float) {
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f, fade(0x18000000, alpha))
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f,
            fade(0x14FFFFFF, alpha), 0.55f)
    }

    private fun drawButton(context: DrawContext, rect: SilentRect, label: String, hovered: Boolean, alpha: Float) {
        val fill = if (hovered) colorWithAlpha(ClientTheme.accentA, 44, alpha) else fade(0x1AFFFFFF, alpha)
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f, fill)
        ExosGuiRenderer.roundedOutline(context.matrices, rect.x, rect.y, rect.w, rect.h, 5f,
            fade(if (hovered) ClientTheme.accentA else 0x18FFFFFF, alpha), 0.6f)
        ExosGuiRenderer.clickGuiBody(context.matrices, label,
            rect.x + (rect.w - ExosGuiRenderer.clickGuiBodyWidth(label, 5.8f)) * 0.5f,
            rect.y + 4.7f, fade(0xE6FFFFFF.toInt(), alpha), 5.8f)
    }

    private fun drawMiniButton(context: DrawContext, rect: SilentRect, label: String, hovered: Boolean, alpha: Float) {
        val fill = if (hovered) colorWithAlpha(ClientTheme.accentA, 36, alpha) else fade(0x17000000, alpha)
        ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y, rect.w, rect.h, 4f, fill)
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(label, rect.w - 4f, 4.5f),
            rect.x + 3f, rect.y + 3f, fade(0xD8FFFFFF.toInt(), alpha), 4.5f)
    }

    private fun configListViewport(): SilentRect =
        SilentRect(SilentClickGuiLayout.content.x, SilentClickGuiLayout.content.y + 27f,
            SilentClickGuiLayout.content.w, SilentClickGuiLayout.content.h - 27f)

    private fun configListHeight(count: Int): Float =
        8f + count * 42f + 8f

    private fun configCardRect(index: Int, scroll: Float): SilentRect =
        SilentRect(SilentClickGuiLayout.content.x + 6f,
            configListViewport().y + 8f + index * 42f - scroll,
            SilentClickGuiLayout.content.w - 12f,
            36f)

    private fun localSkinId(): Identifier? {
        val player = mc.player as? AbstractClientPlayer ?: return null
        val skin = runCatching { player.skin }.getOrNull()
        val skinId = skin?.takeIf { it.secure() }?.body()?.id()
        return skinId
            ?.takeUnless { it == MissingTextureAtlasSprite.getLocation() }
            ?: DefaultPlayerSkin.get(player.uuid).body().id()
    }

    private fun inputTransform(): SilentTransform =
        SilentClickGuiLayout.transform(width, height, visualScale())

    private fun startClosing() {
        if (closing) {
            return
        }

        closeScaleStart = visualScale()
        closeAlphaStart = visualAlpha()
        closing = true
        closingStartedAt = System.currentTimeMillis()
        searchFocused = false
        settings.clearTransient()
    }

    private fun visualScale(): Float {
        val now = System.currentTimeMillis()
        if (!closing) {
            return SilentClickGuiAnimation.openScale(now - openedAt)
        }

        return SilentClickGuiAnimation.closeValue(
            closeScaleStart,
            SilentClickGuiAnimation.MIN_SCALE,
            now - closingStartedAt,
            SilentClickGuiAnimation.CLOSE_MS
        )
    }

    private fun visualAlpha(): Float {
        val now = System.currentTimeMillis()
        if (!closing) {
            return SilentClickGuiAnimation.openAlpha(now - openedAt)
        }

        return SilentClickGuiAnimation.closeValue(
            closeAlphaStart,
            0f,
            now - closingStartedAt,
            SilentClickGuiAnimation.CLOSE_MS
        )
    }

    private fun closeFinished(): Boolean =
        System.currentTimeMillis() - closingStartedAt >= SilentClickGuiAnimation.CLOSE_MS

    private inline fun withLocalScissor(
        context: DrawContext,
        rect: SilentRect,
        block: () -> Unit,
    ) {
        if (rect.w <= 0f || rect.h <= 0f ||
            !rect.x.isFinite() || !rect.y.isFinite() || !rect.right.isFinite() || !rect.bottom.isFinite()) {
            return
        }

        context.enableScissor(
            floor(rect.x).toInt(),
            floor(rect.y).toInt(),
            ceil(rect.right).toInt(),
            ceil(rect.bottom).toInt()
        )
        try {
            block()
        } finally {
            context.disableScissor()
        }
    }

    private fun truncate(text: String, maxWidth: Float, size: Float): String {
        if (ExosGuiRenderer.clickGuiBodyWidth(text, size) <= maxWidth) {
            return text
        }

        var output = text
        while (output.length > 1 && ExosGuiRenderer.clickGuiBodyWidth("$output...", size) > maxWidth) {
            output = output.dropLast(1)
        }
        return "$output..."
    }

    private fun fade(color: Int, alpha: Float): Int {
        val baseAlpha = (color ushr 24) and 0xFF
        val nextAlpha = (baseAlpha * alpha.coerceIn(0f, 1f)).roundToInt().coerceIn(0, 255)
        return (nextAlpha shl 24) or (color and 0x00FFFFFF)
    }

    private fun colorWithAlpha(color: Int, baseAlpha: Int, globalAlpha: Float): Int =
        fade((baseAlpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF), globalAlpha)

    companion object {
        private var rememberedPage = SilentClickGuiPage.COMBAT
    }

}
