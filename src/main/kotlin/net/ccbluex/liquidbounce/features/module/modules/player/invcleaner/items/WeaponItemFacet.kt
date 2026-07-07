package net.ccbluex.liquidbounce.features.module.modules.player.invcleaner.items
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.minecraft.world.item.MaceItem
import net.minecraft.world.item.TridentItem
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword

class WeaponItemFacet(val itemSlot: ItemSlot) : Comparable<WeaponItemFacet> {

    val itemStack
        get() = itemSlot.itemStack

    private val score: Int
        get() = when {
            itemStack.item is MaceItem -> 4
            itemStack.isSword -> 3
            itemStack.isAxe -> 2
            itemStack.item is TridentItem -> 1
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
