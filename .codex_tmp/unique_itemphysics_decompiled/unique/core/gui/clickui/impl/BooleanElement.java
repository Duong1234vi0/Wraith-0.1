package unique.core.gui.clickui.impl;

import java.awt.Color;
import net.minecraft.class_332;
import org.lwjgl.glfw.GLFW;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.clickui.AbstractElement;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class BooleanElement extends AbstractElement {
   float animation = 0.0F;
   float animation2 = 0.0F;

   public BooleanElement(Setting setting) {
      super(setting);
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      super.render(context, mouseX, mouseY, delta);
      Render2DEngine.drawRound(
         context.method_51448(),
         this.x + this.width - 21.0F,
         this.y + this.height / 2.0F - 4.0F,
         15.0F,
         8.0F,
         1.0F,
         7.0F * this.animation > 4.0F ? HudEditor.getColor(0) : new Color(687865855, true)
      );
      this.animation = AnimationUtility.fast(this.animation, this.setting.getValue() ? 1.0F : 0.0F, 20.0F);
      this.animation2 = AnimationUtility.fast(this.animation2, this.setting.getValue() ? 1.0F : 0.0F, 8.0F);
      Render2DEngine.drawRound(
         context.method_51448(), this.x + this.width - 20.0F + 7.0F * this.animation, this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 1.0F, new Color(-1)
      );
      Render2DEngine.drawRound(
         context.method_51448(), this.x + this.width - 20.0F + 7.0F * this.animation2, this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 1.0F, new Color(-1)
      );
      if (7.0F * this.animation > 4.0F) {
         FontRenderers.sf_bold_mini
            .drawString(context.method_51448(), "v", this.x + this.width - 19.0F, this.y + this.height / 2.0F - 2.0F, new Color(-1).getRGB());
      } else {
         FontRenderers.sf_bold_mini
            .drawString(context.method_51448(), "x", this.x + this.width - 12.0F, this.y + this.height / 2.0F - 2.0F, new Color(-1).getRGB());
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, this.x + this.width - 21.0F, this.y + this.height / 2.0F - 4.0F, 15.0, 8.0)) {
         if (GLFW.glfwGetPlatform() != 393219) {
            GLFW.glfwSetCursor(IManager.mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221188));
         }

         ClickGUI.anyHovered = true;
      }

      FontRenderers.sf_medium_mini
         .drawString(
            context.method_51448(),
            this.setting.getName(),
            (this.setting.group != null ? 2.0F : 0.0F) + (this.x + 6.0F),
            this.y + this.height / 2.0F - 3.0F + 2.0F,
            new Color(-1).getRGB()
         );
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (this.hovered && button == 0) {
         this.setting.setValue(!(Boolean)this.setting.getValue());
         Managers.SOUND.playBoolean();
      }

      super.mouseClicked(mouseX, mouseY, button);
   }
}
