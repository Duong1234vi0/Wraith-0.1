package sky.core.modules.impl.miscellaneous;

import java.util.Iterator;
import sky.core.SkyCore;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.utils.managers.impl.FriendManager;

public class NameProtect extends Module {
   private static final String DEFAULT_ALIAS = "Protected";
   public static StringSetting customNickname = new StringSetting("Protected");
   public static BooleanSetting protectfriends = new BooleanSetting("Скрывать друзей", false);

   public NameProtect() {
      super("Name Protect", "Скрывает ваш ник", Category.Miscellaneous);
      customNickname.set("Protected");
      this.addSettings(new Setting[]{customNickname, protectfriends});
   }

   public static String replaceName(String name) {
      if (name != null && !name.isEmpty()) {
         String alias = getReplacementName();
         name = name.replace(mc.session.getUsername(), alias);
         FriendManager.FriendEntry friend;
         if ((Boolean)protectfriends.get()) {
            for(Iterator var2 = SkyCore.getInstance().getFriendManager().getFriends().iterator(); var2.hasNext(); name = name.replace(friend.getName(), alias)) {
               friend = (FriendManager.FriendEntry)var2.next();
            }
         }

         return name;
      } else {
         return name;
      }
   }

   public static String getReplacementName() {
      String raw = (String)customNickname.get();
      if (raw == null) {
         return "Protected";
      } else {
         String trim = raw.trim();
         return trim.isEmpty() ? "Protected" : trim;
      }
   }
}
