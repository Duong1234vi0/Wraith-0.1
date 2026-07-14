package sky.core.ui.Interface.elements.impl;

import com.adl.nativeprotect.User;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import sky.core.events.EventRender2D;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class WatermarkRender implements ElementRender {
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   private final Dragging dragging;

   public WatermarkRender(Dragging dragging) {
      this.dragging = dragging;
   }

   public void render(EventRender2D.Post event) {
      if (Interface.isOldHud()) {
         this.renderClassic(event);
      } else {
         float posX = this.dragging.getX();
         float posY = this.dragging.getY();
         MatrixStack ms = event.getStack();
         String titleText = "StichDLC";
         String icon = "a";
         String nameText = User.getInstance().profile("username");
         String iconName = "b";
         String ipIcon = "g";

         String ip;
         try {
            boolean isMultiplayer = Minecraft.getInstance().getCurrentServerData() != null;
            ip = isMultiplayer ? Minecraft.getInstance().getCurrentServerData().serverIP : "localhost";
         } catch (Exception var34) {
            ip = "localhost";
         }

         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         int textColor = -1;
         int separatorColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * 0.2F);
         float skyCoreWidth = Fonts.sf_medium[13].getWidth(titleText);
         float userWidth = Fonts.sf_medium[13].getWidth(nameText);
         float ipWidth = Fonts.sf_medium[13].getWidth(ip);
         float totalHeight = 22.0F;
         float padding = 6.0F;
         float gap = 6.0F;
         float innerGap = 4.0F;
         float smallGap = 2.0F;
         float borderRadius = 6.0F;
         float iconSize = 7.0F;
         float fontSize = 6.0F;
         float separatorWidth = 0.75F;
         float separatorHeight = 21.0F;
         float leftSectionWidth = iconSize + innerGap + skyCoreWidth;
         float rightSectionWidth = iconSize + smallGap + userWidth + gap + gap + iconSize + smallGap + ipWidth;
         float totalWidth = padding + leftSectionWidth + gap + separatorWidth + gap + rightSectionWidth + padding;
         RenderUtil.drawRoundedRectangle(posX, posY, totalWidth, totalHeight, borderRadius, bgColor);
         float currentX = posX + padding;
         float textY = posY + (totalHeight - fontSize) / 2.0F + 1.5F;
         Fonts.skycore[14].drawString(ms, icon, (double)currentX, (double)(posY + (totalHeight - iconSize) / 2.0F + 2.0F), logoColor);
         currentX += iconSize + innerGap;
         Fonts.sfregular[13].drawString(ms, titleText, (double)currentX, (double)textY, textColor);
         currentX += skyCoreWidth + gap;
         RenderUtil.drawMinecraftRectangle(ms, currentX, posY + 0.5F, separatorWidth, separatorHeight, separatorColor);
         currentX += separatorWidth + gap;
         Fonts.skycore[14].drawString(ms, iconName, (double)currentX, (double)(posY + (totalHeight - iconSize) / 2.0F + 2.5F), logoColor);
         currentX += iconSize + smallGap;
         Fonts.sfregular[13].drawString(ms, nameText, (double)currentX, (double)textY, textColor);
         currentX += userWidth + gap;
         RenderUtil.drawRoundedRectangle(currentX - 2.5F, posY + totalHeight / 2.0F - 1.0F, 3.0F, 3.0F, 0.5F, ColorUtil.applyOpacity(textColor, 0.1F));
         currentX += gap;
         Fonts.skycore[14].drawString(ms, ipIcon, (double)currentX, (double)(posY + (totalHeight - iconSize) / 2.0F + 2.5F), logoColor);
         currentX += iconSize + smallGap;
         Fonts.sfregular[13].drawString(ms, ip, (double)currentX, (double)textY, textColor);
         this.dragging.setWidth(totalWidth);
         this.dragging.setHeight(totalHeight);
      }
   }

   private void renderClassic(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float x = this.dragging.getX();
      float y = this.dragging.getY();
      int mainSize = true;
      int labelSize = true;
      int iconSize = true;
      String user = User.getInstance().profile("username");
      String fpsValue = String.valueOf(Minecraft.debugFPS);

      String pingValue;
      try {
         boolean isMultiplayer = Minecraft.getInstance().getCurrentServerData() != null;
         int var73;
         if (isMultiplayer) {
            ClientPlayNetHandler var10000 = Minecraft.getInstance().getConnection();
            Minecraft.getInstance();
            var73 = var10000.getPlayerInfo(Minecraft.player.getUniqueID()).getResponseTime();
         } else {
            var73 = 0;
         }

         int pingVal = var73;
         pingValue = String.valueOf(pingVal);
      } catch (Exception var70) {
         pingValue = "0";
      }

      String tpsValue = "20";
      Locale var74 = Locale.ENGLISH;
      Object[] var10002 = new Object[1];
      Minecraft.getInstance();
      var10002[0] = Minecraft.player.getPosX();
      String xValue = String.format(var74, "%.0f", var10002);
      var74 = Locale.ENGLISH;
      var10002 = new Object[1];
      Minecraft.getInstance();
      var10002[0] = Minecraft.player.getPosY();
      String yValue = String.format(var74, "%.0f", var10002);
      var74 = Locale.ENGLISH;
      var10002 = new Object[1];
      Minecraft.getInstance();
      var10002[0] = Minecraft.player.getPosZ();
      String zValue = String.format(var74, "%.0f", var10002);
      Minecraft.getInstance();
      double var75 = Minecraft.player.getPosX();
      Minecraft.getInstance();
      double dx = var75 - Minecraft.player.prevPosX;
      Minecraft.getInstance();
      var75 = Minecraft.player.getPosY();
      Minecraft.getInstance();
      double dy = var75 - Minecraft.player.prevPosY;
      Minecraft.getInstance();
      var75 = Minecraft.player.getPosZ();
      Minecraft.getInstance();
      double dz = var75 - Minecraft.player.prevPosZ;
      String bpsValue = String.format(Locale.ENGLISH, "%.1f", Math.sqrt(dx * dx + dy * dy + dz * dz) * 20.0);
      int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = (Boolean)alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG);
      int whiteColor = -1;
      int textColor = ColorUtil.applyOpacity(whiteColor, 180);
      int dotColor = ColorUtil.hex("#9A9A9A");
      float textFpsWidth = Fonts.sf_medium[13].getWidth("fps");
      float textMsWidth = Fonts.sf_medium[13].getWidth("ms");
      float textTpsWidth = Fonts.sf_medium[13].getWidth("tps");
      float textXWidth = Fonts.sf_medium[13].getWidth("x");
      float textYWidth = Fonts.sf_medium[13].getWidth("y");
      float textZWidth = Fonts.sf_medium[13].getWidth("z");
      float textBpsWidth = Fonts.sf_medium[13].getWidth("bps");
      float userWidth = Fonts.sf_medium[14].getWidth(user);
      float iconAWidth = Fonts.skycore[14].getWidth("a");
      float iconBWidth = Fonts.skycore[14].getWidth("b");
      float iconFWidth = Fonts.skycore[14].getWidth("f");
      float iconGWidth = Fonts.skycore[14].getWidth("g");
      float iconHWidth = Fonts.skycore[14].getWidth("h");
      float iconIWidth = Fonts.skycore[14].getWidth("i");
      float iconJWidth = Fonts.skycore[14].getWidth("j");
      float skycoreTextWidth = Fonts.sf_medium[14].getWidth("SkyCore");
      float fpsWidth = Fonts.sf_medium[14].getWidth(fpsValue);
      float pingWidth = Fonts.sf_medium[14].getWidth(pingValue);
      float tpsWidth = Fonts.sf_medium[14].getWidth(tpsValue);
      float xWidth = Fonts.sf_medium[14].getWidth(xValue);
      float yWidth = Fonts.sf_medium[14].getWidth(yValue);
      float zWidth = Fonts.sf_medium[14].getWidth(zValue);
      float bpsWidth = Fonts.sf_medium[14].getWidth(bpsValue);
      float paddingLeft = 7.0F;
      float spacingAfterIconA = 4.0F;
      float spacingAfterText = 3.0F;
      float spacingAfterDot = 3.0F;
      float spacingAfterLabel = 1.5F;
      float spacingAfterValue = 3.0F;
      float paddingRight = 6.0F;
      float tpsTextGap = 1.2F;
      float rowHeight = 19.0F;
      float roundRadius = 6.5F;
      float gap = 3.0F;
      float topW = paddingLeft + iconAWidth + spacingAfterIconA + skycoreTextWidth + spacingAfterText + 3.0F + spacingAfterDot + iconBWidth + spacingAfterText + userWidth + spacingAfterText + 3.0F + spacingAfterDot + iconFWidth + spacingAfterText + fpsWidth + textFpsWidth + spacingAfterText + 3.0F + spacingAfterDot + iconGWidth + spacingAfterText + pingWidth + textMsWidth + spacingAfterText + 3.0F + spacingAfterDot + iconHWidth + spacingAfterText + tpsWidth + tpsTextGap + textTpsWidth + paddingRight;
      float bottomW = paddingLeft + iconJWidth + spacingAfterText + textXWidth + spacingAfterLabel + xWidth + spacingAfterValue + textYWidth + spacingAfterLabel + yWidth + spacingAfterValue + textZWidth + spacingAfterLabel + zWidth + spacingAfterValue + 3.0F + spacingAfterDot + iconIWidth + spacingAfterText + bpsWidth + textBpsWidth + paddingRight;
      float width = Math.max(topW, bottomW);
      this.dragging.setWidth(width);
      this.dragging.setHeight(rowHeight * 2.0F + gap);
      RenderUtil.drawBlurredRoundedRectangle(x, y, topW, rowHeight, roundRadius, bgColor, 1.0F);
      RenderUtil.drawBlurredRoundedRectangle(x, y + rowHeight + gap, bottomW, rowHeight, roundRadius, bgColor, 1.0F);
      float localX = x + paddingLeft;
      float dotY = y + rowHeight / 2.0F;
      float textYPos = y + (rowHeight - Fonts.sf_semibold[14].getHeight()) / 2.0F + 0.2F;
      float labelY = y + (rowHeight - Fonts.sf_semibold[13].getHeight()) / 2.0F + 0.25F;
      float iconY = y + (rowHeight - Fonts.skycore[14].getHeight()) / 2.0F + 0.35F;
      Fonts.skycore[14].drawString(ms, "a", (double)localX, (double)iconY, logoColor);
      localX += iconAWidth + spacingAfterIconA;
      Fonts.sf_semibold[14].drawString(ms, "StichDLC", (double)localX, (double)textYPos, whiteColor);
      localX += skycoreTextWidth + spacingAfterText;
      RenderUtil.drawCircle(localX + 1.7F, dotY + 0.6F, 0.0F, 360.0F, 1.2F, 0.8F, true, dotColor);
      localX += 3.0F + spacingAfterDot;
      Fonts.skycore[14].drawString(ms, "b", (double)localX, (double)iconY, logoColor);
      localX += iconBWidth + spacingAfterText;
      Fonts.sf_semibold[14].drawString(ms, user, (double)localX, (double)textYPos, whiteColor);
      localX += userWidth + spacingAfterText;
      RenderUtil.drawCircle(localX + 1.5F, dotY + 0.6F, 0.0F, 360.0F, 1.2F, 0.8F, true, dotColor);
      localX += 3.0F + spacingAfterDot;
      Fonts.skycore[14].drawString(ms, "f", (double)localX, (double)iconY, logoColor);
      localX += iconFWidth + spacingAfterText;
      Fonts.sf_semibold[14].drawString(ms, fpsValue, (double)localX, (double)textYPos, whiteColor);
      localX += fpsWidth;
      Fonts.sf_semibold[13].drawString(ms, "fps", (double)(localX - 0.1F), (double)(labelY + 0.35F), textColor);
      localX += textFpsWidth + spacingAfterText;
      RenderUtil.drawCircle(localX + 1.7F, dotY + 0.6F, 0.0F, 360.0F, 1.2F, 0.8F, true, dotColor);
      localX += 3.0F + spacingAfterDot;
      Fonts.skycore[14].drawString(ms, "g", (double)localX, (double)iconY, logoColor);
      localX += iconGWidth + spacingAfterText;
      Fonts.sf_semibold[14].drawString(ms, pingValue, (double)localX, (double)textYPos, whiteColor);
      localX += pingWidth;
      Fonts.sf_semibold[13].drawString(ms, "ms", (double)(localX - 0.1F), (double)(labelY + 0.35F), textColor);
      localX += textMsWidth + spacingAfterText;
      RenderUtil.drawCircle(localX + 1.7F, dotY + 0.6F, 0.0F, 360.0F, 1.2F, 0.8F, true, dotColor);
      localX += 3.0F + spacingAfterDot;
      Fonts.skycore[14].drawString(ms, "h", (double)localX, (double)iconY, logoColor);
      localX += iconHWidth + spacingAfterText;
      Fonts.sf_semibold[14].drawString(ms, tpsValue, (double)localX, (double)textYPos, whiteColor);
      localX += tpsWidth;
      Fonts.sf_semibold[13].drawString(ms, "tps", (double)(localX + tpsTextGap), (double)(labelY + 0.35F), textColor);
      float var76 = localX + textTpsWidth + spacingAfterText;
      float by = y + rowHeight + gap;
      localX = x + paddingLeft;
      dotY = by + rowHeight / 2.0F;
      textYPos = by + (rowHeight - Fonts.sf_semibold[14].getHeight()) / 2.0F + 0.2F;
      labelY = by + (rowHeight - Fonts.sf_semibold[13].getHeight()) / 2.0F + 0.25F;
      iconY = by + (rowHeight - Fonts.skycore[14].getHeight()) / 2.0F + 0.35F;
      Fonts.skycore[14].drawString(ms, "j", (double)localX, (double)iconY, logoColor);
      localX += iconJWidth + spacingAfterText;
      Fonts.sf_semibold[13].drawString(ms, "x", (double)localX, (double)labelY, textColor);
      localX += textXWidth + spacingAfterLabel;
      Fonts.sf_semibold[14].drawString(ms, xValue, (double)localX, (double)textYPos, whiteColor);
      localX += xWidth + spacingAfterValue;
      Fonts.sf_semibold[13].drawString(ms, "y", (double)(localX - 0.9F), (double)labelY, textColor);
      localX += textYWidth + spacingAfterLabel;
      Fonts.sf_semibold[14].drawString(ms, yValue, (double)(localX - 1.0F), (double)textYPos, whiteColor);
      localX += yWidth + spacingAfterValue;
      Fonts.sf_semibold[13].drawString(ms, "z", (double)(localX - 1.8F), (double)labelY, textColor);
      localX += textZWidth + spacingAfterLabel;
      Fonts.sf_semibold[14].drawString(ms, zValue, (double)(localX - 2.0F), (double)textYPos, whiteColor);
      localX += zWidth + spacingAfterValue;
      RenderUtil.drawCircle(localX - 0.1F, dotY + 0.6F, 0.0F, 360.0F, 1.2F, 0.8F, true, dotColor);
      localX += 3.0F + spacingAfterDot;
      Fonts.skycore[14].drawString(ms, "i", (double)(localX - 2.0F), (double)iconY, logoColor);
      localX += iconIWidth + spacingAfterText;
      Fonts.sf_semibold[14].drawString(ms, bpsValue, (double)(localX - 2.0F), (double)textYPos, whiteColor);
      localX += bpsWidth;
      Fonts.sf_semibold[13].drawString(ms, "bps", (double)(localX - 1.8F), (double)(labelY + 0.35F), textColor);
   }
}
