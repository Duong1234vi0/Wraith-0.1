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
 *
 */
package net.ccbluex.liquidbounce.injection.mixins.minecraft.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.ccbluex.liquidbounce.utils.aiming.RotationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItem {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void hookSwordUse(Level world, Player user, InteractionHand hand,
                              CallbackInfoReturnable<InteractionResult> cir) {
    }

    @ModifyReturnValue(method = "getUseAnimation", at = @At("RETURN"))
    private ItemUseAnimation hookSwordUseAction(ItemUseAnimation original) {
        return original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int hookMaxUseTime(int original) {
        return original;
    }

    @ModifyExpressionValue(method = "getPlayerPOVHitResult", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;calculateViewVector(FF)Lnet/minecraft/world/phys/Vec3;"))
    private static Vec3 hookFixRotation(Vec3 original, Level world, Player player, ClipContext.Fluid fluidHandling) {
        var rotation = RotationManager.INSTANCE.getCurrentRotation();

        if (player == Minecraft.getInstance().player && rotation != null) {
            return rotation.getDirectionVector();
        }

        return original;
    }

}
