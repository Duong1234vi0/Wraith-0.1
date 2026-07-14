/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DimensionTransformer;

public interface A_1130_n
extends DimensionTransformer {
    @Override
    default public int n_1700_B(int x) {
        return x - 1;
    }

    @Override
    default public int J_1907_R(int z) {
        return z - 1;
    }
}


