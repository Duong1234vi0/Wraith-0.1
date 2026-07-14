package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.network.play.client.CPlayerDiggingPacket.Action;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import other.bot.Bot;
import other.bot.BotManager;
import sky.core.events.EventBlockDamage;
import sky.core.events.EventGameUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class InstantRebreak extends Module {
   private int sinceLastDamage;
   private final BlockPos.Mutable blockPos = new BlockPos.Mutable(0, Integer.MIN_VALUE, 0);
   private int ticks;
   private Direction direction;

   public InstantRebreak() {
      super("AutoLes", "Фармит на /warp les через команду .bot", Category.Miscellaneous);
   }

   @Native
   public void onEnable() {
      this.ticks = 0;
      this.sinceLastDamage = 999;
      this.blockPos.setPos(0, Integer.MIN_VALUE, 0);
      this.direction = null;
      super.onEnable();
   }

   @EventTarget
   @Native
   public void dreak(EventBlockDamage e) {
      this.direction = e.getDirection();
      this.blockPos.setPos(e.getPos());
   }

   @EventTarget
   @Native
   public void tick(EventGameUpdate e) {
      if (this.ticks >= 0) {
         Iterator var2 = BotManager.autoLesBots.iterator();

         while(var2.hasNext()) {
            Bot bot = (Bot)var2.next();
            if (bot != null && bot.connection != null && bot.connection.bot != null && bot.connection.getWorld() != null && this.shouldMineBot(bot)) {
               Direction dir = this.direction == null ? Direction.UP : this.direction;
               lookAtBlock(bot.connection.bot, this.blockPos);
               bot.connection.sendPacket(new CPlayerDiggingPacket(Action.STOP_DESTROY_BLOCK, this.blockPos, dir));
               bot.connection.sendPacket(new CAnimateHandPacket(Hand.MAIN_HAND));
            }
         }
      } else {
         ++this.ticks;
      }

   }

   @Native
   private boolean shouldMineBot(Bot bot) {
      if (bot != null && bot.connection != null && bot.connection.bot != null && bot.connection.getWorld() != null) {
         if (this.blockPos.getY() == Integer.MIN_VALUE) {
            return false;
         } else if (this.blockPos.getY() >= 0 && this.blockPos.getY() < 256) {
            float reach = bot.connection.botController != null ? bot.connection.botController.getBlockReachDistance() : 4.5F;
            double dist = bot.connection.bot.getPositionVec().distanceTo(new Vector3d((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5));
            if (dist > (double)reach) {
               return false;
            } else {
               boolean creative = bot.connection.botController != null && bot.connection.botController.isInCreativeMode();
               return canBreak(bot.connection.getWorld(), this.blockPos, creative);
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Native
   private static void lookAtBlock(PlayerEntity player, BlockPos pos) {
      Vector3d eyes = player.getEyePosition(1.0F);
      Vector3d target = new Vector3d((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
      Vector3d diff = target.subtract(eyes);
      double diffXZ = Math.sqrt(diff.x * diff.x + diff.z * diff.z);
      float yaw = (float)(MathHelper.atan2(diff.z, diff.x) * 57.29577951308232) - 90.0F;
      float pitch = (float)(-(MathHelper.atan2(diff.y, diffXZ) * 57.29577951308232));
      yaw = MathHelper.wrapDegrees(yaw);
      pitch = MathHelper.clamp(pitch, -90.0F, 90.0F);
      player.rotationYaw = yaw;
      player.rotationPitch = pitch;
      player.rotationYawHead = yaw;
      player.renderYawOffset = yaw;
   }

   @Native
   private static boolean canBreak(World world, BlockPos blockPos, boolean creative) {
      if (world == null) {
         return false;
      } else {
         BlockState state = world.getBlockState(blockPos);
         if (state == null) {
            return false;
         } else if (!creative && state.getBlockHardness(world, blockPos) < 0.0F) {
            return false;
         } else {
            return state.getShape(world, blockPos) != VoxelShapes.empty();
         }
      }
   }
}
