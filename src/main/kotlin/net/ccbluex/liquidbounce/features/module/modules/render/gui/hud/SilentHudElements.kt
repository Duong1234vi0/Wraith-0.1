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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.events.NotificationEvent
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.misc.nameprotect.ModuleNameProtect
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHud
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.render.gui.drawRoundedPlayerSkinFace
import net.ccbluex.liquidbounce.utils.client.ServerObserver
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.currentFps
import net.ccbluex.liquidbounce.utils.entity.deltaMovement
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screens.ChatScreen
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import org.lwjgl.glfw.GLFW
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

enum class SilentHudElementKind {
    WATERMARK,
    HOTKEYS,
    INFORMATION,
    NOTIFICATIONS,
    TARGET_HUD,
    STAFF_LIST,
}

data class SilentHudElementDescriptor(
    val name: String,
    val kind: SilentHudElementKind?,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val draggable: Boolean,
    val allowDragX: Boolean = draggable,
    val allowDragY: Boolean = draggable,
    val implemented: Boolean = true,
)

data class SilentHotKeyRow(
    val name: String,
    val bind: String,
    val category: Category,
)

interface HudNotificationSink {
    fun addModuleNotification(moduleName: String, enabled: Boolean)
    fun addTextNotification(title: String, message: String, severity: NotificationEvent.Severity)
}

object SilentHudLayout {
    const val MEDIA_PLAYER_BLOCKER = "Silent MediaPlayerInfo selects desktop DBus/native backends and Wraith has no " +
        "supported FCL/Android media session source."

    val silentHudRegistrationOrder = listOf(
        "Media Player",
        "Notifications",
        "Watermark",
        "HotKeys",
        "Information",
        "Target HUD",
        "Staff List",
    )

    val implementedDescriptors = listOf(
        SilentHudElementDescriptor("Notifications", SilentHudElementKind.NOTIFICATIONS, 0, 200, 160, 20, true, false, true),
        SilentHudElementDescriptor("Watermark", SilentHudElementKind.WATERMARK, 10, 6, 180, 24, false, false, false),
        SilentHudElementDescriptor("HotKeys", SilentHudElementKind.HOTKEYS, 30, 30, 92, 20, true),
        SilentHudElementDescriptor("Information", SilentHudElementKind.INFORMATION, 5, 5, 120, 20, false, false, false),
        SilentHudElementDescriptor(
            "TargetHUD",
            SilentHudElementKind.TARGET_HUD,
            10,
            80,
            CelestialTargetHudLayout.EDITOR_WIDTH,
            CelestialTargetHudLayout.EDITOR_HEIGHT,
            true,
        ),
        SilentHudElementDescriptor("StaffList", SilentHudElementKind.STAFF_LIST, 30, 150, 96, 20, true),
    )

    val mediaPlayerDescriptor = SilentHudElementDescriptor(
        "MediaPlayer",
        null,
        715,
        20,
        160,
        40,
        true,
        implemented = false,
    )

    fun targetHoldVisible(nowMs: Long, lastSeenMs: Long, holdMs: Long = 500L): Boolean =
        nowMs - lastSeenMs <= holdMs

    fun notificationY(baseY: Float, index: Int, rowHeight: Float, spacing: Float): Float =
        baseY + index * (rowHeight + spacing)

    fun smallScreenClamp(x: Float, y: Float, width: Float, height: Float, screenW: Float, screenH: Float): Pair<Float, Float> =
        x.coerceIn(0f, max(0f, screenW - width)) to y.coerceIn(0f, max(0f, screenH - height))

    fun hotKeyRows(modules: Iterable<ClientModule>): List<SilentHotKeyRow> =
        modules
            .filter { it.enabled && !it.hidden && !it.bind.isUnbound }
            .map { SilentHotKeyRow(it.name, it.bind.keyName, it.category) }
            .sortedBy { it.name.lowercase(Locale.US) }
}

abstract class SilentHudElementBase(
    descriptor: SilentHudElementDescriptor,
) : HudElement(descriptor.name, descriptor.x, descriptor.y, descriptor.width, descriptor.height) {
    protected val kind = descriptor.kind
    override val draggable = descriptor.draggable
    override val allowDragX = descriptor.allowDragX
    override val allowDragY = descriptor.allowDragY

    override fun isVisibleInHud(): Boolean =
        enabled && kind?.let(ModuleHud::isSilentElementVisible) == true
}

class SilentWatermarkElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.WATERMARK }) {

