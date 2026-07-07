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
package net.ccbluex.liquidbounce.utils.item
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.item.ArmorItem
import net.minecraft.item.MiningToolItem
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorMaterials
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.item.equipment.EquipmentType

val ArmorType.equipmentSlot: EquipmentSlot
    get() = slot

fun ArmorItem.armorMaterial(): ArmorMaterial {
    val path = BuiltInRegistries.ITEM.getKey(this).path
    return when {
        "netherite" in path -> ArmorMaterials.NETHERITE
        "diamond" in path -> ArmorMaterials.DIAMOND
        "iron" in path -> ArmorMaterials.IRON
        "chainmail" in path -> ArmorMaterials.CHAINMAIL
        "gold" in path || "golden" in path -> ArmorMaterials.GOLD
        "copper" in path -> ArmorMaterials.COPPER
        "turtle" in path -> ArmorMaterials.TURTLE_SCUTE
        else -> ArmorMaterials.LEATHER
    }
}

fun MiningToolItem.toolMaterial(): ToolMaterial {
    val path = BuiltInRegistries.ITEM.getKey(this).path
    return when {
        "netherite" in path -> ToolMaterial.NETHERITE
        "diamond" in path -> ToolMaterial.DIAMOND
        "iron" in path -> ToolMaterial.IRON
        "stone" in path -> ToolMaterial.STONE
        "gold" in path || "golden" in path -> ToolMaterial.GOLD
        "copper" in path -> ToolMaterial.COPPER
        else -> ToolMaterial.WOOD
    }
}

fun ArmorItem.armorType(): EquipmentType = when (defaultInstance.get(DataComponents.EQUIPPABLE)?.slot) {
    EquipmentSlot.HEAD -> ArmorType.HELMET
    EquipmentSlot.CHEST -> ArmorType.CHESTPLATE
    EquipmentSlot.LEGS -> ArmorType.LEGGINGS
    EquipmentSlot.FEET -> ArmorType.BOOTS
    EquipmentSlot.BODY -> ArmorType.BODY
    else -> ArmorType.BODY
}
