package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventAspectRatio extends EventCancellable implements Event {
   private float aspectRatio;

   public EventAspectRatio(float aspectRatio) {
      this.aspectRatio = aspectRatio;
   }

   public float getAspectRatio() {
      return this.aspectRatio;
   }

   public void setAspectRatio(float aspectRatio) {
      this.aspectRatio = aspectRatio;
   }
}
