package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class TextSetting extends Setting {
   public TextSetting(String name) {
      super(name, "");
   }

   public TextSetting(String name, Supplier visible) {
      super(name, "");
      this.visible(visible);
   }
}
