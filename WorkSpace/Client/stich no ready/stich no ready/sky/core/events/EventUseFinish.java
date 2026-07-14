package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EventUseFinish extends EventCancellable implements Event {
   private ItemStack itemStack;
   private World worldIn;
   private LivingEntity entityLiving;

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public World getWorldIn() {
      return this.worldIn;
   }

   public LivingEntity getEntityLiving() {
      return this.entityLiving;
   }

   public void setItemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
   }

   public void setWorldIn(World worldIn) {
      this.worldIn = worldIn;
   }

   public void setEntityLiving(LivingEntity entityLiving) {
      this.entityLiving = entityLiving;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventUseFinish)) {
         return false;
      } else {
         EventUseFinish other = (EventUseFinish)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label47: {
               Object this$itemStack = this.getItemStack();
               Object other$itemStack = other.getItemStack();
               if (this$itemStack == null) {
                  if (other$itemStack == null) {
                     break label47;
                  }
               } else if (this$itemStack.equals(other$itemStack)) {
                  break label47;
               }

               return false;
            }

            Object this$worldIn = this.getWorldIn();
            Object other$worldIn = other.getWorldIn();
            if (this$worldIn == null) {
               if (other$worldIn != null) {
                  return false;
               }
            } else if (!this$worldIn.equals(other$worldIn)) {
               return false;
            }

            Object this$entityLiving = this.getEntityLiving();
            Object other$entityLiving = other.getEntityLiving();
            if (this$entityLiving == null) {
               if (other$entityLiving != null) {
                  return false;
               }
            } else if (!this$entityLiving.equals(other$entityLiving)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventUseFinish;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $itemStack = this.getItemStack();
      result = result * 59 + ($itemStack == null ? 43 : $itemStack.hashCode());
      Object $worldIn = this.getWorldIn();
      result = result * 59 + ($worldIn == null ? 43 : $worldIn.hashCode());
      Object $entityLiving = this.getEntityLiving();
      result = result * 59 + ($entityLiving == null ? 43 : $entityLiving.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getItemStack());
      return "EventUseFinish(itemStack=" + var10000 + ", worldIn=" + String.valueOf(this.getWorldIn()) + ", entityLiving=" + String.valueOf(this.getEntityLiving()) + ")";
   }

   public EventUseFinish(ItemStack itemStack, World worldIn, LivingEntity entityLiving) {
      this.itemStack = itemStack;
      this.worldIn = worldIn;
      this.entityLiving = entityLiving;
   }
}
