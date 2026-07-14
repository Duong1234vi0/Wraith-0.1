package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.item.ItemStack;

public class EventUseEnderPearl extends EventCancellable implements Event {
   private ItemStack itemStack;
   private int cooldownTicks;

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public int getCooldownTicks() {
      return this.cooldownTicks;
   }

   public void setItemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
   }

   public void setCooldownTicks(int cooldownTicks) {
      this.cooldownTicks = cooldownTicks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventUseEnderPearl)) {
         return false;
      } else {
         EventUseEnderPearl other = (EventUseEnderPearl)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getCooldownTicks() != other.getCooldownTicks()) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventUseEnderPearl;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getCooldownTicks();
      Object $itemStack = this.getItemStack();
      result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getItemStack());
      return "EventUseEnderPearl(itemStack=" + var10000 + ", cooldownTicks=" + this.getCooldownTicks() + ")";
   }

   public EventUseEnderPearl(ItemStack itemStack, int cooldownTicks) {
      this.itemStack = itemStack;
      this.cooldownTicks = cooldownTicks;
   }
}
