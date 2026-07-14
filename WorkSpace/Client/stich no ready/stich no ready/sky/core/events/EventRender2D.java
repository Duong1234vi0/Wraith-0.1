package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;

public class EventRender2D extends EventCancellable implements Event {
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
      } else if (!(o instanceof EventRender2D)) {
         return false;
      } else {
         EventRender2D other = (EventRender2D)o;
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
      return other instanceof EventRender2D;
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
      return "EventRender2D(Stack=" + var10000 + ", partialTicks=" + this.getPartialTicks() + ")";
   }

   public EventRender2D(MatrixStack Stack, float partialTicks) {
      this.Stack = Stack;
      this.partialTicks = partialTicks;
   }

   public static class Send extends EventRender2D {
      public Send(MatrixStack stack, float partialTicks) {
         super(stack, partialTicks);
      }
   }

   public static class Post extends EventRender2D {
      public Post(MatrixStack stack, float partialTicks) {
         super(stack, partialTicks);
      }
   }

   public static class Pre extends EventRender2D {
      public Pre(MatrixStack stack, float partialTicks) {
         super(stack, partialTicks);
      }
   }
}
