package unique.core.features.modules.misc;

import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class NameProtect extends Module {
   public static Setting<Boolean> hideFriends = new Setting<>("Friends", true);

   public NameProtect() {
      super("NameProtect", Module.Category.MISC);
   }

   public static String getCustomName(String name) {
      if (ModuleManager.nameProtect == null || !ModuleManager.nameProtect.isEnabled()) {
         return name;
      } else if (name.equalsIgnoreCase("SD_AkiraExploit")) {
         return "Admin -> Secret";
      } else if (name.equals(mc.method_1548().method_1676())) {
         return "User -> Universe";
      } else {
         return hideFriends.getValue() && Managers.FRIEND.isFriend(name) ? "User -> Universe" : name;
      }
   }
}
