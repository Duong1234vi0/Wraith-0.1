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

import net.ccbluex.liquidbounce.features.module.ModuleManager
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ExosGuiRenderer
import net.ccbluex.liquidbounce.utils.item.isConsumable
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.PlayerSkinDrawer
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import java.awt.Color
import java.text.Normalizer
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

private fun hudItemCount(vararg items: Item): Int {
    val player = mc.player ?: return 0
    val all = player.inventory.main + listOf(player.offHandStack)
    return all.sumOf { stack -> if (items.any { stack.isOf(it) }) stack.count else 0 }
}

private fun String.toHudAscii(): String {
    val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalized
        .replace(Regex("\\p{Mn}+"), "")
        .map { if (it.code in 32..126) it else '?' }
        .joinToString("")
}

private fun String.toTitleWords(): String {
    return split('_', ' ')
        .filter { it.isNotBlank() }
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}

private fun secondsToClock(ticks: Int): String {
    if (ticks < 0 || ticks > 20 * 60 * 60) {
        return "*:*"
    }

    val total = ticks / 20
    val minutes = total / 60
    val seconds = total % 60
    return "%d:%02d".format(java.util.Locale.US, minutes, seconds)
}

/**
 * Base class for HUD elements.
 */
abstract class HudElement(
    val name: String,
    x: Int,
    y: Int,
    var width: Int,
    var height: Int
) {
    var x: Int = x
        set(value) {
            field = value
            savePosition()
        }

    var y: Int = y
        set(value) {
            field = value
            savePosition()
        }

    var enabled: Boolean = true
        set(value) {
            field = value
            HudConfig.setElementEnabled(name, value)
        }

    init {
        loadFromConfig()
    }

    private fun loadFromConfig() {
        try {
            HudConfig.getElementPosition(name)?.let { saved ->
                fieldSetPosition(saved.first, saved.second)
            }
            enabled = HudConfig.getElementEnabled(name)
        } catch (e: Exception) {
            println("Error loading config for HUD element $name: ${e.message}")
        }
    }

    private fun fieldSetPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
    }

    private fun savePosition() {
        try {
            HudConfig.setElementPosition(name, x, y)
        } catch (e: Exception) {
            println("Error saving position for HUD element $name: ${e.message}")
        }
    }

    abstract fun render(context: DrawContext, isSelected: Boolean)

    open fun renderPreview(context: DrawContext) {
        if (enabled) {
            render(context, false)
        }
    }

    fun isMouseOver(mouseX: Int, mouseY: Int): Boolean {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height
    }

    open fun openSettings() {
        enabled = !enabled
    }

    protected fun renderBackground(context: DrawContext, isSelected: Boolean) {
        if (isSelected) {
            context.fill(x, y, x + width, y + height, 0x80222222.toInt())
            context.drawBorder(x, y, width, height, 0xFFFFFFFF.toInt())
        }
    }

    protected fun panel(context: DrawContext, w: Float, h: Float, title: String? = null) {
        ExosGuiRenderer.roundedRect(context.matrices, x.toFloat(), y.toFloat(), w, h, 5f, 0xB912151C.toInt())
        ExosGuiRenderer.roundedOutline(context.matrices, x.toFloat(), y.toFloat(), w, h, 5f, 0x28FFFFFF)
        if (title != null) {
            val titleWidth = textWidth(title, 12f)
            text(context, title, x + (w - titleWidth) * 0.5f, y + 4.5f, 0xFFF5F5F5.toInt(), 12f)
            ExosGuiRenderer.roundedGradientRect(context.matrices, x + 5f, y + 18.5f, w - 10f, 1.5f, 1f, accent(0), accent(80), accent(80), accent(0))
        }
    }

    protected fun text(context: DrawContext, value: String, tx: Float, ty: Float, color: Int = 0xFFFFFFFF.toInt(), size: Float = 12f) {
        ExosGuiRenderer.text(context.matrices, value.toHudAscii(), tx, ty, color, size)
    }

    protected fun textWidth(value: String, size: Float = 12f): Float = ExosGuiRenderer.textWidth(value.toHudAscii(), size)

    protected fun setBounds(w: Float, h: Float) {
        width = ceil(w).toInt().coerceAtLeast(1)
        height = ceil(h).toInt().coerceAtLeast(1)
    }

    protected fun accent(offset: Int = 0): Int {
        val hue = ((System.currentTimeMillis() / 35L + offset) % 360L) / 360f
        return Color.HSBtoRGB(hue, 0.52f, 1f)
    }

    protected fun mutedAccent(offset: Int = 0): Int = ExosGuiRenderer.withAlpha(accent(offset), 155)

    protected fun itemCount(vararg items: Item): Int {
        val player = mc.player ?: return 0
        val all = player.inventory.main + listOf(player.offHandStack)
        return all.sumOf { stack -> if (items.any { stack.isOf(it) }) stack.count else 0 }
    }

    protected fun drawItem(context: DrawContext, stack: ItemStack, ix: Int, iy: Int, scale: Float = 1f) {
        if (stack.isEmpty) {
            return
        }
        context.matrices.push()
        context.matrices.translate(ix.toFloat(), iy.toFloat(), 0f)
        context.matrices.scale(scale, scale, 1f)
        context.drawItem(stack, 0, 0)
        context.drawStackOverlay(mc.textRenderer, stack, 0, 0, null)
        context.matrices.pop()
    }
}

