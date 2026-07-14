/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.utils.ConcurrentCommentedConfigWrapper;
import com.electronwill.nightconfig.core.utils.ObservedMap;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

final class AutosaveCommentedFileConfig
extends ConcurrentCommentedConfigWrapper<CommentedFileConfig>
implements CommentedFileConfig {
    private final Runnable autoSaveListener;

    AutosaveCommentedFileConfig(CommentedFileConfig fileConfig, Runnable autosaveListener) {
        super(fileConfig);
        this.autoSaveListener = autosaveListener;
    }

    private void autoSave() {
        this.save();
        this.autoSaveListener.run();
    }

    @Override
    public void save() {
        ((CommentedFileConfig)this.config).save();
    }

    @Override
    public <T> T set(List<String> path, Object value) {
        Object result = super.set(path, value);
        this.autoSave();
        return result;
    }

    @Override
    public boolean add(List<String> path, Object value) {
        boolean result = super.add(path, value);
        this.autoSave();
        return result;
    }

    @Override
    public <T> T remove(List<String> path) {
        Object result = super.remove(path);
        this.autoSave();
        return result;
    }

    @Override
    public String setComment(List<String> path, String comment) {
        String result = super.setComment(path, comment);
        this.autoSave();
        return result;
    }

    @Override
    public String removeComment(List<String> path) {
        String result = super.removeComment(path);
        this.autoSave();
        return result;
    }

    @Override
    public void removeAll(UnmodifiableConfig config) {
        super.removeAll(config);
        this.autoSave();
    }

    @Override
    public void putAll(UnmodifiableConfig config) {
        super.putAll(config);
        this.autoSave();
    }

    @Override
    public void clear() {
        super.clear();
        this.autoSave();
    }

    @Override
    public void clearComments() {
        super.clearComments();
        this.autoSave();
    }

    @Override
    public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
        super.putAllComments(commentedConfig);
        this.autoSave();
    }

    @Override
    public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
        super.putAllComments(comments);
        this.autoSave();
    }

    @Override
    public Map<String, Object> valueMap() {
        return new ObservedMap<String, Object>(super.valueMap(), this::autoSave);
    }

    @Override
    public Map<String, String> commentMap() {
        return new ObservedMap<String, String>(super.commentMap(), this::autoSave);
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
    public void load() {
        ((CommentedFileConfig)this.config).load();
    }

    @Override
    public void close() {
        ((CommentedFileConfig)this.config).close();
    }

    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> action) {
        R result = ((CommentedFileConfig)this.config).bulkCommentedUpdate(action);
        this.autoSave();
        return result;
    }

    @Override
    public <R> R bulkUpdate(Function<? super Config, R> action) {
        R result = CommentedFileConfig.super.bulkUpdate(action);
        this.autoSave();
        return result;
    }
}

