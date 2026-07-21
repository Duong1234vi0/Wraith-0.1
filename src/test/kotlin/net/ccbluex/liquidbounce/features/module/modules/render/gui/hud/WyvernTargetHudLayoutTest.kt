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

class WyvernTargetHudLayoutTest {

    @Test
    fun `source variants expose exact bounds and geometry`() {
        assertEquals(listOf("Classic", "V2"), WyvernTargetHudStyle.entries.map { it.choiceName })

        val classic = WyvernTargetHudLayout.geometry(WyvernTargetHudStyle.CLASSIC, 10f, 20f)
        assertRect(classic.panel, 10f, 20f, 100f, 38f)
        assertRect(classic.head, 14.5f, 22f, 24.5f, 24.5f)
        assertClose(42.2f, classic.textX)
        assertClose(26.7f, classic.nameY)
        assertClose(27.2f, classic.healthTextY)
        assertRect(classic.bar, 15f, 49f, 90f, 5f)

        val v2 = WyvernTargetHudLayout.geometry(WyvernTargetHudStyle.V2, 10f, 20f)
        assertRect(v2.panel, 10f, 20f, 100f, 36f)
        assertRect(v2.head, 15f, 25f, 26f, 26f)
        assertClose(46f, v2.textX)
        assertClose(26.75f, v2.nameY)
        assertClose(36.25f, v2.healthTextY)
        assertRect(v2.bar, 45.5f, 45.5f, 59f, 5.5f)
    }

    @Test
    fun `health and absorption normalization stays finite and bounded`() {
        assertClose(10f, WyvernTargetHudLayout.sanitizeHealth(10f, 20f))
        assertClose(0f, WyvernTargetHudLayout.sanitizeHealth(-1f, 20f))
        assertClose(20f, WyvernTargetHudLayout.sanitizeHealth(30f, 20f))
        assertClose(0f, WyvernTargetHudLayout.sanitizeHealth(Float.NaN, 20f))
        assertClose(0f, WyvernTargetHudLayout.sanitizeHealth(Float.POSITIVE_INFINITY, 20f))
        assertClose(0f, WyvernTargetHudLayout.sanitizeHealth(10f, 0f))

        assertClose(0.5f, WyvernTargetHudLayout.ratio(10f, 20f))
        assertClose(1f, WyvernTargetHudLayout.ratio(40f, 20f))
        assertClose(0f, WyvernTargetHudLayout.ratio(Float.NaN, 20f))
        assertClose(0f, WyvernTargetHudLayout.ratio(10f, Float.POSITIVE_INFINITY))
        assertClose(45f, WyvernTargetHudLayout.width(90f, 0.5f))
        assertClose(90f, WyvernTargetHudLayout.width(90f, 2f))
        assertClose(0f, WyvernTargetHudLayout.width(90f, Float.NaN))
    }

    @Test
    fun `damage lag follows source durations and healing catches up immediately`() {
        val animation = WyvernTargetHealthAnimation()
        animation.reset(20f, 20f, 0f)

        animation.update(10f, 20f, 4f, 1_000L)
        val currentDone = animation.update(10f, 20f, 4f, 1_250L)
        assertClose(0.5f, currentDone.currentRatio)
        assertTrue(currentDone.outdatedRatio > currentDone.currentRatio)
        assertClose(0.2f, currentDone.absorptionRatio)

        val delayedDone = animation.update(10f, 20f, 4f, 1_900L)
        assertClose(0.5f, delayedDone.outdatedRatio)
        animation.update(20f, 20f, 0f, 2_000L)
        val healed = animation.update(20f, 20f, 0f, 2_250L)
        assertClose(1f, healed.currentRatio)
        assertClose(1f, healed.outdatedRatio)
    }

