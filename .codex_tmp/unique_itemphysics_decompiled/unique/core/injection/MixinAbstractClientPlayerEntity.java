package unique.core.injection;

import net.minecraft.class_2960;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;

@Mixin({class_742.class})
public class MixinAbstractClientPlayerEntity {
   @Inject(
      method = {"getSkinTextures"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void modifySkinTextures(CallbackInfoReturnable<class_8685> cir) {
      if (ModuleManager.capes != null && ModuleManager.capes.isEnabled()) {
         class_742 player = (class_742)this;
         class_2960 customCape = ModuleManager.capes.getCapeTexture(player);
         if (customCape != null) {
            class_8685 original = (class_8685)cir.getReturnValue();
            class_2960 elytraTexture = ModuleManager.capes.elytraTexture.getValue() ? customCape : original.comp_1628();
            class_8685 modified = new class_8685(
               original.comp_1626(), original.comp_1911(), customCape, elytraTexture, original.comp_1629(), original.comp_1630()
            );
            cir.setReturnValue(modified);
         }
      }
   }
}
