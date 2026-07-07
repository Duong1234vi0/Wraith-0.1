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
package net.ccbluex.liquidbounce.features.module.modules.render.esp.modes

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.features.module.modules.render.nametags.ModuleNameTags
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForGUI
import net.ccbluex.liquidbounce.render.utils.rainbow
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.armorItems
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.drawStackOverlay
import net.ccbluex.liquidbounce.utils.entity.getActualHealth
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.entity.offset
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.decoration.ArmorStand
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.LingeringPotionItem
import net.minecraft.world.item.PotionItem
import net.minecraft.world.item.SplashPotionItem
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

object EspCelestialMode : EspMode("Celestial") {

    private val borderMode by enumChoice("BorderMode", BorderMode.BOX)
    private val borderRect by boolean("BorderRect", true)
    private val renderTags by boolean("RenderTags", true)
    private val tagsBackground by boolean("TagsBackground", true)
    private val renderArmor by boolean("RenderArmor", true)
    private val healthBar by boolean("HealthBar", true)
    private val healthText by boolean("HealthText", true)
    private val healthColorMode by enumChoice("HealthColorMode", HealthColorMode.HEALTH)
    private val healthColor by color("HealthColor", Color4b.WHITE)
    private val friendHighlight by boolean("FriendHighlight", false)
    private val mobEsp by boolean("MobESP", false)
    private val itemEsp by boolean("ItemESP", true)
    private val itemBoxes by boolean("ItemBoxes", true)
    private val itemTags by boolean("ItemTags", true)
    private val itemHighlight by boolean("ItemHighlight", false)
    private val itemHighlightColor by color("ItemHighlightColor", Color4b(255, 213, 80, 255))
    private val colorMode by enumChoice("ColorMode", BoxColorMode.THEME)
    private val color by color("Color", Color4b.WHITE)
    private val itemColorMode by enumChoice("ItemColorMode", BoxColorMode.THEME)
    private val itemColor by color("ItemColor", Color4b.WHITE)

    @Suppress("unused")
    private val renderHandler = handler<OverlayRenderEvent> { event ->
        val level = mc.level ?: return@handler
        val localPlayer = mc.player ?: return@handler

        val tickDelta = event.tickDelta
        val records = ArrayList<RenderRecord>(96)

        for (entity in level.entitiesForRendering()) {
            if (records.size >= MAX_RECORDS) {
                break
            }
            if (!shouldRenderEntity(entity)) {
                continue
            }
            if (entity.distanceToSqr(localPlayer) > MAX_DISTANCE_SQ) {
                continue
            }

            val box = entity.screenBox(tickDelta) ?: continue
            val projectedBounds = projectBox(box) ?: continue
            val bounds = projectedBounds.withMinimumSize(
                if (borderMode == BorderMode.CORNER) MIN_CORNER_BOX_WIDTH else MIN_BOX_WIDTH,
                if (borderMode == BorderMode.CORNER) MIN_CORNER_BOX_HEIGHT else MIN_BOX_HEIGHT
            )
            if (bounds.width < MIN_BOX_WIDTH || bounds.height < MIN_BOX_HEIGHT) {
                continue
            }

            records += RenderRecord(entity, bounds, entity.distanceToSqr(localPlayer))
        }

        records.sortByDescending { it.distanceSqr }

        renderEnvironmentForGUI {
            records.forEachIndexed { index, record ->
                drawRecord(event.context, record, index)
            }
        }
    }

    override fun shouldCancelVanillaNametag(entity: Entity): Boolean {
        return renderTags && entity is LivingEntity && shouldRenderLiving(entity)
    }

    private fun drawRecord(context: DrawContext, record: RenderRecord, zIndex: Int) {
        when (val entity = record.entity) {
            is ItemEntity -> drawItemEntity(context, entity, record.bounds, zIndex)
            is LivingEntity -> drawLivingEntity(context, entity, record.bounds, zIndex)
        }
    }

