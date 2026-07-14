/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.io.CharacterInput;
import com.electronwill.nightconfig.core.io.ParsingException;
import com.electronwill.nightconfig.toml.Toml;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.ValueParser;
import java.util.List;

final class ArrayParser {
    static List<?> parse(CharacterInput input, TomlParser parser, CommentedConfig parentConfig) {
        List list = parser.createList();
        boolean first = true;
        char firstChar;
        while ((firstChar = Toml.readUsefulChar(input)) != ']') {
            if (firstChar == ',') {
                if (first) {
                    throw new ParsingException("Invalid array: [,]");
                }
                throw new ParsingException("Invalid double comma in array.");
            }
            Object value = ValueParser.parse(input, firstChar, parser, parentConfig);
            list.add(value);
            char after = Toml.readUsefulChar(input);
            if (after == ']') {
                return list;
            }
            if (after != ',') {
                throw new ParsingException("Invalid separator '" + after + "' in array.");
            }
            first = false;
        }
        return list;
    }

    private ArrayParser() {
    }
}

