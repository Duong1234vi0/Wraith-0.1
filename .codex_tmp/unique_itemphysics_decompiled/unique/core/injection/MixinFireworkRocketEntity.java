package unique.core.injection;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1299;
import net.minecraft.class_1671;
import net.minecraft.class_1676;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import unique.core.features.modules.movement.ElytraBooster;

@Mixin({class_1671.class})
public abstract class MixinFireworkRocketEntity extends class_1676 {
   @Unique
   private class_243 rotation = class_243.field_1353;

   protected MixinFireworkRocketEntity(class_1299<? extends class_1676> type, class_1937 world) {
      super(type, world);
   }

   @ModifyExpressionValue(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
      )}
   )
   private class_243 capture(class_243 original) {
      this.rotation = original;
      return original;
   }

   @Redirect(
      method = {"tick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;",
         ordinal = 0
      )
   )
   private class_243 redirect(class_243 v, double x, double y, double z) {
      ElytraBooster b = ElytraBooster.INSTANCE;
      class_310 mc = class_310.method_1551();
      if (b != null && b.isEnabled() && mc.field_1724 != null && mc.field_1724.method_6128()) {
         double boost = b.mode.getValue() == ElytraBooster.Mode.Auto ? Math.min(2.2, v.method_1033() * 1.15) : b.boost.getValue().floatValue();
         return this.apply(v, boost);
      } else {
         return this.apply(v, 1.5);
      }
   }

   @Unique
   private class_243 apply(class_243 v, double boost) {
      return v.method_1031(
         this.rotation.field_1352 * 0.1 + (this.rotation.field_1352 * boost - v.field_1352) * 0.5,
         this.rotation.field_1351 * 0.1 + (this.rotation.field_1351 * boost - v.field_1351) * 0.5,
         this.rotation.field_1350 * 0.1 + (this.rotation.field_1350 * boost - v.field_1350) * 0.5
      );
   }
}
