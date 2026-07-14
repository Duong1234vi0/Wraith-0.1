/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import lightning.product.K_4074_S;
import lightning.product.c_1514_x;

public interface IBlockTypeAccess {
    public K_4074_S getBlock(int var1, int var2, int var3);

    default public K_4074_S getBlock(c_1514_x pos) {
        return this.getBlock(pos.getX(), pos.getY(), pos.getZ());
    }
}

