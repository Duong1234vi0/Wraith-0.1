package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.network.play.server.SUpdateTimePacket;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventPacket;

public class TPSHandler {
   private static float adjustTicks = 0.0F;
   long timestamp;
   private static float TPS = 20.0F;

   @EventTarget
   private void onPacket(EventPacket e) {
      if (e.getPacket() instanceof SUpdateTimePacket) {
         this.updateTPS();
      }

   }

   private void updateTPS() {
      long delay = System.nanoTime() - this.timestamp;
      float maxTPS = 20.0F;
      float rawTPS = maxTPS * (1.0E9F / (float)delay);
      float boundedTPS = MathHelper.clamp(rawTPS, 0.0F, maxTPS);
      TPS = (float)Math.round(boundedTPS * 2.0F) / 2.0F;
      adjustTicks = boundedTPS - maxTPS;
      this.timestamp = System.nanoTime();
   }

   public static float getAdjustTicks() {
      return adjustTicks;
   }

   public static float getTPS() {
      return TPS;
   }
}
