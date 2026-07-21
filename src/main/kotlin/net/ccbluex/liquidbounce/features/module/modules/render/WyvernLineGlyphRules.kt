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

internal data class GlyphDash(val start: Double, val end: Double)

internal object WyvernLineGlyphRules {
    const val ALPHA_DURATION_MS = 1_200f
    const val MOVEMENT_PROGRESS = 0.025f
    const val STEP_LENGTH = 2.5
    const val DASH_LENGTH = 0.58
    const val GAP_LENGTH = 0.34
    const val REVERSE_DOT_LIMIT = -0.5

    val glowPasses = listOf(
        8f to 0.04f,
        5f to 0.08f,
        2.5f to 0.15f,
        1.35f to 0.22f,
    )

    fun quadInOut(progress: Float): Float {
        val value = progress.coerceIn(0f, 1f)
        return if (value < 0.5f) {
            2f * value * value
        } else {
            1f - (-2f * value + 2f).let { it * it } / 2f
        }
    }

    fun segmentAlpha(pathAlpha: Float, index: Int, pointCount: Int): Float =
        if (pointCount <= 0) 0f else pathAlpha * (index + 1f) / pointCount

    fun shouldCullFirstPerson(firstPerson: Boolean, cameraDot: Double): Boolean =
        firstPerson && cameraDot < 0.0

    fun acceptsDirection(dotWithPrevious: Double): Boolean = dotWithPrevious >= REVERSE_DOT_LIMIT

    fun dashedIntervals(length: Double): List<GlyphDash> {
        if (length <= 0.0) return emptyList()
        if (length <= DASH_LENGTH + GAP_LENGTH + 1.0E-9) return listOf(GlyphDash(0.0, length))
        val result = ArrayList<GlyphDash>()
        var distance = 0.0
        while (distance < length) {
            result += GlyphDash(distance, minOf(length, distance + DASH_LENGTH))
            distance += DASH_LENGTH + GAP_LENGTH
        }
        return result
    }
}
