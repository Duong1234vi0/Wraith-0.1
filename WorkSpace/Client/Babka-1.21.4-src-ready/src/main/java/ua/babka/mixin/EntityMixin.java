package ua.babka.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.event.EventUpdateVelocity;

@Mixin(value={Entity.class})
public abstract class EntityMixin {
    @ModifyExpressionValue(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;isControlledByPlayer()Z")})
    private boolean fixFallDistanceCalculation(boolean original) {
        if (this == MinecraftClient.getInstance().player) {
            return false;
        }
        return original;
    }

    @Inject(method={"updateVelocity"}, at={@At(value="TAIL")})
    private void onUpdateVelocityTail(float speed, Vec3d movementInput, CallbackInfo ci) {
        Entity me = (Entity)this;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null && me.getId() == mc.player.getId()) {
            new EventUpdateVelocity(speed, movementInput).post();
        }
    }
}

