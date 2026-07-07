package unique.core.features.modules.client;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2639;
import net.minecraft.class_2805;
import net.minecraft.class_4587;
import org.jetbrains.annotations.NotNull;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;

public final class FastLatency extends Module {
   private final Setting<Integer> delay = new Setting<>("Delay", 80, 0, 1000);
   private final Timer timer = new Timer();
   private final Timer limitTimer = new Timer();
   private long ping;
   public int resolvedPing;

   public FastLatency() {
      super("FastLatency", Module.Category.CLIENT);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (this.timer.passedMs(5000L) && this.limitTimer.every(this.delay.getValue().intValue())) {
         this.sendPacket(new class_2805(1337, "w "));
         this.ping = System.currentTimeMillis();
         this.timer.reset();
      }
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2639 c && c.comp_2262() == 1337) {
         this.resolvedPing = (int)MathUtility.clamp((float)(System.currentTimeMillis() - this.ping), 0.0F, 1000.0F);
         this.timer.setMs(5000L);
      }
   }
}
