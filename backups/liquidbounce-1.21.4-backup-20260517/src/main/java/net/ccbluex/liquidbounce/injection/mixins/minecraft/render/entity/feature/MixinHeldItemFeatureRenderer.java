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

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomModels;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(HeldItemFeatureRenderer.class)
public class MixinHeldItemFeatureRenderer {

    @ModifyArgs(
            method = "renderItem(Lnet/minecraft/client/render/entity/state/ArmedEntityRenderState;Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/util/Arm;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V")
    )
    private void hookRabbitHeldItemOffset(
            Args args,
            ArmedEntityRenderState state,
            ItemRenderState itemState,
            Arm arm,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {
        if (!ModuleCustomModels.shouldUseRabbit(state)) {
            return;
        }

        boolean left = arm == Arm.LEFT;
        args.set(0, ((left ? -4.0F : 0.9F) / 16.0F) + 0.1F);
        args.set(1, 0.125F);
        args.set(2, -0.9F);
    }
}
