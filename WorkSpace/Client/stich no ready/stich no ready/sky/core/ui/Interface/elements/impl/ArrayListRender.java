package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.gui.screen.ChatScreen;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ArrayListRender implements ElementRender {
   public static final BooleanSetting showCombat = new BooleanSetting("Combat", true);
   public static final BooleanSetting showMovement = new BooleanSetting("Movement", true);
   public static final BooleanSetting showVisuals = new BooleanSetting("Visuals", true);
   public static final BooleanSetting showPlayer = new BooleanSetting("Player", true);
   public static final BooleanSetting showMisc = new BooleanSetting("Misc", true);
   private static final float ROW_GAP = -1.4F;
   private static final int ROW_PADDING = 0;
   private static final int FONT_SIZE = 13;
   private static final long GRADIENT_CYCLE_MS = 4500L;
   private static final float ROW_HEIGHT = 13.0F;
   private static final float PADDING_X = 5.0F;
   private static final float LINE_WIDTH = 0.5F;
   private static final float NAME_SUFFIX_GAP = 3.0F;
   private static final int TEXT_WHITE = -1;
   private static final float SLIDE_OFFSET = 10.0F;
   private static final float GRADIENT_DARK_BRIGHTNESS = 0.62F;
   private static final List PREVIEW_NAMES = Arrays.asList("Auto Swap", "No Web", "Elytra Target", "Sprint");
   private static final List PREVIEW_SUFFIXES = Arrays.asList("Предметы", "Custom", "Reallyworld", "");
   private final Dragging dragging;
   private final AnimationUtil listAlphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final Map entryAnimations = new HashMap();
   private final Map entryCache = new HashMap();

   public void render(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      boolean preview = mc.currentScreen instanceof ChatScreen;
      List activeEntries = this.collectEntries();
      boolean hasActive = !activeEntries.isEmpty();
      boolean showList = hasActive || preview;
      this.listAlphaAnimation.update(showList ? 1.0F : 0.0F);
      float listAlpha = this.listAlphaAnimation.getValue();
      if (listAlpha <= 0.01F && this.listAlphaAnimation.isDone()) {
         this.dragging.setWidth(0.0F);
         this.dragging.setHeight(0.0F);
      } else {
         if (activeEntries.isEmpty() && preview) {
            activeEntries = this.buildPreviewEntries();
         }

         this.updateEntryAnimations(activeEntries, preview);
         List rows = this.buildRenderRows(activeEntries);
         if (rows.isEmpty()) {
            this.dragging.setWidth(0.0F);
            this.dragging.setHeight(0.0F);
         } else {
            rows.sort(Comparator.comparingDouble((r) -> {
               return (double)(-r.entry.sortWidth);
            }));
            int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
            int darkColor = ColorUtil.darken(logoColor, 0.62F);
            int bgColor = ColorUtil.rgba(0, 0, 0, 150);
            int suffixColor = -1;
            float maxRowWidth = 0.0F;
            float contentHeight = 0.0F;
            Iterator var15 = rows.iterator();

            while(var15.hasNext()) {
               RenderedRow row = (RenderedRow)var15.next();
               float anim = row.anim * listAlpha;
               if (!(anim <= 0.01F)) {
                  maxRowWidth = Math.max(maxRowWidth, row.entry.rowWidth);
                  contentHeight += 11.6F * anim;
               }
            }

            if (contentHeight <= 0.01F) {
               this.dragging.setWidth(0.0F);
               this.dragging.setHeight(0.0F);
            } else {
               float rowBlurHeight = 13.0F;
               float listWidth = maxRowWidth + 0.5F;
               int screenWidth = mc.getMainWindow().getScaledWidth();
               float posX = this.dragging.getX();
               float posY = this.dragging.getY();
               boolean mirror = posX + listWidth * 0.5F >= (float)screenWidth * 0.5F;
               if (mirror) {
                  if (posX < 0.0F) {
                     posX = 4.0F;
                     this.dragging.setX(posX);
                  }

                  if (posX + listWidth > (float)screenWidth + 2.0F) {
                     posX = Math.max(4.0F, (float)screenWidth - listWidth - 4.0F);
                     this.dragging.setX(posX);
                  }
               } else if (posX < 0.0F || posX + listWidth > (float)screenWidth + 2.0F) {
                  posX = Math.max(4.0F, (float)screenWidth - listWidth - 4.0F);
                  this.dragging.setX(posX);
               }

               if (posY < 0.0F) {
                  posY = 8.0F;
                  this.dragging.setY(posY);
               }

               float lineX = mirror ? posX + listWidth - 0.5F : posX;
               int lineColor = ColorUtil.applyOpacity(logoColor, listAlpha);
               int totalHi = Math.round(contentHeight) - 1;
               int visibleRowCount = rows.size();
               RenderUtil.drawMinecraftRectangle(ms, (float)Math.round(lineX), (float)Math.round(posY) + 1.0F, (float)Math.round(0.5F), (float)totalHi, lineColor);
               float renderY = posY;
               int rowIndex = 0;
               Iterator var28 = rows.iterator();

               while(var28.hasNext()) {
                  RenderedRow row = (RenderedRow)var28.next();
                  float anim = row.anim * listAlpha;
                  if (!(anim <= 0.01F)) {
                     ArrayListEntry entry = row.entry;
                     float slideOffset = (1.0F - anim) * 10.0F;
                     float drawWidth = entry.rowWidth - 2.0F;
                     float bgX = mirror ? lineX - drawWidth + slideOffset : lineX + 0.5F - slideOffset;
                     RenderUtil.drawBlurredRoundedRectangle(bgX, renderY, drawWidth, rowBlurHeight, 0.0F, bgColor, anim);
                     float textY = renderY + (rowBlurHeight - Fonts.sf_medium[13].getHeight()) / 2.0F + 1.0F;
                     float suffixWidth = entry.suffix.isEmpty() ? 0.0F : 3.0F + Fonts.sf_medium[13].getWidth(entry.suffix);
                     float totalTextWidth = entry.nameWidth + suffixWidth;
                     float textX = mirror ? bgX + drawWidth - 5.0F - totalTextWidth : bgX + 5.0F;
                     int nameColor = getVerticalFlowColor(rowIndex, visibleRowCount, logoColor, darkColor);
                     ++rowIndex;
                     nameColor = ColorUtil.applyOpacity(nameColor, anim);
                     Fonts.sf_medium[13].drawString(ms, entry.name, (double)(textX - 0.5F), (double)textY, nameColor);
                     if (!entry.suffix.isEmpty()) {
                        float suffixX = textX + entry.nameWidth + 3.0F - 0.5F;
                        int suffixWithAlpha = ColorUtil.applyOpacity(suffixColor, anim);
                        Fonts.sf_medium[13].drawString(ms, entry.suffix, (double)suffixX, (double)textY, suffixWithAlpha);
                     }

                     renderY += (rowBlurHeight + -1.4F) * anim;
                  }
               }

               this.dragging.setWidth(listWidth);
               this.dragging.setHeight(contentHeight);
            }
         }
      }
   }

   private void updateEntryAnimations(List activeEntries, boolean preview) {
      Set activeIds = new HashSet();
      Iterator var4 = activeEntries.iterator();

      while(var4.hasNext()) {
         ArrayListEntry entry = (ArrayListEntry)var4.next();
         activeIds.add(entry.id);
         this.entryCache.put(entry.id, entry);
         AnimationUtil anim = (AnimationUtil)this.entryAnimations.computeIfAbsent(entry.id, (key) -> {
            return new AnimationUtil(0.0F, 12.0F);
         });
         anim.update(1.0F);
      }

      var4 = (new ArrayList(this.entryAnimations.keySet())).iterator();

      while(var4.hasNext()) {
         String id = (String)var4.next();
         if (!activeIds.contains(id)) {
            ((AnimationUtil)this.entryAnimations.get(id)).update(0.0F);
         }
      }

      this.entryAnimations.entrySet().removeIf((e) -> {
         return !activeIds.contains(e.getKey()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
      });
      if (!preview) {
         this.entryCache.keySet().removeIf((idx) -> {
            return !this.entryAnimations.containsKey(idx);
         });
      }

   }

   private List buildRenderRows(List activeEntries) {
      List rows = new ArrayList();
      Set added = new HashSet();
      Iterator var4 = activeEntries.iterator();

      while(true) {
         ArrayListEntry entry;
         AnimationUtil anim;
         float animVal;
         do {
            do {
               if (!var4.hasNext()) {
                  var4 = this.entryAnimations.entrySet().iterator();

                  while(var4.hasNext()) {
                     Map.Entry e = (Map.Entry)var4.next();
                     if (!added.contains(e.getKey())) {
                        float animVal = ((AnimationUtil)e.getValue()).getValue();
                        if (!(animVal <= 0.01F)) {
                           ArrayListEntry cached = (ArrayListEntry)this.entryCache.get(e.getKey());
                           if (cached != null) {
                              rows.add(new RenderedRow(cached, animVal));
                           }
                        }
                     }
                  }

                  return rows;
               }

               entry = (ArrayListEntry)var4.next();
               anim = (AnimationUtil)this.entryAnimations.get(entry.id);
            } while(anim == null);

            animVal = anim.getValue();
         } while(animVal <= 0.01F && anim.isDone());

         rows.add(new RenderedRow(entry, animVal));
         added.add(entry.id);
      }
   }

   private List collectEntries() {
      List entries = new ArrayList();
      Iterator var2 = SkyCore.getInstance().getModuleManager().getModules().iterator();

      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.isEnabled() && !(module instanceof Interface) && isCategoryVisible(module.getCategory())) {
            String name = module.getName();
            String suffix = getModuleSuffix(module);
            entries.add(createEntry(name, suffix));
         }
      }

      entries.sort(Comparator.comparingDouble((e) -> {
         return (double)e.sortWidth;
      }).reversed());
      return entries;
   }

   private List buildPreviewEntries() {
      List entries = new ArrayList();

      for(int i = 0; i < PREVIEW_NAMES.size(); ++i) {
         entries.add(createEntry((String)PREVIEW_NAMES.get(i), (String)PREVIEW_SUFFIXES.get(i)));
      }

      entries.sort(Comparator.comparingDouble((e) -> {
         return (double)e.sortWidth;
      }).reversed());
      return entries;
   }

   private static ArrayListEntry createEntry(String name, String suffix) {
      float nameWidth = Fonts.sf_medium[13].getWidth(name);
      float suffixWidth = suffix.isEmpty() ? 0.0F : 3.0F + Fonts.sf_medium[13].getWidth(suffix);
      float textWidth = nameWidth + suffixWidth;
      float rowWidth = 10.0F + textWidth;
      return new ArrayListEntry(name, name, suffix, nameWidth, rowWidth, textWidth);
   }

   private static boolean isCategoryVisible(Category category) {
      boolean var10000;
      switch (category) {
         case Combat:
            var10000 = (Boolean)showCombat.get();
            break;
         case Movement:
            var10000 = (Boolean)showMovement.get();
            break;
         case Visuals:
            var10000 = (Boolean)showVisuals.get();
            break;
         case Player:
            var10000 = (Boolean)showPlayer.get();
            break;
         case Miscellaneous:
            var10000 = (Boolean)showMisc.get();
            break;
         default:
            throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   private static int getVerticalFlowColor(int rowIndex, int rowCount, int logoColor, int darkColor) {
      if (rowCount <= 1) {
         return logoColor;
      } else {
         float rowT = (float)rowIndex / (float)(rowCount - 1);
         float flow = (float)(System.currentTimeMillis() % 4500L) / 4500.0F;
         float phase = (1.0F - rowT + flow) * 6.2831855F;
         float blend = (float)((Math.sin((double)phase) + 1.0) * 0.5);
         blend = smoothstep(blend);
         return ColorUtil.interpolate(darkColor, logoColor, blend);
      }
   }

   private static float smoothstep(float t) {
      t = Math.max(0.0F, Math.min(1.0F, t));
      return t * t * (3.0F - 2.0F * t);
   }

   private static String getModuleSuffix(Module module) {
      Iterator var1 = module.getSettings().iterator();

      while(var1.hasNext()) {
         Setting setting = (Setting)var1.next();
         if (setting instanceof ModeSetting mode) {
            if (setting.isVisible()) {
               return (String)mode.get();
            }
         }
      }

      return "";
   }

   public ArrayListRender(Dragging dragging) {
      this.dragging = dragging;
   }

   private static final class RenderedRow {
      private final ArrayListEntry entry;
      private final float anim;

      private RenderedRow(ArrayListEntry entry, float anim) {
         this.entry = entry;
         this.anim = anim;
      }
   }

   private static final class ArrayListEntry {
      private final String id;
      private final String name;
      private final String suffix;
      private final float nameWidth;
      private final float rowWidth;
      private final float sortWidth;

      private ArrayListEntry(String id, String name, String suffix, float nameWidth, float rowWidth, float sortWidth) {
         this.id = id;
         this.name = name;
         this.suffix = suffix;
         this.nameWidth = nameWidth;
         this.rowWidth = rowWidth;
         this.sortWidth = sortWidth;
      }

      public String getId() {
         return this.id;
      }

      public String getName() {
         return this.name;
      }

      public String getSuffix() {
         return this.suffix;
      }

      public float getNameWidth() {
         return this.nameWidth;
      }

      public float getRowWidth() {
         return this.rowWidth;
      }

      public float getSortWidth() {
         return this.sortWidth;
      }
   }
}
