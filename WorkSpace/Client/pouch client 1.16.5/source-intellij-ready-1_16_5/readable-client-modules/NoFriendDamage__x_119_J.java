/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.h_2739_B;
import lightning.product.o_148_s;
import lightning.product.r_3979_X;
import lightning.product.y_2603_k;

public class x_119_J
extends X_3546_T {
    public x_119_J() {
        super("NoFriendDamage", y_2603_k.n_1700_B);
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B e) {
        r_3979_X attackAura = (r_3979_X)o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class);
        N_4263_v n_4263_v = e.J_1907_R();
        if (n_4263_v instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)n_4263_v;
            if (o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName()) && (!attackAura.w_1484_f() || attackAura.v_4262_N != e.J_1907_R())) {
                e.n_1700_B(true);
            }
        }
    }
}

