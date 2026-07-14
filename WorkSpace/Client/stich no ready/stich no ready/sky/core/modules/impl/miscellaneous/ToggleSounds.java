package sky.core.modules.impl.miscellaneous;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class ToggleSounds extends Module {
   public static ModeSetting type = new ModeSetting("Тип звука", "Тип 1", new String[]{"1", "2", "3", "4"});
   public static SliderSetting volume = new SliderSetting("Громкость", 75.0F, 0.0F, 100.0F, 1.0F);

   public ToggleSounds() {
      super("ClientSounds", "ЗВУКИ!!!!!!!!!!!!!!!!!!!", Category.Miscellaneous);
      this.addSettings(new Setting[]{type, volume});
   }

   public static String getSoundFile(boolean isEnable) {
      String var10000;
      switch ((String)type.get()) {
         case "1":
            var10000 = isEnable ? "enabled0" : "disabled0";
            break;
         case "2":
            var10000 = isEnable ? "enabled3" : "disabled3";
            break;
         case "3":
            var10000 = isEnable ? "MODULE_ON3" : "MODULE_OFF3";
            break;
         case "4":
            var10000 = isEnable ? "enabled2" : "disabled2";
            break;
         default:
            var10000 = null;
      }

      return var10000;
   }
}
