/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.K_4719_o;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.k_2610_C;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;

public class c_4453_h
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Night Vision", "Night Vision", "Gamma", "\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430", "\u0422\u0435\u043c\u043d\u044b\u0439");
    private final I_686_h w_1484_f = new I_686_h("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0413\u0430\u043c\u043c\u044b", 1.0f, 1.0f, 10.0f, 1.0f, () -> this.v_4262_N.J_1907_R("Gamma"));
    private final I_686_h t_148_a = new I_686_h("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u044f", 5.0f, 1.0f, 15.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430"));
    private final I_686_h s_956_w = new I_686_h("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u0442\u0435\u043c\u043d\u043e\u0442\u044b", 0.0f, 0.0f, 0.5f, 0.1f, () -> this.v_4262_N.J_1907_R("\u0422\u0435\u043c\u043d\u044b\u0439"));
    private final p_1977_n u_2550_I = new p_1977_n("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u043e\u0447\u044c\u044e", false);
    private final p_1977_n M_588_G = new p_1977_n("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u043f\u0435\u0449\u0435\u0440\u0430\u0445", false);
    private float P_4830_p = 1.0f;
    private boolean h_1847_R = false;

    public c_4453_h() {
        super("FullBright", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Override
    public void n_1700_B() {
        if (c_4453_h.c_3005_b.P_4830_p != null) {
            this.P_4830_p = (float)c_4453_h.c_3005_b.P_4830_p.c_132_F;
        }
        if (c_4453_h.c_3005_b.Y_259_p != null) {
            this.h_1847_R = c_4453_h.c_3005_b.Y_259_p.J_1907_R(J_588_u.M_182_A);
        }
        super.n_1700_B();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (c_4453_h.c_3005_b.Y_259_p == null || c_4453_h.c_3005_b.P_4830_p == null || c_4453_h.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.u_2550_I.t_148_a().booleanValue() && !this.h_1847_R()) {
            this.M_182_A();
            return;
        }
        if (this.M_588_G.t_148_a().booleanValue() && !this.Q_4569_t()) {
            this.M_182_A();
            return;
        }
        if (this.v_4262_N.J_1907_R("Gamma")) {
            this.t_1786_h();
        } else if (this.v_4262_N.J_1907_R("Night Vision")) {
            this.N_4405_n();
        } else if (this.v_4262_N.J_1907_R("\u041f\u043b\u0430\u0432\u043d\u043e\u0435 \u043e\u0441\u0432\u0435\u0449\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430")) {
            this.w_1457_N();
        } else if (this.v_4262_N.J_1907_R("\u0422\u0435\u043c\u043d\u044b\u0439")) {
            this.Y_601_j();
        }
    }

    private boolean h_1847_R() {
        if (c_4453_h.c_3005_b.Y_601_j == null) {
            return false;
        }
        long dayTime = c_4453_h.c_3005_b.Y_601_j.Z_976_R() % 24000L;
        return dayTime >= 13000L && dayTime < 23000L;
    }

    private boolean Q_4569_t() {
        if (c_4453_h.c_3005_b.Y_601_j == null || c_4453_h.c_3005_b.Y_259_p == null) {
            return false;
        }
        c_1514_x playerPos = c_4453_h.c_3005_b.Y_259_p.b_2312_j();
        int skyLight = c_4453_h.c_3005_b.Y_601_j.getLightFor(K_4719_o.n_1700_B, playerPos);
        return skyLight <= 3;
    }

    private void M_182_A() {
        if (c_4453_h.c_3005_b.P_4830_p != null) {
            c_4453_h.c_3005_b.P_4830_p.c_132_F = this.P_4830_p;
        }
        if (c_4453_h.c_3005_b.Y_259_p != null && !this.h_1847_R) {
            c_4453_h.c_3005_b.Y_259_p.G_564_y(J_588_u.M_182_A);
        }
    }

    private void t_1786_h() {
        c_1514_x p = c_4453_h.c_3005_b.Y_259_p.b_2312_j();
        int lvl = Math.max(c_4453_h.c_3005_b.Y_601_j.getLightFor(K_4719_o.J_1907_R, p), c_4453_h.c_3005_b.Y_601_j.getLightFor(K_4719_o.n_1700_B, p));
        double t = 1.2 + Math.pow(1.0 - (double)lvl / 15.0, 2.0) * 10.8;
        c_4453_h.c_3005_b.P_4830_p.c_132_F += (u_530_F.n_1700_B(t, 1.2, 12.0) - c_4453_h.c_3005_b.P_4830_p.c_132_F) * 0.3;
        c_4453_h.c_3005_b.Y_259_p.G_564_y(J_588_u.M_182_A);
    }

    private void N_4405_n() {
        c_4453_h.c_3005_b.P_4830_p.c_132_F = 0.0;
        c_4453_h.c_3005_b.Y_259_p.n_1700_B(new k_2610_C(J_588_u.M_182_A, 999999999, 1));
    }

    private void w_1457_N() {
        float targetGamma = ((Float)this.t_148_a.J_1907_R()).floatValue();
        if (c_4453_h.c_3005_b.P_4830_p.c_132_F < (double)targetGamma) {
            c_4453_h.c_3005_b.P_4830_p.c_132_F = Math.min(c_4453_h.c_3005_b.P_4830_p.c_132_F + (double)0.1f, (double)targetGamma);
        } else if (c_4453_h.c_3005_b.P_4830_p.c_132_F > (double)targetGamma) {
            c_4453_h.c_3005_b.P_4830_p.c_132_F = Math.max(c_4453_h.c_3005_b.P_4830_p.c_132_F - (double)0.1f, (double)targetGamma);
        }
        c_4453_h.c_3005_b.Y_259_p.G_564_y(J_588_u.M_182_A);
    }

    private void Y_601_j() {
        float targetGamma = ((Float)this.s_956_w.J_1907_R()).floatValue();
        if (c_4453_h.c_3005_b.P_4830_p.c_132_F > (double)targetGamma) {
            c_4453_h.c_3005_b.P_4830_p.c_132_F = Math.max(c_4453_h.c_3005_b.P_4830_p.c_132_F - (double)0.1f, (double)targetGamma);
        } else if (c_4453_h.c_3005_b.P_4830_p.c_132_F < (double)targetGamma) {
            c_4453_h.c_3005_b.P_4830_p.c_132_F = Math.min(c_4453_h.c_3005_b.P_4830_p.c_132_F + (double)0.1f, (double)targetGamma);
        }
        c_4453_h.c_3005_b.Y_259_p.G_564_y(J_588_u.M_182_A);
    }

    @Override
    public void J_1907_R() {
        if (c_4453_h.c_3005_b.Y_259_p == null || c_4453_h.c_3005_b.P_4830_p == null) {
            return;
        }
        c_4453_h.c_3005_b.P_4830_p.c_132_F = this.P_4830_p;
        if (this.v_4262_N.J_1907_R("Night Vision") || !this.h_1847_R) {
            c_4453_h.c_3005_b.Y_259_p.G_564_y(J_588_u.M_182_A);
        }
        super.J_1907_R();
    }
}

