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

import kotlin.math.abs
import kotlin.math.exp

class TweenFloat(
    initial: Float = 0f,
    private val durationMs: Long = 220L,
    private val easing: (Float) -> Float = HudEasing::cubicOut,
) {
    var value = initial
        private set
    private var from = initial
    private var target = initial
    private var startedAt = System.nanoTime()

    fun update(newTarget: Float): Float {
        if (abs(newTarget - target) > 0.0001f) {
            from = value
            target = newTarget
            startedAt = System.nanoTime()
        }

        val elapsedMs = (System.nanoTime() - startedAt) / 1_000_000f
        val progress = (elapsedMs / durationMs.coerceAtLeast(1)).coerceIn(0f, 1f)
        value = HudEasing.lerp(from, target, easing(progress))
        if (progress >= 1f || abs(target - value) < 0.0005f) {
            value = target
        }
        return value
    }

    fun snap(newValue: Float) {
        value = newValue
        from = newValue
        target = newValue
        startedAt = System.nanoTime()
    }
}

class SpringFloat(
    initial: Float = 0f,
    private val stiffness: Float = 22f,
    private val damping: Float = 16f,
) {
    var value = initial
        private set
    private var velocity = 0f
    private var lastUpdate = System.nanoTime()

    fun update(target: Float): Float {
        val now = System.nanoTime()
        val dt = ((now - lastUpdate) / 1_000_000_000f).coerceIn(0f, 0.05f)
        lastUpdate = now
        if (dt <= 0f) {
            return value
        }

        val displacement = target - value
        velocity += displacement * stiffness * dt
        velocity *= exp(-damping * dt)
        value += velocity * dt

        if (abs(target - value) < 0.0008f && abs(velocity) < 0.0008f) {
            value = target
            velocity = 0f
        }
        return value
    }

    fun snap(target: Float) {
        value = target
        velocity = 0f
        lastUpdate = System.nanoTime()
    }
}

object HudEasing {
    fun cubicOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        val inv = 1f - t
        return 1f - inv * inv * inv
    }

    fun quintOut(value: Float): Float {
        val t = value.coerceIn(0f, 1f)
        val inv = 1f - t
        return 1f - inv * inv * inv * inv * inv
    }

    fun lerp(from: Float, to: Float, progress: Float): Float = from + (to - from) * progress.coerceIn(0f, 1f)
}
