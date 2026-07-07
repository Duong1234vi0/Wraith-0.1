package unique.core.features.modules.render;

import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class AspectRatio extends Module {
   public Setting<Float> ratio = new Setting<>("Ratio", 1.78F, 0.1F, 5.0F);

   public AspectRatio() {
      super("AspectRatio", Module.Category.RENDER);
   }
}
