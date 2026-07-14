package dev.client.modules.impl.util;

import dev.client.event.classes.ClickEvent;
import dev.client.event.interfaces.IClickaable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;

@Environment(EnvType.CLIENT)
public class CordDropper extends Module implements IClickaable, IUtil {
   private final KeySetting bind = ((KeySetting)(new KeySetting()).name("Bind")).value(0);

   public CordDropper() {
      super(new ModuleInfo("CordDropper", Category.UTIL, "Отправляет координаты в чат по нажатию кнопки"));
      this.addSetting(new Setting[]{this.bind});
   }

   public void onClick(ClickEvent event) {
      if (mc.currentScreen == null && event.getAction() == 1 && event.getKey() == this.bind.getValue()) {
         ClientPlayNetworkHandler var10000 = mc.getNetworkHandler();
         int var10001 = (int)mc.player.getX();
         var10000.sendChatMessage("!x: " + var10001 + " y: " + (int)mc.player.getY() + " z: " + (int)mc.player.getZ());
      }

   }
}
