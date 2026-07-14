package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;

public class EventEntityHitBox extends EventCancellable implements Event {
   private Entity entity;
   private float size;

   public Entity getEntity() {
      return this.entity;
   }

   public float getSize() {
      return this.size;
   }

   public void setEntity(Entity entity) {
      this.entity = entity;
   }

   public void setSize(float size) {
      this.size = size;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getEntity());
      return "EventEntityHitBox(entity=" + var10000 + ", size=" + this.getSize() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventEntityHitBox)) {
         return false;
      } else {
         EventEntityHitBox other = (EventEntityHitBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else if (Float.compare(this.getSize(), other.getSize()) != 0) {
            return false;
         } else {
            Object this$entity = this.getEntity();
            Object other$entity = other.getEntity();
            if (this$entity == null) {
               if (other$entity == null) {
                  return true;
               }
            } else if (this$entity.equals(other$entity)) {
               return true;
            }

            return false;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventEntityHitBox;
   }

   public int hashCode() {
      int PRIME = true;
      int result = super.hashCode();
      result = result * 59 + Float.floatToIntBits(this.getSize());
      Object $entity = this.getEntity();
      result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
      return result;
   }

   public EventEntityHitBox(Entity entity, float size) {
      this.entity = entity;
      this.size = size;
   }
}
