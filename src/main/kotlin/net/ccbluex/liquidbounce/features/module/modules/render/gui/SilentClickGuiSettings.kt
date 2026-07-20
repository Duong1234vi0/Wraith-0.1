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

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.config.types.ActionValue
import net.ccbluex.liquidbounce.config.types.BindValue
import net.ccbluex.liquidbounce.config.types.ChooseListValue
import net.ccbluex.liquidbounce.config.types.MultiChooseListValue
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.RangedValue
import net.ccbluex.liquidbounce.config.types.Value
import net.ccbluex.liquidbounce.config.types.ValueType
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.input.MouseButtonEvent
import org.lwjgl.glfw.GLFW
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

class SilentClickGuiSettings {

    data class SettingEntry(
        val value: Value<*>,
        val depth: Int,
        val sectionOnly: Boolean = false,
    )

    data class RenderResult(
        val contentHeight: Float,
        val maxScroll: Float,
    )

    private data class SettingHit(
        val entry: SettingEntry,
        val rect: SilentRect,
        val viewport: SilentRect,
    )

    private val settingHits = mutableListOf<SettingHit>()
    private val renderedValueIds = mutableSetOf<Int>()
    private val expandedSelectors = mutableSetOf<Int>()
    private val switchProgress = mutableMapOf<Int, Float>()

    private var dragging: SettingHit? = null
    private var draggingRangeHandle: SilentClickGuiSettingsMath.RangeHandle? = null
    private var bindListening: BindValue? = null

    val isDragging: Boolean
        get() = dragging != null

    val isListeningForBind: Boolean
        get() = bindListening != null

    fun beginFrame() {
        settingHits.clear()
        renderedValueIds.clear()
    }

    fun finishFrame() {
        val validIds = renderedValueIds
        expandedSelectors.retainAll(validIds)

        dragging?.takeUnless { idOf(it.entry.value) in validIds }?.let {
            clearDrag()
        }
        bindListening?.takeUnless { idOf(it) in validIds }?.let {
            bindListening = null
        }
    }

    fun clearTransient() {
        clearDrag()
        bindListening = null
    }

    fun clearDrag() {
        dragging = null
        draggingRangeHandle = null
    }

    fun clearBindListening() {
        bindListening = null
    }

    fun visibleSettings(configurable: Configurable): List<SettingEntry> {
        val output = mutableListOf<SettingEntry>()

        fun visit(value: Value<*>, depth: Int) {
            if (!shouldShow(value)) {
                return
            }

            when (value) {
                is ToggleableConfigurable -> {
                    output += SettingEntry(value, depth)
                    if (value.enabled) {
                        value.get().forEach { child ->
                            if (!child.name.equals("Enabled", ignoreCase = true)) {
                                visit(child, depth + 1)
                            }
                        }
                    }
                }

                is ChoiceConfigurable<*> -> {
                    output += SettingEntry(value, depth)
                    value.activeChoice.get().forEach { child ->
                        visit(child, depth + 1)
                    }
                }

                is Configurable -> {
                    output += SettingEntry(value, depth, sectionOnly = true)
                    value.get().forEach { child -> visit(child, depth + 1) }
                }

                else -> output += SettingEntry(value, depth)
            }
        }

        configurable.get().forEach { visit(it, 0) }
        return output
    }

    fun contentHeight(entries: List<SettingEntry>): Float =
        entries.sumOf { settingHeight(it).toDouble() }.toFloat()

