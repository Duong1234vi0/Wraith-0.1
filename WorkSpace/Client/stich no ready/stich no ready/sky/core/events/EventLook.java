package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventLook extends EventCancellable implements Event {
   public double yaw;
   public double pitch;

   public double getYaw() {
      return this.yaw;
   }

   public double getPitch() {
      return this.pitch;
   }

   public void setYaw(double yaw) {
      this.yaw = yaw;
   }

   public void setPitch(double pitch) {
      this.pitch = pitch;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventLook)) {
         return false;
      } else {
         EventLook other = (EventLook)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Double.compare(this.getYaw(), other.getYaw()) != 0) {
            return false;
         } else {
            return Double.compare(this.getPitch(), other.getPitch()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventLook;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      long $yaw = Double.doubleToLongBits(this.getYaw());
      result = result * 59 + (int)($yaw >>> 32 ^ $yaw);
      long $pitch = Double.doubleToLongBits(this.getPitch());
      result = result * 59 + (int)($pitch >>> 32 ^ $pitch);
      return result;
   }

   public String toString() {
      double var10000 = this.getYaw();
      return "EventLook(yaw=" + var10000 + ", pitch=" + this.getPitch() + ")";
   }

   public EventLook(double yaw, double pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }
}
