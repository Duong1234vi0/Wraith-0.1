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
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.entity.armorItems
import net.ccbluex.liquidbounce.utils.entity.camera
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.render.WorldToScreen
import net.minecraft.client.gui.DrawContext
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import java.util.Locale
import kotlin.math.max

/**
 * Wyvern-DLC-style compact player nametags.
 */
object ModuleNameTags : ClientModule("NameTags", Category.RENDER, aliases = arrayOf("Nametags")) {

    private val entityTypes by multiEnumChoice("EntityTypes", EntityType.PLAYER, EntityType.ITEM)
    private val armor by boolean("Armor", true)
    private val mainHand by boolean("MainHand", true)
    private val offHand by boolean("OffHand", true)

    @Suppress("unused")
    private val overlayHandler = handler<OverlayRenderEvent> { event ->
        val level = mc.level ?: return@handler
        if (mc.player == null) {
            return@handler
        }

        val tags = level.players()
            .asSequence()
            .filter { it != player }
            .filter { shouldRenderNametag(it) }
            .mapNotNull { createTag(it, event.tickDelta) }
            .sortedByDescending { it.distanceSqr }
            .toList()

        for ((index, tag) in tags.withIndex()) {
            drawTag(event.context, tag, index)
        }
    }

    @JvmStatic
    fun shouldRenderNametag(entity: Entity): Boolean {
        return entity is Player && EntityType.PLAYER in entityTypes
    }

    @JvmStatic
    fun shouldCancelVanillaNametag(entity: Entity): Boolean {
        return entity is Player
    }

    private fun createTag(entity: Player, tickDelta: Float): Tag? {
        val interpolated = entity.interpolateCurrentPosition(tickDelta)
        val head = WorldToScreen.calculateScreenPos(
            interpolated.add(0.0, entity.bbHeight.toDouble() + 0.35, 0.0)
        ) ?: return null
        val legs = WorldToScreen.calculateScreenPos(
            interpolated.add(0.0, -0.35, 0.0)
        ) ?: return null
        if (!isVisibleDepth(head.z.toFloat()) || !isVisibleDepth(legs.z.toFloat())) {
            return null
        }

        val cameraPos = mc.gameRenderer.camera.position()
        val distanceSqr = cameraPos.distanceToSqr(interpolated)
        val distance = kotlin.math.sqrt(distanceSqr)
        val scale = (5.0 / max(distance, 5.0)).toFloat().coerceIn(0.55f, 1.35f)
        return Tag(entity, head.x.toFloat(), head.y.toFloat(), head.z.toFloat(), distanceSqr, scale)
    }

    private fun drawTag(context: DrawContext, tag: Tag, zIndex: Int) {
        if (!isVisibleDepth(tag.depth)) {
            return
        }

        // #Edit By Codex 2026-06-28: context.matrices is only a compat key.
        // The real 1.26 GUI extractor reads context.pose(); using PoseStack
        // translation here made the whole tag render at the top-left corner.
        context.pose().pushMatrix()
        try {
            context.pose().translate(tag.x, tag.y)
            context.pose().scale(tag.scale, tag.scale)
            drawPlayerTag(context, tag.entity, zIndex)
        } finally {
            context.pose().popMatrix()
        }
    }

