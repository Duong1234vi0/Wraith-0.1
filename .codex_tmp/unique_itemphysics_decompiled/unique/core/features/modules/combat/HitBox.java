package unique.core.features.modules.combat;

import unique.core.core.manager.client.ServerManager;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public final class HitBox extends Module {
   public static final Setting<Float> XZExpand = new Setting<>("XZExpand", 1.0F, 0.0F, 5.0F);
   public static final Setting<Float> YExpand = new Setting<>("YExpand", 0.0F, 0.0F, 5.0F);
   public static final Setting<Boolean> affectToAura = new Setting<>("AffectToAura", false);

   public HitBox() {
      super("Hitbox", Module.Category.COMBAT);
   }

   @Override
   public String getDisplayInfo() {
      return "H: " + ServerManager.round2(XZExpand.getValue().floatValue()) + " V: " + ServerManager.round2(YExpand.getValue().floatValue());
   }
}
