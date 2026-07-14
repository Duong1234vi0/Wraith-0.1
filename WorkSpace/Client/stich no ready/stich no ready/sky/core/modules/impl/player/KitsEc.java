package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class KitsEc extends Module {
   private final String[] kits = new String[]{"universal", "delta", "sponsor", "cobra", "god", "slime", "fame", "snowman"};
   private int kitIndex = 0;
   private int delay = 0;
   private int state = 0;
   private static final int EC_SLOT = 21;
   private static final long PAUSE_TIME = 60000L;
   private long pauseEndTime = 0L;

   public KitsEc() {
      super("KitsEc", "Автофарм китов без задержки между китами, 1 мин пауза после цикла", Category.Player);
   }

   public void onEnable() {
      super.onEnable();
      this.kitIndex = 0;
      this.delay = 0;
      this.state = 0;
      this.pauseEndTime = 0L;
   }

   public void onDisable() {
      super.onDisable();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         Minecraft.player.closeScreen();
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (this.delay > 0) {
            --this.delay;
         } else {
            long currentTime = System.currentTimeMillis();
            if (this.pauseEndTime > 0L) {
               if (currentTime < this.pauseEndTime) {
                  return;
               }

               this.kitIndex = 0;
               this.pauseEndTime = 0L;
            }

            switch (this.state) {
               case 0:
                  this.requestKit();
                  break;
               case 1:
                  this.openEnderChest();
                  break;
               case 2:
                  this.takeItemFromEnderChest();
                  break;
               case 3:
                  this.closeAndCheckInventory();
            }

         }
      }
   }

   private void requestKit() {
      if (this.kitIndex >= this.kits.length) {
         this.pauseEndTime = System.currentTimeMillis() + 60000L;
         this.kitIndex = 0;
         this.state = 0;
         this.delay = 2;
      } else {
         Minecraft var10000 = mc;
         String var10001 = this.kits[this.kitIndex];
         Minecraft.player.sendChatMessage("/kit " + var10001);
         ++this.kitIndex;
         this.state = 1;
         this.delay = 3;
      }
   }

   private void openEnderChest() {
      if (this.hasItems()) {
         Minecraft var10000 = mc;
         Minecraft.player.sendChatMessage("/ec");
         this.delay = 3;
         this.state = 2;
      } else {
         this.state = 0;
         this.delay = 2;
      }

   }

   private void takeItemFromEnderChest() {
      if (mc.currentScreen != null) {
         Minecraft var10001 = mc;
         Minecraft var10005 = mc;
         mc.playerController.windowClick(Minecraft.player.openContainer.windowId, 21, 0, ClickType.PICKUP, Minecraft.player);
         this.delay = 2;
         this.state = 3;
      } else {
         this.delay = 2;
      }

   }

   private void closeAndCheckInventory() {
      if (mc.currentScreen == null) {
         if (this.hasItems()) {
            this.state = 1;
            this.delay = 3;
         } else {
            this.state = 0;
            this.delay = 2;
         }

      } else {
         Minecraft var10000 = mc;
         if (Minecraft.player.openContainer.inventorySlots.size() < 90) {
            this.delay = 2;
         } else {
            int slot = this.findItemSlot();
            if (slot == -1) {
               var10000 = mc;
               Minecraft.player.closeScreen();
               var10000 = mc;
               Minecraft.player.sendChatMessage("/ci");
               this.delay = 5;
            } else {
               Minecraft var10001 = mc;
               Minecraft var10005 = mc;
               mc.playerController.windowClick(Minecraft.player.openContainer.windowId, slot, 0, ClickType.QUICK_MOVE, Minecraft.player);
               this.delay = 1;
            }
         }
      }
   }

   private int findItemSlot() {
      int i = 54;

      while(true) {
         Minecraft var10001 = mc;
         if (i >= Minecraft.player.openContainer.inventorySlots.size()) {
            return -1;
         }

         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.openContainer.getSlot(i).getStack();
         if (stack != null && !stack.isEmpty() && this.isValidItem(stack)) {
            return i;
         }

         ++i;
      }
   }

   private boolean hasItems() {
      for(int i = 0; i < 36; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (this.isValidItem(stack)) {
            return true;
         }
      }

      return false;
   }

   private boolean isValidItem(ItemStack stack) {
      if (stack != null && !stack.isEmpty()) {
         String name = stack.getDisplayName().getString().toLowerCase();
         return name.contains("викинг") || name.contains("клирик") || name.contains("ливалка") || stack.getItem() == Items.FEATHER;
      } else {
         return false;
      }
   }
}