    private fun drawLivingEntity(context: DrawContext, entity: LivingEntity, bounds: ScreenBounds, zIndex: Int) {
        val color = entityColor(entity)

        if (borderRect) {
            drawBorder(context, bounds, color)
        }

        if (healthBar) {
            drawHealthBar(context, entity, bounds)
        }

        if (renderTags && !(entity is Player && ModuleNameTags.running)) {
            drawLivingTag(context, entity, bounds)
        }

        if (renderArmor && entity is Player) {
            drawEquipment(context, entity, bounds, zIndex)
        }
    }

    private fun drawItemEntity(context: DrawContext, entity: ItemEntity, bounds: ScreenBounds, zIndex: Int) {
        val stack = entity.item
        val color = itemEntityColor(stack)

        if (itemBoxes) {
            drawBorder(context, bounds, color)
        }

        if (itemTags) {
            val count = if (stack.count > 1) " x${stack.count}" else ""
            drawCenteredText(
                context,
                stack.hoverName.string + count,
                bounds.centerX,
                bounds.top - TAG_OFFSET,
                color,
                TAG_TEXT_SIZE,
                background = true
            )
        }
    }

    private fun drawBorder(context: DrawContext, bounds: ScreenBounds, color: Int) {
        when (borderMode) {
            BorderMode.BOX -> drawBoxBorder(context, bounds, color)
            BorderMode.CORNER -> drawCornerBorder(context, bounds, color)
        }
    }

    private fun drawBoxBorder(context: DrawContext, bounds: ScreenBounds, color: Int) {
        val left = bounds.left
        val top = bounds.top
        val right = bounds.right
        val bottom = bounds.bottom

        drawHorizontalSegment(context, left, right, top, BACKING_THICKNESS, BLACK)
        drawHorizontalSegment(context, left, right, bottom, BACKING_THICKNESS, BLACK)
        drawVerticalSegment(context, left, top, bottom, BACKING_THICKNESS, BLACK)
        drawVerticalSegment(context, right, top, bottom, BACKING_THICKNESS, BLACK)

        drawHorizontalSegment(context, left, right, top, LINE_THICKNESS, color)
        drawHorizontalSegment(context, left, right, bottom, LINE_THICKNESS, color)
        drawVerticalSegment(context, left, top, bottom, LINE_THICKNESS, color)
        drawVerticalSegment(context, right, top, bottom, LINE_THICKNESS, color)
    }

    private fun drawCornerBorder(context: DrawContext, bounds: ScreenBounds, color: Int) {
        if (bounds.width < MIN_CORNER_BOX_WIDTH || bounds.height < MIN_CORNER_BOX_HEIGHT) {
            drawBoxBorder(context, bounds, color)
            return
        }

        val horizontal = cornerLength(bounds.width / 3.0f, CORNER_MIN_HORIZONTAL, bounds.width * CORNER_MAX_PORTION)
        val vertical = cornerLength(bounds.height / 4.0f, CORNER_MIN_VERTICAL, bounds.height * CORNER_MAX_PORTION)

        fun drawCornerLine(x1: Float, y1: Float, x2: Float, y2: Float, horizontalLine: Boolean, color: Int) {
            if (horizontalLine) {
                drawHorizontalSegment(context, x1, x2, y1, LINE_THICKNESS, color)
            } else {
                drawVerticalSegment(context, x1, y1, y2, LINE_THICKNESS, color)
            }
        }

        fun drawCornerBack(x1: Float, y1: Float, x2: Float, y2: Float, horizontalLine: Boolean) {
            if (horizontalLine) {
                drawHorizontalSegment(context, x1, x2, y1, BACKING_THICKNESS, BLACK)
            } else {
                drawVerticalSegment(context, x1, y1, y2, BACKING_THICKNESS, BLACK)
            }
        }

        val left = bounds.left
        val top = bounds.top
        val right = bounds.right
        val bottom = bounds.bottom

        drawCornerBack(left, top, left + horizontal, top, true)
        drawCornerBack(right - horizontal, top, right, top, true)
        drawCornerBack(left, bottom, left + horizontal, bottom, true)
        drawCornerBack(right - horizontal, bottom, right, bottom, true)
        drawCornerBack(left, top, left, top + vertical, false)
        drawCornerBack(right, top, right, top + vertical, false)
        drawCornerBack(left, bottom - vertical, left, bottom, false)
        drawCornerBack(right, bottom - vertical, right, bottom, false)

        drawCornerLine(left, top, left + horizontal, top, true, color)
        drawCornerLine(right - horizontal, top, right, top, true, color)
        drawCornerLine(left, bottom, left + horizontal, bottom, true, color)
        drawCornerLine(right - horizontal, bottom, right, bottom, true, color)
        drawCornerLine(left, top, left, top + vertical, false, color)
        drawCornerLine(right, top, right, top + vertical, false, color)
        drawCornerLine(left, bottom - vertical, left, bottom, false, color)
        drawCornerLine(right, bottom - vertical, right, bottom, false, color)
    }

