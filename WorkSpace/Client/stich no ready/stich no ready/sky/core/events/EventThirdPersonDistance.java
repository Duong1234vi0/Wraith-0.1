package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventThirdPersonDistance extends EventCancellable implements Event {
   private double distance;

   public double getDistance() {
      return this.distance;
   }

   public void setDistance(double distance) {
      this.distance = distance;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventThirdPersonDistance)) {
         return false;
      } else {
         EventThirdPersonDistance other = (EventThirdPersonDistance)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return Double.compare(this.getDistance(), other.getDistance()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventThirdPersonDistance;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      long $distance = Double.doubleToLongBits(this.getDistance());
      result = result * 59 + (int)($distance >>> 32 ^ $distance);
      return result;
   }

   public String toString() {
      return "EventThirdPersonDistance(distance=" + this.getDistance() + ")";
   }

   public EventThirdPersonDistance(double distance) {
      this.distance = distance;
   }
}
