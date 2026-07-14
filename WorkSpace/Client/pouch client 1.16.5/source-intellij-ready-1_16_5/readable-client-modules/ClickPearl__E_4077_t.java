/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_3504_M;
import lightning.product.a_408_T;
import lightning.product.d_2169_p;
import lightning.product.h_1015_G;
import lightning.product.i_2572_h;
import lightning.product.i_4434_b;
import lightning.product.p_1183_T;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.r_4790_y;
import lightning.product.u_1934_K;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class E_4077_t
extends X_3546_T {
    private final q_3206_W v_4262_N = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0431\u0440\u043e\u0441\u0430\u043d\u0438\u044f");
    private final q_366_O w_1484_f = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439");
    private boolean t_148_a;
    private int s_956_w = -1;
    private int u_2550_I = 0;

    public E_4077_t() {
        super("ClickPearl", y_2603_k.G_564_y);
        this.n_1700_B(this.w_1484_f, this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        this.t_148_a = ((Integer)this.v_4262_N.J_1907_R()).intValue() == e.n_1700_B() && e.J_1907_R() && !E_4077_t.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.v_2746_S) && u_1934_K.n_1700_B(q_4592_V.v_2746_S) != -1;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.w_1484_f.J_1907_R("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439")) {
            if (this.s_956_w != -1) {
                if (this.u_2550_I > 1) {
                    --this.u_2550_I;
                    return;
                }
                if (this.u_2550_I == 1) {
                    E_4077_t.c_3005_b.w_1457_N.processRightClick(E_4077_t.c_3005_b.Y_259_p, E_4077_t.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                    --this.u_2550_I;
                    return;
                }
                if (this.u_2550_I == 0) {
                    E_4077_t.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
                    E_4077_t.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    this.s_956_w = -1;
                    this.u_2550_I = -1;
                }
                return;
            }
            if (this.t_148_a) {
                int slot = u_1934_K.n_1700_B(q_4592_V.v_2746_S);
                if (slot == -1) {
                    this.t_148_a = false;
                    return;
                }
                if (r_4790_y.n_1700_B()) {
                    r_4790_y.n_1700_B(new F_1446_q(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y()), 360.0f, 1, 1000);
                    F_1446_q f_1446_q = new F_1446_q(E_4077_t.c_3005_b.Y_259_p);
                    if (f_1446_q.n_1700_B(F_1446_q.J_1907_R()) > 1.0) {
                        return;
                    }
                }
                this.s_956_w = E_4077_t.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (slot < 9) {
                    E_4077_t.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
                    E_4077_t.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    this.u_2550_I = 2;
                } else {
                    E_4077_t.c_3005_b.w_1457_N.windowClick(E_4077_t.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, slot, this.s_956_w, a_408_T.R_4764_Y, E_4077_t.c_3005_b.Y_259_p);
                    this.u_2550_I = 2;
                }
                this.t_148_a = false;
            }
            return;
        }
        if (!this.t_148_a) {
            return;
        }
        int slot = u_1934_K.n_1700_B(q_4592_V.v_2746_S);
        if (r_4790_y.n_1700_B()) {
            r_4790_y.n_1700_B(new F_1446_q(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y()), 360.0f, 1, 1000);
            F_1446_q f_1446_q = new F_1446_q(E_4077_t.c_3005_b.Y_259_p);
            if (f_1446_q.n_1700_B(F_1446_q.J_1907_R()) > 1.0) {
                return;
            }
        }
        if (slot < 9) {
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(E_4077_t.c_3005_b.Y_259_p.l_1268_F.G_564_y));
        } else {
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(slot));
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            E_4077_t.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(slot));
        }
        this.t_148_a = false;
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.t_148_a = false;
        if (this.s_956_w != -1) {
            E_4077_t.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
            E_4077_t.c_3005_b.w_1457_N.syncCurrentPlayItem();
            this.s_956_w = -1;
        }
        this.u_2550_I = 0;
    }
}

