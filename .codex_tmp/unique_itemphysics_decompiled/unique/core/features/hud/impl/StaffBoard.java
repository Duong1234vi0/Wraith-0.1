package unique.core.features.hud.impl;

import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.class_124;
import net.minecraft.class_1934;
import net.minecraft.class_268;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_640;
import unique.core.features.cmd.impl.StaffCommand;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class StaffBoard extends HudElement {
   private static final Pattern validUserPattern = Pattern.compile("^\\w{3,16}$");
   private List<String> players = new ArrayList<>();
   private List<String> notSpec = new ArrayList<>();
   private Map<String, class_2960> skinMap = new HashMap<>();
   private float vAnimation;
   private float hAnimation;

   public StaffBoard() {
      super("StaffBoard", 50, 50);
   }

   public static List<String> getOnlinePlayer() {
      return mc.field_1724
         .field_3944
         .method_2880()
         .stream()
         .map(class_640::method_2966)
         .<String>map(GameProfile::getName)
         .filter(profileName -> validUserPattern.matcher(profileName).matches())
         .collect(Collectors.toList());
   }

   public static List<String> getOnlinePlayerD() {
      List<String> S = new ArrayList<>();

      for (class_640 player : mc.field_1724.field_3944.method_2880()) {
         if (mc.method_1542() || player.method_2955() == null) {
            break;
         }

         String prefix = player.method_2955().method_1144().getString();
         if (check(class_124.method_539(prefix).toLowerCase())
            || StaffCommand.staffNames.toString().toLowerCase().contains(player.method_2966().getName().toLowerCase())
            || player.method_2966().getName().toLowerCase().contains("1danil_mansoru1")
            || player.method_2966().getName().toLowerCase().contains("barslan_")
            || player.method_2966().getName().toLowerCase().contains("timmings")
            || player.method_2966().getName().toLowerCase().contains("timings")
            || player.method_2966().getName().toLowerCase().contains("ruthless")
            || player.method_2955().method_1144().getString().contains("YT")
            || player.method_2955().method_1144().getString().contains("Y") && player.method_2955().method_1144().getString().contains("T")) {
            String name = Arrays.asList(player.method_2955().method_1204().toArray()).toString().replace("[", "").replace("]", "");
            if (player.method_2958() == class_1934.field_9219) {
               S.add(player.method_2955().method_1144().getString() + name + ":gm3");
            } else {
               S.add(player.method_2955().method_1144().getString() + name + ":active");
            }
         }
      }

      return S;
   }

   public List<String> getVanish() {
      List<String> list = new ArrayList<>();

      for (class_268 s : mc.field_1687.method_8428().method_1159()) {
         if (!s.method_1144().getString().isEmpty() && !mc.method_1542()) {
            String name = Arrays.asList(s.method_1204().toArray()).toString().replace("[", "").replace("]", "");
            if (!getOnlinePlayer().contains(name)
               && !name.isEmpty()
               && (
                  StaffCommand.staffNames.toString().toLowerCase().contains(name.toLowerCase()) && check(s.method_1144().getString().toLowerCase())
                     || check(s.method_1144().getString().toLowerCase())
                     || name.toLowerCase().contains("1danil_mansoru1")
                     || name.toLowerCase().contains("barslan_")
                     || name.toLowerCase().contains("timmings")
                     || name.toLowerCase().contains("timings")
                     || name.toLowerCase().contains("ruthless")
                     || s.method_1144().getString().contains("YT")
                     || s.method_1144().getString().contains("Y") && s.method_1144().getString().contains("T")
               )) {
               list.add(s.method_1144().getString() + name + ":vanish");
            }
         }
      }

      return list;
   }

   public static boolean check(String name) {
      return mc.method_1558() != null && mc.method_1558().field_3761.contains("mcfunny")
         ? name.contains("helper") || name.contains("moder") || name.contains("модер") || name.contains("хелпер")
         : name.contains("helper")
            || name.contains("moder")
            || name.contains("admin")
            || name.contains("owner")
            || name.contains("curator")
            || name.contains("куратор")
            || name.contains("модер")
            || name.contains("админ")
            || name.contains("хелпер")
            || name.contains("поддержка")
            || name.contains("сотрудник")
            || name.contains("зам")
            || name.contains("стажёр");
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      List<String> all = new ArrayList<>();
      all.addAll(this.players);
      all.addAll(this.notSpec);
      int y_offset1 = 0;
      float max_width = 50.0F;
      float pointerX = 0.0F;

      for (String player : all) {
         if (y_offset1 == 0) {
            y_offset1 += 4;
         }

         y_offset1 += 9;
         float nameWidth = FontRenderers.sf_bold_mini.getStringWidth(player.split(":")[0]);
         float timeWidth = FontRenderers.sf_bold_mini
            .getStringWidth(
               player.split(":")[1].equalsIgnoreCase("vanish")
                  ? class_124.field_1061 + "V"
                  : (player.split(":")[1].equalsIgnoreCase("gm3") ? class_124.field_1061 + "V " + class_124.field_1054 + "(GM3)" : class_124.field_1060 + "Z")
            );
         float width = (nameWidth + timeWidth) * 1.4F;
         if (width > max_width) {
            max_width = width;
         }

         if (timeWidth > pointerX) {
            pointerX = timeWidth;
         }
      }

      this.vAnimation = AnimationUtility.fast(this.vAnimation, 14 + y_offset1, 15.0F);
      this.hAnimation = AnimationUtility.fast(this.hAnimation, max_width, 15.0F);
      Render2DEngine.drawHudBase(context.method_51448(), this.getPosX(), this.getPosY(), this.hAnimation, this.vAnimation, HudEditor.hudRound.getValue());
      if (HudEditor.hudStyle.is(HudEditor.HudStyle.Glowing)) {
         FontRenderers.sf_bold
            .drawCenteredString(
               context.method_51448(), "Staff", this.getPosX() + this.hAnimation / 2.0F, this.getPosY() + 4.0F, HudEditor.textColor.getValue().getColorObject()
            );
      } else {
         FontRenderers.sf_bold.drawGradientCenteredString(context.method_51448(), "Staff", this.getPosX() + this.hAnimation / 2.0F, this.getPosY() + 4.0F, 10);
      }

      if (y_offset1 > 0) {
         if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
            Render2DEngine.drawRectDumbWay(
               context.method_51448(),
               this.getPosX() + 4.0F,
               this.getPosY() + 13.0F,
               this.getPosX() + this.getWidth() - 8.0F,
               this.getPosY() + 14.0F,
               new Color(1426063359, true)
            );
         } else {
            Render2DEngine.horizontalGradient(
               context.method_51448(),
               this.getPosX() + 2.0F,
               this.getPosY() + 13.7F,
               this.getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F,
               this.getPosY() + 13.5F,
               Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0),
               HudEditor.textColor.getValue().getColorObject()
            );
            Render2DEngine.horizontalGradient(
               context.method_51448(),
               this.getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F,
               this.getPosY() + 13.7F,
               this.getPosX() + 2.0F + this.hAnimation - 4.0F,
               this.getPosY() + 14.0F,
               HudEditor.textColor.getValue().getColorObject(),
               Render2DEngine.injectAlpha(HudEditor.textColor.getValue().getColorObject(), 0)
            );
         }
      }

      Render2DEngine.addWindow(context.method_51448(), this.getPosX(), this.getPosY(), this.getPosX() + this.hAnimation, this.getPosY() + this.vAnimation, 1.0);
      int y_offset = 0;

      for (String player : all) {
         float px = this.getPosX() + (max_width - pointerX - 10.0F);
         class_2960 tex = this.getTexture(player);
         if (tex != null) {
            context.method_25293(tex, (int)(this.getPosX() + 3.0F), (int)(this.getPosY() + 16.0F + y_offset), 8, 8, 8.0F, 8.0F, 8, 8, 64, 64);
            context.method_25293(tex, (int)(this.getPosX() + 3.0F), (int)(this.getPosY() + 16.0F + y_offset), 8, 8, 40.0F, 8.0F, 8, 8, 64, 64);
         }

         FontRenderers.sf_bold_mini
            .drawString(
               context.method_51448(),
               player.split(":")[0],
               this.getPosX() + 13.0F,
               this.getPosY() + 19.0F + y_offset,
               HudEditor.textColor.getValue().getColor()
            );
         FontRenderers.sf_bold_mini
            .drawCenteredString(
               context.method_51448(),
               player.split(":")[1].equalsIgnoreCase("vanish")
                  ? class_124.field_1061 + "O"
                  : (player.split(":")[1].equalsIgnoreCase("gm3") ? class_124.field_1054 + "O" : class_124.field_1060 + "O"),
               px + (this.getPosX() + max_width - px) / 2.0F,
               this.getPosY() + 19.0F + y_offset,
               HudEditor.textColor.getValue().getColor()
            );
         Render2DEngine.drawRect(context.method_51448(), px, this.getPosY() + 17.0F + y_offset, 0.5F, 8.0F, new Color(1157627903, true));
         y_offset += 9;
      }

      Render2DEngine.popWindow();
      this.setBounds(this.getPosX(), this.getPosY(), this.hAnimation, this.vAnimation);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1724.field_6012 % 10 == 0) {
         this.players = this.getVanish();
         this.notSpec = getOnlinePlayerD();
         this.players.sort(String::compareTo);
         this.notSpec.sort(String::compareTo);
      }
   }

   private class_2960 getTexture(String n) {
      class_2960 id = null;
      if (this.skinMap.containsKey(n)) {
         id = this.skinMap.get(n);
      }

      for (class_640 ple : mc.method_1562().method_2880()) {
         if (n.contains(ple.method_2966().getName())) {
            id = ple.method_52810().comp_1626();
            if (!this.skinMap.containsKey(n)) {
               this.skinMap.put(n, id);
            }
            break;
         }
      }

      return id;
   }
}
