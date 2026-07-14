package sky.core.utils.animations.advanced;

import sky.core.utils.timer.Timer;

public class Animation {
   private final Timer timer = new Timer();
   private int speed;
   private double size = 1.0;
   private boolean forward;
   private Easing easing;

   public boolean finished(boolean forward) {
      boolean var10000;
      label25: {
         if (this.timer.finished((long)this.speed)) {
            if (forward) {
               if (this.forward) {
                  break label25;
               }
            } else if (!this.forward) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean finished() {
      return this.timer.finished((long)this.speed) && this.forward;
   }

   public Animation setForward(boolean forward) {
      if (this.forward != forward) {
         this.forward = forward;
         this.timer.setMillis((long)((double)System.currentTimeMillis() - (this.size - Math.min(this.size, (double)this.timer.getElapsedTime()))));
      }

      return this;
   }

   public Animation finish() {
      this.timer.setMillis(System.currentTimeMillis() - (long)this.speed);
      return this;
   }

   public Animation setEasing(Easing easing) {
      this.easing = easing;
      return this;
   }

   public Animation setSpeed(int speed) {
      this.speed = speed;
      return this;
   }

   public Animation setSize(float size) {
      this.size = (double)size;
      return this;
   }

   public float getLinear() {
      if (this.forward) {
         return this.timer.finished((long)this.speed) ? (float)this.size : (float)((double)this.timer.getElapsedTime() / (double)this.speed * this.size);
      } else {
         return this.timer.finished((long)this.speed) ? 0.0F : (float)((1.0 - (double)this.timer.getElapsedTime() / (double)this.speed) * this.size);
      }
   }

   public float get() {
      if (this.forward) {
         return this.timer.finished((long)this.speed) ? (float)this.size : (float)(this.easing.apply((double)this.timer.getElapsedTime() / (double)this.speed) * this.size);
      } else {
         return this.timer.finished((long)this.speed) ? 0.0F : (float)((1.0 - this.easing.apply((double)this.timer.getElapsedTime() / (double)this.speed)) * this.size);
      }
   }

   public float reversed() {
      return 1.0F - this.get();
   }

   public void reset() {
      this.timer.reset();
   }

   public Timer getTimer() {
      return this.timer;
   }

   public int getSpeed() {
      return this.speed;
   }

   public double getSize() {
      return this.size;
   }

   public boolean isForward() {
      return this.forward;
   }

   public Easing getEasing() {
      return this.easing;
   }
}
