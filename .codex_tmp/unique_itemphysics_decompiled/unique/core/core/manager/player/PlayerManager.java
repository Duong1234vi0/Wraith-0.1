package unique.core.core.manager.player;

import java.util.ArrayDeque;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1675;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2735;
import net.minecraft.class_2813;
import net.minecraft.class_2815;
import net.minecraft.class_2868;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventFixVelocity;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.events.impl.EventPlayerJump;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.EventTick;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.injection.accesors.IClientPlayerEntity;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.world.ExplosionUtility;

public class PlayerManager implements IManager {
   public float yaw;
   public float pitch;
   public float lastYaw;
   public float lastPitch;
   public float currentPlayerSpeed;
   public float averagePlayerSpeed;
   public int ticksElytraFlying;
   public int serverSideSlot;
   public final Timer switchTimer = new Timer();
   private final ArrayDeque<Float> speedResult = new ArrayDeque<>(20);
   public float bodyYaw;
   public float prevBodyYaw;
   public boolean inInventory;

   @EventHandler(
      priority = 200
   )
   public void onSync(EventSync event) {
      if (!Module.fullNullCheck()) {
         this.yaw = mc.field_1724.method_36454();
         this.pitch = mc.field_1724.method_36455();
         this.lastYaw = ((IClientPlayerEntity)mc.field_1724).getLastYaw();
         this.lastPitch = ((IClientPlayerEntity)mc.field_1724).getLastPitch();
         if (mc.field_1755 == null) {
            this.inInventory = false;
         }

         if (mc.field_1724.method_6128() && mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833) {
            this.ticksElytraFlying++;
         } else {
            this.ticksElytraFlying = 0;
         }
      }
   }

   @EventHandler
   public void onTick(EventTick e) {
      this.currentPlayerSpeed = (float)Math.hypot(
         mc.field_1724.method_23317() - mc.field_1724.field_6014, mc.field_1724.method_23321() - mc.field_1724.field_5969
      );
      if (this.speedResult.size() > 20) {
         this.speedResult.poll();
      }

      this.speedResult.add(this.currentPlayerSpeed);
      float average = 0.0F;

      for (Float value : this.speedResult) {
         average += MathUtility.clamp(value, 0.0F, 20.0F);
      }

      this.averagePlayerSpeed = average / this.speedResult.size();
   }

   @EventHandler(
      priority = -200
   )
   public void postSync(EventPostSync event) {
      if (mc.field_1724 != null) {
         this.prevBodyYaw = this.bodyYaw;
         this.bodyYaw = this.getBodyYaw();
         if (!ModuleManager.rotations.clientLook.getValue()) {
            mc.field_1724.method_36456(this.yaw);
            mc.field_1724.method_36457(this.pitch);
         }

         ModuleManager.rotations.fixRotation = Float.NaN;
      }
   }

   @EventHandler
   public void onJump(EventPlayerJump e) {
      ModuleManager.rotations.onJump(e);
   }

   @EventHandler
   public void onPlayerMove(EventFixVelocity e) {
      ModuleManager.rotations.onPlayerMove(e);
   }

   @EventHandler
   public void modifyVelocity(EventPlayerTravel e) {
      ModuleManager.rotations.modifyVelocity(e);
   }

   @EventHandler
   public void onKeyInput(EventKeyboardInput e) {
      ModuleManager.rotations.onKeyInput(e);
   }

   @EventHandler
   public void onSyncWithServer(@NotNull PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2813) {
         this.inInventory = true;
      }

      if (event.getPacket() instanceof class_2868 slot) {
         this.switchTimer.reset();
         this.serverSideSlot = slot.method_12442();
      }

