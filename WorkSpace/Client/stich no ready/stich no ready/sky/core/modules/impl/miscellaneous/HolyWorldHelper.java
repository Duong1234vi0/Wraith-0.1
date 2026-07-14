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
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventKey;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class HolyWorldHelper extends Module {
   public BindSetting useBlazeRod = new BindSetting("Штучка");
   public BindSetting useNetherStar = new BindSetting("Стан");
   public BindSetting usePrismarineShard = new BindSetting("Взрывная Трапка");
   public BindSetting useChorusFruit = new BindSetting("Трапка (Хорус)");
   public BindSetting useSnowball = new BindSetting("Снежок");
   public BindSetting useShulkerBox = new BindSetting("Рюкзак (Шалкер)");
   BooleanSetting stop = new BooleanSetting("Полная остановка", false);
   boolean canUse = false;
   public boolean slow = false;
   private boolean progress = false;
   ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
   Item swapItem;
   private boolean allow;
   private long delay = -1L;
   private int savedInvSlot = -1;
   private int savedHotbarSlot = -1;

   public HolyWorldHelper() {
      super("Holyworld Assist", "Ассит под Holyworld", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.useBlazeRod, this.useNetherStar, this.usePrismarineShard, this.useChorusFruit, this.useSnowball, this.useShulkerBox, this.stop});
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
      Minecraft var10003;
      Minecraft var10005;
      if (slot >= 0 && this.allow) {
         label65: {
            MoveComponent.stopTicks = 1;
            if ((Boolean)this.stop.get()) {
               MoveComponent.stop = true;
            }

            if (MoveUtil.isMoving()) {
               if ((Boolean)this.stop.get()) {
                  break label65;
               }

               var10000 = mc;
               if (Minecraft.player.serverSprintState) {
                  break label65;
               }

               var10000 = mc;
               if (Minecraft.player.isSprinting()) {
                  break label65;
               }
            }

            var10000 = mc;
            int hotbarSlot = Minecraft.player.inventory.currentItem;
            this.savedInvSlot = slot;
            this.savedHotbarSlot = hotbarSlot;
            var10005 = mc;
            mc.playerController.windowClick(0, this.savedInvSlot, this.savedHotbarSlot, ClickType.SWAP, Minecraft.player);
            mc.playerController.syncCurrentPlayItem();
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CAnimateHandPacket(Hand.MAIN_HAND));
            this.delay = System.currentTimeMillis() + 100L;
            if (mc.currentScreen == null) {
               var10000 = mc;
               var10003 = mc;
               Minecraft.player.connection.sendPacket(new CCloseWindowPacket(Minecraft.player.openContainer.windowId));
            }

            this.allow = false;
         }
      }

      if (this.delay >= 0L && System.currentTimeMillis() >= this.delay) {
         if (this.savedInvSlot != -1 && this.savedHotbarSlot != -1) {
            MoveComponent.stopTicks = 1;
            if ((Boolean)this.stop.get()) {
               MoveComponent.stop = true;
            }

            if (MoveUtil.isMoving()) {
               if ((Boolean)this.stop.get()) {
                  return;
               }

               var10000 = mc;
               if (Minecraft.player.serverSprintState) {
                  return;
               }

               var10000 = mc;
               if (Minecraft.player.isSprinting()) {
                  return;
               }
            }

            var10005 = mc;
            mc.playerController.windowClick(0, this.savedInvSlot, this.savedHotbarSlot, ClickType.SWAP, Minecraft.player);
            mc.playerController.syncCurrentPlayItem();
            if (mc.currentScreen == null) {
               var10000 = mc;
               var10003 = mc;
               Minecraft.player.connection.sendPacket(new CCloseWindowPacket(Minecraft.player.openContainer.windowId));
            }

            this.savedInvSlot = -1;
            this.savedHotbarSlot = -1;
            this.delay = -1L;
            this.swapItem = null;
            this.progress = false;
            this.canUse = false;
         } else {
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

            boolean keyWasPressed = e.getKey() == (Integer)this.useBlazeRod.get() || e.getKey() == (Integer)this.useNetherStar.get() || e.getKey() == (Integer)this.usePrismarineShard.get() || e.getKey() == (Integer)this.useChorusFruit.get() || e.getKey() == (Integer)this.useSnowball.get() || e.getKey() == (Integer)this.useShulkerBox.get();
            if (keyWasPressed) {
               this.slow = true;
            }

            long sleep = 0L;
            if (e.isHold()) {
               if ((Integer)this.useBlazeRod.get() == e.getKey()) {
                  this.useItemAndClick(Items.FIRE_CHARGE, sleep);
               }

               if ((Integer)this.useNetherStar.get() == e.getKey()) {
                  this.useItemAndClick(Items.NETHER_STAR, sleep);
               }

               if ((Integer)this.usePrismarineShard.get() == e.getKey()) {
                  this.useItemAndClick(Items.PRISMARINE_SHARD, sleep);
               }

               if ((Integer)this.useChorusFruit.get() == e.getKey()) {
                  this.useItemAndClick(Items.POPPED_CHORUS_FRUIT, sleep);
               }

               if ((Integer)this.useSnowball.get() == e.getKey()) {
                  this.useItemAndClick(Items.SNOWBALL, sleep);
               }

               if ((Integer)this.useShulkerBox.get() == e.getKey()) {
                  this.useItemAndClick(Items.SHULKER_BOX, sleep);
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
