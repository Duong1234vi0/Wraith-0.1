package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;

public class EventCriticalHit extends EventCancellable implements Event {
   private final Entity targetEntity;

   public Entity getTarget() {
      return this.targetEntity;
   }

   public Entity getTargetEntity() {
      return this.targetEntity;
   }

   public EventCriticalHit(Entity targetEntity) {
      this.targetEntity = targetEntity;
   }
}
