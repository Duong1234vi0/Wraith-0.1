package dev.client.modules.impl.util;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

@Environment(EnvType.CLIENT)
public class NoItemBreak extends Module implements ITickable, IUtil {
   public NoItemBreak() {
      super(new ModuleInfo("NoItemBreak", Category.UTIL, "Убирает предмет из руки до его поломки"));
   }

   public void onTick(TickEvent event) {
      ItemStack heldItem = mc.player.getMainHandStack();
      if (heldItem.isDamageable() && heldItem.getDamage() >= heldItem.getMaxDamage() - 1) {
         int bestSlot = -1;

         for(int i = 0; i < 9; ++i) {
            ItemStack stackInSlot = mc.player.getInventory().getStack(i);
            if (!stackInSlot.isEmpty() && i != mc.player.getInventory().selectedSlot && (!stackInSlot.isDamageable() || stackInSlot.getDamage() < stackInSlot.getMaxDamage() - 1)) {
               bestSlot = i;
               break;
            }
         }

         if (bestSlot != -1) {
            mc.player.getInventory().selectedSlot = bestSlot;
            mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(bestSlot));
         }
      }

   }
}
