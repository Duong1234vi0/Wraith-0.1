package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.KeyMapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class BindElement extends Element {
   final BindSetting setting;
   public boolean activated;

   public BindElement(BindSetting setting) {
      this.setting = setting;
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      String bind = this.activated ? "..." : ((Integer)this.setting.get() != -1 ? KeyMapper.getKey((Integer)this.setting.get()) : "None");
      float boxWidth = Fonts.sf_regular[13].getWidth(bind) + 5.0F;
      float boxHeight = 11.0F;
      float textWidth = Math.max(20.0F, this.getWidth() - boxWidth - 11.0F);
      Fonts.sf_regular[13].drawScrolledString(stack, this.setting.getName(), this.getX() + 6.0F, this.getY() + 3.0F, textWidth, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha), MathUtil.isHovered(mouseX, mouseY, this.getX() + 6.0F, this.getY(), textWidth, Fonts.sf_regular[14].getHeight() + 2.0F), this.getScrollState());
      int currentBg = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.BUTTON), ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), alpha);
      float x = this.getX() + this.getWidth() - boxWidth - 5.0F;
      float y = this.getY() + this.getHeight() - boxHeight;
      float bgAlpha = ThemeEditor.getAlpha(ThemeSettings.BUTTON_INACTIVE) / 255.0F * alpha;
      int finalBg = ColorUtil.applyOpacity(currentBg, bgAlpha);
      RenderUtil.drawRoundedRectangle(x, y - 4.5F, boxWidth, boxHeight, 2.0F, finalBg);
      float rectCenterX = x + boxWidth / 2.0F;
      float rectCenterY = y - 4.0F + boxHeight / 2.0F;
      float textX = rectCenterX - Fonts.sf_medium[13].getWidth(bind) / 2.0F;
      int textY = (int)(rectCenterY - Fonts.sf_medium[13].getHeight() / 2.0F + 1.0F);
      Fonts.sf_medium[13].drawString(stack, bind, (double)textX, (double)textY, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha));
      this.setHeight(15.0F);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.activated) {
         if (keyCode == 261 || keyCode == 256) {
            this.setting.set(-1);
            this.activated = false;
            return;
         }

         this.setting.set(keyCode);
         this.activated = false;
      }

      super.keyPressed(keyCode, scanCode, modifiers);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      float boxWidth = Fonts.sf_regular[13].getWidth((Integer)this.setting.get() != -1 ? KeyMapper.getKey((Integer)this.setting.get()) : "None") + 5.0F;
      float boxHeight = 11.0F;
      float boxX = this.getX() + this.getWidth() - boxWidth - 5.0F;
      float boxY = this.getY() + this.getHeight() - boxHeight - 6.0F;
      if (this.activated) {
         if (button == 0 || button == 1) {
            return;
         }

         if (button >= 1) {
            this.setting.set(button);
            this.activated = false;
            return;
         }
      }

      if (MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         if (button == 0) {
            if (mouseX >= boxX && mouseX <= boxX + boxWidth && mouseY >= boxY && mouseY <= boxY + boxHeight) {
               this.activated = true;
            }
         } else if (button == 2) {
            this.setting.set(-1);
            this.activated = false;
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }
}
