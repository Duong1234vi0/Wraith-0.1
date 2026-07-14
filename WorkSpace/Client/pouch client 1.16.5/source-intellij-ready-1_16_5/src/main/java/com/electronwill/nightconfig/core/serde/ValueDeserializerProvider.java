/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;

@FunctionalInterface
public interface ValueDeserializerProvider<T, R> {
    public ValueDeserializer<T, R> provide(Class<?> var1, TypeConstraint var2);
}

