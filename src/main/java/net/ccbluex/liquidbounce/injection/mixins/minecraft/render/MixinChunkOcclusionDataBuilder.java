package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import net.ccbluex.liquidbounce.features.module.modules.render.ModuleXRay;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VisGraph.class)
public abstract class MixinChunkOcclusionDataBuilder {

    @Inject(method = "setOpaque", at = @At("HEAD"), cancellable = true)
    private void onSetOpaque(BlockPos pos, CallbackInfo ci) {
        if (ModuleXRay.INSTANCE.getRunning()) {
            ci.cancel();
        }
    }

}
