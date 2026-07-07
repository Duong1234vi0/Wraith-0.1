package unique.core.features.hud.impl;

import java.awt.Color;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_332;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.render.NameTags;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class Radar extends HudElement {
   private final Setting<Radar.Mode> mode = new Setting<>("Mode", Radar.Mode.Rect);
   private final Setting<Radar.ColorMode> colorMode = new Setting<>("ColorMode", Radar.ColorMode.Sync);
   private final Setting<Integer> size = new Setting<>("Size", 80, 20, 300);
   private final Setting<ColorSetting> color2 = new Setting<>("Color", new ColorSetting(-15724528));
   private final Setting<ColorSetting> color3 = new Setting<>("PlayerColor", new ColorSetting(-979657829));
   private final Setting<Radar.Component> c1 = new Setting<>("Component1", Radar.Component.Name, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<Radar.Component> c2 = new Setting<>("Component2", Radar.Component.Hp, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<Radar.Component> c3 = new Setting<>("Component3", Radar.Component.Ping, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<Radar.Component> c4 = new Setting<>("Component4", Radar.Component.None, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<Radar.Component> c5 = new Setting<>("Component5", Radar.Component.None, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<class_124> c12 = new Setting<>("Color1", class_124.field_1068, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<class_124> c22 = new Setting<>("Color2", class_124.field_1068, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<class_124> c32 = new Setting<>("Color3", class_124.field_1068, v -> this.mode.is(Radar.Mode.Text));
   private final Setting<class_124> c42 = new Setting<>("Color4", class_124.field_1068, v -> this.mode.is(Radar.Mode.Text));

   public Radar() {
      super("Radar", 100, 100);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      if (this.mode.getValue() == Radar.Mode.Rect) {
         Render2DEngine.drawHudBase(
            context.method_51448(),
            this.getPosX(),
            this.getPosY(),
            this.size.getValue().intValue(),
            this.size.getValue().intValue(),
            HudEditor.hudRound.getValue()
         );
         if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
            Render2DEngine.drawRectDumbWay(
               context.method_51448(),
               this.getPosX(),
               this.getPosY() + (this.size.getValue().intValue() / 2.0F + 0.25F),
               this.getPosX() + this.size.getValue().intValue(),
               this.getPosY() + this.size.getValue().intValue() / 2.0F - 0.25F,
               new Color(1426063359, true)
            );
            Render2DEngine.drawRectDumbWay(
               context.method_51448(),
               this.getPosX() + (this.size.getValue().intValue() / 2.0F - 0.25F),
               this.getPosY() - 0.5F,
               this.getPosX() + this.size.getValue().intValue() / 2.0F + 0.25F,
               this.getPosY() + this.size.getValue().intValue() - 1.0F,
               new Color(1426063359, true)
            );
         } else {
            Render2DEngine.draw2DGradientRect(
               context.method_51448(),
               (float)(this.getPosX() + (this.size.getValue().intValue() / 2.0F - 0.5)),
               (float)(this.getPosY() + 3.5),
               (float)(this.getPosX() + (this.size.getValue().intValue() / 2.0F + 0.2)),
               (float)(this.getPosY() + this.size.getValue().intValue() - 3.5),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject()
            );
            Render2DEngine.draw2DGradientRect(
               context.method_51448(),
               this.getPosX() + 3.5F,
               this.getPosY() + (this.size.getValue().intValue() / 2.0F - 0.2F),
               this.getPosX() + this.size.getValue().intValue() - 3.5F,
               this.getPosY() + (this.size.getValue().intValue() / 2.0F + 0.5F),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject(),
               this.color2.getValue().getColorObject()
            );
         }

         for (class_1657 entityPlayer : Managers.ASYNC.getAsyncPlayers()) {
            if (entityPlayer != mc.field_1724) {
               float posX = (float)(
                     entityPlayer.field_6014
                        + (entityPlayer.field_6014 - entityPlayer.method_23317()) * Render3DEngine.getTickDelta()
                        - mc.field_1724.method_23317()
                  )
                  * 2.0F;
               float posZ = (float)(
                     entityPlayer.field_5969
                        + (entityPlayer.field_5969 - entityPlayer.method_23321()) * Render3DEngine.getTickDelta()
                        - mc.field_1724.method_23321()
                  )
                  * 2.0F;
               float cos = (float)Math.cos(mc.field_1724.method_5705(Render3DEngine.getTickDelta()) * 0.017453292);
               float sin = (float)Math.sin(mc.field_1724.method_5705(Render3DEngine.getTickDelta()) * 0.017453292);
               float rotY = -(posZ * cos - posX * sin);
               float rotX = -(posX * cos + posZ * sin);
               if (rotY > this.size.getValue().intValue() / 2.0F - 6.0F) {
                  rotY = this.size.getValue().intValue() / 2.0F - 6.0F;
               } else if (rotY < -(this.size.getValue().intValue() / 2.0F - 8.0F)) {
                  rotY = -(this.size.getValue().intValue() / 2.0F - 8.0F);
               }

               if (rotX > this.size.getValue().intValue() / 2.0F - 5.0F) {
                  rotX = this.size.getValue().intValue() / 2.0F - 5.0F;
               } else if (rotX < -(this.size.getValue().intValue() / 2.0F - 5.0F)) {
                  rotX = -(this.size.getValue().intValue() / 2.0F - 5.0F);
               }

               Render2DEngine.drawRound(
                  context.method_51448(),
                  this.getPosX() + this.size.getValue().intValue() / 2.0F + rotX - 2.0F,
                  this.getPosY() + this.size.getValue().intValue() / 2.0F + rotY - 2.0F,
                  4.0F,
                  4.0F,
                  2.0F,
                  this.color3.getValue().getColorObject()
               );
            }
         }
      }

      if (this.mode.getValue() == Radar.Mode.Text) {
         float offset_y = 0.0F;

         for (class_1657 entityPlayerx : Managers.ASYNC.getAsyncPlayers()) {
            if (entityPlayerx != mc.field_1724) {
               String str = String.format(
                  "%s %s %s %s %s",
                  this.getText(this.c1, entityPlayerx),
                  this.getText(this.c2, entityPlayerx),
                  this.getText(this.c3, entityPlayerx),
                  this.getText(this.c4, entityPlayerx),
                  this.getText(this.c5, entityPlayerx)
               );
               if (this.colorMode.getValue() == Radar.ColorMode.Sync) {
                  FontRenderers.sf_bold
                     .drawString(context.method_51448(), str, this.getPosX(), this.getPosY() + offset_y, HudEditor.getColor((int)(offset_y * 2.0F)).getRGB());
               } else {
                  FontRenderers.sf_bold.drawString(context.method_51448(), str, this.getPosX(), this.getPosY() + offset_y, this.color2.getValue().getColor());
               }

               offset_y += FontRenderers.sf_bold.getFontHeight(str);
            }
         }
      }

      this.setBounds(this.getPosX(), this.getPosY(), this.size.getValue().intValue(), this.size.getValue().intValue());
   }

   public String getText(Setting<Radar.Component> c, class_1657 player) {
      switch ((Radar.Component)c.getValue()) {
         case Name:
            return this.c12.getValue() + player.method_5477().getString() + class_124.field_1070;
         case Hp:
            int health = (int)Math.ceil(player.method_6032() + player.method_6067());
            return ModuleManager.nameTags.getHealthColor(health) + health + class_124.field_1070;
         case Distance:
            return "" + this.c32.getValue() + (int)Math.ceil(mc.field_1724.method_5739(player)) + "m" + class_124.field_1070;
         case Ping:
            return "" + this.c22.getValue() + NameTags.getEntityPing(player) + "ms" + class_124.field_1070;
         case TotemPops:
            return "" + this.c42.getValue() + Managers.COMBAT.getPops(player) + class_124.field_1070;
         default:
            return "";
      }
   }

   public static enum ColorMode {
      Sync,
      Custom;
   }

   public static enum Component {
      Name,
      Hp,
      Distance,
      Ping,
      TotemPops,
      None;
   }

   private static enum Mode {
      Rect,
      Text;
   }
}
