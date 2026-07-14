package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.SkullItem;
import sky.core.events.EventUpdate;
import sky.core.utils.Wrapper;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ServerUtil;

public class HolyWorldJoinHandler implements Wrapper {
   private static final TimeUtil timer = new TimeUtil();
   private static Phase phase;
   private static int targetGrief;

   public static void startRejoin() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         targetGrief = getGrief();
         if (targetGrief > 0) {
            phase = HolyWorldJoinHandler.Phase.SENT_HUB;
            var10000 = mc;
            Minecraft.player.sendChatMessage("/hub");
            timer.reset();
         }
      }
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      if (phase != HolyWorldJoinHandler.Phase.IDLE) {
         if (!ServerUtil.isConnectedToServer("holyworld")) {
            reset();
         }

         if (phase == HolyWorldJoinHandler.Phase.SENT_HUB && timer.hasTimeElapsed(200L)) {
            this.useCompass();
            phase = HolyWorldJoinHandler.Phase.USED_COMPASS;
            timer.reset();
         } else if (phase == HolyWorldJoinHandler.Phase.USED_COMPASS && !(mc.currentScreen instanceof ContainerScreen)) {
            if (timer.hasTimeElapsed(500L)) {
               this.useCompass();
               timer.reset();
            }

         } else {
            Screen var3 = mc.currentScreen;
            if (var3 instanceof ContainerScreen) {
               ContainerScreen container = (ContainerScreen)var3;

               for(int i = 0; i < container.getContainer().inventorySlots.size(); ++i) {
                  Slot slot = (Slot)container.getContainer().inventorySlots.get(i);
                  if (!slot.getStack().isEmpty()) {
                     if (phase == HolyWorldJoinHandler.Phase.USED_COMPASS) {
                        if (targetGrief <= 16 && slot.getStack().getDisplayName().getString().contains("СолоЛайт")) {
                           phase = HolyWorldJoinHandler.Phase.CLICKED_GRIEF_SURVIVAL;
                           this.clickSlot(i);
                           break;
                        }

                        if (targetGrief > 16 && targetGrief <= 37 && slot.getStack().getDisplayName().getString().contains("ДуоЛайт")) {
                           phase = HolyWorldJoinHandler.Phase.CLICKED_GRIEF_SURVIVAL;
                           this.clickSlot(i);
                           break;
                        }

                        if (targetGrief > 37 && targetGrief <= 53 && slot.getStack().getDisplayName().getString().contains("ТриоЛайт")) {
                           phase = HolyWorldJoinHandler.Phase.CLICKED_GRIEF_SURVIVAL;
                           this.clickSlot(i);
                           break;
                        }

                        if (targetGrief > 53 && targetGrief <= 69 && slot.getStack().getDisplayName().getString().contains("КланЛайт")) {
                           phase = HolyWorldJoinHandler.Phase.CLICKED_GRIEF_SURVIVAL;
                           this.clickSlot(i);
                           break;
                        }
                     }

                     if (phase == HolyWorldJoinHandler.Phase.CLICKED_GRIEF_SURVIVAL && slot.getStack().getTag().toString().contains("#" + targetGrief) && slot.getStack().getItem() instanceof SkullItem && timer.hasTimeElapsed(100L)) {
                        this.clickSlot(i);
                        reset();
                        break;
                     }
                  }
               }

            }
         }
      }
   }

   private void useCompass() {
      Minecraft var10000 = mc;
      Minecraft.player.sendChatMessage("/lite");
   }

   private void clickSlot(int slotIndex) {
      Minecraft var10001 = mc;
      Minecraft var10005 = mc;
      mc.playerController.windowClick(Minecraft.player.openContainer.windowId, slotIndex, 0, ClickType.PICKUP, Minecraft.player);
      timer.reset();
   }

   public static int getGrief() {
      if (mc.world == null) {
         return -1;
      } else {
         String headerText = mc.ingameGUI.getTabList().getHeader().getString();
         if (headerText != null && !headerText.isEmpty()) {
            String displayName = headerText.toLowerCase();
            int prefix = displayName.indexOf("лайт");
            if (prefix == -1) {
               return -1;
            } else {
               try {
                  String numberStr = displayName.substring(prefix + "лайт".length()).replaceAll("\\D", "");
                  if (!numberStr.isEmpty()) {
                     return Integer.parseInt(numberStr);
                  }
               } catch (NumberFormatException var4) {
               }

               return -1;
            }
         } else {
            return -1;
         }
      }
   }

   private static void reset() {
      phase = HolyWorldJoinHandler.Phase.IDLE;
      targetGrief = -1;
      timer.reset();
   }

   static {
      phase = HolyWorldJoinHandler.Phase.IDLE;
      targetGrief = -1;
   }

   private static enum Phase {
      IDLE,
      SENT_HUB,
      USED_COMPASS,
      CLICKED_GRIEF_SURVIVAL;

      // $FF: synthetic method
      private static Phase[] $values() {
         return new Phase[]{IDLE, SENT_HUB, USED_COMPASS, CLICKED_GRIEF_SURVIVAL};
      }
   }
}
