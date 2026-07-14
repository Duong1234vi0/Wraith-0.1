package sky.core.utils.managers.impl.dragmanager.elements.impl;

import com.adl.nativeprotect.Native;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.HashMap;
import java.util.Map;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class DraggingModeElement extends Element {
   private final ModeSetting setting;
   private final Map colorAnimations;

   public DraggingModeElement(ModeSetting setting) {
      this.setting = setting;
      this.colorAnimations = new HashMap();
      String[] var2 = setting.strings;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String mode = var2[var4];
         this.colorAnimations.put(mode, new AnimationUtil(mode.equals(setting.get()) ? 1.0F : 0.0F, 10.0F, Easings.LINEAR));
      }

   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      float offsetX = 0.0F;
      float offsetY = 0.0F;
      float totalHeight = 0.0F;
      Fonts.sf_medium[12].drawString(stack, this.setting.getName(), (double)(this.getX() + 3.0F), (double)(this.getY() + 5.0F), ThemeEditor.getColor(ThemeSettings.TEXT));
      String[] var8 = this.setting.strings;
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         String text = var8[var10];
         float boxWidth = Fonts.sf_regular[12].getWidth(text) + 4.0F;
         float boxHeight = Fonts.sf_regular[12].getHeight() + 6.0F;
         if (offsetX + boxWidth >= this.getWidth() + 20.0F) {
            offsetX = 0.0F;
            offsetY += boxHeight + 1.0F;
         }

         AnimationUtil animation = (AnimationUtil)this.colorAnimations.get(text);
         animation.update(text.equals(this.setting.get()) ? 1.0F : 0.0F);
         float progress = animation.getValue();
         int currentBg = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.BUTTON), ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), progress);
         int currentText = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE), progress);
         RenderUtil.drawRoundedRectangle(this.getX() + offsetX + 2.0F, this.getY() + 11.0F + offsetY, boxWidth, boxHeight - 0.5F, 1.5F, ColorUtil.applyOpacity(currentBg, text.equals(this.setting.get()) ? ThemeEditor.getAlpha(ThemeSettings.BUTTON) / 255.0F * alpha : ThemeEditor.getAlpha(ThemeSettings.BUTTON_INACTIVE) / 255.0F * alpha));
         float rectCenterX = this.getX() + offsetX + boxWidth / 2.0F;
         float rectCenterY = this.getY() + 11.0F + offsetY + boxHeight / 2.0F + 0.5F;
         float textX = rectCenterX - Fonts.sf_regular[12].getWidth(text) / 2.0F;
         float textY = rectCenterY - Fonts.sf_regular[12].getHeight() / 2.0F + 0.5F;
         Fonts.sf_regular[12].drawString(stack, text, (double)(textX + 2.0F), (double)textY, ColorUtil.applyOpacity(currentText, text.equals(this.setting.get()) ? ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha : ThemeEditor.getAlpha(ThemeSettings.TEXT_INACTIVE) / 255.0F * alpha));
         offsetX += boxWidth + 1.0F;
         totalHeight = Math.max(totalHeight, offsetY + boxHeight);
      }

      this.setHeight(totalHeight + Fonts.sf_medium[12].getHeight() + 7.0F);
   }

   @Native
   public void mouseClicked(float mouseX, float mouseY, int button) {
      boolean changed = false;
      if (button == 0) {
         float offsetX = 0.0F;
         float offsetY = 0.0F;
         String[] var7 = this.setting.strings;
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            String text = var7[var9];
            float boxWidth = Fonts.sf_regular[12].getWidth(text) + 4.0F;
            float boxHeight = Fonts.sf_regular[12].getHeight() + 6.0F;
            if (offsetX + boxWidth >= this.getWidth() + 20.0F) {
               offsetX = 0.0F;
               offsetY += boxHeight + 1.0F;
            }

            if (MathUtil.isHovered(mouseX, mouseY, this.getX() + offsetX + 1.0F, this.getY() + 9.0F + offsetY, boxWidth + 1.0F, boxHeight + 2.0F)) {
               this.setting.set(text);
               changed = true;
               break;
            }

            offsetX += boxWidth + 1.0F;
         }
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY() - 1.0F, this.getWidth(), this.getHeight())) {
         this.setting.set(this.setting.defaultVal);
         changed = true;
      }

      if (changed) {
         this.colorAnimations.forEach((mode, anim) -> {
            anim.update(mode.equals(this.setting.get()) ? 1.0F : 0.0F);
         });
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public ModeSetting getSetting() {
      return this.setting;
   }
}
