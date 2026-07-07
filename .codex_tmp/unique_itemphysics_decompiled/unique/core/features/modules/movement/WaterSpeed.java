package unique.core.features.modules.movement;

import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class WaterSpeed extends Module {
   public final Setting<WaterSpeed.Mode> mode = new Setting<>("Mode", WaterSpeed.Mode.FunTime);
   private long lastMoveTime = 0L;

   public WaterSpeed() {
      super("WaterSpeed", Module.Category.MOVEMENT);
   }

   @Override
   public void onUpdate() {
      if (this.mode.getValue() == WaterSpeed.Mode.DolphinGrace) {
         if (mc.field_1724 != null && mc.field_1724.method_5681()) {
            mc.field_1724.method_6092(new class_1293(class_1294.field_5900, 2, 2));
         } else if (mc.field_1724 != null) {
            mc.field_1724.method_6016(class_1294.field_5900);
         }
      } else {
         if (this.mode.getValue() == WaterSpeed.Mode.FunTime) {
            if (mc.field_1724 == null || !mc.field_1724.method_5799()) {
               return;
            }

            boolean isMoving = MovementUtility.isMoving();
            if (isMoving) {
               this.lastMoveTime = System.currentTimeMillis();
            }

            boolean hasDepthStrider = false;
            class_1799 boots = mc.field_1724.method_31548().method_7372(class_1304.field_6166.method_5927());
            if (!boots.method_7960()) {
               String bootsString = boots.toString().toLowerCase();
               String bootsName = boots.method_7964().getString().toLowerCase();
               if (bootsString.contains("depth_strider")
                  || bootsString.contains("aqua_affinity")
                  || bootsName.contains("depth")
                  || bootsName.contains("aqua")
                  || bootsName.contains("water")) {
                  hasDepthStrider = true;
               }
            }

            class_1799 offhand = mc.field_1724.method_6079();
            boolean hasPlayerHead = !offhand.method_7960() && offhand.method_7909() == class_1802.field_8575;
            float speedMultiplier;
            if (hasDepthStrider) {
               speedMultiplier = hasPlayerHead ? 1.04F : 1.043F;
            } else {
               speedMultiplier = 1.046F;
            }

            if (mc.field_1690.field_1894.method_1434()) {
               mc.field_1724
                  .method_18800(
                     mc.field_1724.method_18798().field_1352 * speedMultiplier,
                     mc.field_1724.method_18798().field_1351,
                     mc.field_1724.method_18798().field_1350 * speedMultiplier
                  );
            }

            if (!mc.field_1724.field_5976 && !isMoving && System.currentTimeMillis() - this.lastMoveTime > 300L) {
               if (mc.field_1724.field_6012 % 3 == 0) {
                  mc.field_1724
                     .method_18800(
                        mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 - 0.03, mc.field_1724.method_18798().field_1350
                     );
               } else {
                  mc.field_1724
                     .method_18800(
                        mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 + 0.019, mc.field_1724.method_18798().field_1350
                     );
               }
            }
         }
      }
   }

   @Override
   public void onEnable() {
      this.lastMoveTime = System.currentTimeMillis();
   }

   @Override
   public void onDisable() {
      if (mc.field_1724 != null) {
         mc.field_1724.method_6016(class_1294.field_5900);
      }
   }

   public static enum Mode {
      DolphinGrace,
      FunTime;
   }
}
