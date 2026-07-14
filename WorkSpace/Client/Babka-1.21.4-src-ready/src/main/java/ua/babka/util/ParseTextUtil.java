package ua.babka.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.text.KeybindTextContent;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import ua.babka.render.font.MsdfFont;
import ua.babka.util.IMinecraft;

public class ParseTextUtil
implements IMinecraft {
    public static List<MsdfFont.ColoredGlyph> parseTextToColoredGlyphs(Text text) {
        ArrayList<MsdfFont.ColoredGlyph> result = new ArrayList<MsdfFont.ColoredGlyph>();
        ParseTextUtil.parseTextRecursive(text, -1, result);
        return result;
    }

    private static void parseTextRecursive(Text text, int currentColor, List<MsdfFont.ColoredGlyph> result) {
        Style style = text.getStyle();
        int color = style.getColor() != null ? style.getColor().getRgb() | 0xFF000000 : currentColor;
        TextContent content = text.getContent();
        String raw = "";
        if (content instanceof PlainTextContent.Literal) {
            PlainTextContent.Literal literal = (PlainTextContent.Literal)content;
            raw = literal.string();
        } else if (content instanceof TranslatableTextContent) {
            TranslatableTextContent translatable = (TranslatableTextContent)content;
            raw = translatable.getKey();
        } else if (content instanceof KeybindTextContent) {
            KeybindTextContent keybind = (KeybindTextContent)content;
            raw = keybind.getKey();
        }
        for (int i2 = 0; i2 < raw.length(); ++i2) {
            char c2 = raw.charAt(i2);
            if (c2 == '§' && i2 + 1 < raw.length()) {
                ++i2;
                continue;
            }
            result.add(new MsdfFont.ColoredGlyph(c2, color));
        }
        for (Text sibling : text.getSiblings()) {
            ParseTextUtil.parseTextRecursive(sibling, color, result);
        }
    }
}

