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
package net.ccbluex.liquidbounce.features.module.modules.render.killeffect

import net.minecraft.world.phys.Vec3

internal enum class KillTrackedState {
    ALIVE,
    DEAD,
    REMOVED,
}

internal class KillEffectTracking<T> {
    private val entries = linkedMapOf<Int, Entry<T>>()

    val size: Int
        get() = entries.size

    fun track(id: Int, value: T, alive: Boolean, nowMs: Long) {
        if (alive) entries[id] = Entry(value, nowMs)
    }

    fun process(nowMs: Long, state: (T) -> KillTrackedState): List<T> {
        val deaths = ArrayList<T>()
        val iterator = entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next().value
            when (state(entry.value)) {
                KillTrackedState.REMOVED -> iterator.remove()
                KillTrackedState.DEAD -> {
                    deaths += entry.value
                    iterator.remove()
                }
                KillTrackedState.ALIVE -> if (nowMs - entry.startTime > KillEffectRules.TRACK_TIMEOUT_MS) {
                    iterator.remove()
                }
            }
        }
        return deaths
    }

    fun clear() = entries.clear()

    private data class Entry<T>(val value: T, val startTime: Long)
}

internal data class KillEffectRenderPass(val width: Float, val alphaMaximum: Int, val additive: Boolean)
internal data class KillEffectLineNormal(val x: Float, val y: Float, val z: Float)

internal object KillEffectRules {
    const val TRACK_TIMEOUT_MS = 4_000L
    const val EFFECT_DURATION_MS = 800L
    const val START_HEIGHT = 200.0
    const val HORIZONTAL_RANGE = 0.8
    const val MIN_DOWN_STEP = 6.0
    const val DOWN_STEP_RANGE = 10.0
    const val FADE_IN_END = 0.15f

    val renderPasses = listOf(
        KillEffectRenderPass(40f, 40, true),
        KillEffectRenderPass(24f, 80, true),
        KillEffectRenderPass(12f, 130, true),
        KillEffectRenderPass(6f, 190, true),
        KillEffectRenderPass(4.4f, 255, false),
    )

    fun progress(nowMs: Long, startMs: Long): Float =
        ((nowMs - startMs).coerceAtLeast(0L).toFloat() / EFFECT_DURATION_MS).coerceIn(0f, 1f)

    fun alpha(progress: Float): Float {
        val value = progress.coerceIn(0f, 1f)
        return if (value < FADE_IN_END) value / FADE_IN_END else 1f - (value - FADE_IN_END) / (1f - FADE_IN_END)
    }

    fun generateLightning(target: Vec3, random: () -> Double): List<Vec3> {
        val points = ArrayList<Vec3>()
        var current = target.add(0.0, START_HEIGHT, 0.0)
        points += current
        while (current.y > target.y) {
            val next = current.add(
                (random() - 0.5) * HORIZONTAL_RANGE,
                -(MIN_DOWN_STEP + random() * DOWN_STEP_RANGE),
                (random() - 0.5) * HORIZONTAL_RANGE,
            )
            current = if (next.y <= target.y) target else next
            points += current
        }
        if (points.last() != target) points += target
        return points
    }

    fun lineNormal(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float): KillEffectLineNormal? {
        val dx = x1 - x2
        val dy = y1 - y2
        val dz = z1 - z2
        val lengthSquared = dx * dx + dy * dy + dz * dz
        if (!lengthSquared.isFinite() || lengthSquared <= 1.0E-12f) return null
        val inverseLength = 1f / kotlin.math.sqrt(lengthSquared)
        val normal = KillEffectLineNormal(dx * inverseLength, dy * inverseLength, dz * inverseLength)
        return normal.takeIf { it.x.isFinite() && it.y.isFinite() && it.z.isFinite() }
    }
}
