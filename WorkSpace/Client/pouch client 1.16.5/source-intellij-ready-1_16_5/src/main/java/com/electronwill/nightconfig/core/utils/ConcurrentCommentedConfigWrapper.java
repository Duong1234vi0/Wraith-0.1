/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.utils.CommentedConfigWrapper;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ConcurrentCommentedConfigWrapper<C extends ConcurrentCommentedConfig>
extends CommentedConfigWrapper<C>
implements ConcurrentCommentedConfig {
    protected ConcurrentCommentedConfigWrapper(C config) {
        super(config);
    }

    @Override
    public ConcurrentCommentedConfig createSubConfig() {
        return ((ConcurrentCommentedConfig)this.config).createSubConfig();
    }

    @Override
    public void bulkRead(Consumer<? super UnmodifiableConfig> action) {
        ((ConcurrentCommentedConfig)this.config).bulkRead(action);
    }

    @Override
    public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
        return ((ConcurrentCommentedConfig)this.config).bulkRead(action);
    }

    @Override
    public void bulkCommentedRead(Consumer<? super UnmodifiableCommentedConfig> action) {
        ((ConcurrentCommentedConfig)this.config).bulkCommentedRead(action);
    }

    @Override
    public <R> R bulkCommentedRead(Function<? super UnmodifiableCommentedConfig, R> action) {
        return ((ConcurrentCommentedConfig)this.config).bulkCommentedRead(action);
    }

    @Override
    public void bulkUpdate(Consumer<? super Config> action) {
        ((ConcurrentCommentedConfig)this.config).bulkUpdate(action);
    }

    @Override
    public <R> R bulkUpdate(Function<? super Config, R> action) {
        return ((ConcurrentCommentedConfig)this.config).bulkUpdate(action);
    }

    @Override
    public void bulkCommentedUpdate(Consumer<? super CommentedConfig> action) {
        ((ConcurrentCommentedConfig)this.config).bulkCommentedUpdate(action);
    }

    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> action) {
        return ((ConcurrentCommentedConfig)this.config).bulkCommentedUpdate(action);
    }
}

