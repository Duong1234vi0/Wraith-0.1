package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventInventoryClose extends EventCancellable implements Event {
   private int windowId;

   public int getWindowId() {
      return this.windowId;
   }

   public void setWindowId(int windowId) {
      this.windowId = windowId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventInventoryClose)) {
         return false;
      } else {
         EventInventoryClose other = (EventInventoryClose)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getWindowId() == other.getWindowId();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventInventoryClose;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getWindowId();
      return result;
   }

   public String toString() {
      return "EventInventoryClose(windowId=" + this.getWindowId() + ")";
   }

   public EventInventoryClose(int windowId) {
      this.windowId = windowId;
   }
}
