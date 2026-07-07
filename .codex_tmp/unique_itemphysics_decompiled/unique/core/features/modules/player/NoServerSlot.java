package unique.core.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2735;
import net.minecraft.class_2868;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;

public class NoServerSlot extends Module {
   public NoServerSlot() {
      super("NoServerSlot", Module.Category.PLAYER);
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_2735) {
         event.cancel();
         this.sendPacket(new class_2868(mc.field_1724.method_31548().field_7545));
      }
   }
}
