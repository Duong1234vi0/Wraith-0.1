package unique.core.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.math.MathUtility;

public class AntiAim extends Module {
   private final Setting<AntiAim.Mode> pitchMode = new Setting<>("PitchMode", AntiAim.Mode.None);
   private final Setting<AntiAim.Mode> yawMode = new Setting<>("YawMode", AntiAim.Mode.None);
   public Setting<Integer> Speed = new Setting<>("Speed", 1, 1, 45);
   public Setting<Integer> yawDelta = new Setting<>("YawDelta", 60, -360, 360);
   public Setting<Integer> pitchDelta = new Setting<>("PitchDelta", 10, -90, 90);
   public Setting<Integer> yawOffset = new Setting<>("YawOffset", 0, -180, 180);
   public final Setting<Boolean> bodySync = new Setting<>("BodySync", true);
   public final Setting<Boolean> allowInteract = new Setting<>("AllowInteract", true);
   private float rotationYaw;
   private float rotationPitch;
   private float pitch_sinus_step;
   private float yaw_sinus_step;

   public AntiAim() {
      super("AntiAim", Module.Category.PLAYER);
   }

   @EventHandler(
      priority = 99
   )
   public void onSync(EventSync e) {
      if (!this.allowInteract.getValue() || !mc.field_1690.field_1886.method_1434() && !mc.field_1690.field_1886.method_1434()) {
         double gcdFix = Math.pow((Double)mc.field_1690.method_42495().method_41753() * 0.6 + 0.2, 3.0) * 1.2;
         if (this.yawMode.getValue() != AntiAim.Mode.None) {
            mc.field_1724.method_36456((float)(this.rotationYaw - (this.rotationYaw - mc.field_1724.method_36454()) % gcdFix));
            if (this.bodySync.getValue()) {
               mc.field_1724.method_5636(this.rotationYaw);
            }
         }

         if (this.pitchMode.getValue() != AntiAim.Mode.None) {
            mc.field_1724.method_36457((float)(this.rotationPitch - (this.rotationPitch - mc.field_1724.method_36455()) % gcdFix));
         }
      }
   }

   @EventHandler(
      priority = 100
   )
   public void onCalc(PlayerUpdateEvent e) {
      if (this.pitchMode.getValue() == AntiAim.Mode.RandomAngle && mc.field_1724.field_6012 % this.Speed.getValue() == 0) {
         this.rotationPitch = MathUtility.random(90.0F, -90.0F);
      }

      if (this.yawMode.getValue() == AntiAim.Mode.RandomAngle && mc.field_1724.field_6012 % this.Speed.getValue() == 0) {
         this.rotationYaw = MathUtility.random(0.0F, 360.0F);
      }

      if (this.yawMode.getValue() == AntiAim.Mode.Spin && mc.field_1724.field_6012 % this.Speed.getValue() == 0) {
         this.rotationYaw = this.rotationYaw + this.yawDelta.getValue().intValue();
         if (this.rotationYaw > 360.0F) {
            this.rotationYaw = 0.0F;
         }

         if (this.rotationYaw < 0.0F) {
            this.rotationYaw = 360.0F;
         }
      }

      if (this.pitchMode.getValue() == AntiAim.Mode.Spin && mc.field_1724.field_6012 % this.Speed.getValue() == 0) {
         this.rotationPitch = this.rotationPitch + this.pitchDelta.getValue().intValue();
         if (this.rotationPitch > 90.0F) {
            this.rotationPitch = -90.0F;
         }

         if (this.rotationPitch < -90.0F) {
            this.rotationPitch = 90.0F;
         }
      }

      if (this.pitchMode.getValue() == AntiAim.Mode.Sinus) {
         this.pitch_sinus_step = this.pitch_sinus_step + this.Speed.getValue().intValue() / 10.0F;
         this.rotationPitch = (float)(mc.field_1724.method_36455() + this.pitchDelta.getValue().intValue() * Math.sin(this.pitch_sinus_step));
         this.rotationPitch = MathUtility.clamp(this.rotationPitch, -90.0F, 90.0F);
      }

      if (this.yawMode.getValue() == AntiAim.Mode.Sinus) {
         this.yaw_sinus_step = this.yaw_sinus_step + this.Speed.getValue().intValue() / 10.0F;
         this.rotationYaw = (float)(
            mc.field_1724.method_36454() + this.yawDelta.getValue().intValue() * Math.sin(this.yaw_sinus_step) + this.yawOffset.getValue().intValue()
         );
      }

      if (this.pitchMode.getValue() == AntiAim.Mode.Fixed) {
         this.rotationPitch = this.pitchDelta.getValue().intValue();
      }

      if (this.yawMode.getValue() == AntiAim.Mode.Fixed) {
         this.rotationYaw = this.yawDelta.getValue().intValue();
      }

      if (this.pitchMode.getValue() == AntiAim.Mode.Static) {
         this.rotationPitch = mc.field_1724.method_36455() + this.pitchDelta.getValue().intValue();
         this.rotationPitch = MathUtility.clamp(this.rotationPitch, -90.0F, 90.0F);
      }

      if (this.yawMode.getValue() == AntiAim.Mode.Static) {
         this.rotationYaw = mc.field_1724.method_36454() % 360.0F + this.yawDelta.getValue().intValue();
      }

      if (this.pitchMode.getValue() == AntiAim.Mode.Jitter) {
         if (mc.field_1724.field_6012 % (this.Speed.getValue() * 2) == 0) {
            this.rotationPitch = this.pitchDelta.getValue().intValue() / 2.0F;
         }

         if (mc.field_1724.field_6012 % (this.Speed.getValue() * 2) == this.Speed.getValue()) {
            this.rotationPitch = this.pitchDelta.getValue().intValue() / -2.0F;
         }
      }

      if (this.yawMode.getValue() == AntiAim.Mode.Jitter) {
         if (mc.field_1724.field_6012 % (this.Speed.getValue() * 2) == 0) {
            this.rotationYaw = this.yawDelta.getValue().intValue() / 2.0F + this.yawOffset.getValue().intValue() + mc.field_1724.method_36454();
         }

         if (mc.field_1724.field_6012 % (this.Speed.getValue() * 2) == this.Speed.getValue()) {
            this.rotationYaw = this.yawDelta.getValue().intValue() / -2.0F + this.yawOffset.getValue().intValue() + mc.field_1724.method_36454();
         }
      }

      ModuleManager.rotations.fixRotation = this.rotationYaw;
   }

   public static enum Mode {
      None,
      RandomAngle,
      Spin,
      Sinus,
      Fixed,
      Static,
      Jitter;
   }
}
