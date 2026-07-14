package sky.core.utils.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.render.ColorUtil;

public class ChatUtil implements Wrapper {
   public static final char COLOR_CODE = '§';

   public static void addText(Object message, Object... objects) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (message == null) {
            addText("Object is null");
         } else {
            String prefix = "SkyCore Client ⇨";
            int startColor = ThemeEditor.getColor(ThemeSettings.LOGO);
            int endColor = ColorUtil.darken(startColor, 0.5F);
            IFormattableTextComponent finalText = new StringTextComponent("");

            StringTextComponent letter;
            for(int i = 0; i < "SkyCore Client ⇨".length(); ++i) {
               char c = "SkyCore Client ⇨".charAt(i);
               letter = new StringTextComponent(String.valueOf(c));
               Style style;
               if (c == 8680) {
                  style = Style.EMPTY.setColor(Color.fromInt(8947848));
               } else {
                  float progress = (float)i / (float)("SkyCore Client ⇨".length() - 1);
                  int gradientColor = ColorUtil.interpolate(startColor, endColor, progress);
                  style = Style.EMPTY.setColor(Color.fromInt(gradientColor & 16777215)).applyFormatting(TextFormatting.BOLD);
               }

               letter.setStyle(style);
               finalText.append(letter);
            }

            finalText.append(new StringTextComponent(" "));
            if (message instanceof ITextComponent) {
               finalText.append((ITextComponent)message);
            } else {
               String msg = String.format(message.toString(), objects).replace('&', '§');
               String cleanMsg = msg.replaceAll("§[0-9a-fk-or]", "");
               letter = new StringTextComponent(cleanMsg);
               letter.setStyle(Style.EMPTY.setColor(Color.fromInt(16777215)));
               finalText.append(letter);
            }

            mc.ingameGUI.getChatGUI().printChatMessage(finalText);
         }
      }
   }

   public static void addChatMessage(String message) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         String prefix = "SkyCore Client ⇨";
         int startColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int endColor = ColorUtil.darken(startColor, 0.5F);
         IFormattableTextComponent finalText = new StringTextComponent("");

         for(int i = 0; i < "SkyCore Client ⇨".length(); ++i) {
            char c = "SkyCore Client ⇨".charAt(i);
            StringTextComponent letter = new StringTextComponent(String.valueOf(c));
            Style style;
            if (c == 8680) {
               style = Style.EMPTY.setColor(Color.fromInt(8947848));
            } else {
               float progress = (float)i / (float)("SkyCore Client ⇨".length() - 1);
               int gradientColor = ColorUtil.interpolate(startColor, endColor, progress);
               style = Style.EMPTY.setColor(Color.fromInt(gradientColor & 16777215)).applyFormatting(TextFormatting.BOLD);
            }

            letter.setStyle(style);
            finalText.append(letter);
         }

         finalText.append(new StringTextComponent(" "));
         String msg = message.replace('&', '§');
         StringTextComponent mainText = new StringTextComponent(msg);
         finalText.append(mainText);
         mc.ingameGUI.getChatGUI().printChatMessage(finalText);
      }
   }
}
