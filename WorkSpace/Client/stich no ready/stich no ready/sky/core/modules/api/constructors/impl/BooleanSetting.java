package sky.core.modules.api.constructors.impl;

import java.util.function.Supplier;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;

public class BooleanSetting extends Setting {
   public boolean defaultVal;
   private int bind = -100;
   private Module.ToggleMode toggleMode;
   private boolean Keybindvisible;

   public BooleanSetting(String name, Boolean defaultVal) {
      super(name, defaultVal);
      this.toggleMode = Module.ToggleMode.TOGGLE;
      this.Keybindvisible = true;
      this.defaultVal = defaultVal;
   }

   public BooleanSetting(String name, Boolean defaultVal, Supplier visible) {
      super(name, defaultVal);
      this.toggleMode = Module.ToggleMode.TOGGLE;
      this.Keybindvisible = true;
      this.defaultVal = defaultVal;
      this.visible(visible);
   }

   public void setDefaultVal(boolean defaultVal) {
      this.defaultVal = defaultVal;
   }

   public void setBind(int bind) {
      this.bind = bind;
   }

   public void setToggleMode(Module.ToggleMode toggleMode) {
      this.toggleMode = toggleMode;
   }

   public void setKeybindvisible(boolean Keybindvisible) {
      this.Keybindvisible = Keybindvisible;
   }

   public boolean isDefaultVal() {
      return this.defaultVal;
   }

   public int getBind() {
      return this.bind;
   }

   public Module.ToggleMode getToggleMode() {
      return this.toggleMode;
   }

   public boolean isKeybindvisible() {
      return this.Keybindvisible;
   }
}
