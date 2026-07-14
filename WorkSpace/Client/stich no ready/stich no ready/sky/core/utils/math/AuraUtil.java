package sky.core.utils.math;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector4f;
import sky.core.handlers.impl.Rotation;
import sky.core.utils.Wrapper;

public final class AuraUtil {
   public static Vector3d getClosestVec(Vector3d vec, AxisAlignedBB AABB) {
      return new Vector3d(MathHelper.clamp(vec.getX(), AABB.minX, AABB.maxX), MathHelper.clamp(vec.getY(), AABB.minY, AABB.maxY), MathHelper.clamp(vec.getZ(), AABB.minZ, AABB.maxZ));
   }

   public static Vector3d getClosestVec(Vector3d vec, Entity entity) {
      return getClosestVec(vec, entity.getBoundingBox());
   }

   public static Vector3d getClosestVec(Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d eyePosVec = Minecraft.player.getEyePosition(Wrapper.mc.getRenderPartialTicks());
      return getClosestVec(eyePosVec, entity).subtract(eyePosVec);
   }

   public static Vector3d calculateCakeElytraVector(LivingEntity target, boolean targetAir, float predictV) {
      Minecraft var10000 = Wrapper.mc;
      double var7 = Minecraft.player.getPosY();
      Minecraft var10001 = Wrapper.mc;
      double yExpand = MathHelper.clamp(var7 + (double)Minecraft.player.getEyeHeight() - target.getPosY(), 0.0, (double)(target.getHeight() / 1.5F));
      Vector3d var8 = target.getPositionVec().add(0.0, yExpand, 0.0);
      var10001 = Wrapper.mc;
      Vector3d vectorPredict = var8.subtract(Minecraft.player.getEyePosition(1.0F)).add(target.getForward().normalize().scale((double)predictV));
      var8 = target.getPositionVec().add(0.0, yExpand, 0.0);
      var10001 = Wrapper.mc;
      Vector3d vectorDefault = var8.subtract(Minecraft.player.getEyePosition(1.0F));
      return targetAir ? vectorPredict : vectorDefault;
   }

   public static Vector3d getVector(Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d eye = Minecraft.player.getEyePosition(1.0F);
      Vector3d raw = (new Vector3d(entity.getPositionVec().x, MathHelper.lerp(MathHelper.clamp(eye.distanceTo(entity.getPositionVec().add(0.0, (double)entity.getEyeHeight(), 0.0)) / 3.0, 0.0, 1.0), entity.getBoundingBox().minY, MathHelper.clamp(eye.y, entity.getBoundingBox().minY, entity.getBoundingBox().maxY)), entity.getPositionVec().z)).subtract(eye);
      var10000 = Wrapper.mc;
      double distance = Minecraft.player.getDistanceEyePos(entity);
      return raw.normalize().scale(distance);
   }

   public static double getStrictDistance(Entity entity) {
      return getClosestVec(entity).length();
   }

   public static double calculateFOVFromCamera(LivingEntity target) {
      Vector4f rotation = calculateRotationFromCamera(target);
      float yawDelta = rotation.z;
      float pitchDelta = rotation.w;
      return Math.sqrt((double)(yawDelta * yawDelta + pitchDelta * pitchDelta));
   }

