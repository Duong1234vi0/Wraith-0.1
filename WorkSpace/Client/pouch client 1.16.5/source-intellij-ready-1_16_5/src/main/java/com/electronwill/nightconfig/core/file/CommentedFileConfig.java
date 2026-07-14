/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.file.CheckedCommentedFileConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfigBuilder;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.file.FormatDetector;
import com.electronwill.nightconfig.core.file.NoFormatFoundException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Function;

public interface CommentedFileConfig
extends ConcurrentCommentedConfig,
FileConfig {
    @Override
    default public CommentedFileConfig checked() {
        return new CheckedCommentedFileConfig(this);
    }

    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> var1);

    @Override
    default public void bulkCommentedUpdate(Consumer<? super CommentedConfig> action) {
        this.bulkCommentedUpdate((? super CommentedConfig config) -> {
            action.accept((CommentedConfig)config);
            return null;
        });
    }

    @Override
    default public <R> R bulkUpdate(Function<? super Config, R> action) {
        return this.bulkCommentedUpdate(action);
    }

    public static CommentedFileConfig of(File file) {
        return CommentedFileConfig.of(file.toPath());
    }

    public static CommentedFileConfig of(File file, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.of(file.toPath(), format);
    }

    public static CommentedFileConfig of(Path file) {
        ConfigFormat<?> format = FormatDetector.detect(file);
        if (format == null || !format.supportsComments()) {
            throw new NoFormatFoundException("No suitable format for " + String.valueOf(file.getFileName()));
        }
        return CommentedFileConfig.of(file, format);
    }

    public static CommentedFileConfig of(Path file, ConfigFormat<? extends CommentedConfig> format) {
        return (CommentedFileConfig)CommentedFileConfig.builder(file, format).build();
    }

    public static CommentedFileConfig of(String filePath) {
        return CommentedFileConfig.of(Paths.get(filePath, new String[0]));
    }

    public static CommentedFileConfig of(String filePath, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.of(Paths.get(filePath, new String[0]), format);
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(File file) {
        return CommentedFileConfig.ofConcurrent(file.toPath());
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(File file, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.ofConcurrent(file.toPath(), format);
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(Path file) {
        return (CommentedFileConfig)CommentedFileConfig.builder(file).concurrent().build();
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(Path file, ConfigFormat<? extends CommentedConfig> format) {
        return (CommentedFileConfig)CommentedFileConfig.builder(file, format).concurrent().build();
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(String filePath, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.ofConcurrent(Paths.get(filePath, new String[0]), format);
    }

    @Deprecated
    public static CommentedFileConfig ofConcurrent(String filePath) {
        return CommentedFileConfig.ofConcurrent(Paths.get(filePath, new String[0]));
    }

    public static CommentedFileConfigBuilder builder(File file, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.builder(file.toPath(), format);
    }

    public static CommentedFileConfigBuilder builder(File file) {
        return CommentedFileConfig.builder(file.toPath());
    }

    public static CommentedFileConfigBuilder builder(Path file, ConfigFormat<? extends CommentedConfig> format) {
        return new CommentedFileConfigBuilder(file, format);
    }

    public static CommentedFileConfigBuilder builder(Path file) {
        ConfigFormat<?> format = FormatDetector.detect(file);
        if (format == null) {
            throw new NoFormatFoundException("No suitable format for " + String.valueOf(file.getFileName()));
        }
        if (!format.supportsComments()) {
            throw new NoFormatFoundException("The available format doesn't support comments for " + String.valueOf(file.getFileName()));
        }
        return CommentedFileConfig.builder(file, format);
    }

    public static CommentedFileConfigBuilder builder(String filePath) {
        return CommentedFileConfig.builder(Paths.get(filePath, new String[0]));
    }

    public static CommentedFileConfigBuilder builder(String filePath, ConfigFormat<? extends CommentedConfig> format) {
        return CommentedFileConfig.builder(Paths.get(filePath, new String[0]), format);
    }
}

