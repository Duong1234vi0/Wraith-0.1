package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class BindSetting extends Setting {
   public BindSetting(String name) {
      super(name, -1);
   }

   public BindSetting(String name, Supplier visible) {
      super(name, -1);
      this.visible(visible);
   }
}
