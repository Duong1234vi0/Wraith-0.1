package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class EventRotation extends EventCancellable implements Event {
   private Vector3d position;
   private Vector2f rotation;

   public Vector3d getPosition() {
      return this.position;
   }

   public Vector2f getRotation() {
      return this.rotation;
   }

   public void setPosition(Vector3d position) {
      this.position = position;
   }

   public void setRotation(Vector2f rotation) {
      this.rotation = rotation;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventRotation)) {
         return false;
      } else {
         EventRotation other = (EventRotation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$position = this.getPosition();
            Object other$position = other.getPosition();
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$rotation = this.getRotation();
            Object other$rotation = other.getRotation();
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventRotation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $position = this.getPosition();
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $rotation = this.getRotation();
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getPosition());
      return "EventRotation(position=" + var10000 + ", rotation=" + String.valueOf(this.getRotation()) + ")";
   }

   public EventRotation(Vector3d position, Vector2f rotation) {
      this.position = position;
      this.rotation = rotation;
   }
}
