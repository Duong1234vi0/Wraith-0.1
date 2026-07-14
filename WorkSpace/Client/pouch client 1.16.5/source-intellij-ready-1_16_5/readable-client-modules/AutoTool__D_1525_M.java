/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_3416_z;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.u_1934_K;
import lightning.product.x_4991_F;
import lightning.product.y_2603_k;

public class D_1525_M
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u0421\u0430\u0439\u043b\u0435\u043d\u0442", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u0421\u0430\u0439\u043b\u0435\u043d\u0442");
    private int w_1484_f = -1;
    private int t_148_a = -1;
    private int s_956_w = -1;

    public D_1525_M() {
        super("AutoTool", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!this.Q_4569_t()) {
            return;
        }
        if (D_1525_M.c_3005_b.Y_259_p == null || D_1525_M.c_3005_b.Y_601_j == null || D_1525_M.c_3005_b.Y_259_p.G_624_v()) {
            this.s_956_w = -1;
            return;
        }
        if (this.t_1786_h()) {
            int bestToolSlot = this.M_182_A();
            if (bestToolSlot != -1) {
                if (this.s_956_w == -1) {
                    this.s_956_w = D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                }
                D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = bestToolSlot;
            }
        } else if (this.s_956_w != -1) {
            D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
            this.s_956_w = -1;
        }
    }

    private boolean h_1847_R() {
        return this.v_4262_N.J_1907_R("\u0421\u0430\u0439\u043b\u0435\u043d\u0442");
    }

    private boolean Q_4569_t() {
        return this.v_4262_N.J_1907_R("\u041e\u0431\u044b\u0447\u043d\u044b\u0439") || this.v_4262_N.J_1907_R("\u041b\u0435\u0433\u0438\u0442");
    }

    @Override
    public void J_1907_R() {
        if (D_1525_M.c_3005_b.Y_259_p != null) {
            if (this.h_1847_R() && this.w_1484_f != -1) {
                D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.w_1484_f;
                D_1525_M.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.w_1484_f));
            }
            if (this.Q_4569_t() && this.s_956_w != -1) {
                D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
            }
        }
        this.w_1484_f = -1;
        this.t_148_a = -1;
        this.s_956_w = -1;
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(x_4991_F e) {
        if (!this.h_1847_R()) {
            return;
        }
        if (e.G_564_y() == x_4991_F.n_1700_B.n_1700_B) {
            int bestToolSlot;
            if (this.w_1484_f == -1) {
                this.w_1484_f = D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            }
            if ((bestToolSlot = this.J_1907_R(e)) != -1) {
                if (D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y != bestToolSlot) {
                    D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = bestToolSlot;
                }
                if (this.t_148_a != bestToolSlot) {
                    D_1525_M.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(bestToolSlot));
                    this.t_148_a = bestToolSlot;
                }
            }
        } else if (this.w_1484_f != -1) {
            D_1525_M.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.w_1484_f;
            D_1525_M.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.w_1484_f));
            this.w_1484_f = -1;
            this.t_148_a = -1;
        }
    }

    private int J_1907_R(x_4991_F e) {
        int bestToolSlot = -1;
        if (e.J_1907_R().J_1907_R() == a_3742_W.y_1700_S) {
            for (int i = 0; i < 9; ++i) {
                if (D_1525_M.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != q_4592_V.v_3490_u) continue;
                bestToolSlot = i;
                break;
            }
        }
        if (bestToolSlot == -1) {
            bestToolSlot = u_1934_K.n_1700_B(e.J_1907_R());
        }
        return bestToolSlot;
    }

    private int M_182_A() {
        if (!(D_1525_M.c_3005_b.Z_875_P instanceof G_3416_z)) {
            return -1;
        }
        G_3416_z ray = (G_3416_z)D_1525_M.c_3005_b.Z_875_P;
        T_2915_h block = D_1525_M.c_3005_b.Y_601_j.getBlockState(ray.n_1700_B()).J_1907_R();
        if (block == a_3742_W.y_1700_S) {
            for (int slot = 0; slot < 9; ++slot) {
                if (D_1525_M.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot).J_1907_R() != q_4592_V.v_3490_u) continue;
                return slot;
            }
        }
        return u_1934_K.n_1700_B(D_1525_M.c_3005_b.Y_601_j.getBlockState(ray.n_1700_B()));
    }

    private boolean t_1786_h() {
        return D_1525_M.c_3005_b.Z_875_P != null && D_1525_M.c_3005_b.P_4830_p.D_60_a.G_564_y();
    }
}

