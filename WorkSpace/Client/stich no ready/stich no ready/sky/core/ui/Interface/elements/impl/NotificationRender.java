package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.screen.ChatScreen;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class NotificationRender implements ElementRender {
   public static BooleanSetting shield = new BooleanSetting("Ломание щита", false);
   public static BooleanSetting spec = new BooleanSetting("Просьба о наблюдении", false);
   public static BooleanSetting warps = new BooleanSetting("Пиар варпов", false);
   public static BooleanSetting module = new BooleanSetting("Состояние модулей/настроек", true);
   public static BooleanSetting lowstrength = new BooleanSetting("Низкая прочность предметов", true);
   public static BooleanSetting effects = new BooleanSetting("Закончились важные зелья", true);
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   private final Dragging dragging;
   private final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final TimeUtil previewTimer = new TimeUtil();
   private int previewIndex = 0;
   private boolean wasChatOpen = false;
   private static final Map CATEGORY_ICONS = new HashMap();
   private static final int TEXT_COLOR = -1;
   private static final List PREVIEW_ICONS;
   private static final String PREVIEW_TEXT = "Это пример уведомления";

   public void render(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      boolean isChatOpen = mc.currentScreen instanceof ChatScreen;
      boolean hasNotifications = NotificationManager.hasActiveNotifications();
      if (isChatOpen && !this.wasChatOpen) {
         this.previewIndex = 0;
         this.previewTimer.reset();
      }

      this.wasChatOpen = isChatOpen;
      boolean shouldShowPreview = isChatOpen && !hasNotifications;
      this.alphaAnimation.update(shouldShowPreview ? 1.0F : 0.0F);
      float globalAlpha = this.alphaAnimation.getValue();
      if (globalAlpha <= 0.01F) {
         this.dragging.setWidth(0.0F);
         this.dragging.setHeight(0.0F);
      } else {
         if (isChatOpen && this.previewTimer.hasTimeElapsed(1000L, true)) {
            this.previewIndex = (this.previewIndex + 1) % PREVIEW_ICONS.size();
         }

         String currentIcon = (String)PREVIEW_ICONS.get(this.previewIndex);
         this.renderPreviewCard(ms, posY, currentIcon, globalAlpha);
      }
   }

   private void renderPreviewCard(MatrixStack ms, float y, String icon, float alpha) {
      float cardHeight = 19.0F;
      float cardRadius = 5.0F;
      float paddingX = 8.0F;
      float paddingY = 6.0F;
      float gap = 4.0F;
      float iconSize = 7.0F;
      String text = "Это пример уведомления";
      float textWidth = Fonts.sfregular[12].getWidth(text);
      float cardWidth = paddingX + iconSize + gap + textWidth + paddingX;
      float x = ((float)mc.getMainWindow().getScaledWidth() - cardWidth) / 2.0F;
      int cardBgColor = ThemeEditor.getColor(ThemeSettings.WINDOW_BG);
      int iconColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(iconColor, 0.1F);
      int bgWithAlpha = ColorUtil.applyOpacity(bgColor, alpha);
      int textWithAlpha = ColorUtil.applyOpacity(-1, alpha);
      int iconWithAlpha = ColorUtil.applyOpacity(iconColor, alpha);
      RenderUtil.drawRoundedRectangle(x, y, cardWidth, cardHeight, cardRadius, bgWithAlpha);
      float iconHeight = Fonts.skycore[13].getHeight();
      float textHeight = Fonts.sfregular[12].getHeight();
      float contentHeight = Math.max(iconHeight, textHeight);
      float contentY = y + (cardHeight - contentHeight) / 2.0F;
      float iconX = x + paddingX;
      float iconY = contentY + (contentHeight - iconHeight) / 2.0F + 1.0F;
      if (icon != null && !icon.isEmpty()) {
         Fonts.skycore[13].drawString(ms, icon, (double)iconX, (double)iconY, iconWithAlpha);
      }

      float textX = iconX + iconSize + gap;
      float textY = contentY + (contentHeight - textHeight) / 2.0F + 1.0F;
      Fonts.sfregular[12].drawString(ms, text, (double)textX, (double)textY, textWithAlpha);
      this.dragging.setWidth(cardWidth);
      this.dragging.setHeight(cardHeight);
   }

   public static String getCategoryIcon(Module module) {
      Category category = module.getCategory();
      return (String)CATEGORY_ICONS.getOrDefault(category, "d");
   }

   public NotificationRender(Dragging dragging) {
      this.dragging = dragging;
   }

   static {
      CATEGORY_ICONS.put(Category.Combat, "l");
      CATEGORY_ICONS.put(Category.Movement, "m");
      CATEGORY_ICONS.put(Category.Visuals, "b");
      CATEGORY_ICONS.put(Category.Player, "i");
      CATEGORY_ICONS.put(Category.Miscellaneous, "d");
      PREVIEW_ICONS = new ArrayList();
      PREVIEW_ICONS.add("l");
      PREVIEW_ICONS.add("m");
      PREVIEW_ICONS.add("b");
      PREVIEW_ICONS.add("i");
      PREVIEW_ICONS.add("d");
   }
}
