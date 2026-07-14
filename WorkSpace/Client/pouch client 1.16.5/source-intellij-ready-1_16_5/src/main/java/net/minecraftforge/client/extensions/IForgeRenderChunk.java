/*
 * Decompiled with CFR 0.152.
 */
package net.minecraftforge.client.extensions;

import lightning.product.Y_3830_x;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;

public interface IForgeRenderChunk {
    default public Y_3830_x createRegionRenderCache(b_4507_u world, c_1514_x from, c_1514_x to, int subtract) {
        return Y_3830_x.n_1700_B(world, from, to, subtract);
    }
}

