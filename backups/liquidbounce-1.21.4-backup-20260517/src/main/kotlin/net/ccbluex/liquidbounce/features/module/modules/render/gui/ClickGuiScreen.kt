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

import net.ccbluex.liquidbounce.config.ConfigSystem
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
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.input.InputBind
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.InputUtil
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.lwjgl.glfw.GLFW
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Thunder-style dropdown ClickGUI mapped to LiquidBounce modules and values.
 */
@Suppress("TooManyFunctions", "LargeClass")
class ClickGuiScreen : Screen(Text.literal("ClickGUI")) {

    private val renderCategories = listOf(
        Category.COMBAT,
        Category.MOVEMENT,
        Category.RENDER,
        Category.PLAYER,
        Category.MISC
    )

    private val scrollOffsets = mutableMapOf<Category, Float>()
    private val scrollTargets = mutableMapOf<Category, Float>()
    private val expandedModules = mutableSetOf<ClientModule>()
    private val expandProgress = mutableMapOf<ClientModule, Float>()
    private val expandedSections = mutableMapOf<String, Boolean>()
    private val booleanAnimations = mutableMapOf<String, Float>()
    private val sliderAnimations = mutableMapOf<String, Float>()
    private val choiceDropDowns = mutableMapOf<String, Boolean>()

    private var openStartedAt = 0L
    private var searchText = ""
    private var searchFocused = false
    private var hoveredDescription: String? = null
    private var bindingModule: ClientModule? = null
    private var bindingValue: Pair<ClientModule, BindValue>? = null
    private var editingValue: TextEdit? = null
    private var dragging: Dragging? = null
    private var selectedTheme = 0
    private var soundStep = 0
    private var stateRestored = false
    private var stateSaved = false

    private val accentA: Int
        get() = THEME_COLORS[selectedTheme.coerceIn(THEME_COLORS.indices)].first

    private val accentB: Int
        get() = THEME_COLORS[selectedTheme.coerceIn(THEME_COLORS.indices)].second

    private val modulesByCategory: Map<Category, List<ClientModule>>
        get() = ModuleManager.getModules()
            .groupBy { it.category }
            .mapValues { (_, modules) -> modules.sortedBy { it.name.lowercase() } }

    override fun init() {
        super.init()
        openStartedAt = System.currentTimeMillis()
        renderCategories.forEach {
            scrollOffsets.putIfAbsent(it, 0f)
            scrollTargets.putIfAbsent(it, 0f)
        }
        if (!stateRestored) {
            restoreSavedState()
            stateRestored = true
        }
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        hoveredDescription = null
        renderBackgroundSurface(context)

        val animation = easeOpen()
        context.matrices.push()
        context.matrices.translate(width * 0.5f, height * 0.5f, 0f)
        context.matrices.scale(animation, animation, 1f)
        context.matrices.translate(-width * 0.5f, -height * 0.5f, 0f)

        val startX = panelStartX()
        val startY = panelStartY()

        renderCategories.forEachIndexed { index, category ->
            updateScroll(category)
            renderPanel(context, category, startX + index * (PANEL_WIDTH + PANEL_MARGIN), startY, mouseX, mouseY)
        }

        renderDescription(context, startY)
        renderThemeBar(context, mouseX, mouseY)
        renderSearch(context, mouseX, mouseY)
        context.matrices.pop()
    }

