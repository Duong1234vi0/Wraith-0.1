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

@file:Suppress("TooManyFunctions")

package net.ccbluex.liquidbounce.utils.item
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.brigadier.StringReader
import net.ccbluex.liquidbounce.utils.block.defaultState
import net.ccbluex.liquidbounce.utils.block.isFullCube
import net.ccbluex.liquidbounce.utils.client.isOlderThanOrEqual1_8
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.regular
import net.minecraft.world.level.block.Block
import net.minecraft.commands.arguments.item.ItemParser
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeInstance
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.*
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.core.Holder
import net.minecraft.core.BlockPos
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Create item with NBT tags
 *
 * @docs https://minecraft.gamepedia.com/Commands/give
 */
fun createItem(stack: String, amount: Int = 1): ItemStack =
    ItemParser(mc.level!!.registryAccess()).parse(StringReader(stack)).createItemStack(amount)

fun createSplashPotion(name: String, vararg effects: MobEffectInstance): ItemStack {
    val itemStack = ItemStack(Items.SPLASH_POTION)

    itemStack.set(DataComponents.CUSTOM_NAME, regular(name))
    itemStack.set<PotionContents>(
        DataComponents.POTION_CONTENTS,
        PotionContents(Optional.empty(), Optional.empty(), effects.asList(), Optional.empty())
    )

    return itemStack
}

/**
 * Check if a stack is nothing (means empty slot)
 */
@OptIn(ExperimentalContracts::class)
fun ItemStack?.isNothing(): Boolean {
    contract {
        returns(true) implies (this@isNothing != null)
    }

    return this?.isEmpty == true
}

fun ItemStack?.getEnchantmentCount(): Int {
    val enchantments = this?.get(DataComponents.ENCHANTMENTS) ?: return 0

    return enchantments.size()
}

fun ItemStack?.getEnchantment(enchantment: ResourceKey<Enchantment>): Int {
    val enchantments = this?.get(DataComponents.ENCHANTMENTS) ?: return 0

    return enchantments.getLevel(enchantment.toRegistryEntry())
}

val ItemStack.isConsumable: Boolean
    get() = this.isFood || this.item == Items.POTION || this.item == Items.MILK_BUCKET

val ItemStack.isFood: Boolean
    get() = foodComponent != null && this.useAnimation == ItemUseAnimation.EAT

val ItemStack.foodComponent: FoodProperties?
    get() = this.get(DataComponents.FOOD)

private val BUNDLE_ITEMS = setOf(
    Items.BUNDLE,
    Items.WHITE_BUNDLE,
    Items.ORANGE_BUNDLE,
    Items.MAGENTA_BUNDLE,
    Items.LIGHT_BLUE_BUNDLE,
    Items.YELLOW_BUNDLE,
    Items.LIME_BUNDLE,
    Items.PINK_BUNDLE,
    Items.GRAY_BUNDLE,
    Items.LIGHT_GRAY_BUNDLE,
    Items.CYAN_BUNDLE,
    Items.PURPLE_BUNDLE,
    Items.BLUE_BUNDLE,
    Items.BROWN_BUNDLE,
    Items.GREEN_BUNDLE,
    Items.RED_BUNDLE,
    Items.BLACK_BUNDLE
)

val ItemStack.isBundle
    get() = this.item in BUNDLE_ITEMS

val ItemStack.isSword: Boolean
    get() = this.`is`(ItemTags.SWORDS)

val ItemStack.isAxe: Boolean
    get() = this.`is`(ItemTags.AXES)

val ItemStack.isPickaxe: Boolean
    get() = this.`is`(ItemTags.PICKAXES)

val ItemStack.isShovel: Boolean
    get() = this.`is`(ItemTags.SHOVELS)

val ItemStack.isHoe: Boolean
    get() = this.`is`(ItemTags.HOES)

val ItemStack.isMiningTool: Boolean
    get() = isAxe || isPickaxe || isShovel || isHoe

fun isHotbarSlot(slot: Int) = slot == 45 || slot in 36..44

fun ItemStack.getAttributeValue(attribute: Holder<Attribute>): Double? = getComponents()
    .getOrDefault(
        DataComponents.ATTRIBUTE_MODIFIERS,
        ItemAttributeModifiers.EMPTY
    )
    .compute(attribute, 0.0, EquipmentSlot.MAINHAND)
    .takeIf { it != 0.0 }

val ItemStack.attackDamage: Double
    get() {
        val entityBaseDamage = player.getAttributeValue(Attributes.ATTACK_DAMAGE)
        val baseDamage = getAttributeValue(Attributes.ATTACK_DAMAGE)
            ?: return 0.0

        /*
         * Client-side damage calculation for enchantments does not exist anymore
         * see https://bugs.mojang.com/browse/MC-196250
         *
         * We now use the following formula to calculate the damage:
         * https://minecraft.wiki/w/Sharpness
         * >= 1.9 -> 0.5 * level + 0.5
         * else -> 1.25 * level
         */
        return entityBaseDamage + baseDamage + getSharpnessDamage()
    }

val ItemStack.sharpnessLevel: Int
    get() = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS.toRegistryEntry(), this)

fun ItemStack.getSharpnessDamage(level: Int = sharpnessLevel): Double =
    if (!isOlderThanOrEqual1_8) {
        when (level) {
            0 -> 0.0
            else -> 0.5 * level + 0.5
        }
    } else {
        level * 1.25
    }

val ItemStack.attackSpeed: Float
    get() = item.getAttributeValue(Attributes.ATTACK_SPEED)

val ItemStack.durability
    get() = this.maxDamage - this.damage

private fun Item.getAttributeValue(attribute: Holder<Attribute>): Float {
    return this.components()
        .getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY)
        .compute(attribute, 0.0, EquipmentSlot.MAINHAND)
        .toFloat()
}

fun ResourceKey<Enchantment>.toRegistryEntry(): Holder<Enchantment> {
    val world = mc.level
    requireNotNull(world) { "Level is null" }

    val registry = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
    return registry.get(this).orElseThrow { IllegalArgumentException("Unknown enchantment key $this") }
}

fun ItemStack.getBlock(): Block? {
    val item = this.item
    if (item !is BlockItem) {
        return null
    }

   return item.block
}

fun ItemStack.isFullBlock(): Boolean {
    val block = this.getBlock() ?: return false
    return block.defaultState.isFullCube(mc.level!!, BlockPos.ZERO)
}
