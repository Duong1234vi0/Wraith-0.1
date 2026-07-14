/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.serde.SerializerContext;
import com.electronwill.nightconfig.core.serde.ValueSerializer;

@FunctionalInterface
public interface ValueSerializerProvider<V, R> {
    public ValueSerializer<V, R> provide(Class<?> var1, SerializerContext var2);
}

