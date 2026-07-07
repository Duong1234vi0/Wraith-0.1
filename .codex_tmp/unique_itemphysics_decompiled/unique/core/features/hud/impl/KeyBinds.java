package unique.core.features.hud.impl;

import java.util.Objects;
import net.minecraft.class_332;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class KeyBinds extends HudElement {
   public final Setting<ColorSetting> oncolor = new Setting<>("OnColor", new ColorSetting(-1));
   public final Setting<ColorSetting> offcolor = new Setting<>("OffColor", new ColorSetting(1));
   public final Setting<Boolean> onlyEnabled = new Setting<>("OnlyEnabled", false);
   public final Setting<Boolean> syncColor = new Setting<>("SyncColor", false);
   private float vAnimation;
   private float hAnimation;
   private float heightAnimation = 0.0F;
   private int activeBind = 0;
   private float visibilityAnimation = 0.0F;

   public KeyBinds() {
      super("KeyBinds", 80, 80);
   }

   private void renderBackground(class_332 context, float x, float y, float width, float height) {
      float round = HudEditor.hudRound.getValue();
      if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
         Render2DEngine.drawHudBase2(
            context.method_51448(),
            x,
            y,
            width,
            height,
            round,
            Math.max(HudEditor.blurStrength.getValue(), 12.0F),
            Math.min(HudEditor.blurOpacity.getValue() * 1.2F, 1.0F),
            0.85F
         );
      } else {
         Render2DEngine.drawHudBase(context.method_51448(), x, y, width, height, round);
      }
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      float x = this.getPosX();
      float y = this.getPosY();
      float rowHeight = 12.0F;
      float headerHeight = 16.0F;
      float headerGap = 3.0F;
      float bottomExtra = 2.0F;
      context.method_51448().method_22903();
      this.activeBind = 0;
      float maxNameWidth = 0.0F;
      float maxBindWidth = 0.0F;

      for (Module feature : Managers.MODULE.modules) {
         if ((feature.isEnabled() || !this.onlyEnabled.getValue())
            && !Objects.equals(feature.getBind().getBind(), "None")
            && feature != ModuleManager.clickGui
            && feature != ModuleManager.thunderHackGui) {
            this.activeBind++;
            String key = getShortKeyName(feature).toUpperCase();
            float nameWidth = FontRenderers.sf_bold_mini.getStringWidth(feature.getName());
            float bindWidth = FontRenderers.sf_bold_mini.getStringWidth("[")
               + FontRenderers.sf_bold_mini.getStringWidth(key)
               + FontRenderers.sf_bold_mini.getStringWidth("]");
            if (nameWidth > maxNameWidth) {
               maxNameWidth = nameWidth;
            }

            if (bindWidth > maxBindWidth) {
               maxBindWidth = bindWidth;
            }
         }
      }

      float width2 = Math.max(70.0F, maxNameWidth + maxBindWidth + 20.0F);
      float height2 = this.activeBind > 0 ? headerHeight + headerGap + rowHeight * this.activeBind + bottomExtra : headerHeight + 4.0F;
      this.renderBackground(context, x, y, width2, height2);
      String title = "Keybinds";
      float titleWidth = FontRenderers.sf_bold.getStringWidth(title);
      FontRenderers.sf_bold.drawString(context.method_51448(), title, x + width2 / 2.0F - titleWidth / 2.0F, y + 7.0F, HudEditor.getColor(0).getRGB());
      int i = 0;

      for (Module featurex : Managers.MODULE.modules) {
         int syncedColor = HudEditor.getColor(i).getRGB();
         if ((featurex.isEnabled() || !this.onlyEnabled.getValue())
            && !Objects.equals(featurex.getBind().getBind(), "None")
            && featurex != ModuleManager.clickGui
            && featurex != ModuleManager.thunderHackGui) {
            float bindY = y + headerHeight + headerGap + i * rowHeight;
            int nameColor = this.syncColor.getValue()
               ? syncedColor
               : (featurex.isEnabled() ? this.oncolor.getValue().getColor() : this.offcolor.getValue().getColor());
            FontRenderers.sf_bold_mini.drawString(context.method_51448(), featurex.getName(), x + 6.0F, bindY + 2.0F, nameColor);
            String keyx = getShortKeyName(featurex).toUpperCase();
            float keyWidth = FontRenderers.sf_bold_mini.getStringWidth(keyx);
            float leftWidth = FontRenderers.sf_bold_mini.getStringWidth("[");
            float totalWidth = leftWidth + keyWidth + FontRenderers.sf_bold_mini.getStringWidth("]");
            float startX = x + width2 - totalWidth - 6.0F;
            FontRenderers.sf_bold_mini.drawString(context.method_51448(), "[", startX, bindY + 2.0F, this.syncColor.getValue() ? syncedColor : nameColor);
            FontRenderers.sf_bold_mini.drawString(context.method_51448(), keyx, startX + leftWidth, bindY + 2.0F, nameColor);
            FontRenderers.sf_bold_mini
               .drawString(context.method_51448(), "]", startX + leftWidth + keyWidth, bindY + 2.0F, this.syncColor.getValue() ? syncedColor : nameColor);
            i++;
         }
      }

      context.method_51448().method_22909();
      this.heightAnimation = AnimationUtility.fast(this.heightAnimation, height2, 11.0F);
      this.visibilityAnimation = AnimationUtility.fast(this.visibilityAnimation, this.activeBind > 0 ? 1.0F : 0.0F, 8.0F);
      this.setBounds(x, y, width2, height2);
   }

   @NotNull
   public static String getShortKeyName(Module feature) {
      String var1 = feature.getBind().getBind();

      return switch (var1) {
         case "LEFT_CONTROL" -> "LCtrl";
         case "RIGHT_CONTROL" -> "RCtrl";
         case "LEFT_SHIFT" -> "LShift";
         case "RIGHT_SHIFT" -> "RShift";
         case "LEFT_ALT" -> "LAlt";
         case "RIGHT_ALT" -> "RAlt";
         default -> feature.getBind().getBind();
      };
   }
}
