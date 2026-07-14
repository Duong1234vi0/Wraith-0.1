package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class EventEffectKill implements Event {
   private LivingEntity entity;
   private DamageSource cause;

   public LivingEntity getEntity() {
      return this.entity;
   }

   public DamageSource getCause() {
      return this.cause;
   }

   public EventEffectKill(LivingEntity entity, DamageSource cause) {
      this.entity = entity;
      this.cause = cause;
   }
}
