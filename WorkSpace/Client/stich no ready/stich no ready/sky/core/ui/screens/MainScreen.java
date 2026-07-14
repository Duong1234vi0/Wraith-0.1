package sky.core.ui.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorldSelectionScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.GradientUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class MainScreen extends Screen {
   private final List menuButtons = new ArrayList();
   private static final float BUTTON_WIDTH = 140.0F;
   private static final float BUTTON_HEIGHT = 23.5F;
   private static final float SPACING = 4.5F;

   public MainScreen() {
      super(NarratorChatListener.EMPTY);
   }

   protected void init() {
      super.init();
      this.menuButtons.clear();
      Vector2f center = new Vector2f((float)((int)((float)Wrapper.mc.getMainWindow().getScaledWidth() / 2.0F)), (float)((int)((float)Wrapper.mc.getMainWindow().getScaledHeight() / 2.0F)));
      float startY = center.getY() - 53.75F;
      this.addButton("Одиночная игра", center.getX(), startY, 140.0F, true, () -> {
         assert this.minecraft != null;

         this.minecraft.displayGuiScreen(new WorldSelectionScreen(this));
      });
      this.addButton("Сетевая игра", center.getX(), startY + 23.5F + 4.5F, 140.0F, false, () -> {
         assert this.minecraft != null;

         Screen screen = this.minecraft.gameSettings.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
         this.minecraft.displayGuiScreen((Screen)screen);
      });
      this.addButton("Аккаунты", center.getX(), startY + 56.0F, 140.0F, false, () -> {
         assert this.minecraft != null;

         this.minecraft.displayGuiScreen(new AccountsScreen());
      });
      this.addButton("Настройки", center.getX(), startY + 84.0F, 140.0F, false, () -> {
         assert this.minecraft != null;

         this.minecraft.displayGuiScreen(new OptionsScreen(this, this.minecraft.gameSettings));
      });

      assert this.minecraft != null;

      float var10002 = center.getX();
      float var10003 = startY + 134.0F;
      Minecraft var10006 = this.minecraft;
      Objects.requireNonNull(var10006);
      this.addButton("Выйти", var10002, var10003, 140.0F, false, var10006::shutdown);
   }

   private void addButton(String text, float x, float y, float width, boolean showTitle, Runnable action) {
      MainMenuButton button = new MainMenuButton((int)(x - width / 2.0F), (int)y, (int)width, 23, new StringTextComponent(text), showTitle, (btn) -> {
         action.run();
      });
      this.menuButtons.add(button);
      this.buttons.add(button);
   }

   public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
      int baseScale = (int)Wrapper.mc.getMainWindow().getGuiScaleFactor();
      Wrapper.mc.gameRenderer.setupOverlayRendering(2.0F);
      float factor = (float)baseScale / 2.0F;
      mouseX = (int)((float)mouseX * factor);
      mouseY = (int)((float)mouseY * factor);
      int windowWidth = Wrapper.mc.getMainWindow().getScaledWidth();
      int windowHeight = Wrapper.mc.getMainWindow().getScaledHeight();
      float centerY = (float)windowHeight / 2.0F;
      float startY = centerY - 53.75F;

      int i;
      float by;
      for(i = 0; i < this.menuButtons.size(); ++i) {
         MainMenuButton b = (MainMenuButton)this.menuButtons.get(i);
         by = startY + (float)i * 28.0F;
         if (i == 4) {
            by = startY + 134.0F;
         }

         b.y = (int)by;
      }

      RenderUtil.drawRectangle(0.0F, 0.0F, (float)windowWidth, (float)windowHeight, ColorUtil.getColor(13, 17, 23, 255));
      this.drawDotGrid(windowWidth, windowHeight);
      i = ThemeEditor.getColor(ThemeSettings.LOGO);
      this.drawRadialGlow((float)windowWidth / 2.0F, (float)windowHeight * 0.1F, (float)windowWidth * 0.55F, i);
      float normMouseX = (float)mouseX / (float)windowWidth * 2.0F - 1.0F;
      by = (float)mouseY / (float)windowHeight * 2.0F - 1.0F;
      float offsetYBackground = by * 3.0F;
      float maxParalax = 2.0F;
      int renderWidth = (int)((float)windowWidth * 1.05F);
      int renderHeight = (int)((float)windowHeight * 1.05F);
      float maxOffsetX = (float)(renderWidth - windowWidth) / 2.0F;
      float maxOffsetY = (float)(renderHeight - windowHeight) / 2.0F;
      if (windowWidth >= 800) {
         float offsetXOutline = normMouseX * maxParalax;
         float outlineWidth = 336.0F;
         float outlineHeight = 480.0F;
         offsetXOutline = MathHelper.clamp(offsetXOutline, -maxOffsetX, maxOffsetX);
         float outlineY = centerY - outlineHeight / 2.0F - 13.5F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/icon_outline.png"), offsetXOutline - 45.0F, outlineY + offsetYBackground, outlineWidth, outlineHeight, -1);
         float offsetXIcon = normMouseX * maxParalax;
         float iconWidth = 457.0F;
         float iconHeight = 515.0F;
         offsetXIcon = MathHelper.clamp(offsetXIcon, -maxOffsetX, maxOffsetX);
         float iconY = centerY - iconHeight / 2.0F + 24.0F;
         RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/icon.png"), offsetXIcon - 10.0F, iconY + offsetYBackground, iconWidth, iconHeight, -1);
         Fonts.sf_regular[13].drawString(matrices, "Авторское право ©Peduj 2025", (double)(((float)Wrapper.mc.getMainWindow().getScaledWidth() - Fonts.sf_regular[13].getWidth("Авторское право @g 2025")) / 2.0F), (double)((float)Wrapper.mc.getMainWindow().getScaledHeight() - Fonts.sf_regular[13].getHeight() - 3.0F), ColorUtil.getColor(255, 255, 255, 80));
      }

      super.render(matrices, mouseX, mouseY, delta);
      Wrapper.mc.gameRenderer.setupOverlayRendering();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      int baseScale = (int)Wrapper.mc.getMainWindow().getGuiScaleFactor();
      double factor = (double)baseScale / 2.0;
      double sx = mouseX * factor;
      double sy = mouseY * factor;
      boolean handled = false;
      Iterator var14 = this.menuButtons.iterator();

      while(var14.hasNext()) {
         MainMenuButton widget = (MainMenuButton)var14.next();
         if (widget.mouseClicked(sx, sy, button)) {
            handled = true;
         }
      }

      return handled || super.mouseClicked(sx, sy, button);
   }

   private void drawDotGrid(int width, int height) {
      int dotSpacing = 18;
      int dotRadius = 1;
      int dotColor = ColorUtil.getColor(255, 255, 255, 28);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableTexture();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      float r = (float)(dotColor >> 16 & 255) / 255.0F;
      float g = (float)(dotColor >> 8 & 255) / 255.0F;
      float b = (float)(dotColor & 255) / 255.0F;
      float a = (float)(dotColor >> 24 & 255) / 255.0F;

      for(int x = dotSpacing; x < width; x += dotSpacing) {
         for(int y = dotSpacing; y < height; y += dotSpacing) {
            buffer.pos((double)(x - dotRadius), (double)(y - dotRadius), 0.0).color(r, g, b, a).endVertex();
            buffer.pos((double)(x - dotRadius), (double)(y + dotRadius), 0.0).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + dotRadius), (double)(y + dotRadius), 0.0).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + dotRadius), (double)(y - dotRadius), 0.0).color(r, g, b, a).endVertex();
         }
      }

      tessellator.draw();
      RenderSystem.enableTexture();
      RenderSystem.disableBlend();
   }

   private void drawRadialGlow(float cx, float cy, float radius, int themeColor) {
      int tr = themeColor >> 16 & 255;
      int tg = themeColor >> 8 & 255;
      int tb = themeColor & 255;
      int steps = 64;
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableTexture();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
      buffer.pos((double)cx, (double)cy, 0.0).color((float)tr / 255.0F, (float)tg / 255.0F, (float)tb / 255.0F, 0.18F).endVertex();

      for(int i = 0; i <= steps; ++i) {
         double angle = 6.283185307179586 * (double)i / (double)steps;
         float ex = cx + (float)Math.cos(angle) * radius;
         float ey = cy + (float)Math.sin(angle) * radius * 0.55F;
         buffer.pos((double)ex, (double)ey, 0.0).color((float)tr / 255.0F, (float)tg / 255.0F, (float)tb / 255.0F, 0.0F).endVertex();
      }

      tessellator.draw();
      RenderSystem.enableTexture();
      RenderSystem.disableBlend();
   }

   public static class MainMenuButton extends Button {
      private final boolean showTitle;

      public MainMenuButton(int x, int y, int width, int height, StringTextComponent title, boolean showTitle, Button.IPressable pressedAction) {
         super(x, y, width, height, title, pressedAction);
         this.showTitle = showTitle;
      }

      public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
         float buttonWidth = 139.0F;
         float buttonY = (float)this.y + 5.5F;
         float buttonX;
         if (Wrapper.mc.getMainWindow().getScaledWidth() < 800) {
            buttonX = ((float)Wrapper.mc.getMainWindow().getScaledWidth() - buttonWidth) / 2.0F;
         } else {
            float rightMargin = 74.0F;
            buttonX = (float)Wrapper.mc.getMainWindow().getScaledWidth() - buttonWidth - rightMargin;
         }

         boolean hovered = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + 25.0F;
         RenderUtil.drawRoundedRectangleGradient(buttonX, (float)this.y + 5.5F, buttonWidth, 25.0F, 5.0F, ColorUtil.getColor(9, 9, 9), ColorUtil.getColor(8, 8, 8), ColorUtil.getColor(31, 31, 31), ColorUtil.getColor(12, 12, 12), 1.0F);
         String text = this.getMessage().getString();
         float textWidth = Fonts.sf_regular[26].getWidth(text);
         float textHeight = Fonts.sf_regular[26].getHeight();
         float textX = buttonX + (buttonWidth - textWidth) / 2.0F;
         float textY = (float)(this.y + 6) + (25.0F - textHeight) / 2.0F;
         int textColor = hovered ? ColorUtil.getColor(255, 255, 255, 200) : ColorUtil.getColor(255, 255, 255, 130);
         Fonts.sf_regular[26].drawString(matrixStack, text, (double)textX, (double)(textY - 2.0F), textColor);
         if (this.showTitle) {
            ITextComponent gradientLogo = GradientUtil.gradient("SkyCore", ColorUtil.getColor(0, 0, 255), ColorUtil.getColor(0, 200, 200), 5, 45.0F);
            ITextComponent gradientClient = GradientUtil.gradient("Client", ColorUtil.getColor(0, 0, 255), ColorUtil.getColor(0, 200, 200), 5, 45.0F);
            float titleX = buttonX + (buttonWidth - Fonts.sf_semibold[36].getWidth("SkyCore")) / 2.0F;
            Fonts.sf_semibold[36].drawText(matrixStack, gradientLogo, (double)titleX, (double)(this.y - 40), -1);
            Fonts.sf_semibold[36].drawText(matrixStack, gradientClient, (double)(titleX + 4.5F), (double)((float)this.y - 24.5F), -1);
         }

      }

      public boolean mouseClicked(double mouseX, double mouseY, int button) {
         float buttonWidth = 139.0F;
         float rightMargin = 74.0F;
         float scaledWidthAt2 = (float)((double)Wrapper.mc.getMainWindow().getFramebufferWidth() / 2.0);
         float buttonX;
         if (scaledWidthAt2 < 800.0F) {
            buttonX = (scaledWidthAt2 - buttonWidth) / 2.0F;
         } else {
            buttonX = scaledWidthAt2 - buttonWidth - rightMargin;
         }

         boolean isWithinBounds = mouseX >= (double)buttonX && mouseX < (double)(buttonX + buttonWidth) && mouseY >= (double)((float)this.y + 5.5F) && mouseY < (double)((float)this.y + 5.5F + 25.0F);
         if (isWithinBounds && this.active && this.visible) {
            this.onClick(mouseX, mouseY);
            return true;
         } else {
            return false;
         }
      }

      public boolean isShowTitle() {
         return this.showTitle;
      }
   }
}
