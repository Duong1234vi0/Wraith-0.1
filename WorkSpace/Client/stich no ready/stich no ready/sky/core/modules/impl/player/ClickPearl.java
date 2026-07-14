package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventKey;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.InventoryUtil;

public class ClickPearl extends Module {
   public BindSetting clickKey = new BindSetting("Кнопка");
   public BooleanSetting legit = new BooleanSetting("Легитный", false);
   private long delay;

   public ClickPearl() {
      super("ClickPearl", "Позволяет мгновенно бросать эндер-жемчуг, нажатием на определенную кнопку", Category.Player);
      this.clickKey.set(-98);
      this.addSettings(new Setting[]{this.clickKey, this.legit});
   }

   @EventTarget
   private void onKey(EventKey e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (e.getKey() == (Integer)this.clickKey.get()) {
            this.handleClickPearl();
         }

      }
   }

   private void handleClickPearl() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.getCooldownTracker().hasCooldown(Items.ENDER_PEARL) && InventoryUtil.getItemSlot(Items.ENDER_PEARL) != -1) {
         if ((Boolean)this.legit.get()) {
            InventoryUtil.inventorySwapClick(Items.ENDER_PEARL, false);
         } else {
            if (!ServerUtil.isConnectedToServer("funtime") && !ServerUtil.isConnectedToServer("spooky")) {
               InventoryUtil.inventorySwapClick(Items.ENDER_PEARL, false);
               this.useItem(Hand.MAIN_HAND);
            } else {
               int hbSlot = this.findItem(Items.ENDER_PEARL, true);
               int invSlot = this.findItem(Items.ENDER_PEARL, false);
               int slot = this.findAndThrowItem(hbSlot, invSlot);
               if (slot > 8) {
                  mc.playerController.pickItem(slot);
               }

               this.useItem(Hand.MAIN_HAND);
            }

         }
      }
   }

   private int findItem(Item item, boolean hotbarOnly) {
      for(int i = hotbarOnly ? 0 : 9; i < (hotbarOnly ? 9 : 36); ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() == item) {
            return i;
         }
      }

      return -1;
   }

   private int findAndThrowItem(int hbSlot, int invSlot) {
      Minecraft var10000;
      if (hbSlot != -1) {
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hbSlot));
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
         var10000 = mc;
         Minecraft var10003 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
         this.delay = System.currentTimeMillis();
         return hbSlot;
      } else if (invSlot != -1) {
         mc.playerController.pickItem(invSlot);
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
         this.delay = System.currentTimeMillis();
         return invSlot;
      } else {
         return -1;
      }
   }

   private void useItem(Hand hand) {
      Minecraft var10000 = mc;
      Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(hand));
   }
}
