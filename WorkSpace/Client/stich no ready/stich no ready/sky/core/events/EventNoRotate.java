package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventNoRotate extends EventCancellable implements Event {
   float yaw;
   float pitch;

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventNoRotate)) {
         return false;
      } else {
         EventNoRotate other = (EventNoRotate)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getYaw(), other.getYaw()) != 0) {
            return false;
         } else {
            return Float.compare(this.getPitch(), other.getPitch()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventNoRotate;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getYaw());
      result = result * 59 + Float.floatToIntBits(this.getPitch());
      return result;
   }

   public String toString() {
      float var10000 = this.getYaw();
      return "EventNoRotate(yaw=" + var10000 + ", pitch=" + this.getPitch() + ")";
   }

   public EventNoRotate(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }
}
