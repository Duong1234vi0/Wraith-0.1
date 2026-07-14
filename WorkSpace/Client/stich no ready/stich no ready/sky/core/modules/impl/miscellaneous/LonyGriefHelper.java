package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventKey;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class LonyGriefHelper extends Module {
   public BindSetting useBlazeRod = new BindSetting("Ливалка");
   public BindSetting cryingObsidianKey = new BindSetting("Трапка");
   public BindSetting clayKey = new BindSetting("Ливалка с платформой");
   public BooleanSetting autoGps = new BooleanSetting("AutoGps", true);
   BooleanSetting stop = new BooleanSetting("Полная остановка", false);
   boolean canUse = false;
   public boolean slow = false;
   private boolean progress = false;
   private final Pattern pattern = Pattern.compile("Его координаты: (-?\\d+)\\. (-?\\d+)\\. (-?\\d+)\\.");
   Item swapItem;
   private boolean allow;
   private long delay = -1L;
   private int savedInvSlot = -1;
   private int savedHotbarSlot = -1;

   public LonyGriefHelper() {
      super("LonyGrief Assist", "Ассит под LonyGrief", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.useBlazeRod, this.cryingObsidianKey, this.clayKey, this.stop, this.autoGps});
   }

   private void useItemAndClick(Item item) {
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
   public void onPacket(EventPacket event) {
      if ((Boolean)this.autoGps.get() && event.isReceive() && event.getPacket() instanceof SChatPacket) {
         SChatPacket packet = (SChatPacket)event.getPacket();
         String text = packet.getChatComponent().getString();
         if (text.contains("Его координаты:")) {
            Matcher matcher = this.pattern.matcher(text);
            if (matcher.find()) {
               String x = matcher.group(1);
               String z = matcher.group(3);
               Minecraft var10000 = mc;
               if (Minecraft.player != null) {
                  var10000 = mc;
                  Minecraft.player.sendChatMessage(".gps " + x + " " + z);
               }
            }
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

            boolean keyWasPressed = e.getKey() == (Integer)this.useBlazeRod.get() || e.getKey() == (Integer)this.cryingObsidianKey.get() || e.getKey() == (Integer)this.clayKey.get();
            if (keyWasPressed) {
               this.slow = true;
            }

            if (e.isHold()) {
               if ((Integer)this.useBlazeRod.get() == e.getKey()) {
                  this.useItemAndClick(Items.MAGMA_CREAM);
               }

               if ((Integer)this.cryingObsidianKey.get() == e.getKey()) {
                  this.useItemAndClick(Items.CRYING_OBSIDIAN);
               }

               if ((Integer)this.clayKey.get() == e.getKey()) {
                  this.useItemAndClick(Items.CLAY_BALL);
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
