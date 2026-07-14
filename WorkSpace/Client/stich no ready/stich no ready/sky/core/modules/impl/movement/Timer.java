package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CConfirmTransactionPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventKey;
import sky.core.events.EventMotion;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.math.TimeUtil;

public class Timer extends Module {
   private final ModeSetting mode = new ModeSetting("Mode", "Обычный", new String[]{"Обычный", "Grim"});
   private final BindSetting grimBind = new BindSetting("Кнопка буста", () -> {
      return this.mode.is("Grim");
   });
   private final SliderSetting timerAmount = new SliderSetting("Скорость", 2.0F, 1.0F, 5.0F, 0.025F);
   private final BooleanSetting smart = new BooleanSetting("Умный", true, () -> {
      return !this.mode.is("Grim");
   });
   private final BooleanSetting movingUp = new BooleanSetting("Добавлять в движении", false, () -> {
      return !this.mode.is("Grim");
   });
   private final SliderSetting upValue = new SliderSetting("Значение", 0.02F, 0.01F, 0.5F, 0.01F, () -> {
      return (Boolean)this.movingUp.get();
   });
   private final SliderSetting ticks = new SliderSetting("Скорость убывания", 1.0F, 0.15F, 3.0F, 0.1F, () -> {
      return !this.mode.is("Grim");
   });
   private final float maxViolation = 100.0F;
   private float violation = 0.0F;
   private double prevPosX;
   private double prevPosY;
   private double prevPosZ;
   private float yaw;
   private float pitch;
   private boolean isBoost;
   private final TimeUtil timerUtil = new TimeUtil();

   public Timer() {
      super("Timer", "Изменяет скорость игры", Category.Movement);
      this.addSettings(new Setting[]{this.mode, this.grimBind, this.timerAmount, this.smart, this.movingUp, this.upValue, this.ticks});
   }

   @EventTarget
   public void onEvent(EventKey e) {
      if (this.mode.is("Grim") && e.getKey() == (Integer)this.grimBind.get()) {
         this.isBoost = true;
      }

   }

   @EventTarget
   @Native
   public void onEvent(EventMotion e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         float var10001 = e.getYaw();
         float var10002 = e.getPitch();
         Minecraft var10003 = mc;
         double var2 = Minecraft.player.getPosX();
         Minecraft var10004 = mc;
         double var3 = Minecraft.player.getPosY();
         Minecraft var10005 = mc;
         this.updateTimer(var10001, var10002, var2, var3, Minecraft.player.getPosZ());
      }
   }

   @EventTarget
   public void onEvent(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (this.timerUtil.hasTimeElapsed(25000L)) {
            this.reset();
            this.timerUtil.reset();
         }

         var10000 = mc;
         if (!Minecraft.player.isOnGround() && !this.isBoost) {
            this.violation += 0.1F;
            this.violation = MathHelper.clamp(this.violation, 0.0F, 100.0F / (this.mode.is("Grim") ? 1.0F : (Float)this.timerAmount.get()));
         }

         if (!this.mode.is("Grim") || this.isBoost) {
            mc.timer.setSpeed((Float)this.timerAmount.get());
            if ((Boolean)this.smart.get() && !(mc.timer.getSpeed() <= 1.0F)) {
               if (this.violation < 100.0F / (Float)this.timerAmount.get()) {
                  this.violation += this.mode.is("Grim") ? 0.05F : (Float)this.ticks.get();
                  this.violation = MathHelper.clamp(this.violation, 0.0F, 100.0F / (this.mode.is("Grim") ? 1.0F : (Float)this.timerAmount.get()));
               } else {
                  this.resetSpeed();
               }
            }
         }

      }
   }

   @EventTarget
   public void onEvent(EventPacket e) {
      if (this.mode.is("Grim")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            IPacket packet = e.getPacket();
            if (e.isReceive()) {
               if (packet instanceof SPlayerPositionLookPacket && this.isBoost) {
                  this.resetSpeed();
                  this.reset();
               }

               if (packet instanceof SEntityVelocityPacket) {
                  SEntityVelocityPacket vel = (SEntityVelocityPacket)packet;
                  int var4 = vel.getEntityID();
                  Minecraft var10001 = mc;
                  if (var4 == Minecraft.player.getEntityId()) {
                     this.reset();
                     this.resetSpeed();
                  }
               }
            }

            if (e.isSend() && packet instanceof CConfirmTransactionPacket) {
               e.setCancelled(true);
            }

            return;
         }
      }

   }

   @Native
   private void updateTimer(float yaw, float pitch, double posX, double posY, double posZ) {
      if (this.notMoving(posX, posY, posZ, yaw, pitch)) {
         if (this.mode.is("Grim")) {
            this.violation = (float)((double)this.violation - 0.05);
         } else {
            this.violation = (float)((double)this.violation - ((double)(Float)this.ticks.get() + 0.4));
         }
      } else if ((Boolean)this.movingUp.get() && !this.mode.is("Grim")) {
         this.violation -= (Float)this.upValue.get();
      }

      this.violation = (float)MathHelper.clamp((double)this.violation, 0.0, Math.floor(100.0));
      this.prevPosX = posX;
      this.prevPosY = posY;
      this.prevPosZ = posZ;
      this.yaw = yaw;
      this.pitch = pitch;
   }

   private boolean notMoving(double posX, double posY, double posZ, float yaw, float pitch) {
      return this.prevPosX == posX && this.prevPosY == posY && this.prevPosZ == posZ && this.yaw == yaw && this.pitch == pitch;
   }

   private void resetSpeed() {
      mc.timer.resetSpeed();
      this.setEnabled(false);
   }

   private void reset() {
      if (this.mode.is("Grim")) {
         this.violation = 100.0F / (Float)this.timerAmount.get();
         this.isBoost = false;
      }

   }

   public void onEnable() {
      this.reset();
      mc.timer.resetSpeed();
      super.onEnable();
   }

   public void onDisable() {
      this.reset();
      mc.timer.resetSpeed();
      this.timerUtil.reset();
      super.onDisable();
   }
}
