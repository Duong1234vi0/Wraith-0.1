package ua.babka.render;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;

public class ReplaceUtil {
    public static Text replace(Text input, String target, String replacement) {
        if (input == null || target == null || replacement == null) {
            return input;
        }
        MutableText result = Text.empty().setStyle(input.getStyle());
        ReplaceUtil.appendReplaced(result, input, target, replacement);
        return result;
    }

    private static void appendReplaced(MutableText result, Text current, String target, String replacement) {
        TextContent content = current.getContent();
        Style style = current.getStyle();
        if (content instanceof PlainTextContent.Literal) {
            PlainTextContent.Literal literal = (PlainTextContent.Literal)content;
            Pattern pattern = Pattern.compile(Pattern.quote(target), 2);
            String replaced = pattern.matcher(literal.string()).replaceAll(replacement);
            result.append((Text)Text.literal((String)replaced).setStyle(style));
        }
        for (Text sibling : current.getSiblings()) {
            ReplaceUtil.appendReplaced(result, sibling, target, replacement);
        }
    }

    public static Text replaceLiteral(Text input, String target, String replacement) {
        if (input == null) {
            return null;
        }
        String full = input.getString();
        if (!full.toLowerCase().contains(target.toLowerCase())) {
            return input;
        }
        full = full.replaceAll("(?i)" + Pattern.quote(target), replacement);
        MutableText out = Text.empty();
        List<StyledChar> styledChars = ReplaceUtil.flatten(input);
        int index = 0;
        for (int i2 = 0; i2 < full.length(); ++i2) {
            Style style = index < styledChars.size() ? styledChars.get((int)index).style : Style.EMPTY;
            out.append((Text)Text.literal((String)String.valueOf(full.charAt(i2))).setStyle(style));
            ++index;
        }
        return out;
    }

    private static List<StyledChar> flatten(Text text) {
        ArrayList<StyledChar> list = new ArrayList<StyledChar>();
        ReplaceUtil.collect(text, list);
        return list;
    }

    private static void collect(Text text, List<StyledChar> list) {
        Style style = text.getStyle();
        TextContent textContent = text.getContent();
        if (textContent instanceof PlainTextContent.Literal) {
            PlainTextContent.Literal literal = (PlainTextContent.Literal)textContent;
            String s2 = literal.string();
            for (int i2 = 0; i2 < s2.length(); ++i2) {
                list.add(new StyledChar(s2.charAt(i2), style));
            }
        }
        for (Text sibling : text.getSiblings()) {
            ReplaceUtil.collect(sibling, list);
        }
    }

