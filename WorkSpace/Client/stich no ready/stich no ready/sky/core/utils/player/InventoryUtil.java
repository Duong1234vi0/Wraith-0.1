package sky.core.utils.player;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.network.play.client.CClickWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.Hand;
import sky.core.handlers.impl.InventoryHandler;
import sky.core.utils.Wrapper;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ChatUtil;

public class InventoryUtil implements Wrapper {
   private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

   public static int getSlot(Item item) {
      if (mc != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            for(int i = 0; i < 36; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
               if (!stack.isEmpty() && stack.getItem() == item) {
                  return i;
               }
            }

            return -1;
         }
      }

      return -1;
   }

   public static Slot getSlottest(Item item) {
      Minecraft var10000 = mc;
      return (Slot)Minecraft.player.openContainer.inventorySlots.stream().filter((s) -> {
         return s.getStack().getItem().equals(item);
      }).findFirst().orElse((Object)null);
   }

   public static void findItemAndThrow(Item item) {
      Minecraft var10000 = mc;
      if (Minecraft.player.getCooldownTracker().hasCooldown(item)) {
         ChatUtil.addText(item.getName().getString() + " - находиться в кулдауне", "Error");
      } else {
         Slot slot = getSlottest(item);
         if (slot == null) {
            ChatUtil.addText(item.getName().getString() + " - не обнаружен в инвентаре", "Error");
         } else {
            findItemAndThrow(slot);
         }
      }
   }

   public static void findItemAndThrow(Slot slot) {
      swapHand(slot, Hand.MAIN_HAND, true);
      useItem(Hand.MAIN_HAND);
      swapHand(slot, Hand.MAIN_HAND, true);
   }

   public static void swapHand(Slot slot, Hand hand, boolean packet) {
      if (slot != null) {
         swapHand(slot.slotNumber, hand, packet);
      }

   }

   public static void swapHand(int slotId, Hand hand, boolean packet) {
      if (slotId != -1) {
         int var4;
         if (hand.equals(Hand.MAIN_HAND)) {
            Minecraft var10000 = mc;
            var4 = Minecraft.player.inventory.currentItem;
         } else {
            var4 = 40;
         }

         int button = var4;
         clickSlotId(slotId, button, ClickType.SWAP, packet);
      }
   }

   public static void useItem(Hand hand) {
      Minecraft var10000 = mc;
      Minecraft.player.connection.sendPacketWithoutEvent(new CPlayerTryUseItemPacket(hand));
   }

   public static void clickSlotId(int slotId, int buttonId, ClickType clickType, boolean packet) {
      Minecraft var10000 = mc;
      clickSlotId(Minecraft.player.openContainer.windowId, slotId, buttonId, clickType, packet);
   }

   public static void clickSlotId(int windowId, int slotId, int buttonId, ClickType clickType, boolean packet) {
      if (packet) {
         Minecraft var10000 = mc;
         Minecraft var10008 = mc;
         Minecraft var10009 = mc;
         Minecraft.player.connection.sendPacket(new CClickWindowPacket(windowId, slotId, buttonId, clickType, ItemStack.EMPTY, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
      } else {
         Minecraft var10005 = mc;
         mc.playerController.windowClick(windowId, slotId, buttonId, clickType, Minecraft.player);
      }

   }

   public static void clickSlotId(int slotId, int buttonId, ClickType clickType) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         Minecraft var10005 = mc;
         mc.playerController.windowClick(0, slotId, buttonId, clickType, Minecraft.player);
      }
   }

   public static void moveItem(int from, int to) {
      if (from != to && from != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.playerController != null) {
            from = from < 9 ? from + 36 : from;
            if (to == 45) {
               clickSlotId(from, 40, ClickType.SWAP);
            } else {
               clickSlotId(from, 0, ClickType.SWAP);
               clickSlotId(to, 0, ClickType.SWAP);
               clickSlotId(from, 0, ClickType.SWAP);
            }

            return;
         }
      }

   }

   public static void moveItemtest(int from, int to) {
      if (from != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.playerController != null) {
            from = from < 9 ? from + 36 : from;
            if (from == to) {
               return;
            }

            clickSlotId(from, 0, ClickType.SWAP, false);
            clickSlotId(to, 0, ClickType.SWAP, false);
            clickSlotId(from, 0, ClickType.SWAP, false);
            return;
         }
      }

   }

   public static int antipoletToOffhand(Item item) {
      if (getSlot(item) != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            for(int i = 0; i < 36; ++i) {
               var10000 = mc;
               if (Minecraft.player.inventory.getStackInSlot(i).getItem() == item) {
                  int originalSlot = i < 9 ? 36 + i : i;
                  moveItem(originalSlot, 45);
                  (new Timer()).schedule(new TimerTask() {
                     public void run() {
                        if (Wrapper.mc != null && Wrapper.mc.gameSettings != null && !Wrapper.mc.gameSettings.keyBindSneak.isKeyDown()) {
                           Wrapper.mc.gameSettings.keyBindSneak.setPressed(true);
                        }

                     }
                  }, 150L);
                  (new Timer()).schedule(new TimerTask() {
                     public void run() {
                        if (Wrapper.mc != null && Wrapper.mc.gameSettings != null && Wrapper.mc.gameSettings.keyBindSneak.isKeyDown()) {
                           Wrapper.mc.gameSettings.keyBindSneak.setPressed(false);
                        }

                     }
                  }, 200L);
                  return originalSlot;
               }
            }

            return -1;
         }
      }

      return -1;
   }

   public static boolean isOnHotBar(int index) {
      return index >= 36 && index <= 44;
   }

   public static void swapItem(int from, int to) {
      if (from != to) {
         from = from < 9 ? from + 36 : from;
         Minecraft var10001 = mc;
         Minecraft var10005 = mc;
         mc.playerController.windowClick(Minecraft.player.openContainer.windowId, from, 0, ClickType.SWAP, Minecraft.player);
         var10001 = mc;
         var10005 = mc;
         mc.playerController.windowClick(Minecraft.player.openContainer.windowId, to, 0, ClickType.SWAP, Minecraft.player);
         var10001 = mc;
         var10005 = mc;
         mc.playerController.windowClick(Minecraft.player.openContainer.windowId, from, 0, ClickType.SWAP, Minecraft.player);
      }
   }

   public static int findBestSlotInHotBar() {
      int emptySlot = findEmptySlot();
      return emptySlot != -1 ? emptySlot : findNonSwordSlot();
   }

   public static boolean haveHotBar(Item item) {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         Minecraft.player.inventory.getStackInSlot(i);
         var10000 = mc;
         if (Minecraft.player.inventory.getStackInSlot(i).getItem() == item) {
            return true;
         }
      }

      return false;
   }

   public static int find(Item item) {
      int slot = -1;
      Minecraft var10000 = mc;
      Iterator var2 = Minecraft.player.getArmorInventoryList().iterator();

      ItemStack stack;
      while(var2.hasNext()) {
         stack = (ItemStack)var2.next();
         if (stack.getItem() == item) {
            return -2;
         }
      }

      for(int i = 0; i < 36; ++i) {
         var10000 = mc;
         stack = Minecraft.player.inventory.getStackInSlot(i);
         if (stack.getItem() == item) {
            slot = i;
            break;
         }
      }

      if (slot < 9 && slot != -1) {
         slot += 36;
      }

      return slot;
   }

   private static int findNonSwordSlot() {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         if (!(Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof SwordItem)) {
            var10000 = mc;
            if (!(Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof ElytraItem)) {
               var10000 = mc;
               if (Minecraft.player.inventory.currentItem != i) {
                  return i;
               }
            }
         }
      }

      return -1;
   }

   public static void switchItem(Item item, int to, boolean back, int timeDelayBack) {
      if (InventoryHelper.getItemIndex(item) != -1) {
         int formativeSlot = getItemSlot(item);
         Minecraft var10005 = mc;
         mc.playerController.windowClick(0, formativeSlot, to, ClickType.SWAP, Minecraft.player);
         if (back) {
            TimeUtil.addTask((long)timeDelayBack, () -> {
               Minecraft var10005 = mc;
               mc.playerController.windowClick(0, formativeSlot, to, ClickType.SWAP, Minecraft.player);
            });
         }

      }
   }

   public static int getItemSlot(Item input) {
      int slot = -1;

      for(int i = 0; i < 36; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (stack.getItem() == input) {
            slot = i;
            break;
         }
      }

      if (slot < 9 && slot != -1) {
         slot += 36;
      }

      return slot;
   }

   public static void pickItem(int from, int to) {
      if (from != to) {
         from = from < 9 ? from + 36 : from;
         pickupItem(from, 0);
         pickupItem(to, 0);
         pickupItem(from, 0);
      }
   }

   public static int getAxe(boolean hotBar) {
      int startSlot = hotBar ? 0 : 9;
      int endSlot = hotBar ? 9 : 36;

      for(int i = startSlot; i < endSlot; ++i) {
         Minecraft var10000 = mc;
         ItemStack itemStack = Minecraft.player.inventory.getStackInSlot(i);
         if (itemStack.getItem() instanceof AxeItem) {
            return i;
         }
      }

      return -1;
   }

   public static int findAxeSlot() {
      for(int i = 0; i < 45; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() instanceof AxeItem) {
            return i;
         }
      }

      return -1;
   }

   public static void pickupItem(int slot, int button) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         Minecraft var10005 = mc;
         mc.playerController.windowClick(0, slot, button, ClickType.PICKUP, Minecraft.player);
      }
   }

   public static int getChestPlateSlot() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return -1;
      } else {
         int i = 0;

         while(true) {
            Minecraft var10001 = mc;
            if (i >= Minecraft.player.inventory.mainInventory.size()) {
               return -1;
            }

            var10000 = mc;
            ItemStack itemStack = (ItemStack)Minecraft.player.inventory.mainInventory.get(i);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getEquipmentSlot() == EquipmentSlotType.CHEST) {
               return i;
            }

            ++i;
         }
      }
   }

   public static void moveToArmor(int fromSlot, int armorSlot) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
         Minecraft var10005 = mc;
         mc.playerController.windowClick(0, invFrom, 40, ClickType.SWAP, Minecraft.player);
         var10005 = mc;
         mc.playerController.windowClick(0, armorSlot, 40, ClickType.SWAP, Minecraft.player);
         var10005 = mc;
         mc.playerController.windowClick(0, invFrom, 40, ClickType.SWAP, Minecraft.player);
      }
   }

   public static int getItemCount(Item item) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return 0;
      } else {
         int count = 0;
         var10000 = mc;
         Iterator var2 = Minecraft.player.inventory.mainInventory.iterator();

         while(var2.hasNext()) {
            ItemStack stack = (ItemStack)var2.next();
            if (!stack.isEmpty() && stack.getItem().equals(item)) {
               count += stack.getCount();
            }
         }

         return count;
      }
   }

   public static int findBestToolForBlock(BlockState blockState) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return -1;
      } else {
         int bestSlot = -1;
         float bestSpeed = 1.0F;

         for(int i = 0; i < 9; ++i) {
            var10000 = mc;
            ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof ToolItem) {
               var10000 = mc;
               float speed = Minecraft.player.calculateItemDigSpeed(stack, blockState);
               if (speed > bestSpeed) {
                  bestSpeed = speed;
                  bestSlot = i;
               }
            }
         }

         return bestSlot;
      }
   }

   private static int findEmptySlot() {
      if (mc != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            for(int i = 0; i < 36; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
               if (stack.isEmpty()) {
                  return i < 9 ? i + 36 : i;
               }
            }

            return -1;
         }
      }

      return -1;
   }

   public static void inventorySwapClick(Item item) {
      inventorySwapClick(item, true);
   }

   public static void inventorySwapClick(Item item, boolean offhandWhileEating) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         int slot = getSlot(item);
         if (slot != -1) {
            var10000 = mc;
            int currentSlot = Minecraft.player.inventory.currentItem;
            boolean isInHotbar = slot < 9;
            if (isInHotbar && slot == currentSlot) {
               useItemInHand(offhandWhileEating);
            } else if (isInHotbar) {
               swapAndUseItem(slot, currentSlot, offhandWhileEating);
            } else {
               int hotbarSlot = currentSlot % 8 + 1;
               Minecraft var10005 = mc;
               mc.playerController.windowClick(0, slot, hotbarSlot, ClickType.SWAP, Minecraft.player);
               swapAndUseItem(hotbarSlot, currentSlot, offhandWhileEating);
               var10005 = mc;
               mc.playerController.windowClick(0, slot, hotbarSlot, ClickType.SWAP, Minecraft.player);
            }

         }
      }
   }

   private static void useItemInHand(boolean offhandWhileEating) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (offhandWhileEating) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               var10000 = mc;
               if (!Minecraft.player.isBlocking()) {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.OFF_HAND));
                  return;
               }
            }
         }

         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
      }
   }

   private static void swapAndUseItem(int targetSlot, int currentSlot, boolean offhandWhileEating) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (offhandWhileEating) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               var10000 = mc;
               if (!Minecraft.player.isBlocking()) {
                  swapItem(targetSlot, 45);
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.OFF_HAND));
                  swapItem(targetSlot, 45);
                  return;
               }
            }
         }

         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(targetSlot));
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(currentSlot));
      }
   }

   public static int getBestFoodSlot() {
      if (mc != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            int best = -1;
            int max = 0;

            for(int i = 0; i < 45; ++i) {
               var10000 = mc;
               ItemStack s = Minecraft.player.inventory.getStackInSlot(i);
               if (!s.isEmpty() && s.getItem().isFood() && s.getCount() > max) {
                  best = i;
                  max = s.getCount();
               }
            }

            return best;
         }
      }

      return -1;
   }

   public static int findEnchantedTotemSlot() {
      if (mc != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            for(int i = 0; i < 45; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
               if (!stack.isEmpty() && stack.getItem() == Items.TOTEM_OF_UNDYING && !EnchantmentHelper.getEnchantments(stack).isEmpty()) {
                  return i;
               }
            }

            return -1;
         }
      }

      return -1;
   }

   public static boolean stackHasAnyEffect(ItemStack stack, boolean includeRegular, boolean includeSplash, boolean includeLingering, Effect... effects) {
      if (stack != null && !stack.isEmpty()) {
         Item item = stack.getItem();
         boolean typeOk = includeRegular && item == Items.POTION || includeSplash && item == Items.SPLASH_POTION || includeLingering && item == Items.LINGERING_POTION;
         if (!typeOk) {
            return false;
         } else {
            Iterator var7 = PotionUtils.getEffectsFromStack(stack).iterator();

            while(var7.hasNext()) {
               EffectInstance instance = (EffectInstance)var7.next();
               Effect[] var9 = effects;
               int var10 = effects.length;

               for(int var11 = 0; var11 < var10; ++var11) {
                  Effect effect = var9[var11];
                  if (instance.getPotion() == effect) {
                     return true;
                  }
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public static int findPotionSlotWithEffects(boolean preferHotbar, boolean includeRegular, boolean includeSplash, boolean includeLingering, Effect... effects) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return -1;
      } else {
         int i;
         ItemStack s;
         if (preferHotbar) {
            for(i = 0; i < 9; ++i) {
               var10000 = mc;
               s = Minecraft.player.inventory.getStackInSlot(i);
               if (stackHasAnyEffect(s, includeRegular, includeSplash, includeLingering, effects)) {
                  return i;
               }
            }
         } else {
            for(i = 0; i < 9; ++i) {
               var10000 = mc;
               s = Minecraft.player.inventory.getStackInSlot(i);
               if (stackHasAnyEffect(s, includeRegular, includeSplash, includeLingering, effects)) {
                  return i;
               }
            }

            for(i = 9; i < 36; ++i) {
               var10000 = mc;
               s = Minecraft.player.inventory.getStackInSlot(i);
               if (stackHasAnyEffect(s, includeRegular, includeSplash, includeLingering, effects)) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   public static int findEmptyInventorySlot() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return -1;
      } else {
         for(int i = 0; i < 36; ++i) {
            var10000 = mc;
            ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
               return i < 9 ? i + 36 : i;
            }
         }

         return -1;
      }
   }

   public static void useItemLegit(Object object, Item item) {
      int slot = getSlot(item);
      if (slot != -1) {
         if (slot < 9) {
            InventoryHandler.startWithLegit(object, slot);
         } else {
            InventoryHandler.startFromInventory(object, slot);
         }

      }
   }
}
