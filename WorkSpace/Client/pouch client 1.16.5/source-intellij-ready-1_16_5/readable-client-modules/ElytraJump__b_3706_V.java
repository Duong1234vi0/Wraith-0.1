/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_3014_r;
import lightning.product.F_747_P;
import lightning.product.T_3952_j;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.g_4727_e;
import lightning.product.h_1015_G;
import lightning.product.m_2262_U;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;

public class b_3706_V
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "BravoFFA", "BravoFFA", "ReallyWorld", "LonyGrief");
    private final p_1977_n w_1484_f = new p_1977_n("\u0421\u0432\u0430\u043f\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u0443", false);
    private boolean t_148_a = false;
    private float s_956_w = 0.0f;

    public b_3706_V() {
        super("ElytraJump", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (b_3706_V.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!(b_3706_V.c_3005_b.Y_259_p.C_415_h.J_1907_R || !b_3706_V.c_3005_b.Y_259_p.M_1641_O() || b_3706_V.c_3005_b.Y_259_p.a_2180_A() || b_3706_V.c_3005_b.Y_259_p.W_3464_O() || b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != q_4592_V.B_1548_Z || b_3706_V.c_3005_b.P_4830_p.T_69_K.G_564_y())) {
            b_3706_V.c_3005_b.Y_259_p.e_837_t();
        }
        if (!(b_3706_V.c_3005_b.Y_259_p.C_415_h.J_1907_R || b_3706_V.c_3005_b.Y_259_p.M_1641_O() || b_3706_V.c_3005_b.Y_259_p.a_2180_A() || b_3706_V.c_3005_b.Y_259_p.k_578_l() || b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != q_4592_V.B_1548_Z || !g_4727_e.G_564_y(b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E)))) {
            b_3706_V.c_3005_b.Y_259_p.y_2447_C();
            b_3706_V.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(b_3706_V.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
        }
        if (b_3706_V.c_3005_b.Y_259_p.M_1641_O() || b_3706_V.c_3005_b.Y_259_p.a_2180_A() || b_3706_V.c_3005_b.Y_259_p.W_3464_O()) {
            b_3706_V.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            this.t_148_a = false;
        }
        if (b_3706_V.c_3005_b.Y_259_p.H_3699_F > 0 && this.w_1484_f.t_148_a().booleanValue() && b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z) {
            this.Q_4569_t();
            return;
        }
        int elytraSlot = E_3014_r.n_1700_B(q_4592_V.B_1548_Z);
        if (b_3706_V.c_3005_b.Y_259_p.G_564_y != null && elytraSlot >= 0 && (elytraSlot < 9 ? elytraSlot + 36 : elytraSlot) == 38 && b_3706_V.c_3005_b.Y_259_p.G_564_y.jump && !b_3706_V.c_3005_b.Y_259_p.k_578_l()) {
            b_3706_V.c_3005_b.Y_259_p.G_564_y.jump = false;
        }
        if (b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z) {
            b_3706_V.c_3005_b.P_4830_p.T_69_K.n_1700_B(true);
            if (b_3706_V.c_3005_b.Y_259_p.k_578_l()) {
                e_2866_D motion = b_3706_V.c_3005_b.Y_259_p.I_4348_c();
                if (this.v_4262_N.J_1907_R("ReallyWorld")) {
                    b_3706_V.c_3005_b.Y_259_p.h_1847_R(motion.J_1907_R, motion.R_4764_Y + 0.02658, motion.G_564_y);
                } else if (this.v_4262_N.J_1907_R("BravoFFA") || this.v_4262_N.J_1907_R("LonyGrief")) {
                    b_3706_V.c_3005_b.Y_259_p.h_1847_R(motion.J_1907_R, motion.R_4764_Y + (double)F_747_P.G_564_y(0.06f, 0.061f), motion.G_564_y);
                }
            }
        } else if (this.w_1484_f.t_148_a().booleanValue()) {
            this.h_1847_R();
        } else {
            b_3706_V.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            this.t_148_a = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (b_3706_V.c_3005_b.Y_259_p == null || !b_3706_V.c_3005_b.Y_259_p.k_578_l()) {
            this.t_148_a = false;
            return;
        }
        if (!this.t_148_a) {
            this.t_148_a = true;
            this.s_956_w = b_3706_V.c_3005_b.Y_259_p.p_178_J;
        }
        float fixedPitch = 10.0f;
        e.n_1700_B(this.s_956_w);
        e.J_1907_R(fixedPitch);
        b_3706_V.c_3005_b.Y_259_p.p_178_J = this.s_956_w;
        b_3706_V.c_3005_b.Y_259_p.f_3449_S = this.s_956_w;
        b_3706_V.c_3005_b.Y_259_p.C_1162_e = this.s_956_w;
        b_3706_V.c_3005_b.Y_259_p.f_4016_n = fixedPitch;
    }

    private void h_1847_R() {
        int slot = E_3014_r.n_1700_B(q_4592_V.B_1548_Z);
        if (slot >= 0) {
            E_3014_r.n_1700_B(slot, 6);
        }
    }

    private void Q_4569_t() {
        int slot = E_3014_r.M_182_A();
        if (slot >= 0) {
            E_3014_r.n_1700_B(slot, 6);
        }
    }

    @Override
    public void n_1700_B() {
        if (this.w_1484_f.t_148_a().booleanValue() && b_3706_V.c_3005_b.Y_259_p != null) {
            this.h_1847_R();
        }
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        b_3706_V.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
        if (this.w_1484_f.t_148_a().booleanValue() && b_3706_V.c_3005_b.Y_259_p != null && b_3706_V.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z) {
            this.Q_4569_t();
        }
        super.J_1907_R();
    }
}

