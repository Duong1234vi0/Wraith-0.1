package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventTotemParticle extends EventCancellable implements Event {
   private double x;
   private double y;
   private double z;
   private double xSpeed;
   private double ySpeed;
   private double zSpeed;

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public double getXSpeed() {
      return this.xSpeed;
   }

   public double getYSpeed() {
      return this.ySpeed;
   }

   public double getZSpeed() {
      return this.zSpeed;
   }

   public EventTotemParticle(double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.xSpeed = xSpeed;
      this.ySpeed = ySpeed;
      this.zSpeed = zSpeed;
   }
}
