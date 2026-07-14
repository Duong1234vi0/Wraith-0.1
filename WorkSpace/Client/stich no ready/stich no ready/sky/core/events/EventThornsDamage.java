package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class EventThornsDamage extends EventCancellable implements Event {
   private final Entity attacker;
   private final LivingEntity user;
   private final int level;
   private float damage;

   public EventThornsDamage(Entity attacker, LivingEntity user, int level, float damage) {
      this.attacker = attacker;
      this.user = user;
      this.level = level;
      this.damage = damage;
   }

   public Entity getAttacker() {
      return this.attacker;
   }

   public LivingEntity getUser() {
      return this.user;
   }

   public int getLevel() {
      return this.level;
   }

   public float getDamage() {
      return this.damage;
   }

   public void setDamage(float damage) {
      this.damage = damage;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getAttacker());
      return "EventThornsDamage(attacker=" + var10000 + ", user=" + String.valueOf(this.getUser()) + ", level=" + this.getLevel() + ", damage=" + this.getDamage() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventThornsDamage)) {
         return false;
      } else {
         EventThornsDamage other = (EventThornsDamage)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else if (Float.compare(this.getDamage(), other.getDamage()) != 0) {
            return false;
         } else {
            label40: {
               Object this$attacker = this.getAttacker();
               Object other$attacker = other.getAttacker();
               if (this$attacker == null) {
                  if (other$attacker == null) {
                     break label40;
                  }
               } else if (this$attacker.equals(other$attacker)) {
                  break label40;
               }

               return false;
            }

            Object this$user = this.getUser();
            Object other$user = other.getUser();
            if (this$user == null) {
               if (other$user != null) {
                  return false;
               }
            } else if (!this$user.equals(other$user)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventThornsDamage;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getLevel();
      result = result * 59 + Float.floatToIntBits(this.getDamage());
      Object $attacker = this.getAttacker();
      result = result * 59 + ($attacker == null ? 43 : $attacker.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      return result;
   }
}
