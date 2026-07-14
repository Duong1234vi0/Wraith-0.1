package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventMouseClicked extends EventCancellable implements Event {
   private final int key;
   private final float mouseX;
   private final float mouseY;

   public int getKey() {
      return this.key;
   }

   public float getMouseX() {
      return this.mouseX;
   }

   public float getMouseY() {
      return this.mouseY;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventMouseClicked)) {
         return false;
      } else {
         EventMouseClicked other = (EventMouseClicked)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getKey() != other.getKey()) {
            return false;
         } else if (Float.compare(this.getMouseX(), other.getMouseX()) != 0) {
            return false;
         } else {
            return Float.compare(this.getMouseY(), other.getMouseY()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventMouseClicked;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getKey();
      result = result * 59 + Float.floatToIntBits(this.getMouseX());
      result = result * 59 + Float.floatToIntBits(this.getMouseY());
      return result;
   }

   public String toString() {
      int var10000 = this.getKey();
      return "EventMouseClicked(key=" + var10000 + ", mouseX=" + this.getMouseX() + ", mouseY=" + this.getMouseY() + ")";
   }

   public EventMouseClicked(int key, float mouseX, float mouseY) {
      this.key = key;
      this.mouseX = mouseX;
      this.mouseY = mouseY;
   }
}
