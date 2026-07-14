package sky.core.ui.gui.guihandlers;

import com.mojang.blaze3d.matrix.MatrixStack;
import sky.core.modules.api.elements.Element;
import sky.core.modules.api.elements.ModuleElement;
import sky.core.modules.api.elements.impl.BooleanElement;
import sky.core.ui.gui.additionals.BindingPanel;
import sky.core.utils.Wrapper;

public class BindingPanelHandler implements Wrapper {
   private BindingPanel activePanel;
   private Element activeElement;

   public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
      if (this.activePanel != null && this.activeElement != null) {
         this.activePanel.updatePosition(this.activeElement.getX() + this.activeElement.getWidth(), this.activeElement.getY() + 4.0F);
         this.activePanel.render(matrixStack);
         if (this.activePanel.shouldClose && this.activePanel.isClosed()) {
            this.activePanel = null;
            this.activeElement = null;
         }
      }

   }

   public boolean mouseClicked(float mouseX, float mouseY, int button) {
      return this.activePanel != null ? this.activePanel.mouseClicked(mouseX, mouseY, button) : false;
   }

   public boolean keyPressed(int keyCode) {
      if (this.activePanel != null) {
         this.activePanel.keyPressed(keyCode);
         return true;
      } else {
         return false;
      }
   }

   public void close() {
      if (this.activePanel != null) {
         this.activePanel.shouldClose = true;
      }

   }

   public boolean isActive() {
      return this.activePanel != null && this.activeElement != null && !this.activePanel.isClosed();
   }

   public void openForModule(ModuleElement moduleElement) {
      if (moduleElement != null) {
         moduleElement.setBind(true);
         if (this.activePanel != null && this.activeElement == moduleElement) {
            this.activePanel.shouldClose = false;
         } else {
            this.activeElement = moduleElement;
            this.activePanel = new BindingPanel(this.activeElement);
         }
      }
   }

   public void openForBoolean(BooleanElement booleanElement) {
      if (booleanElement != null) {
         booleanElement.setBind(true);
         if (this.activePanel != null && this.activeElement == booleanElement) {
            this.activePanel.shouldClose = false;
         } else {
            this.activeElement = booleanElement;
            this.activePanel = new BindingPanel(this.activeElement);
         }
      }
   }
}
