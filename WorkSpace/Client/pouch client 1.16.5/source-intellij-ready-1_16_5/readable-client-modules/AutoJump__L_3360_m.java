/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_588_u;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.r_3979_X;
import lightning.product.y_2603_k;

public class L_3360_m
extends X_3546_T {
    public static N_4463_r v_4262_N = new N_4463_r("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u0435\u0441\u043b\u0438", new p_1977_n("\u0410\u043a\u0442\u0438\u0432\u043d\u0430 Attack Aura", false), new p_1977_n("\u0410\u043a\u0442\u0438\u0432\u043d\u043e \u0437\u0435\u043b\u044c\u0435 \u0437\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u044f", true));

    public L_3360_m() {
        super("AutoJump", y_2603_k.J_1907_R);
        this.n_1700_B(v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        r_3979_X aura;
        if (!L_3360_m.c_3005_b.Y_259_p.M_1641_O() || L_3360_m.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
            return;
        }
        if (L_3360_m.c_3005_b.Y_259_p.U_1241_n > 0.0f) {
            return;
        }
        boolean shouldJump = false;
        if (v_4262_N.J_1907_R("\u0410\u043a\u0442\u0438\u0432\u043d\u0430 Attack Aura").booleanValue() && (aura = o_148_s.Y_601_j().J_1907_R().n_1700_B).w_1484_f() && aura.h_1847_R() != null) {
            shouldJump = true;
        }
        if (v_4262_N.J_1907_R("\u0410\u043a\u0442\u0438\u0432\u043d\u043e \u0437\u0435\u043b\u044c\u0435 \u0437\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u044f").booleanValue() && L_3360_m.c_3005_b.Y_259_p.J_1907_R(J_588_u.J_1907_R)) {
            shouldJump = true;
        }
        if (shouldJump && L_3360_m.c_3005_b.Y_259_p.M_1641_O()) {
            L_3360_m.c_3005_b.Y_259_p.e_837_t();
        }
    }
}

