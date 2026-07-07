/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.item
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.SimpleContainer

fun SimpleContainer.inventoryAsCompound(title: Component): List<CompoundTag> = emptyList()
