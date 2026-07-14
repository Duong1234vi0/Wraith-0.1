package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import sky.core.modules.api.constructors.impl.ItemSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ItemElement extends Element implements Wrapper {
   private final ItemSetting setting;
   private final AnimationUtil stateAnimation;
   private final AnimationUtil backgroundAnimation;
   private boolean selected;

   public ItemElement(ItemSetting setting) {
      this.stateAnimation = new AnimationUtil(0.0F, 8.0F, Easings.QUAD_IN_OUT);
      this.backgroundAnimation = new AnimationUtil(0.0F, 8.0F, Easings.QUAD_IN_OUT);
      this.selected = false;
      this.setting = setting;
      this.stateAnimation.setValue((Boolean)setting.get() ? 1.0F : 0.0F);
      this.backgroundAnimation.setValue(0.0F);
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      this.backgroundAnimation.update(this.selected ? 1.0F : 0.0F);
      int bgColor = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.FIELD), ThemeEditor.getColor(ThemeSettings.FIELD_INACTIVE), this.backgroundAnimation.getValue());
      RenderUtil.drawRoundedRectangle(this.getX(), this.getY(), 108.0F, 15.0F, 2.5F, bgColor);
      RenderUtil.drawStack(this.setting.getItemStack(), this.getX() + 3.0F, this.getY() + 3.5F, 0.5F);
      Fonts.sf_regular[14].drawScrolledString(stack, this.setting.getName(), this.getX() + 13.0F, this.getY() + 6.0F, 80.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha), MathUtil.isHovered(mouseX, mouseY, this.getX() + 13.0F, this.getY(), 80.0F, Fonts.sf_regular[14].getHeight() + 2.0F), this.getScrollState());
      RenderUtil.drawRoundedRectangle(this.getX() + 96.0F, this.getY() + 3.0F, 9.0F, 9.0F, 2.5F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), ThemeEditor.getAlpha(ThemeSettings.BUTTON_INACTIVE) / 255.0F * 0.55F * alpha));
      this.stateAnimation.update((Boolean)this.setting.get() ? 1.0F : 0.0F);
      float iconX = this.getX() + 98.5F;
      float iconY = this.getY() + 5.5F;
      float scale = this.stateAnimation.getValue() < 0.5F ? 1.0F - this.stateAnimation.getValue() * 2.0F : (this.stateAnimation.getValue() - 0.5F) * 2.0F;
      float activeAlpha = ThemeEditor.getAlpha(ThemeSettings.INDICATOR) / 255.0F * this.stateAnimation.getValue();
      float inactiveAlpha = ThemeEditor.getAlpha(ThemeSettings.INDICATOR_INACTIVE) / 255.0F * (1.0F - this.stateAnimation.getValue());
      RenderUtil.scaleStart(iconX + 3.0F, iconY + 3.0F, scale);
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/check.png"), iconX - 1.0F, iconY - 1.0F, 6.0F, 6.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.INDICATOR), activeAlpha));
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/xmark.png"), iconX, iconY, 4.0F, 4.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.INDICATOR_INACTIVE), inactiveAlpha));
      RenderUtil.scaleEnd();
      this.setHeight(20.0F);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (button == 0 && MathUtil.isHovered(mouseX, mouseY, this.getX() + 96.0F, this.getY() + 3.0F, 9.0F, 9.0F)) {
         if (this.stateAnimation.isAlive() && this.stateAnimation.getValue() != this.stateAnimation.getTarget()) {
            return;
         }

         this.setting.set(!(Boolean)this.setting.get());
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }

   public ItemSetting getSetting() {
      return this.setting;
   }

   public AnimationUtil getStateAnimation() {
      return this.stateAnimation;
   }

   public AnimationUtil getBackgroundAnimation() {
      return this.backgroundAnimation;
   }

   public boolean isSelected() {
      return this.selected;
   }
}
