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

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import kotlin.math.sqrt

enum class WyvernTargetHudStyle(override val choiceName: String) : NamedChoice {
    CLASSIC("Classic"),
    V2("V2"),
}

data class WyvernTargetHudRect(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
)

data class WyvernTargetHudGeometry(
    val panel: WyvernTargetHudRect,
    val head: WyvernTargetHudRect,
    val textX: Float,
    val nameY: Float,
    val healthTextY: Float,
    val bar: WyvernTargetHudRect,
    val equipmentX: Float,
    val equipmentY: Float,
)

object WyvernTargetHudLayout {
    const val WIDTH = 100f
    const val CLASSIC_HEIGHT = 38f
    const val V2_HEIGHT = 36f
    const val CLASSIC_RADIUS = 4f
    const val V2_RADIUS = 6f
    const val CLASSIC_HEAD_RADIUS = 10f
    const val V2_HEAD_RADIUS = 3f
    const val BAR_RADIUS = 0.5f
    const val ITEM_SCALE = 0.6f
    const val ITEM_STEP = 10f
    const val VISIBILITY_MS = 250L
    const val RETAIN_IN_MS = 1300L
    const val RETAIN_OUT_MS = 2200L
    const val HEALTH_MS = 250L
    const val OUTDATED_HEALTH_MS = 650L
    const val ABSORPTION_MS = 250L

    fun height(style: WyvernTargetHudStyle): Float = when (style) {
        WyvernTargetHudStyle.CLASSIC -> CLASSIC_HEIGHT
        WyvernTargetHudStyle.V2 -> V2_HEIGHT
    }

    fun geometry(style: WyvernTargetHudStyle, x: Float, y: Float): WyvernTargetHudGeometry = when (style) {
        WyvernTargetHudStyle.CLASSIC -> {
            val textX = x + 4.5f + 24.5f + 3.2f
            WyvernTargetHudGeometry(
                panel = WyvernTargetHudRect(x, y, WIDTH, CLASSIC_HEIGHT),
                head = WyvernTargetHudRect(x + 4.5f, y + 2f, 24.5f, 24.5f),
                textX = textX,
                nameY = y + 6.7f,
                healthTextY = y + 7.2f,
                bar = WyvernTargetHudRect(x + 5f, y + 29f, 90f, 5f),
                equipmentX = textX - 1f,
                equipmentY = y + 17.5f,
            )
        }

        WyvernTargetHudStyle.V2 -> WyvernTargetHudGeometry(
            panel = WyvernTargetHudRect(x, y, WIDTH, V2_HEIGHT),
            head = WyvernTargetHudRect(x + 5f, y + 5f, 26f, 26f),
            textX = x + 36f,
            nameY = y + 6.75f,
            healthTextY = y + 16.25f,
            bar = WyvernTargetHudRect(x + 35.5f, y + 25.5f, 59f, 5.5f),
            equipmentX = x + 3f,
            equipmentY = y - 11f,
        )
    }

    fun sanitizeHealth(health: Float, maxHealth: Float): Float {
        val safeMax = maxHealth.takeIf { it.isFinite() && it > 0f } ?: return 0f
        return health.takeIf(Float::isFinite)?.coerceIn(0f, safeMax) ?: 0f
    }

    fun ratio(value: Float, maxHealth: Float): Float {
        val safeMax = maxHealth.takeIf { it.isFinite() && it > 0f } ?: return 0f
        return (value.takeIf(Float::isFinite)?.coerceAtLeast(0f) ?: 0f).div(safeMax).coerceIn(0f, 1f)
    }

    fun width(totalWidth: Float, ratio: Float): Float =
        totalWidth.takeIf { it.isFinite() && it > 0f }
            ?.times(ratio.takeIf(Float::isFinite)?.coerceIn(0f, 1f) ?: 0f)
            ?.coerceIn(0f, totalWidth) ?: 0f

    fun truncateName(name: String, style: WyvernTargetHudStyle): String =
        name.take(if (style == WyvernTargetHudStyle.CLASSIC) 8 else 9)

