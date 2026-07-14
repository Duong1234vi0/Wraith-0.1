/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntSet;
import java.io.Closeable;
import javax.annotation.Nullable;
import lightning.product.s_3940_w;

public interface e_3495_r
extends Closeable {
    @Override
    default public void close() {
    }

    @Nullable
    default public s_3940_w n_1700_B(int character) {
        return null;
    }

    public IntSet n_1700_B();
}

