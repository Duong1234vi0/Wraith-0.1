package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;

public class EventPopTotem extends EventCancellable implements Event {
   private Entity entity;

   public Entity getEntity() {
      return this.entity;
   }

   public EventPopTotem(Entity entity) {
      this.entity = entity;
   }
}
