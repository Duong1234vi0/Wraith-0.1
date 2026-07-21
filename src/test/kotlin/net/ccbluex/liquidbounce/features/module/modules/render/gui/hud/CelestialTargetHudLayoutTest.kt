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
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class CelestialTargetHudLayoutTest {

    @Test
    fun `Celestial geometry follows supplied branch`() {
        val geometry = CelestialTargetHudLayout.geometry(10f, 20f, 8f)

        assertClose(170f / 1.5f, geometry.panel.width)
        assertClose(57f / 1.5f, geometry.panel.height)
        assertClose(32f, geometry.head.width)
        assertClose(32f, geometry.head.height)
        assertEquals(114, CelestialTargetHudLayout.EDITOR_WIDTH)
        assertEquals(38, CelestialTargetHudLayout.EDITOR_HEIGHT)
        assertClose(4f, CelestialTargetHudLayout.SPACING)
        assertClose(geometry.panel.y + 3f, geometry.head.y)
        assertClose(geometry.panel.y + (geometry.panel.height - geometry.head.height) * 0.5f, geometry.head.y)
        assertClose(geometry.panel.x + 38f, geometry.textX)
        assertClose(geometry.panel.y + 6f, geometry.nameY)
        assertClose(geometry.panel.y + 20f, geometry.healthTextY)
        assertClose(geometry.panel.x + 37f, geometry.healthTrack.x)
        assertClose(geometry.panel.y + 27.5f, geometry.healthTrack.y)
        assertClose(73.333336f, geometry.healthTrack.width)
        assertClose(8f, geometry.healthTrack.height)
        assertClose(7.5f, geometry.healthFillHeight)
        assertTrue(geometry.nameClip.x + geometry.nameClip.width <= geometry.panel.x + geometry.panel.width)
    }

    @Test
    fun `health ratios and fill widths are finite and bounded`() {
        assertClose(0.5f, CelestialTargetHudLayout.healthRatio(10f, 20f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(0f, 20f))
        assertClose(1f, CelestialTargetHudLayout.healthRatio(30f, 20f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(10f, 0f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(10f, -20f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(Float.NaN, 20f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(Float.POSITIVE_INFINITY, 20f))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(10f, Float.NaN))
        assertClose(0f, CelestialTargetHudLayout.healthRatio(10f, Float.POSITIVE_INFINITY))

        val trackWidth = CelestialTargetHudLayout.geometry(0f, 0f, 8f).healthTrack.width
        listOf(-1f, 0f, 0.5f, 1f, 2f, Float.NaN, Float.POSITIVE_INFINITY).forEach { ratio ->
            val fill = CelestialTargetHudLayout.fillWidth(trackWidth, ratio)
            assertTrue(fill.isFinite(), ratio.toString())
            assertTrue(fill in 0f..trackWidth, ratio.toString())
        }
        assertClose(trackWidth * 0.5f, CelestialTargetHudLayout.fillWidth(trackWidth, 0.5f))
    }

    @Test
    fun `health animation moves both directions and resets between targets`() {
        val animation = CelestialTargetHealthAnimation()
        animation.resetForTarget(20f, 20f)
        val damaged = animation.update(10f, 20f)
        assertTrue(damaged.health in 10f..<20f)
        assertTrue(damaged.ratio in 0.5f..<1f)

        val healed = animation.update(20f, 20f)
        assertTrue(healed.health > damaged.health)
        assertTrue(healed.ratio > damaged.ratio)

        animation.resetForTarget(4f, 40f)
        assertClose(4f, animation.snapshot().health)
        assertClose(0.1f, animation.snapshot().ratio)

        animation.reset()
        assertClose(0f, animation.snapshot().health)
        assertClose(0f, animation.snapshot().ratio)
    }

    @Test
    fun `Target HUD registration and lifecycle remain singular and Wraith owned`() {
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt")
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt")
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/SilentHudElements.kt")

        assertEquals(1, Regex("CelestialTargetHudElement\\(\\)").findAll(manager).count())
        assertFalse(manager.contains("SilentTargetHudElement()"))
        listOf(
            "SilentNotificationElement()",
            "SilentWatermarkElement()",
            "SilentHotKeysElement()",
            "SilentInformationElement()",
            "SilentStaffListElement()",
        ).forEach { assertEquals(1, Regex(Regex.escape(it)).findAll(manager).count(), it) }

        assertTrue(module.contains("boolean(\"TargetHUD\", true)"))
        assertTrue(module.contains("handler<WorldChangeEvent>"))
        assertTrue(module.contains("handler<DisconnectEvent>"))
        assertTrue(module.contains("HudManager.resetTransient()"))
        assertTrue(renderer.contains("ModuleKillAura.targetTracker.target"))
        assertTrue(renderer.contains("targetHoldVisible"))
        assertTrue(renderer.contains("mc.screen is ChatScreen || mc.screen is HudScreen"))
        assertTrue(renderer.contains("ModuleNameProtect.replace"))
    }

    @Test
    fun `old Silent visuals are absent and Celestial primitives are present`() {
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/SilentHudElements.kt")
        val fontManager = source("src/main/kotlin/net/ccbluex/liquidbounce/render/FontManager.kt")
        val hudElement = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudElement.kt")

        listOf("drawHealthCircle", "drawEquipment", "distanceValue", "absorptionRatio", "roundHalf").forEach {
            assertFalse(renderer.contains(it), it)
        }
        listOf("shaderGlow", "roundedOutline", "HP: %.1f", "healthTrack", "drawRoundedPlayerSkinFace").forEach {
            assertTrue(renderer.contains(it), it)
        }
        assertFalse(fontManager.contains("celestial", ignoreCase = true))
        assertFalse(hasCelestialFontResource())
        assertTrue(hudElement.contains("context.pose().pushMatrix()"))
        assertTrue(hudElement.contains("width * scale"))
        assertTrue(hudElement.contains("height * scale"))
        listOf(0.7f, 1f, 1.2f).forEach { scale ->
            assertClose(CelestialTargetHudLayout.EDITOR_WIDTH * scale, 114f * scale)
            assertClose(CelestialTargetHudLayout.EDITOR_HEIGHT * scale, 38f * scale)
        }
    }

    private fun hasCelestialFontResource(): Boolean {
        val root = Path.of("src/main/resources")
        return Files.walk(root).use { paths ->
            paths.anyMatch { it.fileName.toString().contains("celestial", ignoreCase = true) }
        }
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.001f) {
            "expected <$expected> but was <$actual>"
        }
    }
}
