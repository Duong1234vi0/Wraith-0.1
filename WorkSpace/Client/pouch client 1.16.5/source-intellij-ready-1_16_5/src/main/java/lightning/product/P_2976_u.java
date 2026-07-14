/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_2915_h;
import lightning.product.c_1514_x;

public class P_2976_u {
    private final c_1514_x n_1700_B;
    private final T_2915_h J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public P_2976_u(c_1514_x pos, T_2915_h blockType, int eventId, int eventParameterIn) {
        this.n_1700_B = pos;
        this.J_1907_R = blockType;
        this.R_4764_Y = eventId;
        this.G_564_y = eventParameterIn;
    }

    public c_1514_x n_1700_B() {
        return this.n_1700_B;
    }

    public T_2915_h J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof P_2976_u)) {
            return false;
        }
        P_2976_u blockeventdata = (P_2976_u)p_equals_1_;
        return this.n_1700_B.equals(blockeventdata.n_1700_B) && this.R_4764_Y == blockeventdata.R_4764_Y && this.G_564_y == blockeventdata.G_564_y && this.J_1907_R == blockeventdata.J_1907_R;
    }

    public int hashCode() {
        int i = this.n_1700_B.hashCode();
        i = 31 * i + this.J_1907_R.hashCode();
        i = 31 * i + this.R_4764_Y;
        return 31 * i + this.G_564_y;
    }

    public String toString() {
        return "TE(" + String.valueOf(this.n_1700_B) + ")," + this.R_4764_Y + "," + this.G_564_y + "," + String.valueOf(this.J_1907_R);
    }
}

