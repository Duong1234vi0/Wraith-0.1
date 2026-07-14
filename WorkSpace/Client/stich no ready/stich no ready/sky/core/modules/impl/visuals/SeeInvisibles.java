package sky.core.modules.impl.visuals;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class SeeInvisibles extends Module {
   private final SliderSetting alpha = new SliderSetting("Прозрачность", 0.5F, 0.3F, 1.0F, 0.1F);
   private final BooleanSetting armorStands = new BooleanSetting("Отображать стойки брони", false);

   public SeeInvisibles() {
      super("See Invisibles", "Позволяет изменить прозрачность невидимых игроков, облегчая их обнаружение", Category.Visuals);
      this.addSettings(new Setting[]{this.alpha, this.armorStands});
   }

   public SliderSetting getAlpha() {
      return this.alpha;
   }

   public BooleanSetting getArmorStands() {
      return this.armorStands;
   }
}
