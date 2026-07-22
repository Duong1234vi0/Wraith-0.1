package net.ccbluex.liquidbounce.features.module.modules.render.gui

/**
 * The card deliberately keeps selection out of every surface/control field.
 * Selection is a name treatment only; enabled and hover remain independent.
 */
data class CompactModuleCardStyle(
    val fillColor: Int,
    val outlineColor: Int,
    val outlineWidth: Float,
    val nameColor: Int,
    val bindColor: Int,
    val descriptionColor: Int,
    val settingsControlColor: Int,
    val enabledStripColor: Int,
)

object CompactModuleCardStyles {
    fun style(enabled: Boolean, selected: Boolean, hovered: Boolean, alpha: Float = 1f): CompactModuleCardStyle {
        val fill = when {
            enabled -> withAlpha(ClientTheme.accentA, 30)
            hovered -> 0x17FFFFFF
            else -> 0x0FFFFFFF
        }
        val outline = if (enabled) ClientTheme.accentA else 0x14FFFFFF
        return CompactModuleCardStyle(
            fillColor = fade(fill, alpha),
            outlineColor = fade(outline, alpha),
            outlineWidth = 0.55f,
            nameColor = fade(if (selected) ClientTheme.accentA else if (enabled) 0xFFFFFFFF.toInt() else 0xC8FFFFFF.toInt(), alpha),
            bindColor = fade(0x75FFFFFF, alpha),
            descriptionColor = fade(0x72FFFFFF, alpha),
            settingsControlColor = fade(0x88FFFFFF.toInt(), alpha),
            enabledStripColor = fade(withAlpha(ClientTheme.accentA, 170), alpha),
        )
    }

    private fun fade(color: Int, alpha: Float): Int {
        val base = (color ushr 24) and 0xFF
        val next = (base * alpha.coerceIn(0f, 1f)).toInt().coerceIn(0, 255)
        return (next shl 24) or (color and 0x00FFFFFF)
    }

    private fun withAlpha(color: Int, alpha: Int): Int = (alpha.coerceIn(0, 255) shl 24) or (color and 0x00FFFFFF)
}
