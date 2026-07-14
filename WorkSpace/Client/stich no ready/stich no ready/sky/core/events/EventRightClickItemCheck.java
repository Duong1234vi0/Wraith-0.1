package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class EventRightClickItemCheck extends EventCancellable implements Event {
   private ItemStack itemStack;
   private Hand hand;

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public Hand getHand() {
      return this.hand;
   }

   public void setItemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
   }

   public void setHand(Hand hand) {
      this.hand = hand;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventRightClickItemCheck)) {
         return false;
      } else {
         EventRightClickItemCheck other = (EventRightClickItemCheck)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$itemStack = this.getItemStack();
            Object other$itemStack = other.getItemStack();
            if (this$itemStack == null) {
               if (other$itemStack != null) {
                  return false;
               }
            } else if (!this$itemStack.equals(other$itemStack)) {
               return false;
            }

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
      return other instanceof EventRightClickItemCheck;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $itemStack = this.getItemStack();
      result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
      Object $hand = this.getHand();
      result = result * 59 + ($hand == null ? 43 : $hand.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getItemStack());
      return "EventRightClickItemCheck(itemStack=" + var10000 + ", hand=" + String.valueOf(this.getHand()) + ")";
   }

   public EventRightClickItemCheck(ItemStack itemStack, Hand hand) {
      this.itemStack = itemStack;
      this.hand = hand;
   }
}
