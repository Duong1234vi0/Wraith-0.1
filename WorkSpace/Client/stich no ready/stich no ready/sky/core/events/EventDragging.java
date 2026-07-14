package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventDragging extends EventCancellable implements Event {
   private final double mouseX;
   private final double mouseY;

   public double getMouseX() {
      return this.mouseX;
   }

   public double getMouseY() {
      return this.mouseY;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventDragging)) {
         return false;
      } else {
         EventDragging other = (EventDragging)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Double.compare(this.getMouseX(), other.getMouseX()) != 0) {
            return false;
         } else {
            return Double.compare(this.getMouseY(), other.getMouseY()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventDragging;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      long $mouseX = Double.doubleToLongBits(this.getMouseX());
      result = result * 59 + (int)($mouseX >>> 32 ^ $mouseX);
      long $mouseY = Double.doubleToLongBits(this.getMouseY());
      result = result * 59 + (int)($mouseY >>> 32 ^ $mouseY);
      return result;
   }

   public String toString() {
      double var10000 = this.getMouseX();
      return "EventDragging(mouseX=" + var10000 + ", mouseY=" + this.getMouseY() + ")";
   }

   public EventDragging(double mouseX, double mouseY) {
      this.mouseX = mouseX;
      this.mouseY = mouseY;
   }
}
