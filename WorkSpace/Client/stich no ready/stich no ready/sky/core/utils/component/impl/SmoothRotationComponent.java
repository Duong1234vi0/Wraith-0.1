package sky.core.utils.component.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.LookHandler;
import sky.core.handlers.impl.Rotation;
import sky.core.utils.component.Component;
import sky.core.utils.component.Instance;

public class SmoothRotationComponent extends Component {
   private RotationTask currentTask;
   private float currentYawSpeed;
   private float currentPitchSpeed;
   private float currentYawReturnSpeed;
   private float currentPitchReturnSpeed;
   private int currentPriority;
   private int currentTimeout;
   private int idleTicks;
   private Rotation targetRotation;

   public SmoothRotationComponent() {
      this.currentTask = SmoothRotationComponent.RotationTask.IDLE;
   }

   public static SmoothRotationComponent getInstance() {
      return (SmoothRotationComponent)Instance.getComponent(SmoothRotationComponent.class);
   }

   private void resetRotation() {
      Rotation targetRotation = new Rotation(LookHandler.getFreeYaw(), LookHandler.getFreePitch());
      if (this.updateRotation(targetRotation, this.currentYawReturnSpeed(), this.currentPitchReturnSpeed())) {
         this.stopRotation();
      }

   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (this.currentTask().equals(SmoothRotationComponent.RotationTask.AIM) && this.idleTicks() > this.currentTimeout()) {
         this.currentTask(SmoothRotationComponent.RotationTask.RESET);
      }

      if (this.currentTask().equals(SmoothRotationComponent.RotationTask.RESET)) {
         this.resetRotation();
      }

      ++this.idleTicks;
   }

   public static void update(Rotation target, float yawSpeed, float pitchSpeed, float yawReturnSpeed, float pitchReturnSpeed, int timeout, int priority, boolean clientRotation) {
      SmoothRotationComponent instance = getInstance();
      if (instance.currentPriority() <= priority) {
         if (instance.currentTask().equals(SmoothRotationComponent.RotationTask.IDLE) && !clientRotation) {
            LookHandler.setActive(true);
         }

         instance.currentYawSpeed(yawSpeed);
         instance.currentPitchSpeed(pitchSpeed);
         instance.currentYawReturnSpeed(yawReturnSpeed);
         instance.currentPitchReturnSpeed(pitchReturnSpeed);
         instance.currentTimeout(timeout);
         instance.currentPriority(priority);
         instance.currentTask(SmoothRotationComponent.RotationTask.AIM);
         instance.targetRotation(target);
         instance.updateRotation(target, yawSpeed, pitchSpeed);
      }
   }

   public static void update(Rotation targetRotation, float turnSpeed, float returnSpeed, int timeout, int priority) {
      update(targetRotation, turnSpeed, turnSpeed, returnSpeed, returnSpeed, timeout, priority, false);
   }

   private boolean updateRotation(Rotation targetRotation, float lazinessH, float lazinessV) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         Minecraft var10002 = mc;
         Minecraft.player.rotationYaw = this.smoothRotation(Minecraft.player.rotationYaw, (double)targetRotation.getYaw(), lazinessH);
         var10000 = mc;
         var10002 = mc;
         Minecraft.player.rotationPitch = MathHelper.clamp(this.smoothRotation(Minecraft.player.rotationPitch, (double)targetRotation.getPitch(), lazinessV), -90.0F, 90.0F);
         this.idleTicks(0);
         var10002 = mc;
         return (new Rotation(Minecraft.player)).getDelta(targetRotation) < 1.0F;
      }
   }

   public void stopRotation() {
      this.currentTask(SmoothRotationComponent.RotationTask.IDLE);
      this.currentPriority(0);
      if (!RotationComponent.getInstance().isRotating()) {
         LookHandler.setActive(false);
      }

   }

   public boolean isRotating() {
      return !this.currentTask.equals(SmoothRotationComponent.RotationTask.IDLE);
   }

   private float smoothRotation(float currentAngle, double targetAngle, float smoothFactor) {
      float angleDifference = (float)MathHelper.wrapDegrees(targetAngle - (double)currentAngle);
      float adjustmentSpeed = Math.abs(angleDifference / smoothFactor);
      float angleAdjustment = adjustmentSpeed * Math.signum(Math.signum(angleDifference));
      if (Math.abs(angleAdjustment) > Math.abs(angleDifference)) {
         angleAdjustment = angleDifference;
      }

      return currentAngle + SensUtil.getSens(angleAdjustment) - SensUtil.getSens(angleAdjustment) % SensUtil.getGCDValue();
   }

   public RotationTask currentTask() {
      return this.currentTask;
   }

   public float currentYawSpeed() {
      return this.currentYawSpeed;
   }

   public float currentPitchSpeed() {
      return this.currentPitchSpeed;
   }

   public float currentYawReturnSpeed() {
      return this.currentYawReturnSpeed;
   }

   public float currentPitchReturnSpeed() {
      return this.currentPitchReturnSpeed;
   }

   public int currentPriority() {
      return this.currentPriority;
   }

   public int currentTimeout() {
      return this.currentTimeout;
   }

   public int idleTicks() {
      return this.idleTicks;
   }

   public Rotation targetRotation() {
      return this.targetRotation;
   }

   public SmoothRotationComponent currentTask(RotationTask currentTask) {
      this.currentTask = currentTask;
      return this;
   }

   public SmoothRotationComponent currentYawSpeed(float currentYawSpeed) {
      this.currentYawSpeed = currentYawSpeed;
      return this;
   }

   public SmoothRotationComponent currentPitchSpeed(float currentPitchSpeed) {
      this.currentPitchSpeed = currentPitchSpeed;
      return this;
   }

   public SmoothRotationComponent currentYawReturnSpeed(float currentYawReturnSpeed) {
      this.currentYawReturnSpeed = currentYawReturnSpeed;
      return this;
   }

   public SmoothRotationComponent currentPitchReturnSpeed(float currentPitchReturnSpeed) {
      this.currentPitchReturnSpeed = currentPitchReturnSpeed;
      return this;
   }

   public SmoothRotationComponent currentPriority(int currentPriority) {
      this.currentPriority = currentPriority;
      return this;
   }

   public SmoothRotationComponent currentTimeout(int currentTimeout) {
      this.currentTimeout = currentTimeout;
      return this;
   }

   public SmoothRotationComponent idleTicks(int idleTicks) {
      this.idleTicks = idleTicks;
      return this;
   }

   public SmoothRotationComponent targetRotation(Rotation targetRotation) {
      this.targetRotation = targetRotation;
      return this;
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
