package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventMouseTick implements Event {
   private int button;

   public int getButton() {
      return this.button;
   }

   public void setButton(int button) {
      this.button = button;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventMouseTick)) {
         return false;
      } else {
         EventMouseTick other = (EventMouseTick)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getButton() == other.getButton();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventMouseTick;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getButton();
      return result;
   }

   public String toString() {
      return "EventMouseTick(button=" + this.getButton() + ")";
   }

   public EventMouseTick(int button) {
      this.button = button;
   }
}
