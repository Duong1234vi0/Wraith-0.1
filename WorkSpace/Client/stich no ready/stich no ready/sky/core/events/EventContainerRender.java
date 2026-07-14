package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.inventory.container.Container;

public class EventContainerRender extends EventCancellable implements Event {
   private MatrixStack stack;
   private int guiLeft;
   private int guiTop;
   private Container container;

   public MatrixStack getStack() {
      return this.stack;
   }

   public int getGuiLeft() {
      return this.guiLeft;
   }

   public int getGuiTop() {
      return this.guiTop;
   }

   public Container getContainer() {
      return this.container;
   }

   public void setStack(MatrixStack stack) {
      this.stack = stack;
   }

   public void setGuiLeft(int guiLeft) {
      this.guiLeft = guiLeft;
   }

   public void setGuiTop(int guiTop) {
      this.guiTop = guiTop;
   }

   public void setContainer(Container container) {
      this.container = container;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventContainerRender)) {
         return false;
      } else {
         EventContainerRender other = (EventContainerRender)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getGuiLeft() != other.getGuiLeft()) {
            return false;
         } else if (this.getGuiTop() != other.getGuiTop()) {
            return false;
         } else {
            label40: {
               Object this$stack = this.getStack();
               Object other$stack = other.getStack();
               if (this$stack == null) {
                  if (other$stack == null) {
                     break label40;
                  }
               } else if (this$stack.equals(other$stack)) {
                  break label40;
               }

               return false;
            }

            Object this$container = this.getContainer();
            Object other$container = other.getContainer();
            if (this$container == null) {
               if (other$container != null) {
                  return false;
               }
            } else if (!this$container.equals(other$container)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventContainerRender;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getGuiLeft();
      result = result * 59 + this.getGuiTop();
      Object $stack = this.getStack();
      result = result * 59 + ($stack == null ? 43 : $stack.hashCode());
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getStack());
      return "EventContainerRender(stack=" + var10000 + ", guiLeft=" + this.getGuiLeft() + ", guiTop=" + this.getGuiTop() + ", container=" + String.valueOf(this.getContainer()) + ")";
   }

   public EventContainerRender(MatrixStack stack, int guiLeft, int guiTop, Container container) {
      this.stack = stack;
      this.guiLeft = guiLeft;
      this.guiTop = guiTop;
      this.container = container;
   }

   public static class Post extends EventContainerRender {
      public Post(MatrixStack stack, int guiLeft, int guiTop, Container container) {
         super(stack, guiLeft, guiTop, container);
      }
   }

   public static class Pre extends EventContainerRender {
      public Pre(MatrixStack stack, int guiLeft, int guiTop, Container container) {
         super(stack, guiLeft, guiTop, container);
      }
   }
}
