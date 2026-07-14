package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.math.vector.Vector3d;

public class EventElytraFlying extends EventCancellable implements Event {
   private Vector3d vector3d;
   private float f;

   public Vector3d getVector3d() {
      return this.vector3d;
   }

   public float getF() {
      return this.f;
   }

   public void setVector3d(Vector3d vector3d) {
      this.vector3d = vector3d;
   }

   public void setF(float f) {
      this.f = f;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventElytraFlying)) {
         return false;
      } else {
         EventElytraFlying other = (EventElytraFlying)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getF(), other.getF()) != 0) {
            return false;
         } else {
            Object this$vector3d = this.getVector3d();
            Object other$vector3d = other.getVector3d();
            if (this$vector3d == null) {
               if (other$vector3d != null) {
                  return false;
               }
            } else if (!this$vector3d.equals(other$vector3d)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventElytraFlying;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getF());
      Object $vector3d = this.getVector3d();
      result = result * 59 + ($vector3d == null ? 43 : $vector3d.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getVector3d());
      return "EventElytraFlying(vector3d=" + var10000 + ", f=" + this.getF() + ")";
   }

   public EventElytraFlying(Vector3d vector3d, float f) {
      this.vector3d = vector3d;
      this.f = f;
   }
}
