package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;

public class AirStuck extends Module {
   public AirStuck() {
      super("AirStuck", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      if (mc.field_1724 != null) {
         mc.field_1724.method_18800(0.0, 0.0, 0.0);
      }
   }

   @Override
   public void onDisable() {
      if (mc.field_1724 != null) {
         mc.field_1724.field_5960 = false;
      }
   }

   @EventHandler
   public void onTick(EventTick event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         mc.field_1724.method_18800(0.0, 0.0, 0.0);
         mc.field_1724.field_6017 = 0.0F;
         mc.field_1724.field_5960 = true;
         mc.field_1724.field_3913.field_3905 = 0.0F;
         mc.field_1724.field_3913.field_3907 = 0.0F;
         mc.field_1724.field_3913.field_3904 = false;
         mc.field_1724.field_3913.field_3903 = false;
         if (mc.field_1724.field_6012 % 2 == 0) {
            double x = mc.field_1724.method_23317();
            double y = mc.field_1724.method_23318();
            double z = mc.field_1724.method_23321();
            mc.field_1724.method_23327(x, y, z);
         }
      }
   }
}
