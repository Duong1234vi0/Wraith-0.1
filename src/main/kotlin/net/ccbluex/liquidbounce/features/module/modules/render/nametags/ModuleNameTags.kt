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
package net.ccbluex.liquidbounce.features.module.modules.render.nametags

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.misc.antibot.ModuleAntiBot
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClickGuiScreen
import net.ccbluex.liquidbounce.render.gui.resolvePlayerSkin
import net.ccbluex.liquidbounce.render.font.msdf.FontRenderer
import net.ccbluex.liquidbounce.render.font.msdf.Fonts
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.drawStackOverlay
import net.ccbluex.liquidbounce.utils.entity.drawText
import net.ccbluex.liquidbounce.utils.entity.getActualHealth
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.DrawContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.contents.PlainTextContents
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.AABB
import java.util.Locale
import java.util.UUID
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Source-faithful port of AlphaDLC `NameTags`.
 *
 * Layout constants, font sizes (via [Fonts.getSize], which halves the display
 * size exactly like AlphaDLC), text composition, skin/armor rendering, and the
 * fixed GUI-pixel tag geometry mirror the reference. Held items intentionally
 * render as icons in the equipment row instead of AlphaDLC's text tags.
 * Tags do not scale with distance.
 */
object ModuleNameTags : ClientModule("NameTags", Category.RENDER, aliases = arrayOf("Nametags")) {

    private const val TAG_PADDING_TOP = 1.0f
    private const val TAG_PADDING_BOTTOM = 0.5f
    private const val TAG_BG_ROUND = 1.0f
    private val TAG_BG_COLOR = argb(150, 27, 27, 30)
    private const val EQUIPMENT_SLOT_SIZE = 14.0f
    private const val EQUIPMENT_ITEM_SCALE = 0.65f
    private const val EQUIPMENT_SLOT_STEP = 15.0f
    private const val EQUIPMENT_ROW_OFFSET = 23.666666984558105

    private const val FRIEND_RGB = 0x55FF55

    private val entityTypes by multiEnumChoice("EntityTypes", EntityType.PLAYERS, EntityType.ITEMS)
    private val playerSettings by multiEnumChoice("PlayerSettings", PlayerSetting.ARMOR, PlayerSetting.HELD_ITEMS)
        .apply {
            doNotIncludeWhen {
                EntityType.PLAYERS !in entityTypes &&
                    EntityType.MOBS !in entityTypes &&
                    EntityType.ANIMALS !in entityTypes &&
                    EntityType.FRIENDS !in entityTypes
            }
        }
    private val fontMode by enumChoice("Font", FontChoice.NEW)
    private val blur by boolean("Blur", false)

    private val players = ArrayList<Player>()
    private val playerTextCache = HashMap<UUID, MutableComponent>()
    private val playerSkinCache = HashMap<UUID, Identifier>()
    private val equipmentScratch = ArrayList<ItemStack>(6)
    private val playerLayouts = ArrayList<PlayerTagLayout>(64)
    private val genericLayouts = ArrayList<GenericTagLayout>(128)
    private var searchBox = AABB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    @JvmStatic
    fun shouldCancelVanillaNametag(entity: net.minecraft.world.entity.Entity): Boolean {
        // AlphaDLC PlayerEntityRendererMixin cancels vanilla PLAYER labels whenever
        // the module is enabled, independent of the Players selection.
        return entity is Player
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        players.clear()
        playerTextCache.clear()
        playerSkinCache.clear()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        players.clear()
        playerTextCache.clear()
        playerSkinCache.clear()
        val level = mc.level ?: return@handler
        for (player in level.players()) {
            if (player === mc.player || isGhost(player)) {
                continue
            }
            players += player
            val friend = FriendManager.isFriend(player)
            playerTextCache[player.uuid] = buildPlayerText(player, friend)
            playerSkinCache[player.uuid] = resolveSkin(player)
        }
    }

    override fun disable() {
        playerTextCache.clear()
        playerSkinCache.clear()
        players.clear()
        playerLayouts.clear()
        genericLayouts.clear()
        NameTagsBlur.invalidate()
    }

