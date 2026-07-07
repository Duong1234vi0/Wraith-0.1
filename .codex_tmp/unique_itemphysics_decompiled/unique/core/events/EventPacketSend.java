package unique.core.events;

import net.minecraft.class_2596;

public class EventPacketSend {
   private final class_2596<?> packet;
   private boolean cancelled;

   public EventPacketSend(class_2596<?> packet) {
      this.packet = packet;
   }

   public class_2596<?> getPacket() {
      return this.packet;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void cancel() {
      this.cancelled = true;
   }
}
