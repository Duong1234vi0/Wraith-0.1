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
package net.ccbluex.liquidbounce.injection.mixins.minecraft.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomModels;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ItemInHandLayer.class)
public class MixinHeldItemFeatureRenderer {

    @ModifyArgs(
            method = "submitArmWithItem",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V")
    )
    private void hookRabbitHeldItemOffset(
            Args args,
            ArmedEntityRenderState state,
            ItemStackRenderState item,
            ItemStack itemStack,
            HumanoidArm arm,
            PoseStack matrices,
            SubmitNodeCollector submitNodeCollector,
            int light
    ) {
        if (!ModuleCustomModels.shouldUseRabbit(state)) {
            return;
        }

        boolean left = arm == HumanoidArm.LEFT;
        args.set(0, ((left ? -4.0F : 0.9F) / 16.0F) + 0.1F);
        args.set(1, 0.125F);
        args.set(2, -0.9F);
    }
}
