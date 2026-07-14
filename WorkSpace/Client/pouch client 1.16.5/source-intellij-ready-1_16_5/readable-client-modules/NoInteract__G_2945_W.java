/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lightning.product.N_4463_r;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.o_1800_r;
import lightning.product.p_1977_n;
import lightning.product.q_4592_V;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class G_2945_W
extends X_3546_T {
    public p_1977_n v_4262_N = new p_1977_n("\u041d\u0435 \u0441\u0442\u0430\u0432\u0438\u0442\u044c \u0448\u0430\u0440,\u0441\u0444\u0435\u0440\u0443", false);
    public p_1977_n w_1484_f = new p_1977_n("\u0412\u0441\u0435 \u0431\u043b\u043e\u043a\u0438", false);
    public N_4463_r t_148_a = new N_4463_r("\u041e\u0431\u044c\u0435\u043a\u0442\u044b", () -> this.w_1484_f.t_148_a() == false, new p_1977_n("\u0421\u0442\u043e\u0439\u043a\u0438", true), new p_1977_n("\u0421\u0443\u043d\u0434\u0443\u043a\u0438", true), new p_1977_n("\u0414\u0432\u0435\u0440\u0438", true), new p_1977_n("\u041a\u043d\u043e\u043f\u043a\u0438", true), new p_1977_n("\u0412\u043e\u0440\u043e\u043d\u043a\u0438", true), new p_1977_n("\u0420\u0430\u0437\u0434\u0430\u0442\u0447\u0438\u043a\u0438", true), new p_1977_n("\u041d\u043e\u0442\u043d\u044b\u0435 \u0431\u043b\u043e\u043a\u0438", true), new p_1977_n("\u0412\u0435\u0440\u0441\u0442\u0430\u043a\u0438", true), new p_1977_n("\u041b\u044e\u043a\u0438", true), new p_1977_n("\u041f\u0435\u0447\u043a\u0438", true), new p_1977_n("\u041a\u0430\u043b\u0438\u0442\u043a\u0438", true), new p_1977_n("\u041d\u0430\u043a\u043e\u0432\u0430\u043b\u044c\u043d\u0438", true), new p_1977_n("\u0420\u044b\u0447\u0430\u0433\u0438", true));

    public G_2945_W() {
        super("NoInteract", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    public Set<T_2915_h> h_1847_R() {
        HashSet<T_2915_h> blocks = new HashSet<T_2915_h>();
        this.n_1700_B(blocks, "\u0414\u0432\u0435\u0440\u0438", a_3742_W.D_1525_M, a_3742_W.Q_79_h, a_3742_W.s_2345_S, a_3742_W.h_2739_B, a_3742_W.z_1063_O, a_3742_W.F_518_D, a_3742_W.r_3030_V);
        this.n_1700_B(blocks, "\u041a\u043d\u043e\u043f\u043a\u0438", a_3742_W.V_537_k, a_3742_W.o_3599_Z, a_3742_W.V_3982_O, a_3742_W.k_200_a);
        this.n_1700_B(blocks, "\u0421\u0443\u043d\u0434\u0443\u043a\u0438", a_3742_W.L_1362_X, a_3742_W.I_686_h, a_3742_W.k_2348_i);
        this.n_1700_B(blocks, "\u0412\u043e\u0440\u043e\u043d\u043a\u0438", a_3742_W.p_3749_n);
        this.n_1700_B(blocks, "\u0420\u0430\u0437\u0434\u0430\u0442\u0447\u0438\u043a\u0438", a_3742_W.h_4811_f, a_3742_W.c_1732_c);
        this.n_1700_B(blocks, "\u041d\u043e\u0442\u043d\u044b\u0435 \u0431\u043b\u043e\u043a\u0438", a_3742_W.L_2871_u);
        this.n_1700_B(blocks, "\u0412\u0435\u0440\u0441\u0442\u0430\u043a\u0438", a_3742_W.O_2934_T, a_3742_W.j_1376_w, a_3742_W.i_4833_u, a_3742_W.m_1628_s);
        this.n_1700_B(blocks, "\u041b\u044e\u043a\u0438", a_3742_W.q_817_e, a_3742_W.q_2475_j);
        this.n_1700_B(blocks, "\u041f\u0435\u0447\u043a\u0438", a_3742_W.P_925_e, a_3742_W.F_2052_z, a_3742_W.H_2506_c);
        this.n_1700_B(blocks, "\u041a\u0430\u043b\u0438\u0442\u043a\u0438", a_3742_W.I_3289_C, a_3742_W.c_2829_Q, a_3742_W.A_4727_d, a_3742_W.I_4367_m, a_3742_W.k_3129_Y, a_3742_W.m_4661_u);
        this.n_1700_B(blocks, "\u041d\u0430\u043a\u043e\u0432\u0430\u043b\u044c\u043d\u0438", a_3742_W.c_1608_O);
        this.n_1700_B(blocks, "\u0420\u044b\u0447\u0430\u0433\u0438", a_3742_W.x_92_N);
        return blocks;
    }

    private void n_1700_B(Set<T_2915_h> blocks, String interactionType, T_2915_h ... blockIds) {
        if (this.t_148_a.J_1907_R(interactionType).booleanValue()) {
            Collections.addAll(blocks, blockIds);
        }
    }

    @Y_1740_V
    private void n_1700_B(o_1800_r e) {
        if (this.v_4262_N.t_148_a().booleanValue() && e.G_564_y() == x_1688_C.J_1907_R && G_2945_W.c_3005_b.Y_259_p.S_4035_N().J_1907_R() == q_4592_V.C_3560_B) {
            e.n_1700_B(true);
        }
        if (this.w_1484_f.t_148_a().booleanValue()) {
            e.n_1700_B(true);
        } else if (this.h_1847_R().contains(e.R_4764_Y().getBlockState(e.P_1922_E().n_1700_B()).J_1907_R())) {
            e.n_1700_B(true);
        }
    }
}

