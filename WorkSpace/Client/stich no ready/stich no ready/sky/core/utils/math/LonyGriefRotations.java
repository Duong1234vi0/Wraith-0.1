package sky.core.utils.math;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.utils.Wrapper;
import sky.core.utils.component.impl.SensUtil;

public class LonyGriefRotations implements Wrapper {
   public Vector2f calculateRotation(LivingEntity target, Vector2f rotation) {
      Vector3d pos = this.calculatePoint(target);
      float shortestYawPath = (float)(((Math.toDegrees(Math.atan2(pos.z, pos.x)) - 90.0 - (double)rotation.x) % 360.0 + 540.0) % 360.0 - 180.0);
      float findPitch = (float)Math.min(90.0, -Math.toDegrees(Math.atan2(pos.y, Math.hypot(pos.x, pos.z))));
      float targetYaw = SensUtil.getSens(rotation.x + shortestYawPath);
      float targetPitch = SensUtil.getSens(MathHelper.clamp(findPitch, -90.0F, 90.0F));
      return new Vector2f(targetYaw, targetPitch);
   }

   private Vector3d calculatePoint(LivingEntity target) {
      Vector3d var10000 = target.getBoundingBox().getCenter();
      Minecraft var10001 = mc;
      return var10000.subtract(Minecraft.player.getEyePosition(1.0F));
   }

   public Vector2f getRotations(Vector3d vec3d) {
      return this.getRotations(vec3d.x, vec3d.y, vec3d.z);
   }

   public Vector2f getRotations(double x, double y, double z) {
      Minecraft var10001 = mc;
      double deltaX = x - Minecraft.player.getPosX();
      var10001 = mc;
      double deltaY = y - Minecraft.player.getPosYEye();
      var10001 = mc;
      double deltaZ = z - Minecraft.player.getPosZ();
      double distance = (double)MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
      float yaw = (float)(MathHelper.atan2(deltaZ, deltaX) * 57.29577951308232 - 90.0);
      float pitch = (float)(-MathHelper.atan2(deltaY, distance) * 57.29577951308232);
      return new Vector2f(yaw, pitch);
   }

   public Vector3d predict(LivingEntity entity, Vector3d pos, int ticks) {
      Vector3d velocity = entity.getMotion();
      if (entity.isElytraFlying() && Math.hypot(entity.prevPosX - entity.getPosX(), entity.prevPosZ - entity.getPosZ()) * 20.0 <= 5.0 && (entity.getPosY() - entity.prevPosY) * 20.0 <= 5.0) {
         return pos;
      } else {
         for(int i = 0; i < ticks; ++i) {
            Vector3d eyePos = entity.getEyePosition(1.0F);
            float pitchRad = (float)Math.toRadians((double)entity.rotationPitch);
            double horizontalSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
            double velocityLength = velocity.length();
            float cos = MathHelper.cos(pitchRad);
            cos = (float)((double)(cos * cos) * Math.min(1.0, eyePos.length() / 0.4));
            velocity = velocity.add(0.0, -0.08 * (-1.0 + (double)cos * 0.75), 0.0);
            double lift;
            Vector3d lookVec;
            if (velocity.y < 0.0 && horizontalSpeed > 0.0) {
               lift = velocity.y * -0.1 * (double)cos;
               lookVec = entity.getLook(1.0F);
               velocity = velocity.add(lookVec.x * lift / horizontalSpeed, lift, lookVec.z * lift / horizontalSpeed);
            }

            if (pitchRad < 0.0F && horizontalSpeed > 0.0) {
               lift = velocityLength * (double)(-MathHelper.sin(pitchRad)) * 0.04;
               lookVec = entity.getLook(1.0F);
               velocity = velocity.add(-lookVec.x * lift / horizontalSpeed, lift * 3.2, -lookVec.z * lift / horizontalSpeed);
            }

            if (horizontalSpeed > 0.0) {
               Vector3d lookVec = entity.getLook(1.0F);
               velocity = velocity.add((lookVec.x / horizontalSpeed * velocityLength - velocity.x) * 0.1, 0.0, (lookVec.z / horizontalSpeed * velocityLength - velocity.z) * 0.1);
            }

            velocity = velocity.mul(0.99, 0.98, 0.99);
            pos = pos.add(velocity);
         }

         return pos;
      }
   }
}