    private fun drawHealthBar(context: DrawContext, entity: LivingEntity, bounds: ScreenBounds) {
        val maxHealth = entity.maxHealth.coerceAtLeast(1.0f)
        val health = entity.getActualHealth().coerceIn(0.0f, maxHealth)
        val ratio = (health / maxHealth).coerceIn(0.0f, 1.0f)
        val height = bounds.height * ratio
        val x = bounds.left - HEALTH_BAR_GAP - HEALTH_BAR_WIDTH
        val fillTop = bounds.bottom - height
        val color = healthBarColor(entity, ratio)

        drawFilledRect(context, x - 1.0f, bounds.top - 1.0f, x + HEALTH_BAR_WIDTH + 1.0f, bounds.bottom + 1.0f, BLACK)
        drawFilledRect(context, x, bounds.top, x + HEALTH_BAR_WIDTH, bounds.bottom, TRACK)
        drawFilledRect(context, x, fillTop, x + HEALTH_BAR_WIDTH, bounds.bottom, color)

        if (healthText) {
            val text = formatHealth(health)
            val width = ExosGuiRenderer.bodyWidth(text, HP_TEXT_SIZE)
            val textX = x - width - 2.0f
            val textY = (fillTop - HP_TEXT_SIZE * 0.5f).coerceIn(bounds.top - 2.0f, bounds.bottom - HP_TEXT_SIZE)
            ExosGuiRenderer.body(context.matrices, text, textX, textY, 0xFFFFFFFF.toInt(), HP_TEXT_SIZE, true)
        }
    }

    private fun drawLivingTag(context: DrawContext, entity: LivingEntity, bounds: ScreenBounds) {
        val name = entity.name.string
        val hp = formatHealth(entity.getActualHealth())
        val text = "$name [$hp HP]"
        drawCenteredText(
            context,
            text,
            bounds.centerX,
            bounds.top - TAG_OFFSET,
            0xFFFFFFFF.toInt(),
            TAG_TEXT_SIZE,
            tagsBackground
        )
    }

    private fun drawCenteredText(
        context: DrawContext,
        text: String,
        centerX: Float,
        baselineY: Float,
        color: Int,
        size: Float,
        background: Boolean
    ) {
        if (text.isEmpty()) {
            return
        }

        val width = ExosGuiRenderer.bodyWidth(text, size)
        val height = size + 4.0f
        val x = centerX - width * 0.5f
        val y = baselineY - height

        if (background) {
            ExosGuiRenderer.roundedRect(
                context.matrices,
                x - 3.0f,
                y + 1.0f,
                width + 6.0f,
                height,
                1.5f,
                TAG_BACKGROUND
            )
        }

        ExosGuiRenderer.body(context.matrices, text, x, y + 2.0f, color, size, true)
    }

