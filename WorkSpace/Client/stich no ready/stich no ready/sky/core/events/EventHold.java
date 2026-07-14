package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventHold implements Event {
   private int key;

   public int getKey() {
      return this.key;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventHold)) {
         return false;
      } else {
         EventHold other = (EventHold)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getKey() == other.getKey();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventHold;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getKey();
      return result;
   }

   public String toString() {
      return "EventHold(key=" + this.getKey() + ")";
   }

   public EventHold(int key) {
      this.key = key;
   }
}
