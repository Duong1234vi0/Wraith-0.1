package unique.core.injection;

import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3609;
import net.minecraft.class_3610;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2353;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;

@Mixin({class_3609.class})
public abstract class MixinFlowableFluid {
   @Shadow
   protected abstract boolean method_15749(class_1922 var1, class_2338 var2, class_2350 var3);

   @Inject(
      method = {"getVelocity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getVelocityHook(class_1922 world, class_2338 pos, class_3610 state, CallbackInfoReturnable<class_243> cir) {
      if (ModuleManager.noPush.isEnabled() && (Boolean)ModuleManager.noPush.water.getValue()) {
         double d = 0.0;
         double e = 0.0;
         class_2339 mutable = new class_2339();
         class_243 vec3d = new class_243(d, 0.0, e);
         if ((Boolean)state.method_11654(class_3609.field_15902)) {
            for (class_2350 direction2 : class_2353.field_11062) {
               mutable.method_25505(pos, direction2);
               if (this.method_15749(world, mutable, direction2) || this.method_15749(world, mutable.method_10084(), direction2)) {
                  vec3d = vec3d.method_1029().method_1031(0.0, -6.0, 0.0);
                  break;
               }
            }
         }

         cir.setReturnValue(vec3d.method_1029());
      }
   }
}
