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

import net.ccbluex.liquidbounce.event.events.NotificationEvent
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.misc.nameprotect.ModuleNameProtect
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud
import net.ccbluex.liquidbounce.features.module.modules.render.WyvernHudElement
import net.ccbluex.liquidbounce.features.module.modules.render.WyvernHudMode
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.gui.CORNER_BOTTOM_RIGHT
import net.ccbluex.liquidbounce.render.gui.CORNER_BOTTOM_LEFT
import net.ccbluex.liquidbounce.render.gui.CORNER_TOP_LEFT
import net.ccbluex.liquidbounce.render.gui.CORNER_TOP_RIGHT
import net.ccbluex.liquidbounce.render.gui.CORNERS_BOTTOM
import net.ccbluex.liquidbounce.render.gui.CORNERS_LEFT
import net.ccbluex.liquidbounce.render.gui.CORNERS_RIGHT
import net.ccbluex.liquidbounce.render.gui.CORNERS_TOP
import net.ccbluex.liquidbounce.render.gui.drawRoundedSkinFace
import net.ccbluex.liquidbounce.render.gui.drawRoundedTexture
import net.ccbluex.liquidbounce.render.gui.drawSkinFace
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.registerDynamicImageFromResources
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.ChatScreen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.resources.DefaultPlayerSkin
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.GameType
import java.util.ArrayDeque
import java.util.Locale
import java.util.UUID
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.roundToInt

interface HudNotificationSink {
    fun addModuleNotification(moduleName: String, enabled: Boolean)
    fun addTextNotification(title: String, message: String, severity: NotificationEvent.Severity)
}

abstract class WyvernHudElementBase(
    name: String,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    private val kind: WyvernHudElement,
    private val elementMode: WyvernHudMode? = null
) : HudElement(name, x, y, width, height) {
    protected val mode: WyvernHudMode
        get() = elementMode ?: ModuleHud.wyvernHudMode

    protected val isHud2: Boolean
        get() = mode == WyvernHudMode.HUD2

    protected val chatOpen: Boolean
        get() = mc.screen is ChatScreen

    override fun isVisibleInHud(): Boolean {
        val modeMatches = elementMode == null || elementMode == ModuleHud.wyvernHudMode
        return super.isVisibleInHud() && modeMatches && ModuleHud.isWyvernElementVisible(kind)
    }
}

class WyvernWatermarkElement(name: String, x: Int, y: Int, elementMode: WyvernHudMode) :
    WyvernHudElementBase(name, x, y, 134, 24, WyvernHudElement.WATERMARK, elementMode) {

    private val widthAnimation = HudFloatAnimation(200L, 120f)

    override fun render(context: DrawContext, isSelected: Boolean) {
        if (isHud2) {
            renderCompact(context)
        } else {
            renderClassic(context)
        }

        renderBackground(context, isSelected)
    }

    private fun renderClassic(context: DrawContext) {
        val server = mc.currentServer?.ip ?: "Singleplayer"
        val playerName = ModuleNameProtect.replace(mc.session.username)
        val clientName = "Wraith"
        val version = "26.1.2"
        val fps = mc.currentFps.toString()
        val h = 18f
        val px = x.toFloat()
        val py = y.toFloat()
        val centerY = py + h * 0.5f
        val radius = 4f
        val logoPartWidth = 22f
        val logoSize = 12f
        val padding = 6f
        val gap = 8f
        val sectionIconSize = 8f
        val sectionIconGap = 4.5f
        val titleSize = 7.5f
        val sectionSize = 7.2f
        val titleW = HudDraw.bodyWidth("$clientName ", titleSize) + HudDraw.bodyWidth(version, titleSize)
        val nameW = sectionIconSize + sectionIconGap + HudDraw.bodyWidth(playerName, sectionSize)
        val fpsW = sectionIconSize + sectionIconGap + HudDraw.bodyWidth(fps, sectionSize) + 1.5f +
            HudDraw.bodyWidth("FPS", sectionSize)
        val fixedWidth = logoPartWidth + padding + titleW + gap + nameW + gap + fpsW + gap +
            sectionIconSize + sectionIconGap + padding
        val maxScreenWidth = (mc.window.guiScaledWidth - px - 8f).coerceAtLeast(122f)
        val serverMaxWidth = (maxScreenWidth - fixedWidth).coerceAtLeast(32f)
        val renderedServer = HudDraw.truncate(server, serverMaxWidth, sectionSize)
        val renderedServerW = sectionIconSize + sectionIconGap + HudDraw.bodyWidth(renderedServer, sectionSize)
        val targetWidth = (logoPartWidth + padding + titleW + gap + nameW + gap + fpsW + gap + renderedServerW + padding)
            .coerceAtLeast(122f)
            .coerceAtMost(maxScreenWidth)
        val currentWidth = widthAnimation.update(targetWidth)

        width = ceil(currentWidth).toInt()
        height = ceil(h).toInt()

        HudSurfaceRenderer.drawPanel(context, px, py, currentWidth, h, alpha = 1f, accentStrength = 0.75f)
        ExosGuiRenderer.roundedRectCorners(
            context.matrices,
            px,
            py,
            logoPartWidth,
            h,
            radius,
            HudDraw.argb(78, 0, 0, 0),
            CORNERS_LEFT
        )
        HudSurfaceRenderer.drawAccentStrip(context, px + logoPartWidth + 4f, py + h - 1.4f, currentWidth - logoPartWidth - 9f, 0.45f)

        val segmentY = py + 3f
        val segmentH = h - 6f
        val titleX = px + logoPartWidth + padding - 2f
        val userX = titleX + titleW + gap
        val fpsX = userX + nameW + gap
        val serverX = fpsX + fpsW + gap
        drawWatermarkSegment(context, titleX, titleW + 4f, segmentY, segmentH, 0.72f)
        drawWatermarkSegment(context, userX - 2f, nameW + 4f, segmentY, segmentH, 0.58f)
        drawWatermarkSegment(context, fpsX - 2f, fpsW + 4f, segmentY, segmentH, 0.58f)
        drawWatermarkSegment(context, serverX - 2f, renderedServerW + 4f, segmentY, segmentH, 0.48f)
        drawWatermarkDivider(context, px + logoPartWidth + 0.5f, centerY, 1f)
        drawWatermarkDivider(context, userX - gap * 0.5f, centerY, 0.65f)
        drawWatermarkDivider(context, fpsX - gap * 0.5f, centerY, 0.65f)
        drawWatermarkDivider(context, serverX - gap * 0.5f, centerY, 0.65f)

        HudDraw.icon(
            context,
            HudDraw.WYVERN_LOGO,
            px + (logoPartWidth - logoSize) * 0.5f,
            HudDraw.centeredIconY(centerY, logoSize),
            logoSize.roundToInt(),
            HudDraw.accent()
        )

        var tx = px + logoPartWidth + padding
        val titleY = HudDraw.centeredTextY(centerY, titleSize)
        HudDraw.title(context, "$clientName ", tx, titleY, titleSize, HudDraw.WHITE)
        tx += HudDraw.bodyWidth("$clientName ", titleSize)
        HudDraw.title(context, version, tx, titleY, titleSize, HudDraw.accent())
        tx += HudDraw.bodyWidth(version, titleSize) + gap

        val sectionY = HudDraw.centeredTextY(centerY, sectionSize)
        tx = drawWatermarkSection(context, tx, centerY, sectionY, HudDraw.USER_ICON, playerName, sectionSize, HudDraw.WHITE)
        tx += gap
        HudDraw.icon(
            context,
            HudDraw.FPS_ICON,
            tx,
            HudDraw.centeredIconY(centerY, sectionIconSize),
            sectionIconSize.roundToInt(),
            HudDraw.accent()
        )
        tx += sectionIconSize + sectionIconGap
        HudDraw.body(context, fps, tx, sectionY, sectionSize, HudDraw.WHITE)
        tx += HudDraw.bodyWidth(fps, sectionSize) + 1.5f
        HudDraw.body(context, "FPS", tx, sectionY, sectionSize, HudDraw.TEXT_MUTED)
        tx += HudDraw.bodyWidth("FPS", sectionSize) + gap
        drawWatermarkSection(
            context,
            tx,
            centerY,
            sectionY,
            HudDraw.SERVER_ICON,
            renderedServer,
            sectionSize,
            HudDraw.TEXT_MUTED
        )
    }

    private fun renderCompact(context: DrawContext) {
        val subtitle = ModuleNameProtect.replace(mc.session.username)
        val px = x.toFloat()
        val py = y.toFloat()
        val circleSize = 32f
        val cardHeight = 24f
        val cardX = px + circleSize * 0.5f - 4f
        val cardY = py + (circleSize - cardHeight) * 0.5f
        val logoSize = 14f
        val titleSize = 8f
        val subtitleSize = 7f
        val textOffsetX = 22f
        val cardPaddingRight = 14f
        val contentW = max(HudDraw.titleWidth("Wraith", titleSize), HudDraw.bodyWidth(subtitle, subtitleSize))
        val cardW = textOffsetX + contentW + cardPaddingRight

        width = ceil(cardX - px + cardW).toInt()
        height = circleSize.toInt()

        HudSurfaceRenderer.drawPanel(context, px, py, circleSize, circleSize, accentStrength = 0.55f)
        HudSurfaceRenderer.drawPanel(context, cardX, cardY, cardW, cardHeight, accentStrength = 0.45f)
        HudDraw.icon(
            context,
            HudDraw.WYVERN_LOGO,
            px + (circleSize - logoSize) * 0.5f,
            HudDraw.centeredIconY(py + circleSize * 0.5f, logoSize),
            logoSize.roundToInt(),
            HudDraw.accent()
        )

        val textX = cardX + textOffsetX - 2f
        HudDraw.title(context, "Wraith", textX, cardY + 4.5f, titleSize, HudDraw.argb(255, 235, 235, 235))
        HudDraw.body(
            context,
            HudDraw.truncate(subtitle, cardW - textOffsetX - cardPaddingRight, subtitleSize),
            textX,
            cardY + 14.75f,
            subtitleSize,
            HudDraw.TEXT_MUTED
        )
    }

    private fun drawWatermarkSection(
        context: DrawContext,
        x: Float,
        centerY: Float,
        textY: Float,
        icon: Identifier,
        text: String,
        textSize: Float,
        textColor: Int
    ): Float {
        val iconSize = 8f
        val iconGap = 4.5f
        HudDraw.icon(
            context,
            icon,
            x,
            HudDraw.centeredIconY(centerY, iconSize),
            iconSize.roundToInt(),
            HudDraw.accent()
        )
        val textX = x + iconSize + iconGap
        HudDraw.body(context, text, textX, textY, textSize, textColor)
        return textX + HudDraw.bodyWidth(text, textSize)
    }

    private fun drawWatermarkSegment(
        context: DrawContext,
        x: Float,
        width: Float,
        y: Float,
        height: Float,
        alpha: Float
    ) {
        HudSurfaceRenderer.drawChip(context, x, y, width.coerceAtLeast(2f), height, alpha)
    }

    private fun drawWatermarkDivider(context: DrawContext, x: Float, centerY: Float, alpha: Float) {
        ExosGuiRenderer.roundedRect(
            context.matrices,
            x,
            centerY - 4.5f,
            0.55f,
            9f,
            0.25f,
            HudDraw.argb((28f * alpha).toInt(), 255, 255, 255)
        )
    }
}

