package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventUpdate implements Event {
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventUpdate)) {
         return false;
      } else {
         EventUpdate other = (EventUpdate)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventUpdate;
   }

   public int hashCode() {
      int result = true;
      return 1;
   }

   public String toString() {
      return "EventUpdate()";
   }
}
