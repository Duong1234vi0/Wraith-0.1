package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.constructors.impl.ButtonSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ButtonElement extends Element {
   private final ButtonSetting setting;
   private final String textOn;
   private final String textOff;
   private float width;
   private float height;
   private final AnimationUtil hoverAnimation;

   public ButtonElement(ButtonSetting setting, String textOff, String textOn) {
      this.hoverAnimation = new AnimationUtil(0.0F, 10.0F, Easings.LINEAR);
      this.setting = setting;
      this.textOn = textOn;
      this.textOff = textOff;
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      this.width = 94.0F;
      this.height = 13.0F;
      float toggleX = this.getX() + 4.5F;
      float toggleY = this.getY() - 2.0F;
      boolean isHovered = MathUtil.isHovered(mouseX, mouseY, toggleX, toggleY, this.width, this.height);
      this.hoverAnimation.update(isHovered ? 1.0F : 0.0F);
      int interpolatedBg = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.BUTTON), ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), this.hoverAnimation.getValue());
      float bgAlpha = (isHovered ? ThemeEditor.getAlpha(ThemeSettings.BUTTON) : ThemeEditor.getAlpha(ThemeSettings.BUTTON_INACTIVE)) / 255.0F * alpha;
      int finalBg = ColorUtil.applyOpacity(interpolatedBg, bgAlpha);
      RenderUtil.drawRoundedRectangle(toggleX, toggleY, this.width, this.height, 2.0F, finalBg);
      String displayText = (Boolean)this.setting.get() ? this.textOn : this.textOff;
      float textWidth = Fonts.sf_regular[12].getWidth(displayText);
      float rectCenterX = toggleX + this.width / 2.0F;
      int textX = (int)(rectCenterX - textWidth / 2.0F) + 1;
      float textY = this.getY() + (this.getHeight() - 15.5F);
      int interpolatedText = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE), this.hoverAnimation.getValue());
      float txtAlpha = (isHovered ? ThemeEditor.getAlpha(ThemeSettings.TEXT) : ThemeEditor.getAlpha(ThemeSettings.TEXT_INACTIVE)) / 255.0F * alpha;
      int finalText = ColorUtil.applyOpacity(interpolatedText, txtAlpha);
      Fonts.sfregular[12].drawString(stack, displayText, (double)textX, (double)textY, finalText);
      this.setHeight(19.0F);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (MathUtil.isHovered(mouseX, mouseY, this.getX() + 3.0F, this.getY() - 4.0F, this.width + 1.0F, this.height + 2.0F)) {
         if (button == 0) {
            this.setting.set(!(Boolean)this.setting.get());
         } else if (button == 2) {
            this.setting.set(this.setting.defaultVal);
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }
}
