package sky.core.ui.gui.additionals;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.lwjgl.glfw.GLFW;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class Searcher {
   private float x;
   private float y;
   private float width;
   private float height;
   private static final float VISUAL_Y_OFFSET = -16.0F;
   private String text = "";
   private boolean focused = false;
   private boolean activated = false;
   private boolean isTextSelected = false;
   private long cursorAnimationStart = 0L;

   public Searcher(float x, float y, float width, float height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public void render(MatrixStack stack, float alphaRender) {
      float fieldX = this.x;
      float fieldY = this.y + -16.0F;
      float fieldW = this.width;
      float fieldH = this.height;
      int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(logoColor, 0.1F);
      int bgWithAlpha = ColorUtil.applyOpacity(bgColor, ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * alphaRender);
      RenderUtil.drawRoundedRectangle(fieldX, fieldY, fieldW, fieldH - 3.0F, 6.0F, bgWithAlpha);
      String displayText = this.text.isEmpty() && !this.focused ? "Искать.." : this.text;
      float textX = fieldX + 5.0F;
      float maxWidth = this.width - 10.0F;
      if (!this.text.isEmpty() && Fonts.sf_medium[15].getWidth(displayText) > maxWidth) {
         while(Fonts.sf_medium[15].getWidth(displayText) > maxWidth && displayText.length() > 1) {
            displayText = displayText.substring(1);
         }
      }

      float cursorX;
      if (this.text.isEmpty() && !this.focused) {
         cursorX = textX + Fonts.sf_medium[15].getWidth("Поиск");
      } else {
         cursorX = textX + Fonts.sf_medium[15].getWidth(displayText);
      }

      float rightBound = fieldX + fieldW - 5.0F;
      if (cursorX > rightBound) {
         cursorX = rightBound;
      }

      if (this.activated) {
         long now = System.currentTimeMillis();
         if (this.cursorAnimationStart == 0L) {
            this.cursorAnimationStart = now;
         }

         float alpha = (float)Math.sin((double)((float)((now - this.cursorAnimationStart) % 1000L) / 500.0F) * Math.PI) * 0.5F + 0.5F;
         float cursorWidth = 4.0F;
         float cursorY = fieldY + fieldH - 8.0F;
         RenderUtil.drawMinecraftRectangle(stack, cursorX + 2.0F, cursorY - 2.0F, cursorWidth, 0.5F, ColorUtil.applyOpacity(-1, (int)(alpha * 255.0F * alphaRender)));
      } else {
         this.cursorAnimationStart = 0L;
      }

      float textY = fieldY + (fieldH / 2.0F - 4.0F) - 2.2F;
      Fonts.sfregular[14].drawString(stack, displayText, (double)(textX + 1.0F), (double)(textY + 3.0F), this.text.isEmpty() && !this.focused ? ColorUtil.getColorWithAlpha(ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE), ThemeEditor.getAlpha(ThemeSettings.TEXT_INACTIVE) * alphaRender) : ColorUtil.getColorWithAlpha(ThemeEditor.getColor(ThemeSettings.TEXT), ThemeEditor.getAlpha(ThemeSettings.TEXT) * alphaRender));
   }

   public boolean mouseClicked(float mouseX, float mouseY) {
      float fieldX = this.x;
      float fieldY = this.y + -16.0F;
      float fieldW = this.width;
      float fieldH = this.height;
      boolean wasFocused = this.focused;
      this.focused = mouseX >= fieldX && mouseX <= fieldX + fieldW - 1.0F && mouseY >= fieldY && mouseY <= fieldY + fieldH - 1.0F;
      if (this.focused && !wasFocused) {
         this.activated = true;
         this.cursorAnimationStart = System.currentTimeMillis();
         this.isTextSelected = false;
      } else if (!this.focused) {
         this.activated = false;
         this.isTextSelected = false;
      }

      return this.focused;
   }

   public void charTyped(char codePoint) {
      if (this.focused && this.activated) {
         if (this.isTextSelected) {
            this.text = String.valueOf(codePoint);
            this.isTextSelected = false;
         } else if (codePoint >= ' ' && codePoint <= '~' && this.text.length() < 30) {
            this.text = this.text + codePoint;
         }
      }

   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.focused && this.activated) {
         if (keyCode == 65 && (modifiers & 2) != 0) {
            if (!this.text.isEmpty()) {
               this.isTextSelected = true;
            }

            return;
         }

         if (keyCode == 67 && (modifiers & 2) != 0) {
            if (this.isTextSelected && !this.text.isEmpty()) {
               GLFW.glfwSetClipboardString(GLFW.glfwGetCurrentContext(), this.text);
            }

            return;
         }

         if (keyCode == 86 && (modifiers & 2) != 0) {
            String clipboardText = GLFW.glfwGetClipboardString(GLFW.glfwGetCurrentContext());
            if (clipboardText != null && !clipboardText.isEmpty()) {
               if (this.isTextSelected) {
                  this.text = "";
                  this.isTextSelected = false;
               }

               int remainingChars = 30 - this.text.length();
               if (remainingChars > 0) {
                  this.text = this.text + clipboardText.substring(0, Math.min(remainingChars, clipboardText.length()));
               }
            }

            return;
         }

         if (keyCode == 259 && !this.text.isEmpty()) {
            if (this.isTextSelected) {
               this.text = "";
               this.isTextSelected = false;
            } else {
               this.text = this.text.substring(0, this.text.length() - 1);
            }

            return;
         }

         if (keyCode == 257 || keyCode == 256) {
            this.activated = false;
            this.focused = false;
            this.isTextSelected = false;
         }
      }

   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public String getText() {
      return this.text;
   }

   public boolean isFocused() {
      return this.focused;
   }

   public boolean isActivated() {
      return this.activated;
   }

   public boolean isTextSelected() {
      return this.isTextSelected;
   }

   public long getCursorAnimationStart() {
      return this.cursorAnimationStart;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setFocused(boolean focused) {
      this.focused = focused;
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }

   public void setTextSelected(boolean isTextSelected) {
      this.isTextSelected = isTextSelected;
   }

   public void setCursorAnimationStart(long cursorAnimationStart) {
      this.cursorAnimationStart = cursorAnimationStart;
   }
}
