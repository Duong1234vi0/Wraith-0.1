package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.joml.Vector2f;
import sky.core.events.EventRender2D;
import sky.core.events.EventRenderTooltip;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.RenderUtil;

public class ShulkerPreview extends Module {
   private final BooleanSetting world = new BooleanSetting("Отображать в мире", true);

   public ShulkerPreview() {
      super("Shulker Preview", "Позволяет посмотреть что находится в шалкере (Зажать CTRL при наведении на шалкер)", Category.Visuals);
      this.addSettings(new Setting[]{this.world});
   }

   @EventTarget
   public void onRenderTooltip(EventRenderTooltip e) {
      if (shouldShowPreview(e.stack)) {
         e.setCancelled(true);
         showPreview(e.matrixStack, e.stack, e.mouseX, e.mouseY);
      }

   }

   @EventTarget
   public void onRender2DWorld(EventRender2D e) {
      if ((Boolean)this.world.get()) {
         List shulkerDrops = new ArrayList();
         Iterator var3 = mc.world.getAllEntities().iterator();

         while(var3.hasNext()) {
            Entity ent = (Entity)var3.next();
            if (ent instanceof ItemEntity) {
               ItemEntity itemEnt = (ItemEntity)ent;
               ItemStack drop = itemEnt.getItem();
               if (drop.getItem() instanceof BlockItem && ((BlockItem)drop.getItem()).getBlock() instanceof ShulkerBoxBlock) {
                  shulkerDrops.add(itemEnt);
               }
            }
         }

         shulkerDrops.sort(Comparator.comparingDouble((ie) -> {
            Minecraft var10001 = mc;
            return ie.getDistanceSq(Minecraft.player.getPositionVec());
         }).reversed());
         var3 = shulkerDrops.iterator();

         while(true) {
            Vector2f screen;
            ItemStack drop;
            CompoundNBT rootTag;
            do {
               do {
                  ItemEntity itemEnt;
                  do {
                     do {
                        if (!var3.hasNext()) {
                           return;
                        }

                        itemEnt = (ItemEntity)var3.next();
                        drop = itemEnt.getItem();
                        rootTag = drop.getTag();
                     } while(rootTag == null);
                  } while(!rootTag.contains("BlockEntityTag"));

                  screen = ProjectUtil.project2D(MathUtil.interpolate(itemEnt, e.getPartialTicks()));
               } while(screen.x == Float.MAX_VALUE);
            } while(screen.y == Float.MAX_VALUE);

            int baseOffsetX = 14;
            float panelX = screen.x + (float)baseOffsetX;
            int baseOffset = 20;
            float panelY = screen.y - (float)baseOffset;
            DyeColor dropColor = ShulkerBoxBlock.getColorFromItem(drop.getItem());
            int tint = -16777216 | (dropColor != null ? dropColor.getColorValue() : DyeColor.PURPLE.getColorValue());
            RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/shulker_view/shulker_box_tooltip.png"), panelX, panelY, 129.0F, 129.0F, tint);
            CompoundNBT blockTag = rootTag.getCompound("BlockEntityTag");
            NonNullList items = NonNullList.withSize(27, ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(blockTag, items);

            for(int i = 0; i < items.size(); ++i) {
               ItemStack content = (ItemStack)items.get(i);
               if (!content.isEmpty()) {
                  int col = i % 9;
                  int row = i / 9;
                  float slotX = panelX + 4.0F + (float)(col * 9);
                  float slotY = panelY + 4.0F + (float)(row * 9);
                  RenderUtil.drawStack(content, slotX, slotY, 0.5F);
               }
            }
         }
      }
   }

   public static boolean shouldShowPreview(ItemStack stack) {
      return Screen.hasControlDown() && stack.getItem() instanceof BlockItem && ((BlockItem)stack.getItem()).getBlock() instanceof ShulkerBoxBlock;
   }

   public static void showPreview(MatrixStack matrixStack, ItemStack stack, int mouseX, int mouseY) {
      List texts = new ArrayList();
      texts.add(stack.getDisplayName().deepCopy().mergeStyle(TextFormatting.WHITE));
      texts.add((new StringTextComponent("Содержит 27 предметов")).mergeStyle(TextFormatting.GRAY));
      List procs = (List)texts.stream().map(ITextComponent::func_241878_f).collect(Collectors.toList());
      mc.currentScreen.renderTooltip(matrixStack, procs, mouseX, mouseY);
      Stream var10000 = procs.stream();
      FontRenderer var10001 = mc.fontRenderer;
      Objects.requireNonNull(var10001);
      int maxWidth = var10000.mapToInt(var10001::func_243245_a).max().orElse(0);
      int x = mouseX + 12;
      int y = mouseY - 12;
      int k = 8;
      if (procs.size() > 1) {
         k += 2 + (procs.size() - 1) * 10;
      }

      if (x + maxWidth > mc.currentScreen.width) {
         x -= 28 + maxWidth;
      }

      if (y + k + 6 > mc.currentScreen.height) {
         y = mc.currentScreen.height - k - 6;
      }

      DyeColor dye = ShulkerBoxBlock.getColorFromItem(stack.getItem());
      int tint = -16777216 | (dye != null ? dye.getColorValue() : DyeColor.PURPLE.getColorValue());
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/shulker_view/shulker_box_tooltip.png"), (float)(x - 4), (float)(y + k + 5), 256.0F, 256.0F, tint);
      CompoundNBT rootTag = stack.getTag();
      if (rootTag != null && rootTag.contains("BlockEntityTag")) {
         CompoundNBT blockTag = rootTag.getCompound("BlockEntityTag");
         NonNullList items = NonNullList.withSize(27, ItemStack.EMPTY);
         ItemStackHelper.loadAllItems(blockTag, items);
         int startX = x + 4;
         int startY = y + k + 13;

         for(int i = 0; i < items.size(); ++i) {
            ItemStack content = (ItemStack)items.get(i);
            if (!content.isEmpty()) {
               int col = i % 9;
               int row = i / 9;
               int slotX = startX + col * 18;
               int slotY = startY + row * 18;
               RenderSystem.pushMatrix();
               RenderSystem.translatef(0.0F, 0.0F, 32.0F);
               mc.currentScreen.setBlitOffset(200);
               mc.getItemRenderer().zLevel = 200.0F;
               RenderUtil.drawStack(content, (float)slotX, (float)slotY, 1.0F);
               mc.currentScreen.setBlitOffset(0);
               mc.getItemRenderer().zLevel = 0.0F;
               RenderSystem.popMatrix();
            }
         }
      }

   }
}
