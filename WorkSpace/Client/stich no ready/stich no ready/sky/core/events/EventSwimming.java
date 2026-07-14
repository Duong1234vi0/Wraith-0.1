package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventSwimming implements Event {
   private float pitch;
   private float yaw;

   public float getPitch() {
      return this.pitch;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public EventSwimming(float pitch, float yaw) {
      this.pitch = pitch;
      this.yaw = yaw;
   }
}
