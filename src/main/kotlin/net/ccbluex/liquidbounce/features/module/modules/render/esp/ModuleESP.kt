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
package net.ccbluex.liquidbounce.features.module.modules.render.esp

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaDlcWorldRenderer
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClickGuiScreen
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.font.msdf.Fonts
import net.ccbluex.liquidbounce.render.gui.drawFlatQuad
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.statusEffects
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.decoration.ArmorStand
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import java.util.Locale
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * AlphaDLC Esp replacement. This intentionally has one source-style implementation,
 * not the old current-project ESP choice/mode architecture.
 */
object ModuleESP : ClientModule("ESP", Category.RENDER) {

    private const val FRIEND_RGB = 0x0055FF55

    private val entityTypes by multiEnumChoice("EntityTypes", EntityType.PLAYERS)
    private val boxMode by enumChoice("BoxMode", BoxMode.GLOW)
    private val glowThickness by float("GlowThickness", 3.0f, 1.0f..6.0f).apply {
        doNotIncludeWhen { boxMode != BoxMode.GLOW }
    }
    private val potions by boolean("Potions", false)
    private val potionScale by float("PotionScale", 1.0f, 0.5f..2.0f).apply {
        doNotIncludeWhen { !potions }
    }
    private val boxColor by color("BoxColor", Color4b(255, 255, 255, 55))

    private val players = ArrayList<Player>()
    private val glowBoxes = ArrayList<AABB>()
    private val glowColors = ArrayList<Int>()

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        players.clear()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val localPlayer = mc.player
        val level = mc.level
        players.clear()
        if (localPlayer == null || level == null) {
            return@handler
        }

