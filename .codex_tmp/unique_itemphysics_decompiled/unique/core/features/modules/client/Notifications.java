package unique.core.features.modules.client;

import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public final class Notifications extends Module {
   public final Setting<Notifications.Mode> mode = new Setting<>("Mode", Notifications.Mode.CrossHair);

   public Notifications() {
      super("Notifications", Module.Category.CLIENT);
   }

   public static enum Mode {
      Default,
      CrossHair,
      Text;
   }
}
