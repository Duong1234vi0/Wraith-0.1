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
    fun `camera culling segment alpha and direct dashed geometry match source`() {
        assertTrue(WyvernLineGlyphRules.shouldCullFirstPerson(true, -0.01))
        assertFalse(WyvernLineGlyphRules.shouldCullFirstPerson(false, -1.0))
        assertClose(0.2f, WyvernLineGlyphRules.segmentAlpha(1f, 0, 5))
        assertClose(0.8f, WyvernLineGlyphRules.segmentAlpha(1f, 3, 5))
        assertEquals(0, WyvernLineGlyphRules.dashCount(0.0))
        assertEquals(1, WyvernLineGlyphRules.dashCount(0.92))
        assertEquals(2, WyvernLineGlyphRules.dashCount(1.5))
        assertEquals(3, WyvernLineGlyphRules.dashCount(WyvernLineGlyphRules.STEP_LENGTH))
        val module = source()
        assertTrue(module.contains("CameraType.FIRST_PERSON"))
        assertTrue(module.contains("appendPreparedDashes"))
        assertTrue(module.contains("while (distance < length)"))
        assertFalse(module.contains("GlyphDash"))
        assertFalse(module.contains("dashedIntervals"))
        assertFalse(module.contains("List<GlyphDash>"))
    }

    @Test
    fun `frame geometry is prepared once then reused for all five passes`() {
        assertEquals(
            listOf(8f to 0.04f, 5f to 0.08f, 2.5f to 0.15f, 1.35f to 0.22f),
            WyvernLineGlyphRules.glowPasses,
        )
        val module = source()
        assertEquals(1, Regex("prepareFrameGeometry\\(").findAll(module).count() - 1)
        assertTrue(module.indexOf("prepareFrameGeometry(event.camera.position(), event.partialTicks)") <
            module.indexOf("WyvernLineGlyphRules.glowPasses.forEachIndexed"))
        assertTrue(module.contains("appendPreparedPass(index, thickness * width, alpha)"))
        assertTrue(module.contains("appendPreparedPass(LineGlyphBatchRenderer.LOGICAL_PASS_COUNT - 1, thickness, 1f)"))
        assertTrue(module.contains("val baseColor = ClientTheme.accentA"))
        assertFalse(module.contains("ClientTheme.accentB"))
        assertTrue(module.contains("private val frameGeometry = LineGlyphFrameGeometry()"))
        assertFalse(module.contains("drawCustomMesh"))
        assertFalse(module.contains("drawLines"))
        val manager = Files.readString(Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt"))
        assertEquals(1, Regex("ModuleLineGlyphs,").findAll(manager).count())
    }

    @Test
    fun `native line renderer has four additive draws one normal draw and one render pass`() {
        val renderer = Files.readString(Path.of(RENDERER))
        assertEquals(5, LineGlyphBatchRenderer.LOGICAL_PASS_COUNT)
        assertEquals(1, LineGlyphBatchRenderer.MAX_RENDER_PASSES)
        assertTrue(renderer.contains("RenderPipelines.LINES_SNIPPET"))
        assertTrue(renderer.contains("DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH"))
        assertTrue(renderer.contains("BlendFunction.LIGHTNING"))
        assertTrue(renderer.contains("BlendFunction.TRANSLUCENT"))
        assertTrue(renderer.contains("DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false)"))
        assertTrue(renderer.contains(".withCull(false)"))
        assertEquals(1, Regex("createRenderPass\\(").findAll(renderer).count())
        assertFalse(renderer.contains("RenderSystem.lineWidth"))
        assertFalse(renderer.contains("DEBUG_LINES"))
        assertFalse(renderer.contains("drawCustomMesh"))
    }

    @Test
    fun `line normals reject invalid and zero length segments`() {
        assertEquals(null, WyvernLineGlyphRules.lineNormal(0f, 0f, 0f, 0f, 0f, 0f))
        assertEquals(null, WyvernLineGlyphRules.lineNormal(Float.NaN, 0f, 0f, 1f, 0f, 0f))
        val normal = requireNotNull(WyvernLineGlyphRules.lineNormal(0f, 0f, 0f, 2f, 3f, 6f))
        assertTrue(normal.x.isFinite() && normal.y.isFinite() && normal.z.isFinite())
        assertClose(1f, kotlin.math.sqrt(normal.x * normal.x + normal.y * normal.y + normal.z * normal.z))
    }

    @Test
    fun `vertex budgets remain bounded for source counts`() {
        assertEquals(7_500L, WyvernLineGlyphRules.maxLineVertices(10, 26))
        assertEquals(37_500L, WyvernLineGlyphRules.maxLineVertices(50, 26))
        assertEquals(150_000L, WyvernLineGlyphRules.maxLineVertices(200, 26))
        assertEquals(0L, WyvernLineGlyphRules.maxLineVertices(200, 1))
        assertEquals(0L, WyvernLineGlyphRules.maxLineVertices(0, 26))
    }

    private fun source(): String = Files.readString(Path.of(MODULE))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.0001f) { "expected <$expected> but was <$actual>" }
    }

    private companion object {
        const val MODULE = "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleLineGlyphs.kt"
        const val RENDERER = "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/LineGlyphBatchRenderer.kt"
    }
}
