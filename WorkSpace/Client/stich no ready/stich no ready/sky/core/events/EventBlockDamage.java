package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class EventBlockDamage extends EventCancellable implements Event {
   private BlockState blockState;
   private BlockPos pos;
   private State state;
   private Direction direction;

   public BlockState getBlockState() {
      return this.blockState;
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public State getState() {
      return this.state;
   }

   public Direction getDirection() {
      return this.direction;
   }

   public void setBlockState(BlockState blockState) {
      this.blockState = blockState;
   }

   public void setPos(BlockPos pos) {
      this.pos = pos;
   }

   public void setState(State state) {
      this.state = state;
   }

   public void setDirection(Direction direction) {
      this.direction = direction;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventBlockDamage)) {
         return false;
      } else {
         EventBlockDamage other = (EventBlockDamage)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label59: {
               Object this$blockState = this.getBlockState();
               Object other$blockState = other.getBlockState();
               if (this$blockState == null) {
                  if (other$blockState == null) {
                     break label59;
                  }
               } else if (this$blockState.equals(other$blockState)) {
                  break label59;
               }

               return false;
            }

            Object this$pos = this.getPos();
            Object other$pos = other.getPos();
            if (this$pos == null) {
               if (other$pos != null) {
                  return false;
               }
            } else if (!this$pos.equals(other$pos)) {
               return false;
            }

            Object this$state = this.getState();
            Object other$state = other.getState();
            if (this$state == null) {
               if (other$state != null) {
                  return false;
               }
            } else if (!this$state.equals(other$state)) {
               return false;
            }

            Object this$direction = this.getDirection();
            Object other$direction = other.getDirection();
            if (this$direction == null) {
               if (other$direction != null) {
                  return false;
               }
            } else if (!this$direction.equals(other$direction)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventBlockDamage;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $blockState = this.getBlockState();
      result = result * 59 + ($blockState == null ? 43 : $blockState.hashCode());
      Object $pos = this.getPos();
      result = result * 59 + ($pos == null ? 43 : $pos.hashCode());
      Object $state = this.getState();
      result = result * 59 + ($state == null ? 43 : $state.hashCode());
      Object $direction = this.getDirection();
      result = result * 59 + ($direction == null ? 43 : $direction.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getBlockState());
      return "EventBlockDamage(blockState=" + var10000 + ", pos=" + String.valueOf(this.getPos()) + ", state=" + String.valueOf(this.getState()) + ", direction=" + String.valueOf(this.getDirection()) + ")";
   }

   public EventBlockDamage(BlockState blockState, BlockPos pos, State state, Direction direction) {
      this.blockState = blockState;
      this.pos = pos;
      this.state = state;
      this.direction = direction;
   }

   public static enum State {
      START,
      STOP;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{START, STOP};
      }
   }
}
