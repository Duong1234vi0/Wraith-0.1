package sky.core.utils.math;

public class TimeUtil {
   private long lastMS;
   private final long ms = System.currentTimeMillis();

   public TimeUtil() {
      this.reset();
   }

   public void reset() {
      this.lastMS = System.currentTimeMillis();
   }

   public long getTimePassed() {
      return System.currentTimeMillis() - this.lastMS;
   }

   public void setTime(long time) {
      this.lastMS = time;
   }

   public boolean isReached(long time) {
      return System.currentTimeMillis() - this.lastMS > time;
   }

   public boolean hasTimeElapsed(long time) {
      return this.getTimePassed() >= time;
   }

   public boolean finished(long delay) {
      return System.currentTimeMillis() - delay >= this.lastMS;
   }

   public long getElapsedTime() {
      return System.currentTimeMillis() - this.lastMS;
   }

   public boolean hasTimeElapsed(long time, boolean reset) {
      boolean hasElapsed = this.getTimePassed() >= time;
      if (hasElapsed && reset) {
         this.reset();
      }

      return hasElapsed;
   }

   public long getTime() {
      return System.currentTimeMillis() - this.lastMS;
   }

   public static void addTask(long delay, Runnable task) {
      (new Thread(() -> {
         try {
            Thread.sleep(delay);
            task.run();
         } catch (InterruptedException var4) {
            var4.printStackTrace();
         }

      })).start();
   }

   public boolean hasTimeElapsed() {
      return this.ms < System.currentTimeMillis();
   }

   public boolean hasReached(double milliseconds) {
      return (double)this.getTimePassed() >= milliseconds;
   }
}