    override fun render(context: DrawContext, isSelected: Boolean) {
        setRuntimePosition(10, 6)

        val px = x.toFloat()
        val py = y.toFloat()
        val textSize = 8f
        val iconSize = textSize + 2f
        val paddingX = 8f
        val paddingY = 4f
        val gap = 4f
        val height = paddingY * 2f + max(SilentHudDraw.bodyHeight(textSize), SilentHudDraw.iconHeight(iconSize))
        val username = ModuleNameProtect.replace(mc.user.name.ifBlank { "Player" })
        val server = currentServerLabel()
        val fps = mc.currentFps.toString()
        val tps = formatTps(ServerObserver.tps)
        val label = animatedClientLabel(System.currentTimeMillis())

        val sections = listOf(
            Segment("i", label),
            Segment("N", username),
            Segment("L", server),
            Segment("t", fps),
            Segment("q", tps),
        )
        val contentWidth = sections.sumOf { segment ->
            (SilentHudDraw.iconWidth(segment.icon, iconSize) + gap + SilentHudDraw.bodyWidth(segment.text, textSize)).toDouble()
        }.toFloat() + gap * (sections.size - 1)
        val totalWidth = paddingX * 2f + contentWidth

        width = ceil(totalWidth).toInt().coerceAtLeast(1)
        this.height = ceil(height).toInt().coerceAtLeast(1)
        serverCopyRect = null

        SilentHudDraw.panel(context, px, py, totalWidth, height, 5f, 1f)
        var cursorX = px + paddingX
        val centerY = py + height * 0.5f
        sections.forEach { segment ->
            val iconY = SilentHudDraw.centeredY(centerY, iconSize)
            val textY = SilentHudDraw.centeredY(centerY, textSize)
            SilentHudDraw.icon(context, segment.icon, cursorX, iconY, iconSize, SilentHudDraw.accent())
            cursorX += SilentHudDraw.iconWidth(segment.icon, iconSize) + gap
            val textW = SilentHudDraw.bodyWidth(segment.text, textSize)
            if (segment.text == server) {
                serverCopyRect = SilentRect(cursorX, py, textW, height)
            }
            SilentHudDraw.body(context, segment.text, cursorX, textY, textSize, SilentHudDraw.WHITE)
            cursorX += textW + gap
        }

        renderBackground(context, isSelected)
    }

    override fun onChatClick(mouseX: Int, mouseY: Int, button: Int): Boolean {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return false
        }

        val rect = serverCopyRect ?: return false
        val server = currentServerLabel()
        if (server == SINGLEPLAYER || !rect.contains(mouseX.toFloat(), mouseY.toFloat())) {
            return false
        }

        mc.keyboardHandler.clipboard = server
        return true
    }

    private var serverCopyRect: SilentRect? = null

    private data class Segment(val icon: String, val text: String)

    private fun animatedClientLabel(nowMs: Long): String {
        val labels = listOf(
            "Wraith ${LiquidBounce.clientVersion}",
            "Minecraft 26.1.2",
            "build: ${LiquidBounce.clientCommit}",
        )
        val slot = (nowMs / 5000L).floorMod(labels.size)
        val phase = (nowMs % 5000L).toFloat() / 5000f
        val text = labels[slot]
        val visible = when {
            phase < 0.18f -> (text.length * (phase / 0.18f)).roundToInt()
            phase > 0.86f -> (text.length * ((1f - phase) / 0.14f)).roundToInt()
            else -> text.length
        }.coerceIn(1, text.length)
        return text.substring(0, visible)
    }

    private fun currentServerLabel(): String =
        mc.currentServer?.ip ?: SINGLEPLAYER

    private fun formatTps(value: Double): String =
        if (value.isFinite()) String.format(Locale.US, "%.1f", value.coerceIn(0.0, 20.0)) else "--"

    private fun Long.floorMod(size: Int): Int = Math.floorMod(this.toInt(), size)

    private companion object {
        const val SINGLEPLAYER = "Singleplayer"
    }
}

class SilentHotKeysElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.HOTKEYS }) {

    private val states = linkedMapOf<String, RowState>()
    private var smoothedHeight = height.toFloat()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val rows = SilentHudLayout.hotKeyRows(ModuleManager)
        val activeKeys = rows.mapTo(mutableSetOf()) { it.name }
        rows.forEach { row ->
            states.getOrPut(row.name) { RowState() }.row = row
        }
        states.values.forEach { state ->
            state.exiting = state.row?.name !in activeKeys
        }
        states.entries.removeIf { it.value.exiting && it.value.progress < 0.02f }

