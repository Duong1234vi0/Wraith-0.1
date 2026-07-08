package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.events.GameRenderEvent;
import net.ccbluex.liquidbounce.event.events.PerspectiveEvent;
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleCustomAmbience;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleTracers;
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection;
import net.ccbluex.liquidbounce.features.module.modules.render.handshader.ModuleHandShader;
import net.ccbluex.liquidbounce.utils.render.WorldToScreen;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private Camera mainCamera;

    @Inject(method = "render", at = @At("HEAD"))
    private void hookGameRender(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(GameRenderEvent.INSTANCE);
    }

    @Inject(method = "extractCamera", at = @At("TAIL"))
    private void hookWorldToScreenMatrices(
        DeltaTracker deltaTracker,
        float worldPartialTicks,
        float cameraEntityPartialTicks,
        CallbackInfo ci,
        @Local(name = "cameraState") CameraRenderState cameraState
    ) {
        // #Edit By Codex: feed the raw camera projection + view to WorldToScreen so 2D ESP/Glow project correctly.
        WorldToScreen.INSTANCE.setMatrices(cameraState.projectionMatrix, cameraState.viewRotationMatrix, cameraState.pos);
    }

    @Inject(
        method = "renderLevel",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/state/level/CameraEntityRenderState;isSleeping:Z",
            opcode = Opcodes.GETFIELD
        )
    )
    private void hookWorldRender(
        DeltaTracker deltaTracker,
        CallbackInfo ci,
        @Local(name = "cameraState") CameraRenderState cameraState,
        @Local(name = "projectionMatrix") Matrix4f projectionMatrix,
        @Local(name = "modelViewMatrix") Matrix4fc modelViewMatrix
    ) {
        PoseStack newMatStack = new PoseStack();
        newMatStack.mulPose(modelViewMatrix);
        AlphaProjection.INSTANCE.setMatrices(projectionMatrix, modelViewMatrix, cameraState.pos);
        EventManager.INSTANCE.callEvent(new WorldRenderEvent(
                newMatStack,
                this.mainCamera,
                deltaTracker.getGameTimeDeltaPartialTick(false)
        ));
    }

    @Inject(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/GameRenderer;renderItemInHand(Lnet/minecraft/client/renderer/state/level/CameraRenderState;FLorg/joml/Matrix4fc;)V"
        )
    )
    private void hookHandShaderBeforeHandRender(DeltaTracker deltaTracker, CallbackInfo ci) {
        ModuleHandShader.INSTANCE.beginHandCapture(this.minecraft.getMainRenderTarget());
    }

    @Inject(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/GameRenderer;renderItemInHand(Lnet/minecraft/client/renderer/state/level/CameraRenderState;FLorg/joml/Matrix4fc;)V",
            shift = At.Shift.AFTER
        )
    )
    private void hookHandShaderAfterHandRender(DeltaTracker deltaTracker, CallbackInfo ci) {
        ModuleHandShader.INSTANCE.finishHandCapture(this.minecraft.getMainRenderTarget());
    }

    @ModifyArg(
            method = "renderLevel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/fog/FogRenderer;getBuffer(Lnet/minecraft/client/renderer/fog/FogRenderer$FogMode;)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;")
    )
    private FogRenderer.FogMode injectCustomAmbienceDisableFog(FogRenderer.FogMode fogMode) {
        return ModuleCustomAmbience.FogConfigurable.INSTANCE.shouldDisableWorldFog()
                ? FogRenderer.FogMode.NONE
                : fogMode;
    }

    @Inject(method = "bobView", at = @At("HEAD"), cancellable = true)
    private void injectBobView(CameraRenderState cameraState, PoseStack poseStack, CallbackInfo ci) {
        if (ModuleTracers.INSTANCE.getRunning()) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(method = "extractOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;getCameraType()Lnet/minecraft/client/CameraType;"))
    private CameraType hookPerspectiveEvent(CameraType original) {
        return EventManager.INSTANCE.callEvent(new PerspectiveEvent(original)).getPerspective();
    }

}
