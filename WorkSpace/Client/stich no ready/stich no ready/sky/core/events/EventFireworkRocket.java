package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventFireworkRocket extends EventCancellable implements Event {
   private float speed;
   private float ySpeed;

   public float getSpeed() {
      return this.speed;
   }

   public float getYSpeed() {
      return this.ySpeed;
   }

   public void setSpeed(float speed) {
      this.speed = speed;
   }

   public void setYSpeed(float ySpeed) {
      this.ySpeed = ySpeed;
   }

   public EventFireworkRocket(float speed, float ySpeed) {
      this.speed = speed;
      this.ySpeed = ySpeed;
   }
}
