package net.ccbluex.liquidbounce.features.module.modules.player.invcleaner.items

import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.minecraft.item.AxeItem
import net.minecraft.item.MaceItem
import net.minecraft.item.SwordItem
import net.minecraft.item.TridentItem

class WeaponItemFacet(val itemSlot: ItemSlot) : Comparable<WeaponItemFacet> {

    val itemStack
        get() = itemSlot.itemStack

    private val score: Int
        get() = when (itemStack.item) {
            is MaceItem -> 4
            is SwordItem -> 3
            is AxeItem -> 2
            is TridentItem -> 1
            else -> 0
        }

    override fun compareTo(other: WeaponItemFacet): Int =
        compareValuesBy(
            this,
            other,
            { it.score },
            { it.itemStack.maxDamage - it.itemStack.damage }
        )
}
