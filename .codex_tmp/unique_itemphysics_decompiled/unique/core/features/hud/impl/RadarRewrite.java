package unique.core.features.hud.impl;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import unique.core.core.Managers;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.MSAAFramebuffer;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class RadarRewrite extends HudElement {
   private final Setting<Boolean> glow = new Setting<>("Glow", false);
   private final Setting<Float> width = new Setting<>("Height", 2.28F, 0.1F, 5.0F);
   private final Setting<Float> down = new Setting<>("Down", 3.63F, 0.1F, 20.0F);
   private final Setting<Float> tracerWidth = new Setting<>("Width", 0.44F, 0.0F, 8.0F);
   private final Setting<Integer> xOffset = new Setting<>("TracerRadius", 68, 20, 100);
   private final Setting<Integer> pitchLock = new Setting<>("PitchLock", 42, 0, 90);
   private final Setting<RadarRewrite.triangleModeEn> triangleMode = new Setting<>("TracerCMode", RadarRewrite.triangleModeEn.Astolfo);
   private final Setting<Float> CRadius = new Setting<>("CompassRadius", 47.0F, 0.1F, 70.0F);
   private final Setting<ColorSetting> ciColor = new Setting<>("Circle", new ColorSetting(new Color(16777215)));
   private final Setting<ColorSetting> colorf = new Setting<>("Friend", new ColorSetting(new Color(59392)));
   private final Setting<ColorSetting> colors = new Setting<>("Tracer", new ColorSetting(new Color(16776960)));

   public RadarRewrite() {
      super("AkrienRadar", 50, 50);
   }

   public static float getRotations(class_1297 entity) {
      if (mc.field_1724 == null) {
         return 0.0F;
      } else {
         double x = interp(entity.method_19538().field_1352, entity.field_6014) - interp(mc.field_1724.method_19538().field_1352, mc.field_1724.field_6014);
         double z = interp(entity.method_19538().field_1350, entity.field_5969) - interp(mc.field_1724.method_19538().field_1350, mc.field_1724.field_5969);
         return (float)(-(Math.atan2(x, z) * (180.0 / Math.PI)));
      }
   }

   public static double interp(double d, double d2) {
      return d2 + (d - d2) * Render3DEngine.getTickDelta();
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      if (!fullNullCheck()) {
         float middleW = mc.method_22683().method_4486() * this.getX();
         float middleH = mc.method_22683().method_4502() * this.getY();
         MSAAFramebuffer.use(
            false,
            () -> {
               context.method_51448().method_22903();
               this.renderCompass(context.method_51448(), middleW + this.CRadius.getValue(), middleH + this.CRadius.getValue());
               context.method_51448().method_22909();
               int color = 0;
               context.method_51448().method_22903();
               context.method_51448().method_46416(middleW + this.CRadius.getValue(), middleH + this.CRadius.getValue(), 0.0F);
               context.method_51448()
                  .method_22907(
                     class_7833.field_40714
                        .rotationDegrees(
                           90.0F / Math.abs(90.0F / MathUtility.clamp(mc.field_1724.method_36455(), (float)this.pitchLock.getValue().intValue(), 90.0F))
                              - 102.0F
                        )
                  );
               context.method_51448().method_46416(-(middleW + this.CRadius.getValue()), -(middleH + this.CRadius.getValue()), 0.0F);

               for (class_1657 e : Lists.newArrayList(mc.field_1687.method_18456())) {
                  if (e != mc.field_1724) {
                     context.method_51448().method_22903();
                     float yaw = getRotations(e) - mc.field_1724.method_36454();
                     context.method_51448().method_46416(middleW + this.CRadius.getValue(), middleH + this.CRadius.getValue(), 0.0F);
                     context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(yaw));
                     context.method_51448().method_46416(-(middleW + this.CRadius.getValue()), -(middleH + this.CRadius.getValue()), 0.0F);
                     if (Managers.FRIEND.isFriend(e)) {
                        color = this.colorf.getValue().getColor();
                     } else {
                        color = switch ((RadarRewrite.triangleModeEn)this.triangleMode.getValue()) {
                           case Custom -> this.colors.getValue().getColor();
                           case Astolfo -> Render2DEngine.astolfo(false, 1).getRGB();
                        };
                     }

                     Render2DEngine.drawTracerPointer(
                        context.method_51448(),
                        middleW + this.CRadius.getValue(),
                        middleH - this.xOffset.getValue().intValue() + this.CRadius.getValue(),
                        this.width.getValue() * 5.0F,
                        this.tracerWidth.getValue(),
                        this.down.getValue(),
                        true,
                        this.glow.getValue(),
                        color
                     );
                     context.method_51448().method_46416(middleW + this.CRadius.getValue(), middleH + this.CRadius.getValue(), 0.0F);
                     context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(-yaw));
                     context.method_51448().method_46416(-(middleW + this.CRadius.getValue()), -(middleH + this.CRadius.getValue()), 0.0F);
                     context.method_51448().method_22909();
                  }
               }

               context.method_51448().method_22909();
            }
         );
         this.setBounds(this.getPosX(), this.getPosY(), (int)(this.CRadius.getValue() * 2.0F), (int)(this.CRadius.getValue() * 2.0F));
      }
   }

   public void renderCompass(class_4587 matrices, float x, float y) {
      float pitchFactor = Math.abs(90.0F / MathUtility.clamp(mc.field_1724.method_36455(), (float)this.pitchLock.getValue().intValue(), 90.0F));
      this.drawEllipsCompas(
         matrices, -((int)mc.field_1724.method_36454()), x, y, pitchFactor, 1.0F, -2.0F, 1.0F, this.ciColor.getValue().getColorObject(), false
      );
      this.drawEllipsCompas(matrices, -((int)mc.field_1724.method_36454()), x, y, pitchFactor, 1.0F, 0.0F, 3.0F, Color.WHITE, true);
   }

   public void drawEllipsCompas(class_4587 matrices, int yaw, float x, float y, float x2, float y2, float margin, float width, Color color, boolean Dir) {
      this.drawElipse(matrices, x, y, x2, y2, 15 + yaw, 75 + yaw, margin, width, color, Dir ? "W" : "");
      this.drawElipse(matrices, x, y, x2, y2, 105 + yaw, 165 + yaw, margin, width, color, Dir ? "N" : "");
      this.drawElipse(matrices, x, y, x2, y2, 195 + yaw, 255 + yaw, margin, width, color, Dir ? "E" : "");
      this.drawElipse(matrices, x, y, x2, y2, 285 + yaw, 345 + yaw, margin, width, color, Dir ? "S" : "");
   }

   public void drawElipse(
      class_4587 matrices, float x, float y, float rx, float ry, float start, float end, float margin, float width, Color color, String direction
   ) {
      if (start > end) {
         float endOffset = end;
         end = start;
         start = endOffset;
      }

      RenderSystem.enableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShader(class_757::method_34540);
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);
      float radius = this.CRadius.getValue() - margin;

      for (float i = start; i <= end; i += 6.0F) {
         float stage = (i - start) / 360.0F;
         if (!Objects.equals(direction, "")) {
            color = HudEditor.getColor((int)(stage * 500.0F));
         }

         float cos = (float)Math.cos(i * Math.PI / 180.0);
         float sin = (float)Math.sin(i * Math.PI / 180.0);
         bufferBuilder.method_22912(x + cos * (radius / ry), y + sin * (radius / rx), 0.0F).method_39415(color.getRGB());
         bufferBuilder.method_22912(x + cos * ((radius - width) / ry), y + sin * ((radius - width) / rx), 0.0F).method_39415(color.getRGB());
      }

      Render2DEngine.endBuilding(bufferBuilder);
      RenderSystem.disableBlend();
      if (!Objects.equals(direction, "")) {
         FontRenderers.getModulesRenderer()
            .drawString(
               matrices,
               direction,
               x - 2.0F + Math.cos((start - 15.0F) * Math.PI / 180.0) * (radius / ry),
               y - 1.0F + Math.sin((start - 15.0F) * Math.PI / 180.0) * (radius / rx),
               -1
            );
      }
   }

   public static enum mode2 {
      Custom,
      Astolfo,
      TwoColor;
   }

   public static enum triangleModeEn {
      Custom,
      Astolfo;
   }
}