    @Suppress("unused", "LongMethod", "ComplexMethod")
    private val overlayHandler = handler<OverlayRenderEvent> { event ->
        val level = mc.level ?: return@handler
        val localPlayer = mc.player ?: return@handler
        if (mc.screen is ClickGuiScreen) {
            return@handler
        }
        NameTagsBlur.beginFrame()

        val context = event.context
        val useCustomFont = fontMode == FontChoice.NEW
        val font: FontRenderer? = if (useCustomFont) {
            Fonts.getSize(13, Fonts.FontType.SUISSE_SEMIBOLD)
        } else {
            null
        }
        val bigFont: FontRenderer? = if (useCustomFont) {
            Fonts.getSize(15, Fonts.FontType.SUISSE_MEDIUM)
        } else {
            null
        }

        val showPlayers = EntityType.PLAYERS in entityTypes
        val showFriends = EntityType.FRIENDS in entityTypes
        val showMobs = EntityType.MOBS in entityTypes
        val showAnimals = EntityType.ANIMALS in entityTypes
        val showItems = EntityType.ITEMS in entityTypes
        val showTnt = EntityType.TNT in entityTypes
        val showArmor = PlayerSetting.ARMOR in playerSettings
        val showHands = PlayerSetting.HELD_ITEMS in playerSettings
        val aresMine = isAresMineCompatibilityActive()

        if (!(showPlayers || showFriends || showMobs || showAnimals || showItems || showTnt)) {
            return@handler
        }

        playerLayouts.clear()
        genericLayouts.clear()
        val renderDistance = sqrt(AlphaProjection.maxRenderDistanceSq())
        searchBox = localPlayer.boundingBox.inflate(renderDistance)

        if (showPlayers || showFriends) {
            collectPlayerLayouts(showPlayers, showFriends, useCustomFont, font, aresMine)
        }
        if (showMobs || showAnimals || showItems || showTnt) {
            collectWorldLayouts(level, showMobs, showAnimals, showItems, showTnt, useCustomFont, font, bigFont)
        }

        for (layout in playerLayouts) {
            queuePlayerBackgrounds(context, layout, showArmor, showHands)
        }
        for (layout in genericLayouts) {
            queueGenericBackgrounds(context, layout, showArmor, showHands)
        }

        for (layout in playerLayouts) {
            drawPlayerContent(context, layout, showArmor, showHands, useCustomFont, font)
        }
        for (layout in genericLayouts) {
            drawGenericContent(context, layout, useCustomFont, font, bigFont, showArmor, showHands)
        }
    }

    private fun collectPlayerLayouts(
        showPlayers: Boolean,
        showFriends: Boolean,
        useCustomFont: Boolean,
        font: FontRenderer?,
        aresMine: Boolean
    ) {
        for (player in players) {
            if (isGhost(player)) {
                continue
            }
            val friend = FriendManager.isFriend(player)
            if (!showPlayers && (!showFriends || !friend)) {
                continue
            }
            if (!AlphaProjection.isWithinRenderDistance(player) || !AlphaProjection.canSee(player.boundingBox)) {
                continue
            }
            val box = AlphaProjection.projectEntityBox(player, mc.deltaTracker.getGameTimeDeltaPartialTick(true))
                ?: continue

            val text = playerTextCache[player.uuid] ?: buildPlayerText(player, friend)
            val layout = PlayerTagLayout(
                player = player,
                text = text,
                centerX = box.minX + (box.maxX - box.minX) / 2.0,
                topY = box.minY - 2.0,
                vecY = box.minY,
                skin = playerSkinCache[player.uuid],
                aresMine = aresMine
            )
            computePlayerTagMetrics(layout, useCustomFont, font, aresMine)
            playerLayouts += layout
        }
    }

