/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.c_4037_x;
import lightning.product.j_3341_s;

public class W_3265_k {
    private static final M_1336_P n_1700_B = j_3341_s.n_1700_B(new M_1336_P(0.2f, 1.0f, -0.7f), M_1336_P::G_564_y);
    private static final M_1336_P J_1907_R = j_3341_s.n_1700_B(new M_1336_P(-0.2f, 1.0f, 0.7f), M_1336_P::G_564_y);
    private static final M_1336_P R_4764_Y = j_3341_s.n_1700_B(new M_1336_P(0.2f, 1.0f, -0.7f), M_1336_P::G_564_y);
    private static final M_1336_P G_564_y = j_3341_s.n_1700_B(new M_1336_P(-0.2f, -1.0f, 0.7f), M_1336_P::G_564_y);

    public static void n_1700_B() {
        c_4037_x.P_4830_p();
        c_4037_x.Q_4569_t();
        c_4037_x.n_1700_B(1032, 5634);
    }

    public static void J_1907_R() {
        c_4037_x.h_1847_R();
        c_4037_x.M_182_A();
    }

    public static void n_1700_B(D_1098_v matrix) {
        c_4037_x.n_1700_B(R_4764_Y, G_564_y, matrix);
    }

    public static void J_1907_R(D_1098_v matrixIn) {
        c_4037_x.n_1700_B(n_1700_B, J_1907_R, matrixIn);
    }

    public static void R_4764_Y() {
        c_4037_x.n_1700_B(n_1700_B, J_1907_R);
    }

    public static void G_564_y() {
        c_4037_x.J_1907_R(n_1700_B, J_1907_R);
    }
}

