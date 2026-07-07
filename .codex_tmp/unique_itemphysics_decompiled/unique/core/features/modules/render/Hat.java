package unique.core.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class Hat extends Module {
   private final Setting<Hat.Mode> mode = new Setting<>("Mode", Hat.Mode.Hat);
   private final Setting<Boolean> syncColor = new Setting<>("SyncColor", true);
   private final Setting<Boolean> colorEditor = new Setting<>("ColorEditor", false);
   private final Setting<ColorSetting> color = new Setting<>(
      "Color", new ColorSetting(new Color(255, 255, 255, 255)), v -> this.colorEditor.getValue() && !this.syncColor.getValue()
   );
   private final Setting<Boolean> firstPerson = new Setting<>("FirstPerson", false);
   private static final int SEGMENTS = 128;
   private static final float RADIUS = 0.7F;
   private static final float HEIGHT = 0.36F;
   private static final float VENUS_RADIUS = 0.5F;
   private static final float VENUS_HEIGHT = 1.0F;

   public Hat() {
      super("Hat", Module.Category.RENDER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (mc.field_1724 != null) {
         if (this.firstPerson.getValue() || !mc.field_1690.method_31044().method_31034()) {
            double x = mc.field_1724.field_6014
               + (mc.field_1724.method_23317() - mc.field_1724.field_6014) * Render3DEngine.getTickDelta()
               - mc.method_1561().field_4686.method_19326().method_10216();
            double y = mc.field_1724.field_6036
               + (mc.field_1724.method_23318() - mc.field_1724.field_6036) * Render3DEngine.getTickDelta()
               - mc.method_1561().field_4686.method_19326().method_10214()
               + mc.field_1724.method_17682();
            double z = mc.field_1724.field_5969
               + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * Render3DEngine.getTickDelta()
               - mc.method_1561().field_4686.method_19326().method_10215();
            stack.method_22903();
            stack.method_22904(x, y, z);
            Render3DEngine.setupRender();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.setShader(class_757::method_34540);
            if (this.mode.getValue() == Hat.Mode.Hat) {
               this.renderHat(stack.method_23760().method_23761());
            } else if (this.mode.getValue() == Hat.Mode.VenusWare) {
               this.renderVenusWare(stack.method_23760().method_23761());
            }

            RenderSystem.enableCull();
            RenderSystem.enableDepthTest();
            Render3DEngine.endRender();
            stack.method_22909();
         }
      }
   }

   private void renderHat(Matrix4f matrix) {
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      Color hatColor = this.syncColor.getValue() ? HudEditor.getColor(0) : this.color.getValue().getColorObject();
      buffer.method_22918(matrix, 0.0F, 0.36F, 0.0F).method_39415(hatColor.getRGB());

      for (int i = 0; i <= 128; i++) {
         double a = i / 128.0 * Math.PI * 2.0;
         buffer.method_22918(matrix, (float)Math.cos(a) * 0.7F, 0.0F, (float)Math.sin(a) * 0.7F).method_39415(hatColor.getRGB());
      }

      Render2DEngine.endBuilding(buffer);
   }

   private void renderVenusWare(Matrix4f matrix) {
      Color ringColor = this.syncColor.getValue() ? HudEditor.getColor(0) : this.color.getValue().getColorObject();
      float alpha = ringColor.getAlpha() / 255.0F;
      float time = (float)(System.currentTimeMillis() % 2000L) / 2000.0F * (float) Math.PI * 2.0F;

      for (int ring = 0; ring < 3; ring++) {
         float radius = 0.45F + ring * 0.05F;
         float height = 0.2F + ring * 0.02F;
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
         buffer.method_22918(matrix, 0.0F, height, 0.0F).method_1336(ringColor.getRed(), ringColor.getGreen(), ringColor.getBlue(), 0);

         for (int i = 0; i <= 128; i++) {
            double angle = i / 128.0 * Math.PI * 2.0 + time + ring * 0.5;
            float px = (float)Math.cos(angle) * radius;
            float pz = (float)Math.sin(angle) * radius;
            float fade = i / 128.0F;
            int finalAlpha = (int)(alpha * 255.0F * fade);
            buffer.method_22918(matrix, px, height, pz).method_1336(ringColor.getRed(), ringColor.getGreen(), ringColor.getBlue(), finalAlpha);
         }

         Render2DEngine.endBuilding(buffer);
      }
   }

   public static enum Mode {
      Hat,
      VenusWare;
   }
}
