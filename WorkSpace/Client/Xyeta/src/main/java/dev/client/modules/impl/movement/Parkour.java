package dev.client.modules.impl.movement;

import dev.client.event.classes.Render3DEvent;
import dev.client.event.interfaces.IRenderable3D;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Parkour extends Module implements IRenderable3D, IUtil {
   private final TimerUtil timerUtil = new TimerUtil();

   public Parkour() {
      super(new ModuleInfo("Parkour", Category.MOVEMENT, "Прыгает на конце блока"));
   }

   public void onRender3D(Render3DEvent event) {
      if (mc.player.isOnGround() && !mc.options.jumpKey.isPressed() && !mc.world.getBlockCollisions(mc.player, mc.player.getBoundingBox().expand((double)-0.01F, (double)0.0F, (double)-0.01F).offset((double)0.0F, -0.99, (double)0.0F)).iterator().hasNext() && this.timerUtil.every((double)150.0F)) {
         mc.player.jump();
      }

   }
}