class WyvernArrayListElement(
    name: String,
    x: Int,
    y: Int,
    elementMode: WyvernHudMode,
    private val rightOffset: Int
) : WyvernHudElementBase(name, x, y, 72, 1, WyvernHudElement.ARRAY_LIST, elementMode) {

    private val alphaAnimation = HudFloatAnimation(200L, 1f)
    private val moduleAnimations = mutableMapOf<String, HudFloatAnimation>()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val rows = activeRows()
        val alpha = alphaAnimation.update(if (rows.any { it.progress > 0.01f } || chatOpen) 1f else 0f)
        if (alpha <= 0.01f) {
            return
        }

        val visibleRows = rows.filter { it.progress > 0.01f }
            .sortedByDescending { HudDraw.bodyWidth(it.name, 8f) }

        val availableWidth = (mc.window.guiScaledWidth - rightOffset - 4f).coerceAtLeast(40f)
        val maxWidth = (visibleRows.maxOfOrNull { HudDraw.bodyWidth(it.name, 8f) + 8f } ?: 0f)
            .coerceAtMost(availableWidth)
        val px = mc.window.guiScaledWidth - maxWidth - rightOffset
        val py = y.toFloat()
        x = px.roundToInt()
        width = ceil(maxWidth).toInt().coerceAtLeast(1)

        var currentY = py
        visibleRows.forEachIndexed { index, row ->
            val rowProgress = row.progress.coerceIn(0f, 1f)
            val rowAlpha = alpha * rowProgress
            val name = HudDraw.truncate(row.name, maxWidth - 8f, 8f)
            val textWidth = HudDraw.bodyWidth(name, 8f)
            val rowWidth = textWidth + 8f
            val rowHeight = 12f * rowProgress
            val rowX = px + maxWidth - rowWidth
            val cornerMask = CORNERS_LEFT or
                (if (index == 0) CORNER_TOP_RIGHT else 0) or
                (if (index == visibleRows.lastIndex) CORNER_BOTTOM_RIGHT else 0)

            HudDraw.blurPanel(context, rowX, currentY, rowWidth, rowHeight, 2f, rowAlpha * 0.65f)
            ExosGuiRenderer.roundedRectCorners(
                context.matrices,
                rowX,
                currentY,
                rowWidth,
                rowHeight,
                2f,
                HudDraw.argb((125 * rowAlpha).toInt(), 0, 0, 0),
                cornerMask
            )
            HudDraw.body(
                context,
                name,
                rowX + 4f,
                currentY + ((rowHeight - 8f) * 0.5f + 1f).coerceAtLeast(0f),
                8f,
                HudDraw.fade(HudDraw.WHITE, rowAlpha)
            )
            currentY += rowHeight
        }

        height = ceil(currentY - py).toInt().coerceAtLeast(1)
        renderBackground(context, isSelected)
    }

    private fun activeRows(): List<ArrayListRow> {
        val modules = ModuleManager.filter { !it.hidden }
        val keys = modules.map { it.name }.toSet()
        moduleAnimations.keys.retainAll(keys)

        return modules.mapNotNull { module ->
            val animation = moduleAnimations.getOrPut(module.name) { HudFloatAnimation(200L, if (module.enabled) 1f else 0f) }
            val progress = animation.update(if (module.enabled) 1f else 0f)
            if (progress <= 0.01f && !module.enabled) {
                null
            } else {
                ArrayListRow(ModuleNameProtect.replace(module.name), progress)
            }
        }
    }

    private data class ArrayListRow(val name: String, val progress: Float)
}

class WyvernKeybindsElement(name: String, x: Int, y: Int, elementMode: WyvernHudMode) :
    WyvernHudElementBase(name, x, y, 86, 20, WyvernHudElement.KEYBINDS, elementMode) {

    private val widthAnimation = HudFloatAnimation(200L, 86f)
    private val alphaAnimation = HudFloatAnimation(200L, 1f)
    private val rowAnimations = mutableMapOf<String, BindRowState>()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val rows = animatedBinds()
        val alpha = alphaAnimation.update(if (rows.isNotEmpty() || chatOpen) 1f else 0f)
        if (alpha <= 0.01f) {
            return
        }

        val renderRows = if (rows.isEmpty() && chatOpen) listOf(BindRow("No binds", "-", 1f, 1f)) else rows
        val maxName = renderRows.maxOfOrNull { HudDraw.bodyWidth(it.name, 7.2f) * it.progress } ?: 0f
        val maxKey = renderRows.maxOfOrNull { HudDraw.bodyWidth(it.key, 7.2f) * it.progress } ?: 0f
        val targetWidth = if (isHud2) max(82f, maxName + maxKey + 30f) else max(85f, maxName + maxKey + 45f)
        val currentWidth = widthAnimation.update(targetWidth)
        val density = ModuleHud.densityFactor
        val headerHeight = 15f * density
        val rowHeight = 11f * density
        val rowsHeight = renderRows.sumOf { (rowHeight * it.progress).toDouble() }.toFloat()
        val totalHeight = headerHeight + rowsHeight + 4f
        // #Edit By Codex 2026-06-27: use the saved/editor position. The old right-offset
        // anchor rewrote x every frame, so Hotkeys could only be dragged vertically in chat.
        val px = x.toFloat()
        val py = y.toFloat()

        width = ceil(currentWidth).toInt()
        height = ceil(totalHeight).toInt()

        HudDraw.drawHeaderPanel(context, px, py, currentWidth, totalHeight, if (isHud2) "Keybinds" else "Hotkeys", alpha)

        val label = if (isHud2) "Keybinds" else "Hotkeys"
        val headerTextSize = 8f * density
        val rowTextSize = 7.2f * density
        val headerIconSize = 9f * density
        val leftPadding = if (isHud2) 6f else 7f
        val rightPadding = 7f
        val headerCenterY = py + headerHeight * 0.5f
        HudDraw.title(
            context,
            label,
            px + leftPadding,
            HudDraw.centeredTextY(headerCenterY, headerTextSize),
            headerTextSize,
            HudDraw.fade(HudDraw.WHITE, alpha)
        )
        HudDraw.icon(
            context,
            HudDraw.HOTKEY_ICON,
            px + currentWidth - rightPadding - headerIconSize,
            HudDraw.centeredIconY(headerCenterY, headerIconSize),
            headerIconSize.roundToInt(),
            HudDraw.accent(alpha)
        )

        val bodyExtraHeight = totalHeight - headerHeight - rowsHeight
        var rowY = py + headerHeight + bodyExtraHeight * 0.5f
        val keyEnd = px + currentWidth - rightPadding
        HudDraw.enableClip(
            context,
            px,
            py,
            px,
            py + headerHeight,
            currentWidth,
            (totalHeight - headerHeight).coerceAtLeast(0f)
        )
        try {
            renderRows.forEach { row ->
                val rowProgress = row.progress.coerceIn(0f, 1f)
                val animatedHeight = rowHeight * rowProgress
                if (animatedHeight <= 0.05f) {
                    return@forEach
                }

                val rowAlpha = alpha * row.alpha * rowProgress
                val keyW = HudDraw.bodyWidth(row.key, rowTextSize)
                val chipW = keyW + 7f
                val chipH = 8f * density
                val nameMax = currentWidth - chipW - leftPadding - rightPadding - 7f
                val rowCenterY = rowY + animatedHeight * 0.5f
                val rowTextY = HudDraw.centeredTextY(rowCenterY, rowTextSize)
                val rowTextX = px + leftPadding
                if (rowAlpha > 0.02f) {
                    HudDraw.body(
                        context,
                        HudDraw.truncate(row.name, nameMax, rowTextSize),
                        rowTextX,
                        rowTextY,
                        rowTextSize,
                        HudDraw.fade(HudDraw.WHITE, rowAlpha)
                    )
                    ExosGuiRenderer.roundedRect(
                        context.matrices,
                        keyEnd - chipW - 4f,
                        rowCenterY - 3.5f * density,
                        0.65f,
                        7f * density,
                        0.25f,
                        HudDraw.argb((36f * rowAlpha).toInt(), 255, 255, 255)
                    )
                    HudSurfaceRenderer.drawChip(
                        context,
                        keyEnd - chipW,
                        rowCenterY - chipH * 0.5f,
                        chipW,
                        chipH,
                        rowAlpha
                    )
                    HudDraw.body(
                        context,
                        row.key,
                        keyEnd - chipW + (chipW - keyW) * 0.5f,
                        rowTextY,
                        rowTextSize,
                        HudDraw.fade(HudDraw.WHITE, rowAlpha * 0.88f)
                    )
                }
                rowY += animatedHeight
            }
        } finally {
            HudDraw.disableClip(context)
        }

        renderBackground(context, isSelected)
    }

    private fun animatedBinds(): List<BindRow> {
        val modules = ModuleManager.filter { !it.hidden && !it.bind.isUnbound }
        val seenKeys = mutableSetOf<String>()

        modules.forEach { module ->
            val key = module.name
            val shouldShow = module.enabled || chatOpen
            seenKeys += key
            if (shouldShow || rowAnimations.containsKey(key)) {
                val state = rowAnimations.getOrPut(key) { BindRowState(HudFloatAnimation(200L, 0f)) }
                state.name = module.name
                state.key = module.bind.keyName
                state.alpha = if (module.enabled || !chatOpen) 1f else 0.55f
                state.active = shouldShow
            }
        }

        rowAnimations.forEach { (key, state) ->
            if (key !in seenKeys) {
                state.active = false
            }
        }

        val iterator = rowAnimations.iterator()
        val rows = mutableListOf<BindRow>()
        while (iterator.hasNext()) {
            val (key, state) = iterator.next()
            val progress = state.animation.update(if (state.active) 1f else 0f)
            if (!state.active && progress <= 0.01f) {
                iterator.remove()
                continue
            }
            if (progress > 0.01f) {
                rows += BindRow(state.name.ifBlank { key }, state.key, state.alpha, progress)
            }
        }

        return rows.sortedBy { it.name.lowercase(Locale.ROOT) }
    }

    private data class BindRow(val name: String, val key: String, val alpha: Float, val progress: Float)

    private class BindRowState(val animation: HudFloatAnimation) {
        var name: String = ""
        var key: String = ""
        var alpha: Float = 1f
        var active: Boolean = false
    }
}

