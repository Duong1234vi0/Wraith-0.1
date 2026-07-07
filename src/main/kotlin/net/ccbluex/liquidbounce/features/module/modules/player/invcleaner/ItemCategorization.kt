package net.ccbluex.liquidbounce.features.module.modules.player.invcleaner
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.features.module.modules.player.invcleaner.items.WeaponItemFacet
import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.minecraft.world.item.MaceItem
import net.minecraft.world.item.TridentItem
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword

class ItemCategorization(private val slots: Iterable<ItemSlot>) {

    fun getItemFacets(slot: ItemSlot): List<Any> {
        val item = slot.itemStack.item
        return if (slot.itemStack.isSword || slot.itemStack.isAxe || item is MaceItem || item is TridentItem) {
            listOf(WeaponItemFacet(slot))
        } else {
            emptyList()
        }
    }

    fun getItemFacets(): List<Any> = slots.flatMap(::getItemFacets)
}
