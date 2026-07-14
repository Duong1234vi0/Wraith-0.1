package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventGameUpdate implements Event {
   int smoothIntervalMS;

   public int getSmoothIntervalMS() {
      return this.smoothIntervalMS;
   }

   public void setSmoothIntervalMS(int smoothIntervalMS) {
      this.smoothIntervalMS = smoothIntervalMS;
   }
}
