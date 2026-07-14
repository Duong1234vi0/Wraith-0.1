package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.UseAction;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventMotion;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.StopWatch;
import sky.core.utils.math.MathUtil;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.MoveUtil;

public class Flight extends Module {
   private final ModeSetting modeSetting = new ModeSetting("Тип", "Скольжение", new String[]{"Скольжение", "Прыжки", "Движение", "Обычный", "ReallyWorld Dragon", "Glide Fly"});
   private final SliderSetting speed = new SliderSetting("Скорость", 1.5F, 0.1F, 10.0F, 0.1F, () -> {
      return !this.modeSetting.is("Прыжки") && !this.modeSetting.is("ReallyWorld Dragon") && !this.modeSetting.is("Glide Fly");
   });
   private final SliderSetting speedY = new SliderSetting("Скорость по Y", 1.5F, 0.1F, 10.0F, 0.1F, () -> {
      return !this.modeSetting.is("Прыжки") && !this.modeSetting.is("ReallyWorld Dragon") && !this.modeSetting.is("Glide Fly");
   });
   private final SliderSetting glideSpeed = new SliderSetting("Скорость", 0.087F, 0.0F, 0.25F, 0.001F, () -> {
      return this.modeSetting.is("Glide Fly");
   });
   private TimeUtil ticks = new TimeUtil();
   private final StopWatch glideTicks = new StopWatch();
   int ticksTwo = 0;

   public Flight() {
      super("Flight", "Позволяет летать, облегчая перемещение", Category.Movement);
      this.addSettings(new Setting[]{this.modeSetting, this.speed, this.speedY, this.glideSpeed});
   }

   public boolean isGlideFlyMode() {
      return this.isEnabled() && this.modeSetting.is("Glide Fly");
   }

   private boolean isConsumableUse(UseAction action) {
      return action == UseAction.EAT || action == UseAction.DRINK;
   }

   private void blockEating() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.isHandActive()) {
            var10000 = mc;
            UseAction var2;
            if (Minecraft.player.getActiveItemStack() != null) {
               var10000 = mc;
               var2 = Minecraft.player.getActiveItemStack().getUseAction();
            } else {
               var2 = null;
            }

            UseAction action = var2;
            if (action != null && this.isConsumableUse(action)) {
               Minecraft var10001 = mc;
               mc.playerController.onStoppedUsingItem(Minecraft.player);
               mc.gameSettings.keyBindUseItem.setPressed(false);
            }
         }
      }
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      if (this.isGlideFlyMode()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying()) {
               this.blockEating();
               return;
            }
         }
      }

   }

   @EventTarget
   @Native
   public void onEvent(EventMotion event) {
      boolean isSneaking = mc.gameSettings.keyBindSneak.isKeyDown();
      boolean isJumping = mc.gameSettings.keyBindJump.isKeyDown();
      float motionSpeed = (Float)this.speedY.get();
      Minecraft var10000;
      switch ((String)this.modeSetting.get()) {
         case "Скольжение":
            var10000 = mc;
            Minecraft.player.setMotion(0.0, -0.004999999888241291, 0.0);
            if (isSneaking) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)(-motionSpeed);
            } else if (isJumping) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)motionSpeed;
            }

            var10000 = mc;
            if (Minecraft.player.isOnGround()) {
               var10000 = mc;
               Minecraft.player.jump();
            }

            MoveUtil.setMotion((double)(Float)this.speed.get());
            break;
         case "Обычный":
            var10000 = mc;
            Minecraft.player.setMotion(0.0, 0.0, 0.0);
            if (isSneaking) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)(-motionSpeed);
            } else if (isJumping) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)motionSpeed;
            }

            var10000 = mc;
            if (Minecraft.player.isOnGround()) {
               var10000 = mc;
               Minecraft.player.jump();
            }

            MoveUtil.setMotion((double)(Float)this.speed.get());
            break;
         case "Движение":
            if (isSneaking) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)(-motionSpeed);
            } else if (isJumping) {
               var10000 = mc;
               Minecraft.player.motion.y = (double)motionSpeed;
            }

            MoveUtil.setMotion((double)(Float)this.speed.get());
            break;
         case "Прыжки":
            if (isJumping) {
               var10000 = mc;
               Minecraft.player.jump();
            }
            break;
         case "ReallyWorld Dragon":
            var10000 = mc;
            if (Minecraft.player.abilities.isFlying) {
               var10000 = mc;
               Minecraft.player.setMotion(0.0, 0.0, 0.0);
               boolean moving = MoveUtil.isMoving();
               boolean vertical = isSneaking || isJumping;
               if (moving) {
                  MoveUtil.setMotion(vertical ? 0.7 : 1.09);
               }

               if (isSneaking && !moving) {
                  var10000 = mc;
                  Minecraft.player.motion.y = -1.0;
               } else if (isJumping && !moving) {
                  var10000 = mc;
                  Minecraft.player.motion.y = 1.0;
               }

               if (isSneaking) {
                  var10000 = mc;
                  Minecraft.player.motion.y = -0.74;
               } else if (isJumping) {
                  var10000 = mc;
                  Minecraft.player.motion.y = 0.74;
               }
            }
            break;
         case "Glide Fly":
            var10000 = mc;
            if (Minecraft.player.isElytraFlying()) {
               var10000 = mc;
               Vector3d pos = Minecraft.player.getPositionVec();
               var10000 = mc;
               float yaw = Minecraft.player.rotationYaw;
               double forward = (double)(Float)this.glideSpeed.get();
               var10000 = mc;
               double bps = MathUtil.getBps(Minecraft.player, 1);
               float limit = ServerUtil.isConnectedToServer("reallyworld") ? 40.0F : 100.0F;
               if (bps >= (double)limit) {
                  forward = 0.0;
               }

               double dx = -Math.sin(Math.toRadians((double)yaw)) * forward;
               double dz = Math.cos(Math.toRadians((double)yaw)) * forward;
               var10000 = mc;
               ClientPlayerEntity var20 = Minecraft.player;
               double var10001 = dx * (double)MathUtil.randomtest(1.1F, 1.21F);
               Minecraft var10002 = mc;
               var20.setVelocity(var10001, Minecraft.player.getMotion().y - 0.019999999552965164, dz * (double)MathUtil.randomtest(1.1F, 1.21F));
               if (this.glideTicks.finished(50.0)) {
                  var10000 = mc;
                  Minecraft.player.setPosition(pos.getX() + dx, pos.getY(), pos.getZ() + dz);
                  this.glideTicks.reset();
               }

               var10000 = mc;
               var20 = Minecraft.player;
               var10001 = dx * (double)MathUtil.randomtest(1.1F, 1.21F);
               var10002 = mc;
               var20.setVelocity(var10001, Minecraft.player.getMotion().y + 0.01600000075995922, dz * (double)MathUtil.randomtest(1.1F, 1.21F));
               event.setX(event.getX() + dx);
               event.setZ(event.getZ() + dz);
            }
      }

   }
}
