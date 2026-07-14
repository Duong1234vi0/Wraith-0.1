package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;

public class EventEntityRayTrace extends EventCancellable implements Event {
   private final Entity entity;

   public EventEntityRayTrace(Entity entity) {
      this.entity = entity;
   }

   public Entity getEntity() {
      return this.entity;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventEntityRayTrace)) {
         return false;
      } else {
         EventEntityRayTrace other = (EventEntityRayTrace)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$entity = this.getEntity();
            Object other$entity = other.getEntity();
            if (this$entity == null) {
               if (other$entity != null) {
                  return false;
               }
            } else if (!this$entity.equals(other$entity)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventEntityRayTrace;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $entity = this.getEntity();
      result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
      return result;
   }

   public String toString() {
      return "EventEntityRayTrace(entity=" + String.valueOf(this.getEntity()) + ")";
   }
}
