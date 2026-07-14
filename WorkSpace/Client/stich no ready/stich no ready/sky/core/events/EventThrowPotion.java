package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class EventThrowPotion extends EventCancellable implements Event {
   private final LivingEntity entity;
   private final ItemStack itemStack;
   private final double distanceFactor;
   private final List effects;

   public LivingEntity getEntity() {
      return this.entity;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public double getDistanceFactor() {
      return this.distanceFactor;
   }

   public List getEffects() {
      return this.effects;
   }

   public EventThrowPotion(LivingEntity entity, ItemStack itemStack, double distanceFactor, List effects) {
      this.entity = entity;
      this.itemStack = itemStack;
      this.distanceFactor = distanceFactor;
      this.effects = effects;
   }
}