class WatermarkElement(x: Int, y: Int) : HudElement("WaterMark", x, y, 165, 20) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val username = mc.session.username
        val server = if (mc.isInSingleplayer) "SinglePlayer" else mc.networkHandler?.serverInfo?.address ?: "Server"
        val value = "Oblivex  |  $username  |  $server"
        val iconSize = 15f
        val iconX = x + 5f
        val iconY = y + 3f
        val textX = x + 27f
        val w = 36f + textWidth(value, 12f)
        panel(context, w, 22f)
        ExosGuiRenderer.roundedGradientRect(context.matrices, iconX, iconY, iconSize, iconSize, 4f, accent(0), accent(60), accent(90), accent(20))
        val oWidth = textWidth("O", 10f)
        text(context, "O", iconX + (iconSize - oWidth) * 0.5f, iconY + 2.1f, 0xFF10131A.toInt(), 10f)
        ExosGuiRenderer.roundedRect(context.matrices, x + 24f, y + 5f, 1f, 12f, 0.5f, 0x66FFFFFF)
        text(context, "Oblivex", textX, y + 5f, accent(0), 12f)
        text(context, value.removePrefix("Oblivex"), textX + textWidth("Oblivex", 12f), y + 5f, 0xFFE8E8E8.toInt(), 12f)
        setBounds(w, 22f)
        renderBackground(context, isSelected)
    }
}

class ModuleListElement(x: Int, y: Int) : HudElement("ArrayList", x, y, 120, 80) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val modules = ModuleManager.getModules()
            .filter { it.enabled && !it.hidden && it.name != "HUD" }
            .sortedByDescending { textWidth(it.name, 12f) }

        if (modules.isEmpty()) {
            if (isSelected) {
                setBounds(100f, 18f)
                renderBackground(context, true)
            }
            return
        }

        val maxWidth = modules.maxOf { textWidth(it.name, 12f) } + 12f
        val reverse = x > mc.window.scaledWidth / 2
        var yy = y.toFloat()
        modules.forEachIndexed { index, module ->
            val w = textWidth(module.name, 12f) + 10f
            val drawX = if (reverse) x + maxWidth - w else x.toFloat()
            ExosGuiRenderer.roundedRect(context.matrices, drawX, yy, w, 15f, 3f, 0x8C11141B.toInt())
            ExosGuiRenderer.roundedRect(context.matrices, if (reverse) drawX + w - 2f else drawX, yy, 2f, 15f, 1f, accent(index * 24))
            text(context, module.name, drawX + 5f, yy + 3.2f, accent(index * 24), 12f)
            yy += 15f
        }
        setBounds(maxWidth, modules.size * 15f)
        renderBackground(context, isSelected)
    }
}

