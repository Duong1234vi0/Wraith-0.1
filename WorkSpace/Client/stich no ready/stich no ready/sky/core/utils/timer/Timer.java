package sky.core.utils.timer;

import net.minecraft.client.Minecraft;

public class Timer {
   private long startTime = System.currentTimeMillis();
   private long millis;

   public Timer() {
      this.reset();
   }

   public static Timer create() {
      return new Timer();
   }

   public boolean finished(long delay) {
      return System.currentTimeMillis() - delay >= this.millis;
   }

   public void reset() {
      this.millis = System.currentTimeMillis();
   }

   public long getElapsedTime() {
      return System.currentTimeMillis() - this.millis;
   }

   public double deltaTime() {
      int fps = Minecraft.debugFPS;
      return fps > 0 ? 1.0 / (double)fps : 1.0;
   }

   public boolean every(long ms) {
      boolean passed = this.getMillis(System.nanoTime() - this.millis) >= ms;
      if (passed) {
         this.reset();
      }

      return passed;
   }

   public boolean passed(long time) {
      return System.currentTimeMillis() - this.startTime > time;
   }

   public long getMillis(long time) {
      return time / 1000000L;
   }

   public boolean hasReached(int i) {
      return false;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public long getMillis() {
      return this.millis;
   }

   public void setStartTime(long startTime) {
      this.startTime = startTime;
   }

   public void setMillis(long millis) {
      this.millis = millis;
   }
}
