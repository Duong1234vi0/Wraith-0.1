package sky.core.modules.api.elements;

import com.mojang.blaze3d.matrix.MatrixStack;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Iterator;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ButtonSetting;
import sky.core.modules.api.constructors.impl.ClickSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.modules.api.constructors.impl.TextSetting;
import sky.core.modules.api.elements.impl.BindElement;
import sky.core.modules.api.elements.impl.BooleanElement;
import sky.core.modules.api.elements.impl.ButtonElement;
import sky.core.modules.api.elements.impl.ClickElement;
import sky.core.modules.api.elements.impl.ColorElement;
import sky.core.modules.api.elements.impl.ModeElement;
import sky.core.modules.api.elements.impl.MultiBooleanElement;
import sky.core.modules.api.elements.impl.SliderElement;
import sky.core.modules.api.elements.impl.StringElement;
import sky.core.modules.api.elements.impl.TextElement;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class ModuleElement extends Element {
   private final Module module;
   private final AnimationUtil animation;
   private final AnimationUtil alphaAnimation;
   public boolean open;
   private boolean bind;
   private final ObjectArrayList elements;

   public ModuleElement(Module module) {
      this.animation = new AnimationUtil(0.0F, 12.0F, Easings.CIRC_IN_OUT);
      this.alphaAnimation = new AnimationUtil(0.0F, 12.0F);
      this.elements = new ObjectArrayList();
      this.module = module;
      Iterator var2 = module.getSettings().iterator();

      while(var2.hasNext()) {
         Setting setting = (Setting)var2.next();
         if (setting instanceof BooleanSetting booleanSetting) {
            this.elements.add(new BooleanElement(booleanSetting));
         }

         if (setting instanceof SliderSetting sliderSetting) {
            this.elements.add(new SliderElement(sliderSetting));
         }

         if (setting instanceof BindSetting bindSetting) {
            this.elements.add(new BindElement(bindSetting));
         }

         if (setting instanceof ModeSetting modeSetting) {
            this.elements.add(new ModeElement(modeSetting));
         }

         if (setting instanceof MultiBooleanSetting multiBooleanSetting) {
            this.elements.add(new MultiBooleanElement(multiBooleanSetting));
         }

         if (setting instanceof ColorSetting colorSetting) {
            this.elements.add(new ColorElement(colorSetting));
         }

         if (setting instanceof StringSetting stringSetting) {
            this.elements.add(new StringElement(stringSetting));
         }

         if (setting instanceof TextSetting textSetting) {
            this.elements.add(new TextElement(textSetting));
         }

         if (setting instanceof ButtonSetting buttonSetting) {
            this.elements.add(new ButtonElement(buttonSetting, buttonSetting.getTextOn(), buttonSetting.getTextOff()));
         }

         if (setting instanceof ClickSetting clickSetting) {
            this.elements.add(new ClickElement(clickSetting));
         }
      }

   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      RenderUtil.beginNoLiquidGlass();

      try {
         this.module.getAnimation().update(this.module.isEnabled() ? 1.0F : 0.0F);
         float enabledState = this.module.getAnimation().getValue();
         RenderUtil.drawRoundedRectangle(this.getX() - 3.0F, this.getY() - 0.2F, this.getWidth() - 1.0F, this.getHeight() + 2.0F, 5.0F, ColorUtil.interpolate(ThemeEditor.getColor(ThemeSettings.FIELD), ThemeEditor.getColor(ThemeSettings.FIELD_INACTIVE), enabledState * alpha));
         float textX = this.getX() + 2.0F;
         int textY = (int)(this.getY() + (18.0F - Fonts.sfregular[16].getHeight()) / 2.0F);
         int disabledTextColor = ThemeEditor.getColor(ThemeSettings.TEXT);
         int enabledTextColor = ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.TEXT), 0.65F);
         int textColor = ColorUtil.interpolate(disabledTextColor, enabledTextColor, enabledState);
         Fonts.sf_regular[15].drawString(stack, this.module.getName(), (double)(textX + 1.0F), (double)((float)textY + 1.5F), ColorUtil.getColorWithAlpha(textColor, (float)((int)(ThemeEditor.getAlpha(ThemeSettings.TEXT) * alpha))));
         if (this.elements.stream().anyMatch(Element::isVisible)) {
            int enabledIconColor = ColorUtil.rgb(255, 255, 255);
            int disabledIconColor = ColorUtil.rgb(170, 170, 170);
            int iconColor = ColorUtil.interpolate(enabledIconColor, disabledIconColor, enabledState);
            Fonts.mototanya[12].drawString(stack, "i", (double)(this.getX() + this.getWidth() - 15.0F), (double)((float)textY + 3.0F), ColorUtil.getColorWithAlpha(iconColor, (float)((int)(alpha * 255.0F))));
         }

         this.drawComponents(stack, mouseX, mouseY, alpha);
         super.render(stack, mouseX, mouseY, alpha);
      } finally {
         RenderUtil.endNoLiquidGlass();
      }

   }

   private boolean isModuleHeaderHovered(float mouseX, float mouseY) {
      return MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY() + 1.0F, this.getWidth() - 4.0F, 16.0F);
   }

   public void drawComponents(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      this.animation.update(this.open ? 1.0F : 0.0F);
      this.alphaAnimation.update(this.open ? 1.0F : 0.0F);
      if ((double)this.animation.getValue() > 0.01) {
         ScissorUtil.start((double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)this.getHeight());
         float baseYOffset = -8.0F * (1.0F - this.animation.getValue());
         float baseY = this.getY() + 18.0F + baseYOffset;
         float currentYOffset = 0.0F;
         ObjectListIterator var8 = this.elements.iterator();

         while(var8.hasNext()) {
            Element element = (Element)var8.next();
            float visibleValue = element.getVisibilityAnimation();
            if (visibleValue > 0.0F) {
               float offsetY = -8.0F * (1.0F - visibleValue);
               float targetY = baseY + currentYOffset + offsetY;
               float currentY = baseY + (targetY - baseY) * this.animation.getValue();
               element.setX((float)Math.round(this.getX()) - 3.0F);
               element.setY((float)Math.round(currentY));
               element.setWidth(this.getWidth());
               float alphaFade = this.alphaAnimation.getValue() * visibleValue * alpha;
               element.render(stack, mouseX, mouseY, alphaFade);
               currentYOffset += element.getHeight() * visibleValue;
            }
         }

         ScissorUtil.end();
      }

   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      ObjectListIterator var4 = this.elements.iterator();

      while(var4.hasNext()) {
         Element element = (Element)var4.next();
         if (element.isVisible()) {
            element.keyPressed(keyCode, scanCode, modifiers);
         }
      }

      super.keyPressed(keyCode, scanCode, modifiers);
   }

   public void charTyped(char codePoint, int modifiers) {
      ObjectListIterator var3 = this.elements.iterator();

      while(var3.hasNext()) {
         Element element = (Element)var3.next();
         if (element.isVisible()) {
            element.charTyped(codePoint, modifiers);
         }
      }

      super.charTyped(codePoint, modifiers);
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (this.isInScissorZone(mouseX, mouseY) && this.isModuleHeaderHovered(mouseX, mouseY)) {
         if (button == 0) {
            this.module.toggle();
         } else if (button == 1) {
            if (this.elements.stream().anyMatch(Element::isVisible)) {
               this.open = !this.open;
            }
         } else if (button == 2) {
            this.bind = !this.bind;
         }
      }

      if (this.open && this.animation.getValue() > 0.99F && this.isInScissorZone(mouseX, mouseY)) {
         for(int i = this.elements.size() - 1; i >= 0; --i) {
            Element element = (Element)this.elements.get(i);
            if (element.isVisible() && !(element.getVisibilityAnimation() < 0.99F) && element.isHovered(mouseX, mouseY)) {
               element.mouseClicked(mouseX, mouseY, button);
               break;
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (this.open) {
         ObjectListIterator var7 = this.elements.iterator();

         while(var7.hasNext()) {
            Element element = (Element)var7.next();
            if (element.isVisible() && element.mouseScrolled(mouseX, mouseY, delta)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (this.open) {
         ObjectListIterator var10 = this.elements.iterator();

         while(var10.hasNext()) {
            Element element = (Element)var10.next();
            if (element.isVisible() && element.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isInScissorZone(float mouseX, float mouseY) {
      return this.getPanel() == null ? false : MathUtil.isHovered(mouseX, mouseY, this.getPanel().getX(), this.getPanel().getY() + 28.0F, this.getPanel().getWidth(), this.getPanel().getHeight() - 35.0F);
   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      ObjectListIterator var4 = this.elements.iterator();

      while(var4.hasNext()) {
         Element element = (Element)var4.next();
         element.mouseReleased(mouseX, mouseY, button);
      }

      super.mouseReleased(mouseX, mouseY, button);
   }

   public void setOpen(boolean open) {
      this.open = open;
   }

   public void setBind(boolean bind) {
      this.bind = bind;
   }

   public Module getModule() {
      return this.module;
   }

   public AnimationUtil getAnimation() {
      return this.animation;
   }

   public AnimationUtil getAlphaAnimation() {
      return this.alphaAnimation;
   }

   public boolean isOpen() {
      return this.open;
   }

   public boolean isBind() {
      return this.bind;
   }

   public ObjectArrayList getElements() {
      return this.elements;
   }
}
