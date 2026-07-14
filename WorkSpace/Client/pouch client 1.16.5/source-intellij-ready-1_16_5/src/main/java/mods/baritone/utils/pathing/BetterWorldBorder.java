/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.pathing;

import lightning.product.T_603_v;

public class BetterWorldBorder {
    private final double minX;
    private final double maxX;
    private final double minZ;
    private final double maxZ;

    public BetterWorldBorder(T_603_v border) {
        this.minX = border.P_1922_E();
        this.maxX = border.v_4262_N();
        this.minZ = border.u_1723_Y();
        this.maxZ = border.w_1484_f();
    }

    public boolean entirelyContains(int x, int z) {
        return (double)(x + 1) > this.minX && (double)x < this.maxX && (double)(z + 1) > this.minZ && (double)z < this.maxZ;
    }

    public boolean canPlaceAt(int x, int z) {
        return (double)x > this.minX && (double)(x + 1) < this.maxX && (double)z > this.minZ && (double)(z + 1) < this.maxZ;
    }
}

