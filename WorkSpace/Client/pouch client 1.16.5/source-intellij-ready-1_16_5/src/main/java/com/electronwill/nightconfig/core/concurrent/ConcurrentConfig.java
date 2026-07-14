/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.concurrent;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ConcurrentConfig
extends Config {
    public <R> R bulkRead(Function<? super UnmodifiableConfig, R> var1);

    default public void bulkRead(Consumer<? super UnmodifiableConfig> action) {
        this.bulkRead((? super UnmodifiableConfig config) -> {
            action.accept((UnmodifiableConfig)config);
            return null;
        });
    }

    public <R> R bulkUpdate(Function<? super Config, R> var1);

    default public void bulkUpdate(Consumer<? super Config> action) {
        this.bulkUpdate((? super Config config) -> {
            action.accept((Config)config);
            return null;
        });
    }

    @Override
    public ConcurrentConfig createSubConfig();
}

