package unique.core.gui.clickui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.BaritoneSettings;
import unique.core.features.modules.client.ClickGui;
import unique.core.gui.clickui.impl.SearchBar;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class Category extends AbstractCategory {
   private boolean scrollHover;
   private final List<AbstractButton> buttons = new ArrayList<>();
   public float catHeight;

   public Category(Module.Category category, ArrayList<Module> features, float x, float y, float width, float height) {
      super(category.getName(), x, y, width, height);
      if (category.getName().equals("Client")) {
         this.buttons.add(new SearchBar());
      }

      features.forEach(feature -> {
         if (!(feature instanceof BaritoneSettings) || UniqueCore.baritone) {
            this.buttons.add(new ModuleButton(feature));
         }
      });
   }

   @Override
   public void init() {
      this.buttons.forEach(AbstractButton::init);
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      this.setWidth(ModuleManager.clickGui.moduleWidth.getValue().intValue());
      this.scrollHover = Render2DEngine.isHovered(mouseX, mouseY, this.getX(), this.getY() + this.height, this.width, this.catHeight + 20.0F);
      context.method_51448().method_22903();
      boolean popStack = false;
      float height1;
      if (ModuleManager.clickGui.scrollMode.getValue() != ClickGui.scrollModeEn.Old
         && !(this.getButtonsHeight() < ModuleManager.clickGui.catHeight.getValue().intValue())) {
         height1 = ModuleManager.clickGui.catHeight.getValue().intValue();
      } else {
         height1 = (float)this.getButtonsHeight();
      }

      this.catHeight = AnimationUtility.fast(this.catHeight, height1, 30.0F);
      if (this.isOpen()) {
         Render2DEngine.drawHudBase(
            context.method_51448(), this.getX() + 3.0F, this.getY() + this.height - 6.0F, this.width - 6.0F, this.catHeight, 1.0F, false
         );
         if (ModuleManager.clickGui.scrollMode.getValue() != ClickGui.scrollModeEn.Old
            && !(this.getButtonsHeight() < ModuleManager.clickGui.catHeight.getValue().intValue())) {
            Render2DEngine.addWindow(
               context.method_51448(),
               this.getX() + 3.0F,
               this.getY() + this.height - 6.0F,
               this.getX() + 3.0F + this.width - 6.0F,
               this.getY() + this.height - 6.0F + ModuleManager.clickGui.catHeight.getValue().intValue(),
               1.0
            );
            popStack = true;
         }

         Render2DEngine.drawBlurredShadow(
            context.method_51448(), (int)this.getX() + 4, (int)(this.getY() + this.height - 6.0F), (int)this.width - 8, 8.0F, 7, new Color(0, 0, 0, 180)
         );

         for (AbstractButton button : this.buttons) {
            if (!(button instanceof ModuleButton mb && SearchBar.listening && !mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase()))) {
               if (popStack && this.buttons.getFirst().getY() + this.moduleOffset < this.getY() + this.height) {
                  button.setY(this.getY() + this.height + this.moduleOffset);
               } else {
                  button.setY(this.getY() + this.height);
                  this.moduleOffset = 0.0F;
               }

               button.setX(this.getX() + 2.0F);
               button.setWidth(this.width - 4.0F);
               button.setHeight(ModuleManager.clickGui.moduleHeight.getValue().intValue());
               button.render(context, mouseX, mouseY, delta);
            }
         }
      }

      if (popStack) {
         Render2DEngine.popWindow();
      }

      Render2DEngine.drawHudBase(context.method_51448(), this.getX() + 2.0F, this.getY() - 5.0F, this.width - 4.0F, this.height, 1.0F, false);
      Render2DEngine.drawBlurredShadow(
         context.method_51448(),
         (int)this.getX() + (this.width - 4.0F) / 2.0F - FontRenderers.categories.getStringWidth(this.getName()) / 2.0F,
         (int)this.getY() + this.height / 2.0F - 10.0F,
         FontRenderers.categories.getStringWidth(this.getName()) + 6.0F,
         13.0F,
         20,
         Render2DEngine.injectAlpha(Color.black, 170)
      );
      FontRenderers.categories
         .drawCenteredString(
            context.method_51448(),
            this.getName(),
            (int)this.getX() + 2 + (this.width - 4.0F) / 2.0F,
            (int)this.getY() + this.height / 2.0F - 7.0F,
            new Color(-1).getRGB()
         );
      context.method_51448().method_22909();
      this.updatePosition();
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (button == 1 && this.hovered) {
         this.setOpen(!this.isOpen());
      }

      super.mouseClicked(mouseX, mouseY, button);
      if (this.isOpen() && this.scrollHover) {
         this.buttons.forEach(b -> b.mouseClicked(mouseX, mouseY, button));
      }
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int button) {
      super.mouseReleased(mouseX, mouseY, button);
      if (this.isOpen()) {
         this.buttons.forEach(b -> b.mouseReleased(mouseX, mouseY, button));
      }
   }

   @Override
   public boolean keyTyped(int keyCode) {
      if (this.isOpen()) {
         this.buttons.forEach(b -> b.keyTyped(keyCode));
      }

      return false;
   }

   @Override
   public void charTyped(char key, int keyCode) {
      if (this.isOpen()) {
         this.buttons.forEach(b -> b.charTyped(key, keyCode));
      }
   }

   @Override
   public void onClose() {
      super.onClose();
      this.buttons.forEach(AbstractButton::onGuiClosed);
   }

   @Override
   public void tick() {
      this.buttons.forEach(AbstractButton::tick);
   }

   private void updatePosition() {
      float offsetY = 0.0F;

      for (AbstractButton button : this.buttons) {
         if (!(button instanceof ModuleButton mb) || !SearchBar.listening || mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase())) {
            button.setTargetOffset(offsetY);
            if (button instanceof ModuleButton mbutton && mbutton.isOpen()) {
               for (AbstractElement element : mbutton.getElements()) {
                  if (element.isVisible()) {
                     offsetY += element.getHeight();
                  }
               }

               offsetY += 2.0F;
            }

            offsetY += button.getHeight();
         }
      }
   }

   @Override
   public void hudClicked(Module module) {
      for (AbstractButton button : this.buttons) {
         if (button instanceof ModuleButton mbutton && mbutton.module == module) {
            mbutton.setOpen(true);
         }
      }
   }

   public double getButtonsHeight() {
      double height = 8.0;

      for (AbstractButton button : this.buttons) {
         if (!(button instanceof ModuleButton mb && SearchBar.listening && !mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase()))) {
            if (button instanceof ModuleButton mbutton) {
               if (mbutton.isOpen()) {
                  height += 2.0;
               }

               height += mbutton.getElementsHeight();
            }

            height += button.getHeight();
         }
      }

      return height;
   }
}
