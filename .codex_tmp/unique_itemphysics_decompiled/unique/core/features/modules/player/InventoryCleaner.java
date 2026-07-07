package unique.core.features.modules.player;

import java.util.ArrayList;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2815;
import net.minecraft.class_4587;
import net.minecraft.class_490;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.ItemSelectSetting;
import unique.core.utility.Timer;

public class InventoryCleaner extends Module {
   public final Setting<ItemSelectSetting> items = new Setting<>("Items", new ItemSelectSetting(new ArrayList<>()));
   private final Setting<InventoryCleaner.DropWhen> dropWhen = new Setting<>("DropWhen", InventoryCleaner.DropWhen.NotInInventory);
   private final Setting<Integer> delay = new Setting<>("Delay", 50, 0, 500);
   private final Setting<Boolean> cleanChests = new Setting<>("CleanChests", false);
   private final Timer delayTimer = new Timer();
   private boolean dirty;

   public InventoryCleaner() {
      super("InventoryCleaner", Module.Category.PLAYER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      boolean inInv = mc.field_1755 instanceof class_490;
      if (mc.field_1724.field_7512 instanceof class_1707 chest && this.cleanChests.getValue()) {
         for (int i = 0; i < chest.method_7629().method_5439(); i++) {
            class_1735 slot = chest.method_7611(i);
            if (slot.method_7681()
               && this.dropThisShit(slot.method_7677())
               && !mc.field_1755.method_25440().getString().contains("Аукцион")
               && !mc.field_1755.method_25440().getString().contains("покупки")
               && this.delayTimer.every(this.delay.getValue().intValue())) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 1, class_1713.field_7795, mc.field_1724);
               this.dirty = true;
            }
         }
      }

      if (this.dropWhen.getValue() != InventoryCleaner.DropWhen.Inventory || inInv) {
         if (this.dropWhen.getValue() != InventoryCleaner.DropWhen.NotInInventory || !inInv) {
            for (int slot = 0; slot < 36; slot++) {
               class_1799 itemFromslot = mc.field_1724.method_31548().method_5438(slot);
               if (this.dropThisShit(itemFromslot)) {
                  this.drop(slot);
               }
            }

            if (this.dirty && this.delayTimer.passedMs(this.delay.getValue() + 100)) {
               this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               this.debug("after click cleaning...");
               this.dirty = false;
            }
         }
      }
   }

   private void drop(int slot) {
      if (this.delayTimer.every(this.delay.getValue().intValue())) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slot < 9 ? slot + 36 : slot, 1, class_1713.field_7795, mc.field_1724);
         this.dirty = true;
      }
   }

   private boolean dropThisShit(class_1799 stack) {
      return this.items.getValue().getItemsById().contains(stack.method_7909().method_7876().replace("block.minecraft.", "").replace("item.minecraft.", ""));
   }

   public static enum DropWhen {
      Inventory,
      Always,
      NotInInventory;
   }
}
