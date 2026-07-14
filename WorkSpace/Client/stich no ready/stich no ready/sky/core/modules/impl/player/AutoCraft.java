package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.util.text.StringTextComponent;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;

public class AutoCraft extends Module {
   private final ModeSetting craftMode = new ModeSetting("Что крафтить", "Бумага", new String[]{"Бумага", "Фейерверк 1", "Фейерверк 2", "Фейерверк 3"});
   private State state;
   private int delay;
   private int timeout;
   private int startCount;

   public AutoCraft() {
      super("AutoCraft", "Автоматически крафтит бумагу и фейерверки через /craft", Category.Player);
      this.state = AutoCraft.State.OPENING;
      this.delay = 0;
      this.timeout = 120;
      this.startCount = 0;
      this.addSettings(new Setting[]{this.craftMode});
   }

   public void onEnable() {
      super.onEnable();
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         this.toggle();
      } else {
         this.state = AutoCraft.State.OPENING;
         this.delay = 0;
         this.timeout = 120;
         this.startCount = this.countItem(this.getCraftItem());
         if (!this.hasResources()) {
            this.sendMessage("§cНе хватает ресурсов для крафта!");
            this.toggle();
         } else {
            var10000 = mc;
            Minecraft.player.sendChatMessage("/craft");
         }
      }
   }

   public void onDisable() {
      super.onDisable();
   }

   private void sendMessage(String msg) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         ClientPlayerEntity var2 = Minecraft.player;
         StringTextComponent var10001 = new StringTextComponent(msg);
         Minecraft var10002 = mc;
         var2.sendMessage(var10001, Minecraft.player.getUniqueID());
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         if (this.delay > 0) {
            --this.delay;
         } else {
            switch (this.state) {
               case OPENING:
                  if (this.isCraftingTableOpen()) {
                     this.state = AutoCraft.State.CHECK;
                  } else {
                     --this.timeout;
                     if (this.timeout <= 0) {
                        this.finishCrafting();
                     }
                  }
                  break;
               case CHECK:
                  if (!this.isCraftingTableOpen()) {
                     this.finishCrafting();
                     return;
                  }

                  if (!this.hasResources()) {
                     this.state = AutoCraft.State.CLEANUP;
                  } else {
                     this.state = AutoCraft.State.FILL;
                  }
                  break;
               case FILL:
                  if (!this.isCraftingTableOpen()) {
                     this.finishCrafting();
                     return;
                  }

                  if (this.fillSlots()) {
                     this.delay = 3;
                     this.state = AutoCraft.State.TAKE;
                  } else {
                     this.state = AutoCraft.State.CLEANUP;
                  }
                  break;
               case TAKE:
                  if (!this.isCraftingTableOpen()) {
                     this.finishCrafting();
                     return;
                  }

                  this.takeResult();
                  this.delay = 3;
                  this.state = AutoCraft.State.CHECK;
                  break;
               case CLEANUP:
                  this.cleanupSlots();
                  this.delay = 2;
                  this.state = AutoCraft.State.CLOSE;
                  break;
               case CLOSE:
                  this.finishCrafting();
            }

         }
      } else {
         this.toggle();
      }
   }

   private void finishCrafting() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && this.isCraftingTableOpen()) {
         var10000 = mc;
         Minecraft var10003 = mc;
         Minecraft.player.connection.sendPacket(new CCloseWindowPacket(Minecraft.player.openContainer.windowId));
         var10000 = mc;
         Minecraft.player.closeScreen();
      }

      int crafted = this.countItem(this.getCraftItem()) - this.startCount;
      if (crafted > 0) {
         this.sendMessage("§aУспешно скрафчено: §f" + crafted + " §aшт.");
      } else {
         this.sendMessage("§cНе хватает ресурсов для крафта!");
      }

      this.toggle();
   }

   private Item getCraftItem() {
      return this.craftMode.is("Бумага") ? Items.PAPER : Items.FIREWORK_ROCKET;
   }

   private int getFireworkLevel() {
      if (this.craftMode.is("Фейерверк 1")) {
         return 1;
      } else if (this.craftMode.is("Фейерверк 2")) {
         return 2;
      } else {
         return this.craftMode.is("Фейерверк 3") ? 3 : 1;
      }
   }

   private boolean hasResources() {
      if (this.craftMode.is("Бумага")) {
         return this.countItem(Items.SUGAR_CANE) >= 3;
      } else {
         int level = this.getFireworkLevel();
         return this.countItem(Items.PAPER) >= 1 && this.countItem(Items.GUNPOWDER) >= level;
      }
   }

   private boolean fillSlots() {
      boolean hasPaper;
      boolean hasGunpowder;
      if (this.craftMode.is("Бумага")) {
         boolean has1 = this.placeItemInSlot(Items.SUGAR_CANE, 1);
         hasPaper = this.placeItemInSlot(Items.SUGAR_CANE, 2);
         hasGunpowder = this.placeItemInSlot(Items.SUGAR_CANE, 3);
         return has1 && hasPaper && hasGunpowder;
      } else {
         int level = this.getFireworkLevel();
         hasPaper = this.placeItemInSlot(Items.PAPER, 1);
         hasGunpowder = true;

         for(int i = 0; i < level; ++i) {
            if (!this.placeItemInSlot(Items.GUNPOWDER, 2 + i)) {
               hasGunpowder = false;
               break;
            }
         }

         return hasPaper && hasGunpowder;
      }
   }

   private boolean placeItemInSlot(Item item, int slot) {
      Minecraft var10000 = mc;
      Container container = Minecraft.player.openContainer;
      if (container == null) {
         return false;
      } else {
         ItemStack slotStack = container.getSlot(slot).getStack();
         if (!slotStack.isEmpty() && slotStack.getItem() == item) {
            return true;
         } else {
            int itemSlot = this.findItemInInventory(item, slot);
            if (itemSlot == -1) {
               return false;
            } else {
               int windowId = container.windowId;
               if (mc.playerController != null) {
                  Minecraft var10005 = mc;
                  mc.playerController.windowClick(windowId, itemSlot, 0, ClickType.PICKUP, Minecraft.player);
                  var10005 = mc;
                  mc.playerController.windowClick(windowId, slot, 0, ClickType.PICKUP, Minecraft.player);
                  var10000 = mc;
                  if (!Minecraft.player.inventory.getItemStack().isEmpty()) {
                     var10005 = mc;
                     mc.playerController.windowClick(windowId, itemSlot, 0, ClickType.PICKUP, Minecraft.player);
                  }
               }

               return true;
            }
         }
      }
   }

   private int findItemInInventory(Item item, int excludedSlot) {
      Minecraft var10000 = mc;
      Container container = Minecraft.player.openContainer;
      if (container == null) {
         return -1;
      } else {
         for(int i = 10; i < container.inventorySlots.size(); ++i) {
            if (i != excludedSlot) {
               ItemStack stack = container.getSlot(i).getStack();
               if (!stack.isEmpty() && stack.getItem() == item) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   private void takeResult() {
      Minecraft var10000 = mc;
      Container container = Minecraft.player.openContainer;
      if (container != null) {
         ItemStack result = container.getSlot(0).getStack();
         if (!result.isEmpty()) {
            if (mc.playerController != null) {
               Minecraft var10005 = mc;
               mc.playerController.windowClick(container.windowId, 0, 0, ClickType.QUICK_MOVE, Minecraft.player);
            }

         }
      }
   }

   private void cleanupSlots() {
      Minecraft var10000 = mc;
      Container container = Minecraft.player.openContainer;
      if (container != null && this.isCraftingTableOpen()) {
         for(int i = 1; i <= 9; ++i) {
            ItemStack stack = container.getSlot(i).getStack();
            if (!stack.isEmpty() && mc.playerController != null) {
               Minecraft var10005 = mc;
               mc.playerController.windowClick(container.windowId, i, 0, ClickType.QUICK_MOVE, Minecraft.player);
            }
         }

      }
   }

   private boolean isCraftingTableOpen() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         return Minecraft.player.openContainer instanceof WorkbenchContainer;
      }
   }

   private int countItem(Item item) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return 0;
      } else {
         int count = 0;
         var10000 = mc;
         Iterator var3 = Minecraft.player.inventory.mainInventory.iterator();

         while(var3.hasNext()) {
            ItemStack stack = (ItemStack)var3.next();
            if (!stack.isEmpty() && stack.getItem() == item) {
               count += stack.getCount();
            }
         }

         return count;
      }
   }

   private static enum State {
      OPENING,
      CHECK,
      FILL,
      TAKE,
      CLEANUP,
      CLOSE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{OPENING, CHECK, FILL, TAKE, CLEANUP, CLOSE};
      }
   }
}
