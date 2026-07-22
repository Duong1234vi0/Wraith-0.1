package net.ccbluex.liquidbounce.features.module.modules.render.gui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CompactModuleCardStyleTest {
    @Test
    fun `selection changes only the name treatment`() {
        val normal = CompactModuleCardStyles.style(enabled = false, selected = false, hovered = false)
        val selected = CompactModuleCardStyles.style(enabled = false, selected = true, hovered = false)
        assertNotEquals(normal.nameColor, selected.nameColor)
        assertEquals(normal.fillColor, selected.fillColor)
        assertEquals(normal.outlineColor, selected.outlineColor)
        assertEquals(normal.outlineWidth, selected.outlineWidth)
        assertEquals(normal.bindColor, selected.bindColor)
        assertEquals(normal.descriptionColor, selected.descriptionColor)
        assertEquals(normal.settingsControlColor, selected.settingsControlColor)
    }

    @Test
    fun `enabled visuals remain independent from selection`() {
        val enabled = CompactModuleCardStyles.style(enabled = true, selected = false, hovered = false)
        val enabledSelected = CompactModuleCardStyles.style(enabled = true, selected = true, hovered = false)
        assertEquals(enabled.fillColor, enabledSelected.fillColor)
        assertEquals(enabled.outlineColor, enabledSelected.outlineColor)
        assertEquals(ClientTheme.accentA and 0x00FFFFFF, enabledSelected.nameColor and 0x00FFFFFF)
    }

    @Test
    fun `enabled and selected change only the module name`() {
        val disabled = CompactModuleCardStyles.style(false, false, false)
        val enabled = CompactModuleCardStyles.style(true, false, false)
        val selected = CompactModuleCardStyles.style(false, true, false)
        val enabledSelected = CompactModuleCardStyles.style(true, true, false)
        listOf(enabled, selected, enabledSelected).forEach { style ->
            assertEquals(disabled.fillColor, style.fillColor)
            assertEquals(disabled.outlineColor, style.outlineColor)
            assertEquals(disabled.outlineWidth, style.outlineWidth)
            assertEquals(disabled.bindColor, style.bindColor)
            assertEquals(disabled.descriptionColor, style.descriptionColor)
            assertEquals(disabled.settingsControlColor, style.settingsControlColor)
        }
        assertEquals(enabled.nameColor, selected.nameColor)
        assertEquals(enabled.nameColor, enabledSelected.nameColor)
    }

    @Test
    fun `hover changes only neutral surface values`() {
        val disabled = CompactModuleCardStyles.style(false, false, false)
        val hovered = CompactModuleCardStyles.style(false, false, true)
        assertNotEquals(disabled.fillColor, hovered.fillColor)
        assertNotEquals(disabled.outlineColor, hovered.outlineColor)
        assertEquals(disabled.nameColor, hovered.nameColor)
        assertEquals(disabled.bindColor, hovered.bindColor)
        assertEquals(disabled.descriptionColor, hovered.descriptionColor)
        assertEquals(disabled.settingsControlColor, hovered.settingsControlColor)
    }

    @Test
    fun `hover does not turn the selected control accent`() {
        val selected = CompactModuleCardStyles.style(false, true, false)
        val hovered = CompactModuleCardStyles.style(false, true, true)
        assertEquals(selected.settingsControlColor, hovered.settingsControlColor)
        assertEquals(selected.nameColor, hovered.nameColor)
        assertEquals(0xFFFFFF, hovered.outlineColor and 0x00FFFFFF)
    }
}
