package sky.core.ui.gui.additionals;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import sky.core.modules.Module;
import sky.core.modules.api.elements.Element;
import sky.core.modules.api.elements.ModuleElement;
import sky.core.modules.api.elements.impl.BooleanElement;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.KeyMapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class BindingPanel implements Wrapper {
   private final Element element;
   private final AnimationUtil animation;
   private final AnimationUtil strikeAnimation;
   private final AnimationUtil toggleModeAnimation;
   private final AnimationUtil hideTooltipAnimation;
   private final AnimationUtil resetTooltipAnimation;
   private float x;
   private float y;
   private final float width;
   private final float height;
   private boolean waitingForKey;
   public boolean shouldClose;

   public BindingPanel(Element element) {
      this.animation = new AnimationUtil(0.0F, 6.0F, Easings.QUAD_IN_OUT);
      this.strikeAnimation = new AnimationUtil(0.0F, 6.0F, Easings.LINEAR);
      this.toggleModeAnimation = new AnimationUtil(0.0F, 5.0F, Easings.LINEAR);
      this.hideTooltipAnimation = new AnimationUtil(0.0F, 10.0F, Easings.LINEAR);
      this.resetTooltipAnimation = new AnimationUtil(0.0F, 10.0F, Easings.LINEAR);
      this.width = 70.0F;
      this.height = 18.0F;
      this.waitingForKey = false;
      this.shouldClose = false;
      this.element = element;
      this.x = element.getX() + element.getWidth();
      this.y = element.getY() + 4.0F;
      boolean isVisible = element instanceof ModuleElement ? ((ModuleElement)element).getModule().isKeybindvisible() : ((BooleanElement)element).getSetting().isKeybindvisible();
      this.strikeAnimation.setValue(isVisible ? 0.0F : 1.0F);
   }

   public void render(MatrixStack matrixStack) {
      Module.ToggleMode currentMode = this.element instanceof ModuleElement ? ((ModuleElement)this.element).getModule().getToggleMode() : ((BooleanElement)this.element).getSetting().getToggleMode();
      this.toggleModeAnimation.setSpeed(5.0F);
      this.toggleModeAnimation.setEasing(Easings.LINEAR);
      this.toggleModeAnimation.update(currentMode == Module.ToggleMode.HOLD ? 1.0F : 0.0F);
      float tmValue = this.toggleModeAnimation.getValue();
      if (this.shouldClose && (double)this.animation.getValue() <= 0.01) {
         if (this.element instanceof ModuleElement) {
            ((ModuleElement)this.element).setBind(false);
         } else {
            ((BooleanElement)this.element).setBind(false);
         }

         this.shouldClose = false;
         this.waitingForKey = false;
      }

      this.animation.setSpeed(6.0F);
      this.animation.setEasing(Easings.QUAD_IN_OUT);
      AnimationUtil var10000 = this.animation;
      float var10001;
      if (this.shouldClose) {
         var10001 = 0.0F;
      } else {
         label100: {
            label99: {
               if (this.element instanceof ModuleElement) {
                  if (((ModuleElement)this.element).isBind()) {
                     break label99;
                  }
               } else if (((BooleanElement)this.element).isBind()) {
                  break label99;
               }

               var10001 = 0.0F;
               break label100;
            }

            var10001 = 1.0F;
         }
      }

      var10000.update(var10001);
      if (!(this.animation.getValue() <= 0.0F)) {
         RenderUtil.scaleStart(this.x + 35.0F, this.y + 9.0F, this.animation.getValue());
         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         int bgWithAlpha = ColorUtil.applyOpacity(bgColor, ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * this.animation.getValue());
         RenderUtil.drawRoundedRectangle(this.x, this.y, 70.0F, 18.0F, 5.0F, bgWithAlpha);
         Fonts.sf_regular[14].drawString(matrixStack, "Бинд", (double)(this.x + 5.0F), (double)(this.y + 7.0F), ThemeEditor.getColor(ThemeSettings.TEXT));
         float mouseX = (float)mc.mouseHelper.getMouseX() * (float)mc.getMainWindow().getScaledWidth() / (float)mc.getMainWindow().getWidth();
         float mouseY = (float)mc.mouseHelper.getMouseY() * (float)mc.getMainWindow().getScaledHeight() / (float)mc.getMainWindow().getHeight();
         boolean isVisibleHovered = MathUtil.isHovered(mouseX, mouseY, this.x + 23.5F, this.y + 4.0F, 9.0F, 8.0F);
         this.hideTooltipAnimation.setSpeed(10.0F);
         this.hideTooltipAnimation.setEasing(Easings.LINEAR);
         float prevResetAlpha = this.resetTooltipAnimation.getValue();
         if (isVisibleHovered && prevResetAlpha <= 0.0F) {
            this.hideTooltipAnimation.update(1.0F);
         } else {
            this.hideTooltipAnimation.update(0.0F);
         }

         float hideAlpha = this.hideTooltipAnimation.getValue();
         boolean isVisible = this.element instanceof ModuleElement ? ((ModuleElement)this.element).getModule().isKeybindvisible() : ((BooleanElement)this.element).getSetting().isKeybindvisible();
         this.strikeAnimation.setSpeed(6.0F);
         this.strikeAnimation.setEasing(Easings.LINEAR);
         this.strikeAnimation.update(isVisible ? 0.0F : 1.0F);
         float prog = this.strikeAnimation.getValue();
         int iconColor = ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.INDICATOR_INACTIVE), ThemeEditor.getColor(ThemeSettings.INDICATOR), prog);
         boolean isBinHovered = MathUtil.isHovered(mouseX, mouseY, this.x + 34.0F, this.y + 5.0F, 8.0F, 8.0F);
         this.resetTooltipAnimation.setSpeed(10.0F);
         this.resetTooltipAnimation.setEasing(Easings.LINEAR);
         if (isBinHovered && hideAlpha <= 0.0F) {
            this.resetTooltipAnimation.update(1.0F);
         } else {
            this.resetTooltipAnimation.update(0.0F);
         }

         float resetAlpha = this.resetTooltipAnimation.getValue();
         if (resetAlpha > 0.0F) {
            float widthReset = Fonts.sf_regular[12].getWidth("Сбросить") + 4.0F;
            RenderUtil.drawBlurredRoundedRectangle(this.x + 33.0F, this.y - 4.5F, widthReset, 9.0F, 1.0F, ThemeEditor.getColor(ThemeSettings.TOOLTIP_BG), resetAlpha);
            int textColor = ThemeEditor.getColor(ThemeSettings.TEXT);
            int textColorWithAlpha = ColorUtil.applyOpacity(textColor, (int)(resetAlpha * 255.0F));
            Fonts.sf_regular[12].drawString(matrixStack, "Сбросить", (double)(this.x + 35.0F), (double)(this.y - 1.0F), textColorWithAlpha);
         }

         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/bin.png"), this.x + 30.0F, this.y + 5.0F, 8.0F, 8.0F, ThemeEditor.getColor(ThemeSettings.INDICATOR));
         int currentBind = this.element instanceof ModuleElement ? ((ModuleElement)this.element).getModule().getBind() : ((BooleanElement)this.element).getSetting().getBind();
         RenderUtil.drawRoundedRectangle(this.x + 43.0F, this.y + 4.0F, 23.0F, 10.0F, 3.0F, ThemeEditor.getColor(ThemeSettings.BUTTON));
         float textX = this.x + 42.5F + (23.0F - Fonts.sf_regular[12].getWidth(this.waitingForKey ? "..." : (currentBind == -100 ? "Нету" : KeyMapper.getKey(currentBind)))) / 2.0F;
         float textY = this.y + 4.0F + (10.0F - Fonts.sf_regular[12].getHeight()) / 2.0F + 1.0F;
         Fonts.sfregular[12].drawString(matrixStack, this.waitingForKey ? "..." : (currentBind == -100 ? "Нету" : KeyMapper.getKey(currentBind)), (double)(textX + 0.5F), (double)textY, ThemeEditor.getColor(ThemeSettings.TEXT));
         int activeBg = ThemeEditor.getColor(ThemeSettings.BUTTON_INACTIVE);
         int inactiveBg = ThemeEditor.getColor(ThemeSettings.BUTTON);
         ColorUtil.interpolate(activeBg, inactiveBg, tmValue);
         float toggleTextX = this.x + 3.5F + (30.0F - Fonts.sf_regular[12].getWidth("Toggle")) / 2.0F;
         float toggleTextY = this.y + 16.0F + (10.0F - Fonts.sf_regular[12].getHeight()) / 2.0F + 1.0F;
         int activeText = ThemeEditor.getColor(ThemeSettings.TEXT_INACTIVE);
         int inactiveText = ThemeEditor.getColor(ThemeSettings.TEXT);
         ColorUtil.interpolate(activeText, inactiveText, tmValue);
         ColorUtil.interpolate(inactiveBg, activeBg, tmValue);
         float holdTextX = this.x + 35.5F + (30.0F - Fonts.sf_regular[12].getWidth("Hold")) / 2.0F;
         float holdTextY = this.y + 16.0F + (10.0F - Fonts.sf_regular[12].getHeight()) / 2.0F + 1.0F;
         ColorUtil.interpolate(inactiveText, activeText, tmValue);
         RenderUtil.scaleEnd();
         if (this.waitingForKey) {
            RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), 0.0F, 0.0F, (float)mc.getMainWindow().getScaledWidth(), (float)mc.getMainWindow().getScaledHeight(), ColorUtil.applyOpacity(-1, 160));
            Fonts.sf_regular[30].drawString(matrixStack, "Нажмите на любую кнопку", (double)(((float)mc.getMainWindow().getScaledWidth() - Fonts.sf_regular[32].getWidth("Нажмите на любую кнопку")) / 2.0F), (double)(((float)mc.getMainWindow().getScaledHeight() - Fonts.sf_regular[32].getHeight()) / 2.0F - 4.0F), -1);
         }

      }
   }

   public boolean mouseClicked(float mouseX, float mouseY, int button) {
      boolean isBinding = this.element instanceof ModuleElement ? ((ModuleElement)this.element).isBind() : ((BooleanElement)this.element).isBind();
      if (isBinding && !((double)this.animation.getValue() < 0.1)) {
         if (this.waitingForKey) {
            if (MathUtil.isHovered(mouseX, mouseY, this.x + 34.0F, this.y + 5.0F, 8.0F, 8.0F) && button == 0) {
               if (this.element instanceof ModuleElement) {
                  ((ModuleElement)this.element).getModule().setBind(-100);
               } else {
                  ((BooleanElement)this.element).getSetting().setBind(-100);
               }

               this.waitingForKey = false;
               return true;
            } else if (MathUtil.isHovered(mouseX, mouseY, this.x + 43.0F, this.y + 4.0F, 23.0F, 10.0F) && button == 0) {
               this.waitingForKey = false;
               return true;
            } else if (button != 0 && button != 1) {
               if (button >= 1) {
                  if (this.element instanceof ModuleElement) {
                     ((ModuleElement)this.element).getModule().setBind(button);
                  } else {
                     ((BooleanElement)this.element).getSetting().setBind(button);
                  }

                  this.waitingForKey = false;
                  return true;
               } else {
                  return true;
               }
            } else {
               return true;
            }
         } else if (MathUtil.isHovered(mouseX, mouseY, this.x, this.y, 70.0F, 18.0F)) {
            if (MathUtil.isHovered(mouseX, mouseY, this.x + 43.0F, this.y + 4.0F, 23.0F, 10.0F) && button == 0) {
               this.waitingForKey = true;
               return true;
            } else if (MathUtil.isHovered(mouseX, mouseY, this.x + 34.0F, this.y + 5.0F, 8.0F, 8.0F) && button == 0) {
               if (this.element instanceof ModuleElement) {
                  ((ModuleElement)this.element).getModule().setBind(-100);
               } else {
                  ((BooleanElement)this.element).getSetting().setBind(-100);
               }

               return true;
            } else {
               return true;
            }
         } else {
            this.shouldClose = true;
            return true;
         }
      } else {
         return false;
      }
   }

   public void keyPressed(int keyCode) {
      if (this.waitingForKey) {
         if (keyCode != 256 && keyCode != 261) {
            if (keyCode != -1 && keyCode != 0 && keyCode != 1) {
               if (this.element instanceof ModuleElement) {
                  ((ModuleElement)this.element).getModule().setBind(keyCode);
               } else if (this.element instanceof BooleanElement) {
                  ((BooleanElement)this.element).getSetting().setBind(keyCode);
               }
            }
         } else if (this.element instanceof ModuleElement) {
            ((ModuleElement)this.element).getModule().setBind(-100);
         } else if (this.element instanceof BooleanElement) {
            ((BooleanElement)this.element).getSetting().setBind(-100);
         }

         this.waitingForKey = false;
      } else if (keyCode == 256) {
         this.shouldClose = true;
      }

   }

   public void updatePosition(float x, float y) {
      float yOffset = this.element instanceof BooleanElement ? -4.0F : 0.0F;
      this.x = x;
      this.y = y + yOffset;
   }

   public boolean isClosed() {
      return this.animation.getValue() <= 0.01F;
   }

   public Element getElement() {
      return this.element;
   }
}
