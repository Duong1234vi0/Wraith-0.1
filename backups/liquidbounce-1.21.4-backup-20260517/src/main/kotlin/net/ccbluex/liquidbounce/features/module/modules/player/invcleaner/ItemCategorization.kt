package net.ccbluex.liquidbounce.features.module.modules.player.invcleaner

import net.ccbluex.liquidbounce.features.module.modules.player.invcleaner.items.WeaponItemFacet
import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.minecraft.item.AxeItem
import net.minecraft.item.MaceItem
import net.minecraft.item.SwordItem
import net.minecraft.item.TridentItem

class ItemCategorization(private val slots: Iterable<ItemSlot>) {

    fun getItemFacets(slot: ItemSlot): List<Any> {
        val item = slot.itemStack.item
        return if (item is SwordItem || item is AxeItem || item is MaceItem || item is TridentItem) {
            listOf(WeaponItemFacet(slot))
        } else {
            emptyList()
        }
    }

    fun getItemFacets(): List<Any> = slots.flatMap(::getItemFacets)
}
