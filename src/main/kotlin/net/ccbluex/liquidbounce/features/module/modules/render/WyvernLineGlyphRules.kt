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

import kotlin.math.sqrt

internal data class LineGlyphNormal(val x: Float, val y: Float, val z: Float)

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

    fun dashCount(length: Double): Int {
        if (!length.isFinite() || length <= 0.0) return 0
        if (length <= DASH_LENGTH + GAP_LENGTH + 1.0E-9) return 1
        var count = 0
        var distance = 0.0
        while (distance < length) {
            count++
            distance += DASH_LENGTH + GAP_LENGTH
        }
        return count
    }

    @Suppress("LongParameterList")
    fun lineNormal(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float): LineGlyphNormal? {
        if (!x1.isFinite() || !y1.isFinite() || !z1.isFinite() ||
            !x2.isFinite() || !y2.isFinite() || !z2.isFinite()
        ) return null
        val dx = x2 - x1
        val dy = y2 - y1
        val dz = z2 - z1
        val length = sqrt(dx * dx + dy * dy + dz * dz)
        if (!length.isFinite() || length <= 1.0E-6f) return null
        return LineGlyphNormal(dx / length, dy / length, dz / length)
    }

    fun maxLineVertices(pathCount: Int, maxPoints: Int, passCount: Int = 5): Long {
        if (pathCount <= 0 || maxPoints < 2 || passCount <= 0) return 0
        return pathCount.toLong() * (maxPoints - 1) * dashCount(STEP_LENGTH) * 2L * passCount
    }
}
