package net.ccbluex.liquidbounce.injection.mixins.minecraft.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomModels;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class MixinCapeLayer {

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hookCustomModelsHideCape(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light,
                                          AvatarRenderState state, float yRot, float xRot, CallbackInfo ci) {
        if (ModuleCustomModels.shouldHideCape(state)) {
            ci.cancel();
        }
    }
}
