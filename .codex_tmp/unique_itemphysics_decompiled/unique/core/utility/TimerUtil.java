package unique.core.utility;

public class TimerUtil {
   private long ms = System.currentTimeMillis();

   public boolean hasTimeElapsed(long delay) {
      return System.currentTimeMillis() - this.ms >= delay;
   }

   public void reset() {
      this.ms = System.currentTimeMillis();
   }

   public long getTime() {
      return System.currentTimeMillis() - this.ms;
   }

   public void setTime(long time) {
      this.ms = time;
   }
}
