package unique.core.features.hud.impl;

import java.awt.Color;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import unique.core.features.hud.HudElement;
import unique.core.utility.render.Render2DEngine;

public class InventoryHud extends HudElement {
   public InventoryHud() {
      super("InventoryHUD", 100, 100);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      if (mc.field_1724 != null) {
         int rows = 3;
         int cols = 9;
         int slotSize = 18;
         int padding = 2;
         float xPos = this.getPosX();
         float yPos = this.getPosY();
         int width = cols * (slotSize + padding) - padding;
         int height = rows * (slotSize + padding) - padding;

         for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
               int slot = col + row * 9 + 9;
               if (slot < mc.field_1724.method_31548().field_7547.size()) {
                  int x = (int)(xPos + col * (slotSize + padding));
                  int y = (int)(yPos + row * (slotSize + padding));
                  this.drawSlotBackground(context, x, y, slotSize, slotSize);
                  class_1799 stack = mc.field_1724.method_31548().method_5438(slot);
                  if (!stack.method_7960()) {
                     context.method_51427(stack, x + 1, y + 1);
                     context.method_51431(mc.field_1772, stack, x + 1, y + 1);
                  }
               }
            }
         }

         this.setBounds(xPos, yPos, width, height);
      }
   }

   private void drawSlotBackground(class_332 context, float x, float y, float w, float h) {
      Render2DEngine.drawRect(context.method_51448(), x, y, w, h, new Color(0, 0, 0, 170));
   }
}
