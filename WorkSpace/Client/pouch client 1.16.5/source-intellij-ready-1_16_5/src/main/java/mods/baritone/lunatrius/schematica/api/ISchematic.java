/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.lunatrius.schematica.api;

import lightning.product.K_4074_S;
import lightning.product.c_1514_x;

public interface ISchematic {
    public K_4074_S getBlockState(c_1514_x var1);

    public int getWidth();

    public int getHeight();

    public int getLength();
}

