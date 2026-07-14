package sky.core.utils.player;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import sky.core.utils.Wrapper;

public final class RayTraceUtil {
   public static boolean rayTraceEntity(float yaw, float pitch, double distance, Entity entity, boolean checkBlocks) {
      Minecraft var10000 = Wrapper.mc;
      if (Minecraft.player != null && Wrapper.mc.world != null && entity != null) {
         var10000 = Wrapper.mc;
         Vector3d start = Minecraft.player.getEyePosition(Wrapper.mc.getRenderPartialTicks());
         var10000 = Wrapper.mc;
         Vector3d look = Minecraft.player.getVectorForRotation(pitch, yaw);
         Vector3d end = start.add(look.x * distance, look.y * distance, look.z * distance);
         AxisAlignedBB box = entity.getBoundingBox().grow((double)entity.getCollisionBorderSize());
         Optional entityHit = box.rayTrace(start, end);
         if (!box.contains(start) && entityHit.isEmpty()) {
            return false;
         } else if (!checkBlocks) {
            return true;
         } else {
            Vector3d entityHitVec = box.contains(start) ? start : (Vector3d)entityHit.get();
            double entityDist = start.distanceTo(entityHitVec);
            Minecraft var10006 = Wrapper.mc;
            RayTraceContext blockCtx = new RayTraceContext(start, end, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player);
            BlockRayTraceResult blockResult = Wrapper.mc.world.rayTraceBlocks(blockCtx);
            if (blockResult.getType() == Type.MISS) {
               return true;
            } else {
               return start.distanceTo(blockResult.getHitVec()) >= entityDist - 0.05;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean rayTraceSingleEntityWithCustomBox(float yaw, float pitch, double distance, Entity entity, float offsetValue) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d eyeVec = Minecraft.player.getEyePosition(Wrapper.mc.getRenderPartialTicks());
      Vector3d lookVec = getVectorForRotation(pitch, yaw);
      Vector3d endVec = eyeVec.add(lookVec.scale(distance));
      AxisAlignedBB entityBox = entity.getBoundingBox().expand((double)offsetValue, 0.0, (double)offsetValue);
      return entityBox.contains(eyeVec) || entityBox.rayTrace(eyeVec, endVec).isPresent();
   }

   public static Entity getMouseOver(Entity target, float yaw, float pitch, double distance) {
      Entity entity = Wrapper.mc.getRenderViewEntity();
      if (entity != null && Wrapper.mc.world != null) {
         RayTraceResult objectMouseOver = null;
         boolean flag = distance > 3.0;
         Vector3d startVec = entity.getEyePosition(1.0F);
         Vector3d directionVec = getVectorForRotation(pitch, yaw);
         Vector3d endVec = startVec.add(directionVec.x * distance, directionVec.y * distance, directionVec.z * distance);
         AxisAlignedBB axisalignedbb = target.getBoundingBox().grow((double)target.getCollisionBorderSize());
         EntityRayTraceResult entityraytraceresult = rayTraceEntities(entity, startVec, endVec, axisalignedbb, (p_lambda$getMouseOver$0_0_) -> {
            return !p_lambda$getMouseOver$0_0_.isSpectator() && p_lambda$getMouseOver$0_0_.canBeCollidedWith();
         }, distance);
         if (entityraytraceresult != null) {
            if (flag && startVec.distanceTo(startVec) > distance) {
               objectMouseOver = BlockRayTraceResult.createMiss(startVec, (Direction)null, new BlockPos(startVec));
            }

            if (distance < distance || objectMouseOver == null) {
               objectMouseOver = entityraytraceresult;
            }
         }

         if (objectMouseOver == null) {
            return null;
         } else {
            try {
               return ((EntityRayTraceResult)objectMouseOver).getEntity();
            } catch (ClassCastException var14) {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public static EntityRayTraceResult rayTraceEntities(Entity shooter, Vector3d startVec, Vector3d endVec, AxisAlignedBB boundingBox, Predicate filter, double distance) {
      World world = shooter.world;
      double closestDistance = distance;
      Entity entity = null;
      Vector3d closestHitVec = null;
      Iterator var12 = world.getEntitiesInAABBexcluding(shooter, boundingBox, filter).iterator();

      while(true) {
         while(var12.hasNext()) {
            Entity entity1 = (Entity)var12.next();
            AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double)entity1.getCollisionBorderSize());
            Optional optional = axisalignedbb.rayTrace(startVec, endVec);
            if (axisalignedbb.contains(startVec)) {
               if (closestDistance >= 0.0) {
                  entity = entity1;
                  closestHitVec = startVec;
                  closestDistance = 0.0;
               }
            } else if (optional.isPresent()) {
               Vector3d vector3d1 = (Vector3d)optional.get();
               double d3 = startVec.distanceTo((Vector3d)optional.get());
               if (d3 < closestDistance || closestDistance == 0.0) {
                  boolean flag1 = false;
                  if (!flag1 && entity1.getLowestRidingEntity() == shooter.getLowestRidingEntity()) {
                     if (closestDistance == 0.0) {
                        entity = entity1;
                        closestHitVec = vector3d1;
                     }
                  } else {
                     entity = entity1;
                     closestHitVec = vector3d1;
                     closestDistance = d3;
                  }
               }
            }
         }

         return entity == null ? null : new EntityRayTraceResult(entity, closestHitVec);
      }
   }

   public static boolean rayTraceSingleEntity(float yaw, float pitch, double distance, Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
      var10000 = Wrapper.mc;
      Vector3d lookVec = Minecraft.player.getVectorForRotation(pitch, yaw);
      Vector3d extendedVec = eyeVec.add(lookVec.scale(distance));
      AxisAlignedBB AABB = entity.getBoundingBox();
      return AABB.contains(eyeVec) || AABB.rayTrace(eyeVec, extendedVec).isPresent();
   }

   public static boolean rayTraceSmallHitBox(float yaw, float pitch, double distance, Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
      var10000 = Wrapper.mc;
      Vector3d lookVec = Minecraft.player.getVectorForRotation(pitch, yaw);
      Vector3d extendedVec = eyeVec.add(lookVec.scale(distance));
      AxisAlignedBB originalBB = entity.getBoundingBox();
      float value = 1.6F;
      double centerX = (originalBB.minX + originalBB.maxX) / 2.0;
      double centerY = (originalBB.minY + originalBB.maxY) / 2.0;
      double centerZ = (originalBB.minZ + originalBB.maxZ) / 2.0;
      double halfX = (originalBB.maxX - originalBB.minX) / 2.0 / (double)value;
      double halfY = (originalBB.maxY - originalBB.minY) / 2.0 / 1.2000000476837158;
      double halfZ = (originalBB.maxZ - originalBB.minZ) / 2.0 / (double)value;
      AxisAlignedBB smallBB = new AxisAlignedBB(centerX - halfX, centerY - halfY, centerZ - halfZ, centerX + halfX, centerY + halfY, centerZ + halfZ);
      return smallBB.contains(eyeVec) || smallBB.rayTrace(eyeVec, extendedVec).isPresent();
   }

   public static Vector3d getVectorForRotation(float pitch, float yaw) {
      Minecraft var10000 = Wrapper.mc;
      return Minecraft.player.getVectorForRotation(pitch, yaw);
   }

   private RayTraceUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
