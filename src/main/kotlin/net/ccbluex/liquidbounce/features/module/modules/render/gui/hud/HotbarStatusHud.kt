/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.count
import net.ccbluex.liquidbounce.utils.entity.drawItem
import net.ccbluex.liquidbounce.utils.entity.drawStackOverlay
import net.ccbluex.liquidbounce.utils.entity.drawTextWithShadow
import net.ccbluex.liquidbounce.utils.entity.isEmpty
import net.ccbluex.liquidbounce.utils.entity.item
import net.ccbluex.liquidbounce.utils.entity.main
import net.ccbluex.liquidbounce.utils.entity.matrices
import net.ccbluex.liquidbounce.utils.entity.offhandItem
import net.minecraft.client.gui.DrawContext
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

/** Pure calculations shared by the fixed hotbar-attached status widgets and their tests. */
internal object HotbarStatusHudLogic {

    internal data class CountedStack(val isTotem: Boolean, val count: Int)

    fun totalTotems(mainInventory: Iterable<ItemStack>, offhand: ItemStack): Int {
        return totalTotems(
            mainInventory.map { stack -> CountedStack(stack.item == Items.TOTEM_OF_UNDYING, stack.count) },
            CountedStack(offhand.item == Items.TOTEM_OF_UNDYING, offhand.count),
        )
    }

    internal fun totalTotems(mainInventory: Iterable<CountedStack>, offhand: CountedStack): Int {
        val mainCount = mainInventory.sumOf { stack -> if (stack.isTotem) stack.count else 0 }
        return mainCount + if (offhand.isTotem) offhand.count else 0
    }

    fun airAdjustment(isUnderWater: Boolean, air: Int, maxAir: Int): Int {
        if (maxAir <= 0) {
            return if (isUnderWater) 10 else 0
        }

        val boundedAir = air.coerceAtMost(maxAir)
        return if (!isUnderWater && boundedAir >= maxAir) 0 else 10
    }

    fun durabilityPercent(damage: Int, maxDamage: Int): Int? {
        if (maxDamage <= 0) {
            return null
        }

        val boundedDamage = damage.coerceIn(0, maxDamage)
        val damageRatio = boundedDamage.toFloat() / maxDamage.toFloat()
        return (100 - (damageRatio * 100f).toInt()).coerceIn(0, 100)
    }

    fun durabilityColor(damage: Int, maxDamage: Int): Int? {
        if (maxDamage <= 0) {
            return null
        }

        val boundedDamage = damage.coerceIn(0, maxDamage)
        val green = (maxDamage - boundedDamage).toFloat() / maxDamage.toFloat()
        val red = 1f - green
        val redChannel = (red * 255f).toInt().coerceIn(0, 255)
        val greenChannel = (green * 255f).toInt().coerceIn(0, 255)
        return (255 shl 24) or (redChannel shl 16) or (greenChannel shl 8)
    }

    fun armorIconX(centerX: Int, visualIndex: Int): Int = centerX + 12 + visualIndex * 20
}

/**
 * Small lifecycle state for V-Core's local TotemPop pulse. It deliberately stores no player or
 * ItemStack snapshot beyond identity used to detect a local-player replacement.
 */
internal class HotbarStatusHudPulseState {
    var ticks: Int = 0
        private set

    private var owner: Any? = null

    val factor: Float
        get() = ticks / 15f

    fun onTotemPop(entity: Any?, localPlayer: Any?) {
        synchronizeOwner(localPlayer)
        if (localPlayer != null && entity === localPlayer) {
            ticks = 15
        }
    }

    fun tick(localPlayer: Any?) {
        if (owner !== localPlayer) {
            owner = localPlayer
            ticks = 0
            return
        }

        if (ticks > 0) {
            ticks--
        }
    }

    fun reset() {
        owner = null
        ticks = 0
    }

