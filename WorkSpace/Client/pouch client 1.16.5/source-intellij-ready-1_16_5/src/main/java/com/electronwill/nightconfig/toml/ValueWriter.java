/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.core.io.CharacterOutput;
import com.electronwill.nightconfig.core.io.WritingException;
import com.electronwill.nightconfig.toml.ArrayWriter;
import com.electronwill.nightconfig.toml.StringWriter;
import com.electronwill.nightconfig.toml.TableWriter;
import com.electronwill.nightconfig.toml.TemporalWriter;
import com.electronwill.nightconfig.toml.TomlWriter;
import java.time.temporal.Temporal;
import java.util.List;

final class ValueWriter {
    private static void writeString(String string, CharacterOutput output, TomlWriter writer) {
        if (writer.writesLiteral(string)) {
            if (writer.writesMultiline(string)) {
                StringWriter.writeLiteralMultiline(string, output);
            } else {
                StringWriter.writeLiteral(string, output);
            }
        } else if (writer.writesMultiline(string)) {
            StringWriter.writeBasicMultiline(string, output, writer);
        } else {
            StringWriter.writeBasic(string, output);
        }
    }

    static void write(Object value, CharacterOutput output, TomlWriter writer) {
        if (value instanceof Config) {
            TableWriter.writeInline((Config)value, output, writer);
        } else if (value instanceof List) {
            ArrayWriter.write((List)value, output, writer);
        } else if (value instanceof CharSequence) {
            ValueWriter.writeString(value.toString(), output, writer);
        } else if (value instanceof Enum) {
            ValueWriter.writeString(((Enum)value).name(), output, writer);
        } else if (value instanceof Temporal) {
            TemporalWriter.write((Temporal)value, output);
        } else if (value instanceof Float || value instanceof Double) {
            double d = ((Number)value).doubleValue();
            if (Double.isNaN(d)) {
                output.write("nan");
            } else if (d == Double.POSITIVE_INFINITY) {
                output.write("+inf");
            } else if (d == Double.NEGATIVE_INFINITY) {
                output.write("-inf");
            } else {
                output.write(value.toString());
            }
        } else if (value instanceof Number || value instanceof Boolean) {
            output.write(value.toString());
        } else {
            if (value == null || value == NullObject.NULL_OBJECT) {
                throw new WritingException("TOML doesn't support null values");
            }
            throw new WritingException("Unsupported value type: " + String.valueOf(value.getClass()));
        }
    }

    private ValueWriter() {
    }
}

