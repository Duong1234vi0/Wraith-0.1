/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.io.CharacterInput;
import com.electronwill.nightconfig.core.io.CharsWrapper;
import com.electronwill.nightconfig.core.io.Utils;
import java.util.List;

final class Toml {
    private static final char[] WHITESPACE_OR_NEWLINE = new char[]{'\t', ' ', '\n', '\r'};
    private static final char[] WHITESPACE = new char[]{'\t', ' '};
    private static final char[] NEWLINE = new char[]{'\n'};
    private static final char[] FORBIDDEN_IN_ALL_BARE_KEYS = new char[]{'.', '[', ']', '#', '='};

    static char readUsefulChar(CharacterInput input) {
        char next = input.readCharAndSkip(WHITESPACE_OR_NEWLINE);
        while (next == '#') {
            input.readCharsUntil(NEWLINE);
            next = input.readCharAndSkip(WHITESPACE_OR_NEWLINE);
        }
        return next;
    }

    static int readUseful(CharacterInput input, List<CharsWrapper> commentsList) {
        int next = input.readAndSkip(WHITESPACE_OR_NEWLINE);
        while (next == 35) {
            CharsWrapper comment = Toml.readLine(input);
            commentsList.add(comment);
            next = input.readAndSkip(WHITESPACE_OR_NEWLINE);
        }
        return next;
    }

    static char readNonSpaceChar(CharacterInput input, boolean skipNewlines) {
        return skipNewlines ? input.readCharAndSkip(WHITESPACE_OR_NEWLINE) : input.readCharAndSkip(WHITESPACE);
    }

    static int readNonSpace(CharacterInput input, boolean skipNewlines) {
        return skipNewlines ? input.readAndSkip(WHITESPACE_OR_NEWLINE) : input.readAndSkip(WHITESPACE);
    }

    static CharsWrapper readLine(CharacterInput input) {
        CharsWrapper chars = input.readUntil(NEWLINE);
        int lastIndex = chars.length() - 1;
        if (lastIndex >= 0 && chars.get(lastIndex) == '\r') {
            return chars.subView(0, lastIndex);
        }
        return chars;
    }

    static boolean isControlChar(char c) {
        return (c <= '\u001f' || c == '\u007f') && !Character.isSurrogate(c);
    }

    static boolean isControlChar(int c) {
        return c <= 31 || c == 127;
    }

    static boolean isValidCodePoint(int c) {
        return c <= 55295 || c >= 57344 && c <= 0x10FFFF;
    }

    static boolean isValidInBareKey(char c, boolean lenient) {
        if (lenient) {
            return c > ' ' && !Utils.arrayContains(FORBIDDEN_IN_ALL_BARE_KEYS, c) && !Toml.isControlChar(c);
        }
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '-' || c == '_';
    }

    static boolean isValidBareKey(CharSequence csq, boolean lenient) {
        int len = csq.length();
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; ++i) {
            if (Toml.isValidInBareKey(csq.charAt(i), lenient)) continue;
            return false;
        }
        return true;
    }

    static boolean isKeyValueSeparator(char c, boolean lenient) {
        return c == '=' || lenient && c == ':';
    }

    private Toml() {
    }
}

