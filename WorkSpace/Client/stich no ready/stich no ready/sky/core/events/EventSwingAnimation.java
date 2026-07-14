package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.Hand;

public class EventSwingAnimation extends EventCancellable implements Event {
   private AbstractClientPlayerEntity player;
   private float swingProgress;
   private Hand hand;
   private MatrixStack matrixStack;

   public AbstractClientPlayerEntity getPlayer() {
      return this.player;
   }

   public float getSwingProgress() {
      return this.swingProgress;
   }

   public Hand getHand() {
      return this.hand;
   }

   public MatrixStack getMatrixStack() {
      return this.matrixStack;
   }

   public void setPlayer(AbstractClientPlayerEntity player) {
      this.player = player;
   }

   public void setSwingProgress(float swingProgress) {
      this.swingProgress = swingProgress;
   }

   public void setHand(Hand hand) {
      this.hand = hand;
   }

   public void setMatrixStack(MatrixStack matrixStack) {
      this.matrixStack = matrixStack;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSwingAnimation)) {
         return false;
      } else {
         EventSwingAnimation other = (EventSwingAnimation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getSwingProgress(), other.getSwingProgress()) != 0) {
            return false;
         } else {
            label49: {
               Object this$player = this.getPlayer();
               Object other$player = other.getPlayer();
               if (this$player == null) {
                  if (other$player == null) {
                     break label49;
                  }
               } else if (this$player.equals(other$player)) {
                  break label49;
               }

               return false;
            }

            Object this$hand = this.getHand();
            Object other$hand = other.getHand();
            if (this$hand == null) {
               if (other$hand != null) {
                  return false;
               }
            } else if (!this$hand.equals(other$hand)) {
               return false;
            }

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
      return other instanceof EventSwingAnimation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getSwingProgress());
      Object $player = this.getPlayer();
      result = result * 59 + ($player == null ? 43 : $player.hashCode());
      Object $hand = this.getHand();
      result = result * 59 + ($hand == null ? 43 : $hand.hashCode());
      Object $matrixStack = this.getMatrixStack();
      result = result * 59 + ($matrixStack == null ? 43 : $matrixStack.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getPlayer());
      return "EventSwingAnimation(player=" + var10000 + ", swingProgress=" + this.getSwingProgress() + ", hand=" + String.valueOf(this.getHand()) + ", matrixStack=" + String.valueOf(this.getMatrixStack()) + ")";
   }

   public EventSwingAnimation(AbstractClientPlayerEntity player, float swingProgress, Hand hand, MatrixStack matrixStack) {
      this.player = player;
      this.swingProgress = swingProgress;
      this.hand = hand;
      this.matrixStack = matrixStack;
   }
}
