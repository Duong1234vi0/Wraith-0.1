package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class ColorSetting extends Setting {
   public int defaultVal;
   public boolean alphaBar;

   public ColorSetting(String name, boolean alphaBar, Integer defaultVal) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.alphaBar = alphaBar;
   }

   public ColorSetting(String name, boolean alphaBar, Integer defaultVal, Supplier visible) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.alphaBar = alphaBar;
      this.visible(visible);
   }

   public float getAlpha() {
      return (float)((Integer)this.get() >> 24 & 255);
   }
}
