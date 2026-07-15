package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingSignRenderer.class)
public abstract class MixinHandMyItemsHangingSignRenderer {

    @Inject(method = "submitSpecial", at = @At("HEAD"), cancellable = true)
    private static void hmi$correctItemTransform(
        SpriteGetter sprites,
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int lightCoords,
        int overlayCoords,
        Model.Simple model,
        SpriteId sprite,
        CallbackInfo ci
    ) {
        if (!HandMyItemsRuntime.isActive()) {
            return;
        }

        poseStack.pushPose();
        try {
            poseStack.translate(0.5F, 0.9375F, 0.5F);
            poseStack.translate(0.0F, -0.3125F, 0.0F);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            submitNodeCollector.submitModel(
                model,
                Unit.INSTANCE,
                poseStack,
                lightCoords,
                OverlayTexture.NO_OVERLAY,
                -1,
                sprite,
                sprites,
                0,
                null
            );
        } finally {
            poseStack.popPose();
        }
        ci.cancel();
    }
}
