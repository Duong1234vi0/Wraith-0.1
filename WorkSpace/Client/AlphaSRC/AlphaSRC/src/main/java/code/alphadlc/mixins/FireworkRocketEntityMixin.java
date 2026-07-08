/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.class_1309
 *  net.minecraft.class_1671
 *  net.minecraft.class_243
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 */
package code.alphadlc.mixins;

import code.alphadlc.events.player.FireworkEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_1671.class})
public class FireworkRocketEntityMixin
implements QuickImports {
    @Shadow
    @Nullable
    private class_1309 field_7616;

    @WrapOperation(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;")})
    public class_243 getRotationVectorHook(class_1309 instance, Operation<class_243> original) {
        if (this.field_7616 == FireworkRocketEntityMixin.mc.field_1724) {
            return TurnsConnection.INSTANCE.getMoveRotation().toVector();
        }
        return (class_243)original.call(new Object[]{instance});
    }

    @WrapOperation(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal=0)})
    public class_243 getVelocityHook(class_1309 instance, Operation<class_243> original) {
        if (this.field_7616 == FireworkRocketEntityMixin.mc.field_1724) {
            class_243 velocity = (class_243)original.call(new Object[]{instance});
            FireworkEvent event = SafeEvents.dispatch(() -> new FireworkEvent(velocity));
            return event != null ? event.getVector() : velocity;
        }
        return (class_243)original.call(new Object[]{instance});
    }
}
