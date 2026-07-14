package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.util.math.vector.Vector3d;

public class EventFireworkVelocity implements Event {
   private Vector3d velocity;

   public EventFireworkVelocity(Vector3d velocity) {
      this.velocity = velocity;
   }

   public Vector3d getVelocity() {
      return this.velocity;
   }

   public void setVelocity(Vector3d velocity) {
      this.velocity = velocity;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventFireworkVelocity)) {
         return false;
      } else {
         EventFireworkVelocity other = (EventFireworkVelocity)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$velocity = this.getVelocity();
            Object other$velocity = other.getVelocity();
            if (this$velocity == null) {
               if (other$velocity != null) {
                  return false;
               }
            } else if (!this$velocity.equals(other$velocity)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventFireworkVelocity;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $velocity = this.getVelocity();
      result = result * 59 + ($velocity == null ? 43 : $velocity.hashCode());
      return result;
   }

   public String toString() {
      return "EventFireworkVelocity(velocity=" + String.valueOf(this.getVelocity()) + ")";
   }
}
