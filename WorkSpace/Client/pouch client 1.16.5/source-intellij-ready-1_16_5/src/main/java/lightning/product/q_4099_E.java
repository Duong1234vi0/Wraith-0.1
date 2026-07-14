/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.W_2163_m;
import lightning.product.b_257_Y;
import lightning.product.r_4970_d;

public final class q_4099_E
extends Enum<q_4099_E> {
    public static final /* enum */ q_4099_E n_1700_B = new q_4099_E(r_4970_d.n_1700_B);
    public static final /* enum */ q_4099_E J_1907_R = new q_4099_E(r_4970_d.H_2857_Y);
    public static final /* enum */ q_4099_E R_4764_Y = new q_4099_E(r_4970_d.Z_875_P);
    private final r_4970_d G_564_y;
    private static final /* synthetic */ q_4099_E[] P_1922_E;

    public static q_4099_E[] values() {
        return (q_4099_E[])P_1922_E.clone();
    }

    public static q_4099_E valueOf(String name) {
        return Enum.valueOf(q_4099_E.class, name);
    }

    private q_4099_E(r_4970_d orientation) {
        this.G_564_y = orientation;
    }

    public int n_1700_B(int rotationIn, int rotationCount) {
        int i = rotationCount / 2;
        int j = rotationIn > i ? rotationIn - rotationCount : rotationIn;
        switch (this.ordinal()) {
            case 2: {
                return (rotationCount - j) % rotationCount;
            }
            case 1: {
                return (i - j + rotationCount) % rotationCount;
            }
        }
        return rotationIn;
    }

    public W_2163_m n_1700_B(b_257_Y facing) {
        b_257_Y.n_1700_B direction$axis = facing.h_1847_R();
        return !(this == J_1907_R && direction$axis == b_257_Y.n_1700_B.R_4764_Y || this == R_4764_Y && direction$axis == b_257_Y.n_1700_B.n_1700_B) ? W_2163_m.n_1700_B : W_2163_m.R_4764_Y;
    }

    public b_257_Y J_1907_R(b_257_Y facing) {
        if (this == R_4764_Y && facing.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
            return facing.u_1723_Y();
        }
        return this == J_1907_R && facing.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? facing.u_1723_Y() : facing;
    }

    public r_4970_d n_1700_B() {
        return this.G_564_y;
    }

    private static /* synthetic */ q_4099_E[] J_1907_R() {
        return new q_4099_E[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        P_1922_E = q_4099_E.J_1907_R();
    }
}