    @Test
    fun `visibility and retention use Wyvern timing and easing`() {
        assertEquals(250L, WyvernTargetHudLayout.VISIBILITY_MS)
        assertEquals(1_300L, WyvernTargetHudLayout.RETAIN_IN_MS)
        assertEquals(2_200L, WyvernTargetHudLayout.RETAIN_OUT_MS)
        assertEquals(250L, WyvernTargetHudLayout.HEALTH_MS)
        assertEquals(650L, WyvernTargetHudLayout.OUTDATED_HEALTH_MS)
        assertEquals(250L, WyvernTargetHudLayout.ABSORPTION_MS)

        val retention = WyvernFloatAnimation(1_300L, WyvernEasing.CIRC_OUT)
        retention.update(1f, 1_000L)
        assertTrue(retention.current(1_650L) > 0.8f)
        assertClose(1f, retention.current(2_300L))
        retention.update(0f, 2_300L, 2_200L, WyvernEasing.CIRC_OUT)
        assertTrue(retention.current(3_400L) > 0f)
        assertClose(0f, retention.current(4_500L))
    }

    @Test
    fun `registration style lifecycle name and item paths are source constrained`() {
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt")
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt")
        val renderer = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/SilentHudElements.kt")
        val layout = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/WyvernTargetHudLayout.kt")

        assertEquals(1, Regex("WyvernTargetHudElement\\(\\)").findAll(manager).count())
        assertFalse(manager.contains("CelestialTargetHud"))
        assertTrue(module.contains("boolean(\"TargetHUD\", true)"))
        assertTrue(module.contains("enumChoice(\"TargetHUDStyle\", WyvernTargetHudStyle.CLASSIC)"))
        assertTrue(module.contains("handler<WorldChangeEvent>"))
        assertTrue(module.contains("handler<DisconnectEvent>"))
        assertTrue(module.contains("HudManager.resetTransient()"))
        assertTrue(renderer.contains("ModuleKillAura.targetTracker.target"))
        assertTrue(renderer.contains("mc.screen is ChatScreen || mc.screen is HudScreen"))
        assertTrue(renderer.contains("ModuleNameProtect.replace"))
        assertTrue(renderer.contains("getActualHealth(fromScoreboard = true)"))
        assertTrue(renderer.contains("context.pose().pushMatrix()"))
        assertTrue(renderer.contains("context.pose().translate"))
        assertTrue(renderer.contains("context.pose().scale"))
        assertFalse(renderer.contains("context.matrices.push"))

        val orderedItems = listOf(
            "player.mainHandItem",
            "player.offhandItem",
            "EquipmentSlot.HEAD",
            "EquipmentSlot.CHEST",
            "EquipmentSlot.LEGS",
            "EquipmentSlot.FEET",
        )
        assertEquals(orderedItems.sortedBy(layout::indexOf), orderedItems)
        assertTrue(layout.contains(".filterNot(ItemStack::isEmpty)"))
        assertTrue(renderer.contains("context.drawItem(stack, 0, 0)"))
        assertTrue(renderer.contains("context.drawStackOverlay"))
    }

    @Test
    fun `Celestial implementation and geometry are removed without changing sibling widgets`() {
        val hudDir = Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud")
        val sources = Files.walk(hudDir).use { paths ->
            paths.filter { Files.isRegularFile(it) && it.toString().endsWith(".kt") }
                .toList()
                .joinToString("\n") { Files.readString(it) }
        }
        assertFalse(sources.contains("CelestialTargetHud"))
        assertFalse(Files.exists(hudDir.resolve("CelestialTargetHudLayout.kt")))

        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt")
        listOf(
            "SilentNotificationElement()",
            "SilentWatermarkElement()",
            "SilentHotKeysElement()",
            "SilentInformationElement()",
            "SilentStaffListElement()",
        ).forEach { assertEquals(1, Regex(Regex.escape(it)).findAll(manager).count(), it) }
        assertEquals("abcdefgh", WyvernTargetHudLayout.truncateName("abcdefghij", WyvernTargetHudStyle.CLASSIC))
        assertEquals("abcdefghi", WyvernTargetHudLayout.truncateName("abcdefghij", WyvernTargetHudStyle.V2))
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertRect(rect: WyvernTargetHudRect, x: Float, y: Float, width: Float, height: Float) {
        assertClose(x, rect.x)
        assertClose(y, rect.y)
        assertClose(width, rect.width)
        assertClose(height, rect.height)
    }

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.001f) { "expected <$expected> but was <$actual>" }
    }
}