open class TextChipElement(
    name: String,
    x: Int,
    y: Int,
    private val icon: String,
    private val provider: () -> String
) : HudElement(name, x, y, 80, 16) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val value = provider()
        val w = textWidth(value, 12f) + 31f
        panel(context, w, 19f)
        text(context, icon, x + 7f, y + 4.5f, accent(30), 12f)
        ExosGuiRenderer.roundedRect(context.matrices, x + 20f, y + 4f, 1f, 11f, 0.5f, 0x55FFFFFF)
        text(context, value, x + 26f, y + 4.5f, 0xFFEDEDED.toInt(), 12f)
        setBounds(w, 19f)
        renderBackground(context, isSelected)
    }
}

class CoordsElement(x: Int, y: Int) : TextChipElement("Coords", x, y, "C", {
    val player = mc.player
    if (player == null) {
        "XYZ 0 0 0"
    } else {
        val nether = if (player.world.registryKey.value.path == "the_nether") 8.0 else 0.125
        "XYZ ${player.x.toInt()} ${player.y.toInt()} ${player.z.toInt()} [${(player.x * nether).toInt()} ${(player.z * nether).toInt()}]"
    }
})

class FpsCounterElement(x: Int, y: Int) : TextChipElement("Fps", x, y, "F", { "FPS ${mc.currentFps}" })

class PingHudElement(x: Int, y: Int) : TextChipElement("Ping", x, y, "P", {
    val player = mc.player
    val ping = player?.let { mc.networkHandler?.getPlayerListEntry(it.uuid)?.latency } ?: 0
    "Ping ${ping}ms"
})

class MemoryHudElement(x: Int, y: Int) : TextChipElement("Memory", x, y, "M", {
    val runtime = Runtime.getRuntime()
    val used = (runtime.totalMemory() - runtime.freeMemory()) / 1024L / 1024L
    val max = runtime.maxMemory() / 1024L / 1024L
    "Mem ${used}/${max}MB"
})

class SpeedometerElement(x: Int, y: Int) : TextChipElement("Speedometer", x, y, "S", {
    val player = mc.player
    val speed = if (player == null) 0.0 else sqrt((player.x - player.prevX).let { it * it } + (player.z - player.prevZ).let { it * it }) * 20.0
    "Speed %.2f".format(java.util.Locale.US, speed)
})

class TPSCounterElement(x: Int, y: Int) : TextChipElement("TPS", x, y, "T", { "TPS 20.0" })

class TotemCounterElement(x: Int, y: Int) : TextChipElement("Totems", x, y, "T", { "Totems ${hudItemCount(Items.TOTEM_OF_UNDYING)}" })

class GapplesHudElement(x: Int, y: Int) : TextChipElement("Gapples", x, y, "G", { "Gapples ${hudItemCount(Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_APPLE)}" })

class PVPResourcesElement(x: Int, y: Int) : HudElement("PVPResources", x, y, 150, 24) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val resources = listOf(
            Items.TOTEM_OF_UNDYING.defaultStack to itemCount(Items.TOTEM_OF_UNDYING),
            Items.ENCHANTED_GOLDEN_APPLE.defaultStack to itemCount(Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_APPLE),
            Items.ENDER_PEARL.defaultStack to itemCount(Items.ENDER_PEARL),
            Items.EXPERIENCE_BOTTLE.defaultStack to itemCount(Items.EXPERIENCE_BOTTLE)
        )
        val w = 8f + resources.size * 33f
        panel(context, w, 25f)
        resources.forEachIndexed { index, (stack, count) ->
            val ix = x + 6 + index * 33
            drawItem(context, stack, ix, y + 4, 0.8f)
            text(context, count.toString(), ix + 17f, y + 8f, 0xFFEDEDED.toInt(), 10f)
        }
        setBounds(w, 25f)
        renderBackground(context, isSelected)
    }
}

class ArmorHudElement(x: Int, y: Int) : HudElement("ArmorHud", x, y, 86, 22) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val armor = mc.player?.armorItems?.toList()?.reversed().orEmpty()
        panel(context, 102f, 37f)
        armor.forEachIndexed { index, stack ->
            val ix = x + 7 + index * 23
            drawItem(context, stack, ix, y + 5, 0.82f)
            if (!stack.isEmpty && stack.isDamageable && stack.maxDamage > 0) {
                val percent = ((stack.maxDamage - stack.damage).toFloat() / stack.maxDamage.toFloat() * 100f)
                    .roundToInt()
                    .coerceIn(0, 100)
                val value = percent.toString()
                val valueWidth = textWidth(value, 8f)
                text(context, value, ix + (13f - valueWidth) * 0.5f, y + 25f, durabilityColor(percent), 8f)
            }
        }
        setBounds(102f, 37f)
        renderBackground(context, isSelected)
    }

    private fun durabilityColor(percent: Int): Int {
        return when {
            percent >= 70 -> 0xFFB8FF8A.toInt()
            percent >= 35 -> 0xFFFFE082.toInt()
            else -> 0xFFFF8A8A.toInt()
        }
    }
}

