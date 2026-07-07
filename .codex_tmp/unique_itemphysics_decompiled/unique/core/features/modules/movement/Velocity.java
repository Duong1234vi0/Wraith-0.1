package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2664;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2846;
import net.minecraft.class_6373;
import net.minecraft.class_2828.class_2829;
import net.minecraft.class_2828.class_2830;
import net.minecraft.class_2846.class_2847;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IClientPlayerEntity;
import unique.core.injection.accesors.IExplosionS2CPacket;
import unique.core.injection.accesors.ISPacketEntityVelocity;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class Velocity extends Module {
   public Setting<Boolean> onlyAura = new Setting<>("OnlyDuringAura", false);
   public Setting<Boolean> pauseInWater = new Setting<>("PauseInLiquids", false);
   public Setting<Boolean> explosions = new Setting<>("Explosions", true);
   public Setting<Boolean> cc = new Setting<>("PauseOnFlag", false);
   public Setting<Boolean> fire = new Setting<>("PauseOnFire", false);
   private final Setting<Velocity.modeEn> mode = new Setting<>("Mode", Velocity.modeEn.Matrix);
   public Setting<Float> vertical = new Setting<>("Vertical", 0.0F, 0.0F, 100.0F, v -> this.mode.getValue() == Velocity.modeEn.Custom);
   private final Setting<Velocity.jumpModeEn> jumpMode = new Setting<>("JumpMode", Velocity.jumpModeEn.Jump, v -> this.mode.getValue() == Velocity.modeEn.Jump);
   public Setting<Float> horizontal = new Setting<>(
      "Horizontal", 0.0F, 0.0F, 100.0F, v -> this.mode.getValue() == Velocity.modeEn.Custom || this.mode.getValue() == Velocity.modeEn.Jump
   );
   public Setting<Float> motion = new Setting<>("Motion", 0.42F, 0.4F, 0.5F, v -> this.mode.getValue() == Velocity.modeEn.Jump);
   public Setting<Boolean> fail = new Setting<>("SmartFail", true, v -> this.mode.getValue() == Velocity.modeEn.Jump);
   public Setting<Float> failRate = new Setting<>("FailRate", 0.3F, 0.0F, 1.0F, v -> this.mode.getValue() == Velocity.modeEn.Jump && this.fail.getValue());
   public Setting<Float> jumpRate = new Setting<>("FailJumpRate", 0.25F, 0.0F, 1.0F, v -> this.mode.getValue() == Velocity.modeEn.Jump && this.fail.getValue());
   private boolean doJump;
   private boolean failJump;
   private boolean skip;
   private boolean flag;
   private int grimTicks;
   private int ccCooldown;

   public Velocity() {
      super("Velocity", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (!fullNullCheck()) {
         if (mc.field_1724 == null
            || !mc.field_1724.method_5799() && !mc.field_1724.method_5869() && !mc.field_1724.method_5771()
            || !this.pauseInWater.getValue()) {
            if (mc.field_1724 == null || !mc.field_1724.method_5809() || !this.fire.getValue() || mc.field_1724.field_6235 <= 0) {
               if (this.ccCooldown > 0) {
                  this.ccCooldown--;
               } else {
                  if (e.getPacket() instanceof class_2743 pac
                     && pac.method_11818() == mc.field_1724.method_5628()
                     && (!this.onlyAura.getValue() || ModuleManager.aura.isEnabled())) {
                     switch ((Velocity.modeEn)this.mode.getValue()) {
                        case Matrix:
                           if (!this.flag) {
                              e.cancel();
                              this.flag = true;
                           } else {
                              this.flag = false;
                              ((ISPacketEntityVelocity)pac).setMotionX((int)(pac.method_11815() * -0.1));
                              ((ISPacketEntityVelocity)pac).setMotionZ((int)(pac.method_11819() * -0.1));
                           }
                           break;
                        case Cancel:
                           e.cancel();
                           break;
                        case Sunrise:
                           e.cancel();
                           this.sendPacket(new class_2829(mc.field_1724.method_23317(), -999.0, mc.field_1724.method_23321(), true));
                           break;
                        case Custom:
                           ((ISPacketEntityVelocity)pac).setMotionX((int)((float)pac.method_11815() * this.horizontal.getValue() / 100.0F));
                           ((ISPacketEntityVelocity)pac).setMotionY((int)((float)pac.method_11816() * this.vertical.getValue() / 100.0F));
                           ((ISPacketEntityVelocity)pac).setMotionZ((int)((float)pac.method_11819() * this.horizontal.getValue() / 100.0F));
                           break;
                        case Redirect:
                           double vX = Math.abs(pac.method_11815());
                           double vZ = Math.abs(pac.method_11819());
                           double[] motion = MovementUtility.forward(vX + vZ);
                           ((ISPacketEntityVelocity)pac).setMotionX((int)motion[0]);
                           ((ISPacketEntityVelocity)pac).setMotionY(0);
                           ((ISPacketEntityVelocity)pac).setMotionZ((int)motion[1]);
                           break;
                        case OldGrim:
                           e.cancel();
                           this.grimTicks = 6;
                           break;
                        case Jump:
                           ((ISPacketEntityVelocity)pac).setMotionX((int)((float)pac.method_11815() * this.horizontal.getValue() / 100.0F));
                           ((ISPacketEntityVelocity)pac).setMotionZ((int)((float)pac.method_11819() * this.horizontal.getValue() / 100.0F));
                           break;
                        case GrimNew:
                           e.cancel();
                           this.flag = true;
                     }
                  }

                  if (e.getPacket() instanceof class_2664 explosion && this.explosions.getValue()) {
                     switch ((Velocity.modeEn)this.mode.getValue()) {
                        case Cancel:
                           ((IExplosionS2CPacket)explosion).setMotionX(0.0F);
                           ((IExplosionS2CPacket)explosion).setMotionY(0.0F);
                           ((IExplosionS2CPacket)explosion).setMotionZ(0.0F);
                           break;
                        case Custom:
                           ((IExplosionS2CPacket)explosion).setMotionX(((IExplosionS2CPacket)explosion).getMotionX() * this.horizontal.getValue() / 100.0F);
                           ((IExplosionS2CPacket)explosion).setMotionZ(((IExplosionS2CPacket)explosion).getMotionZ() * this.horizontal.getValue() / 100.0F);
                           ((IExplosionS2CPacket)explosion).setMotionY(((IExplosionS2CPacket)explosion).getMotionY() * this.vertical.getValue() / 100.0F);
                           break;
                        case GrimNew:
                           ((IExplosionS2CPacket)explosion).setMotionX(0.0F);
                           ((IExplosionS2CPacket)explosion).setMotionY(0.0F);
                           ((IExplosionS2CPacket)explosion).setMotionZ(0.0F);
                           this.flag = true;
                     }
                  }

                  if (this.mode.getValue() == Velocity.modeEn.OldGrim && e.getPacket() instanceof class_6373 && this.grimTicks > 0) {
                     e.cancel();
                     this.grimTicks--;
                  }

                  if (e.getPacket() instanceof class_2708 && (this.cc.getValue() || this.mode.getValue() == Velocity.modeEn.GrimNew)) {
                     this.ccCooldown = 5;
                  }
               }
            }
         }
      }
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 == null || !mc.field_1724.method_5799() && !mc.field_1724.method_5869() || !this.pauseInWater.getValue()) {
         switch ((Velocity.modeEn)this.mode.getValue()) {
            case Matrix:
               if (mc.field_1724.field_6235 > 0 && !mc.field_1724.method_24828()) {
                  double var3 = mc.field_1724.method_36454() * (float) (Math.PI / 180.0);
                  double var5 = Math.sqrt(
                     mc.field_1724.method_18798().field_1352 * mc.field_1724.method_18798().field_1352
                        + mc.field_1724.method_18798().field_1350 * mc.field_1724.method_18798().field_1350
                  );
                  mc.field_1724.method_18800(-Math.sin(var3) * var5, mc.field_1724.method_18798().field_1351, Math.cos(var3) * var5);
                  mc.field_1724.method_5728(mc.field_1724.field_6012 % 2 != 0);
               }
               break;
            case Jump:
               if ((this.failJump || mc.field_1724.field_6235 > 6) && mc.field_1724.method_24828()) {
                  if (this.failJump) {
                     this.failJump = false;
                  }

                  if (!this.doJump) {
                     this.skip = true;
                  }

                  if (Math.random() <= this.failRate.getValue().floatValue() && this.fail.getValue()) {
                     if (Math.random() <= this.jumpRate.getValue().floatValue()) {
                        this.doJump = true;
                        this.failJump = true;
                     } else {
                        this.doJump = false;
                        this.failJump = false;
                     }
                  } else {
                     this.doJump = true;
                     this.failJump = false;
                  }

                  if (this.skip) {
                     this.skip = false;
                     return;
                  }

                  switch ((Velocity.jumpModeEn)this.jumpMode.getValue()) {
                     case Motion:
                        mc.field_1724
                           .method_18800(
                              mc.field_1724.method_18798().method_10216(), this.motion.getValue().floatValue(), mc.field_1724.method_18798().method_10215()
                           );
                        break;
                     case Jump:
                        mc.field_1724.method_6043();
                        break;
                     case Both:
                        mc.field_1724.method_6043();
                        mc.field_1724
                           .method_18800(
                              mc.field_1724.method_18798().method_10216(), this.motion.getValue().floatValue(), mc.field_1724.method_18798().method_10215()
                           );
                  }
               }
               break;
            case GrimNew:
               if (this.flag) {
                  if (this.ccCooldown <= 0) {
                     this.sendPacket(
                        new class_2830(
                           mc.field_1724.method_23317(),
                           mc.field_1724.method_23318(),
                           mc.field_1724.method_23321(),
                           ((IClientPlayerEntity)mc.field_1724).getLastYaw(),
                           ((IClientPlayerEntity)mc.field_1724).getLastPitch(),
                           mc.field_1724.method_24828()
                        )
                     );
                     this.sendPacket(new class_2846(class_2847.field_12973, class_2338.method_49638(mc.field_1724.method_19538()), class_2350.field_11033));
                  }

                  this.flag = false;
               }
         }

         if (this.grimTicks > 0) {
            this.grimTicks--;
         }
      }
   }

   private boolean isValidMotion(double motion, double min, double max) {
      return Math.abs(motion) > min && Math.abs(motion) < max;
   }

   @Override
   public void onEnable() {
      this.grimTicks = 0;
   }

   public static enum jumpModeEn {
      Motion,
      Jump,
      Both;
   }

   public static enum modeEn {
      Matrix,
      Cancel,
      Sunrise,
      Custom,
      Redirect,
      OldGrim,
      Jump,
      GrimNew;
   }
}
