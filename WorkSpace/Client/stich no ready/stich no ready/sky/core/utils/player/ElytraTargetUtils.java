package sky.core.utils.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import sky.core.SkyCore;
import sky.core.modules.impl.movement.ElytraMotion;
import sky.core.modules.impl.movement.ElytraTarget;
import sky.core.utils.Wrapper;

public class ElytraTargetUtils implements Wrapper {
   public static void updateNumber() {
   }

   public static float getJitterYaw(LivingEntity entity) {
      return 0.0F;
   }

   public static float getJitterPitch(LivingEntity entity) {
      return 0.0F;
   }

   public static boolean canTarget(LivingEntity target) {
      if (target == null) {
         return false;
      } else {
         Minecraft var10000 = mc;
         if (Minecraft.player.getCooledAttackStrength(1.5F) < 0.94F) {
            return true;
         } else {
            var10000 = mc;
            return Minecraft.player.isHandActive();
         }
      }
   }

   public static boolean fullCheck() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         boolean var3;
         label17: {
            ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
            ElytraMotion elytraMotion = SkyCore.getInstance().getModuleManager().getElytraMotion();
            if (elytraTarget.isEnabled()) {
               var10000 = mc;
               if (Minecraft.player.isElytraFlying() && !elytraMotion.isEnabled()) {
                  var3 = true;
                  break label17;
               }
            }

            var3 = false;
         }

         boolean flag = var3;
         return flag;
      } else {
         return false;
      }
   }
}
