package net.ccbluex.liquidbounce.utils.item
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.core.component.DataComponents
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.ItemStack

fun ItemStack.getPotionEffects(): Iterable<MobEffectInstance> {
    return this.get(DataComponents.POTION_CONTENTS)?.effects ?: emptyList()
}
