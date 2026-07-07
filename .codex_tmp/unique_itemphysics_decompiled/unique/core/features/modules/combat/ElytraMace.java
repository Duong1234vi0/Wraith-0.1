package unique.core.features.modules.combat;

import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_9362;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class ElytraMace extends Module {
   public final Setting<Integer> range = new Setting<>("Range", 100, 1, 256);
   public final Setting<Float> predict = new Setting<>("Predict", 1.5F, 0.1F, 5.0F);
   public final Setting<Boolean> autoSwap = new Setting<>("AutoSwap", true);
   public final Setting<Boolean> swapBack = new Setting<>("SwapBack", true, v -> this.autoSwap.getValue());
   public final Setting<Integer> distanceSwap = new Setting<>("DistanceSwap", 5, 1, 20, v -> this.autoSwap.getValue());
   private boolean attackedThisFall;
   private class_1799 lastChestpiece;
   private class_243 lastTargetPos;
   private class_243 targetVelocity;
   private boolean hasSwappedToArmor;
   private boolean hasSwappedToElytra;
   private int elytraSlot = -1;
   private int armorSlot = -1;

   public ElytraMace() {
      super("ElytraMaceHelper", Module.Category.COMBAT);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (ModuleManager.aura.isEnabled()) {
            class_1309 target = this.getBestTarget();
            if (target == null) {
               this.lastTargetPos = null;
               this.targetVelocity = null;
            } else {
               this.updateTargetVelocity(target);
               class_243 predicted = this.getAdvancedPrediction(target);
               class_243 playerPos = mc.field_1724.method_19538();
               class_243 playerVelocity = mc.field_1724.method_18798();
               double dx = playerPos.field_1352 - predicted.field_1352;
               double dy = playerPos.field_1351 - predicted.field_1351;
               double dz = playerPos.field_1350 - predicted.field_1350;
               double horizontalDist = Math.sqrt(dx * dx + dz * dz);
               if (mc.field_1724.method_6128()) {
                  if (this.autoSwap.getValue()
                     && !this.hasSwappedToArmor
                     && dy > 0.0
                     && dy <= this.distanceSwap.getValue().intValue()
                     && !mc.field_1724.method_24828()) {
                     this.handleArmorSwap(false);
                     this.hasSwappedToArmor = true;
                     this.hasSwappedToElytra = false;
                  }

                  double fallSpeed = Math.abs(playerVelocity.field_1351);
                  double totalDist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                  boolean isAboveTarget = dy > 2.0 && dy < 15.0;
                  boolean isNearHorizontal = horizontalDist <= 3.0;
                  boolean isFallingFast = fallSpeed > 0.4;
                  boolean hasMace = mc.field_1724.method_6047().method_7909() instanceof class_9362;
                  boolean isInRange = totalDist <= 5.0;
                  if (isAboveTarget && isNearHorizontal && isFallingFast && !this.attackedThisFall && hasMace && isInRange) {
                     mc.field_1761.method_2918(mc.field_1724, target);
                     mc.field_1724.method_6104(class_1268.field_5808);
                     this.attackedThisFall = true;
                  }
               }

               if (mc.field_1724.method_24828() || this.isOnBlock()) {
                  this.attackedThisFall = false;
                  if (this.autoSwap.getValue() && this.swapBack.getValue() && !this.hasSwappedToElytra && this.lastChestpiece != null) {
                     this.handleArmorSwap(true);
                     this.hasSwappedToElytra = true;
                     this.hasSwappedToArmor = false;
                  }
               }
            }
         }
      }
   }

   private void updateTargetVelocity(class_1309 target) {
      class_243 currentPos = target.method_19538();
      if (this.lastTargetPos != null) {
         this.targetVelocity = currentPos.method_1020(this.lastTargetPos);
      } else {
         this.targetVelocity = new class_243(
            target.method_23317() - target.field_6014, target.method_23318() - target.field_6036, target.method_23321() - target.field_5969
         );
      }

      this.lastTargetPos = currentPos;
   }

   private class_243 getAdvancedPrediction(class_1297 target) {
      if (this.targetVelocity == null) {
         this.targetVelocity = new class_243(
            target.method_23317() - target.field_6014, target.method_23318() - target.field_6036, target.method_23321() - target.field_5969
         );
      }

      class_243 currentPos = target.method_19538();
      class_243 playerPos = mc.field_1724.method_19538();
      class_243 playerVel = mc.field_1724.method_18798();
      double distanceToTarget = playerPos.method_1022(currentPos);
      double predictFactor = this.predict.getValue().floatValue();
      double targetSpeed = Math.sqrt(
         this.targetVelocity.field_1352 * this.targetVelocity.field_1352 + this.targetVelocity.field_1350 * this.targetVelocity.field_1350
      );
      double playerSpeed = Math.sqrt(playerVel.field_1352 * playerVel.field_1352 + playerVel.field_1350 * playerVel.field_1350);
      double adaptivePredict = predictFactor;
      if (targetSpeed > 0.25) {
         adaptivePredict = predictFactor * (1.0 + targetSpeed * 2.0);
      }

      if (distanceToTarget > 15.0) {
         adaptivePredict *= 1.0 + distanceToTarget / 40.0;
      }

      if (playerSpeed > 1.0) {
         adaptivePredict *= 1.0 + playerSpeed * 0.3;
      }

      double px = currentPos.field_1352 + this.targetVelocity.field_1352 * adaptivePredict;
      double py = currentPos.field_1351 + this.targetVelocity.field_1351 * (adaptivePredict * 0.4);
      double pz = currentPos.field_1350 + this.targetVelocity.field_1350 * adaptivePredict;
      class_243 dir = new class_243(this.targetVelocity.field_1352, 0.0, this.targetVelocity.field_1350);
      if (dir.method_1027() > 0.0) {
         dir = dir.method_1029();
      }

      if (targetSpeed > 0.1) {
         double lead = Math.min(distanceToTarget / 15.0, 3.0);
         px += dir.field_1352 * lead * targetSpeed;
         pz += dir.field_1350 * lead * targetSpeed;
      }

      return new class_243(px, py, pz);
   }

   private void handleArmorSwap(boolean toElytra) {
      class_1799 current = mc.field_1724.method_6118(class_1304.field_6174);
      if (toElytra) {
         if (current.method_7909() != class_1802.field_8833 && this.elytraSlot != -1) {
            this.swapSlot(this.elytraSlot);
            this.lastChestpiece = null;
         }
      } else if (current.method_7909() == class_1802.field_8833) {
         this.lastChestpiece = current;
         this.findSlots();
         if (this.armorSlot != -1) {
            this.swapSlot(this.armorSlot);
         }
      }
   }

   private void findSlots() {
      this.elytraSlot = -1;
      this.armorSlot = -1;

      for (int i = 0; i < 36; i++) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack.method_7909() == class_1802.field_8833 && this.elytraSlot == -1) {
            this.elytraSlot = i;
         }

         if ((stack.method_7909() == class_1802.field_22028 || stack.method_7909() == class_1802.field_8058) && this.armorSlot == -1) {
            this.armorSlot = i;
         }
      }
   }

   private void swapSlot(int slot) {
      if (slot >= 0 && slot < 36) {
         int windowSlot = slot < 9 ? slot + 36 : slot;
         mc.field_1761.method_2906(mc.field_1724.field_7498.field_7763, windowSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(mc.field_1724.field_7498.field_7763, 6, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(mc.field_1724.field_7498.field_7763, windowSlot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   private boolean isOnBlock() {
      class_2338 pos = mc.field_1724.method_24515().method_10074();
      return !mc.field_1687.method_8320(pos).method_26215();
   }

   private class_1309 getBestTarget() {
      List<class_1297> list = mc.field_1687
         .method_8333(
            mc.field_1724,
            mc.field_1724.method_5829().method_1014(this.range.getValue().intValue()),
            e -> e instanceof class_1309 && e.method_5805() && e != mc.field_1724 && mc.field_1724.method_6057(e)
         );
      return list.stream().map(e -> (class_1309)e).filter(e -> e.method_6032() > 0.0F).min(Comparator.comparingDouble(e -> {
         class_243 predicted = this.getAdvancedPrediction(e);
         return mc.field_1724.method_19538().method_1022(predicted) - e.method_6032() / 10.0;
      })).orElse(null);
   }
}
