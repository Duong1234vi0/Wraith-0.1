package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleItemPhysics;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public abstract class MixinItemEntityRenderer {

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void hookItemPhysics(ItemEntityRenderState state, PoseStack poseStack,
                                 SubmitNodeCollector submitNodeCollector, CameraRenderState camera,
                                 CallbackInfo ci) {
        if (ModuleItemPhysics.submitItemEntity(state, poseStack, submitNodeCollector)) {
            ci.cancel();
        }
    }

}
