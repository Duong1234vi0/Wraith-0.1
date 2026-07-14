package sky.core.modules.api.constructors.impl;

import com.adl.nativeprotect.Native;
import java.util.function.Supplier;
import sky.core.modules.api.constructors.Setting;

public class ClickSetting extends Setting {
   private final Runnable action;

   public ClickSetting(String name, Runnable action) {
      super(name, (Object)null);
      this.action = action;
   }

   public ClickSetting(String name, Runnable action, Supplier visible) {
      super(name, (Object)null);
      this.action = action;
      this.visible(visible);
   }

   @Native
   public void performAction() {
      if (this.action != null) {
         this.action.run();
      }

   }
}
