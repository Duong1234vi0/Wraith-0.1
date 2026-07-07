package unique.core.features.hud.impl;

import java.awt.Color;
import java.lang.invoke.StringConcatFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1959;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_7924;
import org.apache.commons.lang3.StringUtils;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderer;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.math.FrameRateCounter;
import unique.core.utility.math.MathUtility;

public class LegacyHud extends Module {
   private static final class_1799 totem = new class_1799(class_1802.field_8288);
   private final Setting<LegacyHud.Font> customFont = new Setting<>("Font", LegacyHud.Font.Minecraft);
   private final Setting<ColorSetting> colorSetting = new Setting<>("Color", new ColorSetting(new Color(30719)));
   private final Setting<Boolean> renderingUp = new Setting<>("RenderingUp", false);
   private final Setting<Boolean> waterMark = new Setting<>("Watermark", false);
   private final Setting<Boolean> arrayList = new Setting<>("ActiveModules", false);
   private final Setting<Boolean> coords = new Setting<>("Coords", false);
   private final Setting<Boolean> direction = new Setting<>("Direction", false);
   private final Setting<Boolean> armor = new Setting<>("Armor", false);
   private final Setting<Boolean> totems = new Setting<>("Totems", false);
   private final Setting<Boolean> greeter = new Setting<>("Welcomer", false);
   private final Setting<Boolean> speed = new Setting<>("Speed", false);
   private final Setting<Boolean> bps = new Setting<>("BPS", false, v -> this.speed.getValue());
   public final Setting<Boolean> potions = new Setting<>("Potions", false);
   private final Setting<Boolean> ping = new Setting<>("Ping", false);
   private final Setting<Boolean> tps = new Setting<>("TPS", false);
   private final Setting<Boolean> extraTps = new Setting<>("ExtraTPS", true, v -> this.tps.getValue());
   private final Setting<Boolean> offhandDurability = new Setting<>("OffhandDurability", false);
   private final Setting<Boolean> mainhandDurability = new Setting<>("MainhandDurability", false);
   private final Setting<Boolean> fps = new Setting<>("FPS", false);
   private final Setting<Boolean> chests = new Setting<>("Chests", false);
   private final Setting<Boolean> worldTime = new Setting<>("WorldTime", false);
   private final Setting<Boolean> biome = new Setting<>("Biome", false);
   public Setting<Boolean> time = new Setting<>("Time", false);
   public Setting<Integer> waterMarkY = new Setting<>("WatermarkPosY", 2, 0, 20, v -> this.waterMark.getValue());
   private int color;

