package sky.core.utils.player;

import sky.core.utils.math.TimeUtil;

public class PerfectDelay {
   private long delay = 0L;
   private final TimeUtil time = new TimeUtil();

   public boolean cooldownComplete() {
      return this.cooldownComplete(this.delay);
   }

   public boolean cooldownComplete(long delay) {
      return this.time.finished(delay);
   }

   public void reset(long delay) {
      this.time.reset();
      this.delay = delay;
   }

   public long delay() {
      return this.delay;
   }

   public TimeUtil time() {
      return this.time;
   }
}
