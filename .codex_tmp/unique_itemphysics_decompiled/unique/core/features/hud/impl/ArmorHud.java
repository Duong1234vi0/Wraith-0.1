package unique.core.features.hud.impl;

import java.awt.Color;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import unique.core.features.hud.HudElement;
import unique.core.utility.render.Render2DEngine;

public class ArmorHud extends HudElement {
   public ArmorHud() {
      super("ArmorHud", 0, 0);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      if (mc.field_1724 != null) {
         int sw = mc.method_22683().method_4486();
         int sh = mc.method_22683().method_4502();
         float hotbarX = (sw - 182) / 2.0F;
         float hotbarY = sh - 22 - 3;
         float x = hotbarX + 182.0F + 4.0F;
         float y = hotbarY + 6.0F;
         class_1304[] slots = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
         int size = 16;
         int spacing = 2;
         float drawX = x;

         for (class_1304 slot : slots) {
            class_1799 stack = mc.field_1724.method_6118(slot);
            Render2DEngine.drawRect(context.method_51448(), drawX - 1.0F, y - 1.0F, size + 2, size + 2, new Color(0, 0, 0, 140));
            if (!stack.method_7960()) {
               context.method_51427(stack, (int)drawX, (int)y);
               context.method_51431(mc.field_1772, stack, (int)drawX, (int)y);
            }

            drawX += size + spacing;
         }

         this.setBounds(x, y, 4 * (size + spacing), size);
      }
   }
}
