package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventNoSlow extends EventCancellable implements Event {
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventNoSlow)) {
         return false;
      } else {
         EventNoSlow other = (EventNoSlow)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventNoSlow;
   }

   public int hashCode() {
      int result = true;
      return 1;
   }

   public String toString() {
      return "EventNoSlow()";
   }
}
