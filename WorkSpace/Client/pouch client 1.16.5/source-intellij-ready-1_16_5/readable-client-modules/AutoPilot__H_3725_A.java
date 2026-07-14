/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_2157_Z;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.d_2992_c;
import lightning.product.g_4727_e;
import lightning.product.h_1015_G;
import lightning.product.n_1494_c;
import lightning.product.o_12_W;
import lightning.product.p_1977_n;
import lightning.product.q_4592_V;
import lightning.product.u_1403_d;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;

public class H_3725_A
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u0428\u0430\u0440", true);
    private final p_1977_n w_1484_f = new p_1977_n("\u042d\u043b\u0438\u0442\u0440\u0430", true);
    private final p_1977_n t_148_a = new p_1977_n("\u041e\u0441\u043a\u043e\u043b\u043e\u043a", true);
    private final p_1977_n s_956_w = new p_1977_n("\u041e\u0441\u0442\u0440\u043e\u0442\u0430 VI", false);
    private final p_1977_n u_2550_I = new p_1977_n("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0451\u0442", false);
    private final p_1977_n M_588_G = new p_1977_n("\u0410\u0443\u0440\u0430", true);

    public H_3725_A() {
        super("AutoPilot", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (H_3725_A.c_3005_b.Y_259_p == null || H_3725_A.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v entity : H_3725_A.c_3005_b.Y_601_j.J_1907_R()) {
            float[] r;
            if (!(entity instanceof n_1494_c)) continue;
            n_1494_c itemEntity = (n_1494_c)entity;
            Z_1993_T itemStack = itemEntity.P_1922_E();
            String displayName = itemStack.N_4405_n().getString();
            if (this.v_4262_N.t_148_a().booleanValue() && itemStack.J_1907_R() instanceof o_12_W) {
                r = this.n_1700_B(entity);
                H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
                H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.w_1484_f.t_148_a().booleanValue() && itemStack.J_1907_R() instanceof g_4727_e) {
                r = this.n_1700_B(entity);
                H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
                H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.t_148_a.t_148_a().booleanValue() && itemStack.J_1907_R() == q_4592_V.b_3334_n && displayName.contains("\u041e\u0441\u043a\u043e\u043b\u043e\u043a")) {
                r = this.n_1700_B(entity);
                H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
                H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.s_956_w.t_148_a().booleanValue() && (itemStack.J_1907_R() instanceof u_1403_d || itemStack.J_1907_R() instanceof B_2157_Z) && K_4096_w.n_1700_B(d_2992_c.P_4830_p, itemStack) >= 6) {
                r = this.n_1700_B(entity);
                H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
                H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.u_2550_I.t_148_a().booleanValue() && itemStack.J_1907_R() == q_4592_V.a_2319_C && displayName.contains("\u0410\u043d\u0442\u0438 \u041f\u043e\u043b\u0451\u0442")) {
                r = this.n_1700_B(entity);
                H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
                H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (!this.M_588_G.t_148_a().booleanValue() || !(itemStack.J_1907_R() == q_4592_V.E_2115_e && displayName.contains("\u0410\u0443\u0440\u0430 \u041e\u0445\u043e\u0442\u043d\u0438\u043a\u0430") || itemStack.J_1907_R() == q_4592_V.i_4833_u && displayName.contains("\u0410\u0443\u0440\u0430 \u0422\u0432\u0451\u0440\u0434\u043e\u0441\u0442\u0438 \u0411\u0440\u043e\u043d\u0438") || itemStack.J_1907_R() == q_4592_V.k_135_a && displayName.contains("\u0410\u0443\u0440\u0430 \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438") || itemStack.J_1907_R() == q_4592_V.u_3578_p && displayName.contains("\u0410\u0443\u0440\u0430 \u0411\u043e\u0433\u0430\u0447\u0430") || itemStack.J_1907_R() == q_4592_V.L_630_w && displayName.contains("\u0410\u0443\u0440\u0430 \u0417\u0430\u0449\u0438\u0442\u044b \u041e\u0442 \u041f\u0430\u0434\u0435\u043d\u0438\u044f")) && (itemStack.J_1907_R() != q_4592_V.b_3334_n || !displayName.contains("\u0410\u0443\u0440\u0430 \u0417\u0430\u0449\u0438\u0442\u044b \u041e\u0442 \u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u043e\u0432"))) continue;
            r = this.n_1700_B(entity);
            H_3725_A.c_3005_b.Y_259_p.p_178_J = r[0];
            H_3725_A.c_3005_b.Y_259_p.f_4016_n = r[1];
        }
    }

    public float[] n_1700_B(N_4263_v entity) {
        double x = entity.O_3598_v() - H_3725_A.c_3005_b.Y_259_p.O_3598_v();
        double y = entity.X_2960_b() - H_3725_A.c_3005_b.Y_259_p.X_2960_b() - 1.0;
        double z = entity.l_2647_k() - H_3725_A.c_3005_b.Y_259_p.l_2647_k();
        double u = u_530_F.n_1700_B(x * x + z * z);
        float yaw = (float)(u_530_F.G_564_y(z, x) * 57.29577951308232 - 90.0);
        float pitch = (float)(-u_530_F.G_564_y(y, u) * 57.29577951308232);
        return new float[]{yaw, pitch};
    }
}

