package sky.core.ui.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.MathHelper;
import sky.core.SkyCore;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.elements.IBuilder;
import sky.core.modules.api.elements.ModuleElement;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class Panel implements IBuilder {
   private Category category;
   protected float x;
   protected float y;
   float maxHeight = 0.0F;
   protected float width = 120.0F;
   protected float height = 275.0F;
   private List modules = new ArrayList();
   private final List allModules = new ArrayList();
   private AnimationUtil scrollAnimation;
   private float scroll;

   public Panel() {
      this.scrollAnimation = new AnimationUtil(0.0F, 7.5F, Easings.QUAD_OUT);
   }

   private void initializePanel(Category category) {
      this.category = category;
      Iterator var2 = SkyCore.getInstance().getModuleManager().getModules().iterator();

      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.getCategory() == category) {
            ModuleElement component = new ModuleElement(module);
            component.setPanel(this);
            this.allModules.add(component);
         }
      }

      this.allModules.sort(Comparator.comparing((moduleElement) -> {
         return moduleElement.getModule().getName();
      }, String.CASE_INSENSITIVE_ORDER));
      this.modules.addAll(this.allModules);
   }

   public Panel(Category category) {
      this.scrollAnimation = new AnimationUtil(0.0F, 7.5F, Easings.QUAD_OUT);
      this.initializePanel(category);
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      this.scrollAnimation.update(this.scroll);
      float animatedScroll = this.scrollAnimation.getValue();
      int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(logoColor, 0.1F);
      int bgWithAlpha = ColorUtil.applyOpacity(bgColor, ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * alpha);
      RenderUtil.drawRoundedRectangle(this.x, this.y + 4.0F, this.width - 8.0F, this.height - 4.0F, 10.0F, bgWithAlpha);
      this.drawHeader(stack, alpha);
      this.drawContent(stack, mouseX, mouseY, alpha, animatedScroll);
   }

   private void drawHeader(MatrixStack stack, float alpha) {
      String categoryName = this.category.name().equals("Misc") ? "Misc" : this.category.name();
      String categoryIcon = this.getCategoryIcon();
      float iconWidth = Fonts.skycore[14].getWidth(categoryIcon);
      float textWidth = Fonts.sf_regular[19].getWidth(categoryName);
      float gap = 4.0F;
      float totalWidth = iconWidth + gap + textWidth;
      float startX = this.getX() + (this.getWidth() - 8.0F - totalWidth) / 2.0F;
      float textY = this.getY() + 12.0F + 1.0F;
      int iconColor = ColorUtil.getColorWithAlpha(ThemeEditor.getColor(ThemeSettings.LOGO), (float)((int)(ThemeEditor.getAlpha(ThemeSettings.LOGO) * alpha)));
      int textColor = ColorUtil.getColorWithAlpha(ThemeEditor.getColor(ThemeSettings.HEADER), (float)((int)(ThemeEditor.getAlpha(ThemeSettings.HEADER) * alpha)));
      Fonts.skycore[14].drawString(stack, categoryIcon, (double)(startX - 4.0F), (double)(textY + 5.0F), iconColor);
      Fonts.sf_regular[19].drawString(stack, categoryName, (double)(startX + iconWidth + gap - 4.0F), (double)(textY + 2.0F), textColor);
   }

   private String getCategoryIcon() {
      String var10000;
      switch (this.category) {
         case Combat:
            var10000 = "l";
            break;
         case Movement:
            var10000 = "m";
            break;
         case Visuals:
            var10000 = "b";
            break;
         case Player:
            var10000 = "i";
            break;
         case Miscellaneous:
            var10000 = "j";
            break;
         default:
            var10000 = "d";
      }

      return var10000;
   }

   private void drawContent(MatrixStack stack, float mouseX, float mouseY, float alpha, float animatedScroll) {
      float contentHeight = this.getHeight() - 34.5F;
      if (this.maxHeight > contentHeight) {
         this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
         animatedScroll = MathHelper.clamp(animatedScroll, -this.maxHeight + contentHeight, 0.0F);
      } else {
         animatedScroll = 0.0F;
         this.scroll = 0.0F;
      }

      float visibleTop = this.getY() + 28.0F;
      float visibleBottom = visibleTop + this.height - 28.0F;
      ScissorUtil.start((double)this.getX(), (double)(this.getY() + 28.0F), (double)this.getWidth(), (double)(this.getHeight() - 28.66F));
      float offset = 0.0F;

      ModuleElement element;
      for(Iterator var10 = this.modules.iterator(); var10.hasNext(); offset += element.getHeight() + 4.0F) {
         element = (ModuleElement)var10.next();
         element.setX(this.getX() + 8.0F);
         element.setY((float)Math.round(this.getY() + 30.0F + offset + animatedScroll));
         element.setWidth(this.getWidth() - 16.0F);
         element.setHeight(17.0F);
         float moduleTop;
         float moduleBottom;
         if (element.getAnimation().getValue() > 0.0F) {
            moduleTop = (float)element.getElements().stream().mapToDouble((sub) -> {
               return (double)(sub.getHeight() * sub.getVisibilityAnimation());
            }).sum();
            moduleBottom = 16.0F + moduleTop * element.getAnimation().getValue();
            element.setHeight(moduleBottom);
         }

         moduleTop = element.getY();
         moduleBottom = moduleTop + element.getHeight();
         if (moduleBottom > visibleTop && moduleTop < visibleBottom) {
            element.render(stack, mouseX, mouseY, alpha);
         }
      }

      this.maxHeight = offset;
      ScissorUtil.end();
   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      if (MathUtil.isHovered(mouseX, mouseY, this.getX(), this.getY() + 28.0F, this.getWidth(), this.getHeight() - 35.0F)) {
         Iterator var4 = this.modules.iterator();

         while(var4.hasNext()) {
            ModuleElement element = (ModuleElement)var4.next();
            float moduleTop = element.getY();
            float moduleBottom = moduleTop + element.getHeight();
            if (moduleBottom > this.getY() + 28.0F && moduleTop < this.getY() + 28.0F + this.getHeight() - 35.0F && MathUtil.isHovered(mouseX, mouseY, element.getX(), element.getY(), element.getWidth(), element.getHeight())) {
               element.mouseClicked(mouseX, mouseY, button);
            }
         }
      }

   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      Iterator var4 = this.modules.iterator();

      while(var4.hasNext()) {
         ModuleElement element = (ModuleElement)var4.next();
         element.mouseReleased(mouseX, mouseY, button);
      }

   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (MathUtil.isHovered((float)mouseX, (float)mouseY, this.getX(), this.getY() + 28.0F, this.getWidth(), this.getHeight() - 35.0F)) {
         float visibleTop = this.getY() + 28.0F;
         float visibleBottom = visibleTop + this.getHeight() - 28.0F;
         Iterator var12 = this.modules.iterator();

         while(var12.hasNext()) {
            ModuleElement element = (ModuleElement)var12.next();
            float moduleTop = element.getY();
            float moduleBottom = moduleTop + element.getHeight();
            if (moduleBottom > visibleTop && moduleTop < visibleBottom && element.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
               return true;
            }
         }
      }

      return false;
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      Iterator var4 = this.modules.iterator();

      while(var4.hasNext()) {
         ModuleElement element = (ModuleElement)var4.next();
         element.keyPressed(keyCode, scanCode, modifiers);
      }

   }

   public void charTyped(char codePoint, int modifiers) {
      Iterator var3 = this.modules.iterator();

      while(var3.hasNext()) {
         ModuleElement element = (ModuleElement)var3.next();
         element.charTyped(codePoint, modifiers);
      }

   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      Iterator var7 = this.modules.iterator();

      while(var7.hasNext()) {
         ModuleElement element = (ModuleElement)var7.next();
         if (element.mouseScrolled(mouseX, mouseY, delta)) {
            return true;
         }
      }

      if (MathUtil.isHovered((float)mouseX, (float)mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         float contentHeight = this.getHeight() - 34.5F;
         boolean canScroll = this.maxHeight > contentHeight;
         float previousScroll = this.scroll;
         this.setScroll((float)((double)this.getScroll() + delta * 20.0));
         this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
         return canScroll && this.scroll != previousScroll;
      } else {
         return false;
      }
   }

   public Category getCategory() {
      return this.category;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getMaxHeight() {
      return this.maxHeight;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public List getModules() {
      return this.modules;
   }

   public List getAllModules() {
      return this.allModules;
   }

   public AnimationUtil getScrollAnimation() {
      return this.scrollAnimation;
   }

   public float getScroll() {
      return this.scroll;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setMaxHeight(float maxHeight) {
      this.maxHeight = maxHeight;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public void setModules(List modules) {
      this.modules = modules;
   }

   public void setScrollAnimation(AnimationUtil scrollAnimation) {
      this.scrollAnimation = scrollAnimation;
   }

   public void setScroll(float scroll) {
      this.scroll = scroll;
   }
}
