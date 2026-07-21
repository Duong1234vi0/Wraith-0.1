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
package net.ccbluex.liquidbounce.features.module.modules.render.killeffect

import net.minecraft.world.phys.Vec3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class KillEffectStateTest {

    @Test
    fun `attack tracking does not spawn immediately and replaces by entity id`() {
        val tracking = KillEffectTracking<String>()
        tracking.track(7, "first", alive = true, nowMs = 0L)
        assertEquals(1, tracking.size)
        assertTrue(tracking.process(1_000L) { KillTrackedState.ALIVE }.isEmpty())

        tracking.track(7, "replacement", alive = true, nowMs = 3_000L)
        assertEquals(1, tracking.size)
        assertTrue(tracking.process(6_500L) { KillTrackedState.ALIVE }.isEmpty())
        assertEquals(1, tracking.size)
        assertTrue(tracking.process(7_001L) { KillTrackedState.ALIVE }.isEmpty())
        assertEquals(0, tracking.size)

        tracking.track(8, "ignored", alive = false, nowMs = 0L)
        assertEquals(0, tracking.size)
    }

    @Test
    fun `death spawns once while removal and timeout do not`() {
        val tracking = KillEffectTracking<String>()
        tracking.track(1, "dead", alive = true, nowMs = 0L)
        assertEquals(listOf("dead"), tracking.process(4_001L) { KillTrackedState.DEAD })
        assertTrue(tracking.process(4_002L) { KillTrackedState.DEAD }.isEmpty())

        tracking.track(2, "removed", alive = true, nowMs = 0L)
        assertTrue(tracking.process(1L) { KillTrackedState.REMOVED }.isEmpty())
        assertEquals(0, tracking.size)
        assertEquals(4_000L, KillEffectRules.TRACK_TIMEOUT_MS)
    }

    @Test
    fun `lightning generation descends and clamps exactly to target`() {
        val target = Vec3(10.0, 20.0, -5.0)
        val values = generateSequence { sequenceOf(0.0, 0.5, 1.0) }.flatten().iterator()
        val points = KillEffectRules.generateLightning(target) { values.next() }

        assertEquals(target.add(0.0, 200.0, 0.0), points.first())
        assertEquals(target, points.last())
        assertTrue(points.size > 2)
        points.zipWithNext().forEach { (start, end) -> assertTrue(end.y < start.y) }
        points.drop(1).dropLast(1).forEachIndexed { index, point ->
            val previous = points[index]
            assertTrue(abs(point.x - previous.x) <= 0.400001)
            assertTrue(abs(point.z - previous.z) <= 0.400001)
        }
    }

    @Test
    fun `effect lifetime and alpha curve match source`() {
        assertEquals(800L, KillEffectRules.EFFECT_DURATION_MS)
        assertClose(0f, KillEffectRules.progress(1_000L, 1_000L))
        assertClose(0.5f, KillEffectRules.progress(1_400L, 1_000L))
        assertClose(1f, KillEffectRules.progress(1_800L, 1_000L))
        assertClose(0f, KillEffectRules.alpha(0f))
        assertClose(1f, KillEffectRules.alpha(0.15f))
        assertTrue(KillEffectRules.alpha(0.5f) < 1f)
        assertClose(0f, KillEffectRules.alpha(1f))
    }

    @Test
    fun `render passes preserve additive glow and normal core constants`() {
        assertEquals(
            listOf(
                KillEffectRenderPass(40f, 40, true),
                KillEffectRenderPass(24f, 80, true),
                KillEffectRenderPass(12f, 130, true),
                KillEffectRenderPass(6f, 190, true),
                KillEffectRenderPass(4.4f, 255, false),
            ),
            KillEffectRules.renderPasses,
        )
        assertEquals(4, KillEffectRules.renderPasses.count { it.additive })
        assertFalse(KillEffectRules.renderPasses.last().additive)
        assertEquals(5, KillEffectBatchRenderer.LOGICAL_PASS_COUNT)
        assertEquals(1, KillEffectBatchRenderer.MAX_RENDER_PASSES)
    }

    @Test
    fun `line normals are finite and zero length segments are rejected`() {
        assertEquals(null, KillEffectRules.lineNormal(1f, 2f, 3f, 1f, 2f, 3f))
        assertEquals(null, KillEffectRules.lineNormal(Float.NaN, 0f, 0f, 1f, 0f, 0f))
        val normal = requireNotNull(KillEffectRules.lineNormal(0f, 0f, 0f, 0f, 3f, 4f))
        assertTrue(normal.x.isFinite() && normal.y.isFinite() && normal.z.isFinite())
        assertClose(1f, kotlin.math.sqrt(normal.x * normal.x + normal.y * normal.y + normal.z * normal.z))
    }

    @Test
    fun `renderer batches all segments into five native line draws`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/killeffect/ModuleKillEffect.kt")
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/killeffect/KillEffectBatchRenderer.kt")
        assertFalse(module.contains("drawLines("))
        assertTrue(module.contains("KillEffectBatchRenderer.appendLine("))
        assertTrue(module.contains("KillEffectBatchRenderer.flush("))
        assertTrue(renderer.contains("DefaultVertexFormat.POSITION_COLOR_NORMAL_LINE_WIDTH"))
        assertTrue(renderer.contains("RenderPipelines.LINES_SNIPPET"))
        assertTrue(renderer.contains("BlendFunction.LIGHTNING"))
        assertTrue(renderer.contains("BlendFunction.TRANSLUCENT"))
        assertEquals(1, Regex("createRenderPass\\(").findAll(renderer).count())
        assertEquals(1, Regex("drawIndexed\\(").findAll(renderer).count())
        assertTrue(renderer.indexOf("uploaded += upload") < renderer.indexOf("createRenderPass("))
    }

    @Test
    fun `module uses living deaths center height local thunder and cleanup`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/killeffect/ModuleKillEffect.kt")
        assertTrue(module.contains("event.entity as? LivingEntity"))
        assertTrue(module.contains("tracked.track(living.id, living, living.isAlive"))
        assertTrue(module.contains("entity.position().add(0.0, entity.bbHeight * 0.5, 0.0)"))
        assertTrue(module.contains("SoundEvents.LIGHTNING_BOLT_THUNDER"))
        assertTrue(module.contains("SoundSource.WEATHER"))
        assertTrue(module.contains("1f,\n            1f,\n            false"))
        assertTrue(module.contains("handler<WorldChangeEvent> { clearRuntime() }"))
        assertTrue(module.contains("handler<DisconnectEvent> { clearRuntime() }"))
        assertTrue(module.contains("KillEffectBatchRenderer.flush(event.matrixStack.last().pose())"))
        assertTrue(module.contains("private val effects = ArrayList<LightningEffect>()"))
        assertFalse(module.contains("Clientbound"))
        assertFalse(module.contains("LightningBolt"))
        assertFalse(module.contains(" by boolean("))
        assertFalse(module.contains(" by float("))
        assertFalse(module.contains(" by int("))
        assertFalse(module.contains("CopyOnWriteArrayList"))
    }

    @Test
    fun `KillEffect is registered once and FragEffects remains independent`() {
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt")
        val frag = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleFragEffects.kt")
        assertEquals(1, Regex("ModuleKillEffect,").findAll(manager).count())
        assertEquals(1, Regex("ModuleFragEffects,").findAll(manager).count())
        assertFalse(frag.contains("ModuleKillEffect"))
        assertFalse(frag.contains("KillEffectRules"))
    }

    @Test
    fun `Wyvern has KillEffect but no HitEffect and Wraith HitEffect remains independent`() {
        val wyvernRoot = Path.of("WorkSpace/Client/wyvern-dlc/src/main/java")
        val wyvernFiles = Files.walk(wyvernRoot).use { stream ->
            stream.filter(Files::isRegularFile).map { it.fileName.toString() }.toList()
        }
        assertTrue("KillEffect.java" in wyvernFiles)
        assertFalse(wyvernFiles.any { it.equals("HitEffect.java", ignoreCase = true) })
        val hitEffect = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHitEffect.kt")
        assertTrue(hitEffect.contains("Xyeta/Wild HitEffect port"))
        assertFalse(hitEffect.contains("KillEffectRules"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.0001f) { "expected <$expected> but was <$actual>" }
    }
}
