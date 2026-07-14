/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.conversion.AbstractConvertedCommentedConfig;
import com.electronwill.nightconfig.core.conversion.ConversionTable;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConvertedCommentedFileConfig
extends AbstractConvertedCommentedConfig<CommentedFileConfig>
implements CommentedFileConfig {
    public ConvertedCommentedFileConfig(CommentedFileConfig config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
        this(config, readTable::convert, writeTable::convert, supportPredicate);
    }

    public ConvertedCommentedFileConfig(CommentedFileConfig config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
        super(config, readConversion, writeConversion, supportPredicate);
    }

    @Override
    public File getFile() {
        return ((CommentedFileConfig)this.config).getFile();
    }

    @Override
    public Path getNioPath() {
        return ((CommentedFileConfig)this.config).getNioPath();
    }

    @Override
    public void save() {
        ((CommentedFileConfig)this.config).save();
    }

    @Override
    public void load() {
        ((CommentedFileConfig)this.config).load();
    }

    @Override
    public void close() {
        ((CommentedFileConfig)this.config).close();
    }

    @Override
    public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
        return ((CommentedFileConfig)this.config).bulkRead(action);
    }

    @Override
    public <R> R bulkCommentedRead(Function<? super UnmodifiableCommentedConfig, R> action) {
        return ((CommentedFileConfig)this.config).bulkCommentedRead(action);
    }

    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> action) {
        return ((CommentedFileConfig)this.config).bulkCommentedUpdate(action);
    }

    @Override
    public <R> R bulkUpdate(Function<? super Config, R> action) {
        return ((CommentedFileConfig)this.config).bulkUpdate(action);
    }

    @Override
    public ConcurrentCommentedConfig createSubConfig() {
        return ((CommentedFileConfig)this.config).createSubConfig();
    }
}

