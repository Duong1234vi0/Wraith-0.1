/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_243
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_1792.class})
public class ItemMixin {
    @Redirect(method={"raycast"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/player/PlayerEntity;getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"))
    private static class_243 raycastHook(class_1657 player, float pitch, float yaw) {
        if (player == null) {
            return new class_243(0.0, 0.0, 1.0);
        }
        return TurnsConnection.INSTANCE.getRotation().toVector();
    }
}
