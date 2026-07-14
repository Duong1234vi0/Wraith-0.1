package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.util.HandSide;

public class EventTransformSideFirstPerson implements Event {
   private HandSide handSide;
   private float equippedProg;

   public HandSide getHandSide() {
      return this.handSide;
   }

   public float getEquippedProg() {
      return this.equippedProg;
   }

   public void setHandSide(HandSide handSide) {
      this.handSide = handSide;
   }

   public void setEquippedProg(float equippedProg) {
      this.equippedProg = equippedProg;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventTransformSideFirstPerson)) {
         return false;
      } else {
         EventTransformSideFirstPerson other = (EventTransformSideFirstPerson)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getEquippedProg(), other.getEquippedProg()) != 0) {
            return false;
         } else {
            Object this$handSide = this.getHandSide();
            Object other$handSide = other.getHandSide();
            if (this$handSide == null) {
               if (other$handSide != null) {
                  return false;
               }
            } else if (!this$handSide.equals(other$handSide)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventTransformSideFirstPerson;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getEquippedProg());
      Object $handSide = this.getHandSide();
      result = result * 59 + ($handSide == null ? 43 : $handSide.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getHandSide());
      return "EventTransformSideFirstPerson(handSide=" + var10000 + ", equippedProg=" + this.getEquippedProg() + ")";
   }

   public EventTransformSideFirstPerson(HandSide handSide, float equippedProg) {
      this.handSide = handSide;
      this.equippedProg = equippedProg;
   }
}
