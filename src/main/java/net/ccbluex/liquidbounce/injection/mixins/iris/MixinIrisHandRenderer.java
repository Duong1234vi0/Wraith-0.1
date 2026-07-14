package net.ccbluex.liquidbounce.injection.mixins.iris;

import net.ccbluex.liquidbounce.features.module.modules.render.handshader.IrisHandCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.irisshaders.iris.pathways.HandRenderer", remap = false)
public abstract class MixinIrisHandRenderer {

    @Inject(
        method = "renderSolid(Lorg/joml/Matrix4fc;FLnet/minecraft/client/Camera;" +
            "Lnet/minecraft/client/renderer/state/level/CameraRenderState;" +
            "Lnet/minecraft/client/renderer/GameRenderer;" +
            "Lnet/irisshaders/iris/pipeline/WorldRenderingPipeline;)V",
        at = @At("TAIL"),
        require = 0,
        remap = false
    )
    private void liquidbounce$finishSolidHandPhase(CallbackInfo ci) {
        IrisHandCompat.INSTANCE.finishSolidHandPhase();
    }

    @Inject(
        method = "renderTranslucent(Lorg/joml/Matrix4fc;FLnet/minecraft/client/Camera;" +
            "Lnet/minecraft/client/renderer/state/level/CameraRenderState;" +
            "Lnet/minecraft/client/renderer/GameRenderer;" +
            "Lnet/irisshaders/iris/pipeline/WorldRenderingPipeline;)V",
        at = @At("HEAD"),
        require = 0,
        remap = false
    )
    private void liquidbounce$beginTranslucentHandPhase(CallbackInfo ci) {
        IrisHandCompat.INSTANCE.beginTranslucentHandPhase();
    }

    @Inject(
        method = "renderTranslucent(Lorg/joml/Matrix4fc;FLnet/minecraft/client/Camera;" +
            "Lnet/minecraft/client/renderer/state/level/CameraRenderState;" +
            "Lnet/minecraft/client/renderer/GameRenderer;" +
            "Lnet/irisshaders/iris/pipeline/WorldRenderingPipeline;)V",
        at = @At("TAIL"),
        require = 0,
        remap = false
    )
    private void liquidbounce$finishTranslucentHandPhase(CallbackInfo ci) {
        IrisHandCompat.INSTANCE.finishTranslucentHandPhase();
    }
}
