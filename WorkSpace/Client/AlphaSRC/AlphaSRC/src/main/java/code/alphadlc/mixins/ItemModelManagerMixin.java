/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10442
 *  net.minecraft.class_10444
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_811
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.ItemReplacer;
import net.minecraft.class_10442;
import net.minecraft.class_10444;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_811;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_10442.class})
public class ItemModelManagerMixin {
    @Inject(method={"update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ModelTransformationMode;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V"}, at={@At(value="RETURN")})
    private void alphadlc$itemReplacerCapture(class_10444 state, class_1799 stack, class_811 mode, class_1937 world, class_1309 entity, int seed, CallbackInfo ci) {
        ItemReplacer.captureForRender(stack, mode, entity);
    }

    @Inject(method={"update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ModelTransformationMode;ZLnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V"}, at={@At(value="RETURN")})
    private void alphadlc$itemReplacerCaptureWithGlint(class_10444 state, class_1799 stack, class_811 mode, boolean glint, class_1937 world, class_1309 entity, int seed, CallbackInfo ci) {
        ItemReplacer.captureForRender(stack, mode, entity);
    }
}