class WyvernPotionsElement(name: String, x: Int, y: Int, elementMode: WyvernHudMode) :
    WyvernHudElementBase(name, x, y, 86, 20, WyvernHudElement.POTIONS, elementMode) {

    private val widthAnimation = HudFloatAnimation(200L, 82f)
    private val alphaAnimation = HudFloatAnimation(200L, 1f)
    private val rowAnimations = mutableMapOf<String, PotionRowState>()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val rows = animatedPotions()
        val alpha = alphaAnimation.update(if (rows.isNotEmpty() || chatOpen) 1f else 0f)
        if (alpha <= 0.01f) {
            return
        }

        val renderRows = if (rows.isEmpty() && chatOpen) {
            listOf(PotionRow("No effects", "--:--", null, 1f))
        } else {
            rows
        }
        val maxName = renderRows.maxOfOrNull { HudDraw.bodyWidth(it.name, 7.2f) * it.progress } ?: 0f
        val maxDuration = renderRows.maxOfOrNull { HudDraw.bodyWidth(it.duration, 7.2f) * it.progress } ?: 0f
        val targetWidth = if (isHud2) max(85f, maxName + maxDuration + 31f) else max(80f, maxName + maxDuration + 35f)
        val currentWidth = widthAnimation.update(targetWidth)
        val density = ModuleHud.densityFactor
        val headerHeight = 15f * density
        val rowHeight = 11f * density
        val rowsHeight = renderRows.sumOf { (rowHeight * it.progress).toDouble() }.toFloat()
        val totalHeight = headerHeight + rowsHeight + 4f
        val px = x.toFloat()
        val py = y.toFloat()

        width = ceil(currentWidth).toInt()
        height = ceil(totalHeight).toInt()

        HudDraw.drawHeaderPanel(context, px, py, currentWidth, totalHeight, "Potions", alpha)
        val headerTextSize = 8f * density
        val rowTextSize = 7.2f * density
        val headerIconSize = 9f * density
        val rowIconSize = 8f * density
        val leftPadding = if (isHud2) 6f else 7f
        val rightPadding = 7f
        val rowIconX = px + if (isHud2) 4f else 6f
        val rowTextX = rowIconX + rowIconSize + if (isHud2) 2f else 2.5f
        val headerCenterY = py + headerHeight * 0.5f
        HudDraw.title(
            context,
            "Potions",
            px + leftPadding,
            HudDraw.centeredTextY(headerCenterY, headerTextSize),
            headerTextSize,
            HudDraw.fade(HudDraw.WHITE, alpha)
        )
        HudDraw.icon(
            context,
            HudDraw.POTION_ICON,
            px + currentWidth - rightPadding - headerIconSize,
            HudDraw.centeredIconY(headerCenterY, headerIconSize),
            headerIconSize.roundToInt(),
            HudDraw.accent(alpha)
        )

        val bodyExtraHeight = totalHeight - headerHeight - rowsHeight
        var rowY = py + headerHeight + bodyExtraHeight * 0.5f
        HudDraw.enableClip(
            context,
            px,
            py,
            px,
            py + headerHeight,
            currentWidth,
            (totalHeight - headerHeight).coerceAtLeast(0f)
        )
        try {
            renderRows.forEach { row ->
                val rowProgress = row.progress.coerceIn(0f, 1f)
                val animatedHeight = rowHeight * rowProgress
                if (animatedHeight <= 0.05f) {
                    return@forEach
                }

                val rowAlpha = alpha * rowProgress
                val durationW = HudDraw.bodyWidth(row.duration, rowTextSize)
                val chipW = durationW + 7f
                val chipH = 8f * density
                val rowCenterY = rowY + animatedHeight * 0.5f
                val iconY = HudDraw.centeredIconY(rowCenterY, rowIconSize)
                val rowTextY = HudDraw.centeredTextY(rowCenterY, rowTextSize)

                if (row.icon != null) {
                    drawEffectIcon(
                        context,
                        row.icon,
                        rowIconX,
                        iconY,
                        rowIconSize.roundToInt(),
                        HudDraw.fade(HudDraw.WHITE, rowAlpha)
                    )
                } else {
                    ExosGuiRenderer.roundedRect(
                        context.matrices,
                        rowIconX,
                        iconY,
                        rowIconSize,
                        rowIconSize,
                        2f,
                        HudDraw.accent(rowAlpha)
                    )
                }

                if (rowAlpha > 0.02f) {
                    ExosGuiRenderer.roundedRect(
                        context.matrices,
                        rowTextX - 2.5f,
                        rowCenterY - 3.5f * density,
                        0.65f,
                        7f * density,
                        0.25f,
                        HudDraw.argb((34f * rowAlpha).toInt(), 255, 255, 255)
                    )
                    HudDraw.body(
                        context,
                        HudDraw.truncate(row.name, currentWidth - chipW - (rowTextX - px) - rightPadding - 3f, rowTextSize),
                        rowTextX,
                        rowTextY,
                        rowTextSize,
                        HudDraw.fade(HudDraw.WHITE, rowAlpha)
                    )
                    HudSurfaceRenderer.drawChip(
                        context,
                        px + currentWidth - chipW - rightPadding,
                        rowCenterY - chipH * 0.5f,
                        chipW,
                        chipH,
                        rowAlpha
                    )
                    HudDraw.body(
                        context,
                        row.duration,
                        px + currentWidth - chipW - rightPadding + (chipW - durationW) * 0.5f,
                        rowTextY,
                        rowTextSize,
                        HudDraw.fade(HudDraw.WHITE, rowAlpha * 0.88f)
                    )
                }
                rowY += animatedHeight
            }
        } finally {
            HudDraw.disableClip(context)
        }

        renderBackground(context, isSelected)
    }

    private fun animatedPotions(): List<PotionRow> {
        val effects = mc.player?.statusEffects?.sortedBy { it.effect.value().displayName.string } ?: emptyList()
        val activeKeys = mutableSetOf<String>()

        effects.forEach { effect ->
            val key = potionKey(effect)
            val state = rowAnimations.getOrPut(key) { PotionRowState(HudFloatAnimation(250L, 0f)) }
            state.name = effectName(effect)
            state.duration = durationText(effect.duration)
            state.icon = effectIcon(effect)
            state.active = true
            activeKeys += key
        }

        rowAnimations.forEach { (key, state) ->
            if (key !in activeKeys) {
                state.active = false
            }
        }

        val iterator = rowAnimations.iterator()
        val rows = mutableListOf<PotionRow>()
        while (iterator.hasNext()) {
            val (_, state) = iterator.next()
            val progress = state.animation.update(if (state.active) 1f else 0f)
            if (!state.active && progress <= 0.01f) {
                iterator.remove()
                continue
            }
            if (progress > 0.01f) {
                rows += PotionRow(state.name, state.duration, state.icon, progress)
            }
        }

        return rows.sortedBy { it.name.lowercase(Locale.ROOT) }
    }

    private fun effectName(effect: net.minecraft.world.effect.MobEffectInstance): String {
        val base = effect.effect.value().displayName.string
        return if (effect.amplifier > 0) "$base ${effect.amplifier + 1}" else base
    }

    private fun durationText(ticks: Int): String {
        if (ticks == net.minecraft.world.effect.MobEffectInstance.INFINITE_DURATION) {
            return "Inf"
        }

        val totalSeconds = (ticks / 20).coerceAtLeast(0)
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
    }

    private fun potionKey(effect: net.minecraft.world.effect.MobEffectInstance): String {
        val id = BuiltInRegistries.MOB_EFFECT.getKey(effect.effect.value())?.path ?: "speed"
        return "$id:${effect.amplifier}"
    }

    private fun effectIcon(effect: net.minecraft.world.effect.MobEffectInstance): Identifier {
        val id = BuiltInRegistries.MOB_EFFECT.getKey(effect.effect.value())?.path ?: "speed"
        return Identifier.fromNamespaceAndPath("minecraft", "textures/mob_effect/$id.png")
    }

    private fun drawEffectIcon(
        context: DrawContext,
        icon: Identifier,
        x: Float,
        y: Float,
        size: Int,
        color: Int
    ) {
        context.blit(RenderPipelines.GUI_TEXTURED, icon, x.toInt(), y.toInt(), 0f, 0f, size, size, size, size, color)
    }

    private data class PotionRow(val name: String, val duration: String, val icon: Identifier?, val progress: Float)

    private class PotionRowState(val animation: HudFloatAnimation) {
        var name: String = ""
        var duration: String = "--:--"
        var icon: Identifier? = null
        var active: Boolean = false
    }
}

