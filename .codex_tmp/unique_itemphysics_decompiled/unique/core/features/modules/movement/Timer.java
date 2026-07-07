package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_3532;
import net.minecraft.class_6373;
import unique.core.UniqueCore;
import unique.core.events.impl.PacketEvent;
import unique.core.events.impl.PostPlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.utility.player.MovementUtility;

public class Timer extends Module {
   private final Setting<Timer.Mode> mode = new Setting<>("Mode", Timer.Mode.Normal);
   private final Setting<Boolean> old = new Setting<>("Old", false, v -> this.mode.is(Timer.Mode.Matrix));
   public final Setting<Float> speed = new Setting<>("Speed", 2.0F, 0.1F, 10.0F, v -> this.mode.getValue() != Timer.Mode.Shift);
   private final Setting<Integer> shiftTicks = new Setting<>("ShiftTicks", 10, 1, 40, v -> this.mode.getValue() == Timer.Mode.Shift);
   private final Setting<Bind> boostKey = new Setting<>("BoostKey", new Bind(-1, false, false), v -> this.mode.getValue() == Timer.Mode.Grim);
   private final Setting<Timer.OnFlag> onFlag = new Setting<>("OnFlag", Timer.OnFlag.Reset);
   public static float energy;
   public static float yaw;
   public static float pitch;
   private static double prevPosX;
   private static double prevPosY;
   private static double prevPosZ;
   private long cancelTime;

   public Timer() {
      super("Timer", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      UniqueCore.TICK_TIMER = 1.0F;
      if (!this.mode.is(Timer.Mode.Matrix)) {
         energy = 0.0F;
      }

      if (this.mode.is(Timer.Mode.Grim)) {
         this.cancelTime = System.currentTimeMillis();
      }
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
   }

   @Override
   public void onUpdate() {
      switch ((Timer.Mode)this.mode.getValue()) {
         case Normal:
            UniqueCore.TICK_TIMER = this.speed.getValue();
            break;
         case Matrix:
            if (!MovementUtility.isMoving()) {
               UniqueCore.TICK_TIMER = 1.0F;
               return;
            }

            UniqueCore.TICK_TIMER = Math.max(this.speed.getValue(), 1.0F);
            if (energy > 0.0F) {
               energy = class_3532.method_15363(energy - (0.1F * this.speed.getValue() - 0.1F), 0.0F, 1.0F);
            } else {
               this.disable(ClientSettings.isRu() ? "Заряд таймера кончился! Отключаю.." : "Timer's out of charge! Disabling..");
            }
         case Shift:
         default:
            break;
         case Grim:
            if (energy <= 0.0F || !this.isKeyPressed(this.boostKey) || UniqueCore.core.getSetBackTime() < 2000L) {
               UniqueCore.TICK_TIMER = 1.0F;
               return;
            }

            UniqueCore.TICK_TIMER = Math.max(this.speed.getValue(), 1.0F);
            energy = class_3532.method_15363(energy - (0.0025F * this.speed.getValue() - 0.0025F), 0.0F, 1.0F);
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (this.mode.is(Timer.Mode.Grim) && e.getPacket() instanceof class_6373 && UniqueCore.core.getSetBackTime() > 2000L) {
         if (System.currentTimeMillis() - this.cancelTime > 25000L) {
            this.cancelTime = System.currentTimeMillis();
            energy = 0.0F;
            return;
         }

         if (!MovementUtility.isMoving()) {
            energy = Math.clamp(energy + 0.005F, 0.0F, 1.0F);
         }

         e.cancel();
      }

      if (e.getPacket() instanceof class_2708) {
         switch ((Timer.OnFlag)this.onFlag.getValue()) {
            case Disable:
               energy = 0.0F;
               this.disable(ClientSettings.isRu() ? "Отключён т.к. тебя флагнуло!" : "Disabled because you got flagged!");
               break;
            case Reset:
               UniqueCore.TICK_TIMER = 1.0F;
               energy = 0.0F;
         }
      }

      if (e.getPacket() instanceof class_2743 velo && velo.method_11818() == mc.field_1724.method_5628() && this.mode.is(Timer.Mode.Grim)) {
         UniqueCore.TICK_TIMER = 1.0F;
         energy = 0.0F;
      }
   }

   @EventHandler
   public void onPostPlayerUpdate(PostPlayerUpdateEvent event) {
      if (this.mode.getValue() == Timer.Mode.Shift) {
         if (energy < 0.9F) {
            this.disable(ClientSettings.isRu() ? "Перед повторным использованием необходимо постоять на месте!" : "Standing still is required before reuse!");
            return;
         }

         event.cancel();
         event.setIterations(this.shiftTicks.getValue());
         this.disable(ClientSettings.isRu() ? "Тики пропущены! Отключаю.." : "Ticks shifted! Disabling..");
      }
   }

   public void onEntitySync() {
      if (this.mode.is(Timer.Mode.Matrix)) {
         energy = Math.clamp(notMoving() ? energy + 0.025F : energy - (this.old.getValue() ? 0.005F : 0.0F), 0.0F, 1.0F);
      }

      prevPosX = mc.field_1724.method_23317();
      prevPosY = mc.field_1724.method_23318();
      prevPosZ = mc.field_1724.method_23321();
      yaw = mc.field_1724.method_36454();
      pitch = mc.field_1724.method_36455();
   }

   private static boolean notMoving() {
      return prevPosX == mc.field_1724.method_23317()
         && prevPosY == mc.field_1724.method_23318()
         && prevPosZ == mc.field_1724.method_23321()
         && yaw == mc.field_1724.method_36454()
         && pitch == mc.field_1724.method_36455();
   }

   public static enum Mode {
      Normal,
      Matrix,
      Shift,
      Grim;
   }

   public static enum OnFlag {
      Disable,
      None,
      Reset;
   }
}
