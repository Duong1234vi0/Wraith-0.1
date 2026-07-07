package unique.core.features.modules.misc;

import java.util.ArrayList;
import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2611;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.render.StorageEsp;
import unique.core.setting.Setting;
import unique.core.setting.impl.ItemSelectSetting;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;

public class ChestStealer extends Module {
   public final Setting<ItemSelectSetting> items = new Setting<>("Items", new ItemSelectSetting(new ArrayList<>()));
   private final Setting<Integer> delay = new Setting<>("Delay", 100, 0, 1000);
   private final Setting<Boolean> random = new Setting<>("Random", false);
   private final Setting<Boolean> close = new Setting<>("Close", false);
   private final Setting<Boolean> autoMyst = new Setting<>("AutoMyst", false);
   private final Setting<ChestStealer.Sort> sort = new Setting<>("Sort", ChestStealer.Sort.None);
   private final Timer autoMystDelay = new Timer();
   private final Timer timer = new Timer();
   private final Random rnd = new Random();

   public ChestStealer() {
      super("ChestStealer", Module.Category.MISC);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (mc.field_1724.field_7512 instanceof class_1707 chest) {
         for (int i = 0; i < chest.method_7629().method_5439(); i++) {
            class_1735 slot = chest.method_7611(i);
            if (slot.method_7681()
               && this.isAllowed(slot.method_7677())
               && this.timer
                  .every(this.delay.getValue() + (this.random.getValue() && this.delay.getValue() != 0 ? this.rnd.nextInt(this.delay.getValue()) : 0))
               && !mc.field_1755.method_25440().getString().contains("Аукцион")
               && !mc.field_1755.method_25440().getString().contains("покупки")) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7794, mc.field_1724);
               this.autoMystDelay.reset();
            }
         }

         if (this.isContainerEmpty(chest) && this.close.getValue()) {
            mc.field_1724.method_7346();
         }
      }
   }

   @EventHandler
   public void onPlayerUpdate(PlayerUpdateEvent event) {
      if (this.autoMyst.getValue() && mc.field_1755 == null && this.autoMystDelay.passedMs(3000L)) {
         for (class_2586 be : StorageEsp.getBlockEntities()) {
            if (be instanceof class_2611 && !(mc.field_1724.method_5707(be.method_11016().method_46558()) > 39.0)) {
               mc.field_1761
                  .method_2896(
                     mc.field_1724,
                     class_1268.field_5808,
                     new class_3965(
                        be.method_11016().method_46558().method_1031(MathUtility.random(-0.4, 0.4), 0.375, MathUtility.random(-0.4, 0.4)),
                        class_2350.field_11036,
                        be.method_11016(),
                        false
                     )
                  );
               mc.field_1724.method_6104(class_1268.field_5808);
               break;
            }
         }
      }
   }

   private boolean isAllowed(class_1799 stack) {
      boolean allowed = this.items.getValue().contains(stack.method_7909().method_7876().replace("block.minecraft.", "").replace("item.minecraft.", ""));

      return switch ((ChestStealer.Sort)this.sort.getValue()) {
         case None -> true;
         case WhiteList -> allowed;
         default -> !allowed;
      };
   }

   private boolean isContainerEmpty(class_1707 container) {
      for (int i = 0; i < (container.method_7629().method_5439() == 90 ? 54 : 27); i++) {
         if (container.method_7611(i).method_7681()) {
            return false;
         }
      }

      return true;
   }

   private static enum Sort {
      None,
      WhiteList,
      BlackList;
   }
}