class WyvernStaffElement(name: String, x: Int, y: Int, elementMode: WyvernHudMode, private val rightOffset: Int? = null) :
    WyvernHudElementBase(name, x, y, 86, 20, WyvernHudElement.STAFF, elementMode) {

    private val widthAnimation = HudFloatAnimation(200L, 86f)
    private val alphaAnimation = HudFloatAnimation(200L, 1f)
    private var lastUpdate = 0L
    private val staffRows = mutableListOf<StaffRow>()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val now = System.currentTimeMillis()
        if (now - lastUpdate > 250L) {
            updateStaff()
            lastUpdate = now
        }

        val rows = if (staffRows.isEmpty() && chatOpen) listOf(StaffRow("No staff", "", null, false)) else staffRows
        val alpha = alphaAnimation.update(if (rows.isNotEmpty()) 1f else 0f)
        if (alpha <= 0.01f) {
            return
        }

        val maxName = rows.maxOfOrNull { HudDraw.bodyWidth(it.displayName, 7.2f) } ?: 0f
        val currentWidth = widthAnimation.update(if (isHud2) max(82f, maxName + 35f) else max(85f, maxName + 65f))
        val headerHeight = 15f * ModuleHud.densityFactor
        val totalHeight = headerHeight + rows.size * 11f + 4f
        val px = rightOffset?.let { mc.window.guiScaledWidth - currentWidth - it } ?: x.toFloat()
        val py = y.toFloat()
        if (rightOffset != null) {
            x = px.roundToInt()
        }

        width = ceil(currentWidth).toInt()
        height = ceil(totalHeight).toInt()

        HudDraw.drawHeaderPanel(context, px, py, currentWidth, totalHeight, if (isHud2) "Staff" else "StaffList", alpha)
        if (isHud2) {
            val titleW = HudDraw.bodyWidth("Staff", 8f) + 13f
            HudDraw.title(context, "S", px + (currentWidth - titleW) * 0.5f, py + 4.8f, 7.5f, HudDraw.accent(alpha))
            HudDraw.body(context, "Staff", px + (currentWidth - titleW) * 0.5f + 12f, py + 4.9f, 8f, HudDraw.fade(HudDraw.WHITE, alpha))
        } else {
            HudDraw.title(context, "StaffList", px + 7f, py + 4.5f, 8f, HudDraw.fade(HudDraw.WHITE, alpha))
            HudDraw.title(context, "S", px + currentWidth - 14f, py + 5.2f, 8f, HudDraw.accent(alpha))
        }

        var rowY = py + headerHeight + 2f
        rows.forEach { row ->
            val faceX = px + if (isHud2) 6f else 6f
            val textX = if (row.skinId != null) {
                val drewFace = context.drawSkinFace(row.skinId, faceX, rowY + 1.5f, 8f, HudDraw.fade(HudDraw.WHITE, alpha))
                if (!drewFace) {
                    ExosGuiRenderer.roundedRect(
                        context.matrices,
                        faceX,
                        rowY + 1.5f,
                        8f,
                        8f,
                        2f,
                        HudDraw.argb((80 * alpha).toInt(), 255, 255, 255)
                    )
                }
                px + if (isHud2) 16f else 22f
            } else {
                px + 7f
            }
            HudDraw.body(
                context,
                HudDraw.truncate(row.displayName, currentWidth - (textX - px) - 18f, 7.2f),
                textX,
                rowY + 2.5f,
                7.2f,
                HudDraw.fade(HudDraw.WHITE, alpha)
            )
            ExosGuiRenderer.roundedRect(
                context.matrices,
                px + currentWidth - 10f,
                rowY + 3.5f,
                4f,
                4f,
                2f,
                if (row.vanished) HudDraw.argb((255 * alpha).toInt(), 255, 32, 32) else HudDraw.argb((255 * alpha).toInt(), 32, 255, 32)
            )
            rowY += 11f
        }

        renderBackground(context, isSelected)
    }

    private fun updateStaff() {
        val connection = mc.connection ?: run {
            staffRows.clear()
            return
        }

        val rows = connection.playerList.mapNotNull { entry ->
            val profile = entry.profile ?: return@mapNotNull null
            val name = profile.name ?: return@mapNotNull null
            val display = entry.tabListDisplayName?.string ?: name
            val prefix = display.replace(name, "").trim()
            if (prefix.length < 2 || !containsStaffKeyword(prefix)) {
                return@mapNotNull null
            }

            val skin = runCatching { entry.skin.body().id() }.getOrNull()
                ?: DefaultPlayerSkin.get(profile.id ?: UUID.nameUUIDFromBytes(name.toByteArray())).body().id()
            StaffRow(HudDraw.truncate(display, 90f, 7.2f), name, skin, entry.gameMode == GameType.SPECTATOR)
        }.distinctBy { it.key }

        staffRows.clear()
        staffRows += rows
    }

    private fun containsStaffKeyword(text: String): Boolean {
        val lower = normalizePrefix(text).lowercase(Locale.US)
        return STAFF_KEYWORDS.any(lower::contains)
    }

    private fun normalizePrefix(text: String): String {
        return text
            .replace("ᴀ", "A")
            .replace("ʙ", "B")
            .replace("ᴄ", "C")
            .replace("ᴅ", "D")
            .replace("ᴇ", "E")
            .replace("ғ", "F")
            .replace("ɢ", "G")
            .replace("ʜ", "H")
            .replace("ɪ", "I")
            .replace("ᴊ", "J")
            .replace("ᴋ", "K")
            .replace("ʟ", "L")
            .replace("ᴍ", "M")
            .replace("ɴ", "N")
            .replace("ꜱ", "S")
            .replace("ᴏ", "O")
            .replace("ᴘ", "P")
            .replace("ǫ", "Q")
            .replace("ʀ", "R")
            .replace("ᴛ", "T")
            .replace("ᴜ", "U")
            .replace("ᴠ", "V")
            .replace("ᴡ", "W")
            .replace("ʏ", "Y")
            .replace("ᴢ", "Z")
            .replace("ꔗ", "moder")
            .replace("ꔥ", "moder")
            .replace("ꔡ", "moder")
            .replace("ꔳ", "admin")
    }

    private data class StaffRow(val displayName: String, val key: String, val skinId: Identifier?, val vanished: Boolean)

    private companion object {
        private val STAFF_KEYWORDS = setOf(
            "helper", "admin", "moder", "staff", "curator", "st.moder", "moder+", "ml.admin",
            "стаж", "сотруд", "помощ", "админ", "модер", "ᴀдмин"
        )
    }
}

