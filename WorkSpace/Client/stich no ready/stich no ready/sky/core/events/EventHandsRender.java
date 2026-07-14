package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.ActiveRenderInfo;

public class EventHandsRender extends EventCancellable implements Event {
   private ActiveRenderInfo activeRenderInfo;
   private MatrixStack stack;
   private float part;

   public ActiveRenderInfo getActiveRenderInfo() {
      return this.activeRenderInfo;
   }

   public MatrixStack getStack() {
      return this.stack;
   }

   public float getPart() {
      return this.part;
   }

   public void setActiveRenderInfo(ActiveRenderInfo activeRenderInfo) {
      this.activeRenderInfo = activeRenderInfo;
   }

   public void setStack(MatrixStack stack) {
      this.stack = stack;
   }

   public void setPart(float part) {
      this.part = part;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventHandsRender)) {
         return false;
      } else {
         EventHandsRender other = (EventHandsRender)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getPart(), other.getPart()) != 0) {
            return false;
         } else {
            Object this$activeRenderInfo = this.getActiveRenderInfo();
            Object other$activeRenderInfo = other.getActiveRenderInfo();
            if (this$activeRenderInfo == null) {
               if (other$activeRenderInfo != null) {
                  return false;
               }
            } else if (!this$activeRenderInfo.equals(other$activeRenderInfo)) {
               return false;
            }

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
      return other instanceof EventHandsRender;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPart());
      Object $activeRenderInfo = this.getActiveRenderInfo();
      result = result * 59 + ($activeRenderInfo == null ? 43 : $activeRenderInfo.hashCode());
      Object $stack = this.getStack();
      result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getActiveRenderInfo());
      return "EventHandsRender(activeRenderInfo=" + var10000 + ", stack=" + String.valueOf(this.getStack()) + ", part=" + this.getPart() + ")";
   }

   public EventHandsRender(ActiveRenderInfo activeRenderInfo, MatrixStack stack, float part) {
      this.activeRenderInfo = activeRenderInfo;
      this.stack = stack;
      this.part = part;
   }

   public static class Post extends EventHandsRender {
      public Post(ActiveRenderInfo activeRenderInfo, MatrixStack stack, float part) {
         super(activeRenderInfo, stack, part);
      }
   }

   public static class Pre extends EventHandsRender {
      public Pre(ActiveRenderInfo activeRenderInfo, MatrixStack stack, float part) {
         super(activeRenderInfo, stack, part);
      }
   }
}
