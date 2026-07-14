package sky.core.modules.impl.visuals;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class ExtendedTab extends Module {
   public static SliderSetting maxplayers = new SliderSetting("Максимальное кол-во игроков", 25.0F, 20.0F, 50.0F, 5.0F);
   public static SliderSetting maxcolumns = new SliderSetting("Максимальное кол-во колонок", 4.0F, 3.0F, 5.0F, 1.0F);

   public ExtendedTab() {
      super("BetterTab", "Увеличивает отображаемое кол-во игроков в табе", Category.Visuals);
      this.addSettings(new Setting[]{maxplayers, maxcolumns});
   }
}
