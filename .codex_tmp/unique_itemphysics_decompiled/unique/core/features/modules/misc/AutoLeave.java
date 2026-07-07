package unique.core.features.modules.misc;

import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_634;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class AutoLeave extends Module {
   private final Setting<Boolean> playerLeave = new Setting<>("PlayerLeave", true);
   private final Setting<Integer> rangeLeave = new Setting<>("RangeLeave", 50, 1, 100);
   private final Setting<Boolean> healthLeave = new Setting<>("HealthLeave", true);
   private final Setting<Integer> healthValue = new Setting<>("Health", 15, 1, 50, v -> this.healthLeave.getValue());

   public AutoLeave() {
      super("AutoLeave", Module.Category.MISC);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.playerLeave.getValue()) {
            if (this.healthLeave.getValue() && mc.field_1724.method_6032() <= this.healthValue.getValue().intValue()) {
               this.disconnectFromServer("§c[AutoLeave] Low Health (" + mc.field_1724.method_6032() + ")");
               this.disable();
            } else {
               for (class_1657 pl : mc.field_1687.method_18456()) {
                  if (pl != mc.field_1724) {
                     double dist = mc.field_1724.method_5739(pl);
                     if (dist <= this.rangeLeave.getValue().intValue()) {
                        this.disconnectFromServer("§c[AutoLeave] Player Detected Nearby (" + (int)dist + " Blocks)");
                        this.disable();
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void disconnectFromServer(String reason) {
      class_634 connection = mc.method_1562();
      if (connection != null) {
         connection.method_48296().method_10747(class_2561.method_30163(reason));
      }
   }
}