    private fun drawEquipment(context: DrawContext, entity: Player, bounds: ScreenBounds, zIndex: Int) {
        val localPlayer = mc.player ?: return
        if (entity.distanceToSqr(localPlayer) > ARMOR_RENDER_DISTANCE_SQ || bounds.height < EQUIPMENT_MIN_COLUMN_HEIGHT) {
            return
        }

        val stacks = equipmentStacks(entity)
        if (stacks.isEmpty()) {
            return
        }

        val layout = equipmentLayout(bounds, stacks.size) ?: return
        var x = bounds.right + EQUIPMENT_SIDE_GAP
        val screenWidth = mc.window.guiScaledWidth.toFloat()
        if (x + layout.iconSize > screenWidth - SCREEN_EDGE_PADDING) {
            x = bounds.left - EQUIPMENT_SIDE_GAP - layout.iconSize
        }
        if (x < SCREEN_EDGE_PADDING || x + layout.iconSize > screenWidth - SCREEN_EDGE_PADDING) {
            return
        }

        val startY = (bounds.centerY - layout.columnHeight * 0.5f)
            .coerceIn(SCREEN_EDGE_PADDING, mc.window.guiScaledHeight.toFloat() - layout.columnHeight - SCREEN_EDGE_PADDING)
        var y = startY

        for ((index, stack) in stacks.withIndex()) {
            context.pose().pushMatrix()
            try {
                context.pose().translate(x, y)
                context.pose().scale(layout.scale, layout.scale)
                context.drawItem(stack, 0, 0, zIndex + index)
                context.drawStackOverlay(mc.font, stack, 0, 0)
            } finally {
                context.pose().popMatrix()
            }
            y += layout.iconSize + layout.gap
        }
    }

    private fun equipmentStacks(entity: Player): List<ItemStack> {
        val stacks = ArrayList<ItemStack>(6)
        entity.armorItems.forEach { stack ->
            if (!stack.isEmpty) {
                stacks += stack
            }
        }
        if (!entity.mainHandItem.isEmpty) {
            stacks += entity.mainHandItem
        }
        if (!entity.offhandItem.isEmpty) {
            stacks += entity.offhandItem
        }
        return stacks
    }

    private fun shouldRenderEntity(entity: Entity): Boolean {
        if (entity.isRemoved) {
            return false
        }
        if (entity is ItemEntity) {
            return itemEsp && !entity.item.isEmpty
        }
        if (entity !is LivingEntity) {
            return false
        }
        return shouldRenderLiving(entity)
    }

    private fun shouldRenderLiving(entity: LivingEntity): Boolean {
        val localPlayer = mc.player ?: return false
        if (!entity.isAlive || entity is ArmorStand) {
            return false
        }
        if (entity === localPlayer) {
            return false
        }
        if (entity is Player) {
            return !entity.isSpectator
        }
        return mobEsp
    }

    private fun Entity.screenBox(tickDelta: Float): AABB? {
        val interpolatedPosition = interpolateCurrentPosition(tickDelta)
        val positionOffset = interpolatedPosition.subtract(position())
        val box = boundingBox.offset(positionOffset)
            .inflate(if (this is ItemEntity) ITEM_BOX_EXPAND else ENTITY_BOX_EXPAND)
        return if (box.isFinite()) box else null
    }

