package sky.core.modules.impl.player;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class ItemScroller extends Module {
   public SliderSetting delay = new SliderSetting("Задержка", 8.0F, 0.0F, 10.0F, 1.0F);

   public ItemScroller() {
      super("Item Scroller", "Позволяет быстро перекладывать вещи в инвенторе", Category.Player);
      this.addSettings(new Setting[]{this.delay});
   }
}
