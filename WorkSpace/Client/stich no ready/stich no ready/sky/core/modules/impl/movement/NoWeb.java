package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.player.MoveUtil;

public class NoWeb extends Module {
   public NoWeb() {
      super("No Web", "Убирает замедление в паутине", Category.Movement);
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (Minecraft.player.isInWeb) {
            var10000 = mc;
            Vector3d motion = Minecraft.player.getMotion();
            var10000 = mc;
            Minecraft.player.setMotion(motion.x, 0.0, motion.z);
            if (mc.gameSettings.keyBindJump.isKeyDown()) {
               var10000 = mc;
               motion = Minecraft.player.getMotion();
               var10000 = mc;
               Minecraft.player.setMotion(motion.x, 0.9, motion.z);
            }

            if (mc.gameSettings.keyBindSneak.isKeyDown()) {
               var10000 = mc;
               motion = Minecraft.player.getMotion();
               var10000 = mc;
               Minecraft.player.setMotion(motion.x, -0.9, motion.z);
            }

            MoveUtil.setMotion(0.21);
         }
      }
   }
}
