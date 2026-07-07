package unique.core.gui.clickui.impl;

import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import unique.core.core.Managers;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.clickui.AbstractElement;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;
import unique.core.utility.render.animation.AnimationUtility;

public class ModeElement extends AbstractElement {
   public Setting setting2;
   private boolean open;
   private double wheight;
   private String prevMode;
   private float animation;
   private float animation2;

   public ModeElement(Setting setting) {
      super(setting);
      this.setting2 = setting;
      this.prevMode = setting.currentEnumName();
   }

   @Override
   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.animation = AnimationUtility.fast(this.animation, this.open ? 0.0F : 1.0F, 15.0F);
      this.animation2 = AnimationUtility.fast(this.animation2, 1.0F, 10.0F);
      float tx = this.x + this.width - 11.0F;
      float ty = this.y + 7.5F;
      class_4587 matrixStack = context.method_51448();
      float thetaRotation = -180.0F * this.animation;
      matrixStack.method_22903();
      matrixStack.method_46416(tx, ty, 0.0F);
      matrixStack.method_22907(class_7833.field_40718.rotationDegrees(thetaRotation));
      matrixStack.method_46416(-tx, -ty, 0.0F);
      matrixStack.method_46416(this.x + this.width - 14.0F, this.y + 4.5F, 0.0F);
      context.method_25290(TextureStorage.guiArrow, 0, 0, 0.0F, 0.0F, 6, 6, 6, 6);
      matrixStack.method_46416(-(this.x + this.width - 14.0F), -this.y - 4.5F, 0.0F);
      matrixStack.method_22909();
      if (this.setting.group != null) {
         Render2DEngine.drawRect(context.method_51448(), this.x + 4.0F, this.y, 1.0F, 17.0F, HudEditor.getColor(1));
      }

      FontRenderers.sf_medium_mini
         .drawString(
            matrixStack,
            this.setting2.getName(),
            (this.setting.group != null ? 2.0F : 0.0F) + (this.x + 6.0F),
            this.y + this.wheight / 2.0 - 3.0 + 3.0,
            new Color(-1).getRGB()
         );
      if (this.animation2 < 0.99 && !Objects.equals(this.setting2.currentEnumName(), this.prevMode)) {
         FontRenderers.sf_medium_mini
            .drawString(
               matrixStack,
               this.prevMode,
               (int)(this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.prevMode)),
               3.0 + (this.y + this.wheight / 2.0 - 3.0) - this.animation2 * 5.0F,
               Render2DEngine.applyOpacity(new Color(-1), this.animation2)
            );
         FontRenderers.sf_medium_mini
            .drawString(
               matrixStack,
               this.setting2.currentEnumName(),
               this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting2.currentEnumName()),
               3.0 + (this.y + this.wheight / 2.0 - 3.0) - this.animation2 * 5.0F + 5.0,
               Render2DEngine.applyOpacity(new Color(-1), 1.0F - this.animation2)
            );
      } else {
         FontRenderers.sf_medium_mini
            .drawString(
               matrixStack,
               this.setting2.currentEnumName(),
               this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting.currentEnumName()),
               3.0 + (this.y + this.wheight / 2.0 - 3.0),
               new Color(-1).getRGB()
            );
      }

      if (this.open) {
         Color color = HudEditor.getColor(0);
         double offsetY = 0.0;

         for (int i = 0; i <= this.setting2.getModes().length - 1; i++) {
            FontRenderers.sf_medium_mini
               .drawString(
                  matrixStack,
                  this.setting2.getModes()[i],
                  this.x + this.width / 2.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting2.getModes()[i]) / 2.0F,
                  this.y + this.wheight + 2.0 + offsetY,
                  this.setting2.currentEnumName().equalsIgnoreCase(this.setting2.getModes()[i]) ? color.getRGB() : new Color(-1).getRGB()
               );
            offsetY += 12.0;
         }
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int button) {
      if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.wheight)) {
         if (button == 0) {
            this.prevMode = this.setting2.currentEnumName();
            this.animation2 = 0.0F;
            this.setting2.increaseEnum();
            Managers.SOUND.playBoolean();
         } else {
            this.open = !this.open;
            if (this.open) {
               Managers.SOUND.playSwipeIn();
            } else {
               Managers.SOUND.playSwipeOut();
            }
         }
      }

      if (this.open) {
         double offsetY = 0.0;

         for (int i = 0; i <= this.setting2.getModes().length - 1; i++) {
            if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y + this.wheight + offsetY, this.width, 12.0) && button == 0) {
               this.prevMode = this.setting2.currentEnumName();
               this.animation2 = 0.0F;
               this.setting2.setEnumByNumber(i);
               Managers.SOUND.playBoolean();
            }

            offsetY += 12.0;
         }
      }

      super.mouseClicked(mouseX, mouseY, button);
   }

   public void setWHeight(double height) {
      this.wheight = height;
   }

   public boolean isOpen() {
      return this.open;
   }
}
