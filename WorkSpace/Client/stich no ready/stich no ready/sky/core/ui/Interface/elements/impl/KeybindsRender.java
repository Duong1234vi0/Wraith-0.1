package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputMappings;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.misc.KeyMapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class KeybindsRender implements ElementRender {
   private final Dragging dragging;
   private final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final AnimationUtil ordinaryAlphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final AnimationUtil slideAnimation = new AnimationUtil(-20.0F, 10.0F);
   private final AnimationUtil widthAnimation = new AnimationUtil(90.0F, 10.0F);
   private final AnimationUtil heightAnimation = new AnimationUtil(62.5F, 10.0F);
   private final Map entryAnimations = new HashMap();
   private final Map itemAnimations = new HashMap();
   private final Map ordinaryEntryAnimations = new HashMap();
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);

   public void render(EventRender2D.Post event) {
      if (Interface.isOldHud()) {
         this.renderDefault(event);
      } else {
         this.renderNursultan(event);
      }

   }

   private void renderDefault(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List entries = this.collectOrdinaryEntries();
      boolean hasActive = entries.stream().anyMatch(OrdinaryEntry::isActive);
      boolean show = mc.currentScreen instanceof ChatScreen || hasActive;
      this.ordinaryAlphaAnimation.update(show ? 1.0F : 0.0F);
      float globalAlpha = this.ordinaryAlphaAnimation.getValue();
      if (globalAlpha <= 0.01F) {
         this.dragging.setWidth(65.0F);
         this.dragging.setHeight(18.5F);
      } else {
         float headerHeight = 12.3F;
         float itemHeight = 9.6F;
         float maxCombinedWidth = 0.0F;
         List currentIds = new ArrayList();
         Iterator var13 = entries.iterator();

         while(true) {
            OrdinaryEntry entry;
            AnimationUtil anim;
            String var10000;
            do {
               if (!var13.hasNext()) {
                  this.ordinaryEntryAnimations.entrySet().removeIf((e) -> {
                     return !currentIds.contains(e.getKey()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
                  });
                  float width = Math.max(65.0F, maxCombinedWidth);
                  float contentHeight = 0.0F;
                  Iterator var32 = entries.iterator();

                  while(var32.hasNext()) {
                     OrdinaryEntry entry = (OrdinaryEntry)var32.next();
                     AnimationUtil anim = (AnimationUtil)this.ordinaryEntryAnimations.get(entry.id);
                     if (anim != null) {
                        contentHeight += itemHeight * anim.getValue();
                     }
                  }

                  float windowHeight = headerHeight + (contentHeight <= 0.01F ? 6.0F : contentHeight + 6.7F);
                  int textColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_TEXT), ThemeEditor.getAlpha(ThemeSettings.WINDOW_TEXT) / 255.0F * globalAlpha);
                  int iconColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * globalAlpha);
                  float topOffset = 3.8F;
                  RenderUtil.drawBlurredRoundedRectangle(posX, posY + topOffset, width, windowHeight - topOffset, 4.9F, (Boolean)alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), globalAlpha);
                  float renderY;
                  if (width > 35.0F) {
                     renderY = posX + 54.0F + (width - 65.0F);
                     Fonts.lox[13].drawString(ms, "d", (double)(renderY + 0.5F), (double)(posY + 10.0F), iconColor);
                     Fonts.sf_medium[15].drawString(ms, "Keybinds", (double)(posX + 4.0F), (double)(posY + 9.0F), textColor);
                  }

                  renderY = posY + headerHeight + 3.0F;
                  Iterator var20 = entries.iterator();

                  while(var20.hasNext()) {
                     OrdinaryEntry entry = (OrdinaryEntry)var20.next();
                     AnimationUtil itemAnim = (AnimationUtil)this.ordinaryEntryAnimations.get(entry.id);
                     if (itemAnim != null) {
                        float animVal = itemAnim.getValue();
                        if (!(animVal <= 0.01F)) {
                           float slideOffset = (1.0F - animVal) * 10.0F;
                           int itemTextColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_TEXT), ThemeEditor.getAlpha(ThemeSettings.WINDOW_TEXT) / 255.0F * animVal * globalAlpha);
                           int itemBindColor = ColorUtil.rgba(180, 180, 180, 255);
                           Fonts.sf_medium[14].drawString(ms, entry.name, (double)(posX + 4.0F - slideOffset), (double)(renderY + 4.05F), itemTextColor);
                           var10000 = this.toShortBind(entry.bind);
                           String shortBind = "[" + var10000 + "]";
                           float bindWidth = Fonts.sf_medium[14].getWidth(shortBind);
                           float bindX = posX + width - bindWidth - 3.0F + slideOffset;
                           if (width > bindWidth + 12.0F) {
                              Fonts.sf_medium[14].drawString(ms, shortBind, (double)(bindX - 0.5F), (double)(renderY + 4.05F), itemBindColor);
                           }

                           renderY += itemHeight * animVal;
                        }
                     }
                  }

                  this.dragging.setHeight(windowHeight);
                  this.dragging.setWidth(width);
                  return;
               }

               entry = (OrdinaryEntry)var13.next();
               currentIds.add(entry.id);
               anim = (AnimationUtil)this.ordinaryEntryAnimations.computeIfAbsent(entry.id, (key) -> {
                  return new AnimationUtil(0.0F, 10.0F);
               });
               anim.update(entry.active ? 1.0F : 0.0F);
            } while(!(anim.getValue() > 0.01F) && !entry.active);

            var10000 = this.toShortBind(entry.bind);
            String shortBind = "[" + var10000 + "]";
            float combined = Fonts.sf_medium[14].getWidth(entry.name) + Fonts.sf_medium[14].getWidth(shortBind) + 22.0F;
            maxCombinedWidth = Math.max(maxCombinedWidth, combined);
         }
      }
   }

   private void renderNursultan(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List entries = this.collectModuleKeybinds();
      boolean hasActive = entries.stream().anyMatch(ModuleKeybindEntry::isActive);
      boolean show = mc.currentScreen instanceof ChatScreen || hasActive;
      this.alphaAnimation.update(show ? 1.0F : 0.0F);
      float globalAlpha = this.alphaAnimation.getValue();
      if (globalAlpha <= 0.01F) {
         this.dragging.setWidth(90.0F);
         this.dragging.setHeight(25.0F);
      } else {
         float totalWidth = 90.0F;
         float paddingX = 6.0F;
         float paddingY = 7.5F;
         float gap = 7.5F;
         float itemGap = 6.0F;
         float borderRadius = 5.5F;
         float iconSize = 6.0F;
         float headerGap = 3.0F;
         float itemHeight = 7.0F;
         float maxItemWidth = 0.0F;
         List currentIds = new ArrayList();
         float headerHeight = 12.0F + gap;
         float contentHeight = 0.0F;
         Iterator var22 = entries.iterator();

         while(true) {
            ModuleKeybindEntry entry;
            float animVal;
            do {
               if (!var22.hasNext()) {
                  this.entryAnimations.entrySet().removeIf((e) -> {
                     return !currentIds.contains(e.getKey()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
                  });
                  float targetWidth = Math.max(totalWidth, maxItemWidth + paddingX * 2.0F);
                  float animatedHeight = paddingY + headerHeight + contentHeight + paddingY - 10.0F;
                  this.widthAnimation.update(targetWidth);
                  float animatedWidth = this.widthAnimation.getValue();
                  int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
                  int bgColor = ColorUtil.darken(logoColor, 0.1F);
                  int iconColor = logoColor;
                  int textColor = -1;
                  int bindColor = logoColor;
                  RenderUtil.drawRoundedRectangle(posX, posY, animatedWidth, animatedHeight, borderRadius, ColorUtil.applyOpacity(bgColor, (int)(255.0F * globalAlpha)));
                  float currentY = posY + paddingY;
                  float currentX = posX + paddingX;
                  Fonts.skycore[14].drawString(ms, "c", (double)currentX, (double)(currentY + 2.9F), ColorUtil.applyOpacity(logoColor, (int)(255.0F * globalAlpha)));
                  currentX += iconSize + headerGap;
                  Fonts.sfregular[13].drawString(ms, "Keybinds", (double)(currentX + 1.0F), (double)(currentY + 2.5F), ColorUtil.applyOpacity(textColor, (int)(255.0F * globalAlpha)));
                  currentY += headerHeight;
                  Iterator var32 = entries.iterator();

                  while(var32.hasNext()) {
                     ModuleKeybindEntry entry = (ModuleKeybindEntry)var32.next();
                     AnimationUtil itemAnim = (AnimationUtil)this.entryAnimations.get(entry.name + entry.bind);
                     if (itemAnim != null) {
                        float animVal = itemAnim.getValue();
                        if (!(animVal <= 0.01F)) {
                           currentX = posX + paddingX;
                           float slideOffset = (1.0F - animVal) * 10.0F;
                           Fonts.skycore[13].drawString(ms, entry.categoryIcon, (double)currentX, (double)(currentY - 1.0F - slideOffset), ColorUtil.applyOpacity(iconColor, (int)(255.0F * animVal * globalAlpha)));
                           currentX += iconSize + itemGap;
                           Fonts.sfregular[13].drawString(ms, entry.name, (double)currentX, (double)(currentY - 2.0F - slideOffset), ColorUtil.applyOpacity(textColor, (int)(255.0F * animVal * globalAlpha)));
                           float nameWidth = Fonts.sfregular[13].getWidth(entry.name);
                           float var10000 = currentX + nameWidth;
                           String shortBind = this.toShortBind(entry.bind);
                           float bindWidth = Fonts.sfregular[13].getWidth(shortBind);
                           float bindX = posX + animatedWidth - paddingX - bindWidth;
                           Fonts.sfregular[13].drawString(ms, shortBind, (double)(bindX + slideOffset), (double)(currentY - 2.0F - slideOffset), ColorUtil.applyOpacity(bindColor, (int)(255.0F * animVal * globalAlpha)));
                           currentY += (itemHeight + itemGap) * animVal;
                        }
                     }
                  }

                  this.dragging.setWidth(animatedWidth);
                  this.dragging.setHeight(animatedHeight);
                  return;
               }

               entry = (ModuleKeybindEntry)var22.next();
               String var10001 = entry.name;
               currentIds.add(var10001 + entry.bind);
               var10001 = entry.name;
               AnimationUtil anim = (AnimationUtil)this.entryAnimations.computeIfAbsent(var10001 + entry.bind, (key) -> {
                  return new AnimationUtil(0.0F, 10.0F);
               });
               anim.update(entry.isActive() ? 1.0F : 0.0F);
               animVal = anim.getValue();
            } while(!(animVal > 0.01F) && !entry.isActive());

            float nameWidth = Fonts.sf_medium[12].getWidth(entry.name);
            float bindWidth = Fonts.sf_medium[12].getWidth(this.toShortBind(entry.bind));
            float itemWidth = iconSize + headerGap + nameWidth + itemGap + bindWidth;
            maxItemWidth = Math.max(maxItemWidth, itemWidth);
            contentHeight += (itemHeight + itemGap) * animVal;
         }
      }
   }

   private String toShortBind(int bind) {
      String bindStr = InputMappings.getInputByCode(bind, -1).getTranslationKey().replace("key.keyboard.", "").replace("key.mouse.", "M").replace("MOUSE", "M").replace("CONTROL", "C").replace("LEFT", "L").replace("RIGHT", "R").replace("_", "");
      if (bindStr.equals("semicolon")) {
         bindStr = ";";
      }

      if (bindStr.equals("apostrophe")) {
         bindStr = "'";
      }

      if (bindStr.equals("3")) {
         bindStr = "M3";
      }

      if (bindStr.equals("4")) {
         bindStr = "M4";
      }

      if (bindStr.length() > 4) {
         bindStr = bindStr.substring(0, 4);
      }

      return bindStr.toUpperCase();
   }

   private List collectOrdinaryEntries() {
      List entries = new ArrayList();
      Iterator var2 = SkyCore.getInstance().getModuleManager().getModules().iterator();

      label42:
      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.getBind() != -100) {
            entries.add(new OrdinaryEntry("module::" + module.getName(), module.getName(), module.getBind(), module.isEnabled()));
         }

         Iterator var4 = module.getSettings().iterator();

         while(true) {
            BooleanSetting booleanSetting;
            do {
               Setting setting;
               do {
                  if (!var4.hasNext()) {
                     continue label42;
                  }

                  setting = (Setting)var4.next();
               } while(!(setting instanceof BooleanSetting));

               booleanSetting = (BooleanSetting)setting;
            } while(booleanSetting.getBind() == -100);

            String var10003 = module.getName();
            entries.add(new OrdinaryEntry("setting::" + var10003 + "::" + booleanSetting.getName(), booleanSetting.getName(), booleanSetting.getBind(), module.isEnabled() && (Boolean)booleanSetting.get()));
         }
      }

      return entries;
   }

   private List getActiveItems() {
      List activeItems = new ArrayList();
      Iterator var2 = SkyCore.getInstance().getModuleManager().getModules().iterator();

      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.getBind() != -100 && module.isEnabled() && module.isKeybindvisible()) {
            activeItems.add(new KeybindItem(module.getName(), module.getBind()));
         }

         Iterator var4 = module.getSettings().iterator();

         while(var4.hasNext()) {
            Setting setting = (Setting)var4.next();
            if (setting instanceof BooleanSetting booleanSetting) {
               if (booleanSetting.getBind() != -100 && (Boolean)booleanSetting.get() && booleanSetting.isKeybindvisible()) {
                  activeItems.add(new KeybindItem(booleanSetting.getName(), booleanSetting.getBind()));
               }
            }
         }
      }

      return activeItems;
   }

   private float[] calculateDimensions(List activeItems) {
      float maxNameWidth = 0.0F;
      float maxBindWidth = 0.0F;

      KeybindItem item;
      for(Iterator var4 = activeItems.iterator(); var4.hasNext(); maxBindWidth = Math.max(maxBindWidth, Fonts.sf_medium[12].getWidth(KeyMapper.getKey(item.getBind())))) {
         item = (KeybindItem)var4.next();
         maxNameWidth = Math.max(maxNameWidth, Fonts.sf_medium[12].getWidth(item.getName()));
      }

      float width = Math.max(60.0F, maxNameWidth + maxBindWidth + 18.5F);
      return new float[]{width, maxBindWidth};
   }

   private List collectModuleKeybinds() {
      List entries = new ArrayList();
      Iterator var2 = SkyCore.getInstance().getModuleManager().getModules().iterator();

      label42:
      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.getBind() != -100) {
            String categoryIcon = this.getCategoryIcon(module);
            entries.add(new ModuleKeybindEntry(module.getName(), module.getBind(), categoryIcon, module.isEnabled()));
         }

         Iterator var8 = module.getSettings().iterator();

         while(true) {
            BooleanSetting booleanSetting;
            do {
               Setting setting;
               do {
                  if (!var8.hasNext()) {
                     continue label42;
                  }

                  setting = (Setting)var8.next();
               } while(!(setting instanceof BooleanSetting));

               booleanSetting = (BooleanSetting)setting;
            } while(booleanSetting.getBind() == -100);

            String categoryIcon = this.getCategoryIcon(module);
            entries.add(new ModuleKeybindEntry(booleanSetting.getName(), booleanSetting.getBind(), categoryIcon, module.isEnabled() && (Boolean)booleanSetting.get()));
         }
      }

      return entries;
   }

   private String getCategoryIcon(Module module) {
      String categoryName = String.valueOf(module.getCategory());
      if (categoryName != null) {
         switch (categoryName.toLowerCase()) {
            case "combat":
               return KeybindsRender.ModuleCategory.Combat.getIcon();
            case "movement":
               return KeybindsRender.ModuleCategory.Movement.getIcon();
            case "render":
               return KeybindsRender.ModuleCategory.Render.getIcon();
            case "player":
               return KeybindsRender.ModuleCategory.Player.getIcon();
            case "util":
               return KeybindsRender.ModuleCategory.Util.getIcon();
            default:
               return "d";
         }
      } else {
         return "d";
      }
   }

   public KeybindsRender(Dragging dragging) {
      this.dragging = dragging;
   }

   private static class OrdinaryEntry {
      private final String id;
      private final String name;
      private final int bind;
      private final boolean active;

      private OrdinaryEntry(String id, String name, int bind, boolean active) {
         this.id = id;
         this.name = name;
         this.bind = bind;
         this.active = active;
      }

      private boolean isActive() {
         return this.active;
      }
   }

   private static class ModuleKeybindEntry {
      private final String name;
      private final int bind;
      private final String categoryIcon;
      private final boolean active;

      private ModuleKeybindEntry(String name, int bind, String categoryIcon, boolean active) {
         this.name = name;
         this.bind = bind;
         this.categoryIcon = categoryIcon;
         this.active = active;
      }

      private boolean isActive() {
         return this.active;
      }
   }

   private static class KeybindItem {
      private final String name;
      private final int bind;

      public KeybindItem(String name, int bind) {
         this.name = name;
         this.bind = bind;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            KeybindItem that = (KeybindItem)o;
            return this.bind == that.bind && this.name.equals(that.name);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return 31 * this.name.hashCode() + this.bind;
      }

      public String getName() {
         return this.name;
      }

      public int getBind() {
         return this.bind;
      }
   }

   private static enum ModuleCategory {
      Combat("l"),
      Movement("m"),
      Render("b"),
      Player("i"),
      Util("j");

      private final String icon;

      private ModuleCategory(String icon) {
         this.icon = icon;
      }

      public String getIcon() {
         return this.icon;
      }

      // $FF: synthetic method
      private static ModuleCategory[] $values() {
         return new ModuleCategory[]{Combat, Movement, Render, Player, Util};
      }
   }
}
