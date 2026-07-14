package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.HashMap;
import java.util.Iterator;
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

public class ModeElement extends Element {
   private final ModeSetting setting;
   private final Map colorAnimations;
   private static final int ACTIVE_TEXT_COLOR = ColorUtil.rgb(0, 0, 0);
   private static final int INACTIVE_TEXT_COLOR = ColorUtil.rgb(187, 187, 187);
   private static final int INACTIVE_BG_COLOR = ColorUtil.rgb(80, 80, 80);
   private static final float INACTIVE_BG_OPACITY = 0.35F;

   public ModeElement(ModeSetting setting) {
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
      int headerColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha);
      Fonts.sfregular[14].drawString(stack, this.setting.getName(), (double)(this.getX() + 6.0F), (double)(this.getY() + 2.0F), headerColor);
      String currentMode = (String)this.setting.get();
      float var10000 = this.getX() + this.getWidth() - 4.5F - Fonts.sfregular[14].getWidth(currentMode);
      String[] var11 = this.setting.strings;
      int var12 = var11.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         String text = var11[var13];
         float boxWidth = Fonts.sfregular[12].getWidth(text) + 5.0F;
         float boxHeight = Fonts.sfregular[12].getHeight() + 6.0F;
         if (offsetX + boxWidth >= this.getWidth() - 8.0F) {
            offsetX = 0.0F;
            offsetY += boxHeight + 2.0F;
         }

         AnimationUtil animation = (AnimationUtil)this.colorAnimations.get(text);
         animation.update(text.equals(this.setting.get()) ? 1.0F : 0.0F);
         float state = clamp01(animation.getValue());
         float offFactor = 1.0F - state;
         float boxX = this.getX() + 5.5F + offsetX;
         float boxY = this.getY() + 10.0F + offsetY - 0.5F;
         float boxW = boxWidth + 2.0F;
         float boxH = boxHeight + 1.0F;
         float radius = 2.5F;
         int activeBgColor = ThemeEditor.getColor(ThemeSettings.BUTTON);
         float activeBgAlpha = ThemeEditor.getAlpha(ThemeSettings.BUTTON) / 255.0F;
         int bgColor = lerpColor(INACTIVE_BG_COLOR, activeBgColor, state);
         float bgAlpha = lerp(0.35F, activeBgAlpha, state) * alpha;
         RenderUtil.drawRoundedRectangle(boxX, boxY, boxW, boxH, radius, ColorUtil.applyOpacity(bgColor, bgAlpha));
         float rectCenterX = this.getX() + 5.0F + offsetX + boxWidth / 2.0F;
         float rectCenterY = this.getY() + 10.0F + offsetY + boxHeight / 2.0F;
         float textX = rectCenterX - Fonts.sfregular[12].getWidth(text) / 2.0F;
         float textY = rectCenterY - Fonts.sfregular[12].getHeight() / 2.0F + 1.0F;
         int textColor = lerpColor(INACTIVE_TEXT_COLOR, ACTIVE_TEXT_COLOR, state);
         Fonts.sfregular[12].drawString(stack, text, (double)(textX + 1.5F), (double)textY, ColorUtil.applyOpacity(textColor, alpha));
         offsetX += boxWidth + 3.0F;
         totalHeight = Math.max(totalHeight, offsetY + boxHeight);
      }

      this.setHeight(9.5F + totalHeight + Fonts.sfregular[14].getHeight() + 2.5F);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      int var7;
      if (button == 0) {
         float offsetX = 0.0F;
         float offsetY = 0.0F;
         String[] var6 = this.setting.strings;
         var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String text = var6[var8];
            float boxWidth = Fonts.sfregular[12].getWidth(text) + 5.0F;
            float boxHeight = Fonts.sfregular[12].getHeight() + 6.0F;
            if (offsetX + boxWidth >= this.getWidth() - 8.0F) {
               offsetX = 0.0F;
               offsetY += boxHeight + 2.0F;
            }

            if (MathUtil.isHovered(mouseX, mouseY, this.getX() + 5.5F + offsetX, this.getY() + 10.0F + offsetY - 0.5F, boxWidth + 1.0F, boxHeight + 0.5F)) {
               boolean anyAlive = false;
               Iterator var13 = this.colorAnimations.values().iterator();

               while(var13.hasNext()) {
                  AnimationUtil anim = (AnimationUtil)var13.next();
                  if (anim.isAlive() && anim.getValue() != anim.getTarget()) {
                     anyAlive = true;
                     break;
                  }
               }

               if (!anyAlive) {
                  this.setting.set(text);
                  String[] var23 = this.setting.strings;
                  int var24 = var23.length;

                  for(int var15 = 0; var15 < var24; ++var15) {
                     String mode = var23[var15];
                     ((AnimationUtil)this.colorAnimations.get(mode)).update(mode.equals(this.setting.get()) ? 1.0F : 0.0F);
                  }
               }
               break;
            }

            offsetX += boxWidth + 3.0F;
         }
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         boolean anyAlive = false;
         Iterator var18 = this.colorAnimations.values().iterator();

         while(var18.hasNext()) {
            AnimationUtil anim = (AnimationUtil)var18.next();
            if (anim.isAlive() && anim.getValue() != anim.getTarget()) {
               anyAlive = true;
               break;
            }
         }

         if (!anyAlive) {
            this.setting.set(this.setting.defaultVal);
            String[] var20 = this.setting.strings;
            int var21 = var20.length;

            for(var7 = 0; var7 < var21; ++var7) {
               String mode = var20[var7];
               ((AnimationUtil)this.colorAnimations.get(mode)).update(mode.equals(this.setting.get()) ? 1.0F : 0.0F);
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   private static float clamp01(float value) {
      return Math.max(0.0F, Math.min(1.0F, value));
   }

   private static float lerp(float from, float to, float progress) {
      return from + (to - from) * clamp01(progress);
   }

   private static int lerpColor(int from, int to, float progress) {
      progress = clamp01(progress);
      int fr = from >> 16 & 255;
      int fg = from >> 8 & 255;
      int fb = from & 255;
      int tr = to >> 16 & 255;
      int tg = to >> 8 & 255;
      int tb = to & 255;
      int r = (int)((float)fr + (float)(tr - fr) * progress);
      int g = (int)((float)fg + (float)(tg - fg) * progress);
      int b = (int)((float)fb + (float)(tb - fb) * progress);
      return -16777216 | r << 16 | g << 8 | b;
   }
}
