package unique.core.features.modules.combat;

import net.minecraft.class_1657;
import org.jetbrains.annotations.Nullable;
import unique.core.core.Managers;
import unique.core.core.manager.player.CombatManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.base.TrapModule;
import unique.core.setting.Setting;

public final class AutoTrap extends TrapModule {
   private final Setting<CombatManager.TargetBy> targetBy = new Setting<>("Target By", CombatManager.TargetBy.Distance);
   private final Setting<Boolean> targetMovingPlayers = new Setting<>("MovingPlayers", false);

   public AutoTrap() {
      super("AutoTrap", Module.Category.COMBAT);
   }

   @Override
   protected boolean needNewTarget() {
      return this.target == null
         || this.target.method_5739(mc.field_1724) > this.range.getValue()
         || this.target.method_6032() + this.target.method_6067() <= 0.0F
         || this.target.method_29504();
   }

   @Nullable
   @Override
   protected class_1657 getTarget() {
      return Managers.COMBAT
         .getTarget(this.range.getValue(), this.targetBy.getValue(), p -> p.method_18798().method_1027() < 0.08 || this.targetMovingPlayers.getValue());
   }
}
