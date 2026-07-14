package sky.core.ui.gui.autobuy;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;

public class PurchaseHistory {
   private static final List history = new ArrayList();

   public static void add(ItemStack stack, long price, String seller) {
      if (stack != null && !stack.isEmpty()) {
         ItemStack stackCopy = stack.copy();
         history.add(0, new Log(stackCopy, price, seller, System.currentTimeMillis()));
         if (history.size() > 100) {
            history.remove(history.size() - 1);
         }

      }
   }

   public static List getHistory() {
      return history;
   }

   public static void clear() {
      history.clear();
   }

   public static int size() {
      return history.size();
   }

   public static class Log {
      public final ItemStack stack;
      public final long price;
      public final String seller;
      public final long time;

      public Log(ItemStack stack, long price, String seller, long time) {
         this.stack = stack.copy();
         this.price = price;
         this.seller = seller;
         this.time = time;
      }
   }
}
