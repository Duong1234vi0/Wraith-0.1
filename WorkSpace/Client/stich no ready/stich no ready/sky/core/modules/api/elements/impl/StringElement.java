package sky.core.modules.api.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.lwjgl.glfw.GLFW;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.modules.api.elements.Element;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class StringElement extends Element {
   private final StringSetting setting;
   private String inputText;
   public boolean isFocused = false;
   private boolean isTextSelected = false;
   private long cursorAnimationStart = 0L;
   private final AnimationUtil caretFadeAnimation;

   public StringElement(StringSetting setting) {
      this.caretFadeAnimation = new AnimationUtil(0.0F, 12.0F, Easings.SINE_OUT);
      this.setting = setting;
      this.inputText = (String)setting.get();
   }

   public void setInputText(String text) {
      this.inputText = text == null ? "" : text;
      this.setting.setValue(this.inputText);
   }

   public void render(MatrixStack matrixStack, float mouseX, float mouseY, float alpha) {
      super.render(matrixStack, mouseX, mouseY, alpha);
      this.setHeight(16.0F);
      String displayText = this.inputText.isEmpty() ? this.setting.getName() : this.inputText;
      boolean isEmpty = this.inputText.isEmpty();
      float inputWidth = this.getWidth() - 36.0F;
      RenderUtil.drawRoundedRectangle(this.getX() + 5.0F, this.getY(), inputWidth, 11.0F, 3.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE), ThemeEditor.getAlpha(ThemeSettings.BUTTON_INACTIVE) / 255.0F * alpha));
      int cursorX;
      if (this.isTextSelected && !isEmpty) {
         cursorX = (int)Math.min(Fonts.sf_medium[13].getWidth(displayText), 71.0F);
         RenderUtil.drawMinecraftRectangle(matrixStack, this.getX() + 7.0F, this.getY() + 2.0F, (float)(cursorX + 1), 8.0F, ColorUtil.applyOpacity(ColorUtil.getColor(26, 53, 255), 255.0F * alpha));
      }

      if (isEmpty) {
         cursorX = (int)(this.getX() + 7.0F);
      } else {
         int textWidth = (int)Math.min(Fonts.sf_medium[13].getWidth(displayText), 70.0F);
         cursorX = (int)(this.getX() + 8.0F + (float)textWidth);
         int rightBound = (int)(this.getX() + 5.0F + inputWidth - 2.0F);
         if (cursorX > rightBound) {
            cursorX = rightBound;
         }
      }

      long now = System.currentTimeMillis();
      if (this.cursorAnimationStart == 0L) {
         this.cursorAnimationStart = now;
      }

      float blinkAlpha = (float)Math.sin((double)((float)((now - this.cursorAnimationStart) % 1000L) / 500.0F) * Math.PI) * 0.5F + 0.5F;
      this.caretFadeAnimation.update(this.isFocused ? 1.0F : 0.0F);
      if (!this.isFocused) {
         this.isTextSelected = false;
      }

      float caretFade = this.caretFadeAnimation.getValue();
      if (caretFade > 0.01F) {
         float finalAlpha = blinkAlpha * caretFade;
         RenderUtil.drawMinecraftRectangle(matrixStack, (float)cursorX, this.getY() + 2.0F, 0.5F, 8.0F, ColorUtil.applyOpacity(-1, (int)(finalAlpha * 255.0F)));
      }

      while(Fonts.sf_regular[13].getWidth(displayText) > 71.0F && !displayText.isEmpty()) {
         displayText = displayText.substring(1);
      }

      Fonts.sf_regular[13].drawString(matrixStack, displayText, (double)(this.getX() + 8.0F), (double)(this.getY() + 4.5F), this.inputText.isEmpty() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE), ThemeEditor.getAlpha(ThemeSettings.TEXT_INACTIVE) / 255.0F * alpha) : ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) / 255.0F * alpha));
      this.setting.set(this.inputText);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      boolean isHovered = this.isHovered(mouseX, mouseY);
      if (isHovered) {
         this.isFocused = true;
         this.isTextSelected = false;
         this.cursorAnimationStart = System.currentTimeMillis();
      } else if (this.isFocused) {
         this.isFocused = false;
         this.isTextSelected = false;
         this.setting.setValue(this.inputText);
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      if (this.isFocused && !this.isHovered(mouseX, mouseY)) {
         this.isFocused = false;
         this.isTextSelected = false;
         this.setting.setValue(this.inputText);
      }

      super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean isHovered(float mouseX, float mouseY) {
      return mouseX >= this.getX() + 5.0F && mouseX <= this.getX() + this.getWidth() - 5.0F && mouseY >= this.getY() && mouseY <= this.getY() + 11.0F;
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.isFocused) {
         if (keyCode == 257 || keyCode == 256) {
            this.isFocused = false;
            this.isTextSelected = false;
            this.setting.setValue(this.inputText);
            return;
         }

         if (keyCode == 65 && (modifiers & 2) != 0) {
            this.isTextSelected = true;
            return;
         }

         if (keyCode == 67 && (modifiers & 2) != 0) {
            if (this.isTextSelected && !this.inputText.isEmpty()) {
               GLFW.glfwSetClipboardString(GLFW.glfwGetCurrentContext(), this.inputText);
            }

            return;
         }

         if (keyCode == 86 && (modifiers & 2) != 0) {
            String clipboardText = GLFW.glfwGetClipboardString(GLFW.glfwGetCurrentContext());
            if (clipboardText != null && !clipboardText.isEmpty()) {
               if (this.isTextSelected) {
                  this.inputText = "";
                  this.isTextSelected = false;
               }

               int maxLen = this.setting.getName().equals("Название") ? 16 : 100;
               int remainingChars = maxLen - this.inputText.length();
               if (remainingChars > 0) {
                  this.inputText = this.inputText + clipboardText.substring(0, Math.min(remainingChars, clipboardText.length()));
               }
            }

            return;
         }

         if (keyCode == 259) {
            if (this.isTextSelected) {
               this.inputText = "";
               this.isTextSelected = false;
            } else if (!this.inputText.isEmpty()) {
               this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
            }

            return;
         }
      }

      super.keyPressed(keyCode, scanCode, modifiers);
   }

   public void clearText() {
      this.inputText = "";
      this.setting.setValue("");
      this.isFocused = false;
      this.isTextSelected = false;
   }

   public void charTyped(char codePoint, int modifiers) {
      if (this.isFocused) {
         if (this.isTextSelected) {
            this.inputText = String.valueOf(codePoint);
            this.isTextSelected = false;
         } else {
            int maxLen = this.setting.getName().equals("Название") ? 16 : 100;
            if (this.inputText.length() < maxLen) {
               this.inputText = this.inputText + codePoint;
            }
         }
      }

      super.charTyped(codePoint, modifiers);
   }

   public StringSetting getSetting() {
      return this.setting;
   }

   public String getInputText() {
      return this.inputText;
   }

   public boolean isFocused() {
      return this.isFocused;
   }

   public boolean isTextSelected() {
      return this.isTextSelected;
   }

   public long getCursorAnimationStart() {
      return this.cursorAnimationStart;
   }

   public AnimationUtil getCaretFadeAnimation() {
      return this.caretFadeAnimation;
   }
}
