package unique.core.gui.clickui.impl;

import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_3544;
import org.lwjgl.glfw.GLFW;
import unique.core.UniqueCore;
import unique.core.features.modules.Module;
import unique.core.gui.clickui.AbstractElement;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;

public class StringElement extends AbstractElement {
   public boolean listening;
   private String currentString = "";

   public StringElement(Setting setting) {
      super(setting);
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      Render2DEngine.drawRect(context.method_51448(), this.getX() + 5.0F, this.getY() + 2.0F, this.getWidth() - 11.0F, 10.0F, new Color(-1811939328, true));
      FontRenderers.sf_medium_mini
         .drawString(
            context.method_51448(),
            this.listening
               ? this.currentString + (Module.mc.field_1724 != null && Module.mc.field_1724.field_6012 % 5 != 0 ? "" : "_")
               : (String)this.setting.getValue(),
            this.x + 6.0F,
            this.y + this.height / 2.0F,
            -1
         );
      if (Render2DEngine.isHovered(mouseX, mouseY, this.getX() + 5.0F, this.getY() + 2.0F, this.getWidth() - 11.0F, 10.0)) {
         if (GLFW.glfwGetPlatform() != 393219) {
            GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221186));
         }

         ClickGUI.anyHovered = true;
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (this.hovered && button == 0) {
         this.listening = !this.listening;
      }

      if (this.listening) {
         UniqueCore.currentKeyListener = UniqueCore.KeyListening.Strings;
         this.currentString = (String)this.setting.getValue();
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   @Override
   public void charTyped(char key, int keyCode) {
      if (class_3544.method_57175(key)) {
         this.currentString = this.currentString + key;
      }
   }

   @Override
   public void keyTyped(int keyCode) {
      if (UniqueCore.currentKeyListener == UniqueCore.KeyListening.Strings) {
         if (this.listening) {
            switch (keyCode) {
               case 32:
                  this.currentString = this.currentString + " ";
               case 256:
               default:
                  break;
               case 257:
                  this.setting.setValue(this.currentString != null && !this.currentString.isEmpty() ? this.currentString : this.setting.getDefaultValue());
                  this.currentString = "";
                  this.listening = !this.listening;
                  break;
               case 259:
                  this.currentString = SliderElement.removeLastChar(this.currentString);
            }
         }
      }
   }
}
