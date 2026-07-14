package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.constructors.impl.TextSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.font.Fonts;

public class TextElement extends Element {
   private final TextSetting setting;

   public TextElement(TextSetting setting) {
      this.setting = setting;
   }

   public void render(MatrixStack matrixStack, float mouseX, float mouseY, float alpha) {
      super.render(matrixStack, mouseX, mouseY, alpha);
      float xPos = this.getX() + (this.getWidth() - Fonts.sf_semibold[17].getWidth(this.setting.getName())) / 2.0F;
      Fonts.sfregular[17].drawString(matrixStack, this.setting.getName(), (double)xPos, (double)(this.getY() + 4.0F), ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha));
      this.setHeight(11.0F + Fonts.sf_regular[14].getHeight());
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }
}
