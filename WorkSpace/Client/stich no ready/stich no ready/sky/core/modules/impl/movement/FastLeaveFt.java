package sky.core.modules.impl.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class FastLeaveFt extends Module {
   public FastLeaveFt() {
      super("FastLeaveFt", "Тепает обрано где вы были до прыжка и только на снегу", Category.Movement);
   }

   public void onEnable() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         float yaw = Minecraft.player.rotationYaw;
         double x = -Math.sin(Math.toRadians((double)yaw));
         double z = Math.cos(Math.toRadians((double)yaw));
         var10000 = mc;
         Vector3d currentPos = Minecraft.player.getPositionVec();
         Vector3d newPos = currentPos.add(x * 50.0, 0.0, z * 50.0);
         var10000 = mc;
         Minecraft.player.setPosition(newPos.x, newPos.y, newPos.z);
         (new Thread(() -> {
            try {
               Thread.sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            this.toggle();
         })).start();
      } else {
         this.toggle();
      }
   }
}
