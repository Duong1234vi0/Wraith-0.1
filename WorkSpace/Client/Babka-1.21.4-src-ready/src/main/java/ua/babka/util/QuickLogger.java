package ua.babka.util;

import java.util.Arrays;
import java.util.stream.Stream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import ua.babka.render.ColorProvider;

public interface QuickLogger {
    public static Text getPrefix() {
        MutableText prefix = Text.literal((String)"");
        String name = "Babka";
        for (int i2 = 0; i2 < name.length(); ++i2) {
            int color = ColorProvider.interpolateColor(ColorProvider.getThemeColor(), ColorProvider.getThemeColorTwo(), (float)i2 / (float)name.length());
            prefix.append((Text)Text.literal((String)String.valueOf(name.charAt(i2))).setStyle(Style.EMPTY.withColor(TextColor.fromRgb((int)color))));
        }
        prefix.append((Text)Text.literal((String)" -> ").setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        return prefix;
    }

    default public void logDirect(String message, Object ... args) {
        this.logDirect(message.formatted(args));
    }

    default public void logDirect(Text ... components) {
        MutableText component = Text.literal((String)"");
        component.append(QuickLogger.getPrefix());
        component.append((Text)Text.literal((String)" "));
        Arrays.asList(components).forEach(arg_0 -> ((MutableText)component).append(arg_0));
        if (MinecraftClient.getInstance().player != null) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage((Text)component);
        }
    }

    default public void logDirect(String message, Formatting color) {
        Stream.of(message.split("\n")).forEach(line -> {
            MutableText component = Text.literal((String)line.replace("\t", "    "));
            component.setStyle(component.getStyle().withColor(color));
            this.logDirect(new Text[]{component});
        });
    }

    default public void logDirect(String message) {
        this.logDirect(message, Formatting.GRAY);
    }
}