      if (event.getPacket() instanceof class_2815) {
         this.inInventory = false;
      }
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_2735 slot) {
         this.switchTimer.reset();
         this.serverSideSlot = slot.method_11803();
      }
   }

   private float getBodyYaw() {
      double x = mc.field_1724.method_23317() - mc.field_1724.field_6014;
      double z = mc.field_1724.method_23321() - mc.field_1724.field_5969;
      float offset = this.bodyYaw;
      if (x * x + z * z > 0.0025000002F) {
         offset = (float)(class_3532.method_15349(z, x) * 180.0F / (float)Math.PI - 90.0);
      }

      if (mc.field_1724.field_6251 > 0.0F) {
         offset = ((IClientPlayerEntity)class_310.method_1551().field_1724).getLastYaw();
      }

      float deltaBodyYaw = class_3532.method_15363(
         class_3532.method_15393(
            ((IClientPlayerEntity)class_310.method_1551().field_1724).getLastYaw() - (this.bodyYaw + class_3532.method_15393(offset - this.bodyYaw) * 0.3F)
         ),
         -45.0F,
         75.0F
      );
      return (deltaBodyYaw > 50.0F ? deltaBodyYaw * 0.2F : 0.0F) + ((IClientPlayerEntity)class_310.method_1551().field_1724).getLastYaw() - deltaBodyYaw;
   }

   public boolean checkRtx(float yaw, float pitch, float distance, float wallDistance, Aura.RayTrace rt) {
      if (rt == Aura.RayTrace.OFF) {
         return true;
      } else {
         class_239 result = this.rayTrace(distance, yaw, pitch);
         class_243 startPoint = mc.field_1724.method_19538().method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0);
         double distancePow2 = Math.pow(distance, 2.0);
         if (result != null) {
            distancePow2 = startPoint.method_1025(result.method_17784());
         }

         class_243 rotationVector = this.getRotationVector(pitch, yaw).method_1021(distance);
         class_243 endPoint = startPoint.method_1019(rotationVector);
         class_238 entityArea = mc.field_1724.method_5829().method_18804(rotationVector).method_1009(1.0, 1.0, 1.0);
         double maxDistance = Math.max(distancePow2, Math.pow(wallDistance, 2.0));
         class_3966 ehr;
         if (rt == Aura.RayTrace.OnlyTarget && Aura.target != null) {
            ehr = class_1675.method_18075(
               mc.field_1724, startPoint, endPoint, entityArea, e -> !e.method_7325() && e.method_5863() && e == Aura.target, maxDistance
            );
         } else {
            ehr = class_1675.method_18075(mc.field_1724, startPoint, endPoint, entityArea, e -> !e.method_7325() && e.method_5863(), maxDistance);
         }

         if (ehr != null) {
            boolean allowedWallDistance = startPoint.method_1025(ehr.method_17784()) <= Math.pow(wallDistance, 2.0);
            boolean wallMissing = result == null;
            boolean wallBehindEntity = startPoint.method_1025(ehr.method_17784()) < distancePow2;
            boolean allowWallHit = wallMissing || allowedWallDistance || wallBehindEntity;
            if (allowWallHit && startPoint.method_1025(ehr.method_17784()) <= Math.pow(distance, 2.0)) {
               return ehr.method_17782() == Aura.target || Aura.target == null || rt == Aura.RayTrace.OnlyTarget;
            }
         }

         return false;
      }
   }

   public boolean checkRtx(float yaw, float pitch, float distance, float wallDistance, class_1297 entity) {
      class_239 result = this.rayTrace(distance, yaw, pitch);
      class_243 startPoint = mc.field_1724.method_19538().method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0);
      double distancePow2 = Math.pow(distance, 2.0);
      if (result != null) {
         distancePow2 = startPoint.method_1025(result.method_17784());
      }

      class_243 rotationVector = this.getRotationVector(pitch, yaw).method_1021(distance);
      class_243 endPoint = startPoint.method_1019(rotationVector);
      class_238 entityArea = mc.field_1724.method_5829().method_18804(rotationVector).method_1009(1.0, 1.0, 1.0);
      double maxDistance = Math.max(distancePow2, Math.pow(wallDistance, 2.0));
      class_3966 ehr = class_1675.method_18075(
         mc.field_1724, startPoint, endPoint, entityArea, e -> !e.method_7325() && e.method_5863() && e == entity, maxDistance
      );
      if (ehr != null) {
         boolean allowedWallDistance = startPoint.method_1025(ehr.method_17784()) <= Math.pow(wallDistance, 2.0);
         boolean wallMissing = result == null;
         boolean wallBehindEntity = startPoint.method_1025(ehr.method_17784()) < distancePow2;
         boolean allowWallHit = wallMissing || allowedWallDistance || wallBehindEntity;
         if (allowWallHit && startPoint.method_1025(ehr.method_17784()) <= Math.pow(distance, 2.0)) {
            return ehr.method_17782() == entity;
         }
      }

      return false;
   }

   public class_1297 getRtxTarget(float yaw, float pitch, float distance, boolean ignoreWalls) {
      class_1297 targetedEntity = null;
      class_239 result = ignoreWalls ? null : this.rayTrace(distance, yaw, pitch);
      class_243 vec3d = mc.field_1724.method_19538().method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0);
      double distancePow2 = Math.pow(distance, 2.0);
      if (result != null) {
         distancePow2 = result.method_17784().method_1025(vec3d);
      }

      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * distance, vec3d2.field_1351 * distance, vec3d2.field_1350 * distance);
      class_238 box = mc.field_1724.method_5829().method_18804(vec3d2.method_1021(distance)).method_1009(1.0, 1.0, 1.0);
      class_3966 entityHitResult = class_1675.method_18075(
         mc.field_1724, vec3d, vec3d3, box, entity -> !entity.method_7325() && entity.method_5863(), distancePow2
      );
      if (entityHitResult != null) {
         class_1297 entity2 = entityHitResult.method_17782();
         class_243 vec3d4 = entityHitResult.method_17784();
         double g = vec3d.method_1025(vec3d4);
         if ((g < distancePow2 || result == null) && entity2 instanceof class_1309) {
            return entity2;
         }
      }

      return targetedEntity;
   }

   public class_243 getRtxPoint(float yaw, float pitch, float distance) {
      class_243 vec3d = mc.field_1724.method_19538().method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0);
      double distancePow2 = Math.pow(distance, 2.0);
      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * distance, vec3d2.field_1351 * distance, vec3d2.field_1350 * distance);
      class_238 box = mc.field_1724.method_5829().method_18804(vec3d2.method_1021(distance)).method_1009(1.0, 1.0, 1.0);
      class_3966 entityHitResult = class_1675.method_18075(
         mc.field_1724, vec3d, vec3d3, box, entity -> !entity.method_7325() && entity.method_5863(), distancePow2
      );
      if (entityHitResult != null) {
         class_1297 entity2 = entityHitResult.method_17782();
         class_243 vec3d4 = entityHitResult.method_17784();
         if (entity2 instanceof class_1309) {
            return vec3d4;
         }
      }

      return null;
   }

   public boolean isLookingAtBox(float yaw, float pitch, class_2338 blockPos) {
      class_243 vec3d = mc.field_1724.method_5836(1.0F);
      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * 7.0, vec3d2.field_1351 * 7.0, vec3d2.field_1350 * 7.0);
      class_3965 result = ExplosionUtility.rayCastBlock(new class_3959(vec3d, vec3d3, class_3960.field_17558, class_242.field_1348, mc.field_1724), blockPos);
      return result != null && result.method_17783() == class_240.field_1332 && result.method_17777().equals(blockPos);
   }

   public class_239 rayTrace(double dst, float yaw, float pitch) {
      class_243 vec3d = mc.field_1724.method_5836(1.0F);
      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
      return mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3960.field_17559, class_242.field_1348, mc.field_1724));
   }

   public class_239 getRtxTarget(float yaw, float pitch, double x, double y, double z) {
      class_239 result = this.rayTrace(5.0, yaw, pitch, x, y, z);
      class_243 vec3d = new class_243(x, y, z).method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0);
      double distancePow2 = 25.0;
      if (result != null) {
         distancePow2 = result.method_17784().method_1025(vec3d);
      }

      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * 5.0, vec3d2.field_1351 * 5.0, vec3d2.field_1350 * 5.0);
      class_238 box = new class_238(x - 0.3, y, z - 0.3, x + 0.3, y + 1.8, z + 0.3).method_18804(vec3d2.method_1021(5.0)).method_1009(1.0, 1.0, 1.0);
      class_3966 entityHitResult = class_1675.method_18075(
         mc.field_1724, vec3d, vec3d3, box, entity -> !entity.method_7325() && entity.method_5863(), distancePow2
      );
      if (entityHitResult != null) {
         class_1297 entity2 = entityHitResult.method_17782();
         class_243 vec3d4 = entityHitResult.method_17784();
         double g = vec3d.method_1025(vec3d4);
         if ((g < distancePow2 || result == null) && entity2 instanceof class_1309) {
            return entityHitResult;
         }
      }

      return result;
   }

   public boolean isInWeb() {
      class_238 pBox = mc.field_1724.method_5829();
      class_2338 pBlockPos = class_2338.method_49638(mc.field_1724.method_19538());

      for (int x = pBlockPos.method_10263() - 2; x <= pBlockPos.method_10263() + 2; x++) {
         for (int y = pBlockPos.method_10264() - 1; y <= pBlockPos.method_10264() + 4; y++) {
            for (int z = pBlockPos.method_10260() - 2; z <= pBlockPos.method_10260() + 2; z++) {
               class_2338 bp = new class_2338(x, y, z);
               if (pBox.method_994(new class_238(bp)) && mc.field_1687.method_8320(bp).method_26204() == class_2246.field_10343) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public class_239 rayTrace(double dst, float yaw, float pitch, double x, double y, double z) {
      class_243 vec3d = new class_243(x, y, z);
      class_243 vec3d2 = this.getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
      return mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3960.field_17559, class_242.field_1348, mc.field_1724));
   }

   public static float[] calcAngle(class_243 to) {
      if (to == null) {
         return null;
      } else {
         double difX = to.field_1352 - mc.field_1724.method_33571().field_1352;
         double difY = (to.field_1351 - mc.field_1724.method_33571().field_1351) * -1.0;
         double difZ = to.field_1350 - mc.field_1724.method_33571().field_1350;
         double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
         return new float[]{
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0),
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist)))
         };
      }
   }

   public static class_241 calcAngleVec(class_243 to) {
      if (to == null) {
         return null;
      } else {
         double difX = to.field_1352 - mc.field_1724.method_33571().field_1352;
         double difY = (to.field_1351 - mc.field_1724.method_33571().field_1351) * -1.0;
         double difZ = to.field_1350 - mc.field_1724.method_33571().field_1350;
         double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
         return new class_241(
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0),
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist)))
         );
      }
   }

   @NotNull
   public class_243 getRotationVector(float yaw, float pitch) {
      return new class_243(
         class_3532.method_15374(-pitch * (float) (Math.PI / 180.0)) * class_3532.method_15362(yaw * (float) (Math.PI / 180.0)),
         -class_3532.method_15374(yaw * (float) (Math.PI / 180.0)),
         class_3532.method_15362(-pitch * (float) (Math.PI / 180.0)) * class_3532.method_15362(yaw * (float) (Math.PI / 180.0))
      );
   }

   public static float[] calcAngle(class_243 from, class_243 to) {
      if (to == null) {
         return null;
      } else {
         double difX = to.field_1352 - from.field_1352;
         double difY = (to.field_1351 - from.field_1351) * -1.0;
         double difZ = to.field_1350 - from.field_1350;
         double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
         return new float[]{
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0),
            (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist)))
         };
      }
   }
}
