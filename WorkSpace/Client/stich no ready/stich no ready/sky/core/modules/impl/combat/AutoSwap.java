package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import sky.core.events.EventKey;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.InventoryUtil;

public class AutoSwap extends Module {
   private final BooleanSetting handMode = new BooleanSetting("Рука", true);
   private final BooleanSetting headMode = new BooleanSetting("Голова", false);
   private final BindSetting swapKey;
   private final ModeSetting itemType;
   private final ModeSetting swapType;
   private final BindSetting swapKeyHead;
   private final ModeSetting itemTypeHead;
   private final ModeSetting swapTypeHead;
   private final BooleanSetting totemSwap;
   private final BooleanSetting notif;
   private final TimeUtil stopWatch;
   private final TimeUtil attackSpeedTimer;
   private boolean hadAttackSpeedRecently;
   private int storedAxeSlot;
   private ItemStack lastOffhandStack;
   private boolean lastAttackSpeedResult;
   private boolean isSwapping;

   public AutoSwap() {
      super("AutoSwap", "У этого модуля нет описания!", Category.Combat);
      BooleanSetting var10004 = this.handMode;
      Objects.requireNonNull(var10004);
      this.swapKey = new BindSetting("Свап (рука)", var10004::get);
      BooleanSetting var10005 = this.handMode;
      Objects.requireNonNull(var10005);
      this.itemType = new ModeSetting("Свапать с", "Щит", var10005::get, new String[]{"Щит", "Геплы", "Тотем", "Шар "});
      var10005 = this.handMode;
      Objects.requireNonNull(var10005);
      this.swapType = new ModeSetting("Свапать на", "Геплы", var10005::get, new String[]{"Геплы", "Щит", "Тотем", "Шар "});
      var10004 = this.headMode;
      Objects.requireNonNull(var10004);
      this.swapKeyHead = new BindSetting("Свап (голова)", var10004::get);
      var10005 = this.headMode;
      Objects.requireNonNull(var10005);
      this.itemTypeHead = new ModeSetting("Свапать c", "Шар ", var10005::get, new String[]{"Шар ", "Шлем"});
      var10005 = this.headMode;
      Objects.requireNonNull(var10005);
      this.swapTypeHead = new ModeSetting("Свапать нa", "Шлем", var10005::get, new String[]{"Шлем", "Шар "});
      this.totemSwap = new BooleanSetting("Свап топора на меч", true);
      this.notif = new BooleanSetting("Отображать свап предметов", true);
      this.stopWatch = new TimeUtil();
      this.attackSpeedTimer = new TimeUtil();
      this.hadAttackSpeedRecently = false;
      this.storedAxeSlot = -1;
      this.lastOffhandStack = ItemStack.EMPTY;
      this.lastAttackSpeedResult = false;
      this.isSwapping = false;
      this.addSettings(new Setting[]{this.handMode, this.headMode, this.swapKey, this.itemType, this.swapType, this.swapKeyHead, this.itemTypeHead, this.swapTypeHead, this.totemSwap, this.notif});
   }

