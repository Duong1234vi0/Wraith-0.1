package dev.client.modules.impl.util;

import dev.client.event.classes.DropItemEvent;
import dev.client.event.interfaces.IDropable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LockSlot extends Module implements IDropable, IUtil {
   private final MultiBoxSetting lockSlots = ((MultiBoxSetting)(new MultiBoxSetting()).name("Lock Slot")).booleanSettings(((BooleanSetting)(new BooleanSetting()).name("Slot 1")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 2")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 3")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 4")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 5")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 6")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 7")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 8")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Slot 9")).value(false));

   public LockSlot() {
      super(new ModuleInfo("LockSlot", Category.UTIL, "Не позволяет выбрасывать вещи из выбранных слотов хотбара"));
      this.addSetting(new Setting[]{this.lockSlots});
   }

   public void onDrop(DropItemEvent event) {
      int currentSlot = mc.player.getInventory().selectedSlot;
      if (this.lockSlots.getValueByName("Slot " + (currentSlot + 1))) {
         event.cancel();
      }

   }
}
