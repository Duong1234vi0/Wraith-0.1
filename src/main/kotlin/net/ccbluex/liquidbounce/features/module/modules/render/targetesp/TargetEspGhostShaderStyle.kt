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
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

import kotlin.math.roundToInt

/** Pure contribution budget for the two shader-enabled Ghost render passes. */
internal object TargetEspGhostShaderStyle {
    const val GHOST_SHADER_BASE_ALPHA = 0.20F
    const val GHOST_LATE_HALO_SCALE = 1.45F
    const val GHOST_LATE_HALO_ALPHA = 0.11F
    const val GHOST_LATE_CORE_ALPHA = 0.82F

    const val GHOST_V2_SHADER_BASE_SCALE = 1.80F
    const val GHOST_V2_SHADER_BASE_ALPHA = 0.09F
    const val GHOST_V2_LATE_CORE_SCALE = 1.00F
    const val GHOST_V2_LATE_CORE_ALPHA = 0.62F
    const val GHOST_V2_HEAD_HALO_SCALE = 1.45F
    const val GHOST_V2_HEAD_HALO_ALPHA = 0.11F
    const val GHOST_V2_HEAD_CORE_ALPHA = 0.82F

    fun alpha(baseAlpha: Float, multiplier: Float): Float =
        (finiteNonNegative(baseAlpha) * finiteNonNegative(multiplier)).coerceIn(0.0F, 1.0F)

    fun alphaByte(baseAlpha: Int, multiplier: Float): Int =
        (baseAlpha.coerceIn(0, 255) * finiteNonNegative(multiplier)).roundToInt().coerceIn(0, 255)

    fun scale(baseSize: Float, multiplier: Float): Float =
        (finiteNonNegative(baseSize) * finiteNonNegative(multiplier)).coerceAtLeast(0.0F)

    fun ghosts2ShaderContributions(trailPoints: Int, heads: Int): Int =
        trailPoints.coerceAtLeast(0) + heads.coerceAtLeast(0) * 2

    fun ghostV2NormalContributions(trailPoints: Int): Int = trailPoints.coerceAtLeast(0) * 3

    fun ghostV2ShaderContributions(trailPoints: Int, heads: Int): Int =
        trailPoints.coerceAtLeast(0) * 2 + heads.coerceAtLeast(0) * 2

    private fun finiteNonNegative(value: Float): Float =
        if (value.isFinite()) value.coerceAtLeast(0.0F) else 0.0F
}
