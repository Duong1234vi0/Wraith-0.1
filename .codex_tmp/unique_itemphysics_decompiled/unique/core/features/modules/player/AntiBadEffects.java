package unique.core.features.modules.player;

import net.minecraft.class_1294;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class AntiBadEffects extends Module {
   private final Setting<Boolean> blindness = new Setting<>("Blindness", true);
   private final Setting<Boolean> nausea = new Setting<>("Nausea", true);
   private final Setting<Boolean> miningFatigue = new Setting<>("MiningFatigue", true);
   private final Setting<Boolean> levitation = new Setting<>("Levitation", true);
   private final Setting<Boolean> slowness = new Setting<>("Slowness", true);
   private final Setting<Boolean> jumpBoost = new Setting<>("JumpBoost", true);

   public AntiBadEffects() {
      super("AntiBadEffects", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724.method_6059(class_1294.field_5919) && this.blindness.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5919);
      }

      if (mc.field_1724.method_6059(class_1294.field_5916) && this.nausea.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5916);
      }

      if (mc.field_1724.method_6059(class_1294.field_5901) && this.miningFatigue.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5901);
      }

      if (mc.field_1724.method_6059(class_1294.field_5902) && this.levitation.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5902);
      }

      if (mc.field_1724.method_6059(class_1294.field_5909) && this.slowness.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5909);
      }

      if (mc.field_1724.method_6059(class_1294.field_5913) && this.jumpBoost.getValue()) {
         mc.field_1724.method_6016(class_1294.field_5913);
      }
   }
}
