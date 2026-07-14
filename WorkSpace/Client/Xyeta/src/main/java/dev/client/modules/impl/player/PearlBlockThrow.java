package dev.client.modules.impl.player;

import dev.client.event.classes.SendPacketEvent;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.mixins.other.IPlayerInteractBlockC2SPacketMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Hand;

@Environment(EnvType.CLIENT)
public class PearlBlockThrow extends Module implements ISendPacketable, IUtil {
   public PearlBlockThrow() {
      super(new ModuleInfo("PearlBlockThrow", Category.PLAYER, "NoDesc"));
   }

   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      Packet var3 = sendPacketEvent.getPacket();
      if (var3 instanceof PlayerInteractBlockC2SPacket p) {
         if (mc.player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
            ((IPlayerInteractBlockC2SPacketMixin)p).setHand(Hand.OFF_HAND);
         }
      }

   }
}