class InventoryHudElement(x: Int, y: Int) : HudElement("InventoryHud", x, y, 166, 62) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val player = mc.player ?: return
        panel(context, 166f, 67f, "Inventory")
        for (row in 0 until 3) {
            for (col in 0 until 9) {
                val slot = 9 + row * 9 + col
                drawItem(context, player.inventory.main[slot], x + 6 + col * 17, y + 22 + row * 15, 0.72f)
            }
        }
        setBounds(166f, 67f)
        renderBackground(context, isSelected)
    }
}

class PotionHudElement(x: Int, y: Int) : HudElement("PotionHud", x, y, 120, 48) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val effects = mc.player?.statusEffects?.toList().orEmpty()
        val visible = effects.take(8)
        val maxName = visible.maxOfOrNull {
            val name = Registries.STATUS_EFFECT.getId(it.effectType.value())?.path?.toTitleWords()
                ?: it.effectType.value().name.string
            textWidth(name, 9f)
        } ?: textWidth("None", 9f)
        val timeWidth = visible.maxOfOrNull { textWidth(secondsToClock(it.duration), 9f) } ?: 0f
        val w = max(130f, 31f + maxName + timeWidth + 18f)
        val h = max(28f, 25f + visible.size * 15f)
        panel(context, w, h, "Potions")
        visible.forEachIndexed { index, effect ->
            val rowY = y + 23f + index * 15f
            context.matrices.push()
            context.matrices.translate((x + 7).toFloat(), rowY - 1f, 0f)
            context.drawSpriteStretched(RenderLayer::getGuiTextured, mc.statusEffectSpriteManager.getSprite(effect.effectType), 0, 0, 9, 9)
            context.matrices.pop()

            val name = Registries.STATUS_EFFECT.getId(effect.effectType.value())?.path?.toTitleWords()
                ?: effect.effectType.value().name.string
            val amp = if (effect.amplifier > 0) " ${effect.amplifier + 1}" else ""
            text(context, (name + amp).take(18), x + 20f, rowY, 0xFFEDEDED.toInt(), 9f)
            val duration = secondsToClock(effect.duration)
            text(context, duration, x + w - textWidth(duration, 9f) - 7f, rowY, 0xFFBFEFFF.toInt(), 9f)
        }
        if (visible.isEmpty()) {
            text(context, "None", x + (w - textWidth("None", 10f)) * 0.5f, y + 22f, 0xFFB8BEC8.toInt(), 10f)
        }
        setBounds(w, h)
        renderBackground(context, isSelected)
    }
}

class KeyBindsElement(x: Int, y: Int) : HudElement("KeyBinds", x, y, 118, 42) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val binds = ModuleManager.getModules()
            .filter { it.enabled && !it.hidden && !it.bind.isUnbound }
            .take(8)
        val maxName = binds.maxOfOrNull { textWidth(it.name.take(16), 10f) } ?: 0f
        val maxKey = binds.maxOfOrNull { textWidth(it.bind.keyName.replace("_", " "), 10f) } ?: 0f
        val w = max(126f, 18f + maxName + maxKey + 19f)
        val h = max(30f, 23f + binds.size * 14f)
        panel(context, w, h, "KeyBinds")
        binds.forEachIndexed { index, module ->
            val rowY = y + 23f + index * 14f
            val key = module.bind.keyName.replace("_", " ")
            text(context, module.name.take(16), x + 7f, rowY, 0xFFEDEDED.toInt(), 10f)
            text(context, key, x + w - textWidth(key, 10f) - 7f, rowY, accent(index * 32), 10f)
        }
        setBounds(w, h)
        renderBackground(context, isSelected)
    }
}

