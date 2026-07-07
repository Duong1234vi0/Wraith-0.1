package unique.core.injection;

import net.minecraft.class_839;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;

@Mixin({class_839.class})
public class MixinMobSpawnerBlockEntityRenderer {
   @Inject(
      method = {"render(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;Lnet/minecraft/client/render/entity/EntityRenderDispatcher;DD)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderHook(CallbackInfo ci) {
      if (!Module.fullNullCheck() && ModuleManager.noRender.isOn() && ModuleManager.noRender.spawnerEntity.getValue()) {
         ci.cancel();
      }
   }
}
