package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.math.BlockPos;

public class EventBlockCollide extends EventCancellable implements Event {
   private BlockPos pos;

   public BlockPos getPos() {
      return this.pos;
   }

   public void setPos(BlockPos pos) {
      this.pos = pos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventBlockCollide)) {
         return false;
      } else {
         EventBlockCollide other = (EventBlockCollide)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$pos = this.getPos();
            Object other$pos = other.getPos();
            if (this$pos == null) {
               if (other$pos != null) {
                  return false;
               }
            } else if (!this$pos.equals(other$pos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventBlockCollide;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $pos = this.getPos();
      result = result * 59 + ($pos == null ? 43 : $pos.hashCode());
      return result;
   }

   public String toString() {
      return "EventBlockCollide(pos=" + String.valueOf(this.getPos()) + ")";
   }

   public EventBlockCollide(BlockPos pos) {
      this.pos = pos;
   }
}
