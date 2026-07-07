/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */

package net.ccbluex.liquidbounce.config.gson.serializer.minecraft
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.ItemStack
import java.lang.reflect.Type

object ItemStackSerializer : JsonSerializer<ItemStack> {
    override fun serialize(src: ItemStack?, typeOfSrc: Type, context: JsonSerializationContext) = src?.let {
        JsonObject().apply {
            addProperty("identifier", BuiltInRegistries.ITEM.getKey(it.item).toString())
            add("displayName", context.serialize(it.hoverName))
            addProperty("count", it.count)
            addProperty("damage", it.damageValue)
            addProperty("maxDamage", it.maxDamage)
            addProperty("empty", it.isEmpty)
            it.get(DataComponents.ENCHANTMENTS)?.entrySet()
                ?.takeIf { set -> set.isNotEmpty() }
                ?.let { entries ->
                    // TODO: this property is deprecated. Please remove it in 0.32.0
                    addProperty("hasEnchantment", true)
                    add("enchantments", JsonObject().apply {
                        for (entry in entries) {
                            addProperty(entry.key.getRegisteredName(), entry.intValue)
                        }
                    })
                }
        }
    }

}
