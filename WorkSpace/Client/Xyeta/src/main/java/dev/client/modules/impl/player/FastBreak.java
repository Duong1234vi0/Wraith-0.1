package dev.client.modules.impl.player;

import dev.client.event.classes.BreakEvent;
import dev.client.event.interfaces.IBreakable;
import dev.client.mixins.other.IClientPlayerInteractionManagerMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class FastBreak extends Module implements IBreakable, IUtil {
   boolean breaking = false;
   private final TimerUtil timerUtil = new TimerUtil();
   BlockPos blockPos = null;

   public FastBreak() {
      super(new ModuleInfo("FastBreak", Category.PLAYER, "Убирает задержку на ломание блоков"));
   }

   @Compile
   public void onBreak(BreakEvent event) {
      BlockPos blockPos = event.getBlockPos();
      Direction direction = event.getDirection();
      if ((double)((IClientPlayerInteractionManagerMixin)mc.interactionManager).getBreakingProgress() >= (double)0.5F) {
         mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, blockPos, direction));
         mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, blockPos, direction));
      }

   }
}
