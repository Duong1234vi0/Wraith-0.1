package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.event.EventCloseInv;
import ua.babka.event.EventNoSlow;
import ua.babka.event.EventPlayerSync;
import ua.babka.event.EventPlayerSyncEnd;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.EventPostMotion;
import ua.babka.event.EventSprintSync;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.module.impl.player.LockSlot;
import ua.babka.module.impl.player.NoPush;
import ua.babka.util.Instance;

@Mixin(value={ClientPlayerEntity.class})
public abstract class ClientPlayerEntityMixin {
    @Final
    @Shadow
    protected MinecraftClient client;
    @Final
    @Shadow
    public ClientPlayNetworkHandler networkHandler;

    @Shadow
    public abstract void closeScreen();

    @Inject(method={"dropSelectedItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDropSelectedItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        LockSlot lockSlot = Instance.get(LockSlot.class);
        if (lockSlot != null && lockSlot.isCurrentSlotLockedForDrop()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void tick(CallbackInfo ci) {
        new EventPlayerUpdate().post();
    }

    @Inject(method={"tick"}, at={@At(value="RETURN")})
    private void tickReturn(CallbackInfo ci) {
        new EventPostMotion().post();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="HEAD")})
    private void sendMovementPackets(CallbackInfo ci) {
        new EventPlayerSync().post();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="RETURN")})
    private void sendMovementPacketsEnd(CallbackInfo ci) {
        new EventPlayerSyncEnd().post();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;sendSprintingPacket()V", shift=At.Shift.AFTER, ordinal=0)})
    private void onSprint(CallbackInfo ci) {
        new EventSprintSync().post();
    }

    @Redirect(method={"tickMovement"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require=0)
    private boolean onIsUsingItemRedirect(ClientPlayerEntity player) {
        if (player.isUsingItem()) {
            EventNoSlow event = new EventNoSlow();
            event.post();
            return player.isUsingItem() && player.getVehicle() == null && !event.isCancelled();
        }
        return player.isUsingItem() && player.getVehicle() == null;
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    public void pushOutOfBlocks(double x2, double z2, CallbackInfo ci) {
        if (Instance.get(FreeCamera.class).isEnabled() || Instance.get(NoPush.class).isEnabled() && Instance.get(NoPush.class).objects.isEnabled("Блоки")) {
            ci.cancel();
        }
    }

    @Overwrite
    public void closeHandledScreen() {
        EventCloseInv event = new EventCloseInv();
        event.post();
        if (!event.isCancelled()) {
            ScreenHandler screenHandler = ((PlayerEntity)this).currentScreenHandler;
            this.networkHandler.sendPacket((Packet)new CloseHandledScreenC2SPacket(screenHandler.syncId));
        }
        this.closeScreen();
    }
}

