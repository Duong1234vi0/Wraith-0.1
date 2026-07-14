package dev.client.modules.impl.combat;

import dev.client.WildClient;
import dev.client.event.classes.SendPacketEvent;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.mixins.other.IPlayerInteractEntityC2SPacketMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

@Environment(EnvType.CLIENT)
public class NoFriendDamage extends Module implements ISendPacketable, IUtil {
   public NoFriendDamage() {
      super(new ModuleInfo("NoFriendDamage", Category.COMBAT, "Отменяет урон по клиентским друзьям"));
   }

   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      Packet var3 = sendPacketEvent.getPacket();
      if (var3 instanceof PlayerInteractEntityC2SPacket packet) {
         if (mc.world != null && WildClient.INSTANCE.getFriendManager().isFriend(mc.world.getEntityById(((IPlayerInteractEntityC2SPacketMixin)packet).getId()).getNameForScoreboard())) {
            sendPacketEvent.cancel();
         }
      }

   }
}
