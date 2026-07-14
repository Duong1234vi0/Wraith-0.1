/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_3014_r;
import lightning.product.G_3416_z;
import lightning.product.H_2034_c;
import lightning.product.I_3710_B;
import lightning.product.N_3268_u;
import lightning.product.T_3952_j;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.g_4727_e;
import lightning.product.h_1015_G;
import lightning.product.k_4690_i;
import lightning.product.m_2262_U;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.u_1934_K;
import lightning.product.v_887_r;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class y_3712_F
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u041c\u043e\u0434", "GrimJump", "GrimJump", "MLG", "GrimFlag", "Hay Bale", "Elytra");
    private boolean w_1484_f;
    private boolean t_148_a;

    public y_3712_F() {
        super("NoFall", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (y_3712_F.c_3005_b.Y_259_p == null || y_3712_F.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("GrimJump")) {
            boolean bl = this.w_1484_f = !y_3712_F.c_3005_b.Y_259_p.M_1641_O() && y_3712_F.c_3005_b.Y_259_p.U_1241_n > 2.5f;
        }
        if (this.v_4262_N.J_1907_R("Elytra")) {
            if (y_3712_F.c_3005_b.Y_259_p.U_1241_n <= 3.0f) {
                return;
            }
            if (y_3712_F.c_3005_b.Y_259_p.k_578_l()) {
                return;
            }
            int elytraSlot = E_3014_r.n_1700_B(q_4592_V.B_1548_Z);
            if (elytraSlot == -1) {
                return;
            }
            if (y_3712_F.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != q_4592_V.B_1548_Z) {
                int chestPlateSlot = E_3014_r.M_182_A();
                if (chestPlateSlot != -1) {
                    E_3014_r.n_1700_B(chestPlateSlot, 6);
                } else {
                    E_3014_r.n_1700_B(elytraSlot, 6);
                }
            }
            if (y_3712_F.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z && g_4727_e.G_564_y(y_3712_F.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E))) {
                y_3712_F.c_3005_b.Y_259_p.y_2447_C();
                y_3712_F.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(y_3712_F.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        if (y_3712_F.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("GrimJump")) {
            if (!this.w_1484_f || !y_3712_F.c_3005_b.Y_259_p.M_1641_O()) {
                return;
            }
            event.n_1700_B(true);
            y_3712_F.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u(true));
            this.w_1484_f = false;
            return;
        }
        if (this.v_4262_N.J_1907_R("GrimFlag")) {
            event.J_1907_R(false);
            if (y_3712_F.c_3005_b.P_4830_p.k_3961_g.G_564_y()) {
                double add = y_3712_F.c_3005_b.Y_259_p.t_2577_l % 2 != 1 ? (double)0.08f : (double)0.05f;
                y_3712_F.c_3005_b.Y_259_p.h_1847_R(y_3712_F.c_3005_b.Y_259_p.I_4348_c().J_1907_R, y_3712_F.c_3005_b.Y_259_p.I_4348_c().R_4764_Y + add, y_3712_F.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
            return;
        }
        if (this.v_4262_N.J_1907_R("MLG")) {
            if (y_3712_F.c_3005_b.Y_259_p.U_1241_n <= 5.0f) {
                return;
            }
            e_2866_D start = y_3712_F.c_3005_b.Y_259_p.s_4990_V();
            e_2866_D end = start.J_1907_R(0.0, -15.0, 0.0);
            G_3416_z result = y_3712_F.c_3005_b.Y_601_j.n_1700_B(new H_2034_c(start, end, H_2034_c.n_1700_B.n_1700_B, H_2034_c.J_1907_R.n_1700_B, y_3712_F.c_3005_b.Y_259_p));
            int waterSlot = u_1934_K.n_1700_B(q_4592_V.W_2770_z);
            if (result != null && result.R_4764_Y() == I_3710_B.n_1700_B.J_1907_R && waterSlot >= 0) {
                int oldSlot = y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                event.J_1907_R(86.0f);
                y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y = waterSlot;
                y_3712_F.c_3005_b.w_1457_N.processRightClick(y_3712_F.c_3005_b.Y_259_p, y_3712_F.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlot;
            }
            return;
        }
        if (this.v_4262_N.J_1907_R("Hay Bale")) {
            if (y_3712_F.c_3005_b.Y_259_p.U_1241_n <= 5.0f) {
                return;
            }
            e_2866_D start = y_3712_F.c_3005_b.Y_259_p.s_4990_V();
            e_2866_D end = start.J_1907_R(0.0, -15.0, 0.0);
            G_3416_z result = y_3712_F.c_3005_b.Y_601_j.n_1700_B(new H_2034_c(start, end, H_2034_c.n_1700_B.n_1700_B, H_2034_c.J_1907_R.n_1700_B, y_3712_F.c_3005_b.Y_259_p));
            int haySlot = u_1934_K.n_1700_B(q_4592_V.v_3080_v);
            if (result != null && result.R_4764_Y() == I_3710_B.n_1700_B.J_1907_R && haySlot >= 0) {
                int oldSlot = y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                event.J_1907_R(86.0f);
                y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y = haySlot;
                if (y_3712_F.c_3005_b.Y_601_j instanceof k_4690_i) {
                    y_3712_F.c_3005_b.w_1457_N.func_217292_a(y_3712_F.c_3005_b.Y_259_p, y_3712_F.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
                }
                y_3712_F.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlot;
            }
            return;
        }
    }

    @Y_1740_V
    public void n_1700_B(v_887_r event) {
        if (!this.v_4262_N.J_1907_R("GrimJump")) {
            return;
        }
        if (event.J_1907_R() == v_887_r.n_1700_B.n_1700_B) {
            this.t_148_a = true;
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (!this.v_4262_N.J_1907_R("GrimJump")) {
            return;
        }
        if (y_3712_F.c_3005_b.Y_259_p == null || !this.t_148_a) {
            return;
        }
        event.P_1922_E(true);
        this.t_148_a = false;
    }
}

