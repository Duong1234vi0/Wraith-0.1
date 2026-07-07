package unique.core.injection;

import java.util.List;
import net.minecraft.class_1087;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_777;
import net.minecraft.class_916;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.render.ItemPhysics;

@Mixin({class_916.class})
public abstract class MixinItemEntityRenderer {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRender(class_1542 entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      ItemPhysics itemPhysics = ModuleManager.itemPhysics;
      if (itemPhysics != null && itemPhysics.isEnabled()) {
         class_310 mc = class_310.method_1551();
         class_1799 stack = entity.method_6983();
         class_1087 model = mc.method_1480().method_4019(stack, mc.field_1687, null, entity.method_5628());
         List<class_777> quads = model.method_4707(null, null, mc.field_1687.field_9229);
         itemPhysics.onRenderItemEntity(entity, matrices, vertexConsumers, light, tickDelta, quads);
         ci.cancel();
      }
   }
}
