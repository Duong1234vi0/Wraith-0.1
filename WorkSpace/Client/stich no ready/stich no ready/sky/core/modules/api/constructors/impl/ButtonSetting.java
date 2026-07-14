package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class ButtonSetting extends Setting {
   public boolean defaultVal;
   public final String textOn;
   public final String textOff;

   public ButtonSetting(String name, Boolean defaultVal, String textOn, String textOff) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.textOff = textOff;
      this.textOn = textOn;
   }

   public ButtonSetting(String name, Boolean defaultVal, String textOn, String textOff, Supplier visible) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.textOff = textOff;
      this.textOn = textOn;
      this.visible(visible);
   }

   public boolean isDefaultVal() {
      return this.defaultVal;
   }

   public String getTextOn() {
      return this.textOn;
   }

   public String getTextOff() {
      return this.textOff;
   }
}
