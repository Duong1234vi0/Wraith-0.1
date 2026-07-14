package dev.client.modules.impl.player;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;

@Environment(EnvType.CLIENT)
public class ItemSwapFix extends Module implements IReceivePacketable, IUtil {
   public ItemSwapFix() {
      super(new ModuleInfo("ItemSwapFix", Category.PLAYER, "Отменяет перемещение предметов сервером"));
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      if (receivePacketEvent.getPacket() instanceof UpdateSelectedSlotS2CPacket && mc.world != null && mc.player != null) {
         receivePacketEvent.cancel();
         if (mc.player.networkHandler != null) {
            mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
         }
      }

   }
}
