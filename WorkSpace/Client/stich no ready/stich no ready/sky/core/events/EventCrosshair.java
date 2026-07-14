package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;

public class EventCrosshair extends EventCancellable implements Event {
   private MatrixStack Stack;
   private float partialTicks;

   public MatrixStack getStack() {
      return this.Stack;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setStack(MatrixStack Stack) {
      this.Stack = Stack;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventCrosshair)) {
         return false;
      } else {
         EventCrosshair other = (EventCrosshair)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getPartialTicks(), other.getPartialTicks()) != 0) {
            return false;
         } else {
            Object this$Stack = this.getStack();
            Object other$Stack = other.getStack();
            if (this$Stack == null) {
               if (other$Stack != null) {
                  return false;
               }
            } else if (!this$Stack.equals(other$Stack)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventCrosshair;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPartialTicks());
      Object $Stack = this.getStack();
      result = result * 59 + ($Stack == null ? 43 : $Stack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getStack());
      return "EventCrosshair(Stack=" + var10000 + ", partialTicks=" + this.getPartialTicks() + ")";
   }

   public EventCrosshair(MatrixStack Stack, float partialTicks) {
      this.Stack = Stack;
      this.partialTicks = partialTicks;
   }
}
