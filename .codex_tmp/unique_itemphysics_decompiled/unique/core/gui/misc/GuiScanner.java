package unique.core.gui.misc;

import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;

public class GuiScanner extends class_437 {
   int radarx;
   int radary;
   int radarx1;
   int radary1;
   int centerx;
   int centery;
   int consolex;
   int consoley;
   int consolex1;
   int consoley1;
   int hovery;
   int hoverx;
   int searchx;
   int searchy;
   int wheely;

   public GuiScanner() {
      super(class_2561.method_30163("GuiScanner"));
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      if (Module.mc.field_1724 != null) {
         this.radarx = Module.mc.method_22683().method_4486() / 8;
         this.radarx1 = Module.mc.method_22683().method_4486() * 5 / 8;
         this.radary = Module.mc.method_22683().method_4502() / 2 - (this.radarx1 - this.radarx) / 2;
         this.radary1 = Module.mc.method_22683().method_4502() / 2 + (this.radarx1 - this.radarx) / 2;
         this.centerx = (this.radarx + this.radarx1) / 2;
         this.centery = (this.radary + this.radary1) / 2;
         this.consolex = (int)(Module.mc.method_22683().method_4486() * 5.5F / 8.0F);
         this.consolex1 = Module.mc.method_22683().method_4486() - 50;
         this.consoley = this.radary;
         this.consoley1 = this.radary1 - 50;
         Render2DEngine.drawRectDumbWay(context.method_51448(), this.consolex, this.consoley, this.consolex1, this.consoley1, new Color(-150205428, true));
         Render2DEngine.drawRectDumbWay(
            context.method_51448(), this.consolex, this.consoley1 + 3, this.consolex1, this.consoley1 + 17, new Color(-150205428, true)
         );
         FontRenderers.monsterrat
            .drawString(context.method_51448(), "cursor pos: " + this.hoverx * 64 + "x  " + this.hovery * 64 + "z", this.consolex + 4, this.consoley1 + 6, -1);
         Render2DEngine.drawRectDumbWay(
            context.method_51448(), this.consolex, this.consoley1 + 20, this.consolex1, this.consoley1 + 35, new Color(-150205428, true)
         );
         FontRenderers.monsterrat.drawString(context.method_51448(), "tracker off", this.consolex + 4, this.consoley1 + 26, -1);
         Render2DEngine.drawRectDumbWay(context.method_51448(), this.radarx, this.radary, this.radarx1, this.radary1, new Color(-535489259, true));
         Render2DEngine.drawRectDumbWay(
            context.method_51448(), this.centerx - 1.0F, this.centery - 1.0F, this.centerx + 1.0F, this.centery + 1.0F, new Color(16712451)
         );
         Render2DEngine.drawRectDumbWay(
            context.method_51448(),
            (float)(Module.mc.field_1724.method_23317() / 16.0 / 4.0 + this.centerx),
            (float)(Module.mc.field_1724.method_23321() / 16.0 / 4.0 + this.centery),
            (float)(Module.mc.field_1724.method_23317() / 16.0 / 4.0 + (this.radarx1 - this.radarx) / 300.0F + this.centerx),
            (float)(Module.mc.field_1724.method_23321() / 16.0 / 4.0 + (this.radary1 - this.radary) / 300.0F + this.centery),
            new Color(4863)
         );
         if (mouseX > this.radarx && mouseX < this.radarx1 && mouseY > this.radary && mouseY < this.radary1) {
            this.hoverx = mouseX - this.centerx;
            this.hovery = mouseY - this.centery;
         }

         FontRenderers.monsterrat.drawString(context.method_51448(), "X+", this.radarx1 + 5, this.centery, -1);
         FontRenderers.monsterrat.drawString(context.method_51448(), "X-", this.radarx - 15, this.centery, -1);
         FontRenderers.monsterrat.drawString(context.method_51448(), "Y+", this.centerx, this.radary1 + 5, -1);
         FontRenderers.monsterrat.drawString(context.method_51448(), "Y-", this.centerx, this.radary - 8, -1);
      }
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      return super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
   }
}
