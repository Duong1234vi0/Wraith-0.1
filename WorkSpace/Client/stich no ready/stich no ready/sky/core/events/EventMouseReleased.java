package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventMouseReleased extends EventCancellable implements Event {
   private final int key;
   private final double mouseX;
   private final double mouseY;

   public int getKey() {
      return this.key;
   }

   public double getMouseX() {
      return this.mouseX;
   }

   public double getMouseY() {
      return this.mouseY;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventMouseReleased)) {
         return false;
      } else {
         EventMouseReleased other = (EventMouseReleased)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getKey() != other.getKey()) {
            return false;
         } else if (Double.compare(this.getMouseX(), other.getMouseX()) != 0) {
            return false;
         } else {
            return Double.compare(this.getMouseY(), other.getMouseY()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventMouseReleased;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getKey();
      long $mouseX = Double.doubleToLongBits(this.getMouseX());
      result = result * 59 + (int)($mouseX >>> 32 ^ $mouseX);
      long $mouseY = Double.doubleToLongBits(this.getMouseY());
      result = result * 59 + (int)($mouseY >>> 32 ^ $mouseY);
      return result;
   }

   public String toString() {
      int var10000 = this.getKey();
      return "EventMouseReleased(key=" + var10000 + ", mouseX=" + this.getMouseX() + ", mouseY=" + this.getMouseY() + ")";
   }

   public EventMouseReleased(int key, double mouseX, double mouseY) {
      this.key = key;
      this.mouseX = mouseX;
      this.mouseY = mouseY;
   }
}