        val paddingX = 6f
        val paddingY = 4f
        val textSize = 8f
        val iconSize = textSize + 2f
        val gap = 4f
        val lineSpacing = 2f
        val lineHeight = max(SilentHudDraw.bodyHeight(textSize), SilentHudDraw.iconHeight(iconSize))
        val visibleStates = states.values.toList()
        val targetHeight = paddingY * 2f + lineHeight + visibleStates.sumOf {
            ((lineHeight + lineSpacing) * it.update()).toDouble()
        }.toFloat()
        smoothedHeight += (targetHeight - smoothedHeight) * 0.15f

        val headerWidth = SilentHudDraw.iconWidth("k", iconSize) + gap + SilentHudDraw.bodyWidth("HotKeys", textSize)
        val rowWidth = visibleStates.maxOfOrNull { state ->
            val row = state.row ?: return@maxOfOrNull 0f
            SilentHudDraw.iconWidth(categoryIcon(row.category), iconSize) + gap +
                SilentHudDraw.bodyWidth(row.name, textSize) + gap + SilentHudDraw.bodyWidth("[${row.bind}]", textSize)
        } ?: 0f
        val totalWidth = paddingX * 2f + max(headerWidth, rowWidth)
        width = ceil(totalWidth).toInt().coerceAtLeast(1)
        height = ceil(smoothedHeight).toInt().coerceAtLeast(1)

        val px = x.toFloat()
        val py = y.toFloat()
        SilentHudDraw.panel(context, px, py, totalWidth, smoothedHeight, 5f, 1f)
        drawHeader(context, px + paddingX, py + paddingY, lineHeight, textSize, iconSize, "k", "HotKeys")

        var rowY = py + paddingY + lineHeight + lineSpacing
        visibleStates.forEach { state ->
            val row = state.row ?: return@forEach
            val progress = state.progress
            val alpha = progress.coerceIn(0f, 1f)
            val offsetX = -5f * (1f - progress)
            val rowCenter = rowY + lineHeight * 0.5f
            SilentHudDraw.icon(
                context,
                categoryIcon(row.category),
                px + paddingX + offsetX,
                SilentHudDraw.centeredY(rowCenter, iconSize),
                iconSize,
                SilentHudDraw.fade(SilentHudDraw.accent(), alpha)
            )
            var cursorX = px + paddingX + SilentHudDraw.iconWidth(categoryIcon(row.category), iconSize) + gap + offsetX
            SilentHudDraw.body(
                context,
                row.name,
                cursorX,
                SilentHudDraw.centeredY(rowCenter, textSize),
                textSize,
                SilentHudDraw.fade(SilentHudDraw.WHITE, alpha)
            )
            cursorX += SilentHudDraw.bodyWidth(row.name, textSize) + gap
            SilentHudDraw.body(
                context,
                "[${row.bind}]",
                cursorX,
                SilentHudDraw.centeredY(rowCenter, textSize),
                textSize,
                SilentHudDraw.fade(0xFFB4B4B4.toInt(), alpha)
            )
            rowY += (lineHeight + lineSpacing) * progress
        }

        renderBackground(context, isSelected)
    }

    override fun resetState() {
        states.clear()
        smoothedHeight = height.toFloat()
    }

    private fun drawHeader(
        context: DrawContext,
        x: Float,
        y: Float,
        lineHeight: Float,
        textSize: Float,
        iconSize: Float,
        icon: String,
        title: String,
    ) {
        val centerY = y + lineHeight * 0.5f
        SilentHudDraw.icon(context, icon, x, SilentHudDraw.centeredY(centerY, iconSize), iconSize, SilentHudDraw.accent())
        SilentHudDraw.body(
            context,
            title,
            x + SilentHudDraw.iconWidth(icon, iconSize) + 4f,
            SilentHudDraw.centeredY(centerY, textSize),
            textSize,
            SilentHudDraw.WHITE
        )
    }

    private fun categoryIcon(category: Category): String = when (category) {
        Category.COMBAT -> "h"
        Category.MOVEMENT -> "g"
        Category.PLAYER -> "f"
        Category.RENDER -> "e"
        Category.MISC -> "P"
    }

    private class RowState {
        var row: SilentHotKeyRow? = null
        var progress = 0f
        var exiting = false

        fun update(): Float {
            val target = if (exiting) 0f else 1f
            progress += (target - progress) * 0.22f
            if (progress.isNaN()) {
                progress = target
            }
            return progress.coerceIn(0f, 1f)
        }
    }
}

class SilentInformationElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.INFORMATION }) {

    private var smoothedBps = 0f
    private var smoothedY = Float.NaN
    private var coordRect: SilentRect? = null

