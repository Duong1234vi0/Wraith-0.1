/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.io.CharacterInput;
import com.electronwill.nightconfig.core.io.CharsWrapper;
import com.electronwill.nightconfig.core.io.ParsingException;
import com.electronwill.nightconfig.core.io.Utils;
import com.electronwill.nightconfig.toml.Toml;
import com.electronwill.nightconfig.toml.TomlParser;

final class StringParser {
    private static final char[] SINGLE_QUOTE_OR_NEWLINE = new char[]{'\'', '\n', '\r'};

    static String parseBasic(CharacterInput input, TomlParser parser) {
        char c;
        CharsWrapper.Builder builder = parser.createBuilder();
        boolean escape = false;
        while ((c = input.readChar()) != '\"' || escape) {
            if (escape) {
                builder.write(StringParser.unescape(c, input));
                escape = false;
                continue;
            }
            if (c == '\\') {
                escape = true;
                continue;
            }
            if (c == '\n' || c == '\r') {
                throw new ParsingException("Invalid newline in basic string, you should use a multiline string or escape the newline by writing \\n. The string begins with: \"" + String.valueOf(builder) + "\"");
            }
            if (c != '\t' && Toml.isControlChar(c)) {
                String properEscape = "\\u" + Integer.toHexString(c).toUpperCase();
                throw new ParsingException("Invalid control character '" + c + "' in string, you should escape it by writing " + properEscape);
            }
            builder.write(c);
        }
        return builder.toString();
    }

    static String parseLiteral(CharacterInput input, TomlParser parser) {
        String str = input.readCharsUntil(SINGLE_QUOTE_OR_NEWLINE).toString();
        char end = input.readChar();
        if (end != '\'') {
            throw new ParsingException("Invalid newline in literal string, you should use a multiline string. The string is '" + str + "'");
        }
        str.codePoints().forEach(codePoint -> {
            if (codePoint != 9 && Toml.isControlChar(codePoint)) {
                String properEscape = "\\u" + Integer.toHexString(codePoint).toUpperCase();
                CharsWrapper display = new CharsWrapper(Character.toChars(codePoint));
                throw new ParsingException("Invalid control character '" + String.valueOf(display) + "' in literal string '" + str + "', you should escape it by writing " + properEscape);
            }
        });
        return str;
    }

    static String parseMultiBasic(CharacterInput input, TomlParser parser) {
        char c;
        CharsWrapper.Builder builder = parser.createBuilder();
        while ((c = input.readChar()) != '\"' || input.peek() != 34 || input.peek(1) != 34) {
            if (c == '\\') {
                char next = input.readChar();
                if (next == '\n' || next == '\r' && input.peekChar() == '\n' || (next == '\t' || next == ' ') && StringParser.isWhitespace(Toml.readLine(input))) {
                    char nextNonSpace = Toml.readNonSpaceChar(input, true);
                    input.pushBack(nextNonSpace);
                    continue;
                }
                if (next == '\t' || next == ' ') {
                    throw new ParsingException("Invalid escapement: \\" + next);
                }
                builder.write(StringParser.unescape(next, input));
                continue;
            }
            if (c != '\n' && c != '\r' && c != '\t' && Toml.isControlChar(c)) {
                String properEscape = "\\u" + Integer.toHexString(c).toUpperCase();
                throw new ParsingException("Invalid control character '" + c + "' in multiline string, you should escape it by writing " + properEscape);
            }
            builder.write(c);
        }
        input.skipPeeks();
        if (input.peek() == 34) {
            input.skipPeeks();
            builder.write('\"');
        }
        if (input.peek() == 34) {
            input.skipPeeks();
            builder.write('\"');
        }
        return StringParser.buildMultilineString(builder);
    }

    static String parseMultiLiteral(CharacterInput input, TomlParser parser) {
        char c;
        CharsWrapper.Builder builder = parser.createBuilder();
        while ((c = input.readChar()) != '\'' || input.peek() != 39 || input.peek(1) != 39) {
            if (c != '\n' && c != '\r' && c != '\t' && Toml.isControlChar(c)) {
                String properEscape = "\\u" + Integer.toHexString(c).toUpperCase();
                throw new ParsingException("Invalid control character '" + c + "' in multiline literal string, you should escape it by writing " + properEscape);
            }
            builder.append(c);
        }
        input.skipPeeks();
        if (input.peek() == 39) {
            input.skipPeeks();
            builder.write('\'');
        }
        if (input.peek() == 39) {
            input.skipPeeks();
            builder.write('\'');
        }
        return StringParser.buildMultilineString(builder);
    }

    private static String buildMultilineString(CharsWrapper.Builder builder) {
        if (builder.get(0) == '\n') {
            return builder.toString(1);
        }
        if (builder.get(0) == '\r' && builder.get(1) == '\n') {
            return builder.toString(2);
        }
        return builder.toString();
    }

    private static String unescape(char c, CharacterInput input) {
        switch (c) {
            case '\"': 
            case '\\': {
                return String.valueOf(c);
            }
            case 'b': {
                return "\b";
            }
            case 'f': {
                return "\f";
            }
            case 'n': {
                return "\n";
            }
            case 'r': {
                return "\r";
            }
            case 't': {
                return "\t";
            }
            case 'u': {
                CharsWrapper chars = input.readChars(4);
                return StringParser.parseUnicodeCodepoint(chars);
            }
            case 'U': {
                CharsWrapper chars = input.readChars(8);
                return StringParser.parseUnicodeCodepoint(chars);
            }
        }
        throw new ParsingException("Invalid escapement: \\" + c);
    }

    private static String parseUnicodeCodepoint(CharsWrapper chars) {
        try {
            int codePoint = Utils.parseInt(chars, 16);
            if (!Toml.isValidCodePoint(codePoint)) {
                throw new ParsingException("Invalid unicode codepoint: " + String.valueOf(chars));
            }
            return new String(new int[]{codePoint}, 0, 1);
        }
        catch (IllegalArgumentException ex) {
            throw new ParsingException("Invalid unicode codepoint: " + String.valueOf(chars), ex);
        }
    }

    private static boolean isWhitespace(CharSequence csq) {
        for (int i = 0; i < csq.length(); ++i) {
            char c = csq.charAt(i);
            if (c == '\t' || c == ' ') continue;
            return false;
        }
        return true;
    }

    private StringParser() {
    }
}

