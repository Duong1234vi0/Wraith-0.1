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
        assertEquals(enabled.enabledStripColor, enabledSelected.enabledStripColor)
        assertEquals(ClientTheme.accentA and 0x00FFFFFF, enabledSelected.nameColor and 0x00FFFFFF)
    }

    @Test
    fun `hover does not turn the selected control accent`() {
        val selected = CompactModuleCardStyles.style(false, true, false)
        val hovered = CompactModuleCardStyles.style(false, true, true)
        assertEquals(selected.settingsControlColor, hovered.settingsControlColor)
        assertEquals(selected.outlineColor, hovered.outlineColor)
    }
}
