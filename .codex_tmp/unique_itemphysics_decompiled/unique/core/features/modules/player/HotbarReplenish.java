package unique.core.features.modules.player;

import net.minecraft.class_1713;
import net.minecraft.class_1799;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;

public class HotbarReplenish extends Module {
   private final Setting<HotbarReplenish.Mode> mode = new Setting<>("Mode", HotbarReplenish.Mode.SWAP);
   private final Setting<Integer> delay = new Setting<>("Delay", 2, 0, 10);
   private final Setting<Integer> refillThr = new Setting<>("Threshold", 16, 1, 63);
   private final Setting<Integer> refillSmallThr = new Setting<>("PearlsThreshold", 4, 1, 15);
   private final Timer timer = new Timer();

   public HotbarReplenish() {
      super("HotbarReplenish", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1755 == null) {
         if (this.timer.passedMs(this.delay.getValue() * 1000)) {
            for (int i = 0; i < 9; i++) {
               if (this.need(i)) {
                  this.timer.reset();
                  return;
               }
            }
         }
      }
   }

   private boolean need(int slot) {
      class_1799 stack = mc.field_1724.method_31548().method_5438(slot);
      if (!stack.method_7960() && stack.method_7946()) {
         if (stack.method_7914() == 16 && stack.method_7947() > this.refillSmallThr.getValue()) {
            return false;
         } else if (stack.method_7914() == 64 && stack.method_7947() > this.refillThr.getValue()) {
            return false;
         } else {
            for (int i = 9; i < 36; i++) {
               class_1799 item = mc.field_1724.method_31548().method_5438(i);
               if (!item.method_7960() && this.canMerge(stack, item)) {
                  boolean swap = this.mode.is(HotbarReplenish.Mode.QUICK_MOVE);
                  clickSlot(i, swap ? slot : 0, swap ? class_1713.field_7791 : class_1713.field_7794);
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private boolean canMerge(class_1799 source, class_1799 stack) {
      return source.method_7909() == stack.method_7909() && source.method_7964().equals(stack.method_7964());
   }

   private static enum Mode {
      QUICK_MOVE,
      SWAP;
   }
}
