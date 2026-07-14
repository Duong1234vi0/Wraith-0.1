package sky.core.utils.render;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.joml.Vector2f;
import sky.core.SkyCore;
import sky.core.events.EventAspectRatio;
import sky.core.modules.impl.visuals.Removals;
import sky.core.utils.Wrapper;

public class ProjectUtil implements Wrapper {
   public static Vector2f project2D(Vector3d vec) {
      return project2D(vec.x, vec.y, vec.z);
   }

   public static Vector2f project2D(double x, double y, double z) {
      Vector3d camera_pos = mc.getRenderManager().info.getProjectedView();
      Quaternion cameraRotation = mc.getRenderManager().getCameraOrientation().copy();
      cameraRotation.conjugate();
      Vector3f result3f = new Vector3f((float)(camera_pos.x - x), (float)(camera_pos.y - y), (float)(camera_pos.z - z));
      result3f.transform(cameraRotation);
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity instanceof PlayerEntity playerentity) {
         if (!SkyCore.getInstance().getModuleManager().removals.isEnabled() || !Removals.mode.is("Тряска камеры")) {
            hurtCameraEffect(playerentity, result3f);
         }

         if (mc.gameSettings.viewBobbing) {
            calculateViewBobbing(playerentity, result3f);
         }
      }

