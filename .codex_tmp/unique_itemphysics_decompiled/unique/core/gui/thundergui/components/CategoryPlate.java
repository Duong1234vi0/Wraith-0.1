package unique.core.gui.thundergui.components;

import java.awt.Color;
import net.minecraft.class_4587;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.thundergui.ThunderGui;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class CategoryPlate {
   float category_animation = 0.0F;
   private final Module.Category cat;
   private int posX;
   private int posY;

   public CategoryPlate(Module.Category cat, int posX, int posY) {
      this.cat = cat;
      this.posX = posX;
      this.posY = posY;
   }

   public void render(class_4587 matrixStack, int MouseX, int MouseY) {
      this.category_animation = AnimationUtility.fast(this.category_animation, this.isHovered(MouseX, MouseY) ? 1.0F : 0.0F, 15.0F);
      Render2DEngine.addWindow(matrixStack, new Render2DEngine.Rectangle(this.posX, this.posY + 0.5F, this.posX + 84, this.posY + 15.5F));
      if (this.isHovered(MouseX, MouseY)) {
         Render2DEngine.drawRound(
            matrixStack, this.posX, this.posY, 84.0F, 15.0F, 2.0F, new Color(25, 20, 30, (int)MathUtility.clamp(65.0F * this.category_animation, 0.0F, 255.0F))
         );
         Render2DEngine.drawBlurredShadow(matrixStack, MouseX - 20, MouseY - 20, 40.0F, 40.0F, 60, new Color(-1017816450, true));
      }

      FontRenderers.modules.drawString(matrixStack, this.cat.getName(), this.posX + 5, this.posY + 6, -1);
      Render2DEngine.popWindow();
   }

   public void movePosition(float deltaX, float deltaY) {
      this.posY = (int)(this.posY + deltaY);
      this.posX = (int)(this.posX + deltaX);
   }

   public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
      if (this.isHovered(mouseX, mouseY)) {
         ThunderGui.getInstance().new_category = this.cat;
         if (ThunderGui.getInstance().current_category == null) {
            ThunderGui.getInstance().current_category = Module.Category.HUD;
            ThunderGui.getInstance().new_category = this.cat;
         }
      }
   }

   private boolean isHovered(int mouseX, int mouseY) {
      return mouseX > this.posX && mouseX < this.posX + 84 && mouseY > this.posY && mouseY < this.posY + 15;
   }

   public Module.Category getCategory() {
      return this.cat;
   }

   public int getPosY() {
      return this.posY;
   }
}
