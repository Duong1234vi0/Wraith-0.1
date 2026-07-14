package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.block.BlockState;

public class EventGetDigSpeed extends EventCancellable implements Event {
   private BlockState blockState;
   private float digSpeed;

   public BlockState getBlockState() {
      return this.blockState;
   }

   public float getDigSpeed() {
      return this.digSpeed;
   }

   public void setBlockState(BlockState blockState) {
      this.blockState = blockState;
   }

   public void setDigSpeed(float digSpeed) {
      this.digSpeed = digSpeed;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventGetDigSpeed)) {
         return false;
      } else {
         EventGetDigSpeed other = (EventGetDigSpeed)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getDigSpeed(), other.getDigSpeed()) != 0) {
            return false;
         } else {
            Object this$blockState = this.getBlockState();
            Object other$blockState = other.getBlockState();
            if (this$blockState == null) {
               if (other$blockState != null) {
                  return false;
               }
            } else if (!this$blockState.equals(other$blockState)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventGetDigSpeed;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getDigSpeed());
      Object $blockState = this.getBlockState();
      result = result * 59 + ($blockState == null ? 43 : $blockState.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getBlockState());
      return "EventGetDigSpeed(blockState=" + var10000 + ", digSpeed=" + this.getDigSpeed() + ")";
   }

   public EventGetDigSpeed(BlockState blockState, float digSpeed) {
      this.blockState = blockState;
      this.digSpeed = digSpeed;
   }
}
