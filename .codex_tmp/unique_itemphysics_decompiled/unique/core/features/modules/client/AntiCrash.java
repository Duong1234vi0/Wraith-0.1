package unique.core.features.modules.client;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2664;
import net.minecraft.class_2675;
import net.minecraft.class_2708;
import org.jetbrains.annotations.NotNull;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;

public class AntiCrash extends Module {
   public final Setting<Boolean> debug = new Setting<>("Debug", false);
   private Timer debugTimer = new Timer();

   public AntiCrash() {
      super("AntiCrash", Module.Category.CLIENT);
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive receive) {
      if (!(
         receive.getPacket() instanceof class_2664 exp
            && (exp.method_11475() > 1.0E9 || exp.method_11477() > 1.0E9 || exp.method_11478() > 1.0E9 || exp.method_11476() > 1.0E9)
      )) {
         if (!(
            receive.getPacket() instanceof class_2675 p
               && (
                  p.method_11544() > 1.0E9
                     || p.method_11547() > 1.0E9
                     || p.method_11546() > 1.0E9
                     || p.method_11543() > 1.0E9
                     || p.method_11548() > 1.0E9
                     || p.method_11549() > 1.0E9
                     || p.method_11550() > 1.0E9
               )
         )) {
            if (receive.getPacket() instanceof class_2708 pos
               && (
                  pos.method_11734() > 1.0E9
                     || pos.method_11735() > 1.0E9
                     || pos.method_11738() > 1.0E9
                     || pos.method_11736() > 1.0E9
                     || pos.method_11739() > 1.0E9
               )) {
               if (this.debug.getValue() && this.debugTimer.passedMs(1000L)) {
                  this.sendMessage("PlayerPositionLookS2CPacket canceled");
                  this.debugTimer.reset();
               }

               receive.cancel();
            }
         } else {
            if (this.debug.getValue() && this.debugTimer.passedMs(1000L)) {
               this.sendMessage("ParticleS2CPacket canceled");
               this.debugTimer.reset();
            }

            receive.cancel();
         }
      } else {
         if (this.debug.getValue() && this.debugTimer.passedMs(1000L)) {
            this.sendMessage("ExplosionS2CPacket canceled");
            this.debugTimer.reset();
         }

         receive.cancel();
      }
   }
}
