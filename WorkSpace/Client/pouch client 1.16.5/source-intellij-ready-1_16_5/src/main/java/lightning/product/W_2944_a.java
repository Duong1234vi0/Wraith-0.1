/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1514_x;

public class W_2944_a {
    public final int n_1700_B;
    public final int J_1907_R;

    public W_2944_a(int x, int z) {
        this.n_1700_B = x;
        this.J_1907_R = z;
    }

    public W_2944_a(c_1514_x pos) {
        this.n_1700_B = pos.getX();
        this.J_1907_R = pos.getZ();
    }

    public String toString() {
        return "[" + this.n_1700_B + ", " + this.J_1907_R + "]";
    }

    public int hashCode() {
        int i = 1664525 * this.n_1700_B + 1013904223;
        int j = 1664525 * (this.J_1907_R ^ 0xDEADBEEF) + 1013904223;
        return i ^ j;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof W_2944_a)) {
            return false;
        }
        W_2944_a columnpos = (W_2944_a)p_equals_1_;
        return this.n_1700_B == columnpos.n_1700_B && this.J_1907_R == columnpos.J_1907_R;
    }
}

