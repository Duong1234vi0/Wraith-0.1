package unique.core.gui.mainmenu;

import net.minecraft.class_332;
import org.jetbrains.annotations.NotNull;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;

public class MainMenuButton {
   private final float posX;
   private final float posY;
   private final float width;
   private final float height;
   private final String name;
   private final Runnable action;

   public MainMenuButton(float posX, float posY, @NotNull String name, Runnable action, boolean isExit) {
      this.name = name;
      this.posX = posX;
      this.posY = posY;
      this.action = action;
      this.width = isExit ? 220.0F : 105.0F;
      this.height = 38.0F;
   }

   public MainMenuButton(float posX, float posY, @NotNull String name, Runnable action) {
      this(posX, posY, name, action, false);
   }

   public void onRender(class_332 context, float mouseX, float mouseY) {
      float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
      float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
      Render2DEngine.drawHudBase(context.method_51448(), halfOfWidth + this.posX, halfOfHeight + this.posY, this.width, this.height, 10.0F);
      boolean hovered = Render2DEngine.isHovered(mouseX, mouseY, halfOfWidth + this.posX, halfOfHeight + this.posY, this.width, this.height);
      FontRenderers.monsterrat
         .drawCenteredString(
            context.method_51448(),
            this.name,
            halfOfWidth + this.posX + this.width / 2.0F,
            halfOfHeight + this.posY + this.height / 2.0F - 3.5F,
            hovered ? -1 : Render2DEngine.applyOpacity(-1, 0.7F)
         );
   }

   public void onClick(int mouseX, int mouseY) {
      float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
      float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
      boolean hovered = Render2DEngine.isHovered(mouseX, mouseY, halfOfWidth + this.posX, halfOfHeight + this.posY, this.width, this.height);
      if (hovered) {
         this.action.run();
      }
   }
}