    public static String replaceSymbols(String string) {
        return string.replaceAll("ꔗ", String.valueOf(Formatting.BLUE) + "MODER").replaceAll("ꔥ", String.valueOf(Formatting.BLUE) + "ST.MODER").replaceAll("ꔡ", String.valueOf(Formatting.LIGHT_PURPLE) + "MODER+").replaceAll("ꔀ", String.valueOf(Formatting.GRAY) + "PLAYER").replaceAll("ꔉ", String.valueOf(Formatting.YELLOW) + "HELPER").replaceAll("◆", "@").replaceAll("┃", "|").replaceAll("ꔳ", String.valueOf(Formatting.AQUA) + "ML.ADMIN").replaceAll("ꔅ", String.valueOf(Formatting.RED) + "Y" + String.valueOf(Formatting.WHITE) + "T").replaceAll("ꔂ", String.valueOf(Formatting.BLUE) + "D.MODER").replaceAll("ꕠ", String.valueOf(Formatting.YELLOW) + "D.HELPER").replaceAll("ꕄ", String.valueOf(Formatting.RED) + "DRACULA").replaceAll("ꔖ", String.valueOf(Formatting.AQUA) + "OVERLORD").replaceAll("ꕈ", String.valueOf(Formatting.GREEN) + "COBRA").replaceAll("ꔨ", String.valueOf(Formatting.LIGHT_PURPLE) + "DRAGON").replaceAll("ꔤ", String.valueOf(Formatting.RED) + "IMPERATOR").replaceAll("ꔠ", String.valueOf(Formatting.GOLD) + "MAGISTER").replaceAll("ꔄ", String.valueOf(Formatting.BLUE) + "HERO").replaceAll("ꔒ", String.valueOf(Formatting.GREEN) + "AVENGER").replaceAll("ꕒ", String.valueOf(Formatting.WHITE) + "RABBIT").replaceAll("ꔈ", String.valueOf(Formatting.YELLOW) + "TITAN").replaceAll("ꕀ", String.valueOf(Formatting.DARK_GREEN) + "HYDRA").replaceAll("ꔶ", String.valueOf(Formatting.GOLD) + "TIGER").replaceAll("ꔲ", String.valueOf(Formatting.DARK_PURPLE) + "BULL").replaceAll("ꕖ", String.valueOf(Formatting.BLACK) + "BUNNY").replaceAll("ꕗꕘ", String.valueOf(Formatting.YELLOW) + "SPONSOR").replaceAll("🔥", "@").replaceAll("ᴀ", "A").replaceAll("ʙ", "B").replaceAll("ᴄ", "C").replaceAll("ᴅ", "D").replaceAll("ᴇ", "E").replaceAll("ғ", "F").replaceAll("ɢ", "G").replaceAll("ʜ", "H").replaceAll("ɪ", "I").replaceAll("ᴊ", "J").replaceAll("ᴋ", "K").replaceAll("ʟ", "L").replaceAll("ᴍ", "M").replaceAll("ɴ", "N").replaceAll("ꜱ", "S").replaceAll("ᴏ", "O").replaceAll("ᴘ", "P").replaceAll("ǫ", "Q").replaceAll("ʀ", "R").replaceAll("ᴛ", "T").replaceAll("ᴜ", "U").replaceAll("ᴠ", "V").replaceAll("ᴡ", "W").replaceAll("ꜰ", "F").replaceAll("ʏ", "Y").replaceAll("ᴢ", "Z");
    }

