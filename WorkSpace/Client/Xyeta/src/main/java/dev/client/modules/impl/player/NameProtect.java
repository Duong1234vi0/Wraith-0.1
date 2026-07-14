package dev.client.modules.impl.player;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.impl.StringSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NameProtect extends Module implements IUtil {
   private final StringSetting name = ((StringSetting)(new StringSetting()).name("Name")).value("Wild");

   public NameProtect() {
      super(new ModuleInfo("NameProtect", Category.PLAYER, "Скрывает реальный ник игрока"));
   }

   public String replace(String text) {
      String out = text;
      if (mc.player != null && this.isEnabled()) {
         out = text.replaceAll(mc.player.getNameForScoreboard(), this.name.getValue());
      }

      return out;
   }
}
