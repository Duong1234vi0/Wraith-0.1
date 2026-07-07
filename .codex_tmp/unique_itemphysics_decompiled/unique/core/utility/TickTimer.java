package unique.core.utility;

import unique.core.core.manager.IManager;

public class TickTimer {
   private int time;

   public TickTimer() {
      this.reset();
   }

   public boolean passedTicks(long t) {
      if (this.getPassedTicks() < 0) {
         this.reset();
      }

      return this.getPassedTicks() >= t;
   }

   public boolean every(long ms) {
      if (this.getPassedTicks() < 0) {
         this.reset();
      }

      boolean passed = this.getPassedTicks() >= ms;
      if (passed) {
         this.reset();
      }

      return passed;
   }

   public void set(int t) {
      this.time = t;
   }

   public void reset() {
      this.time = IManager.mc.field_1724 == null ? 0 : IManager.mc.field_1724.field_6012;
   }

   private int getPassedTicks() {
      return IManager.mc.field_1724 == null ? 0 : IManager.mc.field_1724.field_6012 - this.time;
   }
}