    public static Text replaceSymbols(Text text) {
        if (text.getString().contains("ꔗ")) {
            text = ReplaceUtil.replace(text, "ꔗ", String.valueOf(Formatting.BLUE) + "MODER");
        }
        if (text.getString().contains("ꔥ")) {
            text = ReplaceUtil.replace(text, "ꔥ", String.valueOf(Formatting.BLUE) + "ST.MODER");
        }
        if (text.getString().contains("ꔡ")) {
            text = ReplaceUtil.replace(text, "ꔡ", String.valueOf(Formatting.LIGHT_PURPLE) + "MODER+");
        }
        if (text.getString().contains("ꔀ")) {
            text = ReplaceUtil.replace(text, "ꔀ", String.valueOf(Formatting.GRAY) + "PLAYER");
        }
        if (text.getString().contains("ꔉ")) {
            text = ReplaceUtil.replace(text, "ꔉ", String.valueOf(Formatting.YELLOW) + "HELPER");
        }
        if (text.getString().contains("◆")) {
            text = ReplaceUtil.replace(text, "◆", "@");
        }
        if (text.getString().contains("┃")) {
            text = ReplaceUtil.replace(text, "┃", "|");
        }
        if (text.getString().contains("ꔳ")) {
            text = ReplaceUtil.replace(text, "ꔳ", String.valueOf(Formatting.AQUA) + "ML.ADMIN");
        }
        if (text.getString().contains("ꔅ")) {
            text = ReplaceUtil.replace(text, "ꔅ", String.valueOf(Formatting.RED) + "Y" + String.valueOf(Formatting.WHITE) + "T");
        }
        if (text.getString().contains("ꔂ")) {
            text = ReplaceUtil.replace(text, "ꔂ", String.valueOf(Formatting.BLUE) + "D.MODER");
        }
        if (text.getString().contains("ꕠ")) {
            text = ReplaceUtil.replace(text, "ꕠ", String.valueOf(Formatting.YELLOW) + "D.HELPER");
        }
        if (text.getString().contains("ꕄ")) {
            text = ReplaceUtil.replace(text, "ꕄ", String.valueOf(Formatting.RED) + "DRACULA");
        }
        if (text.getString().contains("ꔖ")) {
            text = ReplaceUtil.replace(text, "ꔖ", String.valueOf(Formatting.AQUA) + "OVERLORD");
        }
        if (text.getString().contains("ꕈ")) {
            text = ReplaceUtil.replace(text, "ꕈ", String.valueOf(Formatting.GREEN) + "COBRA");
        }
        if (text.getString().contains("ꔨ")) {
            text = ReplaceUtil.replace(text, "ꔨ", String.valueOf(Formatting.LIGHT_PURPLE) + "DRAGON");
        }
        if (text.getString().contains("ꔤ")) {
            text = ReplaceUtil.replace(text, "ꔤ", String.valueOf(Formatting.RED) + "IMPERATOR");
        }
        if (text.getString().contains("ꔠ")) {
            text = ReplaceUtil.replace(text, "ꔠ", String.valueOf(Formatting.GOLD) + "MAGISTER");
        }
        if (text.getString().contains("ꔄ")) {
            text = ReplaceUtil.replace(text, "ꔄ", String.valueOf(Formatting.BLUE) + "HERO");
        }
        if (text.getString().contains("ꔒ")) {
            text = ReplaceUtil.replace(text, "ꔒ", String.valueOf(Formatting.GREEN) + "AVENGER");
        }
        if (text.getString().contains("ꕒ")) {
            text = ReplaceUtil.replace(text, "ꕒ", String.valueOf(Formatting.WHITE) + "RABBIT");
        }
        if (text.getString().contains("ꔈ")) {
            text = ReplaceUtil.replace(text, "ꔈ", String.valueOf(Formatting.YELLOW) + "TITAN");
        }
        if (text.getString().contains("ꕀ")) {
            text = ReplaceUtil.replace(text, "ꕀ", String.valueOf(Formatting.DARK_GREEN) + "HYDRA");
        }
        if (text.getString().contains("ꔶ")) {
            text = ReplaceUtil.replace(text, "ꔶ", String.valueOf(Formatting.GOLD) + "TIGER");
        }
        if (text.getString().contains("ꔲ")) {
            text = ReplaceUtil.replace(text, "ꔲ", String.valueOf(Formatting.DARK_PURPLE) + "BULL");
        }
        if (text.getString().contains("ꕖ")) {
            text = ReplaceUtil.replace(text, "ꕖ", String.valueOf(Formatting.BLACK) + "BUNNY");
        }
        if (text.getString().contains("ꕗꕘ")) {
            text = ReplaceUtil.replace(text, "ꕗꕘ", String.valueOf(Formatting.YELLOW) + "SPONSOR");
        }
        if (text.getString().contains("🔥")) {
            text = ReplaceUtil.replace(text, "🔥", "@");
        }
        if (text.getString().contains("ᴀ")) {
            text = ReplaceUtil.replace(text, "ᴀ", "A");
        }
        if (text.getString().contains("ʙ")) {
            text = ReplaceUtil.replace(text, "ʙ", "B");
        }
        if (text.getString().contains("ᴄ")) {
            text = ReplaceUtil.replace(text, "ᴄ", "C");
        }
        if (text.getString().contains("ᴅ")) {
            text = ReplaceUtil.replace(text, "ᴅ", "D");
        }
        if (text.getString().contains("ᴇ")) {
            text = ReplaceUtil.replace(text, "ᴇ", "E");
        }
        if (text.getString().contains("ғ")) {
            text = ReplaceUtil.replace(text, "ғ", "F");
        }
        if (text.getString().contains("ɢ")) {
            text = ReplaceUtil.replace(text, "ɢ", "G");
        }
        if (text.getString().contains("ʜ")) {
            text = ReplaceUtil.replace(text, "ʜ", "H");
        }
        if (text.getString().contains("ɪ")) {
            text = ReplaceUtil.replace(text, "ɪ", "I");
        }
        if (text.getString().contains("ᴊ")) {
            text = ReplaceUtil.replace(text, "ᴊ", "J");
        }
        if (text.getString().contains("ᴋ")) {
            text = ReplaceUtil.replace(text, "ᴋ", "K");
        }
        if (text.getString().contains("ʟ")) {
            text = ReplaceUtil.replace(text, "ʟ", "L");
        }
        if (text.getString().contains("ᴍ")) {
            text = ReplaceUtil.replace(text, "ᴍ", "M");
        }
        if (text.getString().contains("ɴ")) {
            text = ReplaceUtil.replace(text, "ɴ", "N");
        }
        if (text.getString().contains("ꜱ")) {
            text = ReplaceUtil.replace(text, "ꜱ", "S");
        }
        if (text.getString().contains("ᴏ")) {
            text = ReplaceUtil.replace(text, "ᴏ", "O");
        }
        if (text.getString().contains("ᴘ")) {
            text = ReplaceUtil.replace(text, "ᴘ", "P");
        }
        if (text.getString().contains("ǫ")) {
            text = ReplaceUtil.replace(text, "ǫ", "Q");
        }
        if (text.getString().contains("ʀ")) {
            text = ReplaceUtil.replace(text, "ʀ", "R");
        }
        if (text.getString().contains("ᴛ")) {
            text = ReplaceUtil.replace(text, "ᴛ", "T");
        }
        if (text.getString().contains("ᴜ")) {
            text = ReplaceUtil.replace(text, "ᴜ", "U");
        }
        if (text.getString().contains("ᴠ")) {
            text = ReplaceUtil.replace(text, "ᴠ", "V");
        }
        if (text.getString().contains("ᴡ")) {
            text = ReplaceUtil.replace(text, "ᴡ", "W");
        }
        if (text.getString().contains("ꜰ")) {
            text = ReplaceUtil.replace(text, "ꜰ", "F");
        }
        if (text.getString().contains("ʏ")) {
            text = ReplaceUtil.replace(text, "ʏ", "Y");
        }
        if (text.getString().contains("ᴢ")) {
            text = ReplaceUtil.replace(text, "ᴢ", "Z");
        }
        return text;
    }

