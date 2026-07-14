/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.o_2576_A;

public class ChunkLayerMap<T> {
    private T[] values = new Object[o_2576_A.N_2525_X.length];
    private Supplier<T> defaultValue;

    public ChunkLayerMap(Function<o_2576_A, T> initialValue) {
        o_2576_A[] arendertype = o_2576_A.N_2525_X;
        this.values = new Object[arendertype.length];
        for (int i = 0; i < arendertype.length; ++i) {
            o_2576_A rendertype = arendertype[i];
            T t = initialValue.apply(rendertype);
            this.values[rendertype.G_564_y()] = t;
        }
        for (int j = 0; j < this.values.length; ++j) {
            if (this.values[j] != null) continue;
            throw new RuntimeException("Missing value at index: " + j);
        }
    }

    public T get(o_2576_A layer) {
        return this.values[layer.G_564_y()];
    }

    public Collection<T> values() {
        return Arrays.asList(this.values);
    }
}