    @Suppress("LongParameterList")
    private fun collectWorldLayouts(
        level: net.minecraft.client.multiplayer.ClientLevel,
        showMobs: Boolean,
        showAnimals: Boolean,
        showItems: Boolean,
        showTnt: Boolean,
        useCustomFont: Boolean,
        font: FontRenderer?,
        bigFont: FontRenderer?
    ) {
        val tickDelta = mc.deltaTracker.getGameTimeDeltaPartialTick(true)
        if (showMobs) {
            for (mob in level.getEntitiesOfClass(Mob::class.java, searchBox) { it !is Animal }) {
                collectLivingLayout(mob, useCustomFont, font, tickDelta)
            }
        }
        if (showAnimals) {
            for (animal in level.getEntitiesOfClass(Animal::class.java, searchBox) { true }) {
                collectLivingLayout(animal, useCustomFont, font, tickDelta)
            }
        }
        if (showItems) {
            val items = level.getEntitiesOfClass(ItemEntity::class.java, searchBox) { true }
            items.sortBy { it.item.hoverName.contents.let { c -> c is PlainTextContents && c.text() == "empty" } }
            for (item in items) {
                if (!AlphaProjection.isWithinRenderDistance(item) || !AlphaProjection.canSee(item.boundingBox)) {
                    continue
                }
                val box = AlphaProjection.projectEntityBox(item, tickDelta) ?: continue
                val stack = item.item
                var text: Component = stack.hoverName
                if (stack.count > 1) {
                    text = text.copy().append(
                        "${ChatFormatting.RESET} [${ChatFormatting.RED}${stack.count}${ChatFormatting.GRAY}x${ChatFormatting.RESET}]"
                    )
                }
                val useBigFont = stack.hoverName.contents.let { it is PlainTextContents && it.text() == "empty" }
                val layout = GenericTagLayout(
                    type = GenericType.ITEM,
                    text = text,
                    centerX = box.minX + (box.maxX - box.minX) / 2.0,
                    topY = box.minY,
                    vecY = box.minY,
                    useBigFont = useBigFont
                )
                computeSimpleTagMetrics(layout, useCustomFont, if (useBigFont) bigFont else font)
                genericLayouts += layout
            }
        }
        if (showTnt) {
            for (tnt in level.getEntitiesOfClass(PrimedTnt::class.java, searchBox) { true }) {
                if (!AlphaProjection.isWithinRenderDistance(tnt) || !AlphaProjection.canSee(tnt.boundingBox)) {
                    continue
                }
                val box = AlphaProjection.projectEntityBox(tnt, tickDelta) ?: continue
                val layout = GenericTagLayout(
                    type = GenericType.TNT,
                    text = tnt.getFeedbackDisplayName(),
                    centerX = box.minX + (box.maxX - box.minX) / 2.0,
                    topY = box.minY,
                    vecY = box.minY
                )
                computeSimpleTagMetrics(layout, useCustomFont, font)
                genericLayouts += layout
            }
        }
    }

    private fun collectLivingLayout(
        living: LivingEntity,
        useCustomFont: Boolean,
        font: FontRenderer?,
        tickDelta: Float
    ) {
        if (!AlphaProjection.isWithinRenderDistance(living) || !AlphaProjection.canSee(living.boundingBox)) {
            return
        }
        val box = AlphaProjection.projectEntityBox(living, tickDelta) ?: return
        val layout = GenericTagLayout(
            type = GenericType.LIVING,
            living = living,
            text = buildLivingText(living),
            centerX = box.minX + (box.maxX - box.minX) / 2.0,
            topY = box.minY - 2.0,
            vecY = box.minY
        )
        computeSimpleTagMetrics(layout, useCustomFont, font)
        genericLayouts += layout
    }