class WyvernTargetHudElement(name: String, x: Int, y: Int, elementMode: WyvernHudMode, private val centerYOffset: Float) :
    WyvernHudElementBase(name, x, y, 100, 38, WyvernHudElement.TARGET_HUD, elementMode) {

    private val healthAnimation = SpringFloat(1f, stiffness = 34f, damping = 18f)
    private val oldHealthAnimation = SpringFloat(1f, stiffness = 15f, damping = 13f)
    private val absorptionAnimation = SpringFloat(0f, stiffness = 30f, damping = 17f)
    private val toggleAnimation = TweenFloat(0f, 220L, HudEasing::quintOut)
    private var lastTarget: LivingEntity? = null
    private var lastTargetId: UUID? = null

    override fun render(context: DrawContext, isSelected: Boolean) {
        val target = currentTarget()
        val progress = toggleAnimation.update(if (target != null) 1f else 0f)
        val renderTarget = target ?: lastTarget
        if (renderTarget == null || progress <= 0.01f) {
            if (target == null) {
                lastTarget = null
                lastTargetId = null
            }
            return
        }
        if (target != null) {
            if (lastTargetId != target.uuid) {
                resetTargetAnimations(target)
            }
            lastTargetId = target.uuid
            lastTarget = target
        }

        val targetWidth = if (isHud2) 100f else 100f
        val targetHeight = if (isHud2) 36f else 38f
        val scale = 0.94f + 0.06f * HudEasing.cubicOut(progress)
        val cx = x.toFloat() + targetWidth * 0.5f
        val cy = y.toFloat() + targetHeight * 0.5f
        context.pose().pushMatrix()
        try {
            context.pose().translate(cx, cy)
            context.pose().scale(scale, scale)
            context.pose().translate(-cx, -cy)
            if (isHud2) {
                renderV2(context, renderTarget, progress)
            } else {
                renderClassic(context, renderTarget, progress)
            }
        } finally {
            context.pose().popMatrix()
        }

        renderBackground(context, isSelected)
    }

    private fun currentTarget(): LivingEntity? {
        if (chatOpen) {
            return mc.player
        }

        return ModuleKillAura.targetTracker.target as? LivingEntity
    }

    private fun renderV2(context: DrawContext, target: LivingEntity, alpha: Float) {
        val w = 100f
        val h = 36f
        // #Edit By Codex 2026-06-27: keep TargetHUD at its saved position so chat dragging
        // works on both axes instead of snapping back to screen center every render.
        val px = x.toFloat()
        val py = y.toFloat()
        width = w.toInt()
        height = h.toInt()

        HudSurfaceRenderer.drawPanel(context, px, py, w, h, alpha = alpha, accentStrength = 0.85f)
        drawTargetFace(context, target, px + 5f, py + 5f, 26f, alpha)

        val nameX = px + 36f
        val name = targetName(target, maxChars = 9, maxWidth = w - (nameX - px) - 5f, size = 8f)
        HudDraw.title(context, name, nameX, py + 6.75f, 8f, HudDraw.fade(HudDraw.WHITE, alpha))

        val hp = target.health.coerceAtLeast(0f)
        val maxHp = target.maxHealth.coerceAtLeast(1f)
        val hpText = if (target.absorptionAmount > 0f) {
            String.format(Locale.US, "HP: %.1f + %.1f", hp, target.absorptionAmount)
        } else {
            String.format(Locale.US, "HP: %.1f", hp)
        }
        HudDraw.body(context, hpText, nameX, py + 16.25f, 7f, HudDraw.fade(HudDraw.TEXT_MUTED, alpha))

        drawHealthBars(context, px + 35.5f, py + h - 10.5f, w - 40.5f, 5.5f, hp, maxHp, target.absorptionAmount, alpha)
        if (target is Player) {
            drawArmor(context, target, px + 3f, py - 12f, alpha, 0.6f)
        }
    }

    private fun renderClassic(context: DrawContext, target: LivingEntity, alpha: Float) {
        val w = 100f
        val h = 38f
        // #Edit By Codex 2026-06-27: keep TargetHUD at its saved position so chat dragging
        // works on both axes instead of snapping back to screen center every render.
        val px = x.toFloat()
        val py = y.toFloat()
        width = w.toInt()
        height = h.toInt()

        HudSurfaceRenderer.drawPanel(context, px, py, w, h, alpha = alpha, accentStrength = 0.85f)
        drawTargetFace(context, target, px + 4.5f, py + 2f, 24.5f, alpha)

        val nameX = px + 32.2f
        val hp = target.health.coerceAtLeast(0f)
        val maxHp = target.maxHealth.coerceAtLeast(1f)
        val hpText = String.format(Locale.US, "%.1fhp", hp)
        val hpX = px + w - HudDraw.bodyWidth(hpText, 7f) - 5f
        HudDraw.title(
            context,
            targetName(target, maxChars = 8, maxWidth = hpX - nameX - 4f, size = 8.5f),
            nameX,
            py + 6.7f,
            8.5f,
            HudDraw.fade(HudDraw.WHITE, alpha)
        )
        HudDraw.body(context, hpText, hpX, py + 7.2f, 7f, HudDraw.fade(HudDraw.WHITE, alpha * 0.72f))

        drawHealthBars(context, px + 5f, py + h - 9f, w - 10f, 5f, hp, maxHp, target.absorptionAmount, alpha)
        if (target is Player) {
            drawArmor(context, target, nameX - 1f, py + 16.5f, alpha, 0.55f)
        }
    }

    private fun resetTargetAnimations(target: LivingEntity) {
        val maxHp = target.maxHealth.coerceAtLeast(1f)
        val healthRatio = (target.health.coerceAtLeast(0f) / maxHp).coerceIn(0f, 1f)
        val absorptionRatio = (target.absorptionAmount / maxHp).coerceIn(0f, 1f)
        healthAnimation.snap(healthRatio)
        oldHealthAnimation.snap(healthRatio)
        absorptionAnimation.snap(absorptionRatio)
    }

    private fun drawHealthBars(
        context: DrawContext,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        health: Float,
        maxHealth: Float,
        absorption: Float,
        alpha: Float
    ) {
        val healthRatio = (health / maxHealth).coerceIn(0f, 1f)
        val current = healthAnimation.update(healthRatio)
        val old = if (oldHealthAnimation.value < current) {
            oldHealthAnimation.snap(current)
            current
        } else {
            oldHealthAnimation.update(healthRatio)
        }
        val absorptionRatio = absorptionAnimation.update((absorption / maxHealth).coerceIn(0f, 1f))
        val radius = height * 0.5f
        val currentWidth = width * current.coerceIn(0f, 1f)
        val oldWidth = width * old.coerceIn(0f, 1f)
        val absorptionWidth = width * absorptionRatio.coerceIn(0f, 1f)
        val trackTop = HudDraw.argb((118f * alpha).toInt(), 10, 10, 10)
        val trackBottom = HudDraw.argb((94f * alpha).toInt(), 16, 16, 16)
        val oldLeft = HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accentB(), 0.42f), alpha * 0.38f)
        val oldRight = HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accent(), 0.42f), alpha * 0.38f)
        val currentLeft = HudDraw.fade(HudDraw.accentB(), alpha * 0.94f)
        val currentRight = HudDraw.fade(HudDraw.accent(), alpha * 0.94f)

        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x,
            y,
            width,
            height,
            radius,
            trackTop,
            trackTop,
            trackBottom,
            trackBottom
        )
        if (oldWidth > 0.05f) {
            ExosGuiRenderer.roundedGradientRect(
                context.matrices,
                x,
                y,
                oldWidth,
                height,
                radius,
                oldLeft,
                oldRight,
                oldRight,
                oldLeft
            )
        }
        if (currentWidth > 0.05f) {
            ExosGuiRenderer.roundedGradientRect(
                context.matrices,
                x,
                y,
                currentWidth,
                height,
                radius,
                currentLeft,
                currentRight,
                currentRight,
                currentLeft
            )
        }
        if (absorptionWidth > 0.05f) {
            ExosGuiRenderer.roundedGradientRect(
                context.matrices,
                x,
                y,
                absorptionWidth,
                height,
                radius,
                HudDraw.argb((172 * alpha).toInt(), 204, 150, 26),
                HudDraw.argb((184 * alpha).toInt(), 242, 198, 52),
                HudDraw.argb((184 * alpha).toInt(), 242, 198, 52),
                HudDraw.argb((172 * alpha).toInt(), 204, 150, 26)
            )
        }
    }

    private fun drawTargetFace(context: DrawContext, target: LivingEntity, x: Float, y: Float, size: Float, alpha: Float) {
        val radius = if (isHud2) 3f else 10f.coerceAtMost(size * 0.5f)
        val hurt = (target.hurtTime / 10f).coerceIn(0f, 1f)
        val faceScale = 1f + 0.045f * hurt
        val cx = x + size * 0.5f
        val cy = y + size * 0.5f

        context.pose().pushMatrix()
        try {
            if (hurt > 0.01f) {
                context.pose().translate(cx, cy)
                context.pose().scale(faceScale, faceScale)
                context.pose().translate(-cx, -cy)
            }

            ExosGuiRenderer.roundedRect(context.matrices, x, y, size, size, radius, HudDraw.argb((185 * alpha).toInt(), 0, 0, 0))

            // #Edit By Codex 2026-06-27: draw the avatar texture itself through the rounded
            // mask. The old classic path shrank a square skin tile inside a circular frame,
            // which still looked like a square head pasted onto a round card.
            val drewHead = if (target is Player) {
                val skin = mc.connection?.getPlayerInfo(target.uuid)?.skin?.body()?.id()
                    ?: DefaultPlayerSkin.get(target.uuid).body().id()
                context.drawRoundedSkinFace(skin, x, y, size, radius, HudDraw.fade(HudDraw.WHITE, alpha))
            } else {
                val texture = entityHeadTexture(target)
                texture != null && drawHeadTextureRegion(context, texture, x, y, size, radius, alpha)
            }

            if (!drewHead) {
                if (target is Player) {
                    val fallbackSkin = DefaultPlayerSkin.getDefaultSkin().body().id()
                    val drewFallback = context.drawRoundedSkinFace(
                        fallbackSkin,
                        x,
                        y,
                        size,
                        radius,
                        HudDraw.fade(HudDraw.WHITE, alpha)
                    )
                    if (!drewFallback) {
                        drawEntityPlaceholder(context, target, x, y, size, radius, alpha)
                    }
                } else {
                    drawEntityPlaceholder(context, target, x, y, size, radius, alpha)
                }
            }
            if (hurt > 0.01f) {
                ExosGuiRenderer.roundedRect(context.matrices, x, y, size, size, radius, HudDraw.argb((55 * hurt * alpha).toInt(), 255, 48, 48))
            }
            ExosGuiRenderer.roundedOutline(context.matrices, x, y, size, size, radius, HudDraw.fade(HudDraw.WHITE, alpha * 0.08f), 0.28f)
        } finally {
            context.pose().popMatrix()
        }
    }

    private fun drawEntityPlaceholder(
        context: DrawContext,
        target: LivingEntity,
        x: Float,
        y: Float,
        size: Float,
        radius: Float,
        alpha: Float
    ) {
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x,
            y,
            size,
            size,
            radius,
            HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accentB(), 0.55f), alpha),
            HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accent(), 0.55f), alpha),
            HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accent(), 0.32f), alpha),
            HudDraw.fade(ExosGuiRenderer.shade(HudDraw.accentB(), 0.32f), alpha)
        )

        val key = BuiltInRegistries.ENTITY_TYPE.getKey(target.type).path
        val letter = key.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
        val textSize = if (isHud2) 11f else 10.5f
        val textW = HudDraw.titleWidth(letter, textSize)
        HudDraw.title(
            context,
            letter,
            x + (size - textW) * 0.5f,
            HudDraw.centeredTextY(y + size * 0.5f, textSize),
            textSize,
            HudDraw.fade(HudDraw.WHITE, alpha)
        )
    }

    private fun drawHeadTextureRegion(
        context: DrawContext,
        textureId: Identifier,
        x: Float,
        y: Float,
        size: Float,
        radius: Float,
        alpha: Float
    ): Boolean {
        if (alpha <= 0.01f) {
            return false
        }

        val dimensions = runCatching {
            val texture = mc.textureManager.getTexture(textureId)
            val view = texture.textureView
            view.getWidth(0) to view.getHeight(0)
        }.getOrNull() ?: (64 to 64)
        val textureWidth = dimensions.first.coerceAtLeast(16)
        val textureHeight = dimensions.second.coerceAtLeast(16)
        val tile = (textureWidth / 8).coerceAtLeast(8)
        val srcSize = tile.coerceAtMost(textureHeight - tile).coerceAtLeast(8)
        return context.drawRoundedTexture(
            textureId,
            x,
            y,
            size,
            size,
            radius,
            HudDraw.fade(HudDraw.WHITE, alpha),
            tile.toFloat() / textureWidth.toFloat(),
            tile.toFloat() / textureHeight.toFloat(),
            (tile + srcSize).toFloat() / textureWidth.toFloat(),
            (tile + srcSize).toFloat() / textureHeight.toFloat(),
        )
    }

    private fun entityHeadTexture(target: LivingEntity): Identifier? {
        val entityId = BuiltInRegistries.ENTITY_TYPE.getKey(target.type)
        val path = entityId.path
        val specialPath = when (path) {
            "blaze" -> "textures/entity/blaze.png"
            "bogged" -> "textures/entity/skeleton/bogged.png"
            "cave_spider" -> "textures/entity/spider/cave_spider.png"
            "creeper" -> "textures/entity/creeper/creeper.png"
            "drowned" -> "textures/entity/zombie/drowned.png"
            "elder_guardian" -> "textures/entity/guardian_elder.png"
            "enderman" -> "textures/entity/enderman/enderman.png"
            "evoker" -> "textures/entity/illager/evoker.png"
            "guardian" -> "textures/entity/guardian.png"
            "husk" -> "textures/entity/zombie/husk.png"
            "illusioner" -> "textures/entity/illager/illusioner.png"
            "magma_cube" -> "textures/entity/slime/magmacube.png"
            "piglin" -> "textures/entity/piglin/piglin.png"
            "piglin_brute" -> "textures/entity/piglin/piglin_brute.png"
            "pillager" -> "textures/entity/illager/pillager.png"
            "ravager" -> "textures/entity/illager/ravager.png"
            "skeleton" -> "textures/entity/skeleton/skeleton.png"
            "slime" -> "textures/entity/slime/slime.png"
            "spider" -> "textures/entity/spider/spider.png"
            "stray" -> "textures/entity/skeleton/stray.png"
            "vex" -> "textures/entity/illager/vex.png"
            "villager" -> "textures/entity/villager/villager.png"
            "vindicator" -> "textures/entity/illager/vindicator.png"
            "wandering_trader" -> "textures/entity/wandering_trader/wandering_trader.png"
            "witch" -> "textures/entity/witch.png"
            "wither_skeleton" -> "textures/entity/skeleton/wither_skeleton.png"
            "zoglin" -> "textures/entity/hoglin/zoglin.png"
            "zombie" -> "textures/entity/zombie/zombie.png"
            "zombie_villager" -> "textures/entity/zombie_villager/zombie_villager.png"
            "zombified_piglin" -> "textures/entity/piglin/zombified_piglin.png"
            else -> null
        }

        val candidates = buildList {
            specialPath?.let { add(it) }
            add("textures/entity/$path/$path.png")
            add("textures/entity/$path.png")
        }

        return candidates
            .map { Identifier.withDefaultNamespace(it) }
            .firstOrNull { id -> mc.resourceManager.getResource(id).isPresent }
    }

    private fun targetName(target: LivingEntity, maxChars: Int, maxWidth: Float, size: Float): String {
        val rawName = if (target == mc.player) {
            ModuleNameProtect.replace(mc.session.username)
        } else {
            ModuleNameProtect.replace(target.scoreboardName)
        }
        val charClamped = if (rawName.length > maxChars) rawName.substring(0, maxChars) else rawName

        return HudDraw.truncate(charClamped, maxWidth.coerceAtLeast(0f), size)
    }

    private fun drawArmor(context: DrawContext, player: Player, x: Float, y: Float, alpha: Float, scale: Float) {
        val stacks = buildList {
            add(player.mainHandStack)
            add(player.offHandStack)
            addAll(player.armorItems)
        }.filterNot { it.isEmpty }

        val itemScale = (scale * alpha).coerceIn(0f, scale)
        if (itemScale <= 0.02f) {
            return
        }

        var iconX = x + (5f - alpha * 5f)
        val iconY = y + 1f + (5f - alpha * 5f)
        stacks.forEach { stack ->
            HudDraw.drawScaledItem(context, stack, iconX, iconY, itemScale, alpha)
            iconX += 10f
        }
    }
}

