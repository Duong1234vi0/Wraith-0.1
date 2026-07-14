package sky.core.handlers.impl;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventUpdate;
import sky.core.utils.Wrapper;

public class RotationHandler implements Wrapper {
   private static RotationTask currentTask;
   private static float currentAimSpeed;
   private static float currentResetSpeed;
   private static int currentPriority;
   private static int currentTimeout;
   private static int idleTicks;

   @EventTarget
   private void onUpdate(EventUpdate e) {
      ++idleTicks;
      if (currentTask == RotationHandler.RotationTask.AIM && idleTicks > currentTimeout) {
         currentTask = RotationHandler.RotationTask.RESET;
      }

      if (currentTask == RotationHandler.RotationTask.RESET && updateRotation(Rotation.getReported(), currentResetSpeed)) {
         currentTask = RotationHandler.RotationTask.IDLE;
         currentPriority = 0;
         LookHandler.setActive(false);
      }

   }

   public static void update(Rotation rotation, float turnSpeed, int timeout, int priority) {
      update(rotation, turnSpeed, turnSpeed, timeout, priority);
   }

   public static void update(Rotation rotation, float aimSpeed, float resetSpeed, int timeout, int priority) {
      if (currentPriority <= priority) {
         if (currentTask == RotationHandler.RotationTask.IDLE) {
            LookHandler.setActive(true);
         }

         currentAimSpeed = aimSpeed;
         currentResetSpeed = resetSpeed;
         currentTimeout = timeout;
         currentPriority = priority;
         currentTask = RotationHandler.RotationTask.AIM;
         idleTicks = 0;
         updateRotation(rotation, currentAimSpeed);
      }

   }

   private static boolean updateRotation(Rotation rotation, float turnSpeed) {
      Minecraft var10002 = mc;
      Rotation currentRotation = new Rotation(Minecraft.player);
      float yawDelta = MathHelper.wrapDegrees(rotation.getYaw() - currentRotation.getYaw());
      float pitchDelta = rotation.getPitch() - currentRotation.getPitch();
      float totalDelta = Math.abs(yawDelta) + Math.abs(pitchDelta);
      float yawSpeed = totalDelta == 0.0F ? 0.0F : Math.abs(yawDelta / totalDelta) * turnSpeed;
      float pitchSpeed = totalDelta == 0.0F ? 0.0F : Math.abs(pitchDelta / totalDelta) * turnSpeed;
      Minecraft var10000 = mc;
      float newYaw = Minecraft.player.rotationYaw + MathHelper.clamp(yawDelta, -yawSpeed, yawSpeed);
      var10000 = mc;
      float newPitch = Minecraft.player.rotationPitch + MathHelper.clamp(pitchDelta, -pitchSpeed, pitchSpeed);
      var10000 = mc;
      newYaw = applySensitivityPatch(Minecraft.player.rotationYaw, newYaw);
      var10000 = mc;
      newPitch = applySensitivityPatch(Minecraft.player.rotationPitch, newPitch);
      newPitch = MathHelper.clamp(newPitch, -90.0F, 90.0F);
      var10000 = mc;
      Minecraft.player.rotationYaw = newYaw;
      var10000 = mc;
      Minecraft.player.rotationPitch = newPitch;
      var10002 = mc;
      Rotation finalRotation = new Rotation(Minecraft.player);
      idleTicks = 0;
      return (double)finalRotation.getDelta(rotation) < (double)turnSpeed;
   }

   public static float applySensitivityPatch(float lastYaw, float current) {
      double sens = mc.gameSettings.mouseSensitivity * 0.6000000238418579 + 0.20000000298023224;
      double gcd = sens * sens * sens * 8.0;
      return (float)((double)lastYaw + Math.ceil((double)(current - lastYaw) / gcd / 0.15000000596046448) * gcd * 0.15000000596046448);
   }

   public static boolean isRotating() {
      return currentTask != RotationHandler.RotationTask.IDLE;
   }

   static {
      currentTask = RotationHandler.RotationTask.IDLE;
   }

   public static enum RotationTask {
      AIM,
      RESET,
      IDLE;

      // $FF: synthetic method
      private static RotationTask[] $values() {
         return new RotationTask[]{AIM, RESET, IDLE};
      }
   }
}
