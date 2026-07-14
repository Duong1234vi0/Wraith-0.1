package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.item.Item;

public class EventCooldownTracker extends EventCancellable implements Event {
   private Item item;
   private float partialTicks;

   public Item getItem() {
      return this.item;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setItem(Item item) {
      this.item = item;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventCooldownTracker)) {
         return false;
      } else {
         EventCooldownTracker other = (EventCooldownTracker)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getPartialTicks(), other.getPartialTicks()) != 0) {
            return false;
         } else {
            Object this$item = this.getItem();
            Object other$item = other.getItem();
            if (this$item == null) {
               if (other$item != null) {
                  return false;
               }
            } else if (!this$item.equals(other$item)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventCooldownTracker;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPartialTicks());
      Object $item = this.getItem();
      result = result * 59 + ($item == null ? 43 : $item.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getItem());
      return "EventCooldownTracker(item=" + var10000 + ", partialTicks=" + this.getPartialTicks() + ")";
   }

   public EventCooldownTracker(Item item, float partialTicks) {
      this.item = item;
      this.partialTicks = partialTicks;
   }
}