    override fun render(context: DrawContext, isSelected: Boolean) {
        val player = mc.player
        val textSize = 8f
        val iconSize = textSize + 2f
        val paddingX = 8f
        val paddingY = 4f
        val gap = 4f
        val bps = if (player != null) {
            val motion = player.deltaMovement
            hypot(motion.x, motion.z).toFloat() * 20f
        } else {
            0f
        }
        smoothedBps += (bps - smoothedBps) * 0.2f
        val bpsText = String.format(Locale.US, "%.1f bps", smoothedBps)
        val coords = player?.let {
            "${floor(it.x).toInt()},${floor(it.y).toInt()},${floor(it.z).toInt()}"
        } ?: "0,0,0"

        val lineHeight = max(SilentHudDraw.bodyHeight(textSize), SilentHudDraw.iconHeight(iconSize))
        val totalHeight = paddingY * 2f + lineHeight
        val totalWidth = paddingX * 2f +
            SilentHudDraw.iconWidth("J", iconSize) + gap + SilentHudDraw.bodyWidth(bpsText, textSize) + gap +
            SilentHudDraw.iconWidth("j", iconSize) + gap + SilentHudDraw.bodyWidth(coords, textSize)
        val targetY = mc.window.guiScaledHeight - totalHeight - 5f - if (mc.screen is ChatScreen) 13f else 0f
        if (!smoothedY.isFinite()) {
            smoothedY = targetY
        } else {
            smoothedY += (targetY - smoothedY) * 0.15f
        }
        setRuntimePosition(5, smoothedY.roundToInt())
        width = ceil(totalWidth).toInt().coerceAtLeast(1)
        height = ceil(totalHeight).toInt().coerceAtLeast(1)
        coordRect = null

        val px = x.toFloat()
        val py = y.toFloat()
        SilentHudDraw.panel(context, px, py, totalWidth, totalHeight, 5f, 1f)
        var cursorX = px + paddingX
        val centerY = py + totalHeight * 0.5f
        SilentHudDraw.icon(context, "J", cursorX, SilentHudDraw.centeredY(centerY, iconSize), iconSize, SilentHudDraw.accent())
        cursorX += SilentHudDraw.iconWidth("J", iconSize) + gap
        SilentHudDraw.body(context, bpsText, cursorX, SilentHudDraw.centeredY(centerY, textSize), textSize, SilentHudDraw.WHITE)
        cursorX += SilentHudDraw.bodyWidth(bpsText, textSize) + gap
        SilentHudDraw.icon(context, "j", cursorX, SilentHudDraw.centeredY(centerY, iconSize), iconSize, SilentHudDraw.accent())
        cursorX += SilentHudDraw.iconWidth("j", iconSize) + gap
        val coordsWidth = SilentHudDraw.bodyWidth(coords, textSize)
        coordRect = SilentRect(cursorX, py, coordsWidth, totalHeight)
        SilentHudDraw.body(context, coords, cursorX, SilentHudDraw.centeredY(centerY, textSize), textSize, SilentHudDraw.WHITE)

        renderBackground(context, isSelected)
    }

    override fun onChatClick(mouseX: Int, mouseY: Int, button: Int): Boolean {
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return false
        }
        val player = mc.player ?: return false
        val rect = coordRect ?: return false
        if (!rect.contains(mouseX.toFloat(), mouseY.toFloat())) {
            return false
        }
        mc.keyboardHandler.clipboard = "${floor(player.x).toInt()},${floor(player.y).toInt()},${floor(player.z).toInt()}"
        return true
    }

    override fun resetState() {
        smoothedBps = 0f
        smoothedY = Float.NaN
        coordRect = null
    }
}

class CelestialTargetHudElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.TARGET_HUD }) {

    private var lastTarget: LivingEntity? = null
    private var lastSeenMs = 0L
    private var visibility = 0f
    private var animatedTarget: LivingEntity? = null
    private val healthAnimation = CelestialTargetHealthAnimation()