class WyvernInventoryElement(x: Int, y: Int) :
    WyvernHudElementBase("Inventory", x, y, 180, 60, WyvernHudElement.INVENTORY) {

    private val alphaAnimation = HudFloatAnimation(250L, 1f)

    override fun render(context: DrawContext, isSelected: Boolean) {
        val player = mc.player ?: return
        val alpha = alphaAnimation.update(1f)
        val slot = 18f
        val gap = 1f
        val headerHeight = 14f
        val pad = 5f
        val px = x.toFloat()
        val py = y.toFloat()
        width = 180
        height = 78

        HudSurfaceRenderer.drawHeaderPanel(context, px, py, width.toFloat(), height.toFloat(), headerHeight, alpha)
        HudDraw.title(context, "Inventory", px + 7f, py + 4.35f, 7.5f, HudDraw.fade(HudDraw.WHITE, alpha))
        HudSurfaceRenderer.drawAccentStrip(context, px + 7f, py + headerHeight - 1.2f, width.toFloat() - 14f, alpha * 0.28f)

        val gridX = px + pad
        val gridY = py + headerHeight + 4f
        ExosGuiRenderer.roundedRect(
            context.matrices,
            gridX - 2f,
            gridY - 2f,
            width - pad * 2f + 4f,
            height - headerHeight - 7f,
            3.5f,
            HudDraw.argb((42f * alpha).toInt(), 0, 0, 0)
        )

        for (row in 0 until 3) {
            for (col in 0 until 9) {
                val slotX = gridX + col * (slot + gap)
                val slotY = gridY + row * (slot + gap)
                val color = if ((row + col) % 2 == 0) HudDraw.argb(74, 36, 36, 36) else HudDraw.argb(62, 24, 24, 24)
                val cornerMask = slotCornerMask(col, row, 9, 3)
                if (cornerMask == 0) {
                    ExosGuiRenderer.roundedRect(context.matrices, slotX, slotY, slot, slot, 1.25f, HudDraw.fade(color, alpha))
                } else {
                    ExosGuiRenderer.roundedRectCorners(
                        context.matrices,
                        slotX,
                        slotY,
                        slot,
                        slot,
                        4f,
                        HudDraw.fade(color, alpha),
                        cornerMask
                    )
                }
                val stack = player.inventory.getStack(9 + row * 9 + col)
                HudDraw.drawScaledItem(context, stack, slotX + 3.2f, slotY + 3.2f, 0.72f, alpha)
            }
        }

        renderBackground(context, isSelected)
    }

    private fun slotCornerMask(col: Int, row: Int, columns: Int, rows: Int): Int {
        var mask = 0
        if (col == 0 && row == 0) mask = mask or CORNER_TOP_LEFT
        if (col == columns - 1 && row == 0) mask = mask or CORNER_TOP_RIGHT
        if (col == columns - 1 && row == rows - 1) mask = mask or CORNER_BOTTOM_RIGHT
        if (col == 0 && row == rows - 1) mask = mask or CORNER_BOTTOM_LEFT
        return mask
    }
}

