/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentConfig;
import com.electronwill.nightconfig.core.conversion.AbstractConvertedConfig;
import com.electronwill.nightconfig.core.conversion.ConversionTable;
import com.electronwill.nightconfig.core.file.FileConfig;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertedFileConfig
extends AbstractConvertedConfig<FileConfig>
implements FileConfig {
    public ConvertedFileConfig(FileConfig config, ConversionTable readTable, ConversionTable writeTable, Predicate<Class<?>> supportPredicate) {
        this(config, readTable::convert, writeTable::convert, supportPredicate);
    }

    public ConvertedFileConfig(FileConfig config, Function<Object, Object> readConversion, Function<Object, Object> writeConversion, Predicate<Class<?>> supportPredicate) {
        super(config, readConversion, writeConversion, supportPredicate);
    }

    @Override
    public File getFile() {
        return ((FileConfig)this.config).getFile();
    }

    @Override
    public Path getNioPath() {
        return ((FileConfig)this.config).getNioPath();
    }

    @Override
    public void save() {
        ((FileConfig)this.config).save();
    }

    @Override
    public void load() {
        ((FileConfig)this.config).load();
    }

    @Override
    public void close() {
        ((FileConfig)this.config).close();
    }

    @Override
    public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
        return ((FileConfig)this.config).bulkRead(action);
    }

    @Override
    public <R> R bulkUpdate(Function<? super Config, R> action) {
        return ((FileConfig)this.config).bulkUpdate(action);
    }

    @Override
    public ConcurrentConfig createSubConfig() {
        return ((FileConfig)this.config).createSubConfig();
    }
}

