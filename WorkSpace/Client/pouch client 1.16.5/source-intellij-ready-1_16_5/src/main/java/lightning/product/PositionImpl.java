/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Position;

public class PositionImpl
implements Position {
    protected final double n_1700_B;
    protected final double J_1907_R;
    protected final double R_4764_Y;

    public PositionImpl(double xCoord, double yCoord, double zCoord) {
        this.n_1700_B = xCoord;
        this.J_1907_R = yCoord;
        this.R_4764_Y = zCoord;
    }

    @Override
    public double n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public double J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public double R_4764_Y() {
        return this.R_4764_Y;
    }
}


