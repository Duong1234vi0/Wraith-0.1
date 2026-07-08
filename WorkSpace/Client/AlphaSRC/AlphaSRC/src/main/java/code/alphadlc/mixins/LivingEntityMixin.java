/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1268
 *  net.minecraft.class_1282
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_6880
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.PushEvent;
import code.alphadlc.events.item.SwingDurationEvent;
import code.alphadlc.events.player.EntityDeathEvent;
import code.alphadlc.events.player.EventOnTravelPost;
import code.alphadlc.events.player.JumpEvent;
import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.features.impl.render.SwingAnimations;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.aura.warp.TurnsConstructor;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1268;
import net.minecraft.class_1282;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public abstract class LivingEntityMixin {
    @Shadow
    public float field_6283;
    @Shadow
    private int field_6279;
    @Shadow
    protected boolean field_6252;
    @Unique
    private final class_310 client = class_310.method_1551();

    @Shadow
    public abstract boolean method_6059(class_6880<class_1291> var1);

    @Shadow
    @Nullable
    public abstract class_1293 method_6112(class_6880<class_1291> var1);

    @Inject(method={"hasStatusEffect"}, at={@At(value="HEAD")}, cancellable=true)
    private void onHasStatusEffect(class_6880<class_1291> effect, CallbackInfoReturnable<Boolean> cir) {
        if (effect == null) {
            return;
        }
        class_310 client = class_310.method_1551();
        if (client == null || client.field_1724 == null || this != client.field_1724) {
            return;
        }
        NoRender noRender = NoRender.getInstance();
        if (noRender == null || !noRender.shouldHideBadEffects()) {
            return;
        }
        class_1291 status = (class_1291)effect.comp_349();
        if (status == class_1294.field_5916 || status == class_1294.field_5919) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"getStuckArrowCount"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetStuckArrowCount(CallbackInfoReturnable<Integer> cir) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideStuckArrows()) {
            cir.setReturnValue((Object)0);
        }
    }

    @Inject(method={"getStingerCount"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetStingerCount(CallbackInfoReturnable<Integer> cir) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideStuckArrows()) {
            cir.setReturnValue((Object)0);
        }
    }

    @Shadow
    public abstract boolean method_20232();

    @Inject(method={"isPushable"}, at={@At(value="HEAD")}, cancellable=true)
    public void isPushable(CallbackInfoReturnable<Boolean> infoReturnable) {
        PushEvent event = SafeEvents.dispatch(() -> new PushEvent(PushEvent.Type.COLLISION));
        if (event != null && event.isCancelled()) {
            infoReturnable.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"calcGlidingVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getPitch()F"))
    private float hookModifyFallFlyingPitch(class_1309 instance) {
        if (this != class_310.method_1551().field_1724) {
            return instance.method_36455();
        }
        TurnsConnection rotationManager = TurnsConnection.INSTANCE;
        Turns rotation = rotationManager.getRotation();
        TurnsConstructor configurable = rotationManager.getCurrentRotationPlan();
        if (rotation == null || configurable == null || !configurable.isMoveCorrection() || configurable.isChangeLook()) {
            return instance.method_36455();
        }
        return rotation.getPitch();
    }

    @Redirect(method={"calcGlidingVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"))
    private class_243 hookModifyFallFlyingRotationVector(class_1309 original) {
        if (this != class_310.method_1551().field_1724) {
            return original.method_5720();
        }
        TurnsConnection rotationManager = TurnsConnection.INSTANCE;
        Turns rotation = rotationManager.getRotation();
        TurnsConstructor configurable = rotationManager.getCurrentRotationPlan();
        if (rotation == null || configurable == null || !configurable.isMoveCorrection() || configurable.isChangeLook()) {
            return original.method_5720();
        }
        return rotation.toVector();
    }

    @Inject(method={"jump"}, at={@At(value="HEAD")}, cancellable=true)
    private void jump(CallbackInfo info) {
        class_746 player;
        JumpEvent event;
        LivingEntityMixin livingEntityMixin = this;
        if (livingEntityMixin instanceof class_746 && (event = SafeEvents.dispatch(() -> LivingEntityMixin.lambda$jump$1(player = (class_746)livingEntityMixin))) != null && event.isCancelled()) {
            info.cancel();
        }
    }

    @ModifyExpressionValue(method={"jump"}, at={@At(value="NEW", target="(DDD)Lnet/minecraft/util/math/Vec3d;")})
    private class_243 hookFixRotation(class_243 original) {
        if (this.client.field_1724 == null || this != this.client.field_1724) {
            return original;
        }
        float yaw = TurnsConnection.INSTANCE.getMoveRotation().getYaw() * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yaw) * 0.2f), 0.0, (double)(class_3532.method_15362((float)yaw) * 0.2f));
    }

    @ModifyExpressionValue(method={"calcGlidingVelocity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getPitch()F")})
    private float hookModifyFallFlyingPitch(float original) {
        if (this.client.field_1724 == null || this != this.client.field_1724) {
            return original;
        }
        return TurnsConnection.INSTANCE.getMoveRotation().getPitch();
    }

    @ModifyExpressionValue(method={"calcGlidingVelocity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;")})
    private class_243 hookModifyFallFlyingRotationVector(class_243 original) {
        if (this.client.field_1724 == null || this != this.client.field_1724) {
            return original;
        }
        return TurnsConnection.INSTANCE.getMoveRotation().toVector();
    }

    @Inject(method={"calcGlidingVelocity"}, at={@At(value="RETURN")}, cancellable=true)
    private void onCalcGlidingVelocityReturn(class_243 oldVelocity, CallbackInfoReturnable<class_243> cir) {
        if (this.client.field_1724 == null || this != this.client.field_1724) {
            return;
        }
        if (!EventManager.hasListeners(EventOnTravelPost.class)) {
            return;
        }
        EventOnTravelPost event = SafeEvents.dispatch(() -> new EventOnTravelPost((class_243)cir.getReturnValue()));
        if (event != null) {
            cir.setReturnValue((Object)event.getOldVelocity());
        }
    }

    @Inject(method={"getHandSwingDuration"}, at={@At(value="HEAD")}, cancellable=true)
    private void swingProgressHook(CallbackInfoReturnable<Integer> cir) {
        if (this != this.client.field_1724) {
            return;
        }
        SwingDurationEvent event = SafeEvents.dispatch(() -> new SwingDurationEvent());
        if (event != null && event.isCancelled()) {
            cir.setReturnValue((Object)((int)event.getAnimation()));
        }
    }

    @Inject(method={"swingHand(Lnet/minecraft/util/Hand;)V"}, at={@At(value="HEAD")})
    private void forceSwingRestartOnRetrigger(class_1268 hand, CallbackInfo ci) {
        this.forceSwingRestartOnRetrigger();
    }

    @Inject(method={"swingHand(Lnet/minecraft/util/Hand;Z)V"}, at={@At(value="HEAD")})
    private void forceSwingRestartOnRetrigger(class_1268 hand, boolean fromServerPlayer, CallbackInfo ci) {
        this.forceSwingRestartOnRetrigger();
    }

    @Unique
    private void forceSwingRestartOnRetrigger() {
        if (this.client.field_1724 == null || this != this.client.field_1724) {
            return;
        }
        SwingAnimations swings = SwingAnimations.getInstance();
        if (swings == null || !swings.shouldRestartSwingOnRetrigger()) {
            return;
        }
        if (this.field_6252) {
            this.field_6279 = -1;
        }
    }

    @Inject(method={"onDeath"}, at={@At(value="HEAD")})
    private void onDeath(class_1282 source, CallbackInfo ci) {
        class_1309 entity = (class_1309)this;
        SafeEvents.dispatch(() -> new EntityDeathEvent((class_1297)entity, source));
    }

    @Inject(method={"handleStatus"}, at={@At(value="HEAD")})
    private void handleStatus(byte status, CallbackInfo ci) {
        if (status == 3) {
            class_1309 entity = (class_1309)this;
            SafeEvents.dispatch(() -> new EntityDeathEvent((class_1297)entity, null));
        }
    }

    private static /* synthetic */ JumpEvent lambda$jump$1(class_746 player) {
        return new JumpEvent((class_1657)player);
    }
}
