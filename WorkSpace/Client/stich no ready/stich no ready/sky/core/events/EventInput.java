package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventInput implements Event {
   private float forward;
   private float strafe;
   private boolean forwardKeyDown;
   private boolean backKeyDown;
   private boolean leftKeyDown;
   private boolean rightKeyDown;
   private boolean jump;
   private boolean sneak;
   private double sneakSlowDownMultiplier;

   public float getForward() {
      return this.forward;
   }

   public float getStrafe() {
      return this.strafe;
   }

   public boolean isForwardKeyDown() {
      return this.forwardKeyDown;
   }

   public boolean isBackKeyDown() {
      return this.backKeyDown;
   }

   public boolean isLeftKeyDown() {
      return this.leftKeyDown;
   }

   public boolean isRightKeyDown() {
      return this.rightKeyDown;
   }

   public boolean isJump() {
      return this.jump;
   }

   public boolean isSneak() {
      return this.sneak;
   }

   public double getSneakSlowDownMultiplier() {
      return this.sneakSlowDownMultiplier;
   }

   public void setForward(float forward) {
      this.forward = forward;
   }

   public void setStrafe(float strafe) {
      this.strafe = strafe;
   }

   public void setForwardKeyDown(boolean forwardKeyDown) {
      this.forwardKeyDown = forwardKeyDown;
   }

   public void setBackKeyDown(boolean backKeyDown) {
      this.backKeyDown = backKeyDown;
   }

   public void setLeftKeyDown(boolean leftKeyDown) {
      this.leftKeyDown = leftKeyDown;
   }

   public void setRightKeyDown(boolean rightKeyDown) {
      this.rightKeyDown = rightKeyDown;
   }

   public void setJump(boolean jump) {
      this.jump = jump;
   }

   public void setSneak(boolean sneak) {
      this.sneak = sneak;
   }

   public void setSneakSlowDownMultiplier(double sneakSlowDownMultiplier) {
      this.sneakSlowDownMultiplier = sneakSlowDownMultiplier;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventInput)) {
         return false;
      } else {
         EventInput other = (EventInput)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getForward(), other.getForward()) != 0) {
            return false;
         } else if (Float.compare(this.getStrafe(), other.getStrafe()) != 0) {
            return false;
         } else if (this.isForwardKeyDown() != other.isForwardKeyDown()) {
            return false;
         } else if (this.isBackKeyDown() != other.isBackKeyDown()) {
            return false;
         } else if (this.isLeftKeyDown() != other.isLeftKeyDown()) {
            return false;
         } else if (this.isRightKeyDown() != other.isRightKeyDown()) {
            return false;
         } else if (this.isJump() != other.isJump()) {
            return false;
         } else if (this.isSneak() != other.isSneak()) {
            return false;
         } else {
            return Double.compare(this.getSneakSlowDownMultiplier(), other.getSneakSlowDownMultiplier()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventInput;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getForward());
      result = result * 59 + Float.floatToIntBits(this.getStrafe());
      result = result * 59 + (this.isForwardKeyDown() ? 79 : 97);
      result = result * 59 + (this.isBackKeyDown() ? 79 : 97);
      result = result * 59 + (this.isLeftKeyDown() ? 79 : 97);
      result = result * 59 + (this.isRightKeyDown() ? 79 : 97);
      result = result * 59 + (this.isJump() ? 79 : 97);
      result = result * 59 + (this.isSneak() ? 79 : 97);
      long $sneakSlowDownMultiplier = Double.doubleToLongBits(this.getSneakSlowDownMultiplier());
      result = result * 59 + (int)($sneakSlowDownMultiplier >>> 32 ^ $sneakSlowDownMultiplier);
      return result;
   }

   public String toString() {
      float var10000 = this.getForward();
      return "EventInput(forward=" + var10000 + ", strafe=" + this.getStrafe() + ", forwardKeyDown=" + this.isForwardKeyDown() + ", backKeyDown=" + this.isBackKeyDown() + ", leftKeyDown=" + this.isLeftKeyDown() + ", rightKeyDown=" + this.isRightKeyDown() + ", jump=" + this.isJump() + ", sneak=" + this.isSneak() + ", sneakSlowDownMultiplier=" + this.getSneakSlowDownMultiplier() + ")";
   }

   public EventInput(float forward, float strafe, boolean forwardKeyDown, boolean backKeyDown, boolean leftKeyDown, boolean rightKeyDown, boolean jump, boolean sneak, double sneakSlowDownMultiplier) {
      this.forward = forward;
      this.strafe = strafe;
      this.forwardKeyDown = forwardKeyDown;
      this.backKeyDown = backKeyDown;
      this.leftKeyDown = leftKeyDown;
      this.rightKeyDown = rightKeyDown;
      this.jump = jump;
      this.sneak = sneak;
      this.sneakSlowDownMultiplier = sneakSlowDownMultiplier;
   }
}
