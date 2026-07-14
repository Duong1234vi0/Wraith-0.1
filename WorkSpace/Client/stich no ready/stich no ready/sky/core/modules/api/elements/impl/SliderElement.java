package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.MathHelper;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class SliderElement extends Element {
   private final SliderSetting setting;
   private boolean drag;
   private float anim;

   public SliderElement(SliderSetting setting) {
      this.setting = setting;
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      this.setHeight(24.0F);
      String valueStr = (double)(Float)this.setting.get() == Math.floor((double)(Float)this.setting.get()) ? String.valueOf((int)(Float)this.setting.get()) : String.valueOf(this.setting.get());
      float valueBoxH = 10.0F;
      float valuePad = 3.0F;
      float valueWidth = Fonts.sfregular[12].getWidth(valueStr);
      float valueBoxW = Math.max(7.5F, valueWidth + valuePad * 2.0F - 2.0F);
      float valueX = this.getX() + this.getWidth() - valueBoxW - 4.0F;
      float textWidth = this.getWidth() - valueBoxW - 15.5F;
      Fonts.sfregular[14].drawScrolledString(stack, this.setting.getName(), this.getX() + 5.0F, this.getY() + 2.0F, textWidth, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha), MathUtil.isHovered(mouseX, mouseY, this.getX() + 6.0F, this.getY(), textWidth, Fonts.sf_regular[14].getHeight() + 2.0F), this.getScrollState());
      float targetWidth = (this.getWidth() - 15.0F) * ((Float)this.setting.get() - this.setting.min) / (this.setting.max - this.setting.min);
      this.anim = MathUtil.fast(this.anim, targetWidth, 20.0F);
      RenderUtil.drawRoundedRectangle(valueX, this.getY() + (this.getHeight() - valueBoxH) / 2.0F - 8.0F, valueBoxW, valueBoxH, 2.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SLIDER_WINDOW), ThemeEditor.getAlpha(ThemeSettings.SLIDER_WINDOW) / 255.0F * alpha));
      float textX = valueX + (valueBoxW - valueWidth) * 0.5F;
      Fonts.sfregular[12].drawString(stack, valueStr, (double)(textX + 0.2F), (double)(this.getY() + 3.0F), ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha));
      RenderUtil.drawOutlineRectangle(this.getX() + 5.0F, this.getY() + 12.5F, this.getWidth() - 10.5F, 3.0F, 1.5F, ThemeEditor.getColor(ThemeSettings.OUTLINE), ThemeEditor.getAlpha(ThemeSettings.OUTLINE) * 0.42F * alpha);
      RenderUtil.drawRoundedRectangle(this.getX() + 5.0F, this.getY() + 12.0F, this.anim + 2.0F, 4.0F, 1.5F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SLIDER), ThemeEditor.getAlpha(ThemeSettings.SLIDER) / 255.0F * alpha));
      RenderUtil.drawRoundedRectangle(this.getX() + 4.5F + this.anim, this.getY() + 10.5F, 7.0F, 7.0F, 2.5F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SLIDER_CIRCLE), ThemeEditor.getAlpha(ThemeSettings.SLIDER_CIRCLE) / 255.0F * alpha));
   }

   private void applyMouseX(float mouseX) {
      float width = this.getWidth() - 17.0F;
      float startCenter = this.getX() + 8.5F;
      float ratio = MathHelper.clamp((mouseX - startCenter) / width, 0.0F, 1.0F);
      float value = this.setting.min + ratio * (this.setting.max - this.setting.min);
      this.setting.set(MathHelper.clamp(MathUtil.round(value, this.setting.increment), this.setting.min, this.setting.max));
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (button == 0 && MathUtil.isHovered(mouseX, mouseY, this.getX() + 5.0F, this.getY() + 10.0F, this.getWidth() - 10.5F, 6.0F)) {
         this.drag = true;
         this.applyMouseX(mouseX);
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         this.setting.set(this.setting.defaultVal);
      }

   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.drag = false;
      }

   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      if (button == 0) {
         this.drag = false;
      }

   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (button == 0 && this.drag) {
         this.applyMouseX((float)mouseX);
         return true;
      } else {
         return false;
      }
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (Screen.hasControlDown() && this.isHovered((float)mouseX, (float)mouseY)) {
         float step = this.setting.increment;
         float rawValue = (Float)this.setting.get() + ((float)delta > 0.0F ? step : -step);
         float roundedValue = MathUtil.round(rawValue, step);
         float clampedValue = MathHelper.clamp(roundedValue, this.setting.min, this.setting.max);
         this.setting.set(clampedValue);
         return true;
      } else {
         return false;
      }
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   public void setDrag(boolean drag) {
      this.drag = drag;
   }

   public void setAnim(float anim) {
      this.anim = anim;
   }
}
