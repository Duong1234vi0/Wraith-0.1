package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2846;
import net.minecraft.class_2886;
import net.minecraft.class_3532;
import net.minecraft.class_2846.class_2847;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class BowSpam extends Module {
   private final Setting<Integer> ticks = new Setting<>("Delay", 3, 0, 20);
   private final Setting<Boolean> aim = new Setting<>("Aim", false);
   private final Setting<Boolean> rotations = new Setting<>("Rotations", false);
   private final Setting<Float> aimRange = new Setting<>("AimRange", 40.0F, 1.0F, 100.0F);
   private final Setting<Boolean> lockTarget = new Setting<>("LockTarget", false);
   private class_1309 currentTarget = null;

   public BowSpam() {
      super("BowSpam", Module.Category.COMBAT);
   }

   @EventHandler
   public void onSync(EventSync event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.aim.getValue()) {
            if (this.lockTarget.getValue()) {
               if (this.currentTarget == null || !this.isValidTarget(this.currentTarget)) {
                  this.currentTarget = this.getClosestTarget(this.aimRange.getValue());
               }
            } else {
               this.currentTarget = this.getClosestTarget(this.aimRange.getValue());
            }
         } else {
            this.currentTarget = null;
         }

         if (this.rotations.getValue() && this.currentTarget != null) {
            this.rotateTo(this.currentTarget);
         }

         if ((mc.field_1724.method_6079().method_7909() == class_1802.field_8102 || mc.field_1724.method_6047().method_7909() == class_1802.field_8102)
            && mc.field_1724.method_6115()
            && mc.field_1724.method_6048() >= this.ticks.getValue()) {
            this.sendPacket(new class_2846(class_2847.field_12974, class_2338.field_10980, mc.field_1724.method_5735()));
            this.sendSequencedPacket(
               id -> new class_2886(
                  mc.field_1724.method_6079().method_7909() == class_1802.field_8102 ? class_1268.field_5810 : class_1268.field_5808,
                  id,
                  this.currentTarget != null ? this.getYawToTarget(this.currentTarget) : mc.field_1724.method_36454(),
                  this.currentTarget != null ? this.getPitchToTarget(this.currentTarget) : mc.field_1724.method_36455()
               )
            );
            mc.field_1724.method_6075();
         }
      }
   }

   private boolean isValidTarget(class_1309 target) {
      return target != null && target.method_5805() && mc.field_1724.method_5739(target) <= this.aimRange.getValue() && target instanceof class_1657;
   }

   private class_1309 getClosestTarget(float range) {
      class_1309 closest = null;
      double closestDist = range;

      for (class_1309 entity : mc.field_1687
         .method_8390(class_1309.class, mc.field_1724.method_5829().method_1014(range), e -> e.method_5805() && e != mc.field_1724 && e instanceof class_1657)) {
         double dist = mc.field_1724.method_5739(entity);
         if (dist < closestDist) {
            closest = entity;
            closestDist = dist;
         }
      }

      return closest;
   }

   private void rotateTo(class_1309 target) {
      mc.field_1724.method_36456(mc.field_1724.method_36454() + class_3532.method_15393(this.getYawToTarget(target) - mc.field_1724.method_36454()));
      mc.field_1724.method_36457(mc.field_1724.method_36455() + class_3532.method_15393(this.getPitchToTarget(target) - mc.field_1724.method_36455()));
   }

   private float getYawToTarget(class_1309 target) {
      class_243 eyes = mc.field_1724.method_33571();
      class_243 targetPos = target.method_33571();
      double dx = targetPos.field_1352 - eyes.field_1352;
      double dz = targetPos.field_1350 - eyes.field_1350;
      return (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
   }

   private float getPitchToTarget(class_1309 target) {
      class_243 eyes = mc.field_1724.method_33571();
      class_243 targetPos = target.method_33571();
      double dx = targetPos.field_1352 - eyes.field_1352;
      double dy = targetPos.field_1351 - eyes.field_1351;
      double dz = targetPos.field_1350 - eyes.field_1350;
      double distXZ = Math.sqrt(dx * dx + dz * dz);
      return (float)(-Math.toDegrees(Math.atan2(dy, distXZ)));
   }
}