class WyvernNotifyElement(x: Int, y: Int) :
    WyvernHudElementBase("Notify", x, y, 120, 18, WyvernHudElement.NOTIFICATIONS), HudNotificationSink {

    private val notifications = ArrayDeque<WyvernNotification>()
    private val toggleAnimation = HudFloatAnimation(200L, 0f)

    override fun addModuleNotification(moduleName: String, enabled: Boolean) {
        notifications.addLast(WyvernNotification.Module(moduleName, enabled))
        trim()
    }

    override fun addTextNotification(title: String, message: String, severity: NotificationEvent.Severity) {
        val text = if (message.isBlank()) title else "$title: $message"
        notifications.addLast(WyvernNotification.Text(text, severity))
        trim()
    }

    override fun render(context: DrawContext, isSelected: Boolean) {
        updateNotifications()
        val visible = notifications.any { it.alpha.value > 0.01f } || chatOpen
        val progress = toggleAnimation.update(if (visible) 1f else 0f)
        if (progress <= 0.01f) {
            return
        }

        if (notifications.isEmpty() && chatOpen) {
            notifications.addLast(WyvernNotification.Text("HUD editor", NotificationEvent.Severity.INFO, preview = true))
        }

        if (isHud2) {
            renderV2(context, progress)
        } else {
            renderClassic(context, progress)
        }

        notifications.removeIf { it.preview }
        renderBackground(context, isSelected)
    }

    private fun renderClassic(context: DrawContext, progress: Float) {
        // #Edit By Codex 2026-06-27: notifications now use their saved top-left anchor.
        // Previously this classic layout forced itself to screen center every frame.
        val startY = y.toFloat()
        var yPos = startY
        val items = notifications.toList().asReversed()
        var maxWidth = 100f
        val leftX = x.toFloat()

        items.forEach { notification ->
            val rowProgress = notification.alpha.value
            val alpha = rowProgress * progress
            if (alpha <= 0.001f) {
                return@forEach
            }

            yPos += 10.5f * rowProgress
            val targetY = yPos - 4f
            val drawY = notification.animatedY(targetY, 8f)
            val width = drawClassicNotification(context, leftX, drawY, notification, alpha)
            maxWidth = max(maxWidth, width)
            yPos += 8f * rowProgress
        }

        width = ceil(maxWidth).toInt().coerceAtLeast(100)
        height = ceil(abs(yPos - startY)).toInt().coerceAtLeast(18)
    }

    private fun renderV2(context: DrawContext, progress: Float) {
        val anchorX = x.toFloat()
        var currentY = y.toFloat()
        var maxWidth = 100f
        val bottomStack = y > mc.window.guiScaledHeight * 0.5f

        notifications.toList().asReversed().forEach { notification ->
            val rowProgress = notification.alpha.value
            val alpha = rowProgress * progress
            if (alpha <= 0.001f) {
                return@forEach
            }

            val width = drawV2Notification(
                context,
                anchorX,
                notification.animatedY(currentY, if (bottomStack) -8f else 8f),
                notification,
                alpha
            )
            maxWidth = max(maxWidth, width)
            val rowStep = (NOTIFICATION_HEIGHT + V2_STACK_GAP) * rowProgress
            currentY += if (bottomStack) -rowStep else rowStep
        }

        width = ceil(maxWidth).toInt().coerceAtLeast(100)
        height = ceil(abs(currentY - y.toFloat())).toInt().coerceAtLeast(NOTIFICATION_HEIGHT.roundToInt())
    }

    private fun drawClassicNotification(
        context: DrawContext,
        leftX: Float,
        y: Float,
        notification: WyvernNotification,
        alpha: Float
    ): Float {
        val main = notification.classicMainText
        val status = notification.statusText
        val textSize = 6.75f
        val iconPanelWidth = 17f
        val textGap = 4f
        val statusGapText = if (status.isBlank()) "" else "    "
        val mainWidth = HudDraw.bodyWidth(main, textSize)
        val statusGapWidth = HudDraw.bodyWidth(statusGapText, textSize)
        val statusWidth = if (status.isBlank()) 0f else HudDraw.bodyWidth(status, textSize)
        val width = iconPanelWidth + textGap + mainWidth + statusGapWidth + statusWidth + 7f
        val x = leftX
        val centerY = y + NOTIFICATION_HEIGHT * 0.5f

        HudSurfaceRenderer.drawPanel(context, x, y, width, NOTIFICATION_HEIGHT, alpha = alpha, accentStrength = 0.7f)
        HudSurfaceRenderer.drawAccentStrip(context, x + 3f, y + 1.25f, width - 6f, alpha * 0.35f)
        ExosGuiRenderer.roundedRectCorners(
            context.matrices,
            x,
            y,
            iconPanelWidth,
            NOTIFICATION_HEIGHT,
            ModuleHud.panelRadius.coerceIn(2f, 7f),
            HudDraw.argb((70 * alpha).toInt(), 0, 0, 0),
            CORNERS_LEFT
        )

        drawNotificationIcon(context, notification, x, centerY, iconPanelWidth, CLASSIC_ICON_SIZE, alpha)
        val textY = HudDraw.centeredTextY(centerY, textSize)
        val textX = x + iconPanelWidth + textGap
        HudDraw.body(context, main, textX, textY, textSize, HudDraw.fade(HudDraw.WHITE, alpha))
        if (status.isNotBlank()) {
            HudDraw.body(
                context,
                status,
                textX + mainWidth + statusGapWidth,
                textY,
                textSize,
                notification.statusColor(alpha)
            )
        }
        return width
    }

    private fun drawV2Notification(
        context: DrawContext,
        anchorX: Float,
        y: Float,
        notification: WyvernNotification,
        alpha: Float
    ): Float {
        val main = notification.v2MainText
        val status = notification.statusText
        val textSize = 6.75f
        val iconWidth = notification.iconVisualWidth(V2_ICON_TEXT_SIZE, V2_ICON_TEXTURE_SIZE)
        val mainWidth = HudDraw.bodyWidth(main, textSize)
        val statusWidth = if (status.isBlank()) 0f else HudDraw.bodyWidth(status, textSize)
        val statusPartWidth = if (status.isBlank()) 0f else V2_SPACING + statusWidth
        val width = V2_PADDING * 2f + iconWidth + V2_SPACING + mainWidth + statusPartWidth
        val x = when (v2HorizontalAlignment()) {
            NotifyAlignment.LEFT -> anchorX
            NotifyAlignment.CENTER -> anchorX - width * 0.5f
            NotifyAlignment.RIGHT -> anchorX - width
        }
        val centerY = y + NOTIFICATION_HEIGHT * 0.5f

        HudSurfaceRenderer.drawPanel(context, x, y, width, NOTIFICATION_HEIGHT, alpha = alpha, accentStrength = 0.55f)
        HudSurfaceRenderer.drawAccentStrip(context, x + 4f, y + 1.25f, width - 8f, alpha * 0.30f)

        var currentX = x + V2_PADDING
        drawNotificationIcon(
            context,
            notification,
            currentX,
            centerY,
            iconWidth,
            V2_ICON_TEXTURE_SIZE,
            alpha,
            textIconSize = V2_ICON_TEXT_SIZE
        )
        currentX += iconWidth + V2_SPACING

        val textY = HudDraw.centeredTextY(centerY, textSize)
        HudDraw.body(context, main, currentX, textY, textSize, HudDraw.fade(HudDraw.WHITE, alpha))
        if (status.isNotBlank()) {
            currentX += mainWidth + V2_SPACING
            HudDraw.body(context, status, currentX, textY, textSize, notification.v2StatusColor(alpha))
        }
        return width
    }

    private fun drawNotificationIcon(
        context: DrawContext,
        notification: WyvernNotification,
        x: Float,
        centerY: Float,
        boxWidth: Float,
        textureSize: Float,
        alpha: Float,
        textIconSize: Float = textureSize
    ) {
        val color = notification.iconColor(alpha)
        val texture = notification.iconTexture
        if (texture != null) {
            HudDraw.icon(
                context,
                texture,
                x + (boxWidth - textureSize) * 0.5f,
                HudDraw.centeredIconY(centerY, textureSize),
                textureSize.roundToInt(),
                color
            )
            return
        }

        val icon = notification.fallbackIcon
        val iconWidth = HudDraw.titleWidth(icon, textIconSize)
        HudDraw.title(
            context,
            icon,
            x + (boxWidth - iconWidth) * 0.5f,
            HudDraw.centeredTextY(centerY, textIconSize),
            textIconSize,
            color
        )
    }

    private fun v2HorizontalAlignment(): NotifyAlignment {
        val screenWidth = mc.window.guiScaledWidth.toFloat().coerceAtLeast(1f)
        val anchor = x.toFloat()
        return when {
            anchor > screenWidth * 0.66f -> NotifyAlignment.RIGHT
            anchor > screenWidth * 0.33f -> NotifyAlignment.CENTER
            else -> NotifyAlignment.LEFT
        }
    }

    private fun updateNotifications() {
        val now = System.currentTimeMillis()
        val iterator = notifications.iterator()
        while (iterator.hasNext()) {
            val notification = iterator.next()
            val fading = !notification.preview && now - notification.createdAt > 2000L
            notification.alpha.update(if (fading) 0f else 1f)
            if (fading && notification.alpha.value <= 0.01f) {
                iterator.remove()
            }
        }
    }

    private fun trim() {
        while (notifications.size > 6) {
            notifications.removeFirst()
        }
    }

    private enum class NotifyAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    private companion object {
        private const val NOTIFICATION_HEIGHT = 18f
        private const val CLASSIC_ICON_SIZE = 8.5f
        private const val V2_PADDING = 6f
        private const val V2_SPACING = 4f
        private const val V2_STACK_GAP = 4f
        private const val V2_ICON_TEXTURE_SIZE = 10f
        private const val V2_ICON_TEXT_SIZE = 13f
    }
}

