package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventDropItem extends EventCancellable implements Event {
   private int currentSlot;

   public int getCurrentSlot() {
      return this.currentSlot;
   }

   public void setCurrentSlot(int currentSlot) {
      this.currentSlot = currentSlot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventDropItem)) {
         return false;
      } else {
         EventDropItem other = (EventDropItem)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getCurrentSlot() == other.getCurrentSlot();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventDropItem;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getCurrentSlot();
      return result;
   }

   public String toString() {
      return "EventDropItem(currentSlot=" + this.getCurrentSlot() + ")";
   }

   public EventDropItem(int currentSlot) {
      this.currentSlot = currentSlot;
   }
}
