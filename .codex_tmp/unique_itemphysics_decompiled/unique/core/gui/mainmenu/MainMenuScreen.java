package unique.core.gui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_1074;
import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_429;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import net.minecraft.class_526;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

public class MainMenuScreen extends class_437 {
   private final List<MainMenuButton> buttons = new ArrayList<>();
   private final List<BackgroundSnowParticle> snowParticles = new ArrayList<>();
   private final List<MouseTrailParticle> mouseParticles = new ArrayList<>();
   public boolean confirm = false;
   public static int ticksActive;
   private static MainMenuScreen INSTANCE = new MainMenuScreen();
   private int lastWidth = 0;
   public static boolean snowEnabled = false;

   protected MainMenuScreen() {
      super(class_2561.method_30163("THMainMenuScreen"));
      INSTANCE = this;
      int w = Module.mc.method_22683().method_4486();
      int h = Module.mc.method_22683().method_4502();
      if (this.snowParticles.isEmpty()) {
         for (int i = 0; i < 180; i++) {
            this.snowParticles.add(new BackgroundSnowParticle((float)(Math.random() * w), (float)(Math.random() * h)));
         }
      }

      this.buttons
         .add(
            new MainMenuButton(
               -110.0F,
               -70.0F,
               class_1074.method_4662("menu.singleplayer", new Object[0]).toUpperCase(Locale.ROOT),
               () -> Module.mc.method_1507(new class_526(this))
            )
         );
      this.buttons
         .add(
            new MainMenuButton(
               4.0F,
               -70.0F,
               class_1074.method_4662("menu.multiplayer", new Object[0]).toUpperCase(Locale.ROOT),
               () -> Module.mc.method_1507(new class_500(this))
            )
         );
      this.buttons
         .add(
            new MainMenuButton(
               -110.0F,
               -29.0F,
               class_1074.method_4662("menu.options", new Object[0]).toUpperCase(Locale.ROOT).replace(".", ""),
               () -> Module.mc.method_1507(new class_429(this, Module.mc.field_1690))
            )
         );
      this.buttons.add(new MainMenuButton(4.0F, -29.0F, "CLICKGUI", () -> ModuleManager.clickGui.setGui()));
      this.buttons
         .add(new MainMenuButton(-110.0F, 12.0F, class_1074.method_4662("menu.quit", new Object[0]).toUpperCase(Locale.ROOT), Module.mc::method_1592, true));
   }

   public static MainMenuScreen getInstance() {
      ticksActive = 0;
      if (INSTANCE == null) {
         INSTANCE = new MainMenuScreen();
      }

      return INSTANCE;
   }

   public void method_25393() {
      ticksActive++;
      if (ticksActive > 400) {
         ticksActive = 0;
      }
   }

   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
      int screenW = Module.mc.method_22683().method_4486();
      int screenH = Module.mc.method_22683().method_4502();
      float halfOfWidth = screenW / 2.0F;
      float halfOfHeight = screenH / 2.0F;
      if (screenW != this.lastWidth) {
         for (BackgroundSnowParticle p : this.snowParticles) {
            p.x = (float)(Math.random() * screenW);
            if (this.lastWidth == 0) {
               p.y = (float)(Math.random() * screenH);
            }
         }

         this.lastWidth = screenW;
      }

      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      context.method_25290(TextureStorage.backmenu, 0, 0, 0.0F, 0.0F, screenW, screenH, screenW, screenH);
      if (snowEnabled) {
         RenderSystem.enableBlend();

         for (BackgroundSnowParticle px : this.snowParticles) {
            px.update(screenW, screenH);
            px.render(context);
         }

         RenderSystem.disableBlend();
      }

      for (int i = 0; i < 2; i++) {
         this.mouseParticles.add(new MouseTrailParticle(mouseX, mouseY));
      }

