package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.MoveUtil;

public class WaterSpeed extends Module {
   private final TimeUtil time = new TimeUtil();
   float acceletion = 1.01F;

   public WaterSpeed() {
      super("Water Speed", "Даёт плавать в воде быстрее", Category.Movement);
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate e) {
      Minecraft var10000;
      Minecraft var10001;
      label49: {
         label52: {
            var10001 = mc;
            if (mc.world.getBlockState(Minecraft.player.getPosition().up()).getBlock() != Blocks.WATER) {
               var10001 = mc;
               if (mc.world.getBlockState(Minecraft.player.getPosition().up()).getBlock() != Blocks.LAVA && mc.gameSettings.keyBindJump.isKeyDown()) {
                  break label52;
               }
            }

            var10000 = mc;
            if (Minecraft.player.isInWater()) {
               break label49;
            }

            var10000 = mc;
            if (Minecraft.player.isInLava()) {
               break label49;
            }
         }

         this.time.reset();
      }

      var10000 = mc;
      if (!Minecraft.player.isInWater()) {
         var10000 = mc;
         if (!Minecraft.player.isInLava()) {
            return;
         }
      }

      if (this.time.finished(500L)) {
         float var3;
         if (mc.gameSettings.keyBindJump.isKeyDown()) {
            var3 = 0.05F;
         } else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            var3 = -0.05F;
         } else {
            var10000 = mc;
            var3 = !Minecraft.player.isSprinting() ? 0.005F : 0.0F;
         }

         float ySpeed = var3;
         var10000 = mc;
         if (Minecraft.player.isSprinting() || MoveUtil.isMoving()) {
            this.time.reset();
            ySpeed = 0.0F;
         }

         var10000 = mc;
         Minecraft.player.movementInput.sneaking = false;
         var10000 = mc;
         ClientPlayerEntity var6 = Minecraft.player;
         var10001 = mc;
         double var4 = Minecraft.player.getMotion().x;
         Minecraft var10002 = mc;
         double var5 = Minecraft.player.getMotion().y + (double)ySpeed;
         Minecraft var10003 = mc;
         var6.setVelocity(var4, var5, Minecraft.player.getMotion().z);
      }

   }

   @Native
   public void onDisable() {
      super.onDisable();
      this.acceletion = 1.01F;
   }
}
