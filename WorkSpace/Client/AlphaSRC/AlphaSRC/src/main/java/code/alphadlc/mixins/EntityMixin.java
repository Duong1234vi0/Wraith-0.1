/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.player.BoundingBoxControlEvent;
import code.alphadlc.events.player.EventOnMovePost;
import code.alphadlc.events.player.PlayerVelocityStrafeEvent;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.client.stealth.MovementMixinGuard;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1297.class})
public abstract class EntityMixin
implements QuickImports {
    @Shadow
    private class_238 field_6005;
    @Shadow
    public float field_6031;
    @Unique
    private final class_310 client = class_310.method_1551();

    @Redirect(method={"updateVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;"))
    public class_243 hookVelocity(class_243 movementInput, float speed, float yaw) {
        if (this == EntityMixin.mc.field_1724 && MovementMixinGuard.shouldHandleVelocityStrafe()) {
            class_243 velocity = class_1297.method_18795((class_243)movementInput, (float)speed, (float)yaw);
            PlayerVelocityStrafeEvent event = SafeEvents.dispatch(() -> new PlayerVelocityStrafeEvent(movementInput, speed, yaw, velocity));
            return event != null ? event.getVelocity() : velocity;
        }
        return class_1297.method_18795((class_243)movementInput, (float)speed, (float)yaw);
    }

    @Inject(method={"getBoundingBox"}, at={@At(value="HEAD")}, cancellable=true)
    public final void getBoundingBox(CallbackInfoReturnable<class_238> cir) {
        if (!MovementMixinGuard.isHitBoxActive()) {
            return;
        }
        BoundingBoxControlEvent event = SafeEvents.dispatch(() -> new BoundingBoxControlEvent(this.field_6005, (class_1297)this));
        cir.setReturnValue((Object)(event != null ? event.getBox() : this.field_6005));
    }

    @ModifyVariable(method={"getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"}, at=@At(value="HEAD"), ordinal=1, argsOnly=true)
    private float modifyRotationVectorYaw(float yaw) {
        if (this instanceof class_746) {
            return AuraMixinHelper.resolveYaw(yaw);
        }
        return yaw;
    }

    @ModifyVariable(method={"getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"}, at=@At(value="HEAD"), ordinal=0, argsOnly=true)
    private float modifyPitch(float pitch) {
        if (this instanceof class_746) {
            return AuraMixinHelper.resolvePitch(pitch);
        }
        return pitch;
    }

    @ModifyExpressionValue(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;isControlledByPlayer()Z")})
    public boolean isControlledByPlayerHook(boolean original) {
        if (this == EntityMixin.mc.field_1724 && MovementMixinGuard.shouldUseEntityMovementPhysics()) {
            return false;
        }
        return original;
    }

    @Inject(method={"updateVelocity"}, at={@At(value="TAIL")})
    private void onUpdateVelocityPost(float speed, class_243 movementInput, CallbackInfo ci) {
        class_1297 me = (class_1297)this;
        if (class_310.method_1551().field_1724 != null && me.method_5628() == class_310.method_1551().field_1724.method_5628()) {
            EventManager.callEvent(new EventOnMovePost(speed, movementInput));
        }
    }
}