    private fun projectBox(box: AABB): ScreenBounds? {
        val screenWidth = mc.window.guiScaledWidth.toFloat()
        val screenHeight = mc.window.guiScaledHeight.toFloat()
        var minX = Float.POSITIVE_INFINITY
        var minY = Float.POSITIVE_INFINITY
        var maxX = Float.NEGATIVE_INFINITY
        var maxY = Float.NEGATIVE_INFINITY
        var projected = 0

        fun project(x: Double, y: Double, z: Double) {
            val screen = WorldToScreen.calculateScreenPos(Vec3(x, y, z)) ?: return
            if (screen.z < MIN_PROJECTED_DEPTH || screen.z > MAX_PROJECTED_DEPTH) {
                return
            }
            val sx = screen.x.toFloat()
            val sy = screen.y.toFloat()
            if (!sx.isFinite() || !sy.isFinite()) {
                return
            }
            if (sx < -screenWidth * PROJECTION_SANITY_FACTOR ||
                sx > screenWidth * (PROJECTION_SANITY_FACTOR + 1.0f) ||
                sy < -screenHeight * PROJECTION_SANITY_FACTOR ||
                sy > screenHeight * (PROJECTION_SANITY_FACTOR + 1.0f)
            ) {
                return
            }
            minX = min(minX, sx)
            minY = min(minY, sy)
            maxX = max(maxX, sx)
            maxY = max(maxY, sy)
            projected++
        }

        project(box.minX, box.minY, box.minZ)
        project(box.minX, box.minY, box.maxZ)
        project(box.minX, box.maxY, box.minZ)
        project(box.minX, box.maxY, box.maxZ)
        project(box.maxX, box.minY, box.minZ)
        project(box.maxX, box.minY, box.maxZ)
        project(box.maxX, box.maxY, box.minZ)
        project(box.maxX, box.maxY, box.maxZ)
        project(box.center.x, box.center.y, box.center.z)
        project(box.center.x, box.maxY, box.center.z)
        project(box.center.x, box.minY, box.center.z)

        if (projected == 0) {
            return null
        }
        if (maxX < -PROJECTION_EDGE_MARGIN ||
            minX > screenWidth + PROJECTION_EDGE_MARGIN ||
            maxY < -PROJECTION_EDGE_MARGIN ||
            minY > screenHeight + PROJECTION_EDGE_MARGIN
        ) {
            return null
        }

        val left = minX.coerceIn(-PROJECTION_EDGE_MARGIN, screenWidth + PROJECTION_EDGE_MARGIN)
        val right = maxX.coerceIn(-PROJECTION_EDGE_MARGIN, screenWidth + PROJECTION_EDGE_MARGIN)
        val top = minY.coerceIn(-PROJECTION_EDGE_MARGIN, screenHeight + PROJECTION_EDGE_MARGIN)
        val bottom = maxY.coerceIn(-PROJECTION_EDGE_MARGIN, screenHeight + PROJECTION_EDGE_MARGIN)

        return ScreenBounds(
            min(left, right),
            min(top, bottom),
            max(left, right),
            max(top, bottom)
        )
    }

    private fun entityColor(entity: LivingEntity): Int {
        if (friendHighlight && entity is Player && FriendManager.isFriend(entity)) {
            return FRIEND
        }
        return boxColor(colorMode, color)
    }

    private fun itemEntityColor(stack: ItemStack): Int {
        if (itemHighlight && isImportantItem(stack)) {
            return itemHighlightColor.toARGB()
        }
        return boxColor(itemColorMode, itemColor)
    }

    private fun boxColor(mode: BoxColorMode, custom: Color4b): Int {
        return when (mode) {
            BoxColorMode.THEME -> withAlpha(ClientTheme.accentA, 255)
            BoxColorMode.CUSTOM -> custom.with(a = 255).toARGB()
            BoxColorMode.RAINBOW -> rainbow().with(a = 255).toARGB()
        }
    }

    private fun healthBarColor(entity: LivingEntity, ratio: Float): Int {
        return when (healthColorMode) {
            HealthColorMode.HEALTH -> {
                Color4b.RED.interpolateTo(Color4b.GREEN, ratio.toDouble()).with(a = 255).toARGB()
            }
            HealthColorMode.THEME -> withAlpha(ClientTheme.accentA, 255)
            HealthColorMode.CUSTOM -> healthColor.with(a = 255).toARGB()
        }
    }

    private fun isImportantItem(stack: ItemStack): Boolean {
        val item = stack.item
        val equipSlot = stack.get(DataComponents.EQUIPPABLE)?.slot
        return stack.isSword ||
            stack.isAxe ||
            equipSlot in ARMOR_SLOTS ||
            item is PotionItem ||
            item is SplashPotionItem ||
            item is LingeringPotionItem ||
            item == Items.GOLDEN_APPLE ||
            item == Items.ENCHANTED_GOLDEN_APPLE ||
            item == Items.FIREWORK_ROCKET
    }