    override fun render(context: DrawContext, isSelected: Boolean) {
        width = CelestialTargetHudLayout.EDITOR_WIDTH
        height = CelestialTargetHudLayout.EDITOR_HEIGHT

        val now = System.currentTimeMillis()
        val current = ModuleKillAura.targetTracker.target as? LivingEntity
        val chatPreview = current == null && (mc.screen is ChatScreen || mc.screen is HudScreen)
        val target = current ?: if (chatPreview) mc.player else null
        if (target != null) {
            lastTarget = target
            lastSeenMs = now
        }
        val heldTarget = lastTarget
        val shouldShow = heldTarget != null && (target != null || SilentHudLayout.targetHoldVisible(now, lastSeenMs))
        visibility += ((if (shouldShow) 1f else 0f) - visibility) * 0.24f
        val alpha = visibility.coerceIn(0f, 1f)
        if (alpha <= 0.01f || heldTarget == null) {
            return
        }

        val px = x.toFloat()
        val py = y.toFloat()
        val nameHeight = ExosGuiRenderer.titleHeight(CelestialTargetHudLayout.NAME_SIZE)
        val geometry = CelestialTargetHudLayout.geometry(px, py, nameHeight)
        if (animatedTarget !== heldTarget) {
            animatedTarget = heldTarget
            healthAnimation.resetForTarget(heldTarget.health, heldTarget.maxHealth)
        }
        val health = healthAnimation.update(heldTarget.health, heldTarget.maxHealth)
        val accent = SilentHudDraw.accent()

        ExosGuiRenderer.shaderGlow(
            context.matrices,
            geometry.panel.x,
            geometry.panel.y,
            geometry.panel.width,
            geometry.panel.height,
            CelestialTargetHudLayout.PANEL_RADIUS,
            SilentHudDraw.fade(accent, alpha),
            CelestialTargetHudLayout.SHADOW_SPREAD,
        )
        ExosGuiRenderer.roundedRect(
            context.matrices,
            geometry.panel.x,
            geometry.panel.y,
            geometry.panel.width,
            geometry.panel.height,
            CelestialTargetHudLayout.PANEL_RADIUS,
            SilentHudDraw.fade(0xFF080F1C.toInt(), alpha),
        )
        ExosGuiRenderer.roundedOutline(
            context.matrices,
            geometry.panel.x,
            geometry.panel.y,
            geometry.panel.width,
            geometry.panel.height,
            CelestialTargetHudLayout.PANEL_RADIUS,
            SilentHudDraw.fade(accent, alpha),
            CelestialTargetHudLayout.OUTLINE_WIDTH,
        )
        drawFace(
            context,
            heldTarget,
            geometry.head.x,
            geometry.head.y,
            geometry.head.width,
            alpha,
        )

        val name = ModuleNameProtect.replace(
            if (heldTarget is Player) heldTarget.gameProfile.name else heldTarget.scoreboardName
        )
        val clip = geometry.nameClip
        context.enableScissor(
            floor(clip.x).toInt(),
            floor(clip.y).toInt(),
            ceil(clip.x + clip.width).toInt(),
            ceil(clip.y + clip.height).toInt(),
        )
        try {
            ExosGuiRenderer.title(
                context.matrices,
                name,
                geometry.textX,
                geometry.nameY,
                SilentHudDraw.fade(SilentHudDraw.WHITE, alpha),
                CelestialTargetHudLayout.NAME_SIZE,
            )
        } finally {
            context.disableScissor()
        }

        SilentHudDraw.body(
            context,
            String.format(Locale.US, "HP: %.1f", health.health),
            geometry.textX,
            geometry.healthTextY,
            CelestialTargetHudLayout.HEALTH_TEXT_SIZE,
            SilentHudDraw.fade(SilentHudDraw.WHITE, alpha),
        )
        ExosGuiRenderer.roundedRect(
            context.matrices,
            geometry.healthTrack.x,
            geometry.healthTrack.y,
            geometry.healthTrack.width,
            geometry.healthTrack.height,
            CelestialTargetHudLayout.BAR_RADIUS,
            SilentHudDraw.fade(0xFF232323.toInt(), alpha),
        )
        val fillWidth = CelestialTargetHudLayout.fillWidth(geometry.healthTrack.width, health.ratio)
        if (fillWidth > 0f) {
            ExosGuiRenderer.roundedRect(
                context.matrices,
                geometry.healthTrack.x,
                geometry.healthTrack.y,
                fillWidth,
                geometry.healthFillHeight,
                CelestialTargetHudLayout.BAR_RADIUS,
                ExosGuiRenderer.withAlpha(accent, (200f * alpha).roundToInt()),
            )
        }

        renderBackground(context, isSelected)
    }

    override fun resetState() {
        lastTarget = null
        lastSeenMs = 0L
        visibility = 0f
        animatedTarget = null
        healthAnimation.reset()
    }

    private fun drawFace(context: DrawContext, target: LivingEntity, x: Float, y: Float, size: Float, alpha: Float) {
        if (target is Player && context.drawRoundedPlayerSkinFace(
                target,
                x,
                y,
                size,
                CelestialTargetHudLayout.HEAD_RADIUS,
                SilentHudDraw.fade(SilentHudDraw.WHITE, alpha),
            )
        ) {
            return
        }
        ExosGuiRenderer.roundedRect(
            context.matrices,
            x,
            y,
            size,
            size,
            CelestialTargetHudLayout.HEAD_RADIUS,
            SilentHudDraw.fade(0xFF080F1C.toInt(), alpha),
        )
        val letter = target.scoreboardName.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
        val textW = SilentHudDraw.bodyWidth(letter, 10f)
        SilentHudDraw.body(context, letter, x + (size - textW) * 0.5f, y + 6.5f, 10f, SilentHudDraw.fade(SilentHudDraw.WHITE, alpha))
    }
}

class SilentStaffListElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.STAFF_LIST }) {

    private val states = linkedMapOf<String, StaffState>()
    private var smoothedHeight = height.toFloat()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val rows = staffRows()
        val activeKeys = rows.mapTo(mutableSetOf()) { it.name }
        rows.forEach { row -> states.getOrPut(row.name) { StaffState() }.row = row }
        states.values.forEach { state -> state.exiting = state.row?.name !in activeKeys }
        states.entries.removeIf { it.value.exiting && it.value.progress < 0.02f }

        val paddingX = 6f
        val paddingY = 4f
        val textSize = 8f
        val iconSize = textSize + 2f
        val gap = 4f
        val lineSpacing = 2f
        val lineHeight = max(SilentHudDraw.bodyHeight(textSize), SilentHudDraw.iconHeight(iconSize))
        val visibleStates = states.values.toList()
        val targetHeight = paddingY * 2f + lineHeight + visibleStates.sumOf {
            ((lineHeight + lineSpacing) * it.update()).toDouble()
        }.toFloat()
        smoothedHeight += (targetHeight - smoothedHeight) * 0.15f

        val headerWidth = SilentHudDraw.iconWidth("f", iconSize) + gap + SilentHudDraw.bodyWidth("Staff List", textSize)
        val rowWidth = visibleStates.maxOfOrNull { state ->
            val row = state.row ?: return@maxOfOrNull 0f
            SilentHudDraw.bodyWidth(row.name, textSize) + gap + SilentHudDraw.bodyWidth("[${row.rank}]", textSize)
        } ?: 0f
        val totalWidth = paddingX * 2f + max(headerWidth, rowWidth)
        width = ceil(totalWidth).toInt().coerceAtLeast(1)
        height = ceil(smoothedHeight).toInt().coerceAtLeast(1)

        val px = x.toFloat()
        val py = y.toFloat()
        SilentHudDraw.panel(context, px, py, totalWidth, smoothedHeight, 5f, 1f)
        drawHeader(context, px + paddingX, py + paddingY, lineHeight, textSize, iconSize)

        var rowY = py + paddingY + lineHeight + lineSpacing
        visibleStates.forEach { state ->
            val row = state.row ?: return@forEach
            val progress = state.progress
            val alpha = progress.coerceIn(0f, 1f)
            val offsetX = -5f * (1f - progress)
            val yText = SilentHudDraw.centeredY(rowY + lineHeight * 0.5f, textSize)
            val nameX = px + paddingX + offsetX
            SilentHudDraw.body(context, row.name, nameX, yText, textSize, SilentHudDraw.fade(SilentHudDraw.WHITE, alpha))
            SilentHudDraw.body(
                context,
                "[${row.rank}]",
                nameX + SilentHudDraw.bodyWidth(row.name, textSize) + gap,
                yText,
                textSize,
                SilentHudDraw.fade(SilentHudDraw.accent(), alpha)
            )
            rowY += (lineHeight + lineSpacing) * progress
        }

        renderBackground(context, isSelected)
    }

    override fun resetState() {
        states.clear()
        smoothedHeight = height.toFloat()
    }

    private fun drawHeader(context: DrawContext, x: Float, y: Float, lineHeight: Float, textSize: Float, iconSize: Float) {
        val centerY = y + lineHeight * 0.5f
        SilentHudDraw.icon(context, "f", x, SilentHudDraw.centeredY(centerY, iconSize), iconSize, SilentHudDraw.accent())
        SilentHudDraw.body(
            context,
            "Staff List",
            x + SilentHudDraw.iconWidth("f", iconSize) + 4f,
            SilentHudDraw.centeredY(centerY, textSize),
            textSize,
            SilentHudDraw.WHITE
        )
    }

    private fun staffRows(): List<StaffRow> {
        val connection = mc.connection ?: return if (mc.screen is ChatScreen) listOf(StaffRow("xolizer", "Owner")) else emptyList()
        val rows = connection.onlinePlayers.mapNotNull { entry ->
            val profile = entry.profile ?: return@mapNotNull null
            val name = profile.name ?: return@mapNotNull null
            val display = entry.tabListDisplayName?.string ?: return@mapNotNull null
            val rank = staffRank(display) ?: return@mapNotNull null
            StaffRow(name, rank)
        }.distinctBy { it.name }
        return if (rows.isEmpty() && mc.screen is ChatScreen) listOf(StaffRow("xolizer", "Owner")) else rows
    }

    private fun staffRank(display: String): String? =
        STAFF_PREFIXES.firstNotNullOfOrNull { (needle, short) ->
            short.takeIf { display.contains(needle, ignoreCase = true) }
        }

    private data class StaffRow(val name: String, val rank: String)

    private class StaffState {
        var row: StaffRow? = null
        var progress = 0f
        var exiting = false

        fun update(): Float {
            val target = if (exiting) 0f else 1f
            progress += (target - progress) * 0.22f
            return progress.coerceIn(0f, 1f)
        }
    }

    private companion object {
        val STAFF_PREFIXES = linkedMapOf(
            "Owner" to "Owner",
            "Admin" to "Admin",
            "Moderator" to "Moder",
            "Moder" to "Moder",
            "Helper" to "Helper",
            "Владелец" to "Owner",
            "Админ" to "Admin",
            "Модератор" to "Moder",
            "Хелпер" to "Helper",
        )
    }
}

