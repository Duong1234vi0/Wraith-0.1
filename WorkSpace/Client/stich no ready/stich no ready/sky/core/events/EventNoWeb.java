package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.math.BlockPos;

public class EventNoWeb extends EventCancellable implements Event {
   private BlockPos blockPos;

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void setBlockPos(BlockPos blockPos) {
      this.blockPos = blockPos;
   }

   public EventNoWeb(BlockPos blockPos) {
      this.blockPos = blockPos;
   }
}
