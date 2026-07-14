/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.io.CharacterInput;
import com.electronwill.nightconfig.core.io.CharsWrapper;
import com.electronwill.nightconfig.core.io.ParsingException;
import com.electronwill.nightconfig.core.io.Utils;
import com.electronwill.nightconfig.toml.ArrayParser;
import com.electronwill.nightconfig.toml.StringParser;
import com.electronwill.nightconfig.toml.TableParser;
import com.electronwill.nightconfig.toml.TemporalParser;
import com.electronwill.nightconfig.toml.Toml;
import com.electronwill.nightconfig.toml.TomlParser;

final class ValueParser {
    private static final char[] END_OF_VALUE = new char[]{'\t', ' ', '#', '\n', '\r', ',', ']', '}'};
    private static final char[] END_OF_VALUE_DATE = new char[]{'\t', '#', '\n', '\r', ',', ']', '}'};
    private static final char[] TRUE_END = new char[]{'r', 'u', 'e'};
    private static final char[] FALSE_END = new char[]{'a', 'l', 's', 'e'};
    private static final char[] ONLY_IN_FP_NUMBER = new char[]{'.', 'e', 'E'};
    private static final char[] FP_INFINITY = new char[]{'i', 'n', 'f'};
    private static final char[] FP_NAN = new char[]{'n', 'a', 'n'};

    static Object parse(CharacterInput input, char firstChar, TomlParser parser, CommentedConfig parentConfig) {
        switch (firstChar) {
            case '{': {
                return TableParser.parseInline(input, parser, parentConfig.createSubConfig());
            }
            case '[': {
                return ArrayParser.parse(input, parser, parentConfig);
            }
            case '\'': {
                if (input.peek() == 39 && input.peek(1) == 39) {
                    input.skipPeeks();
                    return StringParser.parseMultiLiteral(input, parser);
                }
                return StringParser.parseLiteral(input, parser);
            }
            case '\"': {
                if (input.peek() == 34 && input.peek(1) == 34) {
                    input.skipPeeks();
                    return StringParser.parseMultiBasic(input, parser);
                }
                return StringParser.parseBasic(input, parser);
            }
            case 't': {
                return ValueParser.parseTrue(input);
            }
            case 'f': {
                return ValueParser.parseFalse(input);
            }
            case '+': 
            case '-': {
                input.pushBack(firstChar);
                return ValueParser.parseNumber(input.readUntil(END_OF_VALUE));
            }
        }
        input.pushBack(firstChar);
        CharsWrapper valueChars = input.readUntil(END_OF_VALUE_DATE);
        if (ValueParser.shouldBeTemporal(valueChars)) {
            return TemporalParser.parse(valueChars);
        }
        CharsWrapper trimmed = valueChars.trimmedView();
        if (trimmed.isEmpty()) {
            throw new ParsingException("Invalid value containing only whitespaces");
        }
        return ValueParser.parseNumber(trimmed);
    }

    static Object parse(CharacterInput input, TomlParser parser, CommentedConfig parentConfig) {
        return ValueParser.parse(input, Toml.readNonSpaceChar(input, false), parser, parentConfig);
    }

    private static boolean shouldBeTemporal(CharsWrapper valueChars) {
        return valueChars.length() >= 8 && (valueChars.get(2) == ':' || valueChars.get(4) == '-' && valueChars.get(7) == '-');
    }

