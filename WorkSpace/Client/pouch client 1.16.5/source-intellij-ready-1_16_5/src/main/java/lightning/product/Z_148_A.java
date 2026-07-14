/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PositionTracker;
import lightning.product.r_4811_B;

public class Z_148_A
implements PositionTracker {
    private final c_1514_x n_1700_B;
    private final e_2866_D J_1907_R;

    public Z_148_A(c_1514_x pos) {
        this.n_1700_B = pos;
        this.J_1907_R = e_2866_D.n_1700_B(pos);
    }

    @Override
    public e_2866_D n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    @Override
    public boolean n_1700_B(r_4811_B entity) {
        return true;
    }

    public String toString() {
        return "BlockPosTracker{blockPos=" + String.valueOf(this.n_1700_B) + ", centerPosition=" + String.valueOf(this.J_1907_R) + "}";
    }
}


