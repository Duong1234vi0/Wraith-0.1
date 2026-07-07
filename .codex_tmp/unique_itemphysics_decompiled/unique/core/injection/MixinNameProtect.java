package unique.core.injection;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.misc.NameProtect;

@Mixin({class_1297.class})
public abstract class MixinNameProtect {
   @Inject(
      method = {"getDisplayName"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onGetDisplayName(CallbackInfoReturnable<class_2561> cir) {
      if (ModuleManager.nameProtect != null && ModuleManager.nameProtect.isEnabled()) {
         class_1297 entity = (class_1297)this;
         if (entity instanceof class_1657 player) {
            String realName = player.method_7334().getName();
            String protectedName = NameProtect.getCustomName(realName);
            if (!protectedName.equals(realName)) {
               cir.setReturnValue(class_2561.method_43470(protectedName));
            }
         }
      }
   }
}
