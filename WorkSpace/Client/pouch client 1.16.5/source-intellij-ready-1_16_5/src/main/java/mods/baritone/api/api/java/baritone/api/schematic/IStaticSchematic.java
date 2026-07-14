/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;

public interface IStaticSchematic
extends ISchematic {
    public K_4074_S getDirect(int var1, int var2, int var3);

    default public K_4074_S[] getColumn(int x, int z) {
        K_4074_S[] column = new K_4074_S[this.heightY()];
        for (int i = 0; i < this.heightY(); ++i) {
            column[i] = this.getDirect(x, i, z);
        }
        return column;
    }
}

