package unique.core.features.hud.impl;

import java.awt.Color;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_332;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;

public class PotionHud extends HudElement {
   public PotionHud() {
      super("Potions HUD", 100, 100);
   }

   public static String getDuration(class_1293 pe) {
      if (pe.method_48559()) {
         return "*:*";
      } else {
         int var1 = pe.method_5584();
         int mins = var1 / 1200;
         String sec = String.format("%02d", var1 % 1200 / 20);
         return mins + ":" + sec;
      }
   }

   private void renderBackground(class_332 context, float x, float y, float width, float height) {
      float round = HudEditor.hudRound.getValue();
      if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
         Render2DEngine.drawHudBase2(
            context.method_51448(),
            x,
            y,
            width,
            height,
            round,
            Math.max(HudEditor.blurStrength.getValue(), 12.0F),
            Math.min(HudEditor.blurOpacity.getValue() * 1.2F, 1.0F),
            0.85F
         );
      } else {
         Render2DEngine.drawHudBase(context.method_51448(), x, y, width, height, round);
      }
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      int hudColor = HudEditor.getColor(0).getRGB();
      float x = this.getPosX();
      float y = this.getPosY();
      float rowHeight = 12.0F;
      float headerHeight = 16.0F;
      float headerGap = 3.0F;
      float bottomExtra = 2.0F;
      float maxLeftWidth = 0.0F;
      float maxRightWidth = 0.0F;
      int count = 0;

      for (class_1293 potionEffect : mc.field_1724.method_6026()) {
         class_1291 potion = (class_1291)potionEffect.method_5579().comp_349();
         String leftText = potion.method_5560().getString() + " " + (potionEffect.method_5578() + 1);
         float leftWidth = 12.0F + FontRenderers.sf_bold_mini.getStringWidth(leftText);
         if (leftWidth > maxLeftWidth) {
            maxLeftWidth = leftWidth;
         }

         String rightText = getDuration(potionEffect);
         float rightWidth = FontRenderers.sf_bold_mini.getStringWidth(rightText);
         if (rightWidth > maxRightWidth) {
            maxRightWidth = rightWidth;
         }

         count++;
      }

      float width2 = Math.max(70.0F, 6.0F + maxLeftWidth + 6.0F + maxRightWidth + 6.0F);
      float height2 = count > 0 ? headerHeight + headerGap + rowHeight * count + bottomExtra : headerHeight + 4.0F;
      this.renderBackground(context, x, y, width2, height2);
      String title = "Potions ";
      FontRenderers.monsterrat.drawString(context.method_51448(), title, x + 6.0F, y + 6.3F, HudEditor.getColor(0).getRGB());
      String icon = "E";
      float iconWidth = FontRenderers.icons.getStringWidth(icon);
      float iconHeight = 5.0F;
      float iconX = x + width2 - 6.0F - iconWidth;
      float iconY = y + (headerHeight - iconHeight) / 2.0F + 1.0F;
      FontRenderers.icons.drawString(context.method_51448(), icon, iconX, iconY, HudEditor.getColor(0).getRGB());
      int i = 0;

      for (class_1293 potionEffect : mc.field_1724.method_6026()) {
         class_1291 potionx = (class_1291)potionEffect.method_5579().comp_349();
         float rowY = y + headerHeight + headerGap + i * rowHeight;
         float rowCenter = rowY + rowHeight / 2.0F;
         float leftX = x + 6.0F;
         float iconY2 = rowCenter - 6.5F;
         context.method_51448().method_22903();
         context.method_51448().method_46416(leftX, iconY2, 0.0F);
         context.method_25298(0, 0, 0, 8, 8, mc.method_18505().method_18663(potionEffect.method_5579()));
         context.method_51448().method_22909();
         String leftTextx = potionx.method_5560().getString() + " " + (potionEffect.method_5578() + 1);
         FontRenderers.sf_bold_mini.drawString(context.method_51448(), leftTextx, leftX + 8.0F + 4.0F, iconY2 + 2.5F, -1);
         String rightText = getDuration(potionEffect);
         float rightWidth = FontRenderers.sf_bold_mini.getStringWidth(rightText);
         float rightX = x + width2 - 6.0F - rightWidth;
         FontRenderers.sf_bold_mini.drawString(context.method_51448(), rightText, rightX, iconY2 + 2.5F, Color.WHITE.getRGB());
         i++;
      }

      this.setBounds(this.getPosX(), this.getPosY(), width2, height2);
   }
}
