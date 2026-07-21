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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class WyvernLineGlyphRulesTest {

    @Test
    fun `settings and gradual count behavior match source`() {
        val module = source()
        assertTrue(module.contains("int(\"Count\", 50, 10..200)"))
        assertTrue(module.contains("float(\"Speed\", 1.0f, 0.1f..5.0f)"))
        assertTrue(module.contains("boolean(\"Glow\", true)"))
        assertTrue(module.contains("float(\"Thickness\", 1.5f, 0.5f..5.0f)"))
        assertTrue(module.contains("if (paths.size < count)"))
        assertFalse(module.contains("while (active < target)"))
        assertFalse(module.contains("MAX_PATHS"))
        assertTrue(module.contains("12 + random.nextInt(15)"))
    }

    @Test
    fun `quad animation movement and removal constants match source`() {
        assertEquals(1_200f, WyvernLineGlyphRules.ALPHA_DURATION_MS)
        assertClose(0f, WyvernLineGlyphRules.quadInOut(0f))
        assertClose(0.125f, WyvernLineGlyphRules.quadInOut(0.25f))
        assertClose(0.5f, WyvernLineGlyphRules.quadInOut(0.5f))
        assertClose(0.875f, WyvernLineGlyphRules.quadInOut(0.75f))
        assertClose(1f, WyvernLineGlyphRules.quadInOut(1f))
        assertEquals(0.025f, WyvernLineGlyphRules.MOVEMENT_PROGRESS)
        assertEquals(2.5, WyvernLineGlyphRules.STEP_LENGTH)
        val module = source()
        assertTrue(module.contains("if (points.size >= maxPoints)"))
        assertTrue(module.contains("removing = true"))
    }

    @Test
    fun `axis and reverse direction behavior is source exact`() {
        assertTrue(WyvernLineGlyphRules.acceptsDirection(-0.5))
        assertFalse(WyvernLineGlyphRules.acceptsDirection(-0.5001))
        val module = source()
        assertTrue(module.contains("when (random.nextInt(3))"))
        assertTrue(module.contains("Direction3(sign, 0.0, 0.0)"))
        assertTrue(module.contains("Direction3(0.0, sign, 0.0)"))
        assertTrue(module.contains("Direction3(0.0, 0.0, sign)"))
        assertTrue(module.contains("do {"))
    }

    @Test
    fun `camera culling segment alpha and dashed geometry match source`() {
        assertTrue(WyvernLineGlyphRules.shouldCullFirstPerson(true, -0.01))
        assertFalse(WyvernLineGlyphRules.shouldCullFirstPerson(false, -1.0))
        assertClose(0.2f, WyvernLineGlyphRules.segmentAlpha(1f, 0, 5))
        assertClose(0.8f, WyvernLineGlyphRules.segmentAlpha(1f, 3, 5))
        assertEquals(listOf(GlyphDash(0.0, 0.92)), WyvernLineGlyphRules.dashedIntervals(0.92))
        val longDashes = WyvernLineGlyphRules.dashedIntervals(1.5)
        assertEquals(2, longDashes.size)
        assertClose(0.58f, longDashes[0].end.toFloat())
        assertClose(0.92f, longDashes[1].start.toFloat())
        assertClose(1.5f, longDashes[1].end.toFloat())
        val module = source()
        assertTrue(module.contains("CameraType.FIRST_PERSON"))
        assertTrue(module.contains("appendDashedSegment"))
    }

    @Test
    fun `glow widths theme and render state are restored`() {
        assertEquals(
            listOf(8f to 0.04f, 5f to 0.08f, 2.5f to 0.15f, 1.35f to 0.22f),
            WyvernLineGlyphRules.glowPasses,
        )
        val module = source()
        assertTrue(module.contains("val lineWidth = thickness * widthMul"))
        assertTrue(module.contains("val baseColor = ClientTheme.accentA"))
        assertFalse(module.contains("ClientTheme.accentB"))
        assertTrue(module.contains("RenderSystem.enableDepthTest()"))
        assertTrue(module.contains("RenderSystem.depthMask(false)"))
        assertTrue(module.contains("RenderSystem.depthMask(true)"))
        assertTrue(module.contains("RenderSystem.disableBlend()"))
        val manager = Files.readString(Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt"))
        assertEquals(1, Regex("ModuleLineGlyphs,").findAll(manager).count())
    }

    private fun source(): String = Files.readString(Path.of(MODULE))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.0001f) { "expected <$expected> but was <$actual>" }
    }

    private companion object {
        const val MODULE = "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleLineGlyphs.kt"
    }
}
