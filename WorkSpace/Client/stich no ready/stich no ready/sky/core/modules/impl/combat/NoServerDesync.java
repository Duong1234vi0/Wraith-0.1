package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.util.math.vector.Vector2f;
import sky.core.events.EventNoRotate;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class NoServerDesync extends Module {
   private boolean isPacketSent;
   private Vector2f rotate;

   public NoServerDesync() {
      super("NoServerDesync", "Не позволяет серверу крутить вас", Category.Combat);
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      if (this.isPacketSent) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof CPlayerPacket) {
            CPlayerPacket packet = (CPlayerPacket)var3;
            packet.setYaw(this.rotate.x);
            packet.setPitch(this.rotate.y);
            this.isPacketSent = false;
         }
      }

   }

   @EventTarget
   public void onEvent(EventNoRotate eventNoRotate) {
      this.rotate = new Vector2f(eventNoRotate.getYaw(), eventNoRotate.getPitch());
      this.isPacketSent = true;
      eventNoRotate.setCancelled(true);
   }
}
