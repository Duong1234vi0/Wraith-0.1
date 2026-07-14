package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.util.Hand;
import sky.core.events.EventCancelHotbar;
import sky.core.events.EventInput;
import sky.core.events.EventUpdate;
import sky.core.utils.Wrapper;

public class InventoryHandler implements Wrapper {
   private static final int DEFAULT_DELAY = 1;
   private static ActionState actionState;
   private static int waitTicks;
   private static int rightClickTicks;
   private static int originalSlot;
   private static boolean pendingRightClick;
   private static boolean pendingInventoryTransfer;
   private static int inventoryTicks;
   private static int pendingInventorySlotId;
   private static int pendingHotbarIndex;
   private static InventoryStage inventoryStage;
   private static Object ownerToken;

   @EventTarget
   public void onEvent(EventInput e) {
      if (isBusy()) {
         e.setForward(0.0F);
         e.setStrafe(0.0F);
         e.setJump(false);
      }

   }

   @EventTarget
   public void onEvent(EventCancelHotbar e) {
      if (isBusy()) {
         e.setCancelled(true);
      }

   }

   @EventTarget
   public void onEvent(EventUpdate e) {
      if (actionState == InventoryHandler.ActionState.WAITING && --waitTicks <= 0) {
         actionState = InventoryHandler.ActionState.READY;
      }

      this.handleRightClick();
      this.handleInventoryTransfer();
   }

   private void handleRightClick() {
      if (pendingRightClick && --rightClickTicks <= 0) {
         click();
         if (originalSlot >= 0) {
            mc.playerController.syncCurrentPlayItem();
            Minecraft var10000 = mc;
            Minecraft.player.inventory.currentItem = originalSlot;
         }

         resetRightClick();
      }
   }

   private void handleInventoryTransfer() {
      if (pendingInventoryTransfer) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.playerController != null && --inventoryTicks <= 0) {
            Minecraft var10005;
            switch (inventoryStage) {
               case SWAP_TO_HOTBAR:
                  var10000 = mc;
                  pendingHotbarIndex = Minecraft.player.inventory.currentItem;
                  var10005 = mc;
                  mc.playerController.windowClick(0, pendingInventorySlotId, pendingHotbarIndex, ClickType.SWAP, Minecraft.player);
                  inventoryStage = InventoryHandler.InventoryStage.RIGHT_CLICK;
                  var10000 = mc;
                  Minecraft.player.closeScreen();
                  resetInventoryTimer();
                  break;
               case RIGHT_CLICK:
                  click();
                  inventoryStage = InventoryHandler.InventoryStage.SWAP_BACK;
                  resetInventoryTimer();
                  break;
               case SWAP_BACK:
                  var10005 = mc;
                  mc.playerController.windowClick(0, pendingInventorySlotId, pendingHotbarIndex, ClickType.SWAP, Minecraft.player);
                  var10000 = mc;
                  Minecraft.player.closeScreen();
                  resetInventoryTransfer();
            }

            return;
         }
      }

   }

   private static void click() {
      Minecraft var10001 = mc;
      mc.playerController.processRightClick(Minecraft.player, mc.world, Hand.MAIN_HAND);
      Minecraft var10000 = mc;
      Minecraft.player.swingArm(Hand.MAIN_HAND);
   }

   private static boolean isBusy() {
      return actionState != InventoryHandler.ActionState.IDLE || pendingInventoryTransfer;
   }

   public static void startWithoutAction(Object owner) {
      if (ownerToken == null || ownerToken == owner) {
         ownerToken = owner;
         actionState = InventoryHandler.ActionState.WAITING;
         waitTicks = 1;
      }
   }

   public static boolean isReadyForAction(Object owner) {
      return actionState == InventoryHandler.ActionState.READY && ownerToken == owner;
   }

   public static void finishAction(Object owner) {
      if (ownerToken == owner) {
         actionState = InventoryHandler.ActionState.IDLE;
         waitTicks = 0;
         ownerToken = null;
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            Minecraft.player.closeScreen();
         }

      }
   }

   public static void startWithLegit(Object owner, int slot) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         if (ownerToken == null || ownerToken == owner) {
            ownerToken = owner;
            var10000 = mc;
            originalSlot = Minecraft.player.inventory.currentItem;
            mc.playerController.syncCurrentPlayItem();
            var10000 = mc;
            Minecraft.player.inventory.currentItem = slot;
            pendingRightClick = true;
            rightClickTicks = 1;
         }
      }
   }

   public static void startFromInventory(Object owner, int slotId) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.playerController != null) {
         if (ownerToken == null || ownerToken == owner) {
            ownerToken = owner;
            pendingInventoryTransfer = true;
            inventoryStage = InventoryHandler.InventoryStage.SWAP_TO_HOTBAR;
            resetInventoryTimer();
            pendingInventorySlotId = slotId;
         }
      }
   }

   private static void resetRightClick() {
      pendingRightClick = false;
      rightClickTicks = 0;
      originalSlot = -1;
      clearOwnerIfIdle();
   }

   private static void resetInventoryTimer() {
      inventoryTicks = 1;
   }

   private static void resetInventoryTransfer() {
      pendingInventoryTransfer = false;
      inventoryStage = InventoryHandler.InventoryStage.SWAP_TO_HOTBAR;
      inventoryTicks = 0;
      pendingHotbarIndex = -1;
      pendingInventorySlotId = -1;
      clearOwnerIfIdle();
   }

   private static void clearOwnerIfIdle() {
      if (!pendingRightClick && !pendingInventoryTransfer && actionState == InventoryHandler.ActionState.IDLE) {
         ownerToken = null;
      }

   }

   static {
      actionState = InventoryHandler.ActionState.IDLE;
      originalSlot = -1;
      pendingInventorySlotId = -1;
      pendingHotbarIndex = -1;
      inventoryStage = InventoryHandler.InventoryStage.SWAP_TO_HOTBAR;
      ownerToken = null;
   }

   private static enum ActionState {
      IDLE,
      WAITING,
      READY;

      // $FF: synthetic method
      private static ActionState[] $values() {
         return new ActionState[]{IDLE, WAITING, READY};
      }
   }

   private static enum InventoryStage {
      SWAP_TO_HOTBAR,
      RIGHT_CLICK,
      SWAP_BACK;

      // $FF: synthetic method
      private static InventoryStage[] $values() {
         return new InventoryStage[]{SWAP_TO_HOTBAR, RIGHT_CLICK, SWAP_BACK};
      }
   }
}
