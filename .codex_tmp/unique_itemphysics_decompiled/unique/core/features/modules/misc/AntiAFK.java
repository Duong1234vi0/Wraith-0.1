package unique.core.features.modules.misc;

import java.util.concurrent.ThreadLocalRandom;
import meteordevelopment.orbit.EventHandler;
import unique.core.core.Managers;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.events.impl.EventSetting;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.player.MovementUtility;

public class AntiAFK extends Module {
   private final Setting<AntiAFK.Mode> mode = new Setting<>("Mode", AntiAFK.Mode.Simple);
   private final Setting<Boolean> onlyWhenAfk = new Setting<>("OnlyWhenAFK", false);
   private final Setting<Boolean> command = new Setting<>("Command", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Boolean> move = new Setting<>("Move", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Boolean> spin = new Setting<>("Spin", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Float> rotateSpeed = new Setting<>("RotateSpeed", 5.0F, 1.0F, 7.0F, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Boolean> jump = new Setting<>("Jump", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Boolean> swing = new Setting<>("Swing", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Boolean> alwayssneak = new Setting<>("AlwaysSneak", false, v -> this.mode.getValue() == AntiAFK.Mode.Simple);
   private final Setting<Integer> radius = new Setting<>("Radius", 64, 1, 128, v -> this.mode.getValue() == AntiAFK.Mode.Baritone);
   private int step;
   private Timer inactiveTime = new Timer();

   public AntiAFK() {
      super("AntiAFK", Module.Category.MISC);
   }

   @Override
   public void onEnable() {
      if (this.alwayssneak.getValue()) {
         mc.field_1690.field_1832.method_23481(true);
      }

      this.step = 0;
   }

   @EventHandler
   public void onSettingChange(EventSetting e) {
      if (e.getSetting() == this.mode) {
         this.step = 0;
      }
   }

   @EventHandler
   public void onKeyboardInput(EventKeyboardInput e) {
      if (mc.field_1724 != null && this.mode.is(AntiAFK.Mode.Simple) && !MovementUtility.isMoving() && this.move.getValue() && this.isAfk()) {
         float angleToRad = (float)Math.toRadians(9 * (mc.field_1724.field_6012 % 40));
         float sin = (float)Math.clamp(Math.sin(angleToRad), -1.0, 1.0);
         float cos = (float)Math.clamp(Math.cos(angleToRad), -1.0, 1.0);
         mc.field_1724.field_3913.field_3905 = Math.round(sin);
         mc.field_1724.field_3913.field_3907 = Math.round(cos);
      }
   }

   @EventHandler(
      priority = -100
   )
   public void onUpdate(PlayerUpdateEvent e) {
      if (this.mode.is(AntiAFK.Mode.Simple) ? this.isActive() : Managers.PLAYER.currentPlayerSpeed > 0.07) {
         this.inactiveTime.reset();
      }

      if (this.mode.getValue() == AntiAFK.Mode.Simple) {
         if (!this.isAfk()) {
            return;
         }

         if (this.move.getValue()) {
            mc.field_1724.method_5728(false);
         }

         if (this.spin.getValue()) {
            double gcdFix = Math.pow((Double)mc.field_1690.method_42495().method_41753() * 0.6 + 0.2, 3.0) * 1.2;
            float newYaw = mc.field_1724.method_36454() + this.rotateSpeed.getValue();
            mc.field_1724.method_36456((float)(newYaw - (newYaw - mc.field_1724.method_36454()) % gcdFix));
         }

         if (this.jump.getValue() && mc.field_1724.method_24828()) {
            mc.field_1724.method_6043();
         }

         if (this.swing.getValue() && ThreadLocalRandom.current().nextInt(99) == 0) {
            mc.field_1724.method_6104(mc.field_1724.method_6058());
         }

         if (this.command.getValue() && ThreadLocalRandom.current().nextInt(99) == 0) {
            mc.field_1724.field_3944.method_45730("qwerty");
         }
      } else if (this.inactiveTime.every(5000L)) {
         if (this.step > 3) {
            this.step = 0;
         }

         switch (this.step) {
            case 0:
               mc.field_1724.field_3944.method_45729("#goto ~ ~" + this.radius.getValue());
               break;
            case 1:
               mc.field_1724.field_3944.method_45729("#goto ~" + this.radius.getValue() + " ~");
               break;
            case 2:
               mc.field_1724.field_3944.method_45729("#goto ~ ~-" + this.radius.getValue());
               break;
            case 3:
               mc.field_1724.field_3944.method_45729("#goto ~-" + this.radius.getValue() + " ~");
         }

         this.step++;
      }
   }

   @Override
   public void onDisable() {
      if (this.alwayssneak.getValue()) {
         mc.field_1690.field_1832.method_23481(false);
      }

      if (this.mode.getValue() == AntiAFK.Mode.Baritone) {
         mc.field_1724.field_3944.method_45729("#stop");
      }
   }

   private boolean isAfk() {
      return !this.onlyWhenAfk.getValue() || this.inactiveTime.passedS(10.0);
   }

   private boolean isActive() {
      return mc.field_1690.field_1894.method_1434()
         || mc.field_1690.field_1913.method_1434()
         || mc.field_1690.field_1849.method_1434()
         || mc.field_1690.field_1881.method_1434();
   }

   private static enum Mode {
      Simple,
      Baritone;
   }
}
