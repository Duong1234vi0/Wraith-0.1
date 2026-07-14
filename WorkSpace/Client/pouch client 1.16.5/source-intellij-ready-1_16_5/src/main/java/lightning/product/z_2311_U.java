/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Q_1082_O;
import lightning.product.u_530_F;

public final class z_2311_U {
    public static final int n_1700_B = 16;
    private final float[] J_1907_R = new float[16];
    private final float[] R_4764_Y = new float[16];
    private int G_564_y;
    private int P_1922_E;

    public void n_1700_B() {
        this.G_564_y = 0;
        this.P_1922_E = 0;
    }

    public void n_1700_B(float yaw, float pitch) {
        this.J_1907_R[this.G_564_y] = yaw;
        this.R_4764_Y[this.G_564_y] = pitch;
        this.G_564_y = (this.G_564_y + 1) % 16;
        this.P_1922_E = Math.min(this.P_1922_E + 1, 16);
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public float n_1700_B(int age) {
        if (age < 0 || age >= this.P_1922_E) {
            return 0.0f;
        }
        int idx = (this.G_564_y - 1 - age + 16) % 16;
        return this.J_1907_R[idx];
    }

    public float J_1907_R(int age) {
        if (age < 0 || age >= this.P_1922_E) {
            return 0.0f;
        }
        int idx = (this.G_564_y - 1 - age + 16) % 16;
        return this.R_4764_Y[idx];
    }

    public boolean R_4764_Y() {
        float m2;
        float m1;
        if (this.P_1922_E < 4) {
            return false;
        }
        float m0 = Math.abs(u_530_F.v_4262_N(this.n_1700_B(0) - this.n_1700_B(1))) + Math.abs(this.J_1907_R(0) - this.J_1907_R(1));
        return m0 + (m1 = Math.abs(u_530_F.v_4262_N(this.n_1700_B(1) - this.n_1700_B(2))) + Math.abs(this.J_1907_R(1) - this.J_1907_R(2))) + (m2 = Math.abs(u_530_F.v_4262_N(this.n_1700_B(2) - this.n_1700_B(3))) + Math.abs(this.J_1907_R(2) - this.J_1907_R(3))) > 0.08f;
    }

    public float[] G_564_y() {
        float[] out = new float[42];
        Q_1082_O.n_1700_B(out, this::n_1700_B, this::J_1907_R, this.P_1922_E);
        return out;
    }
}

