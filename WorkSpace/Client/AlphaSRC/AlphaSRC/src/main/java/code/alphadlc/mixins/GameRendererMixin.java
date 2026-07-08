/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10209
 *  net.minecraft.class_1297
 *  net.minecraft.class_239
 *  net.minecraft.class_243
 *  net.minecraft.class_276
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_408
 *  net.minecraft.class_4587
 *  net.minecraft.class_6367
 *  net.minecraft.class_757
 *  net.minecraft.class_9779
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.display.hud.CustomHudOverlay;
import code.alphadlc.events.render.AspectRatioEvent;
import code.alphadlc.events.render.FovEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.misc.FreeCam;
import code.alphadlc.features.impl.render.HandShader;
import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.features.impl.render.ShaderFog;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.utils.RaycastAngle;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10209;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;
import net.minecraft.class_6367;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_757.class})
public abstract class GameRendererMixin {
    @Final
    @Shadow
    private class_310 field_4015;
    private class_276 alphadlc$beforeHandFbo = null;
    private class_276 alphadlc$afterHandFbo = null;
    @Shadow
    private float field_4005;
    @Shadow
    private float field_3988;
    @Shadow
    private float field_4004;

    @Shadow
    public abstract float method_32796();

    @Inject(method={"updateCrosshairTarget"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;findCrosshairTarget(Lnet/minecraft/entity/Entity;DDF)Lnet/minecraft/util/hit/HitResult;")}, cancellable=true)
    private void onUpdateTargetedEntity(float tickDelta, CallbackInfo ci) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        FreeCam debugCamera = FreeCam.getInstance();
        if (debugCamera.isState()) {
            class_10209.method_64146().method_15407();
            this.field_4015.field_1765 = RaycastAngle.raycast(debugCamera.pos, 4.5, MathAngle.cameraAngle(), false);
            ci.cancel();
        }
    }

    @Redirect(method={"findCrosshairTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"))
    private class_239 hookRaycast(class_1297 instance, double maxDistance, float tickDelta, boolean includeFluids) {
        if (this.field_4015.field_1724 == null || instance != this.field_4015.field_1724) {
            return instance.method_5745(maxDistance, tickDelta, includeFluids);
        }
        return RaycastAngle.raycast(maxDistance, TurnsConnection.INSTANCE.getRotation(), includeFluids);
    }

    @Redirect(method={"findCrosshairTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"))
    private class_243 hookRotationVector(class_1297 instance, float tickDelta) {
        if (this.field_4015.field_1724 == null) {
            return instance.method_5828(tickDelta);
        }
        return TurnsConnection.INSTANCE.getRotation().toVector();
    }

    @Inject(method={"getBasicProjectionMatrix"}, at={@At(value="TAIL")}, cancellable=true)
    public void getBasicProjectionMatrixHook(float fovDegrees, CallbackInfoReturnable<Matrix4f> cir) {
        AspectRatioEvent aspectRatioEvent = SafeEvents.dispatch(() -> new AspectRatioEvent());
        if (aspectRatioEvent != null && aspectRatioEvent.isCancelled()) {
            Matrix4f matrix4f = new Matrix4f();
            if (this.field_4005 != 1.0f) {
                matrix4f.translate(this.field_3988, -this.field_4004, 0.0f);
                matrix4f.scale(this.field_4005, this.field_4005, 1.0f);
            }
            matrix4f.perspective(fovDegrees * ((float)Math.PI / 180), aspectRatioEvent.getRatio(), 0.05f, this.method_32796());
            cir.setReturnValue((Object)matrix4f);
        }
    }

    @ModifyExpressionValue(method={"getFov"}, at={@At(value="INVOKE", target="Ljava/lang/Integer;intValue()I", remap=false)})
    private int hookGetFov(int original) {
        FovEvent event = SafeEvents.dispatch(() -> new FovEvent());
        if (event != null && event.isCancelled()) {
            return event.getFov();
        }
        return original;
    }

    @Inject(method={"renderWorld"}, at={@At(value="FIELD", target="Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode=180, ordinal=0)})
    public void hookWorldRender(class_9779 tickCounter, CallbackInfo ci, @Local(ordinal=2) Matrix4f matrix4f) {
        WorldRenderEvent event;
        if (this.field_4015.method_1561() == null || this.field_4015.method_1561().field_4686 == null) {
            return;
        }
        class_4587 matrixStack = new class_4587();
        matrixStack.method_34425(matrix4f);
        matrixStack.method_61958(this.field_4015.method_1561().field_4686.method_19326().method_22882());
        Render3D.setLastProjMat(RenderSystem.getProjectionMatrix());
        Render3D.setLastWorldSpaceMatrix(matrixStack.method_23760());
        ShaderFog shaderFog = ShaderFog.getInstance();
        if (shaderFog != null && shaderFog.isState()) {
            shaderFog.renderSkyShader();
        }
        if ((event = SafeEvents.dispatch(() -> new WorldRenderEvent(matrixStack, tickCounter.method_60637(false)))) != null) {
            Render3D.onWorldRender(event);
        }
    }

    @Inject(method={"tiltViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTiltViewWhenHurt(class_4587 matrices, float tickDelta, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.isState() && noRender.modeSetting.isSelected("\u0422\u0440\u044f\u0441\u043a\u0430 \u044d\u043a\u0440\u0430\u043d\u0430")) {
            ci.cancel();
        }
    }

    @Inject(method={"renderWorld"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V", shift=At.Shift.BEFORE)})
    private void captureBeforeHand(class_9779 tickCounter, CallbackInfo ci) {
        HandShader handShader = HandShader.getInstance();
        if (handShader == null || !handShader.isState() || !handShader.shouldRender() || this.field_4015.method_1522() == null) {
            if (handShader != null) {
                handShader.invalidateCapture();
            }
            return;
        }
        if (this.alphadlc$beforeHandFbo == null) {
            this.alphadlc$beforeHandFbo = new class_6367(this.field_4015.method_1522().field_1482, this.field_4015.method_1522().field_1481, true);
        }
        if (this.alphadlc$beforeHandFbo.field_1482 != this.field_4015.method_1522().field_1482 || this.alphadlc$beforeHandFbo.field_1481 != this.field_4015.method_1522().field_1481) {
            this.alphadlc$beforeHandFbo.method_1234(this.field_4015.method_1522().field_1482, this.field_4015.method_1522().field_1481);
        }
        handShader.beginHandCapture();
        this.alphadlc$copyColorFramebuffer(this.field_4015.method_1522(), this.alphadlc$beforeHandFbo);
        this.field_4015.method_1522().method_1235(true);
    }

    @Inject(method={"renderWorld"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V", shift=At.Shift.AFTER)})
    private void captureAfterHand(class_9779 tickCounter, CallbackInfo ci) {
        HandShader handShader = HandShader.getInstance();
        if (handShader == null || !handShader.isState() || !handShader.shouldRender() || this.field_4015.method_1522() == null) {
            return;
        }
        if (this.alphadlc$afterHandFbo == null) {
            this.alphadlc$afterHandFbo = new class_6367(this.field_4015.method_1522().field_1482, this.field_4015.method_1522().field_1481, true);
        }
        if (this.alphadlc$afterHandFbo.field_1482 != this.field_4015.method_1522().field_1482 || this.alphadlc$afterHandFbo.field_1481 != this.field_4015.method_1522().field_1481) {
            this.alphadlc$afterHandFbo.method_1234(this.field_4015.method_1522().field_1482, this.field_4015.method_1522().field_1481);
        }
        this.alphadlc$copyColorFramebuffer(this.field_4015.method_1522(), this.alphadlc$afterHandFbo);
        this.field_4015.method_1522().method_1235(false);
        handShader.onShaderCapture(this.alphadlc$beforeHandFbo, this.alphadlc$afterHandFbo);
        handShader.renderCapturedEffect();
    }

    private void alphadlc$copyColorFramebuffer(class_276 src, class_276 dst) {
        RenderSystem.assertOnRenderThread();
        dst.method_1235(false);
        src.method_1233(dst.field_1482, dst.field_1481);
        dst.method_1240();
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;draw()V", ordinal=0, shift=At.Shift.AFTER)})
    private void alphadlc$renderCustomHud(class_9779 tickCounter, boolean tick, CallbackInfo ci, @Local class_332 drawContext) {
        if (!this.field_4015.method_53466() || !tick || this.field_4015.field_1687 == null) {
            return;
        }
        if (this.field_4015.field_1755 instanceof class_408) {
            return;
        }
        CustomHudOverlay.render(drawContext, tickCounter, true);
    }
}
