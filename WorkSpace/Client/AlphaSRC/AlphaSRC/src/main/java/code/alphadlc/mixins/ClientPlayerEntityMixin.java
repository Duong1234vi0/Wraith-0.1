/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1313
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_638
 *  net.minecraft.class_742
 *  net.minecraft.class_744
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.PushEvent;
import code.alphadlc.events.container.CloseScreenEvent;
import code.alphadlc.events.player.EventNoSlow;
import code.alphadlc.events.player.MotionEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.player.PlayerTravelEvent;
import code.alphadlc.events.player.PostMotionEvent;
import code.alphadlc.events.player.PostTickEvent;
import code.alphadlc.events.player.SprintEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.movement.AutoSprint;
import code.alphadlc.features.impl.movement.NoSlow;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.client.stealth.MovementMixinGuard;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.FreeLookStorage;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTick;
import code.alphadlc.utils.interactions.simulate.Simulations;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_744;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_746.class})
public abstract class ClientPlayerEntityMixin
extends class_742 {
    @Final
    @Shadow
    protected class_310 field_3937;
    @Shadow
    public class_744 field_3913;
    @Unique
    private double silentBodyPrevX = 0.0;
    @Unique
    private double silentBodyPrevZ = 0.0;
    private float prevBodyYaw = 0.0f;
    private boolean initialized = false;

    @Shadow
    public abstract float method_5695(float var1);

    @Shadow
    public abstract float method_5705(float var1);

    @Shadow
    protected abstract void method_3148(float var1, float var2);

    public ClientPlayerEntityMixin(class_638 world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    public void tick(CallbackInfo info) {
        if (this.field_3937.field_1724 != null && this.field_3937.field_1687 != null) {
            SafeEvents.dispatch(() -> new TickEvent());
        }
        InventoryTick.update();
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    public void onTick(CallbackInfo ci) {
        if (!this.initialized && QuickImports.mc.field_1724 != null) {
            this.silentBodyPrevX = QuickImports.mc.field_1724.method_23317();
            this.silentBodyPrevZ = QuickImports.mc.field_1724.method_23321();
            this.prevBodyYaw = QuickImports.mc.field_1724.method_43078();
            this.initialized = true;
        }
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", shift=At.Shift.AFTER)})
    public void postTick(CallbackInfo callbackInfo) {
        if (this.field_3937.field_1724 != null && this.field_3937.field_1687 != null) {
            SafeEvents.dispatch(() -> new PostTickEvent());
        }
        if (this.shouldSyncRotation()) {
            float targetYaw = this.method_36454();
            float headBlend = 0.58f;
            this.field_6259 = this.field_6241 = class_3532.method_16439((float)headBlend, (float)this.field_6241, (float)targetYaw);
        }
    }

    @Unique
    private boolean shouldSyncRotation() {
        AttackAura aura = AttackAura.getInstance();
        RotationStorage storage = RotationStorage.instance;
        if (aura == null || !aura.isState() || storage == null || !storage.isRotating() || storage.isCameraReturning()) {
            return false;
        }
        return FreeLookStorage.isActive();
    }

    @Inject(method={"tickMovement"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/input/Input;tick()V", shift=At.Shift.AFTER)})
    private void onInputTick(CallbackInfo ci) {
        if (QuickImports.mc.field_1724 == null) {
            return;
        }
        SafeEvents.dispatch(() -> new PlayerTravelEvent(class_243.field_1353, false));
    }

    @ModifyExpressionValue(method={"sendMovementPackets", "tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F")})
    private float hookSilentRotationYaw(float original) {
        if (QuickImports.mc.field_1724 != null && TurnsConnection.INSTANCE.getCurrentAngle() != null) {
            float newBodyYaw;
            float currentYaw = TurnsConnection.INSTANCE.getRotation().getYaw();
            this.prevBodyYaw = newBodyYaw = Simulations.calculateBodyYaw(currentYaw, this.prevBodyYaw, this.silentBodyPrevX, this.silentBodyPrevZ, QuickImports.mc.field_1724.method_23317(), QuickImports.mc.field_1724.method_23321(), QuickImports.mc.field_1724.field_6251);
            this.silentBodyPrevX = QuickImports.mc.field_1724.method_23317();
            this.silentBodyPrevZ = QuickImports.mc.field_1724.method_23321();
            QuickImports.mc.field_1724.method_5636(newBodyYaw);
            return currentYaw;
        }
        return original;
    }

    @ModifyExpressionValue(method={"sendMovementPackets", "tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F")})
    private float hookSilentRotationPitch(float original) {
        if (QuickImports.mc.field_1724 == null || TurnsConnection.INSTANCE.getCurrentAngle() == null) {
            return original;
        }
        return TurnsConnection.INSTANCE.getRotation().getPitch();
    }

    @Inject(method={"closeHandledScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void closeHandledScreenHook(CallbackInfo info) {
        CloseScreenEvent event = SafeEvents.dispatch(() -> new CloseScreenEvent(this.field_3937.field_1755));
        if (event != null && event.isCancelled()) {
            info.cancel();
        }
    }

    @Redirect(method={"tickMovement"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require=0)
    private boolean tickMovementHook(class_746 player) {
        if (!MovementMixinGuard.shouldHandleNoSlowRedirect()) {
            return player.method_6115();
        }
        EventNoSlow a = SafeEvents.dispatch(() -> new EventNoSlow(false));
        if (a != null && a.isCancelled()) {
            return false;
        }
        return player.method_6115();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="HEAD")}, cancellable=true)
    private void preMotion(CallbackInfo ci) {
        if (!MovementMixinGuard.shouldHandleMotionEvent()) {
            return;
        }
        MotionEvent event = SafeEvents.dispatch(() -> new MotionEvent(this.method_23317(), this.method_23318(), this.method_23321(), this.method_5705(1.0f), this.method_5695(1.0f), this.method_24828()));
        if (event == null) {
            return;
        }
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }
        this.method_5814(event.getX(), event.getY(), event.getZ());
        this.method_36456(event.getYaw());
        this.method_36457(event.getPitch());
    }

    @Inject(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")}, cancellable=true)
    public void onMoveHook(class_1313 movementType, class_243 movement, CallbackInfo ci) {
        if (!MovementMixinGuard.shouldHandleMoveEvent()) {
            return;
        }
        MoveEvent event = SafeEvents.dispatch(() -> new MoveEvent(movement));
        double d = this.method_23317();
        double e = this.method_23321();
        super.method_5784(movementType, event != null ? event.getMovement() : movement);
        this.method_3148((float)(this.method_23317() - d), (float)(this.method_23321() - e));
        ci.cancel();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="RETURN")}, cancellable=true)
    private void postMotion(CallbackInfo ci) {
        PostMotionEvent postMotionEvent;
        if (MovementMixinGuard.shouldHandlePostMotionEvent() && (postMotionEvent = SafeEvents.dispatch(() -> new PostMotionEvent())) != null && postMotionEvent.isCancelled()) {
            ci.cancel();
        }
        InventoryFlowManager.postMotion();
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    public void pushOutOfBlocks(double x, double z, CallbackInfo ci) {
        PushEvent event = SafeEvents.dispatch(() -> new PushEvent(PushEvent.Type.BLOCK));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"shouldStopSprinting"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")}, cancellable=true)
    public void shouldStopSprintingHook(CallbackInfoReturnable<Boolean> cir) {
        AutoSprint autoSprint = AutoSprint.getInstance();
        NoSlow noSlow = NoSlow.getInstance();
        if (autoSprint != null && noSlow != null && autoSprint.isState() && noSlow.isState()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"canStartSprinting"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")}, cancellable=true)
    public void canStartSprintingHook(CallbackInfoReturnable<Boolean> cir) {
        AutoSprint autoSprint = AutoSprint.getInstance();
        NoSlow noSlow = NoSlow.getInstance();
        if (autoSprint != null && noSlow != null && autoSprint.isState() && noSlow.isState()) {
            cir.setReturnValue((Object)true);
        }
    }

    @ModifyExpressionValue(method={"tickMovement"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;canSprint()Z")})
    private boolean hookCanSprint(boolean original) {
        SprintEvent event = SafeEvents.dispatch(() -> new SprintEvent(original));
        return event != null ? event.isSprinting() : original;
    }
}
