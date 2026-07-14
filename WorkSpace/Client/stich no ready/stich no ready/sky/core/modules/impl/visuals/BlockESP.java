package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mods.baritone.utils.IRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.managers.impl.BlockESPManager;

public class BlockESP extends Module {
   private final Map cachedColorToBoxes = new HashMap();
   private int lastEntriesHash = 0;
   private int lastChunkX = Integer.MIN_VALUE;
   private int lastChunkZ = Integer.MIN_VALUE;
   private int lastRange = -1;

   public BlockESP() {
      super("BlockESP", "Подсветка выбранных блоков", Category.Visuals);
   }

   @EventTarget
   public void onRender(EventRender3D eventRender3D) {
      if (mc.world != null) {
         BlockESPManager manager = SkyCore.getInstance().getBlockESPManager();
         List entries = manager.getBlocks();
         int range = 48;
         Minecraft var10000 = mc;
         BlockPos playerPos = Minecraft.player.getPosition();
         int chunkX = playerPos.getX() >> 4;
         int chunkZ = playerPos.getZ() >> 4;
         int listHash = 1;

         BlockESPManager.BlockEntry e;
         for(Iterator var9 = entries.iterator(); var9.hasNext(); listHash = 31 * listHash + e.getColor()) {
            e = (BlockESPManager.BlockEntry)var9.next();
            String n = e.getBlock();
            listHash = 31 * listHash + (n == null ? 0 : n.trim().toLowerCase(Locale.ROOT).hashCode());
         }

         boolean needRebuild = chunkX != this.lastChunkX || chunkZ != this.lastChunkZ || listHash != this.lastEntriesHash || range != this.lastRange;
         if (needRebuild) {
            this.rebuildCache(entries, range, playerPos);
            this.lastEntriesHash = listHash;
            this.lastChunkX = chunkX;
            this.lastChunkZ = chunkZ;
            this.lastRange = range;
         }

         MatrixStack stack = new MatrixStack();
         Iterator var19 = this.cachedColorToBoxes.entrySet().iterator();

         while(var19.hasNext()) {
            Map.Entry e = (Map.Entry)var19.next();
            int c = (Integer)e.getKey();
            IRenderer.startLines(new Color(c >> 16 & 255, c >> 8 & 255, c & 255), 2.5F, true);
            List list = (List)e.getValue();
            list.removeIf((px) -> {
               return mc.world.isAirBlock(px);
            });
            Iterator var15 = list.iterator();

            while(var15.hasNext()) {
               BlockPos p = (BlockPos)var15.next();
               IRenderer.emitAABB(stack, (new AxisAlignedBB(p, p.add(1, 1, 1))).grow(0.002));
            }

            IRenderer.endLines(true);
         }

      }
   }

   private void rebuildCache(List entries, int range, BlockPos playerPos) {
      this.cachedColorToBoxes.clear();
      Map colorByBlock = new HashMap();
      Iterator var5 = entries.iterator();

      while(var5.hasNext()) {
         BlockESPManager.BlockEntry entry = (BlockESPManager.BlockEntry)var5.next();
         String s = entry.getBlock().trim().toLowerCase(Locale.ROOT);
         if (!s.contains(":")) {
            s = "minecraft:" + s;
         }

         ResourceLocation rl = new ResourceLocation(s);
         Registry.BLOCK.getOptional(rl).ifPresent((b) -> {
            colorByBlock.put(b, entry.getColor());
         });
      }

      int px = playerPos.getX();
      int py = playerPos.getY();
      int pz = playerPos.getZ();
      int minY = Math.max(0, py - range);
      int maxY = Math.min(255, py + range);
      BlockPos.Mutable pos = new BlockPos.Mutable();

      for(int x = px - range; x <= px + range; ++x) {
         for(int y = minY; y <= maxY; ++y) {
            for(int z = pz - range; z <= pz + range; ++z) {
               pos.setPos(x, y, z);
               if (mc.world.isBlockPresent(pos)) {
                  BlockState state = mc.world.getBlockState(pos);
                  if (!state.isAir()) {
                     Block block = state.getBlock();
                     Integer color = (Integer)colorByBlock.get(block);
                     if (color != null) {
                        ((List)this.cachedColorToBoxes.computeIfAbsent(color, (k) -> {
                           return new ArrayList();
                        })).add(pos.toImmutable());
                     }
                  }
               }
            }
         }
      }

   }
}
