package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class MultiBooleanElement extends Element {
   private final MultiBooleanSetting setting;
   private final Map colorAnimations;
   private static final int ACTIVE_TEXT_COLOR = ColorUtil.rgb(0, 0, 0);
   private static final int INACTIVE_TEXT_COLOR = ColorUtil.rgb(187, 187, 187);
   private static final int INACTIVE_BG_COLOR = ColorUtil.rgb(80, 80, 80);
   private static final float INACTIVE_BG_OPACITY = 0.35F;

   public MultiBooleanElement(MultiBooleanSetting setting) {
      this.setting = setting;
      this.colorAnimations = new HashMap();
      Iterator var2 = ((List)setting.get()).iterator();

      while(var2.hasNext()) {
         BooleanSetting option = (BooleanSetting)var2.next();
         this.colorAnimations.put(option, new AnimationUtil((Boolean)option.get() ? 1.0F : 0.0F, 10.0F, Easings.LINEAR));
      }

   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      super.render(stack, mouseX, mouseY, alpha);
      float offsetX = 0.0F;
      float offsetY = 0.0F;
      float totalHeight = 0.0F;
      int headerColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha);
      Fonts.sf_regular[14].drawString(stack, this.setting.getName(), (double)(this.getX() + 6.0F), (double)(this.getY() + 2.0F), headerColor);
      int var10000 = (int)((List)this.setting.get()).stream().filter(Setting::get).count();
      String countText = "" + var10000 + "/" + ((List)this.setting.get()).size();
      float counterX = this.getX() + this.getWidth() - 4.5F - Fonts.sfregular[14].getWidth(countText);
      Fonts.sf_regular[14].drawString(stack, countText, (double)counterX, (double)(this.getY() + 2.0F), headerColor);
      float lineLimit = this.getWidth() - 8.0F;
      float maxTextWidth = Math.max(0.0F, lineLimit - 7.0F);

      float boxHeight;
      for(Iterator var13 = ((List)this.setting.get()).iterator(); var13.hasNext(); totalHeight = Math.max(totalHeight, offsetY + boxHeight)) {
         BooleanSetting option = (BooleanSetting)var13.next();
         this.colorAnimations.computeIfAbsent(option, (o) -> {
            return new AnimationUtil((Boolean)o.get() ? 1.0F : 0.0F, 10.0F, Easings.LINEAR);
         });
         String optionText = this.fitTextToWidth(option.getName(), maxTextWidth);
         float textWidth = Fonts.sfregular[12].getWidth(optionText);
         float boxWidth = textWidth + 5.0F;
         boxHeight = Fonts.sfregular[12].getHeight() + 6.0F;
         float boxW = boxWidth + 2.0F;
         float boxH = boxHeight + 1.0F;
         if (offsetX > 0.0F && offsetX + boxW >= lineLimit) {
            offsetX = 0.0F;
            offsetY += boxHeight + 2.0F;
         }

         AnimationUtil animation = (AnimationUtil)this.colorAnimations.get(option);
         animation.update((Boolean)option.get() ? 1.0F : 0.0F);
         float state = clamp01(animation.getValue());
         float offFactor = 1.0F - state;
         float boxX = this.getX() + 5.5F + offsetX;
         float boxY = this.getY() + 10.0F + offsetY - 0.5F;
         float radius = 2.5F;
         int activeBgColor = ThemeEditor.getColor(ThemeSettings.BUTTON);
         float activeBgAlpha = ThemeEditor.getAlpha(ThemeSettings.BUTTON) / 255.0F;
         int currentBgColor = lerpColor(INACTIVE_BG_COLOR, activeBgColor, state);
         float currentBgAlpha = lerp(0.35F, activeBgAlpha, state) * alpha;
         RenderUtil.drawRoundedRectangle(boxX, boxY, boxW, boxH, radius, ColorUtil.applyOpacity(currentBgColor, currentBgAlpha));
         float rectCenterX = this.getX() + 5.0F + offsetX + boxWidth / 2.0F;
         float rectCenterY = this.getY() + 10.0F + offsetY + boxHeight / 2.0F;
         float textX = rectCenterX - textWidth / 2.0F;
         float textY = rectCenterY - Fonts.sfregular[12].getHeight() / 2.0F + 1.0F;
         int currentTextColor = lerpColor(INACTIVE_TEXT_COLOR, ACTIVE_TEXT_COLOR, state);
         Fonts.sfregular[12].drawString(stack, optionText, (double)(textX + 1.5F), (double)textY, ColorUtil.applyOpacity(currentTextColor, alpha));
         offsetX += boxWidth + 3.0F;
      }

      this.setHeight(9.5F + totalHeight + Fonts.sfregular[14].getHeight() + 2.5F);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (button == 0) {
         float offsetX = 0.0F;
         float offsetY = 0.0F;
         float lineLimit = this.getWidth() - 8.0F;
         float maxTextWidth = Math.max(0.0F, lineLimit - 7.0F);

         float boxWidth;
         for(Iterator var8 = ((List)this.setting.get()).iterator(); var8.hasNext(); offsetX += boxWidth + 3.0F) {
            BooleanSetting option = (BooleanSetting)var8.next();
            this.colorAnimations.computeIfAbsent(option, (o) -> {
               return new AnimationUtil((Boolean)o.get() ? 1.0F : 0.0F, 10.0F, Easings.LINEAR);
            });
            String optionText = this.fitTextToWidth(option.getName(), maxTextWidth);
            float textWidth = Fonts.sfregular[12].getWidth(optionText);
            boxWidth = textWidth + 5.0F;
            float boxHeight = Fonts.sfregular[12].getHeight() + 6.0F;
            float boxW = boxWidth + 2.0F;
            float boxH = boxHeight + 1.0F;
            if (offsetX > 0.0F && offsetX + boxW >= lineLimit) {
               offsetX = 0.0F;
               offsetY += boxHeight + 2.0F;
            }

            float boxX = this.getX() + 5.5F + offsetX;
            float boxY = this.getY() + 10.0F + offsetY - 0.5F;
            if (MathUtil.isHovered(mouseX, mouseY, boxX, boxY, boxW, boxH)) {
               AnimationUtil anim = (AnimationUtil)this.colorAnimations.get(option);
               if (anim == null || !anim.isAlive() || anim.getValue() == anim.getTarget()) {
                  option.set(!(Boolean)option.get());
                  ((AnimationUtil)this.colorAnimations.get(option)).update((Boolean)option.get() ? 1.0F : 0.0F);
               }
               break;
            }
         }
      } else if (button == 2 && MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         Iterator var19 = ((List)this.setting.get()).iterator();

         label56:
         while(true) {
            BooleanSetting option;
            AnimationUtil anim;
            do {
               if (!var19.hasNext()) {
                  break label56;
               }

               option = (BooleanSetting)var19.next();
               this.colorAnimations.computeIfAbsent(option, (o) -> {
                  return new AnimationUtil((Boolean)o.get() ? 1.0F : 0.0F, 10.0F, Easings.LINEAR);
               });
               anim = (AnimationUtil)this.colorAnimations.get(option);
            } while(anim != null && anim.isAlive() && anim.getValue() != anim.getTarget());

            option.set(option.defaultVal);
            ((AnimationUtil)this.colorAnimations.get(option)).update(option.defaultVal ? 1.0F : 0.0F);
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isVisible() {
      return this.setting.isVisible();
   }

   private String fitTextToWidth(String text, float maxWidth) {
      if (maxWidth <= 0.0F) {
         return "";
      } else if (Fonts.sfregular[12].getWidth(text) <= maxWidth) {
         return text;
      } else {
         String dots = "...";
         float dotsWidth = Fonts.sfregular[12].getWidth(dots);
         if (dotsWidth >= maxWidth) {
            return "";
         } else {
            StringBuilder builder = new StringBuilder();

            String var10000;
            for(int i = 0; i < text.length(); ++i) {
               var10000 = builder.toString();
               String next = var10000 + text.charAt(i);
               if (Fonts.sfregular[12].getWidth(next) + dotsWidth > maxWidth) {
                  break;
               }

               builder.append(text.charAt(i));
            }

            var10000 = String.valueOf(builder);
            return var10000 + dots;
         }
      }
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
