package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CResourcePackStatusPacket;
import net.minecraft.network.play.client.CResourcePackStatusPacket.Action;
import net.minecraft.network.play.server.SSendResourcePackPacket;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class SRPSpoofer extends Module {
   public SRPSpoofer() {
      super("SRPSpoof", "Подменяет данные о том, что установлен серверный ресурс-пак", Category.Miscellaneous);
   }

   @EventTarget
   public void onPacketReceive(EventPacket e) {
      if (e.getPacket() instanceof SSendResourcePackPacket && e.isReceive()) {
         Minecraft var10000 = mc;
         Minecraft.player.connection.sendPacket(new CResourcePackStatusPacket(Action.ACCEPTED));
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CResourcePackStatusPacket(Action.SUCCESSFULLY_LOADED));
         e.setCancelled(true);
      }

   }
}
