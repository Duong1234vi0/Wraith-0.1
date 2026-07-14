package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class ModeSetting extends Setting {
   public String[] strings;
   public String defaultVal;

   public ModeSetting(String name, String defaultVal, String... strings) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.strings = strings;
   }

   public ModeSetting(String name, String defaultVal, Supplier visible, String... strings) {
      super(name, defaultVal);
      this.defaultVal = defaultVal;
      this.strings = strings;
      this.visible(visible);
   }

   public int getIndex() {
      int index = 0;
      String[] var2 = this.strings;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String val = var2[var4];
         if (val.equalsIgnoreCase((String)this.get())) {
            return index;
         }

         ++index;
      }

      return 0;
   }

   public boolean is(String s) {
      return ((String)this.get()).equalsIgnoreCase(s);
   }
}
