package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.events.impl.EventMove;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class GrimSpeed extends Module {
   public final Setting<GrimSpeed.Mode> mode = new Setting<>("Mode", GrimSpeed.Mode.Distance);
   public Setting<Boolean> pauseInLiquids = new Setting<>("PauseInLiquids", false);
   public Setting<Boolean> pauseWhileSneaking = new Setting<>("PauseWhileSneaking", false);
   public final Setting<Boolean> onlyPlayers = new Setting<>("OnlyPlayers", false);
   public final Setting<Float> speedFactor = new Setting<>("Speed", 8.0F, 1.0F, 15.0F);
   public final Setting<Float> distance = new Setting<>("Distance", 3.0F, 0.5F, 5.0F);
   public final Setting<Boolean> predictMovement = new Setting<>("PredictMovement", true);
   public final Setting<Float> predictionFactor = new Setting<>("PredictionFactor", 2.0F, 1.0F, 5.0F);
   public final Setting<Boolean> smoothMovement = new Setting<>("SmoothMovement", true);
   private class_1297 targetEntity = null;
   private class_243 lastTargetPos = null;
   private class_243 predictedPos = null;
   private double[] lastMotion = new double[]{0.0, 0.0};

   public GrimSpeed() {
      super("GrimSpeed", Module.Category.MOVEMENT);
   }

   private void updateTarget() {
      if (Aura.target == null) {
         this.targetEntity = null;
         this.lastTargetPos = null;
         this.predictedPos = null;
      } else {
         this.targetEntity = Aura.target;
         if (this.targetEntity != null) {
            if (this.lastTargetPos == null) {
               this.predictedPos = this.lastTargetPos = this.targetEntity.method_19538();
            } else {
               class_243 currentPos = this.targetEntity.method_19538();
               class_243 velocity = new class_243(
                  currentPos.field_1352 - this.lastTargetPos.field_1352,
                  currentPos.field_1351 - this.lastTargetPos.field_1351,
                  currentPos.field_1350 - this.lastTargetPos.field_1350
               );
               if (this.predictMovement.getValue()) {
                  this.predictedPos = currentPos.method_1031(
                     velocity.field_1352 * this.predictionFactor.getValue().floatValue(),
                     velocity.field_1351 * this.predictionFactor.getValue().floatValue(),
                     velocity.field_1350 * this.predictionFactor.getValue().floatValue()
                  );
                  if (!mc.field_1687.method_8393((int)this.predictedPos.field_1352 >> 4, (int)this.predictedPos.field_1350 >> 4)) {
                     this.predictedPos = currentPos;
                  }
               } else {
                  this.predictedPos = currentPos;
               }

               this.lastTargetPos = currentPos;
            }
         }
      }
   }

   private boolean isValidTarget(class_1297 entity) {
      if (entity == mc.field_1724) {
         return false;
      } else if (entity instanceof class_1657 && Managers.FRIEND.isFriend((class_1657)entity)) {
         return false;
      } else {
         return this.onlyPlayers.getValue() && !(entity instanceof class_1657) ? false : entity instanceof class_1309 || entity instanceof class_1690;
      }
   }

   private void handleDistanceMode() {
      if (this.targetEntity != null) {
         if (mc.field_1724.field_6235 <= 0) {
            if (MovementUtility.isMoving()) {
               class_243 targetPos = this.predictMovement.getValue() && this.predictedPos != null ? this.predictedPos : this.targetEntity.method_19538();
               double dist = mc.field_1724.method_5707(targetPos);
               if (dist <= this.distance.getValue() * this.distance.getValue()) {
                  float slipperiness = mc.field_1687.method_8320(mc.field_1724.method_23314()).method_26204().method_9499();
                  float horizontalFriction = mc.field_1724.method_24828() ? slipperiness * 0.91F : 0.91F;
                  float verticalFriction = mc.field_1724.method_24828() ? slipperiness : 0.99F;
                  double actualSpeed = this.speedFactor.getValue().floatValue() * 0.01 * horizontalFriction * verticalFriction;
                  double[] directionMotion = this.getDirectionToPoint(mc.field_1724.method_19538(), targetPos, actualSpeed);
                  if (this.smoothMovement.getValue()) {
                     double accelFactor = 0.6;
                     directionMotion[0] = this.lastMotion[0] + (directionMotion[0] - this.lastMotion[0]) * accelFactor;
                     directionMotion[1] = this.lastMotion[1] + (directionMotion[1] - this.lastMotion[1]) * accelFactor;
                  }

                  this.lastMotion[0] = directionMotion[0];
                  this.lastMotion[1] = directionMotion[1];
                  mc.field_1724.method_5762(directionMotion[0], 0.0, directionMotion[1]);
               }
            }
         }
      }
   }

   private double[] getDirectionToPoint(class_243 from, class_243 to, double speed) {
      double dx = to.field_1352 - from.field_1352;
      double dz = to.field_1350 - from.field_1350;
      double len = Math.sqrt(dx * dx + dz * dz);
      return len == 0.0 ? new double[]{0.0, 0.0} : new double[]{dx / len * speed, dz / len * speed};
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
   }

   @Override
   public void onEnable() {
      this.targetEntity = null;
      this.lastTargetPos = null;
      this.predictedPos = null;
      this.lastMotion = new double[]{0.0, 0.0};
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (mc.field_1724.method_52535() && this.pauseInLiquids.getValue() || mc.field_1724.method_5715() && this.pauseWhileSneaking.getValue()) {
         ;
      }
   }

   @EventHandler
   public void modifyVelocity(EventPlayerTravel e) {
      if (this.mode.getValue() == GrimSpeed.Mode.Distance && !e.isPre() && UniqueCore.core.getSetBackTime() > 1000L) {
         this.updateTarget();
         this.handleDistanceMode();
      }
   }

   @EventHandler
   public void onMove(EventMove event) {
      if (mc.field_1724.method_52535() && this.pauseInLiquids.getValue() || mc.field_1724.method_5715() && this.pauseWhileSneaking.getValue()) {
         ;
      }
   }

   public static enum Mode {
      Distance;
   }
}
