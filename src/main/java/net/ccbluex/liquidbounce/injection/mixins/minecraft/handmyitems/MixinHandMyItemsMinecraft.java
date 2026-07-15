package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.LivingEntityAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleHandMyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinHandMyItemsMinecraft {

    @Shadow
    public @Nullable LocalPlayer player;

    @Inject(method = "<init>", at = @At("HEAD"))
    private void hmi$initializeRuntime(CallbackInfo ci) {
        HandMyItemsRuntime.initialize();
    }

    @Inject(method = "setLevel", at = @At("TAIL"))
    private void hmi$restoreRuntimeOnJoin(ClientLevel level, CallbackInfo ci) {
        boolean enabled = ModuleHandMyItems.INSTANCE.getEnabled();
        if (enabled && !HandMyItemsRuntime.isActive()) {
            HandMyItemsRuntime.enable();
        } else if (!enabled && HandMyItemsRuntime.isActive()) {
            HandMyItemsRuntime.disable();
        }
    }

    @Inject(
        method = "startAttack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"
        )
    )
    private void hmi$resetAttackSwing(CallbackInfoReturnable<Boolean> cir) {
        if (HandMyItemsRuntime.isActive() && this.player instanceof LivingEntityAccessor accessor) {
            accessor.hMI5_0$resetMainHandSwing(false);
        }
    }

    @Redirect(
        method = "startUseItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"
        )
    )
    private void hmi$resetInteractionSwing(LocalPlayer player, InteractionHand hand) {
        if (HandMyItemsRuntime.isActive() && player instanceof LivingEntityAccessor accessor) {
            if (hand == InteractionHand.MAIN_HAND) {
                accessor.hMI5_0$resetMainHandSwing(true);
            } else {
                accessor.hMI5_0$resetOffHandSwing(true);
            }
        }
        player.swing(hand);
    }
}
