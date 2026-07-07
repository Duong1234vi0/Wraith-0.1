package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1313;
import net.minecraft.class_1890;
import net.minecraft.class_243;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_6880;
import net.minecraft.class_9701;
import unique.core.events.impl.UseTridentEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class TridentBoost extends Module {
   private final Setting<TridentBoost.Mode> mode = new Setting<>("Mode", TridentBoost.Mode.Motion);
   private final Setting<Float> factor = new Setting<>("Factor", 1.0F, 0.1F, 20.0F);
   public final Setting<Integer> cooldown = new Setting<>("Cooldown", 10, 0, 20);
   public final Setting<Boolean> anyWeather = new Setting<>("AnyWeather", true);

   public TridentBoost() {
      super("TridentBoost", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onUseTrident(UseTridentEvent e) {
      if (mc.field_1724.method_6048() >= this.cooldown.getValue()) {
         float j = class_1890.method_60123(mc.field_1724.method_6030(), mc.field_1724);
         if ((this.anyWeather.getValue() || mc.field_1724.method_5721()) && j > 0.0F) {
            float f = mc.field_1724.method_36454();
            float g = mc.field_1724.method_36455();
            float speedX = -class_3532.method_15374(f * (float) (Math.PI / 180.0)) * class_3532.method_15362(g * (float) (Math.PI / 180.0));
            float speedY = -class_3532.method_15374(g * (float) (Math.PI / 180.0));
            float speedZ = class_3532.method_15362(f * (float) (Math.PI / 180.0)) * class_3532.method_15362(g * (float) (Math.PI / 180.0));
            float plannedSpeed = class_3532.method_15355(speedX * speedX + speedY * speedY + speedZ * speedZ);
            float n = this.mode.is(TridentBoost.Mode.Factor) ? this.factor.getValue() * 3.0F * ((1.0F + j) / 4.0F) : this.factor.getValue();
            speedX *= n / plannedSpeed;
            speedY *= n / plannedSpeed;
            speedZ *= n / plannedSpeed;
            mc.field_1724.method_5762(speedX, speedY, speedZ);
            mc.field_1724.method_40126(20, 8.0F, mc.field_1724.method_6030());
            if (mc.field_1724.method_24828()) {
               mc.field_1724.method_5784(class_1313.field_6308, new class_243(0.0, 1.1999999F, 0.0));
            }

            class_6880<class_3414> registryEntry = class_1890.method_60165(mc.field_1724.method_6030(), class_9701.field_51654).orElse(class_3417.field_15001);
            mc.field_1687.method_43129(null, mc.field_1724, (class_3414)registryEntry.comp_349(), class_3419.field_15248, 1.0F, 1.0F);
         }
      }

      e.cancel();
   }

   private static enum Mode {
      Motion,
      Factor;
   }
}