    private fun drawHorizontalSegment(
        context: DrawContext,
        x1: Float,
        x2: Float,
        y: Float,
        thickness: Float,
        color: Int
    ) {
        val left = floor(min(x1, x2)).toInt()
        val right = ceil(max(x1, x2)).toInt().coerceAtLeast(left + 1)
        val pixelThickness = ceil(thickness).toInt().coerceAtLeast(1)
        val top = (y - pixelThickness * 0.5f).roundToInt()
        context.fill(left, top, right, top + pixelThickness, color)
    }

    private fun drawVerticalSegment(
        context: DrawContext,
        x: Float,
        y1: Float,
        y2: Float,
        thickness: Float,
        color: Int
    ) {
        val top = floor(min(y1, y2)).toInt()
        val bottom = ceil(max(y1, y2)).toInt().coerceAtLeast(top + 1)
        val pixelThickness = ceil(thickness).toInt().coerceAtLeast(1)
        val left = (x - pixelThickness * 0.5f).roundToInt()
        context.fill(left, top, left + pixelThickness, bottom, color)
    }

    private fun drawFilledRect(context: DrawContext, x1: Float, y1: Float, x2: Float, y2: Float, color: Int) {
        val left = floor(min(x1, x2)).toInt()
        val top = floor(min(y1, y2)).toInt()
        val right = ceil(max(x1, x2)).toInt().coerceAtLeast(left + 1)
        val bottom = ceil(max(y1, y2)).toInt().coerceAtLeast(top + 1)
        context.fill(left, top, right, bottom, color)
    }

    private fun cornerLength(value: Float, minLength: Float, maxLength: Float): Float {
        val safeMax = max(maxLength, LINE_THICKNESS)
        val safeMin = min(minLength, safeMax)
        return value.coerceIn(safeMin, safeMax)
    }

    private fun equipmentLayout(bounds: ScreenBounds, count: Int): EquipmentLayout? {
        val availableHeight = bounds.height - EQUIPMENT_VERTICAL_INSET * 2.0f
        if (count <= 0 || availableHeight < EQUIPMENT_MIN_COLUMN_HEIGHT) {
            return null
        }

        val maxIconSizeByHeight = if (count == 1) {
            availableHeight
        } else {
            (availableHeight - EQUIPMENT_MIN_GAP * (count - 1)) / count
        }
        if (maxIconSizeByHeight < EQUIPMENT_MIN_ICON_SIZE) {
            return null
        }

        val iconSize = maxIconSizeByHeight.coerceIn(EQUIPMENT_MIN_ICON_SIZE, EQUIPMENT_MAX_ICON_SIZE)
        val gap = if (count == 1) {
            0.0f
        } else {
            ((availableHeight - iconSize * count) / (count - 1)).coerceIn(EQUIPMENT_MIN_GAP, EQUIPMENT_MAX_GAP)
        }
        val columnHeight = iconSize * count + gap * (count - 1)

        return EquipmentLayout(iconSize, iconSize / VANILLA_ITEM_SIZE, gap, columnHeight)
    }

    private fun formatHealth(value: Float): String {
        return if (value >= 10.0f) {
            String.format(Locale.ROOT, "%.0f", value)
        } else {
            String.format(Locale.ROOT, "%.1f", value)
        }
    }

