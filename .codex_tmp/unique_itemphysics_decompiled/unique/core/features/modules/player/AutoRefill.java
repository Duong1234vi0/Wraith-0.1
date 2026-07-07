package unique.core.features.modules.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1803;
import net.minecraft.class_1812;
import net.minecraft.class_1828;
import net.minecraft.class_1844;
import net.minecraft.class_490;
import net.minecraft.class_9334;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.utility.Timer;

public class AutoRefill extends Module {
   private final Setting<BooleanSettingGroup> general = new Setting<>("General", new BooleanSettingGroup(true));
   private final Setting<Integer> delay = new Setting<>("Delay", 50, 10, 200).addToGroup(this.general);
   private final Setting<Integer> minStack = new Setting<>("MinStack", 16, 1, 64).addToGroup(this.general);
   private final Setting<Boolean> randomPick = new Setting<>("RandomPick", true).addToGroup(this.general);
   private final Setting<BooleanSettingGroup> items = new Setting<>("Items", new BooleanSettingGroup(true));
   private final Setting<Boolean> health = new Setting<>("Health", true).addToGroup(this.items);
   private final Setting<Boolean> regen = new Setting<>("Regen", true).addToGroup(this.items);
   private final Setting<Boolean> strength = new Setting<>("Strength", false).addToGroup(this.items);
   private final Setting<Boolean> speed = new Setting<>("Speed", false).addToGroup(this.items);
   private final Setting<Boolean> tntCarts = new Setting<>("TNTCarts", false).addToGroup(this.items);
   private final Timer timer = new Timer();
   private final Random random = new Random();
   private boolean active;
   private boolean wasInventoryOpen;

   public AutoRefill() {
      super("AutoRefill", Module.Category.PLAYER);
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (!this.active) {
            if (this.needsRefill() && this.hasValidItem()) {
               this.wasInventoryOpen = mc.field_1755 instanceof class_490;
               if (!this.wasInventoryOpen) {
                  mc.method_1507(new class_490(mc.field_1724));
               }

               this.active = true;
               this.timer.reset();
            }
         } else if (!(mc.field_1755 instanceof class_490)) {
            this.stop();
         } else if (this.timer.passedMs(this.delay.getValue().intValue())) {
            if (!this.needsRefill()) {
               this.stop();
            } else {
               int slot = this.findItem();
               if (slot == -1) {
                  this.stop();
               } else {
                  this.click(slot);
               }
            }
         }
      }
   }

   private void stop() {
      if (!this.wasInventoryOpen && mc.field_1755 instanceof class_490) {
         mc.method_1507(null);
      }

      this.active = false;
   }

   private boolean needsRefill() {
      class_1661 inv = mc.field_1724.method_31548();

      for (int i = 0; i < 9; i++) {
         class_1799 stack = (class_1799)inv.field_7547.get(i);
         if (stack.method_7960()) {
            return true;
         }

         if (this.isValidItem(stack) && stack.method_7947() < this.minStack.getValue()) {
            return true;
         }
      }

      return false;
   }

   private int findItem() {
      class_1661 inv = mc.field_1724.method_31548();
      List<Integer> slots = new ArrayList<>();

      for (int i = 9; i < 36; i++) {
         if (this.isValidItem((class_1799)inv.field_7547.get(i))) {
            slots.add(i);
         }
      }

      return slots.isEmpty() ? -1 : this.randomPick.getValue() ? slots.get(this.random.nextInt(slots.size())) : slots.getFirst();
   }

   private boolean hasValidItem() {
      return this.findItem() != -1;
   }

   private boolean isValidItem(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else {
         class_1792 item = stack.method_7909();
         if (this.tntCarts.getValue() && item == class_1802.field_8069) {
            return true;
         } else if (!(item instanceof class_1812) && !(item instanceof class_1828) && !(item instanceof class_1803)) {
            return false;
         } else {
            class_1844 contents = (class_1844)stack.method_57824(class_9334.field_49651);
            if (contents == null) {
               return false;
            } else {
               for (class_1293 inst : contents.method_57397()) {
                  class_1291 effect = (class_1291)inst.method_5579().comp_349();
                  if (this.allowedEffect(effect)) {
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }

   private boolean allowedEffect(class_1291 e) {
      if (this.health.getValue() && e == class_1294.field_5915) {
         return true;
      } else if (this.regen.getValue() && e == class_1294.field_5924) {
         return true;
      } else {
         return this.strength.getValue() && e == class_1294.field_5910 ? true : this.speed.getValue() && e == class_1294.field_5904;
      }
   }

   private void click(int slot) {
      class_1703 handler = mc.field_1724.field_7512;
      mc.field_1761.method_2906(handler.field_7763, slot, 0, class_1713.field_7790, mc.field_1724);
      this.timer.reset();
   }

   @Override
   public void onDisable() {
      this.stop();
   }
}
