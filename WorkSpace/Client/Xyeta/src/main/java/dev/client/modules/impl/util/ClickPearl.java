package dev.client.modules.impl.util;

import dev.client.event.classes.ClickEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IClickaable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class ClickPearl extends Module implements ITickable, IEnableable, IUtil, IClickaable {
   private TimerUtil timerUtil = new TimerUtil();
   private final KeySetting bind = ((KeySetting)(new KeySetting()).name("Bind")).value(0);
   private static int curSlot = 0;
   private boolean changed = false;

   public ClickPearl() {
      super(new ModuleInfo("ClickPearl", Category.UTIL, "Бросает эндер жемчуг по нажатию кнопки"));
      this.addSetting(new Setting[]{this.bind});
   }

   @Compile
   public void onTick(TickEvent event) {
      if (this.changed && this.timerUtil.isReached(100L)) {
         mc.options.useKey.setPressed(false);
         mc.player.getInventory().selectedSlot = curSlot;
         this.changed = false;
      }

   }

   @Compile
   public void onEnable() {
      curSlot = mc.player.getInventory().selectedSlot;
   }

   public void onClick(ClickEvent event) {
      if (mc.currentScreen == null && event.getAction() == 1 && event.getKey() == this.bind.getValue()) {
         for(int i = 0; i < 9; ++i) {
            if (((ItemStack)mc.player.getInventory().main.get(i)).getItem() instanceof EnderPearlItem) {
               curSlot = mc.player.getInventory().selectedSlot;
               mc.player.getInventory().selectedSlot = i;
               mc.options.useKey.setPressed(true);
               this.timerUtil.reset();
               this.changed = true;
            }
         }
      }

   }
}
