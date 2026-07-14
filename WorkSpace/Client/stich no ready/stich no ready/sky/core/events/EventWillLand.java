package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventWillLand extends EventCancellable implements Event {
   private boolean willLand;

   public boolean isWillLand() {
      return this.willLand;
   }

   public void setWillLand(boolean willLand) {
      this.willLand = willLand;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventWillLand)) {
         return false;
      } else {
         EventWillLand other = (EventWillLand)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.isWillLand() == other.isWillLand();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventWillLand;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + (this.isWillLand() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "EventWillLand(willLand=" + this.isWillLand() + ")";
   }

   public EventWillLand(boolean willLand) {
      this.willLand = willLand;
   }
}