    private fun renderBackgroundSurface(context: DrawContext) {
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            0f,
            0xA5101116.toInt(),
            0xA5151720.toInt(),
            0xA50C1118.toInt(),
            0xA518101D.toInt()
        )
    }

    private fun renderPanel(
        context: DrawContext,
        category: Category,
        x: Int,
        y: Int,
        mouseX: Int,
        mouseY: Int
    ) {
        val matrix = context.matrices
        val contentY = y + TITLE_MARGIN_TOP + TITLE_HEIGHT

        ExosGuiRenderer.roundedGradientRect(
            matrix,
            x.toFloat(),
            y.toFloat(),
            PANEL_WIDTH.toFloat(),
            PANEL_HEIGHT.toFloat(),
            11f,
            0xE21A1F2A.toInt(),
            0xDF151923.toInt(),
            0xDF10141C.toInt(),
            0xE1141922.toInt()
        )
        ExosGuiRenderer.roundedOutline(matrix, x.toFloat(), y.toFloat(), PANEL_WIDTH.toFloat(), PANEL_HEIGHT.toFloat(), 11f, 0x2CFFFFFF)

        val title = category.readableName
        val titleWidth = ExosGuiRenderer.textWidth(title, CATEGORY_TEXT_SIZE)
        ExosGuiRenderer.text(
            matrix,
            title,
            x + (PANEL_WIDTH - titleWidth) * 0.5f,
            y + 8f,
            0xFFF3F5FA.toInt(),
            CATEGORY_TEXT_SIZE
        )

        ExosGuiRenderer.roundedGradientRect(
            matrix,
            x + 10f,
            y + TITLE_MARGIN_TOP + TITLE_HEIGHT - 2f,
            PANEL_WIDTH - 20f,
            2f,
            1f,
            accentA,
            accentB,
            accentB,
            accentA
        )

        val maxScroll = maxScroll(category)
        scrollTargets[category] = scrollTargets.getValue(category).coerceIn(0f, maxScroll)
        scrollOffsets[category] = scrollOffsets.getValue(category).coerceIn(0f, maxScroll)

        renderScrollbar(context, category, x, contentY, maxScroll)

        context.enableScissor(x, contentY, x + PANEL_WIDTH, contentY + SCROLL_AREA_HEIGHT)
        var currentY = contentY - scrollOffsets.getValue(category)
        for (module in filteredModules(category)) {
            val moduleHeight = moduleTotalHeight(module)
            if (currentY + moduleHeight >= contentY && currentY <= contentY + SCROLL_AREA_HEIGHT) {
                renderModule(context, module, x, currentY, mouseX, mouseY, contentY)
            }
            currentY += moduleHeight
        }
        context.disableScissor()
    }

    private fun renderModule(
        context: DrawContext,
        module: ClientModule,
        panelX: Int,
        y: Float,
        mouseX: Int,
        mouseY: Int,
        contentY: Int
    ) {
        val rowX = panelX + 8f
        val rowW = PANEL_WIDTH - 16f
        val rowY = y + MODULE_ROW_PAD_Y
        val rowH = FUNCTION_HEIGHT - MODULE_ROW_PAD_Y * 2f
        val hovered = mouseX >= rowX && mouseX <= rowX + rowW && mouseY >= y && mouseY <= y + FUNCTION_HEIGHT
            && mouseY >= contentY && mouseY <= contentY + SCROLL_AREA_HEIGHT
        val active = module.running
        val progress = expandProgress(module)

        if (hovered) {
            hoveredDescription = module.description.get()
        }

        ExosGuiRenderer.roundedRect(
            context.matrices,
            rowX,
            rowY,
            rowW,
            rowH,
            5f,
            if (hovered) 0xBA313846.toInt() else 0x86262C36.toInt()
        )
        if (active) {
            ExosGuiRenderer.roundedGradientRect(
                context.matrices,
                rowX,
                rowY,
                rowW,
                rowH,
                5f,
                ExosGuiRenderer.withAlpha(accentA, 145),
                ExosGuiRenderer.withAlpha(accentB, 175),
                ExosGuiRenderer.withAlpha(accentB, 140),
                ExosGuiRenderer.withAlpha(accentA, 120)
            )
        }

        val text = if (bindingModule === module) "Binding..." else module.name
        val textColor = if (active) 0xFFFFFFFF.toInt() else 0xFFDCE1EA.toInt()
        val textY = rowY + (rowH - MODULE_TEXT_SIZE) * 0.5f
        ExosGuiRenderer.text(
            context.matrices,
            truncate(text, rowW - 27f, MODULE_TEXT_SIZE),
            rowX + 8f,
            textY,
            textColor,
            MODULE_TEXT_SIZE
        )

        if (moduleHasSettings(module)) {
            val arrow = if (progress > 0.5f) "v" else ">"
            ExosGuiRenderer.text(context.matrices, arrow, rowX + rowW - 15f, textY, 0xCFFFFFFF.toInt(), MODULE_TEXT_SIZE)
        }

        if (progress <= 0.01f) {
            return
        }

        var settingY = y + FUNCTION_HEIGHT
        val entries = collectSettings(module)
        for (entry in entries) {
            val fullHeight = settingHeight(entry)
            val visibleHeight = min(fullHeight, (y + FUNCTION_HEIGHT + settingsHeight(module)) - settingY) * progress
            if (visibleHeight > 0f) {
                renderSetting(context, entry, panelX + 10f, settingY, PANEL_WIDTH - 20f, mouseX, mouseY)
            }
            settingY += fullHeight * progress
        }
    }

    private fun renderSetting(
        context: DrawContext,
        entry: SettingEntry,
        x: Float,
        y: Float,
        width: Float,
        mouseX: Int,
        mouseY: Int
    ) {
        val indent = entry.depth * 10f
        val rowX = x + indent
        val rowW = width - indent
        val rowY = y + SETTING_ROW_PAD_Y
        val rowH = settingRowHeight(entry)
        val hovered = mouseX >= rowX && mouseX <= rowX + rowW && mouseY >= rowY && mouseY <= rowY + rowH

        ExosGuiRenderer.roundedRect(
            context.matrices,
            rowX,
            rowY,
            rowW,
            rowH,
            5f,
            if (hovered) 0xA8303744.toInt() else 0x7621262D.toInt()
        )
        if (entry.depth > 0) {
            ExosGuiRenderer.roundedRect(context.matrices, x + 4f, rowY + 2f, 1.4f, rowH - 4f, 0.7f, ExosGuiRenderer.withAlpha(accentB, 150))
        }

        when (entry.kind) {
            EntryKind.SECTION -> renderSection(context, entry, rowX, rowY, rowW)
            EntryKind.TOGGLE_SECTION -> renderToggleSection(context, entry, rowX, rowY, rowW)
            EntryKind.BOOLEAN -> renderBoolean(context, entry, rowX, rowY, rowW)
            EntryKind.NUMBER -> renderNumber(context, entry, rowX, rowY, rowW)
            EntryKind.RANGE -> renderRange(context, entry, rowX, rowY, rowW)
            EntryKind.CHOICE -> renderChoice(context, entry, rowX, rowY, rowW)
            EntryKind.TEXT, EntryKind.BIND, EntryKind.KEY, EntryKind.DISPLAY -> renderTextLike(context, entry, rowX, rowY, rowW)
        }
    }

    private fun renderSection(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        val open = sectionOpen(entry)
        val textY = settingTextY(entry, y, SETTING_TEXT_SIZE)
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 28f, SETTING_TEXT_SIZE), x + 8f, textY, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        ExosGuiRenderer.text(context.matrices, if (open) "v" else ">", x + width - 16f, textY, 0xD8FFFFFF.toInt(), SETTING_TEXT_SIZE)
    }

    private fun renderToggleSection(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        renderSection(context, entry, x, y, width)
        val enabled = toggleSectionValue(entry)?.let(::booleanValue) == true
        renderSwitch(context, entry.key, x + width - 43f, y + (settingRowHeight(entry) - SWITCH_HEIGHT) * 0.5f, enabled)
    }

    private fun renderBoolean(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 16f, SETTING_TEXT_SIZE), x + 8f, y + 5f, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        renderSwitch(context, entry.key, x + 8f, y + settingRowHeight(entry) - SWITCH_HEIGHT - 6f, booleanValue(entry.value) == true)
    }

    private fun renderSwitch(context: DrawContext, key: String, x: Float, y: Float, enabled: Boolean) {
        val animation = animate(booleanAnimations, key, if (enabled) 1f else 0f, 0.38f)
        ExosGuiRenderer.roundedRect(
            context.matrices,
            x,
            y,
            SWITCH_WIDTH,
            SWITCH_HEIGHT,
            SWITCH_HEIGHT * 0.5f,
            if (animation > 0.55f) ExosGuiRenderer.mix(0x70303644, accentA, animation) else 0x70303644
        )
        ExosGuiRenderer.roundedRect(context.matrices, x + 2f + 12f * animation, y + 2f, 8f, 8f, 4f, 0xF2FFFFFF.toInt())
        ExosGuiRenderer.text(
            context.matrices,
            if (enabled) "v" else "x",
            x + if (enabled) 4f else 15f,
            y + 1f,
            0xDDFFFFFF.toInt(),
            9f
        )
    }

    private fun renderNumber(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        val value = entry.value.get()
        val valueText = if (editingValue?.value === entry.value) editingValue?.text.orEmpty() + "_" else formatNumber(value)
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 16f, SETTING_TEXT_SIZE), x + 8f, y + 5f, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        val valueW = min(width - 16f, ExosGuiRenderer.textWidth(valueText, SETTING_TEXT_SIZE) + 12f)
        ExosGuiRenderer.roundedRect(context.matrices, x + 8f, y + 22f, valueW, 16f, 4f, 0x94000000.toInt())
        ExosGuiRenderer.text(
            context.matrices,
            truncate(valueText, valueW - 8f, SETTING_TEXT_SIZE),
            x + 12f,
            y + 22f + (16f - SETTING_TEXT_SIZE) * 0.5f,
            0xFFFFFFFF.toInt(),
            SETTING_TEXT_SIZE
        )
        val (minValue, maxValue) = numberRange(entry.value)
        val current = (value as? Number)?.toDouble() ?: minValue
        val progress = ((current - minValue) / (maxValue - minValue).coerceAtLeast(0.0001)).toFloat().coerceIn(0f, 1f)
        val animated = animate(sliderAnimations, entry.key, progress, 0.4f)
        renderSliderTrack(context, x + 8f, y + settingRowHeight(entry) - 10f, width - 16f, animated, animated)
    }

    private fun renderRange(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        val rangeText = if (editingValue?.value === entry.value) editingValue?.text.orEmpty() + "_" else rangeLabel(entry.value)
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 16f, SETTING_TEXT_SIZE), x + 8f, y + 5f, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        val valueW = min(width - 16f, ExosGuiRenderer.textWidth(rangeText, SETTING_TEXT_SIZE) + 12f)
        ExosGuiRenderer.roundedRect(context.matrices, x + 8f, y + 22f, valueW, 16f, 4f, 0x94000000.toInt())
        ExosGuiRenderer.text(context.matrices, truncate(rangeText, valueW - 8f, SETTING_TEXT_SIZE), x + 12f, y + 22f + (16f - SETTING_TEXT_SIZE) * 0.5f, 0xFFFFFFFF.toInt(), SETTING_TEXT_SIZE)
        val (minValue, maxValue) = numberRange(entry.value)
        val (start, end) = rangeValues(entry.value)
        val span = (maxValue - minValue).coerceAtLeast(0.0001)
        val startProgress = ((start - minValue) / span).toFloat().coerceIn(0f, 1f)
        val endProgress = ((end - minValue) / span).toFloat().coerceIn(0f, 1f)
        renderSliderTrack(context, x + 8f, y + settingRowHeight(entry) - 10f, width - 16f, startProgress, endProgress)
    }

    private fun renderSliderTrack(context: DrawContext, x: Float, y: Float, width: Float, start: Float, end: Float) {
        ExosGuiRenderer.roundedRect(context.matrices, x, y, width, 4f, 2f, 0x70575D68)
        val from = min(start, end)
        val to = max(start, end)
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x + width * from,
            y,
            max(2f, width * (to - from)),
            4f,
            2f,
            accentA,
            accentB,
            accentB,
            accentA
        )
        ExosGuiRenderer.roundedRect(context.matrices, x + width * start - 2f, y - 3f, 4f, 10f, 2f, 0xFFE9EDF4.toInt())
        if (abs(end - start) > 0.001f) {
            ExosGuiRenderer.roundedRect(context.matrices, x + width * end - 2f, y - 3f, 4f, 10f, 2f, 0xFFE9EDF4.toInt())
        }
    }

    private fun renderChoice(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        val current = choiceLabel(entry.value)
        val open = choiceDropDowns[entry.key] == true
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 16f, SETTING_TEXT_SIZE), x + 8f, y + 5f, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        val boxY = y + 22f
        ExosGuiRenderer.roundedRect(context.matrices, x + 7f, boxY, width - 14f, 18f, 5f, 0x94000000.toInt())
        ExosGuiRenderer.roundedOutline(context.matrices, x + 7f, boxY, width - 14f, 18f, 5f, if (open) ExosGuiRenderer.withAlpha(accentA, 150) else 0x30FFFFFF)
        ExosGuiRenderer.text(context.matrices, if (open) "v" else ">", x + width - 20f, boxY + (18f - SETTING_TEXT_SIZE) * 0.5f, 0xD8FFFFFF.toInt(), SETTING_TEXT_SIZE)
        ExosGuiRenderer.text(
            context.matrices,
            truncate(current, width - 44f, SETTING_TEXT_SIZE),
            x + 12f,
            boxY + (18f - SETTING_TEXT_SIZE) * 0.5f,
            0xFFFFFFFF.toInt(),
            SETTING_TEXT_SIZE
        )

        if (!open) {
            return
        }

        val choices = choiceNames(entry.value)
        val dropY = y + baseSettingHeight(entry)
        val dropHeight = choices.size * CHOICE_OPTION_HEIGHT
        choices.forEachIndexed { index, choice ->
            val optionY = dropY + index * CHOICE_OPTION_HEIGHT
            val selected = choiceSelected(entry.value, index)
            ExosGuiRenderer.roundedRect(
                context.matrices,
                x + 7f,
                optionY + 1f,
                width - 14f,
                CHOICE_OPTION_HEIGHT - 2f,
                4f,
                0x8021262D.toInt()
            )
            if (selected) {
                ExosGuiRenderer.roundedGradientRect(
                    context.matrices,
                    x + 7f,
                    optionY + 1f,
                    width - 14f,
                    CHOICE_OPTION_HEIGHT - 2f,
                    4f,
                    ExosGuiRenderer.withAlpha(accentA, 150),
                    ExosGuiRenderer.withAlpha(accentB, 150),
                    ExosGuiRenderer.withAlpha(accentB, 110),
                    ExosGuiRenderer.withAlpha(accentA, 110)
                )
            }
            val optionWidth = ExosGuiRenderer.textWidth(choice, SETTING_TEXT_SIZE)
            ExosGuiRenderer.text(
                context.matrices,
                truncate(choice, width - 24f, SETTING_TEXT_SIZE),
                x + width * 0.5f - min(optionWidth, width - 24f) * 0.5f,
                optionY + (CHOICE_OPTION_HEIGHT - SETTING_TEXT_SIZE) * 0.5f,
                if (selected) 0xFFFFFFFF.toInt() else 0xFFC8CED8.toInt(),
                SETTING_TEXT_SIZE
            )
        }
    }

    private fun renderTextLike(context: DrawContext, entry: SettingEntry, x: Float, y: Float, width: Float) {
        val valueText = when {
            editingValue?.value === entry.value -> editingValue?.text.orEmpty() + "_"
            bindingValue?.second === entry.value -> "Binding..."
            else -> displayValue(entry.value)
        }
        ExosGuiRenderer.text(context.matrices, truncate(entry.value.name, width - 16f, SETTING_TEXT_SIZE), x + 8f, y + 5f, 0xFFE8EBF2.toInt(), SETTING_TEXT_SIZE)
        val boxY = y + 22f
        ExosGuiRenderer.roundedRect(context.matrices, x + 7f, boxY, width - 14f, 18f, 5f, 0x94000000.toInt())
        ExosGuiRenderer.text(context.matrices, truncate(valueText, width - 24f, SETTING_TEXT_SIZE), x + 12f, boxY + (18f - SETTING_TEXT_SIZE) * 0.5f, 0xFFFFFFFF.toInt(), SETTING_TEXT_SIZE)
    }

    private fun renderScrollbar(context: DrawContext, category: Category, x: Int, y: Int, maxScroll: Float) {
        if (maxScroll <= 0f) {
            return
        }

        val barX = x + PANEL_WIDTH - 5f
        val barY = y + 12f
        val barHeight = SCROLL_AREA_HEIGHT - 24f
        val thumbHeight = max(8f, barHeight * (SCROLL_AREA_HEIGHT / (SCROLL_AREA_HEIGHT + maxScroll)))
        val progress = scrollOffsets.getValue(category) / maxScroll
        val thumbY = barY + progress * (barHeight - thumbHeight)

        ExosGuiRenderer.roundedRect(context.matrices, barX, barY, 2.5f, barHeight, 1.2f, 0x48272D38)
        ExosGuiRenderer.roundedRect(context.matrices, barX, thumbY, 2.5f, thumbHeight, 1.2f, 0xAFFFFFFF.toInt())
    }

    private fun renderDescription(context: DrawContext, startY: Int) {
        val description = hoveredDescription?.takeIf { it.isNotBlank() } ?: return
        val display = truncate(description, 300f, 14f)
        val boxW = ExosGuiRenderer.textWidth(display, 14f) + 18f
        val boxX = (width - boxW) * 0.5f
        val boxY = (startY - 31).toFloat()
        ExosGuiRenderer.roundedGradientRect(context.matrices, boxX, boxY, boxW, 20f, 7f, 0xDD1B1E28.toInt(), 0xDD262A36.toInt(), 0xDD1A1D27.toInt(), 0xDD1B1E28.toInt())
        ExosGuiRenderer.roundedOutline(context.matrices, boxX, boxY, boxW, 20f, 7f, 0x32FFFFFF)
        ExosGuiRenderer.text(context.matrices, display, boxX + 9f, boxY + 4f, 0xFFE8EBF2.toInt(), 14f)
    }

    private fun renderSearch(context: DrawContext, mouseX: Int, mouseY: Int) {
        val x = ((width - SEARCH_WIDTH) * 0.5f).toFloat()
        val y = (height - 33).toFloat()
        val hovered = mouseX >= x && mouseX <= x + SEARCH_WIDTH && mouseY >= y && mouseY <= y + SEARCH_HEIGHT
        ExosGuiRenderer.roundedGradientRect(
            context.matrices,
            x,
            y,
            SEARCH_WIDTH.toFloat(),
            SEARCH_HEIGHT.toFloat(),
            SEARCH_HEIGHT * 0.5f,
            if (searchFocused) 0xE5232733.toInt() else 0xD51A1D27.toInt(),
            if (hovered) 0xE52A3040.toInt() else 0xD5202430.toInt(),
            0xD5181B24.toInt(),
            0xD5181B24.toInt()
        )
        ExosGuiRenderer.roundedOutline(context.matrices, x, y, SEARCH_WIDTH.toFloat(), SEARCH_HEIGHT.toFloat(), SEARCH_HEIGHT * 0.5f, if (searchFocused) ExosGuiRenderer.withAlpha(accentA, 150) else 0x30FFFFFF)
        val text = when {
            searchText.isNotEmpty() -> searchText + if (searchFocused && (System.currentTimeMillis() / 500L) % 2L == 0L) "_" else ""
            searchFocused && (System.currentTimeMillis() / 500L) % 2L == 0L -> "_"
            else -> ""
        }
        if (text.isNotEmpty()) {
            ExosGuiRenderer.text(context.matrices, text, x + 10f, y + 5.5f, 0xFFFFFFFF.toInt(), 14f)
        }
    }

    private fun renderThemeBar(context: DrawContext, mouseX: Int, mouseY: Int) {
        val chip = THEME_CHIP_SIZE
        val gap = THEME_CHIP_GAP
        val barWidth = THEME_COLORS.size * chip + (THEME_COLORS.size - 1) * gap + 12f
        val x = (width - barWidth) * 0.5f
        val y = height - 57f

        ExosGuiRenderer.roundedRect(context.matrices, x, y, barWidth, THEME_BAR_HEIGHT, 7f, 0xBC171B24.toInt())

        THEME_COLORS.forEachIndexed { index, colors ->
            val chipX = x + 6f + index * (chip + gap)
            val hovered = mouseX >= chipX && mouseX <= chipX + chip && mouseY >= y + 4f && mouseY <= y + 4f + chip
            ExosGuiRenderer.roundedGradientRect(
                context.matrices,
                chipX,
                y + 4f,
                chip,
                chip,
                4f,
                colors.first,
                colors.second,
                colors.second,
                colors.first
            )
            if (index == selectedTheme || hovered) {
                ExosGuiRenderer.roundedOutline(
                    context.matrices,
                    chipX - 1f,
                    y + 3f,
                    chip + 2f,
                    chip + 2f,
                    5f,
                    if (index == selectedTheme) 0xEFFFFFFF.toInt() else 0x80FFFFFF.toInt()
                )
            }
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && handleThemeClick(mouseX, mouseY)) {
            return true
        }

        val searchBounds = searchBounds()
        if (mouseX >= searchBounds.x && mouseX <= searchBounds.x + searchBounds.w &&
            mouseY >= searchBounds.y && mouseY <= searchBounds.y + searchBounds.h) {
            searchFocused = true
            editingValue = null
            bindingModule = null
            bindingValue = null
            return true
        }
        searchFocused = false

        val startX = panelStartX()
        val startY = panelStartY()
        renderCategories.forEachIndexed { index, category ->
            val panelX = startX + index * (PANEL_WIDTH + PANEL_MARGIN)
            if (mouseX >= panelX && mouseX <= panelX + PANEL_WIDTH &&
                mouseY >= startY + TITLE_MARGIN_TOP + TITLE_HEIGHT &&
                mouseY <= startY + PANEL_HEIGHT) {
                if (handlePanelClick(category, panelX, startY, mouseX, mouseY, button)) {
                    return true
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button)
    }

    private fun handleThemeClick(mouseX: Double, mouseY: Double): Boolean {
        val chip = THEME_CHIP_SIZE
        val gap = THEME_CHIP_GAP
        val barWidth = THEME_COLORS.size * chip + (THEME_COLORS.size - 1) * gap + 12f
        val x = (width - barWidth) * 0.5f
        val y = height - 57f

        THEME_COLORS.indices.forEach { index ->
            val chipX = x + 6f + index * (chip + gap)
            if (mouseX >= chipX && mouseX <= chipX + chip && mouseY >= y + 4f && mouseY <= y + 4f + chip) {
                selectedTheme = index
                playGuiSound(GuiSound.BOOLEAN)
                return true
            }
        }

        return false
    }

    private fun handlePanelClick(category: Category, panelX: Int, panelY: Int, mouseX: Double, mouseY: Double, button: Int): Boolean {
        val contentY = panelY + TITLE_MARGIN_TOP + TITLE_HEIGHT
        var currentY = contentY - scrollOffsets.getValue(category)
        for (module in filteredModules(category)) {
            val moduleHeight = moduleTotalHeight(module)
            if (mouseY >= currentY && mouseY <= currentY + FUNCTION_HEIGHT) {
                return handleModuleClick(module, button)
            }

            if (module in expandedModules) {
                val entries = collectSettings(module)
                var settingY = currentY + FUNCTION_HEIGHT
                for (entry in entries) {
                    val entryHeight = settingHeight(entry)
                    if (mouseY >= settingY && mouseY <= settingY + entryHeight) {
                        val rowX = panelX + 10f + entry.depth * 10f
                        val rowW = PANEL_WIDTH - 20f - entry.depth * 10f
                        if (mouseX >= rowX && mouseX <= rowX + rowW) {
                            return handleSettingClick(module, entry, rowX, settingY, rowW, mouseX, mouseY, button)
                        }
                    }
                    settingY += entryHeight
                }
            }
            currentY += moduleHeight
        }

        return false
    }

    private fun handleModuleClick(module: ClientModule, button: Int): Boolean {
        editingValue = null
        bindingValue = null
        return when (button) {
            0 -> {
                if (!module.disableActivation) {
                    module.enabled = !module.enabled
                }
                true
            }
            1 -> {
                if (moduleHasSettings(module)) {
                    val opened = if (!expandedModules.remove(module)) {
                        expandedModules += module
                        true
                    } else {
                        false
                    }
                    markStateChanged()
                    playGuiSound(if (opened) GuiSound.SWIPE_IN else GuiSound.SWIPE_OUT)
                }
                true
            }
            2 -> {
                bindingModule = module
                playGuiSound(GuiSound.BOOLEAN)
                true
            }
            else -> false
        }
    }

    private fun handleSettingClick(
        module: ClientModule,
        entry: SettingEntry,
        rowX: Float,
        rowY: Float,
        rowW: Float,
        mouseX: Double,
        mouseY: Double,
        button: Int
    ): Boolean {
        editingValue = null
        bindingValue = null

        when (entry.kind) {
            EntryKind.SECTION -> {
                val next = !sectionOpen(entry)
                expandedSections[entry.key] = next
                markStateChanged()
                playGuiSound(if (next) GuiSound.SWIPE_IN else GuiSound.SWIPE_OUT)
            }
            EntryKind.TOGGLE_SECTION -> {
                if (button == 1) {
                    val next = !sectionOpen(entry)
                    expandedSections[entry.key] = next
                    markStateChanged()
                    playGuiSound(if (next) GuiSound.SWIPE_IN else GuiSound.SWIPE_OUT)
                } else {
                    toggleSectionValue(entry)?.let { toggleBoolean(module, it) }
                }
            }
            EntryKind.BOOLEAN -> toggleBoolean(module, entry.value)
            EntryKind.NUMBER -> {
                if (button == 0) {
                    dragging = Dragging.Number(module, entry.value, rowX + 8f, rowW - 16f)
                    updateNumber(module, entry.value, rowX + 8f, rowW - 16f, mouseX)
                } else {
                    editingValue = TextEdit(module, entry.value, formatNumber(entry.value.get()))
                }
            }
            EntryKind.RANGE -> {
                if (button == 0) {
                    dragging = Dragging.Range(module, entry.value, rowX + 8f, rowW - 16f, chooseRangeStart(entry.value, mouseX, rowX + 8f, rowW - 16f))
                    updateRange(module, entry.value, rowX + 8f, rowW - 16f, mouseX, (dragging as Dragging.Range).start)
                } else {
                    editingValue = TextEdit(module, entry.value, rangeLabel(entry.value))
                }
            }
            EntryKind.CHOICE -> {
                val baseHeight = baseSettingHeight(entry)
                val choices = choiceNames(entry.value)
                if (choiceDropDowns[entry.key] == true && mouseY >= rowY + baseHeight && choices.isNotEmpty()) {
                    val index = ((mouseY - rowY - baseHeight) / CHOICE_OPTION_HEIGHT).toInt()
                    if (index in choices.indices) {
                        selectChoice(module, entry.value, index)
                    }
                } else if (button == 1) {
                    choiceDropDowns[entry.key] = choiceDropDowns[entry.key] != true
                    markStateChanged()
                    playGuiSound(if (choiceDropDowns[entry.key] == true) GuiSound.SWIPE_IN else GuiSound.SWIPE_OUT)
                } else {
                    cycleChoice(module, entry.value, 1)
                }
            }
            EntryKind.BIND -> {
                if (entry.value is BindValue) {
                    bindingValue = module to entry.value
                    playGuiSound(GuiSound.BOOLEAN)
                } else {
                    editingValue = TextEdit(module, entry.value, displayValue(entry.value))
                }
            }
            EntryKind.TEXT, EntryKind.KEY, EntryKind.DISPLAY -> editingValue = TextEdit(module, entry.value, displayValue(entry.value))
        }

        return true
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        when (val drag = dragging) {
            is Dragging.Number -> updateNumber(drag.module, drag.value, drag.x, drag.width, mouseX)
            is Dragging.Range -> updateRange(drag.module, drag.value, drag.x, drag.width, mouseX, drag.start)
            null -> return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
        }
        return true
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragging = null
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, horizontalAmount: Double, verticalAmount: Double): Boolean {
        val startX = panelStartX()
        val startY = panelStartY()
        renderCategories.forEachIndexed { index, category ->
            val x = startX + index * (PANEL_WIDTH + PANEL_MARGIN)
            val y = startY + TITLE_MARGIN_TOP + TITLE_HEIGHT
            if (mouseX >= x && mouseX <= x + PANEL_WIDTH && mouseY >= y && mouseY <= y + SCROLL_AREA_HEIGHT) {
                val maxScroll = maxScroll(category)
                if (maxScroll > 0f) {
                    scrollTargets[category] = (scrollTargets.getValue(category) - verticalAmount.toFloat() * 16f)
                        .coerceIn(0f, maxScroll)
                    markStateChanged()
                    playGuiSound(GuiSound.SCROLL)
                    return true
                }
            }
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        bindingModule?.let { module ->
            setModuleBind(module, keyCode)
            bindingModule = null
            return true
        }

        bindingValue?.let { (module, value) ->
            setBindValue(module, value, keyCode)
            bindingValue = null
            return true
        }

        editingValue?.let { edit ->
            when (keyCode) {
                GLFW.GLFW_KEY_ENTER -> {
                    commitTextEdit(edit)
                    editingValue = null
                    return true
                }
                GLFW.GLFW_KEY_ESCAPE -> {
                    editingValue = null
                    return true
                }
                GLFW.GLFW_KEY_BACKSPACE -> {
                    if (edit.text.isNotEmpty()) {
                        edit.text = edit.text.dropLast(1)
                    }
                    return true
                }
            }
        }

        if (searchFocused) {
            when (keyCode) {
                GLFW.GLFW_KEY_ESCAPE -> {
                    searchFocused = false
                    return true
                }
                GLFW.GLFW_KEY_BACKSPACE -> {
                    if (searchText.isNotEmpty()) {
                        searchText = searchText.dropLast(1)
                        resetScroll()
                        markStateChanged()
                    }
                    return true
                }
                GLFW.GLFW_KEY_DELETE -> {
                    searchText = ""
                    resetScroll()
                    markStateChanged()
                    return true
                }
            }
        }

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            close()
            return true
        }

        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        editingValue?.let {
            if (chr.code in 32..126) {
                it.text += chr
                return true
            }
        }

        if (searchFocused && (chr.isLetterOrDigit() || chr == ' ' || chr == '_' || chr == '-')) {
            searchText += chr
            resetScroll()
            markStateChanged()
            return true
        }

        return super.charTyped(chr, modifiers)
    }

    private fun collectSettings(module: ClientModule): List<SettingEntry> {
        val entries = ArrayList<SettingEntry>()
        collectSettings(module, module, 0, entries)
        return entries
    }

    private fun collectSettings(rootModule: ClientModule, configurable: Configurable, depth: Int, out: MutableList<SettingEntry>) {
        for (value in configurable.inner) {
            collectSetting(rootModule, value, depth, out)
        }
    }

    private fun collectSetting(rootModule: ClientModule, value: Value<*>, depth: Int, out: MutableList<SettingEntry>) {
        if (value.notAnOption) {
            return
        }

        when (value) {
            is ChoiceConfigurable<*> -> {
                val entry = SettingEntry(EntryKind.CHOICE, value, rootModule, depth, sectionKey(rootModule, value))
                out += entry
                collectSettings(rootModule, value.activeChoice, depth + 1, out)
            }
            is ToggleableConfigurable -> {
                val entry = SettingEntry(EntryKind.TOGGLE_SECTION, value, rootModule, depth, sectionKey(rootModule, value))
                out += entry
                if (sectionOpen(entry)) {
                    value.inner.filterNot { it.name == "Enabled" }.forEach { child ->
                        collectSetting(rootModule, child, depth + 1, out)
                    }
                }
            }
            is Configurable -> {
                val entry = SettingEntry(EntryKind.SECTION, value, rootModule, depth, sectionKey(rootModule, value))
                out += entry
                if (sectionOpen(entry)) {
                    collectSettings(rootModule, value, depth + 1, out)
                }
            }
            else -> collectValue(rootModule, value, depth, out)
        }
    }

    private fun collectValue(rootModule: ClientModule, value: Value<*>, depth: Int, out: MutableList<SettingEntry>) {
        if (value.notAnOption) {
            return
        }

        val kind = when (value.valueType) {
            ValueType.BOOLEAN -> EntryKind.BOOLEAN
            ValueType.TOGGLEABLE -> if (value.get() is Boolean) EntryKind.BOOLEAN else EntryKind.DISPLAY
            ValueType.FLOAT, ValueType.INT -> EntryKind.NUMBER
            ValueType.FLOAT_RANGE, ValueType.INT_RANGE -> EntryKind.RANGE
            ValueType.CHOOSE, ValueType.CHOICE, ValueType.MULTI_CHOOSE -> EntryKind.CHOICE
            ValueType.TEXT -> EntryKind.TEXT
            ValueType.BIND -> EntryKind.BIND
            ValueType.KEY -> EntryKind.KEY
            else -> EntryKind.DISPLAY
        }

        out += SettingEntry(kind, value, rootModule, depth, sectionKey(rootModule, value))
    }

    private fun filteredModules(category: Category): List<ClientModule> {
        val modules = modulesByCategory[category].orEmpty()
        if (searchText.isBlank()) {
            return modules
        }

        return modules.filter { module ->
            module.name.contains(searchText, ignoreCase = true) ||
                module.description.get().orEmpty().contains(searchText, ignoreCase = true)
        }
    }

    private fun moduleHasSettings(module: ClientModule): Boolean = collectSettings(module).isNotEmpty()

    private fun moduleTotalHeight(module: ClientModule): Float =
        FUNCTION_HEIGHT + settingsHeight(module) * expandProgress(module)

    private fun settingsHeight(module: ClientModule): Float = collectSettings(module).sumOf { settingHeight(it).toDouble() }.toFloat()

    private fun settingRowHeight(entry: SettingEntry): Float = baseSettingHeight(entry) - SETTING_ROW_PAD_Y * 2f

    private fun settingTextY(entry: SettingEntry, y: Float, size: Float): Float = y + (settingRowHeight(entry) - size) * 0.5f

    private fun baseSettingHeight(entry: SettingEntry): Float = when (entry.kind) {
        EntryKind.SECTION, EntryKind.TOGGLE_SECTION -> 34f
        EntryKind.BOOLEAN -> 48f
        EntryKind.NUMBER, EntryKind.RANGE -> 58f
        EntryKind.CHOICE, EntryKind.TEXT, EntryKind.BIND, EntryKind.KEY, EntryKind.DISPLAY -> 56f
    }

    private fun settingHeight(entry: SettingEntry): Float {
        val base = baseSettingHeight(entry)
        if (entry.kind != EntryKind.CHOICE || choiceDropDowns[entry.key] != true) {
            return base
        }

        return base + choiceNames(entry.value).size * CHOICE_OPTION_HEIGHT
    }

    private fun maxScroll(category: Category): Float {
        val total = filteredModules(category).sumOf { moduleTotalHeight(it).toDouble() }.toFloat()
        return max(0f, total - SCROLL_AREA_HEIGHT)
    }

    private fun expandProgress(module: ClientModule): Float {
        val target = if (module in expandedModules) 1f else 0f
        val current = expandProgress.getOrDefault(module, target)
        val next = current + (target - current) * 0.22f
        val snapped = if (abs(next - target) < 0.01f) target else next
        expandProgress[module] = snapped
        return snapped
    }

    private fun updateScroll(category: Category) {
        val current = scrollOffsets.getValue(category)
        val target = scrollTargets.getValue(category).coerceIn(0f, maxScroll(category))
        scrollTargets[category] = target
        scrollOffsets[category] = current + (target - current) * 0.28f
    }

    private fun sectionOpen(entry: SettingEntry): Boolean = expandedSections.getOrDefault(entry.key, true)

    private fun toggleSectionValue(entry: SettingEntry): Value<*>? =
        (entry.value as? ToggleableConfigurable)?.getEnabledValue()?.takeIf { booleanValue(it) != null }

    private fun toggleBoolean(module: ClientModule, value: Value<*>) {
        @Suppress("UNCHECKED_CAST")
        val typedValue = value as? Value<Boolean> ?: return
        val current = booleanValue(value) ?: return
        typedValue.set(!current)
        store(module)
        playGuiSound(GuiSound.BOOLEAN)
    }

    private fun booleanValue(value: Value<*>): Boolean? = value.get() as? Boolean

    private fun cycleChoice(module: ClientModule, value: Value<*>, step: Int) {
        when (value) {
            is ChoiceConfigurable<*> -> {
                val choices = value.choices
                if (choices.isEmpty()) {
                    return
                }
                val index = choices.indexOf(value.activeChoice)
                val next = choices[(index + step + choices.size) % choices.size]
                value.setByString(next.choiceName)
                store(module)
                playGuiSound(GuiSound.BOOLEAN)
            }
            is ChooseListValue<*> -> {
                val choices = value.choices
                if (choices.isEmpty()) {
                    return
                }
                val current = value.get()
                val index = choices.indexOf(current)
                val next = choices[(index + step + choices.size) % choices.size]
                value.setByString(next.choiceName)
                store(module)
                playGuiSound(GuiSound.BOOLEAN)
            }
            is MultiChooseListValue<*> -> {
                // Multi-choice editing stays textual for now; left click intentionally does nothing.
            }
            else -> {
                val current = value.get()
                if (current is NamedChoice) {
                    // Covers simple values backed by NamedChoice through setByString.
                    value.setByString(current.choiceName)
                    store(module)
                    playGuiSound(GuiSound.BOOLEAN)
                }
            }
        }
    }

    private fun choiceNames(value: Value<*>): List<String> = when (value) {
        is ChoiceConfigurable<*> -> value.choices.map { it.choiceName }
        is ChooseListValue<*> -> value.choices.map { it.choiceName }
        is MultiChooseListValue<*> -> value.choices.map { choiceName(it) }
        else -> emptyList()
    }

    private fun choiceSelected(value: Value<*>, index: Int): Boolean = when (value) {
        is ChoiceConfigurable<*> -> value.choices.getOrNull(index) === value.activeChoice
        is ChooseListValue<*> -> value.choices.getOrNull(index) == value.get()
        is MultiChooseListValue<*> -> value.choices.toList().getOrNull(index) in value.get()
        else -> false
    }

    @Suppress("UNCHECKED_CAST")
    private fun selectChoice(module: ClientModule, value: Value<*>, index: Int) {
        when (value) {
            is ChoiceConfigurable<*> -> {
                val choice = value.choices.getOrNull(index) ?: return
                value.setByString(choice.choiceName)
                choiceDropDowns[sectionKey(module, value)] = false
            }
            is ChooseListValue<*> -> {
                val choice = value.choices.getOrNull(index) ?: return
                value.setByString(choice.choiceName)
                choiceDropDowns[sectionKey(module, value)] = false
            }
            is MultiChooseListValue<*> -> {
                val multi = value as MultiChooseListValue<Any>
                val choice = multi.choices.toList().getOrNull(index) ?: return
                multi.toggle(choice)
            }
            else -> return
        }
        store(module)
        playGuiSound(GuiSound.BOOLEAN)
    }

    private fun updateNumber(module: ClientModule, value: Value<*>, x: Float, width: Float, mouseX: Double) {
        val (minValue, maxValue) = numberRange(value)
        val progress = ((mouseX - x) / width).coerceIn(0.0, 1.0)
        val next = minValue + (maxValue - minValue) * progress

        @Suppress("UNCHECKED_CAST")
        when (value.get()) {
            is Int -> (value as Value<Int>).set(next.roundToInt())
            is Float -> (value as Value<Float>).set(roundFloat(next.toFloat()))
        }
        store(module)
    }

    private fun updateRange(module: ClientModule, value: Value<*>, x: Float, width: Float, mouseX: Double, start: Boolean) {
        val (minValue, maxValue) = numberRange(value)
        val progress = ((mouseX - x) / width).coerceIn(0.0, 1.0)
        val next = minValue + (maxValue - minValue) * progress

        @Suppress("UNCHECKED_CAST")
        when (val current = value.get()) {
            is IntRange -> {
                val nextInt = next.roundToInt()
                val newRange = if (start) min(nextInt, current.last)..current.last else current.first..max(nextInt, current.first)
                (value as Value<IntRange>).set(newRange)
            }
            is ClosedFloatingPointRange<*> -> {
                val currentStart = (current.start as Number).toFloat()
                val currentEnd = (current.endInclusive as Number).toFloat()
                val nextFloat = roundFloat(next.toFloat())
                val newRange = if (start) min(nextFloat, currentEnd)..currentEnd else currentStart..max(nextFloat, currentStart)
                (value as Value<ClosedFloatingPointRange<Float>>).set(newRange)
            }
        }
        store(module)
    }

    private fun chooseRangeStart(value: Value<*>, mouseX: Double, x: Float, width: Float): Boolean {
        val (minValue, maxValue) = numberRange(value)
        val (start, end) = rangeValues(value)
        val span = (maxValue - minValue).coerceAtLeast(0.0001)
        val startX = x + width * ((start - minValue) / span).toFloat()
        val endX = x + width * ((end - minValue) / span).toFloat()
        return abs(mouseX - startX) <= abs(mouseX - endX)
    }

    private fun setModuleBind(module: ClientModule, keyCode: Int) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_DELETE) {
            module.bind.unbind()
        } else {
            module.bind.bind(InputUtil.Type.KEYSYM.createFromCode(keyCode))
        }
        store(module)
    }

    private fun setBindValue(module: ClientModule, value: BindValue, keyCode: Int) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_DELETE) {
            value.get().unbind()
        } else {
            value.get().bind(InputUtil.Type.KEYSYM.createFromCode(keyCode))
        }
        store(module)
    }

    private fun commitTextEdit(edit: TextEdit) {
        runCatching {
            edit.value.setByString(edit.text)
            store(edit.module)
        }
    }

    private fun store(module: ClientModule) {
        runCatching { ConfigSystem.storeConfigurable(ModuleManager.modulesConfigurable) }
    }

    private fun displayValue(value: Value<*>): String = when (val current = value.get()) {
        is InputBind -> current.keyName
        is InputUtil.Key -> current.translationKey.substringAfterLast('.').uppercase()
        is Color4b -> "#%08X".format(current.toARGB())
        is NamedChoice -> current.choiceName
        is Collection<*> -> current.joinToString(", ") { it.toString() }
        is ClosedFloatingPointRange<*> -> "${formatNumber(current.start)}..${formatNumber(current.endInclusive)}"
        else -> current.toString()
    }

    private fun choiceLabel(value: Value<*>): String = when (value) {
        is ChoiceConfigurable<*> -> value.activeChoice.choiceName
        is ChooseListValue<*> -> value.get().choiceName
        is MultiChooseListValue<*> -> "${value.get().size}/${value.choices.size}"
        else -> displayValue(value)
    }

    private fun choiceName(choice: Any?): String = when (choice) {
        is NamedChoice -> choice.choiceName
        else -> choice.toString()
    }

    private fun rangeLabel(value: Value<*>): String = when (val current = value.get()) {
        is IntRange -> "${current.first}..${current.last}"
        is ClosedFloatingPointRange<*> -> "${formatNumber(current.start)}..${formatNumber(current.endInclusive)}"
        else -> current.toString()
    }

    private fun rangeValues(value: Value<*>): Pair<Double, Double> = when (val current = value.get()) {
        is IntRange -> current.first.toDouble() to current.last.toDouble()
        is ClosedFloatingPointRange<*> -> (current.start as Number).toDouble() to (current.endInclusive as Number).toDouble()
        else -> 0.0 to 1.0
    }

    private fun numberRange(value: Value<*>): Pair<Double, Double> {
        val range = (value as? RangedValue<*>)?.range ?: return when (value.get()) {
            is Int -> 0.0 to 100.0
            is Float -> 0.0 to 10.0
            is IntRange -> 0.0 to 100.0
            is ClosedFloatingPointRange<*> -> 0.0 to 10.0
            else -> 0.0 to 1.0
        }

        val start = range.start as? Number ?: return 0.0 to 1.0
        val end = range.endInclusive as? Number ?: return 0.0 to 1.0
        return start.toDouble() to end.toDouble()
    }

    private fun formatNumber(value: Any?): String = when (value) {
        is Int -> value.toString()
        is Float -> "%.2f".format(java.util.Locale.US, value)
        is Double -> "%.2f".format(java.util.Locale.US, value)
        is Number -> "%.2f".format(java.util.Locale.US, value.toDouble())
        else -> value.toString()
    }

    private fun roundFloat(value: Float): Float = (value * 100f).roundToInt() / 100f

    private fun truncate(text: String, maxWidth: Float, size: Float): String {
        if (ExosGuiRenderer.textWidth(text, size) <= maxWidth) {
            return text
        }

        var out = text
        while (out.length > 3 && ExosGuiRenderer.textWidth("$out...", size) > maxWidth) {
            out = out.dropLast(1)
        }
        return "$out..."
    }

    private fun resetScroll() {
        renderCategories.forEach {
            scrollTargets[it] = 0f
            scrollOffsets[it] = 0f
        }
    }

    private fun restoreSavedState() {
        val state = ClickGuiConfigManager.loadScreenState()
        val modulesByName = ModuleManager.getModules().associateBy { it.name }

        expandedModules.clear()
        state.expandedModules.orEmpty()
            .mapNotNull(modulesByName::get)
            .forEach { module ->
                expandedModules += module
                expandProgress[module] = 1f
            }

        expandedSections.clear()
        expandedSections.putAll(state.expandedSections.orEmpty())

        searchText = state.searchText.orEmpty()
        selectedTheme = (state.selectedTheme ?: 0).coerceIn(THEME_COLORS.indices)

        renderCategories.forEach { category ->
            val key = category.name
            val target = state.scrollTargets.orEmpty()[key]
                ?: state.scrollOffsets.orEmpty()[key]
                ?: 0f
            val clamped = target.coerceIn(0f, maxScroll(category))
            scrollTargets[category] = clamped
            scrollOffsets[category] = clamped
        }
    }

    private fun saveCurrentState() {
        stateSaved = false
        val targets = renderCategories.associate { category ->
            category.name to scrollTargets.getOrDefault(category, scrollOffsets.getOrDefault(category, 0f))
        }
        val offsets = renderCategories.associate { category ->
            category.name to scrollOffsets.getOrDefault(category, targets.getValue(category.name))
        }

        ClickGuiConfigManager.saveScreenState(
            ClickGuiScreenState(
                expandedModules = expandedModules.map { it.name }.sorted().toSet(),
                expandedSections = expandedSections.toMap(),
                scrollOffsets = offsets,
                scrollTargets = targets,
                searchText = searchText,
                selectedTheme = selectedTheme
            )
        )
    }

    private fun markStateChanged() {
        stateSaved = false
    }

    private fun animate(cache: MutableMap<String, Float>, key: String, target: Float, speed: Float): Float {
        val current = cache.getOrDefault(key, target)
        val next = current + (target - current) * speed
        val snapped = if (abs(next - target) < 0.002f) target else next
        cache[key] = snapped
        return snapped
    }

    private fun panelStartX() = (width - (renderCategories.size * (PANEL_WIDTH + PANEL_MARGIN) - PANEL_MARGIN)) / 2
    private fun panelStartY() = (height - PANEL_HEIGHT) / 2

    private fun searchBounds() = Bounds(((width - SEARCH_WIDTH) * 0.5f), (height - 33).toFloat(), SEARCH_WIDTH.toFloat(), SEARCH_HEIGHT.toFloat())

    private fun drawCategoryIcon(context: DrawContext, category: Category, x: Float, y: Float, size: Float) {
        val matrix = context.matrices
        val bright = categoryAccent(category)
        val dim = ExosGuiRenderer.withAlpha(bright, 125)
        val s = size / 3.2f
        val gap = size / 8f

        when (category) {
            Category.COMBAT -> {
                ExosGuiRenderer.roundedRect(matrix, x + 1f, y + 2f, s, s * 2.15f, 1f, bright)
                ExosGuiRenderer.roundedRect(matrix, x + s + gap, y + 1f, s * 1.75f, s, 1f, dim)
                ExosGuiRenderer.roundedRect(matrix, x + s + gap, y + s + gap + 1f, s * 1.75f, s, 1f, bright)
            }
            Category.MOVEMENT -> {
                ExosGuiRenderer.roundedRect(matrix, x + 1f, y + size * 0.35f, size - 2f, s, 1f, bright)
                ExosGuiRenderer.roundedRect(matrix, x + size * 0.52f, y + 1f, s, size - 2f, 1f, dim)
            }
            Category.RENDER -> {
                ExosGuiRenderer.roundedRect(matrix, x + 1f, y + 3f, size - 2f, size - 6f, 4f, dim)
                ExosGuiRenderer.roundedRect(matrix, x + size * 0.37f, y + size * 0.37f, size * 0.27f, size * 0.27f, 3f, bright)
            }
            Category.PLAYER -> {
                ExosGuiRenderer.roundedRect(matrix, x + size * 0.34f, y + 1f, size * 0.32f, size * 0.32f, 2f, bright)
                ExosGuiRenderer.roundedRect(matrix, x + 2f, y + size * 0.52f, size - 4f, size * 0.35f, 3f, dim)
            }
            Category.MISC -> {
                ExosGuiRenderer.roundedRect(matrix, x + 1f, y + 1f, s, s, 1f, bright)
                ExosGuiRenderer.roundedRect(matrix, x + size - s - 1f, y + 1f, s, s, 1f, dim)
                ExosGuiRenderer.roundedRect(matrix, x + 1f, y + size - s - 1f, s, s, 1f, dim)
                ExosGuiRenderer.roundedRect(matrix, x + size - s - 1f, y + size - s - 1f, s, s, 1f, bright)
            }
        }
    }

    private fun categoryAccent(category: Category): Int = when (category) {
        Category.COMBAT -> accentB
        Category.MOVEMENT -> accentA
        Category.RENDER -> accentB
        Category.PLAYER -> ExosGuiRenderer.mix(accentA, 0xFFFFFFFF.toInt(), 0.25f)
        Category.MISC -> ExosGuiRenderer.mix(accentB, accentA, 0.45f)
    }

    private fun playGuiSound(sound: GuiSound) {
        soundStep = (soundStep + 1) and 7
        val pitchJitter = (soundStep - 3) * sound.jitter
        val pitch = (sound.pitch + pitchJitter).coerceIn(0.75f, 1.35f)
        mc.soundManager.play(PositionedSoundInstance.master(SoundEvent.of(sound.id), pitch, sound.volume))
    }

    private enum class GuiSound(
        val id: Identifier,
        val pitch: Float = 1f,
        val volume: Float = 0.42f,
        val jitter: Float = 0.01f
    ) {
        BOOLEAN(Identifier.of("liquidbounce", "clickgui_boolean"), 1.13f, 0.29f, 0.006f),
        SCROLL(Identifier.of("liquidbounce", "clickgui_scroll"), 0.91f, 0.16f, 0.004f),
        SWIPE_IN(Identifier.of("liquidbounce", "clickgui_swipein"), 1.08f, 0.30f, 0.005f),
        SWIPE_OUT(Identifier.of("liquidbounce", "clickgui_swipeout"), 0.87f, 0.28f, 0.005f)
    }

    private fun sectionKey(module: ClientModule, value: Value<*>) = "${module.name}:${System.identityHashCode(value)}"

    private fun easeOpen(): Float {
        val progress = ((System.currentTimeMillis() - openStartedAt).toFloat() / 180f).coerceIn(0f, 1f)
        return if (progress < 0.5f) {
            2f * progress * progress
        } else {
            1f - (-2f * progress + 2f).let { it * it } * 0.5f
        }
    }

    override fun shouldPause(): Boolean = false

    override fun close() {
        saveStateOnce()
        mc.mouse.lockCursor()
        super.close()
    }

    override fun removed() {
        saveStateOnce()
        super.removed()
    }

    private fun saveStateOnce() {
        if (!stateSaved) {
            saveCurrentState()
            stateSaved = true
        }
    }

    private data class SettingEntry(
        val kind: EntryKind,
        val value: Value<*>,
        val module: ClientModule,
        val depth: Int,
        val key: String
    )

    private enum class EntryKind {
        SECTION,
        TOGGLE_SECTION,
        BOOLEAN,
        NUMBER,
        RANGE,
        CHOICE,
        TEXT,
        BIND,
        KEY,
        DISPLAY
    }

    private sealed class Dragging {
        data class Number(val module: ClientModule, val value: Value<*>, val x: Float, val width: Float) : Dragging()
        data class Range(
            val module: ClientModule,
            val value: Value<*>,
            val x: Float,
            val width: Float,
            val start: Boolean
        ) : Dragging()
    }

    private data class TextEdit(val module: ClientModule, val value: Value<*>, var text: String)
    private data class Bounds(val x: Float, val y: Float, val w: Float, val h: Float)

    private companion object {
        const val PANEL_WIDTH = 175
        const val PANEL_HEIGHT = 340
        const val PANEL_MARGIN = 10
        const val TITLE_MARGIN_TOP = 5
        const val TITLE_HEIGHT = 27
        const val CATEGORY_TEXT_SIZE = 14f
        const val FUNCTION_HEIGHT = 32f
        const val MODULE_ROW_PAD_Y = 3.5f
        const val MODULE_TEXT_SIZE = 14f
        const val SETTING_ROW_PAD_Y = 4f
        const val SETTING_TEXT_SIZE = 13f
        const val SWITCH_WIDTH = 25f
        const val SWITCH_HEIGHT = 12f
        const val CHOICE_OPTION_HEIGHT = 22f
        const val SCROLL_AREA_HEIGHT = PANEL_HEIGHT - TITLE_MARGIN_TOP - TITLE_HEIGHT - 5
        const val SEARCH_WIDTH = 195
        const val SEARCH_HEIGHT = 22
        const val THEME_CHIP_SIZE = 13f
        const val THEME_CHIP_GAP = 6f
        const val THEME_BAR_HEIGHT = 21f

        val THEME_COLORS = arrayOf(
            0xFF35DBFF.toInt() to 0xFFB84DFF.toInt(),
            0xFF00FFA8.toInt() to 0xFF00B7FF.toInt(),
            0xFFFFD166.toInt() to 0xFFFF4D8D.toInt(),
            0xFF9AFF6B.toInt() to 0xFFFFF56B.toInt(),
            0xFFFF6B6B.toInt() to 0xFFFFA35C.toInt(),
            0xFFA78BFA.toInt() to 0xFF5EEAD4.toInt()
        )

    }
}