    fun equipment(player: Player): List<ItemStack> = listOf(
        player.mainHandItem,
        player.offhandItem,
        player.getItemBySlot(EquipmentSlot.HEAD),
        player.getItemBySlot(EquipmentSlot.CHEST),
        player.getItemBySlot(EquipmentSlot.LEGS),
        player.getItemBySlot(EquipmentSlot.FEET),
    ).filterNot(ItemStack::isEmpty)
}

internal enum class WyvernEasing {
    CUBIC_OUT,
    CIRC_OUT,
}

internal class WyvernFloatAnimation(
    private var durationMs: Long,
    private var easing: WyvernEasing,
    initialValue: Float = 0f,
) {
    private var value = initialValue
    private var startValue = initialValue
    private var targetValue = initialValue
    private var startTime = 0L

    fun update(target: Float, nowMs: Long, duration: Long = durationMs, curve: WyvernEasing = easing): Float {
        if (target != targetValue || duration != durationMs || curve != easing) {
            value = valueAt(nowMs)
            startValue = value
            targetValue = target
            startTime = nowMs
            durationMs = duration
            easing = curve
        }
        value = valueAt(nowMs)
        return value
    }

    fun snap(newValue: Float) {
        value = newValue
        startValue = newValue
        targetValue = newValue
        startTime = 0L
    }

    fun current(nowMs: Long): Float {
        value = valueAt(nowMs)
        return value
    }

    private fun valueAt(nowMs: Long): Float {
        if (value == targetValue || durationMs <= 0L || startTime == 0L) {
            return targetValue
        }
        val progress = ((nowMs - startTime).toFloat() / durationMs).coerceIn(0f, 1f)
        val eased = when (easing) {
            WyvernEasing.CUBIC_OUT -> {
                val shifted = progress - 1f
                shifted * shifted * shifted + 1f
            }
            WyvernEasing.CIRC_OUT -> sqrt((1f - (progress - 1f) * (progress - 1f)).coerceAtLeast(0f))
        }
        return startValue + (targetValue - startValue) * eased
    }
}

data class WyvernTargetHealthState(
    val health: Float,
    val currentRatio: Float,
    val outdatedRatio: Float,
    val absorptionRatio: Float,
)

internal class WyvernTargetHealthAnimation {
    private val current = WyvernFloatAnimation(WyvernTargetHudLayout.HEALTH_MS, WyvernEasing.CUBIC_OUT)
    private val outdated = WyvernFloatAnimation(WyvernTargetHudLayout.OUTDATED_HEALTH_MS, WyvernEasing.CUBIC_OUT)
    private val absorption = WyvernFloatAnimation(WyvernTargetHudLayout.ABSORPTION_MS, WyvernEasing.CUBIC_OUT)

    fun reset(health: Float, maxHealth: Float, absorptionAmount: Float) {
        val ratio = WyvernTargetHudLayout.ratio(health, maxHealth)
        current.snap(ratio)
        outdated.snap(ratio)
        absorption.snap(WyvernTargetHudLayout.ratio(absorptionAmount, maxHealth))
    }

    fun update(health: Float, maxHealth: Float, absorptionAmount: Float, nowMs: Long): WyvernTargetHealthState {
        val safeHealth = WyvernTargetHudLayout.sanitizeHealth(health, maxHealth)
        val targetRatio = WyvernTargetHudLayout.ratio(safeHealth, maxHealth)
        val currentRatio = current.update(targetRatio, nowMs)
        val outdatedRatio = if (outdated.current(nowMs) < currentRatio) {
            outdated.snap(currentRatio)
            currentRatio
        } else {
            outdated.update(targetRatio, nowMs)
        }
        val absorptionRatio = absorption.update(
            WyvernTargetHudLayout.ratio(absorptionAmount, maxHealth),
            nowMs,
        )
        return WyvernTargetHealthState(safeHealth, currentRatio, outdatedRatio, absorptionRatio)
    }
}
