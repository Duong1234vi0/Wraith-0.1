package sky.core.utils.managers.impl.notificationmanager.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.StringTextComponent;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.managers.impl.notificationmanager.AbstractNotification;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class FontNotification extends AbstractNotification {
   private final String categoryIcon;
   private final Integer color;
   private static final int TEXT_COLOR = 16777215;

   public FontNotification(String categoryIcon, String message, int color) {
      super(new StringTextComponent(message));
      this.categoryIcon = categoryIcon;
      this.color = color;
   }

   public void render(float x, float y, MatrixStack matrixStack) {
      this.initYIfNeeded(y);
      boolean expired = this.isExpired();
      this.alphaAnimation.update(expired ? 0.0F : 1.0F);
      this.yAnimation.update(this.forceExpire && expired ? y - 2.0F : y);
      float animatedY = this.yAnimation.getValue();
      float animatedAlpha = this.alphaAnimation.getValue();
      boolean ordinaryMode = Interface.isNewHud();
      if (!(animatedAlpha <= 0.01F)) {
         String displayMessage = this.message.getString();
         float cardHeight;
         if (!ordinaryMode) {
            cardHeight = Fonts.icons[16].getWidth(this.categoryIcon) + Fonts.sf_medium[12].getWidth(displayMessage) + 5.0F;
            int iconColor;
            switch (this.categoryIcon) {
               case "I":
               case "L":
                  iconColor = ColorUtil.applyOpacity(16711680, animatedAlpha);
                  break;
               case "H":
                  iconColor = ColorUtil.applyOpacity(65280, animatedAlpha);
                  break;
               case "M":
                  iconColor = ColorUtil.applyOpacity(16753920, animatedAlpha);
                  break;
               default:
                  iconColor = ColorUtil.applyOpacity(16777215, animatedAlpha);
            }

            RenderUtil.drawBlurredRoundedRectangle(x, animatedY, cardHeight + 12.5F, 14.0F, 4.0F, (Boolean)NotificationRender.alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), animatedAlpha);
            Fonts.icons[16].drawString(matrixStack, this.categoryIcon, (double)((int)x + 5), (double)(animatedY + Fonts.icons[16].getHeight()), iconColor);
            RenderUtil.drawMinecraftRectangle(matrixStack, (float)((int)x) + Fonts.icons[16].getWidth(this.categoryIcon) + 8.5F, animatedY + 3.5F, 0.5F, 7.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * animatedAlpha));
            Fonts.sf_medium[12].drawString(matrixStack, displayMessage, (double)((float)((int)x) + Fonts.icons[16].getWidth(this.categoryIcon) + 12.0F), (double)(animatedY + Fonts.sf_semibold[12].getHeight() + 2.0F), ColorUtil.applyOpacity(this.color, animatedAlpha));
         } else {
            cardHeight = 19.0F;
            float cardRadius = 5.0F;
            float paddingX = 8.0F;
            float paddingY = 6.0F;
            float gap = 4.0F;
            float iconSize = 7.0F;
            String prefix = "";
            String moduleName = displayMessage;
            String status = "";
            boolean hasPrefix = displayMessage.startsWith("Module ");
            boolean hasStatus = displayMessage.contains(" enabled") || displayMessage.contains(" disabled");
            if (hasPrefix) {
               prefix = "Module ";
               moduleName = displayMessage.substring(7);
            }

            if (hasStatus) {
               int enabledIdx = moduleName.indexOf(" enabled");
               int disabledIdx = moduleName.indexOf(" disabled");
               String actualName;
               if (enabledIdx != -1) {
                  actualName = moduleName.substring(0, enabledIdx);
                  status = moduleName.substring(enabledIdx);
                  moduleName = actualName;
               } else if (disabledIdx != -1) {
                  actualName = moduleName.substring(0, disabledIdx);
                  status = moduleName.substring(disabledIdx);
                  moduleName = actualName;
               }
            }

            float prefixWidth = Fonts.sfregular[12].getWidth(prefix);
            float moduleNameWidth = Fonts.sfregular[12].getWidth(moduleName);
            float statusWidth = Fonts.sfregular[12].getWidth(status);
            float textWidth = prefixWidth + moduleNameWidth + statusWidth;
            float cardWidth = paddingX + iconSize + gap + textWidth + paddingX;
            int cardBgColor = ThemeEditor.getColor(ThemeSettings.WINDOW_BG);
            int iconColor = ThemeEditor.getColor(ThemeSettings.LOGO);
            int bgColor = ColorUtil.darken(iconColor, 0.1F);
            int bgWithAlpha = ColorUtil.applyOpacity(bgColor, animatedAlpha);
            int whiteColor = ColorUtil.applyOpacity(16777215, animatedAlpha);
            int blueColor = ColorUtil.applyOpacity(iconColor, animatedAlpha);
            int iconWithAlpha = ColorUtil.applyOpacity(iconColor, animatedAlpha);
            RenderUtil.drawRoundedRectangle(x, animatedY, cardWidth, cardHeight, cardRadius, bgWithAlpha);
            float iconX = x + paddingX;
            float iconY = animatedY + paddingY + (iconSize - 7.0F) / 2.0F;
            if (this.categoryIcon != null && !this.categoryIcon.isEmpty()) {
               Fonts.skycore[13].drawString(matrixStack, this.categoryIcon, (double)iconX, (double)(iconY + 2.5F), iconWithAlpha);
            }

            float textY = animatedY + paddingY + (cardHeight - paddingY * 2.0F - Fonts.sfregular[12].getHeight()) / 2.0F + 1.0F;
            float textX = iconX + iconSize + gap;
            if (!prefix.isEmpty()) {
               Fonts.sfregular[12].drawString(matrixStack, prefix, (double)textX, (double)textY, whiteColor);
               textX += prefixWidth;
            }

            Fonts.sfregular[12].drawString(matrixStack, moduleName, (double)textX, (double)textY, blueColor);
            textX += moduleNameWidth;
            if (!status.isEmpty()) {
               Fonts.sfregular[12].drawString(matrixStack, status, (double)textX, (double)textY, whiteColor);
            }

         }
      }
   }

   public String getCategoryIcon() {
      return this.categoryIcon;
   }

   public Integer getColor() {
      return this.color;
   }
}
