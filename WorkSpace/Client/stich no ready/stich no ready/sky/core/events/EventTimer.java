package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventTimer extends EventCancellable implements Event {
   private final long gameTime;

   public long getGameTime() {
      return this.gameTime;
   }

   public EventTimer(long gameTime) {
      this.gameTime = gameTime;
   }
}
