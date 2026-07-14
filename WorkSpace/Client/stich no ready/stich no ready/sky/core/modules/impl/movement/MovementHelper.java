package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventInput;
import sky.core.events.EventMotion;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.player.MoveUtil;

public class MovementHelper extends Module {
   private final BooleanSetting minijump = new BooleanSetting("Незаметные прыжки на ступеньках", true);
   private final BooleanSetting jump = new BooleanSetting("Прыгать на краю блока", false);
   private final BooleanSetting shift = new BooleanSetting("Приседать на краю блока", false);
   private boolean useshift;

   public MovementHelper() {
      super("Movement Helper", "Содержит в себе вспомогательные функции для передвижения", Category.Movement);
      this.addSettings(new Setting[]{this.minijump, this.jump, this.shift});
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000;
      if ((Boolean)this.minijump.get() && MoveUtil.isMoving()) {
         var10000 = mc;
         if (!Minecraft.player.isCrouching() && this.shouldClimbBlock()) {
            var10000 = mc;
            if (Minecraft.player.isOnGround() && !mc.gameSettings.keyBindJump.isKeyDown()) {
               var10000 = mc;
               Minecraft.player.jump();
            }
         }
      }

      if ((Boolean)this.jump.get() && MoveUtil.isBlockUnder(0.001F)) {
         var10000 = mc;
         if (Minecraft.player.isOnGround()) {
            var10000 = mc;
            Minecraft.player.jump();
         }
      }

   }

   @EventTarget
   public void onUpdate(EventMotion event) {
      Minecraft var10000 = mc;
      if (Minecraft.player.isOnGround()) {
         Minecraft var10002 = mc;
         BlockPos belowPos = (new BlockPos(Minecraft.player.getPositionVec())).add(0, -1, 0);
         BlockState belowState = mc.world.getBlockState(belowPos);
         if (this.isNonSolidBlock(belowState)) {
            if (!this.useshift) {
               this.useshift = true;
            }
         } else if (this.useshift) {
            this.useshift = false;
         }
      } else if (this.useshift) {
         this.useshift = false;
      }

   }

   @EventTarget
   public void onUpdate(EventInput event) {
      if ((Boolean)this.shift.get() && !mc.gameSettings.keyBindSneak.isKeyDown()) {
         event.setSneak(this.useshift);
      }

   }

   private boolean shouldClimbBlock() {
      Minecraft var10000 = mc;
      Vector3d playerPos = Minecraft.player.getPositionVec();
      var10000 = mc;
      double yaw = Math.toRadians((double)Minecraft.player.rotationYaw);
      double x = -Math.sin(yaw);
      double z = Math.cos(yaw);
      BlockPos playerBlockPos = new BlockPos(playerPos.x, playerPos.y, playerPos.z);
      BlockPos frontPos = new BlockPos(playerPos.x + x, playerPos.y, playerPos.z + z);
      BlockPos frontUpPos = frontPos.up();
      BlockPos belowPos = playerBlockPos.down();
      boolean isOnStairsOrSlab = mc.world.getBlockState(belowPos).getBlock() instanceof StairsBlock || mc.world.getBlockState(belowPos).getBlock() instanceof SlabBlock;
      boolean isFrontClimbable = mc.world.getBlockState(frontPos).getBlock() instanceof StairsBlock || mc.world.getBlockState(frontPos).getBlock() instanceof SlabBlock || mc.world.getBlockState(frontUpPos).getBlock() instanceof StairsBlock || mc.world.getBlockState(frontUpPos).getBlock() instanceof SlabBlock;
      return isOnStairsOrSlab && isFrontClimbable;
   }

   private boolean isNonSolidBlock(BlockState state) {
      boolean var10000;
      if (!(state.getBlock() instanceof AirBlock) && state.getMaterial() != Material.TALL_PLANTS && state.getMaterial() != Material.PLANTS && state.getMaterial() != Material.WATER && state.getMaterial() != Material.LAVA && state.getBlock() != Blocks.SNOW && !(state.getBlock() instanceof CarpetBlock) && !(state.getBlock() instanceof SlabBlock)) {
         Minecraft var10004 = mc;
         if (!state.getCollisionShape(mc.world, (new BlockPos(Minecraft.player.getPositionVec())).add(0, -1, 0)).isEmpty()) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }
}
