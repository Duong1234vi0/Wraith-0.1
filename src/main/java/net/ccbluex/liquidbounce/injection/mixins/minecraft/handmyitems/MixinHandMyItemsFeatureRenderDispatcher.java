package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.DispatcherStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelStorage;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FeatureRenderDispatcher.class)
public abstract class MixinHandMyItemsFeatureRenderDispatcher {

    @Inject(method = "renderAllFeatures", at = @At("TAIL"))
    private void hmi$clearFrameQueues(CallbackInfo ci) {
        DispatcherStorage.clear();
        ItemModelStorage.clear();
    }
}