   @EventTarget
   public void onEvent(EventKey event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && event.isHold() && !this.isSwapping && this.stopWatch.isReached(300L)) {
         int key = event.getKey();
         if ((Boolean)this.handMode.get() && (Integer)this.swapKey.get() >= 0 && key == (Integer)this.swapKey.get()) {
            this.handleHandSwap();
         }

         if ((Boolean)this.headMode.get() && (Integer)this.swapKeyHead.get() >= 0 && key == (Integer)this.swapKeyHead.get()) {
            this.handleHelmetSwap();
         }

      }
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         this.handleAttackSpeedLogic();
      }
   }

   private void handleHandSwap() {
      this.isSwapping = true;
      Minecraft var10000 = mc;
      ItemStack offhandStack = Minecraft.player.getHeldItemOffhand();
      Item selectedItem = this.getItemByType((String)this.itemType.get());
      Item swapItem = this.getItemByType((String)this.swapType.get());
      Minecraft var10001;
      int slot;
      int invSlot;
      if (offhandStack.getItem() == selectedItem) {
         slot = this.getInventorySlot(swapItem);
         if (slot >= 0) {
            invSlot = slot < 9 ? slot + 36 : slot;
            InventoryUtil.swapHand(invSlot, Hand.OFF_HAND, false);
            var10001 = mc;
            this.notifySwap(Minecraft.player.getHeldItemOffhand());
            this.stopWatch.reset();
         }
      } else {
         slot = this.getInventorySlot(selectedItem);
         if (slot >= 0) {
            invSlot = slot < 9 ? slot + 36 : slot;
            InventoryUtil.swapHand(invSlot, Hand.OFF_HAND, false);
            var10001 = mc;
            this.notifySwap(Minecraft.player.getHeldItemOffhand());
            this.stopWatch.reset();
         }
      }

      this.isSwapping = false;
   }

   private void handleHelmetSwap() {
      this.isSwapping = true;
      Minecraft var10000 = mc;
      ItemStack helmetStack = (ItemStack)Minecraft.player.inventory.armorInventory.get(3);
      Item selectedItem = this.getItemByType((String)this.itemTypeHead.get());
      Item swapItem = this.getItemByType((String)this.swapTypeHead.get());
      Minecraft var10001;
      int slot;
      if (helmetStack.getItem() == selectedItem) {
         slot = this.getInventorySlot(swapItem);
         if (slot >= 0) {
            InventoryUtil.moveItemtest(slot, 5);
            var10001 = mc;
            this.notifySwap((ItemStack)Minecraft.player.inventory.armorInventory.get(3));
            this.stopWatch.reset();
         }
      } else {
         slot = this.getInventorySlot(selectedItem);
         if (slot >= 0) {
            InventoryUtil.moveItemtest(slot, 5);
            var10001 = mc;
            this.notifySwap((ItemStack)Minecraft.player.inventory.armorInventory.get(3));
            this.stopWatch.reset();
         }
      }

      this.isSwapping = false;
   }

   private void handleAttackSpeedLogic() {
      Minecraft var10000 = mc;
      ItemStack offhandItemStack = Minecraft.player.getHeldItemOffhand();
      if (!ItemStack.areItemStacksEqual(offhandItemStack, this.lastOffhandStack)) {
         this.lastAttackSpeedResult = this.hasAttackSpeedDescription(offhandItemStack);
         this.lastOffhandStack = offhandItemStack.copy();
      }

      if (this.lastAttackSpeedResult) {
         this.hadAttackSpeedRecently = true;
         this.attackSpeedTimer.reset();
      } else if (this.attackSpeedTimer.isReached(500L)) {
         this.hadAttackSpeedRecently = false;
      }

      if ((Boolean)this.totemSwap.get() && this.stopWatch.isReached(300L)) {
         var10000 = mc;
         ItemStack mainHand = Minecraft.player.getHeldItemMainhand();
         if (mainHand.getItem() instanceof SwordItem && offhandItemStack.getItem() == Items.PLAYER_HEAD && this.lastAttackSpeedResult) {
            int axeSlot = this.getAxeSlot();
            if (axeSlot != -1) {
               var10000 = mc;
               Minecraft.player.inventory.currentItem = axeSlot;
               this.stopWatch.reset();
            }
         }

         boolean hasAxeInHotbar = this.getAxeSlot() != -1;
         boolean hasSwordInHotbar = this.getSwordSlot() != -1;
         if (hasAxeInHotbar && hasSwordInHotbar) {
            int axeSlot;
            if (!this.lastAttackSpeedResult && this.hadAttackSpeedRecently && mainHand.getItem() instanceof AxeItem) {
               Minecraft var10001 = mc;
               this.storedAxeSlot = Minecraft.player.inventory.currentItem;
               axeSlot = this.getSwordSlot();
               if (axeSlot != -1) {
                  var10000 = mc;
                  Minecraft.player.inventory.currentItem = axeSlot;
                  this.stopWatch.reset();
               }
            } else if (this.lastAttackSpeedResult && mainHand.getItem() instanceof SwordItem && this.storedAxeSlot != -1) {
               axeSlot = this.getAxeSlot();
               if (axeSlot != -1) {
                  var10000 = mc;
                  Minecraft.player.inventory.currentItem = axeSlot;
                  this.storedAxeSlot = -1;
                  this.stopWatch.reset();
               }
            }
         }

      }
   }

   private void notifySwap(ItemStack stack) {
      if ((Boolean)this.notif.get() && !stack.isEmpty()) {
         String itemName = stack.getDisplayName().getString();
         String[] words = itemName.split(" ");
         if (words.length > 1) {
            String lastWord = words[words.length - 1];
            String beforeLast = String.join(" ", (CharSequence[])Arrays.copyOf(words, words.length - 1));
            NotificationManager.addNotification(stack, new StringTextComponent(beforeLast + " " + lastWord), true);
         } else {
            NotificationManager.addNotification(stack, new StringTextComponent("" + itemName), true);
         }
      }

   }

   private Item getItemByType(String itemType) {
      Item var10000;
      switch (itemType) {
         case "Щит":
            var10000 = Items.SHIELD;
            break;
         case "Тотем":
            var10000 = Items.TOTEM_OF_UNDYING;
            break;
         case "Геплы":
            var10000 = Items.GOLDEN_APPLE;
            break;
         case "Шар ":
            var10000 = Items.PLAYER_HEAD;
            break;
         case "Шлем":
            var10000 = Items.NETHERITE_HELMET;
            break;
         default:
            var10000 = Items.AIR;
      }

      return var10000;
   }

   private int getInventorySlot(Item item) {
      if (item == Items.AIR) {
         return -1;
      } else {
         for(int i = 0; i < 36; ++i) {
            Minecraft var10000 = mc;
            if (Minecraft.player.inventory.getStackInSlot(i).getItem() == item) {
               return i;
            }
         }

         return -1;
      }
   }

   private int getSwordSlot() {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof SwordItem) {
            return i;
         }
      }

      return -1;
   }

   private int getAxeSlot() {
      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof AxeItem) {
            return i;
         }
      }

      return -1;
   }

   private boolean hasAttackSpeedDescription(ItemStack itemStack) {
      if (itemStack.isEmpty()) {
         return false;
      } else if (itemStack.hasDisplayName() && itemStack.getDisplayName().getString().contains("Кобры")) {
         return true;
      } else {
         Minecraft var10001 = mc;
         List tooltip = itemStack.getTooltip(Minecraft.player, TooltipFlags.NORMAL);
         Iterator var3 = tooltip.iterator();

         String text;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            ITextComponent component = (ITextComponent)var3.next();
            text = component.getString();
         } while(!text.contains("Скорости Атаки") && !text.contains("Скорость атаки") && !text.contains("Attack Speed"));

         return true;
      }
   }

   public void onDisable() {
      this.storedAxeSlot = -1;
      this.hadAttackSpeedRecently = false;
      this.isSwapping = false;
      this.lastOffhandStack = ItemStack.EMPTY;
      super.onDisable();
   }
}