        for (player in level.players()) {
            if (player !== localPlayer && !isGhost(player)) {
                players += player
            }
        }
    }

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (mc.screen is ClickGuiScreen) {
            return@handler
        }
        if (boxMode != BoxMode.GLOW || entityTypes.isEmpty()) {
            return@handler
        }

        val renderDistance = sqrt(AlphaProjection.maxRenderDistanceSq())
        val searchBox = localPlayer.boundingBox.inflate(renderDistance)
        glowBoxes.clear()
        glowColors.clear()

        if (EntityType.PLAYERS in entityTypes || EntityType.FRIENDS in entityTypes) {
            for (player in players) {
                val friend = FriendManager.isFriend(player)
                if (EntityType.PLAYERS !in entityTypes && (!friend || EntityType.FRIENDS !in entityTypes)) {
                    continue
                }
                addLivingGlow(player, event.partialTicks, rgbFor(friend))
            }
        }

        if (EntityType.MOBS in entityTypes) {
            for (entity in level.getEntitiesOfClass(Mob::class.java, searchBox) { it !is Animal }) {
                addLivingGlow(entity, event.partialTicks, rgbFor(false))
            }
        }

        if (EntityType.ANIMALS in entityTypes) {
            for (entity in level.getEntitiesOfClass(Animal::class.java, searchBox) { true }) {
                addLivingGlow(entity, event.partialTicks, rgbFor(false))
            }
        }

        if (EntityType.ITEMS in entityTypes) {
            for (entity in level.getEntitiesOfClass(ItemEntity::class.java, searchBox) { true }) {
                if (AlphaProjection.isWithinRenderDistance(entity) && AlphaProjection.canSee(entity.boundingBox)) {
                    glowBoxes += entity.boundingBox
                    glowColors += rgbFor(false)
                }
            }
        }

        if (EntityType.ARMOR_STAND in entityTypes) {
            for (entity in level.getEntitiesOfClass(ArmorStand::class.java, searchBox) { true }) {
                addLivingGlow(entity, event.partialTicks, rgbFor(false))
            }
        }

        if (glowBoxes.isNotEmpty()) {
            AlphaDlcWorldRenderer.drawWorldGlow(
                event.matrixStack.last().pose(),
                event.camera.position(),
                glowBoxes,
                glowColors,
                glowThickness
            )
        }
    }

    @Suppress("unused")
    private val overlayRenderHandler = handler<OverlayRenderEvent> { event ->
        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (mc.screen is ClickGuiScreen) {
            return@handler
        }
        if (entityTypes.isEmpty() && !potions) {
            return@handler
        }

        val renderDistance = sqrt(AlphaProjection.maxRenderDistanceSq())
        val searchBox = localPlayer.boundingBox.inflate(renderDistance)
        val context = event.context
        val tickDelta = event.tickDelta
        val draw2dBoxes = boxMode != BoxMode.GLOW

        if (EntityType.PLAYERS in entityTypes || EntityType.FRIENDS in entityTypes || potions) {
            for (player in players) {
                val friend = FriendManager.isFriend(player)
                val drawBox = draw2dBoxes && (EntityType.PLAYERS in entityTypes ||
                    (EntityType.PLAYERS !in entityTypes && EntityType.FRIENDS in entityTypes && friend))

                if (!drawBox && !potions || !passesPlayerOverlayChecks(player, tickDelta)) {
                    continue
                }

                val box = AlphaProjection.projectEntityBox(player, tickDelta) ?: continue
                if (potions) {
                    drawPotionPanels(context, player, box)
                }
                if (drawBox) {
                    drawBox2d(context, box, colorFor(friend))
                }
            }
        }

        if (!draw2dBoxes) {
            return@handler
        }

        if (EntityType.MOBS in entityTypes) {
            for (entity in level.getEntitiesOfClass(Mob::class.java, searchBox) { it !is Animal }) {
                drawProjectedEntity(context, entity, tickDelta, colorFor(false))
            }
        }

        if (EntityType.ANIMALS in entityTypes) {
            for (entity in level.getEntitiesOfClass(Animal::class.java, searchBox) { true }) {
                drawProjectedEntity(context, entity, tickDelta, colorFor(false))
            }
        }

        if (EntityType.ITEMS in entityTypes) {
            for (entity in level.getEntitiesOfClass(ItemEntity::class.java, searchBox) { true }) {
                drawProjectedEntity(context, entity, tickDelta, colorFor(false))
            }
        }

        if (EntityType.ARMOR_STAND in entityTypes) {
            for (entity in level.getEntitiesOfClass(ArmorStand::class.java, searchBox) { true }) {
                drawProjectedEntity(context, entity, tickDelta, colorFor(false))
            }
        }
    }

    override fun disable() {
        players.clear()
        glowBoxes.clear()
        glowColors.clear()
    }

    private fun addLivingGlow(entity: LivingEntity, tickDelta: Float, color: Int) {
        val box = interpolatedLivingBox(entity, tickDelta) ?: return
        glowBoxes += box
        glowColors += color
    }

    private fun interpolatedLivingBox(entity: LivingEntity, tickDelta: Float): AABB? {
        if (!AlphaProjection.isWithinRenderDistance(entity)) {
            return null
        }

        val x = entity.xo + (entity.x - entity.xo) * tickDelta
        val y = entity.yo + (entity.y - entity.yo) * tickDelta
        val z = entity.zo + (entity.z - entity.zo) * tickDelta
        val box = entity.getDimensions(entity.pose).makeBoundingBox(x, y, z)
        return if (AlphaProjection.canSee(box)) box else null
    }

    private fun drawProjectedEntity(context: DrawContext, entity: Entity, tickDelta: Float, color: Int) {
        if (!AlphaProjection.isWithinRenderDistance(entity) || !AlphaProjection.canSee(entity.boundingBox)) {
            return
        }
        val box = AlphaProjection.projectEntityBox(entity, tickDelta) ?: return
        drawBox2d(context, box, color)
    }

    private fun passesPlayerOverlayChecks(entity: Entity, tickDelta: Float): Boolean {
        if (!AlphaProjection.isWithinRenderDistance(entity)) {
            return false
        }

        val x = entity.xo + (entity.x - entity.xo) * tickDelta
        val y = entity.yo + (entity.y - entity.yo) * tickDelta + entity.bbHeight * 0.5
        val z = entity.zo + (entity.z - entity.zo) * tickDelta
        return AlphaProjection.isInFrontOfCamera(Vec3(x, y, z)) && AlphaProjection.canSee(entity.boundingBox)
    }

    private fun drawBox2d(context: DrawContext, box: AlphaProjection.ScreenBox, color: Int) {
        val x = box.minX.toFloat()
        val y = box.minY.toFloat()
        val endX = box.maxX.toFloat()
        val endY = box.maxY.toFloat()
        val width = endX - x
        val height = endY - y

        if (boxMode == BoxMode.CORNER) {
            val size = width / 3.0f
            flatRect(context, x - 0.5f, y - 0.5f, size, 1.0f, color)
            flatRect(context, x - 0.5f, y, 1.0f, size + 0.5f, color)
            flatRect(context, x - 0.5f, endY - size - 0.5f, 1.0f, size, color)
            flatRect(context, x - 0.5f, endY - 0.5f, size, 1.0f, color)
            flatRect(context, endX - size + 1.0f, y - 0.5f, size, 1.0f, color)
            flatRect(context, endX - 0.5f, y, 1.0f, size + 0.5f, color)
            flatRect(context, endX - 0.5f, endY - size - 0.5f, 1.0f, size, color)
            flatRect(context, endX - size + 1.0f, endY - 0.5f, size, 1.0f, color)
        } else if (boxMode == BoxMode.NORMAL) {
            flatRect(context, x - 0.5f, y - 0.5f, width + 1.0f, 1.0f, color)
            flatRect(context, x - 0.5f, y - 0.5f, 1.0f, height + 1.0f, color)
            flatRect(context, x - 0.5f, endY - 0.5f, width + 1.0f, 1.0f, color)
            flatRect(context, endX - 0.5f, y - 0.5f, 1.0f, height + 1.0f, color)
        }
    }

    private fun drawPotionPanels(context: DrawContext, entity: LivingEntity, box: AlphaProjection.ScreenBox) {
        val effects = entity.statusEffects
        if (effects.isEmpty()) {
            return
        }

        val boxHeight = max(1.0, box.maxY - box.minY)
        val userScale = potionScale.coerceIn(0.5f, 2.0f)
        val distanceScale = (boxHeight / 68.0).coerceIn(0.32, 1.0).toFloat()
        val panelScale = userScale * distanceScale
        // AlphaDLC Fonts.getSize halves the requested size internally; using it keeps
        // source-sized text. FontManager.default() would render at ~2x the source size.
        val fontSize = floor(10.0f * panelScale).toInt().coerceIn(8, 20)
        val font = Fonts.getSize(fontSize, Fonts.FontType.DEFAULT)
        val iconSize = max(6.0f, 8.0f * panelScale)
        val panelGap = 1.5f * panelScale
        val paddingX = 3.0f * panelScale
        val iconTextGap = 2.5f * panelScale
        val verticalPad = 2.0f * panelScale
        val textHeight = font.getStringHeight("Ag")
        val panelHeight = max(8.0f, max(11.0f * panelScale, textHeight + verticalPad * 2.0f))
        val rowContentHeight = max(iconSize, textHeight)
        val panelX = box.maxX.toFloat() + 3.0f * distanceScale
        val panelY = box.minY.toFloat()

        val rows = ArrayList<PotionRow>(6)
        for (effect in effects) {
            if (rows.size >= 6) {
                break
            }
            if (!isRenderableDuration(effect.duration)) {
                continue
            }
            val name = effect.effect.value().displayName.string
            val level = effect.amplifier + 1
            val timer = formatPotionDuration(effect.duration)
            val label = "$name $level  $timer"
            val textWidth = font.getStringWidth(label)
            val contentWidth = iconSize + iconTextGap + textWidth
            rows += PotionRow(effect, label, contentWidth, paddingX * 2.0f + contentWidth)
        }

        var drawn = 0
        for (row in rows) {
            val rowY = panelY + drawn * (panelHeight + panelGap)
            flatRect(context, panelX, rowY, row.panelWidth, panelHeight, potionPanelBackground(row.effect))
            drawn++
        }

        drawn = 0
        for (row in rows) {
            val rowY = panelY + drawn * (panelHeight + panelGap)
            val contentX = panelX + paddingX + (row.panelWidth - paddingX * 2.0f - row.contentWidth) * 0.5f
            val contentY = rowY + (panelHeight - rowContentHeight) * 0.5f
            val iconY = contentY + (rowContentHeight - iconSize) * 0.5f
            drawEffectIcon(context, row.effect, contentX, iconY, iconSize)
            val textX = contentX + iconSize + iconTextGap
            val textY = font.getCenteredY(contentY, rowContentHeight, row.label)
            font.drawFlatString(context, row.label, textX, textY, -1)
            drawn++
        }
    }

    private fun drawEffectIcon(context: DrawContext, effect: MobEffectInstance, x: Float, y: Float, size: Float) {
        val sprite = Gui.getMobEffectSprite(effect.effect)
        // Float position/scale via pose (AlphaDLC draws the sprite quad at float
        // coordinates); blitSprite itself only takes int extents so a unit sprite
        // is scaled by the pose to preserve the source's fractional icon size.
        context.pose().pushMatrix()
        try {
            context.pose().translate(x, y)
            context.pose().scale(size, size)
            context.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, 0, 0, 1, 1, -1)
        } finally {
            context.pose().popMatrix()
        }
    }

    private fun isRenderableDuration(ticks: Int): Boolean {
        return ticks == MobEffectInstance.INFINITE_DURATION || ticks > 0
    }

    private fun formatPotionDuration(ticks: Int): String {
        if (ticks == MobEffectInstance.INFINITE_DURATION || ticks > 72000) {
            return "**:**"
        }
        val totalSeconds = ticks / 20
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "$minutes:" + String.format(Locale.US, "%02d", seconds)
    }

    private fun potionPanelBackground(effect: MobEffectInstance): Int {
        val effectRgb = effect.effect.value().color and 0xFFFFFF
        val darkened = multBright(effectRgb or 0xFF000000.toInt(), 2.55f)
        return multAlpha(darkened, 0.38f)
    }

    private fun multBright(color: Int, percent: Float): Int {
        return packColor(
            min(255, Math.round(colorRed(color) / percent)),
            min(255, Math.round(colorGreen(color) / percent)),
            min(255, Math.round(colorBlue(color) / percent)),
            colorAlpha(color)
        )
    }

    private fun multAlpha(color: Int, percent: Float): Int {
        return packColor(
            colorRed(color),
            colorGreen(color),
            colorBlue(color),
            Math.round(colorAlpha(color) * percent)
        )
    }

    private fun colorRed(color: Int): Int = (color shr 16) and 0xFF
    private fun colorGreen(color: Int): Int = (color shr 8) and 0xFF
    private fun colorBlue(color: Int): Int = color and 0xFF
    private fun colorAlpha(color: Int): Int = (color shr 24) and 0xFF

    private fun packColor(red: Int, green: Int, blue: Int, alpha: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or
            (red.coerceIn(0, 255) shl 16) or
            (green.coerceIn(0, 255) shl 8) or
            blue.coerceIn(0, 255)
    }

    private fun flatRect(context: DrawContext, x: Float, y: Float, width: Float, height: Float, color: Int) {
        context.drawFlatQuad(x, y, width, height, color)
    }

    private fun colorFor(friend: Boolean): Int {
        val rgb = if (friend) FRIEND_RGB else boxColor.toARGB() and 0x00FFFFFF
        return rgb or (boxColor.a.coerceIn(0, 255) shl 24)
    }

    private fun rgbFor(friend: Boolean): Int {
        return if (friend) FRIEND_RGB else boxColor.toARGB() and 0x00FFFFFF
    }

    private fun isGhost(entity: Entity): Boolean {
        return entity.customName?.string?.startsWith("Ghost_") == true
    }

    private data class PotionRow(
        val effect: MobEffectInstance,
        val label: String,
        val contentWidth: Float,
        val panelWidth: Float
    )

    private enum class EntityType(override val choiceName: String) : NamedChoice {
        PLAYERS("Players"),
        MOBS("Mobs"),
        ANIMALS("Animals"),
        ITEMS("Items"),
        ARMOR_STAND("ArmorStand"),
        FRIENDS("Friends")
    }

    private enum class BoxMode(override val choiceName: String) : NamedChoice {
        NORMAL("Normal"),
        CORNER("Corner"),
        GLOW("Glow")
    }
}
