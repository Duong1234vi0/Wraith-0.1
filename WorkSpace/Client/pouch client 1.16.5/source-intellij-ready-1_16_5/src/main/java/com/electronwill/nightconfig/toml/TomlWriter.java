/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.io.CharacterOutput;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.core.io.IndentStyle;
import com.electronwill.nightconfig.core.io.NewlineStyle;
import com.electronwill.nightconfig.core.io.WriterOutput;
import com.electronwill.nightconfig.core.utils.StringUtils;
import com.electronwill.nightconfig.toml.StringWriter;
import com.electronwill.nightconfig.toml.TableWriter;
import com.electronwill.nightconfig.toml.Toml;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class TomlWriter
implements ConfigWriter {
    private boolean lenientBareKeys = false;
    private Predicate<UnmodifiableConfig> writeTableInlinePredicate = UnmodifiableConfig::isEmpty;
    private Predicate<String> writeStringLiteralPredicate = str -> false;
    private Predicate<String> writeStringMultilinePredicate = str -> {
        int i0 = str.indexOf(10);
        int i1 = str.indexOf(10, i0 + 1);
        int i2 = str.indexOf(10, i1 + 1);
        int cr = str.indexOf(13);
        return cr == -1 && (i0 >= 0 && i1 > 0 && i2 > 0 || i0 > 0 && i0 < str.length() - 1);
    };
    private Predicate<List<?>> indentArrayElementsPredicate = elem -> false;
    private char[] indent;
    private char[] newline;
    private boolean hideRedundantLevels;
    private int currentIndentLevel;

    public TomlWriter() {
        this.indent = IndentStyle.TABS.chars;
        this.newline = NewlineStyle.system().chars;
        this.hideRedundantLevels = true;
    }

    @Override
    public void write(UnmodifiableConfig config, Writer writer) {
        this.currentIndentLevel = -1;
        WriterOutput output = new WriterOutput(writer);
        TableWriter.writeTopLevel(config, new ArrayList<String>(), output, this);
    }

    public boolean isHidingRedundantLevels() {
        return this.hideRedundantLevels;
    }

    public void setHideRedundantLevels(boolean hideRedundantLevels) {
        this.hideRedundantLevels = hideRedundantLevels;
    }

    public boolean isOmitIntermediateLevels() {
        return this.hideRedundantLevels;
    }

    public void setOmitIntermediateLevels(boolean omitIntermediateLevels) {
        this.setHideRedundantLevels(omitIntermediateLevels);
    }

    public boolean isLenientWithBareKeys() {
        return this.lenientBareKeys;
    }

    public void setLenientWithBareKeys(boolean lenientBareKeys) {
        this.lenientBareKeys = lenientBareKeys;
    }

    public void setWriteTableInlinePredicate(Predicate<UnmodifiableConfig> predicate) {
        this.writeTableInlinePredicate = predicate;
    }

    public void setWriteStringLiteralPredicate(Predicate<String> predicate) {
        this.writeStringLiteralPredicate = predicate;
    }

    public void setWriteStringMultilinePredicate(Predicate<String> predicate) {
        this.writeStringMultilinePredicate = predicate;
    }

    public void setIndentArrayElementsPredicate(Predicate<List<?>> predicate) {
        this.indentArrayElementsPredicate = predicate;
    }

    public void setIndent(IndentStyle indentStyle) {
        this.indent = indentStyle.chars;
    }

    public void setIndent(String indentString) {
        this.indent = indentString.toCharArray();
    }

    public void setNewline(NewlineStyle newlineStyle) {
        this.newline = newlineStyle.chars;
    }

    public void setNewline(String newlineString) {
        this.newline = newlineString.toCharArray();
    }

    void increaseIndentLevel() {
        ++this.currentIndentLevel;
    }

    void decreaseIndentLevel() {
        --this.currentIndentLevel;
    }

    void writeIndent(CharacterOutput output) {
        for (int i = 0; i < this.currentIndentLevel; ++i) {
            output.write(this.indent);
        }
    }

    void writeNewline(CharacterOutput output) {
        output.write(this.newline);
    }

    void writeIndentedComment(String commentString, CharacterOutput output) {
        List<String> comments = StringUtils.splitLines(commentString);
        for (String comment : comments) {
            this.writeIndent(output);
            output.write('#');
            output.write(comment);
            output.write(this.newline);
        }
    }

    void writeIndentedKey(String key, CharacterOutput output) {
        this.writeIndent(output);
        this.writeKey(key, output);
    }

    void writeKey(String key, CharacterOutput output) {
        if (Toml.isValidBareKey(key, this.lenientBareKeys)) {
            output.write(key);
        } else if (this.writeStringLiteralPredicate.test(key)) {
            StringWriter.writeLiteral(key, output);
        } else {
            StringWriter.writeBasic(key, output);
        }
    }

    boolean writesInline(UnmodifiableConfig config) {
        return this.writeTableInlinePredicate.test(config);
    }

    boolean writesLiteral(String string) {
        return this.writeStringLiteralPredicate.test(string);
    }

    boolean writesMultiline(String string) {
        return this.writeStringMultilinePredicate.test(string);
    }

    boolean writesIndented(List<?> list) {
        return this.indentArrayElementsPredicate.test(list);
    }
}

