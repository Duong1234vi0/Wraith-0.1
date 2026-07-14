package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerPacket;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class NoFall extends Module {
   public NoFall() {
      super("NoFall", "Уберает урон от падения", Category.Player);
   }

   @EventTarget
   public void onPacket(EventPacket e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (e.getPacket() instanceof CPlayerPacket) {
            CPlayerPacket packet = (CPlayerPacket)e.getPacket();
            var10000 = mc;
            if (Minecraft.player.fallDistance > 3.0F) {
               var10000 = mc;
               if (!Minecraft.player.isOnGround() && this.isBlockUnder()) {
                  packet.setOnGround(true);
                  var10000 = mc;
                  Minecraft.player.fallDistance = 0.0F;
               }
            }
         }

      }
   }

   private boolean isBlockUnder() {
      Minecraft var10000 = mc;
      if (Minecraft.player.getPosY() < 0.0) {
         return false;
      } else {
         int offset = 0;

         while(true) {
            Minecraft var10001 = mc;
            if (offset >= (int)Minecraft.player.getPosY()) {
               return false;
            }

            var10001 = mc;
            if (!mc.world.isAirBlock(Minecraft.player.getPosition().down(offset))) {
               return true;
            }

            offset += 2;
         }
      }
   }
}
