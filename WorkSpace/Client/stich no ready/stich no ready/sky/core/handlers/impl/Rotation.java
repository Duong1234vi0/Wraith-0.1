package sky.core.handlers.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector2f;
import sky.core.utils.Wrapper;
import sky.core.utils.player.RayTraceUtil;

public class Rotation implements Wrapper {
   private float yaw;
   private float pitch;

   public Rotation(Entity entity) {
      this.yaw = entity.rotationYaw;
      this.pitch = entity.rotationPitch;
   }

   public float getDelta(Rotation target) {
      float yawDelta = MathHelper.wrapDegrees(target.getYaw() - this.yaw);
      float pitchDelta = target.getPitch() - this.pitch;
      return (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
   }

   public static Vector2f camera() {
      return new Vector2f(cameraYaw(), cameraPitch());
   }

   public static float cameraYaw() {
      return MathHelper.wrapDegrees(mc.gameRenderer.getActiveRenderInfo().getYaw() + (float)(mc.gameRenderer.getActiveRenderInfo().isThirdPersonReverse() ? 180 : 0));
   }

   public static RayTraceResult rayTrace(double rayTraceDistance, float yaw, float pitch, Entity entity, RayTraceContext.BlockMode mode) {
      Minecraft var10000 = mc;
      Vector3d startVec = Minecraft.player.getEyePosition(1.0F);
      Vector3d directionVec = RayTraceUtil.getVectorForRotation(pitch, yaw);
      Vector3d endVec = startVec.add(directionVec.x * rayTraceDistance, directionVec.y * rayTraceDistance, directionVec.z * rayTraceDistance);
      return mc.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, mode, FluidMode.NONE, entity));
   }

   public static Rotation getReported() {
      Minecraft var10002 = mc;
      float var0 = Minecraft.player.getLastReportedYaw();
      Minecraft var10003 = mc;
      return new Rotation(var0, Minecraft.player.getLastReportedPitch());
   }

   public static float cameraPitch() {
      return (float)(mc.gameRenderer.getActiveRenderInfo().isThirdPersonReverse() ? -1 : 1) * mc.gameRenderer.getActiveRenderInfo().getPitch();
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Rotation)) {
         return false;
      } else {
         Rotation other = (Rotation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getYaw(), other.getYaw()) != 0) {
            return false;
         } else {
            return Float.compare(this.getPitch(), other.getPitch()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Rotation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getYaw());
      result = result * 59 + Float.floatToIntBits(this.getPitch());
      return result;
   }

   public String toString() {
      float var10000 = this.getYaw();
      return "Rotation(yaw=" + var10000 + ", pitch=" + this.getPitch() + ")";
   }

   public Rotation() {
   }

   public Rotation(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }
}
