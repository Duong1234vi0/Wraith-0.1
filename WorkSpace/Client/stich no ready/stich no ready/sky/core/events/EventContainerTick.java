package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import java.util.List;

public class EventContainerTick extends EventCancellable implements Event {
   private int windowId;
   private List items;

   public int getWindowId() {
      return this.windowId;
   }

   public List getItems() {
      return this.items;
   }

   public void setWindowId(int windowId) {
      this.windowId = windowId;
   }

   public void setItems(List items) {
      this.items = items;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventContainerTick)) {
         return false;
      } else {
         EventContainerTick other = (EventContainerTick)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getWindowId() != other.getWindowId()) {
            return false;
         } else {
            Object this$items = this.getItems();
            Object other$items = other.getItems();
            if (this$items == null) {
               if (other$items != null) {
                  return false;
               }
            } else if (!this$items.equals(other$items)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventContainerTick;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getWindowId();
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      return result;
   }

   public String toString() {
      int var10000 = this.getWindowId();
      return "EventContainerTick(windowId=" + var10000 + ", items=" + String.valueOf(this.getItems()) + ")";
   }

   public EventContainerTick(int windowId, List items) {
      this.windowId = windowId;
      this.items = items;
   }
}