    private fun computePlayerTagMetrics(
        layout: PlayerTagLayout,
        useCustomFont: Boolean,
        font: FontRenderer?,
        aresMine: Boolean
    ) {
        if (aresMine) {
            val headSize = 10.0f
            val badgeSize = 8.0f
            val textHeight = 9.0f
            layout.headSize = headSize
            layout.badgeSize = badgeSize
            layout.textHeight = textHeight
            layout.totalWidth = mc.font.width(layout.text).toFloat() + headSize + 4.0f + layout.badgeWidth
            layout.boxHeight = tagBoxHeight(textHeight)
            layout.contentHeight = max(headSize, textHeight)
            layout.posX = (layout.centerX - layout.totalWidth / 2.0f).toFloat()
            layout.boxTop = (layout.topY - layout.boxHeight).toFloat()
            layout.vanilla = true
            return
        }

        val headSize = 8.0f
        val badgeSize = 6.5f
        layout.headSize = headSize
        layout.badgeSize = badgeSize
        if (useCustomFont && font != null) {
            layout.textHeight = font.getStringHeight(layout.text)
            layout.totalWidth = font.getStringWidth(layout.text) + headSize + 4.0f + layout.badgeWidth
            layout.contentHeight = max(headSize, layout.textHeight)
            layout.boxHeight = tagBoxHeight(layout.contentHeight)
            layout.posX = (layout.centerX - layout.totalWidth / 2.0f).toFloat()
            layout.boxTop = (layout.topY - layout.boxHeight).toFloat()
            layout.vanilla = false
        } else {
            layout.textHeight = 9.0f
            layout.totalWidth = mc.font.width(layout.text).toFloat() + headSize + 4.0f + layout.badgeWidth
            layout.contentHeight = max(headSize, layout.textHeight)
            layout.boxHeight = tagBoxHeight(layout.contentHeight)
            layout.posX = (layout.centerX - layout.totalWidth / 2.0f).toFloat()
            layout.boxTop = (layout.topY - layout.boxHeight).toFloat()
            layout.vanilla = true
        }
    }

    private fun computeSimpleTagMetrics(layout: GenericTagLayout, useCustomFont: Boolean, font: FontRenderer?) {
        if (useCustomFont && font != null) {
            layout.textHeight = font.getStringHeight(layout.text)
            layout.totalWidth = font.getStringWidth(layout.text)
            layout.boxHeight = tagBoxHeight(layout.textHeight)
            layout.posX = (layout.centerX - layout.totalWidth / 2.0f).toFloat()
            layout.boxTop = (layout.topY - layout.boxHeight).toFloat()
            layout.vanilla = false
        } else {
            layout.textHeight = 9.0f
            layout.totalWidth = mc.font.width(layout.text).toFloat()
            layout.boxHeight = tagBoxHeight(layout.textHeight)
            layout.posX = (layout.centerX - layout.totalWidth / 2.0f).toFloat()
            layout.boxTop = (layout.topY - layout.boxHeight).toFloat()
            layout.vanilla = true
        }
    }

    private fun queuePlayerBackgrounds(
        context: DrawContext,
        layout: PlayerTagLayout,
        showArmor: Boolean,
        showHands: Boolean
    ) {
        queueEquipmentRowBackground(context, layout.centerX, layout.vecY, layout.player, showArmor, showHands)
        renderTagBackground(context, layout.posX - 2.0f, layout.boxTop, layout.totalWidth + 4.0f, layout.boxHeight)
    }

    private fun queueGenericBackgrounds(
        context: DrawContext,
        layout: GenericTagLayout,
        showArmor: Boolean,
        showHands: Boolean
    ) {
        val living = layout.living
        if (layout.type == GenericType.LIVING && living != null) {
            queueEquipmentRowBackground(context, layout.centerX, layout.vecY, living, showArmor, showHands)
        }
        renderTagBackground(context, layout.posX - 2.0f, layout.boxTop, layout.totalWidth + 4.0f, layout.boxHeight)
    }

    private fun queueEquipmentRowBackground(
        context: DrawContext,
        centerX: Double,
        vecY: Double,
        entity: LivingEntity,
        showArmor: Boolean,
        showHands: Boolean
    ) {
        val stacks = equipmentRowStacks(entity, showArmor, showHands)
        if (stacks.isEmpty()) {
            return
        }
        val totalWidth = stacks.size * EQUIPMENT_SLOT_STEP - 1.0f
        val posX = (centerX - totalWidth / 2.0f).toFloat()
        val posY = (vecY - EQUIPMENT_ROW_OFFSET).toFloat()
        for (i in stacks.indices) {
            renderTagBackground(context, posX + i * EQUIPMENT_SLOT_STEP, posY, EQUIPMENT_SLOT_SIZE, EQUIPMENT_SLOT_SIZE)
        }
    }

