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
package net.ccbluex.liquidbounce.features.module.modules.render.gui

object SilentClickGuiAnimation {
    const val OPEN_SCALE_MS = 220L
    const val OPEN_ALPHA_MS = 180L
    const val CLOSE_MS = 200L
    const val MIN_SCALE = 0.94f

    fun expoOut(progress: Float): Float {
        val t = progress.coerceIn(0f, 1f)
        return if (t >= 1f) 1f else 1f - Math.pow(2.0, (-10f * t).toDouble()).toFloat()
    }

    fun openScale(elapsedMs: Long): Float =
        MIN_SCALE + (1f - MIN_SCALE) * expoOut(elapsedMs.toFloat() / OPEN_SCALE_MS.toFloat())

    fun openAlpha(elapsedMs: Long): Float =
        expoOut(elapsedMs.toFloat() / OPEN_ALPHA_MS.toFloat())

    fun closeValue(start: Float, end: Float, elapsedMs: Long, durationMs: Long): Float {
        val eased = expoOut(elapsedMs.toFloat() / durationMs.toFloat())
        return start + (end - start) * eased
    }
}
