package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import sky.core.handlers.impl.ReallyWorldJoinHandler;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class AutoJoiner extends Module {
   private final SliderSetting grief = new SliderSetting("Grief", 5.0F, 1.0F, 76.0F, 1.0F);

   public AutoJoiner() {
      super("Auto Joiner", "Автоматически заходит на сервер", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.grief});
   }

   @Native
   public void onEnable() {
      ReallyWorldJoinHandler.setManualGrief(((Float)this.grief.get()).intValue());
      ReallyWorldJoinHandler.startRejoin();
      super.onEnable();
   }

   @Native
   public void onDisable() {
      ReallyWorldJoinHandler.disable();
      super.onDisable();
   }
}
