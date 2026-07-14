package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItem;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.network.play.server.SChangeBlockPacket;
import net.minecraft.network.play.server.SMultiBlockChangePacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import sky.core.events.EventClickBlockRight;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class NoBlockUpdate extends Module {
   private final Set ghostBlocks = new HashSet();
   private final Map previousStates = new HashMap();

   public NoBlockUpdate() {
      super("NoBlockUpdate", "Скрывает апдейты сервера для блоков под собой", Category.Movement);
   }

   @EventTarget
   private void onClickBlock(EventClickBlockRight event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (Minecraft.player.getHeldItem(event.getHand()).getItem() instanceof BlockItem) {
            BlockRayTraceResult result = event.getResult();
            BlockPos placePos = result.getPos().offset(result.getFace());
            if (this.isBlockUnderPlayer(placePos)) {
               this.ghostBlocks.add(placePos.toImmutable());
               this.previousStates.putIfAbsent(placePos.toImmutable(), mc.world.getBlockState(placePos));
            }
         }
      }
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      IPacket var3;
      if (!event.isSend()) {
         var3 = event.getPacket();
         if (var3 instanceof SChangeBlockPacket) {
            SChangeBlockPacket packet = (SChangeBlockPacket)var3;
            if (this.ghostBlocks.contains(packet.getPos())) {
               event.setCancelled(true);
            }

            return;
         }
      }

      if (!event.isSend()) {
         var3 = event.getPacket();
         if (var3 instanceof SMultiBlockChangePacket) {
            SMultiBlockChangePacket packet = (SMultiBlockChangePacket)var3;
            boolean[] shouldCancel = new boolean[]{false};
            packet.func_244310_a((pos, state) -> {
               if (!shouldCancel[0] && this.ghostBlocks.contains(pos.toImmutable())) {
                  shouldCancel[0] = true;
               }

            });
            if (shouldCancel[0]) {
               event.setCancelled(true);
            }

            return;
         }
      }

      if (event.isSend()) {
         var3 = event.getPacket();
         if (var3 instanceof CPlayerTryUseItemOnBlockPacket) {
            CPlayerTryUseItemOnBlockPacket packet = (CPlayerTryUseItemOnBlockPacket)var3;
            BlockPos placePos = packet.func_218794_c().getPos().offset(packet.func_218794_c().getFace());
            if (this.isBlockUnderPlayer(placePos)) {
               event.setCancelled(true);
            }
         }
      }

   }

   public void onDisable() {
      if (mc.world != null) {
         Iterator var1 = this.ghostBlocks.iterator();

         while(var1.hasNext()) {
            BlockPos pos = (BlockPos)var1.next();
            BlockState state = (BlockState)this.previousStates.getOrDefault(pos, Blocks.AIR.getDefaultState());
            mc.world.setBlockState(pos, state, 3);
         }
      }

      this.ghostBlocks.clear();
      this.previousStates.clear();
      super.onDisable();
   }

   private boolean isBlockUnderPlayer(BlockPos blockPos) {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         int minY = (int)Math.floor(Minecraft.player.getPosY()) - 1;
         if (blockPos.getY() > minY) {
            return false;
         } else {
            double var7 = (double)blockPos.getX() + 0.5;
            Minecraft var10001 = mc;
            double dx = var7 - Minecraft.player.getPosX();
            var7 = (double)blockPos.getZ() + 0.5;
            var10001 = mc;
            double dz = var7 - Minecraft.player.getPosZ();
            return dx * dx + dz * dz <= 4.0;
         }
      }
   }
}
