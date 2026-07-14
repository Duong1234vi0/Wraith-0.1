/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.List;
import lightning.product.I_686_h;
import lightning.product.N_4463_r;
import lightning.product.Q_1939_l;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;

public class J_3907_o
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 1000.0f, 10.0f);
    private final N_4463_r w_1484_f = new N_4463_r("\u0412\u044b\u0431\u0440\u0430\u0441\u044b\u0432\u0430\u0442\u044c \u043c\u0443\u0441\u043e\u0440", new p_1977_n("\u041f\u0430\u0443\u0442\u0438\u043d\u0430", false), new p_1977_n("\u041d\u0438\u0442\u043a\u0438", false), new p_1977_n("\u0421\u0442\u0440\u0435\u043b\u044b", false), new p_1977_n("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c", true), new p_1977_n("\u041a\u043e\u0441\u0442\u0438", true), new p_1977_n("\u041f\u043e\u0440\u043e\u0445", false), new p_1977_n("\u0423\u0433\u043e\u043b\u044c", false), new p_1977_n("\u041a\u0440\u0435\u043c\u0435\u043d\u044c", false), new p_1977_n("\u0413\u0440\u0430\u0432\u0438\u0439", false), new p_1977_n("\u0420\u0443\u0434\u044b", false));
    private final p_1977_n t_148_a = new p_1977_n("\u0427\u0438\u0441\u0442\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0445\u043e\u0442\u0431\u0430\u0440", false);
    private long s_956_w = 0L;
    private static final List<q_1613_l> u_2550_I = Arrays.asList(q_4592_V.D_1056_T);
    private static final List<q_1613_l> M_588_G = Arrays.asList(q_4592_V.j_755_i);
    private static final List<q_1613_l> P_4830_p = Arrays.asList(q_4592_V.g_24_p, q_4592_V.g_2783_J, q_4592_V.p_1838_W);
    private static final List<q_1613_l> h_1847_R = Arrays.asList(q_4592_V.m_1964_F);
    private static final List<q_1613_l> Q_4569_t = Arrays.asList(q_4592_V.M_4486_Q);
    private static final List<q_1613_l> M_182_A = Arrays.asList(q_4592_V.i_4482_j);
    private static final List<q_1613_l> t_1786_h = Arrays.asList(q_4592_V.T_797_O, q_4592_V.d_560_A);
    private static final List<q_1613_l> N_4405_n = Arrays.asList(q_4592_V.W_1488_x);
    private static final List<q_1613_l> w_1457_N = Arrays.asList(q_4592_V.e_4240_b);
    private static final List<q_1613_l> Y_601_j = Arrays.asList(q_4592_V.z_1737_N, q_4592_V.d_2427_y, q_4592_V.n_3318_d, q_4592_V.H_1873_g, q_4592_V.g_134_G, q_4592_V.w_1474_C, q_4592_V.X_2960_b, q_4592_V.p_1977_n, q_4592_V.v_4276_D, q_4592_V.w_2749_z, q_4592_V.f_1186_l, q_4592_V.u_3578_p);

    public J_3907_o() {
        super("InvManager", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G event) {
        if (J_3907_o.c_3005_b.Y_259_p == null) {
            return;
        }
        if ((J_3907_o.c_3005_b.Y_1740_V == null || J_3907_o.c_3005_b.Y_1740_V instanceof Q_1939_l) && System.currentTimeMillis() - this.s_956_w >= ((Float)this.v_4262_N.J_1907_R()).longValue()) {
            int startSlot;
            for (int i = startSlot = this.t_148_a.t_148_a() != false ? 0 : 9; i < 36; ++i) {
                Z_1993_T stack = J_3907_o.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (stack.n_1700_B() || !this.n_1700_B(stack)) continue;
                int containerSlot = i < 9 ? i + 36 : i;
                J_3907_o.c_3005_b.w_1457_N.windowClick(J_3907_o.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, 1, a_408_T.P_1922_E, J_3907_o.c_3005_b.Y_259_p);
                this.s_956_w = System.currentTimeMillis();
                return;
            }
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        if (this.w_1484_f.J_1907_R("\u041f\u0430\u0443\u0442\u0438\u043d\u0430") != null && this.w_1484_f.J_1907_R("\u041f\u0430\u0443\u0442\u0438\u043d\u0430").booleanValue() && u_2550_I.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u041d\u0438\u0442\u043a\u0438") != null && this.w_1484_f.J_1907_R("\u041d\u0438\u0442\u043a\u0438").booleanValue() && M_588_G.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u0421\u0442\u0440\u0435\u043b\u044b") != null && this.w_1484_f.J_1907_R("\u0421\u0442\u0440\u0435\u043b\u044b").booleanValue() && P_4830_p.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c") != null && this.w_1484_f.J_1907_R("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c").booleanValue() && h_1847_R.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u041a\u043e\u0441\u0442\u0438") != null && this.w_1484_f.J_1907_R("\u041a\u043e\u0441\u0442\u0438").booleanValue() && Q_4569_t.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u041f\u043e\u0440\u043e\u0445") != null && this.w_1484_f.J_1907_R("\u041f\u043e\u0440\u043e\u0445").booleanValue() && M_182_A.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u0423\u0433\u043e\u043b\u044c") != null && this.w_1484_f.J_1907_R("\u0423\u0433\u043e\u043b\u044c").booleanValue() && t_1786_h.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u041a\u0440\u0435\u043c\u0435\u043d\u044c") != null && this.w_1484_f.J_1907_R("\u041a\u0440\u0435\u043c\u0435\u043d\u044c").booleanValue() && N_4405_n.contains(item)) {
            return true;
        }
        if (this.w_1484_f.J_1907_R("\u0413\u0440\u0430\u0432\u0438\u0439") != null && this.w_1484_f.J_1907_R("\u0413\u0440\u0430\u0432\u0438\u0439").booleanValue() && w_1457_N.contains(item)) {
            return true;
        }
        return this.w_1484_f.J_1907_R("\u0420\u0443\u0434\u044b") != null && this.w_1484_f.J_1907_R("\u0420\u0443\u0434\u044b") != false && Y_601_j.contains(item);
    }
}

