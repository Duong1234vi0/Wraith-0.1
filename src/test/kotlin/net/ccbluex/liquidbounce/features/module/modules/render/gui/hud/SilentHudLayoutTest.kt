/*
 * This file is part of LiquidBounce (https://liquidbounce.net)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.gui.hud

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

class SilentHudLayoutTest {

    @Test
    fun `Silent Hud java registration order is recorded with media blocker`() {
        assertEquals(
            listOf("Media Player", "Notifications", "Watermark", "HotKeys", "Information", "Target HUD", "Staff List"),
            SilentHudLayout.silentHudRegistrationOrder
        )
        assertEquals(
            listOf("Notifications", "Watermark", "HotKeys", "Information", "TargetHUD", "StaffList"),
            SilentHudLayout.implementedDescriptors.map { it.name }
        )
        assertFalse(SilentHudLayout.mediaPlayerDescriptor.implemented)
        assertTrue(SilentHudLayout.MEDIA_PLAYER_BLOCKER.contains("DBus"))
    }

    @Test
    fun `default Silent HUD positions and drag flags follow source renderers`() {
        val descriptors = SilentHudLayout.implementedDescriptors.associateBy { it.name }

        descriptors.getValue("Watermark").assertGeometry(10, 6, false, false, false)
        descriptors.getValue("HotKeys").assertGeometry(30, 30, true, true, true)
        descriptors.getValue("Information").assertGeometry(5, 5, false, false, false)
        descriptors.getValue("TargetHUD").assertGeometry(10, 80, true, true, true)
        descriptors.getValue("StaffList").assertGeometry(30, 150, true, true, true)
        descriptors.getValue("Notifications").assertGeometry(0, 200, true, false, true)
    }

    @Test
    fun `target hold expires at Silent source duration`() {
        assertTrue(SilentHudLayout.targetHoldVisible(1500L, 1000L))
        assertFalse(SilentHudLayout.targetHoldVisible(1501L, 1000L))
    }

    @Test
    fun `notification stacking is deterministic and non overlapping`() {
        assertClose(200f, SilentHudLayout.notificationY(200f, 0, 18f, 3f))
        assertClose(221f, SilentHudLayout.notificationY(200f, 1, 18f, 3f))
        assertClose(242f, SilentHudLayout.notificationY(200f, 2, 18f, 3f))
    }

    @Test
    fun `small screen clamp avoids invalid ranges`() {
        assertEquals(0f to 0f, SilentHudLayout.smallScreenClamp(40f, 50f, 120f, 60f, 80f, 40f))
        assertEquals(10f to 5f, SilentHudLayout.smallScreenClamp(10f, 5f, 20f, 10f, 80f, 40f))
    }

    @Test
    fun `HUD manager registers Silent elements once and no old Wyvern variants`() {
        val manager = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt")

        listOf(
            "SilentNotificationElement()",
            "SilentWatermarkElement()",
            "SilentHotKeysElement()",
            "SilentInformationElement()",
            "CelestialTargetHudElement()",
            "SilentStaffListElement()",
        ).forEach { token ->
            assertEquals(1, Regex(Regex.escape(token)).findAll(manager).count(), token)
        }
        listOf("Wyvern", "WatermarkV2", "KeybindsV2", "TargetHUDV2", "Potions", "Inventory").forEach {
            assertFalse(manager.contains(it), it)
        }
    }

    @Test
    fun `HUD source preserves Silent filtering animation and content driven width rules`() {
        val source = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/SilentHudElements.kt")

        assertTrue(source.contains(".filter { it.enabled && !it.hidden && !it.bind.isUnbound }"))
        assertTrue(source.contains("states.entries.removeIf"))
        assertTrue(source.contains("val contentWidth = sections.sumOf"))
        assertTrue(source.contains("hypot(motion.x, motion.z).toFloat() * 20f"))
        assertTrue(source.contains("CelestialTargetHealthAnimation"))
        assertFalse(source.contains("drawHealthCircle"))
        assertFalse(source.contains("drawEquipment"))
        assertFalse(source.contains("distanceValue"))
        assertTrue(source.contains("entries.removeAll"))
        assertTrue(source.contains("override fun resetState()"))
    }

    @Test
    fun `ModuleHUD exposes Silent toggles and no old Hud mode branches`() {
        val module = source("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt")

        listOf("Watermark", "Notifications", "HotKeys", "Information", "TargetHUD", "StaffList").forEach {
            assertTrue(module.contains("boolean(\"$it\", true)"), it)
        }
        listOf("HudMode", "Hud1", "Hud2", "Material", "Density", "Potions", "Inventory", "isWyvernElementVisible").forEach {
            assertFalse(module.contains(it), it)
        }
        assertTrue(module.contains("HotbarStatusHud.render("))
    }

    @Test
    fun `HUD port does not add desktop media or clipboard dependencies`() {
        val hudSources = Files.walk(Path.of("src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud"))
            .filter { Files.isRegularFile(it) && it.toString().endsWith(".kt") }
            .use { paths -> paths.toList().joinToString("\n") { Files.readString(it) } }
        val build = source("build.gradle.kts")

        assertFalse(build.contains("MediaPlayerInfo"))
        assertFalse(hudSources.contains("java.awt"))
        assertFalse(hudSources.contains("Runtime.getRuntime"))
        assertFalse(hudSources.contains("ThemeManager"))
        assertFalse(hudSources.contains("MediaPlayerInfo.jar"))
        assertTrue(hudSources.contains("keyboardHandler.clipboard"))
        assertTrue(hudSources.contains("ModuleKillAura.targetTracker.target"))
    }

    private fun SilentHudElementDescriptor.assertGeometry(
        expectedX: Int,
        expectedY: Int,
        expectedDraggable: Boolean,
        expectedAllowX: Boolean,
        expectedAllowY: Boolean,
    ) {
        assertEquals(expectedX, x)
        assertEquals(expectedY, y)
        assertEquals(expectedDraggable, draggable)
        assertEquals(expectedAllowX, allowDragX)
        assertEquals(expectedAllowY, allowDragY)
        assertTrue(width > 0)
        assertTrue(height > 0)
    }

    private fun source(path: String): String = Files.readString(Path.of(path))

    private fun assertClose(expected: Float, actual: Float) {
        assertTrue(abs(expected - actual) < 0.001f) {
            "expected <$expected> but was <$actual>"
        }
    }
}
