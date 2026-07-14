/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_148_A;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PositionTracker;

public class WalkTarget {
    private final PositionTracker n_1700_B;
    private final float J_1907_R;
    private final int R_4764_Y;

    public WalkTarget(c_1514_x targetIn, float speedIn, int distanceIn) {
        this(new Z_148_A(targetIn), speedIn, distanceIn);
    }

    public WalkTarget(e_2866_D targetIn, float speedIn, int distanceIn) {
        this(new Z_148_A(new c_1514_x(targetIn)), speedIn, distanceIn);
    }

    public WalkTarget(PositionTracker targetIn, float speedIn, int distanceIn) {
        this.n_1700_B = targetIn;
        this.J_1907_R = speedIn;
        this.R_4764_Y = distanceIn;
    }

    public PositionTracker n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }
}


