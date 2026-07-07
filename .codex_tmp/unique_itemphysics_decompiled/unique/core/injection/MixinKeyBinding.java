package unique.core.injection;

import net.minecraft.class_304;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_304.class})
public abstract class MixinKeyBinding {
   @Shadow
   public abstract boolean method_1435(class_304 var1);

   @Inject(
      method = {"isPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void pressHook(CallbackInfoReturnable<Boolean> cir) {
   }
}
