package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.util.math.vector.Vector3d;

public class EventOnMovePost implements Event {
   private float speed;
   private Vector3d movementInput;

   public float getSpeed() {
      return this.speed;
   }

   public Vector3d getMovementInput() {
      return this.movementInput;
   }

   public void setSpeed(float speed) {
      this.speed = speed;
   }

   public void setMovementInput(Vector3d movementInput) {
      this.movementInput = movementInput;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventOnMovePost)) {
         return false;
      } else {
         EventOnMovePost other = (EventOnMovePost)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getSpeed(), other.getSpeed()) != 0) {
            return false;
         } else {
            Object this$movementInput = this.getMovementInput();
            Object other$movementInput = other.getMovementInput();
            if (this$movementInput == null) {
               if (other$movementInput != null) {
                  return false;
               }
            } else if (!this$movementInput.equals(other$movementInput)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventOnMovePost;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getSpeed());
      Object $movementInput = this.getMovementInput();
      result = result * 59 + ($movementInput == null ? 43 : $movementInput.hashCode());
      return result;
   }

   public String toString() {
      float var10000 = this.getSpeed();
      return "EventOnMovePost(speed=" + var10000 + ", movementInput=" + String.valueOf(this.getMovementInput()) + ")";
   }

   public EventOnMovePost(float speed, Vector3d movementInput) {
      this.speed = speed;
      this.movementInput = movementInput;
   }
}
