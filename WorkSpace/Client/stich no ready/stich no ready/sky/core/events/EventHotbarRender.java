package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;

public class EventHotbarRender extends EventCancellable implements Event {
   private MatrixStack stack;
   private float partialTicks;

   public MatrixStack getStack() {
      return this.stack;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setStack(MatrixStack stack) {
      this.stack = stack;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventHotbarRender)) {
         return false;
      } else {
         EventHotbarRender other = (EventHotbarRender)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getPartialTicks(), other.getPartialTicks()) != 0) {
            return false;
         } else {
            Object this$stack = this.getStack();
            Object other$stack = other.getStack();
            if (this$stack == null) {
               if (other$stack != null) {
                  return false;
               }
            } else if (!this$stack.equals(other$stack)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventHotbarRender;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPartialTicks());
      Object $stack = this.getStack();
      result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getStack());
      return "EventHotbarRender(stack=" + var10000 + ", partialTicks=" + this.getPartialTicks() + ")";
   }

   public EventHotbarRender(MatrixStack stack, float partialTicks) {
      this.stack = stack;
      this.partialTicks = partialTicks;
   }
}
