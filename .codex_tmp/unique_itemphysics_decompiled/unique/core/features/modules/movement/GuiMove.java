package unique.core.features.modules.movement;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2813;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_408;
import net.minecraft.class_433;
import net.minecraft.class_465;
import net.minecraft.class_490;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.TimerUtil;
import unique.core.utility.player.MovementUtility;

public class GuiMove extends Module {
   private final Setting<GuiMove.Bypass> clickBypass = new Setting<>("Mode", GuiMove.Bypass.None);
   private final Setting<Boolean> sneakInGui = new Setting<>("Sneak", true);
   private final Queue<class_2813> packetQueue = new ConcurrentLinkedQueue<>();
   private boolean wasInventoryOpen;
   private final TimerUtil timer = new TimerUtil();

   public GuiMove() {
      super("GuiMove", Module.Category.MOVEMENT);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null) {
         class_304[] keys = new class_304[]{
            mc.field_1690.field_1894,
            mc.field_1690.field_1881,
            mc.field_1690.field_1913,
            mc.field_1690.field_1849,
            mc.field_1690.field_1903,
            mc.field_1690.field_1832
         };
         boolean inBlockedScreen = mc.field_1755 instanceof class_408 || mc.field_1755 instanceof class_433;
         boolean inInventory = mc.field_1755 instanceof class_490;
         if (!inBlockedScreen) {
            if (this.isLegitMode() && !this.packetQueue.isEmpty() && !this.timer.hasTimeElapsed(100L)) {
               for (class_304 key : keys) {
                  key.method_23481(false);
               }
            } else {
               for (class_304 key : keys) {
                  if (key == mc.field_1690.field_1832 && inInventory && !this.sneakInGui.getValue()) {
                     key.method_23481(false);
                  } else {
                     key.method_23481(this.isKeyPressed(key));
                  }
               }

               if (this.isLegitMode()) {
                  if (inInventory) {
                     this.wasInventoryOpen = true;
                  } else if (this.wasInventoryOpen) {
                     this.sendQueuedPackets();
                     this.wasInventoryOpen = false;
                     this.timer.reset();
                  }
               } else {
                  this.packetQueue.clear();
               }
            }
         } else {
            for (class_304 keyx : keys) {
               keyx.method_23481(false);
            }
         }
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (this.isLegitMode()) {
         if (e.getPacket() instanceof class_2813 click && click.method_12194() == 0 && MovementUtility.isMoving() && mc.field_1755 instanceof class_465) {
            this.packetQueue.add(click);
            e.cancel();
         }
      }
   }

   private void sendQueuedPackets() {
      new Thread(() -> {
         try {
            Thread.sleep(80L);

            while (!this.packetQueue.isEmpty()) {
               class_2813 packet = this.packetQueue.poll();
               if (packet != null && mc.method_1562() != null) {
                  mc.method_1562().method_52787(packet);
               }
            }
         } catch (InterruptedException var2) {
         }
      }).start();
   }

   private boolean isLegitMode() {
      return this.clickBypass.getValue() == GuiMove.Bypass.Legit;
   }

   private boolean isKeyPressed(class_304 key) {
      int code = class_3675.method_15981(key.method_1428()).method_1444();
      return code >= 0 && class_3675.method_15987(mc.method_22683().method_4490(), code);
   }

   public static enum Bypass {
      None,
      Legit;
   }
}
