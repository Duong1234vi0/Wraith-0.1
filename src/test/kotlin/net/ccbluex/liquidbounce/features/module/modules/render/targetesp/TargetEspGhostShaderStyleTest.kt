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
package net.ccbluex.liquidbounce.features.module.modules.render.targetesp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TargetEspGhostShaderStyleTest {

    @Test
    fun `Ghosts2 shader contribution budget is one trail layer plus head-only late glow`() {
        val trails = 5 * 35
        val heads = 5
        assertEquals(185, TargetEspGhostShaderStyle.ghosts2ShaderContributions(trails, heads))
        assertEquals(0.20F, TargetEspGhostShaderStyle.GHOST_SHADER_BASE_ALPHA)
        assertEquals(1.45F, TargetEspGhostShaderStyle.GHOST_LATE_HALO_SCALE)
        assertEquals(0.11F, TargetEspGhostShaderStyle.GHOST_LATE_HALO_ALPHA)
        assertEquals(0.82F, TargetEspGhostShaderStyle.GHOST_LATE_CORE_ALPHA)
    }

    @Test
    fun `Ghost V2 keeps three normal layers and reduces shader per-point layers`() {
        val points = 90
        val heads = 3
        assertEquals(270, TargetEspGhostShaderStyle.ghostV2NormalContributions(points))
        assertEquals(186, TargetEspGhostShaderStyle.ghostV2ShaderContributions(points, heads))
        assertTrue(
            TargetEspGhostShaderStyle.ghostV2ShaderContributions(points, heads) <=
                TargetEspGhostShaderStyle.ghostV2NormalContributions(points) + heads * 2
        )
        assertEquals(1.80F, TargetEspGhostShaderStyle.GHOST_V2_SHADER_BASE_SCALE)
        assertEquals(0.09F, TargetEspGhostShaderStyle.GHOST_V2_SHADER_BASE_ALPHA)
        assertEquals(0.62F, TargetEspGhostShaderStyle.GHOST_V2_LATE_CORE_ALPHA)
    }

    @Test
    fun `energy helpers clamp non-finite alpha and nonnegative size`() {
        assertEquals(0.0F, TargetEspGhostShaderStyle.alpha(Float.NaN, 0.2F))
        assertEquals(1.0F, TargetEspGhostShaderStyle.alpha(4.0F, 4.0F))
        assertEquals(0, TargetEspGhostShaderStyle.alphaByte(-20, 0.5F))
        assertEquals(0, TargetEspGhostShaderStyle.alphaByte(255, Float.POSITIVE_INFINITY))
        assertEquals(0.0F, TargetEspGhostShaderStyle.scale(-3.0F, 2.0F))
        assertEquals(0.0F, TargetEspGhostShaderStyle.scale(3.0F, Float.NaN))
    }
}
