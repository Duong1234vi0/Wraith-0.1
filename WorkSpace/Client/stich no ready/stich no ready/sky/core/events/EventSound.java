package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.client.audio.ISound;

public class EventSound extends EventCancellable implements Event {
   private ISound iSound;
   private float factor;

   public ISound getISound() {
      return this.iSound;
   }

   public float getFactor() {
      return this.factor;
   }

   public void setISound(ISound iSound) {
      this.iSound = iSound;
   }

   public void setFactor(float factor) {
      this.factor = factor;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSound)) {
         return false;
      } else {
         EventSound other = (EventSound)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getFactor(), other.getFactor()) != 0) {
            return false;
         } else {
            Object this$iSound = this.getISound();
            Object other$iSound = other.getISound();
            if (this$iSound == null) {
               if (other$iSound != null) {
                  return false;
               }
            } else if (!this$iSound.equals(other$iSound)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventSound;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getFactor());
      Object $iSound = this.getISound();
      result = result * 59 + ($iSound == null ? 43 : $iSound.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getISound());
      return "EventSound(iSound=" + var10000 + ", factor=" + this.getFactor() + ")";
   }

   public EventSound(ISound iSound, float factor) {
      this.iSound = iSound;
      this.factor = factor;
   }
}
