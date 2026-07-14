package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.text.ITextComponent;
import sky.core.SkyCore;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.managers.impl.FriendManager;
import sky.core.utils.misc.ServerUtil;

public class AutoAccept extends Module {
   private static final String[] TELEPORT_REQUESTS = new String[]{"телепортироваться", "has requested teleport", "просит к вам телепортироваться", "запрашивает телепорт к вам"};
   private final BooleanSetting onlyFriend = new BooleanSetting("Принимать запросы только от друзей", true);

   public AutoAccept() {
      super("AutoAccept", "Принимает запросы на телепортацию", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.onlyFriend});
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && !ServerUtil.isPvP()) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof SChatPacket) {
            SChatPacket chatPacket = (SChatPacket)var3;
            String message = getRealChatText(chatPacket.getChatComponent()).toLowerCase();
            Stream var5 = Arrays.stream(TELEPORT_REQUESTS);
            Objects.requireNonNull(message);
            if (var5.noneMatch(message::contains)) {
               return;
            }

            if ((Boolean)this.onlyFriend.get() && !this.isFriendRequest(message)) {
               return;
            }

            var10000 = mc;
            Minecraft.player.sendChatMessage("/tpaccept");
            return;
         }
      }

   }

   private static String getRealChatText(ITextComponent component) {
      if (component == null) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder();
         appendUnformatted(component, sb);
         return sb.toString();
      }
   }

   private static void appendUnformatted(ITextComponent component, StringBuilder sb) {
      sb.append(component.getUnformattedComponentText());
      Iterator var2 = component.getSiblings().iterator();

      while(var2.hasNext()) {
         ITextComponent sibling = (ITextComponent)var2.next();
         appendUnformatted(sibling, sb);
      }

   }

   private boolean isFriendRequest(String message) {
      Iterator var2 = SkyCore.getInstance().getFriendManager().getFriends().iterator();

      while(var2.hasNext()) {
         FriendManager.FriendEntry friend = (FriendManager.FriendEntry)var2.next();
         String friendName = friend.getName();
         if (friendName != null && message.contains(friendName.toLowerCase())) {
            return true;
         }
      }

      NameProtect nameProtect = (NameProtect)SkyCore.getInstance().getModuleManager().getModule(NameProtect.class);
      if (nameProtect != null && nameProtect.isEnabled() && (Boolean)NameProtect.protectfriends.get()) {
         String alias = NameProtect.getReplacementName().toLowerCase();
         return !alias.isEmpty() && message.contains(alias);
      } else {
         return false;
      }
   }
}
