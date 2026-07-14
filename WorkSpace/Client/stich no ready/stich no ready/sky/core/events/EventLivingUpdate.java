package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventLivingUpdate implements Event {
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventLivingUpdate)) {
         return false;
      } else {
         EventLivingUpdate other = (EventLivingUpdate)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventLivingUpdate;
   }

   public int hashCode() {
      int result = true;
      return 1;
   }

   public String toString() {
      return "EventLivingUpdate()";
   }
}
