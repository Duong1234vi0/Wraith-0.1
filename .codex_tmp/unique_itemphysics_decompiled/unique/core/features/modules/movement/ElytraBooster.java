package unique.core.features.modules.movement;

import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public final class ElytraBooster extends Module {
   public static ElytraBooster INSTANCE;
   public final Setting<ElytraBooster.Mode> mode = new Setting<>("Mode", ElytraBooster.Mode.Custom);
   public final Setting<Float> boost = new Setting<>("Boost", 1.5F, 0.1F, 5.0F, v -> this.mode.getValue() == ElytraBooster.Mode.Custom);

   public ElytraBooster() {
      super("ElytraBooster", Module.Category.MOVEMENT);
      INSTANCE = this;
   }

   public static enum Mode {
      Custom,
      Auto;
   }
}
