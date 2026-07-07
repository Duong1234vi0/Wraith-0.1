package unique.core.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2815;
import net.minecraft.class_2848;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import net.minecraft.class_2848.class_2849;
import unique.core.core.Managers;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.utility.Timer;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;

public class ElytraSwap extends Module {
   public final Setting<Boolean> delay = new Setting<>("Delay", false);
   public final Setting<Bind> switchButton = new Setting<>("SwitchButton", new Bind(-1, false, false));
   public final Setting<Bind> fireWorkButton = new Setting<>("FireWorkButton", new Bind(-1, false, false));
   public final Setting<Boolean> startFireWork = new Setting<>("StartFireWork", true);
   public final Setting<Boolean> autoSwap = new Setting<>("AutoSwap", true);
   public final Setting<Integer> distance = new Setting<>("Distance", 5, 3, 10, v -> this.autoSwap.getValue());
   public final Setting<ElytraSwap.FireWorkMode> fireWorkMode = new Setting<>("FireWorkMode", ElytraSwap.FireWorkMode.Silent);
   private final Timer switchTimer = new Timer();
   private final Timer fireworkTimer = new Timer();
   public static boolean swapping = false;

   public ElytraSwap() {
      super("ElytraSwap", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1755 == null) {
            if (this.switchButton.getValue().getKey() != -1 && this.isKeyPressed(this.switchButton.getValue().getKey()) && this.switchTimer.every(500L)) {
               this.swapChest(false);
            }

            if (this.fireWorkButton.getValue().getKey() != -1
               && this.isKeyPressed(this.fireWorkButton.getValue().getKey())
               && this.fireworkTimer.every(500L)
               && mc.field_1724.method_6128()) {
               this.useFireWork();
            }
         }

         if (this.autoSwap.getValue()) {
            if (mc.field_1724.method_6128()) {
               if (!swapping) {
                  if (mc.field_1724.method_31548().method_5438(38).method_7909() == class_1802.field_8833 && this.isTargetInRange(this.getSwapDistance())) {
                     this.swapChest(false);
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.SendPost e) {
      if (e.getPacket() instanceof class_2848 p && p.method_12365() == class_2849.field_12982 && this.startFireWork.getValue()) {
         this.useFireWork();
      }
   }

   private int getSwapDistance() {
      int d = this.distance.getValue();
      if (d <= 4) {
         return 3;
      } else {
         return d <= 7 ? 5 : 10;
      }
   }

   private boolean isTargetInRange(double range) {
      for (class_1297 e : mc.field_1687.method_18112()) {
         if (e instanceof class_1309 && e != mc.field_1724 && e.method_5805() && mc.field_1724.method_5739(e) <= range) {
            return true;
         }
      }

      return false;
   }

   public void useFireWork() {
      SearchInvResult hotbar = InventoryUtility.findItemInHotBar(class_1802.field_8639);
      SearchInvResult inv = InventoryUtility.findItemInInventory(class_1802.field_8639);
      InventoryUtility.saveSlot();
      if (hotbar.found()) {
         hotbar.switchTo();
      } else {
         if (!inv.found()) {
            this.sendMessage(ClientSettings.isRu() ? "У тебя нет фейерверков!" : "You've Got No Fireworks!");
            return;
         }

         mc.field_1761
            .method_2906(mc.field_1724.field_7512.field_7763, inv.slot(), mc.field_1724.method_31548().field_7545, class_1713.field_7791, mc.field_1724);
         this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
      }

      this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
      this.sendPacket(new class_2879(class_1268.field_5808));
      if (this.fireWorkMode.getValue() == ElytraSwap.FireWorkMode.Silent) {
         InventoryUtility.returnSlot();
         if (!hotbar.found()) {
            mc.field_1761
               .method_2906(mc.field_1724.field_7512.field_7763, inv.slot(), mc.field_1724.method_31548().field_7545, class_1713.field_7791, mc.field_1724);
            this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
         }
      }
   }

   public static int getChestPlateSlot() {
      class_1792[] items = new class_1792[]{
         class_1802.field_22028, class_1802.field_8058, class_1802.field_8873, class_1802.field_8523, class_1802.field_8678, class_1802.field_8577
      };

      for (class_1792 item : items) {
         SearchInvResult slot = InventoryUtility.findItemInInventory(item);
         if (slot.found()) {
            return slot.slot();
         }
      }

      return -1;
   }

   private void swapChest(boolean disable) {
      SearchInvResult elytra = InventoryUtility.findItemInInventory(class_1802.field_8833);
      if (mc.field_1724.method_31548().method_5438(38).method_7909() == class_1802.field_8833) {
         int slot = getChestPlateSlot();
         if (slot == -1) {
            this.sendMessage(ClientSettings.isRu() ? "У тебя нет нагрудника!" : "You Don't Have A Chestplate!");
            return;
         }

         if (this.delay.getValue()) {
            Managers.ASYNC.run(() -> {
               swapping = true;
               clickSlot(slot);
               this.sleep();
               clickSlot(6);
               this.sleep();
               clickSlot(slot);
               this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               swapping = false;
            });
         } else {
            swapping = true;
            clickSlot(slot);
            clickSlot(6);
            clickSlot(slot);
            this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
            swapping = false;
         }
      } else if (elytra.found()) {
         if (this.delay.getValue()) {
            new Thread(() -> {
               swapping = true;
               clickSlot(elytra.slot());
               this.sleep();
               clickSlot(6);
               this.sleep();
               clickSlot(elytra.slot());
               this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               if (this.startFireWork.getValue() && mc.field_1724.field_6017 > 0.0F) {
                  this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
               }

               swapping = false;
            }).start();
         } else {
            swapping = true;
            clickSlot(elytra.slot());
            clickSlot(6);
            clickSlot(elytra.slot());
            this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
            if (this.startFireWork.getValue() && mc.field_1724.field_6017 > 0.0F) {
               this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
            }

            swapping = false;
         }
      } else {
         this.sendMessage(ClientSettings.isRu() ? "У тебя нет элитры!" : "You Don't Have An Elytra!");
      }
   }

   private void sleep() {
      try {
         Thread.sleep(200L);
      } catch (Exception var2) {
      }
   }

   private static enum FireWorkMode {
      Silent,
      Normal;
   }
}
