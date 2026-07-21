/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.render

internal data class CubesDash(val start: Float, val end: Float)

internal object WyvernCubesRules {
    const val SPAWN_RADIUS = 12f
    const val BASE_SIZE = 0.18f
    const val BASE_SPEED = 0.25f
    const val GLOW_MULTIPLIER = 1.7f
    const val MAX_RENDER_DISTANCE_SQ = 900.0
    const val RAY_RANGE = 128.0
    const val RAY_DISTANCE_SQ = 1.32
    const val IMPULSE_FORCE = 0.08
    const val IMPULSE_VERTICAL = 0.02

    val glowScales = listOf(10f, 6f, 3.5f)
    val glowAlphas = listOf(0.06f, 0.14f, 0.25f)

    fun shouldUpdate(renderCounter: Int): Boolean = renderCounter % 2 == 0

    fun alpha(life: Int, maxLife: Int): Float {
        if (maxLife <= 0) return 0f
        val lifeProgress = (life.toFloat() / maxLife).coerceIn(0f, 1f)
        val fadeIn = minOf(1f, (maxLife - life) / 20f)
        return lifeProgress * fadeIn
    }

    fun isVisible(distanceSquared: Double, cameraDot: Double): Boolean =
        distanceSquared <= MAX_RENDER_DISTANCE_SQ && cameraDot >= -1.0

    fun dashedIntervals(length: Float, dashLength: Float, gapLength: Float): List<CubesDash> {
        if (length <= 0f || dashLength <= 0f || gapLength < 0f) return emptyList()
        val result = ArrayList<CubesDash>()
        var position = 0f
        while (position < length) {
            result += CubesDash(position, minOf(length, position + dashLength))
            position += dashLength + gapLength
        }
        return result
    }
}
