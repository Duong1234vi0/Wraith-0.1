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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.entity;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = LivingEntity.class, priority = 999)
public abstract class MixinLivingEntityCompatibility {

    // Keep priority 999 to reduce conflicts with mods that target the same hand-swing duration constant.
    // #Edit By Claude 2026-06-17: VCore Animations SlowAnimation — adds SlowValue ticks to the vanilla
    // swing duration constant (6) so the custom swing plays out slower/fuller.
    @ModifyConstant(method = "getCurrentSwingDuration", constant = @Constant(intValue = 6), require = 0)
    private int hookSwingSpeed(int constant) {
        if ((Object) this != Minecraft.getInstance().player) {
            return constant;
        }

        return constant + ModuleAnimations.INSTANCE.extraSwingDuration();
    }

}
