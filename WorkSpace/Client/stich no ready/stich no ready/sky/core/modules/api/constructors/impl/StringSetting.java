package sky.core.modules.api.constructors.impl;

import sky.core.modules.api.constructors.Setting;

public class StringSetting extends Setting {
   public StringSetting(String name) {
      super(name, "");
   }

   public void setValue(String value) {
      this.set(value);
   }

   public String getValue() {
      return (String)this.get();
   }
}
