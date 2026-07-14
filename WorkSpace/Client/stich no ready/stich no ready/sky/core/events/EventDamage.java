package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventDamage extends EventCancellable implements Event {
   private final DamageType damageType;

   public DamageType getDamageType() {
      return this.damageType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventDamage)) {
         return false;
      } else {
         EventDamage other = (EventDamage)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$damageType = this.getDamageType();
            Object other$damageType = other.getDamageType();
            if (this$damageType == null) {
               if (other$damageType != null) {
                  return false;
               }
            } else if (!this$damageType.equals(other$damageType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventDamage;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $damageType = this.getDamageType();
      result = result * 59 + ($damageType == null ? 43 : $damageType.hashCode());
      return result;
   }

   public String toString() {
      return "EventDamage(damageType=" + String.valueOf(this.getDamageType()) + ")";
   }

   public EventDamage(DamageType damageType) {
      this.damageType = damageType;
   }

   public static enum DamageType {
      FALL,
      ARROW,
      ENDER_PEARL,
      BLOCK;

      // $FF: synthetic method
      private static DamageType[] $values() {
         return new DamageType[]{FALL, ARROW, ENDER_PEARL, BLOCK};
      }
   }
}