    fun render(
        context: DrawContext,
        viewport: SilentRect,
        entries: List<SettingEntry>,
        scroll: Float,
        mouseX: Float,
        mouseY: Float,
        alpha: Float,
    ): RenderResult {
        val contentHeight = contentHeight(entries)
        val clampedScroll = SilentClickGuiSettingsMath.clampScroll(scroll, contentHeight, viewport.h)
        val maxScroll = max(0f, contentHeight - viewport.h)

        var y = viewport.y - clampedScroll
        entries.forEach { entry ->
            val height = settingHeight(entry)
            val row = SilentRect(
                viewport.x + 4f + entry.depth * 4.5f,
                y,
                viewport.w - 8f - entry.depth * 4.5f,
                height - 2f
            )

            renderedValueIds += idOf(entry.value)
            if (row.bottom >= viewport.y && row.y <= viewport.bottom) {
                settingHits += SettingHit(entry, row, viewport)
                drawSetting(context, row, entry, mouseX, mouseY, alpha)
            }
            y += height
        }

        if (maxScroll > 0.5f) {
            val thumbHeight = (viewport.h * viewport.h / contentHeight).coerceIn(14f, viewport.h)
            val thumbY = viewport.y + (viewport.h - thumbHeight) * (clampedScroll / maxScroll)
            ExosGuiRenderer.roundedRect(
                context.matrices,
                viewport.right - 3f,
                thumbY,
                1.5f,
                thumbHeight,
                0.75f,
                fade(0x80FFFFFF.toInt(), alpha)
            )
        }

        return RenderResult(contentHeight, maxScroll)
    }

    fun handleMouseBind(event: MouseButtonEvent): Boolean {
        val listening = bindListening ?: return false
        listening.get().bind(InputConstants.Type.MOUSE.getOrCreate(event.button()))
        bindListening = null
        return true
    }

    fun handleKey(event: KeyEvent): Boolean {
        val listening = bindListening ?: return false
        if (event.isEscape()) {
            listening.get().unbind()
        } else {
            listening.get().bind(InputConstants.Type.KEYSYM.getOrCreate(event.key()))
        }
        bindListening = null
        return true
    }

    fun handleClick(mouseX: Float, mouseY: Float, button: Int): Boolean {
        val hit = settingHits.asReversed().firstOrNull { it.rect.contains(mouseX, mouseY) } ?: return false
        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            return true
        }

        when (val value = hit.entry.value) {
            is BindValue -> bindListening = value
            is ActionValue -> value.execute()
            is ToggleableConfigurable -> value.enabled = !value.enabled
            is ChoiceConfigurable<*> -> handleChoiceClick(value, hit.rect, mouseX, mouseY)
            is ChooseListValue<*> -> handleChooseClick(value, hit.rect, mouseX, mouseY)
            is MultiChooseListValue<*> -> handleMultiChooseClick(value, hit.rect, mouseX, mouseY)
            is RangedValue<*> -> {
                beginRangedDrag(hit, mouseX)
                updateDragging(mouseX)
            }

            else -> when (value.type()) {
                ValueType.BOOLEAN -> setAny(value, value.get() != true)
                ValueType.COLOR -> cycleColor(value)
                else -> Unit
            }
        }