private sealed class WyvernNotification(val createdAt: Long, val preview: Boolean = false) {
    val alpha = HudFloatAnimation(300L, 0f)
    private val ySpring = SpringFloat(0f, stiffness = 30f, damping = 19f)
    private var yInitialized = false

    init {
        if (preview) {
            alpha.snap(1f)
        }
    }

    abstract val iconTexture: Identifier?
    abstract val fallbackIcon: String
    abstract val classicMainText: String
    abstract val v2MainText: String
    abstract val statusText: String
    abstract fun iconColor(alpha: Float): Int
    abstract fun statusColor(alpha: Float): Int
    open fun v2StatusColor(alpha: Float): Int = statusColor(alpha)

    fun animatedY(target: Float, enterOffset: Float): Float {
        if (!yInitialized) {
            ySpring.snap(target + enterOffset)
            yInitialized = true
        }
        return ySpring.update(target)
    }

    fun iconVisualWidth(textIconSize: Float, textureSize: Float): Float {
        return if (iconTexture != null) {
            textureSize
        } else {
            HudDraw.titleWidth(fallbackIcon, textIconSize).coerceAtLeast(8f)
        }
    }

    class Module(private val name: String, private val enabled: Boolean) : WyvernNotification(System.currentTimeMillis()) {
        override val iconTexture = if (enabled) HudDraw.NOTIFY_CHECK_ICON else HudDraw.NOTIFY_CROSS_ICON
        override val fallbackIcon = if (enabled) "V" else "X"
        override val classicMainText = " $name"
        override val v2MainText = "$name "
        override val statusText = if (enabled) "Enabled!" else "Disabled!"
        override fun iconColor(alpha: Float) = if (enabled) HudDraw.accent(alpha) else HudDraw.argb((255 * alpha).toInt(), 255, 76, 76)
        override fun statusColor(alpha: Float) = iconColor(alpha)
        override fun v2StatusColor(alpha: Float) = if (enabled) {
            HudDraw.argb((255 * alpha).toInt(), 32, 255, 32)
        } else {
            HudDraw.argb((255 * alpha).toInt(), 255, 32, 32)
        }
    }

    class Text(private val text: String, private val severity: NotificationEvent.Severity, preview: Boolean = false) :
        WyvernNotification(System.currentTimeMillis(), preview) {
        override val iconTexture = when (severity) {
            NotificationEvent.Severity.ERROR -> HudDraw.NOTIFY_CROSS_ICON
            NotificationEvent.Severity.SUCCESS -> HudDraw.NOTIFY_CHECK_ICON
            else -> null
        }
        override val fallbackIcon = when (severity) {
            NotificationEvent.Severity.ERROR -> "!"
            NotificationEvent.Severity.SUCCESS -> "V"
            else -> "i"
        }
        override val classicMainText = " $text"
        override val v2MainText = text
        override val statusText = ""
        override fun iconColor(alpha: Float) = when (severity) {
            NotificationEvent.Severity.ERROR -> HudDraw.argb((255 * alpha).toInt(), 255, 76, 76)
            NotificationEvent.Severity.SUCCESS -> HudDraw.argb((255 * alpha).toInt(), 32, 255, 32)
            else -> HudDraw.accent(alpha)
        }
        override fun statusColor(alpha: Float) = HudDraw.fade(HudDraw.WHITE, alpha)
    }
}

private class HudFloatAnimation(private val durationMs: Long, initial: Float = 0f) {
    var value = initial
        private set
    private var lastUpdate = System.nanoTime()

    fun update(target: Float): Float {
        val now = System.nanoTime()
        val elapsedMs = (now - lastUpdate) / 1_000_000f
        lastUpdate = now
        val linear = (elapsedMs / durationMs.coerceAtLeast(1)).coerceIn(0f, 1f)
        val eased = 1f - (1f - linear) * (1f - linear) * (1f - linear)
        value += (target - value) * eased
        if (abs(target - value) < 0.001f) {
            value = target
        }
        return value
    }

    fun snap(target: Float) {
        value = target
        lastUpdate = System.nanoTime()
    }
}

private object HudDraw {
    const val WHITE: Int = -1
    const val TEXT_MUTED: Int = 0xFFB9C0CB.toInt()
    val HOTKEY_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-hotkey").apply {
        registerDynamicImageFromResources("hud/icons/hotkey.png")
    }
    val POTION_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-potion").apply {
        registerDynamicImageFromResources("hud/icons/potion.png")
    }
    val WYVERN_LOGO: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-wyvern-logo").apply {
        registerDynamicImageFromResources("hud/icons/wyvern_logo.png")
    }
    val SERVER_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-server").apply {
        registerDynamicImageFromResources("hud/icons/server.png")
    }
    val USER_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-user").apply {
        registerDynamicImageFromResources("hud/icons/user.png")
    }
    val FPS_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-icon-fps").apply {
        registerDynamicImageFromResources("hud/icons/fps.png")
    }
    val NOTIFY_CHECK_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-notify-check").apply {
        registerDynamicImageFromResources("clickgui/icons/tick.png")
    }
    val NOTIFY_CROSS_ICON: Identifier = Identifier.fromNamespaceAndPath("liquidbounce", "hud-notify-cross").apply {
        registerDynamicImageFromResources("clickgui/icons/x.png")
    }

    fun accent(alpha: Float = 1f): Int = fade(ClientTheme.accentA, alpha)
    fun accentB(alpha: Float = 1f): Int = fade(ClientTheme.accentB, alpha)

    fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or
            (red.coerceIn(0, 255) shl 16) or
            (green.coerceIn(0, 255) shl 8) or
            blue.coerceIn(0, 255)
    }

    fun fade(color: Int, factor: Float): Int {
        val alpha = (((color ushr 24) and 0xFF) * factor.coerceIn(0f, 1f)).toInt().coerceIn(0, 255)
        return ExosGuiRenderer.withAlpha(color, alpha)
    }

    fun blurPanel(context: DrawContext, x: Float, y: Float, width: Float, height: Float, radius: Float, alpha: Float) {
        // Real glass requires a rounded mask/capture/composite pass. The HUD
        // deliberately leaves this empty so no element can request fullscreen blur.
    }

    fun drawHeaderPanel(context: DrawContext, x: Float, y: Float, width: Float, height: Float, title: String, alpha: Float) {
        val headerHeight = 15f
        HudSurfaceRenderer.drawHeaderPanel(context, x, y, width, height, headerHeight, alpha)
        HudSurfaceRenderer.drawAccentStrip(context, x + 5f, y + headerHeight - 1f, width - 10f, alpha * 0.45f)
    }

    fun enableClip(
        context: DrawContext,
        originX: Float,
        originY: Float,
        x: Float,
        y: Float,
        width: Float,
        height: Float
    ) {
        if (width <= 0f || height <= 0f) {
            return
        }
        val scale = ModuleHud.hudScale.coerceIn(0.7f, 1.2f)
        val sx = originX + (x - originX) * scale
        val sy = originY + (y - originY) * scale
        val ex = sx + width * scale
        val ey = sy + height * scale
        ExosGuiRenderer.enableScissor(context.matrices, sx, sy, ex, ey)
    }

    fun disableClip(context: DrawContext) {
        ExosGuiRenderer.disableScissor(context.matrices)
    }

    fun title(context: DrawContext, text: String, x: Float, y: Float, size: Float, color: Int) {
        ExosGuiRenderer.title(context.matrices, text, x, y, color, size)
    }

    fun body(context: DrawContext, text: String, x: Float, y: Float, size: Float, color: Int) {
        ExosGuiRenderer.body(context.matrices, text, x, y, color, size)
    }

    fun titleWidth(text: String, size: Float): Float = ExosGuiRenderer.titleWidth(text, size)

    fun bodyWidth(text: String, size: Float): Float = ExosGuiRenderer.bodyWidth(text, size)

    fun textHeight(size: Float): Float = ExosGuiRenderer.bodyHeight(size)

    fun centeredTextY(centerY: Float, size: Float): Float = centerY - textHeight(size) * 0.5f

    fun centeredIconY(centerY: Float, size: Float): Float = centerY - size * 0.5f

    fun dot(context: DrawContext, x: Float, centerY: Float, size: Float, color: Int) {
        ExosGuiRenderer.roundedRect(context.matrices, x, centerY - size * 0.5f, size, size, size * 0.5f, color)
    }

    fun icon(context: DrawContext, icon: Identifier, x: Float, y: Float, size: Int, color: Int) {
        context.blit(
            RenderPipelines.GUI_TEXTURED,
            icon,
            x.roundToInt(),
            y.roundToInt(),
            0f,
            0f,
            size,
            size,
            size,
            size,
            color
        )
    }

    fun truncate(text: String, maxWidth: Float, size: Float): String {
        if (maxWidth <= 0f || bodyWidth(text, size) <= maxWidth) {
            return text
        }

        val suffix = ".."
        val suffixWidth = bodyWidth(suffix, size)
        var out = ""
        for (char in text) {
            val candidate = out + char
            if (bodyWidth(candidate, size) + suffixWidth > maxWidth) {
                break
            }
            out = candidate
        }
        return out + suffix
    }

    fun drawScaledItem(context: DrawContext, stack: ItemStack, x: Float, y: Float, scale: Float, alpha: Float) {
        if (stack.isEmpty || alpha <= 0.01f) {
            return
        }

        context.pose().pushMatrix()
        try {
            context.pose().translate(x, y)
            context.pose().scale(scale, scale)
            context.drawItem(stack, 0, 0)
            context.drawStackOverlay(mc.font, stack, 0, 0)
        } finally {
            context.pose().popMatrix()
        }
    }
}
