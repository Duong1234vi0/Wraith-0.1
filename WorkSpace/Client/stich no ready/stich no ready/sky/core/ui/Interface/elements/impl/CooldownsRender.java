package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import sky.core.events.EventRender2D;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class CooldownsRender implements ElementRender {
   private final Dragging dragging;
   private final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final Map itemAnimations = new HashMap();
   private final Map lastCooldownTexts = new HashMap();
   private final Map lastCooldowns = new HashMap();
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   private final AnimationUtil widthAnimation = new AnimationUtil(0.0F, 15.0F);
   private final AnimationUtil heightAnimation = new AnimationUtil(0.0F, 15.0F);

   public void render(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List activeItems = this.getActiveItems();
      this.alphaAnimation.update(!(mc.currentScreen instanceof ChatScreen) && activeItems.isEmpty() ? 0.0F : 1.0F);
      float globalAlpha = this.alphaAnimation.getValue();
      Map itemCooldowns = new HashMap();
      int i = 0;

      while(true) {
         Minecraft var10001 = mc;
         if (i >= Minecraft.player.inventory.getSizeInventory()) {
            i = ColorUtil.applyOpacity(-1, globalAlpha);
            int darkBgColor = ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.LOGO), 0.1F);
            List dimsItems = new ArrayList(activeItems);
            dimsItems.addAll(this.itemAnimations.keySet());
            float[] dimensions = this.calculateDimensions(dimsItems, itemCooldowns);
            float width = dimensions[0];
            RenderUtil.drawBlurredRoundedRectangle(posX, posY, this.widthAnimation.getValue(), this.heightAnimation.getValue(), 3.0F, (Boolean)alphabg.get() ? ColorUtil.applyOpacity(darkBgColor, 0) : darkBgColor, globalAlpha);
            Fonts.icons[16].drawString(ms, "T", (double)(posX - 11.5F + this.widthAnimation.getValue()), (double)(posY + 6.5F), i);
            Fonts.sf_medium[15].drawString(ms, "Cooldowns", (double)(posX + 3.5F), (double)(posY + 5.5F), i);
            float baseItemY = posY + 12.5F;
            List toRemove = new ArrayList();

            AnimationUtil[] anims;
            float itemAlpha;
            for(int i = 0; i < activeItems.size(); ++i) {
               Item item = (Item)activeItems.get(i);
               anims = (AnimationUtil[])this.itemAnimations.computeIfAbsent(item, (k) -> {
                  return new AnimationUtil[]{new AnimationUtil(0.0F, 10.0F), new AnimationUtil(-5.0F, 10.0F), new AnimationUtil((float)(i * 10), 15.0F)};
               });
               anims[0].update(1.0F);
               anims[1].update(0.0F);
               itemAlpha = (float)(i * 10);
               anims[2].update(itemAlpha);
            }

            Iterator var33 = this.itemAnimations.entrySet().iterator();

            Map.Entry entry;
            while(var33.hasNext()) {
               entry = (Map.Entry)var33.next();
               if (!activeItems.contains(entry.getKey())) {
                  AnimationUtil[] anims = (AnimationUtil[])entry.getValue();
                  anims[0].update(0.0F);
                  anims[1].update(-5.0F);
                  if (anims[0].isDone() && anims[1].isDone()) {
                     toRemove.add((Item)entry.getKey());
                  }
               }
            }

            toRemove.forEach((itemx) -> {
               this.itemAnimations.remove(itemx);
               this.lastCooldownTexts.remove(itemx);
               this.lastCooldowns.remove(itemx);
            });
            ScissorUtil.start((double)posX, (double)posY, (double)this.widthAnimation.getValue(), (double)this.heightAnimation.getValue());
            var33 = this.itemAnimations.entrySet().iterator();

            while(true) {
               Item item;
               do {
                  if (!var33.hasNext()) {
                     ScissorUtil.end();
                     this.widthAnimation.update(width);
                     this.heightAnimation.update((float)(15 + activeItems.size() * 10));
                     this.dragging.setHeight(this.heightAnimation.getValue());
                     this.dragging.setWidth(this.widthAnimation.getValue());
                     return;
                  }

                  entry = (Map.Entry)var33.next();
                  item = (Item)entry.getKey();
                  anims = (AnimationUtil[])entry.getValue();
                  itemAlpha = globalAlpha * anims[0].getValue();
               } while(itemAlpha <= 0.01F && anims[0].isDone());

               float itemY = baseItemY + anims[2].getValue();
               float cooldown = (Float)itemCooldowns.getOrDefault(item, 0.0F);
               String nameText = (new ItemStack(item)).getDisplayName().getString();
               String cooldownText = itemCooldowns.containsKey(item) ? this.formatCooldown(cooldown) : (String)this.lastCooldownTexts.getOrDefault(item, "");
               float cooldownWidth = Fonts.sf_medium[13].getWidth(cooldownText);
               String path = Registry.ITEM.getKey(item).getPath();
               String texturePath = "enchanted_golden_apple".equals(path) ? "golden_apple" : ("crossbow".equals(path) ? "crossbow_arrow" : path);
               ResourceLocation texture = new ResourceLocation(Registry.ITEM.getKey(item).getNamespace(), "textures/item/" + texturePath + ".png");
               int animatedTextColor = ColorUtil.applyOpacity(-1, itemAlpha);
               int animatedRectColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * itemAlpha);
               Fonts.sf_medium[13].drawString(ms, nameText, (double)(posX + 3.5F + 5.0F + 2.5F + 2.0F), (double)(itemY + 4.5F), animatedTextColor);
               Fonts.sf_medium[13].drawString(ms, cooldownText, (double)(posX + 3.5F + this.widthAnimation.getValue() - 7.5F - cooldownWidth), (double)(itemY + 4.5F), animatedTextColor);
               RenderUtil.drawMinecraftRectangle(ms, posX + 10.5F, itemY + 3.0F, 0.5F, 5.5F, animatedRectColor);
               RenderUtil.drawImage2D(texture, posX + 2.5F, itemY + 2.0F, 7.0F, 7.0F, ColorUtil.applyOpacity(-1, itemAlpha));
            }
         }

         Minecraft var10000 = mc;
         ItemStack itemStack = Minecraft.player.inventory.getStackInSlot(i);
         if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            var10000 = mc;
            float cooldownSeconds = Minecraft.player.getCooldownTracker().getCustomCooldown(item, mc.getRenderPartialTicks());
            if (cooldownSeconds > 0.0F) {
               itemCooldowns.put(item, cooldownSeconds);
               this.lastCooldownTexts.put(item, this.formatCooldown(cooldownSeconds));
               this.lastCooldowns.put(item, cooldownSeconds);
            }
         }

         ++i;
      }
   }

   private List getActiveItems() {
      Set activeSet = new HashSet();
      int i = 0;

      while(true) {
         Minecraft var10001 = mc;
         if (i >= Minecraft.player.inventory.getSizeInventory()) {
            return new ArrayList(activeSet);
         }

         Minecraft var10000 = mc;
         ItemStack itemStack = Minecraft.player.inventory.getStackInSlot(i);
         if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            var10000 = mc;
            float cooldown = Minecraft.player.getCooldownTracker().getCustomCooldown(item, mc.getRenderPartialTicks());
            if (cooldown > 0.0F) {
               activeSet.add(item);
            }
         }

         ++i;
      }
   }

   private float[] calculateDimensions(List dimsItems, Map itemCooldowns) {
      float maxNameWidth = 0.0F;
      float maxCooldownWidth = 0.0F;

      String cooldownStr;
      for(Iterator var5 = dimsItems.iterator(); var5.hasNext(); maxCooldownWidth = Math.max(maxCooldownWidth, Fonts.sf_medium[13].getWidth(cooldownStr))) {
         Item item = (Item)var5.next();
         maxNameWidth = Math.max(maxNameWidth, Fonts.sf_medium[13].getWidth((new ItemStack(item)).getDisplayName().getString()));
         cooldownStr = itemCooldowns.containsKey(item) ? this.formatCooldown((Float)itemCooldowns.get(item)) : (String)this.lastCooldownTexts.getOrDefault(item, "");
      }

      float width = Math.max(62.0F, 3.5F + maxNameWidth + 3.5F + maxCooldownWidth + 3.5F + 1.0F + 6.0F + 3.5F);
      return new float[]{width};
   }

   private String formatCooldown(float cooldown) {
      int minutes = (int)(cooldown / 60.0F);
      int seconds = (int)(cooldown % 60.0F);
      StringBuilder sb = new StringBuilder();
      if (minutes > 0) {
         sb.append(minutes).append("м");
      }

      if (seconds > 0 || minutes == 0) {
         sb.append(seconds).append("с");
      }

      return sb.toString();
   }

   public CooldownsRender(Dragging dragging) {
      this.dragging = dragging;
   }
}
