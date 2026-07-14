package dev.client.modules.impl.movement;

import dev.client.event.classes.SendPacketEvent;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Environment(EnvType.CLIENT)
public class AirStuck extends Module implements ISendPacketable, IUtil {
   public AirStuck() {
      super(new ModuleInfo("AirStuck", Category.MOVEMENT, "Вызывает зависание игрока в воздухе"));
   }

   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      if (mc.player != null && mc.world != null) {
         mc.player.setVelocity((double)0.0F, (double)0.0F, (double)0.0F);
         mc.player.setMovementSpeed(0.0F);
         Packet var10000 = sendPacketEvent.getPacket();
         Objects.requireNonNull(var10000);
         Packet var2 = var10000;
         if (var2 instanceof PlayerMoveC2SPacket) {
            sendPacketEvent.cancel();
         }
      }
   }
}
