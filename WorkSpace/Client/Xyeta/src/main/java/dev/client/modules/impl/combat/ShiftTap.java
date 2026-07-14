package dev.client.modules.impl.combat;

import dev.client.event.classes.AttackEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IAttackable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ShiftTap extends Module implements ITickable, IAttackable, IUtil {
   long shiftTapEndTime = 0L;
   boolean isModuleControllingSneak = false;

   public ShiftTap() {
      super(new ModuleInfo("ShiftTap", Category.COMBAT, "Присаживается после удара"));
   }

   private void startShiftTap() {
      this.shiftTapEndTime = System.currentTimeMillis() + 25L;
      if (!this.isModuleControllingSneak) {
         mc.options.sneakKey.setPressed(true);
         this.isModuleControllingSneak = true;
      }

   }

   private void stopShiftTap() {
      if (this.isModuleControllingSneak) {
         mc.options.sneakKey.setPressed(false);
         this.isModuleControllingSneak = false;
      }

   }

   public void onAttack(AttackEvent event) {
      if (mc.player != null) {
         this.startShiftTap();
      }
   }

   public void onTick(TickEvent event) {
      if (mc.player != null && !mc.player.isSpectator()) {
         long currentTime = System.currentTimeMillis();
         if (this.isModuleControllingSneak && currentTime > this.shiftTapEndTime) {
            this.stopShiftTap();
         }

      } else {
         this.stopShiftTap();
      }
   }
}
