/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.CheckedCommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.InMemoryCommentedFormat;
import com.electronwill.nightconfig.core.SimpleCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.utils.FakeCommentedConfig;
import com.electronwill.nightconfig.core.utils.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public interface CommentedConfig
extends Config,
UnmodifiableCommentedConfig {
    default public String setComment(String path, String comment) {
        return this.setComment(StringUtils.split(path, '.'), comment);
    }

    public String setComment(List<String> var1, String var2);

    default public String removeComment(String path) {
        return this.removeComment(StringUtils.split(path, '.'));
    }

    public String removeComment(List<String> var1);

    public void clearComments();

    default public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
        for (Map.Entry<String, UnmodifiableCommentedConfig.CommentNode> entry : comments.entrySet()) {
            CommentedConfig config;
            Map<String, UnmodifiableCommentedConfig.CommentNode> children;
            String key = entry.getKey();
            UnmodifiableCommentedConfig.CommentNode node = entry.getValue();
            String comment = node.getComment();
            if (comment != null) {
                this.setComment(Collections.singletonList(key), comment);
            }
            if ((children = node.getChildren()) == null || (config = (CommentedConfig)this.getRaw(Collections.singletonList(key))) == null) continue;
            config.putAllComments(children);
        }
    }

    default public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
        for (UnmodifiableCommentedConfig.Entry entry : commentedConfig.entrySet()) {
            CommentedConfig config;
            Object value;
            String key = entry.getKey();
            String comment = entry.getComment();
            if (comment != null) {
                this.setComment(Collections.singletonList(key), comment);
            }
            if (!((value = entry.getValue()) instanceof UnmodifiableCommentedConfig) || (config = (CommentedConfig)this.getRaw(Collections.singletonList(key))) == null) continue;
            config.putAllComments((UnmodifiableCommentedConfig)value);
        }
    }

    @Override
    default public UnmodifiableCommentedConfig unmodifiable() {
        return new UnmodifiableCommentedConfig(){

            @Override
            public <T> T getRaw(List<String> path) {
                return CommentedConfig.this.getRaw(path);
            }

            @Override
            public String getComment(List<String> path) {
                return CommentedConfig.this.getComment(path);
            }

            @Override
            public boolean contains(List<String> path) {
                return CommentedConfig.this.contains(path);
            }

            @Override
            public boolean containsComment(List<String> path) {
                return CommentedConfig.this.containsComment(path);
            }

            @Override
            public int size() {
                return CommentedConfig.this.size();
            }

            @Override
            public Map<String, Object> valueMap() {
                return Collections.unmodifiableMap(CommentedConfig.this.valueMap());
            }

            @Override
            public Map<String, String> commentMap() {
                return Collections.unmodifiableMap(CommentedConfig.this.commentMap());
            }

            @Override
            public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
                return CommentedConfig.this.getComments();
            }

            @Override
            public Set<? extends UnmodifiableCommentedConfig.Entry> entrySet() {
                return CommentedConfig.this.entrySet();
            }

            @Override
            public ConfigFormat<?> configFormat() {
                return CommentedConfig.this.configFormat();
            }
        };
    }

    @Override
    default public CommentedConfig checked() {
        return new CheckedCommentedConfig(this);
    }

    @Override
    @Deprecated
    public Map<String, String> commentMap();

    public Set<? extends Entry> entrySet();

    @Override
    public CommentedConfig createSubConfig();

    public static CommentedConfig of(ConfigFormat<? extends CommentedConfig> format) {
        return new SimpleCommentedConfig(format, false);
    }

    public static CommentedConfig of(Supplier<Map<String, Object>> mapCreator, ConfigFormat<? extends CommentedConfig> format) {
        return new SimpleCommentedConfig(mapCreator, format);
    }

    @Deprecated
    public static CommentedConfig ofConcurrent(ConfigFormat<? extends CommentedConfig> format) {
        return new SimpleCommentedConfig(format, false);
    }

    public static CommentedConfig inMemory() {
        return (CommentedConfig)InMemoryCommentedFormat.defaultInstance().createConfig();
    }

    @Deprecated
    public static CommentedConfig inMemoryConcurrent() {
        return (CommentedConfig)InMemoryCommentedFormat.defaultInstance().createConcurrentConfig();
    }

    public static CommentedConfig wrap(Map<String, Object> map, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(map, format);
    }

    public static CommentedConfig copy(UnmodifiableConfig config) {
        return new SimpleCommentedConfig(config, config.configFormat(), false);
    }

    public static CommentedConfig copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator) {
        return new SimpleCommentedConfig(config, mapCreator, config.configFormat());
    }

    public static CommentedConfig copy(UnmodifiableConfig config, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(config, format, false);
    }

    public static CommentedConfig copy(UnmodifiableConfig config, Supplier<Map<String, Object>> mapCreator, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(config, mapCreator, format);
    }

    public static CommentedConfig copy(UnmodifiableCommentedConfig config) {
        return new SimpleCommentedConfig(config, config.configFormat(), false);
    }

    public static CommentedConfig copy(UnmodifiableCommentedConfig config, Supplier<Map<String, Object>> mapCreator) {
        return new SimpleCommentedConfig(config, mapCreator, config.configFormat());
    }

    public static CommentedConfig copy(UnmodifiableCommentedConfig config, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(config, format, false);
    }

    public static CommentedConfig copy(UnmodifiableCommentedConfig config, Supplier<Map<String, Object>> mapCreator, ConfigFormat<? extends CommentedConfig> format) {
        return new SimpleCommentedConfig(config, mapCreator, format);
    }

    @Deprecated
    public static CommentedConfig concurrentCopy(UnmodifiableConfig config) {
        return new SimpleCommentedConfig(config, config.configFormat(), true);
    }

    @Deprecated
    public static CommentedConfig concurrentCopy(UnmodifiableConfig config, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(config, format, true);
    }

    @Deprecated
    public static CommentedConfig concurrentCopy(UnmodifiableCommentedConfig config) {
        return new SimpleCommentedConfig(config, config.configFormat(), true);
    }

    @Deprecated
    public static CommentedConfig concurrentCopy(UnmodifiableCommentedConfig config, ConfigFormat<?> format) {
        return new SimpleCommentedConfig(config, format, true);
    }

    public static CommentedConfig fake(Config config) {
        if (config instanceof CommentedConfig) {
            return (CommentedConfig)config;
        }
        return new FakeCommentedConfig(config);
    }

    public static interface Entry
    extends Config.Entry,
    UnmodifiableCommentedConfig.Entry {
        public String setComment(String var1);

        public String removeComment();
    }
}

