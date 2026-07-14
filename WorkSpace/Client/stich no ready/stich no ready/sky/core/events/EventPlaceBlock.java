package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class EventPlaceBlock extends EventCancellable implements Event {
   private final Block block;
   private final BlockPos pos;

   public EventPlaceBlock(Block block, BlockPos pos) {
      this.block = block;
      this.pos = pos;
   }

   public Block getBlock() {
      return this.block;
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventPlaceBlock)) {
         return false;
      } else {
         EventPlaceBlock other = (EventPlaceBlock)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$block = this.getBlock();
            Object other$block = other.getBlock();
            if (this$block == null) {
               if (other$block != null) {
                  return false;
               }
            } else if (!this$block.equals(other$block)) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventPlaceBlock;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $block = this.getBlock();
      result = result * 59 + ($block == null ? 43 : $block.hashCode());
      Object $pos = this.getPos();
      result = result * 59 + ($pos == null ? 43 : $pos.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getBlock());
      return "EventPlaceBlock(block=" + var10000 + ", pos=" + String.valueOf(this.getPos()) + ")";
   }
}
