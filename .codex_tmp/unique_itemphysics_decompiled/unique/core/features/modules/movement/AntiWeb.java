package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2246;
import net.minecraft.class_2560;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.events.impl.EventCollision;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class AntiWeb extends Module {
   public static final Setting<AntiWeb.Mode> mode = new Setting<>("Mode", AntiWeb.Mode.Solid);
   public static final Setting<Boolean> grim = new Setting<>("Grim", false, v -> mode.is(AntiWeb.Mode.Ignore));
   public static final Setting<Float> timer = new Setting<>("Timer", 20.0F, 1.0F, 50.0F, v -> mode.getValue() == AntiWeb.Mode.Timer);
   public Setting<Float> speed = new Setting<>("Speed", 0.3F, 0.0F, 10.0F, v -> mode.getValue() == AntiWeb.Mode.Fly);
   private boolean timerEnabled = false;

   public AntiWeb() {
      super("AntiWeb", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onPlayerUpdate(PlayerUpdateEvent e) {
      if (Managers.PLAYER.isInWeb()) {
         if (mode.getValue() == AntiWeb.Mode.Timer) {
            if (mc.field_1724.method_24828()) {
               UniqueCore.TICK_TIMER = 1.0F;
            } else {
               UniqueCore.TICK_TIMER = timer.getValue();
               this.timerEnabled = true;
            }
         }

         if (mode.getValue() == AntiWeb.Mode.Fly) {
            double[] dir = MovementUtility.forward(this.speed.getValue().floatValue());
            mc.field_1724.method_18800(dir[0], 0.0, dir[1]);
            if (mc.field_1690.field_1903.method_1434()) {
               mc.field_1724.method_18799(mc.field_1724.method_18798().method_1031(0.0, this.speed.getValue().floatValue(), 0.0));
            }

            if (mc.field_1690.field_1832.method_1434()) {
               mc.field_1724.method_18799(mc.field_1724.method_18798().method_1031(0.0, -this.speed.getValue(), 0.0));
            }
         }
      } else if (this.timerEnabled) {
         this.timerEnabled = false;
         UniqueCore.TICK_TIMER = 1.0F;
      }
   }

   @EventHandler
   public void onCollide(EventCollision e) {
      if (e.getState().method_26204() instanceof class_2560 && mode.getValue() == AntiWeb.Mode.Solid) {
         e.setState(class_2246.field_10566.method_9564());
      }
   }

   public static enum Mode {
      Timer,
      Solid,
      Ignore,
      Fly;
   }
}
