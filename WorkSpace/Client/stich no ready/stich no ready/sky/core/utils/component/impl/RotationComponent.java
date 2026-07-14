package sky.core.utils.component.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.LookHandler;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.component.Component;
import sky.core.utils.component.Instance;

public class RotationComponent extends Component {
   private RotationTask currentTask;
   private float currentYawSpeed;
   private float currentPitchSpeed;
   private float currentYawReturnSpeed;
   private float currentPitchReturnSpeed;
   private int currentPriority;
   private int currentTimeout;
   private int idleTicks;
   private Rotation targetRotation;

   public RotationComponent() {
      this.currentTask = RotationComponent.RotationTask.IDLE;
   }

   public static RotationComponent getInstance() {
      return (RotationComponent)Instance.getComponent(RotationComponent.class);
   }

   private void resetRotation() {
      Rotation targetRotation = new Rotation(LookHandler.getFreeYaw(), LookHandler.getFreePitch());
      if (this.updateRotation(targetRotation, this.currentYawReturnSpeed(), this.currentPitchReturnSpeed())) {
         this.stopRotation();
      }

   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (this.currentTask().equals(RotationComponent.RotationTask.AIM) && this.idleTicks() > this.currentTimeout()) {
         this.currentTask(RotationComponent.RotationTask.RESET);
      }

      if (this.currentTask().equals(RotationComponent.RotationTask.RESET)) {
         this.resetRotation();
      }

      ++this.idleTicks;
   }

   public static void update(Rotation rotation, float turnSpeed, int timeout, int priority) {
      update(rotation, turnSpeed, turnSpeed, timeout, priority);
   }

   public static void update(Rotation target, float yawSpeed, float pitchSpeed, float yawReturnSpeed, float pitchReturnSpeed, int timeout, int priority, boolean clientRotation) {
      RotationComponent instance = getInstance();
      if (instance.currentPriority() <= priority) {
         if (instance.currentTask().equals(RotationComponent.RotationTask.IDLE) && !clientRotation) {
            LookHandler.setActive(true);
         }

         instance.currentYawSpeed(yawSpeed);
         instance.currentPitchSpeed(pitchSpeed);
         instance.currentYawReturnSpeed(yawReturnSpeed);
         instance.currentPitchReturnSpeed(pitchReturnSpeed);
         instance.currentTimeout(timeout);
         instance.currentPriority(priority);
         instance.currentTask(RotationComponent.RotationTask.AIM);
         instance.targetRotation(target);
         AttackAura attackAura = (AttackAura)SkyCore.getInstance().getModuleManager().getModule(AttackAura.class);
         instance.updateRotation(target, yawSpeed, pitchSpeed);
      }
   }

   public static void update(Rotation targetRotation, float turnSpeed, float returnSpeed, int timeout, int priority) {
      update(targetRotation, turnSpeed, turnSpeed, returnSpeed, returnSpeed, timeout, priority, false);
   }

   private boolean updateRotation(Rotation targetRotation, float yawSpeed, float pitchSpeed) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         AttackAura attackAura = (AttackAura)SkyCore.getInstance().getModuleManager().getModule(AttackAura.class);
         Minecraft var10002 = mc;
         Rotation currentRotation = new Rotation(Minecraft.player);
         float yawDelta = MathHelper.wrapDegrees(targetRotation.getYaw() - currentRotation.getYaw());
         float pitchDelta = targetRotation.getPitch() - currentRotation.getPitch();
         float clampedYaw = Math.min(Math.abs(yawDelta), yawSpeed);
         float clampedPitch = Math.min(Math.abs(pitchDelta), pitchSpeed);
         var10000 = mc;
         ClientPlayerEntity var11 = Minecraft.player;
         var11.rotationYaw += SensUtil.getSens(MathHelper.clamp(yawDelta, -clampedYaw, clampedYaw)) - SensUtil.getSens(MathHelper.clamp(yawDelta, -clampedYaw, clampedYaw)) % SensUtil.getGCDValue();
         var10000 = mc;
         var11 = Minecraft.player;
         Minecraft var10001 = mc;
         float var12 = MathHelper.clamp(Minecraft.player.rotationPitch + SensUtil.getSens(MathHelper.clamp(pitchDelta, -clampedPitch, clampedPitch)), -90.0F, 90.0F);
         var10002 = mc;
         var11.rotationPitch = var12 - MathHelper.clamp(Minecraft.player.rotationPitch + SensUtil.getSens(MathHelper.clamp(pitchDelta, -clampedPitch, clampedPitch)), -90.0F, 90.0F) % SensUtil.getGCDValue();
         this.idleTicks(0);
         var10002 = mc;
         return (new Rotation(Minecraft.player)).getDelta(targetRotation) < 1.0F;
      }
   }

   public void stopRotation() {
      this.currentTask(RotationComponent.RotationTask.IDLE);
      this.currentPriority(0);
      if (!SmoothRotationComponent.getInstance().isRotating()) {
         LookHandler.setActive(false);
      }

   }

   public boolean isRotating() {
      return !this.currentTask.equals(RotationComponent.RotationTask.IDLE);
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

   public RotationComponent currentTask(RotationTask currentTask) {
      this.currentTask = currentTask;
      return this;
   }

   public RotationComponent currentYawSpeed(float currentYawSpeed) {
      this.currentYawSpeed = currentYawSpeed;
      return this;
   }

   public RotationComponent currentPitchSpeed(float currentPitchSpeed) {
      this.currentPitchSpeed = currentPitchSpeed;
      return this;
   }

   public RotationComponent currentYawReturnSpeed(float currentYawReturnSpeed) {
      this.currentYawReturnSpeed = currentYawReturnSpeed;
      return this;
   }

   public RotationComponent currentPitchReturnSpeed(float currentPitchReturnSpeed) {
      this.currentPitchReturnSpeed = currentPitchReturnSpeed;
      return this;
   }

   public RotationComponent currentPriority(int currentPriority) {
      this.currentPriority = currentPriority;
      return this;
   }

   public RotationComponent currentTimeout(int currentTimeout) {
      this.currentTimeout = currentTimeout;
      return this;
   }

   public RotationComponent idleTicks(int idleTicks) {
      this.idleTicks = idleTicks;
      return this;
   }

   public RotationComponent targetRotation(Rotation targetRotation) {
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
