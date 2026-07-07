package net.ccbluex.liquidbounce.utils.client

import net.minecraft.network.chat.Component

object VanillaTranslationRecognizer {
    val vanillaKeybinds = mutableSetOf<String>()
    val vanillaTranslations = HashSet<String>()

    var isBuildingVanillaKeybinds = false

    fun registerKey(translationKey: String) {
        if (isBuildingVanillaKeybinds) {
            vanillaKeybinds += translationKey
        }
    }

    @Suppress("UnusedReceiverParameter")
    fun isPackLegit(pack: Any?): Boolean = true
}

fun filterNonVanillaText(text: Component): Component = text
