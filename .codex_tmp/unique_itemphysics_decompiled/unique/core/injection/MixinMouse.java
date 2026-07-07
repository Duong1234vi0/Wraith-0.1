package unique.core.injection;

import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.events.impl.EventMouse;
import unique.core.features.modules.Module;

@Mixin({class_312.class})
public class MixinMouse {
   @Inject(
      method = {"onMouseButton"},
      at = {@At("HEAD")}
   )
   public void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo ci) {
      if (window == Module.mc.method_22683().method_4490()) {
         if (action == 0) {
            Managers.MODULE.onMoseKeyReleased(button);
         }

         if (action == 1) {
            Managers.MODULE.onMoseKeyPressed(button);
         }

         UniqueCore.EVENT_BUS.post(new EventMouse(button, action));
      }
   }

   @Inject(
      method = {"onMouseScroll"},
      at = {@At("HEAD")}
   )
   private void onMouseScrollHook(long window, double horizontal, double vertical, CallbackInfo ci) {
      if (window == Module.mc.method_22683().method_4490()) {
         UniqueCore.EVENT_BUS.post(new EventMouse((int)vertical, 2));
      }
   }
}
