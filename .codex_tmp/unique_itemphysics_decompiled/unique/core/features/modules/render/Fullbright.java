package unique.core.features.modules.render;

import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class Fullbright extends Module {
   public static Setting<Float> minBright = new Setting<>("MinBright", 0.5F, 0.0F, 1.0F);

   public Fullbright() {
      super("Fullbright", Module.Category.RENDER);
   }
}