class SilentNotificationElement :
    SilentHudElementBase(SilentHudLayout.implementedDescriptors.first { it.kind == SilentHudElementKind.NOTIFICATIONS }),
    HudNotificationSink {

    override val allowDragX = false
    override val allowDragY = true
    private val entries = ArrayDeque<NotificationEntry>()
    private var animatedBaseY = y.toFloat()

    override fun render(context: DrawContext, isSelected: Boolean) {
        val now = System.currentTimeMillis()
        entries.removeAll { it.expired(now) && it.progress < 0.03f }
        entries.forEach { it.update(now) }

        val renderEntries = if (entries.isEmpty() && mc.screen is ChatScreen) {
            listOf(
                NotificationEntry(
                    listOf(TextPart("Пример уведомления", SilentHudDraw.WHITE)),
                    "P",
                    now,
                    Long.MAX_VALUE,
                ).apply { progress = 1f }
            )
        } else {
            entries.toList()
        }

        val targetBaseY = if (y == 0) mc.window.guiScaledHeight * 0.5f + 10f else y.toFloat()
        animatedBaseY += (targetBaseY - animatedBaseY) * 0.25f

        val textSize = 8f
        val iconSize = textSize + 1.5f
        val paddingX = 8f
        val paddingY = 4f
        val iconGap = 0.5f
        val spacing = 3f
        val lineHeight = max(SilentHudDraw.bodyHeight(textSize), SilentHudDraw.iconHeight(iconSize))
        var maxWidth = 1f
        renderEntries.forEachIndexed { index, entry ->
            val alpha = entry.progress.coerceIn(0f, 1f)
            val iconWidth = entry.icon?.let { SilentHudDraw.iconWidth(it, iconSize) + iconGap } ?: 0f
            val textWidth = entry.parts.sumOf { SilentHudDraw.bodyWidth(it.text, textSize).toDouble() }.toFloat()
            val rowWidth = paddingX * 2f + iconWidth + textWidth
            val rowHeight = paddingY * 2f + lineHeight
            maxWidth = max(maxWidth, rowWidth)
            val rowY = SilentHudLayout.notificationY(animatedBaseY, index, rowHeight, spacing)
            val rowX = (mc.window.guiScaledWidth - rowWidth) * 0.5f
            drawEntry(context, entry, rowX, rowY, rowWidth, rowHeight, alpha, textSize, iconSize, paddingX, iconGap)
        }

        x = ((mc.window.guiScaledWidth - maxWidth) * 0.5f).roundToInt()
        width = ceil(maxWidth).toInt().coerceAtLeast(1)
        height = ceil(renderEntries.size * (paddingY * 2f + lineHeight + spacing)).toInt().coerceAtLeast(1)

        renderBackground(context, isSelected)
    }

    override fun addModuleNotification(moduleName: String, enabled: Boolean) {
        push(
            listOf(
                TextPart("Module ", SilentHudDraw.WHITE),
                TextPart(moduleName, SilentHudDraw.accent()),
                TextPart(if (enabled) " enabled" else " disabled", SilentHudDraw.WHITE),
            ),
            "P",
        )
    }

    override fun addTextNotification(title: String, message: String, severity: NotificationEvent.Severity) {
        val text = listOfNotNull(title.takeIf { it.isNotBlank() }, message.takeIf { it.isNotBlank() })
            .joinToString(": ")
            .ifBlank { severity.name.lowercase(Locale.US) }
        push(listOf(TextPart(text, SilentHudDraw.WHITE)), iconFor(severity))
    }

    override fun resetState() {
        entries.clear()
        animatedBaseY = y.toFloat()
    }

    private fun push(parts: List<TextPart>, icon: String?) {
        while (entries.size >= 15) {
            entries.removeFirst()
        }
        entries.addLast(NotificationEntry(parts, icon, System.currentTimeMillis(), 2000L))
    }

    private fun drawEntry(
        context: DrawContext,
        entry: NotificationEntry,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        alpha: Float,
        textSize: Float,
        iconSize: Float,
        paddingX: Float,
        iconGap: Float,
    ) {
        val scale = 0.85f + alpha * 0.15f
        val centerX = x + width * 0.5f
        val centerY = y + height * 0.5f
        context.pose().pushMatrix()
        try {
            context.pose().translate(centerX, centerY)
            context.pose().scale(scale, scale)
            context.pose().translate(-centerX, -centerY)
            SilentHudDraw.panel(context, x, y, width, height, 5f, alpha)
            var cursorX = x + paddingX
            entry.icon?.let { icon ->
                SilentHudDraw.icon(
                    context,
                    icon,
                    cursorX - 2f,
                    SilentHudDraw.centeredY(centerY, iconSize),
                    iconSize,
                    SilentHudDraw.fade(SilentHudDraw.accent(), alpha)
                )
                cursorX += SilentHudDraw.iconWidth(icon, iconSize) + iconGap
            }
            entry.parts.forEach { part ->
                SilentHudDraw.body(
                    context,
                    part.text,
                    cursorX,
                    SilentHudDraw.centeredY(centerY, textSize),
                    textSize,
                    SilentHudDraw.fade(part.color, alpha)
                )
                cursorX += SilentHudDraw.bodyWidth(part.text, textSize)
            }
        } finally {
            context.pose().popMatrix()
        }
    }

    private fun iconFor(severity: NotificationEvent.Severity): String = when (severity) {
        NotificationEvent.Severity.ERROR -> "x"
        NotificationEvent.Severity.SUCCESS -> "P"
        NotificationEvent.Severity.INFO,
        NotificationEvent.Severity.ENABLED,
        NotificationEvent.Severity.DISABLED -> "P"
    }

    private data class TextPart(val text: String, val color: Int)

    private class NotificationEntry(
        val parts: List<TextPart>,
        val icon: String?,
        val createdMs: Long,
        val durationMs: Long,
    ) {
        var progress = 0f

        fun expired(now: Long): Boolean = now - createdMs > durationMs

        fun update(now: Long) {
            val target = if (expired(now)) 0f else 1f
            progress += (target - progress) * 0.24f
            progress = progress.coerceIn(0f, 1f)
        }
    }
}

