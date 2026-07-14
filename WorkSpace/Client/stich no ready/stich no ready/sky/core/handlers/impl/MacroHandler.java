package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import sky.core.SkyCore;
import sky.core.events.EventKey;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.MacroManager;

public class MacroHandler implements Wrapper {
   @EventTarget
   public void onKey(EventKey e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && !e.isHold() && mc.world != null) {
         Iterator var2 = SkyCore.getInstance().getMacroManager().getMacros().iterator();

         while(var2.hasNext()) {
            MacroManager.MacroEntry m = (MacroManager.MacroEntry)var2.next();
            if (m.getKeyCode() == e.getKey()) {
               String cmd = m.getCommand();
               if (cmd != null && !cmd.isEmpty()) {
                  var10000 = mc;
                  Minecraft.player.sendChatMessage(cmd);
               }
            }
         }

      }
   }
}
