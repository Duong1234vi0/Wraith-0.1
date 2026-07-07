package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class TargetPlus extends Module {
   private final Setting<Float> speed = new Setting<>("Speed", 7.5F, 1.0F, 15.0F);
   private final Setting<Float> range = new Setting<>("Distance", 3.2F, 0.5F, 5.0F);
   private final Setting<Float> predictFactor = new Setting<>("PredictFactor", 1.6F, 0.5F, 4.0F);
   private final Setting<Boolean> predict = new Setting<>("Predict", true);
   private final Setting<Boolean> smooth = new Setting<>("Smooth", true);
   private final Setting<Boolean> onlyPlayers = new Setting<>("OnlyPlayers", true);
   private final Setting<Boolean> pauseLiquids = new Setting<>("PauseInLiquids", false);
   private final Setting<Boolean> pauseSneak = new Setting<>("PauseSneak", false);
   private class_1297 target;
   private class_243 lastTargetPos;
   private class_243 targetVelocity;
   private double motionX;
   private double motionZ;

   public TargetPlus() {
      super("TargetPlus", Module.Category.COMBAT);
   }

   private void updateTarget() {
      class_1297 auraTarget = Aura.target;
      if (!this.isValidTarget(auraTarget)) {
         this.reset();
      } else {
         this.target = auraTarget;
         class_243 pos = this.target.method_19538();
         if (this.lastTargetPos == null) {
            this.lastTargetPos = pos;
            this.targetVelocity = class_243.field_1353;
         } else {
            this.targetVelocity = pos.method_1020(this.lastTargetPos);
            this.lastTargetPos = pos;
         }
      }
   }

   private class_243 getTargetPos() {
      if (this.predict.getValue() && this.targetVelocity != null) {
         class_243 pos = this.target
            .method_19538()
            .method_1031(
               this.targetVelocity.field_1352 * this.predictFactor.getValue().floatValue(),
               0.0,
               this.targetVelocity.field_1350 * this.predictFactor.getValue().floatValue()
            );
         return mc.field_1687.method_8393((int)pos.field_1352 >> 4, (int)pos.field_1350 >> 4) ? pos : this.target.method_19538();
      } else {
         return this.target.method_19538();
      }
   }

   private void move(class_243 to) {
      class_243 from = mc.field_1724.method_19538();
      double dx = to.field_1352 - from.field_1352;
      double dz = to.field_1350 - from.field_1350;
      double dist = Math.sqrt(dx * dx + dz * dz);
      if (!(dist < 0.001)) {
         float slipperiness = mc.field_1687.method_8320(mc.field_1724.method_23314()).method_26204().method_9499();
         float friction = mc.field_1724.method_24828() ? slipperiness * 0.91F : 0.91F;
         double spd = this.speed.getValue().floatValue() * 0.01 * friction;
         double tx = dx / dist * spd;
         double tz = dz / dist * spd;
         if (this.smooth.getValue()) {
            this.motionX = this.motionX + (tx - this.motionX) * 0.55;
            this.motionZ = this.motionZ + (tz - this.motionZ) * 0.55;
         } else {
            this.motionX = tx;
            this.motionZ = tz;
         }

         mc.field_1724.method_5762(this.motionX, 0.0, this.motionZ);
      }
   }

   private boolean isValidTarget(class_1297 e) {
      if (e != null && e != mc.field_1724) {
         if (e instanceof class_1657 p && Managers.FRIEND.isFriend(p)) {
            return false;
         } else {
            return this.onlyPlayers.getValue() && !(e instanceof class_1657) ? false : e instanceof class_1309;
         }
      } else {
         return false;
      }
   }

   private boolean shouldPause() {
      return this.pauseLiquids.getValue() && mc.field_1724.method_52535()
         || this.pauseSneak.getValue() && mc.field_1724.method_5715()
         || mc.field_1724.field_6235 > 0;
   }

   private void reset() {
      this.target = null;
      this.lastTargetPos = null;
      this.targetVelocity = null;
      this.motionX = this.motionZ = 0.0;
   }

   @EventHandler
   public void onTravel(EventPlayerTravel e) {
      if (!e.isPre() && !this.shouldPause() && MovementUtility.isMoving()) {
         if (UniqueCore.core.getSetBackTime() >= 1000L) {
            this.updateTarget();
            if (this.target != null) {
               class_243 pos = this.getTargetPos();
               if (mc.field_1724.method_5707(pos) <= this.range.getValue() * this.range.getValue()) {
                  this.move(pos);
               }
            }
         }
      }
   }

   @Override
   public void onEnable() {
      this.reset();
   }

   @Override
   public void onDisable() {
      this.reset();
      UniqueCore.TICK_TIMER = 1.0F;
   }
}
