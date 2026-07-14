package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventPostUpdate extends EventCancellable implements Event {
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventPostUpdate)) {
         return false;
      } else {
         EventPostUpdate other = (EventPostUpdate)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventPostUpdate;
   }

   public int hashCode() {
      int result = true;
      return 1;
   }

   public String toString() {
      return "EventPostUpdate()";
   }
}
