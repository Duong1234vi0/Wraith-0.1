/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.concurrent;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentConfig;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ConcurrentCommentedConfig
extends CommentedConfig,
ConcurrentConfig {
    public <R> R bulkCommentedRead(Function<? super UnmodifiableCommentedConfig, R> var1);

    default public void bulkCommentedRead(Consumer<? super UnmodifiableCommentedConfig> action) {
        this.bulkCommentedRead((? super UnmodifiableCommentedConfig config) -> {
            action.accept((UnmodifiableCommentedConfig)config);
            return null;
        });
    }

    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> var1);

    default public void bulkCommentedUpdate(Consumer<? super CommentedConfig> action) {
        this.bulkCommentedUpdate((? super CommentedConfig config) -> {
            action.accept((CommentedConfig)config);
            return null;
        });
    }

    @Override
    default public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
        return this.bulkCommentedRead(action);
    }

    @Override
    default public <R> R bulkUpdate(Function<? super Config, R> action) {
        return this.bulkCommentedUpdate(action);
    }

    @Override
    public ConcurrentCommentedConfig createSubConfig();
}

