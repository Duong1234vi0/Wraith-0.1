/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_243
 *  net.minecraft.class_4184
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.events.render.CameraPositionEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4184.class})
public abstract class CameraMixin {
    @Shadow
    private class_243 field_18712;
    @Shadow
    @Final
    private class_2338.class_2339 field_18713;
    @Shadow
    private float field_18718;
    @Shadow
    private float field_18717;

    @Shadow
    public abstract void method_19325(float var1, float var2);

    @Shadow
    protected abstract void method_19324(float var1, float var2, float var3);

    @Shadow
    protected abstract float method_19318(float var1);

    @Inject(method={"update"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setPos(DDD)V", shift=At.Shift.AFTER)}, cancellable=true)
    private void updateHook(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        class_746 player;
        CameraEvent event = SafeEvents.dispatch(() -> new CameraEvent(false, 4.0f, new Turns(this.field_18718, this.field_18717)));
        if (event == null) {
            return;
        }
        Turns angle = event.getAngle();
        if (event.isCancelled() && focusedEntity instanceof class_746 && !(player = (class_746)focusedEntity).method_6113() && thirdPerson) {
            float pitch = inverseView ? -angle.getPitch() : angle.getPitch();
            float yaw = angle.getYaw() - (float)(inverseView ? 180 : 0);
            float distance = event.getDistance();
            this.method_19325(yaw, pitch);
            this.method_19324(event.isCameraClip() ? -distance : -this.method_19318(distance), 0.0f, 0.0f);
            ci.cancel();
        }
    }

    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
    private void redirectSetRotation(class_4184 instance, float yaw, float pitch, class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        CameraEvent event = SafeEvents.dispatch(() -> new CameraEvent(false, 4.0f, new Turns(yaw, pitch)));
        float newYaw = yaw;
        float newPitch = pitch;
        if (event != null && event.isCancelled() && event.getAngle() != null) {
            newYaw = event.getAngle().getYaw();
            newPitch = event.getAngle().getPitch();
        }
        if (thirdPerson && inverseView) {
            newYaw += 180.0f;
            newPitch = -newPitch;
        }
        instance.method_19325(newYaw, newPitch);
    }

    @Inject(method={"setPos(Lnet/minecraft/util/math/Vec3d;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void posHook(class_243 pos, CallbackInfo ci) {
        class_243 originalPos = pos;
        CameraPositionEvent event = SafeEvents.dispatch(() -> new CameraPositionEvent(originalPos));
        pos = event != null ? event.getPos() : originalPos;
        this.field_18712 = pos;
        this.field_18713.method_10102(pos.field_1352, pos.field_1351, pos.field_1350);
        ci.cancel();
    }
}
