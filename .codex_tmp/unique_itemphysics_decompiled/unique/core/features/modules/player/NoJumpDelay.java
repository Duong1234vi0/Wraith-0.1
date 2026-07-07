package unique.core.features.modules.player;

import unique.core.features.modules.Module;
import unique.core.injection.accesors.ILivingEntity;
import unique.core.setting.Setting;

public class NoJumpDelay extends Module {
   private final Setting<Integer> delay = new Setting<>("Delay", 1, 0, 4);

   public NoJumpDelay() {
      super("NoJumpDelay", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (((ILivingEntity)mc.field_1724).getLastJumpCooldown() > this.delay.getValue()) {
         ((ILivingEntity)mc.field_1724).setLastJumpCooldown(this.delay.getValue());
      }
   }
}
