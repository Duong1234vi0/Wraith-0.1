package unique.core.injection;

import net.minecraft.class_337;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.manager.client.ModuleManager;

@Mixin({class_337.class})
public class MixinBossBarHud {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void render(CallbackInfo ci) {
      if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.bossbar.getValue()) {
         ci.cancel();
      }
   }
}
