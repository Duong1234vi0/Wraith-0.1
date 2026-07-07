package unique.core.features.modules.misc;

import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;

public class FastXP extends Module {
   private final Setting<Boolean> antiWaste = new Setting<>("AntiWaste", true);
   private final Setting<Integer> durability;
   private final Setting<Boolean> feetExpGroup;
   private final Setting<Float> feetExpPitch;
   private boolean isThrowingXp;
   private int lastSlot;
   private long lastXpUseTime;
   private final Random rand = new Random();

   public FastXP() {
      super("FastXP", Module.Category.MISC);
      this.durability = new Setting<>("StopOn", 95, 0, 100, v -> this.antiWaste.getValue());
      this.feetExpGroup = new Setting<>("FeetXP", true);
      this.feetExpPitch = new Setting<>("FeetXP Pitch", 70.0F, 45.0F, 90.0F, v -> this.feetExpGroup.getValue());
      this.isThrowingXp = false;
      this.lastSlot = -1;
      this.lastXpUseTime = 0L;
   }

   @EventHandler
   private void onSync(EventSync event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1755 == null) {
            this.handleXpSpam();
            if (this.antiWaste.getValue() && !this.needXp()) {
               this.disable();
            }
         }
      }
   }

   private boolean needXp() {
      int stopOn = this.durability.getValue();
      if (mc.field_1724 == null) {
         return false;
      } else {
         for (class_1799 stack : mc.field_1724.method_31548().field_7547) {
            if (!stack.method_7960() && stack.method_7963()) {
               float percent = PlayerUtility.calculatePercentage(stack);
               if (percent < stopOn) {
                  return true;
               }
            }
         }

         for (class_1799 stackx : mc.field_1724.method_31548().field_7548) {
            if (!stackx.method_7960() && stackx.method_7963()) {
               float percent = PlayerUtility.calculatePercentage(stackx);
               if (percent < stopOn) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private void handleXpSpam() {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         if (this.antiWaste.getValue() && !this.needXp()) {
            this.resetSlot();
         } else {
            if (this.feetExpGroup.getValue()) {
               mc.field_1724.method_36457(this.feetExpPitch.getValue());
            }

            if (!this.isThrowingXp) {
               this.lastSlot = mc.field_1724.method_31548().field_7545;
               this.isThrowingXp = true;
            }

            long now = System.currentTimeMillis();
            int delay = 20 + this.rand.nextInt(15);
            if (now - this.lastXpUseTime >= delay) {
               int xpSlot = InventoryUtility.findItemInHotBar(class_1802.field_8287).slot();
               if (xpSlot == -1) {
                  this.disable();
                  return;
               }

               int oldSlot = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = xpSlot;
               mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
               this.lastXpUseTime = now;
            }
         }
      }
   }

   private void resetSlot() {
      if (mc.field_1724 != null && this.isThrowingXp && this.lastSlot != -1) {
         mc.field_1724.method_31548().field_7545 = this.lastSlot;
      }

      this.isThrowingXp = false;
      this.lastSlot = -1;
   }

   @Override
   public void onDisable() {
      this.resetSlot();
      super.onDisable();
   }
}
