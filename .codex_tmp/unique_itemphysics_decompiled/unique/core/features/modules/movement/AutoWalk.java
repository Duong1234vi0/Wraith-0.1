package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class AutoWalk extends Module {
   private final Setting<AutoWalk.Mode> mode = new Setting<>("Mode", AutoWalk.Mode.Simple);

   public AutoWalk() {
      super("AutoWalk", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      if (this.mode.getValue() == AutoWalk.Mode.Baritone) {
         mc.field_1724
            .field_3944
            .method_45729(
               "#goto "
                  + 3000000.0 * Math.cos(Math.toRadians(mc.field_1724.method_36454() + 90.0F))
                  + " "
                  + 3000000.0 * Math.sin(Math.toRadians(mc.field_1724.method_36454() + 90.0F))
            );
      }
   }

   @Override
   public void onDisable() {
      if (this.mode.getValue() == AutoWalk.Mode.Baritone) {
         mc.field_1724.field_3944.method_45729("#stop");
      }
   }

   @EventHandler
   public void onKey(EventKeyboardInput e) {
      if (this.mode.getValue() == AutoWalk.Mode.Simple) {
         mc.field_1724.field_3913.field_3905 = 1.0F;
      }
   }

   public static enum Mode {
      Simple,
      Baritone;
   }
}
