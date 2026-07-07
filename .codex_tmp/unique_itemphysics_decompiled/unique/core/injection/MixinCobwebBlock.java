package unique.core.injection;

import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2560;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_2846.class_2847;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.movement.AntiWeb;
import unique.core.utility.player.InteractionUtility;

@Mixin({class_2560.class})
public class MixinCobwebBlock {
   @Inject(
      method = {"onEntityCollision"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onEntityCollisionHook(class_2680 state, class_1937 world, class_2338 pos, class_1297 entity, CallbackInfo ci) {
      if (ModuleManager.antiWeb.isEnabled() && AntiWeb.mode.getValue() == AntiWeb.Mode.Ignore && entity == IManager.mc.field_1724) {
         ci.cancel();
         if (AntiWeb.grim.getValue()) {
            InteractionUtility.sendSequencedPacket(id -> new class_2846(class_2847.field_12973, pos, class_2350.field_11036, id));
         }
      }
   }
}
