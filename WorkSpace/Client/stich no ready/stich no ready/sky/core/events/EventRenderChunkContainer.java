package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;

public class EventRenderChunkContainer extends EventCancellable implements Event {
   private ChunkRenderDispatcher.ChunkRender chunkRender;

   public ChunkRenderDispatcher.ChunkRender getChunkRender() {
      return this.chunkRender;
   }

   public void setChunkRender(ChunkRenderDispatcher.ChunkRender chunkRender) {
      this.chunkRender = chunkRender;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventRenderChunkContainer)) {
         return false;
      } else {
         EventRenderChunkContainer other = (EventRenderChunkContainer)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$chunkRender = this.getChunkRender();
            Object other$chunkRender = other.getChunkRender();
            if (this$chunkRender == null) {
               if (other$chunkRender != null) {
                  return false;
               }
            } else if (!this$chunkRender.equals(other$chunkRender)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventRenderChunkContainer;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $chunkRender = this.getChunkRender();
      result = result * 59 + ($chunkRender == null ? 43 : $chunkRender.hashCode());
      return result;
   }

   public String toString() {
      return "EventRenderChunkContainer(chunkRender=" + String.valueOf(this.getChunkRender()) + ")";
   }

   public EventRenderChunkContainer(ChunkRenderDispatcher.ChunkRender chunkRender) {
      this.chunkRender = chunkRender;
   }
}
