package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ColorElement extends Element {
   private final ColorSetting setting;
   private final ColorPickerElement colorPicker;

   public ColorElement(ColorSetting setting) {
      this.setting = setting;
      this.colorPicker = new ColorPickerElement(setting);
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      Fonts.sf_regular[14].drawScrolledString(stack, this.setting.getName(), this.getX() + 5.0F, this.getY() + 2.0F, 80.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha), MathUtil.isHovered(mouseX, mouseY, this.getX() + 6.0F, this.getY(), 80.0F, Fonts.sf_medium[14].getHeight() + 2.0F), this.getScrollState());
      float selectedCircleRadius = 4.0F;
      RenderUtil.drawRoundedRectangle(this.getX() + this.getWidth() - 21.0F, this.getY() - 0.5F, selectedCircleRadius * 2.0F + 1.0F, selectedCircleRadius * 2.0F + 1.0F, selectedCircleRadius, ColorUtil.applyOpacity((Integer)this.setting.get(), this.setting.getAlpha() * alpha / 255.0F));
      this.setHeight(15.0F);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (button == 0 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY() - 1.0F, this.getWidth(), this.getHeight() - 7.0F)) {
         this.colorPicker.setColorPickMode(!this.colorPicker.isColorPickMode());
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         this.setting.set(this.setting.defaultVal);
         this.colorPicker.updateHSBFromColor();
      } else if (this.colorPicker.isColorPickMode()) {
         this.colorPicker.mouseClicked(mouseX, mouseY);
      }

   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      if (this.colorPicker.isColorPickMode()) {
         this.colorPicker.mouseReleased();
      }

   }

   public ColorSetting getSetting() {
      return this.setting;
   }

   public ColorPickerElement getColorPicker() {
      return this.colorPicker;
   }
}
