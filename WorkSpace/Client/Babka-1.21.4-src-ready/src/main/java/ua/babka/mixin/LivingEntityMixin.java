package ua.babka.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.event.EventChangeSprint;
import ua.babka.event.EventManager;
import ua.babka.event.EventOnTravelPost;
import ua.babka.module.impl.player.NoPush;
import ua.babka.module.impl.render.SwingAnimations;
import ua.babka.util.Instance;

@Mixin(value={LivingEntity.class})
public class LivingEntityMixin {
    @Inject(method={"pushAwayFrom"}, at={@At(value="HEAD")}, cancellable=true)
    private void cancelPushAway(Entity entity, CallbackInfo ci) {
        if (Instance.get(NoPush.class).isEnabled() && Instance.get(NoPush.class).objects.isEnabled("Игроки")) {
            ci.cancel();
        }
    }

    @Inject(method={"setSprinting"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSetSprinting(boolean sprinting, CallbackInfo ci) {
        if (this instanceof ClientPlayerEntity && sprinting) {
            EventChangeSprint event = new EventChangeSprint(true);
            event.post();
            if (!event.isSprinting()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"getHandSwingDuration"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetHandSwingDuration(CallbackInfoReturnable<Integer> cir) {
        SwingAnimations swing = Instance.get(SwingAnimations.class);
        if (swing != null && swing.isEnabled()) {
            int speed = (int)swing.speed.getValue();
            cir.setReturnValue((Object)(25 - speed * 2));
        }
    }

    @Inject(method={"calcGlidingVelocity"}, at={@At(value="RETURN")}, cancellable=true)
    private void onCalcGlidingVelocity(Vec3d oldVelocity, CallbackInfoReturnable<Vec3d> cir) {
        EventOnTravelPost event = new EventOnTravelPost(oldVelocity.multiply((double)0.99f, (double)0.98f, (double)0.99f));
        EventManager.post(event);
        cir.setReturnValue((Object)event.getOldVelocity());
    }
}

