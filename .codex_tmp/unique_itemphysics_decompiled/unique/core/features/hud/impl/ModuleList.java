package unique.core.features.hud.impl;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_332;
import unique.core.core.Managers;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;

public class ModuleList extends HudElement {
   private final Setting<ModuleList.Mode> mode = new Setting<>("Mode", ModuleList.Mode.ColorText);
   private final Setting<Integer> gste = new Setting<>("GS", 30, 1, 50);
   private final Setting<Boolean> glow = new Setting<>("glow", false);
   private final Setting<Boolean> hrender = new Setting<>("HideHud", true);
   private final Setting<Boolean> hhud = new Setting<>("HideRender", true);
   private final Setting<ColorSetting> color3 = new Setting<>("RectColor", new ColorSetting(-16777216));
   private final Setting<ColorSetting> color4 = new Setting<>("SideRectColor", new ColorSetting(-16777216));

   public ModuleList() {
      super("ArrayList", 50, 30);
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      boolean reverse = this.getPosX() > mc.method_22683().method_4486() / 2.0F;
      int offset = 0;
      float maxWidth = 0.0F;
      float reversedX = this.getPosX();

      List<Module> list;
      try {
         list = Managers.MODULE
            .getEnabledModules()
            .stream()
            .sorted(Comparator.comparing(modulex -> FontRenderers.modules.getStringWidth(modulex.getFullArrayString()) * -1.0F))
            .toList();
      } catch (IllegalArgumentException var11) {
         return;
      }

      for (Module module : list) {
         if (this.shouldRender(module)) {
            Color color1 = HudEditor.getColor(offset);
            int stringWidth = (int)(
               FontRenderers.modules
                     .getStringWidth(
                        module.getDisplayName()
                           + class_124.field_1080
                           + (module.getDisplayInfo() != null ? " [" + class_124.field_1068 + module.getDisplayInfo() + class_124.field_1080 + "]" : "")
                     )
                  + 3.0F
            );
            if (this.glow.getValue()) {
               Render2DEngine.drawBlurredShadow(
                  context.method_51448(),
                  reverse ? reversedX - stringWidth - 3.0F : this.getPosX(),
                  this.getPosY() + offset - 1.0F,
                  stringWidth + 4,
                  9.0F,
                  this.gste.getValue(),
                  color1
               );
            }

            if (stringWidth > maxWidth) {
               maxWidth = stringWidth;
            }

            offset += 9;
         }
      }

      int var13 = 0;

      for (Module modulex : list) {
         if (this.shouldRender(modulex)) {
            int stringWidthx = (int)(
               FontRenderers.modules
                     .getStringWidth(
                        modulex.getDisplayName()
                           + class_124.field_1080
                           + (modulex.getDisplayInfo() != null ? " [" + class_124.field_1068 + modulex.getDisplayInfo() + class_124.field_1080 + "]" : "")
                     )
                  + 3.0F
            );
            Color color1x = HudEditor.getColor(var13);
            if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
               Render2DEngine.drawRoundedBlur(
                  context.method_51448(),
                  reverse ? reversedX - stringWidthx : this.getPosX(),
                  this.getPosY() + var13,
                  stringWidthx + 1.0F,
                  9.0F,
                  2.0F,
                  HudEditor.blurColor.getValue().getColorObject()
               );
            } else {
               Render2DEngine.drawRect(
                  context.method_51448(),
                  reverse ? reversedX - stringWidthx : this.getPosX(),
                  this.getPosY() + var13,
                  stringWidthx + 1.0F,
                  9.0F,
                  this.mode.getValue() == ModuleList.Mode.ColorRect ? color1x : this.color3.getValue().getColorObject()
               );
               Render2DEngine.drawRect(
                  context.method_51448(),
                  reverse ? reversedX + 1.0F : this.getPosX() - 2.0F,
                  this.getPosY() + var13,
                  2.0F,
                  9.0F,
                  this.mode.getValue() == ModuleList.Mode.ColorRect ? this.color4.getValue().getColorObject() : color1x
               );
            }

            FontRenderers.modules
               .drawString(
                  context.method_51448(),
                  modulex.getDisplayName()
                     + class_124.field_1080
                     + (modulex.getDisplayInfo() != null ? " [" + class_124.field_1068 + modulex.getDisplayInfo() + class_124.field_1080 + "]" : ""),
                  reverse ? reversedX - stringWidthx + 2.0F : this.getPosX() + 3.0F,
                  this.getPosY() + 3.0F + var13,
                  this.mode.getValue() == ModuleList.Mode.ColorRect ? -1 : color1x.getRGB()
               );
            var13 += 9;
         }
      }

      this.setBounds(this.getPosX(), this.getPosY(), (int)maxWidth * (reverse ? -1 : 1), var13);
   }

   private boolean shouldRender(Module m) {
      return m.isDrawn()
         && (!this.hrender.getValue() || m.getCategory() != Module.Category.RENDER)
         && (!this.hhud.getValue() || m.getCategory() != Module.Category.HUD);
   }

   private static enum Mode {
      ColorText,
      ColorRect;
   }
}