    private fun drawPlayerTag(context: DrawContext, entity: Player, zIndex: Int) {
        val matrices = context.matrices
        val isFriend = FriendManager.isFriend(entity)
        val name = entity.name.string
        val friendPrefix = if (isFriend) "[F] " else ""
        val healthText = "[${formatHealth(entity.health)}]"
        val fontSize = 8.5f
        val prefixWidth = ExosGuiRenderer.bodyWidth(friendPrefix, fontSize)
        val nameWidth = ExosGuiRenderer.bodyWidth(name, fontSize)
        val spaceWidth = ExosGuiRenderer.bodyWidth(" ", fontSize)
        val healthWidth = ExosGuiRenderer.bodyWidth(healthText, fontSize)
        val width = prefixWidth + nameWidth + spaceWidth + healthWidth + 6.0f
        val height = 13.0f
        val x = -width * 0.5f
        val y = -height * 0.5f

        ExosGuiRenderer.roundedRect(matrices, x, y, width, height, 2.0f, tagBackgroundColor())

        var textX = x + 3.0f
        // #Edit By Codex 2026-06-28: local Pixel font metrics sit lower than Wyvern's MSDF font.
        // Lift the line inside the 13px plate so the name/health reads optically centered.
        val textY = y + 1.8f
        if (isFriend) {
            ExosGuiRenderer.body(matrices, friendPrefix, textX, textY, 0xFF55FF55.toInt(), fontSize)
            textX += prefixWidth
        }
        ExosGuiRenderer.body(matrices, name, textX, textY, 0xFFFFFFFF.toInt(), fontSize)
        textX += nameWidth + spaceWidth
        ExosGuiRenderer.body(matrices, healthText, textX, textY, healthColor(entity), fontSize)

        if (armor) {
            drawArmor(context, entity, 0.0f, -height * 0.5f - 13.0f, zIndex)
        }
    }

    private fun drawArmor(context: DrawContext, entity: Player, x: Float, y: Float, zIndex: Int) {
        val stacks = equipmentStacks(entity)
        if (stacks.isEmpty()) {
            return
        }

        val itemScale = 0.75f
        val renderedItemSize = 16.0f * itemScale
        val boxSizeItem = 13.0f
        val paddingItem = 1.5f
        val totalWidth = stacks.size * (boxSizeItem + paddingItem) - paddingItem
        var iconX = x - totalWidth * 0.5f
        val iconY = y

        for (stack in stacks) {
            context.pose().pushMatrix()
            try {
                context.pose().translate(
                    iconX + (boxSizeItem - renderedItemSize) * 0.5f,
                    iconY + (boxSizeItem - renderedItemSize) * 0.5f
                )
                context.pose().scale(itemScale, itemScale)
                context.drawItem(stack, 0, 0, zIndex)
            } finally {
                context.pose().popMatrix()
            }
            iconX += boxSizeItem + paddingItem
        }
    }

    private fun equipmentStacks(entity: Player): List<ItemStack> {
        val stacks = ArrayList<ItemStack>(6)
        if (offHand && !entity.offhandItem.isEmpty) {
            stacks += entity.offhandItem
        }
        if (armor) {
            entity.armorItems.forEach { stack ->
                if (!stack.isEmpty) {
                    stacks += stack
                }
            }
        }
        if (mainHand && !entity.mainHandItem.isEmpty) {
            stacks += entity.mainHandItem
        }
        return stacks
    }

    private fun healthColor(entity: Player): Int {
        val ratio = (entity.health / entity.maxHealth).coerceIn(0.0f, 1.0f)
        return when {
            ratio > 0.75f -> 0xFF55FF55.toInt()
            ratio > 0.50f -> 0xFFFFFF55.toInt()
            ratio > 0.25f -> 0xFFFFAA00.toInt()
            else -> 0xFFFF5555.toInt()
        }
    }

    private fun formatHealth(value: Float): String {
        return String.format(Locale.ROOT, "%.1f", value)
    }

    private fun tagBackgroundColor(): Int {
        val color = ClientTheme.accentA
        val r = (((color shr 16) and 0xFF) * 0.1f).toInt().coerceIn(0, 255)
        val g = (((color shr 8) and 0xFF) * 0.1f).toInt().coerceIn(0, 255)
        val b = ((color and 0xFF) * 0.1f).toInt().coerceIn(0, 255)
        return (0xFF shl 24) or (r shl 16) or (g shl 8) or b
    }

    private fun isVisibleDepth(depth: Float): Boolean {
        return depth >= 0.0f && depth <= 1.0f
    }

    private data class Tag(
        val entity: Player,
        val x: Float,
        val y: Float,
        val depth: Float,
        val distanceSqr: Double,
        val scale: Float
    )

    private enum class EntityType(override val choiceName: String) : NamedChoice {
        PLAYER("Player"),
        MOBS("Mobs"),
        ITEM("Item")
    }
}
