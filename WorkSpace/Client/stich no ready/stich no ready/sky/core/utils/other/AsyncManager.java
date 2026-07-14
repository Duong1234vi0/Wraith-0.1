package sky.core.utils.other;

public final class AsyncManager {
   public static Thread run(Runnable runnable) {
      Thread thread = new Thread(() -> {
         runnable.run();
      });
      thread.start();
      return thread;
   }

   private AsyncManager() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
