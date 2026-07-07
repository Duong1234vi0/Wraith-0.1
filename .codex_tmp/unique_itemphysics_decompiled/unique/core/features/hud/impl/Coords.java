package unique.core.features.hud.impl;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_124;
import net.minecraft.class_332;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

public class Coords extends HudElement {
   private final Setting<Coords.NetherCoords> netherCoords = new Setting<>("NetherCoords", Coords.NetherCoords.On);

   public Coords() {
      super("Coords", 100, 10);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      int posX = (int)mc.field_1724.method_23317();
      int posY = (int)mc.field_1724.method_23318();
      int posZ = (int)mc.field_1724.method_23321();
      float nether = !PlayerUtility.isInHell() ? 0.125F : 8.0F;
      int hposX = (int)(mc.field_1724.method_23317() * nether);
      int hposZ = (int)(mc.field_1724.method_23321() * nether);
      String coordinates = "XYZ "
         + class_124.field_1068
         + posX
         + " "
         + posY
         + " "
         + posZ
         + class_124.field_1068
         + (
            !this.netherCoords.is(Coords.NetherCoords.On) && (!this.netherCoords.is(Coords.NetherCoords.OnlyNether) || PlayerUtility.isInHell())
               ? ""
               : " [" + class_124.field_1070 + hposX + " " + hposZ + class_124.field_1068 + "]"
         );
      float pX = this.getPosX() > mc.method_22683().method_4486() / 2.0F
         ? this.getPosX() - FontRenderers.getModulesRenderer().getStringWidth(coordinates)
         : this.getPosX();
      if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
         Render2DEngine.drawRoundedBlur(
            context.method_51448(),
            pX,
            this.getPosY(),
            FontRenderers.getModulesRenderer().getStringWidth(coordinates) + 21.0F,
            13.0F,
            3.0F,
            HudEditor.blurColor.getValue().getColorObject()
         );
         Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, this.getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
         Render2DEngine.setupRender();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.setShaderTexture(0, TextureStorage.coordsIcon);
         Render2DEngine.renderGradientTexture(
            context.method_51448(),
            pX + 2.0F,
            this.getPosY() + 1.0F,
            10.0,
            10.0,
            0.0F,
            0.0F,
            512.0,
            512.0,
            512.0,
            512.0,
            HudEditor.getColor(270),
            HudEditor.getColor(0),
            HudEditor.getColor(180),
            HudEditor.getColor(90)
         );
         Render2DEngine.endRender();
      }

      FontRenderers.getModulesRenderer().drawString(context.method_51448(), coordinates, pX + 18.0F, this.getPosY() + 5.0F, HudEditor.getColor(1).getRGB());
      this.setBounds(pX, this.getPosY(), FontRenderers.getModulesRenderer().getStringWidth(coordinates) + 21.0F, 13.0F);
   }

   private static enum NetherCoords {
      Off,
      On,
      OnlyNether;
   }
}
