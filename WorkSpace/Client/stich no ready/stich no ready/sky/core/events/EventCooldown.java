package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.item.Item;

public class EventCooldown extends EventCancellable implements Event {
   private final Item item;
   private int ticks;

   public void setTicks(int ticks) {
      this.ticks = ticks;
   }

   public Item getItem() {
      return this.item;
   }

   public int getTicks() {
      return this.ticks;
   }

   public EventCooldown(Item item, int ticks) {
      this.item = item;
      this.ticks = ticks;
   }
}
