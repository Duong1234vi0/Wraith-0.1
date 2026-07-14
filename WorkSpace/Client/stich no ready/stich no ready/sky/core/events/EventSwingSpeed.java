package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.Hand;

public class EventSwingSpeed extends EventCancellable implements Event {
   private int swipeSpeed;
   private Hand hand;

   public int getSwipeSpeed() {
      return this.swipeSpeed;
   }

   public Hand getHand() {
      return this.hand;
   }

   public void setSwipeSpeed(int swipeSpeed) {
      this.swipeSpeed = swipeSpeed;
   }

   public void setHand(Hand hand) {
      this.hand = hand;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSwingSpeed)) {
         return false;
      } else {
         EventSwingSpeed other = (EventSwingSpeed)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getSwipeSpeed() != other.getSwipeSpeed()) {
            return false;
         } else {
            Object this$hand = this.getHand();
            Object other$hand = other.getHand();
            if (this$hand == null) {
               if (other$hand != null) {
                  return false;
               }
            } else if (!this$hand.equals(other$hand)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventSwingSpeed;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getSwipeSpeed();
      Object $hand = this.getHand();
      result = result * 59 + ($hand == null ? 43 : $hand.hashCode());
      return result;
   }

   public String toString() {
      int var10000 = this.getSwipeSpeed();
      return "EventSwingSpeed(swipeSpeed=" + var10000 + ", hand=" + String.valueOf(this.getHand()) + ")";
   }

   public EventSwingSpeed(int swipeSpeed, Hand hand) {
      this.swipeSpeed = swipeSpeed;
      this.hand = hand;
   }
}
