package sky.core.utils.managers.impl.dragmanager.elements.impl;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class DraggingBooleanElement extends Element {
   private final BooleanSetting setting;
   private final AnimationUtil animation;

   public DraggingBooleanElement(BooleanSetting setting) {
      this.setting = setting;
      this.animation = new AnimationUtil((Boolean)setting.get() ? 1.0F : 0.0F, 10.0F, Easings.LINEAR);
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      this.animation.update((Boolean)this.setting.get() ? 1.0F : 0.0F);
      super.render(stack, mouseX, mouseY, alpha);
      Fonts.sf_regular[12].drawString(stack, this.setting.getName(), (double)(this.getX() + 3.0F), (double)(this.getY() + 4.5F), ThemeEditor.getColor(ThemeSettings.TEXT));
      float progress = this.animation.getValue();
      int boxColor = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.BUTTON), ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), progress);
      int textColor = ColorUtil.interpolate(-1, ColorUtil.getColor(255, 255, 255, 175), progress);
      RenderUtil.drawRoundedRectangle(this.getX() + this.getWidth() - 18.0F, this.getY() + 2.0F, 15.0F, 8.0F, 3.0F, boxColor);
      float offsetX = progress * 7.0F;
      RenderUtil.drawRoundedRectangle(this.getX() + this.getWidth() - 17.0F + offsetX, this.getY() + 3.0F, 6.0F, 6.0F, 2.0F, textColor);
      this.setHeight(Math.max(10.0F, Fonts.sf_regular[12].getHeight()));
   }

   @Native
   public void mouseClicked(float mouseX, float mouseY, int button) {
      boolean inBounds = mouseX >= this.getX() + this.getWidth() - 18.0F && mouseX <= this.getX() + this.getWidth() - 4.0F && mouseY >= this.getY() + 1.5F && mouseY <= this.getY() + 9.5F;
      if (inBounds) {
         if (button == 0) {
            this.setting.set(!(Boolean)this.setting.get());
            this.animation.update((Boolean)this.setting.get() ? 1.0F : 0.0F);
         } else if (button == 2) {
            this.setting.set(this.setting.defaultVal);
            this.animation.update(this.setting.defaultVal ? 1.0F : 0.0F);
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   public BooleanSetting getSetting() {
      return this.setting;
   }
}