class TargetHudElement(x: Int, y: Int) : HudElement("TargetHud", x, y, 150, 44) {
    private var lastTargetId = Int.MIN_VALUE
    private var animatedHealth = 0f

    override fun render(context: DrawContext, isSelected: Boolean) {
        val target = (ModuleKillAura.targetTracker.target as? LivingEntity) ?: nearestPlayer()
        panel(context, 176f, 75f)
        val name = if (target?.customName != null) {
            target.customName?.string ?: "No target"
        } else {
            target?.let { Registries.ENTITY_TYPE.getId(it.type).path.toTitleWords() } ?: "No target"
        }
        val health = target?.let { (it.health / it.maxHealth).coerceIn(0f, 1f) } ?: 0f
        val healthValue = target?.health?.coerceAtLeast(0f) ?: 0f
        updateAnimatedHealth(target, health)

        drawTargetFace(context, target)
        text(context, name.take(16), x + 49f, y + 8f, 0xFFFFFFFF.toInt(), 12f)

        val healthText = "%.1f HP".format(java.util.Locale.US, healthValue)
        text(context, healthText, x + 169f - textWidth(healthText, 10f), y + 9f, healthColor(health), 10f)

        val barX = x + 49f
        val barY = y + 27f
        val barW = 118f
        ExosGuiRenderer.roundedRect(context.matrices, barX, barY, barW, 8f, 3f, 0x702A303A)
        if (animatedHealth > health + 0.002f) {
            ExosGuiRenderer.roundedRect(context.matrices, barX, barY, barW * animatedHealth, 8f, 3f, 0xAAFF5757.toInt())
        }
        ExosGuiRenderer.roundedGradientRect(context.matrices, barX, barY, barW * health, 8f, 3f, healthColor(health), accent(95), accent(95), healthColor(health))
        drawTargetUseCooldown(context, target)
        drawTargetEquipment(context, target)
        setBounds(176f, 75f)
        renderBackground(context, isSelected)
    }

    private fun updateAnimatedHealth(target: LivingEntity?, health: Float) {
        val targetId = target?.id ?: Int.MIN_VALUE
        if (targetId != lastTargetId) {
            lastTargetId = targetId
            animatedHealth = health
            return
        }

        if (health >= animatedHealth || abs(animatedHealth - health) < 0.002f) {
            animatedHealth = health
        } else {
            animatedHealth += (health - animatedHealth) * 0.08f
        }
        animatedHealth = animatedHealth.coerceIn(0f, 1f)
    }

    private fun drawTargetFace(context: DrawContext, target: LivingEntity?) {
        val faceX = x + 9
        val faceY = y + 11
        val size = 30
        ExosGuiRenderer.roundedRect(context.matrices, faceX - 2f, faceY - 2f, size + 4f, size + 4f, 5f, 0x7710141D)

        if (target is AbstractClientPlayerEntity) {
            PlayerSkinDrawer.draw(context, target.skinTextures, faceX, faceY, size)
        } else {
            val letter = target?.let { Registries.ENTITY_TYPE.getId(it.type).path.take(1).uppercase() } ?: "?"
            val letterWidth = textWidth(letter, 16f)
            ExosGuiRenderer.roundedGradientRect(context.matrices, faceX.toFloat(), faceY.toFloat(), size.toFloat(), size.toFloat(), 5f, accent(0), accent(70), accent(100), accent(20))
            text(context, letter, faceX + (size - letterWidth) * 0.5f, faceY + 7f, 0xFF10131A.toInt(), 16f)
        }
    }

    private fun drawTargetUseCooldown(context: DrawContext, target: LivingEntity?) {
        if (target == null || !target.isUsingItem) {
            return
        }

        val activeStack = target.activeItem
        if (activeStack.isEmpty || !activeStack.isConsumable) {
            return
        }

        val maxUseTime = activeStack.getMaxUseTime(target).coerceAtLeast(1)
        val progress = (target.itemUseTime.toFloat() / maxUseTime.toFloat()).coerceIn(0f, 1f)
        val centerX = x + 24f
        val centerY = y + 58f

        ExosGuiRenderer.ringSegment(context.matrices, centerX, centerY, 12f, 2.2f, -90f, 360f, 0x472A303A)
        ExosGuiRenderer.ringSegment(context.matrices, centerX, centerY, 12f, 2.4f, -90f, 360f * progress, accent(40))
        ExosGuiRenderer.roundedRect(context.matrices, centerX - 8f, centerY - 8f, 16f, 16f, 5f, 0x6A10141D)
        drawItem(context, activeStack, (centerX - 6.2f).roundToInt(), (centerY - 6.2f).roundToInt(), 0.68f)
    }

