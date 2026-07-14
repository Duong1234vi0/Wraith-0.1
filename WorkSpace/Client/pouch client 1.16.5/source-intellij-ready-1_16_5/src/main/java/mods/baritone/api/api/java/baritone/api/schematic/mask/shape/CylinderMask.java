/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic.mask.shape;

import lightning.product.b_257_Y;
import mods.baritone.api.api.java.baritone.api.schematic.mask.AbstractMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.StaticMask;

public final class CylinderMask
extends AbstractMask
implements StaticMask {
    private final double centerA;
    private final double centerB;
    private final double radiusSqA;
    private final double radiusSqB;
    private final boolean filled;
    private final b_257_Y.n_1700_B alignment;

    public CylinderMask(int widthX, int heightY, int lengthZ, boolean filled, b_257_Y.n_1700_B alignment) {
        super(widthX, heightY, lengthZ);
        this.centerA = (double)CylinderMask.getA(widthX, heightY, alignment) / 2.0;
        this.centerB = (double)CylinderMask.getB(heightY, lengthZ, alignment) / 2.0;
        this.radiusSqA = (this.centerA - 1.0) * (this.centerA - 1.0);
        this.radiusSqB = (this.centerB - 1.0) * (this.centerB - 1.0);
        this.filled = filled;
        this.alignment = alignment;
    }

    @Override
    public boolean partOfMask(int x, int y, int z) {
        double da = Math.abs((double)CylinderMask.getA(x, y, this.alignment) + 0.5 - this.centerA);
        double db = Math.abs((double)CylinderMask.getB(y, z, this.alignment) + 0.5 - this.centerB);
        if (this.outside(da, db)) {
            return false;
        }
        return this.filled || this.outside(da + 1.0, db) || this.outside(da, db + 1.0);
    }

    private boolean outside(double da, double db) {
        return da * da / this.radiusSqA + db * db / this.radiusSqB > 1.0;
    }

    private static int getA(int x, int y, b_257_Y.n_1700_B alignment) {
        return alignment == b_257_Y.n_1700_B.n_1700_B ? y : x;
    }

    private static int getB(int y, int z, b_257_Y.n_1700_B alignment) {
        return alignment == b_257_Y.n_1700_B.R_4764_Y ? y : z;
    }
}