    public static String toQwerty(String text) {
        return text.replace("й", "q").replace("ц", "w").replace("у", "e").replace("к", "r").replace("е", "t").replace("н", "y").replace("г", "u").replace("ш", "i").replace("щ", "o").replace("з", "p").replace("х", "[").replace("ъ", "]").replace("ф", "a").replace("ы", "s").replace("в", "d").replace("а", "f").replace("п", "g").replace("р", "h").replace("о", "j").replace("л", "k").replace("д", "l").replace("ж", ";").replace("э", "'").replace("я", "z").replace("ч", "x").replace("с", "c").replace("м", "v").replace("и", "b").replace("т", "n").replace("ь", "m").replace("б", ",").replace("ю", ".").replace("ё", "`").replace("Й", "Q").replace("Ц", "W").replace("У", "E").replace("К", "R").replace("Е", "T").replace("Н", "Y").replace("Г", "U").replace("Ш", "I").replace("Щ", "O").replace("З", "P").replace("Х", "{").replace("Ъ", "}").replace("Ф", "A").replace("Ы", "S").replace("В", "D").replace("А", "F").replace("П", "G").replace("Р", "H").replace("О", "J").replace("Л", "K").replace("Д", "L").replace("Ж", ":").replace("Э", "\"").replace("Я", "Z").replace("Ч", "X").replace("С", "C").replace("М", "V").replace("И", "B").replace("Т", "N").replace("Ь", "M").replace("Б", "<").replace("Ю", ">").replace("Ё", "~");
    }

    private record StyledChar(char c, Style style) {
    }
}

