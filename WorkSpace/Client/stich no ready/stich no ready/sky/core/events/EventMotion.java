package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventMotion extends EventCancellable implements Event {
   private double x;
   private double y;
   private double z;
   private float yaw;
   private float pitch;
   private boolean onGround;
   private boolean isSneaking;
   private boolean isSprinting;

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public boolean isSneaking() {
      return this.isSneaking;
   }

   public boolean isSprinting() {
      return this.isSprinting;
   }

   public void setX(double x) {
      this.x = x;
   }

   public void setY(double y) {
      this.y = y;
   }

   public void setZ(double z) {
      this.z = z;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public void setOnGround(boolean onGround) {
      this.onGround = onGround;
   }

   public void setSneaking(boolean isSneaking) {
      this.isSneaking = isSneaking;
   }

   public void setSprinting(boolean isSprinting) {
      this.isSprinting = isSprinting;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventMotion)) {
         return false;
      } else {
         EventMotion other = (EventMotion)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Double.compare(this.getX(), other.getX()) != 0) {
            return false;
         } else if (Double.compare(this.getY(), other.getY()) != 0) {
            return false;
         } else if (Double.compare(this.getZ(), other.getZ()) != 0) {
            return false;
         } else if (Float.compare(this.getYaw(), other.getYaw()) != 0) {
            return false;
         } else if (Float.compare(this.getPitch(), other.getPitch()) != 0) {
            return false;
         } else if (this.isOnGround() != other.isOnGround()) {
            return false;
         } else if (this.isSneaking() != other.isSneaking()) {
            return false;
         } else {
            return this.isSprinting() == other.isSprinting();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventMotion;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      long $x = Double.doubleToLongBits(this.getX());
      result = result * 59 + (int)($x >>> 32 ^ $x);
      long $y = Double.doubleToLongBits(this.getY());
      result = result * 59 + (int)($y >>> 32 ^ $y);
      long $z = Double.doubleToLongBits(this.getZ());
      result = result * 59 + (int)($z >>> 32 ^ $z);
      result = result * 59 + Float.floatToIntBits(this.getYaw());
      result = result * 59 + Float.floatToIntBits(this.getPitch());
      result = result * 59 + (this.isOnGround() ? 79 : 97);
      result = result * 59 + (this.isSneaking() ? 79 : 97);
      result = result * 59 + (this.isSprinting() ? 79 : 97);
      return result;
   }

   public String toString() {
      double var10000 = this.getX();
      return "EventMotion(x=" + var10000 + ", y=" + this.getY() + ", z=" + this.getZ() + ", yaw=" + this.getYaw() + ", pitch=" + this.getPitch() + ", onGround=" + this.isOnGround() + ", isSneaking=" + this.isSneaking() + ", isSprinting=" + this.isSprinting() + ")";
   }

   public EventMotion(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean isSneaking, boolean isSprinting) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.yaw = yaw;
      this.pitch = pitch;
      this.onGround = onGround;
      this.isSneaking = isSneaking;
      this.isSprinting = isSprinting;
   }
}
