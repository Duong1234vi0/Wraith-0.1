package unique.core.gui.thundergui.components;

import java.awt.Color;
import net.minecraft.class_332;
import unique.core.core.Managers;
import unique.core.features.modules.client.ThunderHackGui;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.thundergui.ThunderGui;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class ConfigComponent {
   float scroll_animation = 0.0F;
   private final String name;
   private final String date;
   private int posX;
   private int posY;
   private int progress;
   private int fade;
   private final int index;
   private boolean first_open = true;
   private float scrollPosY;
   private float prevPosY;

   public ConfigComponent(String name, String date, int posX, int posY, int index) {
      this.name = name;
      this.date = date;
      this.posX = posX;
      this.posY = posY;
      this.fade = 0;
      this.index = index * 5;
      this.scrollPosY = posY;
      this.scroll_animation = 0.0F;
   }

   public void render(class_332 context, int MouseX, int MouseY) {
      if (this.scrollPosY != this.posY) {
         this.scroll_animation = AnimationUtility.fast(this.scroll_animation, 1.0F, 15.0F);
         this.posY = (int)Render2DEngine.interpolate(this.prevPosY, this.scrollPosY, this.scroll_animation);
      }

      if (this.posY <= ThunderGui.getInstance().main_posY + ThunderGui.getInstance().field_22790 && this.posY >= ThunderGui.getInstance().main_posY) {
         if (Managers.CONFIG.currentConfig.getName().equals(this.name + ".th")) {
            Render2DEngine.drawGradientRound(
               context.method_51448(),
               this.posX + 5,
               this.posY,
               285.0F,
               30.0F,
               4.0F,
               Render2DEngine.applyOpacity(new Color(55, 44, 66, 255), this.getFadeFactor()),
               Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), this.getFadeFactor()),
               Render2DEngine.applyOpacity(ThunderHackGui.onColor1.getValue().getColorObject(), this.getFadeFactor()),
               Render2DEngine.applyOpacity(ThunderHackGui.onColor2.getValue().getColorObject(), this.getFadeFactor())
            );
         } else {
            Render2DEngine.drawRound(
               context.method_51448(),
               this.posX + 5,
               this.posY,
               285.0F,
               30.0F,
               4.0F,
               Render2DEngine.applyOpacity(new Color(44, 35, 52, 255), this.getFadeFactor())
            );
         }

         if (this.first_open) {
            Render2DEngine.addWindow(context.method_51448(), this.posX + 5, this.posY, this.posX + 5 + 285, this.posY + 30, 1.0);
            Render2DEngine.drawBlurredShadow(
               context.method_51448(),
               MouseX - 20,
               MouseY - 20,
               40.0F,
               40.0F,
               60,
               Render2DEngine.applyOpacity(new Color(-1017816450, true), this.getFadeFactor())
            );
            Render2DEngine.popWindow();
            this.first_open = false;
         }

         if (this.isHovered(MouseX, MouseY)) {
            Render2DEngine.addWindow(context.method_51448(), this.posX + 5, this.posY, this.posX + 5 + 285, this.posY + 30, 1.0);
            Render2DEngine.drawBlurredShadow(
               context.method_51448(),
               MouseX - 20,
               MouseY - 20,
               40.0F,
               40.0F,
               60,
               Render2DEngine.applyOpacity(new Color(-1017816450, true), this.getFadeFactor())
            );
            Render2DEngine.popWindow();
         }

         Render2DEngine.drawRound(
            context.method_51448(),
            this.posX + 250,
            this.posY + 8,
            30.0F,
            14.0F,
            2.0F,
            Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), this.getFadeFactor())
         );
         if (Render2DEngine.isHovered(MouseX, MouseY, this.posX + 252, this.posY + 10, 10.0, 10.0)) {
            Render2DEngine.drawRound(
               context.method_51448(),
               this.posX + 252,
               this.posY + 10,
               10.0F,
               10.0F,
               2.0F,
               Render2DEngine.applyOpacity(new Color(21, 58, 0, 255), this.getFadeFactor())
            );
         } else {
            Render2DEngine.drawRound(
               context.method_51448(),
               this.posX + 252,
               this.posY + 10,
               10.0F,
               10.0F,
               2.0F,
               Render2DEngine.applyOpacity(new Color(32, 89, 0, 255), this.getFadeFactor())
            );
         }

         if (Render2DEngine.isHovered(MouseX, MouseY, this.posX + 268, this.posY + 10, 10.0, 10.0)) {
            Render2DEngine.drawRound(
               context.method_51448(),
               this.posX + 268,
               this.posY + 10,
               10.0F,
               10.0F,
               2.0F,
               Render2DEngine.applyOpacity(new Color(65, 1, 13, 255), this.getFadeFactor())
            );
         } else {
            Render2DEngine.drawRound(
               context.method_51448(),
               this.posX + 268,
               this.posY + 10,
               10.0F,
               10.0F,
               2.0F,
               Render2DEngine.applyOpacity(new Color(94, 1, 18, 255), this.getFadeFactor())
            );
         }

         FontRenderers.icons.drawString(context.method_51448(), "x", this.posX + 252, this.posY + 13, Render2DEngine.applyOpacity(-1, this.getFadeFactor()));
         FontRenderers.icons.drawString(context.method_51448(), "w", this.posX + 268, this.posY + 13, Render2DEngine.applyOpacity(-1, this.getFadeFactor()));
         FontRenderers.mid_icons.drawString(context.method_51448(), "u", this.posX + 7, this.posY + 5, Render2DEngine.applyOpacity(-1, this.getFadeFactor()));
         FontRenderers.modules
            .drawString(context.method_51448(), this.name, this.posX + 37, this.posY + 6, Render2DEngine.applyOpacity(-1, this.getFadeFactor()));
         FontRenderers.settings
            .drawString(
               context.method_51448(),
               "updated on: " + this.date,
               this.posX + 37,
               this.posY + 17,
               Render2DEngine.applyOpacity(new Color(-4342339, true).getRGB(), this.getFadeFactor())
            );
      }
   }

   private float getFadeFactor() {
      return this.fade / (5.0F + this.index);
   }

   public void onTick() {
      if (this.progress > 4) {
         this.progress = 0;
      }

      this.progress++;
      if (this.fade < 10 + this.index) {
         this.fade++;
      }
   }

   private boolean isHovered(int mouseX, int mouseY) {
      return mouseX > this.posX && mouseX < this.posX + 295 && mouseY > this.posY && mouseY < this.posY + 30;
   }

   public void movePosition(float deltaX, float deltaY) {
      this.posY = (int)(this.posY + deltaY);
      this.posX = (int)(this.posX + deltaX);
      this.scrollPosY = this.posY;
   }

   public void mouseClicked(int MouseX, int MouseY, int clickedButton) {
      if (this.posY <= ThunderGui.getInstance().main_posY + ThunderGui.getInstance().field_22790 && this.posY >= ThunderGui.getInstance().main_posY) {
         if (Render2DEngine.isHovered(MouseX, MouseY, this.posX + 252, this.posY + 10, 10.0, 10.0)) {
            Managers.CONFIG.load(this.name);
         }

         if (Render2DEngine.isHovered(MouseX, MouseY, this.posX + 268, this.posY + 10, 10.0, 10.0)) {
            Managers.CONFIG.delete(this.name);
            ThunderGui.getInstance().loadConfigs();
         }
      }
   }

   public double getPosX() {
      return this.posX;
   }

   public double getPosY() {
      return this.posY;
   }

   public void scrollElement(float deltaY) {
      this.scroll_animation = 0.0F;
      this.prevPosY = this.posY;
      this.scrollPosY += deltaY;
   }
}