    private fun withAlpha(color: Int, alpha: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF)
    }

    private fun AABB.isFinite(): Boolean {
        return minX.isFinite() && minY.isFinite() && minZ.isFinite() &&
            maxX.isFinite() && maxY.isFinite() && maxZ.isFinite()
    }

    private data class RenderRecord(
        val entity: Entity,
        val bounds: ScreenBounds,
        val distanceSqr: Double
    )

    private data class ScreenBounds(
        val left: Float,
        val top: Float,
        val right: Float,
        val bottom: Float
    ) {
        val width: Float
            get() = right - left
        val height: Float
            get() = bottom - top
        val centerX: Float
            get() = (left + right) * 0.5f
        val centerY: Float
            get() = (top + bottom) * 0.5f

        fun withMinimumSize(minWidth: Float, minHeight: Float): ScreenBounds {
            val widthPad = ((minWidth - width).coerceAtLeast(0.0f)) * 0.5f
            val heightPad = ((minHeight - height).coerceAtLeast(0.0f)) * 0.5f

            if (widthPad == 0.0f && heightPad == 0.0f) {
                return this
            }

            return ScreenBounds(
                left - widthPad,
                top - heightPad,
                right + widthPad,
                bottom + heightPad
            )
        }
    }

    private data class EquipmentLayout(
        val iconSize: Float,
        val scale: Float,
        val gap: Float,
        val columnHeight: Float
    )

    private enum class BorderMode(override val choiceName: String) : NamedChoice {
        BOX("Box"),
        CORNER("Corner")
    }

    private enum class BoxColorMode(override val choiceName: String) : NamedChoice {
        THEME("Theme"),
        CUSTOM("Custom"),
        RAINBOW("Rainbow")
    }

    private enum class HealthColorMode(override val choiceName: String) : NamedChoice {
        HEALTH("Health"),
        THEME("Theme"),
        CUSTOM("Custom")
    }

    private const val MAX_RECORDS = 256
    private const val MAX_DISTANCE = 128.0
    private const val MAX_DISTANCE_SQ = MAX_DISTANCE * MAX_DISTANCE
    private const val ARMOR_RENDER_DISTANCE = 48.0
    private const val ARMOR_RENDER_DISTANCE_SQ = ARMOR_RENDER_DISTANCE * ARMOR_RENDER_DISTANCE
    private const val MIN_BOX_WIDTH = 4.0f
    private const val MIN_BOX_HEIGHT = 6.0f
    private const val MIN_CORNER_BOX_WIDTH = 8.0f
    private const val MIN_CORNER_BOX_HEIGHT = 12.0f
    private const val CORNER_MIN_HORIZONTAL = 2.5f
    private const val CORNER_MIN_VERTICAL = 3.0f
    private const val CORNER_MAX_PORTION = 0.42f
    private const val LINE_THICKNESS = 0.75f
    private const val BACKING_THICKNESS = 2.0f
    private const val HEALTH_BAR_WIDTH = 1.5f
    private const val HEALTH_BAR_GAP = 3.0f
    private const val TAG_OFFSET = 5.0f
    private const val TAG_TEXT_SIZE = 7.0f
    private const val HP_TEXT_SIZE = 6.5f
    private const val VANILLA_ITEM_SIZE = 16.0f
    private const val EQUIPMENT_MIN_COLUMN_HEIGHT = 18.0f
    private const val EQUIPMENT_MIN_ICON_SIZE = 8.0f
    private const val EQUIPMENT_MAX_ICON_SIZE = 16.0f
    private const val EQUIPMENT_MIN_GAP = 1.0f
    private const val EQUIPMENT_MAX_GAP = 3.0f
    private const val EQUIPMENT_SIDE_GAP = 5.0f
    private const val EQUIPMENT_VERTICAL_INSET = 1.0f
    private const val SCREEN_EDGE_PADDING = 1.0f
    private const val ENTITY_BOX_EXPAND = 0.03
    private const val ITEM_BOX_EXPAND = 0.06
    private const val MIN_PROJECTED_DEPTH = -1.0
    private const val MAX_PROJECTED_DEPTH = 1.0
    private const val PROJECTION_EDGE_MARGIN = 32.0f
    private const val PROJECTION_SANITY_FACTOR = 3.0f
    private const val BLACK = 0xD9000000.toInt()
    private const val TRACK = 0x7A101010
    private const val TAG_BACKGROUND = 0xA8000000.toInt()
    private const val FRIEND = 0xFF55FF55.toInt()

    private val ARMOR_SLOTS = setOf(
        EquipmentSlot.HEAD,
        EquipmentSlot.CHEST,
        EquipmentSlot.LEGS,
        EquipmentSlot.FEET,
        EquipmentSlot.BODY
    )
}
