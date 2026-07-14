package ua.babka.util;

import java.util.Arrays;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import ua.babka.render.ColorProvider;
import ua.babka.util.IMinecraft;

public class ChatUtil
implements IMinecraft {
    public static void send(Object message) {
        if (ChatUtil.mc.player == null) {
            return;
        }
        MutableText prefix = ChatUtil.gradient("Babka Client", ColorProvider.getThemeColor(), ColorProvider.getThemeColorTwo());
        ChatUtil.mc.player.sendMessage((Text)prefix.append((Text)Text.literal((String)(" " + String.valueOf(Formatting.DARK_GRAY) + "-> " + String.valueOf(Formatting.RESET) + String.valueOf(message)))), false);
    }

    public static void send(Object ... messages) {
        if (ChatUtil.mc.player == null) {
            return;
        }
        MutableText prefix = ChatUtil.gradient("Babka Client", ColorProvider.getThemeColor(), ColorProvider.getThemeColorTwo());
        ChatUtil.mc.player.sendMessage((Text)prefix.append((Text)Text.literal((String)(" " + String.valueOf(Formatting.DARK_GRAY) + "-> " + String.valueOf(Formatting.RESET) + String.join((CharSequence)",", Arrays.toString(messages))))), false);
    }

    public static MutableText gradient(String message, int first, int end) {
        MutableText text = Text.empty();
        for (int i2 = 0; i2 < message.length(); ++i2) {
            int color = ColorProvider.interpolateColor(first, end, (float)i2 / (float)message.length());
            MutableText charText = Text.literal((String)String.valueOf(message.charAt(i2))).setStyle(Style.EMPTY.withColor(TextColor.fromRgb((int)color)));
            text.append((Text)charText);
        }
        return text;
    }
}

