package dev.client.modules.impl.util;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.slot.Slot;

@Environment(EnvType.CLIENT)
public class AHHelper extends Module {
   public AHHelper() {
      super(new ModuleInfo("AHHelper", Category.UTIL, "NoDesc"));
   }

   public void renderCheat(DrawContext context, Slot slot) {
      context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, Color.yellow.getRGB());
   }

   public void renderGood(DrawContext context, Slot slot) {
      context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, Color.green.getRGB());
   }
}