    private fun drawPlayerContent(
        context: DrawContext,
        layout: PlayerTagLayout,
        showArmor: Boolean,
        showHands: Boolean,
        useCustomFont: Boolean,
        font: FontRenderer?
    ) {
        drawEquipmentRowItems(context, layout.centerX, layout.vecY, layout.player, showArmor, showHands)
        if (layout.aresMine) {
            val skin = layout.skin
            if (skin != null) {
                val headY = tagContentY(layout.boxTop, layout.contentHeight, layout.headSize)
                drawHead(context, skin, layout.posX + 1.0f, headY, layout.headSize)
            }
            val textY = tagContentY(layout.boxTop, layout.contentHeight, layout.textHeight)
            context.drawText(
                mc.font,
                layout.text,
                (layout.posX + layout.headSize + 4.0f + layout.badgeWidth).toInt(),
                textY.toInt(),
                0xFFFFFF,
                false
            )
            return
        }

        val skin = layout.skin
        if (skin != null) {
            val headY = tagContentY(layout.boxTop, layout.contentHeight, layout.headSize)
            drawHead(context, skin, layout.posX + 1.0f, headY, layout.headSize)
        }
        // Compatible-client badge is inactive (see class docs / deviation ledger):
        // the fork has no AlphaDLC EmotionWheel relay protocol, so badgeWidth is 0
        // and no badge is drawn.
        if (useCustomFont && font != null) {
            font.drawText(
                context,
                layout.text,
                layout.posX + layout.headSize + 4.0f + layout.badgeWidth,
                customFontTextY(font, layout.boxTop, layout.boxHeight, layout.text)
            )
        } else {
            val textY = tagContentY(layout.boxTop, layout.contentHeight, layout.textHeight)
            context.drawText(
                mc.font,
                layout.text,
                (layout.posX + layout.headSize + 4.0f + layout.badgeWidth).toInt(),
                textY.toInt(),
                0xFFFFFF,
                true
            )
        }
    }

    private fun drawGenericContent(
        context: DrawContext,
        layout: GenericTagLayout,
        useCustomFont: Boolean,
        font: FontRenderer?,
        bigFont: FontRenderer?,
        showArmor: Boolean,
        showHands: Boolean
    ) {
        val living = layout.living
        if (layout.type == GenericType.LIVING && living != null) {
            drawEquipmentRowItems(context, layout.centerX, layout.vecY, living, showArmor, showHands)
        }
        val activeFont = if (layout.useBigFont && bigFont != null) bigFont else font
        if (useCustomFont && activeFont != null) {
            activeFont.drawText(
                context,
                layout.text,
                layout.posX,
                customFontTextY(activeFont, layout.boxTop, layout.boxHeight, layout.text)
            )
        } else {
            context.drawText(
                mc.font,
                layout.text,
                layout.posX.toInt(),
                tagContentY(layout.boxTop, layout.textHeight, layout.textHeight).toInt(),
                0xFFFFFF,
                true
            )
        }
    }

    private fun drawEquipmentRowItems(
        context: DrawContext,
        centerX: Double,
        vecY: Double,
        entity: LivingEntity,
        showArmor: Boolean,
        showHands: Boolean
    ) {
        val stacks = equipmentRowStacks(entity, showArmor, showHands)
        if (stacks.isEmpty()) {
            return
        }
        val totalWidth = stacks.size * EQUIPMENT_SLOT_STEP - 1.0f
        val posX = (centerX - totalWidth / 2.0f).toFloat()
        val posY = (vecY - EQUIPMENT_ROW_OFFSET).toFloat()
        val itemVisualSize = 10.4f
        val itemOffset = (EQUIPMENT_SLOT_SIZE - itemVisualSize) / 2.0f - 1.0f
        for (i in stacks.indices) {
            val itemX = posX + i * EQUIPMENT_SLOT_STEP + itemOffset
            val itemY = posY + itemOffset
            context.pose().pushMatrix()
            try {
                context.pose().translate(itemX + 1.0f, itemY + 1.0f)
                context.pose().scale(EQUIPMENT_ITEM_SCALE, EQUIPMENT_ITEM_SCALE)
                context.drawItem(stacks[i], 0, 0)
                context.drawStackOverlay(mc.font, stacks[i], 0, 0)
            } finally {
                context.pose().popMatrix()
            }
        }
    }

