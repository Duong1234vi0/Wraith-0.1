package unique.core.features.modules.render;

import java.awt.Color;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render3DEngine;

public class Tracers extends Module {
   private final Setting<Float> height = new Setting<>("Height", 0.0F, 0.0F, 2.0F);
   private final Setting<ColorSetting> color = new Setting<>("Color", new ColorSetting(new Color(-1812004864, true)));
   private final Setting<ColorSetting> friendColor = new Setting<>("Friends", new ColorSetting(new Color(-1827152291, true)));

   public Tracers() {
      super("Tracers", Module.Category.RENDER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      for (class_1657 player : Managers.ASYNC.getAsyncPlayers()) {
         if (player != mc.field_1724) {
            Color color1 = this.color.getValue().getColorObject();
            if (Managers.FRIEND.isFriend(player)) {
               color1 = this.friendColor.getValue().getColorObject();
            }

            double x1 = mc.field_1724.field_6014 + (mc.field_1724.method_23317() - mc.field_1724.field_6014) * Render3DEngine.getTickDelta();
            double y1 = mc.field_1724.method_18381(mc.field_1724.method_18376())
               + mc.field_1724.field_6036
               + (mc.field_1724.method_23318() - mc.field_1724.field_6036) * Render3DEngine.getTickDelta();
            double z1 = mc.field_1724.field_5969 + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * Render3DEngine.getTickDelta();
            class_243 vec2 = new class_243(0.0, 0.0, 75.0)
               .method_1037(-((float)Math.toRadians(mc.field_1773.method_19418().method_19329())))
               .method_1024(-((float)Math.toRadians(mc.field_1773.method_19418().method_19330())))
               .method_1031(x1, y1, z1);
            double x = player.field_6014 + (player.method_23317() - player.field_6014) * Render3DEngine.getTickDelta();
            double y = player.field_6036 + (player.method_23318() - player.field_6036) * Render3DEngine.getTickDelta();
            double z = player.field_5969 + (player.method_23321() - player.field_5969) * Render3DEngine.getTickDelta();
            Render3DEngine.drawLineDebug(vec2, new class_243(x, y + this.height.getValue().floatValue(), z), color1);
         }
      }
   }
}