    private fun drawTargetEquipment(context: DrawContext, target: LivingEntity?) {
        val stacks = if (target == null) {
            listOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        } else {
            listOf(target.mainHandStack) + target.armorItems.toList().reversed()
        }

        stacks.take(5).forEachIndexed { index, stack ->
            val slotX = x + 49 + index * 23
            val slotY = y + 42
            ExosGuiRenderer.roundedRect(context.matrices, slotX.toFloat(), slotY.toFloat(), 18f, 18f, 4f, 0x5510141D)
            drawItem(context, stack, slotX + 1, slotY + 1, 0.78f)

            if (!stack.isEmpty && stack.isDamageable && stack.maxDamage > 0) {
                val percent = ((stack.maxDamage - stack.damage).toFloat() / stack.maxDamage.toFloat() * 100f)
                    .roundToInt()
                    .coerceIn(0, 100)
                val value = percent.toString()
                text(context, value, slotX + (18f - textWidth(value, 7f)) * 0.5f, y + 62f, durabilityColor(percent), 7f)
            }
        }
    }

    private fun durabilityColor(percent: Int): Int {
        return when {
            percent >= 70 -> 0xFFB8FF8A.toInt()
            percent >= 35 -> 0xFFFFE082.toInt()
            else -> 0xFFFF8A8A.toInt()
        }
    }

    private fun healthColor(health: Float): Int {
        val red = 255
        val green = (75 + 180f * health).roundToInt().coerceIn(75, 255)
        val blue = (80f * health).roundToInt().coerceIn(0, 80)
        return (0xFF shl 24) or (red shl 16) or (green shl 8) or blue
    }

    private fun nearestPlayer(): PlayerEntity? {
        val player = mc.player ?: return null
        return mc.world?.players
            ?.filter { it != player && !it.isSpectator && it.isAlive }
            ?.minByOrNull { it.squaredDistanceTo(player) }
    }
}

class RadarRewriteElement(x: Int, y: Int) : HudElement("RadarRewrite", x, y, 82, 82) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val size = 82f
        panel(context, size, size, "Radar")
        val cx = x + size / 2f
        val cy = y + size / 2f + 5f
        ExosGuiRenderer.roundedRect(context.matrices, cx - 1f, cy - 1f, 2f, 2f, 1f, 0xFFFFFFFF.toInt())
        val player = mc.player
        if (player != null) {
            mc.world?.players?.filter { it != player }?.take(20)?.forEachIndexed { index, other ->
                val dx = (other.x - player.x).coerceIn(-30.0, 30.0).toFloat()
                val dz = (other.z - player.z).coerceIn(-30.0, 30.0).toFloat()
                ExosGuiRenderer.roundedRect(context.matrices, cx + dx, cy + dz, 3f, 3f, 1.5f, accent(index * 45))
            }
        }
        setBounds(size, size)
        renderBackground(context, isSelected)
    }
}

class StaffBoardElement(x: Int, y: Int) : HudElement("StaffBoard", x, y, 118, 38) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val staff = mc.networkHandler?.playerList
            ?.map { it.profile.name }
            ?.filter { name ->
                val lower = name.lowercase()
                listOf("admin", "mod", "helper", "staff", "owner").any { lower.contains(it) }
            }
            .orEmpty()
            .take(6)
        val h = max(30f, 22f + staff.size * 12f)
        panel(context, 118f, h, "Staff")
        if (staff.isEmpty()) {
            text(context, "Clear", x + 45f, y + 22f, 0xFF9CFFB2.toInt(), 10f)
        } else {
            staff.forEachIndexed { index, name ->
                text(context, name.take(15), x + 7f, y + 22f + index * 12f, 0xFFFFA4A4.toInt(), 10f)
            }
        }
        setBounds(118f, h)
        renderBackground(context, isSelected)
    }
}