    private fun drawHead(context: DrawContext, skin: Identifier, x: Float, y: Float, size: Float) {
        NameTagsSkin.render(context, skin, x, y, size)
    }

    private fun equipmentRowStacks(entity: LivingEntity, showArmor: Boolean, showHands: Boolean): List<ItemStack> {
        equipmentScratch.clear()
        if (showHands) {
            addEquipmentStack(entity.mainHandItem)
        }
        if (showArmor) {
            addEquipmentStack(entity.getItemBySlot(EquipmentSlot.FEET))
            addEquipmentStack(entity.getItemBySlot(EquipmentSlot.LEGS))
            addEquipmentStack(entity.getItemBySlot(EquipmentSlot.CHEST))
            addEquipmentStack(entity.getItemBySlot(EquipmentSlot.HEAD))
        }
        if (showHands) {
            addEquipmentStack(entity.offhandItem)
        }
        return equipmentScratch
    }

    private fun addEquipmentStack(stack: ItemStack) {
        if (!stack.isEmpty) {
            equipmentScratch += stack
        }
    }

    private fun resolveSkin(player: Player): Identifier {
        return resolvePlayerSkin(player)
    }

    private fun buildPlayerText(player: Player, friend: Boolean): MutableComponent {
        val health = actualHealth(player)
        val text = Component.empty()
        if (friend) {
            text.append("[${ChatFormatting.GREEN}F${ChatFormatting.RESET}] ")
        }
        if (ModuleAntiBot.isBot(player)) {
            text.append("[${ChatFormatting.BLACK}BOT${ChatFormatting.RESET}] ")
        }
        val displayName = convertDisplayName(player.displayName ?: player.name)
        text.append(if (friend) colorizeText(displayName, FRIEND_RGB) else displayName)
        if (health > 0.0f) {
            text.append("${ChatFormatting.RESET} [${ChatFormatting.RED}${healthString(health)}${ChatFormatting.RESET}]")
        }
        return text
    }

    private fun buildLivingText(entity: LivingEntity): MutableComponent {
        val health = actualHealth(entity)
        val text = Component.empty()
        text.append(entity.displayName ?: entity.name)
        if (health > 0.0f) {
            text.append("${ChatFormatting.RESET} [${ChatFormatting.RED}${healthString(health)}${ChatFormatting.RESET}]")
        }
        return text
    }

    private fun actualHealth(entity: LivingEntity): Float {
        val baseHealth = entity.getActualHealth()
        val hp = baseHealth + entity.absorptionAmount
        val maxHealth = max(entity.maxHealth, baseHealth) + entity.absorptionAmount
        return hp.coerceIn(0.0f, maxHealth)
    }

    private fun healthString(hp: Float): String {
        return String.format(java.util.Locale.US, "%.1f", hp).replace(",", ".").replace(".0", "")
    }

    private fun colorizeText(text: Component, rgb: Int): MutableComponent {
        val result = Component.empty()
        text.visit<Unit>({ style, content ->
            if (content.isNotEmpty()) {
                result.append(Component.literal(content).withStyle(style.withColor(rgb)))
            }
            java.util.Optional.empty()
        }, Style.EMPTY)
        return result
    }

    private fun convertDisplayName(original: Component): MutableComponent {
        val result = Component.empty()
        processTextComponent(original, result)
        return result
    }

