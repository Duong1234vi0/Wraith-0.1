package net.ccbluex.liquidbounce.injection.mixins.iris;

import net.ccbluex.liquidbounce.features.module.modules.render.handshader.IrisHandCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.irisshaders.iris.targets.RenderTargets", remap = false)
public abstract class MixinIrisRenderTargets {

    @Inject(method = "copyPreHandDepth()V", at = @At("TAIL"), require = 0, remap = false)
    private void liquidbounce$rememberRenderTargets(CallbackInfo ci) {
        IrisHandCompat.INSTANCE.onPreHandDepthCopied(this);
    }
}
