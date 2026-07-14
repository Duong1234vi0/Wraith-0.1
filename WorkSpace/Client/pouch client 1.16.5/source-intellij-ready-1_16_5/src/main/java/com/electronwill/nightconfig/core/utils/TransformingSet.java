/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.utils.TransformingCollection;
import java.util.Set;
import java.util.function.Function;

public final class TransformingSet<InternalV, ExternalV>
extends TransformingCollection<InternalV, ExternalV>
implements Set<ExternalV> {
    public TransformingSet(Set<InternalV> internalCollection, Function<? super InternalV, ? extends ExternalV> readTransformation, Function<? super ExternalV, ? extends InternalV> writeTransformation, Function<Object, Object> searchTransformation) {
        super(internalCollection, readTransformation, writeTransformation, searchTransformation);
    }
}

