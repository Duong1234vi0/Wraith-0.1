package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Blocks;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector2f;
import sky.core.events.EventPacket;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.PlayerUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.StyledFont;

public class Test extends Module {
   private final List consumables = new ArrayList();
   private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
   private final float fontSize = 16.0F;

   public Test() {
      super("TrapDuration", "Показывает время жизни расходников.", Category.Visuals);
   }

   @EventTarget
   public void event(EventPacket e) {
      if (e.isReceive()) {
         IPacket packet = e.getPacket();
         if (packet instanceof SPlaySoundEffectPacket) {
            SPlaySoundEffectPacket wrapper = (SPlaySoundEffectPacket)packet;
            if (wrapper.getSound().getName().getPath().equals("block.piston.contract") || wrapper.getSound().getName().getPath().equals("block.piston.extend") || wrapper.getSound().getName().getPath().equals("entity.wither.break_block")) {
               this.consumables.add(Pair.of(System.currentTimeMillis() + 15000L, Vector3d.copyCentered(new BlockPos(wrapper.getX(), wrapper.getY(), wrapper.getZ()))));
            }

            if (wrapper.getSound().getName().getPath().equals("block.anvil.place") || wrapper.getSound().getName().getPath().equals("entity.wither.break_block")) {
               BlockPos soundPos = new BlockPos(wrapper.getX(), wrapper.getY(), wrapper.getZ());
               long delay = 250L;
               if (this.scheduler.isShutdown() || this.scheduler.isTerminated()) {
                  this.scheduler = Executors.newSingleThreadScheduledExecutor();
               }

               this.scheduler.schedule(() -> {
                  List cubes = PlayerUtil.getCube(soundPos, 4.0F, 4.0F);
                  cubes.stream().filter((pos) -> {
                     return MathUtil.getDistance(soundPos, pos) > 2.0 && mc.world.getBlockState(pos).getBlock().equals(Blocks.COBBLESTONE);
                  }).min(Comparator.comparing((pos) -> {
                     return MathUtil.getDistance(soundPos, pos);
                  })).ifPresent((pos) -> {
                     long andesiteCount = PlayerUtil.getCube(pos, 1.0F, 1.0F).stream().filter((pos2) -> {
                        return mc.world.getBlockState(pos2).getBlock().equals(Blocks.ANDESITE);
                     }).count();
                     if (andesiteCount == 16L || andesiteCount == 9L || andesiteCount == 10L) {
                        int time = andesiteCount == 16L ? '\uea60' : 20000;
                        this.consumables.add(Pair.of(System.currentTimeMillis() + (long)time - delay, Vector3d.copyCentered(pos).add(0.0, andesiteCount == 16L ? -0.5 : 0.0, 0.0)));
                     }

                  });
               }, delay, TimeUnit.MILLISECONDS);
            }
         }
      }

   }

   @EventTarget
   public void asad(EventRender2D eventRender2D, StyledFont fonts) {
      if (ServerUtil.isConnectedToServer("funtime")) {
         Iterator iterator = this.consumables.iterator();

         while(iterator.hasNext()) {
            Pair cons = (Pair)iterator.next();
            double timeLeft = (double)((Long)cons.first - System.currentTimeMillis()) / 1000.0;
            if (timeLeft <= 0.0) {
               iterator.remove();
            } else {
               Vector2f vec2f = ProjectUtil.project2D((Vector3d)cons.second);
               String text = MathUtil.round(timeLeft, 1.0) + "с";
               int width = (int)fonts.getWidth(text);
               float posX = vec2f.x - (float)width / 2.0F;
               float posY = vec2f.y;
               float textOffsetY = 5.0F;
               RenderUtil.drawRect((double)(posX - 1.0F), (double)(posY - 1.0F), (double)(posX + (float)width + 2.0F), (double)(posY + 16.0F - 1.0F), ColorUtil.getColor(0, 0, 0, 128));
               new MatrixStack();
            }
         }

      }
   }

   public void onDisable() {
      this.consumables.clear();
      this.scheduler.shutdown();
   }
}
