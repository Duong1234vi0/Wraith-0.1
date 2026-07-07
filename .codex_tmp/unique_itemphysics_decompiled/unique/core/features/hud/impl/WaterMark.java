package unique.core.features.hud.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_332;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.misc.NameProtect;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

public class WaterMark extends HudElement {
   public WaterMark() {
      super("WaterMark", 100, 15);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      String username = NameProtect.getCustomName(mc.method_1548().method_1676());
      String fpsText = mc.method_47599() + " FPS";
      String serverText = !mc.method_1542() && mc.method_1562() != null && mc.method_1562().method_45734() != null
         ? mc.method_1562().method_45734().field_3761
         : "SinglePlayer";
      String versionText = "Last";
      String brandText = "PTK Recode";
      float height = 15.0F;
      float x = this.getPosX();
      float y = this.getPosY();
      float brandTextWidth = FontRenderers.sf_bold.getStringWidth(brandText);
      float ptkBoxWidth = 16.0F + brandTextWidth + 6.0F;
      float nameWidth = FontRenderers.sf_bold.getStringWidth(username);
      float fpsWidth = FontRenderers.sf_bold.getStringWidth(fpsText);
      float serverWidth = FontRenderers.sf_bold.getStringWidth(serverText);
      float infoBoxWidth = 17.0F + nameWidth + 6.0F + 11.0F + 2.0F + fpsWidth + 6.0F + 11.0F + 2.0F + serverWidth + 6.0F;
      float versionWidth = FontRenderers.sf_bold.getStringWidth(versionText);
      float versionBoxWidth = versionWidth + 8.0F;
      float infoBoxX = x + ptkBoxWidth + 2.0F;
      float versionBoxX = infoBoxX + infoBoxWidth + 2.0F;
      Render2DEngine.drawRoundedBlur(context.method_51448(), x, y, ptkBoxWidth, height, 3.0F, HudEditor.blurColor.getValue().getColorObject());
      Render2DEngine.drawRoundedBlur(context.method_51448(), infoBoxX, y, infoBoxWidth, height, 3.0F, HudEditor.blurColor.getValue().getColorObject());
      Render2DEngine.drawRoundedBlur(context.method_51448(), versionBoxX, y, versionBoxWidth, height, 3.0F, HudEditor.blurColor.getValue().getColorObject());
      Render2DEngine.setupRender();
      RenderSystem.setShaderTexture(0, TextureStorage.miniLogo);
      Render2DEngine.renderGradientTexture(
         context.method_51448(),
         x + 3.0F,
         y + 2.0F,
         11.0,
         11.0,
         0.0F,
         0.0F,
         128.0,
         128.0,
         128.0,
         128.0,
         HudEditor.getColor(270),
         HudEditor.getColor(0),
         HudEditor.getColor(180),
         HudEditor.getColor(90)
      );
      FontRenderers.sf_bold.drawGradientString(context.method_51448(), brandText, x + 16.0F, y + 5.0F, 20);
      float currentX = infoBoxX + 6.0F;
      RenderSystem.setShaderTexture(0, TextureStorage.playerIcon);
      Render2DEngine.renderGradientTexture(
         context.method_51448(),
         currentX,
         y + 3.0F,
         9.0,
         9.0,
         0.0F,
         0.0F,
         128.0,
         128.0,
         128.0,
         128.0,
         HudEditor.getColor(0),
         HudEditor.getColor(90),
         HudEditor.getColor(180),
         HudEditor.getColor(270)
      );
      FontRenderers.sf_bold.drawString(context.method_51448(), username, currentX + 11.0F, y + 5.0F, HudEditor.textColor.getValue().getColor());
      currentX += 11.0F + nameWidth + 6.0F;
      RenderSystem.setShaderTexture(0, TextureStorage.fpsIcon);
      Render2DEngine.renderGradientTexture(
         context.method_51448(),
         currentX,
         y + 2.0F,
         11.0,
         11.0,
         0.0F,
         0.0F,
         128.0,
         128.0,
         128.0,
         128.0,
         HudEditor.getColor(0),
         HudEditor.getColor(90),
         HudEditor.getColor(180),
         HudEditor.getColor(270)
      );
      FontRenderers.sf_bold.drawString(context.method_51448(), fpsText, currentX + 13.0F, y + 5.0F, HudEditor.textColor.getValue().getColor());
      currentX += 13.0F + fpsWidth + 6.0F;
      RenderSystem.setShaderTexture(0, TextureStorage.serverIcon);
      Render2DEngine.renderGradientTexture(
         context.method_51448(),
         currentX,
         y + 2.0F,
         11.0,
         11.0,
         0.0F,
         0.0F,
         128.0,
         128.0,
         128.0,
         128.0,
         HudEditor.getColor(0),
         HudEditor.getColor(90),
         HudEditor.getColor(180),
         HudEditor.getColor(270)
      );
      FontRenderers.sf_bold.drawString(context.method_51448(), serverText, currentX + 13.0F, y + 5.0F, HudEditor.textColor.getValue().getColor());
      FontRenderers.sf_bold.drawGradientString(context.method_51448(), versionText, versionBoxX + 4.0F, y + 5.0F, 10);
      Render2DEngine.endRender();
      this.setBounds(x, y, ptkBoxWidth + 2.0F + infoBoxWidth + 2.0F + versionBoxWidth, height);
   }

   @Override
   public void onUpdate() {
   }
}
