package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.LivingEntity;

public class EventTravel extends EventCancellable implements Event {
   private LivingEntity entity;

   public LivingEntity getEntity() {
      return this.entity;
   }

   public void setEntity(LivingEntity entity) {
      this.entity = entity;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventTravel)) {
         return false;
      } else {
         EventTravel other = (EventTravel)o;
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
      return other instanceof EventTravel;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $entity = this.getEntity();
      result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
      return result;
   }

   public String toString() {
      return "EventTravel(entity=" + String.valueOf(this.getEntity()) + ")";
   }

   public EventTravel(LivingEntity entity) {
      this.entity = entity;
   }
}
