/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1657
 *  net.minecraft.class_1664
 *  net.minecraft.class_243
 *  net.minecraft.class_742
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.PushEvent;
import code.alphadlc.events.player.PlayerTravelEvent;
import code.alphadlc.events.player.SwimmingEvent;
import code.alphadlc.features.impl.render.CustomCape;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1657;
import net.minecraft.class_1664;
import net.minecraft.class_243;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1657.class})
public abstract class PlayerEntityMixin
implements QuickImports {
    @Inject(method={"isPushedByFluids"}, at={@At(value="HEAD")}, cancellable=true)
    public void isPushedByFluids(CallbackInfoReturnable<Boolean> cir) {
        PushEvent event = SafeEvents.dispatch(() -> new PushEvent(PushEvent.Type.WATER));
        if (event != null && event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"isPartVisible"}, at={@At(value="HEAD")}, cancellable=true)
    private void customCape$isPartVisible(class_1664 modelPart, CallbackInfoReturnable<Boolean> cir) {
        if (modelPart != class_1664.field_7559) {
            return;
        }
        class_1657 player = (class_1657)this;
        if (!(player instanceof class_742)) {
            return;
        }
        class_742 clientPlayer = (class_742)player;
        CustomCape customCape = CustomCape.getInstance();
        if (customCape != null && customCape.shouldApply(clientPlayer)) {
            cir.setReturnValue((Object)true);
        }
    }

    @ModifyExpressionValue(method={"attack"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/player/PlayerEntity;getYaw()F")})
    private float hookFixRotation(float original) {
        return AuraMixinHelper.resolveYaw(original);
    }

    @ModifyExpressionValue(method={"travel"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/player/PlayerEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;")})
    public class_243 travelHook(class_243 vec3d) {
        SwimmingEvent event = SafeEvents.dispatch(() -> new SwimmingEvent(vec3d));
        return event != null ? event.getVector() : vec3d;
    }

    @Inject(method={"travel"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTravelPre(class_243 movementInput, CallbackInfo ci) {
        if (PlayerEntityMixin.mc.field_1724 == null) {
            return;
        }
        PlayerTravelEvent event = SafeEvents.dispatch(() -> new PlayerTravelEvent(movementInput, true));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"travel"}, at={@At(value="RETURN")}, cancellable=true)
    private void onTravelPost(class_243 movementInput, CallbackInfo ci) {
        if (PlayerEntityMixin.mc.field_1724 == null) {
            return;
        }
        PlayerTravelEvent event = SafeEvents.dispatch(() -> new PlayerTravelEvent(movementInput, false));
        if (event == null || event.isCancelled()) {
            // empty if block
        }
    }
}
