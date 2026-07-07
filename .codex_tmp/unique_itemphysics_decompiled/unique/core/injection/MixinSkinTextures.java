package unique.core.injection;

import net.minecraft.class_2960;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_8685.class})
public class MixinSkinTextures {
   @Inject(
      method = {"texture"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getSkinTextureHook(CallbackInfoReturnable<class_2960> cir) {
   }
}