   public static Vector4f calculateRotationFromCamera(LivingEntity target) {
      Vector3d var10000 = getClosestTargetPoint(target);
      Minecraft var10001 = Wrapper.mc;
      Vector3d vec = var10000.subtract(Minecraft.player.getEyePosition(Wrapper.mc.getRenderPartialTicks()));
      float rawYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
      float rawPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.sqrt(Math.pow(vec.x, 2.0) + Math.pow(vec.z, 2.0)))));
      float yawDelta = MathHelper.wrapDegrees(rawYaw - Rotation.cameraYaw());
      float pitchDelta = rawPitch - Rotation.cameraPitch();
      return new Vector4f(rawYaw, rawPitch, yawDelta, pitchDelta);
   }

   public static Vector3d getClosestTargetPoint(Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      return getClosestTargetPoint(Minecraft.player.getEyePosition(Wrapper.mc.getRenderPartialTicks()), entity, Math.min(entity.getWidth(), entity.getHeight()) / 4.0F);
   }

   public static Vector3d getClosestTargetPoint(Vector3d vec, Entity entity, float point) {
      if (entity == null) {
         return Vector3d.ZERO;
      } else {
         AxisAlignedBB boundingBox = entity.getBoundingBox().grow((double)(-point));
         Vector3d center = boundingBox.getCenter();
         Vector3d closestPoint = Vector3d.ZERO;
         double closestDistance = Double.MAX_VALUE;

         double offsetX;
         double offsetY;
         double offsetZ;
         for(offsetX = 0.0; offsetX <= (boundingBox.maxX - boundingBox.minX) / 2.0; offsetX += 0.1) {
            for(offsetY = 0.0; offsetY <= (boundingBox.maxY - boundingBox.minY) / 2.0; offsetY += 0.1) {
               for(offsetZ = 0.0; offsetZ <= (boundingBox.maxZ - boundingBox.minZ) / 2.0; offsetZ += 0.1) {
                  int[] var14 = new int[]{-1, 1};
                  int var15 = var14.length;

                  for(int var16 = 0; var16 < var15; ++var16) {
                     int signX = var14[var16];
                     int[] var18 = new int[]{-1, 1};
                     int var19 = var18.length;

                     for(int var20 = 0; var20 < var19; ++var20) {
                        int signY = var18[var20];
                        int[] var22 = new int[]{-1, 1};
                        int var23 = var22.length;

                        for(int var24 = 0; var24 < var23; ++var24) {
                           int signZ = var22[var24];
                           double var10000 = center.x + (double)signX * offsetX;
                           var10000 = center.y + (double)signY * offsetY;
                           var10000 = center.z + (double)signZ * offsetZ;
                        }
                     }
                  }
               }
            }
         }

         if (!closestPoint.equals(Vector3d.ZERO)) {
            return closestPoint;
         } else {
            offsetX = MathHelper.clamp(vec.x, boundingBox.minX, boundingBox.maxX);
            offsetY = MathHelper.clamp(vec.y, boundingBox.minY, boundingBox.maxY);
            offsetZ = MathHelper.clamp(vec.z, boundingBox.minZ, boundingBox.maxZ);
            return new Vector3d(offsetX, offsetY, offsetZ);
         }
      }
   }

   public static boolean isJumpBlockedByCeiling() {
      boolean blockAboveHead = isBlockAboveHead();
      boolean cannotMoveUp = !canMoveUp();
      Minecraft var10000 = Wrapper.mc;
      if (!Minecraft.player.isInWater()) {
         var10000 = Wrapper.mc;
         if (!Minecraft.player.isOnLadder()) {
            var10000 = Wrapper.mc;
            if (!Minecraft.player.abilities.isFlying) {
               var10000 = Wrapper.mc;
               return Minecraft.player.isOnGround() && blockAboveHead && cannotMoveUp;
            }
         }
      }

      return false;
   }

   public static boolean isBlockAboveHead() {
      Minecraft var10000 = Wrapper.mc;
      double upHeight = !Minecraft.player.isOnGround() ? 1.5 : 2.5;
      AxisAlignedBB collisionBox = createBoundingBox(upHeight);
      Minecraft var10001 = Wrapper.mc;
      return Wrapper.mc.world.getCollisionShapes(Minecraft.player, collisionBox).iterator().hasNext();
   }

   public static boolean canMoveUp() {
      Minecraft var10000 = Wrapper.mc;
      boolean isCrouchingAndJumping = Minecraft.player.getPose() == Pose.CROUCHING && Wrapper.mc.gameSettings.keyBindJump.isKeyDown();
      double var4;
      if (!isCrouchingAndJumping) {
         var10000 = Wrapper.mc;
         var4 = (double)Minecraft.player.getHeight() + 0.2;
      } else {
         var10000 = Wrapper.mc;
         var4 = (double)Minecraft.player.getHeight() + 0.05;
      }

      double upHeight = var4;
      AxisAlignedBB upMoveBox = createBoundingBox(upHeight);
      Minecraft var10001 = Wrapper.mc;
      return !Wrapper.mc.world.getCollisionShapes(Minecraft.player, upMoveBox).iterator().hasNext();
   }

   private static AxisAlignedBB createBoundingBox(double heightOffset) {
      Minecraft var10002 = Wrapper.mc;
      double var4 = Minecraft.player.getPosX() - 0.3;
      Minecraft var10003 = Wrapper.mc;
      double var5 = Minecraft.player.getPosY();
      Minecraft var10004 = Wrapper.mc;
      var5 += (double)Minecraft.player.getHeight();
      var10004 = Wrapper.mc;
      double var6 = Minecraft.player.getPosZ() - 0.3;
      Minecraft var10005 = Wrapper.mc;
      double var2 = Minecraft.player.getPosX() + 0.3;
      Minecraft var10006 = Wrapper.mc;
      double var3 = Minecraft.player.getPosY() + heightOffset;
      Minecraft var10007 = Wrapper.mc;
      return new AxisAlignedBB(var4, var5, var6, var2, var3, Minecraft.player.getPosZ() + 0.3);
   }

   private AuraUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
