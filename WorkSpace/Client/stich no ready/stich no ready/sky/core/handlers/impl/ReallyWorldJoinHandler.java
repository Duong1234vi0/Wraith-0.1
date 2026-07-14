package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CClickWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.network.play.server.SJoinGamePacket;
import net.minecraft.network.play.server.SOpenWindowPacket;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.impl.player.AutoJoiner;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.player.InventoryUtil;

public class ReallyWorldJoinHandler implements Wrapper {
   private static boolean enabled = false;
   private static int targetGrief = -1;
   private static Integer manualGrief = null;
   private static int pendingArrowClicks = 0;

   public static void startRejoin() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         targetGrief = manualGrief != null ? manualGrief : getGrief();
         if (targetGrief > 0) {
            pendingArrowClicks = Math.max(0, (targetGrief - 1) / 32);
            enabled = true;
            useCompassAndOpenSelector();
         }
      }
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      if (enabled) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.openContainer != null) {
               if (mc.currentScreen instanceof ContainerScreen) {
                  mc.displayGuiScreen((Screen)null);
               }

               Minecraft var10001;
               int slot;
               Slot containerSlot;
               Minecraft var10003;
               ItemStack stack;
               Minecraft var10008;
               Minecraft var10009;
               if (pendingArrowClicks > 0) {
                  slot = 0;

                  while(true) {
                     var10001 = mc;
                     if (slot >= Minecraft.player.openContainer.inventorySlots.size()) {
                        break;
                     }

                     var10000 = mc;
                     containerSlot = Minecraft.player.openContainer.getSlot(slot);
                     if (containerSlot != null && containerSlot.getHasStack()) {
                        stack = containerSlot.getStack();
                        if (stack.getItem() == Items.ARROW) {
                           var10000 = mc;
                           var10003 = mc;
                           var10008 = mc;
                           var10009 = mc;
                           Minecraft.player.connection.sendPacket(new CClickWindowPacket(Minecraft.player.openContainer.windowId, slot, 0, ClickType.PICKUP, stack, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
                           --pendingArrowClicks;
                           return;
                        }
                     }

                     ++slot;
                  }
               }

               slot = 0;

               while(true) {
                  var10001 = mc;
                  if (slot >= Minecraft.player.openContainer.inventorySlots.size()) {
                     return;
                  }

                  var10000 = mc;
                  containerSlot = Minecraft.player.openContainer.getSlot(slot);
                  if (containerSlot != null && containerSlot.getHasStack()) {
                     stack = containerSlot.getStack();
                     String itemName = stack.getDisplayName().getString();
                     String targetName = String.format("ГРИФ #%s (1.16.5+)", targetGrief);
                     if (itemName.equalsIgnoreCase(targetName)) {
                        var10000 = mc;
                        var10003 = mc;
                        var10008 = mc;
                        var10009 = mc;
                        Minecraft.player.connection.sendPacket(new CClickWindowPacket(Minecraft.player.openContainer.windowId, slot, 0, ClickType.PICKUP, stack, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
                     }
                  }

                  ++slot;
               }
            }
         }
      }

   }

   @EventTarget
   public void onPacket(EventPacket event) {
      if (enabled && event.isReceive()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            IPacket var3 = event.getPacket();
            if (var3 instanceof SChatPacket) {
               SChatPacket packet = (SChatPacket)var3;
               String message = packet.getChatComponent().getString();
               if (message.contains("Подождите несколько секунд перед повторым подключением!")) {
                  event.setCancelled(true);
               } else if (message.contains("Не удалось подключиться к серверу")) {
                  event.setCancelled(true);
                  ChatUtil.addChatMessage(String.format("Попытка зайти на гриф: #%s: " + String.valueOf(TextFormatting.RED) + "Неудачно", targetGrief));
               } else if (message.contains("К сожалению сервер переполнен")) {
                  event.setCancelled(true);
                  ChatUtil.addChatMessage(String.format("Попытка зайти на гриф: #%s: " + String.valueOf(TextFormatting.RED) + "Неудачно", targetGrief));
               } else {
                  useCompassAndOpenSelector();
               }
            }

            var3 = event.getPacket();
            if (var3 instanceof SOpenWindowPacket) {
               SOpenWindowPacket packet = (SOpenWindowPacket)var3;
               if (packet.getTitle().getString().contains("Выбор сервера")) {
                  var10000 = mc;
                  if (Minecraft.player.openContainer != null) {
                     var10000 = mc;
                     ClientPlayNetHandler var8 = Minecraft.player.connection;
                     int var10003 = packet.getWindowId();
                     ClickType var10006 = ClickType.PICKUP;
                     Minecraft var10007 = mc;
                     ItemStack var4 = Minecraft.player.openContainer.getSlot(21).getStack();
                     Minecraft var10008 = mc;
                     Minecraft var10009 = mc;
                     var8.sendPacket(new CClickWindowPacket(var10003, 21, 0, var10006, var4, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
                     event.setCancelled(true);
                  }
               }
            }

            if (event.getPacket() instanceof SJoinGamePacket) {
               ChatUtil.addChatMessage(String.format("Попытка зайти на гриф: #%s: " + String.valueOf(TextFormatting.GREEN) + "Готово", targetGrief));
               enabled = false;
               AutoJoiner autoJoiner = (AutoJoiner)SkyCore.getInstance().getModuleManager().getModule(AutoJoiner.class);
               if (autoJoiner != null && autoJoiner.isEnabled()) {
                  autoJoiner.settoggled(false);
               }
            }

         }
      }
   }

   private static void useCompassAndOpenSelector() {
      int slot = InventoryUtil.getSlot(Items.COMPASS);
      if (slot != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(slot));
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
            return;
         }
      }

   }

   public static int getGrief() {
      if (mc.world == null) {
         return -1;
      } else {
         Iterator var0 = mc.world.getScoreboard().getScoreObjectives().iterator();

         String board;
         do {
            if (!var0.hasNext()) {
               return -1;
            }

            ScoreObjective team = (ScoreObjective)var0.next();
            board = team.getDisplayName().getString();
         } while(!board.contains("ГРИФ #"));

         return Integer.parseInt(board.split("#")[1].replace(" ", ""));
      }
   }

   public static void setManualGrief(int grief) {
      manualGrief = grief;
   }

   public static void clearManualGrief() {
      manualGrief = null;
   }

   public static void disable() {
      enabled = false;
      manualGrief = null;
      targetGrief = -1;
      pendingArrowClicks = 0;
   }
}
