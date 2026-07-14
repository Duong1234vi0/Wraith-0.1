package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;

public class EventRender3D extends EventCancellable implements Event {
   float partialTicks;
   public MatrixStack matrixStack;

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public MatrixStack getMatrixStack() {
      return this.matrixStack;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public void setMatrixStack(MatrixStack matrixStack) {
      this.matrixStack = matrixStack;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventRender3D)) {
         return false;
      } else {
         EventRender3D other = (EventRender3D)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getPartialTicks(), other.getPartialTicks()) != 0) {
            return false;
         } else {
            Object this$matrixStack = this.getMatrixStack();
            Object other$matrixStack = other.getMatrixStack();
            if (this$matrixStack == null) {
               if (other$matrixStack != null) {
                  return false;
               }
            } else if (!this$matrixStack.equals(other$matrixStack)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventRender3D;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPartialTicks());
      Object $matrixStack = this.getMatrixStack();
      result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
      return result;
   }

   public String toString() {
      float var10000 = this.getPartialTicks();
      return "EventRender3D(partialTicks=" + var10000 + ", matrixStack=" + String.valueOf(this.getMatrixStack()) + ")";
   }

   public EventRender3D(float partialTicks, MatrixStack matrixStack) {
      this.partialTicks = partialTicks;
      this.matrixStack = matrixStack;
   }
}
