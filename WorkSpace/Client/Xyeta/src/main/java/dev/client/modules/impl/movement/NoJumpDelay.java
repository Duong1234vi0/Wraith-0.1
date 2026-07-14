package dev.client.modules.impl.movement;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.mixins.other.ILivingEntityMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NoJumpDelay extends Module implements ITickable, IUtil {
   public NoJumpDelay() {
      super(new ModuleInfo("NoJumpDelay", Category.MOVEMENT, "Спаммит прыжками в низком пространстве"));
   }

   public void onTick(TickEvent event) {
      ((ILivingEntityMixin)mc.player).setJumpingCooldown(0);
   }
}
