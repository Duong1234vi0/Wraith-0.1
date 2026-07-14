package sky.core.utils.misc;

import java.util.ArrayList;
import java.util.List;

public class FrameRateUtil {
   private static final List records = new ArrayList();
   private static int fps = 5;

   public static void recordFrame() {
      long c = System.currentTimeMillis();
      records.add(c);
      records.removeIf((aLong) -> {
         return aLong + 1000L < System.currentTimeMillis();
      });
      fps = Math.max(records.size(), 4);
   }

   public static int getFps() {
      return fps;
   }
}
