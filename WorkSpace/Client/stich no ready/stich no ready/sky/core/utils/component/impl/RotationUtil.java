package sky.core.utils.component.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.handlers.impl.Rotation;
import sky.core.utils.Wrapper;

public final class RotationUtil implements Wrapper {
   private static final double SAFETY_OFFSET = 0.22;

   public static Vector2f calculate(Vector3d fromVec, Vector3d toVec) {
      double TO_DEGREES = 57.29577951308232;
      Vector3d diff = toVec.subtract(fromVec);
      double distance = Math.hypot(diff.x, diff.z);
      float yaw = (float)(MathHelper.atan2(diff.z, diff.x) * 57.29577951308232) - 90.0F;
      float pitch = (float)(-(MathHelper.atan2(diff.y, distance) * 57.29577951308232));
      return new Vector2f(yaw, pitch);
   }

   public static float[] calculateDelta(float currentYaw, float currentPitch, float targetYaw, float targetPitch) {
      float deltaYaw = MathHelper.wrapDegrees(targetYaw - currentYaw);
      float deltaPitch = targetPitch - currentPitch;
      return new float[]{deltaYaw, deltaPitch};
   }

   public static Rotation calculateDelta(Rotation current, Rotation target) {
      float deltaYaw = MathHelper.wrapDegrees(target.getYaw() - current.getYaw());
      float deltaPitch = target.getPitch() - current.getPitch();
      return new Rotation(deltaYaw, deltaPitch);
   }

   public static Vector2f calculate(org.joml.Vector3d fromVec, org.joml.Vector3d toVec) {
      double TO_DEGREES = 57.29577951308232;
      org.joml.Vector3d diff = toVec.sub(fromVec);
      double distance = Math.hypot(diff.x(), diff.z());
      float yaw = (float)(MathHelper.atan2(diff.z(), diff.x()) * 57.29577951308232) - 90.0F;
      float pitch = (float)(-(MathHelper.atan2(diff.y(), distance) * 57.29577951308232));
      return new Vector2f(yaw, pitch);
   }

   public static Vector2f calculate(Entity entity) {
      return calculate(entity.getPositionVec().add(0.0, (double)entity.getEyeHeight(), 0.0));
   }

   public static Vector2f calculate(Vector3d toVec) {
      Minecraft var10000 = mc;
      Vector3d var1 = Minecraft.player.getPositionVec();
      Minecraft var10002 = mc;
      return calculate(var1.add(0.0, (double)Minecraft.player.getEyeHeight(), 0.0), toVec);
   }

   public static Vector2f calculate(Vector3d toVec, Direction dir) {
      double x = toVec.x + 0.5;
      double y = toVec.y + 0.5;
      double z = toVec.z + 0.5;
      x += (double)dir.getDirectionVec().getX() * 0.5;
      y += (double)dir.getDirectionVec().getY() * 0.5;
      z += (double)dir.getDirectionVec().getZ() * 0.5;
      return calculate(new Vector3d(x, y, z));
   }

   public static float getAngleDifference(float dir, float yaw) {
      float f = Math.abs(yaw - dir) % 360.0F;
      return f > 180.0F ? 360.0F - f : f;
   }

   public static Vector3d getEyesPos(Entity entity) {
      return entity.getPositionVec().add(0.0, (double)entity.getEyeHeight(entity.getPose()), 0.0);
   }

   public static float[] calculateAngle(Vector3d to) {
      Minecraft var10000 = mc;
      return calculateAngle(getEyesPos(Minecraft.player), to);
   }

