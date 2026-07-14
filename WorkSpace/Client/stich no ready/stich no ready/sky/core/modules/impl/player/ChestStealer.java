package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.HopperContainer;
import net.minecraft.inventory.container.Slot;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.math.TimeUtil;

public class ChestStealer extends Module {
   private final SliderSetting stealDelay = new SliderSetting("Задержка", 100.0F, 0.0F, 1000.0F, 1.0F);
   private final BooleanSetting randomize = new BooleanSetting("Рандомизация", false);
   private final TimeUtil timer = new TimeUtil();

   public ChestStealer() {
      super("ChestStealer", "Автоматически открывает сундуки и забирает из них предметы", Category.Player);
      this.addSettings(new Setting[]{this.stealDelay, this.randomize});
   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      Container openContainer = Minecraft.player.openContainer;
      if (openContainer instanceof ChestContainer || openContainer instanceof HopperContainer) {
         List slots = openContainer.inventorySlots;
         if (this.timer.hasTimeElapsed(((Float)this.stealDelay.get()).longValue())) {
            this.findValidItemCustom(slots).ifPresent((slot) -> {
               Minecraft var10000 = mc;
               if (Minecraft.player.openContainer == openContainer) {
                  Minecraft var10005 = mc;
                  mc.playerController.windowClick(openContainer.windowId, slot.slotNumber, 0, ClickType.QUICK_MOVE, Minecraft.player);
                  this.timer.reset();
               }

            });
         }
      }

   }

   private Optional findValidItemCustom(List slots) {
      int var10000 = slots.size();
      Minecraft var10001 = mc;
      int containerSlotCount = var10000 - Minecraft.player.inventory.mainInventory.size();
      List containerSlots = slots.subList(0, containerSlotCount);
      List validSlots = containerSlots.stream().filter((slot) -> {
         return !slot.getStack().isEmpty();
      }).filter((slot) -> {
         Minecraft var10000 = mc;
         return !Minecraft.player.getCooldownTracker().hasCooldown(slot.getStack().getItem());
      }).toList();
      if (validSlots.isEmpty()) {
         return Optional.empty();
      } else {
         return (Boolean)this.randomize.get() ? Optional.of((Slot)validSlots.get(ThreadLocalRandom.current().nextInt(validSlots.size()))) : validSlots.stream().findFirst();
      }
   }
}
