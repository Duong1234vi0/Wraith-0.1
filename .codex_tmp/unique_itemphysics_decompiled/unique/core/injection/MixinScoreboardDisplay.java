package unique.core.injection;

import net.minecraft.class_2561;
import net.minecraft.class_9011;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.misc.NameProtect;

@Mixin({class_9011.class})
public abstract class MixinScoreboardDisplay {
   @Inject(
      method = {"name"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onName(CallbackInfoReturnable<class_2561> cir) {
      if (ModuleManager.nameProtect != null && ModuleManager.nameProtect.isEnabled()) {
         class_2561 original = (class_2561)cir.getReturnValue();
         if (original != null) {
            String content = original.getString();
            String protectedName = NameProtect.getCustomName(content);
            if (!protectedName.equals(content)) {
               cir.setReturnValue(class_2561.method_43470(protectedName));
            }
         }
      }
   }
}
