package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import sky.core.events.EventRender2D;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ArmorRender implements ElementRender {
   private static final int ARMOR_SLOT_COUNT = 4;
   private static final float BAR_GAP_FROM_HOTBAR = 10.0F;

   public void render(EventRender2D.Post event) {
      boolean hasArmor = false;
      Minecraft var10000 = mc;
      Iterator var3 = Minecraft.player.getArmorInventoryList().iterator();

      while(var3.hasNext()) {
         ItemStack itemStack = (ItemStack)var3.next();
         if (!itemStack.isEmpty()) {
            hasArmor = true;
            break;
         }
      }

      if (hasArmor) {
         int screenHeight;
         int centerX;
         MatrixStack matrixStack;
         boolean var29;
         label57: {
            matrixStack = event.getStack();
            int screenWidth = mc.getMainWindow().getScaledWidth();
            screenHeight = mc.getMainWindow().getScaledHeight();
            centerX = screenWidth / 2;
            var10000 = mc;
            if (Minecraft.player.getPrimaryHand().opposite() == HandSide.RIGHT) {
               var10000 = mc;
               if (!Minecraft.player.getHeldItemOffhand().isEmpty()) {
                  var29 = true;
                  break label57;
               }
            }

            var29 = false;
         }

         boolean isRightHand = var29;
         int handOffset = isRightHand ? 29 : 0;
         int barY = HotBarRender.shouldReplace() ? HotBarRender.getBarY(screenHeight) : screenHeight - 22;
         int itemY = HotBarRender.shouldReplace() ? HotBarRender.getItemY(screenHeight) - 2 : screenHeight - 16 - 3;
         float barX = (float)(centerX + 91) + 10.0F + (float)handOffset;
         float barWidth = 80.0F;
         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         int separatorColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * 0.2F);
         int textColor = ColorUtil.rgba(255, 255, 255, 180);
         RenderUtil.drawRoundedRectangle(barX, (float)barY, barWidth, 22.0F, 6.0F, bgColor);

         for(int i = 1; i < 4; ++i) {
            float sepX = barX + (float)(i * 20);
            RenderUtil.drawMinecraftRectangle(matrixStack, sepX, (float)barY + 0.5F, 0.75F, 21.0F, separatorColor);
         }

         RenderSystem.enableRescaleNormal();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         IngameGui ingameGui = mc.ingameGUI;
         int slot = 0;
         var10000 = mc;

         for(Iterator var19 = Minecraft.player.getArmorInventoryList().iterator(); var19.hasNext(); ++slot) {
            ItemStack stack = (ItemStack)var19.next();
            float cellX = barX + (float)(slot * 20);
            int itemX = Math.round(cellX + 2.0F);
            float var10004 = event.getPartialTicks();
            Minecraft var10005 = mc;
            HotBarRender.renderHotbarItem(ingameGui, matrixStack, itemX, itemY, var10004, Minecraft.player, stack);
            if (!stack.isEmpty() && stack.getCount() > 1) {
               String count = String.valueOf(stack.getCount());
               float countWidth = Fonts.sf_regular[10].getWidth(count);
               float countX = cellX + (20.0F - countWidth) / 2.0F;
               float countY = (float)(barY + 22) - Fonts.sf_regular[10].getHeight() - 1.0F;
               Fonts.sf_regular[10].drawString(matrixStack, count, (double)(countX + 7.0F), (double)countY, textColor);
            }
         }

         RenderSystem.disableRescaleNormal();
         RenderSystem.disableBlend();
      }
   }
}
