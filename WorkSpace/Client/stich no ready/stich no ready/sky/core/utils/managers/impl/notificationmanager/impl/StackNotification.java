package sky.core.utils.managers.impl.notificationmanager.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.item.ItemStack;
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

public class StackNotification extends AbstractNotification {
   private final ItemStack stack;
   private final boolean autoSwapStyle;
   private static final int TEXT_COLOR = -1;

   public StackNotification(ItemStack stack, ITextComponent message) {
      this(stack, message, false);
   }

   public StackNotification(ItemStack stack, ITextComponent message, boolean autoSwapStyle) {
      super(message);
      this.stack = stack == null ? ItemStack.EMPTY : stack.copy();
      this.autoSwapStyle = autoSwapStyle;
   }

   public boolean isAutoSwapStyle() {
      return this.autoSwapStyle;
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
            RenderUtil.drawBlurredRoundedRectangle(x, animatedY, Fonts.sf_medium[12].getWidth(this.message.getString()) + 24.0F, 13.0F, 4.0F, (Boolean)NotificationRender.alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), animatedAlpha);
            int offsetX = (int)(x + 4.0F);
            int offsetY = (int)(animatedY + 2.5F);
            RenderUtil.drawStack(this.stack, (float)offsetX, (float)offsetY, 0.5F, animatedAlpha);
            RenderUtil.drawMinecraftRectangle(matrixStack, (float)((int)x + 16), animatedY + 3.0F, 0.5F, 7.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * animatedAlpha));
            Fonts.sf_medium[12].drawText(matrixStack, this.message, (double)((float)((int)x) + 20.0F), (double)(animatedY + Fonts.sf_medium[12].getHeight() + 1.5F), ColorUtil.applyOpacity(-1, animatedAlpha));
         } else {
            float cardHeight = 19.0F;
            float cardRadius = 5.0F;
            float paddingX = this.autoSwapStyle ? 6.0F : 8.0F;
            float paddingY = 6.0F;
            float gap = 4.0F;
            float iconSize = 10.0F;
            String text = this.message.getString();
            float textWidth = Fonts.sfregular[12].getWidth(text);
            float contentWidth = paddingX + iconSize + gap + textWidth + paddingX;
            int cardBgColor = ThemeEditor.getColor(ThemeSettings.LOGO);
            int bgColor = ColorUtil.darken(cardBgColor, 0.1F);
            int bgWithAlpha = ColorUtil.applyOpacity(bgColor, animatedAlpha);
            int textWithAlpha = ColorUtil.applyOpacity(-1, animatedAlpha);
            float drawX = this.autoSwapStyle ? ((float)Wrapper.mc.getMainWindow().getScaledWidth() - contentWidth) / 2.0F : x;
            RenderUtil.drawRoundedRectangle(drawX, animatedY, contentWidth, cardHeight, cardRadius, bgWithAlpha);
            float textHeight = Fonts.sfregular[12].getHeight();
            float contentHeight = Math.max(iconSize, textHeight);
            float contentY = animatedY + (cardHeight - contentHeight) / 2.0F;
            float iconX = drawX + paddingX;
            float iconY = contentY + (contentHeight - iconSize) / 2.0F;
            RenderUtil.drawStack(this.stack, (float)((int)iconX), (float)((int)iconY), 0.625F, animatedAlpha);
            float textX = iconX + iconSize + gap;
            float textY = contentY + (contentHeight - textHeight) / 2.0F + 1.0F;
            int logoColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), animatedAlpha);
            int firstQuote = text.indexOf(39);
            int secondQuote = text.indexOf(39, firstQuote + 1);
            if (firstQuote != -1 && secondQuote != -1 && firstQuote < secondQuote) {
               String beforeQuote = text.substring(0, firstQuote);
               String quotedText = text.substring(firstQuote + 1, secondQuote);
               String afterQuote = text.substring(secondQuote + 1);
               float beforeWidth = Fonts.sfregular[12].getWidth(beforeQuote);
               float quotedWidth = Fonts.sfregular[12].getWidth(quotedText);
               if (!beforeQuote.isEmpty()) {
                  Fonts.sfregular[12].drawString(matrixStack, beforeQuote, (double)textX, (double)textY, textWithAlpha);
               }

               Fonts.sfregular[12].drawString(matrixStack, quotedText, (double)(textX + beforeWidth), (double)textY, logoColor);
               if (!afterQuote.isEmpty()) {
                  Fonts.sfregular[12].drawString(matrixStack, afterQuote, (double)(textX + beforeWidth + quotedWidth), (double)textY, textWithAlpha);
               }
            } else {
               Fonts.sfregular[12].drawString(matrixStack, text, (double)textX, (double)textY, textWithAlpha);
            }

         }
      }
   }
}