    private fun processTextComponent(component: Component, result: MutableComponent) {
        val contents = component.contents
        val content = if (contents is PlainTextContents) contents.text() else ""
        if (content.isNotEmpty()) {
            var foundPrefix = false
            for ((key, info) in DONAT_PREFIXES) {
                val keyIndex = content.indexOf(key)
                if (keyIndex < 0) {
                    continue
                }
                val prefixText = "${info.color}${info.name}"
                val afterKey = keyIndex + key.length
                val needsSpace = afterKey >= content.length || content[afterKey] != ' '
                val before = content.substring(0, keyIndex)
                val after = if (afterKey < content.length) content.substring(afterKey) else ""
                val replaced = before + prefixText + (if (needsSpace) " " else "") + after
                result.append(Component.literal(replaced))
                foundPrefix = true
                break
            }
            if (!foundPrefix) {
                result.append(Component.literal(content).withStyle(component.style))
            }
        }
        for (sibling in component.siblings) {
            processTextComponent(sibling, result)
        }
    }

    private fun renderTagBackground(context: DrawContext, x: Float, y: Float, width: Float, height: Float) {
        if (blur) {
            NameTagsBlur.renderMaskedBackground(context, x, y, width, height, TAG_BG_ROUND, TAG_BG_COLOR)
            return
        }
        NameTagsBackground.render(context, x, y, width, height, TAG_BG_ROUND, TAG_BG_COLOR)
    }

    private fun isGhost(player: Player): Boolean {
        return player.customName?.string?.startsWith("Ghost_") == true
    }

    /**
     * Screen-space preview entry point for ClickGUI/HUD callers.
     *
     * [entityTopY] and [entityBottomY] are the already-projected vertical bounds
     * that the world renderer normally gets from [AlphaProjection].
     */
    @JvmStatic
    fun renderPreview(context: DrawContext, player: Player, centerX: Double, entityTopY: Double, entityBottomY: Double) {
        val friend = FriendManager.isFriend(player)
        if (EntityType.PLAYERS !in entityTypes && (EntityType.FRIENDS !in entityTypes || !friend)) {
            return
        }

        val useCustomFont = fontMode == FontChoice.NEW
        val font: FontRenderer? = if (useCustomFont) {
            Fonts.getSize(13, Fonts.FontType.SUISSE_SEMIBOLD)
        } else {
            null
        }
        val showArmor = PlayerSetting.ARMOR in playerSettings
        val showHands = PlayerSetting.HELD_ITEMS in playerSettings
        val aresMine = isAresMineCompatibilityActive()

        NameTagsBlur.beginFrame()
        val layout = PlayerTagLayout(
            player = player,
            text = buildPlayerText(player, friend),
            centerX = centerX,
            topY = entityTopY - 2.0,
            vecY = entityTopY,
            skin = resolveSkin(player),
            aresMine = aresMine
        )
        computePlayerTagMetrics(layout, useCustomFont, font, aresMine)

        queuePlayerBackgrounds(context, layout, showArmor, showHands)
        drawPlayerContent(context, layout, showArmor, showHands, useCustomFont, font)
    }

    private fun isAresMineCompatibilityActive(): Boolean {
        if (mc.hasSingleplayerServer()) {
            return false
        }

        val address = mc.connection?.serverData?.ip ?: mc.currentServer?.ip
        val brand = mc.connection?.serverBrand()

        return address.containsAresMineMarker() || brand.containsAresMineMarker()
    }

    private fun String?.containsAresMineMarker(): Boolean {
        return this?.lowercase(Locale.ROOT)?.contains("aresmine") == true
    }

    private fun tagBoxHeight(contentHeight: Float): Float = contentHeight + TAG_PADDING_TOP + TAG_PADDING_BOTTOM

    private fun tagContentY(boxTop: Float, contentHeight: Float, elementHeight: Float): Float =
        boxTop + TAG_PADDING_TOP + (contentHeight - elementHeight) / 2.0f

    private fun customFontTextY(font: FontRenderer, boxTop: Float, boxHeight: Float, text: Component): Float =
        font.getCenteredY(boxTop, boxHeight, text.string)

    private fun argb(a: Int, r: Int, g: Int, b: Int): Int =
        (a shl 24) or (r shl 16) or (g shl 8) or b

