package sky.core.utils.managers.impl.notificationmanager.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.notificationmanager.AbstractNotification;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ImageNotification extends AbstractNotification {
   private final ResourceLocation image;
   private final Integer overrideColor;
   private static final int TEXT_COLOR = -1;

   public ImageNotification(ResourceLocation image, ITextComponent message) {
      super(message);
      this.image = image;
      this.overrideColor = null;
   }

   public ImageNotification(ResourceLocation image, ITextComponent message, int overrideColor) {
      super(message);
      this.image = image;
      this.overrideColor = overrideColor;
   }

   public void render(float x, float y, MatrixStack matrixStack) {
      this.initYIfNeeded(y);
      boolean expired = this.isExpired();
      this.alphaAnimation.update(expired ? 0.0F : 1.0F);
      this.yAnimation.update(this.forceExpire && expired ? y - 4.0F : y);
      float animatedY = this.yAnimation.getValue();
      float animatedAlpha = this.alphaAnimation.getValue();
      boolean ordinaryMode = Interface.isNewHud();
      if (!(animatedAlpha <= 0.01F)) {
         if (!ordinaryMode) {
            RenderUtil.drawBlurredRoundedRectangle(x, animatedY, Fonts.sf_semibold[12].getWidth(this.message.getString()) + 24.0F, 13.0F, 4.0F, (Boolean)NotificationRender.alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), animatedAlpha);
            int offsetX = (int)(x + 4.0F);
            int offsetY = (int)(animatedY + 2.5F);
            if (this.overrideColor != null && this.image.getPath().contains("potion")) {
               int colored = ColorUtil.applyOpacity(this.overrideColor, animatedAlpha);
               RenderUtil.drawPotionLiquid(colored, (float)offsetX, (float)offsetY, 8.0F, 8.0F);
            }

            RenderUtil.drawImage2D(this.image, (float)offsetX, (float)offsetY, 8.0F, 8.0F, ColorUtil.applyOpacity(-1, animatedAlpha));
            RenderUtil.drawMinecraftRectangle(matrixStack, (float)((int)x + 16), animatedY + 3.0F, 0.5F, 7.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * animatedAlpha));
            Fonts.sf_semibold[12].drawText(matrixStack, this.message, (double)((float)((int)x) + 20.0F), (double)(animatedY + Fonts.sf_semibold[12].getHeight() + 1.5F), ColorUtil.applyOpacity(-1, 1.0F * animatedAlpha));
         } else {
            float cardHeight = 19.0F;
            float cardRadius = 5.0F;
            float paddingX = 8.0F;
            float paddingY = 6.0F;
            float gap = 4.0F;
            float iconSize = 10.0F;
            String text = this.message.getString();
            float textWidth = Fonts.sfregular[12].getWidth(text);
            float cardWidth = paddingX + iconSize + gap + textWidth + paddingX;
            float centeredX = ((float)Wrapper.mc.getMainWindow().getScaledWidth() - cardWidth) / 2.0F;
            int cardBgColor = ThemeEditor.getColor(ThemeSettings.LOGO);
            int bgColor = ColorUtil.darken(cardBgColor, 0.1F);
            int bgWithAlpha = ColorUtil.applyOpacity(bgColor, animatedAlpha);
            int textWithAlpha = ColorUtil.applyOpacity(-1, animatedAlpha);
            RenderUtil.drawRoundedRectangle(centeredX, animatedY, cardWidth, cardHeight, cardRadius, bgWithAlpha);
            float textHeight = Fonts.sfregular[12].getHeight();
            float contentHeight = Math.max(iconSize, textHeight);
            float contentY = animatedY + (cardHeight - contentHeight) / 2.0F;
            float iconX = centeredX + paddingX;
            float iconY = contentY + (contentHeight - iconSize) / 2.0F;
            if (this.overrideColor != null && this.image.getPath().contains("potion")) {
               int colored = ColorUtil.applyOpacity(this.overrideColor, animatedAlpha);
               RenderUtil.drawPotionLiquid(colored, (float)((int)iconX), (float)((int)iconY), (float)((int)iconSize), (float)((int)iconSize));
            }

            RenderUtil.drawImage2D(this.image, (float)((int)iconX), (float)((int)iconY) - 0.5F, (float)((int)iconSize), (float)((int)iconSize), ColorUtil.applyOpacity(-1, animatedAlpha));
            float textX = iconX + iconSize + gap;
            float textY = contentY + (contentHeight - textHeight) / 2.0F + 1.0F;
            int logoColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), animatedAlpha);
            int firstQuote = text.indexOf(39);
            int secondQuote = text.indexOf(39, firstQuote + 1);
            String beforeDot;
            if (firstQuote != -1 && secondQuote != -1 && firstQuote < secondQuote) {
               beforeDot = text.substring(0, firstQuote);
               String quotedText = text.substring(firstQuote + 1, secondQuote);
               String afterQuote = text.substring(secondQuote + 1);
               float beforeWidth = Fonts.sfregular[12].getWidth(beforeDot);
               float quotedWidth = Fonts.sfregular[12].getWidth(quotedText);
               if (!beforeDot.isEmpty()) {
                  Fonts.sfregular[12].drawString(matrixStack, beforeDot, (double)textX, (double)textY, textWithAlpha);
               }

               Fonts.sfregular[12].drawString(matrixStack, quotedText, (double)(textX + beforeWidth), (double)textY, logoColor);
               if (!afterQuote.isEmpty()) {
                  Fonts.sfregular[12].drawString(matrixStack, afterQuote, (double)(textX + beforeWidth + quotedWidth), (double)textY, textWithAlpha);
               }
            } else if (this.overrideColor != null && text.contains("зачарован:") && text.endsWith("●")) {
               beforeDot = text.substring(0, text.length() - 1);
               Fonts.sfregular[12].drawString(matrixStack, beforeDot, (double)textX, (double)textY, textWithAlpha);
               float beforeDotWidth = Fonts.sfregular[12].getWidth(beforeDot);
               int dotColor = ColorUtil.applyOpacity(this.overrideColor, animatedAlpha);
               Fonts.sfregular[12].drawString(matrixStack, "●", (double)(textX + beforeDotWidth), (double)textY, dotColor);
            } else {
               Fonts.sfregular[12].drawString(matrixStack, text, (double)textX, (double)textY, textWithAlpha);
            }

         }
      }
   }
}
