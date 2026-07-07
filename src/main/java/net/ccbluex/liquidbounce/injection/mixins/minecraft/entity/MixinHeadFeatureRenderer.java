package net.ccbluex.liquidbounce.injection.mixins.minecraft.entity;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomModels;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public class MixinHeadFeatureRenderer<S extends LivingEntityRenderState> {
    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    ) private void onRender(PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, int i, S livingEntityRenderState, float f, float g, CallbackInfo ci) {
        if (ModuleCustomModels.shouldHidePlayerFeature(livingEntityRenderState)) {
            ci.cancel();
        }
    }
}
