/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.serde.DeserializerContext;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import java.util.Optional;

public interface ValueDeserializer<T, R> {
    public R deserialize(T var1, Optional<TypeConstraint> var2, DeserializerContext var3);
}

