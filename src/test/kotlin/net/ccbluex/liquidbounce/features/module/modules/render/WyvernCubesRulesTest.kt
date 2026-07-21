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
import java.security.MessageDigest
import kotlin.math.abs

class WyvernCubesRulesTest {

    @Test
    fun `settings and source constants match Wyvern`() {
        val module = source(MODULE)
        assertTrue(module.contains("enumChoice(\"Animation\", Animation.SCATTER)"))
        assertTrue(module.contains("enumChoice(\"Shape\", Shape.CUBES)"))
        assertTrue(module.contains("int(\"Count\", 30, 5..100)"))
        assertTrue(module.contains("float(\"Size\", 1.0F, 0.1F..3.0F)"))
        assertTrue(module.contains("float(\"Speed\", 1.0F, 0.1F..5.0F)"))
        assertFalse(module.contains("LifeTime"))
        assertEquals(12f, WyvernCubesRules.SPAWN_RADIUS)
        assertEquals(0.18f, WyvernCubesRules.BASE_SIZE)
        assertEquals(0.25f, WyvernCubesRules.BASE_SPEED)
        assertEquals(1.7f, WyvernCubesRules.GLOW_MULTIPLIER)
        assertEquals(900.0, WyvernCubesRules.MAX_RENDER_DISTANCE_SQ)
    }

    @Test
    fun `source cadence alpha and culling decisions are pure`() {
        assertFalse(WyvernCubesRules.shouldUpdate(1))
        assertTrue(WyvernCubesRules.shouldUpdate(2))
        assertClose(0f, WyvernCubesRules.alpha(100, 100))
        assertClose(0.25f * 0.75f, WyvernCubesRules.alpha(5, 20))
        assertTrue(WyvernCubesRules.isVisible(900.0, -1.0))
        assertFalse(WyvernCubesRules.isVisible(900.01, 1.0))
        assertFalse(WyvernCubesRules.isVisible(1.0, -1.01))
    }

    @Test
    fun `dash segmentation and render passes match source`() {
        assertEquals(2, WyvernCubesRules.dashCount(1f, 0.3f, 0.25f))
        assertEquals(0, WyvernCubesRules.dashCount(1f, 0f, 0.25f))
        assertEquals(listOf(10f, 6f, 3.5f), WyvernCubesRules.glowScales)
        assertEquals(listOf(0.06f, 0.14f, 0.25f), WyvernCubesRules.glowAlphas)
        val module = source(MODULE)
        assertTrue(module.contains("p.renderAlpha * 0.4f"))
        assertTrue(module.contains("val dashLen = s * 0.3F"))
        assertTrue(module.contains("val gapLen = s * 0.25F"))
        assertTrue(module.contains("shape == Shape.CUBES"))
    }

    @Test
    fun `production geometry is allocation bounded and submitted as three local batches`() {
        val module = source(MODULE)
        val renderer = source(RENDERER)
        assertFalse(module.contains("CubesDash"))
        assertFalse(module.contains("dashedIntervals("))
        assertFalse(module.contains("floatArrayOf("))
        assertFalse(module.contains("arrayOf(\n            // front"))
        assertTrue(module.contains("var position = 0f"))
        assertTrue(module.contains("position += dashLen + gapLen"))
        assertTrue(module.contains("private val localMatrix = Matrix4f()"))
        assertFalse(module.contains("IMPULSE_DEBOUNCE_MS"))
        assertFalse(module.contains("lastImpulseAt"))
        assertEquals(3, CubesBatchRenderer.LOGICAL_BATCH_COUNT)
        assertEquals(1, CubesBatchRenderer.MAX_RENDER_PASSES)
        assertTrue(renderer.contains("DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH"))
        assertTrue(renderer.contains("BlendFunction.LIGHTNING"))
        assertTrue(renderer.contains("BlendFunction.TRANSLUCENT"))
        assertEquals(1, Regex("createRenderPass\\(").findAll(renderer).count())
        assertTrue(renderer.indexOf("buildAndUpload(glowBuilder") < renderer.indexOf("createRenderPass("))
        assertTrue(renderer.contains("mc.textureManager.getTexture(glowTexture)"))
    }

    @Test
    fun `vertex budgets remain finite at source count limits and minimum size`() {
        val perParticle = WyvernCubesRules.cubeVerticesPerParticle(WyvernCubesRules.BASE_SIZE * 0.1f)
        assertTrue(perParticle in 1..256)
        for (count in listOf(5, 30, 100)) {
            val vertices = perParticle.toLong() * count
            assertTrue(vertices in 1..25_600)
        }
        assertEquals(0, WyvernCubesRules.cubeVerticesPerParticle(0f) - 36)
    }

    @Test
    fun `movement lifetime maintenance and impulse are source backed`() {
        val module = source(MODULE)
        assertTrue(module.contains("minOf(target - currentSize, 5)"))
        assertTrue(module.contains("260 + random.nextInt(220)"))
        assertTrue(module.contains("420 + random.nextInt(420)"))
        assertTrue(module.contains("p.wobblePhase += 0.06F * spd"))
        assertTrue(module.contains("p.vx *= 0.995F"))
        assertTrue(module.contains("WyvernCubesRules.RAY_DISTANCE_SQ"))
        assertEquals(0.08, WyvernCubesRules.IMPULSE_FORCE)
        assertEquals(0.02, WyvernCubesRules.IMPULSE_VERTICAL)
    }

    @Test
    fun `Wyvern glow asset replaces Wonderful and shader prepass`() {
        val module = source(MODULE)
        val mixin = source("src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinWorldRenderer.java")
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt")
        val glow = Path.of("src/main/resources/assets/liquidbounce/icons/glow.png")
        assertTrue(Files.exists(glow))
        assertEquals("677c6878e4c9c83bb783e680be411542d325cd302816c2a8e6e64176410e759a", sha256(glow))
        assertFalse(Files.exists(Path.of("src/main/resources/resources/liquidbounce/wonderful/particle/bloom.png")))
        assertTrue(module.contains("Identifier.fromNamespaceAndPath(\"liquidbounce\", \"icons/glow.png\")"))
        assertFalse(module.contains("renderShaderWorldPass"))
        assertFalse(module.contains("IrisShaderPack"))
        assertFalse(mixin.contains("ModuleCubes.renderShaderWorldPass"))
        assertEquals(1, Regex("ModuleCubes,").findAll(manager).count())
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun sha256(path: Path): String = MessageDigest.getInstance("SHA-256")
        .digest(Files.readAllBytes(path)).joinToString("") { "%02x".format(it) }

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.0001f) { "expected <$expected> but was <$actual>" }
    }

    private companion object {
        const val MODULE = "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleCubes.kt"
        const val RENDERER = "src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/CubesBatchRenderer.kt"
    }
}