   public static float[] calculateAngle(Vector3d from, Vector3d to) {
      double difX = to.x - from.x;
      double difY = (to.y - from.y) * -1.0;
      double difZ = to.z - from.z;
      double dist = (double)MathHelper.sqrt((float)(difX * difX + difZ * difZ));
      float yD = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0);
      float pD = (float)MathHelper.clamp(MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))), -90.0, 90.0);
      return new float[]{yD, pD};
   }

   public static Vector3f getDirectionVector(float yaw, float pitch) {
      float yawRadians = (float)Math.toRadians((double)yaw);
      float pitchRadians = (float)Math.toRadians((double)pitch);
      float x = -MathHelper.cos(pitchRadians) * MathHelper.sin(yawRadians);
      float y = -MathHelper.sin(pitchRadians);
      float z = MathHelper.cos(pitchRadians) * MathHelper.cos(yawRadians);
      return new Vector3f(x, y, z);
   }

   public static Vector3d findPerfectInternalAimPoint(Entity target, double maxDistance) {
      Minecraft var10000 = mc;
      Vector3d playerEyes = Minecraft.player.getEyePosition(1.0F);
      AxisAlignedBB targetBB = getShrunkHitbox(target.getBoundingBox());
      Vector3d closestPoint = new Vector3d(MathHelper.clamp(playerEyes.x, targetBB.minX, targetBB.maxX), MathHelper.clamp(playerEyes.y, targetBB.minY, targetBB.maxY), MathHelper.clamp(playerEyes.z, targetBB.minZ, targetBB.maxZ));
      if (isStrictlyInside(closestPoint, targetBB)) {
         closestPoint = projectToInnerSurface(playerEyes, targetBB);
      }

      double distance = playerEyes.distanceTo(closestPoint);
      if (distance > maxDistance) {
         Vector3d direction = closestPoint.subtract(playerEyes).normalize();
         closestPoint = playerEyes.add(direction.scale(maxDistance));
         closestPoint = clampToInnerHitbox(closestPoint, targetBB);
      }

      return closestPoint;
   }

   private static AxisAlignedBB getShrunkHitbox(AxisAlignedBB original) {
      return new AxisAlignedBB(original.minX + 0.22, original.minY + 0.22, original.minZ + 0.22, original.maxX - 0.22, original.maxY - 0.22, original.maxZ - 0.22);
   }

   private static boolean isStrictlyInside(Vector3d point, AxisAlignedBB box) {
      return point.x > box.minX && point.x < box.maxX && point.y > box.minY && point.y < box.maxY && point.z > box.minZ && point.z < box.maxZ;
   }

   private static Vector3d projectToInnerSurface(Vector3d from, AxisAlignedBB box) {
      double[] distances = new double[]{from.x - box.minX, box.maxX - from.x, from.y - box.minY, box.maxY - from.y, from.z - box.minZ, box.maxZ - from.z};
      int closestFace = 0;

      for(int i = 1; i < distances.length; ++i) {
         if (distances[i] < distances[closestFace]) {
            closestFace = i;
         }
      }

      double x = from.x;
      double y = from.y;
      double z = from.z;
      switch (closestFace) {
         case 0:
            x = box.minX;
            break;
         case 1:
            x = box.maxX;
            break;
         case 2:
            y = box.minY;
            break;
         case 3:
            y = box.maxY;
            break;
         case 4:
            z = box.minZ;
            break;
         case 5:
            z = box.maxZ;
      }

      return new Vector3d(x, y, z);
   }

   private static Vector3d clampToInnerHitbox(Vector3d point, AxisAlignedBB box) {
      double x = MathHelper.clamp(point.x, box.minX, box.maxX);
      double y = MathHelper.clamp(point.y, box.minY, box.maxY);
      double z = MathHelper.clamp(point.z, box.minZ, box.maxZ);
      return new Vector3d(x, y, z);
   }

   private static boolean isPointInside(Vector3d point, AxisAlignedBB box) {
      return point.x > box.minX && point.x < box.maxX && point.y > box.minY && point.y < box.maxY && point.z > box.minZ && point.z < box.maxZ;
   }

   private static Vector3d findClosestSurfacePoint(Vector3d from, AxisAlignedBB box) {
      double[] distances = new double[]{from.x - box.minX, box.maxX - from.x, from.y - box.minY, box.maxY - from.y, from.z - box.minZ, box.maxZ - from.z};
      int minIndex = 0;

      for(int i = 1; i < distances.length; ++i) {
         if (distances[i] < distances[minIndex]) {
            minIndex = i;
         }
      }

      double x = from.x;
      double y = from.y;
      double z = from.z;
      switch (minIndex) {
         case 0:
            x = box.minX;
            break;
         case 1:
            x = box.maxX;
            break;
         case 2:
            y = box.minY;
            break;
         case 3:
            y = box.maxY;
            break;
         case 4:
            z = box.minZ;
            break;
         case 5:
            z = box.maxZ;
      }

      return new Vector3d(x, y, z);
   }

   private static Vector3d snapToSurface(Vector3d point, AxisAlignedBB box) {
      double x = MathHelper.clamp(point.x, box.minX, box.maxX);
      double y = MathHelper.clamp(point.y, box.minY, box.maxY);
      double z = MathHelper.clamp(point.z, box.minZ, box.maxZ);
      double dx = Math.min(Math.abs(x - box.minX), Math.abs(x - box.maxX));
      double dy = Math.min(Math.abs(y - box.minY), Math.abs(y - box.maxY));
      double dz = Math.min(Math.abs(z - box.minZ), Math.abs(z - box.maxZ));
      if (dx <= dy && dx <= dz) {
         x = Math.abs(x - box.minX) < Math.abs(x - box.maxX) ? box.minX : box.maxX;
      } else if (dy <= dx && dy <= dz) {
         y = Math.abs(y - box.minY) < Math.abs(y - box.maxY) ? box.minY : box.maxY;
      } else {
         z = Math.abs(z - box.minZ) < Math.abs(z - box.maxZ) ? box.minZ : box.maxZ;
      }

      return new Vector3d(x, y, z);
   }

   private static Vector3d ensureOnSurface(Vector3d point, AxisAlignedBB box) {
      double EPSILON = 1.0E-5;
      boolean onX = Math.abs(point.x - box.minX) < 1.0E-5 || Math.abs(point.x - box.maxX) < 1.0E-5;
      boolean onY = Math.abs(point.y - box.minY) < 1.0E-5 || Math.abs(point.y - box.maxY) < 1.0E-5;
      boolean onZ = Math.abs(point.z - box.minZ) < 1.0E-5 || Math.abs(point.z - box.maxZ) < 1.0E-5;
      return !onX && !onY && !onZ ? snapToSurface(point, box) : point;
   }

   private static Vector3d rayTraceToBoxSurface(Vector3d start, Vector3d direction, AxisAlignedBB box) {
      double tMin = Double.NEGATIVE_INFINITY;
      double tMax = Double.POSITIVE_INFINITY;

      for(int i = 0; i < 3; ++i) {
         double min = i == 0 ? box.minX : (i == 1 ? box.minY : box.minZ);
         double max = i == 0 ? box.maxX : (i == 1 ? box.maxY : box.maxZ);
         double origin = i == 0 ? start.x : (i == 1 ? start.y : start.z);
         double dir = i == 0 ? direction.x : (i == 1 ? direction.y : direction.z);
         if (Math.abs(dir) < 1.0E-6) {
            if (origin < min || origin > max) {
               return null;
            }
         } else {
            double t1 = (min - origin) / dir;
            double t2 = (max - origin) / dir;
            if (t1 > t2) {
               double temp = t1;
               t1 = t2;
               t2 = temp;
            }

            tMin = Math.max(tMin, t1);
            tMax = Math.min(tMax, t2);
            if (tMin > tMax) {
               return null;
            }
         }
      }

      return start.add(direction.scale(tMin));
   }

   private static Vector3d correctToBoxSurface(Vector3d point, AxisAlignedBB box) {
      double x = MathHelper.clamp(point.x, box.minX, box.maxX);
      double y = MathHelper.clamp(point.y, box.minY, box.maxY);
      double z = MathHelper.clamp(point.z, box.minZ, box.maxZ);
      double[] distances = new double[]{Math.abs(x - box.minX), Math.abs(x - box.maxX), Math.abs(y - box.minY), Math.abs(y - box.maxY), Math.abs(z - box.minZ), Math.abs(z - box.maxZ)};
      int closestFace = 0;

      for(int i = 1; i < distances.length; ++i) {
         if (distances[i] < distances[closestFace]) {
            closestFace = i;
         }
      }

      switch (closestFace) {
         case 0:
            x = box.minX;
            break;
         case 1:
            x = box.maxX;
            break;
         case 2:
            y = box.minY;
            break;
         case 3:
            y = box.maxY;
            break;
         case 4:
            z = box.minZ;
            break;
         case 5:
            z = box.maxZ;
      }

      return new Vector3d(x, y, z);
   }

   public static Vector2f getClosestAimAngles(Entity target) {
      Minecraft var10000 = mc;
      Vector3d eyePos = Minecraft.player.getEyePosition(1.0F);
      AxisAlignedBB bb = target.getBoundingBox();
      double minX = bb.minX;
      double minY = bb.minY;
      double minZ = bb.minZ;
      double maxX = bb.maxX;
      double maxY = bb.maxY;
      double maxZ = bb.maxZ;
      double midX = (minX + maxX) / 2.0;
      double midY = (minY + maxY) / 2.0;
      double midZ = (minZ + maxZ) / 2.0;
      List points = new ArrayList();
      points.add(new Vector3d(minX, minY, minZ));
      points.add(new Vector3d(minX, minY, maxZ));
      points.add(new Vector3d(minX, maxY, minZ));
      points.add(new Vector3d(minX, maxY, maxZ));
      points.add(new Vector3d(maxX, minY, minZ));
      points.add(new Vector3d(maxX, minY, maxZ));
      points.add(new Vector3d(maxX, maxY, minZ));
      points.add(new Vector3d(maxX, maxY, maxZ));
      points.add(new Vector3d(midX, midY, minZ));
      points.add(new Vector3d(midX, midY, maxZ));
      points.add(new Vector3d(midX, minY, midZ));
      points.add(new Vector3d(midX, maxY, midZ));
      points.add(new Vector3d(minX, midY, midZ));
      points.add(new Vector3d(maxX, midY, midZ));
      float bestFov = Float.MAX_VALUE;
      Vector3d bestPoint = null;
      var10000 = mc;
      float currentYaw = Minecraft.player.rotationYaw;
      var10000 = mc;
      float currentPitch = Minecraft.player.rotationPitch;
      Iterator var26 = points.iterator();

      while(var26.hasNext()) {
         Vector3d point = (Vector3d)var26.next();
         Vector2f aimAngles = calculate(point);
         float fov = calculateFov(currentYaw, currentPitch, aimAngles.x, aimAngles.y);
         if (fov < bestFov) {
            bestFov = fov;
            bestPoint = point;
         }
      }

      if (bestPoint != null) {
         return calculate(bestPoint);
      } else {
         return calculate(new Vector3d(midX, midY, midZ));
      }
   }

   public static Vector2f calculateLimitedAim(Entity target, double maxDistance) {
      Vector3d aimPoint = findPerfectInternalAimPoint(target, maxDistance);
      return calculate(aimPoint);
   }

   public static float calculateFov(float cameraYaw, float cameraPitch, float targetYaw, float targetPitch) {
      Vector3f cameraDirection = getDirectionVector(cameraYaw, cameraPitch);
      Vector3f targetDirection = getDirectionVector(targetYaw, targetPitch);
      float dotProduct = cameraDirection.dot(targetDirection);
      dotProduct = MathHelper.clamp(dotProduct, -1.0F, 1.0F);
      float angleRadians = (float)Math.acos((double)dotProduct);
      return (float)Math.toDegrees((double)angleRadians);
   }

   private RotationUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
