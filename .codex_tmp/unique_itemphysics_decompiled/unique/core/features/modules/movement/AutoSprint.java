package unique.core.features.modules.movement;

import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;

public class AutoSprint extends Module {
   public final Setting<AutoSprint.Mode> mode = new Setting<>("Mode", AutoSprint.Mode.Normal);
   public static final Setting<Boolean> sprint = new Setting<>("KeepSprint", true, v -> ModuleManager.autoSprint.mode.getValue() == AutoSprint.Mode.Normal);
   public static final Setting<Float> motion = new Setting<>(
      "Motion", 1.0F, 0.0F, 1.0F, v -> sprint.getValue() && ModuleManager.autoSprint.mode.getValue() == AutoSprint.Mode.Normal
   );
   public final Setting<Boolean> stopWhileUsing = new Setting<>("StopWhileUsing", true);
   public final Setting<Boolean> pauseWhileAura = new Setting<>("PauseWhileAura", false);
   public final Setting<Boolean> pauseInWater = new Setting<>("PauseInWater", true);

   public AutoSprint() {
      super("AutoSprint", Module.Category.MOVEMENT);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null) {
         if (this.pauseInWater.getValue() && mc.field_1724.method_5799()) {
            mc.field_1724.method_5728(false);
         } else {
            boolean isAuraPausing = this.pauseWhileAura.getValue() && ModuleManager.aura.isEnabled() && Aura.target != null;
            boolean canSprint = mc.field_1724.method_7344().method_7586() > 6
               && mc.field_1724.field_3913.field_3905 > 0.8F
               && !mc.field_1724.field_5976
               && !mc.field_1724.method_5715()
               && (!mc.field_1724.method_6115() || !this.stopWhileUsing.getValue())
               && !isAuraPausing;
            boolean isFalling = !mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.08F;
            switch ((AutoSprint.Mode)this.mode.getValue()) {
               case Legit:
                  if (canSprint) {
                     mc.field_1690.field_1867.method_23481(true);
                  } else {
                     mc.field_1690.field_1867.method_23481(false);
                     mc.field_1724.method_5728(false);
                  }
                  break;
               case Normal:
                  if (!canSprint) {
                     mc.field_1724.method_5728(false);
                     return;
                  }

                  mc.field_1724.method_5728(true);
                  if (motion.getValue() != 1.0F) {
                     mc.field_1724
                        .method_18800(
                           mc.field_1724.method_18798().field_1352 * motion.getValue().floatValue(),
                           mc.field_1724.method_18798().field_1351,
                           mc.field_1724.method_18798().field_1350 * motion.getValue().floatValue()
                        );
                  }
                  break;
               case Reset:
                  if (!canSprint) {
                     mc.field_1724.method_5728(false);
                     return;
                  }

                  if (isFalling) {
                     mc.field_1724.method_5728(false);
                  } else {
                     mc.field_1724.method_5728(true);
                  }

                  if (ModuleManager.aura.isEnabled() && Aura.target != null && mc.field_1724.method_7261(0.5F) >= 0.9F) {
                     doSprintReset();
                  }
            }
         }
      }
   }

   public static void doSprintReset() {
      if (mc.field_1724 != null && mc.field_1724.method_5624()) {
         mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12985));
         mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12981));
      }
   }

   public static enum Mode {
      Legit,
      Normal,
      Reset;
   }
}