    private static Number parseNumber(CharsWrapper valueChars) {
        long longValue;
        CharsWrapper remaining;
        char first = valueChars.get(0);
        if (first == '-') {
            remaining = valueChars.subView(1);
            if (remaining.contentEquals(FP_INFINITY)) {
                return Double.NEGATIVE_INFINITY;
            }
        } else {
            remaining = first == '+' ? valueChars.subView(1) : valueChars;
        }
        if (remaining.contentEquals(FP_INFINITY)) {
            return Double.POSITIVE_INFINITY;
        }
        if (remaining.contentEquals(FP_NAN)) {
            return Double.NaN;
        }
        CharsWrapper numberChars = valueChars;
        int base = 10;
        if (valueChars.length() > 2) {
            switch (valueChars.subView(0, 2).toString()) {
                case "0x": {
                    base = 16;
                    break;
                }
                case "0b": {
                    base = 2;
                    break;
                }
                case "0o": {
                    base = 8;
                }
            }
            if (base != 10) {
                numberChars = valueChars.subView(2);
            }
        }
        numberChars = ValueParser.simplifyNumber(numberChars, base);
        if (base == 10 && numberChars.indexOfFirst(ONLY_IN_FP_NUMBER) != -1) {
            if (numberChars.length() > 1) {
                if (numberChars.get(0) == '0' && ValueParser.isDigitChar(numberChars.get(1), 10)) {
                    throw new ParsingException("Invalid leading zero in floating-point number " + String.valueOf(numberChars));
                }
                if (numberChars.length() > 2 && (numberChars.startsWith("-0") || numberChars.startsWith("+0")) && ValueParser.isDigitChar(numberChars.get(2), 10)) {
                    throw new ParsingException("Invalid leading zero (after sign) in floating-point number " + String.valueOf(numberChars));
                }
            }
            try {
                return Utils.parseDouble(numberChars);
            }
            catch (NumberFormatException ex) {
                throw new ParsingException("Invalid floating-point value: " + String.valueOf(valueChars));
            }
        }
        if (base == 10) {
            if (numberChars.length() > 1 && numberChars.get(0) == '0') {
                throw new ParsingException("Invalid leading zero in base 10 integer " + String.valueOf(numberChars));
            }
            if (numberChars.length() > 2 && (numberChars.startsWith("-0") || numberChars.startsWith("+0")) && ValueParser.isDigitChar(numberChars.get(2), 10)) {
                throw new ParsingException("Invalid leading zero (after sign) in base 10 integer " + String.valueOf(numberChars));
            }
        }
        try {
            longValue = Utils.parseLong(numberChars, base);
        }
        catch (NumberFormatException ex) {
            throw new ParsingException("Invalid integer value: " + String.valueOf(valueChars));
        }
        int intValue = (int)longValue;
        if ((long)intValue == longValue) {
            return intValue;
        }
        return longValue;
    }

    private static boolean isDigitChar(char c, int base) {
        switch (base) {
            case 2: {
                return c == '0' || c == '1';
            }
            case 8: {
                return c >= '0' && c <= '7';
            }
            case 10: {
                return c >= '0' && c <= '9';
            }
            case 16: {
                return c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
            }
        }
        throw new IllegalArgumentException("Unsupported base " + base);
    }

    private static CharsWrapper simplifyNumber(CharsWrapper numberChars, int base) {
        CharsWrapper.Builder builder = new CharsWrapper.Builder(16);
        int previousChar = -1;
        for (char c : numberChars) {
            switch (c) {
                case '_': {
                    if (previousChar == -1) {
                        throw new ParsingException("Invalid leading underscore in number " + String.valueOf(numberChars));
                    }
                    if (ValueParser.isDigitChar((char)previousChar, base)) break;
                    throw new ParsingException("Invalid underscore in number (each underscore must be surrounded by digits): " + String.valueOf(numberChars));
                }
                case '.': {
                    if (previousChar == -1) {
                        throw new ParsingException("Invalid leading decimal point in number " + String.valueOf(numberChars));
                    }
                    if (!ValueParser.isDigitChar((char)previousChar, base)) {
                        throw new ParsingException("Invalid decimal point in number (each decimal point must be surrounded by digits): " + String.valueOf(numberChars));
                    }
                    builder.append(c);
                    break;
                }
                case 'e': {
                    if (base != 16 && !ValueParser.isDigitChar((char)previousChar, base)) {
                        throw new ParsingException("Invalid sequence " + previousChar + "e in number" + String.valueOf(numberChars));
                    }
                }
                default: {
                    builder.append(c);
                }
            }
            previousChar = c;
        }
        if (previousChar == 95) {
            throw new ParsingException("Invalid trailing underscore in number: " + String.valueOf(numberChars));
        }
        if (previousChar == 46) {
            throw new ParsingException("Invalid trailing decimal point in number: " + String.valueOf(numberChars));
        }
        if (previousChar == 101 || previousChar == 69 || previousChar == 45 || previousChar == 43) {
            throw new ParsingException("Invalid trailing '" + previousChar + "' in number: " + String.valueOf(numberChars));
        }
        return builder.build();
    }

    private static Boolean parseFalse(CharacterInput input) {
        CharsWrapper remaining = input.readUntil(END_OF_VALUE);
        if (!remaining.contentEquals(FALSE_END)) {
            throw new ParsingException("Invalid value f" + String.valueOf(remaining) + " - Expected the boolean value false.");
        }
        return false;
    }

    private static Boolean parseTrue(CharacterInput input) {
        CharsWrapper remaining = input.readUntil(END_OF_VALUE);
        if (!remaining.contentEquals(TRUE_END)) {
            throw new ParsingException("Invalid value t" + String.valueOf(remaining) + " - Expected the boolean value true.");
        }
        return true;
    }

    private ValueParser() {
    }
}

