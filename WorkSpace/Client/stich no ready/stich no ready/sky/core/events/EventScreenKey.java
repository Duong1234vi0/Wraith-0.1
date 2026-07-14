package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventScreenKey implements Event {
   private int key;
   private boolean hold;

   public int getKey() {
      return this.key;
   }

   public boolean isHold() {
      return this.hold;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public void setHold(boolean hold) {
      this.hold = hold;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventScreenKey)) {
         return false;
      } else {
         EventScreenKey other = (EventScreenKey)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getKey() != other.getKey()) {
            return false;
         } else {
            return this.isHold() == other.isHold();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventScreenKey;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getKey();
      result = result * 59 + (this.isHold() ? 79 : 97);
      return result;
   }

   public String toString() {
      int var10000 = this.getKey();
      return "EventScreenKey(key=" + var10000 + ", hold=" + this.isHold() + ")";
   }

   public EventScreenKey(int key, boolean hold) {
      this.key = key;
      this.hold = hold;
   }
}
