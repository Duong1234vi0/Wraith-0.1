package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_265;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_3966;
import net.minecraft.class_2828.class_2829;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class MaceDamage extends Module {
   private final Setting<Boolean> spam = new Setting<>("Spam", false);
   private final Setting<Integer> fallHeight = new Setting<>("FallHeight", 20, 1, 170);
   private long lastUse = 0L;

   public MaceDamage() {
      super("MaceDamage", Module.Category.COMBAT);
   }

   @EventHandler
   private void onAttack(PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2824) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            long now = System.currentTimeMillis();
            if (this.spam.getValue() || now - this.lastUse >= 1000L) {
               class_1799 mainHand = mc.field_1724.method_6047();
               if (mainHand.method_7909() == class_1802.field_49814) {
                  boolean targetIsLiving = false;
                  class_239 hit = mc.field_1765;
                  if (hit instanceof class_3966) {
                     class_1297 e = ((class_3966)hit).method_17782();
                     if (e instanceof class_1309) {
                        targetIsLiving = true;
                     }
                  }

                  if (targetIsLiving) {
                     int height = this.determineHeight();
                     if (height > 0) {
                        int safeHeight = Math.min(height, this.fallHeight.getValue());
                        double x = mc.field_1724.method_23317();
                        double y = mc.field_1724.method_23318();
                        double z = mc.field_1724.method_23321();

                        try {
                           this.sendPacket(new class_2829(x, y, z, mc.field_1724.method_24828()));
                           double newYUp = y + safeHeight;
                           this.sendPacket(new class_2829(x, newYUp, z, false));
                           this.sendPacket(new class_2829(x, y, z, false));
                           this.lastUse = now;
                        } catch (Exception var17) {
                           var17.printStackTrace();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private int determineHeight() {
      class_238 boundingBox = mc.field_1724.method_5829();

      for (int i = this.fallHeight.getValue(); i >= 1; i--) {
         class_238 newBB = boundingBox.method_989(0.0, i, 0.0);
         boolean noCollision = true;

         for (class_265 shape : mc.field_1687.method_20812(mc.field_1724, newBB)) {
            if (shape != null && !shape.method_1110()) {
               noCollision = false;
               break;
            }
         }

         if (noCollision) {
            return i;
         }
      }

      if (this.fallHeight.getValue() >= 1) {
         class_238 newBB = boundingBox.method_989(0.0, 1.0, 0.0);
         boolean noCollision = true;

         for (class_265 shapex : mc.field_1687.method_20812(mc.field_1724, newBB)) {
            if (shapex != null && !shapex.method_1110()) {
               noCollision = false;
               break;
            }
         }

         if (noCollision) {
            return 1;
         }
      }

      return 0;
   }

   private void sendPacket(class_2828 packet) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(packet);
      }
   }
}
