package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventThirdPersonRender extends EventCancellable implements Event {
   private boolean thirdperson;

   public boolean isThirdperson() {
      return this.thirdperson;
   }

   public void setThirdperson(boolean thirdperson) {
      this.thirdperson = thirdperson;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventThirdPersonRender)) {
         return false;
      } else {
         EventThirdPersonRender other = (EventThirdPersonRender)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.isThirdperson() == other.isThirdperson();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventThirdPersonRender;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + (this.isThirdperson() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "EventThirdPersonRender(thirdperson=" + this.isThirdperson() + ")";
   }

   public EventThirdPersonRender(boolean thirdperson) {
      this.thirdperson = thirdperson;
   }
}
