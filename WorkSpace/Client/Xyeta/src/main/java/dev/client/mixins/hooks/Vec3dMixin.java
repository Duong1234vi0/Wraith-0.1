package dev.client.mixins.hooks;

import dev.client.mixins.other.IVec3dMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin({Vec3d.class})
public class Vec3dMixin implements IVec3dMixin {
   @Mutable
   @Shadow
   @Final
   public double y;

   public void setY(float y) {
      this.y = (double)y;
   }
}