      double fov = mc.gameRenderer.getFOVModifier(mc.getRenderManager().info, mc.getRenderPartialTicks(), true);
      float aspectRatio = getEffectiveAspectRatio();
      return calculateScreenPosition(result3f, fov, aspectRatio);
   }

   private static void calculateViewBobbing(PlayerEntity playerentity, Vector3f result3f) {
      float walked = playerentity.distanceWalkedModified;
      float f = walked - playerentity.prevDistanceWalkedModified;
      float f1 = -(walked + f * mc.getRenderPartialTicks());
      float f2 = MathHelper.lerp(mc.getRenderPartialTicks(), playerentity.prevCameraYaw, playerentity.cameraYaw);
      Quaternion quaternion = new Quaternion(Vector3f.XP, Math.abs(MathHelper.cos(f1 * 3.1415927F - 0.2F) * f2) * 5.0F, true);
      quaternion.conjugate();
      result3f.transform(quaternion);
      Quaternion quaternion1 = new Quaternion(Vector3f.ZP, MathHelper.sin(f1 * 3.1415927F) * f2 * 3.0F, true);
      quaternion1.conjugate();
      result3f.transform(quaternion1);
      Vector3f bobTranslation = new Vector3f(MathHelper.sin(f1 * 3.1415927F) * f2 * 0.5F, -Math.abs(MathHelper.cos(f1 * 3.1415927F) * f2), 0.0F);
      bobTranslation.setY(-bobTranslation.getY());
      result3f.add(bobTranslation);
   }

   private static void hurtCameraEffect(PlayerEntity playerentity, Vector3f result3f) {
      float partialTicks = mc.getRenderPartialTicks();
      float f = (float)playerentity.hurtTime - partialTicks;
      float f2;
      Quaternion quaternion1;
      if (playerentity.getShouldBeDead()) {
         f2 = Math.min((float)playerentity.deathTime + partialTicks, 20.0F);
         quaternion1 = new Quaternion(Vector3f.ZP, 40.0F - 8000.0F / (f2 + 200.0F), true);
         quaternion1.conjugate();
         result3f.transform(quaternion1);
      }

      if (!(f < 0.0F)) {
         f /= (float)playerentity.maxHurtTime;
         f = MathHelper.sin(f * f * f * f * 3.1415927F);
         f2 = playerentity.attackedAtYaw;
         quaternion1 = new Quaternion(Vector3f.YP, -f2, true);
         quaternion1.conjugate();
         result3f.transform(quaternion1);
         Quaternion quaternion2 = new Quaternion(Vector3f.ZP, -f * 14.0F, true);
         quaternion2.conjugate();
         result3f.transform(quaternion2);
         Quaternion quaternion3 = new Quaternion(Vector3f.ZP, f2, true);
         quaternion3.conjugate();
         result3f.transform(quaternion3);
      }
   }

   private static float getEffectiveAspectRatio() {
      float nativeAspectRatio = (float)mc.getMainWindow().getFramebufferWidth() / (float)mc.getMainWindow().getFramebufferHeight();
      EventAspectRatio event = new EventAspectRatio(nativeAspectRatio);
      EventManager.call(event);
      float effectiveAspect = event.isCancelled() ? nativeAspectRatio : event.getAspectRatio();
      return effectiveAspect / nativeAspectRatio;
   }

   private static Vector2f calculateScreenPosition(Vector3f result3f, double fov, float aspectRatio) {
      float halfHeight = (float)mw.getScaledHeight() / 2.0F;
      float scaleFactor = halfHeight / (result3f.getZ() * (float)Math.tan(Math.toRadians(fov / 2.0)));
      return result3f.getZ() < 0.0F ? new Vector2f(-result3f.getX() * scaleFactor / aspectRatio + (float)mc.getMainWindow().getScaledWidth() / 2.0F, (float)mc.getMainWindow().getScaledHeight() / 2.0F - result3f.getY() * scaleFactor) : new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
   }

   public static AxisAlignedBB getEntityBox(Entity entity, Vector3d vec) {
      AxisAlignedBB var10000;
      Vector3d size;
      double var10002;
      double var10003;
      float var3;
      double var10004;
      double var10005;
      double var10006;
      label12: {
         size = new Vector3d(entity.getBoundingBox().maxX - entity.getBoundingBox().minX, entity.getBoundingBox().maxY - entity.getBoundingBox().minY, entity.getBoundingBox().maxZ - entity.getBoundingBox().minZ);
         var10000 = new AxisAlignedBB;
         var10002 = vec.x - size.x / 2.0;
         var10003 = vec.y;
         var10004 = vec.z - size.z / 2.0;
         var10005 = vec.x + size.x / 2.0;
         var10006 = vec.y + size.y;
         if (entity.isSneaking()) {
            Minecraft var10008 = mc;
            if (!Minecraft.player.abilities.isFlying) {
               var3 = 0.1F;
               break label12;
            }
         }

         var3 = 0.0F;
      }

      var10000.<init>(var10002, var10003, var10004, var10005, var10006 + (double)(0.2F - var3), vec.z + size.z / 2.0);
      return var10000;
   }

   public static Vector3d[] getCorners(AxisAlignedBB AABB) {
      return new Vector3d[]{new Vector3d(AABB.minX, AABB.minY, AABB.minZ), new Vector3d(AABB.minX, AABB.minY, AABB.maxZ), new Vector3d(AABB.minX, AABB.maxY, AABB.minZ), new Vector3d(AABB.minX, AABB.maxY, AABB.maxZ), new Vector3d(AABB.maxX, AABB.minY, AABB.minZ), new Vector3d(AABB.maxX, AABB.minY, AABB.maxZ), new Vector3d(AABB.maxX, AABB.maxY, AABB.minZ), new Vector3d(AABB.maxX, AABB.maxY, AABB.maxZ)};
   }

   public static boolean isInView(Entity entity) {
      if (mc.getRenderViewEntity() == null) {
         return false;
      } else {
         return mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(entity.getBoundingBox()) || entity.ignoreFrustumCheck;
      }
   }

   public static float getPerspectiveScale(Vector3d worldPos, float baseSize) {
      Vector3d camera_pos = mc.getRenderManager().info.getProjectedView();
      Quaternion cameraRotation = mc.getRenderManager().getCameraOrientation().copy();
      cameraRotation.conjugate();
      Vector3f vecToTarget = new Vector3f((float)(camera_pos.x - worldPos.x), (float)(camera_pos.y - worldPos.y), (float)(camera_pos.z - worldPos.z));
      vecToTarget.transform(cameraRotation);
      float depth = vecToTarget.getZ();
      if (depth >= 0.0F) {
         return 0.0F;
      } else {
         double fov = mc.gameRenderer.getFOVModifier(mc.getRenderManager().info, mc.getRenderPartialTicks(), true);
         float scaleFactor = (float)mc.getMainWindow().getScaledHeight() / (2.0F * Math.abs(depth) * (float)Math.tan(Math.toRadians(fov / 2.0)));
         return baseSize * scaleFactor * 0.01F;
      }
   }
}
