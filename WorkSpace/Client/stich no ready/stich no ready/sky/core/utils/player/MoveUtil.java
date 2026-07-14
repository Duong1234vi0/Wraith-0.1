package sky.core.utils.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventInput;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.Wrapper;

public class MoveUtil implements Wrapper {
   public static boolean isMoving() {
      Minecraft var10000 = mc;
      boolean var0;
      if ((double)Minecraft.player.movementInput.moveStrafe == 0.0) {
         var10000 = mc;
         if ((double)Minecraft.player.movementInput.moveForward == 0.0) {
            var0 = false;
            return var0;
         }
      }

      var0 = true;
      return var0;
   }

   public static boolean isInLiquid() {
      Minecraft var10000 = mc;
      boolean var0;
      if (!Minecraft.player.isInWater()) {
         var10000 = mc;
         if (!Minecraft.player.isInLava()) {
            var0 = false;
            return var0;
         }
      }

      var0 = true;
      return var0;
   }

   public static double direction(float rotationYaw, double moveForward, double moveStrafing) {
      if (moveForward < 0.0) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      if (moveForward < 0.0) {
         forward = -0.5F;
      } else if (moveForward > 0.0) {
         forward = 0.5F;
      }

      if (moveStrafing > 0.0) {
         rotationYaw -= 90.0F * forward;
      }

      if (moveStrafing < 0.0) {
         rotationYaw += 90.0F * forward;
      }

      return Math.toRadians((double)rotationYaw);
   }

   public static boolean isMovingTargetEntity() {
      return SkyCore.getInstance().getModuleManager().attackAura.isEnabled() && AttackAura.getTarget() != null && AttackAura.getBpsTarget() > 0.0;
   }

   public static void moveToPosition(EventInput event, Vector3d position, float currentYaw) {
      Minecraft var10001 = mc;
      double deltaX = position.x - Minecraft.player.getPosX();
      var10001 = mc;
      double deltaZ = position.z - Minecraft.player.getPosZ();
      double angleToTarget = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0;
      angleToTarget = MathHelper.wrapDegrees(angleToTarget);
      float bestForward = 0.0F;
      float bestStrafe = 0.0F;
      float minDifference = Float.MAX_VALUE;

      for(float forward = -1.0F; forward <= 1.0F; ++forward) {
         for(float strafe = -1.0F; strafe <= 1.0F; ++strafe) {
            if (forward != 0.0F || strafe != 0.0F) {
               double moveAngle = Math.toDegrees(direction(currentYaw, (double)forward, (double)strafe));
               double difference = Math.abs(angleToTarget - MathHelper.wrapDegrees(moveAngle));
               if (difference < (double)minDifference) {
                  minDifference = (float)difference;
                  bestForward = forward;
                  bestStrafe = strafe;
               }
            }
         }
      }

      event.setForward(bestForward);
      event.setStrafe(bestStrafe);
   }

   public static void fixMovementAura(EventInput event, float yaw) {
      float forward = event.getForward();
      float strafe = event.getStrafe();
      Minecraft var10000 = mc;
      float var15;
      if (Minecraft.player.isElytraFlying()) {
         var10000 = mc;
         var15 = Minecraft.player.rotationYaw;
      } else {
         var15 = yaw;
      }

      double angle = MathHelper.wrapDegrees(Math.toDegrees(direction(var15, (double)forward, (double)strafe)));
      if (forward != 0.0F || strafe != 0.0F) {
         float closestForward = 0.0F;
         float closestStrafe = 0.0F;
         float closestDifference = Float.MAX_VALUE;

         for(float predictedForward = -1.0F; predictedForward <= 1.0F; ++predictedForward) {
            for(float predictedStrafe = -1.0F; predictedStrafe <= 1.0F; ++predictedStrafe) {
               if (predictedStrafe != 0.0F || predictedForward != 0.0F) {
                  var10000 = mc;
                  double predictedAngle = MathHelper.wrapDegrees(Math.toDegrees(direction(Minecraft.player.rotationYaw, (double)predictedForward, (double)predictedStrafe)));
                  double difference = Math.abs(angle - predictedAngle);
                  if (difference < (double)closestDifference) {
                     closestDifference = (float)difference;
                     closestForward = predictedForward;
                     closestStrafe = predictedStrafe;
                  }
               }
            }
         }

         event.setForward(closestForward);
         event.setStrafe(closestStrafe);
      }
   }

   public static void fixMovement(EventInput event, float yaw) {
      float forward = event.getForward();
      float strafe = event.getStrafe();
      Minecraft var10000 = mc;
      float var15;
      if (Minecraft.player.isElytraFlying()) {
         var10000 = mc;
         var15 = Minecraft.player.rotationYaw;
      } else {
         var15 = yaw;
      }

      double angle = MathHelper.wrapDegrees(Math.toDegrees(direction(var15, (double)forward, (double)strafe)));
      if (forward != 0.0F || strafe != 0.0F) {
         float closestForward = 0.0F;
         float closestStrafe = 0.0F;
         float closestDifference = Float.MAX_VALUE;

         for(float predictedForward = -1.0F; predictedForward <= 1.0F; ++predictedForward) {
            for(float predictedStrafe = -1.0F; predictedStrafe <= 1.0F; ++predictedStrafe) {
               if (predictedStrafe != 0.0F || predictedForward != 0.0F) {
                  var10000 = mc;
                  double predictedAngle = MathHelper.wrapDegrees(Math.toDegrees(direction(Minecraft.player.rotationYaw, (double)predictedForward, (double)predictedStrafe)));
                  double difference = Math.abs(angle - predictedAngle);
                  if (difference < (double)closestDifference) {
                     closestDifference = (float)difference;
                     closestForward = predictedForward;
                     closestStrafe = predictedStrafe;
                  }
               }
            }
         }

         event.setForward(closestForward);
         event.setStrafe(closestStrafe);
      }
   }

   public static void setMotion(double speed) {
      if (isMoving()) {
         double yaw = getDirection(true);
         Minecraft var10000 = mc;
         ClientPlayerEntity var4 = Minecraft.player;
         double var10001 = -Math.sin(yaw) * speed;
         Minecraft var10002 = mc;
         var4.setMotion(var10001, Minecraft.player.motion.y, Math.cos(yaw) * speed);
      }
   }

   public static double getDirection(boolean toRadians) {
      Minecraft var10000 = mc;
      float rotationYaw = Minecraft.player.rotationYaw;
      var10000 = mc;
      if (Minecraft.player.moveForward < 0.0F) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      var10000 = mc;
      if (Minecraft.player.moveForward < 0.0F) {
         forward = -0.5F;
      } else {
         var10000 = mc;
         if (Minecraft.player.moveForward > 0.0F) {
            forward = 0.5F;
         }
      }

      var10000 = mc;
      if (Minecraft.player.moveStrafing > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      var10000 = mc;
      if (Minecraft.player.moveStrafing < 0.0F) {
         rotationYaw += 90.0F * forward;
      }

      return toRadians ? Math.toRadians((double)rotationYaw) : (double)rotationYaw;
   }

   public static boolean isBlockUnder(float under) {
      Minecraft var10000 = mc;
      if (Minecraft.player.getPosY() < 0.0) {
         return false;
      } else {
         var10000 = mc;
         AxisAlignedBB aab = Minecraft.player.getBoundingBox().offset(0.0, (double)(-under), 0.0);
         Minecraft var10001 = mc;
         return mc.world.getCollisionShapes(Minecraft.player, aab).toList().isEmpty();
      }
   }
}
