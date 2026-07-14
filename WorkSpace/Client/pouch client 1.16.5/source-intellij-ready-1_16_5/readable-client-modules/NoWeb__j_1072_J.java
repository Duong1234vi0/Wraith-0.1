/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_759_W;
import lightning.product.a_3742_W;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;

public class j_1072_J
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Motion", "Motion");
    private final p_1977_n w_1484_f = new p_1977_n("\u041b\u043e\u043c\u0430\u0442\u044c \u0441\u043a\u0432\u043e\u0437\u044c \u043f\u0430\u0443\u0442\u0438\u043d\u0443", false);

    public j_1072_J() {
        super("NoWeb", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(Z_759_W event) {
        if (!this.w_1484_f.t_148_a().booleanValue()) {
            return;
        }
        if (j_1072_J.c_3005_b.Y_259_p == null || j_1072_J.c_3005_b.Y_601_j == null) {
            return;
        }
        if (event.J_1907_R() != null && event.J_1907_R().J_1907_R() == a_3742_W.y_1700_S) {
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (j_1072_J.c_3005_b.Y_259_p == null || j_1072_J.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!j_1072_J.c_3005_b.Y_259_p.s_2121_j) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Motion")) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        j_1072_J.c_3005_b.Y_259_p.h_1847_R(j_1072_J.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.0, j_1072_J.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        if (j_1072_J.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
            j_1072_J.c_3005_b.Y_259_p.h_1847_R(j_1072_J.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.995, j_1072_J.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        } else if (j_1072_J.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            j_1072_J.c_3005_b.Y_259_p.h_1847_R(j_1072_J.c_3005_b.Y_259_p.I_4348_c().J_1907_R, -0.995, j_1072_J.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        }
        u_925_K.n_1700_B((double)0.22f);
    }
}

