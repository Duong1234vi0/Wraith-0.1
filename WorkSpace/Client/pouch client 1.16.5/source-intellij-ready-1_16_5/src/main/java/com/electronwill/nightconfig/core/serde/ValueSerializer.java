/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.serde.SerializerContext;

public interface ValueSerializer<T, R> {
    public R serialize(T var1, SerializerContext var2);
}

