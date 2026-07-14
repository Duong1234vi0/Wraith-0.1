/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.RandomStringUtils
 */
package lightning.product;

import java.util.Random;
import lightning.product.F_747_P;
import lightning.product.I_686_h;
import lightning.product.N_4463_r;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.u_925_K;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;
import org.apache.commons.lang3.RandomStringUtils;

public class J_2666_z
extends X_3546_T {
    private final N_4463_r v_4262_N = new N_4463_r("\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u044f", new p_1977_n("\u041f\u0440\u044b\u0436\u043e\u043a", false), new p_1977_n("\u041a\u043e\u043c\u0430\u043d\u0434\u0430", true), new p_1977_n("\u041a\u0430\u0447\u0430\u043d\u0438\u0435 \u0440\u0443\u043a\u043e\u0439", false), new p_1977_n("\u041f\u043e\u0432\u043e\u0440\u043e\u0442 \u043a\u0430\u043c\u0435\u0440\u044b", false), new p_1977_n("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435", false));
    private final I_686_h w_1484_f = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 20.0f, 1.0f, 120.0f, 1.0f);
    private final p_1977_n t_148_a = new p_1977_n("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430", false);
    private final I_686_h s_956_w = new I_686_h("\u041c\u0438\u043d. \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 15.0f, 5.0f, 60.0f, 1.0f, () -> this.t_148_a.t_148_a());
    private final I_686_h u_2550_I = new I_686_h("\u041c\u0430\u043a\u0441. \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u0441\u0435\u043a)", 30.0f, 10.0f, 120.0f, 1.0f, () -> this.t_148_a.t_148_a());
    private final q_366_O M_588_G = new q_366_O("\u0422\u0438\u043f \u043a\u043e\u043c\u0430\u043d\u0434\u044b", "\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f", () -> this.v_4262_N.J_1907_R("\u041a\u043e\u043c\u0430\u043d\u0434\u0430"), "\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u0430\u044f");
    private final I_686_h P_4830_p = new I_686_h("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u044b\u0436\u043a\u043e\u0432", 1.0f, 1.0f, 5.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u041f\u0440\u044b\u0436\u043e\u043a"));
    private final I_686_h h_1847_R = new I_686_h("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f (\u0442\u0438\u043a)", 10.0f, 5.0f, 40.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435"));
    private final p_1977_n Q_4569_t = new p_1977_n("\u0421\u0431\u0440\u043e\u0441 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", true);
    private final V_4557_X M_182_A = new V_4557_X();
    private final Random t_1786_h = new Random();
    private long N_4405_n = 20000L;
    private int w_1457_N = 0;
    private int Y_601_j = 0;

    public J_2666_z() {
        super("AntiAFK", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Override
    public void n_1700_B() {
        this.M_182_A.n_1700_B();
        this.N_4405_n = (long)(((Float)this.w_1484_f.J_1907_R()).floatValue() * 1000.0f);
        super.n_1700_B();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (J_2666_z.c_3005_b.Y_259_p == null || J_2666_z.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.w_1457_N > 0) {
            this.P_1922_E(true);
            --this.w_1457_N;
            return;
        }
        if (this.w_1457_N == 0 && this.Y_601_j != -1) {
            this.P_1922_E(false);
            this.Y_601_j = -1;
        }
        if (this.Q_4569_t.t_148_a().booleanValue() && u_925_K.n_1700_B()) {
            this.M_182_A.n_1700_B();
            this.N_4405_n = this.h_1847_R();
            return;
        }
        if (this.M_182_A.n_1700_B((double)this.N_4405_n)) {
            this.Q_4569_t();
            this.M_182_A.n_1700_B();
            this.N_4405_n = this.h_1847_R();
        }
    }

    private void P_1922_E(boolean pressed) {
        J_2666_z.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        J_2666_z.c_3005_b.P_4830_p.A_1038_p.n_1700_B(false);
        J_2666_z.c_3005_b.P_4830_p.r_715_M.n_1700_B(false);
        J_2666_z.c_3005_b.P_4830_p.i_1637_u.n_1700_B(false);
        if (!pressed) {
            return;
        }
        switch (this.Y_601_j) {
            case 0: {
                J_2666_z.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                break;
            }
            case 1: {
                J_2666_z.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                break;
            }
            case 2: {
                J_2666_z.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 3: {
                J_2666_z.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
                break;
            }
            case 4: {
                J_2666_z.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                J_2666_z.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 5: {
                J_2666_z.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                J_2666_z.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
                break;
            }
            case 6: {
                J_2666_z.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                J_2666_z.c_3005_b.P_4830_p.r_715_M.n_1700_B(true);
                break;
            }
            case 7: {
                J_2666_z.c_3005_b.P_4830_p.A_1038_p.n_1700_B(true);
                J_2666_z.c_3005_b.P_4830_p.i_1637_u.n_1700_B(true);
            }
        }
    }

    private long h_1847_R() {
        if (this.t_148_a.t_148_a().booleanValue()) {
            float min = ((Float)this.s_956_w.J_1907_R()).floatValue();
            float max = ((Float)this.u_2550_I.J_1907_R()).floatValue();
            float randomValue = F_747_P.G_564_y(min, max);
            return (long)(randomValue * 1000.0f);
        }
        return (long)(((Float)this.w_1484_f.J_1907_R()).floatValue() * 1000.0f);
    }

    private void Q_4569_t() {
        if (this.v_4262_N.J_1907_R("\u041f\u0440\u044b\u0436\u043e\u043a").booleanValue() && J_2666_z.c_3005_b.Y_259_p.M_1641_O()) {
            int jumps = (int)((Float)this.P_4830_p.J_1907_R()).floatValue();
            for (int i = 0; i < jumps; ++i) {
                if (!J_2666_z.c_3005_b.Y_259_p.M_1641_O()) continue;
                J_2666_z.c_3005_b.Y_259_p.e_837_t();
            }
        }
        if (this.v_4262_N.J_1907_R("\u041a\u043e\u043c\u0430\u043d\u0434\u0430").booleanValue()) {
            J_2666_z.c_3005_b.Y_259_p.n_1700_B("/" + RandomStringUtils.randomAlphabetic((int)5));
        }
        if (this.v_4262_N.J_1907_R("\u041a\u0430\u0447\u0430\u043d\u0438\u0435 \u0440\u0443\u043a\u043e\u0439").booleanValue()) {
            J_2666_z.c_3005_b.Y_259_p.n_1700_B(this.t_1786_h.nextBoolean() ? x_1688_C.n_1700_B : x_1688_C.J_1907_R);
        }
        if (this.v_4262_N.J_1907_R("\u041f\u043e\u0432\u043e\u0440\u043e\u0442 \u043a\u0430\u043c\u0435\u0440\u044b").booleanValue()) {
            float randomYaw = this.t_1786_h.nextFloat() * 360.0f;
            float randomPitch = this.t_1786_h.nextFloat() * 180.0f - 90.0f;
            J_2666_z.c_3005_b.Y_259_p.p_178_J = randomYaw;
            J_2666_z.c_3005_b.Y_259_p.f_4016_n = randomPitch;
        }
        if (this.v_4262_N.J_1907_R("\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u043e\u0435 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435").booleanValue()) {
            this.Y_601_j = this.t_1786_h.nextInt(8);
            this.w_1457_N = (int)((Float)this.h_1847_R.J_1907_R()).floatValue();
        }
    }

    @Override
    public void J_1907_R() {
        this.M_182_A.n_1700_B();
        if (J_2666_z.c_3005_b.P_4830_p != null) {
            J_2666_z.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
            J_2666_z.c_3005_b.P_4830_p.A_1038_p.n_1700_B(false);
            J_2666_z.c_3005_b.P_4830_p.r_715_M.n_1700_B(false);
            J_2666_z.c_3005_b.P_4830_p.i_1637_u.n_1700_B(false);
        }
        this.w_1457_N = 0;
        this.Y_601_j = -1;
        super.J_1907_R();
    }
}

