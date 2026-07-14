package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.math.vector.Matrix4f;

public class EventRenderWor extends EventCancellable implements Event {
   public float partialTicks;
   public MatrixStack matrixStack;
   public Matrix4f matrix;

   public EventRenderWor(float partialTicks, MatrixStack matrixStack, Matrix4f matrix) {
      this.partialTicks = partialTicks;
      this.matrixStack = matrixStack;
      this.matrix = matrix;
   }

   public void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public void setMatrixStack(MatrixStack matrixStack) {
      this.matrixStack = matrixStack;
   }

   public void setMatrix(Matrix4f matrix) {
      this.matrix = matrix;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventRenderWor)) {
         return false;
      } else {
         EventRenderWor other = (EventRenderWor)o;
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

            Object this$matrix = this.getMatrix();
            Object other$matrix = other.getMatrix();
            if (this$matrix == null) {
               if (other$matrix != null) {
                  return false;
               }
            } else if (!this$matrix.equals(other$matrix)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventRenderWor;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPartialTicks());
      Object $matrixStack = this.getMatrixStack();
      result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
      Object $matrix = this.getMatrix();
      result = result * 59 + ($matrix == null ? 43 : $matrix.hashCode());
      return result;
   }

   public String toString() {
      float var10000 = this.getPartialTicks();
      return "EventRenderWor(partialTicks=" + var10000 + ", matrixStack=" + String.valueOf(this.getMatrixStack()) + ", matrix=" + String.valueOf(this.getMatrix()) + ")";
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public MatrixStack getMatrixStack() {
      return this.matrixStack;
   }

   public Matrix4f getMatrix() {
      return this.matrix;
   }
}
