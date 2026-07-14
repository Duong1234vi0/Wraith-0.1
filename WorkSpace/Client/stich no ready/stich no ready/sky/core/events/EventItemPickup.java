package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class EventItemPickup extends EventCancellable implements Event {
   private Entity collectedEntity;
   private Entity collector;
   private ItemStack itemStack;
   private int amount;

   public Entity getCollectedEntity() {
      return this.collectedEntity;
   }

   public Entity getCollector() {
      return this.collector;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public int getAmount() {
      return this.amount;
   }

   public void setCollectedEntity(Entity collectedEntity) {
      this.collectedEntity = collectedEntity;
   }

   public void setCollector(Entity collector) {
      this.collector = collector;
   }

   public void setItemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventItemPickup)) {
         return false;
      } else {
         EventItemPickup other = (EventItemPickup)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getAmount() != other.getAmount()) {
            return false;
         } else {
            label49: {
               Object this$collectedEntity = this.getCollectedEntity();
               Object other$collectedEntity = other.getCollectedEntity();
               if (this$collectedEntity == null) {
                  if (other$collectedEntity == null) {
                     break label49;
                  }
               } else if (this$collectedEntity.equals(other$collectedEntity)) {
                  break label49;
               }

               return false;
            }

            Object this$collector = this.getCollector();
            Object other$collector = other.getCollector();
            if (this$collector == null) {
               if (other$collector != null) {
                  return false;
               }
            } else if (!this$collector.equals(other$collector)) {
               return false;
            }

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
      return other instanceof EventItemPickup;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getAmount();
      Object $collectedEntity = this.getCollectedEntity();
      result = result * 59 + ($collectedEntity == null ? 43 : $collectedEntity.hashCode());
      Object $collector = this.getCollector();
      result = result * 59 + ($collector == null ? 43 : $collector.hashCode());
      Object $itemStack = this.getItemStack();
      result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getCollectedEntity());
      return "EventItemPickup(collectedEntity=" + var10000 + ", collector=" + String.valueOf(this.getCollector()) + ", itemStack=" + String.valueOf(this.getItemStack()) + ", amount=" + this.getAmount() + ")";
   }

   public EventItemPickup(Entity collectedEntity, Entity collector, ItemStack itemStack, int amount) {
      this.collectedEntity = collectedEntity;
      this.collector = collector;
      this.itemStack = itemStack;
      this.amount = amount;
   }
}
