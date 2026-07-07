package unique.core.features.modules.client;

import net.minecraft.class_243;
import net.minecraft.class_3532;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventFixVelocity;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.events.impl.EventPlayerJump;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;

public class Rotations extends Module {
   private final Setting<Rotations.MoveFixMode> moveFix = new Setting<>("MoveFix", Rotations.MoveFixMode.Focused);
   public final Setting<Boolean> clientLook = new Setting<>("ClientLook", false);
   public float fixRotation = Float.NaN;
   private float prevYaw;
   private float prevPitch;

   public Rotations() {
      super("MoveFix", Module.Category.MOVEMENT);
   }

   public void onJump(EventPlayerJump e) {
      if (!Float.isNaN(this.fixRotation) && this.moveFix.getValue() != Rotations.MoveFixMode.Off && !mc.field_1724.method_3144()) {
         if (e.isPre()) {
            this.prevYaw = mc.field_1724.method_36454();
            mc.field_1724.method_36456(this.fixRotation);
         } else {
            mc.field_1724.method_36456(this.prevYaw);
         }
      }
   }

   public void onPlayerMove(EventFixVelocity event) {
      if (this.moveFix.getValue() == Rotations.MoveFixMode.Free) {
         if (Float.isNaN(this.fixRotation) || mc.field_1724.method_3144()) {
            return;
         }

         event.setVelocity(this.fix(this.fixRotation, event.getMovementInput(), event.getSpeed()));
      }
   }

   public void modifyVelocity(EventPlayerTravel e) {
      if (ModuleManager.aura.isEnabled()
         && Aura.target != null
         && ModuleManager.aura.rotationMode.not(Aura.Mode.None)
         && ModuleManager.aura.elytraTarget.getValue()
         && Managers.PLAYER.ticksElytraFlying > 5) {
         if (e.isPre()) {
            this.prevYaw = mc.field_1724.method_36454();
            this.prevPitch = mc.field_1724.method_36455();
            mc.field_1724.method_36456(this.fixRotation);
            mc.field_1724.method_36457(ModuleManager.aura.rotationPitch);
         } else {
            mc.field_1724.method_36456(this.prevYaw);
            mc.field_1724.method_36457(this.prevPitch);
         }
      } else {
         if (this.moveFix.getValue() == Rotations.MoveFixMode.Focused && !Float.isNaN(this.fixRotation) && !mc.field_1724.method_3144()) {
            if (e.isPre()) {
               this.prevYaw = mc.field_1724.method_36454();
               mc.field_1724.method_36456(this.fixRotation);
            } else {
               mc.field_1724.method_36456(this.prevYaw);
            }
         }
      }
   }

   public void onKeyInput(EventKeyboardInput e) {
      if (this.moveFix.getValue() == Rotations.MoveFixMode.Free) {
         if (Float.isNaN(this.fixRotation) || mc.field_1724.method_3144()) {
            return;
         }

         float mF = mc.field_1724.field_3913.field_3905;
         float mS = mc.field_1724.field_3913.field_3907;
         float delta = (mc.field_1724.method_36454() - this.fixRotation) * (float) (Math.PI / 180.0);
         float cos = class_3532.method_15362(delta);
         float sin = class_3532.method_15374(delta);
         mc.field_1724.field_3913.field_3907 = Math.round(mS * cos - mF * sin);
         mc.field_1724.field_3913.field_3905 = Math.round(mF * cos + mS * sin);
      }
   }

   private class_243 fix(float yaw, class_243 movementInput, float speed) {
      double d = movementInput.method_1027();
      if (d < 1.0E-7) {
         return class_243.field_1353;
      } else {
         class_243 vec3d = (d > 1.0 ? movementInput.method_1029() : movementInput).method_1021(speed);
         float f = class_3532.method_15374(yaw * (float) (Math.PI / 180.0));
         float g = class_3532.method_15362(yaw * (float) (Math.PI / 180.0));
         return new class_243(vec3d.field_1352 * g - vec3d.field_1350 * f, vec3d.field_1351, vec3d.field_1350 * g + vec3d.field_1352 * f);
      }
   }

   @Override
   public boolean isToggleable() {
      return false;
   }

   public static enum MoveFixMode {
      Off,
      Focused,
      Free;
   }
}
