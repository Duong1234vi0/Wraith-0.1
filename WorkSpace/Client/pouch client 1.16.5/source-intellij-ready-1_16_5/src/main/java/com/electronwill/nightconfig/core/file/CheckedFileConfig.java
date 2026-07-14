/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.utils.ConfigWrapper;
import com.electronwill.nightconfig.core.utils.TransformingMap;
import com.electronwill.nightconfig.core.utils.TransformingSet;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

class CheckedFileConfig
extends ConfigWrapper<FileConfig>
implements FileConfig {
    CheckedFileConfig(FileConfig config) {
        super(config);
    }

    @Override
    public Path getNioPath() {
        return ((FileConfig)this.config).getNioPath();
    }

    @Override
    public File getFile() {
        return ((FileConfig)this.config).getFile();
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
    public FileConfig checked() {
        return this;
    }

    @Override
    public <T> T set(List<String> path, Object value) {
        return super.set(path, this.checkedValue(value));
    }

    @Override
    public boolean add(List<String> path, Object value) {
        return super.add(path, this.checkedValue(value));
    }

    @Override
    public Map<String, Object> valueMap() {
        return new TransformingMap<String, Object, Object>(super.valueMap(), v -> v, this::checkedValue, o -> o);
    }

    @Override
    public Set<? extends Config.Entry> entrySet() {
        return new TransformingSet<Config.Entry, Config.Entry>(super.entrySet(), v -> v, this::checkedValue, o -> o);
    }

    @Override
    public String toString() {
        return "checked of " + String.valueOf(this.config);
    }

    @Override
    public ConcurrentConfig createSubConfig() {
        return ((FileConfig)this.config).createSubConfig();
    }

    private void checkValue(Object value) {
        ConfigFormat<?> format = this.configFormat();
        if (value != null && !format.supportsType(value.getClass())) {
            throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getTypeName());
        }
        if (value == null && !format.supportsType(null)) {
            throw new IllegalArgumentException("Null values aren't supported by this configuration.");
        }
        if (value instanceof Config) {
            ((Config)value).valueMap().forEach((k, v) -> this.checkValue(v));
        }
    }

    private <T> T checkedValue(T value) {
        this.checkValue(value);
        return value;
    }
}

