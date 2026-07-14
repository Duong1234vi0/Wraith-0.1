package sky.core.ui.Interface.elements.impl;

import com.darkmagician6.eventapi.EventManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.settings.AttackIndicatorStatus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector4f;
import net.optifine.CustomItems;
import other.bot.Bot;
import other.bot.BotManager;
import sky.core.SkyCore;
import sky.core.events.EventHotbarRender;
import sky.core.modules.Module;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public final class HotBarRender implements Wrapper {
   public static final int HOTBAR_WIDTH = 182;
   public static final int HOTBAR_HEIGHT = 22;
   public static final int SLOT_WIDTH = 20;
   public static final int SLOT_ITEM_PADDING = 2;
   public static final int GAP_ABOVE_VANILLA = 2;
   private static final float ITEM_SCALE = 0.72F;
   private static final float BORDER_RADIUS = 6.0F;
   private static final float SELECTION_RADIUS = 6.0F;
   private static final float SEPARATOR_WIDTH = 0.75F;
   private static final float SEPARATOR_HEIGHT = 21.0F;
   private static final int SLOT_LABEL_FONT_SIZE = 13;
   private static final AnimationUtil selectionSlotAnim;

   private HotBarRender() {
   }

   public static boolean shouldReplace() {
      try {
         Module module = SkyCore.getInstance().getModuleManager().getModule(Interface.class);
         return module != null && module.isEnabled() && Interface.elements.is("Хотбар") != null && Interface.elements.is("Хотбар");
      } catch (Exception var1) {
         return false;
      }
   }

   public static int getBarY(int screenHeight) {
      return screenHeight - 22 - 2;
   }

   public static int getItemY(int screenHeight) {
      int barY = getBarY(screenHeight);
      float itemSize = 11.52F;
      return barY + Math.round((22.0F - itemSize) / 2.0F);
   }

   public static float getSlotCellX(int screenWidth, float slot) {
      return (float)screenWidth / 2.0F - 91.0F + slot * 20.0F;
   }

   public static int getSlotX(int screenWidth, int slot) {
      return Math.round(getSlotCellX(screenWidth, (float)slot) + 2.0F);
   }

   private static float getSelectionWidth(float slot) {
      return slot >= 7.5F ? 22.0F : 21.0F;
   }

   private static int getSelectionCornerSlot(float animatedSlot, int targetSlot) {
      return selectionSlotAnim.isAlive() ? MathHelper.clamp(Math.round(animatedSlot), 0, 8) : targetSlot;
   }

   private static Vector4f getSelectionCornerRadius(int slot) {
      if (slot == 0) {
         return new Vector4f(6.0F, 6.0F, 0.0F, 0.0F);
      } else {
         return slot == 8 ? new Vector4f(0.0F, 0.0F, 6.0F, 6.0F) : new Vector4f(0.0F, 0.0F, 0.0F, 0.0F);
      }
   }

   public static void render(MatrixStack matrixStack, float partialTicks) {
      Minecraft var10000 = mc;
      ClientPlayerEntity player = Minecraft.player;
      Bot bot = null;
      Iterator var4 = BotManager.allBots.iterator();

      while(var4.hasNext()) {
         Bot b = (Bot)var4.next();
         if (mc.renderViewEntity == b.connection.bot) {
            bot = b;
            break;
         }
      }

      if (bot != null) {
         if (bot.connection.bot == null) {
            return;
         }
      } else if (player == null) {
         return;
      }

      PlayerEntity renderPlayer = bot != null ? bot.connection.bot : player;
      int screenWidth = mc.getMainWindow().getScaledWidth();
      int screenHeight = mc.getMainWindow().getScaledHeight();
      int centerX = screenWidth / 2;
      int barX = centerX - 91;
      int barY = getBarY(screenHeight);
      int itemY = getItemY(screenHeight) - 2;
      int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(logoColor, 0.1F);
      int separatorColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * 0.2F);
      int textColor = ColorUtil.rgba(255, 255, 255, 180);
      int selectedSlot = bot != null ? bot.connection.bot.inventory.currentItem : player.inventory.currentItem;
      RenderUtil.beginInterfaceRounding();
      RenderUtil.beginHudLiquidGlass();

      ItemStack slotStack;
      float labelWidth;
      float labelHeight;
      float labelX;
      float labelY;
      int slot;
      float attackStrength;
      int indicatorY;
      try {
         RenderUtil.drawRoundedRectangle((float)barX, (float)barY, 182.0F, 22.0F, 6.0F, bgColor);
         int slotLabelColor = 1;

         while(true) {
            float animatedSlot;
            if (slotLabelColor >= 9) {
               slotLabelColor = ColorUtil.rgba(255, 255, 255, 30);

               for(slot = 0; slot < 9; ++slot) {
                  slotStack = bot != null ? (ItemStack)bot.connection.bot.inventory.mainInventory.get(slot) : (ItemStack)player.inventory.mainInventory.get(slot);
                  if (slotStack.isEmpty()) {
                     String label = String.valueOf(slot + 1);
                     float cellX = getSlotCellX(screenWidth, (float)slot);
                     labelWidth = Fonts.sf_regular[18].getWidth(label);
                     labelHeight = Fonts.sf_regular[18].getHeight();
                     labelX = cellX + (20.0F - labelWidth) / 2.0F;
                     labelY = (float)barY + (22.0F - labelHeight) / 2.0F;
                     Fonts.sf_regular[18].drawString(matrixStack, label, (double)(labelX + 0.5F), (double)labelY, slotLabelColor);
                  }
               }

               selectionSlotAnim.update((float)selectedSlot);
               animatedSlot = selectionSlotAnim.getValue();
               float selectedCellX = getSlotCellX(screenWidth, animatedSlot);
               attackStrength = getSelectionWidth(animatedSlot);
               indicatorY = getSelectionCornerSlot(animatedSlot, selectedSlot);
               RenderUtil.drawRoundedRectangle(selectedCellX, (float)barY, attackStrength, 22.0F, getSelectionCornerRadius(indicatorY), ColorUtil.applyOpacity(logoColor, 0.18F));
               break;
            }

            animatedSlot = (float)(barX + slotLabelColor * 20);
            RenderUtil.drawMinecraftRectangle(matrixStack, animatedSlot, (float)barY + 0.5F, 0.75F, 21.0F, separatorColor);
            ++slotLabelColor;
         }
      } finally {
         RenderUtil.endHudLiquidGlass();
         RenderUtil.endInterfaceRounding();
      }

      RenderSystem.enableRescaleNormal();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      IngameGui ingameGui = mc.ingameGUI;
      EventManager.call(new EventHotbarRender(matrixStack, partialTicks));
      CustomItems.setRenderOffHand(false);

      for(slot = 0; slot < 9; ++slot) {
         slotStack = bot != null ? (ItemStack)bot.connection.bot.inventory.mainInventory.get(slot) : (ItemStack)player.inventory.mainInventory.get(slot);
         int slotX = getSlotX(screenWidth, slot);
         renderHotbarItem(ingameGui, matrixStack, slotX, itemY, partialTicks, (PlayerEntity)renderPlayer, slotStack);
         if (!slotStack.isEmpty() && slotStack.getCount() > 1) {
            String count = String.valueOf(slotStack.getCount());
            labelWidth = Fonts.sf_regular[10].getWidth(count);
            labelHeight = 11.52F;
            labelX = getSlotCellX(screenWidth, (float)slot);
            labelY = (float)((int)labelX) + (20.0F - labelWidth) / 2.0F;
            float countY = (float)(barY + 22) - Fonts.sf_regular[10].getHeight() - 1.0F;
            Fonts.sf_regular[10].drawString(matrixStack, count, (double)(labelY + 6.0F), (double)countY, textColor);
         }
      }

      ItemStack offhand = bot != null ? bot.connection.bot.getHeldItemOffhand() : player.getHeldItemOffhand();
      HandSide handSide = bot != null ? bot.connection.bot.getPrimaryHand().opposite() : player.getPrimaryHand().opposite();
      if (!offhand.isEmpty()) {
         CustomItems.setRenderOffHand(true);
         if (handSide == HandSide.LEFT) {
            renderHotbarItem(ingameGui, matrixStack, centerX - 91 - 26, itemY, partialTicks, (PlayerEntity)renderPlayer, offhand);
         } else {
            renderHotbarItem(ingameGui, matrixStack, centerX + 91 + 10, itemY, partialTicks, (PlayerEntity)renderPlayer, offhand);
         }

         CustomItems.setRenderOffHand(false);
      }

      if (mc.gameSettings.attackIndicator == AttackIndicatorStatus.HOTBAR) {
         attackStrength = bot != null ? bot.connection.bot.getCooledAttackStrength(0.0F) : player.getCooledAttackStrength(0.0F);
         if (attackStrength < 1.0F) {
            indicatorY = itemY + 4;
            int indicatorX = centerX + 91 + 6;
            if (handSide == HandSide.RIGHT) {
               indicatorX = centerX - 91 - 22;
            }

            mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
            int fillHeight = (int)(attackStrength * 19.0F);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            ingameGui.blit(matrixStack, indicatorX, indicatorY, 0, 94, 18, 18);
            ingameGui.blit(matrixStack, indicatorX, indicatorY + 18 - fillHeight, 18, 112 - fillHeight, 18, fillHeight);
         }
      }

      RenderSystem.disableRescaleNormal();
      RenderSystem.disableBlend();
   }

   public static void renderHotbarItem(IngameGui ingameGui, MatrixStack matrixStack, int x, int y, float partialTicks, PlayerEntity player, ItemStack stack) {
      if (!stack.isEmpty()) {
         float animation = (float)stack.getAnimationsToGo() - partialTicks;
         float centerX = (float)x + 10.0F;
         float centerY = (float)y + 8.0F;
         RenderSystem.pushMatrix();
         RenderSystem.translatef(centerX, centerY, 0.0F);
         if (animation > 0.0F) {
            float popScale = 1.0F + animation / 5.0F;
            RenderSystem.scalef(1.0F / popScale, (popScale + 1.0F) / 2.0F, 1.0F);
         }

         RenderSystem.scalef(0.72F, 0.72F, 1.0F);
         RenderSystem.translatef(-centerX, -centerY, 0.0F);
         ingameGui.itemRenderer.renderItemAndEffectIntoGUI(player, stack, x, y);
         String overlayText = stack.getCount() > 1 ? "" : null;
         ingameGui.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, stack, x, y, overlayText);
         RenderSystem.popMatrix();
      }
   }

   static {
      selectionSlotAnim = new AnimationUtil(0.0F, 14.0F, Easings.CUBIC_OUT);
   }
}
