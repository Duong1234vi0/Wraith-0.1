package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1781;
import net.minecraft.class_243;
import net.minecraft.class_746;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class SuperFW extends Module {
   private final Setting<SuperFW.Mode> mode = new Setting<>("Mode", SuperFW.Mode.Grim);
   private final Setting<Float> customSpeed = new Setting<>("Speed", 1.5F, 1.0F, 5.0F, v -> this.mode.getValue() == SuperFW.Mode.Custom);
   private int rocketCooldown = 0;

   public SuperFW() {
      super("SuperFW", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onTick(EventTick event) {
      class_746 player = mc.field_1724;
      if (player != null && player.method_6128()) {
         if (this.rocketCooldown > 0) {
            this.rocketCooldown--;
         }

         int rocketSlot = this.findRocketSlot();
         if (rocketSlot != -1) {
            if (this.rocketCooldown == 0) {
               int oldSlot = player.method_31548().field_7545;
               player.method_31548().field_7545 = rocketSlot;
               mc.field_1761.method_2919(player, class_1268.field_5808);
               player.method_31548().field_7545 = oldSlot;
               this.rocketCooldown = 5;
            }

            class_243 newVel = null;
            class_243 currentVel = player.method_18798();
            class_243 lookVec = player.method_5663();
            float yaw = player.method_36454();
            float pitch = player.method_36455();
            switch ((SuperFW.Mode)this.mode.getValue()) {
               case Grim: {
                  int ff = yaw > 0.0F ? 45 : -45;
                  double acceleration = Math.abs((yaw + ff) % 90.0F - ff) / 45.0F;
                  double boost = 1.0 + 0.3 * acceleration * acceleration;
                  boolean yAcceleration = Math.abs(pitch) > 60.0F;
                  newVel = new class_243(
                     currentVel.field_1352 * boost, yAcceleration ? currentVel.field_1351 * boost : currentVel.field_1351, currentVel.field_1350 * boost
                  );
                  break;
               }
               case BravoHvH: {
                  int ff = yaw > 0.0F ? 45 : -45;
                  double yawNormalized = Math.abs(yaw % 90.0F) / 90.0;
                  double strongMultiplier = 0.26;
                  double weakMultiplier = strongMultiplier / 2.2;
                  double strongBoost = strongMultiplier * yawNormalized * yawNormalized;
                  double weakBoost = weakMultiplier * yawNormalized * yawNormalized;
                  double boost = 0.95 + strongBoost + weakBoost;
                  boolean yAcceleration = Math.abs(pitch) > 60.0F;
                  newVel = new class_243(
                     currentVel.field_1352 * boost, yAcceleration ? currentVel.field_1351 * boost : currentVel.field_1351, currentVel.field_1350 * boost
                  );
                  break;
               }
               case Custom:
                  newVel = lookVec.method_1021(this.customSpeed.getValue().floatValue());
                  if (newVel.method_1033() < currentVel.method_1033()) {
                     newVel = currentVel;
                  }
            }

            player.method_18799(newVel);
         }
      }
   }

   private int findRocketSlot() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for (int i = 0; i < 9; i++) {
            if (mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1781) {
               return i;
            }
         }

         return -1;
      }
   }

   public static enum Mode {
      Grim,
      BravoHvH,
      Custom;
   }
}
