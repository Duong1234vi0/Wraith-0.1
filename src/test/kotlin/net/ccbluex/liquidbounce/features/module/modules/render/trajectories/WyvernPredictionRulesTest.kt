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
package net.ccbluex.liquidbounce.features.module.modules.render.trajectories

import net.minecraft.world.phys.Vec3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class WyvernPredictionRulesTest {

    @Test
    fun `stationary and dropped item exclusions match source`() {
        assertTrue(WyvernPredictionRules.isStationary(1.0, 2.0, 3.0, 1.0, 2.0, 3.0))
        assertFalse(WyvernPredictionRules.isStationary(1.0, 2.0, 3.0, 0.0, 2.0, 3.0))
        assertTrue(WyvernPredictionRules.droppedItemEligible(false, false, false))
        assertFalse(WyvernPredictionRules.droppedItemEligible(true, false, false))
        assertFalse(WyvernPredictionRules.droppedItemEligible(false, true, false))
        assertFalse(WyvernPredictionRules.droppedItemEligible(false, false, true))
    }

    @Test
    fun `drag coefficients and final gravity are exact`() {
        assertClose(0.99, WyvernPredictionRules.drag(WyvernPredictionEntityKind.TRIDENT, false))
        assertClose(0.99, WyvernPredictionRules.drag(WyvernPredictionEntityKind.TRIDENT, true))
        assertClose(0.99, WyvernPredictionRules.drag(WyvernPredictionEntityKind.PERSISTENT_PROJECTILE, false))
        assertClose(0.6, WyvernPredictionRules.drag(WyvernPredictionEntityKind.PERSISTENT_PROJECTILE, true))
        assertClose(0.99, WyvernPredictionRules.drag(WyvernPredictionEntityKind.THROWN_ITEM, false))
        assertClose(0.8, WyvernPredictionRules.drag(WyvernPredictionEntityKind.THROWN_ITEM, true))
        assertClose(0.8, WyvernPredictionRules.drag(WyvernPredictionEntityKind.DROPPED_ITEM, true))

        val motion = WyvernPredictionRules.updateMotion(
            Vec3(1.0, 2.0, 3.0),
            WyvernPredictionEntityKind.PERSISTENT_PROJECTILE,
            inWater = false,
            gravity = 0.05,
        )
        assertClose(0.99, motion.x)
        assertClose(1.93, motion.y)
        assertClose(2.97, motion.z)
    }

    @Test
    fun `terminal decisions cover block living in-entity and below-world conditions`() {
        assertFalse(WyvernPredictionRules.shouldTerminate(false, false, false, -128.0))
        assertTrue(WyvernPredictionRules.shouldTerminate(true, false, false, 0.0))
        assertTrue(WyvernPredictionRules.shouldTerminate(false, true, false, 0.0))
        assertTrue(WyvernPredictionRules.shouldTerminate(false, false, true, 0.0))
        assertTrue(WyvernPredictionRules.shouldTerminate(false, false, false, -128.01))
        assertEquals(300, WyvernPredictionRules.MAX_STEPS)
        assertClose(-128.0, WyvernPredictionRules.BELOW_WORLD_Y)
        assertClose(0.25, WyvernPredictionRules.ENTITY_EXPANSION)
    }

    @Test
    fun `line glow alpha and time constants follow Predictions`() {
        assertClose(2.0, WyvernPredictionRules.BASE_LINE_WIDTH.toDouble())
        assertClose(4.5, WyvernPredictionRules.INNER_GLOW_WIDTH.toDouble())
        assertClose(7.0, WyvernPredictionRules.OUTER_GLOW_WIDTH.toDouble())
        assertClose(0.0, WyvernPredictionRules.baseAlpha(0).toDouble())
        assertClose(1.0, WyvernPredictionRules.baseAlpha(25).toDouble())
        assertClose(0.3, WyvernPredictionRules.glowAlpha(50).toDouble())
        assertClose(1.0, WyvernPredictionRules.seconds(20))
        assertEquals("1.0 s", ModulePredictions.formatTime(1.0))
    }

    @Test
    fun `ClientBow pull formula and fallback match source`() {
        assertClose(0.0, WyvernPredictionRules.bowPull(0, false).toDouble())
        assertClose(1.0, WyvernPredictionRules.bowPull(0, true).toDouble())
        assertClose(1.0, WyvernPredictionRules.bowPull(20, true).toDouble())
        val half = 0.5f
        assertClose(((half * half + half * 2f) / 3f).toDouble(), WyvernPredictionRules.bowPull(10, true).toDouble())
    }

    @Test
    fun `runtime maps all source entity hierarchies impacts and collision order`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/trajectories/ModulePredictions.kt")
        listOf(
            "is ThrownTrident -> WyvernPredictionEntityKind.TRIDENT",
            "is AbstractArrow -> WyvernPredictionEntityKind.PERSISTENT_PROJECTILE",
            "is ThrowableItemProjectile -> WyvernPredictionEntityKind.THROWN_ITEM",
            "is ItemEntity -> WyvernPredictionEntityKind.DROPPED_ITEM",
        ).forEach { assertTrue(module.contains(it), it) }
        listOf(
            "is ItemEntity -> entity.stack.copy()",
            "is ThrowableItemProjectile -> entity.item.copy()",
            "is AbstractArrow -> entity.pickupItemStackOrigin.copy()",
        ).forEach { assertTrue(module.contains(it), it) }
        assertTrue(module.indexOf("position = position.add(motion)") < module.indexOf("motion = WyvernPredictionRules.updateMotion"))
        assertTrue(module.indexOf("motion = WyvernPredictionRules.updateMotion") < module.indexOf("val blockResult = level.clip"))
        assertTrue(module.contains("candidate.boundingBox.inflate(WyvernPredictionRules.ENTITY_EXPANSION).clip"))
        assertTrue(module.contains("ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE"))
        assertTrue(module.contains("RenderSystem.disableDepthTest()"))
    }

    @Test
    fun `Prediction keeps one registration and only ClientBow settings`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/trajectories/ModulePredictions.kt")
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt")
        assertEquals(1, Regex("ModulePredictions,").findAll(manager).count())
        assertTrue(module.contains("\"Prediction\""))
        assertTrue(module.contains("boolean(\"Markers\", true)"))
        assertTrue(module.contains("boolean(\"LandingBox\", true)"))
        assertTrue(module.contains("float(\"Thickness\", 2f, 0.5f..5f)"))
        listOf("boolean(\"Pearl\"", "boolean(\"Item\"", "boolean(\"Tag\"").forEach {
            assertFalse(module.contains(it), it)
        }
        assertTrue(module.contains("CLIENT_BOW_STEPS = 100"))
        assertTrue(module.contains("CLIENT_BOW_DRAG = 0.99"))
        assertTrue(module.contains("CLIENT_BOW_GRAVITY = 0.05"))
        assertTrue(module.contains("if (index % 3 == 0)"))
        assertTrue(module.contains("context.pose().scale(0.5f, 0.5f)"))
        assertFalse(module.contains("context.matrices.push"))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertClose(expected: Double, actual: Double) {
        assertTrue(abs(expected - actual) < 0.0001) { "expected <$expected> but was <$actual>" }
    }
}
