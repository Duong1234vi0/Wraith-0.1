package unique.core.features.hud.impl;

import java.awt.Color;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.windows.WindowsScreen;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;

public class Hotbar extends HudElement {
   public static final Setting<Hotbar.Mode> lmode = new Setting<>("LeftHandMode", Hotbar.Mode.Mode1);
   public static final Setting<Color> borderColor1 = new Setting<>("BorderColor1", new Color(-1));
   public static final Setting<Color> borderColor2 = new Setting<>("BorderColor2", new Color(-16711681));

   public Hotbar() {
      super("Hotbar", 0, 0);
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!(mc.field_1755 instanceof WindowsScreen)) {
         class_1657 playerEntity = mc.field_1724;
         if (playerEntity != null) {
            class_4587 matrices = context.method_51448();
            int i = mc.method_22683().method_4486() / 2;
            int y = mc.method_22683().method_4502() - 25;
            if (mc.field_1724.method_6079().method_7960()) {
               Render2DEngine.drawHudBase(matrices, i - 90, y, 180.0F, 20.0F, HudEditor.hudRound.getValue());
               this.drawBorder(matrices, i - 90, y, 180, 20);
            } else if (lmode.getValue() == Hotbar.Mode.Mode1) {
               Render2DEngine.drawHudBase(matrices, i - 111, y, 201.0F, 20.0F, HudEditor.hudRound.getValue());
               this.drawBorder(matrices, i - 111, y, 201, 20);
               if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
                  Render2DEngine.drawRect(matrices, i - 109 + 18, y + 2, 0.5F, 15.0F, new Color(1157627903, true));
               } else {
                  Render2DEngine.verticalGradient(
                     matrices,
                     i - 109 + 18,
                     y - 3,
                     i - 108 + 18 - 0.5F,
                     y + 9,
                     Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0),
                     HudEditor.textColor.getValue().getColorObject()
                  );
                  Render2DEngine.verticalGradient(
                     matrices,
                     i - 109 + 18,
                     y + 9,
                     i - 108 + 18 - 0.5F,
                     y + 15,
                     HudEditor.textColor.getValue().getColorObject(),
                     Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0)
                  );
               }
            } else {
               Render2DEngine.drawHudBase(matrices, i - 90, y, 180.0F, 20.0F, HudEditor.hudRound.getValue());
               Render2DEngine.drawHudBase(matrices, i - 112.5F, y, 20.0F, 20.0F, HudEditor.hudRound.getValue());
               this.drawBorder(matrices, i - 90, y, 180, 20);
               this.drawBorder(matrices, (int)(i - 112.5F), y, 20, 20);
            }

            Color c = HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry) ? new Color(2081756437, true) : new Color(2083467055, true);
            Render2DEngine.drawRect(
               matrices,
               i - 88 + playerEntity.method_31548().field_7545 * 19.8F,
               mc.method_22683().method_4502() - 24,
               17.0F,
               17.0F,
               HudEditor.hudRound.getValue(),
               0.7F,
               c,
               c,
               c,
               c
            );
         }
      }
   }

   private void drawBorder(class_4587 matrices, int x, int y, int w, int h) {
      Render2DEngine.horizontalGradient(matrices, x - 1, y - 1, x + w + 1, y, borderColor1.getValue(), borderColor2.getValue());
      Render2DEngine.horizontalGradient(matrices, x - 1, y + h, x + w + 1, y + h + 1, borderColor1.getValue(), borderColor2.getValue());
      Render2DEngine.verticalGradient(matrices, x - 1, y, x, y + h, borderColor1.getValue(), borderColor2.getValue());
      Render2DEngine.verticalGradient(matrices, x + w, y, x + w + 1, y + h, borderColor1.getValue(), borderColor2.getValue());
   }

   public static void renderHotBarItems(float tickDelta, class_332 context) {
      if (!(mc.field_1755 instanceof WindowsScreen)) {
         class_1657 playerEntity = mc.field_1724;
         if (playerEntity != null) {
            class_4587 matrices = context.method_51448();
            int i = mc.method_22683().method_4486() / 2;
            int o = mc.method_22683().method_4502() - 16 - 3;
            if (!mc.field_1724.method_6079().method_7960()) {
               if (lmode.getValue() == Hotbar.Mode.Mode1) {
                  renderHotbarItem(context, i - 109, o - 5, playerEntity.method_6079());
               } else {
                  renderHotbarItem(context, i - 111, o - 5, playerEntity.method_6079());
               }
            }

            for (int m = 0; m < 9; m++) {
               int n = i - 90 + m * 20 + 2;
               if (m == mc.field_1724.method_31548().field_7545) {
                  renderHotbarItem(context, n, o - 7, (class_1799)playerEntity.method_31548().field_7547.get(m));
               } else {
                  renderHotbarItem(context, n, o - 5, (class_1799)playerEntity.method_31548().field_7547.get(m));
               }
            }
         }
      }
   }

   private static void renderHotbarItem(class_332 context, int i, int j, class_1799 itemStack) {
      if (!itemStack.method_7960()) {
         context.method_51448().method_22903();
         context.method_51448().method_46416(i + 8, j + 12, 0.0F);
         context.method_51448().method_22905(0.9F, 0.9F, 1.0F);
         context.method_51448().method_46416(-(i + 8), -(j + 12), 0.0F);
         context.method_51427(itemStack, i, j);
         context.method_51431(mc.field_1772, itemStack, i, j);
         context.method_51448().method_22909();
      }
   }

   public static void renderXpBar(int x, class_4587 matrices) {
      mc.method_16011().method_15396("expBar");
      mc.method_16011().method_15407();
      if (mc.field_1724.field_7520 > 0) {
         mc.method_16011().method_15396("expLevel");
         String string = mc.field_1724.field_7520 + "";
         int k = (int)((mc.method_22683().method_4486() - FontRenderers.sf_bold_mini.getStringWidth(string)) / 2.0F);
         int l = mc.method_22683().method_4502() - 31 - 4;
         FontRenderers.sf_bold_mini.drawString(matrices, string, k, l, 8453920);
         mc.method_16011().method_15407();
      }
   }

   public static enum Mode {
      Mode1,
      Mode2;
   }
}
