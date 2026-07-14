package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;

public class EventAttack extends EventCancellable implements Event {
   private Entity target;

   public Entity getTarget() {
      return this.target;
   }

   public void setTarget(Entity target) {
      this.target = target;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventAttack)) {
         return false;
      } else {
         EventAttack other = (EventAttack)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$target = this.getTarget();
            Object other$target = other.getTarget();
            if (this$target == null) {
               if (other$target != null) {
                  return false;
               }
            } else if (!this$target.equals(other$target)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventAttack;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $target = this.getTarget();
      result = result * 59 + ($target == null ? 43 : $target.hashCode());
      return result;
   }

   public String toString() {
      return "EventAttack(target=" + String.valueOf(this.getTarget()) + ")";
   }

   public EventAttack(Entity target) {
      this.target = target;
   }
}
