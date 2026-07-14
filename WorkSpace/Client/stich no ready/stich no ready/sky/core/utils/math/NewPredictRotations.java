package sky.core.utils.math;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.movement.ElytraTarget;
import sky.core.utils.Wrapper;
import sky.core.utils.component.impl.RotationComponent;

public class NewPredictRotations implements Wrapper {
   public static boolean predictCondition = false;
   private static int movementTicks = 0;
   private static boolean prePredictCondition = false;
   private static final TimeUtil tickStopWatch = new TimeUtil();

   public void updateRotations(LivingEntity target) {
      ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
      Vector3d predictedPos = this.getPredictedPosition(target, ((Float)elytraTarget.getDistance().get()).intValue());
      Minecraft var10000 = mc;
      Vector3d eyePos = Minecraft.player.getEyePosition(mc.getRenderPartialTicks());
      double deltaX = predictedPos.x - eyePos.x;
      double deltaY = predictedPos.y - eyePos.y;
      double deltaZ = predictedPos.z - eyePos.z;
      double horizontalDist = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
      float yawToTarget = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0F;
      float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(deltaY, horizontalDist)));
      RotationComponent.update(new Rotation(yawToTarget, pitchToTarget), 180.0F, 1, 12);
   }

   public static void smartPredict(LivingEntity target) {
      if (tickStopWatch.isReached(50L)) {
         if (target != null) {
            float x = (float)(target.getPosX() - target.prevPosX);
            float y = (float)(target.getPosY() - target.prevPosY);
            float z = (float)(target.getPosZ() - target.prevPosZ);
            float dist = (float)Math.sqrt((double)(x * x + y * y + z * z));
            float finalSpeed = (float)((double)dist * 20.0);
            if (finalSpeed > 20.0F) {
               movementTicks = 0;
               prePredictCondition = false;
               predictCondition = true;
            } else {
               ++movementTicks;
               if (predictCondition) {
                  prePredictCondition = true;
               }

               if (movementTicks >= 3) {
                  predictCondition = false;
                  movementTicks = 0;
                  prePredictCondition = false;
               } else {
                  predictCondition = prePredictCondition;
               }
            }
         }

         tickStopWatch.reset();
      }

   }

   public Vector3d getResolvedPos(LivingEntity target) {
      return target == null ? null : new Vector3d(target.serverX, target.serverY, target.serverZ);
   }

   public Vector3d getPredictedPosition(LivingEntity target, int ticks) {
      AttackAura aura = SkyCore.getInstance().getModuleManager().getAttackAura();
      ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
      Vector3d pos = this.getResolvedPos(target).add(0.0, (double)target.getEyeHeight() * 0.9, 0.0);
      Vector3d velocity = target.getMotion();
      Vector3d lookVec = target.getLookVec();
      float pitch = target.rotationPitch;
      boolean isSpeed = aura.lastSpeed >= 20.0 || aura.lastSpeed != aura.prevSpeed && aura.lastSpeed == 0.0;
      if (elytraTarget.isEnabled()) {
         Minecraft var10000 = mc;
         if (Minecraft.player.isElytraFlying() && isSpeed) {
            velocity = this.simulateElytraTick(velocity, lookVec, pitch, ticks);
            pos = pos.add(velocity);
            return pos;
         }
      }

      return pos;
   }

   public static Vector3d predict(LivingEntity entity, Vector3d pos, int ticks) {
      if (ticks <= 0) {
         return pos;
      } else {
         Vector3d velocity;
         if (!entity.isElytraFlying()) {
            velocity = entity.getMotion();
            return velocity.lengthSquared() < 1.0E-6 ? pos : pos.add(velocity.multiply((double)ticks));
         } else {
            velocity = entity.getMotion();
            if (entity.isElytraFlying() && Math.hypot(entity.prevPosX - entity.getPosX(), entity.prevPosZ - entity.getPosZ()) * 20.0 <= 5.0 && (entity.getPosY() - entity.prevPosY) * 20.0 <= 5.0) {
               return pos;
            } else {
               for(int i = 0; i < ticks; ++i) {
                  Vector3d rotation = entity.getRotationVector();
                  float pitchRad = (float)Math.toRadians((double)entity.rotationPitch);
                  double horizontalSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
                  double velocityLength = velocity.length();
                  float cos = MathHelper.cos(pitchRad);
                  cos = (float)((double)(cos * cos) * Math.min(1.0, rotation.length() / 0.4));
                  velocity = velocity.add(0.0, -0.08 * (-1.0 + (double)cos * 0.75), 0.0);
                  double lift;
                  if (velocity.y < 0.0 && horizontalSpeed > 0.0) {
                     lift = velocity.y * -0.1 * (double)cos;
                     velocity = velocity.add(rotation.x * lift / horizontalSpeed, lift, rotation.z * lift / horizontalSpeed);
                  }

                  if (pitchRad < 0.0F && horizontalSpeed > 0.0) {
                     lift = velocityLength * (double)(-MathHelper.sin(pitchRad)) * 0.04;
                     velocity = velocity.add(-rotation.x * lift / horizontalSpeed, lift * 3.2, -rotation.z * lift / horizontalSpeed);
                  }

                  if (horizontalSpeed > 0.0) {
                     velocity = velocity.add((rotation.x / horizontalSpeed * velocityLength - velocity.x) * 0.1, 0.0, (rotation.z / horizontalSpeed * velocityLength - velocity.z) * 0.1);
                  }

                  velocity = velocity.multiply(0.99, 0.98, 0.99);
                  pos = pos.add(velocity);
               }

               return pos;
            }
         }
      }
   }

   private Vector3d simulateElytraTick(Vector3d velocity, Vector3d lookVec, float pitch, int forward) {
      float pitchRad = pitch * 0.017453292F;
      double lookHorizontalDist = Math.sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z);
      double velocityHorizontalSpeed = Math.sqrt(Entity.horizontalMag(velocity));
      double lookLength = lookVec.length();
      float cosPitch = MathHelper.cos(pitchRad);
      cosPitch = (float)((double)cosPitch * (double)cosPitch * Math.min(1.0, lookLength / 0.4));
      velocity = velocity.add(0.0, 0.08 * (-1.0 + (double)cosPitch * 0.75), 0.0);
      double boost;
      if (velocity.y < 0.0 && lookHorizontalDist > 0.0) {
         boost = velocity.y * -0.1 * (double)cosPitch;
         velocity = velocity.add(lookVec.x * boost / lookHorizontalDist, boost, lookVec.z * boost / lookHorizontalDist);
      }

      if (pitchRad < 0.0F && lookHorizontalDist > 0.0) {
         boost = velocityHorizontalSpeed * (double)(-MathHelper.sin(pitchRad)) * 0.04;
         velocity = velocity.add(-lookVec.x * boost / lookHorizontalDist, boost * 3.2, -lookVec.z * boost / lookHorizontalDist);
      }

      if (lookHorizontalDist > 0.0) {
         velocity = velocity.add((lookVec.x / lookHorizontalDist * velocityHorizontalSpeed - velocity.x) * 0.1, 0.0, (lookVec.z / lookHorizontalDist * velocityHorizontalSpeed - velocity.z) * 0.1);
      }

      Vector3d lastVec = velocity.mul(0.9900000095367432, 0.9800000190734863, 0.9900000095367432);
      return lastVec.scale((double)forward);
   }
}
