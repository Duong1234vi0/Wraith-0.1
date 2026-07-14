package dev.client.modules.impl.util;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.mixins.other.IPlayerPosition;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

@Environment(EnvType.CLIENT)
public class NoRotation extends Module implements IReceivePacketable, IUtil {
   public NoRotation() {
      super(new ModuleInfo("NoRotation", Category.UTIL, "Отменяет поворот камеры сервером"));
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      if (mc.player != null && mc.world != null) {
         Packet var3 = receivePacketEvent.getPacket();
         if (var3 instanceof PlayerPositionLookS2CPacket) {
            PlayerPositionLookS2CPacket pac = (PlayerPositionLookS2CPacket)var3;
         }

      }
   }
}
