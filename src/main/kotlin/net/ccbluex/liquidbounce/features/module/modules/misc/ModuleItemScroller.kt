package net.ccbluex.liquidbounce.features.module.modules.misc
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.inventory.ContainerInput
import org.lwjgl.glfw.GLFW

private typealias MouseClick = (Slot?, Int, Int, ContainerInput) -> Unit
private typealias ScrollerClickAction = (handler: AbstractContainerMenu, slot: Slot, callback: MouseClick) -> Unit

/**
 * Quick item movement
 *
 * @author sqlerrorthing
 */
object ModuleItemScroller : ClientModule("ItemScroller", Category.MISC) {
    @JvmStatic
    val clickMode by enumChoice("ClickMode", ClickMode.QUICK_MOVE)

    @JvmStatic
    @Suppress("MagicNumber")
    val delay by intRange("Delay", 2..3, 0..20, suffix = "ticks")
}

@Suppress("UNUSED")
enum class ClickMode(
    override val choiceName: String,
    val action: ScrollerClickAction
) : NamedChoice {
    QUICK_MOVE("QuickMove", { _, slot, callback ->
        callback(slot, slot.id, GLFW.GLFW_MOUSE_BUTTON_LEFT, ContainerInput.QUICK_MOVE)
    })
}