        return true
    }

    fun updateDragging(mouseX: Float): Boolean {
        val hit = dragging ?: return false
        val value = hit.entry.value as? RangedValue<*> ?: return false
        val track = sliderTrack(hit.rect)
        val progress = SilentClickGuiSettingsMath.sliderProgress(mouseX, track.x, track.w)
        setRangedValue(value, progress, draggingRangeHandle)
        return true
    }

    fun startModuleBind(module: ClientModule): Boolean {
        if (module.notActivatable) {
            return false
        }

        val bind = visibleSettings(module).asSequence()
            .map { it.value }
            .filterIsInstance<BindValue>()
            .firstOrNull { it.name.equals("Bind", ignoreCase = true) }
            ?: return false

        bindListening = bind
        return true
    }

    fun selectorStateToken(): Int = expandedSelectors.hashCode()

    private fun drawSetting(
        context: DrawContext,
        rect: SilentRect,
        entry: SettingEntry,
        mouseX: Float,
        mouseY: Float,
        alpha: Float,
    ) {
        val value = entry.value
        val hovered = rect.contains(mouseX, mouseY)
        if (hovered && !entry.sectionOnly) {
            ExosGuiRenderer.roundedRect(context.matrices, rect.x, rect.y + 1f, rect.w, rect.h - 2f, 4f,
                fade(0x12FFFFFF, alpha))
        }

        when (value) {
            is ToggleableConfigurable -> drawToggleable(context, rect, value, alpha)
            is ChoiceConfigurable<*> -> drawChoice(context, rect, value, alpha)
            is ChooseListValue<*> -> drawChoose(context, rect, value, alpha)
            is MultiChooseListValue<*> -> drawMultiChoose(context, rect, value, alpha)
            is ActionValue -> drawAction(context, rect, value, hovered, alpha)
            is BindValue -> drawBind(context, rect, value, alpha)
            is RangedValue<*> -> drawRanged(context, rect, value, alpha)
            else -> when (value.type()) {
                ValueType.BOOLEAN -> drawBoolean(context, rect, value, alpha)
                ValueType.COLOR -> drawColor(context, rect, value, alpha)
                else -> drawPlain(context, rect, value, entry.sectionOnly, alpha)
            }
        }
    }

    private fun drawToggleable(context: DrawContext, rect: SilentRect, value: ToggleableConfigurable, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        drawSwitch(context, rect.right - 23f, rect.y + 4.5f, value.enabled, idOf(value), alpha)
    }

    private fun drawChoice(context: DrawContext, rect: SilentRect, value: ChoiceConfigurable<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        val selected = value.activeChoice.choiceName
        drawRightText(context, rect, selected, alpha)
        drawOptions(context, rect, value.choices, value.activeChoice, alpha)
    }

    private fun drawChoose(context: DrawContext, rect: SilentRect, value: ChooseListValue<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        drawRightText(context, rect, optionName(value.get()), alpha)
        drawOptions(context, rect, value.choices.toList(), value.get(), alpha)
    }

    private fun drawMultiChoose(context: DrawContext, rect: SilentRect, value: MultiChooseListValue<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        drawRightText(context, rect, "${value.get().size}/${value.choices.size}", alpha)

        if (!isSelectorExpanded(value)) {
            return
        }

        val choices = value.choices.toList()
        choices.forEachIndexed { index, choice ->
            val optionRect = optionRect(rect, index)
            val active = choice in value.get()
            val checkColor = if (active) fade(ClientTheme.accentA, alpha) else fade(0x35FFFFFF, alpha)
            ExosGuiRenderer.roundedRect(context.matrices, optionRect.x, optionRect.y + 2f, 7f, 7f, 2f, checkColor)
            if (active) {
                ExosGuiRenderer.clickGuiBody(context.matrices, "v", optionRect.x + 1.8f, optionRect.y + 0.6f,
                    fade(0xFFFFFFFF.toInt(), alpha), 5.2f)
            }
            ExosGuiRenderer.clickGuiBody(
                context.matrices,
                truncate(optionName(choice), optionRect.w - 11f, 5.4f),
                optionRect.x + 10f,
                optionRect.y + 1f,
                fade(if (active) 0xFFEFF7FF.toInt() else 0x99FFFFFF.toInt(), alpha),
                5.4f
            )
        }
    }

    private fun drawAction(
        context: DrawContext,
        rect: SilentRect,
        value: ActionValue,
        hovered: Boolean,
        alpha: Float,
    ) {
        drawLabel(context, rect, value.name, alpha)
        val buttonWidth = 28f
        val color = if (hovered) 0x45FFFFFF else 0x24FFFFFF
        ExosGuiRenderer.roundedRect(context.matrices, rect.right - buttonWidth, rect.y + 3f,
            buttonWidth, 9.5f, 4f, fade(color, alpha))
        ExosGuiRenderer.clickGuiBody(context.matrices, "Run", rect.right - buttonWidth + 7f,
            rect.y + 4.5f, fade(0xDFFFFFFF.toInt(), alpha), 5.1f)
    }

    private fun drawBind(context: DrawContext, rect: SilentRect, value: BindValue, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        val label = if (bindListening === value) "..." else value.get().keyName
        drawRightText(context, rect, label, alpha)
    }

    private fun drawRanged(context: DrawContext, rect: SilentRect, value: RangedValue<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        drawRightText(context, rect, rangedText(value), alpha)

        val track = sliderTrack(rect)
        ExosGuiRenderer.roundedRect(context.matrices, track.x, track.y, track.w, track.h, track.h * 0.5f,
            fade(0x32000000, alpha))

        when (val current = value.get()) {
            is IntRange -> drawRangeTrack(context, track, intProgress(value, current.first), intProgress(value, current.last),
                alpha)
            is ClosedFloatingPointRange<*> -> {
                val range = current.toFloatRange() ?: return
                drawRangeTrack(context, track, floatProgress(value, range.start), floatProgress(value, range.endInclusive),
                    alpha)
            }
            is Int -> drawSingleTrack(context, track, intProgress(value, current), alpha)
            is Float -> drawSingleTrack(context, track, floatProgress(value, current), alpha)
        }
    }

    private fun drawBoolean(context: DrawContext, rect: SilentRect, value: Value<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        drawSwitch(context, rect.right - 23f, rect.y + 4.5f, value.get() == true, idOf(value), alpha)
    }

    private fun drawColor(context: DrawContext, rect: SilentRect, value: Value<*>, alpha: Float) {
        drawLabel(context, rect, value.name, alpha)
        val color = (value.get() as? Color4b)?.toARGB() ?: ClientTheme.accentA
        ExosGuiRenderer.roundedRect(context.matrices, rect.right - 14f, rect.y + 3.5f, 12f, 9f, 3f,
            fade(color, alpha))
        ExosGuiRenderer.roundedOutline(context.matrices, rect.right - 14f, rect.y + 3.5f, 12f, 9f, 3f,
            fade(0x45FFFFFF, alpha), 0.75f)
    }

    private fun drawPlain(context: DrawContext, rect: SilentRect, value: Value<*>, sectionOnly: Boolean, alpha: Float) {
        val color = if (sectionOnly) 0x8FFFFFFF.toInt() else 0xDFFFFFFF.toInt()
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(value.name, rect.w - 4f, 5.8f),
            rect.x, rect.y + 4.2f, fade(color, alpha), 5.8f)

        if (!sectionOnly) {
            drawRightText(context, rect, value.getValue().toString(), alpha)
        }
    }

    private fun drawSwitch(
        context: DrawContext,
        x: Float,
        y: Float,
        enabled: Boolean,
        id: Int,
        alpha: Float,
    ) {
        val previous = switchProgress.getOrPut(id) { if (enabled) 1f else 0f }
        val target = if (enabled) 1f else 0f
        val next = if (abs(previous - target) < 0.01f) {
            target
        } else {
            previous + (target - previous) * 0.22f
        }
        switchProgress[id] = next

        val trackColor = if (enabled) ClientTheme.accentA else 0x31000000
        ExosGuiRenderer.roundedRect(context.matrices, x, y, 18f, 8f, 4f, fade(trackColor, alpha))
        ExosGuiRenderer.roundedRect(context.matrices, x + 1f + next * 10f, y + 1f, 6f, 6f, 3f,
            fade(0xFFFFFFFF.toInt(), alpha))
    }

    private fun drawOptions(
        context: DrawContext,
        rect: SilentRect,
        choices: List<Any?>,
        active: Any?,
        alpha: Float,
    ) {
        if (!isSelectorExpanded(rectIdentity(rect)) && choices.size != 1) {
            return
        }
        if (!isSelectorExpanded(rectIdentity(rect))) {
            return
        }

        choices.forEachIndexed { index, choice ->
            val optionRect = optionRect(rect, index)
            val selected = choice == active || choice === active
            if (selected) {
                ExosGuiRenderer.roundedRect(context.matrices, optionRect.x, optionRect.y + 1.5f,
                    optionRect.w, 8f, 3.5f, fade(0x26FFFFFF, alpha))
            }
            ExosGuiRenderer.clickGuiBody(
                context.matrices,
                truncate(optionName(choice), optionRect.w - 4f, 5.4f),
                optionRect.x + 3f,
                optionRect.y + 1f,
                fade(if (selected) ClientTheme.accentA else 0x99FFFFFF.toInt(), alpha),
                5.4f
            )
        }
    }

    private fun drawOptions(
        context: DrawContext,
        rect: SilentRect,
        value: ChoiceConfigurable<*>,
        alpha: Float,
    ) = drawOptions(context, rect, value.choices, value.activeChoice, alpha)

    private fun drawOptions(
        context: DrawContext,
        rect: SilentRect,
        value: ChooseListValue<*>,
        alpha: Float,
    ) = drawOptions(context, rect, value.choices.toList(), value.get(), alpha)

    private fun drawLabel(context: DrawContext, rect: SilentRect, label: String, alpha: Float) {
        ExosGuiRenderer.clickGuiBody(context.matrices, truncate(label, rect.w * 0.52f, 5.8f),
            rect.x + 1f, rect.y + 4.2f, fade(0xDFFFFFFF.toInt(), alpha), 5.8f)
    }

    private fun drawRightText(context: DrawContext, rect: SilentRect, label: String, alpha: Float) {
        val width = (rect.w * 0.42f).coerceAtLeast(14f)
        val truncated = truncate(label, width, 5.5f)
        ExosGuiRenderer.clickGuiBody(context.matrices, truncated,
            rect.right - ExosGuiRenderer.clickGuiBodyWidth(truncated, 5.5f) - 1f,
            rect.y + 4.4f, fade(0x88FFFFFF.toInt(), alpha), 5.5f)
    }

    private fun drawSingleTrack(context: DrawContext, track: SilentRect, progress: Float, alpha: Float) {
        val width = track.w * progress.coerceIn(0f, 1f)
        ExosGuiRenderer.roundedRect(context.matrices, track.x, track.y, width, track.h, track.h * 0.5f,
            fade(ClientTheme.accentA, alpha))
        ExosGuiRenderer.roundedRect(context.matrices, track.x + width - 3f, track.y - 2f,
            6f, 6f, 3f, fade(0xFFFFFFFF.toInt(), alpha))
    }

    private fun drawRangeTrack(
        context: DrawContext,
        track: SilentRect,
        startProgress: Float,
        endProgress: Float,
        alpha: Float,
    ) {
        val start = startProgress.coerceIn(0f, 1f)
        val end = endProgress.coerceIn(start, 1f)
        val x = track.x + track.w * start
        val width = track.w * (end - start)
        ExosGuiRenderer.roundedRect(context.matrices, x, track.y, width, track.h, track.h * 0.5f,
            fade(ClientTheme.accentA, alpha))
        ExosGuiRenderer.roundedRect(context.matrices, x - 3f, track.y - 2f, 6f, 6f, 3f,
            fade(0xFFFFFFFF.toInt(), alpha))
        ExosGuiRenderer.roundedRect(context.matrices, x + width - 3f, track.y - 2f, 6f, 6f, 3f,
            fade(0xFFFFFFFF.toInt(), alpha))
    }

    private fun handleChoiceClick(value: ChoiceConfigurable<*>, rect: SilentRect, mouseX: Float, mouseY: Float) {
        if (!isSelectorExpanded(value)) {
            expandedSelectors += idOf(value)
            return
        }

        val index = optionIndex(rect, mouseX, mouseY, value.choices.size)
        if (index == null) {
            expandedSelectors -= idOf(value)
            return
        }

        value.setByString(value.choices[index].choiceName)
        expandedSelectors -= idOf(value)
    }

    private fun handleChooseClick(value: ChooseListValue<*>, rect: SilentRect, mouseX: Float, mouseY: Float) {
        if (!isSelectorExpanded(value)) {
            expandedSelectors += idOf(value)
            return
        }

        val index = optionIndex(rect, mouseX, mouseY, value.choices.size)
        if (index == null) {
            expandedSelectors -= idOf(value)
            return
        }

        value.setByString(value.choices[index].choiceName)
        expandedSelectors -= idOf(value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleMultiChooseClick(value: MultiChooseListValue<*>, rect: SilentRect, mouseX: Float, mouseY: Float) {
        if (!isSelectorExpanded(value)) {
            expandedSelectors += idOf(value)
            return
        }

        val index = optionIndex(rect, mouseX, mouseY, value.choices.size)
        if (index == null) {
            expandedSelectors -= idOf(value)
            return
        }

        val raw = value as MultiChooseListValue<Any?>
        raw.toggle(raw.choices.toList()[index])
    }

    private fun beginRangedDrag(hit: SettingHit, mouseX: Float) {
        dragging = hit
        val value = hit.entry.value as? RangedValue<*> ?: return
        val track = sliderTrack(hit.rect)
        val progress = SilentClickGuiSettingsMath.sliderProgress(mouseX, track.x, track.w)
        draggingRangeHandle = when (val current = value.get()) {
            is IntRange -> SilentClickGuiSettingsMath.nearestRangeHandle(
                intProgress(value, current.first),
                intProgress(value, current.last),
                progress
            )
            is ClosedFloatingPointRange<*> -> {
                val range = current.toFloatRange()
                if (range != null) {
                    SilentClickGuiSettingsMath.nearestRangeHandle(
                        floatProgress(value, range.start),
                        floatProgress(value, range.endInclusive),
                        progress
                    )
                } else {
                    null
                }
            }
            else -> null
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setRangedValue(
        value: RangedValue<*>,
        progress: Float,
        rangeHandle: SilentClickGuiSettingsMath.RangeHandle?,
    ) {
        when (val current = value.get()) {
            is Int -> {
                val range = value.range as? IntRange ?: return
                val next = SilentClickGuiSettingsMath.snapInt(
                    SilentClickGuiSettingsMath.valueFromProgress(progress, range.first.toFloat(), range.last.toFloat()),
                    range.first,
                    range.last,
                    value.step?.toInt()
                )
                (value as RangedValue<Int>).set(next)
            }

            is Float -> {
                val range = (value.range as? ClosedFloatingPointRange<*>)?.toFloatRange() ?: return
                val next = SilentClickGuiSettingsMath.snapFloat(
                    SilentClickGuiSettingsMath.valueFromProgress(progress, range.start, range.endInclusive),
                    range.start,
                    range.endInclusive,
                    value.step?.toFloat()
                )
                (value as RangedValue<Float>).set(next)
            }

            is IntRange -> {
                val range = value.range as? IntRange ?: return
                val raw = SilentClickGuiSettingsMath.valueFromProgress(progress, range.first.toFloat(), range.last.toFloat())
                val snapped = SilentClickGuiSettingsMath.snapInt(raw, range.first, range.last, value.step?.toInt())
                val ordered = SilentClickGuiSettingsMath.orderedIntRange(
                    current,
                    rangeHandle ?: SilentClickGuiSettingsMath.RangeHandle.END,
                    snapped
                )
                (value as RangedValue<IntRange>).set(ordered)
            }

            is ClosedFloatingPointRange<*> -> {
                val currentRange = current.toFloatRange() ?: return
                val range = (value.range as? ClosedFloatingPointRange<*>)?.toFloatRange() ?: return
                val raw = SilentClickGuiSettingsMath.valueFromProgress(progress, range.start, range.endInclusive)
                val snapped = SilentClickGuiSettingsMath.snapFloat(raw, range.start, range.endInclusive, value.step?.toFloat())
                val ordered = SilentClickGuiSettingsMath.orderedFloatRange(
                    currentRange,
                    rangeHandle ?: SilentClickGuiSettingsMath.RangeHandle.END,
                    snapped
                )
                (value as RangedValue<ClosedFloatingPointRange<Float>>).set(ordered)
            }
        }
    }

    private fun intProgress(value: RangedValue<*>, number: Int): Float {
        val range = value.range as? IntRange ?: return 0f
        val width = (range.last - range.first).takeIf { it != 0 } ?: return 0f
        return ((number - range.first).toFloat() / width).coerceIn(0f, 1f)
    }

    private fun floatProgress(value: RangedValue<*>, number: Float): Float {
        val range = (value.range as? ClosedFloatingPointRange<*>)?.toFloatRange() ?: return 0f
        val width = (range.endInclusive - range.start).takeIf { it != 0f } ?: return 0f
        return ((number - range.start) / width).coerceIn(0f, 1f)
    }

    private fun rangedText(value: RangedValue<*>): String {
        val suffix = value.suffix
        return when (val current = value.get()) {
            is Int -> "$current$suffix"
            is Float -> "${formatFloat(current)}$suffix"
            is IntRange -> "${current.first}..${current.last}$suffix"
            is ClosedFloatingPointRange<*> -> current.toFloatRange()?.let {
                "${formatFloat(it.start)}..${formatFloat(it.endInclusive)}$suffix"
            } ?: current.toString()
            else -> current.toString()
        }
    }

    private fun formatFloat(value: Float): String {
        val rounded = (value * 100f).roundToInt() / 100f
        return if (rounded % 1f == 0f) rounded.toInt().toString() else rounded.toString()
    }

    private fun cycleColor(value: Value<*>) {
        val current = value.get() as? Color4b ?: return
        val presets = ClientTheme.COLORS.flatMap { listOf(it.first, it.second) }
        val next = presets.firstOrNull { it and 0x00FFFFFF != current.toARGB() and 0x00FFFFFF }
            ?: ClientTheme.accentA
        setAny(value, Color4b(next, hasAlpha = true).with(a = current.a))
    }

    @Suppress("UNCHECKED_CAST")
    private fun setAny(value: Value<*>, newValue: Any) {
        (value as Value<Any>).set(newValue)
    }

    private fun settingHeight(entry: SettingEntry): Float {
        if (entry.sectionOnly) {
            return 12f
        }

        return when (val value = entry.value) {
            is RangedValue<*> -> 27f
            is ChoiceConfigurable<*> -> selectorHeight(value, value.choices.size)
            is ChooseListValue<*> -> selectorHeight(value, value.choices.size)
            is MultiChooseListValue<*> -> selectorHeight(value, value.choices.size)
            else -> 16f
        }
    }

    private fun selectorHeight(value: Value<*>, count: Int): Float =
        16f + if (isSelectorExpanded(value)) count * 11f + 2f else 0f

    private fun optionRect(rect: SilentRect, index: Int): SilentRect =
        SilentRect(rect.x + 2f, rect.y + 16f + index * 11f, rect.w - 4f, 10f)

    private fun optionIndex(rect: SilentRect, mouseX: Float, mouseY: Float, count: Int): Int? {
        if (mouseY < rect.y + 16f) {
            return null
        }

        val index = ((mouseY - rect.y - 16f) / 11f).toInt()
        return index.takeIf { it in 0 until count && optionRect(rect, it).contains(mouseX, mouseY) }
    }

    private fun sliderTrack(rect: SilentRect): SilentRect =
        SilentRect(rect.x + 2f, rect.y + 18.5f, rect.w - 4f, 2.2f)

    private fun isSelectorExpanded(value: Value<*>): Boolean = idOf(value) in expandedSelectors

    private fun isSelectorExpanded(key: Int): Boolean = key in expandedSelectors

    private fun rectIdentity(rect: SilentRect): Int =
        settingHits.firstOrNull { it.rect == rect }?.entry?.value?.let(::idOf) ?: 0

    private fun shouldShow(value: Value<*>): Boolean {
        if (!value.isVisibleInUi() || value.notAnOption) {
            return false
        }

        return !value.name.equals("Enabled", ignoreCase = true) &&
            !value.name.equals("Hidden", ignoreCase = true)
    }

    private fun optionName(choice: Any?): String = when (choice) {
        is NamedChoice -> choice.choiceName
        null -> ""
        else -> choice.toString()
    }

    private fun ClosedFloatingPointRange<*>.toFloatRange(): ClosedFloatingPointRange<Float>? {
        val startNumber = start as? Number ?: return null
        val endNumber = endInclusive as? Number ?: return null
        return startNumber.toFloat()..endNumber.toFloat()
    }

    private fun SilentRect.contains(x: Float, y: Float): Boolean =
        x >= this.x && x <= this.right && y >= this.y && y <= this.bottom

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

    private fun idOf(value: Value<*>): Int = System.identityHashCode(value)

}
