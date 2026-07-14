/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_3508_C;
import lightning.product.N_3268_u;
import lightning.product.P_3504_Q;
import lightning.product.Q_2753_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.t_3138_Z;
import lightning.product.y_2603_k;

public class z_3044_r
extends X_3546_T {
    private boolean v_4262_N;
    private P_3504_Q w_1484_f;

    public z_3044_r() {
        super("NoServerDesync", y_2603_k.n_1700_B);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        t_3138_Z<?> t_3138_Z2;
        if (this.v_4262_N && (t_3138_Z2 = event.G_564_y()) instanceof N_3268_u) {
            N_3268_u packet = (N_3268_u)t_3138_Z2;
            packet.R_4764_Y(this.w_1484_f.t_148_a);
            packet.G_564_y(this.w_1484_f.s_956_w);
            this.v_4262_N = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(M_3508_C e) {
        this.w_1484_f = new P_3504_Q(e.J_1907_R(), e.R_4764_Y());
        this.v_4262_N = true;
        e.n_1700_B(true);
    }
}

