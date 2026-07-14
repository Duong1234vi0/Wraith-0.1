/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.k_2603_m;
import lightning.product.l_3609_d;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.r_3979_X;
import lightning.product.y_2603_k;

public class s_2345_S
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c AttackAura", true);
    private final p_1977_n w_1484_f = new p_1977_n("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0445\u0430\u0440\u0434\u043a\u043e\u0440", false);

    public s_2345_S() {
        super("AutoRespawn", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(l_3609_d e) {
        if (s_2345_S.c_3005_b.Y_601_j == null || s_2345_S.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.w_1484_f.t_148_a().booleanValue() && s_2345_S.c_3005_b.Y_601_j.Y_259_p().n_1700_B()) {
            return;
        }
        if (this.v_4262_N.t_148_a().booleanValue() && o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class).w_1484_f()) {
            o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class).R_4764_Y();
        }
        s_2345_S.c_3005_b.Y_259_p.G_564_y();
        c_3005_b.n_1700_B((k_2603_m)null);
    }
}