class KillStatsElement(x: Int, y: Int) : TextChipElement("KillStats", x, y, "K", { "KillStreak 0  KD 0.0" })

class ChestCounterElement(x: Int, y: Int) : TextChipElement("ChestCounter", x, y, "C", { "Chests 0" })

class CrosshairElement(x: Int, y: Int) : HudElement("Crosshair", x, y, 18, 18) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val cx = mc.window.scaledWidth / 2f
        val cy = mc.window.scaledHeight / 2f
        ExosGuiRenderer.roundedRect(context.matrices, cx - 7f, cy - 0.5f, 14f, 1f, 0.5f, 0xCCFFFFFF.toInt())
        ExosGuiRenderer.roundedRect(context.matrices, cx - 0.5f, cy - 7f, 1f, 14f, 0.5f, 0xCCFFFFFF.toInt())
        setBounds(18f, 18f)
        renderBackground(context, isSelected)
    }
}

class CrosshairArrowsElement(x: Int, y: Int) : HudElement("CrosshairArrows", x, y, 40, 40) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val cx = mc.window.scaledWidth / 2f
        val cy = mc.window.scaledHeight / 2f
        repeat(4) { i ->
            val angle = Math.toRadians((i * 90).toDouble())
            val px = cx + cos(angle).toFloat() * 24f
            val py = cy + sin(angle).toFloat() * 24f
            ExosGuiRenderer.roundedRect(context.matrices, px - 2f, py - 2f, 4f, 4f, 1f, mutedAccent(i * 60))
        }
        setBounds(40f, 40f)
        renderBackground(context, isSelected)
    }
}

class HotbarElement(x: Int, y: Int) : HudElement("Hotbar", x, y, 184, 24) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val player = mc.player ?: return
        val cx = mc.window.scaledWidth / 2
        val by = mc.window.scaledHeight - 25
        ExosGuiRenderer.roundedRect(context.matrices, (cx - 92).toFloat(), by.toFloat(), 184f, 22f, 6f, 0xAA11141B.toInt())
        ExosGuiRenderer.roundedRect(context.matrices, (cx - 90 + player.inventory.selectedSlot * 20).toFloat(), by + 2f, 18f, 18f, 4f, 0x55FFFFFF)
        setBounds(184f, 24f)
        renderBackground(context, isSelected)
    }
}

class LegacyHudElement(x: Int, y: Int) : HudElement("LegacyHud", x, y, 120, 44) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        val player = mc.player
        val lines = listOf(
            "Oblivex",
            "XYZ ${player?.x?.toInt() ?: 0}, ${player?.y?.toInt() ?: 0}, ${player?.z?.toInt() ?: 0}",
            "FPS ${mc.currentFps}"
        )
        panel(context, 126f, 48f)
        lines.forEachIndexed { index, line ->
            text(context, line, x + 7f, y + 7f + index * 13f, if (index == 0) accent(0) else 0xFFEDEDED.toInt(), 11f)
        }
        setBounds(126f, 48f)
        renderBackground(context, isSelected)
    }
}

class CandleHudElement(x: Int, y: Int) : TextChipElement("CandleHud", x, y, "O", { "Oblivex" })

class CompanionElement(x: Int, y: Int) : TextChipElement("Companion", x, y, "V", { "Companion" })

class KillFeedElement(x: Int, y: Int) : TextChipElement("KillFeed", x, y, "F", { "KillFeed" })

class ParticlesElement(x: Int, y: Int) : HudElement("Particles", x, y, 58, 28) {
    override fun render(context: DrawContext, isSelected: Boolean) {
        panel(context, 58f, 28f)
        repeat(7) { index ->
            val t = (System.currentTimeMillis() / 160.0 + index).toFloat()
            val px = x + 8f + index * 7f
            val py = y + 14f + sin(t.toDouble()).toFloat() * 6f
            ExosGuiRenderer.roundedRect(context.matrices, px, py, 3f, 3f, 1.5f, accent(index * 45))
        }
        setBounds(58f, 28f)
        renderBackground(context, isSelected)
    }
}