private object SilentHudDraw {
    const val WHITE = -1

    fun accent(): Int = ClientTheme.accentA

    fun panel(context: DrawContext, x: Float, y: Float, width: Float, height: Float, radius: Float, alpha: Float) {
        ExosGuiRenderer.roundedRect(context.matrices, x, y, width, height, radius, fade(0xA6000000.toInt(), alpha))
    }

    fun body(context: DrawContext, text: String, x: Float, y: Float, size: Float, color: Int) {
        ExosGuiRenderer.clickGuiBody(context.matrices, text, x, y, color, size)
    }

    fun icon(context: DrawContext, text: String, x: Float, y: Float, size: Float, color: Int) {
        ExosGuiRenderer.clickGuiIcon(context.matrices, text, x, y, color, size)
    }

    fun bodyWidth(text: String, size: Float): Float = ExosGuiRenderer.clickGuiBodyWidth(text, size)

    fun iconWidth(text: String, size: Float): Float = ExosGuiRenderer.clickGuiIconWidth(text, size)

    fun bodyHeight(size: Float): Float = ExosGuiRenderer.bodyHeight(size)

    fun iconHeight(size: Float): Float = ExosGuiRenderer.bodyHeight(size)

    fun centeredY(centerY: Float, size: Float): Float =
        centerY - bodyHeight(size) * 0.5f

    fun fade(color: Int, alpha: Float): Int {
        val baseAlpha = (color ushr 24) and 0xFF
        return ExosGuiRenderer.withAlpha(color, (baseAlpha * alpha.coerceIn(0f, 1f)).roundToInt())
    }

}

private data class SilentRect(val x: Float, val y: Float, val w: Float, val h: Float) {
    val right: Float get() = x + w
    val bottom: Float get() = y + h

    fun contains(px: Float, py: Float): Boolean =
        px >= x && px <= right && py >= y && py <= bottom
}
