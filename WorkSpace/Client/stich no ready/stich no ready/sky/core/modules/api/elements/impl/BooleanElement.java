package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import sky.core.SkyCore;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class BooleanElement extends Element {
   private final BooleanSetting setting;
   private final AnimationUtil stateAnimation;
   private boolean bind;

   public BooleanElement(BooleanSetting setting) {
      this.stateAnimation = new AnimationUtil(0.0F, 8.0F, Easings.QUAD_IN_OUT);
      this.setting = setting;
      this.stateAnimation.setValue((Boolean)setting.get() ? 1.0F : 0.0F);
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      Fonts.sf_regular[14].drawScrolledString(stack, this.setting.getName(), this.getX() + 5.0F, this.getY() + 2.0F, 80.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha), MathUtil.isHovered(mouseX, mouseY, this.getX() + 6.0F, this.getY(), 80.0F, Fonts.sf_regular[14].getHeight() + 2.0F), this.getScrollState());
      this.setHeight(15.0F);
      float toggleX = this.getX() + this.getWidth() - 16.0F;
      float toggleY = this.getY() + this.getHeight() - 10.0F;
      this.stateAnimation.update((Boolean)this.setting.get() ? 1.0F : 0.0F);
      int currentBg = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.BUTTON), ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), alpha);
      int currentText = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE), alpha);
      RenderUtil.drawOutlineRectangle(toggleX + 0.5F, toggleY - 6.0F, 10.0F, 10.0F, 2.5F, currentBg, 34.0F * alpha);
      float scale = this.stateAnimation.getValue() < 0.5F ? 1.0F - this.stateAnimation.getValue() * 2.0F : (this.stateAnimation.getValue() - 0.5F) * 2.0F;
      float activeAlpha = ThemeEditor.getAlpha(ThemeSettings.INDICATOR) / 255.0F * alpha;
      float inactiveAlpha = ThemeEditor.getAlpha(ThemeSettings.INDICATOR_INACTIVE) / 255.0F * alpha;
      RenderUtil.scaleStart(toggleX + 7.0F, toggleY - 1.0F, scale);
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/check.png"), toggleX + 2.5F, toggleY - 4.0F, 6.0F, 6.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.INDICATOR), activeAlpha * this.stateAnimation.getValue()));
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/xmark.png"), toggleX + 3.5F, toggleY - 3.0F, 4.0F, 4.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.INDICATOR_INACTIVE), inactiveAlpha * (1.0F - this.stateAnimation.getValue())));
      RenderUtil.scaleEnd();
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      float toggleX = this.getX() + this.getWidth() - 16.0F;
      float toggleY = this.getY() + (this.getHeight() - 10.0F) - 7.0F;
      if (MathUtil.isHovered(mouseX, mouseY, toggleX + 0.5F, toggleY + 0.5F, 10.0F, 10.0F)) {
         if (button == 0) {
            if (this.stateAnimation.isAlive() && this.stateAnimation.getValue() != this.stateAnimation.getTarget()) {
               return;
            }

            this.setting.set(!(Boolean)this.setting.get());
         } else if (button == 1) {
            SkyCore.getInstance().getDropDown().getBindingPanelManager().openForBoolean(this);
         }
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth() + 10.0F, this.getHeight())) {
         if (this.stateAnimation.isAlive() && this.stateAnimation.getValue() != this.stateAnimation.getTarget()) {
            return;
         }

         this.setting.set(this.setting.defaultVal);
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   public BooleanSetting getSetting() {
      return this.setting;
   }

   public AnimationUtil getStateAnimation() {
      return this.stateAnimation;
   }

   public boolean isBind() {
      return this.bind;
   }

   public void setBind(boolean bind) {
      this.bind = bind;
   }
}