    private class PlayerTagLayout(
        val player: Player,
        val text: MutableComponent,
        val centerX: Double,
        val topY: Double,
        val vecY: Double,
        val skin: Identifier?,
        val aresMine: Boolean
    ) {
        var posX = 0.0f
        var boxTop = 0.0f
        var boxHeight = 0.0f
        var totalWidth = 0.0f
        var contentHeight = 0.0f
        var textHeight = 0.0f
        var headSize = 0.0f
        var badgeSize = 0.0f
        val badgeWidth = 0.0f
        var vanilla = false
    }

    private class GenericTagLayout(
        val type: GenericType,
        val living: LivingEntity? = null,
        val text: Component,
        val centerX: Double,
        val topY: Double,
        val vecY: Double,
        val useBigFont: Boolean = false
    ) {
        var posX = 0.0f
        var boxTop = 0.0f
        var boxHeight = 0.0f
        var totalWidth = 0.0f
        var textHeight = 0.0f
        var vanilla = false
    }

    private enum class GenericType { LIVING, ITEM, TNT }

    private data class PrefixInfo(val name: String, val color: ChatFormatting)

    private enum class EntityType(override val choiceName: String) : NamedChoice {
        PLAYERS("Players"),
        MOBS("Mobs"),
        ANIMALS("Animals"),
        ITEMS("Items"),
        TNT("TNT"),
        FRIENDS("Friends")
    }

    private enum class PlayerSetting(override val choiceName: String) : NamedChoice {
        ARMOR("Armor"),
        HELD_ITEMS("HeldItems")
    }

    private enum class FontChoice(override val choiceName: String) : NamedChoice {
        NEW("New"),
        STANDARD("Standard")
    }

    private val DONAT_PREFIXES: Map<String, PrefixInfo> = linkedMapOf(
        "ꔀ" to PrefixInfo("PLAYER", ChatFormatting.GRAY),
        "ꔄ" to PrefixInfo("HERO", ChatFormatting.DARK_PURPLE),
        "ꔈ" to PrefixInfo("TITAN", ChatFormatting.YELLOW),
        "ꔒ" to PrefixInfo("AVENGER", ChatFormatting.GREEN),
        "ꔖ" to PrefixInfo("OVERLORD", ChatFormatting.DARK_RED),
        "ꔠ" to PrefixInfo("MAGISTER", ChatFormatting.AQUA),
        "ꔤ" to PrefixInfo("IMPERATOR", ChatFormatting.RED),
        "ꔨ" to PrefixInfo("DRAGON", ChatFormatting.LIGHT_PURPLE),
        "ꔲ" to PrefixInfo("BULL", ChatFormatting.GOLD),
        "ꕒ" to PrefixInfo("RABBIT", ChatFormatting.WHITE),
        "ꔶ" to PrefixInfo("TIGER", ChatFormatting.AQUA),
        "ꕄ" to PrefixInfo("DRACULA", ChatFormatting.RED),
        "ꕖ" to PrefixInfo("BUNNY", ChatFormatting.WHITE),
        "ꕀ" to PrefixInfo("HYDRA", ChatFormatting.DARK_AQUA),
        "ꕈ" to PrefixInfo("COBRA", ChatFormatting.GREEN),
        "ꕅ" to PrefixInfo("VAMPIRE", ChatFormatting.RED),
        "ꔁ" to PrefixInfo("MEDIA", ChatFormatting.DARK_PURPLE),
        "ꔅ" to PrefixInfo("YT", ChatFormatting.RED),
        "ꕠ" to PrefixInfo("D.HELPER", ChatFormatting.GREEN),
        "ꔉ" to PrefixInfo("HELPER", ChatFormatting.YELLOW),
        "ꔓ" to PrefixInfo("ML.MODER", ChatFormatting.YELLOW),
        "ꔗ" to PrefixInfo("MODER", ChatFormatting.YELLOW),
        "ꔡ" to PrefixInfo("MODER+", ChatFormatting.YELLOW),
        "ꔥ" to PrefixInfo("ST.MODER", ChatFormatting.YELLOW),
        "ꔩ" to PrefixInfo("GL.MODER", ChatFormatting.AQUA),
        "ꔳ" to PrefixInfo("ML.ADMIN", ChatFormatting.RED),
        "ꔷ" to PrefixInfo("ADMIN", ChatFormatting.BLACK)
    )
}
