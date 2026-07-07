package unique.core.features.modules.combat;

import net.minecraft.class_1657;
import org.jetbrains.annotations.Nullable;
import unique.core.features.modules.Module;
import unique.core.features.modules.base.TrapModule;

public final class SelfTrap extends TrapModule {
   public SelfTrap() {
      super("SelfTrap", Module.Category.COMBAT);
   }

   @Override
   protected boolean needNewTarget() {
      return this.target == null;
   }

   @Nullable
   @Override
   protected class_1657 getTarget() {
      return mc.field_1724;
   }
}
