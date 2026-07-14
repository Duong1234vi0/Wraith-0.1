package sky.core.utils;

public class StopWatch {
   private long startTime;

   public StopWatch() {
      this.reset();
   }

   public boolean finished(double delay) {
      return (double)System.currentTimeMillis() - delay >= (double)this.startTime;
   }

   public boolean every(double delay) {
      boolean finished = this.finished(delay);
      if (finished) {
         this.reset();
      }

      return finished;
   }

   public void reset() {
      this.startTime = System.currentTimeMillis();
   }

   public long elapsedTime() {
      return System.currentTimeMillis() - this.startTime;
   }

   public void setMs(long ms) {
      this.startTime = System.currentTimeMillis() - ms;
   }

   public long getStartTime() {
      return this.startTime;
   }
}
