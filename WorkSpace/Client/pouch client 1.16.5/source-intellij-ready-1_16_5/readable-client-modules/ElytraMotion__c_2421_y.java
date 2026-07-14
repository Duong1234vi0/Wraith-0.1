/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3698_k;
import lightning.product.F_747_P;
import lightning.product.I_686_h;
import lightning.product.M_2562_s;
import lightning.product.N_4263_v;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_4592_V;
import lightning.product.r_3979_X;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.y_2603_k;

public class c_2421_y
extends X_3546_T {
    public final I_686_h v_4262_N = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b", 3.0f, 0.1f, 5.0f, 0.1f);
    private final p_1977_n t_148_a = new p_1977_n("\u0410\u0432\u0442\u043e-\u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", false);
    private final V_4557_X s_956_w = new V_4557_X();
    public boolean w_1484_f;

    public c_2421_y() {
        super("ElytraMotion", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        F_3698_k elytraTarget;
        if (!c_2421_y.c_3005_b.Y_259_p.k_578_l()) {
            this.w_1484_f = false;
            return;
        }
        r_3979_X killAura = o_148_s.Y_601_j().J_1907_R().J_1907_R();
        if (this.n_1700_B(killAura, elytraTarget = o_148_s.Y_601_j().J_1907_R().R_4764_Y())) {
            c_2421_y.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
            this.w_1484_f = true;
        } else {
            c_2421_y.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
            this.w_1484_f = false;
        }
        if (this.t_148_a.t_148_a().booleanValue() && killAura.h_1847_R() != null && F_747_P.J_1907_R(c_2421_y.c_3005_b.Y_259_p) <= 30.0 && this.s_956_w.J_1907_R(500L)) {
            u_1934_K.w_1484_f(q_4592_V.B_1305_J);
            this.s_956_w.n_1700_B();
        }
    }

    @Y_1740_V
    public void n_1700_B(M_2562_s e) {
        if (this.w_1484_f) {
            e.R_4764_Y(new e_2866_D(0.0, 0.0, 0.0));
        }
    }

    public boolean n_1700_B(r_3979_X killAura, F_3698_k elytraTarget) {
        r_4811_B target = killAura.h_1847_R();
        if (target == null) {
            return false;
        }
        boolean canTarget = elytraTarget != null && elytraTarget.h_1847_R() && c_2421_y.c_3005_b.Y_259_p.k_578_l() && target.k_578_l();
        return !canTarget && target.R_4764_Y((N_4263_v)c_2421_y.c_3005_b.Y_259_p) < ((Float)this.v_4262_N.J_1907_R()).floatValue();
    }

    @Override
    public void J_1907_R() {
        this.w_1484_f = false;
        super.J_1907_R();
    }
}

