package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import sky.core.events.EventRender2D;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.utils.player.InventoryUtil;

public class TotemCounterRender implements ElementRender {
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

      int totemCount = InventoryUtil.getItemCount(Items.TOTEM_OF_UNDYING);
      if (totemCount > 0) {
         int screenHeight;
         int centerX;
         MatrixStack matrix;
         boolean var19;
         label34: {
            mc.gameRenderer.setupOverlayRendering();
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/widgets.png"));
            matrix = event.getStack();
            int screenWidth = mc.getMainWindow().getScaledWidth();
            screenHeight = mc.getMainWindow().getScaledHeight();
            centerX = screenWidth / 2;
            var10000 = mc;
            if (Minecraft.player.getPrimaryHand().opposite() == HandSide.RIGHT) {
               var10000 = mc;
               if (!Minecraft.player.getHeldItemOffhand().isEmpty()) {
                  var19 = true;
                  break label34;
               }
            }

            var19 = false;
         }

         boolean isRightHand = var19;
         int handOffset = isRightHand ? 29 : 0;
         RenderSystem.clearCurrentColor();
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableAlphaTest();
         RenderSystem.disableDepthTest();
         RenderSystem.shadeModel(7425);
         RenderSystem.defaultAlphaFunc();
         RenderSystem.defaultBlendFunc();
         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         int yPos = screenHeight - 23;
         int xPos2 = centerX + 189 + 1 + handOffset;
         int xPos3 = centerX + 97 + 1 + handOffset;
         boolean isArmorElementEnabled = Interface.elements.is("Броня");
         int drawX = isArmorElementEnabled && hasArmor ? xPos2 : xPos3;
         AbstractGui.blit(matrix, drawX, yPos, 0, 24.0F, 22.0F, 29, 24, 256, 256);
         ItemStack totemStack = new ItemStack(Items.TOTEM_OF_UNDYING, totemCount);
         ItemRenderer itemRenderer = mc.getItemRenderer();
         itemRenderer.renderItemIntoGUI(totemStack, drawX + 3, yPos + 4);
         itemRenderer.renderItemOverlays(mc.fontRenderer, totemStack, drawX + 3, yPos + 4);
         RenderSystem.enableDepthTest();
         RenderSystem.enableAlphaTest();
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         RenderSystem.shadeModel(7424);
         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         mc.gameRenderer.setupOverlayRendering();
      }
   }
}