    private fun synchronizeOwner(localPlayer: Any?) {
        if (owner !== localPlayer) {
            owner = localPlayer
            ticks = 0
        }
    }
}

/**
 * V-Core-style Totem and armor status overlays. These are intentionally not HudElement cards:
 * their positions are recalculated from the current vanilla-hotbar coordinates every frame.
 */
object HotbarStatusHud {

    fun render(
        context: DrawContext,
        player: Player?,
        renderTotemCounter: Boolean,
        renderArmorDurability: Boolean,
        pulseTicks: Int,
    ) {
        if (player == null || (!renderTotemCounter && !renderArmorDurability)) {
            return
        }

        val screenWidth = mc.window.guiScaledWidth
        val screenHeight = mc.window.guiScaledHeight
        val centerX = screenWidth / 2
        val airAdjustment = HotbarStatusHudLogic.airAdjustment(
            player.isUnderWater,
            player.airSupply,
            player.maxAirSupply,
        )
        val itemY = screenHeight - 55 - airAdjustment
        val totalTotems = if (renderTotemCounter) {
            HotbarStatusHudLogic.totalTotems(player.inventory.main, player.inventory.offhandItem)
        } else {
            0
        }

        if (totalTotems > 0 && pulseTicks > 0) {
            val pulseAlpha = (255f * (pulseTicks / 15f).coerceIn(0f, 1f)).toInt()
            ExosGuiRenderer.shaderGlow(
                context.matrices,
                centerX - 8f,
                itemY.toFloat(),
                16f,
                16f,
                4f,
                argb(pulseAlpha, 255, 0, 0),
                spread = 5f,
            )
        }

        if (renderArmorDurability) {
            renderArmor(context, player, centerX, itemY)
        }

        if (totalTotems > 0) {
            renderTotem(context, centerX, itemY, totalTotems)
        }
    }

    private fun renderTotem(context: DrawContext, centerX: Int, itemY: Int, totalTotems: Int) {
        val stack = Items.TOTEM_OF_UNDYING.defaultInstance
        val iconX = centerX - 8
        context.drawItem(stack, iconX, itemY)
        // The default stack has count one, so this preserves the normal item overlay without
        // duplicating the custom total count as a vanilla stack count.
        context.drawStackOverlay(mc.font, stack, iconX, itemY)

        val countText = totalTotems.toString()
        val textX = centerX - mc.font.width(countText) / 2
        context.drawTextWithShadow(mc.font, countText, textX, itemY - 6, 0xFFFFFFFF.toInt())
    }

    private fun renderArmor(context: DrawContext, player: Player, centerX: Int, itemY: Int) {
        val armorStacks = listOf(
            player.getItemBySlot(EquipmentSlot.HEAD),
            player.getItemBySlot(EquipmentSlot.CHEST),
            player.getItemBySlot(EquipmentSlot.LEGS),
            player.getItemBySlot(EquipmentSlot.FEET),
        )

        armorStacks.forEachIndexed { visualIndex, stack ->
            if (stack.isEmpty) {
                return@forEachIndexed
            }

            val x = HotbarStatusHudLogic.armorIconX(centerX, visualIndex)
            context.drawItem(stack, x, itemY)
            context.drawStackOverlay(mc.font, stack, x, itemY)

            val percent = HotbarStatusHudLogic.durabilityPercent(stack.damageValue, stack.maxDamage)
                ?: return@forEachIndexed
            val color = HotbarStatusHudLogic.durabilityColor(stack.damageValue, stack.maxDamage)
                ?: return@forEachIndexed
            val text = percent.toString()
            val textX = x + 8 - mc.font.width(text) / 2
            context.drawTextWithShadow(mc.font, text, textX, itemY - 6, color)
        }
    }

    private fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int {
        return (alpha.coerceIn(0, 255) shl 24) or
            (red.coerceIn(0, 255) shl 16) or
            (green.coerceIn(0, 255) shl 8) or
            blue.coerceIn(0, 255)
    }
}
