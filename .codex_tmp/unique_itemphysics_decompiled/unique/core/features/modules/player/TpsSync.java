package unique.core.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;

public class TpsSync extends Module {
   public TpsSync() {
      super("TpsSync", Module.Category.PLAYER);
   }

   @EventHandler(
      priority = 100
   )
   public void onTick(EventTick e) {
      if (!ModuleManager.timer.isEnabled()) {
         if (Managers.SERVER.getTPS() > 1.0F) {
            UniqueCore.TICK_TIMER = Managers.SERVER.getTPS() / 20.0F;
         } else {
            UniqueCore.TICK_TIMER = 1.0F;
         }
      }
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
   }
}
