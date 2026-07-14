package sky.core.modules.api.constructors.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class MultiBooleanSetting extends Setting {
   public MultiBooleanSetting(String name, BooleanSetting... settings) {
      super(name, Arrays.asList(settings));

      BooleanSetting setting;
      for(Iterator var3 = ((List)this.get()).iterator(); var3.hasNext(); setting.defaultVal = (Boolean)setting.get()) {
         setting = (BooleanSetting)var3.next();
      }

   }

   public MultiBooleanSetting(String name, Supplier visible, BooleanSetting... settings) {
      super(name, Arrays.asList(settings));

      BooleanSetting setting;
      for(Iterator var4 = ((List)this.get()).iterator(); var4.hasNext(); setting.defaultVal = (Boolean)setting.get()) {
         setting = (BooleanSetting)var4.next();
      }

      this.visible(visible);
   }

   public Boolean is(String settingName) {
      return (Boolean)((List)this.get()).stream().filter((s) -> {
         return s.getName().equalsIgnoreCase(settingName);
      }).findFirst().map(Setting::get).orElse((Object)null);
   }

   public BooleanSetting getIndex(int index) {
      List settings = (List)this.get();
      if (index >= 0 && index < settings.size()) {
         return (BooleanSetting)settings.get(index);
      } else {
         throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + settings.size());
      }
   }
}
