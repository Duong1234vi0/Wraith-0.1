package unique.core.features.hud.impl;

import com.google.common.collect.Lists;
import java.awt.Color;
import net.minecraft.class_1657;
import net.minecraft.class_332;
import net.minecraft.class_7833;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.hud.HudElement;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class CrosshairArrows extends HudElement {
   public static Setting<Boolean> glow = new Setting<>("Glow", false);
   private final Setting<Float> width = new Setting<>("Height", 2.28F, 0.1F, 5.0F);
   private final Setting<BooleanSettingGroup> down = new Setting<>("Down", new BooleanSettingGroup(false));
   private final Setting<Float> downHeight = new Setting<>("DownHeight", 3.63F, 0.1F, 20.0F).addToGroup(this.down);
   private final Setting<Float> tracerWidth = new Setting<>("Width", 0.44F, 0.0F, 8.0F);
   private final Setting<Integer> xOffset = new Setting<>("TracerRadius", 68, 20, 100);
   private final Setting<Integer> pitchLock = new Setting<>("PitchLock", 42, 0, 90);
   private final Setting<CrosshairArrows.triangleModeEn> triangleMode = new Setting<>("TracerCMode", CrosshairArrows.triangleModeEn.Astolfo);
   private final Setting<ColorSetting> colorf = new Setting<>("Friend", new ColorSetting(new Color(59392)));
   private final Setting<ColorSetting> colors = new Setting<>("Tracer", new ColorSetting(new Color(16776960)));
   private float smoothYaw = 0.0F;

   public CrosshairArrows() {
      super("CrosshairArrows", 0, 0);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      if (!fullNullCheck()) {
         float middleW = ModuleManager.crosshair.getAnimatedPosX();
         float middleH = ModuleManager.crosshair.getAnimatedPosY();
         int color = 0;
         context.method_51448().method_22903();
         context.method_51448().method_46416(middleW, middleH, 0.0F);
         context.method_51448()
            .method_22907(
               class_7833.field_40714
                  .rotationDegrees(
                     90.0F / Math.abs(90.0F / MathUtility.clamp(mc.field_1724.method_36455(), (float)this.pitchLock.getValue().intValue(), 90.0F)) - 102.0F
                  )
            );
         context.method_51448().method_46416(-middleW, -middleH, 0.0F);
         this.smoothYaw = AnimationUtility.fast(this.smoothYaw, mc.field_1724.method_36454(), 13.0F);

         for (class_1657 e : Lists.newArrayList(mc.field_1687.method_18456())) {
            if (e != mc.field_1724) {
               context.method_51448().method_22903();
               float yaw = RadarRewrite.getRotations(e) - this.smoothYaw;
               context.method_51448().method_46416(middleW, middleH, 0.0F);
               context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(yaw));
               context.method_51448().method_46416(-middleW, -middleH, 0.0F);
               if (Managers.FRIEND.isFriend(e)) {
                  color = this.colorf.getValue().getColor();
               } else {
                  color = switch ((CrosshairArrows.triangleModeEn)this.triangleMode.getValue()) {
                     case Custom -> this.colors.getValue().getColor();
                     case Astolfo -> Render2DEngine.astolfo(false, 1).getRGB();
                  };
               }

               Render2DEngine.drawTracerPointer(
                  context.method_51448(),
                  middleW,
                  middleH - this.xOffset.getValue().intValue(),
                  this.width.getValue() * 5.0F,
                  this.tracerWidth.getValue(),
                  this.downHeight.getValue(),
                  this.down.getValue().isEnabled(),
                  glow.getValue(),
                  color
               );
               context.method_51448().method_46416(middleW, middleH, 0.0F);
               context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(-yaw));
               context.method_51448().method_46416(-middleW, -middleH, 0.0F);
               context.method_51448().method_22909();
            }
         }

         context.method_51448().method_22909();
      }
   }

   public static enum triangleModeEn {
      Custom,
      Astolfo;
   }
}
