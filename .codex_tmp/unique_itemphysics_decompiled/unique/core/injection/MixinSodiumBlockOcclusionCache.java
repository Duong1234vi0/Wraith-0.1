package unique.core.injection;

import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2358;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.render.XRay;

@Pseudo
@Mixin(
   targets = {"me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache"},
   remap = false
)
public class MixinSodiumBlockOcclusionCache {
   @Inject(
      method = {"shouldDrawSide"},
      at = {@At("RETURN")},
      cancellable = true
   )
   void shouldDrawSideHook(class_2680 state, class_1922 view, class_2338 pos, class_2350 facing, CallbackInfoReturnable<Boolean> cir) {
      if (ModuleManager.xray.isEnabled() && ModuleManager.xray.wallHack.getValue()) {
         cir.setReturnValue(XRay.isCheckableOre(state.method_26204()));
      }

      if (ModuleManager.autoAnchor.isEnabled() && state.method_26204() instanceof class_2358) {
         cir.setReturnValue(false);
      }
   }
}
