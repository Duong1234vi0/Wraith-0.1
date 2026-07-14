/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
import com.electronwill.nightconfig.core.utils.TransformingMap;
import com.electronwill.nightconfig.core.utils.TransformingSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CheckedCommentedConfig
extends CommentedConfigWrapper<CommentedConfig> {
    CheckedCommentedConfig(CommentedConfig config) {
        super(config);
        config.valueMap().forEach((k, v) -> this.checkValue(v));
    }

    @Override
    public CommentedConfig checked() {
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
    public Set<? extends CommentedConfig.Entry> entrySet() {
        return new TransformingSet<CommentedConfig.Entry, CommentedConfig.Entry>(super.entrySet(), v -> v, this::checkedValue, o -> o);
    }

    @Override
    public String toString() {
        return "checked " + String.valueOf(this.config);
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

