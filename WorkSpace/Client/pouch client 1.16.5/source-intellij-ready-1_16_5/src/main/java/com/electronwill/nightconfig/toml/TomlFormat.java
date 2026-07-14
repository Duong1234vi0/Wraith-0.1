/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.toml;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.FormatDetector;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.TomlWriter;
import java.time.temporal.Temporal;
import java.util.Map;
import java.util.function.Supplier;

public final class TomlFormat
implements ConfigFormat<CommentedConfig> {
    private static final TomlFormat INSTANCE = new TomlFormat();

    public static TomlFormat instance() {
        return INSTANCE;
    }

    public static CommentedConfig newConfig() {
        return (CommentedConfig)INSTANCE.createConfig();
    }

    public static CommentedConfig newConfig(Supplier<Map<String, Object>> s) {
        return INSTANCE.createConfig((Supplier)s);
    }

    public static CommentedConfig newConcurrentConfig() {
        return (CommentedConfig)INSTANCE.createConcurrentConfig();
    }

    private TomlFormat() {
    }

    @Override
    public TomlWriter createWriter() {
        return new TomlWriter();
    }

    public TomlParser createParser() {
        return new TomlParser();
    }

    @Override
    public CommentedConfig createConfig(Supplier<Map<String, Object>> mapCreator) {
        return CommentedConfig.of(mapCreator, this);
    }

    @Override
    public boolean supportsComments() {
        return true;
    }

    @Override
    public boolean supportsType(Class<?> type) {
        return type != null && (ConfigFormat.super.supportsType(type) || Temporal.class.isAssignableFrom(type));
    }

    static {
        FormatDetector.registerExtension("toml", INSTANCE);
    }
}

