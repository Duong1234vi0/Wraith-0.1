package sky.core.ui.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class ResourceLoadingScreen {
   private static final ResourceLocation MENU_BACKGROUND = new ResourceLocation("minecraft", "SkyCore/mainmenu/background.png");
   private static final ResourceLocation MENU_LOGO = new ResourceLocation("");

   public static void render(Minecraft mc, MatrixStack matrixStack, int screenWidth, int screenHeight, float alpha, float progress, float displayProgress) {
      int alpha255 = MathHelper.ceil(alpha * 255.0F);
      GlStateManager.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      fillBackground(mc, matrixStack, screenWidth, screenHeight, alpha);
      float barMarginX = (float)screenWidth * 0.03F;
      float barW = (float)screenWidth - barMarginX * 2.0F;
      float barH = 3.5F;
      float barX = barMarginX;
      float barY = (float)screenHeight * 0.98F;
      String loadingMessage = getLoadingMessage(progress);
      String progText = MathHelper.floor(displayProgress * 100.0F) + "%";
      int textGray = ColorUtil.getColor(103, 106, 114, (int)(0.85F * (float)alpha255));

      try {
         if (Fonts.sf_regular != null && Fonts.sf_regular[13] != null) {
            float textY = barY - Fonts.sf_regular[13].getHeight() - 6.0F;
            Fonts.sf_regular[13].drawString(matrixStack, loadingMessage, (double)barX, (double)textY, textGray);
            float progW = Fonts.sf_regular[13].getWidth(progText);
            Fonts.sf_regular[13].drawString(matrixStack, progText, (double)(barX + barW - progW), (double)textY, textGray);
         }
      } catch (Throwable var18) {
      }

      drawProgressBar(barMarginX, barY, barW, barH, progress, alpha);
   }

   private static void fillBackground(Minecraft mc, MatrixStack ms, int w, int h, float alpha) {
      int bgFill = (int)(255.0F * alpha) << 24 | 1381400;
      AbstractGui.fill(ms, 0, 0, w, h, bgFill);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
      mc.getTextureManager().bindTexture(MENU_BACKGROUND);
      AbstractGui.blit(ms, 0, 0, 0.0F, 0.0F, w, h, w, h);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private static void drawProgressBar(float barX, float barY, float barW, float barH, float progress, float alpha) {
      float clamped = MathHelper.clamp(progress, 0.0F, 1.0F);
      float radius = barH * 0.5F;
      int trackColor = ColorUtil.getColor(255, 255, 255, (int)(10.0F * alpha));
      RenderUtil.drawRoundedRectangle(barX, barY, barW, barH, radius, trackColor);
      float fillW = barW * clamped;
      if (!(fillW <= 0.5F)) {
         long time = Util.milliTime();
         float wave = (float)(Math.sin((double)time / 380.0) * 0.5 + 0.5);
         float wave2 = (float)(Math.sin((double)time / 520.0 + 1.4) * 0.5 + 0.5);
         int blueDark = ColorUtil.getColor((int)(40.0F + 30.0F * wave), (int)(100.0F + 40.0F * wave2), (int)(220.0F + 25.0F * wave), 255);
         int blueMid = ColorUtil.getColor((int)(90.0F + 40.0F * wave2), (int)(150.0F + 35.0F * wave), 255, 255);
         int blueBright = ColorUtil.getColor((int)(154.0F + 50.0F * wave), (int)(192.0F + 30.0F * wave2), 255, 255);
         float barAlpha = alpha * (0.75F + 0.25F * wave);
         RenderUtil.drawRoundedRectangleGradient(barX, barY, fillW, barH, radius, blueDark, blueMid, blueBright, blueMid, barAlpha);
         float shineW = Math.min(fillW * 0.35F, 80.0F);
         float shinePhase = (float)(time % 2400L) / 2400.0F;
         float shineX = barX + (fillW + shineW) * shinePhase - shineW;
         shineX = MathHelper.clamp(shineX, barX, barX + fillW - shineW);
         if (shineW > 2.0F && shineX + shineW <= barX + fillW) {
            int shineColor = ColorUtil.getColor(255, 255, 255, (int)(90.0F * alpha * (0.35F + 0.65F * wave)));
            RenderUtil.drawRoundedRectangleGradient(shineX, barY, shineW, barH, radius, ColorUtil.applyOpacity(shineColor, 0), shineColor, ColorUtil.applyOpacity(shineColor, 0), shineColor, barAlpha * 0.85F);
         }

      }
   }

   private static String getLoadingMessage(float progress) {
      if (progress < 0.2F) {
         return "Loading libraries...";
      } else if (progress < 0.6F) {
         return "Loading native...";
      } else if (progress < 0.8F) {
         return "Loading assets...";
      } else {
         return progress < 0.96F ? "Loading configurations..." : "Finalizing resources...";
      }
   }
}
