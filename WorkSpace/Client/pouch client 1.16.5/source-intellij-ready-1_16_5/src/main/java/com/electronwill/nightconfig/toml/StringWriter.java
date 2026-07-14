/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.io.CharacterOutput;
import com.electronwill.nightconfig.core.utils.StringUtils;
import com.electronwill.nightconfig.toml.Toml;
import com.electronwill.nightconfig.toml.TomlWriter;
import java.util.Iterator;

final class StringWriter {
    private static final char[] ESCAPED_B = new char[]{'\\', 'b'};
    private static final char[] ESCAPED_F = new char[]{'\\', 'f'};
    private static final char[] ESCAPED_N = new char[]{'\\', 'n'};
    private static final char[] ESCAPED_R = new char[]{'\\', 'r'};
    private static final char[] ESCAPED_T = new char[]{'\\', 't'};
    private static final char[] ESCAPED_BACKSLASH = new char[]{'\\', '\\'};
    private static final char[] ESCAPED_QUOTE = new char[]{'\\', '\"'};

    static void writeBasic(String str, CharacterOutput output) {
        output.write('\"');
        str.codePoints().forEach(c -> StringWriter.writeBasicChar(c, output));
        output.write('\"');
    }

    static void writeBasicMultiline(String str, CharacterOutput output, TomlWriter writer) {
        output.write("\"\"\"");
        Iterator<String> it = StringUtils.splitLines(str).iterator();
        while (it.hasNext()) {
            String line = it.next();
            writer.writeNewline(output);
            char[] chars = line.toCharArray();
            block7: for (int i = 0; i < chars.length; ++i) {
                char c = chars[i];
                switch (c) {
                    case '\"': {
                        if (i + 1 == chars.length && !it.hasNext() || i + 1 < chars.length && chars[i + 1] == '\"' && i + 2 < chars.length && chars[i + 2] == '\"' && i + 3 < chars.length) {
                            output.write(ESCAPED_QUOTE);
                            continue block7;
                        }
                        output.write(c);
                        continue block7;
                    }
                    case '\b': {
                        output.write(ESCAPED_B);
                        continue block7;
                    }
                    case '\f': {
                        output.write(ESCAPED_F);
                        continue block7;
                    }
                    case '\\': {
                        output.write(ESCAPED_BACKSLASH);
                        continue block7;
                    }
                    default: {
                        if (c != '\t' && c != '\n' && c != '\r' && Toml.isControlChar(c)) {
                            output.write(StringWriter.escapeUnicode(c));
                            continue block7;
                        }
                        output.write(Character.toChars(c));
                    }
                }
            }
        }
        output.write("\"\"\"");
    }

    static void writeLiteral(String str, CharacterOutput output) {
        output.write('\'');
        output.write(str);
        output.write('\'');
    }

    static void writeLiteralMultiline(String str, CharacterOutput output) {
        output.write("'''\n");
        output.write(str);
        output.write("''''");
    }

    private static void writeBasicChar(int c, CharacterOutput output) {
        switch (c) {
            case 92: {
                output.write(ESCAPED_BACKSLASH);
                break;
            }
            case 34: {
                output.write(ESCAPED_QUOTE);
                break;
            }
            case 8: {
                output.write(ESCAPED_B);
                break;
            }
            case 12: {
                output.write(ESCAPED_F);
                break;
            }
            case 10: {
                output.write(ESCAPED_N);
                break;
            }
            case 13: {
                output.write(ESCAPED_R);
                break;
            }
            case 9: {
                output.write(ESCAPED_T);
                break;
            }
            default: {
                if (Toml.isControlChar(c)) {
                    output.write(StringWriter.escapeUnicode(c));
                    break;
                }
                output.write(Character.toChars(c));
            }
        }
    }

    static String escapeUnicode(int codePoint) {
        Object hexa;
        block3: {
            block2: {
                hexa = Integer.toHexString(codePoint).toUpperCase();
                if (((String)hexa).length() >= 4) break block2;
                while (((String)hexa).length() < 4) {
                    hexa = "0" + (String)hexa;
                }
                break block3;
            }
            if (((String)hexa).length() >= 8) break block3;
            while (((String)hexa).length() < 8) {
                hexa = "0" + (String)hexa;
            }
        }
        return "\\u" + (String)hexa;
    }

    private StringWriter() {
    }
}

