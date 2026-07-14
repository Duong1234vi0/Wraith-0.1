package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventKey;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class FunTimeHelper extends Module {
   public BindSetting useDezor = new BindSetting("Дезориентация");
   public BindSetting useTrap = new BindSetting("Трапка");
   public BindSetting usePil = new BindSetting("Явная пыль");
   public BindSetting useSmerch = new BindSetting("Огненный смерч");
   public BindSetting usePlast = new BindSetting("Пласт");
   public BindSetting useAura = new BindSetting("Божья аура");
   public BindSetting useSnow = new BindSetting("Снежок");
   boolean canUse = false;
   public boolean slow = false;
   private boolean progress = false;
   private boolean allow;
   private int savedInvSlot = -1;
   private int savedHotbarSlot = -1;
   private long delay = -1L;
   ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
   Item swapItem;

   public FunTimeHelper() {
      super("FuntimeHelper", "Помощник для сервера Funtime", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.useDezor, this.useTrap, this.usePil, this.useSmerch, this.usePlast, this.useAura, this.useSnow});
   }

   private void useItemAndClick(Item item, long delay) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (InventoryUtil.find(item) >= 0) {
            if (!this.progress) {
               this.swapItem = item;
               this.progress = true;
               this.allow = true;
               this.canUse = true;
            }
         }
      }
   }

   @EventTarget
   public void onUse(EventUpdate e) {
      if (this.swapItem != null) {
         this.itemController(this.swapItem);
      }
   }

   private void itemController(Item item) {
      int slot = InventoryUtil.find(item);
      Minecraft var10000;
      Minecraft var10005;
      if (slot >= 0) {
         int hotbarSlot;
         if (this.allow && InventoryUtil.haveHotBar(Items.ENDER_PEARL)) {
            hotbarSlot = slot - 36;
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hotbarSlot));
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CAnimateHandPacket(Hand.MAIN_HAND));
            this.delay = System.currentTimeMillis() + 150L;
            this.allow = false;
         } else if (this.allow) {
            MoveComponent.stopTicks = 1;
            MoveComponent.stop = true;
            if (!MoveUtil.isMoving()) {
               var10000 = mc;
               hotbarSlot = Minecraft.player.inventory.currentItem % 8 + 1;
               this.savedInvSlot = slot;
               this.savedHotbarSlot = hotbarSlot;
               var10005 = mc;
               mc.playerController.windowClick(0, this.savedInvSlot, this.savedHotbarSlot, ClickType.SWAP, Minecraft.player);
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hotbarSlot));
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CAnimateHandPacket(Hand.MAIN_HAND));
               this.delay = System.currentTimeMillis() + 200L;
               if (mc.currentScreen == null) {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CCloseWindowPacket());
               }

               this.allow = false;
            }
         }
      }

      if (this.delay >= 0L && System.currentTimeMillis() >= this.delay) {
         Minecraft var10003;
         if (this.savedInvSlot != -1 && this.savedHotbarSlot != -1) {
            MoveComponent.stopTicks = 1;
            MoveComponent.stop = true;
            if (!MoveUtil.isMoving()) {
               var10000 = mc;
               var10003 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
               var10005 = mc;
               mc.playerController.windowClick(0, this.savedInvSlot, this.savedHotbarSlot, ClickType.SWAP, Minecraft.player);
               if (mc.currentScreen == null) {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CCloseWindowPacket());
               }

               this.savedInvSlot = -1;
               this.savedHotbarSlot = -1;
               this.delay = -1L;
               this.swapItem = null;
               this.progress = false;
               this.canUse = false;
            }
         } else {
            var10000 = mc;
            var10003 = mc;
            Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
            this.delay = -1L;
            this.swapItem = null;
            this.progress = false;
            this.canUse = false;
         }
      }

   }

   @EventTarget
   public void onKey(EventKey e) {
      if (mc.currentScreen == null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (this.canUse) {
               return;
            }

            boolean keyWasPressed = (Integer)this.useDezor.get() == e.getKey() || (Integer)this.useTrap.get() == e.getKey() || (Integer)this.usePil.get() == e.getKey() || (Integer)this.useSmerch.get() == e.getKey() || (Integer)this.usePlast.get() == e.getKey() || (Integer)this.useAura.get() == e.getKey() || (Integer)this.useSnow.get() == e.getKey();
            if (keyWasPressed) {
               this.slow = true;
            }

            long sleep = 0L;
            if (e.isHold()) {
               if ((Integer)this.useDezor.get() == e.getKey()) {
                  this.useItemAndClick(Items.ENDER_EYE, sleep);
               }

               if ((Integer)this.useTrap.get() == e.getKey()) {
                  this.useItemAndClick(Items.NETHERITE_SCRAP, sleep);
               }

               if ((Integer)this.usePil.get() == e.getKey()) {
                  this.useItemAndClick(Items.SUGAR, sleep);
               }

               if ((Integer)this.useSmerch.get() == e.getKey()) {
                  this.useItemAndClick(Items.FIRE_CHARGE, sleep);
               }

               if ((Integer)this.usePlast.get() == e.getKey()) {
                  this.useItemAndClick(Items.DRIED_KELP, sleep);
               }

               if ((Integer)this.useAura.get() == e.getKey()) {
                  this.useItemAndClick(Items.PHANTOM_MEMBRANE, sleep);
               }

               if ((Integer)this.useSnow.get() == e.getKey()) {
                  this.useItemAndClick(Items.SNOWBALL, sleep);
               }
            }
         }
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      this.slow = false;
   }
}
