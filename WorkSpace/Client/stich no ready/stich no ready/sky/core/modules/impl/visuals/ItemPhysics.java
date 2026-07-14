package sky.core.modules.impl.visuals;

import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;

public class ItemPhysics extends Module {
   public static BooleanSetting size = new BooleanSetting("Уменьшить предметы", false);

   public ItemPhysics() {
      super("Item Physics", "Добавляет физику предметам на земле", Category.Visuals);
      this.addSettings(new Setting[]{size});
   }
}
