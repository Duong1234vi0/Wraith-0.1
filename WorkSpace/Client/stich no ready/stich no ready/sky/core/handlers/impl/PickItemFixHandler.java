package sky.core.handlers.impl;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.server.SHeldItemChangePacket;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.utils.Wrapper;

public class PickItemFixHandler {
   public static boolean fixActive = false;
   public static int tickCounter = 0;

   public static void activateFix() {
      fixActive = true;
      tickCounter = 0;
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      if (fixActive) {
         ++tickCounter;
         if (tickCounter >= 6) {
            fixActive = false;
            tickCounter = 0;
         }
      }

   }

   @EventTarget
   public void onPacketEvent(EventPacket e) {
      if (fixActive) {
         Minecraft var10000 = Wrapper.mc;
         if (Minecraft.player != null && Wrapper.mc.world != null) {
            IPacket var3 = e.getPacket();
            if (var3 instanceof SHeldItemChangePacket) {
               SHeldItemChangePacket fix = (SHeldItemChangePacket)var3;
               if (e.isReceive()) {
                  int var5 = fix.getHeldItemHotbarIndex();
                  Minecraft var10001 = Wrapper.mc;
                  if (var5 != Minecraft.player.inventory.currentItem) {
                     var10000 = Wrapper.mc;
                     if (Minecraft.player.inventory.currentItem >= 8) {
                        var10000 = Wrapper.mc;
                        var5 = Minecraft.player.inventory.currentItem - 1;
                     } else {
                        var10000 = Wrapper.mc;
                        var5 = Minecraft.player.inventory.currentItem + 1;
                     }

                     int newSlot = MathHelper.clamp(var5, 0, 8);
                     var10000 = Wrapper.mc;
                     Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(newSlot));
                     var10000 = Wrapper.mc;
                     Minecraft var10003 = Wrapper.mc;
                     Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
                     e.setCancelled(true);
                  }
               }
            }
         }
      }

   }

   public static boolean isFixActive() {
      return fixActive;
   }
}
