package sky.core.modules.impl.combat;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventDamage;
import sky.core.events.EventInput;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.impl.movement.FreeCamera;
import sky.core.utils.player.DamageUtil;

public class NoVelocity extends Module {
   private final ModeSetting modeSetting = new ModeSetting("Мод", "Обычный", new String[]{"Обычный", "RW"});
   private final BooleanSetting jump = new BooleanSetting("Прыгать", true, () -> {
      return this.modeSetting.is("RW");
   });
   private Vector3d knockbackVelocity;
   private final DamageUtil damageUtil;

   public NoVelocity() {
      super("Velocity", "Не позволяет разным условиям в мире откидывать вас", Category.Combat);
      this.knockbackVelocity = Vector3d.ZERO;
      this.damageUtil = new DamageUtil();
      this.addSettings(new Setting[]{this.modeSetting, this.jump});
   }

   @EventTarget
   public void onPacket(EventPacket e) {
      Minecraft var10000;
      Minecraft var10001;
      SEntityVelocityPacket p;
      IPacket var3;
      int var4;
      if (this.modeSetting.is("Обычный")) {
         var10000 = mc;
         if (Minecraft.player != null) {
            var3 = e.getPacket();
            if (var3 instanceof SEntityVelocityPacket) {
               p = (SEntityVelocityPacket)var3;
               var4 = p.getEntityID();
               var10001 = mc;
               if (var4 == Minecraft.player.getEntityId()) {
                  e.setCancelled(true);
               }
            }
         }
      }

      if (this.modeSetting.is("RW")) {
         this.damageUtil.time(1L);
         var10000 = mc;
         if (Minecraft.player != null && this.damageUtil.isNormalDamage()) {
            var3 = e.getPacket();
            if (var3 instanceof SEntityVelocityPacket) {
               p = (SEntityVelocityPacket)var3;
               var4 = p.getEntityID();
               var10001 = mc;
               if (var4 == Minecraft.player.getEntityId()) {
                  this.knockbackVelocity = new Vector3d((double)p.getMotionX(), (double)p.getMotionY(), (double)p.getMotionZ());
               }
            }
         }

         this.damageUtil.onPacketEvent(e);
      }

   }

   @EventTarget
   private void onDamage(EventDamage e) {
      if (this.modeSetting.is("RW")) {
         this.damageUtil.processDamage(e);
      }

   }

   @EventTarget
   public void onInput(EventInput e) {
      if (this.modeSetting.is("RW")) {
         if (SkyCore.getInstance().getModuleManager().getModule(FreeCamera.class).isEnabled()) {
            this.knockbackVelocity = Vector3d.ZERO;
         } else {
            Minecraft var10000 = mc;
            if (Minecraft.player.hurtTime > 0 && this.knockbackVelocity.lengthSquared() > 0.0) {
               double relativeAngle = this.getRelativeAngle();
               float forward = 0.0F;
               float strafe = 0.0F;
               if (relativeAngle > -0.0 && relativeAngle < 0.0) {
                  forward = 1.0F;
               } else if (!(relativeAngle > 45.0) && !(relativeAngle < -0.0)) {
                  if (relativeAngle >= 0.0 && relativeAngle <= 0.0) {
                     strafe = -0.0F;
                  } else if (relativeAngle <= -45.0 && relativeAngle >= -0.0) {
                     strafe = 0.0F;
                  }
               } else {
                  forward = -0.0F;
               }

               boolean var6;
               label35: {
                  e.setForward(forward);
                  e.setStrafe(strafe);
                  if ((Boolean)this.jump.get()) {
                     Minecraft var10001 = mc;
                     if (Minecraft.player.isOnGround()) {
                        var6 = true;
                        break label35;
                     }
                  }

                  var6 = false;
               }

               e.setJump(var6);
            } else {
               this.knockbackVelocity = Vector3d.ZERO;
            }

         }
      }
   }

   @Native
   private double getRelativeAngle() {
      Minecraft var10000 = mc;
      double yaw = (double)Minecraft.player.rotationYaw;
      double dx = -this.knockbackVelocity.x;
      double dz = -this.knockbackVelocity.z;
      double attackerYaw = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;
      return MathHelper.wrapDegrees(attackerYaw - yaw);
   }
}
