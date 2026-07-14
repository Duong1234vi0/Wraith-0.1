package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.HandSide;

public class EventViewModel extends EventCancellable implements Event {
   private MatrixStack matrixStack;
   private HandSide handside;

   public MatrixStack getMatrixStack() {
      return this.matrixStack;
   }

   public HandSide getHandside() {
      return this.handside;
   }

   public void setMatrixStack(MatrixStack matrixStack) {
      this.matrixStack = matrixStack;
   }

   public void setHandside(HandSide handside) {
      this.handside = handside;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventViewModel)) {
         return false;
      } else {
         EventViewModel other = (EventViewModel)o;
         if (!other.canEqual(this)) {
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

            Object this$handside = this.getHandside();
            Object other$handside = other.getHandside();
            if (this$handside == null) {
               if (other$handside != null) {
                  return false;
               }
            } else if (!this$handside.equals(other$handside)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventViewModel;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $matrixStack = this.getMatrixStack();
      result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
      Object $handside = this.getHandside();
      result = result * 59 + ($handside == null ? 43 : $handside.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getMatrixStack());
      return "EventViewModel(matrixStack=" + var10000 + ", handside=" + String.valueOf(this.getHandside()) + ")";
   }

   public EventViewModel(MatrixStack matrixStack, HandSide handside) {
      this.matrixStack = matrixStack;
      this.handside = handside;
   }
}
