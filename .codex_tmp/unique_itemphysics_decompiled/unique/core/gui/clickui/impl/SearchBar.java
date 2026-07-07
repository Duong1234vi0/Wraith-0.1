package unique.core.gui.clickui.impl;

import net.minecraft.class_332;
import net.minecraft.class_3544;
import net.minecraft.class_3675;
import org.lwjgl.glfw.GLFW;
import unique.core.UniqueCore;
import unique.core.features.modules.Module;
import unique.core.gui.clickui.AbstractButton;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;

public class SearchBar extends AbstractButton {
   public static String moduleName = "";
   public static boolean listening;

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      Render2DEngine.drawGuiBase(
         context.method_51448(),
         this.x + 4.0F,
         this.y + 1.0F,
         this.width - 8.0F,
         this.height - 2.0F,
         1.0F,
         Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height) ? 0.8F : 0.0F
      );
      if (!listening) {
         FontRenderers.sf_medium.drawGradientString(context.method_51448(), "Search...", this.x + 7.0F, this.y + this.height / 2.0F - 3.0F, 2);
      } else {
         FontRenderers.sf_medium
            .drawGradientString(
               context.method_51448(),
               moduleName + (Module.mc.field_1724 != null && Module.mc.field_1724.field_6012 / 10 % 2 != 0 ? "_" : " "),
               this.x + 7.0F,
               this.y + 5.0F,
               2
            );
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
         if (GLFW.glfwGetPlatform() != 393219) {
            GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221186));
         }

         ClickGUI.anyHovered = true;
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      super.mouseClicked(mouseX, mouseY, button);
      boolean isHovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
      if (isHovered) {
         listening = true;
      } else {
         moduleName = "";
         listening = false;
      }

      if (listening) {
         UniqueCore.currentKeyListener = UniqueCore.KeyListening.Search;
      }
   }

   @Override
   public void charTyped(char key, int keyCode) {
      if (class_3544.method_57175(key) && listening) {
         moduleName = moduleName + key;
      }
   }

   @Override
   public void keyTyped(int keyCode) {
      super.keyTyped(keyCode);
      if (keyCode != 70
         || !class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) && !class_3675.method_15987(Module.mc.method_22683().method_4490(), 345)) {
         if (UniqueCore.currentKeyListener == UniqueCore.KeyListening.Search) {
            if (listening) {
               switch (keyCode) {
                  case 32:
                     moduleName = moduleName + " ";
                     break;
                  case 256:
                  case 257:
                     listening = false;
                     moduleName = "";
                     break;
                  case 259:
                     moduleName = SliderElement.removeLastChar(moduleName);
               }
            }
         }
      } else {
         listening = !listening;
         UniqueCore.currentKeyListener = UniqueCore.KeyListening.Search;
      }
   }
}