      this.mouseParticles.removeIf(px -> {
         px.update();
         px.render(context);
         return px.isDead();
      });
      RenderSystem.disableBlend();
      this.buttons.forEach(b -> b.onRender(context, mouseX, mouseY));
      boolean hoveredLogo = Render2DEngine.isHovered(mouseX, mouseY, (int)(halfOfWidth - 120.0F), (int)(halfOfHeight - 130.0F), 210.0, 50.0);
      FontRenderers.thglitchBig
         .drawCenteredString(
            context.method_51448(), "PTK CORE", (int)halfOfWidth, (int)(halfOfHeight - 120.0F), new Color(255, 255, 255, hoveredLogo ? 230 : 180).getRGB()
         );
      Render2DEngine.drawHudBase(
         context.method_51448(),
         screenW - 40,
         screenH - 40,
         30.0F,
         30.0F,
         5.0F,
         Render2DEngine.isHovered(mouseX, mouseY, screenW - 40, screenH - 40, 30.0, 30.0) ? 0.7F : 1.0F
      );
      context.method_25293(TextureStorage.thTeam, screenW - 40, screenH - 40, 30, 30, 0.0F, 0.0F, 30, 30, 30, 30);
      Render2DEngine.drawHudBase(
         context.method_51448(),
         screenW - 80,
         screenH - 40,
         30.0F,
         30.0F,
         5.0F,
         Render2DEngine.isHovered(mouseX, mouseY, screenW - 80, screenH - 40, 30.0, 30.0) ? 0.7F : 1.0F
      );
      context.method_25293(TextureStorage.donation, screenW - 79, screenH - 39, 28, 28, 0.0F, 0.0F, 30, 30, 30, 30);
      int boxW = 100;
      int boxH = 17;
      int boxX = screenW - boxW - 10;
      int boxY = 5;
      boolean hoverSnow = Render2DEngine.isHovered(mouseX, mouseY, boxX, boxY, boxW, boxH);
      Color bgColor = new Color(0, 0, 0, hoverSnow ? 160 : 120);
      Render2DEngine.drawRect(context.method_51448(), boxX, boxY, boxW, boxH, bgColor);
      String text = "SNOW : " + (snowEnabled ? "ON" : "OFF");
      int textColor = snowEnabled ? new Color(0, 255, 0, 255).getRGB() : new Color(255, 255, 255, 255).getRGB();
      FontRenderers.sf_medium.drawCenteredString(context.method_51448(), text, boxX + boxW / 2.0F, boxY + 6, textColor);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
      float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
      this.buttons.forEach(b -> b.onClick((int)mouseX, (int)mouseY));
      if (Render2DEngine.isHovered(mouseX, mouseY, halfOfWidth - 50.0F, halfOfHeight + 70.0F, 100.0, 10.0)) {
         Module.mc.method_1507(new class_442());
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, Module.mc.method_22683().method_4486() - 40, Module.mc.method_22683().method_4502() - 40, 40.0, 40.0)) {
         Module.mc.method_1507(CreditsScreen.getInstance());
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, Module.mc.method_22683().method_4486() - 90, Module.mc.method_22683().method_4502() - 40, 40.0, 40.0)) {
         class_156.method_668().method_673(URI.create("https://discord.gg/AEyetataPB"));
      }

      if (Render2DEngine.isHovered(mouseX, mouseY, (int)(halfOfWidth - 157.0F), (int)(halfOfHeight - 140.0F), 300.0, 70.0)) {
         class_156.method_668().method_673(URI.create("https://www.youtube.com/@AkiraEternal/"));
      }

      int boxW = 100;
      int boxH = 17;
      int boxX = Module.mc.method_22683().method_4486() - boxW - 10;
      int boxY = 5;
      if (Render2DEngine.isHovered(mouseX, mouseY, boxX, boxY, boxW, boxH)) {
         snowEnabled = !snowEnabled;
      }

      return super.method_25402(mouseX, mouseY, button);
   }
}
