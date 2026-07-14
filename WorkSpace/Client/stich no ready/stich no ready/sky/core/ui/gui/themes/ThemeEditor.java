package sky.core.ui.gui.themes;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import sky.core.SkyCore;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.modules.api.elements.impl.ColorElement;
import sky.core.modules.api.elements.impl.StringElement;
import sky.core.ui.gui.Panel;
import sky.core.ui.gui.guihandlers.ColorPickerHandler;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.managers.impl.ClientConfig;
import sky.core.utils.managers.impl.ThemeManager;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class ThemeEditor extends Panel {
   public static final EnumMap colorSettings = new EnumMap(ThemeSettings.class);
   public static List stringSettings = new ArrayList();
   public static List stringElements = new ArrayList();
   private final List colorPickers = new ArrayList();
   private final Map defaultPresets = new LinkedHashMap();
   private final LinkedHashMap customPresets = new LinkedHashMap();
   private final ThemeManager themeManager = new ThemeManager();
   private final float presetCircleRadius = 4.5F;
   private final float presetCircleSpacing = 1.0F;
   private ThemePresetContextMenu contextMenu = null;
   private ThemePreset currentPreset = null;
   private ThemePreset lastPreset = null;
   private final ColorPickerHandler colorPickerHandler;
   private static final float NAME_FIELD_WIDTH = 74.0F;
   private static final float NAME_FIELD_HEIGHT = 11.0F;
   private static final float NAME_FIELD_Y_OFFSET = 30.0F;
   private static final float CREATE_BUTTON_X_OFFSET = 84.0F;
   private static final float CREATE_BUTTON_Y_OFFSET = 30.0F;
   private static final float PRESET_CIRCLES_BOTTOM_OFFSET = 14.0F;
   private static final float PICKERS_Y_OFFSET = 48.0F;
   private static final float PICKERS_TO_PRESETS_GAP = 8.0F;
   float scroll;
   float animatedScroll;
   private final AnimationUtil scrollAnimation;
   private final AnimationUtil createAnimation;
   private final Map presetTooltipAnimations;
   float maxHeight;
   private boolean draggingScrollBar;
   private float scrollDragOffset;

   public ThemeEditor() {
      this.scrollAnimation = new AnimationUtil(0.0F, 10.0F, Easings.SINE_OUT);
      this.createAnimation = new AnimationUtil(0.0F, 3.0F, Easings.LINEAR);
      this.presetTooltipAnimations = new HashMap();
      this.maxHeight = 0.0F;
      this.width = 120.0F;
      this.height = 275.0F;
      this.colorPickerHandler = new ColorPickerHandler(Collections.singletonList(this));
      this.initThemeSettings();
      this.loadCustomThemes();
      this.loadCurrentThemeFromConfig();
   }

   private void initThemeSettings() {
      StringSetting nameSetting = new StringSetting("Название");
      stringSettings.add(nameSetting);
      stringElements.add(new StringElement(nameSetting));
      ThemeSettings[] var2 = ThemeSettings.values();
      int var3 = var2.length;

      int var4;
      ThemeSettings setting;
      for(var4 = 0; var4 < var3; ++var4) {
         setting = var2[var4];
         colorSettings.put(setting, new ColorSetting(setting.getDisplayName(), true, 0));
      }

      Iterator var6 = colorSettings.values().iterator();

      while(var6.hasNext()) {
         ColorSetting colorSetting = (ColorSetting)var6.next();
         colorSetting.setOnChange(this::updateAndSavePreset);
      }

      this.initDefaultPresets();
      var2 = ThemeSettings.values();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         setting = var2[var4];
         this.colorPickers.add(new ColorElement((ColorSetting)colorSettings.get(setting)));
      }

   }

   private void updateAndSavePreset() {
      if (this.currentPreset != null && this.currentPreset.isCustom()) {
         int[] updatedColors = new int[colorSettings.size()];
         ThemeSettings[] settings = ThemeSettings.values();

         for(int i = 0; i < settings.length; ++i) {
            updatedColors[i] = (Integer)((ColorSetting)colorSettings.get(settings[i])).get();
         }

         String creator = "NexusUser";
         String cleanPresetName = this.currentPreset.getName().replace("Название: ", "");
         this.currentPreset = new ThemePreset(this.currentPreset.getName(), updatedColors, creator, true);
         this.customPresets.put(this.currentPreset.getName(), updatedColors);
         this.themeManager.saveTheme(cleanPresetName, this.currentPreset, creator);
      }

   }

   public static int getColor(ThemeSettings setting) {
      return (Integer)((ColorSetting)colorSettings.get(setting)).get();
   }

   public static float getAlpha(ThemeSettings setting) {
      return ((ColorSetting)colorSettings.get(setting)).getAlpha();
   }

   private void initDefaultPresets() {
      this.defaultPresets.clear();
      this.defaultPresets.putAll(DefaultThemePresets.getDefaultPresets());
      String presetToUse = "Название: Прозрачная";
      int[] colors = (int[])this.defaultPresets.get(presetToUse);
      if (colors == null && !this.defaultPresets.isEmpty()) {
         Map.Entry first = (Map.Entry)this.defaultPresets.entrySet().iterator().next();
         presetToUse = (String)first.getKey();
         colors = (int[])first.getValue();
      }

      boolean isCustom = false;
      this.currentPreset = new ThemePreset(presetToUse, colors, "Unknown", isCustom);
      ClientConfig.setCurrentActiveTheme(presetToUse);
      ThemeSettings[] settings = ThemeSettings.values();

      for(int i = 0; i < Math.min(colors.length, settings.length); ++i) {
         ((ColorSetting)colorSettings.get(settings[i])).set(colors[i]);
      }

   }

   private void renderpreset(MatrixStack stack, float mouseX, float mouseY) {
      float startY = this.y + this.height - 14.0F;
      float startX = this.x + 6.0F;
      int index = 0;
      index = this.renderPresetList(stack, mouseX, mouseY, this.defaultPresets, index, startX, startY);
      this.renderPresetList(stack, mouseX, mouseY, this.customPresets, index, startX, startY);
   }

   private int renderPresetList(MatrixStack stack, float mouseX, float mouseY, Map presets, int startIndex, float startX, float startY) {
      for(Iterator var8 = presets.entrySet().iterator(); var8.hasNext(); ++startIndex) {
         Map.Entry preset = (Map.Entry)var8.next();
         if (startIndex >= 22) {
            break;
         }

         float circleX = startX + 10.0F * (float)(startIndex % 11) + 4.0F;
         float circleY = startY + (startIndex >= 11 ? 10.0F : 0.0F);
         boolean hovered = MathUtil.isHovered(mouseX, mouseY, circleX - 4.5F, circleY - 4.5F, 9.0F, 9.0F);
         float selectedCircleRadius = 2.5F;
         RenderUtil.drawRoundedRectangle(circleX - 4.5F, circleY - 4.5F, 10.0F, 10.0F, 4.0F, ((int[])preset.getValue())[ThemeSettings.LOGO.ordinal()]);
         String activeTheme = ClientConfig.getCurrentActiveTheme();
         if (((String)preset.getKey()).equals(activeTheme)) {
            RenderUtil.drawRoundedRectangle(circleX - selectedCircleRadius + 0.5F, circleY - selectedCircleRadius + 0.5F, selectedCircleRadius * 2.0F, selectedCircleRadius * 2.0F, selectedCircleRadius - 1.0F, ColorUtil.getColor(0, 0, 0, 255));
         }

         AnimationUtil anim = (AnimationUtil)this.presetTooltipAnimations.computeIfAbsent((String)preset.getKey(), (key) -> {
            return new AnimationUtil(0.0F, 15.0F, Easings.LINEAR);
         });
         anim.update(hovered ? 1.0F : 0.0F);
      }

      return startIndex;
   }

   public float getPickersScissorTop() {
      return this.y + 48.0F;
   }

   public float getPickersScissorBottom() {
      float presetsTopY = this.y + this.height - 14.0F - 4.5F;
      return Math.max(this.getPickersScissorTop(), presetsTopY - 8.0F);
   }

   private float getScrollAreaY() {
      return this.y + 48.0F;
   }

   private float getScrollAreaHeight() {
      return Math.max(0.0F, this.getPickersScissorBottom() - this.getScrollAreaY());
   }

   private float getThemeThumbHeight(float contentHeight) {
      return Math.max(14.0F, contentHeight / this.maxHeight * this.getScrollAreaHeight() * 0.8F);
   }

   private boolean isHoveringThemeScrollThumb(float mouseX, float mouseY) {
      float contentHeight = this.getHeight() - 46.0F;
      if (this.maxHeight <= contentHeight) {
         return false;
      } else {
         float thumbHeight = this.getThemeThumbHeight(contentHeight);
         float maxThumbTravel = Math.max(0.0F, this.getScrollAreaHeight() - thumbHeight);
         float thumbPosition = maxThumbTravel <= 0.0F ? 0.0F : -this.animatedScroll / (this.maxHeight - contentHeight) * maxThumbTravel;
         float scrollX = this.x + this.getWidth() - 4.5F;
         return MathUtil.isHovered(mouseX, mouseY, scrollX - 4.5F, this.getScrollAreaY() + thumbPosition - 2.0F, 11.0F, thumbHeight + 4.0F);
      }
   }

   public void render(MatrixStack stack, float mouseX, float mouseY, float alpha) {
      this.scrollAnimation.update(this.scroll);
      this.animatedScroll = this.scrollAnimation.getValue();
      if (this.createAnimation.getTarget() == 1.0F && this.createAnimation.isDone()) {
         this.createAnimation.update(0.0F);
      }

      if (!this.createAnimation.isDone()) {
         this.createAnimation.update(this.createAnimation.getTarget());
      }

      float contentHeight = this.getHeight() - 46.0F;
      if (this.maxHeight > contentHeight) {
         this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
         this.animatedScroll = MathHelper.clamp(this.animatedScroll, -this.maxHeight + contentHeight, 0.0F);
      } else {
         this.scroll = this.animatedScroll = 0.0F;
      }

      int logoColor = getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(logoColor, 0.1F);
      int bgWithAlpha = ColorUtil.applyOpacity(bgColor, getAlpha(ThemeSettings.LOGO) / 255.0F * alpha);
      RenderUtil.drawRoundedRectangle(this.x, this.y + 4.0F, this.getWidth() - 8.0F, this.getHeight() - 4.0F, 10.0F, bgWithAlpha);
      String categoryIcon = "";
      String title = "Theme Editor";
      float iconWidth = Fonts.skycore[14].getWidth(categoryIcon);
      float titleWidth = Fonts.sf_regular[19].getWidth(title);
      float headerGap = 4.0F;
      float totalHeaderWidth = iconWidth + headerGap + titleWidth;
      float headerStartX = this.getX() + (this.getWidth() - 8.0F - totalHeaderWidth) / 2.0F;
      float headerTextY = this.getY() + 12.0F + 1.0F;
      int iconColor = ColorUtil.getColorWithAlpha(getColor(ThemeSettings.LOGO), (float)((int)(getAlpha(ThemeSettings.LOGO) * alpha)));
      int headerTextColor = ColorUtil.getColorWithAlpha(getColor(ThemeSettings.HEADER), (float)((int)(getAlpha(ThemeSettings.HEADER) * alpha)));
      Fonts.skycore[14].drawString(stack, categoryIcon, (double)(headerStartX - 4.0F), (double)(headerTextY + 5.0F), iconColor);
      Fonts.sf_regular[19].drawString(stack, title, (double)(headerStartX + iconWidth + headerGap - 4.0F), (double)(headerTextY + 2.0F), headerTextColor);
      Iterator var19 = stringElements.iterator();

      while(var19.hasNext()) {
         StringElement stringElement = (StringElement)var19.next();
         stringElement.setX(this.x + 1.0F);
         stringElement.setY(this.y + 30.0F);
         stringElement.setWidth(99.0F);
         stringElement.setHeight(11.0F);
         stringElement.render(stack, mouseX, mouseY, alpha);
      }

      float buttonX = this.x + 84.0F;
      float buttonY = this.y + 30.0F;
      float buttonWidth = 29.0F;
      float buttonHeight = 11.0F;
      float createAnim = this.createAnimation.getValue();
      int createBg = ColorUtil.interpolate(getColor(ThemeSettings.BUTTON), getColor(ThemeSettings.BUTTON_INACTIVE), createAnim);
      RenderUtil.drawRoundedRectangle(buttonX - 5.0F, buttonY, buttonWidth, buttonHeight, 3.0F, ColorUtil.getColorWithAlpha(createBg, (float)((int)((float)ColorUtil.alpha(createBg) * alpha))));
      float textpedik = buttonX + (buttonWidth - Fonts.sf_regular[12].getWidth("Создать")) / 2.0F;
      float textIgrek = buttonY + 5.5F;
      int createTextColor = ColorUtil.interpolate(getColor(ThemeSettings.TEXT), getColor(ThemeSettings.TEXT_INACTIVE), createAnim);
      Fonts.sf_regular[12].drawString(stack, "Создать", (double)(textpedik - 5.0F), (double)(textIgrek - 1.0F), ColorUtil.getColorWithAlpha(createTextColor, (float)((int)((float)ColorUtil.alpha(createTextColor) * alpha))));
      float pickerY = this.y + 48.0F;
      float pickersScissorY = this.getPickersScissorTop();
      float pickersScissorHeight = Math.max(0.0F, this.getPickersScissorBottom() - pickersScissorY);
      ScissorUtil.start((double)this.getX(), (double)pickersScissorY, (double)(this.getWidth() - 8.0F), (double)pickersScissorHeight);

      ColorElement colorPicker;
      for(Iterator var31 = this.colorPickers.iterator(); var31.hasNext(); pickerY += colorPicker.getHeight() + 1.5F) {
         colorPicker = (ColorElement)var31.next();
         colorPicker.setX(this.x + 2.5F);
         colorPicker.setY(pickerY + this.animatedScroll);
         colorPicker.setWidth(this.getWidth() - 5.0F);
         colorPicker.render(stack, mouseX, mouseY, alpha);
      }

      this.maxHeight = pickerY - (this.y + 14.0F);
      ScissorUtil.end();
      this.renderpreset(stack, mouseX, mouseY);
      this.colorPickerHandler.render(stack, (int)mouseX, (int)mouseY);
      if (this.contextMenu != null) {
         this.contextMenu.render(stack);
         if (this.contextMenu.isClosed()) {
            this.contextMenu = null;
         }
      }

   }

   public void mouseClicked(float mouseX, float mouseY, int button) {
      Iterator var4 = stringElements.iterator();

      while(var4.hasNext()) {
         StringElement stringElement = (StringElement)var4.next();
         stringElement.mouseClicked(mouseX, mouseY, button);
      }

      float menuX;
      float menuY;
      float menuW;
      float menuH;
      if (button == 0 && this.isHoveringThemeScrollThumb(mouseX, mouseY)) {
         menuX = this.getHeight() - 46.0F;
         menuY = this.getThemeThumbHeight(menuX);
         menuW = Math.max(0.0F, this.getScrollAreaHeight() - menuY);
         menuH = menuW <= 0.0F ? 0.0F : -this.animatedScroll / (this.maxHeight - menuX) * menuW;
         this.draggingScrollBar = true;
         this.scrollDragOffset = mouseY - (this.getScrollAreaY() + menuH);
      } else if (this.contextMenu != null && button == 0) {
         menuX = this.contextMenu.getX();
         menuY = this.contextMenu.getY() + 4.0F;
         menuW = this.contextMenu.getWidth();
         menuH = this.contextMenu.getHeight();
         if (MathUtil.isHovered(mouseX, mouseY, menuX, menuY, menuW, menuH)) {
            String presetName = this.getPresetNameByIndex(this.contextMenu.getPresetIndex());
            if (presetName != null && this.customPresets.containsKey(presetName)) {
               String cleanPresetName = presetName.replace("Название: ", "");
               this.themeManager.deleteTheme(cleanPresetName);
               this.customPresets.remove(presetName);
            }
         }

         this.contextMenu.close();
      } else if (MathUtil.isHovered(mouseX, mouseY, this.x + 84.0F, this.y + 30.0F + 2.0F, 29.0F, 11.0F) && button == 0) {
         if (!this.createAnimation.isAlive()) {
            String themeName = (String)((StringSetting)stringSettings.get(0)).get();
            if (themeName != null && !themeName.trim().isEmpty()) {
               this.createAnimation.setValue(0.0F);
               this.createAnimation.update(1.0F);
               this.saveCustomTheme();
            } else {
               ChatUtil.addText("Введите название темы!");
            }
         }
      } else {
         int index = 0;
         List allPresets = new ArrayList();
         allPresets.addAll(this.defaultPresets.entrySet());
         allPresets.addAll(this.customPresets.entrySet());

         for(Iterator var6 = allPresets.iterator(); var6.hasNext(); ++index) {
            Map.Entry preset = (Map.Entry)var6.next();
            if (index >= 22) {
               break;
            }

            float circleX = this.x + 6.0F + 10.0F * (float)(index % 11) + 4.0F;
            float circleY = this.y + this.height - 14.0F + (index >= 11 ? 10.0F : 0.0F);
            if (MathUtil.isHovered(mouseX, mouseY, circleX - 4.5F, circleY - 4.5F, 9.0F, 9.0F)) {
               boolean isCustom = this.customPresets.containsKey(preset.getKey());
               if (button == 0) {
                  this.applyPreset((int[])preset.getValue(), (String)preset.getKey(), isCustom);
                  if (this.contextMenu != null) {
                     this.contextMenu.close();
                  }
               } else if (button == 1 && isCustom) {
                  this.contextMenu = new ThemePresetContextMenu(circleX + 2.0F, circleY - 4.5F - 1.0F, 52.0F, 14.0F, index);
               }

               return;
            }
         }

      }
   }

   private String getPresetNameByIndex(int index) {
      List allPresets = new ArrayList();
      allPresets.addAll(this.defaultPresets.entrySet());
      allPresets.addAll(this.customPresets.entrySet());
      return index >= 0 && index < allPresets.size() ? (String)((Map.Entry)allPresets.get(index)).getKey() : null;
   }

   private void saveCustomTheme() {
      if (this.defaultPresets.size() + this.customPresets.size() < 22) {
         String themeName = (String)((StringSetting)stringSettings.get(0)).get();
         if (themeName != null && !themeName.trim().isEmpty()) {
            String fullThemeName = "Название: " + themeName;
            if (!this.defaultPresets.containsKey(fullThemeName) && !this.customPresets.containsKey(fullThemeName)) {
               String creator = "SkyCore";
               int[] colors = new int[ThemeSettings.values().length];
               ThemeSettings[] settings = ThemeSettings.values();

               for(int i = 0; i < settings.length; ++i) {
                  colors[i] = (Integer)((ColorSetting)colorSettings.get(settings[i])).get();
               }

               this.lastPreset = this.currentPreset;
               this.currentPreset = new ThemePreset(fullThemeName, colors, creator, true);
               this.themeManager.saveTheme(themeName, this.currentPreset, creator);
               this.customPresets.put(fullThemeName, colors);
               ClientConfig.setCurrentActiveTheme(fullThemeName);
               ClientConfig clientConfig = SkyCore.getInstance().getClientConfig();
               if (clientConfig != null) {
                  clientConfig.onThemeCreated(fullThemeName);
               }

               ((StringSetting)stringSettings.get(0)).setValue("");
               ((StringElement)stringElements.get(0)).clearText();
            }
         }
      }
   }

   private void applyPreset(int[] colors, String presetName, boolean isCustom) {
      this.lastPreset = this.currentPreset;
      ThemeSettings[] settings = ThemeSettings.values();

      for(int i = 0; i < Math.min(colors.length, settings.length); ++i) {
         ColorSetting colorSetting = (ColorSetting)colorSettings.get(settings[i]);
         Runnable originalCallback = colorSetting.getOnChange();
         colorSetting.setOnChange((Runnable)null);
         colorSetting.set(colors[i]);
         colorSetting.setOnChange(originalCallback);
      }

      this.currentPreset = new ThemePreset(presetName, colors, "SkyCore", isCustom);
      ClientConfig.setCurrentActiveTheme(presetName);
      this.colorPickerHandler.closeAllPickers();
   }

   private void loadCustomThemes() {
      this.themeManager.getThemeNames().forEach((name) -> {
         ThemeManager.Theme theme = this.themeManager.loadTheme(name);
         if (theme != null && theme.getPresetName() != null && theme.getPresetColors() != null) {
            this.customPresets.put(theme.getPresetName(), theme.getPresetColors());
         }

      });
   }

   private void loadCurrentThemeFromConfig() {
      ClientConfig clientConfig = SkyCore.getInstance().getClientConfig();
      if (clientConfig != null) {
         String savedTheme = ((ClientConfig.ClientConfigData)clientConfig.getData()).getSelectedTheme();
         if (savedTheme != null && !savedTheme.trim().isEmpty()) {
            int[] colors = (int[])this.defaultPresets.get(savedTheme);
            if (colors != null) {
               this.applyPreset(colors, savedTheme, false);
            } else {
               colors = (int[])this.customPresets.get(savedTheme);
               if (colors != null) {
                  this.applyPreset(colors, savedTheme, true);
               }
            }
         }
      }

   }

   public void mouseReleased(float mouseX, float mouseY, int button) {
      if (button == 0) {
         this.draggingScrollBar = false;
      }

      Iterator var4 = this.colorPickers.iterator();

      while(var4.hasNext()) {
         ColorElement colorPicker = (ColorElement)var4.next();
         if (colorPicker.getColorPicker().isColorPickMode()) {
            colorPicker.getColorPicker().mouseReleased();
         }
      }

   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
      if (button == 0 && this.draggingScrollBar) {
         float contentHeight = this.getHeight() - 46.0F;
         if (this.maxHeight <= contentHeight) {
            this.scroll = 0.0F;
            return true;
         } else {
            float thumbHeight = this.getThemeThumbHeight(contentHeight);
            float maxThumbTravel = Math.max(0.0F, this.getScrollAreaHeight() - thumbHeight);
            if (maxThumbTravel <= 0.0F) {
               this.scroll = 0.0F;
               return true;
            } else {
               float thumbTop = MathHelper.clamp((float)mouseY - this.getScrollAreaY() - this.scrollDragOffset, 0.0F, maxThumbTravel);
               float progress = thumbTop / maxThumbTravel;
               this.scroll = -progress * Math.max(0.0F, this.maxHeight - contentHeight);
               return true;
            }
         }
      } else {
         return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
      }
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (MathUtil.isHovered((float)mouseX, (float)mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
         float contentHeight = this.getHeight() - 46.0F;
         boolean canScroll = this.maxHeight > contentHeight;
         float previousScroll = this.scroll;
         this.setScroll((float)((double)this.getScroll() + delta * 10.0));
         this.scroll = MathHelper.clamp(this.scroll, -this.maxHeight + contentHeight, 0.0F);
         if (canScroll && this.scroll != previousScroll) {
            this.colorPickerHandler.closeAllPickers();
         }

         return this.scroll != previousScroll;
      } else {
         return super.mouseScrolled(mouseX, mouseY, delta);
      }
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      Iterator var4 = stringElements.iterator();

      while(var4.hasNext()) {
         StringElement stringElement = (StringElement)var4.next();
         stringElement.keyPressed(keyCode, scanCode, modifiers);
      }

   }

   public void charTyped(char codePoint, int modifiers) {
      Iterator var3 = stringElements.iterator();

      while(var3.hasNext()) {
         StringElement stringElement = (StringElement)var3.next();
         stringElement.charTyped(codePoint, modifiers);
      }

   }

   public void clearContextMenu() {
      this.contextMenu = null;
   }

   public void setContextMenu(ThemePresetContextMenu contextMenu) {
      this.contextMenu = contextMenu;
   }

   public void setCurrentPreset(ThemePreset currentPreset) {
      this.currentPreset = currentPreset;
   }

   public void setLastPreset(ThemePreset lastPreset) {
      this.lastPreset = lastPreset;
   }

   public void setScroll(float scroll) {
      this.scroll = scroll;
   }

   public void setAnimatedScroll(float animatedScroll) {
      this.animatedScroll = animatedScroll;
   }

   public void setMaxHeight(float maxHeight) {
      this.maxHeight = maxHeight;
   }

   public void setDraggingScrollBar(boolean draggingScrollBar) {
      this.draggingScrollBar = draggingScrollBar;
   }

   public void setScrollDragOffset(float scrollDragOffset) {
      this.scrollDragOffset = scrollDragOffset;
   }

   public List getColorPickers() {
      return this.colorPickers;
   }

   public Map getDefaultPresets() {
      return this.defaultPresets;
   }

   public LinkedHashMap getCustomPresets() {
      return this.customPresets;
   }

   public ThemeManager getThemeManager() {
      return this.themeManager;
   }

   public float getPresetCircleRadius() {
      Objects.requireNonNull(this);
      return 4.5F;
   }

   public float getPresetCircleSpacing() {
      Objects.requireNonNull(this);
      return 1.0F;
   }

   public ThemePresetContextMenu getContextMenu() {
      return this.contextMenu;
   }

   public ThemePreset getCurrentPreset() {
      return this.currentPreset;
   }

   public ThemePreset getLastPreset() {
      return this.lastPreset;
   }

   public ColorPickerHandler getColorPickerHandler() {
      return this.colorPickerHandler;
   }

   public float getScroll() {
      return this.scroll;
   }

   public float getAnimatedScroll() {
      return this.animatedScroll;
   }

   public AnimationUtil getScrollAnimation() {
      return this.scrollAnimation;
   }

   public AnimationUtil getCreateAnimation() {
      return this.createAnimation;
   }

   public Map getPresetTooltipAnimations() {
      return this.presetTooltipAnimations;
   }

   public float getMaxHeight() {
      return this.maxHeight;
   }

   public boolean isDraggingScrollBar() {
      return this.draggingScrollBar;
   }

   public float getScrollDragOffset() {
      return this.scrollDragOffset;
   }

   private static class ThemePresetContextMenu {
      private final float x;
      private final float y;
      private final float width;
      private final float height;
      private final int presetIndex;
      private final AnimationUtil scaleAnimation;
      private boolean isClosing;

      public ThemePresetContextMenu(float x, float y, float width, float height, int presetIndex) {
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.presetIndex = presetIndex;
         this.scaleAnimation = new AnimationUtil(0.0F, 1.1F, Easings.LINEAR);
         this.isClosing = false;
      }

      public void render(MatrixStack stack) {
         this.scaleAnimation.setSpeed(10.0F);
         this.scaleAnimation.update(this.isClosing ? 0.0F : 1.0F);
         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         RenderUtil.scaleStart(this.x + this.width / 2.0F, this.y + 4.0F + this.height / 2.0F, this.scaleAnimation.getValue());
         RenderUtil.drawRoundedRectangle(this.x, this.y + 4.0F, this.width, this.height, 5.0F, bgColor);
         float textX = this.x + 13.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/gui/bin.png"), this.x + 3.0F, this.y + 7.5F, 8.0F, 8.0F, ThemeEditor.getColor(ThemeSettings.TEXT));
         Fonts.sf_regular[12].drawString(stack, "Удалить", (double)(textX - 0.5F), (double)(this.y + 10.5F), ThemeEditor.getColor(ThemeSettings.TEXT));
         RenderUtil.scaleEnd();
      }

      public void close() {
         if (!this.isClosing) {
            this.isClosing = true;
            this.scaleAnimation.update(0.0F);
         }

      }

      public boolean isClosed() {
         return this.isClosing && this.scaleAnimation.isDone();
      }

      public float getX() {
         return this.x;
      }

      public float getY() {
         return this.y;
      }

      public float getWidth() {
         return this.width;
      }

      public float getHeight() {
         return this.height;
      }

      public int getPresetIndex() {
         return this.presetIndex;
      }

      public AnimationUtil getScaleAnimation() {
         return this.scaleAnimation;
      }

      public boolean isClosing() {
         return this.isClosing;
      }
   }

   public static class ThemePreset {
      private final String name;
      private final int[] colors;
      private final String creator;
      private final boolean isCustom;

      public ThemePreset(String name, int[] colors, String creator, boolean isCustom) {
         this.name = name;
         this.colors = colors;
         this.creator = creator != null ? creator : "Unknown";
         this.isCustom = isCustom;
      }

      public String getName() {
         return this.name;
      }

      public int[] getColors() {
         return this.colors;
      }

      public String getCreator() {
         return this.creator;
      }

      public boolean isCustom() {
         return this.isCustom;
      }
   }
}