   public LegacyHud() {
      super("LegacyHud", Module.Category.HUD);
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!fullNullCheck()) {
         int width = mc.method_22683().method_4486();
         int height = mc.method_22683().method_4502();

         int offset = switch ((LegacyHud.Font)this.customFont.getValue()) {
            case Minecraft -> 10;
            case Monsterrat -> 9;
            default -> 8;
         };
         this.color = this.colorSetting.getValue().getColor();
         if (this.waterMark.getValue()) {
            this.drawText(context, "unique v1.7b2407", 2, this.waterMarkY.getValue());
         }

         int j = mc.field_1755 instanceof class_408 && !this.renderingUp.getValue() ? 14 : 0;
         if (this.arrayList.getValue()) {
            for (Module module : Managers.MODULE
               .getEnabledModules()
               .stream()
               .filter(Module::isDrawn)
               .sorted(Comparator.comparing(modulex -> this.getStringWidth(modulex.getFullArrayString()) * -1))
               .toList()) {
               if (module.isDrawn()) {
                  String str = module.getDisplayName()
                     + class_124.field_1080
                     + (module.getDisplayInfo() != null ? " [" + class_124.field_1068 + module.getDisplayInfo() + class_124.field_1080 + "]" : "");
                  if (this.renderingUp.getValue()) {
                     this.drawText(context, str, width - 2 - this.getStringWidth(str), 2 + j * offset);
                     j++;
                  } else {
                     j += offset;
                     this.drawText(context, str, width - 2 - this.getStringWidth(str), height - j);
                  }
               }
            }
         }

         int i = mc.field_1755 instanceof class_408 && this.renderingUp.getValue() ? 13 : (this.renderingUp.getValue() ? -2 : 0);
         if (this.potions.getValue()) {
            for (class_1293 potionEffect : new ArrayList(mc.field_1724.method_6026())) {
               class_1291 potion = (class_1291)potionEffect.method_5579().comp_349();
               String power = "";
               switch (potionEffect.method_5578()) {
                  case 0:
                     power = "I";
                     break;
                  case 1:
                     power = "II";
                     break;
                  case 2:
                     power = "III";
                     break;
                  case 3:
                     power = "IV";
                     break;
                  case 4:
                     power = "V";
               }

               String s = potion.method_5560().getString() + " " + power;
               String s2 = StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(PotionHud.getDuration(potionEffect));
               Color c = new Color(((class_1291)potionEffect.method_5579().comp_349()).method_5556());
               if (this.renderingUp.getValue()) {
                  i += offset;
                  this.drawText(context, s + " " + s2, width - this.getStringWidth(s + " " + s2) - 2, height - 2 - i, c.getRGB());
               } else {
                  this.drawText(context, s + " " + s2, width - this.getStringWidth(s + " " + s2) - 2, 2 + i++ * offset, c.getRGB());
               }
            }
         }

         if (this.worldTime.getValue()) {
            String str2 = "WorldTime: " + class_124.field_1068 + mc.field_1687.method_8532() % 24000L;
            this.drawText(context, str2, width - this.getStringWidth(str2) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.mainhandDurability.getValue()) {
            String str = "MainHand"
               + class_124.field_1068
               + " ["
               + (mc.field_1724.method_6047().method_7936() - mc.field_1724.method_6047().method_7919())
               + "]";
            this.drawText(context, str, width - this.getStringWidth(str) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.tps.getValue()) {
            String str = "TPS " + class_124.field_1068 + Managers.SERVER.getTPS() + (this.extraTps.getValue() ? " [" + Managers.SERVER.getTPS2() + "]" : "");
            this.drawText(context, str, width - this.getStringWidth(str) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.speed.getValue()) {
            String str = "Speed "
               + class_124.field_1068
               + MathUtility.round(Managers.PLAYER.currentPlayerSpeed * (this.bps.getValue() ? 20.0F : 72.0F) * UniqueCore.TICK_TIMER)
               + (this.bps.getValue() ? " b/s" : " km/h");
            this.drawText(context, str, width - this.getStringWidth(str) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.biome.getValue()) {
            String str3 = "Biome: " + class_124.field_1068 + biome();
            this.drawText(context, str3, width - this.getStringWidth(str3) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.time.getValue()) {
            String str = "Time " + class_124.field_1068 + new SimpleDateFormat("h:mm a").format(new Date());
            this.drawText(context, str, width - this.getStringWidth(str) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.offhandDurability.getValue()) {
            String str = "OffHand"
               + class_124.field_1068
               + " ["
               + (mc.field_1724.method_6079().method_7936() - mc.field_1724.method_6079().method_7919())
               + "]";
            this.drawText(context, str, width - this.getStringWidth(str) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.ping.getValue()) {
            String str1 = "Ping " + class_124.field_1068 + Managers.SERVER.getPing();
            this.drawText(context, str1, width - this.getStringWidth(str1) - 2, this.renderingUp.getValue() ? height - 2 - (i += offset) : 2 + i++ * offset);
         }

         if (this.fps.getValue()) {
            String fpsText = "FPS " + class_124.field_1068 + FrameRateCounter.INSTANCE.getFps();
            int var17;
            this.drawText(
               context, fpsText, width - this.getStringWidth(fpsText) - 2, this.renderingUp.getValue() ? height - 2 - (var17 = i + offset) : 2 + i++ * offset
            );
         }

         boolean inHell = Objects.equals(mc.field_1687.method_27983().method_29177().method_12832(), "the_nether");
         int posX = (int)mc.field_1724.method_23317();
         int posY = (int)mc.field_1724.method_23318();
         int posZ = (int)mc.field_1724.method_23321();
         float nether = !inHell ? 0.125F : 8.0F;
         int hposX = (int)(mc.field_1724.method_23317() * nether);
         int hposZ = (int)(mc.field_1724.method_23321() * nether);
         i = mc.field_1755 instanceof class_408 ? 14 : 0;
         String coordinates = class_124.field_1068
            + "XYZ "
            + class_124.field_1070
            + (
               inHell
                  ? posX
                     + ", "
                     + posY
                     + ", "
                     + posZ
                     + class_124.field_1068
                     + " ["
                     + class_124.field_1070
                     + hposX
                     + ", "
                     + hposZ
                     + class_124.field_1068
                     + "]"
                     + class_124.field_1070
                  : posX + ", " + posY + ", " + posZ + class_124.field_1068 + " [" + class_124.field_1070 + hposX + ", " + hposZ + class_124.field_1068 + "]"
            );
         String direction1 = "";
         i += offset;
         if (this.direction.getValue()) {
            switch (mc.field_1724.method_5735()) {
               case field_11034:
                  direction1 = "East" + class_124.field_1068 + " [+X]";
                  break;
               case field_11039:
                  direction1 = "West" + class_124.field_1068 + " [-X]";
                  break;
               case field_11043:
                  direction1 = "North" + class_124.field_1068 + " [-Z]";
                  break;
               case field_11035:
                  direction1 = "South" + class_124.field_1068 + " [+Z]";
            }

            this.drawText(context, direction1, 2, height - i - 11);
         }

         if (this.coords.getValue()) {
            this.drawText(context, coordinates, 2, height - i);
         }

         if (this.armor.getValue()) {
            this.renderArmorHUD(true, context);
         }

         if (this.totems.getValue()) {
            this.renderTotemHUD(context);
         }

         if (this.greeter.getValue()) {
            this.renderGreeter(context);
         }
      }
   }

   private void drawText(class_332 context, String str, int x, int y, int color) {
      if (!this.customFont.getValue().equals(LegacyHud.Font.Minecraft)) {
         FontRenderer adapter = switch ((LegacyHud.Font)this.customFont.getValue()) {
            case Monsterrat -> FontRenderers.monsterrat;
            case SF -> FontRenderers.sf_medium;
            default -> FontRenderers.modules;
         };
         adapter.drawString(context.method_51448(), str.replace(class_124.field_1068 + "", ""), x + 0.5, y + 0.5, Color.BLACK.getRGB());
         adapter.drawString(context.method_51448(), str, x, y, color);
      } else {
         context.method_51433(mc.field_1772, str, x, y, color, true);
      }
   }

   private void drawText(class_332 context, String str, int x, int y) {
      if (!this.customFont.getValue().equals(LegacyHud.Font.Minecraft)) {
         FontRenderer adapter = switch ((LegacyHud.Font)this.customFont.getValue()) {
            case Monsterrat -> FontRenderers.monsterrat;
            case SF -> FontRenderers.sf_medium;
            default -> FontRenderers.modules;
         };
         adapter.drawString(context.method_51448(), str.replace(class_124.field_1068 + "", ""), x + 0.5, y + 0.5, Color.BLACK.getRGB());
         adapter.drawString(context.method_51448(), str, x, y, this.color);
      } else {
         context.method_51433(mc.field_1772, str, x, y, this.color, true);
      }
   }

   private int getStringWidth(String str) {
      switch ((LegacyHud.Font)this.customFont.getValue()) {
         case Minecraft:
            return mc.field_1772.method_1727(str);
         case Comfortaa:
         default:
            return (int)FontRenderers.modules.getStringWidth(str);
         case Monsterrat:
            return (int)FontRenderers.monsterrat.getStringWidth(str);
         case SF:
            return (int)FontRenderers.sf_medium.getStringWidth(str);
      }
   }

   public void renderGreeter(class_332 context) {
      String text = "Good " + getTimeOfDay() + mc.field_1724.method_5477().getString();
      this.drawText(context, text, (int)(mc.method_22683().method_4486() / 2.0F - this.getStringWidth(text) / 2.0F + 2.0F), 2);
   }

   public static String getTimeOfDay() {
      int timeOfDay = Calendar.getInstance().get(11);
      if (timeOfDay < 12) {
         return "Morning ";
      } else if (timeOfDay < 16) {
         return "Afternoon ";
      } else {
         return timeOfDay < 21 ? "Evening " : "Night ";
      }
   }

   public void renderTotemHUD(class_332 context) {
      int width = mc.method_22683().method_4486();
      int height = mc.method_22683().method_4502();
      int totems = mc.field_1724
         .method_31548()
         .field_7547
         .stream()
         .filter(itemStack -> itemStack.method_7909() == class_1802.field_8288)
         .mapToInt(class_1799::method_7947)
         .sum();
      int u = mc.field_1724.method_5748();
      int v = Math.min(mc.field_1724.method_5669(), u);
      if (mc.field_1724.method_6079().method_7909() == class_1802.field_8288) {
         totems += mc.field_1724.method_6079().method_7947();
      }

      if (totems > 0) {
         int i = width / 2;
         int y = height - 55 - (!mc.field_1724.method_5869() && v >= u ? 0 : 10);
         int x = i - 189 + 180 + 2;
         context.method_51427(totem, x, y);
         context.method_51431(mc.field_1772, totem, x, y);
         this.drawText(context, totems + "", 8 + (int)(x - this.getStringWidth(totems + "") / 2.0F), y - 7, 16777215);
      }
   }

   private static String biome() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_2960 id = mc.field_1687
            .method_30349()
            .method_30530(class_7924.field_41236)
            .method_10221((class_1959)mc.field_1687.method_23753(mc.field_1724.method_24515()).comp_349());
         return id == null
            ? "Unknown"
            : Arrays.stream(id.method_12832().split("_")).<CharSequence>map(StringUtils::capitalize).collect(Collectors.joining(" "));
      } else {
         return null;
      }
   }

   public void renderArmorHUD(boolean percent, class_332 context) {
      int i = 0;
      int u = mc.field_1724.method_5748();
      int v = Math.min(mc.field_1724.method_5669(), u);
      int y = mc.method_22683().method_4502() - 55 - (!mc.field_1724.method_5869() && v >= u ? 0 : 10);

      for (class_1799 is : mc.field_1724.method_31548().field_7548) {
         i++;
         if (!is.method_7960()) {
            int x = mc.method_22683().method_4486() / 2 - 90 + (9 - i) * 20 + 2;
            context.method_51427(is, x, y);
            context.method_51431(mc.field_1772, is, x, y);
            String s = is.method_7947() > 1 ? is.method_7947() + "" : "";
            this.drawText(context, s, x + 19 - 2 - this.getStringWidth(s), y + 9, 16777215);
            if (percent) {
               float green = (float)(is.method_7936() - is.method_7919()) / is.method_7936();
               float red = 1.0F - green;
               int dmg = 100 - (int)(red * 100.0F);
               this.drawText(
                  context,
                  dmg + "",
                  x + 8 - this.getStringWidth(dmg + "") / 2,
                  y - 11,
                  new Color((int)MathUtility.clamp(red * 255.0F, 0.0F, 255.0F), (int)MathUtility.clamp(green * 255.0F, 0.0F, 255.0F), 0).getRGB()
               );
            }
         }
      }
   }

   private static enum Font {
      Minecraft,
      Comfortaa,
      Monsterrat,
      SF;
   }
}
